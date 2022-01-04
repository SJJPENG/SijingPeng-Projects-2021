<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %> <%@
taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> <%@ taglib
uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %> <%@ page language="java"
contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
  <head>
    <meta charset="UTF-8" />
    <title>Login</title>
    <link rel="shortcut icon" href="favicon.ico" type="image/x-icon" />
    <link rel="stylesheet" href="fontawesome-free-5.12.1-web/css/all.min.css" />
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
          <li>
            <a href="home" class="nav-link">home</a>
          </li>
          <li>
            <a href="about" class="nav-link">about</a>
          </li>
          <li>
            <a href="findlistings" class="nav-link">listings</a>
          </li>
          <li>
            <a href="findusers" class="nav-link">login/sign up</a>
          </li>
        </ul>
      </div>
    </nav>

    <section class="section page-hero">
      <div class="section-center">
        <h2 class="page-hero-title">Login</h2>
      </div>
    </section>

    <section class="products">
      <!-- filters -->
      <div class="filters">
        <div class="filters-container">
          <form action="findusers" method="post">
            <p>
              <label for="username">Username</label>
              <input
                class="search-input"
                name="username"
                id="username"
                <%-- value="${fn:escapeXml(param.username)}" --%>
              />
            </p>
            <p>
              <label for="password">Password</label>
              <input
                class="search-input"
                type="password"
                name="password"
                id="password"
                <%-- value="${fn:escapeXml(param.password)}" --%>
              />
            </p>
            <p>
              <input type="submit" class="btn" value="login" />
              <br /><br /><br />
              <span id="successMessage"><b>${messages.success}</b></span>
            </p>
          </form>
          <br />
          <p>New User?</p>
          <a href="usercreate" class="btn">Sign up</a>
        </div>
      </div>

      <section class="user-profile">
        <c:forEach items="${users}" var="user">
          <div class="profile-title">
            <span></span>
            <h3>User Profile</h3>
            <span></span>
          </div>


          
          <div class="user-info">
              <p>Username:
                <span class="user-info-content">
                  <c:out value="${user.getUserName()}" />
                </span>
              </p>
              <p>Email:
                <span class="user-info-content">
                  <c:out value="${user.getEmail()}" />
                </span>
              </p>
              <p>password:
                <span class="user-info-content">
                  <c:out value="${user.getPassword()}" />
                </span>
              </p>
              <p>First Name:
                <span class="user-info-content">
                  <c:out value="${user.getFirstName()}" />
                </span>
              </p>
              <p>Last Name:
                <span class="user-info-content">
                  <c:out value="${user.getLastName()}" />
                </span>
              </p>
             
              <p><a class="btn" href="userdelete?username=<c:out value="${user.getUserName()}"/>">Delete Account</a></p>
              
              <p></p><a class="btn" href="userupdate?username=<c:out value="${user.getUserName()}"/>">Reset Password</a></p>
          </div>
          
        </c:forEach>
      </section>
    </section>
    <script src="js/app.js"></script>
  </body>
</html>
