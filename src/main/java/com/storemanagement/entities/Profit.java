package com.storemanagement.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "PROFITS")
public class Profit {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private int id;
	@OneToOne
	@JoinColumn(name = "SALES_INVOICE_HEADER_ID", referencedColumnName = "ID")
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
}
