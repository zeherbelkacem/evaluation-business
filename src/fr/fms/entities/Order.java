package fr.fms.entities;

import java.util.Date;

public class Order {
	
	private int id;
	private Date date;
	private double amount;
	private User idUser;
	
	/**
	 * 
	 */
	public Order() {
	}
	
	/**
	 * 
	 * @param id
	 * @param date
	 * @param amount
	 * @param idUser
	 */
	public Order(int id, Date date, double amount, User idUser) {
		super();
		this.id = id;
		this.date = date;
		this.amount = amount;
		this.idUser = idUser;
	}

	/**
	 * 
	 * @return
	 */
	public int getId() {
		return id;
	}

	/**
	 * 
	 * @param id
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * 
	 * @return
	 */
	public Date getDate() {
		return date;
	}

	/**
	 * 
	 * @param date
	 */
	public void setDate(Date date) {
		this.date = date;
	}

	/**
	 * 
	 * @return
	 */
	public double getAmount() {
		return amount;
	}

	/**
	 * 
	 * @param amount
	 */
	public void setAmount(double amount) {
		this.amount = amount;
	}

	/**
	 * 
	 * @return
	 */
	public User getIdUser() {
		return idUser;
	}

	/**
	 * 
	 * @param idUser
	 */
	public void setIdUser(User idUser) {
		this.idUser = idUser;
	}

	/**
	 * 
	 */
	@Override
	public String toString() {
		return "Order [id=" + id + ", date=" + date + ", amount=" + amount + ", idUser=" + idUser + "]";
	}
	
	
}
