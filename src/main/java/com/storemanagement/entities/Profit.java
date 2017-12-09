package com.storemanagement.entities;

import java.util.Date;
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
@Table(name = "PROFITS")
public class Profit {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private int id;
	@ManyToOne
	@JoinColumn(name = "SALES_INVOICE_HEADER_ID")
	private SalesInvoiceHeader salesInvoiceHeader;
	@OneToOne
	@JoinColumn(name = "ITEM_ID", referencedColumnName = "ID")
	private Item item;
	@Column(name = "QTY")
	private int qty;
	@Column(name = "PRICE")
	private double price;
	@Column(name = "PROFIT")
	private double profit;
	@Column(name = "INV_DATE")
	private Date invDate;
	@OneToOne
	@JoinColumn(name = "INVENTORY_ID", referencedColumnName = "ID")
	private Inventory inventory;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public SalesInvoiceHeader getSalesInvoiceHeader() {
		return salesInvoiceHeader;
	}
	public void setSalesInvoiceHeader(SalesInvoiceHeader salesInvoiceHeader) {
		this.salesInvoiceHeader = salesInvoiceHeader;
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
	public double getProfit() {
		return profit;
	}
	public void setProfit(double profit) {
		this.profit = profit;
	}
	public Date getInvDate() {
		return invDate;
	}
	public void setInvDate(Date invDate) {
		this.invDate = invDate;
	}
	public Inventory getInventory() {
		return inventory;
	}
	public void setInventory(Inventory inventory) {
		this.inventory = inventory;
	}
}
