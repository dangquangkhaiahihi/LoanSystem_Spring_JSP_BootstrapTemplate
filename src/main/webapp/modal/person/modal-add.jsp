<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!-- Loan Detail Modal -->
<div class="modal fade" id="modal-add-person" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel"
     aria-hidden="true">
    <div class="modal-dialog" role="document" style="max-width: 70%">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="exampleModalLabel">Tạo người nợ</h5>
                <button class="close" type="button" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">×</span>
                </button>
            </div>
                <div class="modal-body">
                    <form id="form-add-person">
                        <div style="display: flex; flex-direction: row">
                            <div style="display: flex;flex: 1;flex-direction: column;max-width: 50%;">
                                <div style="display: flex;flex: 1;flex-direction: column;max-width: 70%;margin-bottom: 1rem;">
                                    <label for="name-person-add">Họ và tên (*)</label>
                                    <input id="name-person-add" name="name" type="text" class="form-control"
                                           value=""
                                           placeholder="Nhập tên"/>
                                </div>
                                <div style="display: flex;flex: 1;flex-direction: column;max-width: 70%;margin-bottom: 1rem;">
                                    <label for="address-person-add">Địa chỉ</label>
                                    <input id="address-person-add" name="address" type="text" class="form-control"
                                           value=""
                                           placeholder="Nhập địa chỉ"/>
                                </div>
                            </div>
                            <div style="display: flex;flex: 1;flex-direction: column;max-width: 50%">
                                <div style="display: flex;flex: 1;flex-direction: column;max-width: 70%;margin-bottom: 1rem;">
                                    <label for="phone-person-add">Số điện thoại (*)</label>
                                    <input id="phone-person-add" name="phone" type="text" class="form-control"
                                           value=""
                                           placeholder="Nhập địa chỉ"/>
                                </div>
                                <div style="display: flex;flex: 1;flex-direction: column;max-width: 70%;margin-bottom: 1rem;">
                                    <label for="email-person-add">Email</label>
                                    <input id="email-person-add" name="email" type="text" class="form-control"
                                           value=""
                                           placeholder="Nhập email"/>
                                </div>
                            </div>
                        </div>
                    </form>
                </div>
                <div class="modal-footer">
                    <button class="btn btn-secondary" type="button" data-dismiss="modal">Đóng</button>
                    <input id="button-add-person" class="btn btn-primary" value="Xác nhận"/>
                </div>
        </div>
    </div>
</div>

<script>
    function sendRequest(url) {
        $.ajax({
            url: url,
            method: "POST",
            data: $('#form-add-person').serialize(),
            success: function (response) {
                // Extract data from the model map
                var data = response;
                setSuccessMessage('Thêm người dùng thành công');
                $('#modal-success-message').modal('show');
            },
            error: function (response) {
                console.log(response?.responseJSON?.message);
                setErrorMessage(response?.responseJSON?.message);
                $('#modal-error-message').modal('show');
            }
        });
    }

    document.getElementById("button-add-person").addEventListener("click", function () {
            sendRequest('/person/add-update');
        });
</script>