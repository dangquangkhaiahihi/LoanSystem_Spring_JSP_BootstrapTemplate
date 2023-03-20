<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!-- Person Add Modal -->
<div class="modal fade" id="modal-detail-person" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel"
     aria-hidden="true">
    <div class="modal-dialog" role="document" style="max-width: 70%">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="exampleModalLabel">Thông tin người nợ</h5>
                <button class="close" type="button" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">×</span>
                </button>
            </div>
                <div class="modal-body">

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