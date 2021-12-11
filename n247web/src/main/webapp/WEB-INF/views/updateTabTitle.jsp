<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<html>
  <head>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <!-- Bootstrap CSS -->
<link href="/docs/5.0/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-giJF6kkoqNQ00vy+HMDP7azOuL0xtbfIcaT9wjKHr8RbDVddVHyTfAAsrekwKmP1" crossorigin="anonymous">

    <title>N247</title>
  </head>
  <body>
    <h1>update tab form</h1>
<!--  post 수정및 입력창 -->

	<form action="updateTabTitleAction" id="myForm" class="form-inline" method="get">
 	 	<i class="fas fa-search" aria-hidden="true"></i>
 	 	
		<table>
			<tr>
				<td><input class="form-control form-control-sm" type="text" name="tabTitle" placeholder="${tabTitle.tabTitle }" aria-label="tabTitle"></td>
			</tr>
		 	<tr>		
				<td colspan=4>
				<button type="submit" class="btn btn-secondary">V</button>
				<button type="submit" onclick="myFunction()" class="btn btn-secondary">X</button>
				</td>
			</tr>
 	 	</table>
 	 		<input type="hidden" name="tabId" value="${tabTitle.tabId }">
 	 </form>
 	 

	 
 	 <a href="/mvc/board?tabId=${tabTitle.tabId} }"> board </a>
    <!-- Optional JavaScript; choose one of the two! -->

    <!-- Option 1: Bootstrap Bundle with Popper -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>

    <!-- Option 2: Separate Popper and Bootstrap JS -->
    <!--
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.10.2/dist/umd/popper.min.js" integrity="sha384-7+zCNj/IqJ95wo16oMtfsKbZ9ccEh31eOz1HGyDuCQ6wgnyJNSYdrPa03rtR1zdB" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.min.js" integrity="sha384-QJHtvGhmr9XOIpI6YVutG+2QOK9T+ZnN4kzFN1RtK3zEFEIsxhlmWl5/YESvpZ13" crossorigin="anonymous"></script>
    -->
<!--     내용삭제 자바스크립트  -->
	<script>
		function myFunction() {
		 document.getElementById("myForm").reset();
		}
    </script>

    
  </body>
</html>