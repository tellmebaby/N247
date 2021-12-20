package org.first.mvc;


import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.text.SimpleDateFormat;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.springframework.stereotype.Controller;
import org.first.mvc.entity.Fn247;
import org.first.mvc.entity.Member;
import org.first.mvc.entity.Post;
import org.first.mvc.entity.Upload;
import org.first.mvc.entity.UserLog;

/**
 * Handles requests for the application home page.
 */
@Controller
public class DAO {
	
			public static void createMember (String email, String name, String pass, String passcon ) {
				String resource = "org/first/mvc/mybatis_config.xml";
				InputStream inputStream;
				UserLog p1 = new UserLog();
				p1.setEmail(email);
				p1.setName(name);
				p1.setPass(pass);
				p1.setPasscon(passcon);
				try {
					inputStream = Resources.getResourceAsStream(resource);
					SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
					SqlSession session = sqlSessionFactory.openSession();
					session.insert("org.first.mvc.BaseMapper.insertMember", p1);
					session.commit();
					session.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			
			public static void createPost( String postTitle, String description, Integer tabId,Integer userNum,Date dueDay ) {
				String resource = "org/first/mvc/mybatis_config.xml";
				InputStream inputStream;
				Post post = new Post();
				post.setDescription(description);
				post.setPostTitle(postTitle);
				post.setTabId(tabId);
				post.setUserNum(userNum);
				post.setDueDay(dueDay);
				try {
					inputStream = Resources.getResourceAsStream(resource);
					SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
					SqlSession session = sqlSessionFactory.openSession();
					session.insert("org.first.mvc.BaseMapper.insertPost", post);
					session.commit();
					session.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			
			
			public static void createFriTabAdd (Integer ft_userId, Integer ft_tabId ) {
				String resource = "org/first/mvc/mybatis_config.xml";
				InputStream inputStream;
				Fn247 p1 = new Fn247();
				p1.setFt_userId(ft_userId);
				p1.setFt_tabId(ft_tabId);
				try {
					inputStream = Resources.getResourceAsStream(resource);
					SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
					SqlSession session = sqlSessionFactory.openSession();
					session.update("org.first.mvc.BaseMapper.subFriTab", p1);
					session.commit();
					session.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			
			public static void createTab (String tabTitle, Integer userNum, String tab_intro, Date tab_dueDay ) {
				String resource = "org/first/mvc/mybatis_config.xml";
				InputStream inputStream;
				Post tab = new Post();
				tab.setTabTitle(tabTitle);
				tab.setUserNum(userNum);
				tab.setTab_intro(tab_intro);
				tab.setTab_dueDay(tab_dueDay);
				try {
					inputStream = Resources.getResourceAsStream(resource);
					SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
					SqlSession session = sqlSessionFactory.openSession();
					session.insert("org.first.mvc.BaseMapper.insertTab", tab);
					session.commit();
					session.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
	
			public static void createReply (String n247_reDes, Integer n247_reUsId , Integer n247_rePoId , Integer tabId) {
				String resource = "org/first/mvc/mybatis_config.xml";
				InputStream inputStream;
				Post reply = new Post();
				reply.setN247_reDes(n247_reDes);
				reply.setN247_reUsId(n247_reUsId);
				reply.setN247_rePoId(n247_rePoId);
				reply.setN247_reTabId(tabId);
				try {
					inputStream = Resources.getResourceAsStream(resource);
					SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
					SqlSession session = sqlSessionFactory.openSession();
					session.insert("org.first.mvc.BaseMapper.insertReply", reply);
					session.commit();
					session.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			

			public static void friendSubscription (Integer fUserId, Integer myId , Integer tabId ) {
				String resource = "org/first/mvc/mybatis_config.xml";
				InputStream inputStream;
				Fn247 p1 = new Fn247();
				p1.setfUserId(fUserId);
				p1.setMyId(myId);
				try {
					inputStream = Resources.getResourceAsStream(resource);
					SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
					SqlSession session = sqlSessionFactory.openSession();
					session.insert("org.first.mvc.BaseMapper.subFri", p1);
					session.commit();
					session.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			
			//세션에 남길 친구목록 받아가기 
			public static Fn247 getFriendAllList (Integer userNum) {
				Fn247 result = new Fn247();
				List<Fn247> p2 = new ArrayList<Fn247>();
				String resource = "org/first/mvc/mybatis_config.xml";
				InputStream inputStream;
				Fn247 p1 = new Fn247();
				p1.setMyId(userNum);
				
				
				try {
					inputStream = Resources.getResourceAsStream(resource);
					SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
					SqlSession session = sqlSessionFactory.openSession();
					//이제 모든 승인된 친구를 세션에 저장시킨다 작업중 
					p2 = session.selectList("org.first.mvc.BaseMapper.getFriendList", p1);
					result.setFriendAllList(p2);
				} catch (IOException e) {
					e.printStackTrace();
				}
				return result;
			}
			
			public static String cardReSetName (Integer p1, Member p2) {

				String result ="";
				for (int i=0 ; i<p2.getFriendInformationList().size() ; i++) {
					if(p2.getFriendInformationList().get(i).getUserId() == p1) {
						result = p2.getFriendInformationList().get(i).getNickName();
					}
				}
				return result;
			}
			
			//세젼을 이용해 공유탭목록과 유저번호 넣으면 분류해주는 로직
			public static Fn247 friendListClassification (List<Fn247> friendAllList, Integer userNum, Integer tabId, Member friInfo) {
				Fn247 result = new Fn247();
				List<Fn247> tabAdmList = new ArrayList<Fn247>();
				//현재탭에 공유자가 있는지 체크
				//공유자목록을 받아옴 
				tabAdmList = getTabAdmList(tabId);	
				if (tabAdmList == null){
					System.out.println("탭에 공유된 아이디가 없습니다. ");
				}else {
					System.out.println("탭에 공유된 아이디가 있습니다. ");
					//해당탭에 공유된 친구리스트를 아이디,이메일,사진,친구와공유된기록의 아이디를 리던해줌
//					result = getFriendAdmList (tabAdmList);
				}
				//전체 친구목록 파라미터를 넣어주면 유형별로 5가지로 분류해서 리턴 전체친구목록 friendAllList, 나의 유저아이디 userNum, 탭공유기록 tabAdmList  
				result = allFriendListClassification(friendAllList,userNum,tabAdmList,friInfo);
				if(result.getFriendAdmList() != null) {
					System.out.println("결과에 탭에 공유된 친구기록이 있습니다. 뭔가를 넣긴 넣었네요 ");
				}
				if(result.getiApproveAdmList() != null) {
					System.out.println("결과에 탭에 getiApproveAdmList()가 있네요  ");
				}
				return result;
			}
			

			

			public static Fn247 allFriendListClassification (List<Fn247> friendAllList, Integer userNum, List<Fn247> tabAdmList, Member friInfo) {
				Fn247 result = new Fn247();
				List<Fn247> p2 = new ArrayList<Fn247>();
				p2.addAll(friendAllList);
				//내가신청해서 승인된 친구 목록 넣어줄 배열 
				List<Fn247> fri1 = new ArrayList<Fn247>();
				List<Fn247> fri11 = new ArrayList<Fn247>();
				//친구가 신청해서 승인된 친구 목록 넣어줄 배열 
				List<Fn247> fri2 = new ArrayList<Fn247>();
				List<Fn247> fri21 = new ArrayList<Fn247>();
				//내가 친구신청한 승인대기 목록 넣어줄 배열
				List<Fn247> fri3 = new ArrayList<Fn247>();
				//친구가 신청한 승인대기 목록 넣어줄 배열
				List<Fn247> fri4 = new ArrayList<Fn247>();
				//공유된탭기록에 있는 친구정보 목록 넣어줄 배열
				List<Fn247> fri5 = new ArrayList<Fn247>();
				//공유된 탭기록에 있는 친구들을 공유된탭 tabAdmList 파라미터를 주고 친구정보배열을 리턴받음 
				fri5 = getFriendAdmList (tabAdmList, friInfo);
				
				//받아온 모든 관련된 친구들을 분류별로 분류
				for(int i=0 ; i<p2.size(); i++) {
					//true 친구관계가 승인된 데이터 
					if(p2.get(i).getAdm()==1) {
						//true 승인된 친구중 친구신청을 자신이 한 데이터 
						if(p2.get(i).getMyId() == userNum) {
							System.out.println("p2.get"+i+"번째"+p2.get(i).getMyId() + "하고  " + userNum + "랑 같은니까 넣고있어" );
							fri1.add(p2.get(i));
						//false 승인된 친구중 친구신청을 자신이 하지않은 데이터 	
						}else {
							System.out.println("p2.get"+i+"번째"+p2.get(i).getMyId() + "하고  " + userNum + "랑 다르니까 넣고있어" );
							fri2.add(p2.get(i));
						}
				    //false 친구관계가 승인되지 않은 데이터 	
					}else {
						if(p2.get(i).getMyId() == userNum) {
							fri3.add(p2.get(i));
							//System.out.println(p2.get(i).getMyId() + "를 체크중3");
						}else {
							fri4.add(p2.get(i));
							//System.out.println(p2.get(i).getMyId() + "를 체크중4");
						}
					}
				}
				//분류된 목록에 친구의 닉네임 및 이미지를 찾아서 저장
				if(fri1 != null) {
					//현재 탭에 공유된 내가 친추한 친구를 체크 1 및 친구들 닉네임과 이미지저장해주기 
					
					fri11 = getiApproveAdmList(fri1,tabAdmList,friInfo);
					for(int i=0 ; i<fri11.size(); i++) {
						System.out.println("fri11.get(i).getF_name()" + fri11.get(i).getF_name());
					}
					result.setiApproveAdmList(fri11);
					
				}
				if(fri2 != null) {
					
					fri21 = getFriendApproveAdmList(fri2,tabAdmList,friInfo);
					for(int i=0 ; i<fri21.size(); i++) {
						System.out.println("fri21.get(i).getF_name()" + fri21.get(i).getF_name());
					}
					result.setFriendApproveAdmList(fri21);
					
				}
				if(fri3 != null) {
					//친구들 닉네임과 이미지저장해주기 
					List<Fn247> p1 = new ArrayList<Fn247>();
					p1.addAll(getWaitAdmListClassification(fri3,tabAdmList,friInfo));
					result.setiWaitAdmList(p1);
				}
				if(fri4 != null) {
					List<Fn247> p1 = new ArrayList<Fn247>();
					p1.addAll(getWaitAdmListClassification(fri4,tabAdmList,friInfo));
					//친구들 닉네임과 이미지저장해주기 
					result.setWaitingAdmList(p1);
				}
				if(fri5 != null) {
					//공유된 탭에있는 아이들을 리턴
					result.setFriendAdmList(fri5);
				}
				//탭에 공유된 친구목록을 화면에 표시되는지 체크 및 정보입력
//				result.setiApproveAdmList(fri11);
//				for(int i=0 ; i<result.getiApproveAdmList().size(); i++) {
//					System.out.println("result.getiApproveAdmList().get(i).getF_name()" + result.getiApproveAdmList().get(i).getF_name());
//				}
//				result.setFriendApproveAdmList(fri21);
//				
				return result;
			}
			
			
			public static List<Fn247> getiApproveAdmList (List<Fn247> iApproveAdmList, List<Fn247> tabAdmList, Member friInfo ){
				List<Fn247> result = new ArrayList<Fn247>();
				List<Fn247> p1 = new ArrayList<Fn247>();
				p1 = iApproveAdmList;
				//System.out.println("1번 받아왔니 :"+fri1.size());
				for(int i=0 ; i<p1.size(); i++){
					for(int k=0 ; k<friInfo.getFriendInformationList().size(); k++){
						if(p1.get(i).getfUserId() == friInfo.getFriendInformationList().get(k).getUserId()) {
							p1.get(i).setF_name(friInfo.getFriendInformationList().get(k).getNickName());
							p1.get(i).setF_imgName(friInfo.getFriendInformationList().get(k).getUserImg());
							System.out.println("iApproveAdmList.get(i).getF_name() : "+p1.get(i).getF_name()+"님의 정보를 입력중 - "
									+"사진이름은 : " + p1.get(i).getF_imgName());
						}
					}
					p1.get(i).setCheck(0);
					for(int j=0 ; j<tabAdmList.size() ; j++) {
						if(p1.get(i).getfUserId() == tabAdmList.get(j).getFt_userId()) {
							//System.out.println("여기오고있니? 친구아이디가 무려 : " + result.getiApproveAdmList().get(i).getfUserId());
							p1.get(i).setCheck(1);
						}
					}
				}
				
				result.addAll(p1);
				System.out.println("%$^&$%^&$%^&$%^&$%^&$%^&결과는 : "+result.size());
				return result;	
			}
			
			
//			public static Fn247 getiApproveAdmList (List<Fn247> iApproveAdmList, List<Fn247> tabAdmList, Member friInfo ){
//				Fn247 result = new Fn247();
//				List<Fn247> p1 = new ArrayList<Fn247>();
//				p1 = iApproveAdmList;
//				//System.out.println("1번 받아왔니 :"+fri1.size());
//				for(int i=0 ; i<p1.size(); i++){
//					for(int k=0 ; k<friInfo.getFriendInformationList().size(); k++){
//						if(p1.get(i).getfUserId() == friInfo.getFriendInformationList().get(k).getUserId()) {
//							p1.get(i).setF_name(friInfo.getFriendInformationList().get(k).getNickName());
//							p1.get(i).setF_imgName(friInfo.getFriendInformationList().get(k).getUserImg());
//							System.out.println("iApproveAdmList.get(i).getF_name() : "+p1.get(i).getF_name()+"님의 정보를 입력중 - "
//									+"사진이름은 : " + p1.get(i).getF_imgName());
//						}
//					}
//					p1.get(i).setCheck(0);
//					for(int j=0 ; j<tabAdmList.size() ; j++) {
//						if(p1.get(i).getfUserId() == tabAdmList.get(j).getFt_userId()) {
//							//System.out.println("여기오고있니? 친구아이디가 무려 : " + result.getiApproveAdmList().get(i).getfUserId());
//							p1.get(i).setCheck(1);
//						}
//					}
//				}
//				
//				result.setiApproveAdmList(p1);
//				System.out.println("%$^&$%^&$%^&$%^&$%^&$%^&결과는 : "+result.getiApproveAdmList().size());
//				return result;	
//			}
			public static List<Fn247> getFriendApproveAdmList (List<Fn247> friendApproveAdmList, List<Fn247> tabAdmList, Member friInfo ){
				List<Fn247> result = new ArrayList<Fn247>();
				List<Fn247> p1 = new ArrayList<Fn247>();
				p1 = friendApproveAdmList;
				for(int i=0 ; i<p1.size(); i++){
					for(int k=0 ; k<friInfo.getFriendInformationList().size(); k++){
						if(p1.get(i).getMyId() == friInfo.getFriendInformationList().get(k).getUserId()) {
							p1.get(i).setF_name(friInfo.getFriendInformationList().get(k).getNickName());
							p1.get(i).setF_imgName(friInfo.getFriendInformationList().get(k).getUserImg());
							System.out.println("friendApproveAdmList.get(i).getF_name() : "+p1.get(i).getF_name()+"님의 정보를 입력중 - "
									+"사진이름은 : " + p1.get(i).getF_imgName());	
						}
					}
						p1.get(i).setCheck(0);
						for(int j=0 ; j<tabAdmList.size() ; j++) {
							if(p1.get(i).getMyId() == tabAdmList.get(j).getFt_userId()) {
								//System.out.println("여기오고있니?");
								p1.get(i).setCheck(1);
							}
						}
					}
				result.addAll(p1);
				
				return result;	
			}
			
//			public static Fn247 getFriendApproveAdmList (List<Fn247> friendApproveAdmList, List<Fn247> tabAdmList, Member friInfo ){
//				Fn247 result = new Fn247();
//				
//				for(int i=0 ; i<friendApproveAdmList.size(); i++){
//					for(int k=0 ; k<friInfo.getFriendInformationList().size(); k++){
//						if(friendApproveAdmList.get(i).getMyId() == friInfo.getFriendInformationList().get(k).getUserId()) {
//							friendApproveAdmList.get(i).setF_name(friInfo.getFriendInformationList().get(k).getNickName());
//							friendApproveAdmList.get(i).setF_imgName(friInfo.getFriendInformationList().get(k).getUserImg());
//							System.out.println("friendApproveAdmList.get(i).getF_name() : "+friendApproveAdmList.get(i).getF_name()+"님의 정보를 입력중 - "
//									+"사진이름은 : " + friendApproveAdmList.get(i).getF_imgName());	
//						}
//					}
//						friendApproveAdmList.get(i).setCheck(0);
//						for(int j=0 ; j<tabAdmList.size() ; j++) {
//							if(friendApproveAdmList.get(i).getMyId() == tabAdmList.get(j).getFt_userId()) {
//								//System.out.println("여기오고있니?");
//								friendApproveAdmList.get(i).setCheck(1);
//							}
//						}
//					}
//				result.setFriendApproveAdmList(friendApproveAdmList);
//				
//				return result;	
//			}
			
			public static List<Fn247> getWaitAdmListClassification (List<Fn247> waitAdmList, List<Fn247> tabAdmList, Member friInfo ){
				List<Fn247> result = new ArrayList<Fn247>();
				
				for(int i=0 ; i<waitAdmList.size(); i++){
					for(int k=0 ; k<friInfo.getFriendInformationList().size(); k++){
						if(waitAdmList.get(i).getfUserId() == friInfo.getFriendInformationList().get(k).getUserId()) {
							waitAdmList.get(i).setF_name(friInfo.getFriendInformationList().get(k).getNickName());
							waitAdmList.get(i).setF_imgName(friInfo.getFriendInformationList().get(k).getUserImg());
						}
						
					}
//					Member ui = getMember(waitAdmList.get(i).getfUserId());
//					waitAdmList.get(i).setF_name(ui.getNickName());
//					//System.out.println("무슨이름 3"+waitAdmList.get(i).getF_name());
//					waitAdmList.get(i).setF_imgName(ui.getUserImg());
				}
				result = waitAdmList;

				return result;	
			}
			
			public static List<Fn247> getFriendAdmList (List<Fn247> tabAdmList, Member friInfo) {
				
				System.out.println("====================================getFriendAdmList실행중");
				
				List<Fn247> result = new ArrayList<Fn247>();
				List<Fn247> friendAdmList = new ArrayList<Fn247>();
				
				for (int i=0; i<tabAdmList.size(); i++) {
					Fn247 p1 = new Fn247();
					for(int k=0 ; k<friInfo.getFriendInformationList().size(); k++){
						if(tabAdmList.get(i).getFt_userId() == friInfo.getFriendInformationList().get(k).getUserId()) {
							p1.setF_imgName(friInfo.getFriendInformationList().get(k).getUserImg());
							p1.setF_name(friInfo.getFriendInformationList().get(k).getNickName());
							System.out.println("친구목록에 있네요 그래서 공유탭에 보여줄"+p1.getF_name());
							p1.setEmail(friInfo.getFriendInformationList().get(k).getId());
							p1.setfUserId(friInfo.getFriendInformationList().get(k).getUserId());
						}else {
							Member ui = getMember(tabAdmList.get(i).getFt_userId());
							p1.setF_imgName(ui.getUserImg());
							p1.setF_name(ui.getNickName());
							System.out.println("친구목록에 없네 그래서 공유탭에 보여줄"+p1.getF_name());
							p1.setEmail(ui.getId());
							p1.setfUserId(ui.getUserId());
						}
					}
					p1.setIdN247_ft(tabAdmList.get(i).getIdN247_ft());
					friendAdmList.add(p1);
				}
				result.addAll(friendAdmList);
				
				return result;
			}

			public static List<Fn247> readFriListAction(Integer userNum, Integer adm, Integer tabId ) {
			
				List<Fn247> result = new ArrayList<Fn247>();
				String resource = "org/first/mvc/mybatis_config.xml";
				InputStream inputStream;
				Fn247 p1 = new Fn247();
				p1.setMyId(userNum);
				p1.setAdm(adm);
					try {
						inputStream = Resources.getResourceAsStream(resource);
						SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
						SqlSession session = sqlSessionFactory.openSession();
						result = session.selectList("org.first.mvc.BaseMapper.getFriList", p1);
				        for(int i=0 ; i<result.size() ; i++) {
				        	result.get(i).setUtf8create(dateChangeAction(result.get(i).getCreate()));
				        	result.get(i).setF_name(getNickNameToUserNum(result.get(i).getfUserId()));
				        	result.get(i).setEmail(getIdToUserNum(result.get(i).getfUserId()));
				        	result.get(i).setCheck(getCheckFriId(result.get(i).getfUserId(),tabId));
					 	}
					} catch (IOException e) {
						e.printStackTrace();
					}
				
				return result ;
			}

			public static List<Fn247> readReFriListAction(Integer userNum ) {
			
				List<Fn247> result = new ArrayList<Fn247>();
				String resource = "org/first/mvc/mybatis_config.xml";
				InputStream inputStream;
				Fn247 p1 = new Fn247();
				p1.setMyId(userNum);
				try {
					inputStream = Resources.getResourceAsStream(resource);
					SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
					SqlSession session = sqlSessionFactory.openSession();
					result = session.selectList("org.first.mvc.BaseMapper.getReFriList", p1);
			        for(int i=0 ; i<result.size() ; i++) {
			        	result.get(i).setUtf8create(dateChangeAction(result.get(i).getCreate()));
			        	result.get(i).setF_name(getNickNameToUserNum(result.get(i).getMyId()));
			        	result.get(i).setEmail(getIdToUserNum(result.get(i).getfUserId()));
				 	}
				} catch (IOException e) {
					e.printStackTrace();
				}
				
				return result ;
			}
			
			
			public static Integer getCountFtCheck(Integer tabId) {
				String resource = "org/first/mvc/mybatis_config.xml";
				InputStream inputStream;
				
				Integer result = 0;
				try {
					inputStream = Resources.getResourceAsStream(resource);
					SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
					SqlSession session = sqlSessionFactory.openSession();
					result = session.selectOne("org.first.mvc.BaseMapper.countFt", tabId);
		
				} catch (IOException e) {
					e.printStackTrace();
				}
				
				return result ;
			}
			
			
			public static Integer getCountPostCheck(Integer tabId) {
				String resource = "org/first/mvc/mybatis_config.xml";
				InputStream inputStream;
				
				Integer result = 0;
				try {
					inputStream = Resources.getResourceAsStream(resource);
					SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
					SqlSession session = sqlSessionFactory.openSession();
					result = session.selectOne("org.first.mvc.BaseMapper.countIdCheck", tabId);
		
				} catch (IOException e) {
					e.printStackTrace();
				}
				
				return result ;
			}
			
			
			public static Integer getPostId (Integer tabId) {
				String resource = "org/first/mvc/mybatis_config.xml";
				InputStream inputStream;
				List<Post> p1 = new ArrayList<Post>();
				
				Integer result = 0;
				try {
					inputStream = Resources.getResourceAsStream(resource);
					SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
					SqlSession session = sqlSessionFactory.openSession();
					p1 = session.selectList("org.first.mvc.BaseMapper.getPostId", tabId);
					result = p1.get(0).getId();
		
				} catch (IOException e) {
					e.printStackTrace();
				}
				
				return result ;
			}
			
			public static List<Post> getImgNameToPostId (Integer up_postId) {
				String resource = "org/first/mvc/mybatis_config.xml";
				InputStream inputStream;
				List<Post> result = new ArrayList<Post>();
				////System.out.println("파일발견해서 포스트에 넣는중 포스트번호 : " + up_postId);
				try {
					inputStream = Resources.getResourceAsStream(resource);
					SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
					SqlSession session = sqlSessionFactory.openSession();
					result = session.selectList("org.first.mvc.BaseMapper.getUserImgToPost", up_postId);
					
				} catch (IOException e) {
					e.printStackTrace();
				}
				
				return result ;
			 }
			
			 public static Integer getCounFilePost (Integer up_postId) {
				
				String resource = "org/first/mvc/mybatis_config.xml";
				InputStream inputStream;
				
				Integer result = 0;
				Integer count = 0;
				try {
					inputStream = Resources.getResourceAsStream(resource);
					SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
					SqlSession session = sqlSessionFactory.openSession();
					count = session.selectOne("org.first.mvc.BaseMapper.getPostFileCheck", up_postId);
					if(count == 0) {
						result = 0 ;
					}else {
						result = 1 ;
					}
		
				} catch (IOException e) {
					e.printStackTrace();
				}
				
				return result ;
			}
			//getCountTab 파라미터 userNum 리턴 탭 접근시 1,2,3 을 리턴해준다. 
			public static Integer getCountTab(Integer userNum) {
				
				String resource = "org/first/mvc/mybatis_config.xml";
				InputStream inputStream;
				Integer result = 0;
				try {
					inputStream = Resources.getResourceAsStream(resource);
					SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
					SqlSession session = sqlSessionFactory.openSession();
					int count = session.selectOne("org.first.mvc.BaseMapper.countTab", userNum);
					int count2 = session.selectOne("org.first.mvc.BaseMapper.countFriTab", userNum);
					// 1번은 지꺼랑 공유된탭이 있다
					if(count != 0 && count2 == 0) {
						result = 1 ;
					}
					if(count != 0 && count2 != 0) {
						result = 1 ;
					}
					// 2번은 지껀없고 공유된탭이 있다
					if(count == 0 && count2 != 0) {
						result = 2 ;
					}
					
				} catch (IOException e) {
					e.printStackTrace();
				}
				return result ;
			}
			
			public static int getCountTab2(Integer userNum) {
				
				String resource = "org/first/mvc/mybatis_config.xml";
				InputStream inputStream;
				
				int result = 0;
				
				try {
					inputStream = Resources.getResourceAsStream(resource);
					SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
					SqlSession session = sqlSessionFactory.openSession();
					result = session.selectOne("org.first.mvc.BaseMapper.countTab", userNum);
		
				} catch (IOException e) {
					e.printStackTrace();
				}
				
				return result ;
			}
			public static Integer searchFriend (String search ) {
				Integer result = 0;
				String resource = "org/first/mvc/mybatis_config.xml";
				InputStream inputStream;
				try {
					inputStream = Resources.getResourceAsStream(resource);
					SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
					SqlSession session = sqlSessionFactory.openSession();
					result = session.selectOne("org.first.mvc.BaseMapper.countId", search);
				} catch (IOException e) {
					e.printStackTrace();
				}
				
				return result;
			}
	
			public static Integer searchFriend2 (String search, Integer userNum ) {
				Integer result = 0;
				String resource = "org/first/mvc/mybatis_config.xml";
				InputStream inputStream;
				try {
					inputStream = Resources.getResourceAsStream(resource);
					SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
					SqlSession session = sqlSessionFactory.openSession();
					Fn247 friId = new Fn247();
					friId.setfUserId(getUserNum(search));
					friId.setMyId(userNum);
					result = session.selectOne("org.first.mvc.BaseMapper.countfUserId", friId);
				} catch (IOException e) {
					e.printStackTrace();
				}
				return result;
			}
			// 유저 아이디 받아서 삭제되지 않은 탭목록을 반환한다.
			public static List<Post> readTabListAction(Integer userNum) {
				
				List<Post> result = new ArrayList<Post>();
				String resource = "org/first/mvc/mybatis_config.xml";
				InputStream inputStream;
				try {
					inputStream = Resources.getResourceAsStream(resource);
					SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
					SqlSession session = sqlSessionFactory.openSession();
					result = session.selectList("org.first.mvc.BaseMapper.getTabList", userNum);
					System.out.println("DAO.readTabListAction 가동중 : Integer userNum"+ userNum +"받아서 list<Post> 반환 "+result.size() );
				} catch (IOException e) {
					e.printStackTrace();
				}
				
				return result ;
			}
	
	
			public static List<Post> readFriTabListAction(Integer userNum) {
				
				List<Post> result = new ArrayList<Post>();
				List<Post> tabList = new ArrayList<Post>();
				List<Fn247> tabIds = new ArrayList<Fn247>();
				Post tabId = new Post();
				Fn247 p1 = new Fn247();
				p1.setFt_userId(userNum);
				String resource = "org/first/mvc/mybatis_config.xml";
				InputStream inputStream;
				try {
					inputStream = Resources.getResourceAsStream(resource);
					SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
					SqlSession session = sqlSessionFactory.openSession();
					tabIds = session.selectList("org.first.mvc.BaseMapper.getAdmFriTabId", p1);
					for(int i=0 ; i<tabIds.size(); i++) {
						tabId.setTabId(tabIds.get(i).getFt_tabId());
						tabList = session.selectList("org.first.mvc.BaseMapper.getAdmFriTabList", tabId);
						result.addAll(tabList);
						////System.out.println(tabIds.get(i).getFt_tabId() + "받아오는중");
					}
//					for(int i=0 ; i<result.size(); i++) {
//						for(int j=0 ; j<friInfo.getFriendInformationList().size(); j++) {
//							if(result.get(i).getUserNum() == friInfo.getFriendInformationList().get(j).getUserId()) {
//								
//								result.get(i).setImgName(friInfo.getFriendInformationList().get(j).getUserImg());
//								System.out.println(result.get(i).getUserNum()+" 번 사용자 와 "+friInfo.getFriendInformationList().get(j).getUserId()
//										+"번 사용자가 같아서 이미지 이름 : "+result.get(i).getImgName() +" 를 입력합니다. ");
//							}else {
//								System.out.println("%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%% 여기 문제임 " + result.get(i).getUserNum() + "이게 누구냐 친구목록에 없네 ");
//							}
//						}
//					}
				} catch (IOException e) {
					e.printStackTrace();
				}
				return result ;
			}
			
			public static Member sideBarAdmTabimgSet (Member userInfo, Member friInfo){
				Member result = new Member();
				result = userInfo;
				List<Post> p1 = new ArrayList<Post>();
				p1 = userInfo.getFriTabList();
				for(int i=0 ; i<p1.size(); i++) {
					for(int j=0 ; j<friInfo.getFriendInformationList().size() ; j++) {
						if(p1.get(i).getUserNum() == friInfo.getFriendInformationList().get(j).getUserId()) {
							p1.get(i).setImgName(friInfo.getFriendInformationList().get(j).getUserImg());
						}else {
							System.out.println("%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%% 여기 문제임 " + p1.get(i).getUserNum() + "이게 누구냐 친구목록에 없네 ");
						}
					}
				}
				result.setFriTabList(p1);
				
				
				return result;
			}
			
			public static String readTabTitle(Integer tabId) {
				String result = "";
				String resource = "org/first/mvc/mybatis_config.xml";
				InputStream inputStream;
				try {
					inputStream = Resources.getResourceAsStream(resource);
					SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
					SqlSession session = sqlSessionFactory.openSession();
					result = session.selectOne("org.first.mvc.BaseMapper.getTabTitleToTabId", tabId);
				} catch (IOException e) {
					e.printStackTrace();
				}
				return result ;
			}
	
			public static List<Post> readTabAction(Integer tabId) {
				
				List<Post> result = new ArrayList<Post>();
				String resource = "org/first/mvc/mybatis_config.xml";
				InputStream inputStream;
				try {
					inputStream = Resources.getResourceAsStream(resource);
					SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
					SqlSession session = sqlSessionFactory.openSession();
					result = session.selectList("org.first.mvc.BaseMapper.getTab", tabId);
				} catch (IOException e) {
					e.printStackTrace();
				}
				
				return result ;
			}
			
			public static String readTabLastUpdateAction(Integer tabId) {
				
				String result = "";
				Post tabLastUpdate = new Post();
				
				String resource = "org/first/mvc/mybatis_config.xml";
				InputStream inputStream;
				try {
					inputStream = Resources.getResourceAsStream(resource);
					SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
					SqlSession session = sqlSessionFactory.openSession();
					tabLastUpdate = session.selectOne("org.first.mvc.BaseMapper.getTab", tabId);
					result = dateChangeAction(tabLastUpdate.getLastUpdate());
				} catch (IOException e) {
					e.printStackTrace();
				}
				
				return result ;
			}

			public static String getPostLastUpdateAction(Integer tabId) {
				
				String result = "";
				Post lastUpdate = new Post();
				
				String resource = "org/first/mvc/mybatis_config.xml";
				InputStream inputStream;
				try {
					inputStream = Resources.getResourceAsStream(resource);
					SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
					SqlSession session = sqlSessionFactory.openSession();
					lastUpdate = session.selectOne("org.first.mvc.BaseMapper.getPostLastUpdate", tabId);
					result = dateChangeAction(lastUpdate.getLastUpdate());
				} catch (IOException e) {
					e.printStackTrace();
				}
				
				return result ;
			}
		    public static List<Post> getTabIdAction(String tabTitle) {
				
				List<Post> result = new ArrayList<Post>();
				String resource = "org/first/mvc/mybatis_config.xml";
				InputStream inputStream;
				try {
					inputStream = Resources.getResourceAsStream(resource);
					SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
					SqlSession session = sqlSessionFactory.openSession();
					result = session.selectList("org.first.mvc.BaseMapper.getTabId", tabTitle);
				} catch (IOException e) {
					e.printStackTrace();
				}
				return result ;
			}
    
	        public static Integer getLastUseTabId(String arg) {
				Integer result = 0;
				Post p1 = new Post();
				p1.setNick(arg);
				String pt1 = "계정관리자";
				List<Post> p2= new ArrayList<Post>();
				String resource = "org/first/mvc/mybatis_config.xml";
				InputStream inputStream;
				try {
					inputStream = Resources.getResourceAsStream(resource);
					SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
					SqlSession session = sqlSessionFactory.openSession();
					p2 = session.selectList("org.first.mvc.BaseMapper.getLastUseTabId", pt1);
					result = p2.get(0).getTabId();
				} catch (IOException e) {
					e.printStackTrace();
				}
				return result ;
		    }
        
	        public static String getNckToTabId(Integer tabId) {
	    		String result = "";
	    		Post p1 = new Post();
	    		p1.setTabId(tabId);
	    		String resource = "org/first/mvc/mybatis_config.xml";
	    		InputStream inputStream;
	    		try {
	    			inputStream = Resources.getResourceAsStream(resource);
	    			SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
	    			SqlSession session = sqlSessionFactory.openSession();
	    			result = session.selectOne("org.first.mvc.BaseMapper.nicktotabId", p1);
	    		} catch (IOException e) {
	    			e.printStackTrace();
	    		}
	    		return result ;
	    	} 
        
	        public static String getMemberNick(String arg) {
	    		String result = "";
	    		Member p1 = new Member();
	    		p1.setId(arg);
	    		String resource = "org/first/mvc/mybatis_config.xml";
	    		InputStream inputStream;
	    		try {
	    			inputStream = Resources.getResourceAsStream(resource);
	    			SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
	    			SqlSession session = sqlSessionFactory.openSession();
	    			result = session.selectOne("org.first.mvc.BaseMapper.getMemberNick", p1);
	    			
	    		} catch (IOException e) {
	    			e.printStackTrace();
	    		}
	    		
	    		return result ;
	    	}
        
        
	        public static Integer getUserNum(String arg) {
	    		Integer result = 0;
	    		Member p1 = new Member();
	    		p1.setId(arg);
	    		String resource = "org/first/mvc/mybatis_config.xml";
	    		InputStream inputStream;
	    		try {
	    			inputStream = Resources.getResourceAsStream(resource);
	    			SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
	    			SqlSession session = sqlSessionFactory.openSession();
	    			result = session.selectOne("org.first.mvc.BaseMapper.getUserNum", p1);
	    		} catch (IOException e) {
	    			e.printStackTrace();
	    		}
	    		return result ;
	    	}
	        public static String getNickToTabId(Integer arg) {
	    		String result = "";
	    		Post p1 = new Post();
	    		p1.setTabId(arg);
	    		String resource = "org/first/mvc/mybatis_config.xml";
	    		InputStream inputStream;
	    		try {
	    			inputStream = Resources.getResourceAsStream(resource);
	    			SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
	    			SqlSession session = sqlSessionFactory.openSession();
	    			result = session.selectOne("org.first.mvc.BaseMapper.getNickToTabId", p1);
	    		} catch (IOException e) {
	    			e.printStackTrace();
	    		}
	    		return result ;
	        }
        
        
	        public static String getNickNameToUserNum(Integer arg) {
	    		String result = "";
	    		Post p1 = new Post();
	    		p1.setUserNum(arg);
	    		String resource = "org/first/mvc/mybatis_config.xml";
	    		InputStream inputStream;
	    		try {
	    			inputStream = Resources.getResourceAsStream(resource);
	    			SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
	    			SqlSession session = sqlSessionFactory.openSession();
	    			result = session.selectOne("org.first.mvc.BaseMapper.getNickNameToUserNum", p1);
	    		} catch (IOException e) {
	    			e.printStackTrace();
	    		}
	    		
	    		return result ;
	        }
       
        
	        public static String getIdToUserNum(Integer arg) {
	    		String result = "";
	    		Post p1 = new Post();
	    		p1.setUserNum(arg);
	    		String resource = "org/first/mvc/mybatis_config.xml";
	    		InputStream inputStream;
	    		try {
	    			inputStream = Resources.getResourceAsStream(resource);
	    			SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
	    			SqlSession session = sqlSessionFactory.openSession();
	    			result = session.selectOne("org.first.mvc.BaseMapper.getIdToUserNum", p1);
	    			
	    		} catch (IOException e) {
	    			e.printStackTrace();
	    		}
	    		return result ;
	        }
        
        
	        public static Integer getUserNumToTabId(Integer arg) {
	    		Integer result = 0;
	    		Post p1 = new Post();
	    		p1.setTabId(arg);
	    		String resource = "org/first/mvc/mybatis_config.xml";
	    		InputStream inputStream;
	    		try {
	    			inputStream = Resources.getResourceAsStream(resource);
	    			SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
	    			SqlSession session = sqlSessionFactory.openSession();
	    			result = session.selectOne("org.first.mvc.BaseMapper.getUserNumToTabId", p1);
	    		} catch (IOException e) {
	    			e.printStackTrace();
	    		}
	    		return result ;
	        }
	        
	        //탭번호 tabId 주면 공유기록 배열로 담아온다.
	        public static List<Fn247> getTabAdmList (Integer tabId){
	        	List<Fn247> result = new ArrayList<Fn247>();
	        	String resource = "org/first/mvc/mybatis_config.xml";
	    		InputStream inputStream;
	        	//getFriAdmTabId
	    		try {
	    			inputStream = Resources.getResourceAsStream(resource);
	    			SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
	    			SqlSession session = sqlSessionFactory.openSession();
	    			result = session.selectList("org.first.mvc.BaseMapper.getFriAdmTabId", tabId);
	    			
	    		} catch (IOException e) {
	    			e.printStackTrace();
	    		}
	        	return result;
	        }
	        
	        
	        public static Integer getCheckFriId(Integer userNum, Integer tabId) {
	    		Integer result = 0;
	    		Fn247 p1 = new Fn247();
	    		p1.setFt_userId(userNum);
	    		p1.setFt_tabId(tabId);
	    		String resource = "org/first/mvc/mybatis_config.xml";
	    		InputStream inputStream;
	    		try {
	    			inputStream = Resources.getResourceAsStream(resource);
	    			SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
	    			SqlSession session = sqlSessionFactory.openSession();
	    			result = session.selectOne("org.first.mvc.BaseMapper.countFriId", p1);
	    		} catch (IOException e) {
	    			e.printStackTrace();
	    		}
	    		return result ;
	        }
	        public static Post getSecondTabIdAction(Integer userNum) {
			
				List<Post> SecondTab = new ArrayList<Post>();
				Post result = new Post();
				
				String resource = "org/first/mvc/mybatis_config.xml";
				InputStream inputStream;
			
				try {
					inputStream = Resources.getResourceAsStream(resource);
					SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
					SqlSession session = sqlSessionFactory.openSession();
					SecondTab = session.selectList("org.first.mvc.BaseMapper.getSecondTabId", userNum);
					result.setTabId(SecondTab.get(0).getTabId());
				} catch (IOException e) {
					e.printStackTrace();
				}
				
				return result ;
	        }
	
	        public static Integer check(String check) {
	    		
				Integer result = 0;
				
				String resource = "org/first/mvc/mybatis_config.xml";
				InputStream inputStream;
			
				try {
					inputStream = Resources.getResourceAsStream(resource);
					SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
					SqlSession session = sqlSessionFactory.openSession();
					result = session.selectOne("org.first.mvc.BaseMapper.memberCheck", check);
				} catch (IOException e) {
					e.printStackTrace();
				}
				
				return result ;
	        }
	
			
			public static void tabTitleUpdate (Integer tabId, String tabTitle ) {
				String resource = "org/first/mvc/mybatis_config.xml";
				InputStream inputStream;
				Post tab = new Post();
				tab.setTabId(tabId);
				tab.setTabTitle(tabTitle);
				try {
					inputStream = Resources.getResourceAsStream(resource);
					SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
					SqlSession session = sqlSessionFactory.openSession();
					session.update("org.first.mvc.BaseMapper.updateTabTitle", tab);
					session.commit();
					session.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
	
			
			
			 public static Integer checkLoginMember (String id, String password) {
				 
				String resource = "org/first/mvc/mybatis_config.xml";
				InputStream inputStream;
				Integer result = 0;
				Member idPsw = new Member();
				idPsw.setId(id);
				idPsw.setPassword(password);
					
				try {	
					inputStream = Resources.getResourceAsStream(resource);
					SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
					SqlSession session = sqlSessionFactory.openSession();
						
					result = session.selectOne("org.first.mvc.BaseMapper.countIdPsw", idPsw);
				} catch (IOException e) {
					e.printStackTrace();
				
				}
				 return result ;
			 }
			 public static String getUserIntroduce(Integer userId) {	
					
					String resource = "org/first/mvc/mybatis_config.xml";
					InputStream inputStream;
					
					String result = "";
					
					try {
						inputStream = Resources.getResourceAsStream(resource);
						SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
						SqlSession session = sqlSessionFactory.openSession();
						result = session.selectOne("org.first.mvc.BaseMapper.getUserIntroduce", userId);

					} catch (IOException e) {
						e.printStackTrace();
					}
					
					return result ;
			  }
			 public static List<Post> getReplyListToTabId (Integer tabId){
				 List<Post> result = new ArrayList<Post>();
				 String resource = "org/first/mvc/mybatis_config.xml";
				 InputStream inputStream;
				 try {
					 inputStream = Resources.getResourceAsStream(resource);
					 SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
					 SqlSession session = sqlSessionFactory.openSession();
					 result = session.selectList("org.first.mvc.BaseMapper.getReplyListToTabId", tabId);
				 } catch (IOException e) {
						e.printStackTrace();
				 }
				 return result;
			 }
			 
			 public static List<Upload> getPostFileNameListToTabId (Integer tabId){
				 List<Upload> result = new ArrayList<Upload>();
				 String resource = "org/first/mvc/mybatis_config.xml";
				 InputStream inputStream;
				 try {
					 inputStream = Resources.getResourceAsStream(resource);
					 SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
					 SqlSession session = sqlSessionFactory.openSession();
					 result = session.selectList("org.first.mvc.BaseMapper.getPostFileToTabId", tabId);
					 System.out.println("getPostFileNameListToTabId실행하고만들어진 파일네임리스트 "+result.size());
				 } catch (IOException e) {
						e.printStackTrace();
				 }
				 return result;
			 }
			 
			 public static List<Post> replyClassification (Integer id, List<Post> replyList){
				 List<Post> result = new ArrayList<Post>();
				 List<Post> p1 = new ArrayList<Post>();
				 p1.addAll(replyList);
				 for (int i=0 ; i<p1.size() ; i++) {
					 if(p1.get(i).getN247_rePoId() == id) {
						 result.add(p1.get(i));
					 }
				 }
				 return result;
			 }
			 
			 public static List<Upload> postFileClassification (Integer id, List<Upload> postFile){
				 List<Upload> result = new ArrayList<Upload>();
				 List<Upload> p1 = new ArrayList<Upload>();
				 p1.addAll(postFile);
				 for (int i=0 ; i<p1.size() ; i++) {
					 if(p1.get(i).getUp_postId() == id) {
						 result.add(p1.get(i));
					 }
				 }
				 return result;
			 }
			 
			 public static List<Post> postFileClassification2 (Integer id, List<Upload> postFile){
				 List<Post> result = new ArrayList<Post>();
				 Post p2 = new Post();
				 List<Upload> p1 = new ArrayList<Upload>();
				 p1.addAll(postFile);
				 for (int i=0 ; i<p1.size() ; i++) {
					 if(p1.get(i).getUp_postId() == id) {
						 p2.setUp_fileName(p1.get(i).getUp_fileName());
						 System.out.println("p2.setUp_fileName(p1.get(i).getUp_fileName())"+p2.getUp_fileName());
						 result.add(p2);
					 }
				 }
				 System.out.println("result.size()"+result.size());
				 if(result.size() != 0) {
					 System.out.println("result.get(0).getUp_fileName()"+result.get(0).getUp_fileName());
				 }
				 
				 		
				 return result;
			 }
			 
			 public static Post readPostListAction(Integer tabId, Member userInfo) {
				 
				 	System.out.println("실행할게요 가져오라고 해서 포스트 ");
				 	Post result = new Post();
					List<Post> postList = new ArrayList<Post>();
					List<Post> completePostList = new ArrayList<Post>();
					List<Post> allList = new ArrayList<Post>();
					//프로젝트안에 있는 댓글을 가져와서 배열에 넣어준다.
					List<Post> replyList = new ArrayList<Post>();
					replyList.addAll(getReplyListToTabId(tabId));
					
					
					System.out.println("replyList "+replyList.size()+"개 받아왔어요 ");
					List<Upload> postFile = new ArrayList<Upload>();
					postFile.addAll(getPostFileNameListToTabId(tabId));
					System.out.println("postFile "+postFile.size()+"개 받아왔어요 ");
					String resource = "org/first/mvc/mybatis_config.xml";
					InputStream inputStream;
					Post p1 = new Post();
					p1.setTabId(tabId);
					
					Date date_now = new Date(System.currentTimeMillis()); 
					
					//모든 리스트에 각종 정보를 세팅해준다 .
					try {
						inputStream = Resources.getResourceAsStream(resource);
						SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
						SqlSession session = sqlSessionFactory.openSession();
						allList = session.selectList("org.first.mvc.BaseMapper.getPostList", p1);
			            System.out.println("가져온 포스트들의 갯수는 : (DAO) " + allList.size());
						//가져온 포스트에 각종정보를 세팅해준다.
			            for(int i=0 ; i<allList.size() ; i++) {
			            	//작성자 사진 입력 
			            	allList.get(i).setImgName(userInfo.getUserImg());
			            	//진행률 입력
			            	allList.get(i).setProgress(progress(allList.get(i).getCreate(),allList.get(i).getDueDay()));
			            	//진행률 정도
			            	allList.get(i).setProgressBg(progressBg(progress(allList.get(i).getCreate(),allList.get(i).getDueDay())));
			            	//파일이 일단은 없다로 판단 
			            	allList.get(i).setCheck(0);
			            	//포스트에 달린 댓글정보 입력 반복 
			            	if( replyList != null) { 
			            		allList.get(i).setReplyList(postSetReplyList(replyList,allList.get(i).getId()));
			            		System.out.println("실행은 했냐 이거?");
			            	}
			            	//포스트에 파일정보를 찾아서 입력 
		            		for (int j=0 ; j<postFile.size() ; j++) {
		            			  //파일정보와 포스트번호를 비교해서 맞으면 트루 
					        	  if(allList.get(i).getId() == postFile.get(j).getUp_postId()) {
					        		  //파일명을 입력 
					        		  allList.get(i).setUp_fileName(postFile.get(j).getUp_fileName());
					        		  //파일이 있다는 판단 
					        		  allList.get(i).setCheck(1);
					        		  System.out.println(i+"번째 allList.get(i).getUp_fileName" + allList.get(i).getUp_fileName());
					        	 } 
					        }
		            		//포스트에 마지막 업데이트가 없을 경우 트루 
			            	if(allList.get(i).getLastUpdate() == null) {
			            		//트루 만든시간을 생성일로 넣어준다. 
			            		allList.get(i).setTime(dateChangeAction(allList.get(i).getCreate()));	
			            		//생성된지 얼마되었는지 입력 
			            		allList.get(i).setCompareTime(calDateBetweenAndB(dateChangeAction2(allList.get(i).getCreate()),date_now));
			            	}else {
			            		//폴스 마지막 수정시간으로 시간을 입력 
			            		allList.get(i).setTime(dateChangeAction(allList.get(i).getLastUpdate()));
			            		//생성된지 얼마되었는지 입력 
			            		allList.get(i).setCompareTime(calDateBetweenAndB(dateChangeAction2(allList.get(i).getLastUpdate()),date_now));
			            		//생성된지 얼마되었다는 메세지를 입력 
			            		allList.get(i).setCompareMessage(calCardDueDate(dateChangeAction2(allList.get(i).getLastUpdate()),date_now));
			            	}	
			            }
			          //진행중과 완료를 나눠서 보낸다. 완료는 getIsDel = 3
			          for(int i=0 ; i<allList.size() ; i++) {
			        	  if(allList.get(i).getIsDel() == 0) {
			        		  postList.add(allList.get(i));
//			        		  if(postList.get(i).getReplyList() != null) {
//			        			  System.out.println(postList.get(i).getId()+"번 포스트에 받아온 리스트는 :  " + postList.get(i).getReplyList().size() + "개 입니다.");
//			        		  }
			        	  }else {
			        		  completePostList.add(allList.get(i));  
			        	  }
			          }
			          result.setPostList(postList);
			          result.setCompletePostList(completePostList);

					} catch (IOException e) {
						e.printStackTrace();
					}
					
					return result ;
				}
			 
			 //탭에 받아온 탭관련 리플라이리스트 중 카드번호 찾아서 포스트리스트에 넣어준다. 
			 public static List<Post> postSetReplyList (List<Post> replyList, Integer id) {
				 
				 System.out.println("이거 하긴 하냐postSetReplyList 받아온 리스트는 " + replyList.size() +"개 입니다. "
				 		+ "그리고 받아온 아이디는 " + id);
				 List<Post> p1 = new ArrayList<Post>();
				 List<Post> p2 = new ArrayList<Post>();
				 List<Post> result = new ArrayList<Post>();
				 p1.addAll(replyList);
				 
				 for (int i=0 ; i<p1.size(); i++) {
					 if(id == p1.get(i).getN247_rePoId()) {
						 System.out.println(id + ": 포스트 번호와  == 댓글의 포스트번호 :" + p1.get(i).getN247_rePoId() +"가 맞네요  ");
						 
						 p2.add(p1.get(i));
					 }
				 }
				 result.addAll(p2);
				 System.out.println("result 에 받아왔는데 : " + result.size() +"개 입니다. ");
					 		
				 return result;
			 }
			//카드에 댓글달고 댓글 쓴사람들 정보 주기
			 public static List<Post> replyListSetUserInfo (List<Post> postList, Integer userId, Member friInfo, String nick){
				 List<Post> result = new ArrayList<Post>();
				 List<Post> p1 = new ArrayList<Post>();
				 p1.addAll(postList);
				 if(p1 != null) {
					System.out.println("받아온 포스트리스트 사이즈는 : "+p1.size());
				 }
				 
				 for(int i=0 ; i<p1.size(); i++) {
		        	  if(p1.get(i).getReplyList() != null) {
		        		  System.out.println("댓글몇개냐 getPostList():" + p1.get(i).getReplyList().size());
			        		  for(int j=0 ; j<p1.get(i).getReplyList().size(); j++) {
				        		  if(p1.get(i).getReplyList().size() != 0) {
				        			  System.out.println(i+"번째 댓글 뭐 가져가냐 getIdN247_re(): " + p1.get(i).getReplyList().get(j).getIdN247_re()
				     						 +"/getN247_rePoId(): " + p1.get(i).getReplyList().get(j).getN247_rePoId()
				     						 +"/getN247_reDes(): " + p1.get(i).getReplyList().get(j).getN247_reDes()
				     						 +"/getN247_reUsId(): " + p1.get(i).getReplyList().get(j).getN247_reUsId()
				     						 +"/getCreate(): " + p1.get(i).getReplyList().get(j).getCreate()
				     						 +"/getLastModified(): " + p1.get(i).getReplyList().get(j).getLastModified()
				     						 +"/getN247_reIsDel(): " + p1.get(i).getReplyList().get(j).getN247_reIsDel()
				     						 +"/getN247_reTabId(): " + p1.get(i).getReplyList().get(j).getN247_reTabId());
				        			  //받아온 리스트의 유저아이디를 분석해 내아이와 맞으면 내정보넣어준다.
				        			  if(p1.get(i).getReplyList().get(j).getN247_reUsId() == userId) {
				        				  p1.get(i).getReplyList().get(j).setNick(nick);
					        			  System.out.println(")))))))))))))) getNick : "+p1.get(i).getReplyList().get(j).getNick());
					        			//받아온 리스트의 유저아이디를 분석해 내아이와 맞지않으면 친구정보중 맞는걸 넣어준다.
				        			  }else {
				        				  p1.get(i).getReplyList().get(j).setNick(DAO.cardReSetName(p1.get(i).getReplyList().get(j).getN247_reUsId(),friInfo));
					        			  System.out.println(")))))))))))))) getNick : "+p1.get(i).getReplyList().get(j).getNick()); 
				        			  }
				        			  
				        			  p1.get(i).getReplyList().get(j).setKrCreate(DAO.dateChangeAction(p1.get(i).getReplyList().get(j).getLastModified()));
				        	  } 
			        	  }
		        	  }
 
	        	  }
				 result.addAll(p1);
				 
				 
				 return result;
			 }
			 
			 
			 public static List<Post> readReplyList(Integer idN247_re ) {
					List<Post> result = new ArrayList<Post>();
					String resource = "org/first/mvc/mybatis_config.xml";
					InputStream inputStream;
					Post p1 = new Post();
					p1.setN247_rePoId(idN247_re);
					try {
						inputStream = Resources.getResourceAsStream(resource);
						SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
						SqlSession session = sqlSessionFactory.openSession();
						result = session.selectList("org.first.mvc.BaseMapper.getReplyList", p1);
				        for(int i=0 ; i<result.size() ; i++) {
				        	result.get(i).setKrCreate(dateChangeAction(result.get(i).getLastModified()));
				        	result.get(i).setNick(getNickNameToUserNum(result.get(i).getN247_reUsId()));
					 	}  
					} catch (IOException e) {
						e.printStackTrace();
					}
					
					return result ;
				}
			 
			 	public static List<Post> getFriImgList (Integer n247_rePoId ){
				 	List<Post> result = new ArrayList<Post>();
				 	List<Post> reUser = new ArrayList<Post>();
				 	String resource = "org/first/mvc/mybatis_config.xml";
					InputStream inputStream;
					Post p1 = new Post();
					p1.setN247_rePoId(n247_rePoId);
					//System.out.println("얼굴뽑아낼 아이디 가져왔어 : " + n247_rePoId);
					try {
						inputStream = Resources.getResourceAsStream(resource);
						SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
						SqlSession session = sqlSessionFactory.openSession();
						reUser = session.selectList("org.first.mvc.BaseMapper.getFriImgList", p1);
						//System.out.println("애기들 번호 가져왔 :" + reUser.get(0).getN247_reUsId());
						for(int i=0 ; i<reUser.size() ; i++) {
					        result.get(i).setImgName(getUserImg(reUser.get(i).getN247_reUsId()));
					        //System.out.println("애기 얼굴 가져왔어 :" + result.get(i).getImgName());	
						}
					} catch (IOException e) {
						e.printStackTrace();
					}
				 	return result;
			 	}

			 
			 	public static Integer getCountReId(Integer id) {
					
					String resource = "org/first/mvc/mybatis_config.xml";
					InputStream inputStream;
					
					Integer result = 0;
					Integer count = 0;
					
					Post p1 = new Post();
					p1.setN247_rePoId(id);
					try {
						inputStream = Resources.getResourceAsStream(resource);
						SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
						SqlSession session = sqlSessionFactory.openSession();
						count = session.selectOne("org.first.mvc.BaseMapper.countReIdCheck", p1);
						if(count == 0) {
							result = 0 ;
						}else {
							result = 1 ;
						}
					} catch (IOException e) {
						e.printStackTrace();
					}
					return result ;
				}
				public static String getUserImg(Integer userNum) {		
					//System.out.println("이미지 찾는 아이가 들어왔네 찾아서 보여줄게 : " + userNum);
					String resource = "org/first/mvc/mybatis_config.xml";
					InputStream inputStream;
					String result = "";
					try {
						inputStream = Resources.getResourceAsStream(resource);
						SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
						SqlSession session = sqlSessionFactory.openSession();
						result = session.selectOne("org.first.mvc.BaseMapper.getUserImg", userNum);
						if(result==null) {
							result = "gucci.png";
						}
					} catch (IOException e) {
						e.printStackTrace();
					}
					return result ;
				}
				
				public static Integer getIdn247_up(Integer up_userId) {		
					String resource = "org/first/mvc/mybatis_config.xml";
					InputStream inputStream;
					Integer result = 0;
					try {
						inputStream = Resources.getResourceAsStream(resource);
						SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
						SqlSession session = sqlSessionFactory.openSession();
						result = session.selectOne("org.first.mvc.BaseMapper.getIdn247_up", up_userId);
					} catch (IOException e) {
						e.printStackTrace();
					}
					return result ;
				}
				
				
				
				public static Integer getFriAdmIdN247_ft(Integer ft_userId, Integer tabId) {		
					String resource = "org/first/mvc/mybatis_config.xml";
					InputStream inputStream;
					Integer result = 0;
					Fn247 p1 = new Fn247();
					p1.setFt_userId(ft_userId);
					p1.setFt_tabId(tabId);
					try {
						inputStream = Resources.getResourceAsStream(resource);
						SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
						SqlSession session = sqlSessionFactory.openSession();
						result = session.selectOne("org.first.mvc.BaseMapper.getFriAdmIdN247_ft", p1);
					} catch (IOException e) {
						e.printStackTrace();
					}
					return result ;
				}
				
				public static List<Fn247> readFriAdmTabId(Integer tabId) {
					
					List<Fn247> result = new ArrayList<Fn247>();
					
					String resource = "org/first/mvc/mybatis_config.xml";
					InputStream inputStream;
					Fn247 p1 = new Fn247();
					p1.setFt_tabId(tabId);
					try {
						inputStream = Resources.getResourceAsStream(resource);
						SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
						SqlSession session = sqlSessionFactory.openSession();
						result = session.selectList("org.first.mvc.BaseMapper.getFriAdmTabId", p1);
				        for(int i=0 ; i<result.size() ; i++) {
				        	result.get(i).setUtf8create(dateChangeAction(result.get(i).getCreate()));
				        	result.get(i).setF_name(getNickNameToUserNum(result.get(i).getFt_userId()));
				        	result.get(i).setEmail(getIdToUserNum(result.get(i).getFt_userId()));
				        	result.get(i).setF_imgName(getUserImg(result.get(i).getFt_userId()));
					 	}
					} catch (IOException e) {
						e.printStackTrace();
					}
					
					return result ;
				}
				
				public static List<Fn247> readFriAdmList(Integer userNum, Integer adm, Integer tabId ) {
					
					List<Fn247> result = new ArrayList<Fn247>();
					
					String resource = "org/first/mvc/mybatis_config.xml";
					InputStream inputStream;
					Fn247 p1 = new Fn247();
					p1.setMyId(userNum);
					p1.setAdm(adm);
					try {
						inputStream = Resources.getResourceAsStream(resource);
						SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
						SqlSession session = sqlSessionFactory.openSession();
						result = session.selectList("org.first.mvc.BaseMapper.getFriAdmList", p1);
				        for(int i=0 ; i<result.size() ; i++) {
				        	result.get(i).setUtf8create(dateChangeAction(result.get(i).getCreate()));
				        	result.get(i).setF_name(DAO.getNickNameToUserNum(result.get(i).getMyId()));
				        	result.get(i).setEmail(DAO.getIdToUserNum(result.get(i).getMyId()));
				        	result.get(i).setCheck(DAO.getCheckFriId(result.get(i).getMyId(),tabId));
					 	}
					} catch (IOException e) {
						e.printStackTrace();
					}
					return result ;
				}

				public static String getTabIntro(Integer tabId) {

					String resource = "org/first/mvc/mybatis_config.xml";
					InputStream inputStream;
					String result = "";
					try {
						inputStream = Resources.getResourceAsStream(resource);
						SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
						SqlSession session = sqlSessionFactory.openSession();
						result = session.selectOne("org.first.mvc.BaseMapper.getTabIntro", tabId);
					 	
					} catch (IOException e) {
						e.printStackTrace();
					}
					return result ;
				}
				public static Member getMember(Integer userId) {
					
					Member result = new Member();
					String resource = "org/first/mvc/mybatis_config.xml";
					InputStream inputStream;
					
					try {
						inputStream = Resources.getResourceAsStream(resource);
						SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
						SqlSession session = sqlSessionFactory.openSession();
						result = session.selectOne("org.first.mvc.BaseMapper.getMember", userId);
					 	if(result.getUserImg() == null) {
					 		result.setUserImg("gucci.png");
					 	}
					} catch (IOException e) {
						e.printStackTrace();
					}
					return result ;
				}
				public static Member getMemberToId (String id) {
					
					Member result = new Member();
					String resource = "org/first/mvc/mybatis_config.xml";
					InputStream inputStream;
					
					try {
						inputStream = Resources.getResourceAsStream(resource);
						SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
						SqlSession session = sqlSessionFactory.openSession();
						result = session.selectOne("org.first.mvc.BaseMapper.getMemberToId", id);
					 	if(result.getUserImg() == null) {
					 		result.setUserImg("gucci.png");
					 	}
					 	
					} catch (IOException e) {
						e.printStackTrace();
					}
					return result ;
				}
				public static Date getTabDueDayAction(Integer tabId) {

					Post tabDueDay = new Post();
					Date result = null;
					String resource = "org/first/mvc/mybatis_config.xml";
					InputStream inputStream;
					
					try {
						inputStream = Resources.getResourceAsStream(resource);
						SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
						SqlSession session = sqlSessionFactory.openSession();
						tabDueDay = session.selectOne("org.first.mvc.BaseMapper.getTabDueDay", tabId);
					 	result = tabDueDay.getTab_dueDay();
					} catch (IOException e) {
						e.printStackTrace();
					}
					return result ;
				}

				public static Post getTabInforAction(Integer tabId, List<Post> tabList) {

					Post result = new Post();
					List<Post> p1 = new ArrayList<Post>();
					if(tabList != null) {
						for (int i=0 ; i<tabList.size(); i++) {
							System.out.println( "getTabInforAction : "+tabList.get(i).getTab_dueDay());
						}
					}
					p1.addAll(tabList);
					if(p1 != null) {
						for (int i=0 ; i<p1.size(); i++) {
							System.out.println( "getTabInforAction / p1 : "+p1.get(i).getTab_dueDay());
							System.out.println( "getTabInforAction / p1.getTabId() : "+p1.get(i).getTabId());
							System.out.println( "getTabInforAction / tabId : "+tabId);
						}
					}
					Date date_now = new Date(System.currentTimeMillis());
					for (int i=0 ; i<p1.size(); i++) {
						if(p1.get(i).getTabId() == tabId) {
							result = p1.get(i);
							//System.out.println(p1.get(i).getTab_dueDay() + "없냐 이거?");
						}
					}
					result.setMinDay(dateChangeMinAction(date_now));
					result.setMaxDay(dateChangeMaxAction(result.getTab_dueDay()));
					result.setNick(getNickNameToUserNum(result.getUserNum()));
					//탭삭제시 그냥 삭제 할거냐 다른탭을 선택하게 할것이냐정함 
					if(getCountTab2(result.getUserNum()) < 2) {
					result.setTabSelectCheck(0);
					//System.out.println("탭 카운트가 2 미만이네요 ");
					}else {
						result.setTabSelectCheck(1);
						//System.out.println("탭 카운트가 2이상이네요 ");
					}
					Integer Check = getCheckFriId(result.getUserNum(), tabId);
					if(Check != 0) {
						result.setCheck(1);
					}
					result.setTabProgress(tabProgress(tabId,result.getTab_dueDay(),result.getCreate()));
					result.setTabProgressBg(progressBg(result.getTabProgress()));
					result.setDueMessage(tabDueDay2(tabId));
				    

					
					return result ;
				}
				
				public static List<Post> getSelectionList (Integer tabId, List<Post> tabList){
					List<Post> result = new ArrayList<Post>();
					List<Post> p1 = new ArrayList<Post>();
					p1.addAll(tabList);
					for(int i=0 ; i<p1.size() ; i ++) {
						if(p1.get(i).getTabId() == tabId) {
							p1.get(i).setCheck(1);
							System.out.println("탭선택시 나타날 목록 : " + p1.get(i).getTabId() + "번 탭에 체크결과는 : "+p1.get(i).getCheck() );
						}else {
							p1.get(i).setCheck(0);
						}
						result.add(p1.get(i));
					}
					return result;
				}
				public static void userInfoUpdate (String nickName, String mb_introduce ,Integer userId) {
					
					String resource = "org/first/mvc/mybatis_config.xml";
					InputStream inputStream;
					Member p1 = new Member();
					p1.setNickName(nickName);
					p1.setMb_introduce(mb_introduce);
					p1.setUserId(userId);
					
					try {
						inputStream = Resources.getResourceAsStream(resource);
						SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
						SqlSession session = sqlSessionFactory.openSession();
						session.update("org.first.mvc.BaseMapper.updateMemberIntroduce", p1);
						session.commit();
						session.close();
			  
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
				
				public static void updatePost (Integer id, String postTitle, String description, Integer tabId, Date dueDay ) {
					String resource = "org/first/mvc/mybatis_config.xml";
					InputStream inputStream;
					Post post = new Post();
					post.setId(id);
					post.setTabId(tabId);
					post.setPostTitle(postTitle);
					post.setDescription(description);
					post.setDueDay(dueDay);
					
					try {
						inputStream = Resources.getResourceAsStream(resource);
						SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
						SqlSession session = sqlSessionFactory.openSession();
						session.update("org.first.mvc.BaseMapper.updatePost", post);
						session.commit();
						session.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			

				public static void updateFriAdmission(Integer idN247_f, Integer userNum, Integer tabId ) {
					String resource = "org/first/mvc/mybatis_config.xml";
					InputStream inputStream;
					Fn247 p1 = new Fn247();
					p1.setIdN247_f(idN247_f);
					try {
						inputStream = Resources.getResourceAsStream(resource);
						SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
						SqlSession session = sqlSessionFactory.openSession();
						session.update("org.first.mvc.BaseMapper.updateFriAdm", p1);
						session.commit();
						session.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
	 

				

				public static void updateDelFri (Integer idN247_f) {
					String resource = "org/first/mvc/mybatis_config.xml";
					InputStream inputStream;
					Fn247 p1 = new Fn247();
					p1.setIdN247_f(idN247_f);
					try {
						inputStream = Resources.getResourceAsStream(resource);
						SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
						SqlSession session = sqlSessionFactory.openSession();
						session.update("org.first.mvc.BaseMapper.updateDelFri", p1);
						session.commit();
						session.close();
					} catch (IOException e) {
						e.printStackTrace();
					}

				}
	 	
	 	
				public static void updateReply (Integer idN247_re, String n247_reDes ) {
					String resource = "org/first/mvc/mybatis_config.xml";
					InputStream inputStream;
					Post p1 = new Post();
					p1.setN247_reDes(n247_reDes);
					p1.setIdN247_re(idN247_re);
					try {
						inputStream = Resources.getResourceAsStream(resource);
						SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
						SqlSession session = sqlSessionFactory.openSession();
						session.update("org.first.mvc.BaseMapper.updateReply", p1);
						session.commit();
						session.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}

				public static void updateDelFriToTab (Integer idN247_ft) {
					String resource = "org/first/mvc/mybatis_config.xml";
					InputStream inputStream;
					Fn247 p1 = new Fn247();
					p1.setIdN247_ft(idN247_ft);
					try {
						inputStream = Resources.getResourceAsStream(resource);
						SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
						SqlSession session = sqlSessionFactory.openSession();
						session.update("org.first.mvc.BaseMapper.updateDelFriToTab", p1);
						session.commit();
						session.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			 	
			 	
				public static void updatePostTabTitle (Integer tabId, Integer moveOn, Integer isDelCheck ) {
					String resource = "org/first/mvc/mybatis_config.xml";
					InputStream inputStream;
					Post p1 = new Post();
					p1.setTabId(moveOn);
					p1.setIsDel(1);
					Post tab = new Post();
					tab.setTabId(tabId);
					tab.setMoveOn(moveOn);
					try {
						inputStream = Resources.getResourceAsStream(resource);
						SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
						SqlSession session = sqlSessionFactory.openSession();
						if(isDelCheck == 1) {
							//updateAllPostIsDeltoTabId
							session.update("org.first.mvc.BaseMapper.updateAllPostIsDeltoTabId", tabId);
							session.update("org.first.mvc.BaseMapper.updateIsDelTabTitle", p1);
							session.commit();
							session.close();
						}else {
							session.update("org.first.mvc.BaseMapper.updatePostTabTitle", tab);
							session.update("org.first.mvc.BaseMapper.updateIsDelTabTitle", p1);
							session.commit();
							session.close();
						}
						
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
				
				public static void updateTabIntro (Integer tabId, String tab_intro ) {
					String resource = "org/first/mvc/mybatis_config.xml";
					InputStream inputStream;

					Post tab = new Post();
					tab.setTabId(tabId);
					tab.setTab_intro(tab_intro);
					
					try {
						inputStream = Resources.getResourceAsStream(resource);
						SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
						SqlSession session = sqlSessionFactory.openSession();
						session.update("org.first.mvc.BaseMapper.updateTabIntro", tab);
						session.commit();
						session.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			
				
				public static void updateTabDueDay (Integer tabId, Date tab_dueDay ) {
					String resource = "org/first/mvc/mybatis_config.xml";
					InputStream inputStream;

					Post tab = new Post();
					tab.setTabId(tabId);
					tab.setTab_dueDay(tab_dueDay);;
					
					try {
						inputStream = Resources.getResourceAsStream(resource);
						SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
						SqlSession session = sqlSessionFactory.openSession();
						session.update("org.first.mvc.BaseMapper.updateTabDueDay", tab);
						session.commit();
						session.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
				public static void deletePost (Integer id, Integer tabId ) {
					String resource = "org/first/mvc/mybatis_config.xml";
					InputStream inputStream;
					try {
						inputStream = Resources.getResourceAsStream(resource);
						SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
						SqlSession session = sqlSessionFactory.openSession();
						session.update("org.first.mvc.BaseMapper.updateIsDelPost", id);
						session.update("org.first.mvc.BaseMapper.updateIsDelPostfile", id);
						session.commit();
						session.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
				
				public static void completePost (Integer id, Integer isDel) {
					String resource = "org/first/mvc/mybatis_config.xml";
					InputStream inputStream;
					Post p1 = new Post();
					p1.setId(id);
					p1.setIsDel(isDel);
					try {
						inputStream = Resources.getResourceAsStream(resource);
						SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
						SqlSession session = sqlSessionFactory.openSession();
						session.update("org.first.mvc.BaseMapper.updateCompletePost", p1);
						session.commit();
						session.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}

				public static void deleteTabTitle (Integer tabId, Integer userNum ) {
					String resource = "org/first/mvc/mybatis_config.xml";
					InputStream inputStream;
					Post p1 = new Post();
					p1.setTabId(tabId);
					p1.setIsDel(1);
					try {
						inputStream = Resources.getResourceAsStream(resource);
						SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
						SqlSession session = sqlSessionFactory.openSession();
						session.update("org.first.mvc.BaseMapper.updateIsDelTabTitle", p1);
						session.commit();
						session.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}

				public static void deleteUserImg ( Integer idN247_up ) {
					String resource = "org/first/mvc/mybatis_config.xml";
					InputStream inputStream;
					Upload p1 = new Upload();
					p1.setIdN247_up(idN247_up);

					try {
						inputStream = Resources.getResourceAsStream(resource);
						SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
						SqlSession session = sqlSessionFactory.openSession();
						session.update("org.first.mvc.BaseMapper.updateUploadDel", p1);
						session.commit();
						session.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
					//TO DO 서버 시간을 불러와서 한국시간으로 맞춥니다 
				    public static String dateChangeAction(Date p1) {
				    	
				    	SimpleDateFormat sdformat = new SimpleDateFormat("yy-MM-dd HH:mm:ss");
						String result = null;
						Calendar cal = Calendar.getInstance();
						cal.setTime(p1);
						cal.add(Calendar.HOUR, -9);
						result = sdformat.format(cal.getTime());

						return result ;
					}
				    
				    public static String dateChangeMinAction(Date p1) {
				    	
				    	SimpleDateFormat sdformat = new SimpleDateFormat("yyyy-MM-dd");
						String result = null;
						Calendar cal = Calendar.getInstance();
						cal.setTime(p1);
						result = sdformat.format(cal.getTime());

						return result ;
					}
				   
				    public static String dateChangeMaxAction(Date p1) {
				    	
				    	SimpleDateFormat sdformat = new SimpleDateFormat("yyyy-MM-dd");
						String result = null;
						Calendar cal = Calendar.getInstance();
						cal.setTime(p1);
						result = sdformat.format(cal.getTime());

						return result ;
					}
				    
				    public static Date dateChangeAction2(Date p1) {
						Date result = null;
						Calendar cal = Calendar.getInstance();
						cal.setTime(p1);
						cal.add(Calendar.HOUR, -9);
						result = cal.getTime();

						return result ;
					}
				    public static String dateToString(Date p1) {
				    	
				    	SimpleDateFormat sdformat = new SimpleDateFormat("yy-MM-dd HH:mm:ss");
						String result = null;
						Calendar cal = Calendar.getInstance();
						cal.setTime(p1);
						result = sdformat.format(cal.getTime());

						return result ;
					}
				    
				    public static long dateToLong(Date p1) {
				    	
				    	SimpleDateFormat sdformat = new SimpleDateFormat("yy-MM-dd HH:mm:ss");
						String p2 = null;
						Calendar cal = Calendar.getInstance();
						cal.setTime(p1);
						p2 = sdformat.format(cal.getTime());
						long result =  Long.parseLong(p2);
						return result ;
					}
				    
				    //test 해야함 날짜 차이 구하기 
				    public static long calDateBetweenAndB(Date p1, Date p2){
				    	long calDate = 0;
				    	long calDateDays = 0;

					    	Date FirstDate = p1;
					    	Date SecondDate = p2;
					    	calDate = FirstDate.getTime() - SecondDate.getTime();
					    	calDateDays = calDate / (24*60*60*1000);
					    	//두날짜 차의 절대값을 반환한
					    	calDateDays = Math.abs(calDateDays);
					    	
					    	////System.out.println("두 날짜의 차이 : " + calDateDays);
					    	if(calDateDays == 0) {
					    		//System.out.println("날짜 계산 했는데 0이 나왔어요");
					    		//System.out.println ( time.toString() );
					    		Date d1 = p2;
					    		Date d2 = p1;
					    		long diff = d1.getTime() - d2.getTime();
					    		long sec = diff / 3600000;
					    		if(sec == 0) {
					    			sec = diff / 60000;
					    			//System.out.println(sec);
					    		}else {
					    			//System.out.println(sec);
					    		}	
					    	}
				    	return calDateDays;
				    }
				    
				    public static long calDateBetweenAndB2(Date p1, Date p2) {
				    	long calDate = 0;
				    	long calDateDays = 0;

					    	Date FirstDate = p1;
					    	Date SecondDate = p2;
					    	calDate = FirstDate.getTime() - SecondDate.getTime();
					    	calDateDays = calDate / (24*60*60*1000);
					    	//두날짜 차의 절대값을 반환한
					    	//calDateDays = Math.abs(calDateDays);
					    	
					    	//System.out.println("두 날짜의 차이 : " + calDateDays);

				    	return calDateDays;
				    }
				    
				    //카드에 수정된지 얼마돼었는가 보여주는 계산기 
				    public static String calCardDueDate (Date p1, Date p2){
				    	String result = "";
				    	long calDate = 0;
				    	long calDateDays = 0;

					    	Date FirstDate = p1;
					    	Date SecondDate = p2;
					    	calDate = FirstDate.getTime() - SecondDate.getTime();
					    	calDateDays = calDate / (24*60*60*1000);
					    	//두날짜 차의 절대값을 반환한
					    	calDateDays = Math.abs(calDateDays);
					    	result = String.valueOf(calDateDays)+"일전";
					    	////System.out.println("두 날짜의 차이 : " + calDateDays);
					    	if(calDateDays == 0) {
					    		//System.out.println("날짜 계산 했는데 0이 나왔어요");
					    		Date d1 = p2;
					    		Date d2 = p1;
					    		long diff = d1.getTime() - d2.getTime();
					    		//기본은 날짜로 계산 0일경우 시간,분으로나뉩  
					    		long sec = diff / 3600000;
					    		if(sec == 0) {
					    			sec = diff / 60000;
					    			if(sec == 0) {
					    				result = "지금";
					    			}else {
					    				result = String.valueOf(sec)+"분전";
					    			}
					    		}else {
					    			result = String.valueOf(sec)+"시간전";
					    			//System.out.println(result);
					    		}	
					    	}
				    	return result ;
				    }
				    
				    public static String calDateBetweenAndB4 (Date p1, Date p2){
				    	String result = "";
				    	long calDate = 0;
				    	long calDateDays = 0;

					    	Date FirstDate = p1;
					    	Date SecondDate = p2;
					    	calDate = FirstDate.getTime() - SecondDate.getTime();
					    	calDateDays = calDate / (24*60*60*1000);
					    	result = String.valueOf(calDateDays)+"일후 마감";
					    	////System.out.println("두 날짜의 차이 : " + calDateDays);
					    	if(calDateDays == 0) {
					    			result = "마감일 입니다.";
					    	}else if(calDateDays < 0) {
					    		result = "마감일이 지났습니다.";
					    	}
				    	return result ;
				    }
				    public static List<Post> tabLastUpdateSet(List<Post> p1){
				    	
				    	List<Post> result = new ArrayList<Post>();
				    	
				    	for (int i=0 ; i<p1.size(); i ++) {
				    		result.get(i).setTabLastUpdate(dateChangeAction(p1.get(i).getLastUpdate()));
				    	}
				    	return result;
				    }
				    
				    public static long tabDueDay (Integer tabId){
				    	
				    	Date date_now = new Date(System.currentTimeMillis());
						long result = calDateBetweenAndB(date_now,getTabDueDayAction(tabId));
						if(result <= 0) {
							result = 0;
						}
				    	
				    	
				    	return result;
				    }
				    //전체 기간 구하고 남은날 빼준 값넣어주기
				    public static String tabDueDay2 (Integer tabId){
				    	Date date_now = new Date(System.currentTimeMillis());
						String result = calDateBetweenAndB4(getTabDueDayAction(tabId),date_now);
				    	return result;
				    }
 
				    public static Double tabProgress (Integer tabId, Date tab_dueDay, Date create) {
				    	Date date_now = new Date(System.currentTimeMillis());
				    	
				    	long totalDay = calDateBetweenAndB2(tab_dueDay,dateChangeAction2(create));
				    	long dueDay = calDateBetweenAndB2(tab_dueDay,date_now);
				    	//System.out.println("탭 듀데이는 :"+dueDay);
				    	if(dueDay < 0) {
				    		dueDay = 0;
				    	}
				    	int p1 = Long.valueOf(dueDay).intValue();
				    	int p2 = Long.valueOf(totalDay).intValue();
				    	
				    	Double result = 100.0 - (((double)p1/(double)p2) * 100.0) ;
				    	if(result < 0) {
				    		result = 100.0;
				    	}
				    	//System.out.println("100 - ((" + p1 +"/"+ p2 +")*100) = " + result );
				    	return result;
				    }
				    
				    public static Double progress (Date create, Date due) {
				    	Date date_now = new Date(System.currentTimeMillis());
				    	long totalDay = calDateBetweenAndB(dateChangeAction2(create),dateChangeAction2(due));
				    	Double result = 100.0;
				    	
				    	if(totalDay == 0) {
				    		result = 100.0;
				    	}else {
				    		long dueDay = calDateBetweenAndB(dateChangeAction2(due),date_now);
					    	if(dueDay < 0) {
					    		dueDay = 0;
					    	}
					    	int p1 = Long.valueOf(dueDay).intValue();
					    	int p2 = Long.valueOf(totalDay).intValue();
					    	
					    	result = 100.0 - (((double)p1/(double)p2) * 100.0) ;
					    	if(p1 == p2) {
					    		result = 1.0;
					    	//System.out.println("100 - ((" + p1 +"/"+ p2 +")*100) = " + result );
					    	}
				    	}
				    	
				    	
				    	return result;
				    }
				    public static String progressBg (Double p1) {
				    	String result = "danger";
				    	//System.out.println("색깔 정할 아이가 왔어요 :" + p1);
				    	if(p1 < 50.0) {
				    		result = "success";
				    	}else if(p1 < 70.0){
				    		result = "warning";
				    	}else {
				    		result = "danger";
				    	}
				    	
				    	return result;
				    }
				    
					 //배열 중복 제거 처리 
					 public static int remove_Duplicate_Elements (int arr[], int n) {
						 if (n==0 || n==1) {
							 return n;
						 }
						 int[] tempA = new int[n];
						 int j = 0;
						 for (int i=0; i<n-1; i++) {
							 if(arr[i] != arr[i+1]) {
								 tempA[j++] = arr[i]; 
							 }
						 }
						 tempA[j++] = arr[n-1];
						 for (int i=0; i<j; i++) {
							 arr[i] = tempA[i];
						 }
						 return j;
					 }
					//배열 중복 제거 처리 
					 public static void reDuList (String[] args) {			 
						 
						 int arr[] = {11,77,2,2,7,8,8,8,3};
						 //System.out.println("이것도 테스트야 : " + Arrays.toString(arr));
						 Arrays.sort(arr);
						 int length = arr.length;
						 length = remove_Duplicate_Elements(arr, length);
						 for (int i=0; i<length; i++) 
							 System.out.println("중복제거중이야 : "+arr[i]+" ");
					 }
}
