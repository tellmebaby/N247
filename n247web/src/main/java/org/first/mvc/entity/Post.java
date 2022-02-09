package org.first.mvc.entity;

import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

public class Post {
	
	private Integer id;
	
	private String nick;
	
	private String postTitle;
	
	private Date create;
	
	private Date lastUpdate;
	
	private String description;
	
	private String rePerson;
	
	private Integer tabId;
	
	private Integer reTabId;
	
	private String tabTitle;
	
	private String time;

	private Integer isDel;
	
	private Integer moveOn;
	
	private Integer Check;
	
	private Integer userNum;
	
	private Integer idN247_re;
	
	private Integer N247_rePoId;
	
	private String N247_reDes;
	
	private Integer N247_reUsId;
	
	private Integer N247_reIsDel;
	
	private Integer N247_reTabId;
	
	private List <Post> replyList;
	
	private String krCreate;
	
	private Date lastModified;
	
	private String imgName;
	
	private List <Post> fileNameList;
	
	private String up_fileName;
	
	private String tab_intro;
	
	private long compareTime;
	
	private String compareMessage;
	
	private String tabLastUpdate;
	
	private Date tab_LastUpdate;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date Tab_dueDay;
	
	private Double progress;
	
	private String progressBg;
	
	private Double tabProgress;
	
	private String tabProgressBg;
	
	private Date dueDay;
	
	private String dueDayString;
	
	private List <Post> friImgList;
	
	private String minDay;
	
	private String maxDay;

	private int tabSelectCheck;
	
	private List <Post> postList;
	
	private List <Post> completePostList;
	
	private List <Post> tabList;
	
	private List <Post> friTabList;
	
	private List <Post> allTabList;
	
	private List <Post> postFileNameList;
	
	private String dueMessage;
	
	private long tabDueDay;

	private Integer tabAdmCheck;
	
	private Integer postAdmCheck;

	private int dueCheck;
	
	private String search;

	private String nickName;
	
	private Integer userId;
	
	private String userImg;
	
	private String mb_introduce;
	
	private int tabCompCheck;
	
	private int up_check;
	
	private String up_orgName;
	
	private Integer tab_userNum;
	
	private int up_isDel;
	
	private int idN247_up;

	private String insertDes;
	
	private Date tab_create;
	
	
	
	
	public Date getTab_create() {
		return tab_create;
	}

	public void setTab_create(Date tab_create) {
		this.tab_create = tab_create;
	}

	public String getInsertDes() {
		return insertDes;
	}

	public void setInsertDes(String insertDes) {
		this.insertDes = insertDes;
	}

	public int getIdN247_up() {
		return idN247_up;
	}

	public void setIdN247_up(int idN247_up) {
		this.idN247_up = idN247_up;
	}

	public int getUp_isDel() {
		return up_isDel;
	}

	public void setUp_isDel(int up_isDel) {
		this.up_isDel = up_isDel;
	}

	public String getTabLastUpdate() {
		return tabLastUpdate;
	}

	public void setTabLastUpdate(String tabLastUpdate) {
		this.tabLastUpdate = tabLastUpdate;
	}

	public Integer getTab_userNum() {
		return tab_userNum;
	}

	public void setTab_userNum(Integer tab_userNum) {
		this.tab_userNum = tab_userNum;
	}

	public Date getTab_LastUpdate() {
		return tab_LastUpdate;
	}

	public void setTab_LastUpdate(Date tab_LastUpdate) {
		this.tab_LastUpdate = tab_LastUpdate;
	}

	public String getUp_orgName() {
		return up_orgName;
	}

	public void setUp_orgName(String up_orgName) {
		this.up_orgName = up_orgName;
	}

	public int getUp_check() {
		return up_check;
	}

	public void setUp_check(int up_check) {
		this.up_check = up_check;
	}

	public int getTabCompCheck() {
		return tabCompCheck;
	}

	public void setTabCompCheck(int tabCompCheck) {
		this.tabCompCheck = tabCompCheck;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getUserImg() {
		return userImg;
	}

	public void setUserImg(String userImg) {
		this.userImg = userImg;
	}

	public String getMb_introduce() {
		return mb_introduce;
	}

	public void setMb_introduce(String mb_introduce) {
		this.mb_introduce = mb_introduce;
	}

	public String getSearch() {
		return search;
	}

	public void setSearch(String search) {
		this.search = search;
	}

	public String getDueDayString() {
		return dueDayString;
	}

	public void setDueDayString(String dueDayString) {
		this.dueDayString = dueDayString;
	}

	public int getDueCheck() {
		return dueCheck;
	}

	public void setDueCheck(int dueCheck) {
		this.dueCheck = dueCheck;
	}

	public Integer getPostAdmCheck() {
		return postAdmCheck;
	}

	public void setPostAdmCheck(Integer postAdmCheck) {
		this.postAdmCheck = postAdmCheck;
	}

	public List<Post> getAllTabList() {
		return allTabList;
	}

	public void setAllTabList(List<Post> allTabList) {
		this.allTabList = allTabList;
	}

	public Integer getTabAdmCheck() {
		return tabAdmCheck;
	}

	public void setTabAdmCheck(Integer tabAdmCheck) {
		this.tabAdmCheck = tabAdmCheck;
	}

	public Integer getN247_reTabId() {
		return N247_reTabId;
	}

	public void setN247_reTabId(Integer n247_reTabId) {
		N247_reTabId = n247_reTabId;
	}

	public List<Post> getPostFileNameList() {
		return postFileNameList;
	}

	public void setPostFileNameList(List<Post> postFileNameList) {
		this.postFileNameList = postFileNameList;
	}

	public long getTabDueDay() {
		return tabDueDay;
	}

	public void setTabDueDay(long tabDueDay) {
		this.tabDueDay = tabDueDay;
	}

	public String getDueMessage() {
		return dueMessage;
	}

	public void setDueMessage(String dueMessage) {
		this.dueMessage = dueMessage;
	}

	public Double getTabProgress() {
		return tabProgress;
	}

	public void setTabProgress(Double tabProgress) {
		this.tabProgress = tabProgress;
	}

	public String getTabProgressBg() {
		return tabProgressBg;
	}

	public void setTabProgressBg(String tabProgressBg) {
		this.tabProgressBg = tabProgressBg;
	}

	public List<Post> getFriTabList() {
		return friTabList;
	}

	public void setFriTabList(List<Post> friTabList) {
		this.friTabList = friTabList;
	}

	public List<Post> getCompletePostList() {
		return completePostList;
	}

	public void setCompletePostList(List<Post> completePostList) {
		this.completePostList = completePostList;
	}

	public List<Post> getPostList() {
		return postList;
	}

	public void setPostList(List<Post> postList) {
		this.postList = postList;
	}

	public List<Post> getTabList() {
		return tabList;
	}

	public void setTabList(List<Post> tabList) {
		this.tabList = tabList;
	}

	public int getTabSelectCheck() {
		return tabSelectCheck;
	}

	public void setTabSelectCheck(int tabSelectCheck) {
		this.tabSelectCheck = tabSelectCheck;
	}

	public String getMinDay() {
		return minDay;
	}

	public void setMinDay(String minDay) {
		this.minDay = minDay;
	}

	public String getMaxDay() {
		return maxDay;
	}

	public void setMaxDay(String maxDay) {
		this.maxDay = maxDay;
	}

	public String getCompareMessage() {
		return compareMessage;
	}

	public void setCompareMessage(String compareMessage) {
		this.compareMessage = compareMessage;
	}

	public List<Post> getFriImgList() {
		return friImgList;
	}

	public void setFriImgList(List<Post> friImgList) {
		this.friImgList = friImgList;
	}

	public Date getDueDay() {
		return dueDay;
	}

	public void setDueDay(Date dueDay) {
		this.dueDay = dueDay;
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

	public Date getTab_dueDay() {
		return Tab_dueDay;
	}

	public void setTab_dueDay(Date tab_dueDay) {
		Tab_dueDay = tab_dueDay;
	}
	
	public long getCompareTime() {
		return compareTime;
	}

	public void setCompareTime(long compareTime) {
		this.compareTime = compareTime;
	}

	public String getTab_intro() {
		return tab_intro;
	}

	public void setTab_intro(String tab_intro) {
		this.tab_intro = tab_intro;
	}

	public List<Post> getFileNameList() {
		return fileNameList;
	}

	public void setFileNameList(List<Post> fileNameList) {
		this.fileNameList = fileNameList;
	}

	public String getUp_fileName() {
		return up_fileName;
	}

	public void setUp_fileName(String up_fileName) {
		this.up_fileName = up_fileName;
	}

	public String getImgName() {
		return imgName;
	}

	public void setImgName(String imgName) {
		this.imgName = imgName;
	}

	public Date getLastModified() {
		return lastModified;
	}

	public void setLastModified(Date lastModified) {
		this.lastModified = lastModified;
	}

	public String getKrCreate() {
		return krCreate;
	}

	public void setKrCreate(String krCreate) {
		this.krCreate = krCreate;
	}

	public List<Post> getReplyList() {
		return replyList;
	}

	public void setReplyList(List<Post> replyList) {
		this.replyList = replyList;
	}

	public Integer getIdN247_re() {
		return idN247_re;
	}

	public void setIdN247_re(Integer idN247_re) {
		this.idN247_re = idN247_re;
	}

	public Integer getN247_rePoId() {
		return N247_rePoId;
	}

	public void setN247_rePoId(Integer n247_rePoId) {
		N247_rePoId = n247_rePoId;
	}

	public String getN247_reDes() {
		return N247_reDes;
	}

	public void setN247_reDes(String n247_reDes) {
		N247_reDes = n247_reDes;
	}

	public Integer getN247_reUsId() {
		return N247_reUsId;
	}

	public void setN247_reUsId(Integer n247_reUsId) {
		N247_reUsId = n247_reUsId;
	}

	public Integer getN247_reIsDel() {
		return N247_reIsDel;
	}

	public void setN247_reIsDel(Integer n247_reIsDel) {
		N247_reIsDel = n247_reIsDel;
	}

	public Integer getUserNum() {
		return userNum;
	}

	public void setUserNum(Integer userNum) {
		this.userNum = userNum;
	}

	public Date getLastUpdate() {
		return lastUpdate;
	}

	public void setLastUpdate(Date lastUpdate) {
		this.lastUpdate = lastUpdate;
	}

	public Integer getMoveOn() {
		return moveOn;
	}

	public void setMoveOn(Integer moveOn) {
		this.moveOn = moveOn;
	}

	public Integer getCheck() {
		return Check;
	}

	public void setCheck(Integer check) {
		Check = check;
	}

	public Integer getIsDel() {
		return isDel;
	}

	public void setIsDel(Integer isDel) {
		this.isDel = isDel;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public Integer getReTabId() {
		return reTabId;
	}

	public void setReTabId(Integer reTabId) {
		this.reTabId = reTabId;
	}

	public Integer getTabId() {
		return tabId;
	}

	public void setTabId(Integer tabId) {
		this.tabId = tabId;
	}

	public String getTabTitle() {
		return tabTitle;
	}

	public void setTabTitle(String tabTitle) {
		this.tabTitle = tabTitle;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNick() {
	return nick;
}

public void setNick(String nick) {
	this.nick = nick;
}

	public String getPostTitle() {
		return postTitle;
	}

	public void setPostTitle(String postTitle) {
		this.postTitle = postTitle;
	}

	public Date getCreate() {
		return create;
	}

	public void setCreate(Date create) {
		this.create = create;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getRePerson() {
		return rePerson;
	}

	public void setRePerson(String rePerson) {
		this.rePerson = rePerson;
	}
	
}

