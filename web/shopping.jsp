
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <link rel="icon" href="images/logo/cosmetics.png" type="image/x-icon">
        <title>Shopping Page</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
        <link rel="stylesheet" href="css/style.css">
        <link rel="preconnect" href="https://fonts.googleapis.com">
        <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
        <link href="https://fonts.googleapis.com/css2?family=Lobster&family=Rubik:ital,wght@0,300..900;1,300..900&display=swap" rel="stylesheet">
    </head>
    <body>
        <c:import url="header.jsp"></c:import>

            <section class="product">
                <div class="container px-3 my-5 clearfix">
                    <div class="row">
                        <div class="col-lg-3">
                            <h2 class="category">Categories</h2>
                            <ul class="category-list">
                                <li><a href="MainController?category=perfume&action=SearchProductByType" class="custom-link">Perfume</a></li>
                                <li><a href="MainController?category=lipstick&action=SearchProductByType" class="custom-link">Lipstick</a></li>
                                <li><a href="MainController?category=cleanser&action=SearchProductByType" class="custom-link">Cleanser</a></li>
                            </ul>
                        </div>
                        <div class="col-lg-9">
                            <div class="text-center">
                                <p class="fst-italic fw-bold">${requestScope.MESSAGE}</p>
                        </div>
                        <div class="row">
                            <c:if test="${not empty sessionScope.LIST_COSMETICS}">
                                <c:forEach items="${sessionScope.LIST_COSMETICS}" var="cosmetics" varStatus="loop">
                                    <c:if test="${cosmetics.quantity > 0}">
                                        <div class="col-lg-4 text-center">
                                            <form action="MainController" method="POST">
                                                <div class="card border-0 bg-light mb-2 mt-3">
                                                    <div class="card-body position-relative">
                                                        <img src="${cosmetics.image}" class="img-fluid" alt="">
                                                        <div class="details-container">
                                                            <details>
                                                                <summary>Information</summary>
                                                                <p>${cosmetics.describe}</p>
                                                            </details>
                                                        </div>
                                                        <button type="submit" name="action" value="Add" style="border: none; background: none; padding: 0;">
                                                            <img src="images/shopping/cart.png" class="img-fluid" style="width: 35px; margin-top: 15px; margin-bottom: 0px" alt="Add To Cart">  
                                                        </button>
                                                    </div>
                                                </div>
                                                <h6>
                                                    <label>
                                                        ${cosmetics.name}                                          
                                                        <input type="hidden" name="describe" value="${cosmetics.describe}">
                                                        <input type="hidden" name="image" value="${cosmetics.image}">
                                                        <input type="hidden" name="id" value="${cosmetics.id}">
                                                        <input type="hidden" name="productName" value="${cosmetics.name}">
                                                    </label>
                                                </h6>
                                                <p>
                                                    <label>
                                                        ${cosmetics.price}$
                                                        <input type="hidden" name="price" value="${cosmetics.price}">
                                                    </label>
                                                </p>
                                            </form>
                                        </div>
                                    </c:if>
                                </c:forEach>
                            </c:if>
                        </div>
                        <div class="text-center" style="margin-bottom: 25px">
                            <c:forEach begin="1" end="${END_PAGE}" var="pageNumber">
                                <c:choose>
                                    <c:when test="${requestScope.ACTION == 'Shopping'}">
                                        <a class="custom-link fw-bold" href="MainController?action=Shopping&pageNumber=${pageNumber}" style="padding: 10px">${pageNumber}</a>
                                    </c:when>
                                    <c:otherwise> 
                                        <a class="custom-link fw-bold" href="MainController?action=SearchProductByType&category=${requestScope.CATEGORY}&pageNumber=${pageNumber}" style="padding: 10px">${pageNumber}</a>
                                    </c:otherwise>
                                    
                                </c:choose>
                                
                            </c:forEach>
                        </div>

                    </div>
                </div>
            </div>
        </section>
        <c:import url="footer.jsp"></c:import>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>
    </body>
</html>
