package org.first.mvc;

import java.io.IOException;
import java.io.InputStream;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

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
		
		Integer a = getseverdata();
		
		
		String formattedDate = dateFormat.format(date);
		System.out.println("디비에서 가지고 온값 " + a);
		model.addAttribute("serverTime", formattedDate );
		//dis
		return "home";
	}
	
	@RequestMapping(value = "login", method = RequestMethod.GET, produces = "application/json; charset=UTF-8")
	public String login(Locale locale, Model model) {
		
		return "login";
	}
	
	
	@RequestMapping(value = "top", method = RequestMethod.GET, produces = "application/json; charset=UTF-8")
	public String top(Locale locale, Model model) {
		
		return "top";
	}
	
	@RequestMapping(value = "updatePost", method = RequestMethod.GET, produces = "application/json; charset=UTF-8")
	public String updatePost(Locale locale, Model model) {
		
		//하드코딩으로 입력했음 여기 넘어올때 받아와야함
		Post userSet = new Post();
		userSet.setUserId("test1");
		userSet.setTabId(1);
		userSet.setTabTitle("testTab1");
		
		
		model.addAttribute("userSet",userSet);
		return "updatePost";
	}
	
	@RequestMapping(value = "updateTab", method = RequestMethod.GET, produces = "application/json; charset=UTF-8")
	public String updateTab(Locale locale, Model model) {
		
		//하드코딩으로 입력했음 여기 넘어올때 받아와야함
		Post userSet = new Post();
		userSet.setUserId("test1");
		
		model.addAttribute("userSet",userSet);
		return "updateTab";
	}
	
	@RequestMapping(value = "board", method = RequestMethod.GET, produces = "application/json; charset=UTF-8")
	public String board(Locale locale, Model model) {
		
		//들어갈때 로그인 정보를 가져온다 사용자 ID
		//ID로 최신순으로 나열된 Post All (title, date) 을 보여준다
		//Post 내용 (postId, date, title, description, 연락처 연결 id)
		//test
		
		System.out.println("로그인 정보 가져옴");
		System.out.println("탭타이틀 가져옴");
		System.out.println("Post All 가져옴");
		List<Post> postList = new ArrayList<Post>();
		List<Post> tabList = new ArrayList<Post>();
		
		//유저아이디 입력하면 관련 게시글 최신순으로 불러오는 함수
		postList = readPostListAction("test1");
		tabList = readTabListAction("test1");
		
		System.out.println("Post List 불러왔어 " + postList );
		System.out.println("Tab List 불러왔어 "+ tabList);
		
		model.addAttribute("postList",postList);
		model.addAttribute("tabList",tabList);
		return "board";
	}
	//서버에서 아무거나 가져오기
	public static Integer getseverdata() {
		String resource = "org/first/mvc/mybatis_config.xml";
		InputStream inputStream;
		
		Integer result = 0;
		try {
			inputStream = Resources.getResourceAsStream(resource);
			SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
			SqlSession session = sqlSessionFactory.openSession();
			result = session.selectOne("org.first.mvc.BaseMapper.getCountId");

		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return result ;
	}
	
//CRUD
	
	@RequestMapping(value = "createPostAction", method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
	public RedirectView createPostAction(Locale locale, Model model, String userId, String postTitle, String description, Integer tabId, String tabTitle, String rePerson ) {
		
		String resource = "org/first/mvc/mybatis_config.xml";
		InputStream inputStream;
		
		try {
			
			inputStream = Resources.getResourceAsStream(resource);
			SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
			SqlSession session = sqlSessionFactory.openSession();
			
			System.out.println("createPostAction가동");
			Post post = new Post();
			post.setUserId(userId);
			post.setDescription(description);
			post.setPostTitle(postTitle);
			post.setTabId(tabId);
			post.setTabTitle(tabTitle);
			post.setRePerson(rePerson);
			
			System.out.println("포스트 받아왔어 " + post.getDescription());
			
			session.insert("org.first.mvc.BaseMapper.insertPost", post);
			session.commit();
			session.close();
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("포스트 작성하려다가 에러가 났어요  ");
		}
		return new RedirectView("board");
	}
	
	
	
	
	public static Integer readPostAcrion() {
		String resource = "org/first/mvc/mybatis_config.xml";
		InputStream inputStream;
		
		Integer result = 0;
		try {
			inputStream = Resources.getResourceAsStream(resource);
			SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
			SqlSession session = sqlSessionFactory.openSession();
			result = session.selectOne("org.first.mvc.BaseMapper.getCountId");

		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return result ;
	}
	
	public static List<Post> readPostListAction(String userId) {
		
		List<Post> result = new ArrayList<Post>();
		
		String resource = "org/first/mvc/mybatis_config.xml";
		InputStream inputStream;
	
		try {
			inputStream = Resources.getResourceAsStream(resource);
			SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
			SqlSession session = sqlSessionFactory.openSession();
			result = session.selectList("org.first.mvc.BaseMapper.getPostList", userId);
			

		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return result ;
	}
	
	public static Integer updatePostAction() {
		String resource = "org/first/mvc/mybatis_config.xml";
		InputStream inputStream;
		
		Integer result = 0;
		try {
			inputStream = Resources.getResourceAsStream(resource);
			SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
			SqlSession session = sqlSessionFactory.openSession();
			result = session.selectOne("org.first.mvc.BaseMapper.getCountId");

		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return result ;
	}
	
	public static Integer deletePostAction() {
		String resource = "org/first/mvc/mybatis_config.xml";
		InputStream inputStream;
		
		Integer result = 0;
		try {
			inputStream = Resources.getResourceAsStream(resource);
			SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
			SqlSession session = sqlSessionFactory.openSession();
			result = session.selectOne("org.first.mvc.BaseMapper.getCountId");

		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return result ;
	}
	
	
	// 탭 crud
	
	
	@RequestMapping(value = "createTabAction", method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
	public RedirectView createPostAction(Locale locale, Model model, String userId, String tabTitle ) {
		
		String resource = "org/first/mvc/mybatis_config.xml";
		InputStream inputStream;
		
		try {
			
			inputStream = Resources.getResourceAsStream(resource);
			SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
			SqlSession session = sqlSessionFactory.openSession();
			
			System.out.println("createTabAction가동");
			Post tab = new Post();
			tab.setUserId(userId);
			tab.setTabTitle(tabTitle);
			
			System.out.println("탭을 받아왔어 " + tab.getTabTitle());
			
			session.insert("org.first.mvc.BaseMapper.insertTab", tab);
			session.commit();
			session.close();
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("탭 작성하려다가 에러가 났어요  ");
		}
		return new RedirectView("top");
	}
	
	
	public static List<Post> readTabListAction(String userId) {
		
		List<Post> result = new ArrayList<Post>();
		
		String resource = "org/first/mvc/mybatis_config.xml";
		InputStream inputStream;
	
		try {
			inputStream = Resources.getResourceAsStream(resource);
			SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
			SqlSession session = sqlSessionFactory.openSession();
			result = session.selectList("org.first.mvc.BaseMapper.getTabList", userId);
			

		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return result ;
	}
	
}
