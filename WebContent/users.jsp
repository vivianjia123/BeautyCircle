<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@page import="javax.servlet.*"%>
<%@page import="org.apache.shiro.*"%>
<%@page import="service.*"%>
<%@page import="domain.*"%>
<%@page import="service.*"%>
<%@page import="utility.*"%>
<%@page import="java.util.*"%>
<%@page import="org.apache.shiro.authc.*" %>
<%@page import="org.apache.shiro.subject.Subject" %>
<html>
<head>
<meta charset="UTF-8">
<title>Users</title>
	<style>
		ul { list-style-type: none; margin: 0; padding: 0; }
		li { display: inline; }
		button {
  			display: inline-block;
  			}
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

<form id="admin" method="POST" action="FrontServlet?command=UserProfiles"></form>
<form id="register" method="POST" action="FrontServlet?command=NewUsers"></form>
<%	UserService userService = new UserService();  
	
if (request.getParameter("edit") == null && request.getParameter("delete") == null 
 && request.getParameter("admin") == null && request.getParameter("registereduser") == null) {
	List<User> users = userService.findUsers();
%>	<br>
	<button type="submit" name="RegisteredUser" form = "register" value= RegisteredUser style="width:150px">Add New User</button>
	<button type="submit" name="Administrator" form = "register" value=Administrator style="width:150px">Add New Administrator</button>
<h3>Existing Users: </h3>
<%for (User user: users) {%>
	<p><b>UserId: </b> <%=user.getId() %></p >
	<p><b>First Name: </b> <%=user.getFirstName() %></p >
	<p><b>Last Name: </b> <%=user.getLastName() %></p >
	<p><b>Email Address: </b> <%=user.getEmailAddress()%></p >
	<p><b>Address: </b> <%=user.getAddress()%></p> 
	<%if (user.getUserType().equals("class domain.Administrator")){ %>
	<p><b>Type: </b> Administrator</p> 
	<%}else { %>
	<p><b>Type: </b> Registered User</p> 
	<%} %>
	<button type="submit" name="edit" form = "admin" style="width:120px" value=<%=user.getId()%>>Edit</button>
	<button type="submit" name="delete" form = "admin" style="width:120px" value=<%=user.getId()%>>Delete</button>
	<hr>
<%} } else if (request.getParameter("edit") != null) {
	User temporary = new User(Long.parseLong(request.getParameter("edit")));
	User foundUser = userService.findUser(temporary);
	
	if (foundUser.getUserType().equals("class domain.Administrator")){ %>
	<form method="POST" action="FrontServlet?command=UserProfiles">
	<h3>Editing Details for Administrator <%=foundUser.getFirstName() %></h3>
	<p><b>UserId: </b> <%=foundUser.getId() %></p >
	<p><b>First Name: </b> <input name="firstname" type="text" required value=<%=foundUser.getFirstName() %>></p >
	<p><b>Last Name: </b> <input name="lastname" type="text" required value=<%=foundUser.getLastName() %>></p >
	<p><b>Email Address: </b> <input name="emailaddress" required type="email" value=<%=foundUser.getEmailAddress() %>></p >
	<p><b>Address: </b> <input name="address" type="text" required value='<%=foundUser.getAddress() %>'></p>
	<p><b>Username: </b> <input name="username" type="text" required value=<%=foundUser.getUsername() %>></p>
	<Input type="hidden" name="userid" value="<%=foundUser.getId()%>"></Input>
	<Input type="hidden" name="password" value="<%=foundUser.getPassword() %>"></Input>
	<Input type="hidden" name="usertype" value="<%=foundUser.getUserType() %>"></Input>
	<Input type="submit" name=back value="Back" style="width:120px">
	<Input type="submit" name=admin value="Submit" style="width:120px">
	</form>
	<%}else { %>
	<form method="POST" action="FrontServlet?command=UserProfiles">
	<h3>Editing Details for Registered User <%=foundUser.getFirstName() %></h3>
	<p><b>UserId: </b><%=foundUser.getId() %></p >
	<p><b>First Name: </b> <input name="firstname" type="text" required value=<%=foundUser.getFirstName() %>></p >
	<p><b>Last Name: </b> <input name="lastname" type="text" required value=<%=foundUser.getLastName() %>></p >
	<p><b>Email Address: </b> <input name="emailaddress" required type="email" value=<%=foundUser.getEmailAddress() %>></p >
	<p><b>Address: </b> <input name="address" type="text" required value='<%=foundUser.getAddress() %>'></p>
	<p><b>Shipping Address: </b> <input name="shippingaddress" required type="text" value='<%=foundUser.getShippingAddress() %>'></p>
	<p><b>Username: </b> <input name="username" type="text" required value=<%=foundUser.getUsername() %>></p>
	<Input name="userid" type="hidden" value="<%=foundUser.getId() %>"></Input>
	<Input name="password" type="hidden" value="<%=foundUser.getUsername() %>"></Input>
		<Input type="hidden" name="usertype" value="<%=foundUser.getUserType() %>"></Input>
	<Input type="submit" name=back value="Back" style="width:120px">
	<Input type="submit" name=registereduser value="Submit" style="width:120px">
	</form> 
	<%} %>

<%} else if (request.getParameter("admin") != null) {
	long userid = Long.parseLong(request.getParameter("userid"));
	String firstname = request.getParameter("firstname");
	String lastname = request.getParameter("lastname");
	String emailaddress = request.getParameter("emailaddress");
	String address = request.getParameter("address");
	String username = request.getParameter("username");
	String password = request.getParameter("password");
	Administrator newAdmin = new Administrator(userid, firstname, lastname, emailaddress, address, username, password); 
	AdministratorService administratorService = new AdministratorService();
	administratorService.updateAdmin(newAdmin);
	if (userid == AppSession.getAdminUser().getId()) {
		AppSession.init(newAdmin, AppSession.ADMINISTRATOR);
	}%>
		<h3>Details Successfully Edited!</h3>
		<p><b>UserId: </b> <%=newAdmin.getId() %></p >
		<p><b>First Name: </b> <%=newAdmin.getFirstName() %></p >
		<p><b>Last Name: </b> <%=newAdmin.getLastName() %></p >
		<p><b>Email Address: </b> <%=newAdmin.getEmailAddress()%></p >
		<p><b>Address: </b> <%=newAdmin.getAddress()%></p>
		<form method="POST" action="FrontServlet?command=UserProfiles">
		<Input type="submit" name=back value="Back" style="width:120px"></Input>
		</form>

<%} else if (request.getParameter("registereduser") != null){ 
	long userid = Long.parseLong(request.getParameter("userid"));
	User user = new User(userid);
	
	String firstname = request.getParameter("firstname");
	String lastname = request.getParameter("lastname");
	String emailaddress = request.getParameter("emailaddress");
	String address = request.getParameter("address");
	String shippingaddress = request.getParameter("shippingaddress");
	String username = request.getParameter("username");
	String password = request.getParameter("password");
	
	RegisteredUser newUser = new RegisteredUser(userid, firstname, lastname, emailaddress, address, username, password, shippingaddress);
	RegisteredUserService registeredUserService = new RegisteredUserService();
	registeredUserService.updateUser(newUser);
	%>
		<h3>Details Successfully Edited!</h3>
		<p><b>UserId: </b> <%=newUser.getId() %></p >
		<p><b>First Name: </b> <%=newUser.getFirstName() %></p >
		<p><b>Last Name: </b> <%=newUser.getLastName() %></p >
		<p><b>Email Address: </b> <%=newUser.getEmailAddress()%></p >
		<p><b>Address: </b> <%=newUser.getAddress()%></p>
		<form method="POST" action="FrontServlet?command=UserProfiles">
		<Input type="submit" name=back value="Back" style="width:120px"></Input>
		</form>

<%} else if (request.getParameter("delete") != null) {
	long userid = Long.parseLong(request.getParameter("delete"));
	User user = new User(userid);
	User foundUser = userService.findUser(user);
	String firstname = foundUser.getFirstName();
	String lastname = foundUser.getLastName();
	String emailaddress = foundUser.getEmailAddress();
	String address = foundUser.getAddress();
	String shippingaddress = foundUser.getShippingAddress();
	String username = foundUser.getUsername();
	String password = foundUser.getPassword();
	String usertype = foundUser.getUserType();
	
	if (usertype.equals("class domain.Administrator")){ 
		Administrator newAdmin = new Administrator(userid, firstname, lastname, emailaddress, address, username, password); 
		AdministratorService administratorService = new AdministratorService();
		if(newAdmin.getId() == AppSession.getAdminUser().getId()) {%>
			<h3>Cannot delete your own account while logged in!</h3>
			<form method="POST" action="FrontServlet?command=UserProfiles">
			<Input type="submit" name=back value="Back" style="width:120px"></Input>
			</form>
		<%}
		else {
			administratorService.deleteAdmin(newAdmin);
		}
		
	}
	else {
		RegisteredUser newUser = new RegisteredUser(userid, firstname, lastname, emailaddress, address, username, password, shippingaddress);
		RegisteredUserService registeredUserService = new RegisteredUserService();
		registeredUserService.deleteUser(newUser);%>
			<h3>User Deleted Successfully!</h3>
			<form method="POST" action="FrontServlet?command=UserProfiles">
			<Input type="submit" name=back value="Back" style="width:120px"></Input>
			</form>
	<%}%>
	

<%} %>
</body>
</html>