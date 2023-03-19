<%@ page import="com.example.demo.service.dto.UserDto" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!-- Logout Modal-->
<div class="modal fade" id="changePassword" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel"
     aria-hidden="true">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="exampleModalLabel">Đổi mật khẩu</h5>
                <button class="close" type="button" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">×</span>
                </button>
            </div>
            <form method="POST" action="/change-pass" modelAttribute="changePassDto">
                <div class="modal-body">
                    <div style="display: flex;flex-direction: column;">
                        <div style="display: flex;flex: 1;flex-direction: column;max-width: 100%;margin-bottom: 1rem;">
                            <label for="oldPass">Mật khẩu cũ</label>
                            <input id="oldPass" name="oldPass" type="password" class="form-control" value=""
                                   placeholder="Nhập mật khẩu cũ"/>
                        </div>
                        <div style="display: flex;flex: 1;flex-direction: column;max-width: 100%;margin-bottom: 1rem;">
                            <label for="newPass">Mật khẩu mới</label>
                            <input id="newPass" name="newPass" type="password" class="form-control" value=""
                                   placeholder="Nhập mật khẩu mới">
                        </div>
                        <div style="display: flex;flex: 1;flex-direction: column;max-width: 100%;margin-bottom: 1rem;">
                            <label for="reNewPass">Nhập lại mật khẩu mới</label>
                            <input id="reNewPass" name="reNewPass" type="password" class="form-control" value=""
                                   placeholder="Nhập lại mật khẩu mới">
                        </div>
                    </div>

                </div>
                <div class="modal-footer">
                    <button class="btn btn-secondary" type="button" data-dismiss="modal">Hủy</button>
                    <input class="btn btn-primary" type="submit" value="Xác nhận"/>
                </div>
            </form>
        </div>
    </div>
</div>