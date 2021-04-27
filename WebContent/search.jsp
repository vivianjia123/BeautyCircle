<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@page import="java.util.List"%>
<%@page import="service.*"%>
<%@page import="domain.*"%>
<%@page import="javax.servlet.*"%>
<%@page import="utility.AppSession" %>
<html>
<head>
<meta charset="UTF-8">
<title>Products Available</title>
	<style>
		ul { list-style-type: none; margin: 0; padding: 0; }
		li { display: inline; }
	</style>
</head>
<body>
	<h1>BeautyCircle</h1>
<ul>
	<li><a href="FrontServlet?command=HomePage">Home</a>  </li>
	<%if (AppSession.isAuthenticated()) { %>
		<li><a href="FrontServlet?command=Profile">Profile</a>  </li>
		<li><a href="FrontServlet?command=Product">Product</a>  </li>
		<li><a href="FrontServlet?command=Cart">Cart</a>  </li>
		<li><a href="FrontServlet?command=Wishlist">Wishlist</a>  </li>
		<li><a href="FrontServlet?command=Order">Order</a>  </li>
		<li><a href="FrontServlet?command=Logout">Logout</a>  </li>
	<%} else { %>
		<li><a href=FrontServlet?command=Register>Register</a></li>
		<li><a href=FrontServlet?command=Login>Login</a></li>
	<%} %>
</ul>
<br>
<form action="FrontServlet?command=Search" method="POST">
<div>
	<label for="productName">Find Products:</label>
	<input type="text" class="form-control" name="searchProduct">
	<button type="submit" name="search" >Search</button>
</div>
</form> 


<h3>Products Available: </h3>

<%String productName = request.getParameter("searchProduct"); %>
<%if (productName.equals("")) {%>
	<%ProductService productService = new ProductService();
	List<Product> products = productService.showAllAvailableProducts();
	if (!products.isEmpty()) { %>
	<%for (int i = 0; i < products.size(); i++) { %>
		<p><b><%= productService.getProductName(products.get(i)) %></b></p>
		<p><%= productService.getProductDescription(products.get(i)) %></p>
		<p><b>Price: </b> $<%= productService.getProductPrice(products.get(i)) %></p>
		<p><b>Rating: </b> <%= productService.getAverageRating(products.get(i)) %></p>
		<hr>
	<%} %>
	<%} %>
<%} else {
	ProductService productService = new ProductService();
	Product product = new Product(productName);
	List<Product> products = productService.searchProductByName(product);
	if (!products.isEmpty()) {%>
	<%for (int i = 0; i < products.size(); i++) { %>
		<p><b><%= productService.getProductName(products.get(i)) %></b></p>
		<p><%= productService.getProductDescription(products.get(i)) %></p>
		<p><b>Price: </b> $<%= productService.getProductPrice(products.get(i)) %></p>
		<p><b>Rating: </b> <%= productService.getAverageRating(products.get(i)) %></p>
		<hr>
	<%} %>
	<%} else {%>
	<p>Product does not exist!</p>
	<%} %>
<%} %>

</body>
</html>