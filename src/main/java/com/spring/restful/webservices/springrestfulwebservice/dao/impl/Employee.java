package com.spring.restful.webservices.springrestfulwebservice.dao.impl;

import java.util.Objects;

public class Employee implements Comparable<Employee> {

	private int id;
	private String empName;
	private String empAddress;

	public Employee(int id, String empName, String empAddress) {
		super();
		this.id = id;
		this.empName = empName;
		this.empAddress = empAddress;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getEmpName() {
		return empName;
	}

	public void setEmpName(String empName) {
		this.empName = empName;
	}

	public String getEmpAddress() {
		return empAddress;
	}

	public void setEmpAddress(String empAddress) {
		this.empAddress = empAddress;
	}

	@Override
	public String toString() {
		return "Employee [id=" + id + ", empName=" + empName + ", empAddress=" + empAddress + "]";
	}

	@Override
	public int compareTo(Employee o) {

		if(id==o.id) 
			return 0;
		else if(id<o.id) 
			return 1;
		return -1;
	
	}

	@Override
	public int hashCode() {
		return Objects.hash(empAddress, empName, id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Employee other = (Employee) obj;
		return Objects.equals(empAddress, other.empAddress) && Objects.equals(empName, other.empName) && id == other.id;
	}
	

}
