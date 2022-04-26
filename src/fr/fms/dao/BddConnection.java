package fr.fms.dao;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class BddConnection {
	/**/
	public static BddConnection bddConnection ;
	
	/**/
	private static Connection connection;
	private static String driver;
	private static String url;
	private static String login;
	private static String password;
	
	/**
	 * 
	 */
	private BddConnection() {
		try {
			readPropertiesFile("files/config.properties");
			Class.forName(driver);
			connection = DriverManager.getConnection(url, login, password);
		} catch (ClassNotFoundException cl) {
			System.out.println(cl.getMessage());
		}
		catch (SQLException sql) {
			System.out.println(sql.getMessage());
		}
	}
	
	/**
	 * 
	 * @return
	 */
	public static Connection getConnection() {
		bddConnection = new BddConnection();
		return connection;
	}
	
	
	
	/**
	 * 
	 * @param fileName
	 * @return
	 * @throws IOException
	 */
	public static void readPropertiesFile(String fileName) {
		FileInputStream fis = null;
		Properties prop = null;
		try {
			fis = new FileInputStream(fileName);
			prop = new Properties();
			prop.load(fis);
		} catch (FileNotFoundException fnfe) {
			fnfe.printStackTrace();
		} catch (IOException ioe) {
			ioe.printStackTrace();
		} finally {
			try {
				fis.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		driver = prop.getProperty("db.driver.class");
		url = prop.getProperty("db.url");
		login = prop.getProperty("db.login");
		password = prop.getProperty("db.password");
	}


}
