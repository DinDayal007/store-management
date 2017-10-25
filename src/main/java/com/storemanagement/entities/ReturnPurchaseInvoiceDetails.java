package com.storemanagement.entities;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
@Entity
@Table(name = "RETURN_PURCHASE_INVOICE_DETAILS")
public class ReturnPurchaseInvoiceDetails {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private int id;
	@ManyToOne
	@JoinColumn(name = "RETURN_PURCHASE_INVOICE_HEADER_ID")
	private ReturnPurchaseInvoiceHeader returnPurchaseInvoiceHeader;
	@OneToOne
	@JoinColumn(name = "ITEM_ID", referencedColumnName = "ID")
	private Item item;
	@Column(name = "QTY")
	private int qty;
	@Column(name = "PRICE")
	private double price;
	@Column(name = "TOTAL")
	private double total;
	@OneToOne
	@JoinColumn(name = "UNIT_ID", referencedColumnName = "ID")
	private Unit unit;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public ReturnPurchaseInvoiceHeader getReturnPurchaseInvoiceHeader() {
		return returnPurchaseInvoiceHeader;
	}
	public void setReturnPurchaseInvoiceHeader(
			ReturnPurchaseInvoiceHeader returnPurchaseInvoiceHeader) {
		this.returnPurchaseInvoiceHeader = returnPurchaseInvoiceHeader;
	}
	public Item getItem() {
		return item;
	}
	public void setItem(Item item) {
		this.item = item;
	}
	public int getQty() {
		return qty;
	}
	public void setQty(int qty) {
		this.qty = qty;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public double getTotal() {
		return total;
	}
	public void setTotal(double total) {
		this.total = total;
	}
	public Unit getUnit() {
		return unit;
	}
	public void setUnit(Unit unit) {
		this.unit = unit;
	}
}
