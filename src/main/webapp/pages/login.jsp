<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="utf-8"/>
    <meta http-equiv="X-UA-Compatible" content="IE=edge"/>
    <meta
            name="viewport"
            content="width=device-width, initial-scale=1, shrink-to-fit=no"
    />
    <meta name="description" content=""/>
    <meta name="author" content=""/>

    <title>SB Admin 2 - Login</title>

    <!-- Custom fonts for this template-->
    <!--<link-->
    <!--        href="../vendor/fontawesome-free/css/all.min.css"-->
    <!--        rel="stylesheet"-->
    <!--        type="text/css"-->
    <!--/>-->
    <link-->
            href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i"
            rel="stylesheet"
    />

    <!-- Custom styles for this template-->
    <link href="../css/sb-admin-2.min.css" rel="stylesheet"/>
</head>

<body class="bg-gradient-primary">
<div class="container">
    <!-- Outer Row -->
    <%String loginError = (String) request.getAttribute("login-error");%>
    <div class="row justify-content-center">
        <div class="col-xl-10 col-lg-12 col-md-9">
            <div class="card o-hidden border-0 shadow-lg my-5">
                <div class="card-body p-0">
                    <!-- Nested Row within Card Body -->
                    <div class="row">
                        <div class="col-lg-6 d-none d-lg-block bg-login-image"></div>
                        <div class="col-lg-6">
                            <div class="p-5">
                                <div class="text-center">
                                    <h1 class="h4 text-gray-900 mb-4">Xin chào!</h1>
                                </div>
                                <form class="user" action = "/login" method = 'POST'>
                                    <div class="form-group">
                                        <input
                                                type="text"
                                                class="form-control form-control-user"
                                                id="exampleInputEmail"
                                                aria-describedby="emailHelp"
                                                placeholder="Tên Đăng nhập"
                                                name="username"
                                        />
                                    </div>
                                    <div class="form-group">
                                        <input
                                                type="password"
                                                class="form-control form-control-user"
                                                id="exampleInputPassword"
                                                placeholder="Mật khẩu"
                                                name = "password"
                                        />
                                    </div>
                                    <div class="form-group">
                                        <div class="col-md-6 mb-3">
                                            <img alt="captcha" src="/captcha-servlet">
                                        </div>
                                            <input class="form-control form-control-user"
                                            path="captcha" placeholder="Enter Captcha" name = "captcha" required="true"/>
                                    </div>
                                    <%if(loginError != null){%>
                                        <p style="color: red">${loginError}</p>
                                    <%}%>
                                    <p style="color: red">${SPRING_SECURITY_LAST_EXCEPTION.message}</p>

                                    <button
                                            type="submit"
                                            class="btn btn-primary btn-user btn-block"
                                    >
                                        Đăng nhập
                                    </button>
                                </form>
                                <hr/>
                                <div class="text-center">
                                    <a class="small" href="/register-page" data-toggle="modal" data-target="#register-modal">
                                        Tạo tài khoản!
                                    </a>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

<!-- Bootstrap core JavaScript-->
<script src="/home"></script>
<!--<script src="../vendor/bootstrap/js/bootstrap.bundle.min.js"></script>-->

<!-- Core plugin JavaScript-->
<!--<script src="../vendor/jquery-easing/jquery.easing.min.js"></script>-->

<!-- Custom scripts for all pages-->
<!--<script src="../js/sb-admin-2.min.js"></script>-->
</body>
</html>
