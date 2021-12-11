package org.first.mvc;

import java.util.Locale;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
    
    @PostMapping("/register/action2")
    public void boardRegisterAction(MultipartHttpServletRequest multiRequest,Integer up_userId,Integer up_postId) {
    	try {
    		//파일업로드
    		fileService.uploadFile(multiRequest,up_userId,up_postId);
    		
    	}catch(Exception e) {
    		if (logger.isErrorEnabled()) {
    			logger.error("#Exception Message : {}",e.getMessage());
    		}
    	}
    	
    }
    
	@RequestMapping(value = "/register/action", method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
	public String uploadTest(Locale locale, Model model, MultipartHttpServletRequest multiRequest, Integer up_userId, Integer up_postId) {
		System.out.print("왜또 안돼");
		
		try {
    		//파일업로드
    		fileService.uploadFile(multiRequest,up_userId,up_postId);
			
    		
    	}catch(Exception e) {
    		if (logger.isErrorEnabled()) {
    			logger.error("#Exception Message : {}",e.getMessage());
    		}
    	}
		return "uploadTest";
	}

	
	@RequestMapping(value = "/register/userImgUpdateAction2", method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
	public String userInfoUpdate2(Locale locale, Model model, MultipartHttpServletRequest multiRequest, Integer up_userId, Integer up_postId, Integer tabId) {
		
		try {
    		//기존 파일 삭제처리 후 파일업로드
			DAO.deleteUserImg(DAO.getIdn247_up(up_userId));
    		fileService.uploadFile(multiRequest,up_userId,up_postId);
    		
    	}catch(Exception e) {
    		if (logger.isErrorEnabled()) {
    			logger.error("#Exception Message : {}",e.getMessage());
    		}
    	}
		return "board?tabId="+tabId ;
	}
	
	
	
	@RequestMapping(value = "/register/userImgUpdateAction", method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
	public RedirectView userInfoUpdate(Locale locale, Model model, MultipartHttpServletRequest multiRequest, Integer up_userId, Integer up_postId, Integer tabId) {
			
		
			
		try {
    		//기존 파일 삭제처리 후 파일업로드
			if(DAO.getIdn247_up(up_userId)==null) {
				fileService.uploadFile(multiRequest,up_userId,up_postId);
			}else {
				DAO.deleteUserImg(DAO.getIdn247_up(up_userId));
	    		fileService.uploadFile(multiRequest,up_userId,up_postId);
			}
    	}catch(Exception e) {
    		if (logger.isErrorEnabled()) {
    			logger.error("#Exception Message : {}",e.getMessage());
    		}
    	}
		return new RedirectView ("/mvc/board?tabId="+tabId) ;
	}
	
	@RequestMapping(value = "/register/createPostAction", method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
	public RedirectView createPostAction (Locale locale, Model model, String postTitle, String description, Integer tabId, Integer userNum, MultipartHttpServletRequest multiRequest ) {
		
		
		try {
		DAO.createPost(postTitle,description,tabId,userNum);
		Integer postId = DAO.getPostId(tabId);
		fileService.uploadFile(multiRequest,userNum,postId);
		}catch(Exception e) {
			if (logger.isErrorEnabled()) {
    			logger.error("#Exception Message : {}",e.getMessage());
			}
		}
		return new RedirectView("/mvc/board?tabId="+tabId);
	}
	
}
