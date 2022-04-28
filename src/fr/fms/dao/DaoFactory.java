package fr.fms.dao;

import fr.fms.entities.Book;
import fr.fms.entities.Order;
import fr.fms.entities.OrderItem;
import fr.fms.entities.Theme;
import fr.fms.entities.User;

public class DaoFactory {

	/**
	 * 
	 * @return
	 */
	public static LibraryDao<Book> getBookDao() {
		 return new BookDao();
	}
	
	/**
	 * 
	 * @return
	 */
	public static LibraryDao<User> getUserDao() {
		 return new UserDao();
	}
	
	/**
	 * 
	 * @return
	 */
	public static LibraryDao<Theme> getThemeDao() {
		 return new ThemeDao();
	}

	/**
	 * 
	 * @return
	 */
	public static LibraryDao<Order> getOrderDao() {
		return new OrderDao();
	}
	
	/**
	 * 
	 * @return
	 */
	public static LibraryDao<OrderItem> getOrderItemDao() {
		return new OrderItemDao();
	}
}
