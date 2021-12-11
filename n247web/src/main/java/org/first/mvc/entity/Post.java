package org.first.mvc.entity;

import java.util.Date;
import java.util.List;

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
	
	private List <Post> replyList;
	
	private String krCreate;
	
	private Date lastModified;
	
	private String imgName;
	
	private List <Post> fileNameList;
	
	private String up_fileName;
	
	private String tab_intro;
	
	private long compareTime;
	
	private String TabLastUpdate;
	
	private Date Tab_dueDay;
	
	private Double progress;
	
	private String progressBg;
	
	private Date dueDay;
	
	private List <Post> friImgList;


	
	
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

	public String getTabLastUpdate() {
		return TabLastUpdate;
	}

	public void setTabLastUpdate(String tabLastUpdate) {
		TabLastUpdate = tabLastUpdate;
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

