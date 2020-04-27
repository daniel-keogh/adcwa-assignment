<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>ADCWA Final Project</title>
<link rel="stylesheet" href="/css/style.css">
</head>
<body>
	<h1>List of Orders</h1>

	<c:forEach items="${orders}" var="order">
		<h2>${order.oId}</h2>

		<table>
			<tr>
				<th>Quantity</th>
				<th>Order Date</th>
				<th>Customer ID</th>
				<th>Customer Name</th>
				<th>Product ID</th>
				<th>Description</th>
			</tr>
			<tr>
				<td>${order.qty}</td>
				<td>${order.orderDate}</td>
				<td>${order.cust.cId}</td>
				<td>${order.cust.cName}</td>
				<td>${order.prod.pId}</td>
				<td>${order.prod.pDesc}</td>
			</tr>
		</table>
	</c:forEach>

	<a href="/">Home</a>
	<a href="/newOrder.html">Add Order</a>
	<a href="/showProducts.html">List Products</a>
	<a href="/showCustomers.html">List Customers</a>
	<a href="/logout">Logout</a>
</body>
</html>
