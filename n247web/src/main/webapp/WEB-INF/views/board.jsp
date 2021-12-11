<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<html>
  <head>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">

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



    <!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
	<link href="resources/list-groups.css" rel="stylesheet">
	<link href="resources/sidebars.css" rel="stylesheet">
	<script src="https://code.jquery.com/jquery-1.12.4.min.js"></script>

	
    <title>N247</title>
  </head>
  <body>
  	<a href="#">other</a>
	 	 
		 	 
		 	 
		 	 

<!--  해당 탭에 공유된 친구들 목록 -->

 		
 	 <ul class="nav">
 	  <c:forEach items="${friendList }" var="fri" begin="0" >
        <li class="nav-item">
         <img src="/images/${fri.f_imgName }" alt="twbs" width="32" height="32" class="rounded-circle flex-shrink-0" data-bs-toggle="modal" data-bs-target="#userInfo${fri.idN247_ft }">
		 </li>
		 <div class="modal fade" id="userInfo${fri.idN247_ft }" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">		    
		  <div class="modal-dialog">	      
		    <div class="modal-content">
		      <div class="modal-header">
		        <h5 class="modal-title" id="exampleModalLabel">${fri.f_name }</h5>
		        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
		      </div>
		      <div class="modal-body">
		          <div class="mb-3">
		            <input class="form-control" type="text" value="${fri.email }" aria-label="readonly input example" readonly>
		          </div>
		      </div>
		      <form action="updateDelFriToTabAction" id="myForm" class="form-inline" method="get" accept-charset="UTF-8">
			      <div class="modal-footer">
			        <button type="submit" class="btn btn-secondary">공유취소</button>
			        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">닫기</button>
			        <input type="hidden" name="idN247_ft" value="${fri.idN247_ft }">
		            <input type="hidden" name="tabId" value="${selectedTabId }">
			      </div>
		      </form>
		    </div>
		  </div>
		</div>
		 </c:forEach>
		 <li class="nav-item">
		    <a href="#" data-bs-toggle="modal" data-bs-target="#ProjectAdm">
		    <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-align-middle" viewBox="0 0 16 16" >
  				<path d="M6 13a1 1 0 0 0 1 1h2a1 1 0 0 0 1-1V3a1 1 0 0 0-1-1H7a1 1 0 0 0-1 1v10zM1 8a.5.5 0 0 0 .5.5H6v-1H1.5A.5.5 0 0 0 1 8zm14 0a.5.5 0 0 1-.5.5H10v-1h4.5a.5.5 0 0 1 .5.5z"/>
			</svg>
			</a>
<!-- 		 	<a class="nav-link active" aria-current="page" href="#" data-bs-toggle="modal" data-bs-target="#ProjectAdm">+</a> -->
		 </li>
		</ul>
	
	 
	 
	 

<div class="row">
<!-- 		 		사이드바부분   -->
	<div class="col-3">
			
		<h1 class="visually-hidden">Sidebars examples</h1>
		
		  <div class="d-flex flex-column flex-shrink-0 p-3 text-white bg-dark" style="width: 200px;">
		   
		    <a href="/" class="d-flex align-items-center mb-3 mb-md-0 me-md-auto text-white text-decoration-none">
		    <img src="/images/${userSet.imgName }" alt="" width="32" height="32" class="rounded-circle me-2">
		      <span class="fs-4">${selectedTab }</span>
		    </a>
		    <hr>
		     <button type="button" class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#exampleModal" >+</button>	
		    <hr>
		    
		    <ul class="nav nav-pills flex-column mb-auto">
		      <c:forEach items="${tabList }" var="tab" begin="0" >	
			      <li class="nav-item">
			        <a href="/mvc/board?tabId=${tab.tabId }" class="nav-link" aria-current="page">
			          ${tab.tabTitle }
			        </a>
			      </li>
			  </c:forEach>
		    </ul>
		    
		    <hr>
		     <ul class="nav nav-pills flex-column mb-auto">
		      <c:forEach items="${friTabList }" var="friTab" begin="0" >	
			      <li class="nav-item">
			        <a href="/mvc/board2?tabId=${friTab.tabId }&&userNum=${userSet.userNum }" class="nav-link" aria-current="page">
			           <img src="/images/${friTab.imgName }" alt="" width="32" height="32" class="rounded-circle me-2">
			          ${friTab.tabTitle }
			        </a>
			      </li>
			  </c:forEach>
		    </ul>
		    <hr>
		    
		    <div class="dropdown">
		      <a href="#" class="d-flex align-items-center text-white text-decoration-none dropdown-toggle" id="dropdownUser1" data-bs-toggle="dropdown" aria-expanded="false">
		        <img src="/images/${userSet.imgName }" alt="" width="32" height="32" class="rounded-circle me-2">
		        <strong>${nick }</strong>
		      </a>
		      <ul class="dropdown-menu dropdown-menu-dark text-small shadow" aria-labelledby="dropdownUser1">
		        <li><a class="dropdown-item" href="/mvc/updateTab?tabId=${selectedTabId }">New project...</a></li>
		        <li><a class="dropdown-item" href="/mvc/friendBook?userNum=${userSet.userNum }&&tabId=${selectedTabId}">Friend book</a></li>
		        <li><a class="dropdown-item" href="#">cloud</a></li>
		        <li><a class="dropdown-item" href="#" data-bs-toggle="modal" data-bs-target="#userInfo" >user information</a></li>
		        <li><hr class="dropdown-divider"></li>
		        <li><a class="dropdown-item" href="/mvc/">Log out</a></li>
		      </ul>
		    </div>

		  </div>
		  
	</div>
	<div class="col-9">
<!-- 		 		타임라인 부분  -->
				<div class="list-group">
				 <c:forEach items="${postList }" var="post" begin="0" >	
				    <a data-bs-toggle="modal" data-bs-target="#modal-${post.id }"  class="list-group-item list-group-item-action d-flex gap-3 py-3" aria-current="true">
	  				  <img src="/images/${post.imgName }" alt="twbs" width="32" height="32" class="rounded-circle flex-shrink-0">
		  			   <div class="d-flex gap-2 w-100 justify-content-between">
		   				 <div>
		   				   <h6 class="mb-0">${post.postTitle }</h6>
		 				   <p class="mb-0 opacity-75">${post.description} </p>
					     </div>
					    <small class="opacity-50 text-nowrap">${post.time}</small> 
		  			   </div>
				    </a>
				      <form action="deletePostAction" method="get">
						<input type="hidden" name="id" value="${post.id }">
						<input type="hidden" name="tabId" value="${post.tabId }">
						<button type="submit" class="btn btn-primary">-</button>
					  </form>
<!-- 					  모달테스트 2 -->
								  <script>
								// Get the modal
								
								var test = ${selectedTabId};
								var modal = document.getElementById('${post.id}');
								
								console.log(test);
								// When the user clicks anywhere outside of the modal, close it
								window.onclick = function(event) {
								  if (event.target == modal) {
			 					   modal.style.display = "none";
			 					 }
								}
								</script> 
<!-- 모달테스트 -->
		 
		 <div class="modal fade" id="modal-${post.id }" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
		  <div class="modal-dialog">   
		    <div class="modal-content">
		      <div class="modal-header">
		        <h5 class="modal-title" id="exampleModalLabel">메모</h5>
		        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
		      </div>
		      <div class="modal-body">
		         <div class="card">
		         <c:forEach items="${post.fileNameList}" var="fileName" begin="0" > 
		        	 <div class="mb-3"> 
					<img src="/images/${fileName.up_fileName }" class="card-img-top" alt="...">
					</div>
				</c:forEach>
				  <div class="card-body">
				    <h5 class="card-title">${post.postTitle }</h5>
				    <p class="card-text">${post.description }</p>
				 </div>
				</div>
				<c:forEach items="${post.replyList }" var="re" begin="0" > 
		          <div class="mb-3"> 
					    <div class="row">
						    <div class="col">
						      <small>${re.nick }</small>
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
	     	 <form action="createReplyAction" id="myForm" class="form-inline" method="post" accept-charset="UTF-8">
			  <div class="input-group mb-3">
			   <input type="text" class="form-control" name="n247_reDes" placeholder="댓글을 입력해주세요" aria-label="Recipient's username" aria-describedby="button-addon2">
			   <button type="submit" class="btn btn-outline-secondary" type="button" id="button-addon2">댓글</button>
			   <input type="hidden" name="n247_reUsId" value="${userSet.userNum }">
			   <input type="hidden" name="n247_rePoId" value="${post.id }">
			   <input type="hidden" name="tabId" value="${selectedTabId }">
			  </div>
			 </form>
         </div>
		      <div class="modal-footer">
		        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">닫기</button>
		        <button type="button" class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#modal-${post.id }update">수정</button>
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
						        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
						      </div>
						      <div class="modal-body">
						          <div class="input-group mb-3">
								   <input type="text" class="form-control" name="n247_reDes" placeholder="${re.n247_reDes }" aria-label="Recipient's username" aria-describedby="button-addon2">
								   <button type="submit" class="btn btn-outline-secondary" type="button" id="button-addon2">댓글</button>
								   <input type="hidden" name="idN247_re" value="${re.idN247_re }">
								   <input type="hidden" name="tabId" value="${selectedTabId }">
								  </div>
						      </div>
						    </div>
						  </div>
						</form>
					</div>
				</c:forEach>

					
					<div class="modal fade" id="modal-${post.id }update" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
					  <form action="updatePostAction" id="myForm" class="form-inline" method="post" accept-charset="UTF-8">
					  <div class="modal-dialog">     
					    <div class="modal-content">
					      <div class="modal-header">
					        <h5 class="modal-title" id="exampleModalLabel">메모 수정</h5>
					        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
					      </div>
					      <div class="modal-body">
					          <div class="mb-3">
					           	<input class="form-control" type="text" name="postTitle" placeholder="${post.postTitle }" aria-label="postTitle">
					          </div>
					          <div class="mb-3">      
					            <textarea name="description" class="form-control" id="message-text">${post.description }</textarea>
					          </div>
					      </div>
					      <div class="modal-footer">
					        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">닫기</button>
					        <button type="submit" class="btn btn-primary">입력</button>
					        <input type="hidden" name="id" value="${post.id }">
					 	 	<input type="hidden" name="tabId" value="${post.tabId }">
					      </div>
					    </div>
					  </div>
					 </form>
					</div>	
					
				 
			</c:forEach>
   		</div>	
	</div>
</div>		 		
<!-- 		   여기까지  -->  







<div class="modal fade" id="exampleModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
<!--   <form action="/mvc/register/createPostAction" id="myForm" class="form-inline" method="post" accept-charset="UTF-8"> -->
<form id="frm" name="frm" method="post" action="/mvc/register/createPostAction" enctype="multipart/form-data">
    
  <div class="modal-dialog">
      
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title" id="exampleModalLabel">새로운 메모추가</h5>
        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
      </div>
      <div class="modal-body">
          <div class="mb-3">
           	<input class="form-control" type="text" name="postTitle" placeholder="제목을 입력하세요" aria-label="postTitle">
          </div>
          <div class="mb-3">      
            <textarea name="description" class="form-control" id="message-text"></textarea>
          </div>
          <div class="mb-3">  
    		<input type="file" name="file1" />
    	  </div>
      </div>
      <div class="modal-footer">
      	<input type="reset">
        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">닫기</button>
        <button type="submit" class="btn btn-primary">입력</button>
 	 	<input type="hidden" name="tabId" value="${userSet.tabId }">
 	 	<input type="hidden" name="userNum" value="${userSet.userNum }">
      </div>
    </div>
  </div>
 </form>
</div>


<!--    게시판 긁어 옴 날짜와 제목만 보여줌 클릭하면 모달로 전체 타임라인 창을 보여줌 -->

<div class="modal fade" id="userInfo" tabindex="-1" aria-labelledby="userInfoModal" aria-hidden="true">  
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title" id="userInfoModal">회원정보</h5>
        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
      </div>
      <div class="modal-body">
			<div class="card mb-3" style="max-width: 540px;">
			  <div class="row g-0">
				    <div class="col-md-4">
				      <img src="/images/${userSet.imgName }" class="img-fluid rounded-start" alt="..." data-bs-toggle="modal" data-bs-target="#userImgUpdate">
				    </div>
				    <div class="col-md-8">
					      <div class="card-body">
					        <h5 class="card-title">${nick }</h5>
					        <p class="card-text">${email }</p>
					        <p class="card-text">${introduce }</p>
					        <p class="card-text"><small class="text-muted">Last updated 3 mins ago</small></p>
					      </div>
				    </div>
			  </div>
			</div>
		</div>
	    <div class="modal-footer">
	        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">닫기</button>
	        <button type="button" class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#userInfoUpdate" >수정</button>
	    </div>
    </div>
    </div>
</div>




<div class="modal fade" id="userInfoUpdate" tabindex="-1" aria-labelledby="userInfoModal" aria-hidden="true">  
  <form action="userInfoUpdateAction" id="myForm" class="form-inline" method="get" accept-charset="UTF-8">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title" id="userInfoModal">회원정보</h5>
        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
      </div>
      <div class="modal-body">
     	    <div class="input-group mb-3">
  			  <span class="input-group-text" id="basic-addon1">회원명</span>
 			  <input type="text" class="form-control" name="nickName" placeholder="${nick }" aria-label="nickName" aria-describedby="basic-addon1">
			</div>
			<div class="input-group">
			  <span class="input-group-text">소개글</span>
			  <textarea class="form-control" name="mb_introduce" aria-label="With textarea"></textarea>
			</div>
		</div>
	    <div class="modal-footer">
	        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">닫기</button>
	        <button type="submit" class="btn btn-primary">수정</button>
	        <input type="hidden" name="userId" value="${userSet.userNum }">
	        <input type="hidden" name="tabId" value="${selectedTabId }">
	    </div>
    </div>
    </div>
    </form>
</div>

<div class="modal fade" id="userImgUpdate" tabindex="-1" aria-labelledby="userInfoModal" aria-hidden="true">  
  <form id="frm" name="frm" method="post" action="/mvc/register/userImgUpdateAction" enctype="multipart/form-data"> 
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title" id="userInfoModal">사진수정</h5>
        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
      </div>
      <div class="modal-body">
			<input type="file" name="file1" />
		</div>
	    <div class="modal-footer">
	        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">닫기</button>
	        <button type="submit" class="btn btn-primary">수정</button>
	       	<input type="hidden" name="up_userId" value="${userSet.userNum }">
			<input type="hidden" name="up_postId" value="0">
			<input type="hidden" name="tabId" value="${selectedTabId }">
	    </div>
    </div>
    </div>
    </form>
</div>




<div class="modal fade" id="ProjectAdm" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
  <form action="createFriTabAdd" id="myForm" class="form-inline" method="get" accept-charset="UTF-8">
    
  <div class="modal-dialog">
      
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title" id="exampleModalLabel">공유할 친구를 선택하세요</h5>
        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
      </div>
      <div class="modal-body">
      
	      <select class="form-select" name="ft_userId" multiple aria-label="multiple select example">
	            <c:forEach items="${allFriList }" var="fri1" begin="0" >
	               <option value=${fri1.fUserId } <c:out value="${fri1.check == 0 ? '' : 'disabled' }"/>> ${fri1.f_name }</option>
	           
	            </c:forEach>
	            
	            <c:forEach items="${allFriList2 }" var="fri2" begin="0" >
	              <option value=${fri2.myId } <c:out value="${fri2.check == 0 ? '' : 'disabled' }"/>>${fri2.f_name }</option>
	            </c:forEach>
		  </select>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">닫기</button>
        <button type="submit" class="btn btn-primary">추가</button>
 	 	<input type="hidden" name="ft_tabId" value="${selectedTabId }">
      </div>
    </div>
  </div>
 </form>
</div>
    <!-- Optional JavaScript; choose one of the two! -->

    <!-- Option 1: Bootstrap Bundle with Popper -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>

    <!-- Option 2: Separate Popper and Bootstrap JS -->
    <!--
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.10.2/dist/umd/popper.min.js" integrity="sha384-7+zCNj/IqJ95wo16oMtfsKbZ9ccEh31eOz1HGyDuCQ6wgnyJNSYdrPa03rtR1zdB" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.min.js" integrity="sha384-QJHtvGhmr9XOIpI6YVutG+2QOK9T+ZnN4kzFN1RtK3zEFEIsxhlmWl5/YESvpZ13" crossorigin="anonymous"></script>
    -->
	<script>
// 	var exampleModal = document.getElementById('exampleModal')
// 	exampleModal.addEventListener('show.bs.modal', function (event) {
// 	  // Button that triggered the modal
// 	  var button = event.relatedTarget
// 	  // Extract info from data-bs-* attributes
// 	  var recipient = button.getAttribute('data-bs-whatever')
// 	  // If necessary, you could initiate an AJAX request here
// 	  // and then do the updating in a callback.
// 	  //
// 	  // Update the modal's content.
// 	  var modalTitle = exampleModal.querySelector('.modal-title')
// 	  var modalBodyInput = exampleModal.querySelector('.modal-body input')

// 	  modalTitle.textContent = 'New message to ' + recipient
// 	  modalBodyInput.value = recipient
//  	})
	
	</script>
    
  </body>
  
  
  
</html>