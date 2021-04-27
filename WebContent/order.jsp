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
<title>Order</title>
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
	
<%	if(request.getParameterValues("purchaseditems")== null) { %>
		<h3> Order History: </h3>
	<table border='1'> <tr> <th>Name</th> <th>Description</th> <th>Price</th></tr>
		<%OrderService orderService = new OrderService();
		User currentUser = AppSession.getUser();
		RegisteredUser user = new RegisteredUser(currentUser.getId());
		Order order = new Order(user);

		List<Order> foundOrder = orderService.findWithUserId(order);
		ArrayList<Long> orderIdList = new ArrayList<>();
		
		for (Order eachOrder : foundOrder) {
			if (!orderIdList.contains(eachOrder.getId())) {
				orderIdList.add(eachOrder.getId());
			}
		}

		for (Long orderId : orderIdList) {
			Order temporaryOrder = new Order(orderId);
			Order temporary = orderService.findwithOrderId(temporaryOrder);

			for (Product product : temporary.getProducts()) {%>
				<tr><td><%=product.getName() %></td>
					<td><%=product.getDescription() %></td>
					<td><%=product.getPrice() %></td>
			<%} %>
				<tr><td colspan=3>
				<div class = fontStyle>
				<p><b> Order Placed at: </b><%=temporary.getTimeCreated().toString() %></p>
				</div></td></tr>
				
				<tr><td colspan=3>
				<div class = fontStyle>
				<p><b> Total Price: </b><%=temporary.getTotalPrice() %></p>
				</div></td></tr>
				
				<tr><td colspan=3>
				<div class = fontStyle>
				<p><b> Order Status: </b><%=temporary.getStatus() %></p>
				</div></td></tr>
		
		<%} %>
		</table>
		
<% } else if (request.getParameterValues("purchaseditems") != null) {
		ProductService productService = new ProductService();
		OrderService orderService = new OrderService();
		ShoppingCartService shoppingCartService = new ShoppingCartService();
		
		User currentUser = AppSession.getUser();
		RegisteredUser user = new RegisteredUser(currentUser.getId());
		
		String[] productIds = request.getParameterValues("purchaseditems");
		ArrayList<Product> productsPurchased = new ArrayList<>();
		
		
		if (productIds == null) { %>
			<h3> Please select a product to purchase! </h3>
		<%} else { %>
			<h3> Products purchased: </h3>
			<table border='1'> <tr> <th>Name</th> <th>Description</th> <th>Price</th></tr>
			
			<%for (int i = 0; i < productIds.length; i++) { 
				Product product = new Product(Long.parseLong(productIds[i]));

				Product foundProduct = productService.searchProductById(product);

				ShoppingCart shoppingCart = new ShoppingCart(user);
				shoppingCart.setProductMap(foundProduct, 1);

				ShoppingCart foundCart = shoppingCartService.findByUserAndProductId(shoppingCart, foundProduct);
				shoppingCartService.deleteFromCart(foundCart, foundProduct);

				productsPurchased.add(foundProduct);
			%>
			<tr><td><%=foundProduct.getName()%></td>
				<td><%=foundProduct.getDescription()%></td>
				<td><%=foundProduct.getPrice()%></td>
				<td><%=productService.getAverageRating(foundProduct)%></td>
			
			<%}
			Order order = new Order(orderService.getTotalPrice(productsPurchased), user, productsPurchased);
			orderService.createOrder(order);
			%>
			</tr></table>
	<%} %>
<%} %>	
</body>
</html>