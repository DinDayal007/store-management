package com.storemanagement.entities;
import java.util.ArrayList;
import java.util.Collection;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
@Entity
@Table(name = "SUBGROUPS")
public class SubGroup {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private int id;
	@Column(name = "NAME")
	private String name;
	@ManyToOne
	@JoinColumn(name = "MAINGROUP_ID")
	private MainGroup mainGroup;
	@OneToMany(mappedBy = "subGroup")
	private Collection<Item> items = new ArrayList<Item>();
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
	public MainGroup getMainGroup() {
		return mainGroup;
	}
	public void setMainGroup(MainGroup mainGroup) {
		this.mainGroup = mainGroup;
	}
	public Collection<Item> getItems() {
		return items;
	}
	public void setItems(Collection<Item> items) {
		this.items = items;
	}
}
