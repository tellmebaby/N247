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
    <title>N247</title>
  </head>
  <body>
  	<a href="#">other</a>
		 	 

<div class="row">
<!-- 		 		사이드바부분   -->
	<div class="col-4">
			
		<h1 class="visually-hidden">Sidebars examples</h1>
		
		  <div class="d-flex flex-column flex-shrink-0 p-3 text-white bg-dark" style="width: 280px;">
		    <a href="/" class="d-flex align-items-center mb-3 mb-md-0 me-md-auto text-white text-decoration-none">
		      <svg class="bi me-2" width="40" height="32"><use xlink:href="#bootstrap"/></svg>
		      <span class="fs-4">Sidebar</span>
		    </a>
		    <hr>
		    
		    <ul class="nav nav-pills flex-column mb-auto">
		      <c:forEach items="${tabList }" var="tab" begin="0" >	
			      <li class="nav-item">
			        <a href="#" class="nav-link" aria-current="page">
			          <svg class="bi me-2" width="16" height="16"><use xlink:href="#home"/></svg>
			          ${tab.tabTitle }
			        </a>
			      </li>
			  </c:forEach>
		    </ul>
		    
		    <hr>
		    <div class="dropdown">
		      <a href="#" class="d-flex align-items-center text-white text-decoration-none dropdown-toggle" id="dropdownUser1" data-bs-toggle="dropdown" aria-expanded="false">
		        <img src="https://github.com/mdo.png" alt="" width="32" height="32" class="rounded-circle me-2">
		        <strong>mdo</strong>
		      </a>
		      <ul class="dropdown-menu dropdown-menu-dark text-small shadow" aria-labelledby="dropdownUser1">
		        <li><a class="dropdown-item" href="#">New project...</a></li>
		        <li><a class="dropdown-item" href="#">Settings</a></li>
		        <li><a class="dropdown-item" href="#">Profile</a></li>
		        <li><hr class="dropdown-divider"></li>
		        <li><a class="dropdown-item" href="#">Sign out</a></li>
		      </ul>
		    </div>
		  </div>	
	</div>
	<div class="col-6">
	<!-- 		 		타임라인 부분  -->
				<div class="list-group">
				 <c:forEach items="${postList }" var="post" begin="0" >	
				    <a href="#" class="list-group-item list-group-item-action d-flex gap-3 py-3" aria-current="true">
	  				  <img src="https://github.com/twbs.png" alt="twbs" width="32" height="32" class="rounded-circle flex-shrink-0">
		  			  <div class="d-flex gap-2 w-100 justify-content-between">
		   				 <div>
		   				   <h6 class="mb-0">${post.postTitle }</h6>
		 				   <p class="mb-0 opacity-75">${post.description} </p>
					     </div>
					    <small class="opacity-50 text-nowrap">${post.create}</small>
		  			  </div>
				    </a>
				  </c:forEach>
   				 </div>	
	</div>
	<div class="col-2">
	
	    <a href="#">tab1</a>
	    <a href="/mvc/board2">tab2</a>
	    <a href="/mvc/board3">tab3</a>
	    <a href="/mvc/updatePost">입력</a>
	    <a href="/mvc/board3">연락처</a>
	    <a href="/mvc/board3">파일</a>
	</div>
</div>		 		


<!--    게시판 긁어 옴 날짜와 제목만 보여줌 클릭하면 모달로 전체 타임라인 창을 보여줌 -->


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