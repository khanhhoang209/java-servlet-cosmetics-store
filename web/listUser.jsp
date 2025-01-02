<%-- 
    Document   : listUser
    Created on : Jul 7, 2024, 8:02:21 PM
    Author     : khanhhoang
--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>List User Page</title>
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
                    <c:set var="userError" value="${requestScope.USER_ERROR}" />
                    <div class="card  mt-5 mb-5">
                        <div class="card-header" style="color: white; background-color: palevioletred">
                            <div>
                                <form action="MainController" method="POST">
                                    <input style="width: 100%" class="px-2 search" type="text" name="userName" placeholder="Search By User Name"/>
                                    <div class="text-end">
                                        <input style="width: 20%" type="submit" name="action" value="SearchUser" />      
                                    </div>
                                </form> 
                            </div>
                        </div>
                        <div class="card-body">
                            <div class="table-responsive">
                                <table class="table table-bordered m-0">
                                    <thead>
                                        <tr>
                                            <c:out value="${userError.fullNameError}"/><br>
                                            <c:out value="${userError.emailError}"/>
                                            <th class="text-center align-middle py-3 px-4" style="width: 100px;">User ID</th>
                                            <th class="text-center align-middle py-3 px-4" style="width: 160px;">Full Name</th>
                                            <th class="text-center align-middle py-3 px-4" style="width: 100px;">Role ID</th>
                                            <th class="text-center align-middle py-3 px-4" style="width: 80px;">Password</th>
                                            <th class="text-center align-middle py-3 px-4" style="width: 100px;">Email</th> 
                                            <th class="text-center align-middle py-3 px-4" style="width: 100px;">Address</th>
                                            <th class="text-center align-middle py-3 px-4" style="width: 100px;">Delete</th>
                                            <th class="text-center align-middle py-3 px-0" style="width: 100px;">Edit</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <c:forEach items="${sessionScope.LIST_USER}" var="user" varStatus="loop">
                                        <form action="MainController" method="POST">
                                            <tr >
                                                <td class="text-center align-middle">
                                                    <input class="form-control border-0 border-bottom rounded-0 text-center" type="text" name="userID" value="${user.userID}" readonly=""/>
                                                </td>
                                                <td class="text-center align-middle">
                                                    <input class="form-control border-0 border-bottom rounded-0 text-center" type="text" name="fullName" value="${user.fullName}" required=""/>
                                                </td>
                                                <td class="text-center align-middle justify-content-center">
                                                    <select class="form-control form-select border-0 border-bottom rounded-0 text-center" id="roleID" name="roleID">
                                                        <option selected>${user.roleID}</option>
                                                        <option value="AD">AD</option>
                                                        <option value="US">US</option>
                                                    </select>

                                                </td>
                                                <td class="text-center align-middle">
                                                    <input class="form-control border-0 border-bottom rounded-0 text-center" type="text" name="password" value="${user.password}" readonly=""/>
                                                </td>
                                                <td class="text-center align-middle">
                                                    <input class="form-control border-0 border-bottom rounded-0 text-center" type="text" name="email" value="${user.email}" required=""/>
                                                </td> 
                                                <td class="text-center align-middle">
                                                    <input class="form-control border-0 border-bottom rounded-0 text-center" type="text"  name="address" value="${user.address}" required="">
                                                </td>
                                                <td class="text-center align-middle">
                                                    <input type="submit" name="action" value="Delete"/>
                                                </td>
                                                <td class="text-center align-middle">
                                                    <input type="submit" name="action" value="EditUser"/>
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
