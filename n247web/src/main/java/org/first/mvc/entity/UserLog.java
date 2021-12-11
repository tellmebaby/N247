package org.first.mvc.entity;

import java.util.Date;

public class UserLog {
	
	private Integer id;
	
	private String email;
	
	private String name;
	
	private String pass;
	
	private String passcon;
	
	private Date create;
	
	private Date lastUpdate;
	
	private Integer isDel;
	
	private String check;
	
	

	
	
	public String getCheck() {
		return check;
	}

	public void setCheck(String check) {
		this.check = check;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}

	public String getPasscon() {
		return passcon;
	}

	public void setPasscon(String passcon) {
		this.passcon = passcon;
	}

	public Date getCreate() {
		return create;
	}

	public void setCreate(Date create) {
		this.create = create;
	}

	public Date getLastUpdate() {
		return lastUpdate;
	}

	public void setLastUpdate(Date lastUpdate) {
		this.lastUpdate = lastUpdate;
	}

	public Integer getIsDel() {
		return isDel;
	}

	public void setIsDel(Integer isDel) {
		this.isDel = isDel;
	}
}
	
	
	
	
	
	
