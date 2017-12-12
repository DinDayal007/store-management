package com.storemanagement.services;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.criterion.Restrictions;
import com.storemanagement.entities.Item;
import com.storemanagement.entities.ItemBalance;
import com.storemanagement.entities.SubGroup;
import com.storemanagement.utils.JDBCUtil;
public class ItemService extends EntityService {
	private static Connection connection = null;
	private static PreparedStatement preparedStatement = null;
	private static ResultSet resultSet = null;
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
	
	//get item from barcode
	public static Item getItemFromBarcode(String barCode){
		Item item = null;
		try {
			openSession();
			item = (Item) getSession().createCriteria(Item.class).add(Restrictions.eq("code", barCode)).uniqueResult();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeSession();
		}
		return item;
	}
	
	//get item quantities from id
	public static int getItemQty(int itemId, int inventoryId){
		int itemQty = 0;
		connection = JDBCUtil.getCon();
		try{
			String query = "select sum(ITEM_QTY) from item_movment where item_id = ? and inventory_id = ?";
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setInt(1, itemId);
			preparedStatement.setInt(2, inventoryId);
			resultSet = preparedStatement.executeQuery();
			if(resultSet.next())
				itemQty = resultSet.getInt(1);
		}catch (Exception e) {
			e.printStackTrace();
		}
		return itemQty;
	}
	
	//get item quantities from id
	public static ItemBalance getItemBalance(int itemId, int inventoryId){
		ItemBalance itemBalance = null;
		connection = JDBCUtil.getCon();
		try{
			String query = "select sum(ITEM_QTY), item_name, CODE, MIN_LIMIT, MAX_LIMIT, PURCHASE_PRICE, SALE_PRICE from item_movment where item_id = ? and inventory_id = ? group by item_name, CODE, MIN_LIMIT, MAX_LIMIT, PURCHASE_PRICE, SALE_PRICE";
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setInt(1, itemId);
			preparedStatement.setInt(2, inventoryId);
			resultSet = preparedStatement.executeQuery();
			while(resultSet.next()){
				itemBalance = new ItemBalance();
				itemBalance.setItemQty(resultSet.getInt(1));
				itemBalance.setItemName(resultSet.getString(2));
				itemBalance.setItemCode(resultSet.getString(3));
				itemBalance.setItemMin(resultSet.getInt(4));
				itemBalance.setItemMax(resultSet.getInt(5));
				itemBalance.setItemPurchasePrice(resultSet.getInt(6));
				itemBalance.setItemSalePrice(resultSet.getInt(7));
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		return itemBalance;
	}
	
	//get items from inventory id
	public static List<ItemBalance> getItemsFromInventory(int inventoryId){
		List<ItemBalance> itemBalances = new ArrayList<>();
		connection = JDBCUtil.getCon();
		try {
			String query = "select sum(ITEM_QTY), ITEM_ID, item_name, CODE, PURCHASE_PRICE from item_movment where inventory_id = ? group by ITEM_ID, item_name, CODE, MIN_LIMIT, MAX_LIMIT, PURCHASE_PRICE, SALE_PRICE";
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setInt(1, inventoryId);
			resultSet = preparedStatement.executeQuery();
			while(resultSet.next()){
				ItemBalance itemBalance = new ItemBalance();
				itemBalance.setItemQty(resultSet.getInt(1));
				itemBalance.setItemId(resultSet.getInt(2));
				itemBalance.setItemName(resultSet.getString(3));
				itemBalance.setItemCode(resultSet.getString(4));
				itemBalance.setItemPurchasePrice(resultSet.getDouble(5));
				itemBalances.add(itemBalance);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return itemBalances;
	}
	
	//get item quantities from code
	public static ItemBalance getItemFromCode(String code, int inventoryId){
		ItemBalance itemBalance = null;
		connection = JDBCUtil.getCon();
		try{
			String query = "select sum(ITEM_QTY), item_name, MIN_LIMIT, MAX_LIMIT, PURCHASE_PRICE, SALE_PRICE, item_id from item_movment where CODE = ? and INVENTORY_ID = ? group by item_name, MIN_LIMIT, MAX_LIMIT, PURCHASE_PRICE, SALE_PRICE, item_id";
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, code);
			preparedStatement.setInt(2, inventoryId);
			resultSet = preparedStatement.executeQuery();
			while(resultSet.next()){
				itemBalance = new ItemBalance();
				itemBalance.setItemQty(resultSet.getInt(1));
				itemBalance.setItemName(resultSet.getString(2));
				itemBalance.setItemMin(resultSet.getInt(3));
				itemBalance.setItemMax(resultSet.getInt(4));
				itemBalance.setItemPurchasePrice(resultSet.getInt(5));
				itemBalance.setItemSalePrice(resultSet.getInt(6));
				itemBalance.setItemId(resultSet.getInt(7));
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		return itemBalance;
	}
	//get item quantities from inventories
	public static List<ItemBalance> getItemQuantities(){
		List<ItemBalance> itemBalances = new ArrayList<>();
		connection = JDBCUtil.getCon();
		try{
			String query = "SELECT A.* FROM "
				+ " ( SELECT INVENTORY_ID , INVENTORY_NAME , ITEM_ID , MAINGROUP, sub_name , item_name ,HOME, CODE, PURCHASE_PRICE, SALE_PRICE, round(SUM(ITEM_QTY), 0) TOTAL_QTY "
				+ " FROM item_movment"
				+ " GROUP BY INVENTORY_ID , INVENTORY_NAME , ITEM_ID , MAINGROUP, sub_name , item_name ,HOME, CODE, PURCHASE_PRICE, SALE_PRICE"
				+ ")A ";
			preparedStatement = connection.prepareStatement(query);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				ItemBalance itemBalance = new ItemBalance();
				itemBalance.setInventoryId(resultSet.getInt(1));
				itemBalance.setInventoryName(resultSet.getString(2));
				itemBalance.setItemId(resultSet.getInt(3));
				itemBalance.setMainGroup(resultSet.getString(4));
				itemBalance.setSubGroup(resultSet.getString(5));
				itemBalance.setItemName(resultSet.getString(6));
				itemBalance.setItemHome(resultSet.getString(7));
				itemBalance.setItemCode(resultSet.getString(8));
				itemBalance.setItemPurchasePrice(resultSet.getDouble(9));
				itemBalance.setItemSalePrice(resultSet.getDouble(10));
				itemBalance.setItemQty(resultSet.getInt(11));
				itemBalances.add(itemBalance);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return itemBalances;
	}
}
