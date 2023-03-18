<%@ page import="com.example.demo.model.UserDto" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
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
                        </div>
                        <div style="display: flex;flex: 1;flex-direction: column;max-width: 50%">
                            <div style="display: flex;flex: 1;flex-direction: column;max-width: 70%;margin-bottom: 1rem;">
                                <label for="email">Email</label>
                                <input id="email" name="email" type="text" class="form-control" value="<%=userInfo.getEmail()%>"
                                       placeholder="Nhập email">
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