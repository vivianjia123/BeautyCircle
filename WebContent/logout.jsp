<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="utility.AppSession" %>
<%@page import="org.apache.shiro.SecurityUtils" %>
<%@page import="org.apache.shiro.subject.Subject" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Logout</title>
<style>
	ul { list-style-type: none; margin: 0; padding: 0; }
	li { display: inline; }
</style>
</head>
<body>
	<div class= "jumbotron">
	<div class="container text-center">
	</div>
	</div>
	<h1>Beauty Circle</h1>
	
	
	<ul>
	<li><a href="FrontServlet?command=HomePage">Home</a></li>
	<li><a href="FrontServlet?command=Register">Register</a></li>
	<li><a href=FrontServlet?command=Login>Login</a></li>
	</ul>
	<%if(SecurityUtils.getSubject().getSession().getTimeout() == 0) {%>
	<h3> Logout due to inactivity!</h3>
	<%} else {%>
	<h3> Logout was successful!</h3>
	<%} %>

</body>
</html>