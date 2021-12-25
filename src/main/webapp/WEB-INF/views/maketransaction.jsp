<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
 pageEncoding="ISO-8859-1"%>
 <%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Make transaction</title>
<link rel="stylesheet"
href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
</head>
<body>
<h2 style="text-align:center;">Make Transaction</h2>
<div class="container bg-light rounded pt-5 pb-5">
<form:form action="maketrans" modelAttribute="trans" method="post">
<div class="form-group">
 <label for="fromCustId">From Customer Id:</label>
 <form:input path="fromCustId" class="form-control"/>
</div>
<div class="form-group">
 <label for="toCustId">To Customer Id:</label>
 <form:input path="toCustId" class="form-control"/>
</div>
<div class="form-group">
 <label for="amount">Amount:</label>
 <form:input path="amount" class="form-control"/>
</div>
<button type="submit" class="btn btn-success">Send Money</button>
</form:form>
</div>
</body>
</html>