<%-- 
    Document   : listProduct
    Created on : Jul 6, 2024, 10:28:13 PM
    Author     : khanhhoang
--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>List Product Page</title>
        <link rel="icon" href="images/logo/cosmetics.png" type="image/x-icon">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
        <link rel="stylesheet" href="css/cartStyle.css">
        <link rel="preconnect" href="https://fonts.googleapis.com">
        <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
        <link href="https://fonts.googleapis.com/css2?family=Lobster&family=Rubik:ital,wght@0,300..900;1,300..900&display=swap" rel="stylesheet">
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
                    <c:set var="productError" value="${requestScope.PRODUCT_ERROR}" />
                    <div class="card  mt-5 mb-5">
                        <div class="card-header" style="color: white; background-color: palevioletred">
                            <div>
                                <form action="MainController" method="POST">
                                    <input style="width: 100%" class="px-2 search" type="text" name="productName" placeholder="Search By Product Name"/>
                                    <div class="text-end">
                                        <input style="width: 20%" type="submit" name="action" value="SearchProduct" />      
                                    </div>
                                </form> 
                            </div>
                        </div>
                        <div class="card-body">
                            <div class="table-responsive">
                                <table class="table table-bordered m-0">
                                    <thead>
                                        <tr>
                                            <c:out value="${productError.nameError}"/><br>
                                            <th class="text-center py-3 px-4" style="width: 120px;">Image</th>
                                            <th class="text-center py-3 px-4" style="width: 120px;">ID</th>
                                            <th class="text-center py-3 px-4" style="width: 100px;">Name</th>
                                            <th class="text-center py-3 px-4" style="width: 100px;">Price</th>
                                            <th class="text-center py-3 px-4" style="width: 100px;">Quantity</th>
                                            <th class="text-center py-3 px-4" style="width: 100px;">Remove</th>
                                            <th class="text-center align-middle py-3 px-0" style="width: 100px;">Edit</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <c:forEach items="${sessionScope.LIST_COSMETICS}" var="cosmetics" varStatus="loop">
                                        <form action="MainController" method="GET">
                                            <tr>
                                                <td class="p-4">
                                                    <div class="media align-items-center">
                                                        <img src="${cosmetics.image}" class="d-block resize-2 ui-bordered mr-4" alt="">
                                                    </div>
                                                </td>
                                                <td class="text-center align-middle">
                                                    <input class="form-control border-0 border-bottom rounded-0 text-center" type="text" name="id" value="${cosmetics.id}" readonly=""/>
                                                </td>
                                                <td class="text-center align-middle">
                                                    <input class="form-control border-0 border-bottom rounded-0 text-center" type="text" name="productName" value="${cosmetics.name}" required=""/> 
                                                </td>
                                                <td class="text-center align-middle">
                                                    <input class="form-control border-0 border-bottom rounded-0 text-center" type="number" name="price" value="${cosmetics.price}" required=""/>
                                                </td>
                                                <td class="text-center align-middle">
                                                    <input class="form-control border-0 border-bottom rounded-0 text-center" type="number"  name="quantity" value="${cosmetics.quantity}" min="1" required="">
                                                </td>
                                                <td class="text-center align-middle">
                                                    <input type="submit" name="action" value="RemoveProduct"/>
                                                </td>
                                                <td class="text-center align-middle">
                                                    <input type="submit" name="action" value="EditProduct"/>
                                                </td>
                                            </tr>
                                        </form>
                                    </c:forEach>
                                    </tbody>
                                </table>
                            </div>
                        </div>
                    </div>  
                </div>
            </div>
        </div>
        <c:import url="footer.jsp"></c:import>
    </body>
</html>
