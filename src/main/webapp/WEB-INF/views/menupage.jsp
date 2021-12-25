<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page isELIgnored="false"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
<meta charset="ISO-8859-1">
<title>Menu Page</title>
</head>
<body>
	<b style="color: green;">${msg}</b>
	<center>
		<h2>Welcome to Bank</h2>
		<br>
		<a href="customerregister" class="btn btn-primary"> Insert a
			Customer </a> &nbsp; &nbsp; &nbsp; 
		<a href="updateForm" class="btn btn-primary"> Update Customer
		</a> &nbsp; &nbsp; &nbsp; <a href="deleteForm" class="btn btn-primary">
			Delete Customer </a> &nbsp; &nbsp; &nbsp;
			<a href="retrieveForm" class="btn btn-primary">Retrieve Customer By Id</a>&nbsp;&nbsp;&nbsp;&nbsp; <a href="makeTransac"
			class="btn btn-primary"> Make Transaction </a> <br /> <br />
	</center>
	<c:if test="${list.size()> 0}">
		<center>
			<h2>Customers List</h2>
		</center>
		<table class="table table-bordered table-striped table-hover">
			<tr>
				<th>ID</th>
				<th>Customer Name</th>
				<th>Location</th>
				<th>Gender</th>
				<th>Account Type</th>
				<th>Balance</th>
			</tr>
			<c:forEach var="cust" items="${list}">
				<tr>
					<td>${ cust.custId}</td>
					<td>${ cust.custName}</td>
					<td>${ cust.custLoc}</td>
					<td>${ cust.custGender}</td>
					<td>${ cust.custAccounttype}</td>
					<td>${ cust.custBalance}</td>
				</tr>
			</c:forEach>
		</table>
	</c:if>
	<b style="color: red;">${msg2}</b>
</body>
</html>