<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
 pageEncoding="ISO-8859-1"%>
 <%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Customer Data</title>
<link rel="stylesheet"
href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
</head>
<body>
<h2 style="text-align:center;">Customer Details</h2>
<div class="container bg-light rounded pt-5 pb-5">
<table class="table table-bordered table-striped table-hover">
			<tr>
				<th>ID</th>
				<th>Customer Name</th>
				<th>Location</th>
				<th>Gender</th>
				<th>Account Type</th>
				<th>Balance</th>
			</tr>
				<tr>
					<td>${ cust.custId}</td>
					<td>${ cust.custName}</td>
					<td>${ cust.custLoc}</td>
					<td>${ cust.custGender}</td>
					<td>${ cust.custAccounttype}</td>
					<td>${ cust.custBalance}</td>
				</tr>
		</table>
		<br>
<a href="/bank/" class="btn btn-primary">Back to Home Page</a>&nbsp;&nbsp;&nbsp;&nbsp;
</div>
</body>
</html>