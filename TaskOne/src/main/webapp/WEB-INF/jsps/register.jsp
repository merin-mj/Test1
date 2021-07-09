<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<form action="regSubmit" method="post">
		Name:<input type="text" name="name"/><br><br>
		Age:<input type="number" name="age"/><br><br>
		Username:<input type="text" name="username"/><br><br>
		Password:<input type="password" name="password"/><br><br>
		Date Of Birth:<input type="date" name="dob"/><br><br>
		Gender:
		<input type="radio"  name="gender" value="male">Male</input>
		<input type="radio"  name="gender" value="female">Female</input>
		<input type="radio"  name="gender" value="others">Others</input><br><br>
		<input type="submit" value="submit"/><br><br>
	</form>
</body>
</html>