package com.storemanagement.services;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;
import com.storemanagement.entities.Item;
import com.storemanagement.entities.SubGroup;
public class ItemService extends EntityService {
	public static List<Item> getItemsFromSubGroup(SubGroup subGroup) {
		List<Item> items = null;
		try {
			openSession();
			items = getSession()
					.createQuery("from Item where subGroup.id = :subGroupId")
					.setParameter("subGroupId", subGroup.getId()).list();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeSession();
		}
		return items;
	}
	//get item balance from id
	public static int getItemBalance(int itemId, int inventoryId){
		Connection connection = null;
		int itemBalance = 0;
		try {
			connection = DriverManager.getConnection("jdbc:mysql://localhost/usarabia_store?verifyServerCertificate=false&useSSL=true", "root", "root");
			Class.forName("com.mysql.jdbc.Driver");
			String query = "select sum(ITEM_QTY) itemQty from item_movment where item_id = ? and inventory_id = ?";
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setInt(1, itemId);
			statement.setInt(2, inventoryId);
			ResultSet resultSet = statement.executeQuery();
			while(resultSet.next())
				itemBalance = resultSet.getInt(1);
			connection.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return itemBalance;
	}
}
