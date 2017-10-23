package com.storemanagement.entities;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.hibernate.annotations.Immutable;
@Entity
@Immutable
@Table(name = "cash_mvmt")
public class CacheMovementSum {
	@Id
	@Column(name = "inv_number")
	private int invNumber;
	@Column(name = "inventory_id")
	private int inventoryId;
	@Column(name = "inventory_name")
	private String inventoryName;
	@Temporal(TemporalType.DATE)
	@Column(name = "tran_date")
	private Date date;
	@Column(name = "inv_type")
	private String invType;
	@Column(name = "tran_desc")
	private String description;
	@Column(name = "cache_id")
	private int cacheId;
	@Column(name = "cash_name")
	private String cache;
	@Column(name = "total_amt")
	private double total;
	public int getInvNumber() {
		return invNumber;
	}
	public void setInvNumber(int invNumber) {
		this.invNumber = invNumber;
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
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public String getInvType() {
		return invType;
	}
	public void setInvType(String invType) {
		this.invType = invType;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public int getCacheId() {
		return cacheId;
	}
	public void setCacheId(int cacheId) {
		this.cacheId = cacheId;
	}
	public String getCache() {
		return cache;
	}
	public void setCache(String cache) {
		this.cache = cache;
	}
	public double getTotal() {
		return total;
	}
	public void setTotal(double total) {
		this.total = total;
	}
}
