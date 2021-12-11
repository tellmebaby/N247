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
    <h1>tab form</h1>
<!--  post 수정및 입력창 -->

	<form action="createTabAction" id="myForm" class="form-inline" method="post">
 	 	
 	 	
		<table>
			<tr>
				<td><input  type="text" name="tabTitle" placeholder="탭명을 입력하세요" aria-label="tabTitle"></td>
			</tr>
		 	<tr>		
				<td colspan=4>
				<button type="submit" class="btn btn-secondary">V</button>
				<button type="submit" onclick="myFunction()" class="btn btn-secondary">X</button>
				</td>
			</tr>
 	 	</table>
 	 		<input type="hidden" name="nick" value="${userSet.nick }">
 	 		<input type="hidden" name="userNum" value="${userSet.userNum }">
 	 </form>
 	 
 	 <c:forEach items="${tabList }" var="tab" begin="0" >	
        <li class="nav-item">
        	<form action="updateTabTitle" id="myForm" class="form-inline" method="get" accept-charset="UTF-8">
        	   <button type="submit" class="btn btn-secondary">${tab.tabTitle }</button>
        	   <input type="hidden" name="tabId" value="${tab.tabId }">
        	</form>
		    <form action="<c:out value="${tab.check == 0 ? 'deleteTabTitleAction' : 'selectTab' }"/>" id="myForm" class="form-inline" method="get" accept-charset="UTF-8">
		    <button type="submit" class="btn btn-secondary">X</button>
		    <input type="hidden" name="tabId" value="${tab.tabId }">
		    <input type="hidden" name="nick" value="${tab.nick }">
		    <input type="hidden" name="userNum" value="${userSet.userNum }">
		    </form>
		    
		 </li>
	 </c:forEach>
	 
 	 <a href="/mvc/board?tabId=${selectedTabId }"> board </a>
    
    
    <!-- Optional JavaScript; choose one of the two! -->

    <!-- Option 1: Bootstrap Bundle with Popper -->
<!--     <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script> -->

    <!-- Option 2: Separate Popper and Bootstrap JS -->
    
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.10.2/dist/umd/popper.min.js" integrity="sha384-7+zCNj/IqJ95wo16oMtfsKbZ9ccEh31eOz1HGyDuCQ6wgnyJNSYdrPa03rtR1zdB" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.min.js" integrity="sha384-QJHtvGhmr9XOIpI6YVutG+2QOK9T+ZnN4kzFN1RtK3zEFEIsxhlmWl5/YESvpZ13" crossorigin="anonymous"></script>
    
<!--     내용삭제 자바스크립트  -->
	<script>
		function myFunction() {
		 document.getElementById("myForm").reset();
		}
    </script>
   
    
  </body>
</html>