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
	
  			<br><br><br>
		    <div class="container">
			  <div class="row justify-content-center">
			    <div class="col-auto">
			      <img src="/images/logoN247.png" class="img-fluid" width="450">
			    </div>
			  </div>
			  <div class="row justify-content-center">
			    <div class="col-md-auto">
			       <small class=erm >${errorMessage }</small>
			    </div>
			  </div>
			</div>
		   
		   	<div class="container">
			  <div class="row justify-content-center">
			    <div class="col-auto">
			    	<img src="/images/mainImg.png" class="img-fluid" width="350">
			    </div>
			  </div>
			</div>

		    <br><br>
			 <div class="container">
			  <div class="row justify-content-center">
			    <div class="col-auto">
			    	<a href="javascript:void(0)" onclick="kakaoLogin();"><img class="img-fluid" src="resources/images/kakao_login_medium_wide.png"></a>
			    </div>
			  </div>
			 </div>
			 
<!-- 			 <br><br> -->
<!-- 			 <div class="container"> -->
<!-- 			  <div class="row justify-content-center"> -->
<!-- 			    <div class="col-auto"> -->
<!-- 			    	<a href="javascript:void(0)" onclick="test2login();">test3 login</a> -->
<!-- 			    </div> -->
<!-- 			  </div> -->
<!-- 			 </div> -->
			
		<script type="text/javascript" src="http://code.jquery.com/jquery-3.5.1.min.js"></script>
		<!-- 카카오 스크립트 -->
		<script src="https://developers.kakao.com/sdk/js/kakao.js"></script>

			<script>
			
			//카카오로그인
			
			Kakao.init('8fb8c0ff6b3b686826534e5606e18674'); //발급받은 키 중 javascript키를 사용해준다.
			console.log(Kakao.isInitialized()); // sdk초기화여부판단
			//카카오로그인
			function kakaoLogin() {
			    Kakao.Auth.login({
			      success: function (response) {
			        Kakao.API.request({
			          url: '/v2/user/me',
			          success: function (response) {
			        	  //response 를 받아왔어요 
			        	  const id = response.id;
			        	  const nickName = response.properties.nickname;
			        	  const userImg = response.properties.profile_image;
						  const token = Kakao.Auth.getAccessToken();
						  
			        	  console.log(Kakao.Auth.getAccessToken());
			        	  
			        	  console.log('보낼아이들 :' + id + nickName + userImg + token);
			        	  loginKakaoAction(id, nickName, userImg, token);
			
			        	  //보낼거야  이름, 아이디, 사진 그리고 토큰도 보낼거야 loginAction 안했어
			        	  //loginAction 에서 할일은 우선, if 에서 토큰이 유효한지 검증  했어
			        	  //true = 아이디가 디비에 존재하는지 검증 내가 할게 
			        	  //true = 그냥 로그인 / false = 회원가입후 로그인 db에기록 내가 할게 
			        	  
			        	  //curl -v -X GET "https://kapi.kakao.com/v1/user/access_token_info" \ -H "Authorization: Bearer ${ACCESS_TOKEN}" 하는 방법 
			        	  //request.getHeader(String)
			        	  
			          },
			          fail: function (error) {
			            console.log(error)
			          },
			        })
			      },
			      fail: function (error) {
			        console.log(error)
			      },
			    })
			  }

			//로그인 돌려서 체크 받아와
			function loginKakaoAction(id, nickName, userImg, token){
				$.ajax({
			        url: 'loginKakaoAction',
			   	 method: "POST",
				     data: {'id': id, 'nickName' : nickName, 'userImg' : userImg , 'token' : token}
				     })
				
				.done(function(data) {	
					const idCheck = data;
					//로그인 결과에 따라 로그인이 달라진다.
					//첫탭 만들기는 보드 넘어가서 하자 
					//유저아이디를 받아오는 게 나을듯
					if(idCheck == 1){
						console.log('우리 회원입니다.')
						location.href="/mvc/board";
					}
			
				});
			}
			
			function test2login() {
				const id = "test2";
				$.ajax({
			        url: 'test2loginAction',
			   	 method: "POST",
				     data: {'id': id}
				     })
				
				.done(function(data) {	
					const idCheck = data;
					//로그인 결과에 따라 로그인이 달라진다.
					//첫탭 만들기는 보드 넘어가서 하자 
					//유저아이디를 받아오는 게 나을듯
					if(idCheck == 1){
						console.log('우리 회원입니다.')
						location.href="/mvc/board";
					}
				});
			}
			</script>
<!-- 		   modal end -->

    <!-- Option 1: Bootstrap Bundle with Popper -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>

  </body>
</html>