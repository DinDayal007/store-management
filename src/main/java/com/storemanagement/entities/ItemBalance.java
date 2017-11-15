package com.storemanagement.entities;

public class ItemBalance {
	private int inventoryId;
	private String inventoryName;
	private int itemId;
	private String mainGroup;
	private String subGroup;
	private String itemName;
	private String itemHome;
	private String itemCode;
	private double itemPrice;
	private int itemQty;
	private int itemMin;
	private int itemMax;
	public int getInventoryId() {
		return inventoryId;
	}
	public void setInventoryId(int inventoryId) {
		this.inventoryId = inventoryId;
	}
	public String getInventoryName() {
		return inventoryName;
	}
	public void setInventoryName(String inventoryName) {
		this.inventoryName = inventoryName;
	}
	public int getItemId() {
		return itemId;
	}
	public void setItemId(int itemId) {
		this.itemId = itemId;
	}
	public String getMainGroup() {
		return mainGroup;
	}
	public void setMainGroup(String mainGroup) {
		this.mainGroup = mainGroup;
	}
	public String getSubGroup() {
		return subGroup;
	}
	public void setSubGroup(String subGroup) {
		this.subGroup = subGroup;
	}
	public String getItemName() {
		return itemName;
	}
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}
	public String getItemHome() {
		return itemHome;
	}
	public void setItemHome(String itemHome) {
		this.itemHome = itemHome;
	}
	public String getItemCode() {
		return itemCode;
	}
	public void setItemCode(String itemCode) {
		this.itemCode = itemCode;
	}
	public double getItemPrice() {
		return itemPrice;
	}
	public void setItemPrice(double itemPrice) {
		this.itemPrice = itemPrice;
	}
	public int getItemQty() {
		return itemQty;
	}
	public void setItemQty(int itemQty) {
		this.itemQty = itemQty;
	}
	public int getItemMin() {
		return itemMin;
	}
	public void setItemMin(int itemMin) {
		this.itemMin = itemMin;
	}
	public int getItemMax() {
		return itemMax;
	}
	public void setItemMax(int itemMax) {
		this.itemMax = itemMax;
	}
}
