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
    <small>반갑습니다.</small>
    <h1>새로운 탭을 만들어보세요.</h1>
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
 	 		<input type="hidden" name="userNum" value="${userSet.userNum }">
 	 </form>
    


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