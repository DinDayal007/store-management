package com.storemanagement.entities;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
@Entity
@Table(name = "item_movements")
public class ItemMovement {
	@Id
	@Column(name = "inv_number")
	private long invNumber;
	@Temporal(TemporalType.DATE)
	@Column(name = "date")
	private Date date;
	@Column(name = "inventory_id")
	private int inventoryId;
	@Column(name = "inventory_name")
	private String inventoryName;
	@Column(name = "client_id")
	private Integer clientId;
	@Column(name = "NAME")
	private String clientName;
	@Column(name = "CACHE_ID")
	private int cacheId;
	@Column(name = "cash_name")
	private String cacheName;
	@Column(name = "ITEM_ID")
	private int itemId;
	@Column(name = "maingroup")
	private String mainGroupName;
	@Column(name = "sub_name")
	private String subGroupName;
	@Column(name = "item_name")
	private String itemName;
	@Column(name = "ITEM_QTY")
	private int itemQty;
	@Column(name = "MIN_LIMIT")
	private int itemMinLimit;
	@Column(name = "MAX_LIMIT")
	private int itemMaxLimit;
	@Column(name = "HOME")
	private String itemHome;
	@Column(name = "FINAL_TOTAL")
	private double invFinalTotal;
	@Column(name = "inv_type")
	private String invType;
	public long getInvNumber() {
		return invNumber;
	}
	public void setInvNumber(long invNumber) {
		this.invNumber = invNumber;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
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
	public Integer getClientId() {
		return clientId;
	}
	public void setClientId(Integer clientId) {
		this.clientId = clientId;
	}
	public String getClientName() {
		return clientName;
	}
	public void setClientName(String clientName) {
		this.clientName = clientName;
	}
	public int getCacheId() {
		return cacheId;
	}
	public void setCacheId(int cacheId) {
		this.cacheId = cacheId;
	}
	public String getCacheName() {
		return cacheName;
	}
	public void setCacheName(String cacheName) {
		this.cacheName = cacheName;
	}
	public int getItemId() {
		return itemId;
	}
	public void setItemId(int itemId) {
		this.itemId = itemId;
	}
	public String getMainGroupName() {
		return mainGroupName;
	}
	public void setMainGroupName(String mainGroupName) {
		this.mainGroupName = mainGroupName;
	}
	public String getSubGroupName() {
		return subGroupName;
	}
	public void setSubGroupName(String subGroupName) {
		this.subGroupName = subGroupName;
	}
	public String getItemName() {
		return itemName;
	}
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}
	public int getItemQty() {
		return itemQty;
	}
	public void setItemQty(int itemQty) {
		this.itemQty = itemQty;
	}
	public int getItemMinLimit() {
		return itemMinLimit;
	}
	public void setItemMinLimit(int itemMinLimit) {
		this.itemMinLimit = itemMinLimit;
	}
	public int getItemMaxLimit() {
		return itemMaxLimit;
	}
	public void setItemMaxLimit(int itemMaxLimit) {
		this.itemMaxLimit = itemMaxLimit;
	}
	public String getItemHome() {
		return itemHome;
	}
	public void setItemHome(String itemHome) {
		this.itemHome = itemHome;
	}
	public double getInvFinalTotal() {
		return invFinalTotal;
	}
	public void setInvFinalTotal(double invFinalTotal) {
		this.invFinalTotal = invFinalTotal;
	}
	public String getInvType() {
		return invType;
	}
	public void setInvType(String invType) {
		this.invType = invType;
	}
}
