package fr.fms.entities;

public class OrderItem {
	private int id;
	private int quantity;
	private double itemPrice;
	private Order idOrder;
	private Book idBook;
	
	/**
	 * 
	 */
	public OrderItem() {
	}

	/**
	 * 
	 * @param id
	 * @param quantity
	 * @param itemPrice
	 * @param idOrder
	 * @param idBook
	 */
	public OrderItem(int id, int quantity, double itemPrice, Order idOrder, Book idBook) {
		super();
		this.id = id;
		this.quantity = quantity;
		this.itemPrice = itemPrice;
		this.idOrder = idOrder;
		this.idBook = idBook;
	}

	/**
	 * 
	 * @return
	 */
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	/**
	 * 
	 * @return
	 */
	public int getQuantity() {
		return quantity;
	}

	/**
	 * 
	 * @param quantity
	 */
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	/**
	 * 
	 * @return
	 */
	public double getItemPrice() {
		return itemPrice;
	}

	/**
	 * 
	 * @param itemPrice
	 */
	public void setItemPrice(double itemPrice) {
		this.itemPrice = itemPrice;
	}

	public Order getIdOrder() {
		return idOrder;
	}

	/**
	 * 
	 * @param idOrder
	 */
	public void setIdOrder(Order idOrder) {
		this.idOrder = idOrder;
	}

	public Book getIdBook() {
		return idBook;
	}

	/**
	 * 
	 * @param idBook
	 */
	public void setIdBook(Book idBook) {
		this.idBook = idBook;
	}

	/**
	 * 
	 */
	@Override
	public String toString() {
		return "OrderItem [id=" + id + ", quantity=" + quantity + ", itemPrice=" + itemPrice + ", idOrder=" + idOrder
				+ ", idBook=" + idBook + "]";
	}

}
