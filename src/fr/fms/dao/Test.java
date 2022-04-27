package fr.fms.dao;

import fr.fms.entities.Book;

public class Test {

	public static void main(String[] args) {
		BookDao bookDao = DaoFactory.getBookDao();
		//bookDao.create(new Book(0, "titlethirteen", "authorThirteen", "editorThirteen", "descriptionThirteen", 0, 1));
		bookDao.addThemeToBook(13, 1);

	}

}
