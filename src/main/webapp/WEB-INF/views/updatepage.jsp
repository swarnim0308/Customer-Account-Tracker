<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
 pageEncoding="ISO-8859-1"%>
 <%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Update Id</title>
<link rel="stylesheet"
href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
</head>
<body>
<h2 class="text-center">Update Data</h2>
<div class="container bg-light rounded pt-5 pb-5">
<form:form action="update" modelAttribute="cust" method="post">
<div class="form-group">
 <label for="custId">Enter Customer Id for Updation:</label>
 <form:input path="custId" class="form-control"/>
</div>
<button type="submit" class="btn btn-success">Submit</button>
</form:form>
</div>
</body>
</html>