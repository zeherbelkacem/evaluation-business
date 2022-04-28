package fr.fms.business;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import fr.fms.dao.BookDao;
import fr.fms.dao.DaoFactory;
import fr.fms.dao.LibraryDao;
import fr.fms.dao.OrderDao;
import fr.fms.dao.UserDao;
import fr.fms.entities.Book;
import fr.fms.entities.Order;
import fr.fms.entities.OrderItem;
import fr.fms.entities.Theme;
import fr.fms.entities.User;

public class BookBusinessImpl implements BookBusiness {

	private LibraryDao<Book> bookDao = DaoFactory.getBookDao();
	private LibraryDao<Theme> themeDao = DaoFactory.getThemeDao();
	private LibraryDao<User> userDao = DaoFactory.getUserDao();
	private LibraryDao<Order> orderDao = DaoFactory.getOrderDao();
	private LibraryDao<OrderItem> orderItemDao = DaoFactory.getOrderItemDao();	
	
	private Map<Integer, Book> myCartMap;

	public BookBusinessImpl(Map<Integer, Book> myCartMap) {
		this.myCartMap = new HashMap<Integer, Book>();
	}

	@Override
	public List<Book> getAllBooks() {
		return bookDao.readAll();
	}

	@Override
	public List<Book> getAllBooksByTheme(int themeId) {

		return ((BookDao) bookDao).getAllBooksByTheme(themeId);
	}

	@Override
	public List<Theme> getAllThemes() {
		return themeDao.readAll();
	}

	@Override
	public boolean addBookToCart(int bookId) {
		/** verifier l'existanec du livre ID */
		Book book = bookDao.readById(bookId);

		if (book != null) {
			if (myCartMap.containsKey(bookId)) {// add the same product -> quantity incremented
				book.setQuantity(myCartMap.get(bookId).getQuantity() + 1);
				myCartMap.put(book.getId(), book);

			} else {// new product in the bucket (first time)
				book.setQuantity(1);
				myCartMap.put(book.getId(), book);
			}
			return true;
		}
		return false;
	}

	@Override
	public boolean removeBookFromcart(int bookId) {
		if (!myCartMap.isEmpty()) {
			if (myCartMap.get(bookId).getQuantity() > 1)
				myCartMap.get(bookId).setQuantity(myCartMap.get(bookId).getQuantity() - 1);
			else
				myCartMap.remove(bookId);
			return true;
		}
		return false;
	}

	@Override
	public double getTotalAmount() {
		double[] total = { 0 };
//		for (Map.Entry<Integer, Article> entry : cart.entrySet()) 
//			total1+= entry.getValue().getPrice()*entry.getValue().getQuantity();
		myCartMap.values().forEach((a) -> total[0] += a.getUnitaryPrice() * a.getQuantity());
		return total[0];
	}

	@Override
	public int saveOrder(int idUser) {
		
		/*
		 * As we can't add or update a child (orderitem) row (foreign key constraint,
		 * start by save a default parent row (order)
		 */
		orderDao.create(new Order(0, null, getTotalAmount(), idUser));
		int lastOrderId = ((OrderDao) orderDao).getLastOrderId();
		
		/* each line in the bucket corresponds to an item in the order table */
		for(Book book: myCartMap.values()) 
			orderItemDao.create(new OrderItem(0, book.getQuantity(), book.getQuantity()*book.getUnitaryPrice(), lastOrderId, book.getId()));
		
		/*
		 * Update the last default order saving after that all order items were saved
		 * (and clear the bucket)
		 */
		orderDao.update(new Order(lastOrderId, new Date(), getTotalAmount(), idUser));
		myCartMap.clear();

		return lastOrderId;
	
	}

	@Override
	public void loadInvoice(int idOrder) {
		List<String[]> invoiceElement = ((OrderDao) orderDao).getInvoice(idOrder);

		String invoiceNumber = String.valueOf(Integer.parseInt(invoiceElement.get(0)[0]) + 10000);
		String date = invoiceElement.get(0)[2];
		String totalPrice = invoiceElement.get(0)[7];
		String customer = invoiceElement.get(0)[1];
		String fileName = "Invoice-" + customer +"-"+ date + ".txt";

		try {
			File file = new File(fileName);
			FileWriter fr;
			fr = new FileWriter(file, false);

			BufferedWriter br = new BufferedWriter(fr);

			br.write(
					"----------------------------------------------------------------------------------------------\n");
			br.write("SOFTWARE SHOP\n");
			br.write("TOULOUSE\n");
			br.write(
					"----------------------------------------------------------------------------------------------\n\n\n");
			br.write("Facture N° : " + invoiceNumber + "\n");
			br.write("Client :  " + customer + "\n");
			br.write("Date :  " + date + "\n");
			br.write(
					"----------------------------------------------------------------------------------------------\n\n\n\n\n");
			br.write("Liste de Produits\n");
			br.write(
					"----------------------------------------------------------------------------------------------\n");
			br.write(String.format("|%-36s|%-18s|%-12s|%-10s|%-12s|", "TITLE", "DESCRIPTION", "UNITY PRICE", "QUANTITY",
					"TOTAL PRICE") + "\n");
			br.write(
					"----------------------------------------------------------------------------------------------\n");
			for (String[] array : invoiceElement) {
				br.write(String.format("|%-36s|%-18s|%-12s|%-10s|%-12s|", array[3], array[4], array[6], array[5],
						Double.parseDouble(array[6]) * Double.parseDouble(array[5])) + "\n");
			}
			br.write(
					"----------------------------------------------------------------------------------------------\n");
			br.write("Total : " + totalPrice+ "€\n");
			br.write(
					"----------------------------------------------------------------------------------------------\n");
			br.close();
			fr.close();
		} catch (IOException e) {
			System.out.println("An error occurred.");
			e.printStackTrace();
		}


	}

	public Map<Integer, Book> getMyCartMap() {
		return myCartMap;
	}

	public void setMyCartMap(Map<Integer, Book> myCartMap) {
		this.myCartMap = myCartMap;
	}

	@Override
	public List<Book> getMyCart() {
		return new ArrayList<Book>(myCartMap.values());
	}

	@Override
	public int saveANewUser(User user) {
		return ((UserDao) userDao).saveAndGetIdOfUser(user);
	}

	@Override
	public int userAuthentication(User user) {
		int userId = 0;
		List<User> users = userDao.readAll();
		for (User u : users) {
			if (u.getEmail().equalsIgnoreCase(user.getEmail()) && u.getPhone().equalsIgnoreCase(user.getPhone())) {
				userId = u.getId();
			}
		}
		return userId;
	}

	@Override
	public String adminAuthentication(User user) {
//		 select t_roles.role from t_roles left outer join t_users_roles on t_users_roles.idrole=t_roles.idrole left outer join t_users on t_users_roles.iduser= t_users.iduser where t_users.name='admin1';
		return null;
	}

	@Override
	public List<Book> getBookThemesDetails(int idTheme) {
		return ((BookDao) bookDao).getBookThemesDetails(idTheme);
	}

}
