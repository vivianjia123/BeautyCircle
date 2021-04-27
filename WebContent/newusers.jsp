<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Add New User</title>
	<style>
		ul { list-style-type: none; margin: 0; padding: 0; }
		li { display: inline; }
		.container { 
		width: 300px;
		clear: both;}
		.container input {
		width: 100%;
		clear: both;
		}
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
	<li><a href="FrontServlet?command=Cart">Cart</a>  </li>
	<li><a href="FrontServlet?command=Order">Order</a>  </li>
	<li><a href="FrontServlet?command=Logout">Logout</a>  </li>
</ul>

	<form id="create" action="FrontServlet?command=NewUsers" method="POST"></form>
	<form id="back" action="FrontServlet?command=UserProfiles" method="POST"></form>
	<%if (request.getParameter("RegisteredUser") != null) {%>
	<h3>Add New Registered User</h3>
	
	<div class="container">
		<label for="usr">User Name:</label>
		<input type="text" form="create" name="username">
	</div>
	
	<div class="container">
		<label for="pwd">Password:</label>
		<input type="password" form="create" name="password">
	</div>
	
	<div class="container">
		<label for="usr">First Name:</label>
		<input type="text" form="create" name="fname">
	</div>
	
	<div class="container">
		<label for="usr">Last Name:</label>
		<input type="text" form="create" name="lname">
	</div>

	<div class="container">
		<label for="usr">Email:</label>
		<input type="email" form="create" name="email">
	</div>
		
	<div class="container">
		<label for="shippingaddress">Shipping Address:</label>
		<input type="text" form="create" name="shippingaddress">
	</div>

	<div class="container">
		<label for="address">Address:</label>
		<input type="text" form="create" name="address">
	</div>
	<Input type="hidden" name="usertype" form="create" value="RegisteredUser"></Input>
	<br>
	<Input form="create" type="submit" name="register" style="width:120px" value="Create User">
	<button form="back" type="submit" name=back value="Back" style="width:120px">Back</button>

	<%} else if (request.getParameter("Administrator") != null) {%>
	
		<h3>Add New Administrator</h3>
	<div class="container">
		<label for="usr">User Name:</label>
		<input type="text" form="create" name="username">
	</div>
	
	<div class="container">
		<label for="pwd">Password:</label>
		<input type="password" form="create" name="password">
	</div>
	
	<div class="container">
		<label for="usr">First Name:</label>
		<input type="text" form="create" name="fname">
	</div>
	
	<div class="container">
		<label for="usr">Last Name:</label>
		<input type="text" form="create" name="lname">
	</div>

	<div class="container">
		<label for="usr">Email:</label>
		<input type="email" form="create" name="email">
	</div>

	<div class="container">
		<label for="address">Address:</label>
		<input type="text" form="create" name="address">
	</div>
	
	<Input type="hidden" name="usertype" value="Administrator" form="create"></Input>
	<br>
	<Input form="create" type="submit" name="register" value="Create Administrator">
	<button form="back" type="submit" name=back value="Back" style="width:120px">Back</button>
	
	<%} else if (request.getParameter("register") != null){%>
		<h3>User Successfully Created!</h3>
	<%} %>

</body>
</html>