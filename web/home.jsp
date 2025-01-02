<%-- 
    Document   : home.jsp
    Created on : Jul 3, 2024, 11:05:49 PM
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
        <title>Home Page</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
        <link rel="stylesheet" href="css/style.css">
        <link rel="preconnect" href="https://fonts.googleapis.com">
        <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
        <link href="https://fonts.googleapis.com/css2?family=Lobster&family=Rubik:ital,wght@0,300..900;1,300..900&display=swap" rel="stylesheet">
    </head>
    <body>
        <c:import url="header.jsp"></c:import>
                
        <section class="main">
            <div class="container">
                <div class="row">
                    <div class="col-lg-12">
                        <h1 class="text-center">"The Love of beauty is taste. The creation of beauty is art"</h1>
                    </div>
                </div>
            </div>
        </section>

        <section class="new">
            <div class="container py-5">
                <div class="row pt-5">
                    <div class="col-lg-7 m-auto">
                        <div class="row text-center">
                            <div class="col-lg-4">
                                <img src="images/home/cleanser.png" class="img-fluid" alt="cleanser">
                                <h6 style="padding-top: 10px">CLEANSER</h6>
                            </div>
                            <div class="col-lg-4">
                                <img src="images/home/lipstick.png" class="img-fluid" alt="lipstick">
                                <h6 style="padding-top: 10px">LIPSTICK</h6>
                            </div>
                            <div class="col-lg-4">
                                <img src="images/home/perfume.png" class="img-fluid" alt="perfume">
                                <h6 style="padding-top: 10px">PERFUME</h6>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </section>
        
        <section class="product">
            <div class="container-fluid">
                <div class="row py-5">
                    <div class="col-lg-5 m-auto text-center">
                        <h1>Variety Of Perfumes</h1>
                    </div>
                </div>
                <div class="row">
                    <div class="col-lg-3 text-center">
                        <div class="card border-0 bg-light">
                            <div class="card-body">
                                <img src="images/home/perfume1.jpg" class="img-fluid" alt="">
                            </div>
                        </div>
                        <h6 style="margin-top: 15px;">Narciso Rodriguez Ambrée</h6>
                    </div>
                    <div class="col-lg-3 text-center">
                        <div class="card border-0 bg-light">
                            <div class="card-body">
                                <img src="images/home/perfume2.jpg" class="img-fluid" alt="">
                            </div>
                        </div>
                        <h6 style="margin-top: 15px;">Narciso Rodriguez EDP</h6>
                    </div>
                    <div class="col-lg-3 text-center">
                        <div class="card border-0 bg-light">
                            <div class="card-body">
                                <img src="images/home/perfume3.jpg" class="img-fluid" alt="">
                            </div>
                        </div>
                        <h6 style="margin-top: 15px;">Narciso Rodriguez Pure Musc</h6>
                    </div>
                    <div class="col-lg-3 text-center">
                        <div class="card border-0 bg-light">
                            <div class="card-body">
                                <img src="images/home/perfume4.jpg" class="img-fluid" alt="">
                            </div>
                        </div>
                        <h6 style="margin-top: 15px;">Narciso Rodriguez EDT</h6>
                    </div>
                </div>
                <div class="row">
                    <div class="col-lg-6 text-center m-auto mt-4 mb-4">
                        <button class="btn1"><a href="MainController?action=Shopping">Click For More</a></button>
                    </div>
                </div>
            </div>
        </section>
        <c:import url="footer.jsp"></c:import>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>
    </body>
</html>
