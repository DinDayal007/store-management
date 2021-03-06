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
@Table(name = "USERS")
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private int id;
	@Column(name = "NAME", unique = true, nullable = false)
	private String name;
	@Column(name = "PASSWORD")
	private String password;
	@OneToOne
	@JoinColumn(name = "ROLE_ID", referencedColumnName = "ID")
	private Role role;
	@Column(name = "STATUS")
	private int status;
	@OneToOne
	@JoinColumn(name = "CACHE_ID", referencedColumnName = "ID")
	private Cache cache;
	@OneToOne
	@JoinColumn(name = "INVENTORY_ID", referencedColumnName = "ID")
	private Inventory inventory;
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "CREATED_DATE", updatable = false)
	private Date createdDate;
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "LAST_UPDATED_DATE")
	private Date lastUpdatedDate;
	@OneToOne
	@JoinColumn(name = "CREATED_BY", referencedColumnName = "ID", updatable = false)
	private User createdBy;
	@OneToOne
	@JoinColumn(name = "LAST_UPDATED_BY", referencedColumnName = "ID")
	private User lastUpdatedBy;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public Role getRole() {
		return role;
	}
	public void setRole(Role role) {
		this.role = role;
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
	public Date getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}
	public Date getLastUpdatedDate() {
		return lastUpdatedDate;
	}
	public void setLastUpdatedDate(Date lastUpdatedDate) {
		this.lastUpdatedDate = lastUpdatedDate;
	}
	public User getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(User createdBy) {
		this.createdBy = createdBy;
	}
	public User getLastUpdatedBy() {
		return lastUpdatedBy;
	}
	public void setLastUpdatedBy(User lastUpdatedBy) {
		this.lastUpdatedBy = lastUpdatedBy;
	}
}
