package fr.fms.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import fr.fms.entities.Theme;

public class ThemeDao implements LibraryDao<Theme> {

	@Override
	public boolean create(Theme t) {
		String str = "INSERT INTO T_Themes (themeName) VALUES (?);";
		try (PreparedStatement ps = connection.prepareStatement(str)){
			ps.setString(1, t.getThemeName());
					
			if( ps.executeUpdate() == 1)	return true;				
		} catch (SQLException e) {
			e.printStackTrace();
		} 				
		return false;
	}

	@Override
	public List<Theme> readAll() {
		ArrayList<Theme> themes = new ArrayList<Theme>();
		String strSql = "SELECT * FROM T_Themes";		
		try(Statement statement = connection.createStatement()){
			try(ResultSet resultSet = statement.executeQuery(strSql)){ 			
				while(resultSet.next()) {
					int rsIdTheme = resultSet.getInt(1);	
					String rsThemeName = resultSet.getString(2);
					themes.add((new Theme(rsIdTheme, rsThemeName)));						
				}	
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}			
		return themes;
	}

	@Override
	public Theme readById(int id) {
		try (Statement statement = connection.createStatement()){
			String str = "SELECT * FROM T_Themes where IdTheme=" + id + ";";									
			ResultSet rs = statement.executeQuery(str);
			if(rs.next()) 
				return new Theme(rs.getInt(1) , rs.getString(2));
		} catch (SQLException e) {
			e.printStackTrace();
		} 	
		return null;
	}

	@Override
	public boolean update(Theme t) {
		boolean status = false;
		// db connection
		// Connection connection = connect.connection();
		try (PreparedStatement preparedStatement = connection.prepareStatement(
				"update t_themes set themeName = ? where idTheme = ?;")) {
			preparedStatement.setString(1, t.getThemeName());
			preparedStatement.setInt(2, t.getId());
			if (preparedStatement.executeUpdate() == 1)
				status = true;

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return status;

	}

	@Override
	public boolean delete(int idTheme) {
		boolean status = false;
		try {
			//DELETE ON CASCADE
			removeAllthemesForBook(idTheme);
			try (PreparedStatement preparedStatement = connection
					.prepareStatement("DELETE FROM t_themes WHERE idtheme = ?;")) {
				preparedStatement.setInt(1, idTheme);
				if (preparedStatement.executeUpdate() == 1)
					status = true;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return status;
	}
	
	/**
	 * DELETE ON CASCADE
	 * 
	 * @param idBook
	 * @return
	 */
	private boolean removeAllthemesForBook(int idTheme) {
		try (Statement statement = connection.createStatement()) {
			String str = "DELETE FROM T_Books_Themes_Association where IdTheme=" + idTheme + ";";
			statement.executeUpdate(str);
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	
}
