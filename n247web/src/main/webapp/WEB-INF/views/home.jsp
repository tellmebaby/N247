<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<html>
  <head>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
	<style>
	.erm {
	color: red;
	}
	</style>
    <title>N247</title>
  </head>
  <body>
	  	    <nav class="py-2 bg-light border-bottom">
			    <div class="container d-flex flex-wrap">
			      <ul class="nav me-auto">
	
			      </ul>
			      <ul class="nav">
			        <li class="nav-item"><a href="#" class="nav-link link-dark px-2" data-bs-toggle="modal" data-bs-target="#member-login-Modal">Login</a></li>
			        <li class="nav-item"><a href="#" class="nav-link link-dark px-2" data-bs-toggle="modal" data-bs-target="#member-signUp-Modal">Sign up</a></li>
			      </ul>
			    </div>
			</nav>

  			<p>
		    <div class="container">
			  <div class="row justify-content-md-center">
			    <div class="col-md-8">
			      <img src="/images/logoN247.png" class="img-fluid">
			    </div>
			  </div>
			  <div class="row justify-content-md-center">
			    <div class="col-md-auto">
			       <small class=erm >${errorMessage }</small>
			    </div>
			  </div>
			</div>
			
			<p>
		    <p>
		    <p>
		   
		   	<div class="container">
			  <div class="row justify-content-md-center">
			    <div class="col-md-6">
			    	<img src="/images/mainImg.png" class="img-fluid">
			    </div>
			  </div>
			</div>
		   
<!-- 		modal start -->
		   <div class="modal fade" id="member-login-Modal" tabindex="-1" aria-labelledby="ModalLabel" aria-hidden="true">
		 	 <div class="modal-dialog">
		  	  <form action="loginAction" method="post" >
		  	   <div class="modal-content">
		  	     <div class="modal-header">
		  	       <h5 class="modal-title" id="ModalLabel">Login</h5>	
		  	       <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
		  	     </div>
		  	     <div class="modal-body">    
		           <div class="mb-3 row">
		   			<label for="staticEmail" class="col-sm-2 col-form-label">Email</label>
		   			 <div class="col-sm-10">
		     			 <input type="text" name="id" class="form-control" id="staticEmail" placeholder="email@example.com">
		   		  	 </div>
		 	       </div>
				   <div class="mb-3 row">
					 <label for="inputPassword" class="col-sm-2 col-form-label">Password</label>
					  <div class="col-sm-10">
					   <input type="password" name="password" class="form-control" id="inputPassword">
					  </div>   
				   </div>
				 </div>
		    	 <div class="modal-footer">
		           <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">닫기</button>
		           <button type="submit" class="btn btn-primary">로그인</button>
		         </div>
		      </div>
		     </form>
		    </div>
		   </div>
		   
		   
		   <div class="modal fade" id="member-signUp-Modal" tabindex="-1" aria-labelledby="ModalLabel" aria-hidden="true">
		 	 <div class="modal-dialog">
		  	  <form action="memberAction" method="post" >
		  	   <div class="modal-content">
		  	     <div class="modal-header">
		  	       <h5 class="modal-title" id="ModalLabel">Sign up</h5>	
		  	       <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
		  	     </div>
		  	     <div class="modal-body">    
		           <div class="mb-3 row">
		   			<label for="staticEmail-signUp" class="col-sm-2 col-form-label">이메일</label>
		   			 <div class="col-sm-10">
		     			 <input type="text" name="id" class="form-control" id="staticEmail" placeholder="email@example.com">
		   		  	 </div>
		 	       </div>
		 	       <div class="mb-3 row">
		   			<label for="staticEmail" class="col-sm-2 col-form-label">계정이름</label>
		   			 <div class="col-sm-10">
		     			 <input type="text" name="nickName" class="form-control" id="staticEmail" value="">
		   		  	 </div>
		 	       </div>
				   <div class="mb-3 row">
					 <label for="inputPassword" class="col-sm-2 col-form-label">비밀번호</label>
					  <div class="col-sm-10">
					   <input type="password" name="password" class="form-control" id="inputPassword-signUp">
					  </div>   
				   </div>
				   <div class="mb-3 row">
					 <label for="inputPassword" class="col-sm-2 col-form-label">비밀번호 재입력</label>
					  <div class="col-sm-10">
					   <input type="password" name="passwordConfirm" class="form-control" id="inputRePassword-signUp">
					  </div>   
				   </div>
				   <div class="alert alert-warning text-small" role="alert">
			                 <span>비밀번호는 숫자문자조합 8자이상 입력 </span>
			            </div>
				 </div>
		    	 <div class="modal-footer">
		           <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">닫기</button>
		           <button type="submit" class="btn btn-primary">가입하기</button>
		         </div>
		      </div>
		     </form>
		    </div>
		   </div>
<!-- 		   modal end -->
    <!-- Optional JavaScript; choose one of the two! -->

    <!-- Option 1: Bootstrap Bundle with Popper -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>

    <!-- Option 2: Separate Popper and Bootstrap JS -->
    <!--
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.10.2/dist/umd/popper.min.js" integrity="sha384-7+zCNj/IqJ95wo16oMtfsKbZ9ccEh31eOz1HGyDuCQ6wgnyJNSYdrPa03rtR1zdB" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.min.js" integrity="sha384-QJHtvGhmr9XOIpI6YVutG+2QOK9T+ZnN4kzFN1RtK3zEFEIsxhlmWl5/YESvpZ13" crossorigin="anonymous"></script>
    -->
  </body>
</html>