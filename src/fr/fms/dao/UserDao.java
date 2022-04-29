package fr.fms.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import fr.fms.entities.User;

public class UserDao implements LibraryDao<User> {

	@Override
	public boolean create(User t) {
		String str = "INSERT INTO T_Users (name, email, password, phone, address) VALUES (?,?, ?,?, ?);";
		try (PreparedStatement ps = connection.prepareStatement(str)){
			ps.setString(1, t.getName());
			ps.setString(2, t.getEmail());
			ps.setString(3, t.getPassword());
			ps.setString(4, t.getPhone());
			ps.setString(5, t.getAddress());	
			if( ps.executeUpdate() == 1)	return true;				
		} catch (SQLException e) {
			e.printStackTrace();
		} 				
		return false;
	}

	@Override
	public List<User> readAll() {
		ArrayList<User> users = new ArrayList<User>();
		String strSql = "SELECT * FROM T_Users";		
		try(Statement statement = connection.createStatement()){
			try(ResultSet resultSet = statement.executeQuery(strSql)){ 			
				while(resultSet.next()) {
					int rsId = resultSet.getInt(1);	
					String rsName = resultSet.getString(2);
					String rsEmail = resultSet.getString(3);
					String rsPassword = resultSet.getString(4);
					String rsPhone = resultSet.getString(5);
					String rsAddress = resultSet.getString(6);
					users.add(new User(rsId, rsName, rsEmail, rsPassword, rsPhone, rsAddress));						
				}	
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}			
		return users;
	}

	@Override
	public User readById(int id) {
		try (Statement statement = connection.createStatement()){
			String str = "SELECT * FROM T_Users where IdUser=" + id + ";";									
			ResultSet rs = statement.executeQuery(str);
			if(rs.next()) 
				return new User(rs.getInt(1) , rs.getString(2) , rs.getString(3), rs.getString(3), rs.getString(4));
		} catch (SQLException e) {
			e.printStackTrace();
		} 	
		return null;
	}

	@Override
	public boolean update(User t) {
		boolean status = false;
		try {
			try (PreparedStatement preparedStatement = connection
					.prepareStatement("update t_users set name = ?, email = ?, phone =?, address=? where idUser = ?;")) {
				preparedStatement.setString(1, t.getName());
				preparedStatement.setString(2, t.getEmail());
				preparedStatement.setString(3, t.getPhone());
				preparedStatement.setString(4, t.getAddress());
				preparedStatement.setInt(5, t.getId());
				if (preparedStatement.executeUpdate() == 1)
					status = true;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return status;
		
	}

	@Override
	public boolean delete(int id) {
		try (Statement statement = connection.createStatement()){
			String str = "DELETE FROM T_Users where IdUser=" + id + ";";									
			statement.executeUpdate(str);		
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	public int saveAndGetIdOfUser(User user) {
		create(user);
		
		return getTheLastInsertedId(user);
	}

	private int getTheLastInsertedId(User user) {
		int rsIdUser = 0;
		try (Statement statement = connection.createStatement()) {
			try (ResultSet resultSet = statement.executeQuery("SELECT MAX(idUser) FROM t_users")) {

				while (resultSet.next()) {
					rsIdUser = resultSet.getInt(1);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rsIdUser;
	}



}
