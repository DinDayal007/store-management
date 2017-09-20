package com.storemanagement.entities;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
@Entity
@Table(name = "ITEMS")
public class Item {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private int id;
	@Column(name = "CODE")
	private String code;
	@Column(name = "NAME")
	private String name;
	@Column(name = "UNIT_ID")
	private int unitId;
	@Column(name = "PRICE")
	private double price;
	@Column(name = "HOME")
	private String home;
	@Column(name = "TAX")
	private double tax;
	private int supplierId;
	@Column(name = "MIN_LIMIT")
	private int minLimit;
	@Column(name = "MAX_LIMIT")
	private int maxLimit;
	@Temporal(TemporalType.DATE)
	@Column(name = "EXPIRATION_DATE")
	private Date expirationDate;
	@Temporal(TemporalType.DATE)
	@Column(name = "PRODUCTION_DATE")
	private Date productionDate;
	private int minGroupId;
	private int subGroupId;
}
