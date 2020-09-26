package com.technicalsand.springsecurity.auth.defaultlogin.user;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Role {
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Id
	private Long id;
	private String roleName;

	public Role(){}

	public Role(String roleName){
		this.roleName = roleName;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
}
