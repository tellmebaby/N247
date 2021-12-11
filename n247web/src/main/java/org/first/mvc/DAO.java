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
			
			public static void createPost( String postTitle, String description, Integer tabId,Integer userNum ) {
				String resource = "org/first/mvc/mybatis_config.xml";
				InputStream inputStream;
				Post post = new Post();
				post.setDescription(description);
				post.setPostTitle(postTitle);
				post.setTabId(tabId);
				post.setUserNum(userNum);
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
			
			public static void createTab (String tabTitle, Integer userNum ) {
				String resource = "org/first/mvc/mybatis_config.xml";
				InputStream inputStream;
				Post tab = new Post();
				tab.setTabTitle(tabTitle);
				tab.setUserNum(userNum);
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

			public static Integer getCounTab(Integer userNum) {
				
				String resource = "org/first/mvc/mybatis_config.xml";
				InputStream inputStream;
				
				Integer result = 0;
				Integer count = 0;
				try {
					inputStream = Resources.getResourceAsStream(resource);
					SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
					SqlSession session = sqlSessionFactory.openSession();
					count = session.selectOne("org.first.mvc.BaseMapper.countTab", userNum);
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
			
			public static List<Post> readTabListAction(Integer userNum) {
				
				List<Post> result = new ArrayList<Post>();
				String resource = "org/first/mvc/mybatis_config.xml";
				InputStream inputStream;
				try {
					inputStream = Resources.getResourceAsStream(resource);
					SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
					SqlSession session = sqlSessionFactory.openSession();
					result = session.selectList("org.first.mvc.BaseMapper.getTabList", userNum);
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
					for(int i=0 ; i<result.size(); i++) {
						result.get(i).setImgName(getUserImg(result.get(i).getUserNum()));
					}
				} catch (IOException e) {
					e.printStackTrace();
				}
				return result ;
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
	
			
			public static void replyUpdate (Integer tabId, String tabTitle ) {
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
			 
			 public static List<Post> readPostListAction(Integer tabId) {
					List<Post> result = new ArrayList<Post>();
					String resource = "org/first/mvc/mybatis_config.xml";
					InputStream inputStream;
					Post p1 = new Post();
					p1.setTabId(tabId);
					Date date_now = new Date(System.currentTimeMillis()); 
					try {
						inputStream = Resources.getResourceAsStream(resource);
						SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
						SqlSession session = sqlSessionFactory.openSession();
						result = session.selectList("org.first.mvc.BaseMapper.getPostList", p1);
			            for(int i=0 ; i<result.size() ; i++) {
			            	result.get(i).setImgName(getUserImg(result.get(i).getUserNum()));
			            	result.get(i).setProgress(progress(result.get(i).getCreate(),result.get(i).getDueDay()));
		            		result.get(i).setProgressBg(progressBg(progress(result.get(i).getCreate(),result.get(i).getDueDay())));
			            	if(result.get(i).getLastUpdate() == null) {
			            		result.get(i).setTime(dateChangeAction(result.get(i).getCreate()));	
			            		//만는날짜의 시간이 영국시간일껄 
			            		result.get(i).setCompareTime(calDateBetweenAndB(result.get(i).getCreate(),date_now));
			            	}else {
			            	result.get(i).setTime(dateChangeAction(result.get(i).getLastUpdate()));
			            	result.get(i).setCompareTime(calDateBetweenAndB(result.get(i).getLastUpdate(),date_now));
			            	}
			            	
					 	}
					} catch (IOException e) {
						e.printStackTrace();
					}
					
					return result ;
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
				
				public static List<Post> getTabListToTabId(Integer tabId) {
					
					List<Post> result = new ArrayList<Post>();
					String resource = "org/first/mvc/mybatis_config.xml";
					InputStream inputStream;
					
					try {
						inputStream = Resources.getResourceAsStream(resource);
						SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
						SqlSession session = sqlSessionFactory.openSession();
						result = session.selectList("org.first.mvc.BaseMapper.getAdmFriTabList", tabId);
					 	
					} catch (IOException e) {
						e.printStackTrace();
					}
					return result ;
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
				
				public static void updatePost (Integer id, String postTitle, String description, Integer tabId, String rePerson ) {
					String resource = "org/first/mvc/mybatis_config.xml";
					InputStream inputStream;
					Post post = new Post();
					post.setId(id);
					post.setTabId(tabId);
					post.setPostTitle(postTitle);
					post.setDescription(description);
					post.setRePerson(rePerson);
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
			 	
			 	
				public static void updatePostTabTitle (Integer tabId, Integer moveOn ) {
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
						session.update("org.first.mvc.BaseMapper.updatePostTabTitle", tab);
						session.update("org.first.mvc.BaseMapper.updateIsDelTabTitle", p1);
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
				    public static long calDateBetweenAndB(Date p1, Date p2) {
				    	long calDate = 0;
				    	long calDateDays = 0;

					    	Date FirstDate = p1;
					    	Date SecondDate = p2;
					    	calDate = FirstDate.getTime() - SecondDate.getTime();
					    	calDateDays = calDate / (24*60*60*1000);
					    	calDateDays = Math.abs(calDateDays);
					    	
					    	////System.out.println("두 날짜의 차이 : " + calDateDays);

				    	return calDateDays;
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
						long result = calDateBetweenAndB(date_now,getTabListToTabId(tabId).get(0).getTab_dueDay());
				    	
				    	
				    	return result;
				    }
				    //전체 기간 구하고 남은날 빼준 값넣어주기
				    
				    public static Double tabProgress (Integer tabId) {
				    	Date date_now = new Date(System.currentTimeMillis());
				    	long totalDay = calDateBetweenAndB(getTabListToTabId(tabId).get(0).getTab_dueDay(),getTabListToTabId(tabId).get(0).getCreate());
				    	long dueDay = calDateBetweenAndB(getTabListToTabId(tabId).get(0).getTab_dueDay(),date_now);
				    	int p1 = Long.valueOf(dueDay).intValue();
				    	int p2 = Long.valueOf(totalDay).intValue();
				    	
				    	Double result = 100.0 - (((double)p1/(double)p2) * 100.0) ;
				    	
				    	////System.out.println("100 - ((" + p1 +"/"+ p2 +")*100) = " + result );
				    	return result;
				    }
				    
				    public static Double progress (Date create, Date due) {
				    	Date date_now = new Date(System.currentTimeMillis());
				    	long totalDay = calDateBetweenAndB(create,due);
				    	long dueDay = calDateBetweenAndB(due,date_now);
				    	int p1 = Long.valueOf(dueDay).intValue();
				    	int p2 = Long.valueOf(totalDay).intValue();
				    	
				    	Double result = 100.0 - (((double)p1/(double)p2) * 100.0) ;
				    	
				    	////System.out.println("100 - ((" + p1 +"/"+ p2 +")*100) = " + result );
				    	return result;
				    }
				    public static String progressBg (Double p1) {
				    	String result = "";
				    	////System.out.println("색깔 정할 아이가 왔어요 :" + p1);
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
						 System.out.println("이것도 테스트야 : " + Arrays.toString(arr));
						 Arrays.sort(arr);
						 int length = arr.length;
						 length = remove_Duplicate_Elements(arr, length);
						 for (int i=0; i<length; i++) 
							 System.out.println("중복제거중이야 : "+arr[i]+" ");
					 }
}
