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
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.8.1/font/bootstrap-icons.css">
    <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
    <link href="https://fonts.googleapis.com/css?family=Gothic+A1" rel="stylesheet">
    <link href="resources/theme.css" rel="stylesheet" type="text/css" media="all" />
     <!-- Bootstrap CSS -->
	<link href="resources/list-groups.css" rel="stylesheet">
	<link href="resources/sidebars.css" rel="stylesheet">
	<script src="https://code.jquery.com/jquery-1.12.4.min.js"></script>
	<!-- 카카오 스크립트 -->
	<script src="https://developers.kakao.com/sdk/js/kakao.js"></script>
    
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
		      <a href="#" onclick="getUserInfo()" data-bs-toggle="modal" data-bs-target="#userInfo">
		        <img src="${userInformation.userImg }" alt="" width="32" height="32" class="rounded-circle me-2">	        
		      </a>
          </div>
        </div>
        <div class="collapse navbar-collapse flex-column" id="navbar-collapse">
          <p>
          <div id="sideNavbar">
          </div>
          <div>
              <div class="input-group input-group-dark input-group-round">
                <div class="input-group-prepend">
                  <span class="input-group-text">
                    <i class="material-icons" >search</i>
                  </span>
                </div>
                <input name="search" type="search" onKeypress="javascript:if(event.keyCode==13) {searchCard(this.value)}" class="form-control form-control-dark" placeholder="Search" aria-label="Search app">
              </div>
              <button class="btn btn-primary btn-block" type="button" onclick="newProjectModal()" data-bs-toggle="modal" data-bs-target="#project-create-modal" id="nav_newButton">
                New
              </button>
          </div>
        </div>
        <div class="d-none d-lg-block">
		      <a href="#" onclick="getUserInfo()" data-bs-toggle="modal" data-bs-target="#userInfo">
		        <img src="${userInformation.userImg }" alt="" width="32" height="32" class="rounded-circle me-2">	        
		      </a>
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
     				<div id="modal_newProject_minDay">
     				</div>
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
		   <div class="modal-body" id="modal_userInfo_body">
		   <div class="row g-0">
			   	<div class="col-md-4">
			   		<img src="${userInformation.userImg }" class="img-fluid rounded-start" alt="..." data-bs-toggle="modal" data-bs-target="#userImgUpdate">
			   	</div>
			   	<div class="col-md-8">
			   		<h5 class="card-title">${userInformation.nickName }</h5>
			   		<div id="modal_userInfo_intro"></div>
			   	</div>
			</div>
			</div>
			<div class="modal-footer">
			  <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">닫기</button>
			  <button type="button" class="btn btn-secondary" onclick="getAdmFriendsAjax()" data-bs-toggle="modal" data-bs-target="#member-edit">친구관리</button>
			   <button type="button" class="btn btn-secondary" onclick="logout();">로그아웃</button>
		    </div>
		  </div>
		</div>
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
                 <a class="nav-link" id="task-add-members-tab" onclick="getFriendsListByType(1)" data-toggle="tab" href="#task-add-members1" role="tab" aria-controls="task-add-members1" aria-selected="false">새로운 친구추가</a>
               </li>
                <li class="nav-item">
                 <a class="nav-link" id="task-add-members-tab" onclick="getFriendsListByType(2)" data-toggle="tab" href="#task-add-members2" role="tab" aria-controls="task-add-members2" aria-selected="false">친구 관리</a>
               </li>
             </ul> 
		   <div class="modal-body">
	   	     <div class="tab-content">
	   	     	
	   	     	<div class="tab-pane fade" id="task-add-members1" role="tabpanel">
	                <div class="users-manage" data-filter-list="form-group-users">
	                	<h6>새로운 친구</h6>
	                      <div class="mb-3">
	                      	<div class="row">
	                      		<div class="col-9">
	                      		<input type="text" class="form-control" name="search" placeholder="친구이름을 검색해주세요." onchange="search_friendInput()" aria-label="friendsEmail" aria-describedby="button-addon2" id="madal_searchFriend_input">
	                      		</div>
	                      		<div class="col-3">
	                      		<button class="btn btn-outline-secondary" type="button" id="modal_friendSearch_inputButton" onclick="search_friendAction()" disabled >검색</button>
	                      		</div>
	                      	</div> <br>
	                      	<div class="row justify-content-center">
		                      	<div class="col-auto" id="modal_friendSearchResult">			                      	
		                      	</div>
	                      	</div>  
						  </div>  
	                  	<hr>
	                  	<div class="row">
	                  		<h6>보낸 친구 요청</h6>
	                  	</div>
	                    
		                  <div class="mb-3" id="modal_members_type1">
		                  </div>
	                </div>
	            </div>



               <div class="tab-pane fade" id="task-add-members2" role="tabpanel">
                <div class="users-manage" data-filter-list="form-group-users">
                  <h6>현재 친구들</h6>
                  <div class="mb-3" id="modal_members_type2">
                  </div>
                 <hr>
                 <h6>받은 친구 요청</h6>
                  <div class="alert alert-warning text-small" role="alert">
	                 <div class="mb-3" id="modal_members_type3">
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
		
			 <div class="modal fade" id="project-editDueDay-modal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">		    
				<form action="updateTabDueDayAction" id="myForm" class="form-inline" method="POST">
				  <div class="modal-dialog">	      
				    <div class="modal-content">
					  <div class="modal-header">
						 <h5 class="modal-title" id="exampleModalLabel">완료일 변경</h5>
						 <button type="button" class="close btn btn-round" data-bs-dismiss="modal" aria-label="Close">
		    			 <i class="material-icons">close</i></button>
					  </div>
				      <div class="modal-body">
						 <div class="mb-3" id="modal-editDueDay-body">
						 </div>
					  </div>
					  <div class="modal-footer">
					     <button type="submit" class="btn btn-secondary">확인</button>
						 <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">닫기</button>
						 <div id="modal-editDueDay-footer">
						 </div>
					  </div>     
				    </div>
				  </div>
				</form>
		     </div>	
		     
<!-- 						삭제시 두가지 모달이 필요함  -->
			
				
<!-- 			 <div class="modal fade" id="project-del-modal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">		     -->
<!-- 			   <form action="deleteTabAction" id="myForm" class="form-inline" method="get"> -->
<!-- 				  <div class="modal-dialog">	       -->
<!-- 				    <div class="modal-content"> -->
<!-- 					  <div class="modal-header"> -->
<!-- 						<h5 class="modal-title" id="exampleModalLabel">프로젝트 삭제</h5> -->
<!-- 						<button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button> -->
<!-- 				      </div> -->
<!-- 				      <div class="modal-body"> -->
<!-- 					    <div class="mb-3" id="modal_deleteProject_body" > -->
<!-- 					    </div> -->
<!-- 				      </div> -->
<!-- 				      <div class="modal-footer"> -->
<!-- 					    <button type="submit" class="btn btn-secondary">삭제</button> -->
<!-- 					    <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">닫기</button> -->
<!-- 					    <div id="modal_deleteProject_footer"></div> -->
<!-- 					  </div> -->
<!-- 				    </div> -->
<!-- 				  </div> -->
<!-- 			   </form> -->
<!-- 			 </div> -->
						
						
			 <div class="modal fade" id="project-select-modal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">		    
			   <div class="modal-dialog">	      
				 <div class="modal-content">
				   <div class="modal-header">
			         <h5 class="modal-title" id="exampleModalLabel">프로젝트안에 카드가 있습니다. 이동시키시겠습니까?</h5>
					 <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
				   </div>
				   <div class="modal-body" id="modal_projectSelect_body">
				    </div>
				    <div class="modal-footer"> 
					  <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">닫기</button>
					  <button type="button" onclick="projectWithCardDeleteAction()" class="btn btn-secondary">카드 모두삭제</button>
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
			              
			              <div class="form-group row" id="modal_cardAdd_fileUp">  
							<label class="col-3">Upload</label>
							<input type="file" name=file onchange="fileUpCheck()" class="form-control col">
			              </div>
			                  <hr> 
			                  <h6>Timeline</h6>
					<div class="form-group row align-items-center">
			   				<label class="col-3" for="date">카드 완료일 </label>
			   				<div id="modal_newCard_minDay"></div>
			 				</div>
			          </div>
			          <div class="alert alert-warning text-small" id="modal_newCard_alertMessage" role="alert">
			               
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
	       <h5 class="modal_card_title" style="color:white" id="cardModalLabel"></h5>
	       <div class="modal_card_title_input"></div>
	        <button type="button" onclick="reload()" class="close btn btn-round" data-bs-dismiss="modal" aria-label="Close" id="modal_card_closeButton">
				<i class="material-icons">close</i>
			</button>
	      </div>
	      <div class="modal-body" id="modal_card_body">
	
	      </div>
	      <div class="modal-footer" id="modal_card_footer">
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
	
	<div class="modal fade" id="search_friendsInfo_modal" data-bs-backdrop="static" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
	  <div class="modal-dialog">
	    <div class="modal-content">
	      <div class="modal-header">
	       <h5 class="modal-title">친구 정보</h5>
	        <button type="button" onclick="reload()" class="close btn btn-round" data-bs-dismiss="modal" aria-label="Close">
				<i class="material-icons">close</i>
			</button>
	      </div>
	      <div class="modal-body">
			<ul class="avatars" id="modal_searchFriendsInfo_body">
			</ul>
	      </div>
	      <div class="modal-footer" id="modal_searchFriendsInfo_footer">
	      </div>
	    </div>
	  </div>
	</div>
	
	
	<div id="modal_project_addFriends">
	</div>	

	

	
<!-- 	공유취소 모달 -->
	<div class="modal fade" id="projectUnAdm" data-bs-backdrop="static" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
	  <div class="modal-dialog">
	    <div class="modal-content">
	      <div class="modal-header">
	       <h5 class="modal-title">공유된 회원 정보</h5>
	        <button type="button" class="close btn btn-round" data-bs-dismiss="modal" aria-label="Close">
				<i class="material-icons">close</i>
			</button>
	      </div>
	      <div class="modal-body" id="projectUnAdm_modalBody">
	      </div>
	      <div class="modal-footer" id="projectUnAdm_action_button">
	      </div>
	    </div>
	  </div>
	</div>
	
	<div><input type="hidden" name="tabId" value="" id="projectId"></div>
    <script type="text/javascript" src="http://code.jquery.com/jquery-3.5.1.min.js"></script>  
    <script>
   
    
    //jstl 이 들어가면 밖으로 못뽑아 분리 안됨 
    var p1 = 0;
 	var p2 = '${userInformation.userId }';
 	var userNickName = '${userInformation.nickName }';
 	var allFriendsList = [];
 	var minDay = '2022-01-01';
 	var myInfor = [];
   	var allList = [];
   	var navBarProjectList=[];
	//받아온 아이디로 최초 출력시 프로젝트 리스트중 가장 최근 정보를 푼다.
	//그리고 userId도 함께 받아온다.
	
	
	//최초 탭목록 띄우기 파라미터 유저아이디
	getProjectSideList(p2)

	function getProjectSideList(userId){
		
		 $.ajax({
	         url: 'getProjectListAjax',
	    	 method: "POST",
	 	     data: {'userId': userId }
	 	     })
	 	
		.done(function(data) {
			navBarProjectList = data ;
			$('#sideNavbar').html(
					'<ul class="navbar-nav d-lg-block">'+
					'<span class="text-small text-muted">진행중 프로젝트</span>'+
					'<li class="nav-item" id="navList_progress"></li>'+
					'<hr><span class="text-small text-muted">완료된 프로젝트</span>'+
					'<li class="nav-item" id="navList_comp"></li>'+
					'<hr><span class="text-small text-muted">공유 프로젝트</span>'+
					'<li class="nav-item" id="navList_adm"></li>'+
					'</ul>'
					);
			if(navBarProjectList.length != 0){
				//프로젝트리스트를 가져오면 가장 첫번째 탭번호를 p1에 입력
				p1 = navBarProjectList[0].tabId;
				$('#projectId').val(navBarProjectList[0].tabId);
				//이제 프로젝트의 기본정보와 속해있는 카드를 보여줄 차례 (카드작성자의 정보도 포함)
				
				
				console.log('프로젝트가 있어서 가장 최근의 프로젝트 번호를 입력했어요 :'+p1);
				//사이드바에 보여줄 아이들을 분류해줍니다.
				for (var i=0 ; i<navBarProjectList.length ; i++){
					if(navBarProjectList[i].userNum != p2){
						$('#navList_adm').append('<a href="#" class="nav-link" onclick="getProject(this.id)" aria-current="page" id="'+navBarProjectList[i].tabId+'"> '+navBarProjectList[i].tabTitle+'</a>');
					}else{
						if(navBarProjectList[i].isDel != 0){
							$('#navList_comp').append('<a href="#" class="nav-link" onclick="getProject(this.id)" aria-current="page" id="'+navBarProjectList[i].tabId+'"> '+navBarProjectList[i].tabTitle+'</a>');
						}else{
							$('#navList_progress').append('<a href="#" class="nav-link" onclick="getProject(this.id)" aria-current="page" id="'+navBarProjectList[i].tabId+'"> '+navBarProjectList[i].tabTitle+'</a>');
						}
					}	
				}
			}else{
				//만약 가지고있는 프로젝트가 없을시 프로젝트를 만들 수 있는 모달을 열어준다.
				document.getElementById('nav_newButton').click(); 
			}
			//이제 프로젝트의 기본정보와 속해있는 카드를 보여줄 차례 (카드작성자의 정보도 포함)

			getAllList(p1,p2);
			console.log('프로젝트 리스트 받아왔니 2:' + navBarProjectList.length);
			//프로젝트 카드를 보여준다.
			
			
		})
		
	}

	 function getAllList(p1,p2){
			console.log(p1,p2);
			 $.ajax({
		         url: 'getAllListAjax',
		    	 method: "POST",
		 	     data: {'tabId': p1, 'userId' : p2}
		 	     })
		 	
			.done(function(data) {
				allList=data;
				console.log('모든리스트 받아오기 작동중입니다. allList의 갯수는 :' + allList.length);
//	 			getProjectAdmFriendsAjax(p1);
				getFriendsList();
	 			getAdmFriendsAjax();
				getCardList2(allList);
			})
		}
		
		
	var userFriCheck = 0;
	
	//친구목록을 불러온다.
	if(userFriCheck == 1){
		getFriendsList();
	}else{
		
	}
	
	//친구목록 불러오는 함수
	function getFriendsList(){
		
		 $.ajax({
	         url: 'getFriendsListAjax',
	    	 method: "POST",
	 	     data: {'userId' : p2}
	 	     })
	 	
		.done(function(data) {
			allFriendsList = data;
			for(var i=0 ; i<allFriendsList.length ; i++){
					if(allFriendsList[i].userId != p2){
						//console.count(allFriendsList[i].userId +'번 친구가 확인되었습니다.');
					}else{
						myInfor.push(allFriendsList[i])
					}
				}	
			console.log(myInfor[0].mb_introduce);
			//여기서 자신의 정보를 찾는다 - 회원정보에 띄우기위해 
			if(myInfor[0].mb_introduce != null){
				$('#modal_userInfo_body').html(
						'<div class="row g-0">'+
						'<div class="col-md-4">'+
						'<img src="'+myInfor[0].userImg+'" class="img-fluid rounded-start" alt="...">'+
						'</div>'+
						'<div class="col-md-8">'+
						'<h5 class="card-title">'+myInfor[0].nickName+'</h5>'+
						'<p class="card-text">'+myInfor[0].mb_introduce+'</p>'+
						'<p class="card-text"><small class="text-muted">Last updated '+myInfor[0].lastModified+'</small></p>'+
						'</div>'+
						'</div>'
						);
			}else{
				$('#modal_userInfo_body').html(
						'<div class="row g-0">'+
						'<div class="col-md-4">'+
						'<img src="'+myInfor[0].userImg+'" class="img-fluid rounded-start" alt="...">'+
						'</div>'+
						'<div class="col-md-8">'+
						'<h5 class="card-title">'+myInfor[0].nickName+'</h5>'+
						'<p class="card-text">소개글을 입력해주세요.</p>'+
						'<p class="card-text"><small class="text-muted">Last updated '+myInfor[0].lastModified+'</small></p>'+
						'</div>'+
						'</div>'
						);
			}
			
		})

	}
	
	//타입별 분류
	function getFriendsListByType(typeNum){
		
		getFriendsList();
		
		console.log('테스트 성공 타입별 친구부르기 타입은? :' + typeNum);
		var iWaitAdmList = [];
		var iApproveAdmList = [];
		var friendApproveAdmList = [];
		var waitingAdmList = [];
		
		if(typeNum == 1){
			//내가 보낸 승인대기
			for(var i=0 ; i<allFriendsList.length ; i++){
				if(allFriendsList[i].myId == p2 && allFriendsList[i].userId != p2 && allFriendsList[i].adm == 0){
					iWaitAdmList.push(allFriendsList[i]);
					console.log('내가보낸 승인대기 친구는'+allFriendsList[i].nickName);
				}
			}
			
			//1번분류
			$('#modal_members_type1').html(
					'<ul class="avatars text-center">'+
					'<li id="membersType1"></li>'+
					'</ul>');
			if(iWaitAdmList.length != 0){
				for(var i=0 ; i<iWaitAdmList.length ; i++){
					$('#membersType1').append(
							'<a href="#" onclick="iwaitFriends_modal_bodayAction('+iWaitAdmList[i].idN247_f+')" data-bs-toggle="modal" data-bs-target="#search_friendsInfo_modal">'+
							'<img alt="'+iWaitAdmList[i].nickName+'" src="'+iWaitAdmList[i].userImg+'" class="avatar">'+
							'<input type="hidden" id="memberType1_firendsInputNick'+iWaitAdmList[i].idN247_f+'" value="'+iWaitAdmList[i].nickName+'">'+
							'<input type="hidden" id="memberType1_firendsInputImg'+iWaitAdmList[i].idN247_f+'" value="'+iWaitAdmList[i].userImg+'">'+
							'<input type="hidden" id="memberType1_firendsInputIntro'+iWaitAdmList[i].idN247_f+'" value="'+iWaitAdmList[i].mb_introduce+'">'+
							'</a>'
							);
				}
			}
		}else{
			//친구가 보낸 승인대기
			for(var i=0 ; i<allFriendsList.length ; i++){
				if(allFriendsList[i].fUserId == p2 && allFriendsList[i].userId != p2 && allFriendsList[i].adm == 0){
					waitingAdmList.push(allFriendsList[i]);
					console.log('승인대기 친구는'+allFriendsList[i].nickName);
				}else if(allFriendsList[i].myId == p2 && allFriendsList[i].userId != p2 && allFriendsList[i].adm == 1){
					iApproveAdmList.push(allFriendsList[i]);
					console.log('내가추가한 친구목록은'+allFriendsList[i].nickName);
				}else if(allFriendsList[i].fUserId == p2 && allFriendsList[i].userId != p2 && allFriendsList[i].adm == 1){
					friendApproveAdmList.push(allFriendsList[i]);
					console.log('친구가 신청한 목록은'+allFriendsList[i].nickName);
				}
			}
			//2번분류
			$('#modal_members_type2').html(
					'<ul class="avatars text-center">'+
					'<li id="membersType2"></li>'+
					'<li id="membersType3"></li>'+
					'</ul>');
			
			$('#modal_members_type3').html(
					'<ul class="avatars text-center">'+
					'<li id="membersType4"></li>'+
					'</ul>');
			if(iApproveAdmList.length != 0){
				for(var i=0 ; i<iApproveAdmList.length ; i++){
					$('#membersType2').append(
							'<a href="#" onclick="myFriends_modal_bodyAction('+iApproveAdmList[i].idN247_f+')" data-bs-toggle="modal" data-bs-target="#search_friendsInfo_modal">'+
							'<img alt="'+iApproveAdmList[i].nickName+'" src="'+iApproveAdmList[i].userImg+'" class="avatar">'+
							'<input type="hidden" id="memberType23_firendsInputNick'+iApproveAdmList[i].idN247_f+'" value="'+iApproveAdmList[i].nickName+'">'+
							'<input type="hidden" id="memberType23_firendsInputImg'+iApproveAdmList[i].idN247_f+'" value="'+iApproveAdmList[i].userImg+'">'+
							'<input type="hidden" id="memberType23_firendsInputIntro'+iApproveAdmList[i].idN247_f+'" value="'+iApproveAdmList[i].mb_introduce+'">'+
							'</a>'
							);
				}
			}
			if(friendApproveAdmList.length != 0){
				for(var i=0 ; i<friendApproveAdmList.length ; i++){
					$('#membersType3').append(
							'<a href="#" onclick="myFriends_modal_bodyAction('+friendApproveAdmList[i].idN247_f+')" data-bs-toggle="modal" data-bs-target="#search_friendsInfo_modal">'+
							'<img alt="'+friendApproveAdmList[i].nickName+'" src="'+friendApproveAdmList[i].userImg+'" class="avatar">'+
							'<input type="hidden" id="memberType23_firendsInputNick'+friendApproveAdmList[i].idN247_f+'" value="'+friendApproveAdmList[i].nickName+'">'+
							'<input type="hidden" id="memberType23_firendsInputImg'+friendApproveAdmList[i].idN247_f+'" value="'+friendApproveAdmList[i].userImg+'">'+
							'<input type="hidden" id="memberType23_firendsInputIntro'+friendApproveAdmList[i].idN247_f+'" value="'+friendApproveAdmList[i].mb_introduce+'">'+
							'</a>'
							);
				}
			}
			if(waitingAdmList.length != 0){
				for(var i=0 ; i<waitingAdmList.length ; i++){
					$('#membersType4').append(
							'<a href="#" onclick="subFriends_modal_bodayAction('+waitingAdmList[i].idN247_f+')" data-bs-toggle="modal" data-bs-target="#search_friendsInfo_modal">'+
							'<img alt="'+waitingAdmList[i].nickName+'" src="'+waitingAdmList[i].userImg+'" class="avatar">'+
							'<input type="hidden" id="memberType4_firendsInputNick'+waitingAdmList[i].idN247_f+'" value="'+waitingAdmList[i].nickName+'">'+
							'<input type="hidden" id="memberType4_firendsInputImg'+waitingAdmList[i].idN247_f+'" value="'+waitingAdmList[i].userImg+'">'+
							'<input type="hidden" id="memberType4_firendsInputIntro'+waitingAdmList[i].idN247_f+'" value="'+waitingAdmList[i].mb_introduce+'">'+
							'</a>'
							);
				}
			}
		}
		
		
	}
	
	getMinDay();
	
	//오늘날짜 구해줌
	function getMinDay(){
		let today = new Date();
		let year = today.getFullYear();
		let firstMonth = today.getMonth() + 1 ;
		let month = '';
		
		if(firstMonth < 10){
			month = '0' + firstMonth;
		}else{
			month = firstMonth ;
		}
		
		let date = today.getDate();
		minDay = year + '-' + month + '-' + date ;	
	}
	
	
	
	function newProjectModal(){
		$('#modal_newProject_minDay').html('<input class="form-control col flatpickr-input" type="date" name="tab_dueDay" id="date" min="'+minDay+'">');
	}
	
   	
   	function getCardList2(allList){
 		var allCardList = [];
 		var postCheck = 0;
 		var projectInfo = allList;
 		for(var i=0 ; i<allList.length ; i++){
 			if( allList[i].id === postCheck){
 			}else if(allList[i].isDel == 0 || allList[i].isDel == 3){
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
				'<button id="card-add-icon" class="btn btn-round" onclick=" modalInput(0)" data-bs-toggle="modal" data-bs-target="#cardAddModal">'+
				'<i class="material-icons">add</i>'+
				'</button>'+
			'</div>'+
		'</div>'+
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
				      		'<img alt="'+ cards_list[i].nickName +'" class="avatar" src="'+ cards_list[i].userImg +'" />'+
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
				       		'<li><a class="dropdown-item" onclick="updateCompCard('+cards_list[i].id+')" href="#">완료</a></li>'+
				       		'<li><hr class="dropdown-divider"></li>'	+
				       		'<li><a class="dropdown-item text-danger" onclick="updateDeleteCard('+cards_list[i].id+')" href="#">삭제</a></li> '+
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
			       			'<h6 data-filter-by="text">'+ compCards_list[i].postTitle +'</h6><br>'+
			   				'<img id="'+compCards_list[i].id+'compPostUpfile" class="box" src="/images/'+compCards_list[i].up_fileName +' "/>'+ 
			       		  	'<span class="text-small">'+'\u00a0\u00a0\u00a0'+compCards_list[i].description+'</span>'+
			       		    '</a>'+
			            '</div>'+
			            '<div class="card-meta">'+
			                '<ul class="avatars">'+
			               		'<li>'+
			               		'<a data-toggle="tooltip" title="'+compCards_list[i].nickName+'">'+
			              		'<img alt="'+ compCards_list[i].nickName +'" class="avatar" src="'+ compCards_list[i].userImg +'" />'+
			              		'</a>'+
			               		'<small>'+'\u00a0\u00a0'+ compCards_list[i].nickName +' <span class="text-small" style="color:green"> '+ compCards_list[i].compareMessage +'</span></small>'+
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
			               		'<li><a class="dropdown-item text-danger" onclick="updateUnCompCard('+compCards_list[i].id+')" href="#">완료 해제</a></li>'+
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
		$('#breadcrumb-item-userName').html('<a style="color:black" href="#" data-bs-toggle="modal" data-bs-target="#userInfo">'+userNickName+'</a>');
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
			console.log('내프로젝트야' + projectInfo[0].tabId);
			$('#page_topHead_userName').hide();
			$('#page_topHead_dropDown').show();
			$('#page_topHead_dropDown').html(
					'<button type="button" class="btn btn-round" data-bs-toggle="dropdown" data-bs-display="static" aria-expanded="false">'+
					'<i class="material-icons">settings</i></button>'+
					' <ul class="dropdown-menu dropdown-menu-right">'+
						'<li id="dropDown-reDeuDay"><a class="dropdown-item" href="#" data-bs-toggle="modal" data-bs-target="#project-editDueDay-modal">완료일 변경</a></li>'+
						'<li id="dropDown-complete"><a class="dropdown-item" onclick="updateCompProject()" href="#">프로젝트 완료</a></li>'+
						'<li id="dropDown-unComplete"><a class="dropdown-item" onclick="updateUnCompProject()" href="#">완료 해제</a></li>'+
						'<li><hr class="dropdown-divider"></li>'+
						'<li id="dropDown-delProject"><a class="dropdown-item text-danger" href="#" onclick="projectDelete('+projectInfo[0].tabId+')">삭제</a></li>'+
						'<li id="dropDown-moveAllCard"><a class="dropdown-item text-danger" href="#" onclick="projectSelect('+projectInfo[0].tabId+')" data-bs-toggle="modal" data-bs-target="#project-select-modal" >카드이동 후 삭제</a></li>'+
					' </ul>');
			$('#modal-editDueDay-body').html('<input class="form-control col flatpickr-input" type="date" name="tab_dueDay" id="date" min="'+projectInfo[0].minDay+'">');
			$('#modal-editDueDay-footer').html('<input type="hidden" name="tabId" value="'+projectInfo[0].tabId+'">');
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
		let projectSize = [];
		for(var i=0 ; i<projectInfo.length ; i++){
			let tabIdCheck = 0;
			if (projectInfo[i].tabId != tabIdCheck){
				projectSize.push(projectInfo[i]);
				tabIdCheck = projectInfo[i].tabId;
				console.log('프로젝트 갯수새기 시작');
			}
		}
		for(var i=0 ; i<projectInfo.length ; i++){
			var tabSelectCheck = 0;
			if(projectSize.length > 1 && projectInfo[i].id != null && projectInfo[i].isDel == 0){
				$('#dropDown-moveAllCard').show();
				$('#dropDown-delProject').hide();			
				break;
			}else{			
				$('#dropDown-delProject').show();
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
	
	$('#modal_newCard_minDay').html('<input class="form-control col flatpickr-input" type="date" name="dueDay" id="date" min="'+projectInfo[0].minDay+'" max="'+projectInfo[0].maxDay+'">');
	$('#modal_newCard_alertMessage').html('<span>카드 완료일은 '+projectInfo[0].minDay+'부터 '+projectInfo[0].maxDay+' (프로젝트완료일)안에서 선택가능합니다. </span>');
	if(projectInfo[0].tab_userNum == p2 && projectInfo[0].tabCompCheck == 0){
		$('#header_projectTitle').html('<div class="col-md-auto"><h1 onclick="update_project_title()" id="project_title">'+projectInfo[0].tabTitle+'</h1></div> <div id="project_titleUpdate_input"></div>'+
				'<div class="col-md-auto"><p class="lead" onclick="update_project_intro()" id="projcet_intro">'+projectInfo[0].tab_intro+'</p></div><div id="project_introUpdate_input"></div>');	
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
			'<a style="color:#666666" href="#" data-bs-toggle="modal" data-bs-target="#project-editDueDay-modal"><span>'+projectInfo[0].maxDay+' 완료</span></a>'+
			'</div>'+
			'</div>');	
		
	// 020901 탭이 마감일이 지나면 포스트를 작성할 수 없게 한다.
	if(projectInfo[0].tabProgress > 99.99){
		//console.log("테스트 성공" + projectInfo[0].tabProgress);
		$('#card-add-icon').hide();
	}	
		

	}
	
	// 220210-01 카드 완료 함수로 하기
	function updateCompCard(cardId){
		
		$('.btn-options').html('<div class="spinner-border text-light" role="status"></div>');
		
		let projectNumber = document.getElementById("projectId").value;
		
		 $.ajax({
	         url: 'updateCompCardAction',
	    	 method: "POST",
	 	     data: {'id': cardId , 'tabId' : projectNumber}
	 	     })
	 	     
		location.href="/mvc/board";
		 
	}
	// 220221-01 카드 삭제 함수
	function updateDeleteCard(cardId){
		
		 $.ajax({
	         url: 'updateDeleteCardAction',
	    	 method: "POST",
	 	     data: {'id': cardId}
	 	     })

		 location.href="/mvc/board";
	}
	// 220210-02 카드 완료 해제 함수
	
	function updateUnCompCard(cardId){
		
		$('.btn-options').html('<div class="spinner-border text-light" role="status"></div>');
		
		let projectNumber = document.getElementById("projectId").value;
		
		$.ajax({
	         url: 'updateUnCompCardAction',
	    	 method: "POST",
	 	     data: {'id': cardId , 'tabId' : projectNumber}
	 	     })

	 	    location.href="/mvc/board";
	 	   
		
	}
	
	// 220210-03 프로젝트 완료 해제 함수
	
	function updateUnCompProject(){
		
		$('#page_topHead_dropDown').html('<div class="spinner-border text-light" role="status"></div>');   
		
		let projectNumber = document.getElementById("projectId").value;
		
		$.ajax({
	         url: 'updateUnCompProjectAction',
	    	 method: "POST",
	 	     data: {'tabId': projectNumber}
	 	     })
	 	    location.href="/mvc/board";
	}
	
	// 220210-04 프로젝트 완료 함수
	
	function updateCompProject(){
		
		$('#page_topHead_dropDown').html('<div class="spinner-border text-light" role="status"></div>');   
		
		let projectNumber = document.getElementById("projectId").value;
		
		 $.ajax({
	         url: 'updateCompProjectAction',
	    	 method: "POST",
	 	     data: {'tabId': projectNumber}
	 	     })
	 	     
	 	    location.href="/mvc/board";
		 
	}
	
	// 220214-01 메모이미지 여는 함수
	function inputMemoImg(id){
		
		let projectNumber = document.getElementById("projectId").value;
		
		console.log("테스트 성공 id :" + id + "userId : " + p2 +" tabId :" + projectNumber);
		
		$('#modal_card_footer').html('<form id="frm" name="frm" method="post" action="/mvc/register/insertMemoFile" enctype="multipart/form-data">'+
				'<div class="row">'+
				'<input class="momoFileInput" type="file" name=file>'+
				'<button type="submit" onclick="reload()" class="btn btn-outline-secondary" id="memo_file_upButton"><i class="bi bi-file-plus"></i></button>'+
				'<button type="button" onclick="reload()" class="btn btn-outline-secondary" data-bs-dismiss="modal"><i class="bi bi-file-earmark-x"></i></button>'+
				'<input type="hidden" name="n247_rePoId" value="'+id+'">'+
				'<input type="hidden" name="n247_reUsId" value="'+p2+'">'+
				'<input type="hidden" name="n247_reTabId" value="'+projectNumber+'">'+
				'</div>'+
				'</form>'
				);	
		$('#memo_file_upButton').hide();
		$('.momoFileInput').change(function(){
			$('#memo_file_upButton').show();
		});
	}
	
	
	
	//220222-01 카드이동후 프로젝트 삭제 리스트 함수()
	var moveOn = '';
	var isDelSelect = '';
	function projectSelect(projectId){
		$('#modal_projectSelect_body').html('');
		if(navBarProjectList.length > 1){
			console.log(navBarProjectList.length + "this1");
			for (var i=0 ; i<navBarProjectList.length ; i++){
				if(navBarProjectList[i].tabId != projectId && navBarProjectList[i].userNum == p2 &&  navBarProjectList[i].isDel == 0){
					$('#modal_projectSelect_body').append(
							'<button type="button" onclick="projectSelectAction(this.id)" class="btn btn-secondary" id="'+navBarProjectList[i].tabId+'">'+navBarProjectList[i].tabTitle+'</button>');
				}
			}
		}else{
			console.log("this2");
			$('#modal_projectSelect_body').html('<a>카드를 넣을 프로젝트가 없습니다.</a>');
		}
		
		moveOn = projectId;	
	//카드 모두 이동시 0 , 모두 삭제시 1
	isDelSelect = 0 ;	
	}
	
	//220222-02 프로젝트삭제시 카드가 존재하면 카드이동할 목록을 보여준 중 선택하면 옮겨주는 함수
	function projectSelectAction(projectSelectId){
		//console.log(projectSelectId + '번 탭의 카드 이동시키기 테스트 성공' + moveOn +'삭제번호는?:' + isDelSelect);
		
		 $.ajax({
	         url: 'updateSelectProjectAction',
	    	 method: "POST",
	 	     data: {'tabId': projectSelectId , 'moveOn' : moveOn , 'isDelCheck' : isDelSelect}
	 	     })
	 	     
		//일단 보드로 이동시키는데 목록받아올때 걸러내겠지?
 		location.href="/mvc/board?";
		
	}
	
	//220223-01 프로젝트 삭제시 카드 모두 삭제하고 프로젝트삭제 
	function projectWithCardDeleteAction(){
		isDelSelect = 1 ;
		//console.log(moveOn + '번 탭의 카드 모두삭제 테스트 성공' +'삭제번호는?:' + isDelSelect);
		//카드 모두 삭제라 1
		
		 $.ajax({
	         url: 'updateSelectProjectAction',
	    	 method: "POST",
	 	     data: {'tabId': moveOn , 'moveOn' : moveOn , 'isDelCheck' : isDelSelect}
	 	     })
	 	     
		//있는보드중 골라가는데 없으면 퍼스트로 가라
		if(navBarProjectList.length != 0){
			for(var i=0 ; i<navBarProjectList.length ; i++){
				if(navBarProjectList[i].tabId != moveOn && navBarProjectList[i].isDel == 0){
					//console.log('맞는게 있어서 가요');
					location.href="/mvc/board?";
				}else{
					//console.log('맞는게 없어서 가요1');
					//여기서 없으면 모달 띄워라
					document.getElementById('nav_newButton').click(); 
				}
			}	
		}else{
			//console.log('맞는게 없어서 가요2');
			document.getElementById('nav_newButton').click(); 
		}
			
		 
		
	}
	
	function projectDelete(projectId){
		console.log('내용없는 탭 삭제 성공 ' + projectId);
		 $.ajax({
	         url: 'updateDeleteProjectAction',
	    	 method: "POST",
	 	     data: {'tabId': projectId}
	 	     })
	 	     
	 	 $('#page_topHead_dropDown').html('<div class="spinner-border text-light" role="status"></div>');   
	 	 
	 	 location.href="/mvc/board";
	}
	
	
	//220301파일인풋창이 변했을경우
	function fileUpCheck(){
		modalInput(1);
	}
	
	
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
		//console.log("프로젝트 제목 수정 취소 테스트 성공");
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
	
	
	
	function getProject(id){
		console.log('프로젝트 이동했어요 :' + id+'그전 프로젝트 번호는 :' + p1);
		projectNumber = id;
		$('#projectId').val(id);
		$('#tasks').empty();
		$('#cardsList').empty(); 
		$('#completeCardsList').empty(); 
		document.getElementById('navbar-togglerButton').click();
		getAllList(id,p2);
	
		
		
	};
	
	
	function modalInput(p1){
		var uploadCheck = 0;
		uploadCheck = p1;
		let projectNumber = document.getElementById("projectId").value;
		//console.log('업로드체크 받아온거 :' + uploadCheck);
			$('#thisTabUserIdTabId').html(
					'<input type="hidden" name="userId" value="'+p2+'">'+
					'<input type="text" name="tabId" value="'+projectNumber+'" disable>'+
					'<input type="hidden" name="uploadCheck" value="'+uploadCheck+'">'
					);

	}
	

	// 친구를 모두 받아올게 
	function getAdmFriendsAjax(){
		
		console.log('친구관리누른거맞아?');
		
		
		
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
	       	
	       	let projectNumber = document.getElementById("projectId").value;
	       	
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
		       		//console.count("공유친구 반복" + projectAdmList[i].nickName + "님의 정보를 표시중 " );
					$('#header_admSetting').append(
							'<li>'+
			 				'<a href="#" onclick="projectUnAdmGetUserId('+ projectAdmList[i].idN247_ft +')" data-bs-toggle="modal" data-bs-target="#projectUnAdm" data-placement="top" title="'+projectAdmList[i].nickName+'">'+
			 				'<img alt="Claire Connors" class="avatar" src="'+projectAdmList[i].userImg+'" /></a>'+
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
	       	if(selectProjectAdmFriends.length != 0){
	       		for(var i=0 ; i<selectProjectAdmFriends.length ; i++){
	       			
       				$('#modal_projectAdm_body').append(
          		       		'<li>'+
          		       		'<a data-toggle="tooltip" data-bs-toggle="modal" data-bs-target="#modal_info_'+selectProjectAdmFriends[i].userId+'" title="'+selectProjectAdmFriends[i].nickName+'">'+
          		      		'<img alt="'+ selectProjectAdmFriends[i].nickName +'"class="avatar" src="'+ selectProjectAdmFriends[i].userImg +'" />'+
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
					          						'<img src="'+selectProjectAdmFriends[i].userImg+'" class="img-fluid rounded-start" alt="...">'+
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
	       	}else{
   				$('#modal_projectAdm_body').html(
      		       		'<a>공유할 친구가 없습니다.</a>'
      					);
   			}
	       	
	       			
	       			//console.log("아니왜 하나야 :" + selectProjectAdmFriends[i].nickName);

	       	
	       	// 아주 좋아 ();
	       	var admfriends_tabId = projectNumber;
	       	var friendsListTest = selectProjectAdmFriends;
	       	//console.log("갯수좀 보자1 friendsListTest:" + friendsListTest.length);
	    	//console.log("갯수좀 보자2 selectProjectAdmFriends:" + selectProjectAdmFriends.length);
	       	testtesttest(selectProjectAdmFriends,admfriends_tabId);
	       	
	       //console.log("현재 탭번호 이거임1 : " + projectNumber);
	       	getFriendsListByType(2);
	    })
	}	
	
	//공유를 취소할때 유저의 아이디를 넣어주고 리로드 시킨다.
	function projectUnAdmAction(idN247_ft){
		
		//console.log("취소시키기 성공 : " + idN247_ft);
		$.ajax({
	         url: 'isDelProjectAdm',
	    	 method: "POST",
	 	     data: {'idN247_ft': idN247_ft}
	 	     });
		location.href="/mvc/board";
		
	}
	
	
	//여기에 변수를 만들어 insertProjectAdm 함수안에서 로딩안하고 공유친구사진넣어주길 해보자 
	var admFriends_body = [];
	var admTabId = 0;
	function testtesttest(p1,admfriends_tabId){
		for(var i=0 ; i<p1.length ; i++){
			
			admFriends_body.push(p1[i]);
			//console.log("테스트 성공" + admFriends_body[i].nickName);
			
		}
		admTabId = admfriends_tabId;
		//console.log("현재 탭번호 이거임2 : " + admTabId);
	 }

	//이제 삭제 만들어야돼 리스트태그에 아이디심어놓고 삭제하면 함수실행하고 화면서 리스트 아이디 꺼주는걸로 하자.
	
	function insertProjectAdm(userId){
	//console.log("insertProjectAdm 실행 됐어요  : " + userId + "번 유저를 탭번호 :" + admTabId + "에 공유시켰습니다.");
		for (var i=0 ; i<admFriends_body.length ; i++){
			//console.log("뭘찾고있나" + admFriends_body[i].userId);
			if (admFriends_body[i].userId == userId){
				
				//console.log("불러와서 찾기 성공" + admFriends_body[i].nickName);
				$('#header_admSetting').append(
						'<li>'+
		 				'<a href="#" onclick="projectUnAdmGetUserId('+ admFriends_body[i].idN247_ft +')" data-bs-toggle="modal" data-bs-target="#projectUnAdm" data-placement="top" title="'+admFriends_body[i].nickName+'">'+
		 				'<img alt="Claire Connors" class="avatar" src="'+admFriends_body[i].userImg+'" /></a>'+
		 				'</li>');
				
				break;
			}
		}
		
		//console.log("받아온거 표시할게요 : userId =" + userId + " , tabId =" + admTabId);
		$.ajax({
	         url: 'insertProjectAdm',
	    	 method: "POST",
	 	     data: {'userId': userId , 'tabId' : admTabId}
	 	     });
		
		
	}
	
	//공유를 취소할 모달을 열었을때 버튼의 유형을 정해준다 유저의 아이디를 가져와 준비시킨다. 
	function projectUnAdmGetUserId(idN247_ft){
		
		let ft_userId = 0;
		let ft_friednInfo = [];
		//idN247_ft 를 통해 프로젝트 공유된 친구의 정보를 잦는다.
		for(var i=0 ; i<admFriends_body.length ; i++){
			if(admFriends_body[i].idN247_ft == idN247_ft){
				ft_userId = admFriends_body[i].ft_userId;
			}
		}
		
		//친구의 번호로 친구정보를 넣는다.
		if(allFriendsList.length != 0){
			for (var i=0 ; i<allFriendsList.length ; i++){
				if(allFriendsList[i].userId == ft_userId){
					ft_friednInfo.push(allFriendsList[i]);
					break;
				}
			}	
		}
		
		if(ft_friednInfo.length != 0){
			$('#projectUnAdm_modalBody').html(
					'<div class="row g-0">'+
					'<div class="col-md-4">'+
					'<img src="'+ft_friednInfo[0].userImg+'" class="img-fluid rounded-start" alt="...">'+
					'</div>'+
					'<div class="col-md-8">'+
					'<h5 class="card-title">'+ft_friednInfo[0].nickName+'</h5>'+
					'<p class="card-text">'+ft_friednInfo[0].id+'</p>'+
					'<p class="card-text">'+ft_friednInfo[0].mb_introduce+'</p>'+
					'</div>'+
					'</div>'
					);
		}
		
		
		$('#projectUnAdm_action_button').html(
				'<button id="projectUnAdm_action_button" type="button" onclick="projectUnAdmAction('+ idN247_ft +')" class="btn btn-outline-secondary" data-bs-dismiss="modal">공유해제</button>'+
				'<button type="button" onclick="reload()" class="btn btn-outline-secondary" data-bs-dismiss="modal">아니오</button>');
	}
	
	var cardNumber = "";
	
	$(".nav-link").on("click",function(){
		document.getElementById('navbar-togglerButton').click();
	});
	
	
	
	$("#nav_newButton").on("click",function(){
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
		$("#input_card_reply").attr('disabled', true);
		$('#modal_card_footer_spiner').html('<div class="spinner-border text-light" role="status"></div>');
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
					'<div class="card_des_input"></div>'+
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
				$('#cardDescriptionDiv').html('<br><h6 id="cardDesHTag">'+ oneCardList[0].description +'</h6>');
			}
			
			$("#replyListDiv").empty();
			$("#replyLine").empty();
			$('#replyLine').html('<hr>');
			$('#modal_card_footer').html(
					'<input type="text" class="form-control col" onKeypress="javascript:if(event.keyCode==13) {input_replyAction(this.value)}" placeholder="메모를 입력해주세요" id="input_card_reply" maxlength="45">'+
					'<div id="modal_card_footer_spiner"></div>'+
					'<a style="color:#666666" href="#" onclick="inputMemoImg('+oneCardList[0].id+')"><i class="material-icons" id="modal_button_inputFile">attach_file</i></a>'+
					'<button type="button" onclick="reload()" class="btn btn-outline-secondary" data-bs-dismiss="modal">닫기</button>'
					);	
			//20220315 메모 셋팅 함수로 넘긴다. 
			memoSetting(oneCardList);

			if(oneCardList[0].userNum == p2){
				myCardCheck = 1;
			}
			
			$("#cardModalLabel").on("click",function(){
				
				if(myCardCheck == 1){
					
					$('#cardModalLabel').hide();
					$('.modal_card_title_input').show();
					$('.modal_card_title_input').html(
							'<div class="row">'+
							'<input type="text" class="form-control col" onKeypress="javascript:if(event.keyCode==13) {updateCardTitle(this.value,'+oneCardList[0].id+')}" value="'+ oneCardList[0].postTitle +'" id="update_card_title" maxlength="45"><button type="button" class="close btn btn-round" onclick="updat_cardTitle_cancle()" ><i class="material-icons">close</i></button>'+
							'</div>');
					getOneCardId(oneCardList[0].dueDayString);
				}else{
					
					$('#cardModalLabel').text(oneCardList[0].postTitle);
				}
	
			});

			$("#cardDescriptionDiv").on("click",function(){		
				
				console.log('카드내용 빼오기 테스트 :'+document.getElementById("cardDesHTag").innerHTML);
				const cardDes = document.getElementById("cardDesHTag").innerHTML;
				if(myCardCheck == 1){
					
					$('#cardDescriptionDiv').hide();
					$('.card_des_input').show();
					$('.card_des_input').html(
							'<textarea class="form-control col" rows="3"  onKeypress="javascript:if(event.keyCode==13) {updateCardDescription(this.value,'+oneCardList[0].id+')}">'+cardDes+'</textarea>'+
							'<p>'+
							'<div class="container">'+
								'<div class="row justify-content-md-center">'+
									'<div class="col-md-auto">'+
									    '<button type="button" class="close btn btn-round" onclick="updat_cardDes_cancle()" ><i class="material-icons">close</i></button>'+
							   		    '<a style="color:#666666" data-toggle="tooltip" title="파일첨부" onclick="updateCardFile()" data-bs-toggle="modal" data-bs-target="#modal_card_updateFile">'+
							   			'<i class="material-icons">attach_file</i>'+
							       		'</a><p>'+
						       		'</div>'+
					   			'</div>'+
				   			'</div>'
					       		);
					
				}else{
					
					$('#cardDescriptionDiv').html('<br><h6 id="cardDesHTag">'+ oneCardList[0].description +'</h6>');
					
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
	function updat_cardDes_cancle(){
		console.log('테스트 성공');	
		$('.card_des_input').hide();
		$('#cardDescriptionDiv').show();
		
	}
	
	function updat_cardTitle_cancle(){
		console.log('테스트성공');
		$('.modal_card_title_input').hide();
		$('#cardModalLabel').show();
		
	}
	//리플레이 업데이트 함수
	var replyUpdateId = 0;
	
	
	function replyUpdate(replyId){
		$('.reDescription_input').html('');
		$('.reDescription').show();
		$('#reDesc_'+replyId+'').hide();
		const x = document.getElementById("reDes_"+replyId).value;
		console.log(x);
		replyUpdateId = replyId;
		$('#reDesc_input_'+replyId+'').html(
				'<div class="input-group">'+
				'<button type="button" class="close btn btn-round" onclick="updat_reply_cancle()" id="rereDesc_cancleButton"><i class="material-icons">close</i></button>'+
				'<input type="text" class="form-control col" onKeypress="javascript:if(event.keyCode==13) {replyDesUpdate(this.value)}" placeholder="'+x+'" maxlength="45" id="rereDesc_input">'+
				'<button onclick="deleteReply()" class="btn btn-outline-secondary" type="button" id="rereDesc_delButton"><i data-toggle="tooltip" title="메모삭제" class="bi bi-trash3"></i></button>'+
				
				'</div>'		
				);
	}
	
	function updat_reply_cancle(){
		console.log('테스트 성공');
		$('.reDescription_input').html('');
		$('.reDescription').show();
	}
	function reFileUpdate(replyId){
		$('.reFileMenu').html('');
		const reFileName = document.getElementById("reFileName_"+replyId).value;
		const reOrgFileName = document.getElementById("reFileOrgName_"+replyId).value;
		replyUpdateId = replyId;
		$('#icontest_'+replyId+'').show();
		$('#icontest_'+replyId+'').html(		
				'<a href="/images/'+reFileName+'" download="'+reOrgFileName+'"><button class="btn btn-outline-secondary" type="button"><i class="bi bi-caret-down-square"></i></button></a>'+
				'<button onclick="deleteReply()" class="btn btn-outline-secondary" type="button"><i style="color:#666666" data-toggle="tooltip" title="파일삭제" class="bi bi-trash3"></i></button>'+
				'<button onclick="memo_fileOption_cancle('+replyId+')" class="btn btn-outline-secondary" type="button"><i class="bi bi-x" style="color:#666666"></i></button>'
				);
	}
	function memo_fileOption_cancle(replyId){
		console.log('취소 테스트 성공');
		$('#icontest_'+replyId+'').hide();
	}
	function reFileUpdateMouseOut(replyId){
		
		$('#icontest_'+replyId+'').html('');
	}
	
	function clickeventtest(){
		console.log("클릭성공");
	}
	function replyDesUpdate(updateDes){
		
		//이전에 함수 하나더만들어서 창 얼리고 스피너 넣는다.
		$("#rereDesc_input").val('');
		$('#rereDesc_cancleButton').html('');
		$('#rereDesc_delButton').html('<div class="spinner-border text-light" role="status"></div>');
		$("#rereDesc_input").attr('disabled', true);
		$('#modal_card_footer_spiner').html('<div class="spinner-border text-light" role="status"></div>');
		
		$.ajax({
	         url: 'updateReplyDesAjax',
	    	 method: "POST",
	 	     data: {'n247_reDes': updateDes, 'idN247_re' : replyUpdateId, 'n247_rePoId' : n247_rePoId }
	 	     })
	 	
		.done(function(data) {	
			
			
			
			memoSetting(data);
			
			$("#input_card_reply").attr('disabled', false);
			$('#modal_card_footer_spiner').html('');
			
			
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
		let projectNumber = document.getElementById("projectId").value;
		
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
			$('.modal_card_title_input').html('<div class="spinner-border text-light" role="status"></div>');
		 $.ajax({
	         url: 'updateCardTitleAjax',
	    	 method: "POST",
	 	     data: {'postTitle': postTitle, 'id' : id }
	 	     })
	 	
		.done(function() {	
			$('.modal_card_title_input').html('');
			$('.modal_card_title_input').hide();
			$('#cardModalLabel').show();
			$('#cardModalLabel').text(postTitle);	
			$('#card_title_'+id+'').html('<h6 data-filter-by="text" id="card_title_'+id+'">'+postTitle+'<p><small>'+dueDay+' 까지 완료</small></h6><br>');
		});
	};
	
	function updateCardDescription(description,id){
		//console.log("어디까지 오나3 : " + description);
			
			$('.card_des_input').html('<div class="spinner-border text-light" role="status"></div>');
		 $.ajax({
	         url: 'updateCardDescriptionAjax',
	    	 method: "POST",
	 	     data: {'description': description, 'id' : id }
	 	     })
	 	
		.done(function() {	
			$('.card_des_input').hide();
			$('.card_des_input').html(
					'<textarea class="form-control col" rows="3"  onKeypress="javascript:if(event.keyCode==13) {updateCardDescription(this.value,'+id+')}">'+description+'</textarea>'+
					'<p>'+
					'<div class="container">'+
						'<div class="row justify-content-md-center">'+
							'<div class="col-md-auto">'+
							    '<button type="button" class="close btn btn-round" onclick="updat_cardDes_cancle()" ><i class="material-icons">close</i></button>'+
					   		    '<a style="color:#666666" data-toggle="tooltip" title="파일첨부" onclick="updateCardFile()" data-bs-toggle="modal" data-bs-target="#modal_card_updateFile">'+
					   			'<i class="material-icons">attach_file</i>'+
					       		'</a><p>'+
				       		'</div>'+
			   			'</div>'+
		   			'</div>'
			       		);
			$('#cardDescriptionDiv').show();
			$('#cardDescriptionDiv').html('<br><h6 id="cardDesHTag">'+description+'</h6>');
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
	
	function Legacy_createReplyAction(n247_reDes,p2,n247_rePoId,p1){
		// console.log("댓글창 입력중 :" + n247_reDes +"포스트 번호는? :" + n247_rePoId );
		 $.ajax({
	         url: 'ajax_createReplyAction',
	    	 method: "POST",
	 	     data: {'n247_reDes': n247_reDes, 'n247_reUsId' : p2, 'n247_rePoId' : n247_rePoId, 'tabId' : p1}
	 	     })
	 	
		.done(function() {
			insertReplyPreNumber += 1;
			console.log("임시번호 부여하기 어떻게 되가니" + insertReplyPreNumber +'닉네임은 어떻게 되니?' + userNickName);
			$('#replyInputTest').append(
									'<div class="row">'+
										'<div class="col">'+
									      '<small style="color:black">'+userNickName+'</small>'+
									      '<div id="reply_reinsert_'+insertReplyPreNumber+'"><a><small  onclick="replyReInsert(this.id)" id="'+n247_reDes+'">'+' '+n247_reDes+'</small></a><small style="color:#dcdee0">\u00a0\u00a0 지금</small></div>'+
									      '</div>'+
							    	'</div>'+
							    	'<div class="reDescription_input2"></div>');
			$("#input_card_reply").attr('disabled', false);
			$('#modal_card_footer_spiner').html('');
			
			
		})
	}
	
	function createReplyAction(n247_reDes,p2,n247_rePoId,p1){
		// console.log("댓글창 입력중 :" + n247_reDes +"포스트 번호는? :" + n247_rePoId );
		 $.ajax({
	         url: 'ajax_createReplyAction',
	    	 method: "POST",
	 	     data: {'n247_reDes': n247_reDes, 'n247_reUsId' : p2, 'n247_rePoId' : n247_rePoId, 'tabId' : p1}
	 	     })
	 	
		.done(function(data) {

			memoSetting(data);
			
			$("#input_card_reply").attr('disabled', false);
			$('#modal_card_footer_spiner').html('');
			
			
		})
	}
	
	function memoSetting(list){
		
		let oneCardList = list ;
		
		if(oneCardList != null){
			
			$('#replyListDiv').html('');
			//원래 내용 싹지우고
			var reIdCheck2 = 0;
			
			for(var i=0 ; i<oneCardList.length ; i++){
				
				if(oneCardList[i].N247_reDes != null && oneCardList[i].idN247_re != reIdCheck2 && oneCardList[i].N247_reUsId == oneCardList[i].userId && oneCardList[i].N247_reUsId == p2 && oneCardList[i].N247_reIsDel == 0){
					reIdCheck2 = oneCardList[i].idN247_re;
					if(oneCardList[i].n247_reFile != null){
						if(oneCardList[i].n247_reUpCheck != 1){
							$("#replyListDiv").append(
									'<div class="row">'+
										'<div class="col" id="card_reply_'+oneCardList[i].idN247_re+'">'+
									      '<small style="color:black">'+oneCardList[i].nickName+'</small>'+
									      '<a><small>'+' '+oneCardList[i].N247_reDes+'</small></a>'+
									      '<small style="color:#dcdee0">'+'\u00a0\u00a0'+oneCardList[i].N247_reKrModified+'</small>'+
									      '<br><div class="row"><div class="row"><div class="col-sm-12"><a href="#" onclick="reFileUpdate('+oneCardList[i].idN247_re+')" ><img src="/images/'+oneCardList[i].n247_reFile+'" class="rounded float-start" width="200"></a></div></div>'+
									      '<input type="hidden" id="reFileName_'+oneCardList[i].idN247_re+'" value="'+oneCardList[i].n247_reFile+'">'+
									      '<input type="hidden" id="reFileOrgName_'+oneCardList[i].idN247_re+'" value="'+oneCardList[i].n247_reOrgFile+'">'+
									      '<div class="reFileMenu" id="icontest_'+oneCardList[i].idN247_re+'"></div></div>'+
									      '</div>'+
								    '</div>');	
						}else{
							$("#replyListDiv").append(
									'<div class="row">'+
										'<div class="col" id="card_reply_'+oneCardList[i].idN247_re+'">'+
									      '<small style="color:black">'+oneCardList[i].nickName+'</small>'+
									      '<br><div class="row"><div class="row"><div class="col-sm-12"><a href="#" onclick="reFileUpdate('+oneCardList[i].idN247_re+')"><i class="bi bi-files"></i><small>'+' '+oneCardList[i].N247_reDes+'</small></a><small style="color:#dcdee0">'+'\u00a0\u00a0'+oneCardList[i].N247_reKrModified+'</small></div></div>'+
									      '<input type="hidden" id="reFileName_'+oneCardList[i].idN247_re+'" value="'+oneCardList[i].n247_reFile+'">'+
									      '<input type="hidden" id="reFileOrgName_'+oneCardList[i].idN247_re+'" value="'+oneCardList[i].n247_reOrgFile+'">'+
									      '<div class="reFileMenu" id="icontest_'+oneCardList[i].idN247_re+'"></div></div>'+
									      '</div>'+
								    '</div>');	
						}
						
					}else{
						$("#replyListDiv").append(
								'<div class="row">'+
									'<div class="col" id="card_reply_'+oneCardList[i].idN247_re+'">'+
								      '<small style="color:black">'+oneCardList[i].nickName+'</small>'+
								      '<div class="reDescription" id="reDesc_'+oneCardList[i].idN247_re+'"><a><small  onclick="replyUpdate('+oneCardList[i].idN247_re+')">'+' '+oneCardList[i].N247_reDes+'</small></a>'+
								      '<small style="color:#dcdee0">'+'\u00a0\u00a0'+oneCardList[i].N247_reKrModified+'</small></div>'+
								      '<div class="reDescription_input" id="reDesc_input_'+oneCardList[i].idN247_re+'"></div>'+
								      '<input type="hidden" id="reDes_'+oneCardList[i].idN247_re+'" value="'+oneCardList[i].N247_reDes+'">'+
								      '</div>'+
							    '</div>');	
					}

				}else if(oneCardList[i].N247_reDes != null && oneCardList[i].idN247_re != reIdCheck2 && oneCardList[i].N247_reUsId == oneCardList[i].userId && oneCardList[i].N247_reUsId != p2 && oneCardList[i].N247_reIsDel == 0){
					reIdCheck2 = oneCardList[i].idN247_re;
					if(oneCardList[i].n247_reFile != null){
						if(oneCardList[i].n247_reUpCheck != 1){
							$("#replyListDiv").append(
									'<div class="row">'+
										'<div class="col" id="card_reply_'+oneCardList[i].idN247_re+'">'+
									      '<small style="color:black">'+oneCardList[i].nickName+'</small>'+
									      '<a><small>'+' '+oneCardList[i].N247_reDes+'</small></a>'+
									      '<small style="color:#dcdee0">'+'\u00a0\u00a0'+oneCardList[i].N247_reKrModified+'</small>'+
									      '<br><div class="row"><div class="row"><div class="col-sm-12"><a href="#" onclick="reFileUpdate('+oneCardList[i].idN247_re+')" ><img src="/images/'+oneCardList[i].n247_reFile+'" class="rounded float-start" width="200"></a></div></div>'+
									      '<input type="hidden" id="reFileName_'+oneCardList[i].idN247_re+'" value="'+oneCardList[i].n247_reFile+'">'+
									      '<input type="hidden" id="reFileOrgName_'+oneCardList[i].idN247_re+'" value="'+oneCardList[i].n247_reOrgFile+'">'+
									      '<div class="reFileMenu" id="icontest_'+oneCardList[i].idN247_re+'"></div></div>'+
									      '</div>'+
								    '</div>');	
						}else{
							$("#replyListDiv").append(
									'<div class="row">'+
										'<div class="col" id="card_reply_'+oneCardList[i].idN247_re+'">'+
									      '<small style="color:black">'+oneCardList[i].nickName+'</small>'+
									      '<br><div class="row"><div class="row"><div class="col-sm-12"><a href="#" onclick="reFileUpdate('+oneCardList[i].idN247_re+')"><i class="bi bi-files"></i><small>'+' '+oneCardList[i].N247_reDes+'</small></a><small style="color:#dcdee0">'+'\u00a0\u00a0'+oneCardList[i].N247_reKrModified+'</small></div></div>'+
									      '<input type="hidden" id="reFileName_'+oneCardList[i].idN247_re+'" value="'+oneCardList[i].n247_reFile+'">'+
									      '<input type="hidden" id="reFileOrgName_'+oneCardList[i].idN247_re+'" value="'+oneCardList[i].n247_reOrgFile+'">'+
									      '<div class="reFileMenu" id="icontest_'+oneCardList[i].idN247_re+'"></div></div>'+
									      '</div>'+
								    '</div>');	
						}
					}else{
						$("#replyListDiv").append(
								'<div class="row">'+
									'<div class="col" id="card_reply_'+oneCardList[i].idN247_re+'">'+
								      '<small style="color:black">'+oneCardList[i].nickName+'</small>'+
								      '<div class="reDescription" id="reDesc_'+oneCardList[i].idN247_re+'"><a><small  onclick="replyUpdate('+oneCardList[i].idN247_re+')">'+' '+oneCardList[i].N247_reDes+'</small></a>'+
								      '<small style="color:#dcdee0">'+'\u00a0\u00a0'+oneCardList[i].N247_reKrModified+'</small></div>'+
								      '<div class="reDescription_input" id="reDesc_input_'+oneCardList[i].idN247_re+'"></div>'+
								      '<input type="hidden" id="reDes_'+oneCardList[i].idN247_re+'" value="'+oneCardList[i].N247_reDes+'">'+
								      '</div>'+
							    '</div>');	
					}
				}
			}
		}
	}
	
	function replyReInsert(n247_reDes){
		
		console.log("임시번호 부여하기 어떻게 되가니2" + insertReplyPreNumber);
		let reply_des = document.getElementById('')
		
		$('.reDescription_input2').html();
		$('#reply_reinsert_'+insertReplyPreNumber+'').hide();
		$('.reDescription_input2').html(
				'<div class="reDescription_input">'+
				'<div class="row">'+
				'<button type="button" class="close btn btn-round" onclick="updat_reply_cancle2('+insertReplyPreNumber+')" ><i class="material-icons">close</i></button>'+
				'<input type="text" class="form-control col" onKeypress="javascript:if(event.keyCode==13) {updateReDesToDes(this.value)}" placeholder="'+n247_reDes+'" maxlength="45">'+
				'<button onclick="deleteChangeReply()" class="btn btn-outline-secondary" type="button" id="card_modal_memo_deleteButton"><i data-toggle="tooltip" title="메모삭제" class="bi bi-trash3"></i></button>'+
				'</div>'+
				'</div>'
				);
		
		replyReInsert_delReplyDes(n247_reDes);
	}
	
	function updat_reply_cancle2(id){
		console.log('테스트 성공');
		$('.reDescription_input2').html('');
		$('#reply_reinsert_'+id+'').show();
	}
	
	var delReply = '';
	
	function replyReInsert_delReplyDes(delReDes){
		delReply=delReDes;
		//console.log("찾을 내용 받음 :" + delReply);
		//여기서 내용찾아서 원래쓴거 지워

	}
	
	function updateReDesToDes(insertDes){
		 //console.log("찾아서 바꿀 내용 받음 :" + insertDes);
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
		modalClose();
	}
	
	function searchCard(search){

		$.ajax({
	         url: 'searchCardAjax',
	    	 method: "POST",
	 	     data: {'search' : search}
	 	     })
	 	
		.done(function(data) {	
			
			if(data != null){
				console.log('결과는요? ' + data.length);
				$('#breadcrumb-item-projectName').text('"'+search+'" 의 검색 결과');
				$('#project_title').html('');
				$('#projcet_intro').html('');
				$('#header_projectProgress').html('');
				$('#page-projectHeader').html('');
				$('#tasks').html('<div class="card"></div>');
				for(var i=0 ; i < data.length  ; i++ ) {
					$('.card').append(
							'<div class="card-body">'+
							'<ol class="breadcrumb">'+
							'<li class="breadcrumb-item" ><a style="color:black" onclick="getProject(this.id)" id="'+data[i].tabId+'" href="#">'+data[i].tabTitle+'</a></li>'+
							'<li class="breadcrumb-item">'+data[i].postTitle+'</li>'+
							'</ol>'+
							'<div><p class="card-text">'+data[i].description+'<small>'+data[i].krCreate+'</small></p></div>'+
							'</div>'+
							'</div>');
				}
			}

		});
	}
	
	function getUserInfo(){
		
		$.ajax({
	         url: 'getIntroduceAjax',
	    	 method: "POST",
	 	     data: {'userId' : p2}
	 	     })
	 	
		.done(function(data) {	

			if( data != null){
				
				let introduce = data[0].mb_introduce ;
				
				if(introduce != ''){
					$('#modal_userInfo_intro').html('<a style="color:#666666" href="#" onclick="updateIntroInputAction()" >'+introduce+'</a>');
				}else{
					$('#modal_userInfo_intro').html('<a style="color:#666666" href="#" onclick="updateIntroInputAction()" >소개글을 입력해주세요.</a>');
				}
			}
			
		});

	}
	
	function updateIntroInputAction(){

		$('#modal_userInfo_intro').html('<div class="input-group mb-3">'+
				'<input type="text" class="form-control" placeholder="" aria-label="" aria-describedby="button-addon2" onKeypress="javascript:if(event.keyCode==13) {updateIntroduce()}" id="modal_userinfo_introInputAction">'+
				'<button class="btn btn-outline-secondary" type="button" id="button-addon2" onclick="updateIntroduce()"><i class="bi bi-check-lg"></i></button>'+
				'<button class="btn btn-outline-secondary" type="button" id="button-addon2" onclick="getUserInfo()" ><i class="bi bi-x-lg"></i></button>'+
				'</div>');
	}
	
	
	function updateIntroduce(){

		let introduce = document.getElementById("modal_userinfo_introInputAction").value;
		
		console.log('테스트 성공' + introduce);
		
		$.ajax({
	         url: 'updateMemberIntroduceAction',
	    	 method: "POST",
	 	     data: {'mb_introduce' : introduce}
	 	     })
	 	
		.done(function() {	
			
			$('#modal_userInfo_intro').html('<a style="color:#666666" href="#" onclick="updateIntroInputAction()" >'+introduce+'</a>');
			
		});
		
	}
	
	function search_friendInput(){

		const target = document.getElementById('modal_friendSearch_inputButton');
		target.disabled = false;
		
	}
	
	function search_friendAction(){

		let search = document.getElementById('madal_searchFriend_input').value;
		console.log('테스트 성공:'+search);
		//여기까지 성공
		$.ajax({
	         url: 'searchFriendsAjax',
	    	 method: "POST",
	 	     data: {'search' : search}
	 	     })
	 	
		.done(function(data) {	
			
			$('#modal_friendSearchResult').html(
					'<ul class="avatars text-center">'+
					'<li id="result_searchFriendsList"></li>'+
					'</ul>'
					);
			
			if(data != null){
				for(var i=0 ; i<data.length ; i++){
					console.log('받아오는 친구들의 아이디 :' + data[i].userId);
					$('#result_searchFriendsList').append('<a href="#" onclick="search_modal_bodyAction(this.id)" data-bs-toggle="modal" data-bs-target="#search_friendsInfo_modal" id="'+data[i].userId+'"><img alt="'+data[i].nickName+'" src="'+data[i].userImg+'" class="avatar"></a>'+
							'<input type="hidden" id="result_firendsInputNick'+data[i].userId+'" value="'+data[i].nickName+'">'+
							'<input type="hidden" id="result_firendsInputImg'+data[i].userId+'" value="'+data[i].userImg+'">'+
							'<input type="hidden" id="result_firendsInputIntro'+data[i].userId+'" value="'+data[i].mb_introduce+'">');
				}
			}else{
				$('#result_searchFriendsList').append('<a>검색결과가 없습니다.</a>');
			}
			
			
		});
		
	}
	
	function search_modal_bodyAction(p1){

		//빼오자
		console.log('유저아이디 받아오나요 :'+p1);
		
		let nickName = document.getElementById('result_firendsInputNick'+p1).value;
		let userImg = document.getElementById('result_firendsInputImg'+p1).value;
		let mb_introduce = document.getElementById('result_firendsInputIntro'+p1).value;
		
		$('#modal_searchFriendsInfo_body').html(
				'<div class="row g-0">'+
					'<div class="col-md-4" id="moal_userInformation_userImg">'+
						'<img src="'+userImg+'" class="img-fluid rounded-start" alt="...">'+
					'</div>'+
					'<div class="col-md-8">'+
  					'<h5 class="card-title">'+nickName+'</h5>'+
  					'<p class="card-text">'+mb_introduce+'</p>'+
					'</div>'+
				'</div>');
		$('#modal_searchFriendsInfo_footer').html('<button type="button" onclick="friendsAdmAction('+p1+')" class="btn btn-outline-secondary" data-bs-dismiss="modal">친구신청</button>'+
				'<button type="button" onclick="reload()" class="btn btn-outline-secondary" data-bs-dismiss="modal">닫기</button>');
	}
	
	function friendsAdmAction(userId){
		console.log('테스트 성공이야 :' + userId);
		$('#madal_searchFriend_input').val('');
		$('#modal_friendSearchResult').empty();
		$('#modal_searchFriendsInfo_footer').html('<div class="spinner-border text-light" role="status"></div>');
		getFriendsListByType(2);
		$.ajax({
	         url: 'friendSubscriptionAjax',
	    	 method: "POST",
	 	     data: {'fUserId' : userId}
	 	     })
	 	
		.done(function() {
			window.location.reload();
		})
		
	}
	
	function subFriends_modal_bodayAction(p1){
		
		let nickName = document.getElementById('memberType4_firendsInputNick'+p1).value;
		let userImg = document.getElementById('memberType4_firendsInputImg'+p1).value;
		let mb_introduce = document.getElementById('memberType4_firendsInputIntro'+p1).value;
		
		console.log("테스트 성공:" + p1 + nickName + userImg + mb_introduce);
		
		$('#modal_searchFriendsInfo_body').html(
				'<div class="row g-0">'+
					'<div class="col-md-4" id="moal_userInformation_userImg">'+
						'<img src="'+userImg+'" class="img-fluid rounded-start" alt="...">'+
					'</div>'+
					'<div class="col-md-8">'+
  					'<h5 class="card-title">'+nickName+'</h5>'+
  					'<p class="card-text">'+mb_introduce+'</p>'+
					'</div>'+
				'</div>');
		
		$('#modal_searchFriendsInfo_footer').html('<button type="button" onclick="subFriendsAction('+p1+')" class="btn btn-outline-secondary" data-bs-dismiss="modal">친구수락</button>'+
				'<button type="button" onclick="reload()" class="btn btn-outline-secondary" data-bs-dismiss="modal">닫기</button>');
		
		
	}
	
	//내가 신청한 친구 정보 모달 
	function iwaitFriends_modal_bodayAction(p1){
		
		let nickName = document.getElementById('memberType1_firendsInputNick'+p1).value;
		let userImg = document.getElementById('memberType1_firendsInputImg'+p1).value;
		let mb_introduce = document.getElementById('memberType1_firendsInputIntro'+p1).value;
		
		console.log("테스트 성공:" + p1 + nickName + userImg + mb_introduce);
		
		$('#modal_searchFriendsInfo_body').html(
				'<div class="row g-0">'+
					'<div class="col-md-4" id="moal_userInformation_userImg">'+
						'<img src="'+userImg+'" class="img-fluid rounded-start" alt="...">'+
					'</div>'+
					'<div class="col-md-8">'+
  					'<h5 class="card-title">'+nickName+'</h5>'+
  					'<p class="card-text">'+mb_introduce+'</p>'+
					'</div>'+
				'</div>');
		
		$('#modal_searchFriendsInfo_footer').html('<button type="button" onclick="updateDelFriAction('+p1+')" class="btn btn-outline-secondary" data-bs-dismiss="modal">신청취소</button>'+
				'<button type="button" onclick="reload()" class="btn btn-outline-secondary" data-bs-dismiss="modal">닫기</button>');
		
		
	}
	
	//내친구 목록 정보 모달 
	function myFriends_modal_bodyAction(p1){
		
		let nickName = document.getElementById('memberType23_firendsInputNick'+p1).value;
		let userImg = document.getElementById('memberType23_firendsInputImg'+p1).value;
		let mb_introduce = document.getElementById('memberType23_firendsInputIntro'+p1).value;
		
		console.log("테스트 성공:" + p1 + nickName + userImg + mb_introduce);
		
		$('#modal_searchFriendsInfo_body').html(
				'<div class="row g-0">'+
					'<div class="col-md-4" id="moal_userInformation_userImg">'+
						'<img src="'+userImg+'" class="img-fluid rounded-start" alt="...">'+
					'</div>'+
					'<div class="col-md-8">'+
  					'<h5 class="card-title">'+nickName+'</h5>'+
  					'<p class="card-text">'+mb_introduce+'</p>'+
					'</div>'+
				'</div>');
		
		$('#modal_searchFriendsInfo_footer').html('<button type="button" onclick="updateDelFriAction('+p1+')" class="btn btn-outline-secondary" data-bs-dismiss="modal">친구취소</button>'+
				'<button type="button" onclick="reload()" class="btn btn-outline-secondary" data-bs-dismiss="modal">닫기</button>');
		
		
	}
	
	//친구수락하기
	function subFriendsAction(p1){
		console.log('테스트성공' + p1);
		//friendsAdmAjax
		$('#modal_members_type3').html('<div class="spinner-border text-light" role="status"></div>');
		$('#modal_searchFriendsInfo_footer').html('<div class="spinner-border text-light" role="status"></div>');
		$.ajax({
	         url: 'friendsAdmAjax',
	    	 method: "POST",
	 	     data: {'idN247_f' : p1}
	 	     })
	 	
		.done(function() {
			$('#modal_members_type3').empty();
			getFriendsList();
		})
	}
	
	//친구신청취소하기
	function updateDelFriAction(p1){
		console.log('테스트성공' + p1);
		//friendsAdmAjax
		$('#modal_members_type3').html('<div class="spinner-border text-light" role="status"></div>');
		$('#modal_searchFriendsInfo_footer').html('<div class="spinner-border text-light" role="status"></div>');
		$.ajax({
	         url: 'updateDelFriAjax',
	    	 method: "POST",
	 	     data: {'idN247_f' : p1}
	 	     })
	 	
		.done(function() {
			$('#modal_members_type1').empty();
			getFriendsList();
		})
	}
	
	//카카오로그아웃  
	Kakao.init('8fb8c0ff6b3b686826534e5606e18674'); //발급받은 키 중 javascript키를 사용해준다.
	
	function kakaoLogout() {
	    if (Kakao.Auth.getAccessToken()) {
	      Kakao.API.request({
	        url: '/v1/user/unlink',
	        success: function (response) {
	        	console.log(response)
	        	location.href="/mvc/logOutAction";
	        },
	        fail: function (error) {
	          console.log(error)
	        },
	      })
	      Kakao.Auth.setAccessToken(undefined)
	    }
	    
	  }  
	
	function logout(){
		
		location.href="/mvc/logOutAction";
		
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