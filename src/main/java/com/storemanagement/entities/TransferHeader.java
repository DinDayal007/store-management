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
@Table(name = "TRANSFER_HEADER")
public class TransferHeader {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private int id;
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "CREATED_DATE", updatable = false)
	private Date createdDate;
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "CONFIRMATION_DATE")
	private Date confirmationDate;
	@OneToOne
	@JoinColumn(name = "CREATED_BY", referencedColumnName = "ID", updatable = false)
	private User createdBy;
	@OneToOne
	@JoinColumn(name = "CONFIRMED_BY", referencedColumnName = "ID")
	private User confirmedBy;
	@OneToOne
	@JoinColumn(name = "INVENTORY_FROM", referencedColumnName = "ID", updatable = false)
	private Inventory inventoryFrom;
	@OneToOne
	@JoinColumn(name = "INVENTORY_TO", referencedColumnName = "ID", updatable = false)
	private Inventory inventoryTo;
	@OneToOne
	@JoinColumn(name = "CACHE_FROM", referencedColumnName = "ID", updatable = false)
	private Cache cacheFrom;
	@OneToOne
	@JoinColumn(name = "CACHE_TO", referencedColumnName = "ID", updatable = false)
	private Cache cacheTo;
	@Column(name = "TOTAL_PRICE")
	private double totalPrice;
	@Column(name = "STATUS")
	private boolean status;
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "transferHeader")
	private Collection<TransferDetails> transferDetails = new ArrayList<TransferDetails>();
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Date getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}
	public Date getConfirmationDate() {
		return confirmationDate;
	}
	public void setConfirmationDate(Date confirmationDate) {
		this.confirmationDate = confirmationDate;
	}
	public User getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(User createdBy) {
		this.createdBy = createdBy;
	}
	public User getConfirmedBy() {
		return confirmedBy;
	}
	public void setConfirmedBy(User confirmedBy) {
		this.confirmedBy = confirmedBy;
	}
	public Inventory getInventoryFrom() {
		return inventoryFrom;
	}
	public void setInventoryFrom(Inventory inventoryFrom) {
		this.inventoryFrom = inventoryFrom;
	}
	public Inventory getInventoryTo() {
		return inventoryTo;
	}
	public void setInventoryTo(Inventory inventoryTo) {
		this.inventoryTo = inventoryTo;
	}
	public Cache getCacheFrom() {
		return cacheFrom;
	}
	public void setCacheFrom(Cache cacheFrom) {
		this.cacheFrom = cacheFrom;
	}
	public Cache getCacheTo() {
		return cacheTo;
	}
	public void setCacheTo(Cache cacheTo) {
		this.cacheTo = cacheTo;
	}
	public double getTotalPrice() {
		return totalPrice;
	}
	public void setTotalPrice(double totalPrice) {
		this.totalPrice = totalPrice;
	}
	public boolean isStatus() {
		return status;
	}
	public void setStatus(boolean status) {
		this.status = status;
	}
	public Collection<TransferDetails> getTransferDetails() {
		return transferDetails;
	}
	public void setTransferDetails(Collection<TransferDetails> transferDetails) {
		this.transferDetails = transferDetails;
	}
}
