<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %> <%@
taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> <%@ taglib
uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %> <%@ page language="java"
contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
  <head>
    <meta charset="UTF-8" />
    <title>Listings</title>
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
        <h2 class="page-hero-title">Listings</h2>
      </div>
    </section>

    <section class="products">
      <!-- filters -->
      <div class="filters">
        <div class="filters-container">
          <!-- search -->
          <form class="input-form" action="findlistings" method="post">
            <input
              type="text"
              class="search-input"
              placeholder="keywords..."
              name="listingname"
              value="${fn:escapeXml(param.listingname)}"
            />
            <br />
            <input type="submit" class="btn submit-btn" value="Search" />
          </form>
        </div>
      </div>
      <!-- listings -->
      <div class="products-container">
        <c:forEach items="${listingsWithMinPrices}" var="lstWithMP">
          <!-- single listing -->

          <article class="listing-card">
            <div class="listing-img-container">
              <a
                href="<c:out value='${lstWithMP.getListing().getListingURL()}'/>"
              >
                <img
                  class="listing-img"
                  src="<c:out value='${lstWithMP.getListing().getPictureURL()}' />"
                  alt=""
                />
              </a>
              <p class="listing-roomtype">
                <c:out value="${lstWithMP.getListing().getRoomType()}" />
              </p>
            </div>

            <div class="listing-footer">
              <p class="listing-title">
                <a
                  href="hostlistings?hostid=<c:out value='${lstWithMP.getListing().getHosts().getHostId()}'/>"
                >
                  <img
                    class="host-img"
                    src="${lstWithMP.getListing().getHosts().getThumbnailURL()}"
                    alt=""
                  />
                </a>
                <span
                  ><c:out value="${lstWithMP.getListing().getName()}"
                /></span>
              </p>

              <div class="listing-info">
                <p class="listing-nbhood">
                  <span><i class="fas fa-map"></i></span>
                  <c:out
                    value="${lstWithMP.getListing().getNeighborhoods().getName()}"
                  />
                </p>

                <div class="listing-details">
                  <p>
                    ⭐
                    <c:out value="${lstWithMP.getListing().getReviewScore()}" />
                  </p>
                  <p>
                    From $<fmt:formatNumber
                      value="${lstWithMP.getMinPrice()}"
                      minFractionDigits="0"
                      maxFractionDigits="0"
                    />
                  </p>
                </div>
              </div>
            </div>
          </article>
          <!-- end of single listing -->
        </c:forEach>
      </div>
    </section>
    <script src="js/app.js"></script>
  </body>
</html>
