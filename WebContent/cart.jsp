<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@page import="java.util.*"%>
<%@page import="service.*"%>
<%@page import="domain.*"%>
<%@page import="javax.servlet.*"%>
<%@page import="utility.*"%>
<html>
<head>
<meta charset="UTF-8">
<title>Cart</title>
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

<%if (request.getParameter("addtocart") == null && request.getParameter("delete") == null) {%>
<h3> Cart: </h3>
<form id="deleteFromCart" method="POST" action="FrontServlet?command=Cart"></form>
<form id="purchase" method="POST" action="FrontServlet?command=Order"></form>
<table border='1'> <tr> <th>Name</th> <th>Description</th> <th>Price</th></tr>
<% 		ShoppingCartService shoppingCartService = new ShoppingCartService();
		User currentUser = AppSession.getUser();
		RegisteredUser user = new RegisteredUser(currentUser.getId());
		ShoppingCart shoppingCart = new ShoppingCart(user);
		List<ShoppingCart> foundCart = shoppingCartService.findByUserId(shoppingCart);
		if (!foundCart.isEmpty()) {
			for (ShoppingCart cart : foundCart) {
				HashMap<Product, Integer> productMap = cart.getProductMap();
				if (productMap != null) {
					for (Product p : productMap.keySet()) {%>

						<tr><td> <%=p.getName()%> </td> 
							<td> <%=p.getDescription()%> </td>
							<td> <%= p.getPrice()%>  </td>
							<td><input type="checkbox" name="purchaseditems" form="purchase" value=<%=p.getId()%>></td>
							<td><Button type="submit" name='delete' form ="deleteFromCart" value=<%=p.getId()%>>Delete</button></td>
					<% }%>

				<% }%>
		<% }%>
			
	<% }%>
	<tr>
	<td colspan="5">
	<input type="submit" name="submit" form='purchase' style=float:right></input></td></tr></table>
<%} else if (request.getParameter("addtocart") != null) {%>
<h3> Added to Cart: </h3>
<table border='1'> <tr> <th>Name</th> <th>Description</th> <th>Price</th></tr>
<% 		String productId = request.getParameter("addtocart");
		Product product = new Product(Long.parseLong(productId));
		ProductService productService = new ProductService();
		ShoppingCartService shoppingCartService = new ShoppingCartService();
		Product foundProduct = productService.searchProductById(product);
		
		User currentUser = AppSession.getUser();
		RegisteredUser user = new RegisteredUser(currentUser.getId());
		
		ShoppingCart shoppingCart = new ShoppingCart(user);

		shoppingCart.setProductMap(foundProduct, 1);
		shoppingCartService.createCart(shoppingCart);
		
		%>
		
		<tr><td><%=foundProduct.getName() %></td>
			<td><%=foundProduct.getDescription() %></td>
			<td><%=foundProduct.getPrice()%></td></tr>
		</table>


<%} else if (request.getParameter("delete") != null) {%>
<h3> Deleted from Cart: </h3>
<table border='1'> <tr> <th>Name</th> <th>Description</th> <th>Price</th></tr>
<% 		String productId = request.getParameter("delete");
		Product product = new Product(Long.parseLong(productId));
		ProductService productService = new ProductService();
		ShoppingCartService shoppingCartService = new ShoppingCartService();
		Product foundProduct = productService.searchProductById(product);
		
		User currentUser = AppSession.getUser();
		RegisteredUser user = new RegisteredUser(currentUser.getId());
		
		ShoppingCart shoppingCart = new ShoppingCart(user);

		shoppingCartService.deleteFromCart(shoppingCart, foundProduct);
		
		%>
		
		<tr><td><%=foundProduct.getName() %></td>
			<td><%=foundProduct.getDescription() %></td>
			<td><%=foundProduct.getPrice()%></td></tr>
		</table>

<% }%>




</body>
</html>