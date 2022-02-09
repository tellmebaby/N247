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
			        <li class="nav-item"><a href="/mvc/logOutAction" class="nav-link link-dark px-2">Log out</a></li>
			      </ul>
			    </div>
			</nav>
  
  		    <div class="container">
			  <div class="row justify-content-md-center">
			    <div class="col-md-8">
			      <img src="/images/logoN247.png" class="img-fluid">
			    </div>
			  </div>
			  <div class="row justify-content-md-center">
			    <div class="col-md-auto">
			       <small>${userIdTabId.nickName }님 반갑습니다. </small>
			    </div>
			  </div>
			  <div class="row justify-content-md-center">
			  	<div class="col-md-auto">
			  		<h1>이제 프로젝트를 만들어 시작해보세요.</h1>
			  	</div>
			  </div>
			  <div class="row justify-content-md-center">
			  <div class="col-md-auto">
	    		
              <button class="btn btn-primary btn-block" type="button" data-bs-toggle="modal" data-bs-target="#project-create-modal" aria-expanded="false">
                New
              </button>

	    		</div>
   			 </div>
   			 
   			 

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
 	 		<input type="hidden" name="userNum" value="${userIdTabId.userId }">
 	 		<input type="text" name="id" value="${userIdTabId.id }">
 	 		
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
		function myFunction() {
		 document.getElementById("myForm").reset();
		}
    </script>
  </body>
</html>