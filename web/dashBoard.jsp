<%-- 
    Document   : dashBoard.jsp
    Created on : Jul 6, 2024, 9:29:04 PM
    Author     : khanhhoang
--%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="icon" href="images/logo/cosmetics.png" type="image/x-icon">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
        <link rel="stylesheet" href="css/cartStyle.css">
        <link rel="preconnect" href="https://fonts.googleapis.com">
        <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
        <link href="https://fonts.googleapis.com/css2?family=Lobster&family=Rubik:ital,wght@0,300..900;1,300..900&display=swap" rel="stylesheet">
        <title>Dash Board</title>
    </head>
    <body>
        <c:if test="${sessionScope.LOGIN_USER.roleID ne 'AD'|| sessionScope.LOGIN_USER == null}">
            <c:redirect url="home.jsp"/>
        </c:if>
        <c:import url="header.jsp"></c:import>
            <div class="container">
                <div class="row">
                    <div class="col-lg-3 mt-1">
                        <h2 class="category">Management</h2>
                        <ul class="category-list">
                            <li><a class="custom-link" href="admin.jsp">Admin</a></li>
                            <li><a class="custom-link" href="MainController?action=Dashboard">Dashboard</a></li>
                            <li><a class="custom-link" href="MainController?action=ListProductPage">Product</a></li>
                            <li><a class="custom-link" href="MainController?action=AddProductPage">Add Product</a></li>
                            <li><a class="custom-link" href="MainController?action=ListUserPage">User</a></li>
                            <li><a class="custom-link" href="MainController?action=AddUserPage">Add User</a></li>
                        </ul>
                        <h3 class="text-warning">${requestScope.MESSAGE}</h3>
                </div>
                <div class="col-lg-9">
                    <div class="card mt-5 mb-5">
                        <div class="card-header" style="color: white; background-color: palevioletred">
                            <h2 class="text-center" >Dash Board</h2>
                        </div>
                        <div class="card-body"> 
                            <div class="row">
                                <div class="col-lg-6">
                                    <div class="text-center  mt-4 mb-4">
                                        <img src="images/dashboard/sale.png" width="100px">
                                        <h5>Sale: <c:out value="${sessionScope.SALE}"/></h5>
                                    </div>
                                    <div class="text-center  mt-4 mb-4">
                                        <img src="images/dashboard/user.png" width="100px">
                                        <h5>Customer: <c:out value="${sessionScope.NUMBER_OF_CUSTOMER}"/></h5>
                                    </div>
                                </div>
                                <div class="col-lg-6">
                                    <div class="text-center mt-4 mb-4">
                                        <img src="images/dashboard/cosmetics.png" width="100px">
                                        <h5>Product: <c:out value="${sessionScope.NUMBER_OF_PRODUCT}"/></h5>
                                    </div>
                                    <div class="text-center mt-4 mb-4">
                                        <img src="images/dashboard/revenue.png" width="100px">
                                        <h5>Revenue: <c:out value="${sessionScope.TOTAL_PROCEED}$"/></h5>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <c:import url="footer.jsp"></c:import>
    </body>
</html>
