<%-- 
    Document   : login
    Created on : May 28, 2024, 11:37:38 AM
    Author     : khanhhoang
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <link rel="icon" href="images/logo/cosmetics.png" type="image/x-icon">
        <title>Login Page</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
    </head> 
    <body>
        <section class="py-3 py-md-5 py-xl-8">
            <div class="container">
                <div class="row">
                    <div class="col-12">
                        <div class="mb-5">
                            <h2 class="display-5 fw-bold text-center">Sign in</h2>
                            <p class="text-center m-0">Don't have an account? <a href="MainController?action=Create">Sign up</a></p>
                        </div>
                    </div>
                </div>
                <div class="row justify-content-center">
                    <div class="col-12 col-lg-10 col-xl-8">
                        <div class="row gy-5 justify-content-center">
                            <div class="col-12 col-lg-5">
                                <form id="login-form" action="MainController" method="POST">
                                    <div class="row gy-3 overflow-hidden">
                                        <div class="col-12">
                                            <div class="form-floating mb-3">
                                                <input type="text" class="form-control border-0 border-bottom rounded-0" name="userID" id="userID" placeholder="User ID" required>
                                                <label for="userID" class="form-label">User ID</label>
                                            </div>
                                        </div>
                                        <div class="col-12">
                                            <div class="form-floating mb-3">
                                                <input type="password" class="form-control border-0 border-bottom rounded-0" name="password" id="password" placeholder="Password" required>
                                                <label for="password" class="form-label">Password</label>
                                            </div>
                                        </div>
                                        <div class="col-12">
                                            <div class="row justify-content-between">
                                                <div class="col-12">
                                                    <div class="text-center">
                                                        <a href="MainController?action=ForgotPasswordPage" class="link-secondary text-decoration-none">Forgot password?</a>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="col-12">
                                            <div class="d-grid justify-content-center">
                                                <div class="g-recaptcha text-center" data-sitekey="6LdnbgoqAAAAAH2sKlnclhn2451gLgl8_ofdONY6"></div>
                                                <div style="color: red" id="error"></div>
                                            </div>
                                        </div>
                                        <div class="col-12">
                                            <div class="d-grid">
                                                <input type="hidden" name="action" value="Login">
                                                <button class="btn btn-primary btn-lg" onclick="checkCaptcha()" type="button">Log in</button>
                                            </div>
                                        </div>
                                        <div class="col-12">
                                            <h5 class="text-center">${requestScope.ERROR}</h5>
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
                                            <img style="width: 100%; height: auto" src="images/login/Login_Images.jpg" class="rounded img-fluid" alt="Cosmetics Images"/>
                                        </div>
                                        <div class="mb-2 text-center">
                                            <form action="MainController" method="POST">
                                                <button class="btn btn-success btn-lg" name="action" type="submit" value="Shopping">Shopping Now</button>
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
        <script src="https://www.google.com/recaptcha/api.js" async defer></script>
        <script type="text/javascript">
            function checkCaptcha() {
                var form = document.getElementById("login-form");
                var error = document.getElementById("error");
                const response = grecaptcha.getResponse();
                if (response) {
                    form.submit();
                } else {
                    error.textContent = "Please verify that you are not a robot.";
                }
            }
        </script>
    </body>
</html>
