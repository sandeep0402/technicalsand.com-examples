package com.technicalsand.stream.pojo;

import java.io.Serializable;

public class Student implements Serializable {
	private String name;
	private int rollNo;

	public Student(){}

	public Student(String name, int rollNo){
		this.name = name;
		this.rollNo = rollNo;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getRollNo() {
		return rollNo;
	}

	public void setRollNo(int rollNo) {
		this.rollNo = rollNo;
	}
}
