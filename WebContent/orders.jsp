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
<title>Orders</title>
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
<form id="order" method="POST" action="FrontServlet?command=Order"></form>

<% if(request.getParameter("order") == null && request.getParameter("edit") == null 
	&& request.getParameter("submit") == null) {%>
<h3>Existing Users: </h3>
<%UserService userService = new UserService();
List<User> users = userService.findUsers();
for (User user: users) {%>

	<%if (user.getUserType().equals("class domain.RegisteredUser")){ %>
	<p><b>UserId: </b> <%=user.getId() %></p >
	<p><b>First Name: </b> <%=user.getFirstName() %></p >
	<p><b>Last Name: </b> <%=user.getLastName() %></p >
	<p><b>Email Address: </b> <%=user.getEmailAddress()%></p >
	<p><b>Address: </b> <%=user.getAddress()%></p> 
	<Input type="hidden" name="firstname" form="order" value="<%=user.getFirstName()%>"></Input>
	<Input type="hidden" name="lastname" form="order" value="<%=user.getLastName()%>"></Input>
	
	<button type="submit" name="order" form = "order" style="width:120px" value=<%=user.getId()%>>View Orders</button>
	<hr>
	<%} %>
	
<%}}else if(request.getParameter("order") != null) {%>
	<h3>Orders for <%=request.getParameter("firstname") %> <%=request.getParameter("lastname") %>: </h3>
	<table border='1'> 
		
		
	<%
	long userid = Long.parseLong(request.getParameter("order"));
	RegisteredUser user = new RegisteredUser(userid);
	Order order = new Order(user);
	OrderService orderService= new OrderService();
	List<Order> orders = orderService.findWithUserId(order);
	
	ArrayList<Long> orderIdList = new ArrayList<>();
	
	for (Order eachOrder : orders) {
		if (!orderIdList.contains(eachOrder.getId())) {
			orderIdList.add(eachOrder.getId());
		}
	}
	
	for (Long orderId : orderIdList) {
		
		Order temporaryOrder = new Order(orderId);
		Order temporary = orderService.findwithOrderId(temporaryOrder);%>

				<tr><td colspan=3>
				<div class = fontStyle>
				<p><b> OrderId: </b><%=orderId %></p>
				</div></td></tr>

				<tr> <th>Name</th> <th>Description</th> <th>Price</th></tr>
		<%for (Product product : temporary.getProducts()) {%>

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
				
				<tr><td colspan=3>
				<div class = fontStyle>
				<p><b> Edit Order: </b><button type="submit" form="order" name=edit value=<%=orderId%> style="width:120px">Edit</button></p>
				<Input type="hidden" name="firstname" form="order" value="<%=request.getParameter("firstname")%>"></Input>
				<Input type="hidden" name="lastname" form="order" value="<%=request.getParameter("lastname")%>"></Input>
				</div></td></tr>
	
	<%} %>
	</table>
	<br>
	<form method="POST" action="FrontServlet?command=Order">
	<Input type="submit" name=back value="Back" style="width:120px"></Input></form>
<%} else if (request.getParameter("edit") != null) {
	long orderid = Long.parseLong(request.getParameter("edit"));
	Order order = new Order(orderid);
	OrderService orderService= new OrderService();
	Order foundOrder = orderService.findwithOrderId(order);%>
		
	<h3>Editing Order for <%=request.getParameter("firstname") %> <%=request.getParameter("lastname")%>: </h3>
		<table border='1'>
		<tr><td colspan=3>
		<div class = fontStyle>
		<p><b> OrderId: </b><%=orderid %></p>
		</div></td></tr>

		<tr> <th>Name</th> <th>Description</th> <th>Price</th></tr>
		<%for (Product product : foundOrder.getProducts()) {%>

			<tr><td><%=product.getName() %></td>
			<td><%=product.getDescription() %></td>
			<td><%=product.getPrice() %></td>
		<%} %>

		<tr><td colspan=3>
		<div class = fontStyle>
		<p><b> Order Placed at: </b><%=foundOrder.getTimeCreated().toString() %></p>
		</div></td></tr>
		
		<tr><td colspan=3>
		<div class = fontStyle>
		<p><b> Total Price: </b><%=foundOrder.getTotalPrice() %></p>
		</div></td></tr>
				
		<tr><td colspan=3>
		<div class="container">
		<p><b>Category:</b>
		<select name="orderStatus" form = "order" required>
			<option value=<%=foundOrder.getStatus() %> selected ><%=foundOrder.getStatus()%></option>
		    <option value=<%=Order.Status.PENDING%>>PENDING</option>
		    <option value=<%=Order.Status.PROCESSING%>>PROCESSING</option>
		    <option value=<%=Order.Status.DELIVERING%>>DELIVERING</option>
		    <option value=<%=Order.Status.COMPLETE%>>COMPLETE</option>
   	 		<option value=<%=Order.Status.CANCELLED%>>CANCELLED</option>
  		</select>
  		</p>
  		<button type="submit" form="order" name=submit value=<%=orderid%> style="width:120px">Submit</button>
		</div></td></tr>
		</table>
	<br>
	<form method="POST" action="FrontServlet?command=Order">
	<Input type="hidden" name="firstname" value="<%=request.getParameter("firstname")%>"></Input>
	<Input type="hidden" name="lastname" value="<%=request.getParameter("lastname")%>"></Input>
	<Button type="submit" name=order value=<%=orderid%> style="width:120px">Back</Button></form>

<%} else if (request.getParameter("submit") != null) { 
	long orderid = Long.parseLong(request.getParameter("submit"));
	Order order = new Order(orderid);
	OrderService orderService= new OrderService();
	Order foundOrder = orderService.findwithOrderId(order);
	
 	foundOrder.setStatus(request.getParameter("orderStatus"));
 	orderService.updateOrder(foundOrder);


%>
<h3>Order Successfully Updated!</h3>
	<form method="POST" action="FrontServlet?command=Order">
	<Button type="submit" name=back value=Back style="width:120px">Back</Button></form>

<%} %>
</body>
</html>