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
	
	private Integer tabId;
	
	private List<Post> tabList;

	private List<Post> friTabList;
	
	private List<Post> completeTabList;
	
	private List<Post> allTabList;
	
	private List<Member> friendsInformationList;
	
	private List<Member> userInformation;
	
	private List<Member> loginSucceedGetUserInfo;
	
	private int tabCheck;
	
	private Integer ft_tabId;
	
	private Integer isDel;
	
	private Integer ft_isDel;
	
	private Date modified;
	
	private List <Member> friendAdmList;
	
	private List <Member> iWaitAdmList;
	
	private List <Member> waitingAdmList;
	
	private List <Member> iApproveAdmList;
	
	private List <Member> friendApproveAdmList;
	
	private Integer myId;
	
	private Integer adm;
	
	private Integer fUserId;
	
	private Integer ft_userId;
	
	private Integer idN247_ft;
	
	private int check;
	
	private Double progress;
	
	private String progressBg;

	private int friCheck;
	
	private String lastModified;
	
	private int f_isDel;
	
	private Integer userNum;
	
	
	
	
	public Integer getUserNum() {
		return userNum;
	}

	public void setUserNum(Integer userNum) {
		this.userNum = userNum;
	}

	public List<Member> getLoginSucceedGetUserInfo() {
		return loginSucceedGetUserInfo;
	}

	public void setLoginSucceedGetUserInfo(List<Member> loginSucceedGetUserInfo) {
		this.loginSucceedGetUserInfo = loginSucceedGetUserInfo;
	}

	public int getF_isDel() {
		return f_isDel;
	}

	public void setF_isDel(int f_isDel) {
		this.f_isDel = f_isDel;
	}

	public List<Post> getCompleteTabList() {
		return completeTabList;
	}

	public void setCompleteTabList(List<Post> completeTabList) {
		this.completeTabList = completeTabList;
	}

	public String getLastModified() {
		return lastModified;
	}

	public void setLastModified(String lastModified) {
		this.lastModified = lastModified;
	}

	public int getFriCheck() {
		return friCheck;
	}

	public void setFriCheck(int friCheck) {
		this.friCheck = friCheck;
	}

	public List<Post> getAllTabList() {
		return allTabList;
	}

	public void setAllTabList(List<Post> allTabList) {
		this.allTabList = allTabList;
	}

	public Double getProgress() {
		return progress;
	}

	public void setProgress(Double progress) {
		this.progress = progress;
	}

	public String getProgressBg() {
		return progressBg;
	}

	public void setProgressBg(String progressBg) {
		this.progressBg = progressBg;
	}

	public int getCheck() {
		return check;
	}

	public void setCheck(int check) {
		this.check = check;
	}

	public Integer getIdN247_ft() {
		return idN247_ft;
	}

	public void setIdN247_ft(Integer idN247_ft) {
		this.idN247_ft = idN247_ft;
	}

	public Integer getFt_userId() {
		return ft_userId;
	}

	public void setFt_userId(Integer ft_userId) {
		this.ft_userId = ft_userId;
	}

	public Integer getMyId() {
		return myId;
	}

	public void setMyId(Integer myId) {
		this.myId = myId;
	}

	public Integer getAdm() {
		return adm;
	}

	public void setAdm(Integer adm) {
		this.adm = adm;
	}

	public Integer getfUserId() {
		return fUserId;
	}

	public void setfUserId(Integer fUserId) {
		this.fUserId = fUserId;
	}

	public List<Member> getFriendAdmList() {
		return friendAdmList;
	}

	public void setFriendAdmList(List<Member> friendAdmList) {
		this.friendAdmList = friendAdmList;
	}

	public List<Member> getiWaitAdmList() {
		return iWaitAdmList;
	}

	public void setiWaitAdmList(List<Member> iWaitAdmList) {
		this.iWaitAdmList = iWaitAdmList;
	}

	public List<Member> getWaitingAdmList() {
		return waitingAdmList;
	}

	public void setWaitingAdmList(List<Member> waitingAdmList) {
		this.waitingAdmList = waitingAdmList;
	}

	public List<Member> getiApproveAdmList() {
		return iApproveAdmList;
	}

	public void setiApproveAdmList(List<Member> iApproveAdmList) {
		this.iApproveAdmList = iApproveAdmList;
	}

	public List<Member> getFriendApproveAdmList() {
		return friendApproveAdmList;
	}

	public void setFriendApproveAdmList(List<Member> friendApproveAdmList) {
		this.friendApproveAdmList = friendApproveAdmList;
	}

	public Date getModified() {
		return modified;
	}

	public void setModified(Date modified) {
		this.modified = modified;
	}

	public List<Member> getUserInformation() {
		return userInformation;
	}

	public void setUserInformation(List<Member> userInformation) {
		this.userInformation = userInformation;
	}

	public Integer getIsDel() {
		return isDel;
	}

	public void setIsDel(Integer isDel) {
		this.isDel = isDel;
	}

	public Integer getFt_isDel() {
		return ft_isDel;
	}

	public void setFt_isDel(Integer ft_isDel) {
		this.ft_isDel = ft_isDel;
	}

	public Integer getFt_tabId() {
		return ft_tabId;
	}

	public void setFt_tabId(Integer ft_tabId) {
		this.ft_tabId = ft_tabId;
	}

	public int getTabCheck() {
		return tabCheck;
	}

	public void setTabCheck(int tabCheck) {
		this.tabCheck = tabCheck;
	}

	public Integer getTabId() {
		return tabId;
	}

	public void setTabId(Integer tabId) {
		this.tabId = tabId;
	}

	public List<Member> getFriendsInformationList() {
		return friendsInformationList;
	}

	public void setFriendsInformationList(List<Member> friendsInformationList) {
		this.friendsInformationList = friendsInformationList;
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

