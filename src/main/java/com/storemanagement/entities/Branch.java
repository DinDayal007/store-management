package com.storemanagement.entities;
import java.util.ArrayList;
import java.util.Collection;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.OneToMany;
import javax.persistence.Table;
@Entity
@Table(name = "BRANCHES")
public class Branch {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private int id;
	@Column(name = "NAME")
	private String name;
	@Column(name = "ADDRESS")
	private String Address;
	@Lob
	@Column(name = "DESCRIPTION")
	private String description;
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "branch")
	private Collection<Inventory> inventories = new ArrayList<Inventory>();
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
	public String getAddress() {
		return Address;
	}
	public void setAddress(String address) {
		Address = address;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Collection<Inventory> getInventories() {
		return inventories;
	}
	public void setInventories(Collection<Inventory> inventories) {
		this.inventories = inventories;
	}
}
