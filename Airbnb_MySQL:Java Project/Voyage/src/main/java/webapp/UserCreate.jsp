<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@
taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="UTF-8" />
<title>Sign up</title>
<link rel="shortcut icon" href="favicon.ico" type="image/x-icon" />
<link rel="stylesheet"
	href="fontawesome-free-5.12.1-web/css/all.min.css" />
<link rel="stylesheet" href="css/styles.css" />
</head>
<body>
	<nav class="navbar">
	<div class="nav-center">
		<!-- nav header -->
		<div class="nav-header">
			<a href="home" class="nav-logo">Voyage</a>
			<button type="button" class="nav-toggle" id="nav-toggle">
				<i class="fas fa-bars"></i>
			</button>
		</div>
		<!-- nav-links -->
		<ul class="nav-links" id="nav-links">
			<li><a href="home" class="nav-link">home</a></li>
			<li><a href="about" class="nav-link">about</a></li>
			<li><a href="findlistings" class="nav-link">listings</a></li>
			<li><a href="findusers" class="nav-link">login/sign up</a></li>
		</ul>
	</div>
	</nav>

	<section class="section page-hero">
	<div class="section-center">
		<h2 class="page-hero-title">Sign up</h2>
	</div>
	</section>

	<div class="section-center signup">
		<form action="usercreate" method="post">
			<p>
				<label for="username">UserName</label> <input type="text"
					class="search-input" name="username" id="username"
					value="${fn:escapeXml(param.username)}" />
			</p>
			<p>
				<label for="password">Password</label> <input type="password"
					class="search-input" name="password" id="password" />
			</p>
			<p>
				<label for="email">Email</label> <input type="email"
					class="search-input" name="email" id="email"
					value="${fn:escapeXml(param.email)}" />
			</p>


			<p>
				<label for="firstname">FirstName</label> <input type="text"
					class="search-input" name="firstname" id="firstname"
					value="${fn:escapeXml(param.firstname)}" />
			</p>
			<p>
				<label for="lastname">LastName</label> <input type="text"
					class="search-input" name="lastname" id="lastname"
					value="${fn:escapeXml(param.lastname)}" />
			</p>

			<p>
				<input type="submit" class="btn" />
			</p>
		</form>
		<br />
		<br />
		<p>
			<span id="successMessage"><b>${messages.success}</b></span>
		</p>
	</div>
	<script src="js/app.js"></script>
</body>
</html>
