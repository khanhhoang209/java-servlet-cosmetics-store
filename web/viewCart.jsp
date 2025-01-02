<%-- 
    Document   : ViewCart
    Created on : Jun 14, 2024, 11:00:00 AM
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
        <title>Cart Page</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
        <link rel="stylesheet" href="css/cartStyle.css">
        <link rel="preconnect" href="https://fonts.googleapis.com">
        <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
        <link href="https://fonts.googleapis.com/css2?family=Lobster&family=Rubik:ital,wght@0,300..900;1,300..900&display=swap" rel="stylesheet">
    </head>
    <body>
        <c:import url="header.jsp"></c:import>
            <div class="container px-3 my-5 clearfix" >
                <!-- Shopping cart table -->
                <div class="card">
                    <div class="card-header">
                        <h2>Shopping Cart</h2>
                    </div>
                    <div class="card-body">
                        <form action="MainController" method="POST">
                        <div class="table-responsive">
                            <table class="table table-bordered m-0">
                                <thead>
                                    <tr>
                                        <!-- Set columns width -->
                                        <th class="text-center py-3 px-4" style="min-width: 400px;">Product Name &amp; Details</th>
                                        <th class="text-center py-3 px-4" style="width: 120px;">Price</th>
                                        <th class="text-center py-3 px-4" style="width: 120px;">Quantity</th>
                                        <th class="text-center py-3 px-4" style="width: 120px;">Total</th>
                                        <th class="text-center py-3 px-4" style="width: 130px;">Remove</th>
                                        <th class="text-center align-middle py-3 px-0" style="width: 130px;">Edit</th>
                                    </tr>
                                </thead>
                                <tbody>
                                <c:set var="total" value="0" />
                                <c:forEach items="${sessionScope.CART.cart}" var="cosmetics" varStatus="loop">
                                
                                    <tr >
                                        <td class="p-4">
                                            <div class="media align-items-center">
                                                <img src="${cosmetics.value.image}" class="d-block resize ui-bordered mr-4" alt="">
                                                <div class="media-body">
                                                    ${cosmetics.value.name}
                                                    <input type="hidden" name="productName" value="${cosmetics.value.name}"><br>
                                                    <small>
                                                        <p>${cosmetics.value.describe}</p>
                                                    </small>
                                                </div>
                                            </div>
                                        </td>
                                        <td class="text-center font-weight-semibold align-middle p-4">${cosmetics.value.price}</td>
                                        <td class=" text-center align-middle p-4"><input type="number" class="form-control text-center" name="quantity" value="${cosmetics.value.quantity}" min="1" required=""></td>
                                        <td class="text-center font-weight-semibold align-middle p-4">${cosmetics.value.price * cosmetics.value.quantity}</td>
                                        <td class="text-center align-middle">
                                            <input type="submit" name="action" value="Remove"/>
                                        </td>
                                        <td class="text-center align-middle">
                                            <input type="hidden" name="id" value="${cosmetics.value.id}"/>
                                            <input type="submit" name="action" value="Edit"/>
                                        </td>
                                    </tr>
                                
                                <c:set var="total" value="${total + (cosmetics.value.price * cosmetics.value.quantity)}" />
                                <c:set var="TOTAL" value="${total}" scope="session"/>
                            </c:forEach>

                            </tbody>
                        </table>
                    </div>
                    <!-- / Shopping cart table -->
                    <div class="d-flex justify-content-end mt-4">
                        <div>
                            <label class="text-muted font-weight-normal m-0">Total price</label>
                            <div class="text-center text-large"><strong>${total}$</strong></div>
                        </div>
                    </div>
                    <div class="d-flex justify-content-end mt-4">
                        <button type="button" class="btn btn-lg btn-default md-btn-flat mt-2 mr-3"><a class="custom-link" href="MainController?action=Shopping">Back to shopping</a></button>
                        <button type="button" class="btn btn-lg btn-primary mt-2"><a style="text-decoration: none; color: white" href="MainController?action=Confirm">Confirm</a></button>
                    </div>
                    </form>
                </div>
            </div>
        </div>
        <c:import url="footer.jsp"></c:import>
    </body>
</html>
