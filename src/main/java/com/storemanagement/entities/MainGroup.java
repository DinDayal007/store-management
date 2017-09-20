package com.storemanagement.entities;
import java.util.ArrayList;
import java.util.Collection;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
@Entity
@Table(name = "MAINGROUPS")
public class MainGroup {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private int id;
	@Column(name = "NAME")
	private String name;
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "mainGroup")
	private Collection<SubGroup> subGroups = new ArrayList<SubGroup>();
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
	public Collection<SubGroup> getSubGroups() {
		return subGroups;
	}
	public void setSubGroups(Collection<SubGroup> subGroups) {
		this.subGroups = subGroups;
	}
}
