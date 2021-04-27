<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="utility.*" %>
<%@page import="org.apache.shiro.SecurityUtils" %>
<%@page import="org.apache.shiro.subject.Subject" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Error</title>
<style>
	ul { list-style-type: none; margin: 0; padding: 0; }
	li { display: inline; }
	.container {
		width: 200px;
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
		<% SecurityUtils.getSecurityManager().logout(SecurityUtils.getSubject()); %>
	<h3>Invalid Request! Please do not manipulate the url!</h3>

</body>
</html>