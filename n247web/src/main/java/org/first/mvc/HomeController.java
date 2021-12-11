package org.first.mvc;


import java.io.IOException;
import java.io.InputStream;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.regex.Pattern;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
	
	@RequestMapping(value = "uploadTest", method = RequestMethod.GET, produces = "application/json; charset=UTF-8")
	public String uploadTest(Locale locale, Model model) {
		System.out.print("왜또 안돼");
		return "uploadTest";
	}
	
	@RequestMapping(value = "uploadTest2", method = RequestMethod.GET, produces = "application/json; charset=UTF-8")
	public String uploadTest2(Locale locale, Model model) {
		System.out.print("업로드 성공했습니다 ");
		return "uploadTest2";
	}
	
	@RequestMapping(value = "loginAction", method = RequestMethod.POST)
	public String loginAction(Locale locale, Model model, String id, String password ) {
	
		Integer count = DAO.checkLoginMember(id,password);
		String em = "Email이나 비밀번호를 다시 확인하세요. ";

		if(count == 0) {
			model.addAttribute("errorMessage",em);
			return "home";
		}
			String nickName = DAO.getMemberNick(id);
			Integer userNum = DAO.getUserNum(id);
			
			if(DAO.getCounTab(userNum) == 0) {
					
				Post userSet = new Post();
				userSet.setNick(nickName);
				userSet.setUserNum(userNum);
				model.addAttribute("userSet",userSet);
				return "firstboard";
					
			}else {
				Post secondTab = new Post(); 
				secondTab = DAO.getSecondTabIdAction(userNum);
				String introduce = (DAO.getUserIntroduce(userNum));
				Integer tabId = secondTab.getTabId();
					
				List<Post> postList = new ArrayList<Post>();
				List<Post> tabList = new ArrayList<Post>();
				List<Post> friTabList = new ArrayList<Post>();

				postList = DAO.readPostListAction(tabId);
				tabList = DAO.readTabListAction(userNum);
				friTabList = DAO.readFriTabListAction(userNum);
					
				for (int i=0 ; i<postList.size(); i++) {
					if(DAO.getCountReId(postList.get(i).getId()) == 0) {	
					}else {
						postList.get(i).setReplyList(DAO.readReplyList(postList.get(i).getId()));
					}
					if(DAO.getCounFilePost(postList.get(i).getId()) == 0) {
					}else {			
							postList.get(i).setFileNameList(DAO.getImgNameToPostId(postList.get(i).getId()));
					}
				}
				Post p4 = new Post();
				p4.setTabTitle(tabList.get(0).getTabTitle()) ;
			    String tabTT = p4.getTabTitle();
				Post userSet = new Post();
				userSet.setNick(nickName);
				userSet.setTabId(tabId);
				userSet.setUserNum(userNum);
				userSet.setImgName(DAO.getUserImg(userNum));
				String email = DAO.getIdToUserNum(userNum);
				List<Fn247> friList = new ArrayList<Fn247>();
				List<Fn247> allFriList = new ArrayList<Fn247>();
				List<Fn247> allFriList2 = new ArrayList<Fn247>();
				friList = DAO.readFriAdmTabId(tabId);
				allFriList = DAO.readFriListAction(userNum,1,tabId);
				allFriList2 = DAO.readFriAdmList(userNum,1,tabId);
					
					
				model.addAttribute("userSet",userSet);
				model.addAttribute("postList",postList);
				model.addAttribute("tabList",tabList);
				model.addAttribute("friTabList",friTabList);
				model.addAttribute("nick", userSet.getNick());
				model.addAttribute("selectedTab", tabTT);
				model.addAttribute("selectedTabId",tabId);	
				model.addAttribute("email",email);
				model.addAttribute("friendList",friList);
				model.addAttribute("allFriList",allFriList);
				model.addAttribute("allFriList2",allFriList2);
				model.addAttribute("introduce", introduce );
	
				return "board";
				
			}
		
	}

	@RequestMapping(value = "memberAction", method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
	public String memberAction(Locale locale, Model model, String id, String nickName, String password, String passwordConfirm , String role) {
		String resource = "org/first/mvc/mybatis_config.xml";
		InputStream inputStream;		
		Member member = new Member();
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
				return "signup";
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		Integer userNum = DAO.getUserNum(member.getId());
		Post userSet = new Post();
		userSet.setUserNum(userNum);
		model.addAttribute("userSet",userSet);
		return "firstboard";
	}
	
	@RequestMapping(value = "top", method = RequestMethod.GET, produces = "application/json; charset=UTF-8")
	public String top(Locale locale, Model model) {
		return "top";
	}
	
	@RequestMapping(value = "firstboard", method = RequestMethod.GET, produces = "application/json; charset=UTF-8")
	public String firstboard(Locale locale, Model model, String id, String password ) {
		return "firstboard";
	}
	
	@RequestMapping(value = "userInfoUpdateAction", method = RequestMethod.GET, produces = "application/json; charset=UTF-8")
	public RedirectView userInfoUpdateAction(Locale locale, Model model, String nickName, String mb_introduce ,Integer userId, Integer tabId  ) {
		
		DAO.userInfoUpdate(nickName, mb_introduce, userId);
		return new RedirectView ("board?tabId="+tabId);
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
	
	
	@RequestMapping(value = "board2", method = RequestMethod.GET, produces = "application/json; charset=UTF-8")
	public String board2(Locale locale, Model model, Integer tabId, Integer userNum) {
		Post p5 = new Post();
		p5.setNick(DAO.getNickNameToUserNum(userNum));
		String nick = p5.getNick();
		String introduce = (DAO.getUserIntroduce(userNum));
		List<Post> postList = new ArrayList<Post>();
		List<Post> tabList = new ArrayList<Post>();
		List<Post> friTabList = new ArrayList<Post>();
		postList = DAO.readPostListAction(tabId);
		tabList = DAO.readTabListAction(userNum);	
		friTabList = DAO.readFriTabListAction(userNum);
		for (int i=0 ; i<postList.size(); i++) {
			if(DAO.getCountReId(postList.get(i).getId()) == 0) {
			}else {
				postList.get(i).setReplyList(DAO.readReplyList(postList.get(i).getId()));
			}
			if(DAO.getCounFilePost(postList.get(i).getId()) == 0) {
			}else {			
				postList.get(i).setFileNameList(DAO.getImgNameToPostId(postList.get(i).getId()));
			}
		}
		String tabTTT = DAO.readTabTitle(tabId);
		Post userSet = new Post();
		userSet.setNick(nick);
		userSet.setTabId(tabId);
		userSet.setUserNum(userNum);
		userSet.setImgName(DAO.getUserImg(userNum));
		String email = DAO.getIdToUserNum(userNum);
		List<Fn247> friList = new ArrayList<Fn247>();
		List<Fn247> allFriList = new ArrayList<Fn247>();
		List<Fn247> allFriList2 = new ArrayList<Fn247>();
		friList = DAO.readFriAdmTabId(tabId);
		allFriList = DAO.readFriListAction(userNum,1,tabId);
		allFriList2 = DAO.readFriAdmList(userNum,1,tabId);
		model.addAttribute("userSet",userSet);
		model.addAttribute("postList",postList);
		model.addAttribute("tabList",tabList);
		model.addAttribute("nick", userSet.getNick());
		model.addAttribute("selectedTab", tabTTT);
		model.addAttribute("selectedTabId",tabId);	
		model.addAttribute("email",email);
		model.addAttribute("friendList",friList);
		model.addAttribute("allFriList",allFriList);
		model.addAttribute("allFriList2",allFriList2);
		model.addAttribute("friTabList",friTabList);
		model.addAttribute("introduce",introduce);
		return "board2";
	}
	
	@RequestMapping(value = "friendBook", method = RequestMethod.GET, produces = "application/json; charset=UTF-8")
	public String friendBook(Locale locale, Model model, Integer userNum, Integer tabId ) {
		List<Fn247> friList1 = new ArrayList<Fn247>();
		List<Fn247> friList2 = new ArrayList<Fn247>();
		List<Fn247> friList3 = new ArrayList<Fn247>();
		List<Fn247> friAdmList = new ArrayList<Fn247>();
		friList1 = DAO.readFriListAction(userNum,1,tabId);
		for(int i=0 ; i<friList1.size() ; i++) {
			System.out.println("모델로 보내는 친구 아이디 : " + friList1.get(i).getfUserId());
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
	
	@RequestMapping(value = "boardTest", method = RequestMethod.GET, produces = "application/json; charset=UTF-8")
	public String boardTest(Locale locale, Model model ,Integer tabId) {
		Integer userNum = DAO.getUserNumToTabId(tabId);	
		Post p5 = new Post();
		p5.setNick(DAO.getNickNameToUserNum(userNum));
		String nick = p5.getNick();
		String introduce = (DAO.getUserIntroduce(userNum));
		List<Post> postList = new ArrayList<Post>();
		List<Post> tabList = new ArrayList<Post>();
		List<Post> friTabList = new ArrayList<Post>();
		List<Post> selectTabList = new ArrayList<Post>();
		postList = DAO.readPostListAction(tabId);
		//탭리스트에 마지막 업데이트 시간을 넣어줬어 
		tabList = DAO.readTabListAction(userNum);
		friTabList = DAO.readFriTabListAction(userNum);
		for (int i=0 ; i<postList.size(); i++) {
			if(DAO.getCountReId(postList.get(i).getId()) == 0) {
			}else {
				postList.get(i).setReplyList(DAO.readReplyList(postList.get(i).getId()));
			}
			if(DAO.getCounFilePost(postList.get(i).getId()) == 0) {
				
			}else {
				System.out.println("체크결과 파일이 있음 ");
				postList.get(i).setFileNameList(DAO.getImgNameToPostId(postList.get(i).getId()));
			}
		}
		//탭지울때 포스트가 있으면 선택할 수 있게 해주기
		for(int i=0 ; i<tabList.size() ; i ++) {
			if(tabList.get(i).getTabId() == tabId) {
			}else {
				Post p1 = new Post();
				p1.setTabId(tabList.get(i).getTabId());
				p1.setTabTitle(tabList.get(i).getTabTitle());
				selectTabList.add(p1);					
			}
		}
		//탭안에 포스트가 있는지 확인해주기
		Integer tabCheck = DAO.getCountPostCheck(tabId);
		String tabLastUpdate = null;
		//프로젝트의 마지막 업데이트에 포스트가 없을경우 탭의 마지막 수정시간을 가져와서 넣어준다. 
		if(tabCheck == 0) {
			tabLastUpdate = DAO.dateChangeAction(DAO.readTabAction(tabId).get(0).getLastUpdate());
		}else {
			tabLastUpdate = DAO.dateChangeAction(postList.get(0).getCreate());
			//System.out.println("가져온 라스트 업데이트 시간 : " + tabLastUpdate);	
		}
		String tabTTT = DAO.readTabTitle(tabId);
		String tabIntro = DAO.getTabIntro(tabId);
		Post userSet = new Post();
		userSet.setNick(nick);
		userSet.setTabId(tabId);
		userSet.setUserNum(userNum);
		userSet.setImgName(DAO.getUserImg(userNum));
		String email = DAO.getIdToUserNum(userNum);
		List<Fn247> friList = new ArrayList<Fn247>();
		List<Fn247> allFriList = new ArrayList<Fn247>();
		List<Fn247> allFriList2 = new ArrayList<Fn247>();
		friList = DAO.readFriAdmTabId(tabId);
		allFriList = DAO.readFriListAction(userNum,1,tabId);
		allFriList2 = DAO.readFriAdmList(userNum,1,tabId);
		//마감시간 구하기
		long tabDueDay = DAO.tabDueDay(tabId);
		Double tabProgress = DAO.tabProgress(tabId);
		System.out.println("프로젝트 진행률은 : "+tabProgress +"%");
		//색깔구분해주기 1,2,3 으로주기
		String tabProgressBg = DAO.progressBg(tabProgress);
		
		model.addAttribute("selectTabList",selectTabList);
		model.addAttribute("tabCheck",tabCheck);
		model.addAttribute("tabProgressBg",tabProgressBg);
		model.addAttribute("tabProgress",tabProgress);
		model.addAttribute("tabDueDay",tabDueDay);
		model.addAttribute("tabLastUpdate",tabLastUpdate);
		model.addAttribute("userSet",userSet);
		model.addAttribute("postList",postList);
		model.addAttribute("tabList",tabList);
		model.addAttribute("nick", userSet.getNick());
		model.addAttribute("selectedTab", tabTTT);
		model.addAttribute("tabIntro", tabIntro);
		model.addAttribute("selectedTabId",tabId);	
		model.addAttribute("email",email);
		model.addAttribute("friendList",friList);
		model.addAttribute("allFriList",allFriList);
		model.addAttribute("allFriList2",allFriList2);
		model.addAttribute("friTabList",friTabList);
		model.addAttribute("introduce",introduce);
		return "boardTest";
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

	@RequestMapping(value = "board", method = RequestMethod.GET, produces = "application/json; charset=UTF-8")
	public String board(Locale locale, Model model, Integer tabId) {
		Integer userNum = DAO.getUserNumToTabId(tabId);	
		Post p5 = new Post();
		p5.setNick(DAO.getNickNameToUserNum(userNum));
		String nick = p5.getNick();
		String introduce = (DAO.getUserIntroduce(userNum));
		List<Post> postList = new ArrayList<Post>();
		List<Post> tabList = new ArrayList<Post>();
		List<Post> friTabList = new ArrayList<Post>();
		postList = DAO.readPostListAction(tabId);
		
		tabList = DAO.readTabListAction(userNum);
		friTabList = DAO.readFriTabListAction(userNum);
		for (int i=0 ; i<postList.size(); i++) {
			if(DAO.getCountReId(postList.get(i).getId()) == 0) {
			}else {
				postList.get(i).setReplyList(DAO.readReplyList(postList.get(i).getId()));
			}
			if(DAO.getCounFilePost(postList.get(i).getId()) == 0) {
				
			}else {
				System.out.println("체크결과 파일이 있음 ");
				postList.get(i).setFileNameList(DAO.getImgNameToPostId(postList.get(i).getId()));
			}
		}
		
		String tabTTT = DAO.readTabTitle(tabId);
		Post userSet = new Post();
		userSet.setNick(nick);
		userSet.setTabId(tabId);
		userSet.setUserNum(userNum);
		userSet.setImgName(DAO.getUserImg(userNum));
		String email = DAO.getIdToUserNum(userNum);
		List<Fn247> friList = new ArrayList<Fn247>();
		List<Fn247> allFriList = new ArrayList<Fn247>();
		List<Fn247> allFriList2 = new ArrayList<Fn247>();
		friList = DAO.readFriAdmTabId(tabId);
		allFriList = DAO.readFriListAction(userNum,1,tabId);
		allFriList2 = DAO.readFriAdmList(userNum,1,tabId);

		model.addAttribute("userSet",userSet);
		model.addAttribute("postList",postList);
		model.addAttribute("tabList",tabList);
		model.addAttribute("nick", userSet.getNick());
		model.addAttribute("selectedTab", tabTTT);
		model.addAttribute("selectedTabId",tabId);	
		model.addAttribute("email",email);
		model.addAttribute("friendList",friList);
		model.addAttribute("allFriList",allFriList);
		model.addAttribute("allFriList2",allFriList2);
		model.addAttribute("friTabList",friTabList);
		model.addAttribute("introduce",introduce);

		return "board";
	}
	
	
//CRUD
	
	
	@RequestMapping(value = "createMemberAction", method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
		public RedirectView createMemberAction(Locale locale, Model model, String email, String name, String pass, String passcon ) {
	
			DAO.createMember(email,name,pass,passcon);
			
			return new RedirectView("/mvc/");
		}
	
	//포스트 올릴경우 
	@RequestMapping(value = "createPostAction33", method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
		public RedirectView createPostAction33 (Locale locale, Model model, String postTitle, String description, Integer tabId, Integer userNum ) {
			
			DAO.createPost(postTitle,description,tabId,userNum);
			//FileService.uploadFile(multiRequest,userNum,DAO.getPostId(tabId));
			
			return new RedirectView("board?tabId="+tabId);
		}
	//방문객이 포스트 올릴경우 파라미터 포스트제목, 내용, 프로젝트탭아이디, 사용자번호 를 받아온다  이렇게 
	@RequestMapping(value = "createPostAction2", method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
		public RedirectView createPostAction2(Locale locale, Model model, String postTitle, String description, Integer tabId, Integer userNum ) {
			
			DAO.createPost(postTitle,description,tabId,userNum);
			return new RedirectView("board2?tabId="+tabId+"&&userNum="+userNum);
		}
	
	 @RequestMapping(value = "updatePostAction", method = RequestMethod.POST)
		public RedirectView updatePostAction(Locale locale, Model model, Integer id, String postTitle, String description, Integer tabId, String rePerson ) {
		 	DAO.updatePost(id, postTitle, description, tabId, rePerson);
			return new RedirectView("board?tabId="+tabId);
		}
	 
	 @RequestMapping(value = "updatePostAction2", method = RequestMethod.POST)
		public RedirectView updatePostAction2(Locale locale, Model model, Integer id, String postTitle, String description, Integer tabId, String rePerson, Integer userNum ) {
		 	DAO.updatePost(id, postTitle, description, tabId, rePerson);
			return new RedirectView("board2?tabId="+tabId+"&&userNum="+userNum);
		}
	 @RequestMapping(value = "updateFriAdmissionAction", method = RequestMethod.GET)
		public RedirectView updateFriAdmissionAction(Locale locale, Model model, Integer idN247_f, Integer userNum, Integer tabId ) {
		 	DAO.updateFriAdmission(idN247_f, userNum, tabId);
			return new RedirectView("friendBook?userNum="+userNum+"&&tabId="+tabId);
		}
	 
	@RequestMapping(value = "createFriTabAdd", method = RequestMethod.GET)
		public RedirectView createFriTabAdd(Locale locale, Model model, Integer ft_userId, Integer ft_tabId ) {
	    	DAO.createFriTabAdd(ft_userId, ft_tabId);
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

	@RequestMapping(value = "createTabAction", method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
		public RedirectView createTabAction(Locale locale, Model model, String tabTitle, Integer userNum ) {
			DAO.createTab(tabTitle, userNum);
			Post getTabId = new Post();
			getTabId.setTabId(DAO.getTabIdAction(tabTitle).get(0).getTabId());
			return new RedirectView("board?tabId="+getTabId.getTabId());
		}

	@RequestMapping(value = "createReplyAction", method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
		public RedirectView createReplyAction(Locale locale, Model model, String n247_reDes, Integer n247_reUsId , Integer n247_rePoId , Integer tabId) {
			DAO.createReply(n247_reDes, n247_reUsId, n247_rePoId, tabId);
			return new RedirectView("board?tabId="+tabId);
		}
	
	@RequestMapping(value = "createReplyAction2", method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
		public RedirectView createReplyAction2(Locale locale, Model model, String n247_reDes, Integer n247_reUsId , Integer n247_rePoId , Integer tabId) {
			DAO.createReply(n247_reDes, n247_reUsId, n247_rePoId, tabId);
			return new RedirectView("board2?tabId="+tabId+"&&userNum="+n247_reUsId);
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
		public RedirectView replyUpdateAction(Locale locale, Model model, Integer tabId, String tabTitle ) {
			DAO.replyUpdate(tabId, tabTitle);
			return new RedirectView("board?tabId="+tabId);
		}
	
	@RequestMapping(value = "updatePostTabTitleAction", method = RequestMethod.GET)
		public RedirectView updatePostTabTitleAction(Locale locale, Model model, Integer tabId, Integer moveOn ) {
			DAO.updatePostTabTitle(tabId, moveOn);
			return new RedirectView("board?tabId="+tabId);
		}
}
