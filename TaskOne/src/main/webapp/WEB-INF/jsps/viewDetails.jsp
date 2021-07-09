<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" isELIgnored="false"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

<!--  
Name: ${name} <br>
Age: ${age} <br>
User name: ${username} <br>
dob: ${dob} <br>
Gender : ${gender} <br>
-->
	<form action="getdetails" method="post">
		User Name:<input type="text" name="uname"/><br><br>
		<input type="submit" value="search"/><br><br>
	</form>
</body>
</html>