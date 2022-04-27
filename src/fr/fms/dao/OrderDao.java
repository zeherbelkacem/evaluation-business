package fr.fms.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import fr.fms.entities.Order;

public class OrderDao implements LibraryDao<Order> {

	@Override
	public boolean create(Order t) {
		try (PreparedStatement preparedStatement = connection
				.prepareStatement("INSERT INTO t_orders(Date, amount, idUser) VALUES(?,?,?)")) {
			java.sql.Date currentDate = new java.sql.Date(System.currentTimeMillis());
			preparedStatement.setDate(1, currentDate);
			preparedStatement.setDouble(2, t.getAmount());
			preparedStatement.setInt(3, t.getIdUser());

			if (preparedStatement.executeUpdate() == 1) {
				System.out.println("Commande bien inserée");
				return true;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public List<Order> readAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Order readById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean update(Order t) {
		boolean status = false;
		// db connection
		// Connection connection = connect.connection();
		try (PreparedStatement preparedStatement = connection
				.prepareStatement("update t_orders set Date = ?, amount = ?, idUser = ? where idOrder = ?;")) {
			java.sql.Date currentDate = new java.sql.Date(System.currentTimeMillis());
			preparedStatement.setDate(1, currentDate);
			preparedStatement.setDouble(2, t.getAmount());
			preparedStatement.setInt(3, t.getIdUser());
			preparedStatement.setInt(4, t.getId());
			if (preparedStatement.executeUpdate() == 1)
				status = true;

		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return status;
	}

	@Override
	public boolean delete(int id) {
		// TODO Auto-generated method stub
		return false;
	}

	public int getLastOrderId() {
		int rsIdOrder = 0;
		try (Statement statement = connection.createStatement()) {
			try (ResultSet resultSet = statement.executeQuery("SELECT MAX(idOrder) FROM t_orders")) {

				while (resultSet.next()) {
					rsIdOrder = resultSet.getInt(1);

				}
			}
		} catch (SQLException e) {
			System.err.println(e.getMessage());
		}
		return rsIdOrder;
	}

	/**
	 * 
	 * @param orderId
	 * @return
	 */
	public List<String[]> getInvoice(int orderId) {
		List<String[]> invoiceElements = new ArrayList<String[]>();
		try {
			try (PreparedStatement statement = connection.prepareStatement(
					"select t_orders.idorder, t_users.name, t_orders.date, t_books.title, t_books.Description, t_orderItems.quantity, t_orderItems.itemprice, t_orders.amount    from (((t_orders inner join t_orderItems on t_orderItems.idorder=t_orders.idorder) inner join t_users on t_users.idUser=t_orders.iduser) inner join t_books on t_books.idBook=t_orderItems.idBook) where t_orders.idorder=?;")) {
				statement.setInt(1, orderId);
				try (ResultSet resultSet = statement.executeQuery()) {
					while (resultSet.next()) {
						String[] arrayElem = new String[8];
						arrayElem[0] = String.valueOf(resultSet.getInt("idOrder"));
						arrayElem[1] = resultSet.getString("name");
						arrayElem[2] = new SimpleDateFormat("yyyy-MM-dd").format(resultSet.getDate("Date"));
						arrayElem[3] = resultSet.getString("title");
						arrayElem[4] = resultSet.getString("description");
						arrayElem[5] = String.valueOf(resultSet.getInt("Quantity"));
						arrayElem[6] = String.valueOf(resultSet.getDouble("itemprice"));
						arrayElem[7] = String.valueOf(resultSet.getDouble("amount"));
						invoiceElements.add(arrayElem);// asList({rsOrderId, rsDate});
					}
				}
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}

		return invoiceElements;

	}
}
