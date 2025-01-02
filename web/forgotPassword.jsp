<%-- 
    Document   : forgotPassword
    Created on : Jul 8, 2024, 10:29:16 PM
    Author     : khanhhoang
--%>

<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <link rel="icon" href="images/logo/cosmetics.png" type="image/x-icon">
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Forgot Password Page</title>
    <link rel="stylesheet" href="css/cartStyle.css">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
    <style>
        .full-height {
            height: 100vh;
        }
    </style>
</head>
<body>
    <div class="container d-flex justify-content-center align-items-center full-height">
        <div class="row justify-content-center align-items-center text-center">
            <div class="col-lg-12 d-flex justify-content-center">
                <div class="card text-center" style="width: 300px;">
                    <div class="card-header h5 text-white" style="background-color:  palevioletred;">Forgot Password</div>
                    <div class="card-body px-5">
                        <p class="card-text py-2">
                            Enter your email address and we'll send you an email with your password.
                        </p>
                        <form action="MainController" method="POST">
                            <div data-mdb-input-init class="form-outline">
                                <input type="email" id="typeEmail" name="email" class="form-control my-3" required="" />
                                <label class="form-label" for="typeEmail">Email input</label>
                                
                            </div>
                            <button type="submit" name="action" value="ForgotPassword" style="background-color:  palevioletred; color: white;" class="btn w-100">Send Mail</button>
                            <h6 style="color: red; margin-top: 15px">${requestScope.MESSAGE}</h6>
                        </form>
                        <div class="d-flex justify-content-between mt-4">
                            <a class="custom-link" href="MainController?action=Login">Login</a>
                            <a class="custom-link" href="MainController?action=Create">Register</a>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-s3nNuqCG1ylPqeoBe9H1ZFEjFBEMyVuZL5tZqlPBzfw4xAWG/Khooj/l4L8MdcFQ" crossorigin="anonymous"></script>
</body>
</html>
