<%-- 
    Document   : addProduct
    Created on : Jul 7, 2024, 2:45:28 PM
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
        <title>Add Product Page</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
        <link rel="stylesheet" href="css/cartStyle.css">
        <link rel="preconnect" href="https://fonts.googleapis.com">
        <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
        <link href="https://fonts.googleapis.com/css2?family=Lobster&family=Rubik:ital,wght@0,300..900;1,300..900&display=swap" rel="stylesheet">
    </head>
    <body>
        <c:if test="${sessionScope.LOGIN_USER.roleID ne 'AD' || sessionScope.LOGIN_USER == null}">
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
                    <c:set var="productError" value="${requestScope.PRODUCT_ERROR}" />
                    <div class="card mt-5 mb-5">
                        <div class="card-header " style="color: white; background-color: palevioletred">
                            <h2 class="text-center" >Add New Product</h2>
                        </div>
                        <div class="card-body">
                            <form action="MainController" method="POST">
                                <div class="row">
                                    <div class="col-lg-8">
                                        <div class="row gy-5 overflow-hidden">
                                            <div class="col-12">
                                                <div class="form-floating mb-0">
                                                    <input type="text" class="form-control border-0 border-bottom rounded-0" name="productID" id="productID" placeholder="ID" required=""><c:out value="${productError.productIDError}"/>
                                                    <label class="form-label fw-bold" for="productID">Product ID</label>
                                                </div>
                                            </div>
                                            <div class="col-12">
                                                <div class="form-floating mb-0">
                                                    <input type="text" class="form-control border-0 border-bottom rounded-0" name="productName" id="productName" placeholder="Product Name" required=""><c:out value="${productError.nameError}"/>
                                                    <label class="form-label fw-bold" for="productName">Product Name</label>
                                                </div>
                                            </div>
                                            <div class="col-12">
                                                <div class="form-floating mb-0">
                                                    <select class="form-select border-0 border-bottom rounded-0 fw-bold" id="category" name="category">
                                                        <option selected>Category</option>
                                                        <option value="Cleanser">Cleanser</option>
                                                        <option value="Lipstick">Lipstick</option>
                                                        <option value="Perfume">Perfume</option>
                                                    </select><c:out value="${productError.categoryError}"/>
                                                </div>
                                            </div>
                                            <div class="col-12">
                                                <div class="form-floating mb-0">
                                                    <input type="number" class="form-control border-0 border-bottom rounded-0" name="price" id="price" min="1" placeholder="Price" required=""><c:out value="${productError.priceError}"/>
                                                    <label class="form-label fw-bold" for="Price">Price ($)</label>
                                                </div>
                                            </div>
                                            <div class="col-12">
                                                <div class="form-floating mb-0">
                                                    <input type="number" class="form-control border-0 border-bottom rounded-0" name="quantity" id="quantity" min="1" placeholder="Quantity" required=""><c:out value="${productError.quantityError}"/>
                                                    <label class="form-label fw-bold" for="quantity">Quantity</label>
                                                </div>
                                            </div>
                                            <div class="col-12">
                                                <div class="form-floating mb-0">
                                                    <input type="text" class="form-control border-0 border-bottom rounded-0" name="describe" id="describe" placeholder="Describe" required="">
                                                    <label class="form-label fw-bold" for="describe">Describe</label>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="col-lg-4">
                                        <div class="col-12">
                                            <div class="form-floating mb-0">
                                                <input type="file" class="form-control border-0 border-bottom rounded-0" name="image" id="image" accept="image/png, image/jpeg, image/jpg" placeholder="Image" required="">
                                            </div>
                                            <h6 class="text-warning text-center">(Accept only .jpg . jpeg .png)</h6>
                                        </div>
                                    </div>
                                </div>
                                <div class="mt-3" style="width: 100%">
                                    <input class="text-center" type="submit" value="AddProduct" name="action"/>
                                </div>
                            </form>
                        </div>
                    </div>      
                </div>
            </div>
        </div>
        <c:import url="footer.jsp"></c:import>
    </body>
</html>
