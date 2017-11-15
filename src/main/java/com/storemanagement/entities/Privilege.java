package com.storemanagement.entities;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
@Entity
@Table(name = "PRIVILEGES")
public class Privilege {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private int id;
	@ManyToOne
	@JoinColumn(name = "ROLE_ID")
	private Role role;
	@OneToOne
	@JoinColumn(name = "PAGE_ID", referencedColumnName = "ID")
	private Page page;
	@Column(name = "VIEW_PRIVILEGE")
	private boolean view;
	@Column(name = "INSERT_PRIVILEGE")
	private boolean insert;
	@Column(name = "UPDATE_PRIVILEGE")
	private boolean update;
	@Column(name = "DELETE_PRIVILEGE")
	private boolean delete;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Role getRole() {
		return role;
	}
	public void setRole(Role role) {
		this.role = role;
	}
	public Page getPage() {
		return page;
	}
	public void setPage(Page page) {
		this.page = page;
	}
	public boolean isView() {
		return view;
	}
	public void setView(boolean view) {
		this.view = view;
	}
	public boolean isInsert() {
		return insert;
	}
	public void setInsert(boolean insert) {
		this.insert = insert;
	}
	public boolean isUpdate() {
		return update;
	}
	public void setUpdate(boolean update) {
		this.update = update;
	}
	public boolean isDelete() {
		return delete;
	}
	public void setDelete(boolean delete) {
		this.delete = delete;
	}
}
