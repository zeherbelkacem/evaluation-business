package fr.fms.dao;

import java.sql.Connection;
import java.util.List;

public interface LibraryDao<T> {
	
	/* Get sql connection*/
	public Connection connection = BddConnection.getConnection();
	
	/**
	 * 
	 * @return
	 */
	boolean create();
	
	/**
	 * 
	 * @return
	 */
	List<T> readAll();
	
	/**
	 * 
	 * @param id
	 * @return
	 */
	T readById(int id);
	
	/**
	 * 
	 * @param t
	 */
	void update(T t);
	
	/**
	 * 
	 * @param id
	 * @return
	 */
	boolean delete(int id);
	

}