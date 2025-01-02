<%-- 
    Document   : confirmation
    Created on : Jun 20, 2024, 10:56:58 PM
    Author     : khanhhoang
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" %>
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
        <title>Confirm Page</title>
    </head>
    <body>
        <c:if test="${sessionScope.LOGIN_USER.roleID ne 'US'|| sessionScope.LOGIN_USER == null}">
            <c:redirect url="home.jsp"/>
        </c:if>
        <c:import url="header.jsp"></c:import>
            <div class="container">
                <div class="row">
                    <div class="col-lg-8">
                        <div class="card">
                            <div class="card-header text-center">
                                <h2>Confirm Information</h2>
                            </div>
                            <div class="card-body">
                                <form action="MainController" method="POST">
                                    <div class="mb-2">
                                        <label for="fullName" class="form-label">Full Name</label>
                                        <input type="text" class="form-control" id="fullName" name="fullName" value="${sessionScope.LOGIN_USER.fullName}" required="">
                                    </div>
                                    <div class="mb-2">
                                        <label for="email" class="form-label">Email</label>
                                        <input type="email" class="form-control" id="email" name="email" value="${sessionScope.LOGIN_USER.email}" required="">
                                    </div>
                                    <div class="mb-2">
                                        <label for="phone" class="form-label">Phone Number</label>
                                        <input type="text" class="form-control" id="phone" name="phoneNumber" value="${sessionScope.PHONE_NUMBER}" required="">
                                    </div>
                                    <div class="mb-2">
                                        <label for="address" class="form-label">Address</label>
                                        <input type="address" class="form-control" id="address" name="address" value="${sessionScope.LOGIN_USER.address}" required="">
                                    </div>
                                    <div class="mt-3">
                                        <input type="submit" name="action" value="Checkout"/>        
                                    </div>
                                        
                                </form>
                            </div>
                        </div>

                    </div>

                <div class="col-lg-4">
                    <div class="card">
                        <div class="card-header text-center">
                            <h2>Billing Detail</h2>
                        </div>
                        <div class="card-body">
                            <div class="table-responsive">
                                <table class="table table-bordered m-0" border="1">
                                    <thead>
                                        <tr>
                                            <th>No</th>
                                            <th>Name</th>
                                            <th>Price</th>
                                            <th>Quantity</th>
                                            <th>Total</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <c:forEach items="${sessionScope.CART.cart}" var="cosmetics" varStatus="loop">
                                        <form action="MainController" method="POST">
                                            <tr>
                                                <td>${loop.index + 1}</td> 
                                            <td>${cosmetics.value.name}</td>
                                            <td>${cosmetics.value.price}$</td>
                                            <td>${cosmetics.value.quantity}</td>
                                            <td>${cosmetics.value.price * cosmetics.value.quantity}$</td>
                                            </tr>
                                        </form>

                                    </c:forEach>
                                    </tbody>
                                </table>
                                <div style="text-align: right; margin-top: 20px;">
                                    <h5 class="fw-bold" style="color: green;">Total: ${sessionScope.TOTAL}$</h5>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div style="text-align: center; margin-top: 20px">
                        <h3 class="fw-bold" style="color: palevioletred">${sessionScope.MESSAGE}</h3>
                    </div>
                </div>
            </div>
        </div>
        <c:import url="footer.jsp"></c:import>
    </body>
</html>
