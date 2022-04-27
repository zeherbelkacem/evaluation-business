package fr.fms.business;

import java.util.List;

import fr.fms.entities.Book;
import fr.fms.entities.Order;
import fr.fms.entities.Theme;
import fr.fms.entities.User;

public interface BookBusiness {
	
	/**
	 * 
	 * @return
	 */
	public List<Book> getAllBooks();
	
	/**
	 * 
	 * @param themeId
	 * @return
	 */
	public List<Book> getAllBooksByTheme(int themeId);
	
	/**
	 * 
	 * @return
	 */
	public List<Theme> getAllThemes();
	
	/**
	 * 
	 * @param bookId
	 * @return
	 */
	public boolean addBookToCart(int bookId);
	
	/**
	 * 
	 * @param bookId
	 * @return
	 */
	public boolean removeBookFromcart(int bookId);
	
	/**
	 * 
	 * @return
	 */
	public double getTotalAmount();
	
	/**
	 * 
	 * @param idUser
	 * @return
	 */
	public int saveOrder(int idUser);
	
	/**
	 * 
	 * @param idOrder
	 */
	public void loadInvoice(int idOrder);
	public List<Book> getMyCart();

	public int saveANewUser(User user);

	public int userAuthentication(User user);

	public String adminAuthentication(User user);

}
