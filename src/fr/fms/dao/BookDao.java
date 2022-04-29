package fr.fms.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import fr.fms.entities.Book;

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
				if (statement.executeUpdate() == 1)
					return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public List<Book> readAll() {
		List<Book> books = new ArrayList<Book>();

		try (Statement statement = connection.createStatement()) {
			try (ResultSet resultSet = statement.executeQuery("SELECT *FROM t_books")) {

				while (resultSet.next()) {
					int rsId = resultSet.getInt(1);
					String rsTitle = resultSet.getString(2);
					String rsAuthor = resultSet.getString(3);
					String rsEditor = resultSet.getString(4);
					String rsDescription = resultSet.getString(5);
					double rsPrice = resultSet.getDouble(6);
					// int rsQuantity = resultSet.getInt(6);
					books.add(new Book(rsId, rsTitle, rsAuthor, rsEditor, rsDescription, rsPrice));
				}
			}
		} catch (SQLException e) {
			System.err.println(e.getMessage());
		}
		return books;
	}

	@Override
	public Book readById(int id) {
		try (Statement statement = connection.createStatement()) {
			String str = "SELECT * FROM T_books where Idbook=" + id + ";";
			ResultSet rs = statement.executeQuery(str);
			if (rs.next())
				return new Book(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5),
						rs.getDouble(6));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public boolean update(Book t) {
		boolean status = false;
		// db connection
		// Connection connection = connect.connection();
		try (PreparedStatement preparedStatement = connection.prepareStatement(
				"update t_books set title = ?, author = ?, editor = ?, description = ?, unitaryprice = ? where idbook = ?;")) {
			preparedStatement.setString(1, t.getTitle());
			preparedStatement.setString(2, t.getAuthor());
			preparedStatement.setString(3, t.getEditor());
			preparedStatement.setString(4, t.getDescription());
			preparedStatement.setDouble(5, t.getUnitaryPrice());
			preparedStatement.setInt(6, t.getId());
			if (preparedStatement.executeUpdate() == 1)
				status = true;

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return status;

	}

	@Override
	public boolean delete(int idBook) {
		boolean status = false;
		try {
			// DELETE ON CASCADE
			removeAllthemesFromBook(idBook);
			try (PreparedStatement preparedStatement = connection
					.prepareStatement("DELETE FROM t_books WHERE idbook = ?;")) {
				preparedStatement.setInt(1, idBook);
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
	private boolean removeAllthemesFromBook(int idBook) {
		try (Statement statement = connection.createStatement()) {
			String str = "DELETE FROM T_Books_Themes_Association where IdBook=" + idBook + ";";
			statement.executeUpdate(str);
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	/**
	 * 
	 * @param idBook
	 * @param idTheme
	 * @return
	 */
	public boolean removeThemeFromBook(int idBook, int idTheme) {
		boolean status = false;
		try {
			try (PreparedStatement preparedStatement = connection
					.prepareStatement("delete from t_books_themes_association where idbook =? and idtheme=?;")) {
				preparedStatement.setInt(1, idBook);
				preparedStatement.setInt(2, idTheme);
				if (preparedStatement.executeUpdate() == 1)
					status = true;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return status;
	}

	/**
	 * 
	 * @param themeId
	 * @return
	 */
	public List<Book> getAllBooksByTheme(int themeId) {
		List<Book> books = new ArrayList<Book>();

		try (Statement statement = connection.createStatement()) {
			try (ResultSet resultSet = statement.executeQuery(
					"select t_books.idbook, t_books.title, t_books.author, t_books.editor, t_books.description, t_books.unitaryprice from t_books left outer join t_books_themes_association on t_books_themes_association.idbook=t_books.idbook left outer join t_themes on t_books_themes_association.idtheme= t_themes.idtheme where t_themes.idtheme="
							+ themeId + ";")) {

				while (resultSet.next()) {
					int rsId = resultSet.getInt(1);
					String rsTitle = resultSet.getString(2);
					String rsAuthor = resultSet.getString(3);
					String rsEditor = resultSet.getString(4);
					String rsDescription = resultSet.getString(5);
					double rsPrice = resultSet.getDouble(6);
					books.add(new Book(rsId, rsTitle, rsAuthor, rsEditor, rsDescription, rsPrice));
				}
			}
		} catch (SQLException e) {
			System.err.println(e.getMessage());
		}

		return books;
	}

	/**
	 * 
	 * @param bookId
	 * @param themeId
	 * @return
	 */
	public boolean addThemeToBook(int bookId, int themeId) {

		try (Statement statement = connection.createStatement()) {
			String str = "INSERT INTO t_books_themes_association(idbook, idtheme) SELECT * FROM (SELECT " + bookId
					+ " as idbook, " + themeId
					+ " AS idtheme) AS new_value WHERE NOT EXISTS (  SELECT * FROM t_books_themes_association WHERE idbook=+"
					+ bookId + " and idtheme=" + themeId + " );";
			int row = statement.executeUpdate(str);
			if (row == 1)
				return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;

	}

	public List<Book> getBookThemesDetails(int idTheme) {
		List<Book> books = new ArrayList<Book>();

		try (Statement statement = connection.createStatement()) {
//			try (ResultSet resultSet = statement.executeQuery(
//					"select t.idbook, t.title, t.author, t_themes.themeName from t_books AS t left outer join t_books_themes_association on t_books_themes_association.idbook=t.idbook left outer join t_themes on t_books_themes_association.idtheme= t_themes.idtheme where t.idbook="
//							+ idTheme + ";")) {
			try (ResultSet resultSet = statement.executeQuery(
					"select t.idbook, t.title, t.author, h.themeName from t_books AS t left outer join t_books_themes_association as a on a.idbook=t.idbook left outer join t_themes as h on a.idtheme= h.idtheme where t.idbook="
							+ idTheme + ";")) {
				while (resultSet.next()) {
					int rsId = resultSet.getInt(1);
					String rsTitle = resultSet.getString(2);
					String rsAuthor = resultSet.getString(3);
					String rsTheme = resultSet.getString(4);

					books.add(new Book(rsId, rsTitle, rsAuthor, rsTheme, null, 0));
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return books;
	}

}
