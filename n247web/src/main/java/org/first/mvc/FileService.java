package org.first.mvc;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.multipart.*;
import org.first.mvc.entity.Member;
import org.first.mvc.entity.Upload;

@Service("fileService")
public class FileService {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
//	파일업로드
	
	public void uploadFile(MultipartHttpServletRequest multiRequest,Integer up_userId, Integer up_postId, Integer tabId) throws Exception {
			
			
			Date date_now = new Date(System.currentTimeMillis());
			String name = "N247"+DAO.dateToString2(date_now);
			System.out.println(name + "로 저장되는 중");
//			파라미터 이름을 키로 파라미터에 해당하는 파일 정보를 값으로 하는 Map을 가져온다.
			Map < String, MultipartFile > files = multiRequest.getFileMap();
			//System.out.println("가져온게 이거야 파일 "+files.size());
			
//			files.entrySet()의 요소를 읽어온다. 
			Iterator < Entry < String, MultipartFile >> itr = files.entrySet().iterator();
			
			MultipartFile mFile;
//			파일이 업로드 될 경로를 지정한다.
//			아래의 경우 apple 사용자의 Downloads에 파일이 업로드된다.
			String filePath = "/Users/sunghong/git/N247/n247web/src/main/webapp/resources/upload/";
//			String filePath = "/var/lib/tomcat9/webapps/images/";
//			파일명이 중복되었을 경우, 사용할 스트링 객
			String saveFileName = "", savaFilePath = "";
			int up_check = 1;
//			읽어 올 요소가 있으면 true, 없으면 false를 반환한다.
			while (itr.hasNext()) {
				Entry < String, MultipartFile > entry = itr.next();
//				entry에 값을 가져온다.
				mFile = entry.getValue();
				//System.out.println("mFile의 오리지날 파일네임 : "+mFile.getOriginalFilename());
//				파일명 
				String fileName = mFile.getOriginalFilename();
//				String fileName = mFile.getName();
//				확장자를 제외한 파일명 
//				String fileCutName = fileName.substring(0, fileName.lastIndexOf("."));
				String fileCutName = name;
//				확장자 
				String fileExt = fileName.substring(fileName.lastIndexOf(".") + 1);
				String[] extCheck = {"jpg","jpeg","png","bmp","gif","webp","svg"};
				
				for (int i=0 ; i<extCheck.length ; i++) {
//					if(fileExt!=extCheck[i]) {
					if(extCheck[i].equals(fileExt)) {
						System.out.println(i+"번째 검색중 : "+extCheck[i]+" 를 매칭중 ->" + fileExt + "가 매칭되는게 있어서 p1에 1을 준다");
						up_check = 0;
					}
				}
				
//				저장될 경로와 파일명 
				//String saveFilePath = filePath + File.pathSeparator + fileName;
//				String saveFilePath = filePath + fileName;
				String saveFilePath = filePath + name +"."+ fileExt;
				System.out.println(saveFilePath + "," + fileName + "," + fileCutName + "," + fileExt);
//				filePath에 해당되는 파일의 File 객체를 생성한다.
				File fileFolder = new File(filePath);
				
				if (!fileFolder.exists()) {
//					부모 폴더까지 포함하여 경로에 폴더를 만든다.
					if(fileFolder.mkdirs()) {
						logger.info("[file.mkdirs] : succes");
					} else {
						logger.error("[file.mkdirs] : fail");
					}
				}
			
				File saveFile = new File(saveFilePath);
//				saveFile이 file이면 true, 아니면 false
//				파일명이 중복일 경우 파일명(1).확장자, 파일명(2).확장자 와 같은 형태로 생성한다.
				//System.out.println("이런거 저런거 " + saveFile.getAbsolutePath() + saveFile.isFile());
				//나중에 너무 느려지니까 파일명 뒤에 시간을 달아보자 
				if (saveFile.isFile()) {
					boolean _exist = true;
					
					int index = 0;
					
//					동일한 파일명이 존재하지 않을때까지 반복한다.
					while (_exist) {
						index++;
						saveFileName = fileCutName + "(" + index + ")." + fileExt;
						//String dictFile = filePath + File.pathSeparator + saveFileName;
						String dictFile = filePath + saveFileName;
						_exist = new File(dictFile).isFile();
						
						if(!_exist) {
							savaFilePath = dictFile;
						}
					}
//					생성한 파일 객체를 업로드 처리하지 않으면 임시파일에 저장된 파일이 자동적으로 삭제되기 때문에 
//					transferTo(File f) 메서드를 이용해서 업로드처리한다.
					mFile.transferTo(new File(savaFilePath));
					
					//여기서 디비에 넣자 
					

					//바뀐 파일 이름으로 기록되는지 확인해야함 
					if(up_postId != 0 ) {
						createFileToDb(saveFile.getName(),up_userId,up_postId,tabId,up_check,fileName);
					}

				}else {
					
					if(up_postId != 0 ) {
//					생성한 파일 객체를 업로드 처리하지 않으면 임시파일에 저장된 파일이 자동적으로 삭제되기 때문
//					transferTo(File f) 메서드를 이용해서 업로드처리한다.
					mFile.transferTo(saveFile);
			
					createFileToDb(saveFile.getName(),up_userId,up_postId,tabId,up_check,fileName);
					}
				}

			}

	}
	
	
	public void uploadFile2 (MultipartHttpServletRequest multiRequest,Integer up_userId) throws Exception {
		
//			파라미터 이름을 키로 파라미터에 해당하는 파일 정보를 값으로 하는 Map을 가져온다.
			Map < String, MultipartFile > files = multiRequest.getFileMap();
			//System.out.println("가져온게 이거야 파일 "+files.size());
			
//			files.entrySet()의 요소를 읽어온다. 
			Iterator < Entry < String, MultipartFile >> itr = files.entrySet().iterator();
			
			MultipartFile mFile;
//			파일이 업로드 될 경로를 지정한다.
//			아래의 경우 apple 사용자의 Downloads에 파일이 업로드된다.
			String filePath = "/Users/sunghong/git/N247/n247web/src/main/webapp/resources/upload/";
//			String filePath = "/var/lib/tomcat9/webapps/images/";

			
//			파일명이 중복되었을 경우, 사용할 스트링 객
			String saveFileName = "", savaFilePath = "";
			
//			읽어 올 요소가 있으면 true, 없으면 false를 반환한다.
			while (itr.hasNext()) {
				Entry < String, MultipartFile > entry = itr.next();
//				entry에 값을 가져온다.
				mFile = entry.getValue();
				//System.out.println("mFile의 오리지날 파일네임 : "+mFile.getOriginalFilename());
//				파일명 
				String fileName = mFile.getOriginalFilename();
//				확장자를 제외한 파일명 
				String fileCutName = fileName.substring(0, fileName.lastIndexOf("."));
				
//				확장자 
				String fileExt = fileName.substring(fileName.lastIndexOf(".") + 1);
//				저장될 경로와 파일명 
				//String saveFilePath = filePath + File.pathSeparator + fileName;
				String saveFilePath = filePath + fileName;
				System.out.println(saveFilePath + "," + fileName + "," + fileCutName + "," + fileExt);
//				filePath에 해당되는 파일의 File 객체를 생성한다.
				File fileFolder = new File(filePath);
				
				if (!fileFolder.exists()) {
//					부모 폴더까지 포함하여 경로에 폴더를 만든다.
					if(fileFolder.mkdirs()) {
						logger.info("[file.mkdirs] : succes");
					} else {
						logger.error("[file.mkdirs] : fail");
					}
				}
			
				File saveFile = new File(saveFilePath);
//				saveFile이 file이면 true, 아니면 false
//				파일명이 중복일 경우 파일명(1).확장자, 파일명(2).확장자 와 같은 형태로 생성한다.
				//System.out.println("이런거 저런거 " + saveFile.getAbsolutePath() + saveFile.isFile());
				//나중에 너무 느려지니까 파일명 뒤에 시간을 달아보자 
				if (saveFile.isFile()) {
					boolean _exist = true;
					
					int index = 0;
					
//					동일한 파일명이 존재하지 않을때까지 반복한다.
					while (_exist) {
						index++;
						saveFileName = fileCutName + "(" + index + ")." + fileExt;
						//String dictFile = filePath + File.pathSeparator + saveFileName;
						String dictFile = filePath + saveFileName;
						_exist = new File(dictFile).isFile();
						
						if(!_exist) {
							savaFilePath = dictFile;
						}
					}
//					생성한 파일 객체를 업로드 처리하지 않으면 임시파일에 저장된 파일이 자동적으로 삭제되기 때문에 
//					transferTo(File f) 메서드를 이용해서 업로드처리한다.
					mFile.transferTo(new File(savaFilePath));
					//여기서 디비에 넣자 
					//바뀐 파일 이름으로 기록되는지 확인해야함 
						updateUserImgToDb(saveFile.getName(),up_userId);
						System.out.println(saveFile.getName()+"updateUserImgToDb 이게 왜 안들어가지 위 ");	

				}else {
//					생성한 파일 객체를 업로드 처리하지 않으면 임시파일에 저장된 파일이 자동적으로 삭제되기 때문
//					transferTo(File f) 메서드를 이용해서 업로드처리한다.
					mFile.transferTo(saveFile);
						updateUserImgToDb(saveFile.getName(),up_userId);
						System.out.println(saveFile.getName()+" updateUserImgToDb 이게 왜 안들어가지 아래 ");
					}
				}

		}
	
	
//	@GetMapping("/mvc/download")
//	public void downloadFile (String up_fileName, HttpServletRequest request, HttpServletResponse response) throws Exception {
//		
//		System.out.println(" downloadFile 실행했어요 ");
//		try {
//			// 다운로드 받을 파일명을 가져온다.
//			String fileName = request.getParameter(up_fileName);
//			
//			// 다운로드 경로 (내려받을 파일경로를 설정한다.)
//			String filePath = "/Users/sunghong/git/N247/n247web/src/main/webapp/resources/upload/";
//			
//			// 경로와 파일명으로 파일 객체를 생성한다.
//			File dFile = new File(filePath, fileName);
//			
//			// 파일 길이를 가져온다.
//			int fSize = (int) dFile.length();
//			
//			// 파일이 존재
//			if (fSize > 0) {
//				// 파일명을 URLEncoder 하여 attachment, Content-Disposition Header로 설정
//				
//				String encodedFilename = "attachment; filename*=" + "UTF-8" + "''" + URLEncoder.encode(fileName, "UTF-8");
//				
//				// ContentType 설정
//				response.setContentType("application/octet-stream; charset=utf-8");
//				
//				// Header 설정
//				response.setHeader("Content-Disposition", encodedFilename);
//				
//				// ContentLength 설정
//				response.setContentLength(fSize);
//				
//				BufferedInputStream in = null;
//				BufferedOutputStream out = null;
//				
//				/* BufferedInputStream
//				 * 
//				 java.io의 가장 기본파일 입출력 클래스
//				 입력 스트림(통로)를 생성해줌
//				 사용법은 간단하지만, 버퍼를 사용하지 않개 때문에 느림
//				 속도 문제를 해결하기 위해 버퍼를 사용하는 다른 클래스와 같이 쓰는 경우가 많음
//				 */
//				
//				in = new BufferedInputStream(new FileInputStream(dFile));
//				
//				/* BufferedOutputStream
//				 * 
//				 java.io의 가장 기본파일 입출력 클래스
//				 입력 스트림(통로)를 생성해줌
//				 사용법은 간단하지만, 버퍼를 사용하지 않개 때문에 느림
//				 속도 문제를 해결하기 위해 버퍼를 사용하는 다른 클래스와 같이 쓰는 경우가 많음
//				 */
//				
//				out = new BufferedOutputStream(response.getOutputStream());
//				
//				try {
//					byte[] buffer = new byte[4096];
//					int bytesRead = 0;
//					
//					/*
//					 모두 현재 파일 포이너 위치를 기준으로 함 (파일 포인터 앞의 내용은 없는것처럼 작동)
//					 int read() : 1byte씩 내용을 읽어 정수로 반환
//					 int read(byte[] b) : 파일 내용을 한번에 모두 읽어서 배열에 저장
//					 int read(byte[] b. int off, int len) : 'len'길이만큼만 읽어서 배열의 'off'번째 위치부터 저 
//					 */
//					while ((bytesRead = in .read(buffer)) != -1) {
//						out.write(buffer, 0, bytesRead);
//					}
//					
//					// 버퍼에 남은 내용이 있다면, 모두 파일에 출력
//					out.flush();
//				} finally {
//					/*
//					 현재 열려 in,out 스트림을 닫음
//					 메모리 누수를 방지하고 다른 곳에서 리소스 사용이 가능하게 만듬
//					 */
//					in.close();
//					out.close();
//				
//				}
//			}else {
//				throw new FileNotFoundException("파일이 없습니다.");
//			}
//		} catch (Exception e) {
//			logger.info(e.getMessage());
//		}
//		
//	}
	
	public static void createFileToDb (String up_fileName, Integer up_userId, Integer up_postId, Integer tabId, Integer up_check, String up_orgName) {
		
		String resource = "org/first/mvc/mybatis_config.xml";
		InputStream inputStream;
		Upload p1 = new Upload();
//		String fileName = up_fileName.replaceAll("\\\\", "%2B") ;
//		System.out.println("받아오는파일명 : " + fileName);
		p1.setUp_fileName(up_fileName);
		p1.setUp_userId(up_userId);
		p1.setUp_postId(up_postId);
		p1.setUp_tabId(tabId);
		p1.setUp_check(up_check);
		p1.setUp_orgName(up_orgName);

		try {
			inputStream = Resources.getResourceAsStream(resource);
			SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
			SqlSession session = sqlSessionFactory.openSession();
			session.insert("org.first.mvc.BaseMapper.insertFile", p1);
			session.commit();
			session.close();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	public static void updateUserImgToDb (String up_fileName, Integer up_userId) {
		
		String resource = "org/first/mvc/mybatis_config.xml";
		InputStream inputStream;
		Member p2 = new Member();
		p2.setUserImg(up_fileName);
		p2.setUserId(up_userId);

		try {
			inputStream = Resources.getResourceAsStream(resource);
			SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
			SqlSession session = sqlSessionFactory.openSession();
			session.insert("org.first.mvc.BaseMapper.updateUserImg", p2);
			session.commit();
			session.close();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
}
