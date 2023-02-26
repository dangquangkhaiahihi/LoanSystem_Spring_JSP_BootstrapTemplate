<%@ page import="com.example.demo.model.UserDto" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="vi">

<head>

    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">

    <!-- Custom fonts for this template-->
    <link href="../vendor/fontawesome-free/css/all.min.css" rel="stylesheet" type="text/css">
    <link
            href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i"
            rel="stylesheet">

    <!-- Custom styles for this template-->
    <link href="../css/sb-admin-2.min.css" rel="stylesheet">

</head>

<body>
<!-- User Detail Modal -->
<div class="modal fade" id="userDetail" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel"
     aria-hidden="true">
    <div class="modal-dialog" role="document" style="max-width: 70%">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="exampleModalLabel">Thông tin tài khoản.</h5>
                <button class="close" type="button" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">×</span>
                </button>
            </div>
            <form method="POST" action="/update-profile" modelAttribute="userDto">
            <div class="modal-body">

                    <div style="display: flex; flex-direction: row">
                        <div style="display: flex;flex: 1;flex-direction: column;max-width: 50%;">
                            <div style="display: flex;flex: 1;flex-direction: column;max-width: 70%;margin-bottom: 1rem;">
                                <label for="name">Tên khách hàng</label>
                                <input id="name" name="name" type="text" class="form-control" value="<%=userInfo.getName()%>"
                                       placeholder="Nhập tên"/>
                            </div>
                            <div style="display: flex;flex: 1;flex-direction: column;max-width: 70%;margin-bottom: 1rem;">
                                <label>Tên đăng nhập</label>
                                <input type="text" class="form-control" value="<%=userInfo.getUsername()%>" readonly>
                            </div>
                            <div style="display: flex;flex: 1;flex-direction: column;max-width: 70%;margin-bottom: 1rem;">
                                <label>CMT/CCCD</label>
                                <input type="text" class="form-control" value="<%=userInfo.getCccdNum()%>" readonly>
                            </div>
                            <div style="display: flex;flex: 1;flex-direction: column;max-width: 70%;margin-bottom: 1rem;">
                                <label>Giới tính</label>
                                <% if (userInfo.isGender()) { %>
                                <input type="text" class="form-control" value="Nam" readonly>
                                <% } else { %>
                                <input type="text" class="form-control" value="Nữ" readonly>
                                <% } %>
                            </div>
                        </div>
                        <div style="display: flex;flex: 1;flex-direction: column;max-width: 50%">
                            <div style="display: flex;flex: 1;flex-direction: column;max-width: 70%;margin-bottom: 1rem;">
                                <label for="email">Email</label>
                                <input id="email" name="email" type="text" class="form-control" value="<%=userInfo.getEmail()%>"
                                       placeholder="Nhập email">
                            </div>
                            <div style="display: flex;flex: 1;flex-direction: column;max-width: 70%;margin-bottom: 1rem;">
                                <label for="phone">Số điện thoại</label>
                                <input id="phone" name="phone" type="text" class="form-control" value="<%=userInfo.getPhone()%>"
                                       placeholder="Nhập số điện thoại">
                            </div>
                            <div style="display: flex;flex: 1;flex-direction: column;max-width: 70%;margin-bottom: 1rem;">
                                <label>Điểm uy tín</label>
                                <input type="text" class="form-control" value="<%=userInfo.getCreditPoint()%>" readonly>
                            </div>
                            <div style="display: flex;flex: 1;flex-direction: column;max-width: 70%;margin-bottom: 1rem;">
                                <label>Số dư tài khoản</label>
                                <input type="text" class="form-control" value="<%=userInfo.getBalance()%>" readonly>
                            </div>
                        </div>
                    </div>

            </div>
            <div class="modal-footer">
                <button class="btn btn-secondary" type="button" data-dismiss="modal">Đóng</button>
                <input class="btn btn-primary" type="submit" value="Cập nhật"/>
            </div></form>
        </div>
    </div>
</div>

</body>

</html>