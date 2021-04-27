<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="utility.AppSession" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Home</title>
<style> 
	ul { list-style-type: none; margin: 0; padding: 0; }
	li { display: inline; }
</style>
</head>
<body>
	<h1>Beauty Circle</h1>
	<ul>
	
	<li><a href="FrontServlet?command=HomePage">Home</a></li>
	<% if(AppSession.isAuthenticated()) {%>
	<li><a href="FrontServlet?command=Profile">Profile</a>  </li>
	<li><a href="FrontServlet?command=Product">Product</a>  </li>
	<li><a href="FrontServlet?command=Cart">Cart</a>  </li>
	<li><a href="FrontServlet?command=Wishlist">Wishlist</a>  </li>
	<li><a href="FrontServlet?command=Order">Order</a>  </li>
	<li><a href="FrontServlet?command=Logout">Logout</a>  </li>
	<% } else { %>
	<li><a href=FrontServlet?command=Register>Register</a></li>
	<li><a href=FrontServlet?command=Login>Login</a></li>
	<%} %>
	</ul>
	<br>
	<form action=FrontServlet?command=Search method= POST>
	<div>
	<label for=productName>Find Products:</label>
	<input type=text class= form-control name=searchProduct>
	<button type= submit name= search >Search</button>
	</div>
	</form>
	
</body>
</html>
