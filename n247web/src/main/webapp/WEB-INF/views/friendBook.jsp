<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<html>
  <head>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <link rel="canonical" href="https://getbootstrap.com/docs/5.0/examples/cover/">
    <!-- CSS only -->
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-giJF6kkoqNQ00vy+HMDP7azOuL0xtbfIcaT9wjKHr8RbDVddVHyTfAAsrekwKmP1" crossorigin="anonymous">

    <!-- Bootstrap core CSS -->
<link href="/docs/5.0/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-giJF6kkoqNQ00vy+HMDP7azOuL0xtbfIcaT9wjKHr8RbDVddVHyTfAAsrekwKmP1" crossorigin="anonymous">

    <title>N247</title>
  </head>
  <body>
    <h1>friendBook</h1>

    <button type="button" class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#addFriModal" >+</button>	
	<hr>
	<a>친구목록 </a>
 	 <c:forEach items="${friendList1 }" var="fri1" begin="0" >	
        <li class="nav-item">
        	<button type="submit" class="btn btn-secondary" data-bs-toggle="modal" data-bs-target="#userInfo${fri1.idN247_f }">${fri1.f_name }</button>
		    <form action="updateDelFriAction" id="myForm" class="form-inline" method="get" accept-charset="UTF-8">
		    <button type="submit" class="btn btn-secondary">삭제</button>
		    <input type="hidden" name="idN247_f" value="${fri1.idN247_f }">
		    <input type="hidden" name="tabId" value="${selectTab }">
 	    	<input type="hidden" name="userNum" value="${userNum }">
 	    	<input type="hidden" name="ft_userId" value="${fri1.fUserId }">
		    </form>
		 </li>
		 
		 <div class="modal fade" id="userInfo${fri1.idN247_f }" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">		    
		  <div class="modal-dialog">	      
		    <div class="modal-content">
		      <div class="modal-header">
		        <h5 class="modal-title" id="exampleModalLabel">${fri1.f_name }</h5>
		        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
		      </div>
		      <div class="modal-body">
		          <div class="mb-3">
		            <input class="form-control" type="text" value="${fri1.email }" aria-label="readonly input example" readonly>
		          </div>
		      </div>
		      <div class="modal-footer">
		        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">닫기</button>
		      </div>
		    </div>
		  </div>

		</div>
	 </c:forEach>
	 <c:forEach items="${friendAdmList }" var="friA" begin="0" >	
        <li class="nav-item">
        	<button type="submit" class="btn btn-secondary" data-bs-toggle="modal" data-bs-target="#userInfo${friA.myId }">${friA.f_name }</button>
		    <form action="updateDelFriAction" id="myForm" class="form-inline" method="get" accept-charset="UTF-8">
		    <button type="submit" class="btn btn-secondary">삭제</button>
		    <input type="hidden" name="idN247_f" value="${friA.idN247_f }">
		    <input type="hidden" name="tabId" value="${selectTab }">
 	    	<input type="hidden" name="userNum" value="${userNum }">
 	    	<input type="hidden" name="ft_userId" value="${friA.myId }">
		    </form>
		 </li>
		 
		 <div class="modal fade" id="userInfo${friA.myId }" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
		  <div class="modal-dialog">
		    <div class="modal-content">
		      <div class="modal-header">
		        <h5 class="modal-title" id="exampleModalLabel">${friA.f_name}</h5>
		        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
		      </div>
		      <div class="modal-body">
		          <div class="mb-3">
		            <input class="form-control" type="text" value="${friA.email }" aria-label="readonly input example" readonly>
		          </div>
		      </div>
		      <div class="modal-footer">
		        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">닫기</button>
		      </div>
		    </div>
		  </div>
		</div>
	 </c:forEach>
	<hr>
	<a>보낸 친구신청</a>
     	 <c:forEach items="${friendList2 }" var="fri2" begin="0" >	
        <li class="nav-item">
        	<button type="submit" class="btn btn-secondary" data-bs-toggle="modal" data-bs-target="#userInfo${fri2.idN247_f }">${fri2.f_name }</button>
		    <form action="updateDelFriAction" id="myForm" class="form-inline" method="get" accept-charset="UTF-8">
		    <button type="submit" class="btn btn-secondary">취소</button>
		    <input type="hidden" name="idN247_f" value="${fri2.idN247_f }">
		   	<input type="hidden" name="tabId" value="${selectTab }">
 	     	<input type="hidden" name="userNum" value="${userNum }">
		    </form>
		 </li>
		  <div class="modal fade" id="userInfo${fri2.idN247_f }" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">		    
		  <div class="modal-dialog">
		      
		    <div class="modal-content">
		      <div class="modal-header">
		        <h5 class="modal-title" id="exampleModalLabel">${fri2.f_name }</h5>
		        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
		      </div>
		      <div class="modal-body">
		          <div class="mb-3">
		            <input class="form-control" type="text" value="${fri2.email }" aria-label="readonly input example" readonly>
		          </div>
		      </div>
		      <div class="modal-footer">
		        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">닫기</button>
		      </div>
		    </div>
		  </div>
		</div>
	 </c:forEach>
	<hr>
	<a>받은 친구신청</a>
	<c:forEach items="${friendList3 }" var="fri3" begin="0" >	
        <li class="nav-item">
        	<button type="button" class="btn btn-secondary" data-bs-toggle="modal" data-bs-target="#userInfo${fri3.idN247_f }">${fri3.f_name }</button>
		    <form action="updateFriAdmissionAction" id="myForm" class="form-inline" method="get" accept-charset="UTF-8">
		    <button type="submit" class="btn btn-secondary">수락</button>
		    <input type="hidden" name="idN247_f" value="${fri3.idN247_f }">
 	 	    <input type="hidden" name="tabId" value="${selectTab }">
 	     	<input type="hidden" name="userNum" value="${userNum }">
		    </form>
		    <form action="updateDelFriAction" id="myForm" class="form-inline" method="get" accept-charset="UTF-8">
		    <button type="submit" class="btn btn-primary">거절</button>
		    <input type="hidden" name="idN247_f" value="${fri3.idN247_f }">
		    <input type="hidden" name="tabId" value="${selectTab }">
 	    	<input type="hidden" name="userNum" value="${userNum }">
		    </form>
		 </li>
		 
		 
		  <div class="modal fade" id="userInfo${fri3.idN247_f }" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">		    
		  <div class="modal-dialog">		      
		    <div class="modal-content">
		      <div class="modal-header">
		        <h5 class="modal-title" id="exampleModalLabel">${fri3.f_name }</h5>
		        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
		      </div>
		      <div class="modal-body">
		          <div class="mb-3">
		            <input class="form-control" type="text" value="${fri3.email }" aria-label="readonly input example" readonly>
		          </div>
		      </div>
		      <div class="modal-footer">
		        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">닫기</button>
		      </div>
		    </div>
		  </div>
		</div>
	 </c:forEach>
	 
	 <hr>
    <a href="/mvc/board?tabId=${selectTab }"> board </a>
    <!-- Optional JavaScript; choose one of the two! -->   
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.10.2/dist/umd/popper.min.js" integrity="sha384-7+zCNj/IqJ95wo16oMtfsKbZ9ccEh31eOz1HGyDuCQ6wgnyJNSYdrPa03rtR1zdB" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.min.js" integrity="sha384-QJHtvGhmr9XOIpI6YVutG+2QOK9T+ZnN4kzFN1RtK3zEFEIsxhlmWl5/YESvpZ13" crossorigin="anonymous"></script>
 
	
		<div class="modal fade" id="addFriModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
		  <form action="searchFriendAction" id="myForm" class="form-inline" method="get" accept-charset="UTF-8">
		    
		  <div class="modal-dialog">
		      
		    <div class="modal-content">
		      <div class="modal-header">
		        <h5 class="modal-title" id="exampleModalLabel">신청할 친구의 이메일 주소를 입력해주세요</h5>
		        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
		      </div>
		      <div class="modal-body">
		          <div class="mb-3">
		            <input class="form-control" type="text" name="search" value="" aria-label="input example">
		          </div>
		      </div>
		      <div class="modal-footer">
		        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">닫기</button>
		        <button type="submit" class="btn btn-primary">추가</button>
		 	 	<input type="hidden" name="tabId" value="${selectTab }">
		 	 	<input type="hidden" name="userNum" value="${userNum }">
		      </div>
		    </div>
		  </div>
		 </form>
		</div>
  </body>
</html>