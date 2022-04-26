package fr.fms.dao;

public class DaoFactory {

	/**
	 * 
	 * @return
	 */
	public static BookDao getBookDao() {
		 return new BookDao();
	}
	
	/**
	 * 
	 * @return
	 */
	public static UserDao getUserDao() {
		 return new UserDao();
	}
	
	/**
	 * 
	 * @return
	 */
	public static ThemeDao getThemeDao() {
		 return new ThemeDao();
	}
}
