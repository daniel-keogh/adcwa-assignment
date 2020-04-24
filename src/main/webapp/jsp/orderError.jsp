<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>ADCWA Final Project</title>
</head>
<body>
	<h1>Error Creating the following Order</h1>

	<h2>${error.message}</h2>

	<table>
		<tr>
			<th>Product ID</th>
			<th>Customer ID</th>
			<th>Quantity</th>
		</tr>
		<tr>
			<td>${order.prod.pId}</td>
			<td>${order.cust.cId}</td>
			<td>${order.qty}</td>
		</tr>
	</table>

	<a href="/">Home</a>
	<a href="/newOrder.html">New Order</a>
	<a href="/showOrders.html">List Orders</a>
</body>
</html>