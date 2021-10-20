<!DOCTYPE html>
<html lang="en" xmlns:th="https://www.thymeleaf.org/">
<head>
	<title>Registration Confirmation</title>
</head>

<body>

	<h2>User registered successfully!</h2>

	<hr>
	
	<a href="/" th:href="@{/showMyLoginPage}">Login with new user</a>
	<!-- <a href="/" th:href="@{/login}">Login here</a> -->
</body>

</html>