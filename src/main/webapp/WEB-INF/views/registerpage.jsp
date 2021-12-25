<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
 pageEncoding="ISO-8859-1"%>
 <%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Customer Registration</title>
<link rel="stylesheet"
href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
</head>
<body>
<h2 style="text-align:center;">Register Customer</h2>
<div class="container bg-light rounded pt-5 pb-5">
<form:form action="saveForm" modelAttribute="cust" method="post">
<div class="form-group">
 <label for="custName">Customer Name:</label>
 <form:input path="custName" class="form-control"/>
</div>
<div class="form-group">
 <label for="custLoc">Location:</label>
 <form:input path="custLoc" class="form-control"/>
</div>
<div class="form-group">
 <label for="custGender">Gender:</label>
        Male <form:radiobutton path="custGender" value="Male"/>  
        Female <form:radiobutton path="custGender" value="Female"/> 
</div>
<div class="form-group">
 <label for="custAccounttype">Account Type:</label>
 <form:select path="custAccounttype">  
        <form:option value="Current" label="Current"/>  
        <form:option value="Savings" label="Savings"/>  
  </form:select>
<%--  <form:input path="custAccounttype" class="form-control"/> --%>
</div>
<div class="form-group">
 <label for="custBalance">Balance:</label>
 <form:input path="custBalance" class="form-control"/>
</div>
<button type="submit" class="btn btn-success">Submit</button>
</form:form>
</div>
</body>
</html>