<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="utility.*" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Login</title>
<style>
	ul { list-style-type: none; margin: 0; padding: 0; }
	li { display: inline; }
	.container {
		width: 300px;
		clear: both;
	}
	.container input {
		width: 100%;
		clear: both;
	}
</style>
</head>
<body>
	
	<div class= "jumbotron">
	<div class="container text-center">
	</div>
	</div>
	<h1>BeautyCircle</h1>
	
	<ul>
	<li><a href="FrontServlet?command=HomePage">Home</a></li>
	<li><a href="FrontServlet?command=Register">Register</a></li>
	<li><a href=FrontServlet?command=Login>Login</a></li>
	</ul>
	
	<h3>Login</h3>
	<form action="FrontServlet?command=Profile" method="POST">
	<div class ="container">
	<label for="user">Username: </label>
	<input type="text" name="userName">
	<label for="pass">Password: </label>
	<input type="password" name="passWord">
	</div>
	
	<button type="submit" name="log" class="btn btn-default" value="login">Login</button>
	</form>
	

</body>
</html>