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
	public static CategoryDao getCategoryDao() {
		 return new CategoryDao();
	}
}
