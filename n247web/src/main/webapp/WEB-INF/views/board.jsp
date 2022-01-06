<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<html>
  <head>
    <meta charset="utf-8">
    <title>N247</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="A project management Bootstrap theme by Medium Rare">
    <link href="assets/img/favicon.ico" rel="icon" type="image/x-icon">
    <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
    <link href="https://fonts.googleapis.com/css?family=Gothic+A1" rel="stylesheet">
    <link href="resources/theme.css" rel="stylesheet" type="text/css" media="all" />
     <!-- Bootstrap CSS -->
	<link href="resources/list-groups.css" rel="stylesheet">
	<link href="resources/sidebars.css" rel="stylesheet">
	<script src="https://code.jquery.com/jquery-1.12.4.min.js"></script>
    
    	 <style>
      .bd-placeholder-img {
        font-size: 1.125rem;
        text-anchor: middle;
        -webkit-user-select: none;
        -moz-user-select: none;
        user-select: none;
      }

      @media (min-width: 768px) {
        .bd-placeholder-img-lg {
          font-size: 3.5rem;
        }
      }
    </style>
    
  </head>
  <body>
 	 <div class="layout layout-nav-side">
      <div class="navbar navbar-expand-lg bg-dark navbar-dark sticky-top">
	   <img src="/images/gucci.png" alt="twbs" width="50" height="50" class="rounded-circle flex-shrink-0">
        <div class="d-flex align-items-center">
          <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbar-collapse" aria-controls="navbar-collapse" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
          </button>
          <div class="d-block d-lg-none ml-2">
          
          <div class="dropdown">
		      <a href="#" class="d-flex align-items-center text-white text-decoration-none" id="dropdownUser1" data-bs-toggle="dropdown" aria-expanded="false">
		        <img src="/images/${userInformation.userImg }" alt="" width="32" height="32" class="rounded-circle me-2">	        
		      </a>
		      <ul class="dropdown-menu dropdown-menu-right" aria-labelledby="dropdownUser1">
		        <li><a class="dropdown-item" href="#">cloud</a></li>
		        <li><a class="dropdown-item" href="#" data-bs-toggle="modal" data-bs-target="#userInfo" >${userInformation.nickName}의 정보</a></li>
		        <li><hr class="dropdown-divider"></li>
		        <li><a class="dropdown-item" href="/mvc/logOutAction">로그아웃</a></li>
		      </ul>
		    </div>
          
          </div>
        </div>
        <div class="collapse navbar-collapse flex-column" id="navbar-collapse">
          <p>
          <ul class="navbar-nav d-lg-block">
            
			  <c:forEach items="${userInformation.tabList }" var="tab" begin="0" >	
			      <li class="nav-item">
			        <a href="/mvc/board?tabId=${tab.tabId }" class="nav-link" aria-current="page">
			          ${tab.tabTitle }
			        </a>
			      </li>
			  </c:forEach>
          </ul>
          <hr>
          <div class="d-none d-lg-block w-100">
          	<span class="text-small text-muted">공유 프로젝트</span>
          	<ul class="nav nav-small flex-column mt-2">
          	  <c:forEach items="${userInformation.friTabList }" var="friTab" begin="0" >	
			    <li class="nav-item">
			      <a href="/mvc/board?tabId=${friTab.tabId }" class="nav-link" aria-current="page" title="${friTab.nick }님의 ${friTab.tabTitle } 프로젝트">
			        <img src="/images/${friTab.imgName }" alt="" width="16" height="16" class="rounded-circle me-2" >
			        ${friTab.tabTitle }
			      </a>
			    </li>
			  </c:forEach>
			</ul>
			  
			
            <hr>
          </div>
          <div>
          
            <form action="searchPostNickAction" id="search" class="form-inline" method="get">
              <div class="input-group input-group-dark input-group-round">
                <div class="input-group-prepend">
                  <span class="input-group-text">
                    <i class="material-icons" >search</i>
                  </span>
                </div>
                <input name="search" type="search" class="form-control form-control-dark" placeholder="Search" aria-label="Search app">
              </div>
            </form>
            
            <div class="dropdown">
              <button class="btn btn-primary btn-block" type="button" data-bs-toggle="dropdown" aria-expanded="false">
                New
              </button>
              <ul class="dropdown-menu dropdown-menu-right">
	              <li><a class="dropdown-item" href="#" data-bs-toggle="modal" data-bs-target="#project-create-modal">새로운 프로젝트</a></li>             
	              <li><a class="dropdown-item" href="#" data-bs-toggle="modal" data-bs-target="#member-edit">새로운 친구</a></li>
			  </ul>
            </div>
          </div>
        </div>
        <div class="d-none d-lg-block">
          <div class="dropdown">
		      <a href="#" class="d-flex align-items-center text-white text-decoration-none" id="dropdownUser1" data-bs-toggle="dropdown" aria-expanded="false" title="${userInformation.nickName}">
		        <img src="/images/${userInformation.userImg }" alt="" width="32" height="32" class="rounded-circle me-2">	        
		      </a>
		      <ul class="dropdown-menu dropdown-menu-dark text-small shadow" aria-labelledby="dropdownUser1">
		        <li><a class="dropdown-item" href="#">cloud</a></li>
		        <li><a class="dropdown-item" href="#" data-bs-toggle="modal" data-bs-target="#userInfo" >${userInformation.nickName}의 정보</a></li>
		        <li><hr class="dropdown-divider"></li>
		        <li><a class="dropdown-item" href="/mvc/logOutAction">로그아웃</a></li>
		      </ul>
		    </div>
        </div>
      </div>
      
      <!--         사이드바 관련 모달 시작부분  -->
      <div class="modal fade" id="project-create-modal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">		    
		<form action="createTabAction" id="myForm" class="form-inline" method="post">
		 <div class="modal-dialog">	 
		  <div class="modal-content">
		   <div class="modal-header">
		    <h5 class="modal-title">새로운 프로젝트</h5>
              <button type="button" class="close btn btn-round" data-bs-dismiss="modal" aria-label="Close">
                <i class="material-icons">close</i>
              </button>  
		   </div>
		   <div class="modal-body">
            <div class="tab-pane fade show active" id="task-add-details" role="tabpanel">
              <h6>새로운 프로젝트</h6>
                <div class="form-group row align-items-center">
                    <label class="col-3">프로젝트명</label>
                    <input class="form-control col" type="text" name="tabTitle" placeholder="프로젝트명" name="task-name">
                </div>
                <div class="form-group row">
                    <label class="col-3">프로젝트 소개글</label>
                    <textarea class="form-control col" rows="3" name="tab_intro" placeholder="프로젝트 소개글" name="task-description"></textarea>
                </div>
                    <hr> 
                    <h6>Time line</h6>
				<div class="form-group row align-items-center">
     				<label class="col-3" for="date">프로젝트 완료일 </label>
   					<input class="form-control col flatpickr-input" type="date" name="tab_dueDay" id="date" min="${thisTab.minDay }">
   				</div>
            </div>
            <div class="alert alert-warning text-small" role="alert">
                 <span>완료 시점은 언제든지 정할 수 있습니다. </span>
            </div>
           </div>
		   <div class="modal-footer">
		     <button role="button" class="btn btn-primary" type="submit">
               프로젝트 만들기
             </button>
 	 		<input type="hidden" name="userNum" value="${userInformation.userId }">
 	 		<input type="hidden" name="id" value="${userInformation.id }">
		   </div>
		  </div>	     
		</div>
	   </form>
	 </div>

	 <div class="modal fade" id="userInfo" tabindex="-1" aria-labelledby="userInfoModal" aria-hidden="true">  
	   <div class="modal-dialog">
		 <div class="modal-content">
		   <div class="modal-header">
		     <h5 class="modal-title" id="userInfoModal">회원정보</h5>
		     <button type="button" class="close btn btn-round" data-bs-dismiss="modal" aria-label="Close">
		     <i class="material-icons">close</i>
		     </button>
		   </div>
		   <div class="modal-body">
			 <div class="row g-0">
			   <div class="col-md-4">
				 <img src="/images/${userInformation.userImg }" class="img-fluid rounded-start" alt="..." data-bs-toggle="modal" data-bs-target="#userImgUpdate">
			   </div>
		      <div class="col-md-8">
			   
			     <h5 class="card-title">${userInformation.nickName }</h5>
				 <p class="card-text">${userInformation.id }</p>
				 <p class="card-text">${userInformation.mb_introduce }</p>
				 <p class="card-text"><small class="text-muted">Last updated ${userInformation.lastModified }</small></p>
			  
			  </div>
			  </div>
			</div>
			<div class="modal-footer">
			  <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">닫기</button>
			  <button type="button" class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#userInfoUpdate${userInformation.userId }" >수정</button>
		    </div>
		  </div>
		</div>
	  </div>
	  
	  
	  <div class="modal fade" id="userInfoUpdate${userInformation.userId }" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
		  <form action="updateUserInfoAction" id="myForm" class="form-inline" method="post" accept-charset="UTF-8">
		  <div class="modal-dialog">     
		    <div class="modal-content">
		      <div class="modal-header">
		        <h5 class="modal-title" id="exampleModalLabel">회원 정보 수정</h5>
		         <button type="button" class="close btn btn-round" data-bs-dismiss="modal" aria-label="Close">
		     		<i class="material-icons">close</i>
		     	 </button>
		      </div>
		      <div class="modal-body">
		          <div class="mb-3">
		           	<input class="form-control" type="text" name="nickName" value="${userInformation.nickName }" maxlength="10" aria-label="postTitle">
		          </div>
		          <div class="mb-3">      
		            <input class="form-control" type="text" name="mb_introduce" value="${userInformation.mb_introduce }" maxlength="45" aria-label="postTitle">
		          </div>
		      </div>
		      <div class="modal-footer">
		        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">닫기</button>
		        <button type="submit" class="btn btn-primary">입력</button>
		      </div>
		    </div>
		  </div>
		 </form>
		</div>
	  	
	  	<div class="modal fade" id="userImgUpdate" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
		  <form id="frm" name="frm" method="post" action="/mvc/register/userImgUpdateAction" enctype="multipart/form-data">
		  <div class="modal-dialog">     
		    <div class="modal-content">
		      <div class="modal-header">
		        <h5 class="modal-title" id="exampleModalLabel">회원 사진 수정</h5>
		         <button type="button" class="close btn btn-round" data-bs-dismiss="modal" aria-label="Close">
		     		<i class="material-icons">close</i>
		     	 </button>
		      </div>
		      <div class="modal-body">
		        <div class="form-group row">
              		<label class="col-3">Upload</label>
					<input type="file" name=file class="form-control col">
               </div>
		      </div>
		      <div class="modal-footer">
		        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">닫기</button>
		        <button type="submit" class="btn btn-primary">입력</button>
		        
		      </div>
		    </div>
		  </div>
		 </form>
		</div>
	  
<!-- 	  멤버추가 모달시작부 -->

	<div class="modal fade" id="member-edit" tabindex="-1" aria-labelledby="member-edit-modal" aria-hidden="true">  
	   <div class="modal-dialog">
		 <div class="modal-content">
		   <div class="modal-header">
		     <h5 class="modal-title" id="member-edit-Modal">Friends</h5>
		     <button type="button" class="close btn btn-round" data-bs-dismiss="modal" aria-label="Close">
		     <i class="material-icons">close</i>
		     </button>
		   </div>
		   <!--end of modal head-->
		   
			<ul class="nav nav-tabs nav-fill" role="tablist">
               <li class="nav-item">
                 <a class="nav-link" id="task-add-members-tab" data-toggle="tab" href="#task-add-members1" role="tab" aria-controls="task-add-members1" aria-selected="false">새로운 친구추가</a>
               </li>
                <li class="nav-item">
                 <a class="nav-link" id="task-add-members-tab" data-toggle="tab" href="#task-add-members2" role="tab" aria-controls="task-add-members2" aria-selected="false">친구 관리</a>
               </li>
             </ul> 
		   <div class="modal-body">
	   	     <div class="tab-content">
	   	     	
	   	     	<div class="tab-pane fade" id="task-add-members1" role="tabpanel">
	                <div class="users-manage" data-filter-list="form-group-users">
	                	<h6>새로운 친구</h6>
	                      <div class="mb-3">
	                       <form action="searchFriendAction" id="myForm" class="form-inline" method="get" accept-charset="UTF-8">
							  <input type="text" class="form-control" name="search" placeholder="friends@email.com" aria-label="friendsEmail" aria-describedby="button-addon2">
							  <button type="submit" class="btn btn-outline-secondary" type="button" id="button-addon2">검색</button>
							  <input type="hidden" name="tabId" value="${thisTab.tabId }">
							  <input type="hidden" name="userNum" value="${userInformation.userId }">
							  </form>
						  </div>
						  
	                  	<hr>
	                    <h6>보낸 친구 요청</h6>
		                  <div class="mb-3">
		                    <ul class="avatars text-center">
		                      <c:forEach items="${userInformation.iWaitAdmList }" var="iWait" begin="0" >	
		                      <li>
		                      	<a href="/mvc/updateDelFriIWaitAction?fUserId=${iWait.userId }">
		                        <img alt="${iWait.nickName }" src="/images/${iWait.userImg }" class="avatar" data-toggle="tooltip" data-title="${iWait.nickName }에게 보낸 친구신청 취소하기">
		                        </a>
		                      </li>
		                      </c:forEach>
		                    </ul>
		                  </div>
	                </div>
	            </div>



               <div class="tab-pane fade" id="task-add-members2" role="tabpanel">
                <div class="users-manage" data-filter-list="form-group-users">
                  <h6>현재 친구들</h6>
                  <div class="mb-3">
                    <ul class="avatars text-center">
                      <c:forEach items="${userInformation.iApproveAdmList }" var="friAdm1" begin="0" >	
	                      <li>
	                        <a href="/mvc/updateDelFriIWaitAction?fUserId=${friAdm1.userId }">
	                        <img alt="${friAdm1.nickName }" src="/images/${friAdm1.userImg }" class="avatar" data-toggle="tooltip" data-title="${friAdm1.nickName }님과 친구 취소하기">
	                        </a>
	                      </li>
                      </c:forEach>
                      <c:forEach items="${userInformation.friendApproveAdmList }" var="friAdm2" begin="0" >	
	                      <li>
	                        <a href="/mvc/updateDelFriWaitAction?myId=${friAdm2.userId }"">
	                        <img alt="${friAdm2.nickName }" src="/images/${friAdm2.userImg }" class="avatar" data-toggle="tooltip" data-title="${friAdm2.nickName }님과 친구 취소하기">
	                        </a>
	                      </li>
                      </c:forEach>
                    </ul>
                  </div>
                 <hr>
                 <h6>받은 친구 요청</h6>
                  <div class="alert alert-warning text-small" role="alert">
	                 <div class="mb-3">
	                    <ul class="avatars text-center">
	                    <c:forEach items="${userInformation.waitingAdmList }" var="waitFri" begin="0" >	
	                      <li>
	                        <a href="/mvc/updateFriAdmAction?myId=${waitFri.userId }">
	                        <img alt="${waitFri.nickName }" src="/images/${waitFri.userImg }" class="avatar" data-toggle="tooltip" data-title="${waitFri.nickName }님과 친구 맺기">
	                        </a>
	                      </li>
	                      </c:forEach>
	                    </ul>
	                  </div>
	                  <span>사진을 클릭해 친구 요청을 수락해 주세요. </span>
           		  </div>
                </div>
              </div>
             </div>        
              
			</div>
			
			<div class="modal-footer">
			  <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">닫기</button>
		    </div>
		  </div>
		</div>
	  </div>

	  <!-- 			멤버추가 모달 마지막부 -->
	  
	  
      <!-- 		사이드바 관련 모달 마지막 부분 -->
      <div class="main-container">

        <div class="navbar bg-white breadcrumb-bar">
          <nav aria-label="breadcrumb">
            <ol class="breadcrumb">
              <li class="breadcrumb-item"><a>${userInformation.nickName }</a>
              </li>
              <li class="breadcrumb-item"><a href="pages-app.html#">${thisTab.tabTitle }</a>
              </li>
            </ol>
          </nav>
          <!-- Default dropstart button -->
			<div class="dropdown" Style="<c:out value="${thisTab.tabAdmCheck == 0 ? '' : 'display:none' }"/>">
			  <button type="button" class="btn btn-round" data-bs-toggle="dropdown" data-bs-display="static" aria-expanded="false">
			    <i class="material-icons">settings</i>
			  </button>
			  <ul class="dropdown-menu dropdown-menu-right">
			  	  <li><a class="dropdown-item" href="#" data-bs-toggle="modal" data-bs-target="#project-edit-modal">이름 변경</a></li>
			  	  <li><a class="dropdown-item" href="#" data-bs-toggle="modal" data-bs-target="#project-editIntro-modal">소개글 변경</a></li>
			  	  <li><a class="dropdown-item" href="#" data-bs-toggle="modal" data-bs-target="#project-editDueDay-modal">완료일 변경</a></li>
	              <li><a class="dropdown-item" href="#">완료</a></li>
	              <li><hr class="dropdown-divider"></li>
	              <li><a class="dropdown-item text-danger" href="#" data-bs-toggle="modal" data-bs-target="#project-<c:out value="${thisTab.tabSelectCheck == 0 ? 'del' : 'select' }"/>-modal">삭제</a></li> 
			    <!-- Dropdown menu links -->
			  </ul>
			</div>
			<div Style="<c:out value="${thisTab.tabAdmCheck == 0 ? 'display:none' : '' }"/>">
			<small>${thisTab.nick }님의 프로젝트 </small>
			</div>
<!-- 		프로젝트 관련 모달 시작부분  -->
			<div class="modal fade" id="project-edit-modal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">		    
			  <form action="updateTabTitleAction" id="myForm" class="form-inline" method="get">
				<div class="modal-dialog">	      
				  <div class="modal-content">
				     <div class="modal-header">
						 <h5 class="modal-title" id="exampleModalLabel">프로젝트 이름 변경</h5>
						 <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
				     </div>
					 <div class="modal-body">
					   <div class="mb-3">
						 <input class="form-control" type="text" name="tabTitle" value="${thisTab.tabTitle }" maxlength="45" aria-label="tabTitleUpdate" >
					   </div>
					 </div>
					 <div class="modal-footer">
					   <button type="submit" class="btn btn-secondary">확인</button>
					   <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">닫기</button>
					   <input type="hidden" name="tabId" value="${thisTab.tabId }">
					 </div>
						     
				   </div>
				 </div>
			   </form>
			 </div>
						
			 <div class="modal fade" id="project-editDueDay-modal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">		    
			   <form action="updateTabDueDayAction" id="myForm" class="form-inline" method="post">
				  <div class="modal-dialog">	      
				    <div class="modal-content">
					  <div class="modal-header">
						 <h5 class="modal-title" id="exampleModalLabel">프로젝트 완료일 변경</h5>
						 <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
					  </div>
				      <div class="modal-body">
						 <div class="mb-3">
   						    <input class="form-control col flatpickr-input" type="date" name="tab_dueDay" id="date" min="${thisTab.minDay }">
						 </div>
					  </div>
					  <div class="modal-footer">
					     <button type="submit" class="btn btn-secondary">확인</button>
						 <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">닫기</button>
						 <input type="hidden" name="tabId" value="${thisTab.tabId }">
					  </div>     
				    </div>
				  </div>
			   </form>
		     </div>
						
			 <div class="modal fade" id="project-editIntro-modal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">		    
			   <form action="updateTabIntroAction" id="myForm" class="form-inline" method="post">
				  <div class="modal-dialog">	      
				    <div class="modal-content">
				      <div class="modal-header">
						<h5 class="modal-title" id="exampleModalLabel">프로젝트 소개글 변경</h5>
						<button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
				      </div>
				      <div class="modal-body">
					    <div class="mb-3">
						  <input class="form-control" type="text" name="tab_intro" value="${thisTab.tab_intro }" maxlength="250" aria-label="tabIntroUpdate" >
					    </div>
				      </div>
				      <div class="modal-footer">
					     <button type="submit" class="btn btn-secondary">확인</button>
					     <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">닫기</button>
						 <input type="hidden" name="tabId" value="${thisTab.tabId }">
				      </div> 
				    </div>
				  </div>
			   </form>
			 </div>
<!-- 						삭제시 두가지 모달이 필요함  -->

			 <div class="modal fade" id="project-del-modal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">		    
			   <form action="deleteTabAction" id="myForm" class="form-inline" method="get">
				  <div class="modal-dialog">	      
				    <div class="modal-content">
					  <div class="modal-header">
						<h5 class="modal-title" id="exampleModalLabel">프로젝트 삭제</h5>
						<button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
				      </div>
				      <div class="modal-body">
					    <div class="mb-3">
						  <input class="form-control" type="text" value="${thisTab.tabTitle }" aria-label="tabTitleUpdate" readonly>
					    </div>
				      </div>
				      <div class="modal-footer">
					    <button type="submit" class="btn btn-secondary">삭제</button>
					    <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">닫기</button>
					    <input type="hidden" name="tabId" value="${thisTab.tabId }">
					  </div>
				    </div>
				  </div>
			   </form>
			 </div>
						
						
			 <div class="modal fade" id="project-select-modal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">		    
			   <div class="modal-dialog">	      
				 <div class="modal-content">
				   <div class="modal-header">
			         <h5 class="modal-title" id="exampleModalLabel">프로젝트안에 카드가 있습니다. 이동시키시겠습니까?</h5>
					 <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
				   </div>
				   <div class="modal-body">
					 <c:forEach items="${userInformation.tabList }" var="tab" begin="0" >
						    <form action="updatePostTabTitleAction" id="myForm" class="form-inline" method="post">
						    <div Style="<c:out value="${tab.check == 1 ? 'display:none' : '' }"/>">
							    <button type="submit" class="btn btn-secondary">${tab.tabTitle }</button>
							    <input type="hidden" name="tabId" value="${tab.tabId }">
							    <input type="hidden" name="moveOn" value="${thisTab.tabId }">
							    <input type="hidden" name="id" value="${userInformation.id }">
							    <input type="hidden" name="isDelCheck" value="0">
						    </div>
					        </form>
					 </c:forEach>
				    </div>
				    <div class="modal-footer"> 
					  <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">닫기</button>
					  <form action="updatePostTabTitleAction" id="myForm" class="form-inline" method="post">
					  <button type="submit" class="btn btn-secondary">카드 모두삭제</button>
					  <input type="hidden" name="tabId" value="${thisTab.tabId }">
					  <input type="hidden" name="moveOn" value="${thisTab.tabId }">
					  <input type="hidden" name="id" value="${userInformation.id }">
					  <input type="hidden" name="isDelCheck" value="1">
					  </form>
				    </div>
				  </div>
				</div>
			  </div>
			  
<!-- 		프로젝트 관련 모달 끝부분  -->
        </div>
        
        <div class="container">
          <div class="row justify-content-center">
            <div class="col-lg-11 col-xl-10">
              <div class="page-header">
                <h1>${thisTab.tabTitle} </h1>
                <p class="lead">${thisTab.tab_intro }</p>
                <div class="d-flex align-items-center">
                  <c:forEach items="${userInformation.friendAdmList }" var="fri" begin="0" >
	                  <ul class="avatars">				
					    <li class="nav-item">
							<a href="#" data-bs-toggle="modal" data-bs-target="#userInfo${fri.idN247_ft }" data-placement="top" title="${fri.nickName }">
							 <img alt="Claire Connors" class="avatar" src="/images/${fri.userImg }" />
							</a>
						</li>									  
	                  </ul>
                  		<div class="modal fade" id="userInfo${fri.idN247_ft }" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">		    
						  <div class="modal-dialog">	      
						    <div class="modal-content">
						      <div class="modal-header">
						        <h5 class="modal-title" id="exampleModalLabel">${fri.nickName }</h5>
						        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
						      </div>
						      <div class="modal-body">
						          <div class="mb-3">
						            <input class="form-control" type="text" value="${fri.id }" aria-label="readonly input example" readonly>
						          </div>
						      </div>
						      <form action="updateDelFriToTabAction" id="myForm" class="form-inline" method="get" accept-charset="UTF-8">
							      <div class="modal-footer" style="<c:out value="${fri.check == 0 ? '' : 'display:none' }"/>">
							        <button type="submit" class="btn btn-secondary">공유취소</button>
							        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">닫기</button>
							        <input type="hidden" name="idN247_ft" value="${fri.idN247_ft }">
							      </div>
						      </form>
						    </div>
						  </div>
						</div>
                  </c:forEach>
                  <div Style="<c:out value="${thisTab.tabAdmCheck == 0 ? '' : 'display:none' }"/>">
                  <button class="btn btn-round flex-shrink-0" data-bs-toggle="modal" data-bs-target="#ProjectAdm">
                    <i class="material-icons">add</i>
                  </button>
                  </div>
<!--                   친구추가 모달 시작부 -->
                   

				  
				  <div class="modal fade" id="ProjectAdm" tabindex="-1" aria-labelledby="member-edit-modal" aria-hidden="true">  
				   <div class="modal-dialog">
					 <div class="modal-content">
					   <div class="modal-header">
					     <h5 class="modal-title" id="member-edit-Modal">Share project</h5>
					     <button type="button" class="close btn btn-round" data-bs-dismiss="modal" aria-label="Close">
					     <i class="material-icons">close</i>
					     </button>
					   </div>
					   <div class="modal-body">
			                <div class="users-manage" data-filter-list="form-group-users">
			                  <h6>현재 친구들</h6>
			                  <div class="mb-3">
			                    <ul class="avatars text-center">
			                      <c:forEach items="${userInformation.iApproveAdmList }" var="friAdm1" begin="0" >	
			                             <li style="<c:out value="${friAdm1.check == 0 ? '' : 'display:none' }"/>">
									       <a href="/mvc/createFriTabAdd?ft_userId=${friAdm1.userId }&&ft_tabId=${thisTab.tabId }">
									        <img alt="${friAdm1.nickName }" src="/images/${friAdm1.userImg }" class="avatar" data-toggle="tooltip" data-title="${friAdm1.nickName }님과 공유하기">     
									       </a>
									      </li>
			                      </c:forEach>
			                      <c:forEach items="${userInformation.friendApproveAdmList }" var="friAdm2" begin="0" >	
			                      		<li style="<c:out value="${friAdm2.check == 0 ? '' : 'display:none' }"/>">
									      <a href="/mvc/createFriTabAdd?ft_userId=${friAdm2.userId }&&ft_tabId=${thisTab.tabId }" >
									        <img alt="${friAdm2.nickName }" src="/images/${friAdm2.userImg }" class="avatar" data-toggle="tooltip" data-title="${friAdm2.nickName }님과 공유하기 ">     
									      </a>
									    </li>
				                     </c:forEach>
			                    </ul>
			                  </div>
			                </div>
						</div>
						<div class="modal-footer">
						  <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">닫기</button>
					    </div>
					  </div>
					</div>
				  </div>	
				  
				  
				  
				  
				  
				  
		  
				  
<!-- 					친구추가 모달 끝부분 -->

                </div>
                <div>
                  <div class="progress">
                    <div class="progress-bar bg-${thisTab.tabProgressBg} " style="width:${thisTab.tabProgress}%;"></div>
                  </div>
                  <div class="d-flex justify-content-between text-small">
                    <div class="d-flex align-items-center">
                      
                      <span>${thisTab.tabLastUpdate } 마지막 업데이트</span>
                    </div>
                    <span>${thisTab.dueMessage }</span>
                  </div>
                </div>
              </div>

              <div class="tab-content">
                <div class="tab-pane fade show active" id="tasks" role="tabpanel" data-filter-list="card-list-body">
                  <div class="row content-list-head">
                    <div class="col-auto">
                      <h3>Cards</h3>
                      <div style="<c:out value="${thisTab.dueCheck == 0 ? '' : 'display:none' }"/>">
                      <button class="btn btn-round" data-bs-toggle="modal" data-bs-target="#cardAddModal">
                        <i class="material-icons">add</i>
                      </button>
                      </div>
<!--                       카드 추가 모달 시작부분  -->

			 	   <div class="modal fade" id="cardAddModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">		    
					<form id="frm" name="frm" method="post" action="/mvc/register/createPostAction" enctype="multipart/form-data">
					 <div class="modal-dialog">	
					       
					  <div class="modal-content">
					  
					   <div class="modal-header">
					    <h5 class="modal-title">카드 생성</h5>
			              <button type="button" class="close btn btn-round" data-bs-dismiss="modal" aria-label="Close">
			                <i class="material-icons">close</i>
			              </button>  
					   </div>
					   
					   <div class="modal-body">
			            <div class="tab-pane fade show active" id="task-add-details" role="tabpanel">
			              <h6>새로운 카드</h6>
			                <div class="form-group row align-items-center">
			                    <label class="col-3">카드</label>
			                    <input class="form-control col" type="text" name="postTitle" placeholder="카드 제목" name="task-name">
			                </div>
			                <div class="form-group row">
			                    <label class="col-3">내용</label>
			                    <textarea class="form-control col" rows="3" name="description" placeholder="카드 내용" name="task-description"></textarea>
			                </div>
			                <div class="form-group row">
			               		  <label class="col-3">Upload</label>
  								  <input type="file" name=file class="form-control col">
			                </div>
			                    <hr> 
			                    <h6>Timeline</h6>
							<div class="form-group row align-items-center">
			     				<label class="col-3" for="date">카드 완료일 </label>
			   					<input class="form-control col flatpickr-input" type="date" name="dueDay" id="date" min="${thisTab.minDay }" max="${thisTab.maxDay }">
			   				</div>
			            </div>
			            <div class="alert alert-warning text-small" role="alert">
			                 <span>카드 완료일은 프로젝트 기간안에서 선택가능합니다. </span>
			            </div>
			           </div>
			
					   <div class="modal-footer">
					     <button role="button" class="btn btn-primary" type="submit">
			               카드 만들기
			             </button>
					   </div>
					  </div>	     
					</div>
				   </form>
				 </div>

<!-- 						카드투가 모달 끝부분 	 -->
                      
                    </div>
                    <form class="col-md-auto">
                      <div class="input-group input-group-round">
                        <div class="input-group-prepend">
                          <span class="input-group-text">
                            <i class="material-icons">filter_list</i>
                          </span>
                        </div>
                        <input type="search" class="form-control filter-list-input" placeholder="Filter cards" aria-label="Filter cards">
                      </div>
                    </form>
                  </div>
                  <!--end of content list head-->
                  <div class="content-list-body">
                    <div class="card-list">
                      <div class="card-list-head">
                        <h6>진행중인 카드</h6>
                      </div>
                      <div class="card-list-body">
			 		<c:forEach items="${cards.postList }" var="post" begin="0" >	
                        <div class="card card-task">
                          <div class="progress">
                            <div class="progress-bar bg-${post.progressBg }" role="progressbar" style="width: ${post.progress}%" aria-valuenow="25" aria-valuemin="0" aria-valuemax="100"></div>
                          </div>
                          <div class="card-body">
                            <div class="card-title">
                              <a data-bs-toggle="modal" data-bs-target="#modal-${post.id }">
                              <h6 data-filter-by="text">${post.postTitle }</h6>
                              <span class="text-small">${post.description}</span>
                              <span class="text-small" style="color:green">${post.compareMessage} </span>
                              </a>
                            </div>
                            <div class="card-meta">

                              <ul class="avatars">
                                <li>
                                  <a data-toggle="tooltip" title="${post.nickName }">
                                    <img alt="${post.nickName }" class="avatar" src="/images/${post.userImg }" />
                                  </a>
                                </li>
                              </ul>

                              <div class="d-flex align-items-center">
                                <i class="material-icons" style="<c:out value="${post.check == 0 ? 'display:none' : '' }"/>">attach_file</i>
                                
                              </div>
                              
                              <div class="dropdown card-options">
                                <button class="btn-options" type="button"  data-bs-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                  <i class="material-icons" style="<c:out value="${post.postAdmCheck == 0 ? 'display:none' : '' }"/>">more_vert</i>
                                </button>
                                 <ul class="dropdown-menu dropdown-menu-right">
			  	 					<li><a class="dropdown-item" href="#" data-bs-toggle="modal" data-bs-target="#modal-${post.id }update">수정</a></li>
						            <li><a class="dropdown-item" href="/mvc/updateIsDelPostAction?id=${post.id }&&isDel=3">완료</a></li>
						            <li><hr class="dropdown-divider"></li>
						            <li><a class="dropdown-item text-danger" href="/mvc/updateIsDelPostAction?id=${post.id }&&isDel=1">삭제</a></li> 
								    <!-- Dropdown menu links -->
						    	 </ul>
                              </div>
                            </div>
                          </div>
                        </div>
                        
	                    <div class="modal fade" id="modal-${post.id }" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
						  <div class="modal-dialog">   
						    <div class="modal-content">
						      <div class="modal-header">
						        <h5 class="modal-title" id="exampleModalLabel">카드</h5>
						        <button type="button" class="close btn btn-round" data-bs-dismiss="modal" aria-label="Close">
	                				<i class="material-icons">close</i>
	              				</button>  
						      </div>
						      <div class="modal-body">
						        	 <div class="mb-3" style="<c:out value="${post.check == 0 ? 'display:none' : '' }"/>"> 
									<img src="/images/${post.up_fileName }" class="card-img-top" alt="...">
									</div>
								    <h5 class="card-title">${post.postTitle }</h5>
								    <p class="card-text">${post.description }</p>
								    <span class="text-small" style="color:green">${post.compareMessage} </span>
								<hr>
								<c:forEach items="${post.replyList }" var="re" begin="0" > 
						          <div class="mb-3"> 
									    <div class="row">
										    <div class="col-9">
										      <a data-bs-toggle="modal" data-bs-target="#modal-${re.idN247_re }update"><small> ${re.n247_reDes } </small><small style="color:red">${re.krCreate }</small></a>
										    </div>
										    <div class="col-3">
										      <p class="card-text">${re.nickName }</p>
										    </div>
										</div>
								   </div>
								 </c:forEach> 
								 <hr>
								 <form action="createReplyAction" id="myForm" class="form-inline" method="post" accept-charset="UTF-8">
									   <input type="text" class="form-control col" name="n247_reDes" placeholder="댓글을 입력해주세요" maxlength="45" aria-label="Recipient's username" aria-describedby="button-addon2">
									   <button type="submit" class="btn btn-outline-secondary" type="button" id="button-addon2">댓글</button>
									   <input type="hidden" name="n247_reUsId" value="${userInformation.userId }">
									   <input type="hidden" name="n247_rePoId" value="${post.id }">
									   <input type="hidden" name="tabId" value="${thisTab.tabId }">
									 </form>
						         </div>
						    </div>
						  </div>
						</div>	 
						
						<c:forEach items="${post.replyList }" var="re" begin="0" > 
						<div class="modal fade" id="modal-${re.idN247_re }update" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
						  	<form action="updateReplyAction" id="myForm" class="form-inline" method="post" accept-charset="UTF-8">
							  <div class="modal-dialog">     
							    <div class="modal-content">
							      <div class="modal-header">
							        <h5 class="modal-title" id="exampleModalLabel">댓글 수정</h5>
							        <button type="button" class="close btn btn-round" data-bs-dismiss="modal" aria-label="Close">
	                				<i class="material-icons">close</i>
	              					</button> 
							      </div>
							      <div class="modal-body">
							          <div class="mb-3">    
							            	<input class="form-control" type="text" name="n247_reDes" value="${re.n247_reDes }" maxlength="45" aria-label="reDes">  
							          </div>
							      </div>
							      <div class="modal-footer">
							        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">닫기</button>
							        <button type="submit" class="btn btn-primary">수정</button>
							        <a href="/mvc/deleteReplyAction?idN247_re=${re.idN247_re }">
							        <button type="button" class="btn btn-primary">삭제</button>
							        </a>
							        <input type="hidden" name="idN247_re" value="${re.idN247_re }">
							      </div>
							    </div>
							  </div>
							 </form>
							</div>	
							</c:forEach>					
						
						
<!-- 								카드관련 모달 시작부분  -->
                        		<div class="modal fade" id="modal-${post.id }update" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
								  <form action="updatePostAction" id="myForm" class="form-inline" method="post" accept-charset="UTF-8">
								  <div class="modal-dialog">     
								    <div class="modal-content">
								      <div class="modal-header">
								        <h5 class="modal-title" id="exampleModalLabel">카드 수정</h5>
								        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
								      </div>
								      <div class="modal-body">
								          <div class="mb-3">
								           	<input class="form-control" type="text" name="postTitle" value="${post.postTitle }" aria-label="postTitle">
								          </div>
								          <div class="mb-3">      
								            <textarea name="description" class="form-control" id="message-text">${post.description }</textarea>
								          </div>
								          <div class="mb-3">      
								             <input class="form-control col flatpickr-input" type="date" name="dueDay" id="date" min="${thisTab.minDay }" max="${thisTab.maxDay }">
								          </div>
								      </div>
								      <div class="modal-footer">
								        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">닫기</button>
								        <button type="submit" class="btn btn-primary">입력</button>
								        <input type="hidden" name="id" value="${post.id }">
								      </div>
								    </div>
								  </div>
								 </form>
								</div>	
<!-- 								카드관련 모달 끝부분 -->
             		 </c:forEach>          
                      </div>
                    </div>
                    
                    <div class="card-list">
                      <div class="card-list-head">
                        <h6>완료된 카드</h6>
                      </div>
                      <div class="card-list-body">
			 		<c:forEach items="${cards.completePostList }" var="cpost" begin="0" >	
                        <div class="card card-task">
                          <div class="card-body">
                            <div class="card-title">
                              <a data-bs-toggle="modal" data-bs-target="#modal-${cpost.id }">
                              <h6 data-filter-by="text">${cpost.postTitle }</h6>
                              <span class="text-small">${cpost.description}</span>
                              <span class="text-small" style="color:green">${cpost.compareMessage}</span>
                              </a>
                            </div>
                            <div class="card-meta">
                              <ul class="avatars">
                                <li>
                                  <a data-toggle="tooltip" title="${cpost.nickName }">
                                    <img alt="${cpost.nickName }" class="avatar" src="/images/${cpost.userImg }" />
                                  </a>
                                </li>
                              </ul>
                             <div class="d-flex align-items-center">
                                <i class="material-icons" style="<c:out value="${cpost.check == 0 ? 'display:none' : '' }"/>">attach_file</i>
                                
                              </div>
                              <div class="dropdown card-options">
                                <button class="btn-options" type="button"  data-bs-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                  <i class="material-icons" style="<c:out value="${cpost.postAdmCheck == 0 ? 'display:none' : '' }"/>">more_vert</i>
                                </button>
                                 <ul class="dropdown-menu dropdown-menu-right">
						            <li><a class="dropdown-item text-danger" href="/mvc/updateIsDelPostAction?id=${cpost.id }&&isDel=0">완료 해제</a></li> 
								    <!-- Dropdown menu links -->
						    	 </ul>
                              </div>
                            </div>
                          </div>
                        </div>
                        
	                    <div class="modal fade" id="modal-${cpost.id }" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
						  <div class="modal-dialog">   
						    <div class="modal-content">
						      <div class="modal-header">
						        <h5 class="modal-title" id="exampleModalLabel">카드</h5>
						        <button type="button" class="close btn btn-round" data-bs-dismiss="modal" aria-label="Close">
	                				<i class="material-icons">close</i>
	              				</button>
						      </div>
						      <div class="modal-body">
						         
						        	 <div class="mb-3" style="<c:out value="${cpost.check == 0 ? 'display:none' : '' }"/>"> 
									<img src="/images/${cpost.up_fileName }" class="card-img-top" alt="...">
									</div>
								
								    <h5 class="card-title">${cpost.postTitle }</h5>
								    <p class="card-text">${cpost.description }</p>
								    <span class="text-small" style="color:green">${cpost.compareMessage}</span>
								    <hr>
								    
								<c:forEach items="${cpost.replyList }" var="re" begin="0" > 
						          <div class="mb-3"> 
									    <div class="row">
										    <div class="col">
										      <small>${re.nickName }</small>
										    </div>
										    <div class="col-6">
										      <a data-bs-toggle="modal" data-bs-target="#modal-${re.idN247_re }update"><small>${re.n247_reDes }</small></a>
										    </div>
										    <div class="col">
										      <small>${re.krCreate }</small>
										    </div>
										</div>
								   </div>
								 </c:forEach> 
					     	 <hr>
								 <form action="createReplyAction" id="myForm" class="form-inline" method="post" accept-charset="UTF-8">
									   <input type="text" class="form-control col" name="n247_reDes" placeholder="댓글을 입력해주세요" aria-label="Recipient's username" aria-describedby="button-addon2">
									   <button type="submit" class="btn btn-outline-secondary" type="button" id="button-addon2">댓글</button>
									   <input type="hidden" name="n247_reUsId" value="${userInformation.userId }">
									   <input type="hidden" name="n247_rePoId" value="${cpost.id }">
									   <input type="hidden" name="tabId" value="${thisTab.tabId }">
									 </form>
				         </div>
					     
					    </div>
					    
					  </div>
					</div>	 
          		 </c:forEach>          
                   </div>
                 </div>
                    <!--end of content list body-->
                  </div>
                    
                  </div>
                  <!--end of content list-->
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
      
      
	
    <!-- Required vendor scripts (Do not remove) -->
    <script type="text/javascript" src="resources/jquery.min.js"></script>
    <script type="text/javascript" src="resources/popper.min.js"></script>
    <script type="text/javascript" src="resources/bootstrap.js"></script>

    <!-- Optional Vendor Scripts (Remove the plugin script here and comment initializer script out of index.js if site does not use that feature) -->

    <!-- Autosize - resizes textarea inputs as user types -->
    <script type="text/javascript" src="resources/autosize.min.js"></script>
    <!-- Flatpickr (calendar/date/time picker UI) -->
    <script type="text/javascript" src="resources/flatpickr.min.js"></script>
    <!-- Prism - displays formatted code boxes -->
    <script type="text/javascript" src="resources/prism.js"></script>
    <!-- Shopify Draggable - drag, drop and sort items on page -->
    <script type="text/javascript" src="resources/draggable.bundle.legacy.js"></script>
    <script type="text/javascript" src="resources/swap-animation.js"></script>
    <!-- Dropzone - drag and drop files onto the page for uploading -->
    <script type="text/javascript" src="resources/dropzone.min.js"></script>
    <!-- List.js - filter list elements -->
    <script type="text/javascript" src="resources/list.min.js"></script>

    <!-- Required theme scripts (Do not remove) -->
    <script type="text/javascript" src="resources/theme.js"></script>

    <!-- This appears in the demo only - demonstrates different layouts -->
    <style type="text/css">
      .layout-switcher{ position: fixed; bottom: 0; left: 50%; transform: translateX(-50%) translateY(73px); color: #fff; transition: all .35s ease; background: #343a40; border-radius: .25rem .25rem 0 0; padding: .75rem; z-index: 999; }
            .layout-switcher:not(:hover){ opacity: .95; }
            .layout-switcher:not(:focus){ cursor: pointer; }
            .layout-switcher-head{ font-size: .75rem; font-weight: 600; text-transform: uppercase; }
            .layout-switcher-head i{ font-size: 1.25rem; transition: all .35s ease; }
            .layout-switcher-body{ transition: all .55s ease; opacity: 0; padding-top: .75rem; transform: translateY(24px); text-align: center; }
            .layout-switcher:focus{ opacity: 1; outline: none; transform: translateX(-50%) translateY(0); }
            .layout-switcher:focus .layout-switcher-head i{ transform: rotateZ(180deg); opacity: 0; }
            .layout-switcher:focus .layout-switcher-body{ opacity: 1; transform: translateY(0); }
            .layout-switcher-option{ width: 72px; padding: .25rem; border: 2px solid rgba(255,255,255,0); display: inline-block; border-radius: 4px; transition: all .35s ease; }
            .layout-switcher-option.active{ border-color: #007bff; }
            .layout-switcher-icon{ width: 100%; border-radius: 4px; }
            .layout-switcher-body:hover .layout-switcher-option:not(:hover){ opacity: .5; transform: scale(0.9); }
            @media all and (max-width: 990px){ .layout-switcher{ min-width: 250px; } }
            @media all and (max-width: 767px){ .layout-switcher{ display: none; } }
    </style>

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>

  </body>
</html>