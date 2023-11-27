package com.fjla.PT.model;

import java.util.List;
import java.util.UUID;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.GenericGenerator;

import com.fasterxml.jackson.annotation.JsonSubTypes.Type;

import jakarta.persistence.*;

@Entity
@Table(name = "Phones")
public class Phones {

	//@Id
	//@GeneratedValue(strategy = GenerationType.AUTO)
	//@Column(name = "ID", updatable = false, nullable = false, unique = true)
	//private long id;
	
	
	
	/*@GeneratedValue(generator = "UUID")
	@GenericGenerator(
	        name = "UUID",
	        strategy = "org.hibernate.id.UUIDGenerator"
	)
	@Column(name = "ID", updatable = false, nullable = false)
	@ColumnDefault("random_uuid()")*/
	
	@Column(name = "id",updatable = false, unique = true, nullable = false)
	private UUID id;
	
	@Id
	@Column(name = "number", unique = true, nullable = false)
	private String number;
	
	@Column(name = "citycode")
	private String citycode;
	
	@Column(name = "contrycode")
	private String contrycode;
	
	
	
	
	public Phones() {
		this.id = java.util.UUID.randomUUID();
	}
	
	public Phones(String number, String citycode, String contrycode) {
		//this.id = java.util.UUID.randomUUID();
		this.number = number;
		this.citycode = citycode;
		this.contrycode = contrycode;
	}

	
	
	/*
	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}*/

	public UUID getId() {
		return id;
	}
/*
	public void setId(UUID id) {
		this.id = id;
	}*/

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getCitycode() {
		return citycode;
	}

	public void setCitycode(String citycode) {
		this.citycode = citycode;
	}

	public String getContrycode() {
		return contrycode;
	}

	public void setContrycode(String contrycode) {
		this.contrycode = contrycode;
	}
	
}
