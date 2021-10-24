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
	
	
	@RequestMapping(value = "board", method = RequestMethod.GET, produces = "application/json; charset=UTF-8")
	public String board(Locale locale, Model model) {
		
		//들어갈때 로그인 정보를 가져온다 사용자 ID
		//ID로 최신순으로 나열된 Post All (title, date) 을 보여준다
		//Post 내용 (postId, date, title, description, 연락처 연결 id)
		//test
		
		
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
	
	
}
