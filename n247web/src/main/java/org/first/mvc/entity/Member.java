package org.first.mvc.entity;


import java.util.Date;
import java.util.List;


public class Member {
	
	private String id;

	private String nickName;

	private String password;
	
	private String passwordConfirm;
	
	private String role;
	
	private Integer userId;
	
	private Date create;

	private String search;
	
	private String loginId;
	
	private String sourceIp;
	
	private boolean successful;
	
	private String countryCode;
	
	private String userImg;
	
	private String mb_introduce;
	
	private List<Post> tabList;

	private List<Post> friTabList;
	
	private List<Member> friendInformationList;
	
	
	
	public List<Member> getFriendInformationList() {
		return friendInformationList;
	}

	public void setFriendInformationList(List<Member> friendInformationList) {
		this.friendInformationList = friendInformationList;
	}

	public List<Post> getFriTabList() {
		return friTabList;
	}

	public void setFriTabList(List<Post> friTabList) {
		this.friTabList = friTabList;
	}

	public List<Post> getTabList() {
		return tabList;
	}

	public void setTabList(List<Post> tabList) {
		this.tabList = tabList;
	}

	public String getMb_introduce() {
		return mb_introduce;
	}

	public void setMb_introduce(String mb_introduce) {
		this.mb_introduce = mb_introduce;
	}

	public String getUserImg() {
		return userImg;
	}

	public void setUserImg(String userImg) {
		this.userImg = userImg;
	}

	public String getCountryCode() {
		return countryCode;
	}

	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}

	public String getLoginId() {
		return loginId;
	}

	public void setLoginId(String loginId) {
		this.loginId = loginId;
	}

	public String getSourceIp() {
		return sourceIp;
	}

	public void setSourceIp(String sourceIp) {
		this.sourceIp = sourceIp;
	}

	public boolean isSuccessful() {
		return successful;
	}

	public void setSuccessful(boolean successful) {
		this.successful = successful;
	}

	public String getSearch() {
		return search;
	}

	public void setSearch(String search) {
		this.search = search;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPasswordConfirm() {
		return passwordConfirm;
	}

	public void setPasswordConfirm(String passwordConfirm) {
		this.passwordConfirm = passwordConfirm;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Date getCreate() {
		return create;
	}

	public void setCreate(Date create) {
		this.create = create;
	}

	
	
	
	
	
}

