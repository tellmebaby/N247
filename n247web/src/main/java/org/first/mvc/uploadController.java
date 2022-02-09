package org.first.mvc;

import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.first.mvc.entity.Member;
import org.first.mvc.entity.Post;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.*;
import org.springframework.web.servlet.view.RedirectView;

@Controller
public class uploadController {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    
    @Resource(name = "fileService")
    FileService fileService;
    
	
	@RequestMapping(value = "/register/userImgUpdateAction", method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
	public RedirectView userImgUpdate(Locale locale, Model model, MultipartHttpServletRequest multiRequest, HttpServletRequest request ) {
		System.out.println("/register/userImgUpdateAction 돌아가는 중입니다.");
		HttpSession session = request.getSession();
		Member userIdTabId = (Member) session.getAttribute("userIdTabId");
		
		session.setAttribute("userIdTabId", userIdTabId); 	
	
		try {
	    		fileService.uploadFile2(multiRequest,userIdTabId.getUserId());
    	}catch(Exception e) {
    		if (logger.isErrorEnabled()) {
    			logger.error("#Exception Message : {}",e.getMessage());
    		}
    	}
		return new RedirectView ("/mvc/board?tabId="+userIdTabId.getTabId()) ;
	}
	
	@RequestMapping(value = "/register/createPostAction", method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
	public RedirectView createPostAction (Locale locale, Model model, String postTitle, String description, @DateTimeFormat(pattern="yyyy-MM-dd") Date dueDay, MultipartHttpServletRequest multiRequest, Integer userId, Integer tabId) {
		System.out.println("/register/createPostAction 돌아가는 중입니다.");
		
		try {
		Date date_now = new Date(System.currentTimeMillis());
		Date dueDayPara = dueDay;
		if(dueDay == null) {
			dueDayPara = date_now;
		}
		DAO.createPost(postTitle,description,tabId,userId,dueDayPara);
		Integer postId = DAO.getPostId(tabId);
		fileService.uploadFile(multiRequest,userId,postId,tabId);
		}catch(Exception e) {
			if (logger.isErrorEnabled()) {
    			logger.error("#Exception Message : {}",e.getMessage());
			}
		}
		return new RedirectView ("/mvc/board?tabId="+tabId) ;
	}
	
	@RequestMapping(value = "/register/updateFileAction", method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
	public RedirectView updateFileAction (Locale locale, Model model, MultipartHttpServletRequest multiRequest, Integer up_userId, Integer up_postId, Integer up_tabId) {
		System.out.println("/register/createPostAction 돌아가는 중입니다.");
		
		try {
		fileService.uploadFile(multiRequest,up_userId,up_postId,up_tabId);
		}catch(Exception e) {
			if (logger.isErrorEnabled()) {
    			logger.error("#Exception Message : {}",e.getMessage());
			}
		}
		return new RedirectView ("/mvc/board?tabId="+up_tabId) ;
	}
	
	
}
