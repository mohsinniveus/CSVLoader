package com.niveus.CSVLoader.model;

import com.opencsv.bean.CsvBindByName;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "emp")
public class Employee {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	@CsvBindByName(column = "First Name")
	@Column(name = "first_name")
	private String fname;
	
	@CsvBindByName(column = "Last Name")
	@Column(name = "last_name")
	private String lname;
	
	public Employee() {

	}

	public Employee(String fname, String lname) {
		this.fname = fname;
		this.lname = lname;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getFname() {
		return fname;
	}

	public void setFname(String fname) {
		this.fname = fname;
	}

	public String getLname() {
		return lname;
	}

	public void setLname(String lname) {
		this.lname = lname;
	}
	
	@Override
	public String toString() {
		return "Employee [id=" + id + ", fname=" + fname + ", lname=" + lname + "]";
	}

}
