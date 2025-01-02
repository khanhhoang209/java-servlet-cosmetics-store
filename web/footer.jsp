<%-- 
    Document   : footer
    Created on : Jul 4, 2024, 9:06:01 PM
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
        <title>Footer</title>
    </head>
    <body>
        <footer class="text-white pt-5 pb-4" style="background-color: palevioletred">
            <div class="container text-md-left">
                <div class="row text-md-left">
                    <div class="col-lg-3 col-md-3 col-xl-3 mx-auto mt-3">
                        <h5 class="text-uppercase mb-4 font-weight-bold" style="color: black">Cosmetics Store</h5>
                        <p>This company was established with aims to pass PRJ301 - HoaDNT!</p>
                    </div>
                    <div class="col-lg-3 col-md-3 col-xl-3 mx-auto mt-3">
                        <h5 class="text-uppercase mb-4 font-weight-bold" style="color: black">Hot links</h5>             
                        <p>
                            <a href="MainController?action=Home" style="text-decoration: none;" class="text-white">Home</a>
                        </p>
                        <p>
                            <a href="MainController?action=Shopping" style="text-decoration: none;" class="text-white">Shop</a>
                        </p>
                        <p>
                            <a href="MainController?action=View" style="text-decoration: none;" class="text-white">Cart</a>
                        </p>
                        <p>
                            <a href="https://shopee.vn/" target="_blank" style="text-decoration: none;" class="text-white">Supplier</a>
                        </p>
                    </div>
                    <div class="col-lg-4 col-md-4 col-xl-4 mx-auto mt-3">
                        <h5 class="text-uppercase mb-4 font-weight-bold" style="color: black">Contact</h5>
                        <p>
                            <i class="fas fa-home mr-3"></i> E2a-7, D. D1, Long Thanh My, Thanh Pho Thu Duc, Ho Chi Minh
                        </p>
                        <p>
                            <i class="fas fa-envelope mr-3"></i> lehoangkhanh209@gmail.com
                        </p>
                        <p>
                            <i class="fas fa-phone mr-3"></i> 028 3733 0188
                        </p>
                    </div>
                </div>
                <hr class="mb-3">
                <div class="row align-items-center">
                    <div class="col-lg-8 col-md-7">
                        <p> Copyright @2024 All rights reserved by:
                            <a href="https://www.facebook.com/khanh.khanhhoang.209" target="_blank" style="text-decoration: none;">
                                <strong class="text-uppercase font-weight-bold text-warning">Khanh Hoang</strong>
                            </a>
                        </p>
                    </div>
                </div>
            </div>
        </footer>
    </body>
</html>
