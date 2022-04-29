package fr.fms.entities;

public class OrderItem {
	private int id;
	private int quantity;
	private double itemPrice;
	private int idOrder;
	private int idBook;
	
	/**
	 * 
	 * @param id
	 * @param quantity
	 * @param itemPrice
	 * @param idOrder
	 * @param idBook
	 */
	public OrderItem(int id, int quantity, double itemPrice, int idOrder, int idBook) {
		super();
		this.id = id;
		this.quantity = quantity;
		this.itemPrice = itemPrice;
		this.idOrder = idOrder;
		this.idBook = idBook;
	}

	/**
	 * Retourne l'id de l'objet instancié order 
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

	public int getIdOrder() {
		return idOrder;
	}

	/**
	 * 
	 * @param idOrder
	 */
	public void setIdOrder(int idOrder) {
		this.idOrder = idOrder;
	}

	public int getIdBook() {
		return idBook;
	}

	/**
	 * 
	 * @param idBook
	 */
	public void setIdBook(int idBook) {
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
