<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" isELIgnored="false"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>login page</title>
</head>
<body>
	<form action="login" method="post">
		${msg}<br>
		Username:<input type="text" name="username"/><br><br>
		Password:<input type="password" name="password"/><br><br>
		<input type="submit" value="login"/><br><br>
	</form>
</body>
</html>