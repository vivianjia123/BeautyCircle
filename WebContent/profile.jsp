<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@page import="javax.servlet.*"%>
<%@page import="org.apache.shiro.*"%>
<%@page import="service.*"%>
<%@page import="domain.*"%>
<%@page import="utility.*"%>
<%@page import="org.apache.shiro.authc.*" %>
<%@page import="org.apache.shiro.subject.Subject" %>


<html>
<head>
<meta charset="UTF-8">
<title>Profile</title>
	<style>
		ul { list-style-type: none; margin: 0; padding: 0; }
		li { display: inline; }
	</style>
</head>
<body>
	<h1>Beauty Circle</h1>
<ul>
	<li><a href="FrontServlet?command=HomePage">Home</a>  </li>
	<li><a href="FrontServlet?command=Profile">Profile</a>  </li>
	<li><a href="FrontServlet?command=Product">Product</a>  </li>
	<li><a href="FrontServlet?command=Cart">Cart</a>  </li>
	<li><a href="FrontServlet?command=Wishlist">Wishlist</a>  </li>
	<li><a href="FrontServlet?command=Order">Order</a>  </li>
	<li><a href="FrontServlet?command=Logout">Logout</a>  </li>
</ul>

<div class="col-sm-12">
<div class="col-sm-4">
<div id="mySidenav" class="sidenav">
	<%RegisteredUser user;%>
	<%RegisteredUserService registeredUserService = new RegisteredUserService(); %>
	<% if(AppSession.isAuthenticated()) {%>
	<%User foundUser = AppSession.getUser(); 
	  if (AppSession.getUser() != null){%>
	  	<h3>Welcome Back <%=foundUser.getFirstName() %>!</h3>
		<p><b>UserId: </b> <%=foundUser.getId() %></p >
		<p><b>First Name: </b> <%=foundUser.getFirstName() %></p >
		<p><b>Last Name: </b> <%=foundUser.getLastName() %></p >
		<p><b>Email Address: </b> <%=foundUser.getEmailAddress()%></p >
		<p><b>Shipping Address: </b> <%=foundUser.getShippingAddress()%></p > 
	
	<%} else {
		response.sendRedirect("/NewBeautyCircle/error.jsp"); 
		SecurityUtils.getSecurityManager().logout(SecurityUtils.getSubject());}
	  
	} %>
	
</div>
</div>
</div>


</body>
</html>