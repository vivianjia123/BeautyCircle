<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
   <%@page import="javax.servlet.*"%>
<%@page import="org.apache.shiro.*"%>
<%@page import="service.*"%>
<%@page import="domain.*"%>
<%@page import="service.*"%>
<%@page import="utility.*"%>
<%@page import="java.util.*"%>
<%@page import="org.apache.shiro.authc.*" %>
<%@page import="org.apache.shiro.subject.Subject" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Products</title>
	<style>
		ul { list-style-type: none; margin: 0; padding: 0; }
		li { display: inline; }
		button {
  			display: inline-block;
  			}
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
	<li><a href="FrontServlet?command=Order">Order</a>  </li>
	<li><a href="FrontServlet?command=Logout">Logout</a>  </li>
</ul>
<form id="product" method="POST" action="FrontServlet?command=Product"></form>
<%	ProductService productService = new ProductService();
	if(request.getParameter("edit") == null && request.getParameter("submit") == null 
	&& request.getParameter("delete") == null && request.getParameter("add") == null
	&& request.getParameter("create") == null) {
	List<Product> products = productService.showAllAvailableProducts(); %>
	<br>
	<button type="submit" name="add" form = "product" value= addnewproduct style="width:150px">Add New Product</button>
	<h3>Existing Products: </h3>
	<%for (Product product: products) {%>
	<p><b>Product Id: </b> <%=product.getId() %></p >
	<p><b>Product Name: </b> <%=product.getName() %></p >
	<p><b>Product Description: </b> <%=product.getDescription() %></p >
	<p><b>Price: </b> <%=product.getPrice()%></p >
	<p><b>Rating: </b> <%=productService.getAverageRating(product)%></p> 
	<p><b>Category: </b> <%=product.getCategory()%></p> 

	<button type="submit" name="edit" form = "product" style="width:120px" value=<%=product.getId()%>>Edit</button>
	<button type="submit" name="delete" form = "product" style="width:120px" value=<%=product.getId()%>>Delete</button>
	<hr>
<%} } else if(request.getParameter("edit") != null) {
	Product temporary = new Product(Long.parseLong(request.getParameter("edit")));
	Product foundProduct = productService.searchProductById(temporary);%>
	<h3>Editing Details for <%=foundProduct.getName() %></h3>
	<form method="POST" action="FrontServlet?command=Product">
	<p><b>Product Id: </b> <%=foundProduct.getId() %></p >
	<p><b>Product Name: </b> <input name="name" type="text" required value='<%=foundProduct.getName() %>'></p >
	<p><b>Product Description: </b><br><textarea name="description" required style="overflow: auto;" maxlength=500 cols="50" rows="5" wrap="soft"><%=foundProduct.getDescription() %></textarea></p >
	<p><b>Price: </b><input name="price" type="number" step= 0.01 min = 0 required value='<%=foundProduct.getPrice() %>'></p >
	<p><b>Rating: </b> <%=productService.getAverageRating(foundProduct)%></p> 
	<p><b><label for="usr">Category:</label></b>
	<select name="category" required>
			<option value='<%=foundProduct.getCategory()%>' selected="selected"><%=foundProduct.getCategory()%></option>
		    <option value="Makeup">Makeup</option>
		    <option value="Skincare">Skincare</option>
		    <option value="Hair">Hair</option>
		    <option value="Tools & Brushes">Tools & Brushes</option>
   	 		<option value="Fragrance">Fragrance</option>
   	 		<option value="Bath & Body">Bath & Body</option>
  	</select></p>
	<Input type="hidden" name="productid" value="<%=foundProduct.getId()%>"></Input>
	<Input type="hidden" name="rating" value="<%=foundProduct.getRating()%>"></Input>
	<Input type="submit" name=back value="Back" style="width:120px">
	<Input type="submit" name=submit value="Submit" style="width:120px">
	</form>

	
<%} else if (request.getParameter("submit") != null) {
	long productid = Long.parseLong(request.getParameter("productid"));
	String name = request.getParameter("name");
	String description = request.getParameter("description");
	String category = request.getParameter("category");
	System.out.println(category);
	Float price = Float.parseFloat(request.getParameter("price"));
	String rating = request.getParameter("rating");
	rating = rating.replace ("[", "");
	rating = rating.replace ("]", "");
	String[] ratings = rating.split(",");
	ArrayList<Float> ratingList = new ArrayList<>();
	for(String rate: ratings) {
		ratingList.add(Float.parseFloat(rate));
	}
	
	Product newProduct = new Product(productid, name, category, description, price,ratingList);
	productService.updateProduct(newProduct);%>
		<h3>Details Successfully Edited!</h3>
		<p><b>Product Id: </b> <%=newProduct.getId() %></p >
		<p><b>Product Name: </b> <%=newProduct.getName() %></p >
		<p><b>Product Description: </b> <%=newProduct.getDescription() %></p >
		<p><b>Price: </b> <%=newProduct.getPrice()%></p >
		<p><b>Rating: </b> <%=productService.getAverageRating(newProduct)%></p> 
		<p><b>Category: </b> <%=newProduct.getCategory()%></p> 
		<form method="POST" action="FrontServlet?command=Product">
		<Input type="submit" name=back value="Back" style="width:120px"></Input>
		</form>

<%} else if (request.getParameter("delete") != null) { 
	Product temporary = new Product(Long.parseLong(request.getParameter("delete")));
	productService.deleteProductbyId(temporary);
	
%>
			<h3>Product Deleted Successfully!</h3>
			<form method="POST" action="FrontServlet?command=Product">
			<Input type="submit" name=back value="Back" style="width:120px"></Input>
			</form>

<%} else if (request.getParameter("add") != null) {%>
	<h3>Add New Product</h3>
	<form method="POST" action="FrontServlet?command=Product">
	<div class="container">
		<label for="usr">Product Name:</label>
		<input type="text" name="name" required>
	</div>
	
	<div class="container">
		<label for="pwd">Description:</label>
		<input type="text" required name="description">
	</div>
	
	<div class="container">
		<label for="usr">Price:</label>
		<input type="number" required name="price" step = 0.01 min = 0>
	</div>
	
	<div class="container">
		<label for="usr">Category:</label>
		<select name="category" required>
			<option disabled="disabled">Choose a cateogry</option>
		    <option value="Makeup">Makeup</option>
		    <option value="Skincare">Skincare</option>
		    <option value="Hair">Hair</option>
		    <option value="Tools & Brushes">Tools & Brushes</option>
   	 		<option value="Fragrance">Fragrance</option>
   	 		<option value="Bath & Body">Bath & Body</option>
  		</select>
	</div>
	<p><b>Enter Up to 3 Values: </b></p>
	<div class="container">
		<label for="usr">Rating:</label>
		<input type="number" required name="rating" step = 0.01 min = 0 max=5>
	</div>
		<div class="container">
		<label for="usr">Rating:</label>
		<input type="number" required name="rating1" step = 0.01 min = 0 max=5>
	</div>
	
	<div class="container">
		<label for="usr">Rating:</label>
		<input type="number" required name="rating2" step = 0.01 min = 0 max=5>
	</div>
		
	<br>
	<button type="submit" name=back value="Back" style="width:120px">Back</button>
	<Input type="submit" name="create" style="width:120px" value="Create Product">
	</form>

<%} else if (request.getParameter("create") != null) {
	String name = request.getParameter("name");
	String description = request.getParameter("description");
	String category = request.getParameter("category");
	Float price = Float.parseFloat(request.getParameter("price"));
	Float rating1 = Float.parseFloat(request.getParameter("rating"));
	Float rating2 = Float.parseFloat(request.getParameter("rating1"));	
	Float rating3 = Float.parseFloat(request.getParameter("rating2"));
	ArrayList<Float> ratingList = new ArrayList<>();
	ratingList.add(rating1);
	ratingList.add(rating2);
	ratingList.add(rating3);
	
	Product newProduct = new Product(Product.getUniversalId(), name, category, description, price,ratingList);
	Product.increaseUniversalId();
	productService.createProduct(newProduct);
	%>
	
	<h3>Product Created Successfully!</h3>
		<form method="POST" action="FrontServlet?command=Product">
		<Input type="submit" name=back value="Back" style="width:120px"></Input>
		</form>

<%} %>
</body>
</html>