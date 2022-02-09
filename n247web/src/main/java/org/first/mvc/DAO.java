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
				Date date_now = new Date(System.currentTimeMillis());
				tab.setTabTitle(tabTitle);
				//System.out.println("유저번호 잘왔니 : " + userNum);
				tab.setUserNum(userNum);
				tab.setTab_intro(tab_intro);
				if(tab_dueDay != null) {
					tab.setTab_dueDay(tab_dueDay);
				}else {
					tab.setTab_dueDay(date_now);
				}
				
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
			

			public static void friendSubscription (Integer fUserId, Integer myId) {
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
					//System.out.println("DAO.readTabListAction 가동중 : Integer userNum"+ userNum +"받아서 list<Post> 반환 "+result.size() );
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
			
//			public static Member sideBarAdmTabimgSet (Member userInfo, Member friInfo){
//				Member result = new Member();
//				result = userInfo;
//				List<Post> p1 = new ArrayList<Post>();
//				p1 = userInfo.getFriTabList();
//				for(int i=0 ; i<p1.size(); i++) {
//					for(int j=0 ; j<friInfo.getFriendInformationList().size() ; j++) {
//						if(p1.get(i).getUserNum() == friInfo.getFriendInformationList().get(j).getUserId()) {
//							p1.get(i).setImgName(friInfo.getFriendInformationList().get(j).getUserImg());
//						}else {
//							System.out.println("%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%% 여기 문제임 " + p1.get(i).getUserNum() + "이게 누구냐 친구목록에 없네 ");
//						}
//					}
//				}
//				result.setFriTabList(p1);
//				
//				
//				return result;
//			}
			
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
		    
	    	public static List<Post> getReplyListCard(Integer id) {
				
				List<Post> result = new ArrayList<Post>();
				String resource = "org/first/mvc/mybatis_config.xml";
				InputStream inputStream;
				try {
					inputStream = Resources.getResourceAsStream(resource);
					SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
					SqlSession session = sqlSessionFactory.openSession();
					result = session.selectList("org.first.mvc.BaseMapper.getReplyListCard", id);
					if(result.get(0).getDueDay() != null) {
						result.get(0).setDueDayString(dateToString3(result.get(0).getDueDay()));
					}
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
	
			
			public static void updateProjectTitle (String tabTitle, Integer tabId ) {
				String resource = "org/first/mvc/mybatis_config.xml";
				InputStream inputStream;
				Post tab = new Post();
				tab.setTabId(tabId);
				tab.setTabTitle(tabTitle);
				try {
					inputStream = Resources.getResourceAsStream(resource);
					SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
					SqlSession session = sqlSessionFactory.openSession();
					session.update("org.first.mvc.BaseMapper.updateProjectTitle", tab);
					session.commit();
					session.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
	
			public static void updateProjectIntro (String tab_intro, Integer tabId ) {
				String resource = "org/first/mvc/mybatis_config.xml";
				InputStream inputStream;
				Post tab = new Post();
				tab.setTabId(tabId);
				tab.setTab_intro(tab_intro);
				try {
					inputStream = Resources.getResourceAsStream(resource);
					SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
					SqlSession session = sqlSessionFactory.openSession();
					session.update("org.first.mvc.BaseMapper.updateProjectIntro", tab);
					session.commit();
					session.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
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
					 //System.out.println("getPostFileNameListToTabId실행하고만들어진 파일네임리스트 "+result.size());
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
						 //System.out.println("p2.setUp_fileName(p1.get(i).getUp_fileName())"+p2.getUp_fileName());
						 result.add(p2);
					 }
				 }
				 //System.out.println("result.size()"+result.size());
				 if(result.size() != 0) {
					 //System.out.println("result.get(0).getUp_fileName()"+result.get(0).getUp_fileName());
				 }
				 
				 		
				 return result;
			 }
			 
			 public static Post readPostListAction(Integer tabId, Member userInfo) {
				 
				 	//System.out.println("실행할게요 가져오라고 해서 포스트 ");
				 	Post result = new Post();
					List<Post> postList = new ArrayList<Post>();
					List<Post> completePostList = new ArrayList<Post>();
					List<Post> allList = new ArrayList<Post>();
					//프로젝트안에 있는 댓글을 가져와서 배열에 넣어준다.
					List<Post> replyList = new ArrayList<Post>();
					replyList.addAll(getReplyListToTabId(tabId));
					
					
					//System.out.println("replyList "+replyList.size()+"개 받아왔어요 ");
					List<Upload> postFile = new ArrayList<Upload>();
					postFile.addAll(getPostFileNameListToTabId(tabId));
					//System.out.println("postFile "+postFile.size()+"개 받아왔어요 ");
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
			            //System.out.println("가져온 포스트들의 갯수는 : (DAO) " + allList.size());
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
			            		//System.out.println("실행은 했냐 이거?");
			            	}
			            	//포스트에 파일정보를 찾아서 입력 
		            		for (int j=0 ; j<postFile.size() ; j++) {
		            			  //파일정보와 포스트번호를 비교해서 맞으면 트루 
					        	  if(allList.get(i).getId() == postFile.get(j).getUp_postId()) {
					        		  //파일명을 입력 
					        		  allList.get(i).setUp_fileName(postFile.get(j).getUp_fileName());
					        		  //파일이 있다는 판단 
					        		  allList.get(i).setCheck(1);
					        		  //System.out.println(i+"번째 allList.get(i).getUp_fileName" + allList.get(i).getUp_fileName());
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
				 
				 //System.out.println("이거 하긴 하냐postSetReplyList 받아온 리스트는 " + replyList.size() +"개 입니다. "
//				 		+ "그리고 받아온 아이디는 " + id);
				 List<Post> p1 = new ArrayList<Post>();
				 List<Post> p2 = new ArrayList<Post>();
				 List<Post> result = new ArrayList<Post>();
				 p1.addAll(replyList);
				 
				 for (int i=0 ; i<p1.size(); i++) {
					 if(id == p1.get(i).getN247_rePoId()) {
						 //System.out.println(id + ": 포스트 번호와  == 댓글의 포스트번호 :" + p1.get(i).getN247_rePoId() +"가 맞네요  ");
						 
						 p2.add(p1.get(i));
					 }
				 }
				 result.addAll(p2);
				 //System.out.println("result 에 받아왔는데 : " + result.size() +"개 입니다. ");
					 		
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
				        	System.out.println("리플라이리스트에 닉네임 넣고 있어요 : " + result.get(i).getN247_reUsId()+"번: 사용자네요 ");
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
							//System.out.println( "getTabInforAction : "+tabList.get(i).getTab_dueDay());
						}
					}
					p1.addAll(tabList);
					if(p1 != null) {
						for (int i=0 ; i<p1.size(); i++) {
							//System.out.println( "getTabInforAction / p1 : "+p1.get(i).getTab_dueDay());
							//System.out.println( "getTabInforAction / p1.getTabId() : "+p1.get(i).getTabId());
							//System.out.println( "getTabInforAction / tabId : "+tabId);
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
							//System.out.println("탭선택시 나타날 목록 : " + p1.get(i).getTabId() + "번 탭에 체크결과는 : "+p1.get(i).getCheck() );
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
				
				public static void updateCardTitle (String postTitle, Integer id) {
					String resource = "org/first/mvc/mybatis_config.xml";
					InputStream inputStream;
					Post post = new Post();
					post.setId(id);
					post.setPostTitle(postTitle);

					
					try {
						inputStream = Resources.getResourceAsStream(resource);
						SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
						SqlSession session = sqlSessionFactory.openSession();
						session.update("org.first.mvc.BaseMapper.updateCardTitle", post);
						session.commit();
						session.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
				
				public static void updateCardDescription (String description, Integer id) {
					String resource = "org/first/mvc/mybatis_config.xml";
					InputStream inputStream;
					Post post = new Post();
					post.setId(id);
					post.setDescription(description);

					
					try {
						inputStream = Resources.getResourceAsStream(resource);
						SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
						SqlSession session = sqlSessionFactory.openSession();
						session.update("org.first.mvc.BaseMapper.updateCardDescription", post);
						session.commit();
						session.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
				
				
				public static void updateReplyDes (String n247_reDes, Integer idN247_re) {
					String resource = "org/first/mvc/mybatis_config.xml";
					InputStream inputStream;
					Post post = new Post();
					post.setIdN247_re(idN247_re);
					post.setN247_reDes(n247_reDes);

					
					try {
						inputStream = Resources.getResourceAsStream(resource);
						SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
						SqlSession session = sqlSessionFactory.openSession();
						session.update("org.first.mvc.BaseMapper.updateReplyDes", post);
						session.commit();
						session.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
				
				public static void updateReplyDesToDes (String n247_reDes, String insertDes) {
					String resource = "org/first/mvc/mybatis_config.xml";
					InputStream inputStream;
					Post post = new Post();
					post.setInsertDes(insertDes);
					post.setN247_reDes(n247_reDes);

					
					try {
						inputStream = Resources.getResourceAsStream(resource);
						SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
						SqlSession session = sqlSessionFactory.openSession();
						session.update("org.first.mvc.BaseMapper.updateReplyDesToDes", post);
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
				
				public static void updateFriAdm (Integer fUserId, Integer myId ) {
					String resource = "org/first/mvc/mybatis_config.xml";
					InputStream inputStream;
					Fn247 p1 = new Fn247();
					p1.setMyId(myId);
					p1.setfUserId(fUserId);
					try {
						inputStream = Resources.getResourceAsStream(resource);
						SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
						SqlSession session = sqlSessionFactory.openSession();
						session.update("org.first.mvc.BaseMapper.updateFriAdm2", p1);
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
	 	
				public static void updateDelFri2 (Integer myId, Integer fUserId) {
					String resource = "org/first/mvc/mybatis_config.xml";
					InputStream inputStream;
					Fn247 p1 = new Fn247();
					p1.setMyId(myId);
					p1.setfUserId(fUserId);
					try {
						inputStream = Resources.getResourceAsStream(resource);
						SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
						SqlSession session = sqlSessionFactory.openSession();
						session.update("org.first.mvc.BaseMapper.updateDelFri2", p1);
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
				
				
				public static void updateUserInfo (Integer userId, String nickName, String mb_introduce ) {
					String resource = "org/first/mvc/mybatis_config.xml";
					InputStream inputStream;
					Member p1 = new Member();
					p1.setUserId(userId);
					p1.setNickName(nickName);
					p1.setMb_introduce(mb_introduce);
					
					try {
						inputStream = Resources.getResourceAsStream(resource);
						SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
						SqlSession session = sqlSessionFactory.openSession();
						session.update("org.first.mvc.BaseMapper.updateUserInfo", p1);
						session.commit();
						session.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
				
				public static void deleteReply (Integer idN247_re ) {
					String resource = "org/first/mvc/mybatis_config.xml";
					InputStream inputStream;
					Post p1 = new Post();
					p1.setIdN247_re(idN247_re);
					
					try {
						inputStream = Resources.getResourceAsStream(resource);
						SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
						SqlSession session = sqlSessionFactory.openSession();
						//System.out.println("실행할게 : " + p1.getIdN247_re());
						session.update("org.first.mvc.BaseMapper.deleteReply", p1);
						session.commit();
						session.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
				
				public static void deleteChangeReply (String n247_reDes ) {
					String resource = "org/first/mvc/mybatis_config.xml";
					InputStream inputStream;
					Post p1 = new Post();
					p1.setN247_reDes(n247_reDes);
					
					try {
						inputStream = Resources.getResourceAsStream(resource);
						SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
						SqlSession session = sqlSessionFactory.openSession();
						//System.out.println("실행할게 : " + p1.getIdN247_re());
						session.update("org.first.mvc.BaseMapper.deleteChangeReply", p1);
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
			 	
				
				public static void isDelProjectAdm (Integer idN247_ft) {
					String resource = "org/first/mvc/mybatis_config.xml";
					InputStream inputStream;
					Fn247 p1 = new Fn247();
					p1.setIdN247_ft(idN247_ft);
					try {
						inputStream = Resources.getResourceAsStream(resource);
						SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
						SqlSession session = sqlSessionFactory.openSession();
						session.update("org.first.mvc.BaseMapper.isDelProjectAdm", p1);
						session.commit();
						session.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
				
				public static void deleteUpFile (Integer idN247_up) {
					String resource = "org/first/mvc/mybatis_config.xml";
					InputStream inputStream;
					try {
						inputStream = Resources.getResourceAsStream(resource);
						SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
						SqlSession session = sqlSessionFactory.openSession();
						session.update("org.first.mvc.BaseMapper.deleteUpFile", idN247_up);
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
							
							session.update("org.first.mvc.BaseMapper.updateAllPostIsDeltoTabId", tabId);
							session.update("org.first.mvc.BaseMapper.updateIsDelTab", p1);
							session.update("org.first.mvc.BaseMapper.updateDelFtToTabId", moveOn);
							session.commit();
							session.close();
						}else {
							session.update("org.first.mvc.BaseMapper.updatePostTabTitle", tab);
							session.update("org.first.mvc.BaseMapper.updateIsDelTab", p1);
							session.update("org.first.mvc.BaseMapper.updateDelFtToTabId", moveOn);
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
				
				public static void completeTab (Integer tabId, Integer isDel) {
					String resource = "org/first/mvc/mybatis_config.xml";
					InputStream inputStream;
					Post p1 = new Post();
					p1.setTabId(tabId);
					p1.setIsDel(isDel);
					try {
						inputStream = Resources.getResourceAsStream(resource);
						SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
						SqlSession session = sqlSessionFactory.openSession();
						session.update("org.first.mvc.BaseMapper.updateCompleteTab", p1);
						session.commit();
						session.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
				
				public static void deleteTab (Integer tabId) {
					String resource = "org/first/mvc/mybatis_config.xml";
					InputStream inputStream;
					Post p1 = new Post();
					p1.setTabId(tabId);
					p1.setIsDel(1);
					try {
						inputStream = Resources.getResourceAsStream(resource);
						SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
						SqlSession session = sqlSessionFactory.openSession();
						session.update("org.first.mvc.BaseMapper.updateIsDelTab", p1);
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
				    

				    
				    public static String calDateBetweenAndB4 (Date dueday, Date today){
				    	String result = "";
				    	long calDate = 0;
				    	long calDateDays = 0;

					    	Date FirstDate = dueday;
					    	Date SecondDate = today;
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
					 
					 
		 // 최적화 이후 정리중 *******
					 
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
					 
					 
					 
				 //로그인 성공시 아이디를 넣어 유저번호와 탭번호를 리턴.
					 
					 public static List<Member> loginSucceedGetUserInfo (String id){
						List<Member> result = new ArrayList<Member>();
						String resource = "org/first/mvc/mybatis_config.xml";
						InputStream inputStream;
						//System.out.println("처음 받아온 아이디 "+id);
						try {
							inputStream = Resources.getResourceAsStream(resource);
							SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
							SqlSession session = sqlSessionFactory.openSession();
							result = session.selectList("org.first.mvc.BaseMapper.getUserIdTabId", id);
						} catch (IOException e) {
							e.printStackTrace();
						}
						 return result;
					 }
					 
					 //로그인하고 받아온거에서 지워진거 없애줘야함.
					 public static List<Member> getfriendAmdList (Integer tabId) {
						 List<Member> result = new ArrayList<Member>();
							String resource = "org/first/mvc/mybatis_config.xml";
							InputStream inputStream;
							try {
								inputStream = Resources.getResourceAsStream(resource);
								SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
								SqlSession session = sqlSessionFactory.openSession();
								result = session.selectList("org.first.mvc.BaseMapper.getFriendAdmList", tabId);
							} catch (IOException e) {
								e.printStackTrace();
							}
							 return result;
					 }

				 //유저번호와 탭번호중 가장최근의 정보를 리턴 
					 public static Member getUserIdTabId(List<Member> loginSucceedGetUserInfo, String id) {
						 
							Member result = new Member();
							Integer userId = 0;
							Integer tabId = 0;
							Integer tabCheck = 0;
							Integer friCheck = 1;
							
							//System.out.println("getUserIdTabId 돌아갑니다.");
							if(loginSucceedGetUserInfo != null) {
								//System.out.println("내용이 있네요 ");
								List<Member> friTab = new ArrayList<Member>();
								List<Member> myTab = new ArrayList<Member>();
								
								for(int i=0 ; i<loginSucceedGetUserInfo.size(); i++) {
									if(loginSucceedGetUserInfo.get(i).getTabId() == null && loginSucceedGetUserInfo.get(i).getUserId() != null) {
										//System.out.println("여기야 "+ loginSucceedGetUserInfo.get(i).getUserId() );
										userId=loginSucceedGetUserInfo.get(i).getUserId();
										
									
									}else if(loginSucceedGetUserInfo.get(i).getTabId() != null && loginSucceedGetUserInfo.get(i).getIsDel() == 0) {
										//System.out.println("내탭이 있어서 여기로왔어요 : "+ loginSucceedGetUserInfo.get(i).getTabId() +"번 탭아이디가 있네요");
										myTab.add(loginSucceedGetUserInfo.get(i));
										userId = myTab.get(0).getUserId();
										tabId = myTab.get(0).getTabId();
										tabCheck = 1;
										if(loginSucceedGetUserInfo.get(i).getfUserId() == null || loginSucceedGetUserInfo.get(i).getF_isDel() == 1) {
											friCheck = 0;
										}
										break;
									}else if(loginSucceedGetUserInfo.get(i).getFt_tabId() != null && loginSucceedGetUserInfo.get(i).getFt_isDel() == 0){ 
										friTab.add(loginSucceedGetUserInfo.get(i));
										//System.out.println("공유가 있어서 여기로왔어요 : "+ loginSucceedGetUserInfo.get(i).getFt_tabId() +"번 탭아이디가 있네요");
										userId = friTab.get(0).getUserId();
										tabId = friTab.get(0).getFt_tabId();
										tabCheck = 2;
										if(loginSucceedGetUserInfo.get(i).getfUserId() == null || loginSucceedGetUserInfo.get(i).getF_isDel() == 1) {
											friCheck = 0;
										}
									    break;
									}
								}
							}else {
								Member unTabMember = getMemberToId(id);
								userId = unTabMember.getUserId();
								tabId = unTabMember.getFt_tabId();
								tabCheck = 0;
							}
								result.setId(id);
								result.setUserId(userId);
								result.setTabId(tabId);
								result.setTabCheck(tabCheck);
								result.setFriCheck(friCheck);
							
							return result ;
						}

					 
				 //나와 관련된 모든 멤버들 (나포함) 유저정보를 긁어온다.
					 public static List<Member> getMemberList(Integer userId) {
							
						    List<Member> result = new ArrayList<Member>();
							String resource = "org/first/mvc/mybatis_config.xml";
							InputStream inputStream;
							
							try {
								inputStream = Resources.getResourceAsStream(resource);
								SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
								SqlSession session = sqlSessionFactory.openSession();
								result = session.selectList("org.first.mvc.BaseMapper.getMemberList", userId);
							 	
							} catch (IOException e) {
								e.printStackTrace();
							}
							return result ;
						}
				//유저번호를 주고 내정보만 리턴.
					 public static Member userInformation (Member userIdTabId) {
						 
						 Member result = new Member();
						 Member userInformation = new Member();
						 Member friends = new Member();
						 List<Post> projectList = getProjectList(userIdTabId.getUserId());
						//tabId,tabTitle,isDel,lastUpdate,create,userNum,tab_intro,tab_dueDay
						 //System.out.println("몇개가져왔는데? : " + projectList.size() );
						 if(userIdTabId.getFriCheck() == 1) {
							 List<Member> memberList = getMemberList(userIdTabId.getUserId()); 
							 
								 for(int i=0 ; i<memberList.size(); i++) {
										
										if(userIdTabId.getUserId() == memberList.get(i).getUserId()) {
											//System.out.println(userIdTabId.getUserId() +"userIdTabId.getUserId() == memberList.get(i).getUserId()"+ memberList.get(i).getUserId());
											result.setUserId(memberList.get(i).getUserId());
											result.setTabId(userIdTabId.getTabId());
											//System.out.println("result.getUserId()"+result.getUserId());
											result.setId(memberList.get(i).getId());
											result.setNickName(memberList.get(i).getNickName());
											result.setMb_introduce(memberList.get(i).getMb_introduce());
											result.setUserImg(memberList.get(i).getUserImg());
											if(result.getUserImg() == null) {
												result.setUserImg("gucci.png");
											}
											result.setModified(memberList.get(i).getModified());
											result.setCreate(memberList.get(i).getCreate());
											result.setLastModified(dateToString(memberList.get(i).getModified()));
											if(result.getLastModified() != null) {	
											}
											break;
										}
									} 
								 
							 userInformation = result;
							 userInformation.setFriendsInformationList(friendsInformationList(memberList,userInformation).getFriendsInformationList()); 
							 friends = friendsTypeSet(userInformation,userIdTabId);
							 result.setiApproveAdmList(friends.getiApproveAdmList()); 
							 result.setFriendApproveAdmList(friends.getFriendApproveAdmList());
							 result.setiWaitAdmList(friends.getiWaitAdmList());
							 result.setWaitingAdmList(friends.getWaitingAdmList());
							 result.setFriendAdmList(friends.getFriendAdmList());
						 }else {
							 System.out.println("여기로와야지" + userIdTabId.getUserId() +"번 아이디");
							 result = getMember(userIdTabId.getUserId());
							 result.setLastModified(dateToString(dateChangeAction2(result.getModified())));
						 }
						 
						//id(email주소),nickName,mb_introduce,userImg,modified,create

						 Member tabs = projectList(projectList,friends,userIdTabId);

						 result.setTabList(tabs.getTabList());
						 result.setFriTabList(tabs.getFriTabList());
						 result.setCompleteTabList(tabs.getCompleteTabList());
						 result.setAllTabList(projectList);
						 result.setTabId(userIdTabId.getTabId());
						 System.out.println(result.getTabId() + "여기에요 여기에서 없어요 탭아이디");
						 return result;
					 }
					 
				//유저번호를 주고 날뺀 친구목록을 만든다.
					 public static Member friendsInformationList (List<Member> memberList, Member userIdTabId) {
						 Member result = new Member();
						 
						 List<Member> p2 = new ArrayList<Member>();
						 List<Member> p3 = memberList;
							for (int i=0 ; i<memberList.size(); i++) {
								List<Member> p1=new ArrayList<Member>();
								//System.out.println("친구들 아이디좀 볼까 " + memberList.get(i).getUserId());
								if(p3.get(i).getUserId() != userIdTabId.getUserId()) {
									if(p3.get(i).getUserImg() == null) {
										p3.get(i).setUserImg("gucci.png");
									}
									p1.add(p3.get(i));
								}
							 p2.addAll(p1);
							}
						 
						 result.setFriendsInformationList(p2);
						 return result;
					 }
					 
					 
					 
					 //친구정보를 넣어 4가지로 분류해서 리턴.
					 public static Member friendsTypeSet (Member userInformation ,Member userIdTabId ) {
						 Member result = new Member();
						 List<Member> p1 = new ArrayList<Member>();
						 List<Member> p2 = new ArrayList<Member>();
						 List<Member> iApproveAdmList = new ArrayList<Member>();
						 List<Member> friendApproveAdmList = new ArrayList<Member>();
						 List<Member> iWaitAdmList = new ArrayList<Member>();
						 List<Member> waitingAdmList = new ArrayList<Member>();
						 List<Member> friendAdmList = new ArrayList<Member>();

						 p1 = userInformation.getFriendsInformationList();
						 p2 = getfriendAmdList(userIdTabId.getTabId());
						 
						 for (int i=0 ; i<p1.size(); i++) {
							//내추 친수
							 if(p1.get(i).getMyId() == userIdTabId.getUserId() && p1.get(i).getUserId() != userIdTabId.getUserId() && p1.get(i).getAdm() == 1) {
								 //분류된 친구들중 이탭에 이미 공유된 친구가 있으면 체크 = 1
								 for (int j=0 ; j<p2.size(); j++) {
									 if(p1.get(i).getUserId() == p2.get(j).getFt_userId()) {
										 p1.get(i).setCheck(1);
									 }
								 }
								 iApproveAdmList.add(p1.get(i));
							//친추 내수
							 }else if(p1.get(i).getfUserId() == userIdTabId.getUserId() && p1.get(i).getUserId() != userIdTabId.getUserId() && p1.get(i).getAdm() == 1 ) {
								//분류된 친구들중 이탭에 이미 공유된 친구가 있으면 체크 = 1
								 for (int j=0 ; j<p2.size(); j++) {
									 if(p1.get(i).getUserId() == p2.get(j).getFt_userId()) {
										 p1.get(i).setCheck(1);
									 }
								 }
								 friendApproveAdmList.add(p1.get(i));
							//내추 친안수
							 }else if(p1.get(i).getMyId() == userIdTabId.getUserId() && p1.get(i).getUserId() != userIdTabId.getUserId() && p1.get(i).getAdm() == 0) {
								
								 iWaitAdmList.add(p1.get(i));
							//친추 친안수
							 }else if(p1.get(i).getfUserId() == userIdTabId.getUserId() && p1.get(i).getUserId() != userIdTabId.getUserId() && p1.get(i).getAdm() == 0){
								 
								 waitingAdmList.add(p1.get(i));
							 }
							
						 }
						 result.setiApproveAdmList(iApproveAdmList); 
						 if(iApproveAdmList != null) {
							 //System.out.println("친구 확인해볼게요 iApproveAdmList : "+iApproveAdmList.size()); 
						 }
						 result.setFriendApproveAdmList(friendApproveAdmList);
						 if(friendApproveAdmList != null) {
							 //System.out.println("친구 확인해볼게요 friendApproveAdmList : "+friendApproveAdmList.size()); 
						 }
						 result.setiWaitAdmList(iWaitAdmList);
						 if(iWaitAdmList != null) {
							 //System.out.println("친구 확인해볼게요 iWaitAdmList : "+iWaitAdmList.size()); 
						 }
						 result.setWaitingAdmList(waitingAdmList);
						 if(waitingAdmList != null) {
							 //System.out.println("친구 확인해볼게요 waitingAdmList : "+waitingAdmList.size()); 
						 }
						 //탭에 공유되어 있는 친구들
						 int friAdmCheck = 0;
						 for(int i=0 ; i<p2.size() ; i++) {
							 for(int j=0 ; j<p1.size() ; j++) {
								 //System.out.println(p2.get(i).getFt_userId() + "번친구랑 " + p1.get(j).getUserId() + "번 친구 비교중");
								 if(p2.get(i).getFt_userId() == p1.get(j).getUserId()) {
									 p1.get(j).setIdN247_ft(p2.get(i).getIdN247_ft());
									 p1.get(j).setCheck(0);
									 friendAdmList.add(p1.get(j));
									 friAdmCheck = 1;
									 //System.out.println("공유친구 확인좀 위  : " + p1.get(j).getUserId() +"번 정보 넣는중");
								 }
							 }
							 
						 }
						 
						 if(friAdmCheck == 0) {
							 for(int i=0 ; i<p2.size() ; i++) {
								 
									 if(p2.get(i).getFt_userId() ==  userIdTabId.getUserId()) {
										 
									 }else {
										 Member p3 = getMember(p2.get(i).getFt_userId());
										 p3.setIdN247_ft(p2.get(i).getIdN247_ft());
										 p3.setCheck(1);
										 friendAdmList.add(p3);
										 //System.out.println("공유친구 확인좀 아래 : " + p2.get(i).getFt_userId() +"번 정보 넣는중");
									 }
								 }
						 }
						 
						 result.setFriendAdmList(friendAdmList);
						 return result;
					 }
					 
				 // 유저번호를 주고 탭목록을 가져온다.
					 public static List<Post> getProjectList(Integer userId) {
							//System.out.println("getProjectList 실행하기위해 받아온 파라미터 userId:" + userId);
						    List<Post> result = new ArrayList<Post>();
							String resource = "org/first/mvc/mybatis_config.xml";
							InputStream inputStream;
							
							try {
								inputStream = Resources.getResourceAsStream(resource);
								SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
								SqlSession session = sqlSessionFactory.openSession();
								result = session.selectList("org.first.mvc.BaseMapper.getProjectList", userId);
							 	//System.out.println("받아온 결과 리스트 :"+result.size());
							} catch (IOException e) {
								e.printStackTrace();
							}
							return result ;
						}
					 
				// 전체 프로젝트 리스트를 받아 나의 프로젝트와 공유 프로젝트를 분류해서 리턴. 
					 public static Member projectList (List<Post> projectList, Member friends, Member userIdTabId) {
						 Member result = new Member();
						
						 List<Post> tabList = new ArrayList<Post>();
							List<Post> friTabList = new ArrayList<Post>();
							List<Post> completeTabList = new ArrayList<Post>();
							
								for(int i=0 ; i<projectList.size(); i++) {
									List<Post> p1 = new ArrayList<Post>();
									p1 = projectList;
									if(p1.get(i).getUserNum() == userIdTabId.getUserId() && p1.get(i).getIsDel() == 0) {
										
										if(p1.get(i).getTabId() == userIdTabId.getTabId()) {
											p1.get(i).setCheck(1);
										}
										//System.out.println("tabList.add(p1.get(i)); 이거하러옴.");
										tabList.add(p1.get(i));
									}else if(p1.get(i).getUserNum() == userIdTabId.getUserId() && p1.get(i).getIsDel() == 3){
										if(p1.get(i).getTabId() == userIdTabId.getTabId()) {
											p1.get(i).setCheck(1);
										}
										completeTabList.add(p1.get(i));
									}else {
										if(friends.getiApproveAdmList() != null) {
											//탭주인들의 얼굴이미지 주소를 넣어줘야한다.
											for(int j=0 ; j<friends.getiApproveAdmList().size() ; j++) {
												//여기서 저 리스트안에 없는 이탭에 공유가 안된친구의 얼굴이다. 근데 있긴 있지 
												if(p1.get(i).getUserNum() == friends.getiApproveAdmList().get(j).getUserId()) {
													
													p1.get(i).setImgName(friends.getiApproveAdmList().get(j).getUserImg());
													p1.get(i).setNick(friends.getiApproveAdmList().get(j).getNickName());
													if (friends.getiApproveAdmList().get(j).getUserImg() == null) {
														p1.get(i).setImgName("gucci.png");	
													}
												}
											}
										}
										if(friends.getFriendApproveAdmList() != null) {
											for(int j=0 ; j<friends.getFriendApproveAdmList().size() ; j++) {
													if(p1.get(i).getUserNum() == friends.getFriendApproveAdmList().get(j).getUserId()) {
													
													p1.get(i).setImgName(friends.getFriendApproveAdmList().get(j).getUserImg());
													p1.get(i).setNick(friends.getFriendApproveAdmList().get(j).getNickName());
													if (friends.getFriendApproveAdmList().get(j).getUserImg() == null) {
														p1.get(i).setImgName("gucci.png");	
													}
												}
											}
												
											//System.out.println("friTabList.add(p1.get(i)); 이거하러옴.");
											friTabList.add(p1.get(i));
										}
										
										
									}
								}
						 
						 result.setTabList(tabList);
						 //System.out.println("리스트 띄어야해 있냐 : " + result.getTabList().size());
						 if(completeTabList != null) {
							 result.setCompleteTabList(completeTabList);
						 }
						 if(friends != null) {
							 result.setFriTabList(friTabList); 
						 }
						 
						 
						 return result;
					 }
					 
				 //현재탭의 정보세팅 *****
					 public static Post setThisTab (Integer tabId, Member userInformation, Post cards) {
						 Post result = new Post();
						 Post thisTab = new Post();
								String tabTitle = "프로젝트 이름";
								String tabIntro = "탭소개글";
								Date tab_dueDay = new Date();
								Date tab_create = new Date();
								Date lastUpdate = new Date();
							//현재탭의 탭번호 
								thisTab.setTabId(tabId);
								//System.out.println("받아온 탭아이디 : " + tabId);
							//현재탭이 공유탭인지 판단	
								int tabAdmCheck = 0;
								int tabCompCheck = 0;
								System.out.println("체크중 : " + tabCompCheck);
									if(userInformation.getTabList() != null) {
										//System.out.println("내탭중 찾고있음. userInformation.getTabList().size() =  " + userInformation.getTabList().size());
										
										for(int i=0 ; i<userInformation.getTabList().size(); i++) {
											List<Post> p1 = new ArrayList<Post>();
											p1 = userInformation.getTabList();
											//System.out.println(i+"번째 "+userInformation.getTabList().get(i).getTabId()+"탭 체크중 : " + userInformation.getTabList().get(i).getTabTitle());
											//탭아이디가 나의 공유탭목록중에 있다면 tabAdmCheck = 1
											if(p1.get(i).getTabId() == tabId) {
												tabAdmCheck = 0;
												tabTitle = p1.get(i).getTabTitle();
												//System.out.println("tabTitle = p1.get(i).getTabTitle(); 체크 : " + p1.get(i).getTabTitle());
												tabIntro = p1.get(i).getTab_intro();
												tab_dueDay = p1.get(i).getTab_dueDay();
												tab_create = p1.get(i).getCreate();
												lastUpdate = p1.get(i).getLastUpdate();
											}
										}
										
									}
									
									if(userInformation.getCompleteTabList() != null) {
										//System.out.println("내탭중 찾고있음. userInformation.getTabList().size() =  " + userInformation.getTabList().size());
										
										for(int i=0 ; i<userInformation.getCompleteTabList().size(); i++) {
											List<Post> p1 = new ArrayList<Post>();
											p1 = userInformation.getCompleteTabList();
											
											if(p1.get(i).getTabId() == tabId) {
												System.out.println("탭이 여기로 왔어 :" + p1.get(i).getTabId() +"보는중" + tabId);
												tabAdmCheck = 0;
												tabCompCheck = 1;
												System.out.println("체크중 : " + tabCompCheck);
												tabTitle = p1.get(i).getTabTitle();
												//System.out.println("tabTitle = p1.get(i).getTabTitle(); 체크 : " + p1.get(i).getTabTitle());
												tabIntro = p1.get(i).getTab_intro();
												tab_dueDay = p1.get(i).getTab_dueDay();
												tab_create = p1.get(i).getCreate();
												lastUpdate = p1.get(i).getLastUpdate();
											}
										}
										
									}
								
									
									if(userInformation.getFriTabList() != null) {
										//System.out.println("공유탭중 찾고 있음. ");
										for(int i=0 ; i<userInformation.getFriTabList().size(); i++) {
											List<Post> p1 = new ArrayList<Post>();
											p1 = userInformation.getFriTabList();
											
											//탭아이디가 나의 공유탭목록중에 있다면 tabAdmCheck = 1
											if(p1.get(i).getTabId() == tabId) {
												tabAdmCheck = 1;
												tabTitle = p1.get(i).getTabTitle();
												//System.out.println("tabTitle = p1.get(i).getTabTitle(); 체크 : " + p1.get(i).getTabTitle());
												tabIntro = p1.get(i).getTab_intro();
												tab_dueDay = p1.get(i).getTab_dueDay();
												tab_create = p1.get(i).getCreate();
												lastUpdate = p1.get(i).getLastUpdate();
											}
										}
									}
									
								thisTab.setTabCompCheck(tabCompCheck);
								System.out.println("체크중 : " + tabCompCheck + "세팅한건? " + thisTab.getTabCompCheck());
								thisTab.setTabAdmCheck(tabAdmCheck);
							//현재탭의 제목입력
								thisTab.setTabTitle(tabTitle);
							//현재탭의 소개글 입력
								thisTab.setTab_intro(tabIntro);
							//현재탭의 진행률 입력
									Double tabProgress = 0.0;
									tabProgress = tabProgress(tabId,tab_dueDay,tab_create);
								thisTab.setTabProgress(tabProgress);
							//현재탭의 진행상태 색깔 입력
									String tabProgressBg = "danger";
									tabProgressBg = progressBg(tabProgress);
								thisTab.setTabProgressBg(tabProgressBg);
							//현재탭의 마감일에 대한 정보입력 
									String tab_dueMessage = "마감일 입력";
									if(tabCompCheck == 1) {
										tab_dueMessage = "프로젝트가 완료 되었습니다.";
									}else {
										tab_dueMessage = tab_dueMessage(tab_dueDay);
									}
								thisTab.setDueMessage(tab_dueMessage);
								if(tab_dueMessage == "마감일이 지났습니다." || tab_dueMessage == "프로젝트가 완료 되었습니다.") {
									thisTab.setDueCheck(1);
								}else {
									thisTab.setDueCheck(0);
								}
								
							//탭안의 카드생성시 완료제한날짜를 정해준다.
									String maxDay = "";
									String minDay = "";
									maxDay = dateChangeMaxAction(tab_dueDay);
								thisTab.setMaxDay(maxDay);
								Date date_now = new Date(System.currentTimeMillis());
									minDay = dateChangeMaxAction(date_now);
								thisTab.setMinDay(minDay);
							//현재탭 삭제시 포스트가 없거나 이동시킬 나의 프로젝트가 없으면 = 0 (포스트를 함께 삭제 할 수 있게 해준다.)
								int tabSelectCheck = 0;
									
									if(cards.getPostList().size() != 0 || cards.getCompletePostList().size() != 0) {
										tabSelectCheck = 1;
										//System.out.println("탭셀렉트가 있어서 체크 " +cards.getPostList().size()+cards.getCompletePostList().size());
//									}else if (userInformation.getTabList() != null) {
//										tabSelectCheck = 1;
									}
									
								thisTab.setTabSelectCheck(tabSelectCheck);
								//System.out.println("아니 포스트가 없다고 :" + tabSelectCheck);
							//탭주인의 이름 내가 아닐 수도 있다.
									String tabUserNick = "닉네임 없음";
									for(int i=0 ; i<userInformation.getAllTabList().size(); i++) {
										List<Post> p1 = new ArrayList<Post>();
										p1 = userInformation.getAllTabList();
										if(p1.get(i).getTabId() == tabId) {
											//탭목록중 탭주인의 번호와 내번호가 맞다면 내정보를 넣어라 
											if(p1.get(i).getUserNum() == userInformation.getUserId()) {
												tabUserNick = userInformation.getNickName();
											}else {
												for(int j=0 ; j<userInformation.getFriendsInformationList().size() ; j++) {
													//탭목록중 탭의 주인 번호와 내친구들의 번호가 맞다면 
													//System.out.println("친구이름 넣기 "+i+"번째 반복중 : " + userInformation.getFriendsInformationList().get(j).getUserId() +"와 "+p1.get(i).getUserNum()+"의 매칭실패 ");
													if(p1.get(i).getUserNum() == userInformation.getFriendsInformationList().get(j).getUserId() ) {
														//System.out.println("찾았어요 넣을게요 친구이름 : " + userInformation.getFriendsInformationList().get(j).getNickName());
														tabUserNick = userInformation.getFriendsInformationList().get(j).getNickName();
														//단서, 위의 friendAllList 에는 나를 공유시킬 수 있는 수락된 친구들의 정보가 있으면 된다.
													}
												}
											}
										}
									}
							
								thisTab.setNick(tabUserNick);
							//마지막 업데이트시간을 정해주자
								//탭마지막 업데이트시간 넣어, 포스트1번 애들 마지막시간 넣어 ,진행,완료 , 계산해서 오늘과의 차이가 가장작은아이를 마직막업데이트로 만든다.
								String tabLastUpdate = tabLastUpdateSet(lastUpdate,cards);
								thisTab.setTabLastUpdate(tabLastUpdate);
								
						//현재탭의 정보세팅 끝.
						 
						 result = thisTab;
						 
						 return result;
					 }
					 
				 //유저아이디를 주고 포스트를 받아오자 모든 
					 public static List<Post> getPostList (Member userIdTabId ) {
						
						 	List<Post> result = new ArrayList<Post>();
						 	String resource = "org/first/mvc/mybatis_config.xml";
							InputStream inputStream;
							Post p1 = new Post();
							p1.setTabId(userIdTabId.getTabId());
							 System.out.println("getPostList돌아가는중 tabId 왔나요 :" + p1.getTabId());
							try {
								inputStream = Resources.getResourceAsStream(resource);
								SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
								SqlSession session = sqlSessionFactory.openSession();
								result = session.selectList("org.first.mvc.BaseMapper.getPostList", p1);
								
							 	System.out.println("getPostList 에서 몇개 가져왔니 :" + result.size());
								
							} catch (IOException e) {
								e.printStackTrace();
							}

						 return result;
					 }
					 
				//검색어 입력시 유저명과 포스트 제목,내용등을 검색해 탭번호와 함께 리턴
					 public static List<Post> searchPostNick (String search , Integer userNum) {
						 	List<Post> result = new ArrayList<Post>();
						 	List<Post> p2 = new ArrayList<Post>();
						 	String resource = "org/first/mvc/mybatis_config.xml";
							InputStream inputStream;
							Post p1 = new Post();
							p1.setSearch(search);
							p1.setUserNum(userNum);
							
							try {
								inputStream = Resources.getResourceAsStream(resource);
								SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
								SqlSession session = sqlSessionFactory.openSession();
								p2 = session.selectList("org.first.mvc.BaseMapper.searchPostNick", p1);
							    //System.out.println("검색결과 몇개 받아왔나 볼까 : " + result.size());
							    
							    if(p2 != null) {
							    	List<Post> p3 = new ArrayList<Post>();
							    	for(int i=0 ; i<p2.size() ; i++) {
							    		if(p2.get(i).getIsDel() == 0 || p2.get(i).getIsDel() == 3) {
							    			p2.get(i).setKrCreate(dateToString(dateChangeAction2(p2.get(i).getLastUpdate())));
							    			p3.add(p2.get(i));
							    		}
							    	}
							     result.addAll(p3);
							    }
							 	
							} catch (IOException e) {
								e.printStackTrace();
							}

						 return result;
					 }
					
				 //카드리스트 받아서 분류해준다.
					 
					 public static Post cardsSet (Member userIdTabId) {
						 Post result = new Post();
						 List<Post> p1 = (getPostList(userIdTabId));;
						 Date date_now = new Date(System.currentTimeMillis()); 
						 
					 //postList, replyList 를 분류해준다.
						 
					 //postList부분
						 List<Post> postAllList = new ArrayList<Post>();
						 Integer postCheck = 0;
						 for(int i=0 ; i<p1.size() ; i++) {
							 Integer p4 = p1.get(i).getId();
							 List<Post> p2 = new ArrayList<Post>();
							 if(postCheck.equals(p4)) {
							 }else if(p1.get(i).getUserId() == p1.get(i).getUserNum()){
								 postCheck = p1.get(i).getId();
								 p2.add(p1.get(i)); 
							 }
							 postAllList.addAll(p2);
						 }
					 //진행중 카드와 완료카드를 분류해준
						 List<Post> postList = new ArrayList<Post>();
						 List<Post> completePostList = new ArrayList<Post>();
						 for(int i=0 ; i<postAllList.size() ; i++) {
							 if(postAllList.get(i).getIsDel() == 0) {
								 postList.add(postAllList.get(i));
							 }else {
								 completePostList.add(postAllList.get(i));
							 }
						 }
					 //분류된 카드들을 꾸며줘서 세팅함 .
						 result.setPostList(postSet(postList,userIdTabId));
						 result.setCompletePostList(postSet(completePostList,userIdTabId));
						 
					 //replyList부분
						 List<Post> replyAllList = new ArrayList<Post>();
						 List<Post> p6 = new ArrayList<Post>();
						 for(int i=0 ; i<p1.size() ; i++) {
							 List<Post> p3 = new ArrayList<Post>();
							 if(p1.get(i).getIdN247_re() != null) {
								 if(p1.get(i).getUserId().equals(p1.get(i).getN247_reUsId())) {
									 p3.add(p1.get(i));  
								 }
							 }
							 p6.addAll(p3);
						 }
						 replyAllList.addAll(p6);
						 
					 //각 포스트별로 리플라이 리스트를 넣어준다.
						 //유저정보 함께 있음 (nickName,userImg)
						 if(replyAllList != null) {
							 
							 for(int i=0 ; i<result.getPostList().size(); i++) {
								 List<Post> p4 = new ArrayList<Post>();
								 for(int j=0 ; j<replyAllList.size(); j++) {
									 List<Post> replyList = new ArrayList<Post>();
									 if(result.getPostList().get(i).getId().equals(replyAllList.get(j).getN247_rePoId())) {
										 replyAllList.get(j).setKrCreate(calCardDueDate(dateChangeAction2(replyAllList.get(j).getLastModified()),date_now));
										 replyList.add(replyAllList.get(j));
									 }
									 p4.addAll(replyList);
								 }
								 result.getPostList().get(i).setReplyList(p4);
							 }
							 for(int i=0 ; i<result.getCompletePostList().size(); i++) {
								 List<Post> p5 = new ArrayList<Post>();
								 for(int j=0 ; j<replyAllList.size(); j++) {
									 List<Post> replyList = new ArrayList<Post>();
									 if(result.getCompletePostList().get(i).getId().equals(replyAllList.get(j).getN247_rePoId())) {
										 replyAllList.get(j).setKrCreate(calCardDueDate(dateChangeAction2(replyAllList.get(j).getLastModified()),date_now));
										 replyList.add(replyAllList.get(j));
									 }
									p5.addAll(replyList); 
								 }
								 result.getCompletePostList().get(i).setReplyList(p5);
							 } 
						 }
						 
						 
						 return result;
					 }
					 
					 
					 //Ajax 용 cards
					 
					 public static List<Post> cardsList (List<Post> cardsList, Member userIdTabId) {
						 List<Post> result = new ArrayList<Post>();
						 List<Post> p1 = (cardsList);
						 Date date_now = new Date(System.currentTimeMillis()); 
						 
					 //postList, replyList 를 분류해준다.
						 
					 //postList부분
						 List<Post> postAllList = new ArrayList<Post>();
						 Integer postCheck = 0;
						 for(int i=0 ; i<p1.size() ; i++) {
							 Integer p4 = p1.get(i).getId();
							 List<Post> p2 = new ArrayList<Post>();
							 if(postCheck.equals(p4)) {
							 }else {
								 postCheck = p1.get(i).getId();
								 p2.add(p1.get(i)); 
							 }
							 postAllList.addAll(p2);
						 }
					 //진행중 카드와 완료카드를 분류해준
						 List<Post> postList = new ArrayList<Post>();
						 
						 for(int i=0 ; i<postAllList.size() ; i++) {
							 if(postAllList.get(i).getIsDel() == 0) {
								 postList.add(postAllList.get(i));
							 }
						 }
					 //분류된 카드들을 꾸며줘서 세팅함 .
						 result = (postSet(postList,userIdTabId));		 
						 
					 //replyList부분
						 List<Post> replyAllList = new ArrayList<Post>();
						 List<Post> p6 = new ArrayList<Post>();
						 for(int i=0 ; i<p1.size() ; i++) {
							 List<Post> p3 = new ArrayList<Post>();
							 if(p1.get(i).getIdN247_re() != null) {
								 if(p1.get(i).getUserId().equals(p1.get(i).getN247_reUsId())) {
									 p3.add(p1.get(i));  
								 }
							 }
							 p6.addAll(p3);
						 }
						 replyAllList.addAll(p6);
						 
					 //각 포스트별로 리플라이 리스트를 넣어준다.
						 //유저정보 함께 있음 (nickName,userImg)
						 if(replyAllList != null) {
							 
							 for(int i=0 ; i<result.size(); i++) {
								 List<Post> p4 = new ArrayList<Post>();
								 for(int j=0 ; j<replyAllList.size(); j++) {
									 List<Post> replyList = new ArrayList<Post>();
									 if(result.get(i).getId().equals(replyAllList.get(j).getN247_rePoId())) {
										 replyAllList.get(j).setKrCreate(calCardDueDate(dateChangeAction2(replyAllList.get(j).getLastModified()),date_now));
										 replyList.add(replyAllList.get(j));
									 }
									 p4.addAll(replyList);
								 }
								 result.get(i).setReplyList(p4);
							 }
						 }

						 return result;
					 }
					 
					 public static List<Post> compCardsList (Member userIdTabId) {
						 List<Post> result = new ArrayList<Post>();
						 List<Post> p1 = (getPostList(userIdTabId));;
						 Date date_now = new Date(System.currentTimeMillis()); 
						 
					 //postList, replyList 를 분류해준다.
						 
					 //postList부분
						 List<Post> postAllList = new ArrayList<Post>();
						 Integer postCheck = 0;
						 for(int i=0 ; i<p1.size() ; i++) {
							 Integer p4 = p1.get(i).getId();
							 List<Post> p2 = new ArrayList<Post>();
							 if(postCheck.equals(p4)) {
							 }else {
								 postCheck = p1.get(i).getId();
								 p2.add(p1.get(i)); 
							 }
							 postAllList.addAll(p2);
						 }
					 //진행중 카드와 완료카드를 분류해준
						 
						 List<Post> completePostList = new ArrayList<Post>();
						 for(int i=0 ; i<postAllList.size() ; i++) {
							 if(postAllList.get(i).getIsDel() != 0) {
								 completePostList.add(postAllList.get(i));
							 }
						 }
					 //분류된 카드들을 꾸며줘서 세팅함 .
						 
						 result = (postSet(completePostList,userIdTabId));
						 
					 //replyList부분
						 List<Post> replyAllList = new ArrayList<Post>();
						 List<Post> p6 = new ArrayList<Post>();
						 for(int i=0 ; i<p1.size() ; i++) {
							 List<Post> p3 = new ArrayList<Post>();
							 if(p1.get(i).getIdN247_re() != null) {
								 if(p1.get(i).getUserId().equals(p1.get(i).getN247_reUsId())) {
									 p3.add(p1.get(i));  
								 }
							 }
							 p6.addAll(p3);
						 }
						 replyAllList.addAll(p6);
						 
					 //각 포스트별로 리플라이 리스트를 넣어준다.
						 //유저정보 함께 있음 (nickName,userImg)
						 if(replyAllList != null) {
							 
							 for(int i=0 ; i<result.size(); i++) {
								 List<Post> p4 = new ArrayList<Post>();
								 for(int j=0 ; j<replyAllList.size(); j++) {
									 List<Post> replyList = new ArrayList<Post>();
									 if(result.get(i).getId().equals(replyAllList.get(j).getN247_rePoId())) {
										 replyAllList.get(j).setKrCreate(calCardDueDate(dateChangeAction2(replyAllList.get(j).getLastModified()),date_now));
										 replyList.add(replyAllList.get(j));
									 }
									 p4.addAll(replyList);
								 }
								 result.get(i).setReplyList(p4);
							 }
						 }

						 return result;
					 }
					 
					 public static List<Post> postSet(List<Post> p1, Member userId){
						 
						 List<Post> result = new ArrayList<Post>();
						 Date date_now = new Date(System.currentTimeMillis()); 
						 List<Post> postList = p1;
						
						 for (int i=0 ; i<postList.size(); i++) {
							 if(postList.get(i).getId() != null) {
								 System.out.println("포스트 아이디가 있어서 실행중");
								 postList.get(i).setProgress(progress(postList.get(i).getCreate(),postList.get(i).getDueDay()));
								 postList.get(i).setProgressBg(progressBg(postList.get(i).getProgress()));
								 postList.get(i).setCompareMessage(calCardDueDate(dateChangeAction2(postList.get(i).getLastUpdate()),date_now));	
								 //System.out.println("일단 postAdmCheck 를 0으로 넣었어 ");
								 postList.get(i).setPostAdmCheck(0);
								 postList.get(i).setDueDayString(dateToString3(postList.get(i).getDueDay()));
								
								 if(postList.get(i).getUp_fileName() != null) {
									 postList.get(i).setCheck(1);
								 }else {
									 postList.get(i).setCheck(0);
								 }
								 
								 if(postList.get(i).getUserNum() == userId.getUserId()) {
									 postList.get(i).setPostAdmCheck(1);
								 }  
							 }
							
							//현재탭의 진행률 입력
								Double tabProgress = 0.0;
								tabProgress = tabProgress(postList.get(i).getTabId(),postList.get(i).getTab_dueDay(),dateChangeAction2(postList.get(i).getTab_create()));
								postList.get(i).setTabProgress(tabProgress);
								
							//현재탭의 진행상태 색깔 입력
								String tabProgressBg = "danger";
								tabProgressBg = progressBg(tabProgress);
								postList.get(i).setTabProgressBg(tabProgressBg);
								
							//현재탭의 마감일에 대한 정보입력 
								String tab_dueMessage = "마감일 입력";
								if(postList.get(i).getTabCompCheck() == 3) {
									tab_dueMessage = "프로젝트가 완료 되었습니다.";
								}else {
									tab_dueMessage = tab_dueMessage(postList.get(i).getTab_dueDay());
								}
								postList.get(i).setDueMessage(tab_dueMessage);
								
							//탭안의 카드생성시 완료제한날짜를 정해준다.
								String maxDay = "";
								maxDay = dateChangeMaxAction(postList.get(i).getTab_dueDay());
								postList.get(i).setMaxDay(maxDay);
								
							//마지막 업데이트시간을 정해주자
								//탭마지막 업데이트시간 넣어, 포스트1번 애들 마지막시간 넣어 ,진행,완료 , 계산해서 오늘과의 차이가 가장작은아이를 마직막업데이트로 만든다.
//								if(postList.get(i).getTab_LastUpdate() != null) {
//									System.out.println("돌아가는 중이에요 탭 마지막 업데이트 ");
//									String tabLastUpdate = dateToString(postList.get(i).getTab_LastUpdate());
//									postList.get(i).setTabLastUpdate(tabLastUpdate);
//								}
								
						 }
						 
						 result.addAll(postList);
						 
						
						 
						 return result;
					 }
					 
					
					 
					 
				 //오늘기준 현재탭의 마지막 업데이트와 탭안에 카드가 있다면 그 카드중 마지막 업데이트의 차가 적은것을 리턴
					 public static String tabLastUpdateSet (Date lastUpdate, Post cards) {
			
						 Date date_now = new Date(System.currentTimeMillis());
						 String result = "";
						 long tabLastDay = calDateBetweenAndB(dateChangeAction2(lastUpdate),date_now);
						 long cardsLastDay1 = tabLastDay;
						 long cardsLastDay2 = tabLastDay;
						 Date p_last = lastUpdate;
						 Date cp_last = lastUpdate;
						 long p1 = 0 ;
						 
						 if (cards.getPostList() != null) {
							 for(int i=0 ; i<cards.getPostList().size(); ) {
								 p_last = cards.getPostList().get(i).getLastUpdate();
								 cardsLastDay1 = calDateBetweenAndB(dateChangeAction2(p_last),date_now);
								 break;
							 }
							
						 }
						 
						 if (cards.getCompletePostList() != null) {
							 for(int i=0 ; i<cards.getCompletePostList().size(); ) {
								 cp_last = cards.getCompletePostList().get(i).getLastUpdate();
								 cardsLastDay2 = calDateBetweenAndB(dateChangeAction2(cp_last),date_now);
								 break;
							 }
							 
						 }
						 
						 if(cardsLastDay1 < cardsLastDay2) {
							 //System.out.println("여기가 위 cardsLastDay1 : "+cardsLastDay1+"  cardsLastDay2 :"+cardsLastDay2);
							 p1 = cardsLastDay1;
							 result = calCardDueDate(dateChangeAction2(p_last),date_now);
						 }else {
							 //System.out.println("여기는 아래 cardsLastDay1 : "+cardsLastDay1+"  cardsLastDay2 :"+cardsLastDay2);
							 p1 = cardsLastDay2;
							 result = calCardDueDate(dateChangeAction2(cp_last),date_now);
						 }
						 if(tabLastDay < p1) {
							 //System.out.println("여기는 tabLastDay : "+tabLastDay +"p1은 이걸로 결정됐네 :" + p1);
							 result = calCardDueDate(dateChangeAction2(lastUpdate),date_now);
						 }

						 return result;
					 }
					
					 
				 //탭아이디, 탭완료일, 탭생성일을 받아 탭의 진행률을 리턴 
					 public static Double tabProgress (Integer tabId, Date tab_dueDay, Date create) {
					    	Date date_now = new Date(System.currentTimeMillis());
					    	
					    	long totalDay = calDateBetweenAndB2(tab_dueDay,dateChangeAction2(create));
					    	long dueDay = calDateBetweenAndB2(tab_dueDay,date_now);
					    	////System.out.println("탭 듀데이는 :"+dueDay);
					    	if(dueDay < 0) {
					    		dueDay = 0;
					    	}
					    	int p1 = Long.valueOf(dueDay).intValue();
					    	int p2 = Long.valueOf(totalDay).intValue();
					    	
					    	Double result = 100.0 - (((double)p1/(double)p2) * 100.0) ;
					    	if(result < 0) {
					    		result = 100.0;
					    	}
					    	////System.out.println("100 - ((" + p1 +"/"+ p2 +")*100) = " + result );
					    	return result;
					    }
				//진행률 계산기	 
					 public static Double progress (Date create, Date due) {
					    	Date date_now = new Date(System.currentTimeMillis());
					    	long totalDay = calDateBetweenAndB(dateChangeAction2(create),dateChangeAction2(due));
					    	Double result = 100.0;
					    	
					    	if(totalDay == 0) {
					    		result = 100.0;
					    	}else {
					    		long dueDay = calDateBetweenAndB2(dateChangeAction2(due),date_now);
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
						    	if(result < 0) {
						    		result = 100.0;
						    	}
					    	}
					    	
					    	
					    	return result;
					    }
					
				 //탭진행률을 받아 메세지를 리턴 
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
				 //탭완료일을 받아 탭완료상황 메세지를 리턴 
					 public static String tab_dueMessage (Date dueDay){
					    	String result = "";
					    	long calDate = 0;
					    	long calDateDays = 0;
					    	Date date_now = new Date(System.currentTimeMillis());

						    	Date FirstDate = dueDay;
						    	Date SecondDate = date_now;
						    	calDate = FirstDate.getTime() - SecondDate.getTime();
						    	calDateDays = calDate / (24*60*60*1000);
						    	result = String.valueOf(calDateDays)+"일후 마감";
						    	////System.out.println("두 날짜의 차이 : " + calDateDays);
						    	if(calDateDays == 0) {
						    			result = "마감일 입니다.";
						    	}else if(calDateDays < 0) {
						    		result = "마감일이 지나 카드를 생성 할 수 없습니다.";
						    	}
					    	return result ;
					    }
					  
				 //탭안의 카드생성시 완료날짜 범위를 설정해주는 값을 리턴 
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
					 
					 public static String dateToString(Date p1) {
					    	
					    	SimpleDateFormat sdformat = new SimpleDateFormat("yy-MM-dd HH:mm:ss");
							String result = null;
							Calendar cal = Calendar.getInstance();
							cal.setTime(p1);
							result = sdformat.format(cal.getTime());

							return result ;
						}
					 
					 
					 public static String dateToString2(Date p1) {
					    	
					    	SimpleDateFormat sdformat = new SimpleDateFormat("yyyyMMddHHmmss");
							String result = null;
							Calendar cal = Calendar.getInstance();
							cal.setTime(p1);
							result = sdformat.format(cal.getTime());

							return result ;
						}
					 
					 public static String dateToString3(Date p1) {
					    	
					    	SimpleDateFormat sdformat = new SimpleDateFormat("yy.MM.dd");
							String result = null;
							Calendar cal = Calendar.getInstance();
							cal.setTime(p1);
							result = sdformat.format(cal.getTime());

							return result ;
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
						    	
						    	if(calDateDays > 20) {
					    			result = dateToString3(p1);
					    		}
						    	
						    	if(calDateDays > 6 && calDateDays < 21) {
						    		Double resultDays = 1.0 * calDateDays ;
						    		Double calDateWeek = resultDays / 7 ;
						    		for(int i=1 ; i<3 ; i++) {
						    			if(calDateWeek < i+1) {
						    				result = i+"주전";
						    				break;
						    			}
						    		}
						    		
						    	}
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
					    
					    public static Date dateChangeAction2(Date p1) {
							Date result = null;
							Calendar cal = Calendar.getInstance();
							cal.setTime(p1);
							cal.add(Calendar.HOUR, -9);
							result = cal.getTime();

							return result ;
						}
					    
					    public static Integer boardCheck(List<Member> p1, Integer tabId) {
					    	Integer result = 0;
					    	for(int i=0;i<p1.size(); i++) {
					    		if(p1.get(i).getTabId() == tabId && p1.get(i).getIsDel() == 0) {
					    			result = 1;
					    		}else if(p1.get(i).getFt_tabId()==tabId && p1.get(i).getFt_isDel() == 0) {
					    			result = 1;
					    		}
					    	}
					    	return result;
					    }
					    
					    public static List<Member> getFriTabList (Integer tabId){
					    	System.out.println("친구받아오기 실행해서 탭아이디 받아왔어요 : " + tabId);
							List<Member> result = new ArrayList<Member>();
							String resource = "org/first/mvc/mybatis_config.xml";
							InputStream inputStream;
							try {
								inputStream = Resources.getResourceAsStream(resource);
								SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
								SqlSession session = sqlSessionFactory.openSession();
								result = session.selectList("org.first.mvc.BaseMapper.getFriTabList", tabId);
							} catch (IOException e) {
								e.printStackTrace();
							}
							 return result;
						 }
					    
					    public static List<Member> getFriendsAdmList (Integer userId){
							List<Member> result = new ArrayList<Member>();
							String resource = "org/first/mvc/mybatis_config.xml";
							InputStream inputStream;
							try {
								inputStream = Resources.getResourceAsStream(resource);
								SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
								SqlSession session = sqlSessionFactory.openSession();
								result = session.selectList("org.first.mvc.BaseMapper.getFriendsAdmList", userId);
							} catch (IOException e) {
								e.printStackTrace();
							}
							 return result;
						 }
}
