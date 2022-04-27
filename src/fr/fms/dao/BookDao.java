package fr.fms.dao;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.logging.Level;

import fr.fms.entities.Book;
import fr.fms.entities.Theme;

public class BookDao implements LibraryDao<Book> {

	@Override
	public boolean create(Book t) {
		try {
			try (PreparedStatement statement = connection.prepareStatement(
					"INSERT INTO T_Books(Title, Author, Editor, Description, UnitaryPrice) VALUES(?,?,?,?,?);")) {
				statement.setString(1, t.getTitle());
				statement.setString(2, t.getAuthor());
				statement.setString(3, t.getEditor());
				statement.setString(4, t.getDescription());
				statement.setDouble(5, t.getUnitaryPrice());
				if (statement.executeUpdate() == 1 ) {
					addThemeToBook(Statement.RETURN_GENERATED_KEYS ,t.getThemeId());
					return true;
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	/**
	 * 
	 * @param bookId
	 * @param themeId
	 * @return
	 */
	public boolean addThemeToBook(int bookId,int themeId) {
		try (Statement statement = connection.createStatement()){
			String str = "INSERT INTO t_books_themes_association (IdBook, IdTheme)"
						+ " VALUES ("+ bookId+" ,"+ themeId+" );";			
			int row = statement.executeUpdate(str);
			if(row == 1)		return true;
		} catch (SQLException e) {
			e.printStackTrace();
		} 		
		return false;
		
	}

	@Override
	public List<Book> readAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Book readById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void update(Book t) {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean delete(int id) {
		// TODO Auto-generated method stub
		return false;
	}

}
