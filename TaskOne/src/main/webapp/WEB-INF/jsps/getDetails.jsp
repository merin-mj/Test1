<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Details</title>
</head>
<body>
<h3>You search results: </h3>
      	Name: <c:out value="${person.getName()}" /><br>
		Age: <c:out value="${person.getAge()}" /><br>
		DOB: <c:out value="${person.getDob()}" /><br>
		Gender: <c:out value="${person.getGender()}" /><br>
</body>
</html>


<!-- 
Name: ${person.getName()} <br>
Age: ${person.getAge()} <br>
DOB: ${person.getDob()} <br>
Gender: ${person.getGender()} <br>

<c:if test = "${person != null}">
</c:if>


 -->
 

      
      
	