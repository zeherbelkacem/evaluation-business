package fr.fms.dao;

import java.sql.Connection;
import java.util.List;

import fr.fms.entities.Book;

public interface LibraryDao<T> {
	
	/* Get sql connection*/
	public Connection connection = BddConnection.getConnection();
	
	/**
	 * 
	 * @return
	 */
	boolean create(T t);
	
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
	 * @return 
	 */
	boolean update(T t);
	
	/**
	 * 
	 * @param id
	 * @return
	 */
	boolean delete(int id);

	
	

}
