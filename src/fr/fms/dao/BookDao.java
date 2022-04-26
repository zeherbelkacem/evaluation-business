package fr.fms.dao;

import java.sql.PreparedStatement;
import java.sql.SQLException;
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
			System.out.println(e.getMessage());
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
