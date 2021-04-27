<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@page import="java.util.ArrayList"%>
<%@page import="service.*"%>
<%@page import="domain.*"%>
<%@page import="javax.servlet.*"%>
<%@page import="utility.*"%>
<html>
<head>
<meta charset="UTF-8">
<title>WishList</title>
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

<%if (request.getParameter("addtowishlist") == null && request.getParameter("delete") == null) {%>
<h3> WishList: </h3>
<form method="POST" action="FrontServlet?command=Cart" id="addtoCart"></form>
<form method="POST" action="FrontServlet?command=Wishlist" id="deletefromWishList"></form>
<table border='1'> <tr> <th>Name</th> <th>Description</th> <th>Price</th></tr>
	<%WishListService wishListService = new WishListService();
	User currentUser = AppSession.getUser();
	RegisteredUser user = new RegisteredUser(currentUser.getId());
	WishList wishList = new WishList(user);
	ShoppingCart shoppingCart = new ShoppingCart(user);
	ShoppingCartService shoppingCartService = new ShoppingCartService();

	WishList foundwishList = wishListService.findByUserId(wishList);

if (wishListService.findByUserId(wishList) != null) {
	ArrayList<Product> productMap = foundwishList.getWishlist();
	 OrderService orderService = new OrderService();
	 
	for (Product product : productMap) {
		ShoppingCart foundCart = shoppingCartService.findByUserAndProductId(shoppingCart, product);
		Order temporaryOrder = new Order(user);
		temporaryOrder.setProducts(product);
		Order foundOrder = orderService.findwithUserAndProductId(temporaryOrder);
		%>
		<tr>
		<%if (foundCart == null && foundOrder == null) { %>
			
				<td><%= product.getName() %></td> 
				<td><%= product.getDescription() %></td> 
				<td><%= product.getPrice() %></td> 
				<td><Button type="submit" name='addtocart' form='addtoCart' style="width:80px" value=<%= product.getId() %>>Add to Cart</button></td>
			
		<%} else { %>
			
				<td><%= product.getName() %></td> 
				<td><%= product.getDescription() %></td> 
				<td><%= product.getPrice() %></td> 
		<%} %>
		<td><Button type="submit" name='delete' form ='deletefromWishList' style="width:80px" value=<%= product.getId() %>>Delete</button></td></tr>
		
	<%} %>
<%} %>
</table>
<%} else if (request.getParameter("addtowishlist") != null) { %>
	<h3> Product added to wishlist: </h3>
	<form action="FrontServlet?command=Cart" method="POST" id="addtoCart"></form>
	<table border='1'> <tr> <th>Name</th> <th>Description</th> <th>Price</th> </tr>

	<% String productId = request.getParameter("addtowishlist");
	   Product product = new Product(Long.parseLong(productId));
	   ProductService productService = new ProductService();
	   WishListService wishListService = new WishListService();
	   Product foundProduct = productService.searchProductById(product);
	   User currentUser = AppSession.getUser();
	   RegisteredUser user = new RegisteredUser(currentUser.getId());
	   WishList wishList = new WishList(user);
	   wishList.setWishlist(foundProduct);
	   wishListService.createWishList(wishList);
	   
	   Order temporaryOrder = new Order(user);
	   OrderService orderService = new OrderService();
	   temporaryOrder.setProducts(product);
	   Order foundOrder = orderService.findwithUserAndProductId(temporaryOrder);%>
	   
	   <tr>
	   <td><%=foundProduct.getName()%></td>
	   <td><%=foundProduct.getDescription()%></td>
	   <td><%=foundProduct.getPrice()%></td>

		<%if (foundOrder == null) {%>
			<td><Button type=submit name='addtocart' form="addtoCart" value=<%=foundProduct.getId()%>>Add to Cart</button></td>
		<%} %>
		</tr>
	</table>

<%} else if (request.getParameter("delete") != null) { %>
	<h3> Product deleted from Wishlist:</h3>
	<table border='1'> <tr> <th>Name</th> <th>Description</th> <th>Price</th> </tr>

	<% String productId = request.getParameter("delete");
	   Product product = new Product(Long.parseLong(productId));
	   ProductService productService = new ProductService();
	   WishListService wishListService = new WishListService();
	   Product foundProduct = productService.searchProductById(product);
	   User currentUser = AppSession.getUser();
	   RegisteredUser user = new RegisteredUser(currentUser.getId());
	   
	   WishList wishList = new WishList(user);
	   wishListService.DeleteFromWishList(wishList, foundProduct);%>
	   
	   <tr>
	   <td><%=foundProduct.getName()%></td>
	   <td><%=foundProduct.getDescription()%></td>
	   <td><%=foundProduct.getPrice()%></td>
	   </tr>
	</table>
<%} %>
</body>
</html>