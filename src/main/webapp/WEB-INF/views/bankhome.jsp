<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
 pageEncoding="ISO-8859-1"%>
 <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
 <%@ page isELIgnored="false" %>
<!DOCTYPE html>
<html>
<head>
<style>
a:hover {
  background-color: green;
}
</style>
<meta charset="ISO-8859-1">
<title>Home</title>
<link rel="stylesheet"
href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
</head>
<body>
<br/>
<center>
<h2 class="container bg-light pt-5 pb-5 border border-primary rounded">Welcome to Bank</h2>
<br><br>
<a href="customerregister" class="btn btn-primary">Insert a Customer</a>
&nbsp;&nbsp;&nbsp;&nbsp;
<a href="deleteForm" class="btn btn-primary">Delete Customer</a>&nbsp;&nbsp;&nbsp;&nbsp;
<a href="updateForm" class="btn btn-primary">Update Customer</a>&nbsp;&nbsp;&nbsp;&nbsp;
<a href="retrieveForm" class="btn btn-primary">Retrieve Customer By Id</a>&nbsp;&nbsp;&nbsp;&nbsp;
<a href="makeTransac" class="btn btn-primary">Make Transaction</a><br/><br/>
</center>
</body>
</html>