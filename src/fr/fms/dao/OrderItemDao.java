package fr.fms.dao;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import fr.fms.entities.OrderItem;

public class OrderItemDao implements LibraryDao<OrderItem>{

	@Override
	public boolean create(OrderItem t) {
		try (PreparedStatement preparedStatement = connection
				.prepareStatement("INSERT INTO t_orderItems(Quantity, itemPrice, idOrder, idBook) VALUES(?,?,?,?)")) {
			preparedStatement.setInt(1, t.getQuantity());
			preparedStatement.setDouble(2, t.getItemPrice());
			preparedStatement.setInt(3, t.getIdOrder());
			preparedStatement.setInt(4, t.getIdBook());
			
			if (preparedStatement.executeUpdate() == 1)
				System.out.println();//"Ligne de commande bien inserée");

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public List<OrderItem> readAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public OrderItem readById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean update(OrderItem t) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean delete(int id) {
		// TODO Auto-generated method stub
		return false;
	}

}
