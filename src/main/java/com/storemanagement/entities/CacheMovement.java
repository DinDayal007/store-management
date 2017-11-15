package com.storemanagement.entities;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
@Entity
@Table(name = "CACHE_MOVEMENTS")
public class CacheMovement {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private int id;
	@Column(name = "REF_NUMBER")
	private long refNumber;
	@OneToOne
	@JoinColumn(name = "USER_ID", referencedColumnName = "ID")
	private User user;
	@OneToOne
	@JoinColumn(name = "CACHE_ID", referencedColumnName = "ID")
	private Cache cache;
	@OneToOne
	@JoinColumn(name = "INVENTORY_ID", referencedColumnName = "ID")
	private Inventory inventory;
	@Temporal(TemporalType.DATE)
	@Column(name = "DATE")
	private Date date;
	@Column(name = "TYPE")
	private int type;
	@Column(name = "AMOUNT")
	private double amount;
	@Column(name = "DESCRIPTION")
	private String description;
	@OneToOne
	@JoinColumn(name = "CLIENT_ID", referencedColumnName = "ID")
	private Client client;
	@OneToOne
	@JoinColumn(name = "SUPPLIER_ID", referencedColumnName = "ID")
	private Supplier supplier;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public long getRefNumber() {
		return refNumber;
	}
	public void setRefNumber(long refNumber) {
		this.refNumber = refNumber;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public Cache getCache() {
		return cache;
	}
	public void setCache(Cache cache) {
		this.cache = cache;
	}
	public Inventory getInventory() {
		return inventory;
	}
	public void setInventory(Inventory inventory) {
		this.inventory = inventory;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Client getClient() {
		return client;
	}
	public void setClient(Client client) {
		this.client = client;
	}
	public Supplier getSupplier() {
		return supplier;
	}
	public void setSupplier(Supplier supplier) {
		this.supplier = supplier;
	}
}
