<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" isELIgnored="false"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Search</title>
</head>
<body>
	<h3>Enter username to search: </h3>
	<form action="getdetails" method="post">
		User Name:<input type="text" name="uname"/><br><br>
		<input type="submit" value="search"/><br><br>
		${msg}<br>
	</form>
</body>
</html>