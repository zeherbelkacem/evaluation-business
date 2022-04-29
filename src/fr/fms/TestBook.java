package fr.fms;

import fr.fms.dao.BookDao;
import fr.fms.dao.DaoFactory;
import fr.fms.dao.LibraryDao;
import fr.fms.dao.ThemeDao;
import fr.fms.entities.Book;
import fr.fms.entities.Theme;
import fr.fms.entities.User;

public class TestBook {

	public static void main(String[] args) {

		/***************************** BookDao test ************************/
		LibraryDao<Book> bookDao = DaoFactory.getBookDao();
		// create
//		 bookDao.create(new Book(0, "titlethirteen", "authorThirteen",
//		 "editorThirteen", "descriptionThirteen", 0));

		// add theme to book
//		 bookDao.addThemeToBook(1, 1);
		((BookDao) bookDao).getBookThemesDetails(1).forEach(b->{
			System.out.println(b.toString());
		});;
	
		// remove theme from book
//		bookDao.removeThemeFromBook(6, 3);

		// update
		// bookDao.update(new Book(15, "titlethirteen", "authorThirteen",
		// "editorThirteen", "descriptionThirteen", 15));

		// readbyid
//		System.out.println(bookDao.readById(12));
		
		// delete
//		bookDao.delete(16);
		
		// readall
//		System.out.println();
//		bookDao.readAll().forEach(b -> {
//			System.out.println(b.toString());
//		});
		
		//real all by theme
		((BookDao) bookDao).getAllBooksByTheme(1).forEach(b -> {
			System.out.println(b.toString());
		});
			
		/***************************** UserDao TEST ************************/
		LibraryDao<User> userDao = DaoFactory.getUserDao();
		
		//read all
//		userDao.readAll().forEach(u->{
//			System.out.println(u.toString());
//		});
		
		//create user
//		userDao.create(new User(0, "belka", "email@fsm.fr", "056184", "toulouse20"));
		
		//delete
//		userDao.delete(8);
		
		//Update
		userDao.update(new User(7, "Caroline", "caroline@fms.fr", "0655003344", "Toulouse St cy"));
		
		//reda by id
//		System.out.println(userDao.readById(8));
		
		/***************************** ThemeDao TEST ************************/
		ThemeDao themeDao = (ThemeDao) DaoFactory.getThemeDao();
		//read all
		themeDao.readAll().forEach(t->{
			System.out.println(t.toString());
		});
		
		//create theme
		//themeDao.create(new Theme(0, "new theme"));
		
		//delete
		themeDao.delete(14);
		
		//Update
		themeDao.update(new Theme(15, "best sellers"));
		
		//reda by id
		System.out.println(themeDao.readById(8));
	}

}
