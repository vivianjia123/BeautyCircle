<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@page import="java.util.List"%>
<%@page import="service.*"%>
<%@page import="domain.*"%>
<%@page import="javax.servlet.*"%>
<%@page import="utility.*"%>

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
<% ProductService productService = new ProductService();
List<Product> products = productService.showAllAvailableProducts();
if (!products.isEmpty() && AppSession.isAuthenticated()) {%>
	<div class="row">
	<h3> Products: </h3>
	<form name="addtoCart" action="FrontServlet?command=Cart" method="POST" id="addtoCart"></form>
	<form name="addtowishlist" action="FrontServlet?command=Wishlist" method="POST" id="addtowishlist"></form>
	<table border='1'> <tr> <th>Name</th> <th>Description</th> <th>Price</th> <th>Rating</th> </tr>
	<%for (int i = 0; i < products.size(); i++) {
		Product product = products.get(i);%>
		<tr>
			<td><%= product.getName() %></td>
			<td><%= product.getDescription() %></td>
			<td><%= product.getPrice() %></td>
			<td><%= productService.getAverageRating(product) %></td>
			
			<%User currentUser = AppSession.getUser();
			RegisteredUser user = new RegisteredUser(currentUser.getId());
			ShoppingCart temporaryCart = new ShoppingCart(user);
			ShoppingCartService shoppingCartService = new ShoppingCartService();
			ShoppingCart foundCart = shoppingCartService.findByUserAndProductId(temporaryCart, product);
	
			Order temporaryOrder = new Order(user);
			OrderService orderService = new OrderService();
			temporaryOrder.setProducts(product);
			Order foundOrder = orderService.findwithUserAndProductId(temporaryOrder);%>
 			
			<%if (foundCart != null) {%> 
				<td>Item already in cart!</td>
			<%} else if (foundOrder != null) { %>
				<td>Item out of stock!</td>
			<%} else { %>
				<td>
				<Button type="submit" name='addtocart' form="addtoCart" value='<%= product.getId() %>'>Add to Cart</button>
				</td>
			<%}
			WishList wishList = new WishList(user);
			WishListService wishListService = new WishListService();
			WishList foundWishList = wishListService.findByUserAndProductId(wishList, product); %>
			<%if (foundWishList != null) { %>
				<td>Item already in wishlist!</td>
			<%} else if (foundCart != null) { %>
				<td>Cannot add item in wishlist!</td>
			<%} else { %>
				<td>
				<Button type="submit" name='addtowishlist' form="addtowishlist" value='<%= product.getId() %>'>Add to WishList</button>
				</td>
			<%} %>
		</tr>
	<%} %>
	
<%} %>
</table>
</div>
</body>
</html>