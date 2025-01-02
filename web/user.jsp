<%-- 
    Document   : user
    Created on : May 28, 2024, 11:40:15 AM
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
        <title>User Page</title>
    </head>
    <body>
        <c:if test="${sessionScope.LOGIN_USER.roleID ne 'US' || sessionScope.LOGIN_USER == null}">
            <c:redirect url="home.jsp"/>
        </c:if>
        <c:import url="header.jsp"></c:import>
            <div class="container mt-3 mb-3">
                <div class="row align-items-center justify-content-center">
                    <div class="col-lg-8 ">
                        <div class="card">
                            <div class="card-header text-large fw-bold" style="color: white; background-color: palevioletred">
                                <h2>User Profile Detail</h2>
                            </div>
                            <c:set var="userError" value="${requestScope.USER_ERROR}" />
                            <div class="card-body">
                                <form action="MainController" method="POST">
                                    <div class="row gy-3 overflow-hidden">
                                        <div class="form-floating">
                                            <label class="form-label fw-bold" for="userID">User ID</label><br>
                                            <input class="form-control border-0 border-bottom rounded-0" placeholder="User ID" type="text" id="userID" name="userID" value="${sessionScope.LOGIN_USER.userID}" readonly=""/>
                                        </div>             
                                        <div>
                                            <label class="form-label fw-bold" for="fullName">Full Name</label><br>
                                            <input class="form-control border-0 border-bottom rounded-0" placeholder="Full Name" type="text" id="fullName" name="fullName" value="${sessionScope.LOGIN_USER.fullName}" required=""/><c:out value="${userError.fullNameError}"/>
                                        </div>
                                        <div>
                                            <label class="form-label fw-bold" for="email">Email</label><br>
                                            <input class="form-control border-0 border-bottom rounded-0" placeholder="Email" type="text" id="email" name="email" value="${sessionScope.LOGIN_USER.email}" required=""/><c:out value="${userError.emailError}"/>
                                        </div>
                                        <div>
                                            <label class="form-label fw-bold" for="address">Address</label><br>
                                            <input class="form-control border-0 border-bottom rounded-0" placeholder="Address" type="text" id="address" name="address" value="${sessionScope.LOGIN_USER.address}" required=""/>
                                        </div>
                                        <div>
                                            <label class="form-label fw-bold" for="password">Password</label><br>
                                            <input class="form-control border-0 border-bottom rounded-0" placeholder="Password" type="text" id="password" name="password" value="${sessionScope.LOGIN_USER.password}" required=""/>
                                        </div>
                                        <div>
                                            <label class="form-label fw-bold" for="cofirm-password">Confirm Password</label><br>
                                            <input class="form-control border-0 border-bottom rounded-0" placeholder="Confirm Password" type="text" id="confirm-password" name="confirmPassword" value="${sessionScope.LOGIN_USER.password}" required=""/><c:out value="${userError.confirmError}"/>
                                        </div>
                                        <div class="row mt-3 mb-3">
                                            <div class="col-lg-6 text-center">
                                                <input type="submit" name="action" value="Update" style="width: 90%"/>
                                            </div>
                                            <div class="col-lg-6 text-center">
                                                <input type="submit" name="action" value="Logout" style="width: 90%"/>
                                            </div>
                                        </div>
                                </div>
                                </form>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        <c:import url="footer.jsp"></c:import>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>
    </body>
</html>
