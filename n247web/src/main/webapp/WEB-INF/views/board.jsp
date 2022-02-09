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
	  .box {
	  	width: 3rem;
	  	height: 3rem;
	  	border-radius: 20%;
	  	overflow: hidden;
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
          <button id="navbar-togglerButton" class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbar-collapse" aria-controls="navbar-collapse" aria-expanded="false" aria-label="Toggle navigation">
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
            <span class="text-small text-muted">진행중 프로젝트 </span>
			  <c:forEach items="${userInformation.tabList }" var="tab" begin="0" >	
			      <li class="nav-item">

			        <a href="#" class="nav-link" onclick="getProject(this.id)" aria-current="page" id="${tab.tabId }">
			          ${tab.tabTitle }
			        </a>
			      </li>
			  </c:forEach>
			   <hr><span class="text-small text-muted">완료된 프로젝트</span>
			  <c:forEach items="${userInformation.completeTabList }" var="compTab" begin="0" >	
			    <li class="nav-item">

			      <a href="#" class="nav-link" onclick="getProject(this.id)" aria-current="page" id="${compTab.tabId }">
			        ${compTab.tabTitle }
			      </a>
			    </li>
			  </c:forEach>
			  <hr><span class="text-small text-muted">공유 프로젝트</span>
			  <c:forEach items="${userInformation.friTabList }" var="friTab" begin="0" >	
			  
			    <li class="nav-item">

			      <a href="#" class="nav-link" onclick="getProject(this.id)" aria-current="page" id="${friTab.tabId }" title="${friTab.nick }님의 ${friTab.tabTitle } 프로젝트">
			        <img src="/images/${friTab.imgName }" alt="" width="16" height="16" class="rounded-circle me-2" >
			        ${friTab.tabTitle }
			      </a>
			    </li>
			  </c:forEach>
			  <hr>
          </ul>

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
      <div class="main-container">

        <div class="navbar bg-white breadcrumb-bar">
          <nav aria-label="breadcrumb">
            <ol class="breadcrumb">
              <li class="breadcrumb-item" id="breadcrumb-item-userName"></li>
              <li class="breadcrumb-item" id="breadcrumb-item-projectName"></li>
            </ol>
          </nav>
          <!-- Default dropstart button -->
			<div id="page_topHead_dropDown" class="dropdown"></div>
			<div id="page_topHead_userName"></div>

        </div>
        
<!--         body container start -->
        <div class="container">
          <div id="page-projectBody" class="row justify-content-center">
            <div class="col-lg-11 col-xl-10">
              <div class="page-header" id="page-projectHeader">
              </div>
              
              <div class="tab-content">
                <div class="tab-pane fade show active" id="tasks" role="tabpanel" data-filter-list="card-list-body">
                  </div>
                  <!--end of content list-->
                </div>
              </div>
            </div>
          </div>
<!--           body container end -->
        </div>
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
			               <span>카드 완료일은 ${thisTab.minDay }부터 ${thisTab.maxDay } (프로젝트완료일)안에서 선택가능합니다. </span>
			          </div>
			         </div>
			
			   <div class="modal-footer">
			     <button role="button" class="btn btn-primary" type="submit">카드 만들기</button>
			     <div id="thisTabUserIdTabId"></div>
			   </div>
			  </div>	     
			</div>
		</form>
	</div>
	
	
	<div class="modal fade" id="modal_card_updateFile" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">		    
		<form id="frm" name="frm" method="post" action="/mvc/register/updateFileAction" enctype="multipart/form-data">
			 <div class="modal-dialog">	 
			  <div class="modal-content">
			   <div class="modal-header">
			    <h5 class="modal-title">첨부파일관리</h5>
		            <button type="button" class="close btn btn-round" data-bs-dismiss="modal" aria-label="Close" id="modal_file_closeButton">
		              <i class="material-icons">close</i>
		            </button>  
			   </div>
			   <div class="modal-body">
		          <div class="tab-pane fade show active" id="task-add-details" role="tabpanel">
		          	  <div class="form-group row" id="modal_card_updateFile_body_deleteFile"></div>
		              <div class="form-group row" id="modal_card_updateFile_body_fileSelecter"></div>
		          </div>
		           <div class="alert alert-warning text-small" role="alert">
			               <span id="modal_card_updateFile_message"></span>
			          </div>
			   </div>
			   <div class="modal-footer">
			     <button role="button" class="btn btn-primary" type="submit" id="modal_card_updateFile_footerButton">업로드</button>
			     <div id="modal_card_updateFile_footer"></div>
			   </div>
			  </div>	     
			</div>
		</form>
	</div>
	
	<div class="modal fade" id="getCardModal" data-bs-backdrop="static" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
	  <div class="modal-dialog">
	    <div class="modal-content">
	      <div class="modal-header">
	       <h5 class="modal-title" id="cardModalLabel"></h5>
	        <button type="button" onclick="reload()" class="close btn btn-round" data-bs-dismiss="modal" aria-label="Close" id="modal_card_closeButton">
				<i class="material-icons">close</i>
			</button>
	      </div>
	      <div class="modal-body" id="modal_card_body">
	
	      </div>
	      <div class="modal-footer">
	        <input type="text" class="form-control col" onKeypress="javascript:if(event.keyCode==13) {input_replyAction(this.value)}" placeholder="댓글을 입력해주세요" id="input_card_reply" maxlength="45">
	        <button type="button" onclick="reload()" class="btn btn-outline-secondary" data-bs-dismiss="modal">닫기</button>
	      </div>
	    </div>
	  </div>
	</div>
	
	
	<div class="modal fade" id="ProjectAdm" data-bs-backdrop="static" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
	  <div class="modal-dialog">
	    <div class="modal-content">
	      <div class="modal-header">
	       <h5 class="modal-title">프로젝트 진행을 함께 할 친구를 선택해주세요</h5>
	        <button type="button" onclick="reload()" class="close btn btn-round" data-bs-dismiss="modal" aria-label="Close">
				<i class="material-icons">close</i>
			</button>
	      </div>
	      <div class="modal-body">
			<ul class="avatars" id="modal_projectAdm_body">
			</ul>
	      </div>
	      <div class="modal-footer">
	        <button type="button" onclick="reload()" class="btn btn-outline-secondary" data-bs-dismiss="modal">닫기</button>
	      </div>
	    </div>
	  </div>
	</div>
	
	<div id="modal_project_addFriends">
	</div>	
	
	
	<div class="modal fade" id="modal_userInformation" tabindex="-1" aria-labelledby="userInfoModal" aria-hidden="true">  
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
			   <div class="col-md-4" id="moal_userInformation_userImg">
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
	
<!-- 	공유취소 모달 -->
	<div class="modal fade" id="projectUnAdm" data-bs-backdrop="static" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
	  <div class="modal-dialog">
	    <div class="modal-content">
	      <div class="modal-header">
	       <h5 class="modal-title">공유를 취소하시겠습니까?</h5>
	        <button type="button" class="close btn btn-round" data-bs-dismiss="modal" aria-label="Close">
				<i class="material-icons">close</i>
			</button>
	      </div>
	      <div class="modal-body">
	      </div>
	      <div class="modal-footer" id="projectUnAdm_action_button">
	      </div>
	    </div>
	  </div>
	</div>
	

    <script type="text/javascript" src="http://code.jquery.com/jquery-3.5.1.min.js"></script>  
    <script>
    //jstl 이 들어가면 밖으로 못뽑아 분리 안됨 
    var p1 = '${userInformation.tabId}';
 	var p2 = '${userInformation.userId }';
 	var userNickName = '${userInformation.nickName }';
 	var replyUser = '${userInformation.nickName }';
   	var allList = [];
   	
	console.log("로그인 하면 뭘 가져오나 볼게요 :" + p1 + "그리고 :" + p2 );
 	
   	function getAllList(p1,p2){
		
		 $.ajax({
	         url: 'getAllListAjax',
	    	 method: "POST",
	 	     data: {'tabId': p1, 'userId' : p2}
	 	     })
	 	
		.done(function(data) {
			allList=data;
			//console.log('모든리스트 받아오기 작동중입니다. allList의 갯수는 :' + allList.length);
// 			getProjectAdmFriendsAjax(p1);
			getAdmFriendsAjax();
			getCardList2(allList);
			
		})
	};
	
	getAllList(p1,p2);
   	
   	function getCardList2(allList){
   		
 		//console.log("작동합니다." + allList.length);
 		var allCardList = [];
 		var postCheck = 0;
 		var projectInfo = allList;
 		for(var i=0 ; i<allList.length ; i++){
 			if( allList[i].id === postCheck){
 			}else if(allList[i].isDel == 0){
 				postCheck = allList[i].id;
 				allCardList.push(allList[i]);
 			}
 			
 		}
 		
 		var cards_list = allCardList;
	    var compCards_list = allCardList;
	
	   
	$('#tasks').append(
		'<div class="list-head"></div>'+
		'<div class="content-list-body"></div>'
		);
	
	$('#card-add-icon').show();

	$('.list-head').append(
		'<div class="row content-list-head">'+	
		'<div class="col-auto">'+
			'<h3>Cards</h3>'+
			'<div>'+
				'<button id="card-add-icon" class="btn btn-round" onclick=" modalInput()" data-bs-toggle="modal" data-bs-target="#cardAddModal">'+
				'<i class="material-icons">add</i>'+
				'</button>'+
			'</div>'+
		'</div>'+
		'<form class="col-md-auto">'+
		'<div class="col align-self-end">'+
			'<div class="input-group input-group-round">'+
				'<div class="input-group-prepend">'+
					'<span class="input-group-text">'+
					'<i class="material-icons">filter_list</i>'+
					'</span>'+
				'</div>'+
			'<input type="search" class="form-control filter-list-input" placeholder="Filter cards" aria-label="Filter cards">'+
			'</div>'+
			'</div>'+
		'</form>'+
		'</div>'
	);
	if(cards_list.length != 0){
		if(cards_list[0].tabCompCheck == 3){
			$('#card-add-icon').hide();
		}
	}
	
	
	$('.content-list-body').append(
		'<div class="card-list">'+
			'<div class="card-list-head"><h6>진행중 카드</h6></div>'+
			'<div class="card-list-body" id="cardsList"></div>'+
		'</div>'+
		'<div class="card-list">'+
			'<div class="card-list-head"><h6>완료된 카드</h6></div>'+
			'<div class="card-list-body" id="completeCardsList"></div>'+
		'</div>'
		);

		if(cards_list.length != 0){
			for(var i=0 ; i < cards_list.length  ; i++ ) {
				$("#cardsList").append(
				'<div class="card card-task" id="card-task'+cards_list[i].id+'" >'+
				'<div class="progress">' +
						'<div class="progress-bar bg-'+ cards_list[i].progressBg +'" role="progressbar" style="width: '+cards_list[i].progress+'%" aria-valuenow="25" aria-valuemin="0" aria-valuemax="100"></div>'+
						'</div>' +
				'<div class="card-body"> '+
					'<div class="card-title">'+
						'<ul class="avatars">'+
							'<li>'+
				       		'<a  onclick="getOneCardAjax(this.id)" data-bs-toggle="modal" data-bs-target="#getCardModal" id="'+cards_list[i].id+'">' +
				   			'<h6 data-filter-by="text" id="card_title_'+cards_list[i].id+'">'+ cards_list[i].postTitle +'<p><small>'+'  '+cards_list[i].dueDayString+' 까지 완료</small></h6><br>'+
								'<img class="box" id="'+cards_list[i].id+'postUpfile" src="/images/'+cards_list[i].up_fileName +' "/>'+ 
				   		  	'<span data-filter-by="text" class="text-small" id="card_description_'+cards_list[i].id+'">'+'\u00a0\u00a0\u00a0'+cards_list[i].description+'</span>' +
				   		    '</a>'+
				       	 	
				       	 	'</li>'+
				        '</ul>'+
				    '</div>'+
				    '<div class="card-meta">'+
				        '<ul class="avatars">'+
				       		'<li>'+
				       		'<a data-toggle="tooltip" title="'+cards_list[i].nickName+'">'+
				      		'<img alt="'+ cards_list[i].nickName +'" class="avatar" src="/images/'+ cards_list[i].userImg +'" />'+
				      		'</a>'+
				       		'<small>'+'\u00a0\u00a0'+ cards_list[i].nickName +' <span class="text-small" style="color:green"> '+ cards_list[i].compareMessage +'</span></small>'+
				   		 	'</li>'+
				       	'</ul>'+
				       	'<div class="d-flex align-items-center">'+
				   		    '<a style="color:#666666" data-toggle="tooltip" title="'+ cards_list[i].up_orgName +'" href="/images/'+ cards_list[i].up_fileName+'" download="'+ cards_list[i].up_orgName +'">'+
				   			'<i id="'+cards_list[i].id+'attachIcon"  class="material-icons">attach_file</i>'+
				       		'</a>'+
				   		'</div>'+
				   		'<div class="dropdown card-options">'+
				       		'<button class="btn-options" type="button"  data-bs-toggle="dropdown" aria-haspopup="true" aria-expanded="false">'+
				   			'<i id="'+cards_list[i].id+'more_verIcon" class="material-icons">more_vert</i>'+	
				       		'</button>'	  +
							'<ul class="dropdown-menu dropdown-menu-right">' +
				       		'<li><a class="dropdown-item" href="/mvc/updateIsDelPostAction?id='+ cards_list[i].id +'&&isDel=3">완료</a></li>'+
				       		'<li><hr class="dropdown-divider"></li>'	+
				       		'<li><a class="dropdown-item text-danger" href="/mvc/updateIsDelPostAction?id='+ cards_list[i].id +'&&isDel=1">삭제</a></li> '+
							'</ul>'+
						'</div>'+
					'</div>'+
					'</div>'+
				'</div>');
				
				if(cards_list[i].up_fileName == null || cards_list[i].up_isDel == 1){
					$('#'+cards_list[i].id+'postUpfile').hide(); 
					$('#'+cards_list[i].id+'attachIcon').hide(); 
				}
				if(cards_list[i].up_check == 1){
					$('#'+cards_list[i].id+'postUpfile').hide(); 
				}
				if(cards_list[i].postAdmCheck == 0){
					$('#'+cards_list[i].id+'more_verIcon').hide();
				}
				if(cards_list[i].isDel == 3){
					$('#card-task'+cards_list[i].id).hide();
				}
				
			}

			for(var i=0 ; i < compCards_list.length  ; i++ ) {
			$("#completeCardsList").append(
				'<div class="card card-task" id="compCard-task'+cards_list[i].id+'" >'+
					'<div class="card-body"> '+
						'<div class="card-title">'+
			           		'<a onclick="getOneCardAjax(this.id)" data-bs-toggle="modal" data-bs-target="#getCardModal" id="'+compCards_list[i].id+'">' +
			       			'<h6 data-filter-by="text">'+ compCards_list[i].postTitle +'</h6>'+
			   				'<img id="'+compCards_list[i].id+'compPostUpfile" class="avatar" src="/images/'+compCards_list[i].up_fileName +' "/>'+ 
			       		  	'<span class="text-small">'+compCards_list[i].description+'</span>' +
			       		    '</a>'+
			           	 	'<small>'+compCards_list[i].dueDayString+' 까지 완료</small>'+ 
			            '</div>'+
			            '<div class="card-meta">'+
			                '<ul class="avatars">'+
			               		'<li>'+
			               		'<a data-toggle="tooltip" title="'+compCards_list[i].nickName+'">'+
			              		'<img alt="'+ compCards_list[i].nickName +'" class="avatar" src="/images/'+ compCards_list[i].userImg +'" />'+
			              		'</a>'+
			               		'<small>'+ compCards_list[i].nickName +' <span class="text-small" style="color:green"> '+ compCards_list[i].compareMessage +'</span></small>'+
			           		 	'</li>'+
			               	'</ul>'+
			               	'<div class="d-flex align-items-center">'+
			           		    '<a style="color:#666666" data-toggle="tooltip" title="'+ compCards_list[i].up_orgName +'" href="/images/'+ compCards_list[i].up_fileName+'" download="'+ compCards_list[i].up_orgName +'">'+
			           			'<i id="'+compCards_list[i].id+'compAttachIcon"  class="material-icons">attach_file</i>'+
			               		'</a>'+
			           		'</div>'+
			           		'<div class="dropdown card-options">'+
			               		'<button class="btn-options" type="button"  data-bs-toggle="dropdown" aria-haspopup="true" aria-expanded="false">'+
			           			'<i id="'+compCards_list[i].id+'compMore_verIcon" class="material-icons">more_vert</i>'+	
			               		'</button>'	  +
								'<ul class="dropdown-menu dropdown-menu-right">' +
			               		'<li><a class="dropdown-item text-danger" href="/mvc/updateIsDelPostAction?id='+ compCards_list[i].id +'&&isDel=0">완료 해제</a></li>'+
								'</ul>'+
							'</div>'+
						'</div>'+
			       	'</div>'+
		    	'</div>');
				if(compCards_list[i].up_fileName == null){
				$('#'+compCards_list[i].id+'compPostUpfile').hide(); 
				$('#'+compCards_list[i].id+'compAttachIcon').hide(); 
				}
				if(cards_list[i].up_check == 1){
				$('#'+compCards_list[i].id+'compPostUpfile').hide(); 
				}
				if(compCards_list[i].postAdmCheck == 0){
				$('#'+compCards_list[i].id+'compMore_verIcon').hide();
				}
				if(compCards_list[i].isDel == 0){
				$('#compCard-task'+compCards_list[i].id).hide();
				}
				
			}
		}
		

	$('#breadcrumb-item-userName').hide();
		$('#breadcrumb-item-userName').show();
		$('#breadcrumb-item-userName').html('<a>'+userNickName+'</a>');
		$('#breadcrumb-item-projectName').hide();
		$('#breadcrumb-item-projectName').show();
		$('#breadcrumb-item-projectName').html('<a>'+projectInfo[0].tabTitle+'</a>');
		$('#breadcrumb-item-userName').show();
		if(projectInfo[0].tab_userNum != p2){
			$('#page_topHead_dropDown').hide();
			$('#page_topHead_userName').show();
			for(var i=0 ; i<projectInfo.length ; i++){
				if(projectInfo[i].tab_userNum == projectInfo[i].userId){
					
					$('#page_topHead_userName').html('<small>'+projectInfo[i].nickName+'님의 프로젝트 </small>');	
				}
			}
		}else{
			$('#page_topHead_userName').hide();
			$('#page_topHead_dropDown').show();
			$('#page_topHead_dropDown').html(
					'<button type="button" class="btn btn-round" data-bs-toggle="dropdown" data-bs-display="static" aria-expanded="false">'+
					'<i class="material-icons">settings</i></button>'+
					' <ul class="dropdown-menu dropdown-menu-right">'+
						'<li id="dropDown-reDeuDay"><a class="dropdown-item" href="#" data-bs-toggle="modal" data-bs-target="#project-editDueDay-modal">완료일 변경</a></li>'+
						'<li id="dropDown-complete"><a class="dropdown-item" href="completeTabAction?isDel=3">프로젝트 완료</a></li>'+
						'<li id="dropDown-unComplete"><a class="dropdown-item" href="completeTabAction?isDel=0">완료해제</a></li>'+
						'<li><hr class="dropdown-divider"></li>'+
						'<li id="dropDown-delProject"><a class="dropdown-item text-danger" href="#" data-bs-toggle="modal" data-bs-target="#project-del-modal">삭제</a></li>'+
						'<li id="dropDown-moveAllCard"><a class="dropdown-item text-danger" href="#" data-bs-toggle="modal" data-bs-target="#project-select-modal">삭제</a></li>'+
					' </ul>');
		}
		
		$('#dropDown-reName').hide();
		$('#dropDown-reIntro').hide();
		$('#dropDown-reDeuDay').hide();
		$('#dropDown-complete').hide();
		$('#dropDown-unComplete').hide();
		$('#dropDown-divider').hide();
		$('#dropDown-moveAllCard').hide();
		$('#dropDown-delProject').hide();
		if(projectInfo[0].tabCompCheck == 0){
			$('#dropDown-reName').show();
			$('#dropDown-reIntro').show();
			$('#dropDown-reDeuDay').show();
			$('#dropDown-complete').show();
		}else{
			
			$('#dropDown-unComplete').show();
		}
		for(var i=0 ; i<projectInfo.length ; i++){
			var tabSelectCheck = 0;
			if(projectInfo[i].id != null){
				$('#dropDown-delProject').show();
				break;
			}else{
				$('#dropDown-moveAllCard').show();
				break;
			}	
		}
		$('#page-projectHeader').html(
				'<div id="header_projectTitle"></div>'+
				'<div class="d-flex align-items-center">'+
				'<ul class="avatars" id="header_admSetting">'+
				'</ul>'+
				'<div class="col" id="header_admAddicon"></div>'+
				'</div>'+
				'<div id="header_projectProgress"></div>'
				);

		
		
	$('#header_admAdd').hide();	
	
	if(projectInfo[0].tab_userNum == p2 && projectInfo[0].tabCompCheck == 0){
		$('#header_projectTitle').html('<h1 onclick="update_project_title()" id="project_title">'+projectInfo[0].tabTitle+'</h1> <div id="project_titleUpdate_input"></div>'+
				'<p class="lead" onclick="update_project_intro()" id="projcet_intro">'+projectInfo[0].tab_intro+'</p><div id="project_introUpdate_input"></div>');	
		$('#header_admSet').show();	
		getProjectInfor(projectInfo[0].tabTitle,projectInfo[0].tabId,projectInfo[0].tab_intro);
	}else{
		$('#header_projectTitle').html('<h1>'+projectInfo[0].tabTitle+'</h1><p class="lead">'+projectInfo[0].tab_intro+'</p>');	
		$('#header_admSet').hide();	
	}		
		$('#header_projectProgress').html(
			'<div class="progress">'+
			'<div class="progress-bar bg-'+projectInfo[0].tabProgressBg+'" style="width:'+projectInfo[0].tabProgress+'%;"></div>'+
			'</div>'+
			'<div class="d-flex justify-content-between text-small">'+
			'<div class="d-flex align-items-center"><span>'+projectInfo[0].dueMessage+'</span></div>'+
			'<span>'+projectInfo[0].maxDay+' 완료</span>'+
			'</div>'+
			'</div>');	
		
	// 020901 탭이 마감일이 지나면 포스트를 작성할 수 없게 한다.
	if(projectInfo[0].tabProgress > 99.99){
		console.log("테스트 성공" + projectInfo[0].tabProgress);
		$('#card-add-icon').hide();
	}	
		
	};
	
	
	
	
	var update_projcetTitle = '';
	var update_projectId = 0;
	var update_projectIntro = '';
	
	
	function getProjectInfor(titlearg,idarg,introarg){
		update_projcetTitle = titlearg;
		update_projectId = idarg;
		update_projectIntro = introarg;
	}
	
	function update_project_title(){
		$('#project_title').hide();
		$('#project_titleUpdate_input').show();
		$('#project_titleUpdate_input').html(
				'<div class="row" >'+
				'<div class="col-md-auto">'+
				'<input type="text" class="form-control" onKeypress="javascript:if(event.keyCode==13) {update_projcet_titleAction(this.value)}" placeholder="'+update_projcetTitle+'" maxlength="45" aria-label="tabTitleUpdate">'+
				'</div>'+
				'<div class="col-md-auto">'+
				'<button type="button" class="close btn btn-round" onclick="updat_project_title_cancle()" ><i class="material-icons">close</i></button>'+
				'</div>'+
				'</div>'
				);
		$('#project_introUpdate_input').hide();
		$('#projcet_intro').show();
	}
	
	function update_project_intro(){
		$('#projcet_intro').hide();
		$('#project_introUpdate_input').show();
		$('#project_introUpdate_input').html(
				'<div class="row" >'+
				'<div class="col-md-auto">'+
				'<input class="form-control" type="text" onKeypress="javascript:if(event.keyCode==13) {update_projcet_introAction(this.value)}" placeholder="'+update_projectIntro+'" maxlength="250" aria-label="tabIntroUpdate" >'+
				'</div>'+
				'<div class="col-md-auto">'+
				'<button type="button" class="close btn btn-round" onclick="updat_project_intro_cancle()"><i class="material-icons">close</i></button>'+
				'</div>'+
				'</div>'
				);
		$('#project_titleUpdate_input').hide();
		$('#project_title').show();
	}
	
	function updat_project_title_cancle(){
		console.log("프로젝트 제목 수정 취소 테스트 성공");
		$('#project_titleUpdate_input').hide();
		$('#project_title').show();
		
	}
	function updat_project_intro_cancle(){
		$('#project_introUpdate_input').hide();
		$('#projcet_intro').show();
	}
	
	
	function update_projcet_titleAction(insertTitle){
		 $.ajax({
	         url: 'updateProjectTitleAjax',
	    	 method: "POST",
	 	     data: {'tabTitle': insertTitle, 'tabId' : update_projectId}
	 	     })
	 	
		.done(function() {
			$('#project_titleUpdate_input').empty();
			$('#project_title').text(insertTitle);
			$('#project_title').show();
			$('#'+update_projectId+'').text(insertTitle);
			$('#breadcrumb-item-projectName').text(insertTitle);
		});
	}
	
	function update_projcet_introAction(insertIntro){
		 $.ajax({
	         url: 'updateProjectIntroAjax',
	    	 method: "POST",
	 	     data: {'tab_intro': insertIntro, 'tabId' : update_projectId}
	 	     })
	 	
		.done(function() {
			$('#project_introUpdate_input').empty();
			$('#projcet_intro').text(insertIntro);
			$('#projcet_intro').show();
			
		});
	}
	
	
    var projectNumber = p1;
	
	function getProject(id){
		projectNumber = id;
		$('#tasks').empty();
		$('#cardsList').empty(); 
		$('#completeCardsList').empty(); 
		getAllList(projectNumber,p2);
		
	};
	
	function modalInput(){
		if(projectNumber != p1){
			$('#thisTabUserIdTabId').html(
					'<input type="hidden" name="userId" value="'+p2+'">'+
					'<input type="hidden" name="tabId" value="'+projectNumber+'">'
					);
		}else{
			$('#thisTabUserIdTabId').html(
					'<input type="hidden" name="userId" value="'+p2+'">'+
					'<input type="hidden" name="tabId" value="'+p1+'">'
					);
		}
		
	}

	// 친구를 모두 받아올게 
	function getAdmFriendsAjax(){
		 $.ajax({
	         url: 'getAdmFriendsAjax',
	    	 method: "POST",
	 	     data: {'userId': p2}
	 	     })
       .done(function(data) {
	       	$('#modal_projectAdm_body').empty();

	       	var allfriendsList = [];
	       	var iApproveAdmList = [];
	       	var friendApproveAdmList = [];
	       	var iWaitAdmList = [];
	       	var waitingAdmList = [];
	       	var projectAdmList = [];
	       	var selectProjectAdmFriends = [];
	       	var userIdCheck = 0;
	       	var tabAdmFriCheck = 0;
	       	var tabMasterCheck = 0;
	       	
	       	for(var i=0 ; i<data.length ; i++){
	       		if(data[i].ft_tabId == projectNumber){
	       			if(data[i].userNum == p2){
	       			}else{
	       				tabMasterCheck=1;
	       				break;
	       			}
	       		}
	       	}
	       	
	       	for(var i=0 ; i<data.length ; i++){
	       		if( data[i].userId == data[i].ft_userId && data[i].ft_tabId == projectNumber && data[i].ft_userId != tabAdmFriCheck){
 	       			//console.log(projectNumber+"번 현재탭의 공유친구 확인 " + data[i].userId + "번친구가 " + data[i].ft_tabId + "번탭에" );
	       			projectAdmList.push(data[i]);
	       			//console.log("이프로젝트에 공유된친구는 누구죠 ? " + data[i].nickName + "통과된 자료번호는 :" + data[i].idN247_ft);
	       			tabAdmFriCheck = data[i].ft_userId;
	       			
	       		}
	       	}
			//중복아이디 제거를 위해 아이디 순서대로 정렬해준다.
	       	projectAdmList.sort(function(a, b) {
	       	  return a.ft_userId - b.ft_userId;
	       	});
			//아이디가 정렬되었으므로 중복된 아이들을 체크해서 빼준다.
			var dubbleIdCheck  = 0;
			var afterCheckId = [];
	       	for(var i=0 ; i<projectAdmList.length ; i++){
	       		if(projectAdmList[i].ft_userId != dubbleIdCheck){
	       			//console.log("체크 통과한 아이에요 :" + projectAdmList[i].ft_userId + "번 친구")
	       			dubbleIdCheck = projectAdmList[i].ft_userId;
	       			afterCheckId.push(projectAdmList[i]);
	       		}
	       	}
	       	//아이디 중복체크후 다시 넣어준다.
	       	projectAdmList = afterCheckId;
	       	
	       	
	       	//내가아니고  하나의 유저이며 승인이 완료된 친구 
	       	for(var i=0 ; i<data.length ; i++){
	       		if(data[i].userId != p2 && data[i].userId != userIdCheck && data[i].adm == 1){
	       			allfriendsList.push(data[i]);
	       			//console.log("왜 친구들이 매번 달라지죠? " + data[i].nickName);
	       			userIdCheck=data[i].userId;
	       		}
	       	}
	       	
	       	// 여기서 탭에 공유시킬 친구를 분류해줘야돼 
	       	if(projectAdmList.length != 0){
	       		for(var i=0 ; i<allfriendsList.length ; i++){
	       			//console.log("내친구 반복중 : " + allfriendsList[i].nickName);
	       			var addUserCheck = allfriendsList[i].userId;
	       			
		       		for(var j=0 ; j<projectAdmList.length ; j++){
		       			//console.log("걸러낼 친구 찾는중 : " + projectAdmList[j].userId);
		       			if(allfriendsList[i].userId == projectAdmList[j].userId){
		       				//console.log(projectAdmList[j].nickName+"체크통과못한 친구 : " + allfriendsList[i].nickName);
		       				addUserCheck = 0;
		       				break;
		       			}
		       		}
		       		
		       		if (addUserCheck != 0){
		       			//console.log(allfriendsList[i].userId+"체크통과한 친구 : " + allfriendsList[i].nickName);
		       			selectProjectAdmFriends.push(allfriendsList[i]);
		       		}
		       	}
	       	}else{
	       		for(var i=0 ; i<allfriendsList.length ; i++){
	       			selectProjectAdmFriends.push(allfriendsList[i]);
	       		}
	       		
	       	}
	       	
	       	$('#header_admSetting').empty();
	       	if(projectAdmList.length != 0){
		       	for (var i=0 ; i<projectAdmList.length ; i++){
		       		console.count("공유친구 반복" + projectAdmList[i].nickName + "님의 정보를 표시중 " );
					$('#header_admSetting').append(
							'<li>'+
			 				'<a href="#" onclick="projectUnAdmGetUserId('+ projectAdmList[i].idN247_ft +')" data-bs-toggle="modal" data-bs-target="#projectUnAdm" data-placement="top" title="'+projectAdmList[i].nickName+'">'+
			 				'<img alt="Claire Connors" class="avatar" src="/images/'+projectAdmList[i].userImg+'" /></a>'+
			 				'</li>');
					
					}	
	       	}else{	
	       		//console.log("친구가 없습니다. ");	
			}
	       	
	       	
	       	
			$('#header_admAddicon').html(
	   				'<button id="header_admAddicon" onclick="getAdmFriendsAjax()" class="btn btn-round flex-shrink-0" data-bs-toggle="modal" data-bs-target="#ProjectAdm">'+
	   				'<i class="material-icons">add</i></button>');
	       	
	       	
	       	$('#header_admAddicon').show();
	       	if(tabMasterCheck == 1){
	       		$('#header_admAddicon').hide();
	       	}

	       	for(var i=0 ; i<selectProjectAdmFriends.length ; i++){
	       			
	       			$('#modal_projectAdm_body').append(
	          		       		'<li>'+
	          		       		'<a data-toggle="tooltip" data-bs-toggle="modal" data-bs-target="#modal_info_'+selectProjectAdmFriends[i].userId+'" title="'+selectProjectAdmFriends[i].nickName+'">'+
	          		      		'<img alt="'+ selectProjectAdmFriends[i].nickName +'"class="avatar" src="/images/'+ selectProjectAdmFriends[i].userImg +'" />'+
	          		      		'</a>'+
	          		      		'<a>'+
	          		   		 	'</li>'
	          					);
	       			$('#modal_project_addFriends').append(
		       					'<div class="modal fade" id="modal_info_'+selectProjectAdmFriends[i].userId+'" tabindex="-1" aria-labelledby="userInfoModal" aria-hidden="true">'+
		          					'<div class="modal-dialog">'+
			          					'<div class="modal-content">'+
				          					'<div class="modal-header">'+
					          					'<h5 class="modal-title" id="userInfoModal">회원정보</h5>'+
					          					'<button type="button" class="close btn btn-round" data-bs-dismiss="modal" aria-label="Close">'+
					          					'<i class="material-icons">close</i>'+
					          					'</button>'+
				          					'</div>'+
				          					'<div class="modal-body">'+
					          					'<div class="row g-0">'+
						          					'<div class="col-md-4" id="moal_userInformation_userImg">'+
						          						'<img src="/images/'+selectProjectAdmFriends[i].userImg+'" class="img-fluid rounded-start" alt="...">'+
						          					'</div>'+
						          					'<div class="col-md-8">'+
							          					'<h5 class="card-title">'+selectProjectAdmFriends[i].nickName+'</h5>'+
							          					'<p class="card-text">'+selectProjectAdmFriends[i].id+'</p>'+
							          					'<p class="card-text">'+selectProjectAdmFriends[i].mb_introduce+'</p>'+
						          					'</div>'+
					          					'</div>'+
					          				'</div>'+
				          					'<div class="modal-footer">'+
				          						'<button type="button" onclick="insertProjectAdm('+selectProjectAdmFriends[i].userId+')" class="btn btn-secondary" data-bs-dismiss="modal">친구와 공유하기</button>'+
				          						'<button type="button" class="btn btn-secondary" data-bs-dismiss="modal">닫기</button>'+
				          					'</div>'+
			          					'</div>'+
		          					'</div>'+
		      					'</div>'
		       					);

	       	}
	       	// 아주 좋아 ();
	       	var admfriends_tabId = projectNumber;
	       	testtesttest(selectProjectAdmFriends,admfriends_tabId);
	       	
	       //console.log("현재 탭번호 이거임1 : " + projectNumber);
	    })
	}	
	//공유를 취소할 모달을 열었을때 버튼의 유형을 정해준다 유저의 아이디를 가져와 준비시킨다. 
	function projectUnAdmGetUserId(idN247_ft){
		$('#projectUnAdm_action_button').html(
				'<button id="projectUnAdm_action_button" type="button" onclick="projectUnAdmAction('+ idN247_ft +')" class="btn btn-outline-secondary" data-bs-dismiss="modal">예</button>'+
				'<button type="button" onclick="reload()" class="btn btn-outline-secondary" data-bs-dismiss="modal">아니오</button>');
	}
	//공유를 취소할때 유저의 아이디를 넣어주고 리로드 시킨다.
	function projectUnAdmAction(idN247_ft){
		
		console.log("취소시키기 성공 : " + idN247_ft);
		$.ajax({
	         url: 'isDelProjectAdm',
	    	 method: "POST",
	 	     data: {'idN247_ft': idN247_ft}
	 	     });
		location.reload();
		
	}
	
	
	//여기에 변수를 만들어 insertProjectAdm 함수안에서 로딩안하고 공유친구사진넣어주길 해보자 
	var admFriends_body = [];
	var admTabId = 0;
	function testtesttest(p1,admfriends_tabId){
		for(var i=0 ; i<p1.length ; i++){
			
			admFriends_body.push(p1[i]);
			//console.log("테스트 성공" + admFriends_body[i].nickName);
			break;
		}
		admTabId = admfriends_tabId;
		//console.log("현재 탭번호 이거임2 : " + admTabId);
	 }

	//이제 삭제 만들어야돼 리스트태그에 아이디심어놓고 삭제하면 함수실행하고 화면서 리스트 아이디 꺼주는걸로 하자.
	
	function insertProjectAdm(userId){
	//console.log("insertProjectAdm 실행 됐어요  : " + userId + "번 유저를 탭번호 :" + admTabId + "에 공유시켰습니다.");
		for (var i=0 ; i<admFriends_body.length ; i++){
			if (admFriends_body[i].userId == userId){
				
				//console.log("불러와서 찾기 성공" + admFriends_body[i].nickName);
				$('#header_admSetting').append(
						'<li>'+
		 				'<a href="#" data-bs-toggle="modal" data-bs-target="#projectUnAdm" data-placement="top" title="'+admFriends_body[i].nickName+'">'+
		 				'<img alt="Claire Connors" class="avatar" src="/images/'+admFriends_body[i].userImg+'" /></a>'+
		 				'</li>');
				
				break;
			}
		}
		
		console.log("받아온거 표시할게요 : userId =" + userId + " , tabId =" + admTabId);
		$.ajax({
	         url: 'insertProjectAdm',
	    	 method: "POST",
	 	     data: {'userId': userId , 'tabId' : admTabId}
	 	     });
		
		
	}
	
	var cardNumber = "";
	
	$(".nav-link").on("click",function(){
		document.getElementById('navbar-togglerButton').click();
	});
	
	
	
	
	function reload(){
		$('#cardModalLabel').text(''); 
		$('#modal_card_body').html(''); 
		
	};
	
	var n247_rePoId = 0;
	function getOneCardAjax(cardId){
		n247_rePoId = cardId;
		getOneCardAjax2(cardId);
	}
	
	function input_replyAction(n247_reDes){

		$("#input_card_reply").val('');
		
		//console.log("댓글입력 끝나고 카드 모달 다시보여줘야돼 : " + n247_rePoId);
		createReplyAction(n247_reDes,p2,n247_rePoId,p1);
	}
 	
	
	var Card_id = 0;
	var fileUpdateCheck = 0;
	var upFile_id = 0;
	var upFile_name = "";
	function getOneCardAjax2(cardId){
		
		 //console.log("어디까지 오나1 : " + cardId);
		 $.ajax({
	         url: 'getOneCardAjax',
	    	 method: "POST",
	 	     data: {'id': cardId}
	 	     })
	 	
		.done(function(data) {
			var myCardCheck = 0;
			var oneCardList = [];
			$('#modal_card_updateFile_body_fileSelecter').empty();
			$('#modal_card_updateFile_body_deleteFile').empty();
			for(var i=0 ; i<data.length ; i++){
				if(data[i].N247_reUsId == data[i].userId || data[i].N247_reUsId == null){
					oneCardList.push(data[i]);
				}
				
			}
			$("#input_card_reply").val('');
			$('#replyInputTest').html('');
			
			$('#cardModalLabel').text(oneCardList[0].postTitle);
			$('#modal_card_body').append(
					'<div id="cardImageDiv"></div>'+
					'<div id="fileDownLink"></div>'+
					'<div id="cardDescriptionDiv"></div>'+
					'<div id="replyLine"></div>'+
					'<div id="replyListDiv"></div>'+
					'<div id="replyInputTest"></div>'
					);
			$('#cardImageDiv').hide();
			if(oneCardList[0].up_fileName != null && oneCardList[0].up_isDel == 0){
				$('#cardImageDiv').show();
				$('#cardImageDiv').html('<img src="/images/'+oneCardList[0].up_fileName+'" class="card-img-top" alt="...">');	
			}
			if(oneCardList[0].up_check == 1){
				$('#cardImageDiv').html('');
				$('#fileDownLink').html('<a href="/images/'+ oneCardList[0].up_fileName +'" download="'+ oneCardList[0].up_orgName +'">'+ oneCardList[0].up_orgName +'</a>');
			}
			if(oneCardList[0].description === ""){
				$('#cardDescriptionDiv').html('<br><h6>내용을 입력해주세요</h6>');
			}else{
				$('#cardDescriptionDiv').html('<br><h6>'+ oneCardList[0].description +'</h6>');
			}
			
			$("#replyListDiv").empty();
			$("#replyLine").empty();
			$('#replyLine').html('<hr>');
			
			var reIdCheck = 0;
			for(var i=0 ; i<oneCardList.length ; i++){
				if(oneCardList[i].N247_reDes != null && oneCardList[i].idN247_re != reIdCheck && oneCardList[i].N247_reUsId == p2 && oneCardList[i].N247_reIsDel == 0){
					reIdCheck = oneCardList[i].idN247_re;
					$("#replyListDiv").append(
							'<div class="row">'+
								'<div class="col" id="card_reply_'+oneCardList[i].idN247_re+'">'+
							      '<small style="color:black">'+oneCardList[i].nickName+'</small>'+
							      '<a><small  onclick="replyUpdate(this.id)" id="'+oneCardList[i].idN247_re+'">'+' '+oneCardList[i].N247_reDes+'</small></a>'+
							      '</div>'+
						    '</div>');
				}else if(oneCardList[i].N247_reDes != null && oneCardList[i].idN247_re != reIdCheck && oneCardList[i].N247_reUsId != p2 && oneCardList[i].N247_reIsDel == 0){
					reIdCheck = oneCardList[i].idN247_re;
					$("#replyListDiv").append(
							'<div class="row">'+
								'<div class="col" id="card_reply_'+oneCardList[i].idN247_re+'">'+
							      '<small style="color:black">'+oneCardList[i].nickName+'</small>'+
							      '<a id="'+oneCardList[i].idN247_re+'"><small>'+' '+oneCardList[i].N247_reDes+'</small></a>'+
							      '</div>'+
						    '</div>');
				}
				
			}
			
			
			//console.log("카드내용 체크 : " + oneCardList[0].description);	

			if(oneCardList[0].userNum == p2){
				myCardCheck = 1;
			}
			$("#cardModalLabel").on("click",function(){
				$('#cardModalLabel').empty();
				if(myCardCheck == 1){
					$('#cardModalLabel').html(
							'<input type="text" class="form-control col" onKeypress="javascript:if(event.keyCode==13) {updateCardTitle(this.value,'+oneCardList[0].id+')}" value="'+ oneCardList[0].postTitle +'" id="update_card_title" maxlength="45">'
							);
					getOneCardId(oneCardList[0].dueDayString);
				}else{
					$('#cardModalLabel').text(oneCardList[0].postTitle);
				}
	
			});

			$("#cardDescriptionDiv").on("click",function(){
				$('#cardDescriptionDiv').empty();
				if(myCardCheck == 1){
					$('#cardDescriptionDiv').html(
							'<textarea class="form-control col" rows="3"  onKeypress="javascript:if(event.keyCode==13) {updateCardDescription(this.value,'+oneCardList[0].id+')}">'+oneCardList[0].description+'</textarea>'+
							'<p>'+
							'<div class="container">'+
								'<div class="row justify-content-md-center">'+
									'<div class="col-md-auto">'+
							   		    '<a style="color:#666666" data-toggle="tooltip" title="파일첨부" onclick="updateCardFile()" data-bs-toggle="modal" data-bs-target="#modal_card_updateFile">'+
							   			'<i class="material-icons">attach_file</i>'+
							       		'</a><p>'+
						       		'</div>'+
					   			'</div>'+
				   			'</div>'
					       		);
					
				}else{
					
					$('#cardDescriptionDiv').html('<br><h6>'+ oneCardList[0].description +'</h6>');
					
				}
				card_id = oneCardList[0].id;
				//console.log("up_fileName : " + oneCardList[0].up_fileName);
				//console.log("oneCardList[0].up_isDel : " + oneCardList[0].up_isDel);
				//console.log("fileUpdateCheck 1: " + fileUpdateCheck);
				if(oneCardList[0].up_isDel == 1){
					fileUpdateCheck = 1;
					//console.log("fileUpdateCheck 2: " + fileUpdateCheck);
				}else if(oneCardList[0].up_fileName == null){
					fileUpdateCheck = 1;
					//console.log("fileUpdateCheck 3: " + fileUpdateCheck);
				}else{
					upFile_id = oneCardList[0].idN247_up;
					upFile_name = oneCardList[0].up_orgName;
				}
			});
		})
		//console.log("어디까지 오나2 : " + n247_rePoId);
		
	};
	var dueDay = '';
	function getOneCardId(dueDayString){
		dueDay = dueDayString;
	}
	
	//리플레이 업데이트 함수
	var replyUpdateId = 0;
	function replyUpdate(replyId){
		replyUpdateId = replyId;
		$('#card_reply_'+replyId+'').html(
				'<div class="input-group">'+
				'<input type="text" class="form-control col" onKeypress="javascript:if(event.keyCode==13) {replyDesUpdate(this.value)}" placeholder="댓글을 입력해주세요" maxlength="45">'+
				'<button onclick="deleteReply()" class="btn btn-outline-secondary" type="button">삭제</button>'+
				'</div>'
				);
	}
	
	function replyDesUpdate(updateDes){
		$.ajax({
	         url: 'updateReplyDesAjax',
	    	 method: "POST",
	 	     data: {'n247_reDes': updateDes, 'idN247_re' : replyUpdateId}
	 	     })
	 	
		.done(function() {	
			
			$('#card_reply_'+replyUpdateId+'').html(
					 '<small style="color:black">'+userNickName+'</small>'+
				      '<a><small  onclick="replyUpdate(this.id)" id="'+replyUpdateId+'">'+' '+updateDes+'</small></a>'
					);
			
		});
	};
	
	function deleteReply(){
		$.ajax({
	         url: 'deleteReplyAjax',
	    	 method: "POST",
	 	     data: {'idN247_re' : replyUpdateId}
	 	     })
	 	
		.done(function() {	
			
			$('#card_reply_'+replyUpdateId+'').empty();
			
		});
	}
	
	
	function updateCardFile(){
		//console.log("fileUpdateCheck 4: " + fileUpdateCheck);
		if(fileUpdateCheck == 1){
			$('#modal_card_updateFile_footerButton').show();
			$('#modal_card_updateFile_body_fileSelecter').html(
					'<label class="col-3">Upload</label><input type="file" name=file class="form-control col">'		
					);
			$('#modal_card_updateFile_footer').html(
			'<input type="hidden" name="up_userId" value="'+p2+'">'+
			'<input type="hidden" name="up_postId" value="'+card_id+'">'+
			'<input type="hidden" name="up_tabId" value="'+projectNumber+'">'
			);
			$('#modal_card_updateFile_message').text('업로드 할 수 있는 파일의 용량은 10Mbyte 이하 입니다.');
			
		}else{
			$('#modal_card_updateFile_body_deleteFile').html('<label class="col-3">현재파일</label><a href="#" onclick="deleteUpFile('+upFile_id+')" >'+upFile_name+' 삭제하기</a>');
			$('#modal_card_updateFile_footerButton').hide();
			$('#modal_card_updateFile_message').text('한개의 파일만 업로드 가능합니다. 삭제 후 업로드해주세요.');
		}
		
		
	}
	function deleteUpFile(idN247_up){
		$.ajax({
	         url: 'deleteUpFileAjax',
	    	 method: "POST",
	 	     data: {'idN247_up': idN247_up}
	 	     })
	 	
		.done(function() {	
			
			document.getElementById('modal_file_closeButton').click();
			window.location.reload();
		});
	};
	
	function updateCardTitle(postTitle,id){
		
		 $.ajax({
	         url: 'updateCardTitleAjax',
	    	 method: "POST",
	 	     data: {'postTitle': postTitle, 'id' : id }
	 	     })
	 	
		.done(function() {		
			$('#cardModalLabel').text(postTitle);	
			$('#card_title_'+id+'').html('<h6 data-filter-by="text" id="card_title_'+id+'">'+postTitle+'<p><small>'+dueDay+' 까지 완료</small></h6><br>');
		});
	};
	
	function updateCardDescription(description,id){
		//console.log("어디까지 오나3 : " + description);
		 $.ajax({
	         url: 'updateCardDescriptionAjax',
	    	 method: "POST",
	 	     data: {'description': description, 'id' : id }
	 	     })
	 	
		.done(function() {		
			$('#cardDescriptionDiv').html('<br><h6>'+description+'</h6>');
			$('#card_description_'+id+'').text('\u00a0\u00a0\u00a0'+description+'');
		});
	};
	
	document.onkeydown = function(evt) {
	    evt = evt || window.event;
	    if (evt.keyCode == 27) {
	    	reload();
	    }
	};
	
	var insertReplyPreNumber = 1;
	
	function createReplyAction(n247_reDes,p2,n247_rePoId,p1){
		// console.log("댓글창 입력중 :" + n247_reDes +"포스트 번호는? :" + n247_rePoId );
		 $.ajax({
	         url: 'ajax_createReplyAction',
	    	 method: "POST",
	 	     data: {'n247_reDes': n247_reDes, 'n247_reUsId' : p2, 'n247_rePoId' : n247_rePoId, 'tabId' : p1}
	 	     })
	 	
		.done(function() {
			insertReplyPreNumber += 1;
			//console.log("임시번호 부여하기 어떻게 되가니" + insertReplyPreNumber);
			$('#replyInputTest').append(
									'<div class="row">'+
										'<div class="col" id="reply_reinsert_'+insertReplyPreNumber+'">'+
									      '<small style="color:black">'+replyUser+'</small>'+
									      '<a><small  onclick="replyReInsert(this.id)" id="'+n247_reDes+'">'+' '+n247_reDes+'</small></a>'+
									    '</div>'+
							    	'</div>');
			
			
		})
	};
	
	function replyReInsert(n247_reDes){
		//console.log("임시번호 부여하기 어떻게 되가니2" + insertReplyPreNumber);
		$('#reply_reinsert_'+insertReplyPreNumber+'').html(
				'<div class="input-group">'+
				'<input type="text" class="form-control col" onKeypress="javascript:if(event.keyCode==13) {updateReDesToDes(this.value)}" placeholder="수정할 내용을 입력해 주세요" maxlength="45">'+
				'<button onclick="deleteChangeReply()" class="btn btn-outline-secondary" type="button">삭제</button>'+
				'</div>'
				);
		replyReInsert_delReplyDes(n247_reDes);
	}
	
	var delReply = '';
	function replyReInsert_delReplyDes(delReDes){
		delReply=delReDes;
		console.log("찾을 내용 받음 :" + delReply);
		//여기서 내용찾아서 원래쓴거 지워

	}
	
	function updateReDesToDes(insertDes){
		 console.log("찾아서 바꿀 내용 받음 :" + insertDes);
		 $.ajax({
	         url: 'updateReplyDesToDesAjax',
	    	 method: "POST",
	 	     data: {'n247_reDes': delReply, 'insertDes' : insertDes}
	 	     })
	 	
		.done(function() {
			
			
			
			
			modalClose();	
		});
		
	}
	
	function modalClose(){
		document.getElementById('modal_card_closeButton').click();
	}
	
	function deleteChangeReply(){
		$.ajax({
	         url: 'deleteChangeReplyAjax',
	    	 method: "POST",
	 	     data: {'n247_reDes' : delReply}
	 	     })
	 	
		.done(function() {	
			
			$('#reply_reinsert_'+insertReplyPreNumber+'').empty();
			
		});
	}
	
    </script>
    
    
    
	
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