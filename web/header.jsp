<%-- 
    Document   : header
    Created on : Jul 4, 2024, 2:06:22 AM
    Author     : khanhhoang
--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="icon" href="images/logo/cosmetics.png" type="image/x-icon">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
        <link rel="stylesheet" href="css/style.css">
        <link rel="preconnect" href="https://fonts.googleapis.com">
        <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
        <link href="https://fonts.googleapis.com/css2?family=Lobster&family=Rubik:ital,wght@0,300..900;1,300..900&display=swap" rel="stylesheet">
        <title>Header</title>
    </head>
    <body>
        <nav class="navbar navbar-expand-lg">
            <div class="container">
                <a class="navbar-brand" href="home.jsp">Cosmetics</a>
                <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
                    <span class="navbar-toggler-icon"></span>
                </button>
                <div class="collapse navbar-collapse" id="navbarSupportedContent">
                    <ul class="navbar-nav m-auto mb-2 mb-lg-0">                  
                        <li class="nav-item">
                            <a class="nav-link" href="home.jsp">HOME</a>
                        </li>
                        <li class="nav-item">
                            <a  class="nav-link" href="MainController?action=Shopping">SHOP</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="MainController?action=View">CART</a>
                        </li>
                        <li class="nav-item">
                            <c:choose>
                                <c:when test="${sessionScope.LOGIN_USER.roleID == 'US'}">
                                    <a class="nav-link" href="user.jsp">USER</a>
                                </c:when>
                                <c:when test="${sessionScope.LOGIN_USER.roleID == 'AD'}">
                                    <a class="nav-link" href="admin.jsp">ADMIN</a>
                                </c:when>
                                <c:otherwise>
                                    <a class="nav-link" href="MainController?action=Login">LOGIN</a>
                                </c:otherwise>
                            </c:choose>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="MainController?action=Logout">LOGOUT</a>
                        </li>
                    </ul>
                    <form class="d-flex" action="MainController" method="POST">
                        <input class="px-2 search" type="search" placeholder="Search Product" aria-label="Search" name="productName">
                        <button class="btn0" type="submit" name="action" value="SearchProduct">Search</button>
                    </form>
                </div>
            </div>
        </nav>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>
    </body>
</html>
