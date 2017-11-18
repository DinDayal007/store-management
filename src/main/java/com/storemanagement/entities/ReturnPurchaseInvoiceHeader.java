package com.storemanagement.entities;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

@Entity
@Table(name = "RETURN_PURCHASE_INVOICE_HEADER")
public class ReturnPurchaseInvoiceHeader {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private int id;
	@Column(name = "NUMBER")
	private long number;
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "DATE")
	private Date date;
	@OneToOne
	@JoinColumn(name = "USER_ID", referencedColumnName = "ID")
	private User user;
	@Column(name = "TOTAL")
	private double total;
	@OneToOne
	@JoinColumn(name = "PURCHASE_INVOICE_ID", referencedColumnName = "ID")
	private PurchaseInvoiceHeader purchaseInvoiceHeader;
	@Fetch(value = FetchMode.SUBSELECT)
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "returnPurchaseInvoiceHeader", fetch = FetchType.EAGER)
	private Collection<ReturnPurchaseInvoiceDetails> returnPurchaseInvoiceDetails = new ArrayList<ReturnPurchaseInvoiceDetails>();
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
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public double getTotal() {
		return total;
	}
	public void setTotal(double total) {
		this.total = total;
	}
	public PurchaseInvoiceHeader getPurchaseInvoiceHeader() {
		return purchaseInvoiceHeader;
	}
	public void setPurchaseInvoiceHeader(
			PurchaseInvoiceHeader purchaseInvoiceHeader) {
		this.purchaseInvoiceHeader = purchaseInvoiceHeader;
	}
	public Collection<ReturnPurchaseInvoiceDetails> getReturnPurchaseInvoiceDetails() {
		return returnPurchaseInvoiceDetails;
	}
	public void setReturnPurchaseInvoiceDetails(
			Collection<ReturnPurchaseInvoiceDetails> returnPurchaseInvoiceDetails) {
		this.returnPurchaseInvoiceDetails = returnPurchaseInvoiceDetails;
	}
}
