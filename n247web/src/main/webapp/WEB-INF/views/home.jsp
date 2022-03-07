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
		    	 <div class="modal-footer" id="modal_footer_button">
		           <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">닫기</button>
		           <button type="submit" class="btn btn-primary" id="modal_login_button">로그인</button>
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
		     			 <input type="text" name="id" class="form-control" required id="idInput" placeholder="email@example.com">
		   		  	 </div>
		 	       </div>
		 	       <div class="mb-3 row">
		   			<label for="staticEmail" class="col-sm-2 col-form-label">계정이름</label>
		   			 <div class="col-sm-10">
		     			 <input type="text" name="nickName" class="form-control" id="nickInput" placeholder="10자이내 입력">
		   		  	 </div>
		   		  	 
		 	       </div>
				   <div class="mb-3 row">
					 <label for="inputPassword1" class="col-sm-2 col-form-label">비밀번호</label>
					  <div class="col-sm-10">
		     			 <input type="password" name="password" class="form-control" id="inputPassword1" placeholder="숫자문자조합 8자이상 입력">
		   		  	 </div>  
				   </div>
				   <div class="mb-3 row">
					 <label for="inputPassword" class="col-sm-2 col-form-label">비밀번호 재입력</label>
					  <div class="col-sm-10">
					   <input type="password" name="passwordConfirm" class="form-control" id="inputPassword2">
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

		<script type="text/javascript" src="http://code.jquery.com/jquery-3.5.1.min.js"></script>


			<script>
			
			
			var idCheckOk = 0; //현재는 체크안한 상태
			var nickNameCheckOk = 0; //현재는 체크안한 상태 
			
			$("#idInput").change(function idCheck(){
				
				var id = $('#idInput').val();
				
				 $.ajax({
			         url: 'idCheck',
			    	 method: "POST",
			 	     data: {'id': id}
			 	     })
			 	
				.done(function(data) {
					if(data==1){
						
						$('#idInput').val('');
						$('#idInput').attr("placeholder", "해당 email은 사용 할 수 없습니다.");
						//사용할수 없습니다를 띄어
						
						}else{
						idCheckOk = true;
						
						//사용할수 있습니다를 띄어 
						//idCheckOk = true;
						}
					})
				});
			
			$("#nickInput").change(function nickCheck(){
				
				var nick = $('#nickInput').val();
				 $.ajax({
			         url: 'nickCheck',
			    	 method: "POST",
			 	     data: {'nickName': nick}
				 	})
				 	
				.done(function(data) {
					if(data==1){
						
							$('#nickInput').val('');
							$('#nickInput').attr("placeholder", "해당 이름은 사용 할 수 없습니다.");
							//사용할수 없습니다를 띄어
						}else{
							nickNameCheckOk = true;
						}
					})
				});
			
				$("#inputPassword1").change(function passwordCheck(){
				
				var password = $('#inputPassword1').val();
				 $.ajax({
			         url: 'passwordCheck',
			    	 method: "POST",
			 	     data: {'password': password}
				 	})
				 	
				.done(function(data) {
					if(data==1){
						
							$('#inputPassword1').val('');
							$('#inputPassword1').attr("placeholder", "형식에 맞지 않습니다.");
							//사용할수 없습니다를 띄어
						}
					})
				});
				
				$("#inputPassword2").change(function passwordConfirm(){
					var password =$('#inputPassword1').val();
					var passwordConfirm =$('#inputPassword2').val();
					
					if(passwordConfirm == password){
						
					}else{
						$('#inputPassword2').val('');
						$('#inputPassword2').attr("placeholder", "비밀번호가 맞지 않습니다.");
					}
				});

// 			$("#modal_login_button").on("click",function(){
// 				console.log('테스트 성공');
// 				$('#modal_login_button').attr('disabled', true);
// 			});

			</script>
<!-- 		   modal end -->

    <!-- Option 1: Bootstrap Bundle with Popper -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>

  </body>
</html>