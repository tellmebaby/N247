package org.first.mvc;


import java.io.IOException;
import java.io.InputStream;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.view.RedirectView;
import org.first.mvc.entity.Fn247;
import org.first.mvc.entity.Member;
import org.first.mvc.entity.Post;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET, produces = "application/json; charset=UTF-8")
	public String home(Locale locale, Model model) {
		logger.info("Welcome home! The client locale is {}.", locale);
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		String formattedDate = dateFormat.format(date);
		model.addAttribute("serverTime", formattedDate );
		return "home";
	}
	@RequestMapping(value = "loginAction", method = RequestMethod.POST)
	public String loginAction(Locale locale, Model model, String id, String password, HttpServletRequest request ) {
	
		Integer count = DAO.checkLoginMember(id,password);
		String em = "Email이나 비밀번호를 다시 확인하세요. ";
		
		if(count == 0) {
			model.addAttribute("errorMessage",em);
			return "home";
		}
			//로그인 성공시 
			System.out.println("로그인 체크 결과 성공하셨습니다.");
			HttpSession session = request.getSession();
			//유저정보를 id로 불러온다.
			Member userInformation = DAO.getMemberToId(id);
			//친구목록을 유저번호로 불러온다.
			Fn247 friendAllList = DAO.getFriendAllList(userInformation.getUserId());
			//사용자의 유형을 탭목록의 정보로 1,2,3으로 체크한다.
			Integer tabCheck = DAO.getCountTab(userInformation.getUserId());
			System.out.println("탭체크결과 : "+tabCheck);
			//불러온 정보를 세션으로 보낸다.
			session.setAttribute("userInformation", userInformation); 
			session.setAttribute("friendAllList", friendAllList);
			session.setAttribute("tabCheck", tabCheck);
			//친구목록에 자신의 번호를 넣어 유저정보를 입력해준다.
			Fn247 friendTest = DAO.getFriendAllList(userInformation.getUserId());
			Member friendsInformationList = new Member();
			List<Member> t1 = new ArrayList<Member>();
			Member friendsInformation = new Member();
			//모든친구 목록에 유저정보를 입력해준다.
			for (int i=0 ; i<friendTest.getFriendAllList().size() ; i++) {
				//친구목록의 myId가 내꺼면 fuserId()의 정보를 넣어준다 
				if(friendTest.getFriendAllList().get(i).getMyId() == userInformation.getUserId()) {
					friendsInformation=DAO.getMember(friendTest.getFriendAllList().get(i).getfUserId());
				//아니면 myId 의 정보를 넣어준다.	
				}else {
					friendsInformation=DAO.getMember(friendTest.getFriendAllList().get(i).getMyId());
				}
				 t1.add(friendsInformation);
			}
			friendsInformationList.setFriendInformationList(t1);
			//입력된 정보를 세션으로 보낸다.
			session.setAttribute("friendsInformationList", friendsInformationList);
			
			for (int i=0 ; i<friendsInformationList.getFriendInformationList().size() ; i++) {
				System.out.println("친구들 정보를 세션으로 보냅니다. : getId :"+friendsInformationList.getFriendInformationList().get(i).getId()
														+" , getNickName :"+friendsInformationList.getFriendInformationList().get(i).getNickName()
														+" , getMb_introduce :"+friendsInformationList.getFriendInformationList().get(i).getMb_introduce()
														+" , getUserImg :"+friendsInformationList.getFriendInformationList().get(i).getUserImg()
						                                +" , getUserId :"+friendsInformationList.getFriendInformationList().get(i).getUserId());
			}
			
			//사용자의 탭유형이 0 : 아무것도 없으면 탭을 만들 수 있는 페이지로 이동 
			if(tabCheck == 0 ) {
				
				return "firstboard";
			//사용자의 탭유형이 2 : 자신의 탭은 없고 공유탭이 있으면 보드로 이동 	
			}else if (tabCheck == 2){
				//사용자정보에 탭목록을 넣어준다.
				userInformation.setFriTabList(DAO.readFriTabListAction(userInformation.getUserId()));
				return "redirect:board?tabId="+userInformation.getFriTabList().get(0).getTabId();
			//사용자의 탭유형이 1 : 자신의 탭만 있거나 공유탭도 있으면 보드로 이동 
			}else {
				//마지막으로 사용한 탭의 번호를 찾는다.
				Post tabId = new Post(); 
				tabId = DAO.getSecondTabIdAction(userInformation.getUserId());
				//사용자정보에 탭목록을 받아서 넣어준다.
				userInformation.setTabList(DAO.readTabListAction(userInformation.getUserId()));
				//사용자정보에 공유탭목록을 받아서 넣어준다.
				userInformation.setFriTabList(DAO.readFriTabListAction(userInformation.getUserId()));
				System.out.println("userInformation에공유탭목록 집어넣었니 : "+userInformation.getFriTabList().size());

				return "redirect:board?tabId="+tabId.getTabId();
			}
		
	}
	@RequestMapping(value = "board", method = RequestMethod.GET, produces = "application/json; charset=UTF-8")
	public String board(Locale locale, Model model, HttpServletRequest request, Integer tabId ) {
			
			HttpSession session = request.getSession();
			Fn247 friendAllList = (Fn247) session.getAttribute("friendAllList");
			//member 정보보내기 연습중
			Member friendsInformationList = (Member) session.getAttribute("friendsInformationList");
			if(friendsInformationList.getFriendInformationList().size() != 0) {
				System.out.println("친구정보를모두 받아왔어요 "+friendsInformationList.getFriendInformationList().size()+"개");
			}
			Member userInformation = (Member) session.getAttribute("userInformation");
			session.setAttribute("userInformation", userInformation); 
			Integer tabCheck = (Integer) session.getAttribute("tabCheck");
			Integer tabCheck2 = DAO.getCountTab(userInformation.getUserId());
			if(tabCheck != tabCheck2) {
				tabCheck = tabCheck2;
				System.out.println("보드로 바로 접근해서 탭유형을 다시정의한다." + tabCheck +" != "+ tabCheck2);
			}
			//탭번호가 공유에 있는지 판단 
			Integer friCheck = DAO.getCheckFriId(userInformation.getUserId(), tabId);
			Post thisTab = new Post();
			Post cards = new Post();
			//접근승인
			if(userInformation.getUserId() == DAO.getUserNumToTabId(tabId) || tabCheck != 0 ) {
				// 1번유형 자신의 탭만 있거나 공유된 탭도 가지고 있다.
				if (tabCheck == 1) {
					System.out.println("반가워요 "+userInformation.getNickName()+
							"님 프로젝트가 " + userInformation.getTabList().size() + "개 있네요");

					//탭번호가 공유껀지 아닌지 판단
					if(friCheck != 1) {
						//나의 탭목록중 현재탭의 정보를 불러온다.
						System.out.println("공유 체크 결과 1번 진행 ");
						thisTab = DAO.getTabInforAction(tabId,userInformation.getTabList());
						//탭삭제시 현재탭은 선택제외할 체크파라미터를 유저인포에 넣어준다.
						userInformation.setTabList(DAO.getSelectionList(tabId, userInformation.getTabList()));
						for (int i=0 ; i<userInformation.getTabList().size(); i++) {
							System.out.println(userInformation.getTabList().get(i).getTabId()+"번 탭의 체크결과1는 : "+userInformation.getTabList().get(i).getCheck());
						}
					}else {
						//공유 탭목록중 현재탭의 정보를 불러온다.
						thisTab = DAO.getTabInforAction(tabId,userInformation.getFriTabList());
					}
				// 2번 유형 자신의 탭은 없고 공유된탭이 있다
				}else if(tabCheck == 2) {
					System.out.println("반가워요 "+userInformation.getNickName()+
							"님 공유된 프로젝트가 " + userInformation.getFriTabList().size() + "개 있네요 공유탭 진행 ");
					//공유탭 목록 중 현재 탭의 정보를 불러온다.
					thisTab = DAO.getTabInforAction(tabId,userInformation.getFriTabList());
					if(userInformation.getFriTabList() != null) {
						for (int i=0 ; i<userInformation.getFriTabList().size(); i++) {
							System.out.println("없나봐  " + userInformation.getFriTabList().get(i).getTab_dueDay());
						}
					}
				}
				if(userInformation.getFriTabList() != null) {
					System.out.println("$$$$$$$$$$$$$$$$여기 실행 됐어요 ");
					
					userInformation=(DAO.sideBarAdmTabimgSet(userInformation,friendsInformationList));
					System.out.println("$$$$$$$$$$$$$$$$여기 실행 됐어요 ");
				}
				
				
				//친구목록 세션에서 받아와서 분류해서 모델로 JSP에 보
				Fn247 friendSet = new Fn247();
				friendSet = DAO.friendListClassification(friendAllList.getFriendAllList(),userInformation.getUserId(),tabId, friendsInformationList);
				if(friendSet.getiApproveAdmList() != null) {
					for(int i=0 ; i<friendSet.getiApproveAdmList().size() ; i++) {
						System.out.println("@#$@#$@#$@#$@#$@#$@#$"+friendSet.getiApproveAdmList().get(i).getF_name() + "님 의 "
								+"정보가 friendSet.getiApproveAdmList() 에 들어있어요 사진이름은 : " 
								+ friendSet.getiApproveAdmList().get(i).getF_imgName());
					}
				}
				
				//friendSet = DAO.friendListClassification(friendAllList.getFriendAllList(),userInformation.getUserId(),tabId);
				cards = DAO.readPostListAction(tabId,userInformation);
				//카드별로 댓글달고 댓글 쓴사람들 정보 주기
				cards.setPostList(DAO.replyListSetUserInfo(cards.getPostList() , userInformation.getUserId(), friendsInformationList, userInformation.getNickName()));
				cards.setCompletePostList(DAO.replyListSetUserInfo(cards.getCompletePostList() , userInformation.getUserId(), friendsInformationList, userInformation.getNickName()));
 
		        //프로젝트 카드에 뭐들은지 보자 (진행중)
		          for(int i=0 ; i<cards.getPostList().size() ; i++) {
						System.out.println(cards.getPostList().get(i).getId()+"번 포스트의 내용은 : "				
										+ " PostTitle():" +cards.getPostList().get(i).getPostTitle()
										+ " getDescription():" +cards.getPostList().get(i).getDescription()
										+ " getCreate():" +cards.getPostList().get(i).getCreate()
										+ " getTabId():" +cards.getPostList().get(i).getTabId()
										+ " getIsDel():" +cards.getPostList().get(i).getIsDel()
										+ " getLastUpdate():" +cards.getPostList().get(i).getLastUpdate()
										+ " getUserNum():" +cards.getPostList().get(i).getUserNum()
										+ " getDueDay():" +cards.getPostList().get(i).getDueDay()
										+ " getUp_fileName():" +cards.getPostList().get(i).getUp_fileName()
										+ " getCheck():" +cards.getPostList().get(i).getCheck()
										+ " getTime():" +cards.getPostList().get(i).getTime()
										+ " getCompareTime():" +cards.getPostList().get(i).getCompareTime()
										+ " getCompareMessage():" +cards.getPostList().get(i).getCompareMessage()
										+ " getProgress():" +cards.getPostList().get(i).getProgress()
										+ " getProgressBg():" +cards.getPostList().get(i).getProgressBg()
										+" getImgName() :"+cards.getPostList().get(i).getImgName());
					}  
				

				//이탭이 공유탭인지 판단해서 공유시 막야될 기능들을 꺼준다. 1이 공유 
				thisTab.setTabAdmCheck(friCheck);
				String tabLastUpdate = null;
				//프로젝트의 마지막 업데이트에 포스트가 없을경우 탭의 마지막 수정시간을 가져와서 넣어준다. 
				System.out.println("2 thisTab의 탭이름은: " + thisTab.getTabTitle());
				if(cards.getPostList().size() == 0) {
					tabLastUpdate = DAO.readTabLastUpdateAction(tabId);
				}else if(cards.getPostList().size() == 1){
					tabLastUpdate = DAO.getPostLastUpdateAction(tabId);
				}else {
					tabLastUpdate = DAO.readTabLastUpdateAction(tabId);
					System.out.println("가져온 라스트 업데이트 시간 : " + tabLastUpdate);	
				}
				System.out.println("3 thisTab의 탭이름은: " + thisTab.getTabTitle());
				//탭삭제시 포스트가 있으면 탭을 선택하게 하지만 다른탭이 없을경우 그냥 삭제하게 해준다.
				if(thisTab.getTabSelectCheck()==0 || tabCheck==0) {
					thisTab.setTabSelectCheck(0);
				}else {
					thisTab.setTabSelectCheck(1);
				}
				
				//공유탭 사이드바 사진체크 
				if(userInformation.getFriTabList() != null) {
					for (int i=0 ; i<userInformation.getFriTabList().size(); i++) {
						System.out.println("&&&&&&&&&&&userInformation.getFriTabList() != null 이라서 확인좀 할게 : "+userInformation.getFriTabList().get(i).getImgName());
					}	
				}
				
				
				System.out.println("3 thisTab의 탭이름은: " + thisTab.getTabTitle());
				model.addAttribute("cards",cards);
				model.addAttribute("userInformation",userInformation);
				model.addAttribute("friendSet",friendSet);
				model.addAttribute("thisTab",thisTab);
				model.addAttribute("tabCheck",tabCheck);
				model.addAttribute("tabLastUpdate",tabLastUpdate);

		    // 접근실패	
			}else {
				
				System.out.println("탭주인 체크 결과 실패  ");
				return "home";	
			}
		return "board";
	}
	
	@RequestMapping(value = "top", method = RequestMethod.GET, produces = "application/json; charset=UTF-8")
	public String top(Locale locale, Model model) {
		return "top";
	}
	@RequestMapping(value = "firstboard", method = RequestMethod.GET, produces = "application/json; charset=UTF-8")
	public String firstboard(Locale locale, Model model, HttpServletRequest request ) {
		
		HttpSession session = request.getSession();
		Integer userNum = (Integer) session.getAttribute("userNum");
		String nickName = (String) session.getAttribute("nickName");
		
		if(userNum != null) {
			return "home";
		}
		Post userSet = new Post();
		userSet.setUserNum(userNum);
		userSet.setNick(nickName);
		model.addAttribute("userSet",userSet);
		return "firstboard";
	}
	
	@RequestMapping(value = "userInfoUpdateAction", method = RequestMethod.GET, produces = "application/json; charset=UTF-8")
	public RedirectView userInfoUpdateAction(Locale locale, Model model, String nickName, String mb_introduce ,Integer userId, Integer tabId  ) {
		
		DAO.userInfoUpdate(nickName, mb_introduce, userId);
		return new RedirectView ("board?tabId="+tabId+"&&friCheck=0");
	}
		
	@RequestMapping(value = "selectTab", method = RequestMethod.GET, produces = "application/json; charset=UTF-8")
	public String selectTab(Locale locale, Model model, String nick, Integer tabId) {
		List<Post> result = new ArrayList<Post>();
		List<Post> selectTabList = new ArrayList<Post>();
		List<Post> tabList = new ArrayList<Post>();
		Integer userNum = DAO.getUserNumToTabId(tabId);
		tabList = DAO.readTabListAction(userNum);
		for(int i=0 ; i<tabList.size() ; i ++) {
			if(tabList.get(i).getTabId() == tabId) {
			}else {
				Post p1 = new Post();
				p1.setTabId(tabList.get(i).getTabId());
				p1.setTabTitle(tabList.get(i).getTabTitle());
				result.add(p1);					
			}
		}
		for(int i=0 ; i<tabList.size() ; i ++) {
			if(tabList.get(i).getTabId() == tabId) {
			}else {
				Post p1 = new Post();
				p1.setTabId(tabList.get(i).getTabId());
				p1.setTabTitle(tabList.get(i).getTabTitle());
				selectTabList.add(p1);					
			}
		}
		model.addAttribute("tabList",result);
		model.addAttribute("moveOn", tabId);
		return "selectTab";
	}
	
	
	@RequestMapping(value = "updateTab", method = RequestMethod.GET, produces = "application/json; charset=UTF-8")
	public String updateTab(Locale locale, Model model, Integer tabId ) {
		Integer userNum = DAO.getUserNumToTabId(tabId);
		String nickName = DAO.getNickNameToUserNum(userNum);
		Post userSet = new Post();
		userSet.setNick(nickName);
		userSet.setUserNum(userNum);
		List<Post> tabList = new ArrayList<Post>();
		tabList = DAO.readTabListAction(userNum);
		for(int i=0 ; i<tabList.size() ; i++) {
			Integer check = DAO.getCountPostCheck(tabList.get(i).getTabId());
			if(check == 0) {
				tabList.get(i).setCheck(0);
			}else {
				tabList.get(i).setCheck(1);
			}
	 	}
		model.addAttribute("userSet",userSet);
		model.addAttribute("tabList",tabList);
		model.addAttribute("selectedTabId", tabId);
		return "updateTab";
	}
	
	
	@RequestMapping(value = "updateTabTitle", method = RequestMethod.GET, produces = "application/json; charset=UTF-8")
	public String updateTabTitle(Locale locale, Model model, Integer tabId) {	
		
		
	   List<Post> tabTitle = new ArrayList<Post>();
	   tabTitle = DAO.readTabAction(tabId);
	   Post result = new Post();
	   result.setTabId(tabTitle.get(0).getTabId());
	   result.setTabTitle(tabTitle.get(0).getTabTitle());
		model.addAttribute("tabTitle",result);
		return "updateTabTitle";
	}
	
	@RequestMapping(value = "friendBook", method = RequestMethod.GET, produces = "application/json; charset=UTF-8")
	public String friendBook(Locale locale, Model model, Integer userNum, Integer tabId ) {
		List<Fn247> friList1 = new ArrayList<Fn247>();
		List<Fn247> friList2 = new ArrayList<Fn247>();
		List<Fn247> friList3 = new ArrayList<Fn247>();
		List<Fn247> friAdmList = new ArrayList<Fn247>();
		friList1 = DAO.readFriListAction(userNum,1,tabId);
		for(int i=0 ; i<friList1.size() ; i++) {
			//System.out.println("모델로 보내는 친구 아이디 : " + friList1.get(i).getfUserId());
		}
		
		friAdmList = DAO.readFriAdmList(userNum,1,tabId);
		friList2 = DAO.readFriListAction(userNum,0,tabId);
		friList3 = DAO.readReFriListAction(userNum);
		
		model.addAttribute("userNum",userNum);
		model.addAttribute("selectTab", tabId);
		model.addAttribute("friendList1",friList1);
		model.addAttribute("friendList2",friList2);
		model.addAttribute("friendList3",friList3);
		model.addAttribute("friendAdmList",friAdmList);
		return "friendBook";
	}
	
	
	@RequestMapping(value = "searchFriendError", method = RequestMethod.GET, produces = "application/json; charset=UTF-8")
	public String searchFriendError(Locale locale, Model model, Integer userNum, Integer tabId ) {
	
		
		model.addAttribute("userNum",userNum);
		model.addAttribute("selectTab", tabId);
		return "searchFriendError";
	}
	
	@RequestMapping(value = "searchFriend", method = RequestMethod.GET, produces = "application/json; charset=UTF-8")
	public String searchFriend(Locale locale, Model model, String search, Integer userNum, Integer tabId ) {

		Fn247 friId = new Fn247();
		friId.setfUserId(DAO.getUserNum(search));
		
		model.addAttribute("search",search);
		model.addAttribute("userNum",userNum);
		model.addAttribute("selectTab", tabId);
		model.addAttribute("fUserId", friId);
		return "searchFriend";
	}
//CRUD
	

	@RequestMapping(value = "createMemberAction", method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
		public RedirectView createMemberAction(Locale locale, Model model, String email, String name, String pass, String passcon ) {
	
			DAO.createMember(email,name,pass,passcon);
			
			return new RedirectView("/mvc/");
		}
	@RequestMapping(value = "memberAction", method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
	public String memberAction(Locale locale, Model model, String id, String nickName, String password, String passwordConfirm , String role) {
		String resource = "org/first/mvc/mybatis_config.xml";
		InputStream inputStream;		
		Member member = new Member();
		String em = "Email이 이미 존재하거나 비밀번호입력을 다시 확인하세요. ";
		try {
			inputStream = Resources.getResourceAsStream(resource);
			SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
			SqlSession session = sqlSessionFactory.openSession();
			member.setId(id);
			member.setNickName(nickName);
			member.setPassword(password);
			member.setPasswordConfirm(passwordConfirm);
			member.setRole(role);
			boolean idCheck = false;
			boolean nickCheck = false;
			boolean pswCheck = false;
			boolean pswConfirm = false;
			Integer countId = session.selectOne("org.first.mvc.BaseMapper.countId", id);
			if(countId==0) {
				idCheck = true ;
			}
			Integer countNick = session.selectOne("org.first.mvc.BaseMapper.countNick", nickName);
			if(countNick==0) {
				nickCheck = true ;
			}
			if(password.equals(passwordConfirm)) {
				pswConfirm = true;
			}
			if(Pattern.matches("^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{8,}$", password)) {
				pswCheck = true;
			}
			if(idCheck==true&&nickCheck==true&&pswCheck==true&&pswConfirm==true) {
				session.insert("org.first.mvc.BaseMapper.insertMember", member);
				session.commit();
				session.close();
			}else {
				model.addAttribute("errorMessage",em);
				return "home";
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return "firstboard";
	}
	@RequestMapping(value = "boardAction", method = RequestMethod.GET, produces = "application/json; charset=UTF-8")
	public String BoardAction(Locale locale, Model model, Integer tabId) {
		
		return "redirect:board?tabId?="+tabId;
	}
	
	@RequestMapping(value = "logOutAction", method = RequestMethod.GET, produces = "application/json; charset=UTF-8")
	public RedirectView logOutAction(Locale locale, Model model, HttpServletRequest request) {

		HttpSession session = request.getSession();
		session.invalidate();
		
		return new RedirectView("/mvc/");
	}
	
	//포스트 올릴경우 
	@RequestMapping(value = "createPostAction33", method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
		public RedirectView createPostAction33 (Locale locale, Model model, String postTitle, String description, Integer tabId, Integer userNum, Date dueDay ) {
			
			DAO.createPost(postTitle,description,tabId,userNum,dueDay);
			//FileService.uploadFile(multiRequest,userNum,DAO.getPostId(tabId));
			
			return new RedirectView("board?tabId="+tabId);
		}
	//방문객이 포스트 올릴경우 파라미터 포스트제목, 내용, 프로젝트탭아이디, 사용자번호 를 받아온다  이렇게 
	@RequestMapping(value = "createPostAction2", method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
		public RedirectView createPostAction2(Locale locale, Model model, String postTitle, String description, Integer tabId, Integer userNum, Date dueDay ) {
			
			DAO.createPost(postTitle,description,tabId,userNum,dueDay);
			return new RedirectView("board?tabId="+tabId);
		}
	
	 @RequestMapping(value = "updatePostAction", method = RequestMethod.POST)
		public RedirectView updatePostAction(Locale locale, Model model, Integer id, String postTitle, String description, Integer tabId , @DateTimeFormat(pattern="yyyy-MM-dd") Date dueDay) {
		 	DAO.updatePost(id, postTitle, description, tabId, dueDay);
			return new RedirectView("board?tabId="+tabId);
		}
	 
	 @RequestMapping(value = "updateCompletePostAction", method = RequestMethod.GET)
		public RedirectView completePostAction(Locale locale, Model model, Integer id, Integer isDel, Integer tabId, HttpServletRequest request) {
		 	DAO.completePost(id,isDel);
		 	HttpSession session = request.getSession();
			Member userInformation = (Member) session.getAttribute("userInformation");
			session.setAttribute("userInformation", userInformation); 
			
			userInformation.setTabList(DAO.readTabListAction(userInformation.getUserId()));
			userInformation.setFriTabList(DAO.readFriTabListAction(userInformation.getUserId()));
			return new RedirectView("board?tabId="+tabId);
		}
	 
	 @RequestMapping(value = "updateTabIntroAction", method = RequestMethod.POST)
		public RedirectView updateTabIntro(Locale locale, Model model, Integer tabId, String tab_intro, HttpServletRequest request ) {
		 	DAO.updateTabIntro(tabId, tab_intro);
		 	HttpSession session = request.getSession();
			Member userInformation = (Member) session.getAttribute("userInformation");
			session.setAttribute("userInformation", userInformation); 
			
			userInformation.setTabList(DAO.readTabListAction(userInformation.getUserId()));
			userInformation.setFriTabList(DAO.readFriTabListAction(userInformation.getUserId()));
			return new RedirectView("board?tabId="+tabId);
		}
	 @RequestMapping(value = "updateTabDueDayAction", method = RequestMethod.POST)
		public RedirectView updateTabDueDayAction(Locale locale, Model model, Integer tabId, @DateTimeFormat(pattern="yyyy-MM-dd") Date tab_dueDay, HttpServletRequest request ) {
		 	DAO.updateTabDueDay(tabId, tab_dueDay);
		 	HttpSession session = request.getSession();
			Member userInformation = (Member) session.getAttribute("userInformation");
			session.setAttribute("userInformation", userInformation); 

			userInformation.setTabList(DAO.readTabListAction(userInformation.getUserId()));
			userInformation.setFriTabList(DAO.readFriTabListAction(userInformation.getUserId()));
			return new RedirectView("board?tabId="+tabId);
		}
	 @RequestMapping(value = "updatePostAction2", method = RequestMethod.POST)
		public RedirectView updatePostAction2(Locale locale, Model model, Integer id, String postTitle, String description, Integer tabId, @DateTimeFormat(pattern="yyyy-MM-dd") Date dueDay ,Integer userNum ) {
		 	DAO.updatePost(id, postTitle, description, tabId, dueDay);
			return new RedirectView("board?tabId="+tabId);
		}
	 @RequestMapping(value = "updateFriAdmissionAction", method = RequestMethod.GET)
		public RedirectView updateFriAdmissionAction(Locale locale, Model model, Integer idN247_f, Integer userNum, Integer tabId ) {
		 	DAO.updateFriAdmission(idN247_f, userNum, tabId);
			return new RedirectView("friendBook?userNum="+userNum+"&&tabId="+tabId);
		}
	 
	@RequestMapping(value = "createFriTabAdd", method = RequestMethod.GET)
		public RedirectView createFriTabAdd(Locale locale, Model model, Integer ft_userId, Integer ft_tabId, HttpServletRequest request ) {
	    	DAO.createFriTabAdd(ft_userId, ft_tabId);
	    	HttpSession session = request.getSession();
			Member userInformation = (Member) session.getAttribute("userInformation");
			session.setAttribute("userInformation", userInformation); 

			userInformation.setTabList(DAO.readTabListAction(userInformation.getUserId()));
			userInformation.setFriTabList(DAO.readFriTabListAction(userInformation.getUserId()));
			return new RedirectView("board?tabId="+ft_tabId);
		}
	 
	@RequestMapping(value = "updateDelFriAction", method = RequestMethod.GET)
		public RedirectView updateDelFriAction(Locale locale, Model model, Integer idN247_f, Integer userNum, Integer tabId, Integer ft_userId ) {
	 		if(DAO.getCheckFriId(ft_userId,tabId)==0) {
	 			DAO.updateDelFri(idN247_f);
	 		}else {
	 			DAO.updateDelFriToTab(DAO.getFriAdmIdN247_ft(ft_userId, tabId));
	 			DAO.updateDelFri(idN247_f);
	 		}
			return new RedirectView("friendBook?userNum="+userNum+"&&tabId="+tabId);
		}

	@RequestMapping(value = "updateReplyAction", method = RequestMethod.POST)
		public RedirectView updateReplyAction(Locale locale, Model model, Integer idN247_re, Integer tabId, String n247_reDes ) {
	 		DAO.updateReply(idN247_re, n247_reDes);
			return new RedirectView("board?tabId="+tabId);
		}
	 	
	@RequestMapping(value = "updateDelFriToTabAction", method = RequestMethod.GET)
		public RedirectView updateDelFriToTabAction(Locale locale, Model model, Integer idN247_ft, Integer tabId ) {
	 		DAO.updateDelFriToTab(idN247_ft);
			return new RedirectView("board?tabId="+tabId);
		}

	@RequestMapping(value = "deletePostAction", method = RequestMethod.GET)
		public RedirectView deletePostAction(Locale locale, Model model, Integer id, Integer tabId ) {
			DAO.deletePost(id, tabId);
			return new RedirectView("/mvc/board?tabId="+tabId);
		}
	
	@RequestMapping(value = "searchFriendAction", method = RequestMethod.GET)
		public String searchFriendAction (Locale locale, Model model, String search, Integer userNum, Integer tabId ) {
			
			int count = DAO.searchFriend(search);
			String errormessage = "";
				if(count == 0) {
					errormessage = "회원을 찾을 수 없습니다. 이메일을 확인해주세요. ";
				    model.addAttribute("em",errormessage);
					model.addAttribute("userNum",userNum);
					model.addAttribute("selectTab", tabId);
					return "searchFriendError";
				}else {
					int reCount = DAO.searchFriend2(search, userNum);
					if(reCount == 0) {
						model.addAttribute("search",search);
						model.addAttribute("userNum",userNum);
						model.addAttribute("selectTab", tabId);
						model.addAttribute("fUserId", DAO.getUserNum(search));
						return "searchFriend";
					}
					errormessage = "친구신청을 이미 했습니다. 이메일을 확인해주세요. ";
				    model.addAttribute("em",errormessage);
					model.addAttribute("userNum",userNum);
					model.addAttribute("selectTab", tabId);
					return "searchFriendError";
				}
		}
	
	//프로젝트를 새로 생성합니다. 
	@RequestMapping(value = "createTabAction", method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
		public RedirectView createTabAction(Locale locale, Model model, String tabTitle, Integer userNum, String tab_intro, @DateTimeFormat(pattern="yyyy-MM-dd") Date tab_dueDay, String id, HttpServletRequest request ) {
			
			HttpSession session = request.getSession();
			Member userInformation = DAO.getMemberToId(id);
			session.setAttribute("userInformation", userInformation); 
			
			DAO.createTab(tabTitle, userNum,tab_intro,tab_dueDay);
			Post getTabId = new Post();
			getTabId.setTabId(DAO.getTabIdAction(tabTitle).get(0).getTabId());
			userInformation.setTabList(DAO.readTabListAction(userInformation.getUserId()));
			userInformation.setFriTabList(DAO.readFriTabListAction(userInformation.getUserId()));
			return new RedirectView("board?tabId="+getTabId.getTabId());
		}

	@RequestMapping(value = "createReplyAction", method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
		public RedirectView createReplyAction(Locale locale, Model model, String n247_reDes, Integer n247_reUsId , Integer n247_rePoId , Integer tabId, HttpServletRequest request) {
		DAO.createReply(n247_reDes, n247_reUsId, n247_rePoId, tabId );
		
		HttpSession session = request.getSession();
		Member userInformation = DAO.getMember(n247_reUsId);
		session.setAttribute("userInformation", userInformation); 

		userInformation.setTabList(DAO.readTabListAction(userInformation.getUserId()));
		userInformation.setFriTabList(DAO.readFriTabListAction(userInformation.getUserId()));
			return new RedirectView("board?tabId="+tabId);
		}
	
	@RequestMapping(value = "friendSubscriptionAction", method = RequestMethod.GET, produces = "application/json; charset=UTF-8")
		public String friendSubscriptionAction(Locale locale, Model model, Integer fUserId, Integer myId , Integer tabId ) {
			
			DAO.friendSubscription(fUserId, myId, tabId);
	
			Fn247 p1 = new Fn247();
			p1.setfUserId(fUserId);
			p1.setMyId(myId);
			
			List<Fn247> friList1 = new ArrayList<Fn247>();
			List<Fn247> friList2 = new ArrayList<Fn247>();
			List<Fn247> friList3 = new ArrayList<Fn247>();
			List<Fn247> friAdmList = new ArrayList<Fn247>();
			
			friList1 = DAO.readFriListAction(myId,1,tabId);
			friAdmList = DAO.readFriAdmList(myId,1,tabId);
			friList2 = DAO.readFriListAction(myId,0,tabId);
			friList3 = DAO.readReFriListAction(myId);
			
			model.addAttribute("userNum",myId);
			model.addAttribute("selectTab", tabId);
			model.addAttribute("friendList1",friList1);
			model.addAttribute("friendList2",friList2);
			model.addAttribute("friendList3",friList3);
			model.addAttribute("friendAdmList",friAdmList);
			
			return "friendBook";
		}


	@RequestMapping(value = "deleteTabTitleAction", method = RequestMethod.GET)
		public RedirectView deleteTabTitleAction(Locale locale, Model model, Integer tabId, Integer userNum ) {
			DAO.deleteTabTitle(tabId, userNum);
			return new RedirectView("updateTab?tabId="+DAO.getSecondTabIdAction(userNum).getTabId());
		}
	
	@RequestMapping(value = "updateTabTitleAction", method = RequestMethod.GET)
		public RedirectView replyUpdateAction(Locale locale, Model model, Integer tabId, String tabTitle, HttpServletRequest request ) {
			DAO.tabTitleUpdate(tabId, tabTitle);
			HttpSession session = request.getSession();
			Member userInformation = (Member) session.getAttribute("userInformation");
			session.setAttribute("userInformation", userInformation); 
			
			userInformation.setTabList(DAO.readTabListAction(userInformation.getUserId()));
			userInformation.setFriTabList(DAO.readFriTabListAction(userInformation.getUserId()));
			return new RedirectView("board?tabId="+tabId);
		}
	
	@RequestMapping(value = "updatePostTabTitleAction", method = RequestMethod.POST)
		public String updatePostTabTitleAction(Locale locale, Model model, Integer tabId, Integer moveOn, Integer isDelCheck, String id, HttpServletRequest request ) {
		HttpSession session = request.getSession();
		Member userInformation = (Member) session.getAttribute("userInformation");
		session.setAttribute("userInformation", userInformation); 

		DAO.updatePostTabTitle(tabId, moveOn, isDelCheck);
		userInformation.setTabList(DAO.readTabListAction(userInformation.getUserId()));
		userInformation.setFriTabList(DAO.readFriTabListAction(userInformation.getUserId()));	
		
			if(DAO.getCountTab(userInformation.getUserId()) == 0) {
				
				return "firstboard";
					
			}else {
				//System.out.println("너 맞구나 들어와 ");
				Post tabId2 = new Post(); 
				tabId2 = DAO.getSecondTabIdAction(userInformation.getUserId());
				userInformation.setTabList(DAO.readTabListAction(userInformation.getUserId()));
				userInformation.setFriTabList(DAO.readFriTabListAction(userInformation.getUserId()));
				return "redirect:board?tabId="+tabId2.getTabId();
			}
		
		}
	
}
