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
            <div class="modal-body">
                    <form id="form-update-profile">
                    <div style="display: flex; flex-direction: row">
                        <div style="display: flex;flex: 1;flex-direction: column;max-width: 50%;">
                            <div style="display: flex;flex: 1;flex-direction: column;max-width: 70%;margin-bottom: 1rem;">
                                <label for="name-user-profile">Tên khách hàng</label>
                                <input id="name-user-profile" name="name" type="text" class="form-control" value="<%=userInfo.getName()%>"
                                       placeholder="Nhập tên"/>
                            </div>
                            <div style="display: flex;flex: 1;flex-direction: column;max-width: 70%;margin-bottom: 1rem;">
                                <label>Tên đăng nhập</label>
                                <input type="text" class="form-control" value="<%=userInfo.getUsername()%>" readonly>
                            </div>
                        </div>
                        <div style="display: flex;flex: 1;flex-direction: column;max-width: 50%">
                            <div style="display: flex;flex: 1;flex-direction: column;max-width: 70%;margin-bottom: 1rem;">
                                <label for="email-user-profile">Email</label>
                                <input id="email-user-profile" name="email" type="text" class="form-control" value="<%=userInfo.getEmail()%>"
                                       placeholder="Nhập email">
                            </div>
                        </div>
                    </div>
                    </form>
            </div>
            <div class="modal-footer">
                <button class="btn btn-secondary" type="button" data-dismiss="modal">Đóng</button>
                <input id="button-update-profile" class="btn btn-primary" type="submit" value="Cập nhật"/>
            </div>
        </div>
    </div>
</div>

<script>
    function sendRequest(url) {
        $.ajax({
            url: url,
            method: "POST",
            data: $('#form-update-profile').serialize(),
            success: function (response) {
                // Extract data from the model map
                var data = response;
                // Redirect to the view
                window.location.href = data;
            },
            error: function (response) {
                console.log(response);
                //document.getElementById("#content-error-message").value = response?.responseJSON?.message;
                $('#modal-error-message').modal('show');

            }
        });
    }

    document.getElementById("button-update-profile").addEventListener("click", function () {
            sendRequest('/update-profile');
        });
</script>