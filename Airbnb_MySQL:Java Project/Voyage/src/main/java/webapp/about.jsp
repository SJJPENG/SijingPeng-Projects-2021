<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %> <%@
taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> <%@ taglib
uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %> <%@ page language="java"
contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
  <head>
    <meta charset="UTF-8" />
    <title>About</title>
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
    <section class="section about" id="about">
      <!-- title  -->
      <div class="section-title">
        <h2>about <span>us</span></h2>
      </div>
      <!-- end of title  -->
      <!-- about-center -->
      <div class="section-center about-center">
        <!-- about img wrapper-->
        <article class="about-img">
          <img
            src="./images/about.jpeg"
            class="about-photo"
            alt="awesome beach"
          />
        </article>
        <!-- about info -->
        <article class="about-info">
          <h3>Our Team</h3>
          <p>
            Huizhong Zuo, Jia Xu, Yiwei Xia, Chujun Gong, Sijing Peng, Yang Liu,
            Yuanyuan Wu
          </p>
          <br></br>
          <h3>Value</h3>
          <p>
            Voyage is a one-stop website for Airbnb travel planning that
            provides the tool to identify the best option for Airbnb seekers,
            who are not satisfied with the current search features on the Airbnb
            website, to plan trips based on their personalized criteria.
          </p>
        </article>
      </div>
    </section>
    <script src="js/app.js"></script>
  </body>
</html>
