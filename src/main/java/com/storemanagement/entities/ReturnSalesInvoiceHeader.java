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
@Table(name = "RETURN_SALES_INVOICE_HEADER")
public class ReturnSalesInvoiceHeader {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private int id;
	@Column(name = "NUMBER")
	private String number;
	@Temporal(TemporalType.DATE)
	@Column(name = "DATE")
	private Date date;
	@OneToOne
	@JoinColumn(name = "USER_ID", referencedColumnName = "ID")
	private User user;
	@Column(name = "TOTAL")
	private double total;
	@OneToOne
	@JoinColumn(name = "SALES_INVOICE_ID", referencedColumnName = "ID")
	private SalesInvoiceHeader salesInvoiceHeader;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNumber() {
		return number;
	}
	public void setNumber(String number) {
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
	public SalesInvoiceHeader getSalesInvoiceHeader() {
		return salesInvoiceHeader;
	}
	public void setSalesInvoiceHeader(SalesInvoiceHeader salesInvoiceHeader) {
		this.salesInvoiceHeader = salesInvoiceHeader;
	}
}
