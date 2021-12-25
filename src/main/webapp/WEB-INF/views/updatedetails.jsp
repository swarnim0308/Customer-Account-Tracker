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
<h2 style="text-align:center;">Update Customer</h2>
<div class="container bg-light rounded pt-5 pb-5">
<form:form action="saveForm" modelAttribute="cust" method="post">
<div class="form-group">
 <label for="custId">Customer Name:</label>
 <form:input path="custId" class="form-control" value="${cust.custId}" />
</div>
<div class="form-group">
 <label for="custName">Customer Name:</label>
 <form:input path="custName" class="form-control" value="${cust.custName}" />
</div>
<div class="form-group">
 <label for="custLoc">Location:</label>
 <form:input path="custLoc" class="form-control" value="${cust.custLoc}"/>
</div>
<div class="form-group">
 <label for="custGender">Gender:</label>
         Male <form:radiobutton path="custGender" value="Male"/>  
        Female <form:radiobutton path="custGender" value="Female"/>
<%--  <form:input path="custGender" class="form-control" value="${cust.custGender}"/> --%>
</div>
<div class="form-group">
 <label for="custAccounttype">Account Type:</label>
  <form:select path="custAccounttype" class="form-control">  
        <form:option value="Current" label="Current"/>  
        <form:option value="Savings" label="Savings"/>  
  </form:select>
<%--  <form:input path="custAccounttype" class="form-control" value="${cust.custAccounttype}"/> --%>
</div>
<div class="form-group">
 <label for="custBalance">Balance:</label>
 <form:input path="custBalance" class="form-control" value="${cust.custBalance}"/>
</div>
<button type="submit" class="btn btn-success">Submit</button>
</form:form>
</div>
</body>
</html>