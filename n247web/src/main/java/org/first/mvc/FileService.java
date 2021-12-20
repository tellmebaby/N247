package org.first.mvc;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.*;
import org.first.mvc.entity.Upload;

@Service("fileService")
public class FileService {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
//	파일업로드
	
	public void uploadFile(MultipartHttpServletRequest multiRequest,Integer up_userId, Integer up_postId, Integer tabId) throws Exception {
//		파라미터 이름을 키로 파라미터에 해당하는 파일 정보를 값으로 하는 Map을 가져온다.
		Map < String, MultipartFile > files = multiRequest.getFileMap();
		//System.out.println("가져온게 이거야 파일 "+files.size());
		
//		files.entrySet()의 요소를 읽어온다. 
		Iterator < Entry < String, MultipartFile >> itr = files.entrySet().iterator();
		
		MultipartFile mFile;
//		파일이 업로드 될 경로를 지정한다.
//		아래의 경우 apple 사용자의 Downloads에 파일이 업로드된다.
		String filePath = "/Users/sunghong/git/N247/n247web/src/main/webapp/resources/upload/";
//		파일명이 중복되었을 경우, 사용할 스트링 객
		String saveFileName = "", savaFilePath = "";
		
//		읽어 올 요소가 있으면 true, 없으면 false를 반환한다.
		while (itr.hasNext()) {
			Entry < String, MultipartFile > entry = itr.next();
//			entry에 값을 가져온다.
			mFile = entry.getValue();
			//System.out.println("mFile의 오리지날 파일네임 : "+mFile.getOriginalFilename());
//			파일명 
			String fileName = mFile.getOriginalFilename();
//			확장자를 제외한 파일명 
			String fileCutName = fileName.substring(0, fileName.lastIndexOf("."));
//			확장자 
			String fileExt = fileName.substring(fileName.lastIndexOf(".") + 1);
//			저장될 경로와 파일명 
			//String saveFilePath = filePath + File.pathSeparator + fileName;
			String saveFilePath = filePath + fileName;
			System.out.println(saveFilePath + "," + fileName + "," + fileCutName + "," + fileExt);
//			filePath에 해당되는 파일의 File 객체를 생성한다.
			File fileFolder = new File(filePath);
			
			if (!fileFolder.exists()) {
//				부모 폴더까지 포함하여 경로에 폴더를 만든다.
				if(fileFolder.mkdirs()) {
					logger.info("[file.mkdirs] : succes");
				} else {
					logger.error("[file.mkdirs] : fail");
				}
			}
		
			File saveFile = new File(saveFilePath);
//			saveFile이 file이면 true, 아니면 false
//			파일명이 중복일 경우 파일명(1).확장자, 파일명(2).확장자 와 같은 형태로 생성한다.
			//System.out.println("이런거 저런거 " + saveFile.getAbsolutePath() + saveFile.isFile());
			//나중에 너무 느려지니까 파일명 뒤에 시간을 달아보자 
			if (saveFile.isFile()) {
				boolean _exist = true;
				
				int index = 0;
				
//				동일한 파일명이 존재하지 않을때까지 반복한다.
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
//				생성한 파일 객체를 업로드 처리하지 않으면 임시파일에 저장된 파일이 자동적으로 삭제되기 때문에 
//				transferTo(File f) 메서드를 이용해서 업로드처리한다.
				mFile.transferTo(new File(savaFilePath));
				
				//여기서 디비에 넣자 
				

				//바뀐 파일 이름으로 기록되는지 확인해야함 
				createFileToDb(saveFile.getName(),up_userId,up_postId,tabId);
				//System.out.println(saveFile.getName()+"이게 왜 안들어가지 위 ");

			}else {
//				생성한 파일 객체를 업로드 처리하지 않으면 임시파일에 저장된 파일이 자동적으로 삭제되기 때문
//				transferTo(File f) 메서드를 이용해서 업로드처리한다.
				mFile.transferTo(saveFile);
		
				createFileToDb(saveFile.getName(),up_userId,up_postId,tabId);
				//System.out.println(saveFile.getName()+"이게 왜 안들어가지 아래 ");
			}

		}
	
	}
	
	
	public static void createFileToDb (String up_fileName, Integer up_userId, Integer up_postId, Integer tabId) {
		
		String resource = "org/first/mvc/mybatis_config.xml";
		InputStream inputStream;
		Upload p1 = new Upload();
		p1.setUp_fileName(up_fileName);
		p1.setUp_userId(up_userId);
		p1.setUp_postId(up_postId);
		p1.setUp_tabId(tabId);

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
}
