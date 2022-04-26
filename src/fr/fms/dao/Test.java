package fr.fms.dao;

import fr.fms.entities.Book;

public class Test {

	public static void main(String[] args) {
		BookDao bookDao = DaoFactory.getBookDao();
		bookDao.create(new Book(0, null, null, null, null, 0));

	}

}
