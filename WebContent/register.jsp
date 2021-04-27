<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Register</title>
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
	</style>
</head>
<body>

	<h1>Beauty Circle</h1>

	<ul>
	<li><a href="FrontServlet?command=HomePage">Home</a></li>
	<li><a href="FrontServlet?command=Register">Register</a></li>
	<li><a href=FrontServlet?command=Login>Login</a></li>
	</ul>

	<h3>Register</h3>
	<form action="FrontServlet?command=RegisterUser" method="POST">
	<div class="container">
		<label for="usr">User Name:</label>
		<input type="text" class="form-control" name="username">
	</div>
	
	<div class="container">
		<label for="pwd">Password:</label>
		<input type="password" class="form-control" name="password">
	</div>
	
	<div class="container">
		<label for="usr">First Name:</label>
		<input type="text" class="form-control" name="fname">
	</div>
	
	<div class="container">
		<label for="usr">Last Name:</label>
		<input type="text" class="form-control" name="lname">
	</div>

	<div class="container">
		<label for="usr">Email:</label>
		<input type="email" class="form-control" name="email">
	</div>
		
	<div class="container">
		<label for="shippingaddress">Shipping Address:</label>
		<input type="text" class="form-control" name="shippingaddress">
	</div>

	<div class="container">
		<label for="address">Address:</label>
		<input type="text" class="form-control" name="address">
	</div>
	

	<button type=submit class="btn btn-default" name="register">Register</button></form>

</body>
</html>