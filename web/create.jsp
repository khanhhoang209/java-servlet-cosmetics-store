<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="icon" href="images/logo/cosmetics.png" type="image/x-icon">
        <title>Create Page</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">

    </head>
    <body>
        <section class="py-3 py-md-5 py-xl-8">
            <div class="container">
                <div class="row">
                    <div class="col-12">
                        <div class="mb-5">
                            <h2 class="display-5 fw-bold text-center">Sign up</h2>
                            <p class="text-center m-0">We happy to have you join</p>
                        </div>
                    </div>
                </div>
                <c:set var="userError" value="${requestScope.USER_ERROR}" />
                <div class="row justify-content-center">
                    <div class="col-12 col-lg-10 col-xl-8">
                        <div class="row gy-5 justify-content-center">
                            <div class="col-12 col-lg-5">
                                <form action="MainController" method="POST">
                                    <div class="row gy-3 overflow-hidden">
                                        <div class="col-12">
                                            <div class="form-floating mb-0">
                                                <input type="text" class="form-control border-0 border-bottom rounded-0" name="userID" id="userID" placeholder="User ID" required=""><c:out value="${userError.userIDError}" />
                                                <label for="userID" class="form-label">User ID</label>
                                            </div>
                                        </div>
                                        <div class="col-12">
                                            <div class="form-floating mb-0">
                                                <input type="text" class="form-control border-0 border-bottom rounded-0" name="fullName" id="fullName" placeholder="Full Name" required=""><c:out value="${userError.fullNameError}" />
                                                <label for="fullName" class="form-label">Full Name</label>
                                                <input type="hidden" name="roleID" value="US" readonly=""/>
                                            </div>
                                        </div>
                                        <div class="col-12">
                                            <div class="form-floating mb-0">
                                                <input type="text" class="form-control border-0 border-bottom rounded-0" name="email" id="email" placeholder="Email" required=""><c:out value="${userError.emailError}"/>
                                                <label for="email" class="form-label">Email</label>
                                            </div>
                                        </div>
                                        <div class="col-12">
                                            <div class="form-floating mb-0">
                                                <input type="text" class="form-control border-0 border-bottom rounded-0" name="address" id="address" placeholder="Address" required="">
                                                <label for="Address" class="form-label">Address</label>
                                            </div>
                                        </div>
                                        <div class="col-12">
                                            <div class="form-floating mb-0">
                                                <input type="password" class="form-control border-0 border-bottom rounded-0" name="password" id="password" value="" placeholder="Password" required="">
                                                <label for="password" class="form-label">Password</label>
                                            </div>
                                        </div>
                                        <div class="col-12">
                                            <div class="form-floating mb-0">
                                                <input type="password" class="form-control border-0 border-bottom rounded-0" name="confirm" id="confirm" value="" placeholder="Confirm Password" required=""><c:out value="${userError.confirmError}" />
                                                <label for="confirm" class="form-label">Confirm Password</label>
                                            </div>
                                        </div>
                                        <div class="col-12">
                                            <div class="d-grid">
                                                <button class="btn btn-primary btn-lg" name="action" type="submit" value="Create">Register</button>
                                            </div>
                                        </div>
                                        <div class="col-12">
                                            <h5 class="text-center">${userError.error}</h5>
                                        </div>
                                    </div>
                                </form>
                            </div>
                            <div class="col-12 col-lg-2 d-flex align-items-center justify-content-center gap-3 flex-lg-column">
                                <div class="bg-dark h-100 d-none d-lg-block" style="width: 1px; --bs-bg-opacity: .1;"></div>
                                <div class="bg-dark w-100 d-lg-none" style="height: 1px; --bs-bg-opacity: .1;"></div>
                                <div>or</div>
                                <div class="bg-dark h-100 d-none d-lg-block" style="width: 1px; --bs-bg-opacity: .1;"></div>
                                <div class="bg-dark w-100 d-lg-none" style="height: 1px; --bs-bg-opacity: .1;"></div>
                            </div>
                            <div class="col-12 col-lg-5 d-flex align-items-center">
                                <div class="row">
                                    <div class="col-12">
                                        <div class="mb-2 text-center">
                                            <img style="" src="images/login/306403-P8WRAM-415.jpg" class="rounded img-fluid" alt="Cosmetics Images"/>
                                        </div>
                                        <div class="mb-2 text-center">
                                            <form action="MainController" method="POST">
                                                <button class="btn btn-primary btn-lg" name="action" type="submit" value="Login">Login</button>
                                            </form>  
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </section>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>
    </body>
</html>
