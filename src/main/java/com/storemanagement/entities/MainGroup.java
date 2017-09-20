package com.storemanagement.entities;
import java.util.ArrayList;
import java.util.Collection;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
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
	@ElementCollection(fetch = FetchType.EAGER)
	@CollectionTable(name = "SUBGROUPS", joinColumns = @JoinColumn(name = "MAINGROUP_ID"))
	private Collection<SubGroup> supGroups = new ArrayList<SubGroup>();
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
	public Collection<SubGroup> getSupGroups() {
		return supGroups;
	}
	public void setSupGroups(Collection<SubGroup> supGroups) {
		this.supGroups = supGroups;
	}
}
