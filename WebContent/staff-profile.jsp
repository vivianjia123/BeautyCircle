<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@page import="javax.servlet.*"%>
<%@page import="org.apache.shiro.*"%>
<%@page import="service.*"%>
<%@page import="domain.*"%>
<%@page import="service.*"%>
<%@page import="utility.*"%>
<%@page import="org.apache.shiro.authc.*" %>
<%@page import="org.apache.shiro.subject.Subject" %>
<html>
<head>
<meta charset="UTF-8">
<title>Administrator Profile</title>
	<style>
		ul { list-style-type: none; margin: 0; padding: 0; }
		li { display: inline; }
	</style>
</head>
<body>

	<h1>Beauty Circle Administrator</h1>
<ul>
	<li><a href="FrontServlet?command=Profile">Profile</a>  </li>
	<li><a href="FrontServlet?command=UserProfiles">Users</a>  </li>
	<li><a href="FrontServlet?command=Product">Products</a>  </li>
	<li><a href="FrontServlet?command=Order">Order</a>  </li>
	<li><a href="FrontServlet?command=Logout">Logout</a>  </li>
</ul>
	<% 
	if(AppSession.isAuthenticated() && AppSession.getAdminUser() != null && AppSession.hasRole(AppSession.ADMINISTRATOR)) {
		User foundUser = AppSession.getAdminUser();
		if (request.getParameter("edit") == null && request.getParameter("submit") == null) {
		%>
	  	<h3>Welcome Back Administrator <%=foundUser.getFirstName() %>!</h3>
		
		<p><b>UserId: </b> <%=foundUser.getId() %></p >
		<p><b>First Name: </b> <%=foundUser.getFirstName() %></p >
		<p><b>Last Name: </b> <%=foundUser.getLastName() %></p >
		<p><b>Email Address: </b> <%=foundUser.getEmailAddress()%></p >
		<p><b>Address: </b> <%=foundUser.getAddress()%></p> 
		<form method="POST" action="FrontServlet?command=Profile">
		<button type="submit" name="edit" value="edit" style="width:60px">Edit</button></form>
		
		<%} else if (request.getParameter("edit") != null) { %>
		<h3>Editing Details for Administrator <%=foundUser.getFirstName() %></h3>
		<form method="POST" action="FrontServlet?command=Profile">
		<p><b>UserId: </b> <%=foundUser.getId() %></p >
		<p><b>First Name: </b> <input name="firstname" type="text" required value=<%=foundUser.getFirstName() %>></p >
		<p><b>Last Name: </b> <input name="lastname" type="text" required value=<%=foundUser.getLastName() %>></p >
		<p><b>Email Address: </b> <input name="emailaddress" type="email" required value=<%=foundUser.getEmailAddress() %>></p >
		<p><b>Address: </b> <input name="address" type="text" required value='<%=foundUser.getAddress() %>'></p>
		<p><b>Username: </b> <input name="username" type="text" required value=<%=foundUser.getUsername() %>></p>
		<p><b>Password: </b> <input name="password" type="password" required value=<%=foundUser.getPassword() %>></p>
		<p><b>Re-Enter Your Password: </b> <input name="reenterpassword" required type="password"></p>
		<Input type="submit" name=back value="Back" style="width:60px">
		<Input type="submit" name=submit value="Submit" style="width:60px">
		</form>
		
		<%}else if (request.getParameter("submit") != null) {
			String firstname = request.getParameter("firstname");
			String lastname = request.getParameter("lastname");
			String emailaddress = request.getParameter("emailaddress");
			String address = request.getParameter("address");
			String username = request.getParameter("username");
			String password = request.getParameter("password");
			if (request.getParameter("password").equals(request.getParameter("reenterpassword"))) {
				Administrator newAdmin = new Administrator(foundUser.getId(), firstname, lastname, emailaddress, address, username, password); 
				AdministratorService administratorService = new AdministratorService();
				administratorService.updateAdmin(newAdmin);
				%>
					
					<h3>Details Successfully Edited!</h3>
					<p><b>UserId: </b> <%=newAdmin.getId() %></p >
					<p><b>First Name: </b> <%=newAdmin.getFirstName() %></p >
					<p><b>Last Name: </b> <%=newAdmin.getLastName() %></p >
					<p><b>Email Address: </b> <%=newAdmin.getEmailAddress()%></p >
					<p><b>Address: </b> <%=newAdmin.getAddress()%></p>
					<%AppSession.init(newAdmin, AppSession.ADMINISTRATOR); %> 
					<form method="POST" action="FrontServlet?command=Profile">
				<Input type="submit" name=back value="Back" style="width:60px">
					</form>
			
			<% } else {%>
				<h3>Passwords do not match! Please Try again!</h3>
						<form method="POST" action="FrontServlet?command=Profile">
						<p><b>UserId: </b> <%=foundUser.getId() %></p >
						<p><b>First Name: </b> <input name="firstname" type="text" required value=<%=firstname %>></p >
						<p><b>Last Name: </b> <input name="lastname" type="text" required value=<%=lastname %>></p >
						<p><b>Email Address: </b> <input name="emailaddress" required type="email" value=<%=emailaddress %>></p >
						<p><b>Address: </b> <input name="address" type="text" required value='<%=address %>'></p>
						<p><b>Username: </b> <input name="username" type="text" required value=<%=username%>></p>
						<p><b>Password: </b> <input name="password" type="password" required value=<%=foundUser.getPassword() %>></p>
						<p><b>Re-Enter Your Password: </b> <input name="reenterpassword" required type="password"></p>
						<Input type="submit" name=back value="Back" style="width:60px">
						<Input type="submit" name=submit value="Submit"></form>
				
			<% }%>

			
			


	<%} } else {
		response.sendRedirect("/NewBeautyCircle/error.jsp"); 
		SecurityUtils.getSecurityManager().logout(SecurityUtils.getSubject());}%>

</body>
</html>