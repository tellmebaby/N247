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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
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
		
		//System.out.println("받아온 아이디는 : " + id);
		Integer count = DAO.checkLoginMember(id,password);
		String em = "Email이나 비밀번호를 다시 확인하세요. ";
		
		if(count == 0) {
			model.addAttribute("errorMessage",em);
			return "home";
		}
			//로그인 성공시 
			//System.out.println("로그인 체크 결과 성공하셨습니다.");
			HttpSession session = request.getSession();
			//유저번호와 가장최근 탭아이디 및 탭이없을시 텝체크0 를 id를 넣어 리턴한다.
			Member login = new Member();
			login.setLoginSucceedGetUserInfo(DAO.loginSucceedGetUserInfo(id));
			Member userIdTabId = DAO.getUserIdTabId(login.getLoginSucceedGetUserInfo(),id);
			//System.out.println("하하하 급하게 하지말자 처음 받아온거 "+userIdTabId.getUserId());
			if(userIdTabId.getUserId() == null) {
				userIdTabId = DAO.getMemberToId(id);
				//System.out.println("하하하 급하게 하지말자 아래 "+userIdTabId.getUserId() +userIdTabId.getNickName());
			}
			session.setAttribute("login", login); 
			session.setAttribute("userIdTabId", userIdTabId); 

			//사용자의 탭유형이 0 : 아무것도 없으면 탭을 만들 수 있는 페이지로 이동 
			if(userIdTabId.getTabCheck() == 0 ) {
				
				
				//System.out.println("오니 : "+ userIdTabId.getUserId() + userIdTabId.getNickName());
				if(userIdTabId.getUserId() == null) {
					return "home";
				}
				
				userIdTabId = DAO.getMember(userIdTabId.getUserId());
				session.setAttribute("userIdTabId", userIdTabId); 
				model.addAttribute("userIdTabId",userIdTabId);
				
				return "firstboard";
			}else {
				System.out.println("아이디는 뭐냐 " + userIdTabId.getId());
				return "redirect:board?tabId="+userIdTabId.getTabId();
			}
		
	}
	
	
	
	
	
	@RequestMapping(value = "board", method = RequestMethod.GET, produces = "application/json; charset=UTF-8")
	public String board(Locale locale, Model model, HttpServletRequest request, Integer tabId ) {
			
			HttpSession session = request.getSession();
			Member userIdTabId = (Member) session.getAttribute("userIdTabId");
			Member login = (Member) session.getAttribute("login");
			Integer boardCheck = 0;
			if(userIdTabId == null) {
				//System.out.println("탭주인 체크 결과 실패  ");
				String em = "잘못된 접근 입니다. 로그인 해주세요.";
				model.addAttribute("errorMessage",em);
				return "home";	
			}else {
				boardCheck = DAO.boardCheck(login.getLoginSucceedGetUserInfo(), tabId);
				System.out.println("보드로 바로접근 탭번호 : " + tabId +"체크결과는 : " + boardCheck);
			}
			//접근승인 세션에서 받아온 유저번호와 입력된 탭아이디의 유저번호 (탭을 새로만들어서 들어올시 리로딩해야함) 가 맞는지 확인 하거나 탭체크가 0이 아닐때 true. @
//			if(userIdTabId.getUserId() == DAO.getUserNumToTabId(tabId) || userIdTabId.getTabCheck() != 0 ) {
			if(boardCheck != 0 ) {
					userIdTabId.setTabId(tabId);
				//나포함친구들의 정보를 가져온다.@
					System.out.println("getMembertList 사용하기 위해 파라미터 : " + userIdTabId.getUserId());
				//유저의 정보를 세팅해준다.	
					Member userInformation = (DAO.userInformation(userIdTabId));
					System.out.println("제발 탭아이디좀 가져와" + userInformation.getTabId());
//					Post cards = (DAO.cardsSet(userIdTabId));

					
					//System.out.println("카드있는지 봐야돼 home : "+ cards.getCompletePostList().size());
					
					
//					Post thisTab = DAO.setThisTab(tabId, userInformation, cards);
						//tabId,tabAdmCheck,tabSelectCheck,nick,tabTitle,tab_intro,tabProgressBg,tabProgress,dueMessage,maxDay,minDay
					
//					session.setAttribute("thisTab", thisTab); 
					
					session.setAttribute("userInformation", userInformation); 
					model.addAttribute("userInformation", userInformation);
//					model.addAttribute("thisTab",thisTab);
//					model.addAttribute("cards",cards);
					
			// 접근실패	
			}else {
				
				//System.out.println("탭주인 체크 결과 실패  ");
				
				session.invalidate();
				String em = "잘못된 접근 입니다. 로그인 해주세요.";
				model.addAttribute("errorMessage",em);
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
		Member userIdTabId = (Member) session.getAttribute("userIdTabId");
		
		
		System.out.println("오니 : "+ userIdTabId.getUserId() + userIdTabId.getNickName());
		if(userIdTabId.getUserId() != null) {
			return "home";
		}
		
		
		session.setAttribute("userIdTabId", userIdTabId); 
		model.addAttribute("userIdTabId",userIdTabId);
		return "firstboard";
	}
	
	@RequestMapping(value = "searchPostNickAction", method = RequestMethod.GET, produces = "application/json; charset=UTF-8")
	public String searchPostNickAction (Locale locale, Model model, String search, HttpServletRequest request) {
		HttpSession session = request.getSession();
		Member userIdTabId = (Member) session.getAttribute("userIdTabId");
		Member userInformation = (Member) session.getAttribute("userInformation");
		Post thisTab = (Post) session.getAttribute("thisTab");
		
		//System.out.println("검색어 받아왔어 : " + search);
		List<Post> searchResult = DAO.searchPostNick(search, userIdTabId.getUserId());
		
		session.setAttribute("userIdTabId", userIdTabId); 
		
		model.addAttribute("thisTab",thisTab);
		model.addAttribute("searchResult",searchResult);
		model.addAttribute("search",search);
		model.addAttribute("userIdTabId",userIdTabId);
		model.addAttribute("userInformation",userInformation);
		
		return "searchBoard";
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
	public String searchFriendError(Locale locale, Model model, Integer tabId, HttpServletRequest request ) {
		HttpSession session = request.getSession();
		Member userIdTabId = (Member) session.getAttribute("userIdTabId");
		
		session.setAttribute("userIdTabId", userIdTabId); 
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
	
	@RequestMapping(value = "memberAction", method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
	public String memberAction(Locale locale, Model model, String id, String nickName, String password, String passwordConfirm , String role, HttpServletRequest request) {
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
		HttpSession session = request.getSession();
		Member userIdTabId = DAO.getMemberToId(id);
		session.setAttribute("userIdTabId", userIdTabId); 
		model.addAttribute("userIdTabId",userIdTabId);
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
		public RedirectView updatePostAction(Locale locale, Model model, Integer id, String postTitle, String description, @DateTimeFormat(pattern="yyyy-MM-dd") Date dueDay, HttpServletRequest request) {
		 HttpSession session = request.getSession();
		 Member userIdTabId = (Member) session.getAttribute("userIdTabId");
		 DAO.updatePost(id, postTitle, description, userIdTabId.getTabId(), dueDay);
		 session.setAttribute("userIdTabId", userIdTabId); 
			return new RedirectView("board?tabId="+userIdTabId.getTabId());
		}
	 
	 @RequestMapping(value = "updateIsDelPostAction", method = RequestMethod.GET)
		public RedirectView completePostAction(Locale locale, Model model, Integer id, Integer isDel, HttpServletRequest request) {
		 	HttpSession session = request.getSession();
		 	Member userIdTabId = (Member) session.getAttribute("userIdTabId");
		 	DAO.completePost(id,isDel);
			session.setAttribute("userIdTabId", userIdTabId); 
			return new RedirectView("board?tabId="+userIdTabId.getTabId());
		}
	 
	 @RequestMapping(value = "completeTabAction", method = RequestMethod.GET)
		public RedirectView completeTabAction(Locale locale, Model model, Integer isDel, HttpServletRequest request) {
		 	HttpSession session = request.getSession();
		 	Member userIdTabId = (Member) session.getAttribute("userIdTabId");
		 	DAO.completeTab(userIdTabId.getTabId(),isDel);
		 	
			session.setAttribute("userIdTabId", userIdTabId); 
			return new RedirectView("board?tabId="+userIdTabId.getTabId());
		}
	 
	 @RequestMapping(value = "updateTabIntroAction", method = RequestMethod.POST)
		public RedirectView updateTabIntro(Locale locale, Model model, Integer tabId, String tab_intro, HttpServletRequest request ) {
		 	DAO.updateTabIntro(tabId, tab_intro);
		 	HttpSession session = request.getSession();
			Member userIdTabId = (Member) session.getAttribute("userIdTabId");
			
			session.setAttribute("userIdTabId", userIdTabId); 
		
			return new RedirectView("board?tabId="+userIdTabId.getTabId());
		}
	 @RequestMapping(value = "updateTabDueDayAction", method = RequestMethod.POST)
		public RedirectView updateTabDueDayAction(Locale locale, Model model, Integer tabId, @DateTimeFormat(pattern="yyyy-MM-dd") Date tab_dueDay, HttpServletRequest request ) {
		 	DAO.updateTabDueDay(tabId, tab_dueDay);
		 	HttpSession session = request.getSession();
			Member userIdTabId = (Member) session.getAttribute("userIdTabId");
			
			session.setAttribute("userIdTabId", userIdTabId); 
			return new RedirectView("board?tabId="+userIdTabId.getTabId());
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

			return new RedirectView("board?tabId="+ft_tabId);
		}
	 
	@RequestMapping(value = "updateDelFriAction", method = RequestMethod.GET)
		public RedirectView updateDelFriAction(Locale locale, Model model, Integer idN247_f, Integer tabId, Integer ft_userId ) {
	 		if(DAO.getCheckFriId(ft_userId,tabId)==0) {
	 			DAO.updateDelFri(idN247_f);
	 		}else {
	 			DAO.updateDelFriToTab(DAO.getFriAdmIdN247_ft(ft_userId, tabId));
	 			DAO.updateDelFri(idN247_f);
	 		}
			return new RedirectView("board?tabId="+tabId);
		}
	@RequestMapping(value = "updateDelFriIWaitAction", method = RequestMethod.GET)
	public RedirectView updateDelFriIWaitAction(Locale locale, Model model, Integer fUserId, HttpServletRequest request ) {
		HttpSession session = request.getSession();
		Member userIdTabId = (Member) session.getAttribute("userIdTabId");
		
		session.setAttribute("userIdTabId", userIdTabId); 
 			DAO.updateDelFri2(userIdTabId.getUserId(),fUserId);
 	
		return new RedirectView("board?tabId="+userIdTabId.getTabId());
	}
	@RequestMapping(value = "updateDelFriWaitAction", method = RequestMethod.GET)
	public RedirectView updateDelFriWaitAction(Locale locale, Model model, Integer myId, HttpServletRequest request ) {
		HttpSession session = request.getSession();
		Member userIdTabId = (Member) session.getAttribute("userIdTabId");
		
		session.setAttribute("userIdTabId", userIdTabId); 
 			DAO.updateDelFri2(myId,userIdTabId.getUserId());
 	
		return new RedirectView("board?tabId="+userIdTabId.getTabId());
	}
	@RequestMapping(value = "updateFriAdmAction", method = RequestMethod.GET)
	public RedirectView updateFriAdmAction(Locale locale, Model model, Integer myId, Integer tabId, HttpServletRequest request ) {
		HttpSession session = request.getSession();
		Member userIdTabId = (Member) session.getAttribute("userIdTabId");
		
		session.setAttribute("userIdTabId", userIdTabId); 
		
 			DAO.updateFriAdm(userIdTabId.getUserId(),myId);
 	
		return new RedirectView("board?tabId="+userIdTabId.getTabId());
	}
	@RequestMapping(value = "updateUserInfoAction", method = RequestMethod.POST)
	public RedirectView updateUserInfoAction(Locale locale, Model model, String nickName, String mb_introduce, HttpServletRequest request ) {
	HttpSession session = request.getSession();
	Member userIdTabId = (Member) session.getAttribute("userIdTabId");
	
	session.setAttribute("userIdTabId", userIdTabId); 
	
	DAO.updateUserInfo(userIdTabId.getUserId(), nickName, mb_introduce);
	
		return new RedirectView("board?tabId="+userIdTabId.getTabId());
	}
	
	@RequestMapping(value = "updateReplyAction", method = RequestMethod.POST)
		public RedirectView updateReplyAction(Locale locale, Model model, Integer idN247_re, String n247_reDes, HttpServletRequest request ) {
		HttpSession session = request.getSession();
		Member userIdTabId = (Member) session.getAttribute("userIdTabId");
		
		session.setAttribute("userIdTabId", userIdTabId); 
		
		DAO.updateReply(idN247_re, n247_reDes);
			return new RedirectView("board?tabId="+userIdTabId.getTabId());
		}
	@RequestMapping(value = "deleteReplyAction", method = RequestMethod.GET)
	public RedirectView deleteReplyAction(Locale locale, Model model, Integer idN247_re, HttpServletRequest request ) {
	HttpSession session = request.getSession();
	Member userIdTabId = (Member) session.getAttribute("userIdTabId");
	
	session.setAttribute("userIdTabId", userIdTabId); 
	//System.out.println("가져오긴 한거야? 너무 삭제 하고 싶다. : " + idN247_re);
	DAO.deleteReply(idN247_re);
		return new RedirectView("board?tabId="+userIdTabId.getTabId());
	}	
	@RequestMapping(value = "updateDelFriToTabAction", method = RequestMethod.GET)
		public RedirectView updateDelFriToTabAction(Locale locale, Model model, Integer idN247_ft, HttpServletRequest request ) {
		HttpSession session = request.getSession();
		Member userIdTabId = (Member) session.getAttribute("userIdTabId");	
		DAO.updateDelFriToTab(idN247_ft);
		session.setAttribute("userIdTabId", userIdTabId); 
			return new RedirectView("board?tabId="+userIdTabId.getTabId());
		}

	@RequestMapping(value = "deletePostAction", method = RequestMethod.GET)
		public RedirectView deletePostAction(Locale locale, Model model, Integer id, Integer tabId ) {
			DAO.deletePost(id, tabId);
			return new RedirectView("/mvc/board?tabId="+tabId);
		}
	
	@RequestMapping(value = "searchFriendAction", method = RequestMethod.GET)
		public String searchFriendAction (Locale locale, Model model, String search, Integer userNum, Integer tabId, HttpServletRequest request ) {
			HttpSession session = request.getSession();
			Member userIdTabId = (Member) session.getAttribute("userIdTabId");
		
			session.setAttribute("userIdTabId", userIdTabId); 
			int count = DAO.searchFriend(search);
			
			String errormessage = "";
				if(count == 0) {
					errormessage = "회원을 찾을 수 없습니다. 이메일을 확인해주세요. ";
				    model.addAttribute("em",errormessage);
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
					model.addAttribute("selectTab", tabId);
					return "searchFriendError";
				}
		}
	
	//프로젝트를 새로 생성합니다. 
	@RequestMapping(value = "createTabAction", method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
		public String createTabAction(Locale locale, Model model, String tabTitle, Integer userNum, String tab_intro, @DateTimeFormat(pattern="yyyy-MM-dd") Date tab_dueDay, String id, HttpServletRequest request ) {
			
			HttpSession session = request.getSession();
			Member userIdTabId = (Member) session.getAttribute("userIdTabId");
			Member login = new Member();
			
			
			DAO.createTab(tabTitle, userNum,tab_intro,tab_dueDay);
//			userIdTabId.setTabId(DAO.getTabIdAction(tabTitle).get(0).getTabId());
			login.setLoginSucceedGetUserInfo(DAO.loginSucceedGetUserInfo(id));
			userIdTabId = DAO.getUserIdTabId(login.getLoginSucceedGetUserInfo(),id);
			System.out.println("로그인 정보에 뭐들어갔냐 :" + login.getLoginSucceedGetUserInfo().size() + "아이디는?:" + id);
			System.out.println("뭘주길래 실패란거야 : "+userIdTabId.getTabId());
			
			session.setAttribute("login", login); 
			session.setAttribute("userIdTabId", userIdTabId); 
			model.addAttribute("userIdTabId",userIdTabId);
			return "redirect:board?tabId="+userIdTabId.getTabId();
		}

	@RequestMapping(value = "createReplyAction", method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
		public RedirectView createReplyAction(Locale locale, Model model, String n247_reDes, Integer n247_reUsId , Integer n247_rePoId , Integer tabId, HttpServletRequest request) {
		DAO.createReply(n247_reDes, n247_reUsId, n247_rePoId, tabId );
		
		HttpSession session = request.getSession();
		Member userInformation = DAO.getMember(n247_reUsId);
		session.setAttribute("userInformation", userInformation); 

		
			return new RedirectView("board?tabId="+tabId);
		}
	
	@RequestMapping(value = "friendSubscriptionAction", method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
		public String friendSubscriptionAction(Locale locale, Model model, Integer fUserId , HttpServletRequest request) {
			HttpSession session = request.getSession();
			Member userIdTabId = (Member) session.getAttribute("userIdTabId");
			DAO.friendSubscription(fUserId, userIdTabId.getUserId());
			System.out.println("userIdTabId.getId()" + userIdTabId.getId());
			List<Member> loginSucceedGetUserInfo = DAO.loginSucceedGetUserInfo(userIdTabId.getId());
			userIdTabId = DAO.getUserIdTabId(loginSucceedGetUserInfo,userIdTabId.getId());
			
			System.out.println(userIdTabId.getTabId()+"번 탭아이디와 유저아이디 : "+userIdTabId.getUserId());
			
			session.setAttribute("userIdTabId", userIdTabId); 
			return "redirect:board?tabId="+userIdTabId.getTabId();
		}


	@RequestMapping(value = "deleteTabAction", method = RequestMethod.GET)
		public RedirectView deleteTabTitleAction(Locale locale, Model model, Integer tabId, HttpServletRequest request ) {
			DAO.deleteTab(tabId);
			HttpSession session = request.getSession();
			Member userIdTabId = (Member) session.getAttribute("userIdTabId");
			List<Member> loginSucceedGetUserInfo = DAO.loginSucceedGetUserInfo(userIdTabId.getId());
			userIdTabId = DAO.getUserIdTabId(loginSucceedGetUserInfo,userIdTabId.getId());

			session.setAttribute("userIdTabId", userIdTabId); 
			return new RedirectView("board?tabId="+userIdTabId.getTabId());
		}
	
	
	@RequestMapping(value = "updatePostTabTitleAction", method = RequestMethod.POST)
		public String updatePostTabTitleAction(Locale locale, Model model, Integer tabId, Integer moveOn, Integer isDelCheck, String id, HttpServletRequest request ) {
		HttpSession session = request.getSession();
		Member userIdTabId = (Member) session.getAttribute("userIdTabId");

		DAO.updatePostTabTitle(tabId, moveOn, isDelCheck);
		
		
			if(DAO.getCountTab(userIdTabId.getUserId()) == 0) {
				
				return "firstboard";
					
			}else {
				//System.out.println("너 맞구나 들어와 ");
				Post tabId2 = new Post(); 
				tabId2 = DAO.getSecondTabIdAction(userIdTabId.getUserId());

				return "redirect:board?tabId="+tabId2.getTabId();
			}
		
		}
	
	//Ajax
	
	@RequestMapping(value = "idCheck", method = {RequestMethod.POST})
	public @ResponseBody Integer idCheck(@RequestParam("id") String id){
		
		
		String resource = "org/first/mvc/mybatis_config.xml";
		InputStream inputStream;
		Integer result = 0;
		System.out.println("id = "+id);
		System.out.println("resurt = "+result);
		
		
		try {
			
			inputStream = Resources.getResourceAsStream(resource);
			SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
			SqlSession session = sqlSessionFactory.openSession();

			Integer count = session.selectOne("org.first.mvc.BaseMapper.countId", id);
			
			if(count==0) {
				result = count ;
			}else {
				result = 1 ;
			}
		
			System.out.println("resurt = "+result);
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("id check error in sql");
			
		}
		
	 return result;
	}
	
	@RequestMapping(value = "nickCheck", method = {RequestMethod.POST})
	public @ResponseBody Integer nickCheck(@RequestParam("nickName") String nickName){
		
		
		String resource = "org/first/mvc/mybatis_config.xml";
		InputStream inputStream;
		Integer result = 0;
		System.out.println("nick = "+nickName);
		System.out.println("resurt = "+result);
		
		
		try {
			
			inputStream = Resources.getResourceAsStream(resource);
			SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
			SqlSession session = sqlSessionFactory.openSession();

			Integer count = session.selectOne("org.first.mvc.BaseMapper.countNick", nickName);
			
			if(count==0) {
				result = count ;
			}else {
				result = 1 ;
			}
		
			System.out.println("resurt = "+result);
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("nick check error in sql");
			
		}
		
	 return result;
	}
	

	
	@RequestMapping(value = "passwordCheck", method = {RequestMethod.POST})
	public @ResponseBody Integer passwordCheck(@RequestParam("password") String password){

		Integer result = 0;
		System.out.println("nick = "+password);
		System.out.println("resurt = "+result);

			if(Pattern.matches("^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{8,}$", password)) {
				result = 0 ;
			}else {
				result = 1 ;
			}
		
			System.out.println("resurt = "+result);

	 return result;
	}
	
//	@RequestMapping(value = "getCardsListAjax", method = {RequestMethod.POST})
//	public @ResponseBody List<Post> getCardsListAjax(Model model,@RequestParam("tabId") Integer tabId, Integer userId){
//		Member userIdTabId = new Member();
//		userIdTabId.setTabId(tabId);
//		userIdTabId.setUserId(userId);
//		
//		List<Post> result = DAO.getPostList(userIdTabId);
//		
//		
//		System.out.println("ajax 잘 작동하고 있어요 getPostListAjax 탭번호 :" + userIdTabId.getTabId() +" 유저번호 :"+userIdTabId.getUserId());
//		
//		//여기다 작성해서 넘겨보자
//		
//		 List<Post> p1 = (result);
//		
//		 
//		 //postList부분
//		 List<Post> postAllList = new ArrayList<Post>();
//		 Integer postCheck = 0;
//		 for(int i=0 ; i<p1.size() ; i++) {
//			 Integer p4 = p1.get(i).getId();
//			 List<Post> p2 = new ArrayList<Post>();
//			 if(postCheck.equals(p4)) {
//			 }else if(p1.get(i).getUserId() == p1.get(i).getUserNum()) {
//				 postCheck = p1.get(i).getId();
//				 p2.add(p1.get(i)); 
//			 }
//			 postAllList.addAll(p2);
//		 }
//		System.out.println("ajax 잘 작동하고 있어요 getPostListAjax postAllList.size() :" + postAllList.size());
//
//		 result = (DAO.postSet(postAllList,userIdTabId));	
//		 for(int i=0 ; i<result.size(); i++) {
//			 System.out.println("getCheck : "+result.get(i).getCheck()+"  getPostAdmCheck :"+result.get(i).getPostAdmCheck());
//		 }
//		
//	 return result;
//	}
	
	@RequestMapping(value = "getAllListAjax", method = {RequestMethod.POST})
	public @ResponseBody List<Post> getAllListAjax(Model model,@RequestParam("tabId") Integer tabId, Integer userId){
		System.out.println("getAllListAjax 돌아가는 중 " + tabId +"이것은 탭아이디 " + userId + "이것은 유저아이디");
		Member userIdTabId = new Member();
		userIdTabId.setTabId(tabId);
		userIdTabId.setUserId(userId);
		
		List<Post> result = DAO.getPostList(userIdTabId);
		for(int i=0 ; i<result.size(); i++) {
			System.out.println("getPostList 돌아가는중 : " + i);
			if(result.get(i).getTabTitle() != null) {
				System.out.println("여기 따끈따끈한 탭타이틀이 있어요 : " + result.get(i).getTabTitle());
			}
		}
		result = (DAO.postSet(result,userIdTabId));	
		
	 return result;
	}
	
	@RequestMapping(value = "ajax_createReplyAction2", method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
	public @ResponseBody List<Post> ajax_createReplyAction2(Model model,@RequestParam("n247_reDes") String n247_reDes, Integer n247_reUsId , Integer n247_rePoId , Integer tabId) {
		DAO.createReply(n247_reDes, n247_reUsId, n247_rePoId, tabId );
		Member userIdTabId = new Member();
		userIdTabId.setTabId(tabId);
		userIdTabId.setUserId(n247_reUsId);
		
		List<Post> result = DAO.getPostList(userIdTabId);
		
		result = (DAO.postSet(result,userIdTabId));	
		
		return result;
	}
	
	
	@RequestMapping(value = "ajax_createReplyAction", method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
	public @ResponseBody List<Post> ajax_createReplyAction(Model model,@RequestParam("n247_reDes") String n247_reDes, Integer n247_reUsId , Integer n247_rePoId, Integer tabId) {
		System.out.println("ajax_createReplyAction 작동중 ");
		DAO.createReply(n247_reDes, n247_reUsId, n247_rePoId, tabId );
		
		List<Post> result = DAO.getReplyListCard(n247_rePoId);

		return result;
	}
	
	@RequestMapping(value = "getOneCardAjax", method = {RequestMethod.POST})
	public @ResponseBody List<Post> getOneCardAjax(Model model,@RequestParam("id") Integer id){
		System.out.println("getOneCardAjax 작동중 ");
		List<Post> result = DAO.getReplyListCard(id);
		System.out.println(id+"받아서 보낸다.");
	 return result;
	}
	
	// 탭이동시 현재탭에 공유된 친구와 친구맺기 완료된 친구목록과 정보
	
	@RequestMapping(value = "getProjectAdmFriendsAjax", method = {RequestMethod.POST})
	public @ResponseBody List<Member> getProjectAdmFriendsAjax(Model model,@RequestParam("tabId") Integer tabId){
		System.out.println("getUserInfoAjax 작동중 ");
		List<Member> result = DAO.getFriTabList(tabId);
	if(result != null) {
		for (int i=0 ; i<result.size(); i++) {
			System.out.println("받아온 친구는 " + result.size() +"명 이고 이름은 : " + result.get(i).getNickName());
		}
	}
		
	 return result;
	}
	
	@RequestMapping(value = "getAdmFriendsAjax", method = {RequestMethod.POST})
	public @ResponseBody List<Member> getAdmFriendsAjax(Model model,@RequestParam("userId") Integer userId){
		System.out.println("getUserInfoAjax 작동중 ");
		List<Member> result = DAO.getFriendsAdmList(userId);

	 return result;
	}
	
	@RequestMapping(value = "insertProjectAdm", method = {RequestMethod.POST})
	public @ResponseBody void insertProjectAdm(Model model,@RequestParam("userId") Integer userId, Integer tabId){
		System.out.println("getUserInfoAjax 작동중 ");
		DAO.createFriTabAdd(userId, tabId);
	}
	
	@RequestMapping(value = "isDelProjectAdm", method = {RequestMethod.POST})
	public @ResponseBody void isDelProjectAdm(Model model,@RequestParam("idN247_ft") Integer idN247_ft){
		System.out.println("isDelProjectAdm 작동중 ");
		DAO.isDelProjectAdm(idN247_ft);
	}
	
	
	@RequestMapping(value = "updateCardTitleAjax", method = {RequestMethod.POST})
	public @ResponseBody void updateCardTitleAjax(Model model,@RequestParam("postTitle") String postTitle, Integer id){
		System.out.println("getUserInfoAjax 작동중 ");
		DAO.updateCardTitle(postTitle, id);
	}
	
	@RequestMapping(value = "updateCardDescriptionAjax", method = {RequestMethod.POST})
	public @ResponseBody void updateCardDescriptionAjax(Model model,@RequestParam("description") String description, Integer id){
		System.out.println("updateCardDescriptionAjax 작동중 ");
		DAO.updateCardDescription(description, id);
	}
	
	@RequestMapping(value = "deleteUpFileAjax", method = {RequestMethod.POST})
	public @ResponseBody void deleteUpFileAjax(Model model,@RequestParam("idN247_up") Integer idN247_up ){
		System.out.println("deleteUpFileAjax 작동중 ");
		DAO.deleteUpFile(idN247_up);
	}
	
	@RequestMapping(value = "updateReplyDesAjax", method = {RequestMethod.POST})
	public @ResponseBody void updateReplyDesAjax(Model model,@RequestParam("n247_reDes") String n247_reDes, Integer idN247_re){
		System.out.println("updateReplyDesAjax 작동중 ");
		DAO.updateReplyDes(n247_reDes, idN247_re);
	}
	
	@RequestMapping(value = "updateReplyDesToDesAjax", method = {RequestMethod.POST})
	public @ResponseBody void updateReplyDesToDesAjax(Model model,@RequestParam("n247_reDes") String n247_reDes, String insertDes){
		System.out.println("updateReplyDesAjax 작동중 ");
		DAO.updateReplyDesToDes(n247_reDes, insertDes);
	}
	
	@RequestMapping(value = "updateProjectTitleAjax", method = {RequestMethod.POST})
	public @ResponseBody void updateProjectTitleAjax(Model model,@RequestParam("tabTitle") String tabTitle, Integer tabId){
		System.out.println("updateProjectTitleAjax 작동중 ");
		DAO.updateProjectTitle(tabTitle, tabId);
	}
	
	@RequestMapping(value = "updateProjectIntroAjax", method = {RequestMethod.POST})
	public @ResponseBody void updateProjectIntroAjax(Model model,@RequestParam("tab_intro") String tab_intro, Integer tabId){
		System.out.println("updateProjectIntroAjax 작동중 ");
		DAO.updateProjectIntro(tab_intro, tabId);
	}
	
	@RequestMapping(value = "deleteReplyAjax", method = {RequestMethod.POST})
	public @ResponseBody void deleteReplyAjax(Model model,@RequestParam("idN247_re") Integer idN247_re){
		System.out.println("deleteReplyAjax 작동중 ");
		DAO.deleteReply(idN247_re);
	}
	
	@RequestMapping(value = "deleteChangeReplyAjax", method = {RequestMethod.POST})
	public @ResponseBody void deleteChangeReplyAjax(Model model,@RequestParam("n247_reDes") String n247_reDes){
		System.out.println("deleteChangeReplyAjax 작동중 ");
		DAO.deleteChangeReply(n247_reDes);
	}
}
