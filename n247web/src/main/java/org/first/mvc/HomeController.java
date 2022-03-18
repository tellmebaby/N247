package org.first.mvc;



import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
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
			//로그인 성공시 유저의 정보를 가져와서 로그인에 넣어둔다 DB
			//id,nickName,mb_introduce,userImg,modified,tabId,ft_tabId,ft_userId,ft_isDel,fUserId,myId,f_isDel,isDel,userId
			login.setLoginSucceedGetUserInfo(DAO.loginSucceedGetUserInfo(id));
			//사용자유형파악
			Member userIdTabId = DAO.getUserIdTabId(login.getLoginSucceedGetUserInfo(),id);
			System.out.println("하하하 급하게 하지말자 처음 받아온거 "+userIdTabId.getUserId());
			if(userIdTabId.getUserId() == null) {
				userIdTabId = DAO.getMemberToId(id);
				System.out.println("하하하 급하게 하지말자 아래 "+userIdTabId.getUserId() +userIdTabId.getNickName());
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
				//System.out.println("아이디는 뭐냐 " + userIdTabId.getId());
				return "redirect:board?tabId="+userIdTabId.getTabId();
			}
		
	}
	
	
//	@RequestMapping(value = "board", method = RequestMethod.GET, produces = "application/json; charset=UTF-8")
//	public String board(Locale locale, Model model, HttpServletRequest request, Integer tabId ) {
//			
//			HttpSession session = request.getSession();
//			Member userIdTabId = (Member) session.getAttribute("userIdTabId");
//			Member login = (Member) session.getAttribute("login");
//			Integer boardCheck = 0;
//			if(userIdTabId == null) {
//				String em = "잘못된 접근 입니다. 로그인 해주세요.1";
//				model.addAttribute("errorMessage",em);
//				return "home";	
//			}else {
//				boardCheck = DAO.boardCheck(login.getLoginSucceedGetUserInfo(), tabId);
//				//System.out.println("보드로 바로접근 탭번호 : " + tabId +"체크결과는 : " + boardCheck);
//			}
//
//			if(boardCheck != 0 ) {
//					userIdTabId.setTabId(tabId);
//					session.setAttribute("userIdTabId", userIdTabId); 
//					model.addAttribute("userIdTabId", userIdTabId);
//			// 접근실패	
//			}else {
//				session.invalidate();
//				String em = "잘못된 접근 입니다. 로그인 해주세요.2";
//				model.addAttribute("errorMessage",em);
//				return "home";	
//			}
//			
//		return "board";
//	}
	
	@RequestMapping(value = "top", method = RequestMethod.GET, produces = "application/json; charset=UTF-8")
	public String top(Locale locale, Model model) {
		return "top";
	}
	@RequestMapping(value = "firstboard", method = RequestMethod.GET, produces = "application/json; charset=UTF-8")
	public String firstboard(Locale locale, Model model, HttpServletRequest request ) {
		
		HttpSession session = request.getSession();
		Member userIdTabId = (Member) session.getAttribute("userIdTabId");
		
		
		//System.out.println("오니 : "+ userIdTabId.getUserId() + userIdTabId.getNickName());
		if(userIdTabId.getUserId() == null) {
			//System.out.println("안와 홈으로 가 : "+ userIdTabId.getUserId() + userIdTabId.getNickName());
			return "home";
		}
		
		
		session.setAttribute("userIdTabId", userIdTabId); 
		model.addAttribute("userIdTabId",userIdTabId);
		return "firstboard";
	}
	
	@RequestMapping(value = "searchPostNickAction", method = RequestMethod.GET, produces = "application/json; charset=UTF-8")
	public String searchPostNickAction (Locale locale, Model model, String search, HttpServletRequest request) {
		HttpSession session = request.getSession();
		Member userInformation = (Member) session.getAttribute("userInformation");

		List<Post> searchResult = DAO.searchPostNick(search, userInformation.getUserId());
		
		session.setAttribute("userInformation", userInformation); 

		model.addAttribute("searchResult",searchResult);
		model.addAttribute("search",search);
		model.addAttribute("userInformation",userInformation);
		
		return "searchBoard";
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
	 @RequestMapping(value = "updateTabDueDayAction", method = RequestMethod.POST)
		public RedirectView updateTabDueDayAction(Locale locale, Model model, Integer tabId, @DateTimeFormat(pattern="yyyy-MM-dd") Date tab_dueDay, HttpServletRequest request ) {
		 	DAO.updateTabDueDay(tabId, tab_dueDay);
		 	HttpSession session = request.getSession();
		 	Member userInformation = (Member) session.getAttribute("userInformation");
			
		 	session.setAttribute("userInformation", userInformation); 
			return new RedirectView("board");
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

	 @RequestMapping(value = "updatePostAction2", method = RequestMethod.POST)
		public RedirectView updatePostAction2(Locale locale, Model model, Integer id, String postTitle, String description, Integer tabId, @DateTimeFormat(pattern="yyyy-MM-dd") Date dueDay ,Integer userNum ) {
		 	DAO.updatePost(id, postTitle, description, tabId, dueDay);
			return new RedirectView("board?tabId="+tabId);
		}
	 
	@RequestMapping(value = "createFriTabAdd", method = RequestMethod.GET)
		public RedirectView createFriTabAdd(Locale locale, Model model, Integer ft_userId, Integer ft_tabId, HttpServletRequest request ) {
	    	DAO.createFriTabAdd(ft_userId, ft_tabId);
	    	HttpSession session = request.getSession();
//			Member userInformation = (Member) session.getAttribute("userInformation");
//			session.setAttribute("userInformation", userInformation); 

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
		public String createTabAction(Locale locale, Model model, String tabTitle, String tab_intro, @DateTimeFormat(pattern="yyyy-MM-dd") Date tab_dueDay, HttpServletRequest request ) {
			
			HttpSession session = request.getSession();
			Member userInformation = (Member) session.getAttribute("userInformation");
			
			DAO.createTab(tabTitle, userInformation.getUserId(),tab_intro,tab_dueDay);

			return "redirect:board";
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
			//System.out.println("userIdTabId.getId()" + userIdTabId.getId());
			List<Member> loginSucceedGetUserInfo = DAO.loginSucceedGetUserInfo(userIdTabId.getId());
			userIdTabId = DAO.getUserIdTabId(loginSucceedGetUserInfo,userIdTabId.getId());
			
			//System.out.println(userIdTabId.getTabId()+"번 탭아이디와 유저아이디 : "+userIdTabId.getUserId());
			
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
		//System.out.println("id = "+id);
		//System.out.println("resurt = "+result);
		
		
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
		
			//System.out.println("resurt = "+result);
		} catch (IOException e) {
			e.printStackTrace();
			//System.out.println("id check error in sql");
			
		}
		
	 return result;
	}
	
	@RequestMapping(value = "nickCheck", method = {RequestMethod.POST})
	public @ResponseBody Integer nickCheck(@RequestParam("nickName") String nickName){
		
		
		String resource = "org/first/mvc/mybatis_config.xml";
		InputStream inputStream;
		Integer result = 0;
		//System.out.println("nick = "+nickName);
		//System.out.println("resurt = "+result);
		
		
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
		
			//System.out.println("resurt = "+result);
		} catch (IOException e) {
			e.printStackTrace();
			//System.out.println("nick check error in sql");
			
		}
		
	 return result;
	}
	

	
	@RequestMapping(value = "passwordCheck", method = {RequestMethod.POST})
	public @ResponseBody Integer passwordCheck(@RequestParam("password") String password){

		Integer result = 0;
		//System.out.println("nick = "+password);
		//System.out.println("resurt = "+result);

			if(Pattern.matches("^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{8,}$", password)) {
				result = 0 ;
			}else {
				result = 1 ;
			}
		
			//System.out.println("resurt = "+result);

	 return result;
	}
	
	
	@RequestMapping(value = "getAllListAjax", method = {RequestMethod.POST})
	public @ResponseBody List<Post> getAllListAjax(Model model,@RequestParam("tabId") Integer tabId, Integer userId){
		//System.out.println("getAllListAjax 돌아가는 중 " + tabId +"이것은 탭아이디 " + userId + "이것은 유저아이디");
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
		
		for( int i=0 ; i<result.size(); i++) {
			System.out.println("탭의 맥스 데이는 : " + result.get(i).getMaxDay());
		}
		return result;
	}
	
	@RequestMapping(value = "ajax_createReplyAction", method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
	public @ResponseBody List<Post> ajax_createReplyAction(Model model,@RequestParam("n247_reDes") String n247_reDes, Integer n247_reUsId , Integer n247_rePoId, Integer tabId) {
		//System.out.println("ajax_createReplyAction 작동중 ");
		DAO.createReply(n247_reDes, n247_reUsId, n247_rePoId, tabId );
		
		List<Post> result = DAO.getReplyListCard(n247_rePoId);
		

		return result;
	}
	
	
	@RequestMapping(value = "getOneCardAjax", method = {RequestMethod.POST})
	public @ResponseBody List<Post> getOneCardAjax(Model model,@RequestParam("id") Integer id){
		//System.out.println("getOneCardAjax 작동중 ");
		List<Post> result = DAO.getReplyListCard(id);
		//System.out.println(id+"받아서 보낸다.");
	 return result;
	}
	
	// 탭이동시 현재탭에 공유된 친구와 친구맺기 완료된 친구목록과 정보
	
	@RequestMapping(value = "getProjectAdmFriendsAjax", method = {RequestMethod.POST})
	public @ResponseBody List<Member> getProjectAdmFriendsAjax(Model model,@RequestParam("tabId") Integer tabId){
		//System.out.println("getUserInfoAjax 작동중 ");
		List<Member> result = DAO.getFriTabList(tabId);
	if(result != null) {
		for (int i=0 ; i<result.size(); i++) {
			//System.out.println("받아온 친구는 " + result.size() +"명 이고 이름은 : " + result.get(i).getNickName());
		}
	}
		
	 return result;
	}
	
	
	
	
	@RequestMapping(value = "getProjectListAjax", method = {RequestMethod.POST})
	public @ResponseBody List<Post> getProjectListAjax(Model model,@RequestParam("userId") Integer userId){
		//System.out.println("getProjectListAjax 작동중 ");
		List<Post> result = DAO.getProjectList(userId);

	 return result;
	}
	
	@RequestMapping(value = "getFriendsListAjax", method = {RequestMethod.POST})
	public @ResponseBody List<Member> getFriendsListAjax(Model model,@RequestParam("userId") Integer userId){
		//System.out.println("getFriendsListAjax 작동중 ");
		List<Member> result = DAO.getMemberList(userId);

	 return result;
	}
	
	@RequestMapping(value = "insertProjectAdm", method = {RequestMethod.POST})
	public @ResponseBody void insertProjectAdm(Model model,@RequestParam("userId") Integer userId, Integer tabId){
		//System.out.println("insertProjectAdm 작동중 ");
		DAO.createFriTabAdd(userId, tabId);
	}
	
	@RequestMapping(value = "isDelProjectAdm", method = {RequestMethod.POST})
	public @ResponseBody void isDelProjectAdm(Model model,@RequestParam("idN247_ft") Integer idN247_ft){
		//System.out.println("isDelProjectAdm 작동중 ");
		DAO.isDelProjectAdm(idN247_ft);
	}
	
	
	@RequestMapping(value = "updateCardTitleAjax", method = {RequestMethod.POST})
	public @ResponseBody void updateCardTitleAjax(Model model,@RequestParam("postTitle") String postTitle, Integer id){
		//System.out.println("getUserInfoAjax 작동중 ");
		DAO.updateCardTitle(postTitle, id);
	}
	
	@RequestMapping(value = "updateCardDescriptionAjax", method = {RequestMethod.POST})
	public @ResponseBody void updateCardDescriptionAjax(Model model,@RequestParam("description") String description, Integer id){
		//System.out.println("updateCardDescriptionAjax 작동중 ");
		DAO.updateCardDescription(description, id);
	}
	
	@RequestMapping(value = "deleteUpFileAjax", method = {RequestMethod.POST})
	public @ResponseBody void deleteUpFileAjax(Model model,@RequestParam("idN247_up") Integer idN247_up ){
		//System.out.println("deleteUpFileAjax 작동중 ");
		DAO.deleteUpFile(idN247_up);
	}
	
	@RequestMapping(value = "Legacy_updateReplyDesAjax", method = {RequestMethod.POST})
	public @ResponseBody void Legacy_updateReplyDesAjax(Model model,@RequestParam("n247_reDes") String n247_reDes, Integer idN247_re){
		//System.out.println("Legacy_updateReplyDesAjax 작동중 ");
		DAO.updateReplyDes(n247_reDes, idN247_re);
	}
	
	@RequestMapping(value = "updateReplyDesAjax", method = {RequestMethod.POST})
	public @ResponseBody List<Post> updateReplyDesAjax(Model model,@RequestParam("n247_reDes") String n247_reDes, Integer idN247_re, Integer n247_rePoId){
		//System.out.println("updateReplyDesAjax 작동중 ");
		DAO.updateReplyDes(n247_reDes, idN247_re);
		
		List<Post> result = DAO.getReplyListCard(n247_rePoId);
		
		return result;
	}
	
	@RequestMapping(value = "updateReplyDesToDesAjax", method = {RequestMethod.POST})
	public @ResponseBody void updateReplyDesToDesAjax(Model model,@RequestParam("n247_reDes") String n247_reDes, String insertDes){
		//System.out.println("updateReplyDesAjax 작동중 ");
		DAO.updateReplyDesToDes(n247_reDes, insertDes);
	}
	
	@RequestMapping(value = "updateProjectTitleAjax", method = {RequestMethod.POST})
	public @ResponseBody void updateProjectTitleAjax(Model model,@RequestParam("tabTitle") String tabTitle, Integer tabId){
		//System.out.println("updateProjectTitleAjax 작동중 ");
		DAO.updateProjectTitle(tabTitle, tabId);
	}
	
	@RequestMapping(value = "updateProjectIntroAjax", method = {RequestMethod.POST})
	public @ResponseBody void updateProjectIntroAjax(Model model,@RequestParam("tab_intro") String tab_intro, Integer tabId){
		//System.out.println("updateProjectIntroAjax 작동중 ");
		DAO.updateProjectIntro(tab_intro, tabId);
	}
	
	@RequestMapping(value = "deleteReplyAjax", method = {RequestMethod.POST})
	public @ResponseBody void deleteReplyAjax(Model model,@RequestParam("idN247_re") Integer idN247_re){
		//System.out.println("deleteReplyAjax 작동중 ");
		DAO.deleteReply(idN247_re);
	}
	
	@RequestMapping(value = "deleteChangeReplyAjax", method = {RequestMethod.POST})
	public @ResponseBody void deleteChangeReplyAjax(Model model,@RequestParam("n247_reDes") String n247_reDes){
		//System.out.println("deleteChangeReplyAjax 작동중 ");
		DAO.deleteChangeReply(n247_reDes);
	}
	
	@RequestMapping(value = "updateCompCardAction", method = {RequestMethod.POST})
	public @ResponseBody void updateCompCardAction(Model model,@RequestParam("id") Integer id, Integer tabId){
		//System.out.println("deleteChangeReplyAjax 작동중 ");
		DAO.updateIsDelCard(id,3);
		DAO.updateProjectLastUpdate(tabId);
	}
	
	@RequestMapping(value = "updateUnCompCardAction", method = {RequestMethod.POST})
	public @ResponseBody void updateUnCompCardAction(Model model,@RequestParam("id") Integer id, Integer tabId){
		//System.out.println("deleteChangeReplyAjax 작동중 ");
		DAO.updateIsDelCard(id,0);
		DAO.updateProjectLastUpdate(tabId);
	}
	
	@RequestMapping(value = "updateUnCompProjectAction", method = {RequestMethod.POST})
	public @ResponseBody void updateUnCompProjectAction(Model model,@RequestParam("tabId") Integer tabId){
		//System.out.println("deleteChangeReplyAjax 작동중 ");
		DAO.completeTab(tabId,0);
	}
	
	@RequestMapping(value = "updateCompProjectAction", method = {RequestMethod.POST})
	public @ResponseBody void updateCompProjectAction(Model model,@RequestParam("tabId") Integer tabId){
		//System.out.println("deleteChangeReplyAjax 작동중 ");
		DAO.completeTab(tabId,3);
	}
	
	@RequestMapping(value = "updateDeleteCardAction", method = {RequestMethod.POST})
	public @ResponseBody void updateDeleteCardAction(Model model,@RequestParam("id") Integer id){
		//System.out.println("updateIsdelCardAction 작동중 ");
		DAO.updateIsDelCard(id,1);
	}
	
	@RequestMapping(value = "updateSelectProjectAction", method = {RequestMethod.POST})
	public @ResponseBody void updateSelectProjectAction(Model model,@RequestParam("tabId") Integer tabId, Integer moveOn, Integer isDelCheck){
		System.out.println("updateSelectProjectAction 작동중 " + tabId +":"+ moveOn +":"+ isDelCheck);
		DAO.updateDeleteProject(tabId, moveOn, isDelCheck);	
	}
	
	@RequestMapping(value = "updateDeleteProjectAction", method = {RequestMethod.POST})
	public @ResponseBody void updateDeleteProjectAction(Model model,@RequestParam("tabId") Integer tabId){
		System.out.println("updateDeleteProjectAction 작동중 " + tabId );
		DAO.deleteTab(tabId);	
	}
	
	@RequestMapping(value = "updateMemberIntroduceAction", method = {RequestMethod.POST})
	public @ResponseBody void updateMemberIntroduceAction(Model model,@RequestParam("mb_introduce") String mb_introduce, HttpServletRequest request){
		//System.out.println("updateMemberIntroduceAction 작동중 " );
		HttpSession session = request.getSession();
		Member userInformation = (Member) session.getAttribute("userInformation");
		
		System.out.println("이것도 안되는가?"+ userInformation.getUserId());
		
		DAO.updateMemberIntroduce(mb_introduce, userInformation.getUserId());	
	}
	
	
	//카카오 로그인시 처음인지 기존 회원인지 분류 
	@RequestMapping(value = "loginKakaoAction", method = {RequestMethod.POST})
	public @ResponseBody Integer loginKakaoAction(Model model,@RequestParam("id") String id, String nickName, String userImg, String token, HttpServletRequest request){
		System.out.println("loginKakaoAction 작동중 " + id );
		//세션에 세팅하기
		HttpSession session = request.getSession();
		Member userInformation = new Member();
		userInformation.setId(id);
		userInformation.setNickName(nickName);
		userInformation.setUserImg(userImg);
		
		session.setAttribute("userInformation", userInformation); 
		
		Integer result = 0;
		// 최초 로그인이든 아니든 받아와 토큰으로
		// 아이디, 사진url, 이름
		
		// 토큰 유효성 검토
		
		BufferedReader in = null; 
		
		try { URL obj = new URL("https://kapi.kakao.com/v1/user/access_token_info"); 
		// 호출할 url 
		
		HttpURLConnection con = (HttpURLConnection)obj.openConnection(); 
		//curl -v -X GET "https://kapi.kakao.com/v1/user/access_token_info" \ -H "Authorization: Bearer ${ACCESS_TOKEN}" 하는 방법 
   	 	
		//토큰 받아와서 여기 넣어야돼 
		con.setRequestProperty("Authorization", "Bearer "+token);
		con.setRequestMethod("GET");
		in = new BufferedReader(new InputStreamReader(con.getInputStream(), "UTF-8"));
		String line; 
		while((line = in.readLine()) != null) {
			// response를 차례대로 출력 
			System.out.println(line); 
			// 성공시 {"id":2150470617,"expiresInMillis":7092289,"expires_in":7092,"app_id":713877,"appId":713877}
			//아이디 체크해서 디비에 기록된 정보 확인 멤버 확인
			Integer userId = DAO.getUserIdtoId(id);
			// 아이디를 넣어서 db상 유저번호가 있으면
			// 아이디와 닉네임 을 넣어서 두가지 조건이 충족할 경우  
			// 
			System.out.println("아이디 체크 했어요 우리 멤버인지의 결과는 :" + userId);
				if(userId != 0) {
					//정보가 있어 그냥 로그인 하면서 유저아이디 셋팅
					userInformation.setUserId(userId);
					//정보가 있어서 1을 반환
					result = 1;
				}else {
					// 왜 유저아이디를 안줬지?
					// 그럼 신규회누언인가 아니면 닉에네임이 바뀐회인가 
					//정보가 없어 멤버를 디비에 넣는다.
					DAO.signIn(id, nickName, userImg);
					//기록된 디비에서 유저아이디를 뽑아온다.
					userId = DAO.getUserIdtoId(id);
					//세션으로 보낸다.
					userInformation.setUserId(userId);
					//이제 정보가 있어 1을 반환
					result = 1;
				}
			} 
		} catch(Exception e) { 
			//실패시
			e.printStackTrace(); 
			
			} finally { 
				if(in != null) try { in.close(); } catch(Exception e) { e.printStackTrace(); } 
				} 
		
		
		return result;
	}
	//친구용 로그인 테스트 후 삭제 해야함.
	@RequestMapping(value = "test2loginAction", method = {RequestMethod.POST})
	public @ResponseBody Integer test2loginAction(Model model,@RequestParam("id") String id, HttpServletRequest request){
		
		HttpSession session = request.getSession();
		Member userInformation = new Member();
		userInformation.setId(id);
		userInformation.setNickName("테스트친구");
		userInformation.setUserImg("/images/gucci.png");
		
		session.setAttribute("userInformation", userInformation); 
		Integer result = 0;
		Integer userId = DAO.getUserIdtoId(id);
		userInformation.setUserId(userId);
		//이제 정보가 있어 1을 반환
		result = 1;
		
		return result;
		
	}
	
	
	@RequestMapping(value = "board", method = RequestMethod.GET, produces = "application/json; charset=UTF-8")
	public String board(Locale locale, Model model, HttpServletRequest request) {
		HttpSession session = request.getSession();
		Member userInformation = (Member) session.getAttribute("userInformation");
		
		//페이지 기본으로 유저의 세션정보가 없으면 로그인화면으로 간다.
		if(userInformation != null) {
			System.out.println("성공했어"+userInformation.getId());
			//유저가 제대로 로그인 한것이니 여기서 풀어준다.
			
			
			model.addAttribute("userInformation", userInformation);
		}else {
			
			return "home";
		}

		return "board";
	}
	
	//검색어로 결과 받아오기
	@RequestMapping(value = "searchCardAjax", method = {RequestMethod.POST})
	public @ResponseBody List<Post> searchCardAjax(Model model,@RequestParam("search") String search,  HttpServletRequest request){
		
		HttpSession session = request.getSession();
		Member userInformation = (Member) session.getAttribute("userInformation");
		
		List<Post> result = DAO.searchPostNick(search, userInformation.getUserId());
		
		
		return result;
	}

	
	@RequestMapping(value = "getIntroduceAjax", method = {RequestMethod.POST})
	public @ResponseBody List<Member> getIntroduceAjax(Model model,@RequestParam("userId") Integer userId,  HttpServletRequest request){
		
		HttpSession session = request.getSession();
		Member userInformation = (Member) session.getAttribute("userInformation");
		
		
		List<Member> result = DAO.getIntroduce(userInformation.getUserId());
		
		System.out.println("내가 받아온거 :" + result);
		
		return result;
	}
	
	@RequestMapping(value = "getAdmFriendsAjax", method = {RequestMethod.POST})
	public @ResponseBody List<Member> getAdmFriendsAjax(Model model,@RequestParam("userId") Integer userId, HttpServletRequest request){
		//System.out.println("getUserInfoAjax 작동중 ");
		HttpSession session = request.getSession();
		Member userInformation = (Member) session.getAttribute("userInformation");
		
		List<Member> result = DAO.getFriendsAdmList(userInformation.getUserId());

	 return result;
	}
	@RequestMapping(value = "searchFriendsAjax", method = {RequestMethod.POST})
	public @ResponseBody List<Member> searchFriendsAjax(Model model,@RequestParam("search") String search){
		//System.out.println("searchFriendsAjax 작동중 ");
		
		List<Member> result = DAO.searchFriends(search);

	 return result;
	}
	//friendSubscription
	@RequestMapping(value = "friendSubscriptionAjax", method = {RequestMethod.POST})
	public @ResponseBody void friendSubscriptionAjax(Model model,@RequestParam("fUserId") Integer fUserId, HttpServletRequest request){
		System.out.println("friendSubscriptionAjax 작동중 ");
		HttpSession session = request.getSession();
		Member userInformation = (Member) session.getAttribute("userInformation");
		
		DAO.friendSubscription(fUserId, userInformation.getUserId());
	}
	
	@RequestMapping(value = "friendsAdmAjax", method = {RequestMethod.POST})
	public @ResponseBody void friendsAdmAjax(Model model,@RequestParam("idN247_f") Integer idN247_f, HttpServletRequest request){
		System.out.println("friendsAdmAjax 작동중 ");
		HttpSession session = request.getSession();
		Member userInformation = (Member) session.getAttribute("userInformation");
		
		DAO.updateFriAdmission(idN247_f, userInformation.getUserId());
	}
	
	@RequestMapping(value = "updateDelFriAjax", method = {RequestMethod.POST})
	public @ResponseBody void updateDelFriAjax(Model model,@RequestParam("idN247_f") Integer idN247_f, HttpServletRequest request){
		//System.out.println("updateDelFriAjax 작동중 ");
		HttpSession session = request.getSession();
		Member userInformation = (Member) session.getAttribute("userInformation");
		
		DAO.updateDelFri(idN247_f);
	}
	
}
