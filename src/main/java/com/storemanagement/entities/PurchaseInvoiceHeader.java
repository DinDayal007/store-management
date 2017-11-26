package com.storemanagement.entities;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
@Entity
@Table(name = "PURCHASE_INVOICE_HEADER")
public class PurchaseInvoiceHeader {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private int id;
	@Column(name = "NUMBER")
	private long number;
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "DATE")
	private Date date;
	@Column(name = "TYPE")
	private int type;
	@OneToOne
	@JoinColumn(name = "USER_ID", referencedColumnName = "ID")
	private User user;
	@OneToOne
	@JoinColumn(name = "SUPPLIER_ID", referencedColumnName = "ID")
	private Supplier supplier;
	@OneToOne
	@JoinColumn(name = "INVENTORY_ID", referencedColumnName = "ID")
	private Inventory inventory;
	@OneToOne
	@JoinColumn(name = "CACHE_ID", referencedColumnName = "ID")
	private Cache cache;
	@Column(name = "TOTAL")
	private double total;
//	@Column(name = "DISCOUNT")
//	private String discount;
//	@Column(name = "TAX")
//	private int tax;
//	@Column(name = "PAID")
//	private double paid;
//	@Column(name = "REMAIN")
//	private double remain;
	@Column(name = "FINAL_TOTAL")
	private double finalTotal;
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "purchaseInvoiceHeader")
	private Collection<PurchaseInvoiceDetails> purchaseInvoiceDetails = new ArrayList<PurchaseInvoiceDetails>();
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public long getNumber() {
		return number;
	}
	public void setNumber(long number) {
		this.number = number;
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
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public Supplier getSupplier() {
		return supplier;
	}
	public void setSupplier(Supplier supplier) {
		this.supplier = supplier;
	}
	public Inventory getInventory() {
		return inventory;
	}
	public void setInventory(Inventory inventory) {
		this.inventory = inventory;
	}
	public Cache getCache() {
		return cache;
	}
	public void setCache(Cache cache) {
		this.cache = cache;
	}
	public double getTotal() {
		return total;
	}
	public void setTotal(double total) {
		this.total = total;
	}
//	public String getDiscount() {
//		return discount;
//	}
//	public void setDiscount(String discount) {
//		this.discount = discount;
//	}
//	public int getTax() {
//		return tax;
//	}
//	public void setTax(int tax) {
//		this.tax = tax;
//	}
//	public double getPaid() {
//		return paid;
//	}
//	public void setPaid(double paid) {
//		this.paid = paid;
//	}
//	public double getRemain() {
//		return remain;
//	}
//	public void setRemain(double remain) {
//		this.remain = remain;
//	}
//	public double getFinalTotal() {
//		return finalTotal;
//	}
//	public void setFinalTotal(double finalTotal) {
//		this.finalTotal = finalTotal;
//	}
	public Collection<PurchaseInvoiceDetails> getPurchaseInvoiceDetails() {
		return purchaseInvoiceDetails;
	}
	public void setPurchaseInvoiceDetails(
			Collection<PurchaseInvoiceDetails> purchaseInvoiceDetails) {
		this.purchaseInvoiceDetails = purchaseInvoiceDetails;
	}
	public double getFinalTotal() {
		return finalTotal;
	}
	public void setFinalTotal(double finalTotal) {
		this.finalTotal = finalTotal;
	}
}
