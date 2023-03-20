<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!-- Person Edit Modal -->
<div class="modal fade" id="modal-edit-person" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel"
     aria-hidden="true">
    <div class="modal-dialog" role="document" style="max-width: 70%">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="exampleModalLabel">Sửa thông tin người nợ</h5>
                <button class="close" type="button" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">×</span>
                </button>
            </div>
                <div class="modal-body">
                    <form id="form-edit-person">
                    <input id="id-person-edit" name="id" type="text" class="form-control" hidden/>
                        <div style="display: flex; flex-direction: row">
                            <div style="display: flex;flex: 1;flex-direction: column;max-width: 50%;">
                                <div style="display: flex;flex: 1;flex-direction: column;max-width: 70%;margin-bottom: 1rem;">
                                    <label for="name-person-edit">Họ và tên (*)</label>
                                    <input id="name-person-edit" name="namePersonEdit" type="text" class="form-control"
                                           placeholder="Nhập tên"/>
                                </div>
                                <div style="display: flex;flex: 1;flex-direction: column;max-width: 70%;margin-bottom: 1rem;">
                                    <label for="address-person-edit">Địa chỉ</label>
                                    <input id="address-person-edit" name="addressPersonEdit" type="text" class="form-control"
                                           placeholder="Nhập địa chỉ"/>
                                </div>
                            </div>
                            <div style="display: flex;flex: 1;flex-direction: column;max-width: 50%">
                                <div style="display: flex;flex: 1;flex-direction: column;max-width: 70%;margin-bottom: 1rem;">
                                    <label for="phone-person-edit">Số điện thoại (*)</label>
                                    <input id="phone-person-edit" name="phonePersonEdit" type="text" class="form-control"
                                           placeholder="Nhập địa chỉ"/>
                                </div>
                                <div style="display: flex;flex: 1;flex-direction: column;max-width: 70%;margin-bottom: 1rem;">
                                    <label for="email-person-edit">Email</label>
                                    <input id="email-person-edit" name="emailPersonEdit" type="text" class="form-control"
                                           placeholder="Nhập email"/>
                                </div>
                            </div>
                        </div>
                    </form>
                </div>
                <div class="modal-footer">
                    <button class="btn btn-secondary" type="button" data-dismiss="modal">Đóng</button>
                    <input id="button-edit-person" class="btn btn-primary" value="Xác nhận"/>
                </div>
        </div>
    </div>
</div>

<script>
    function sendRequestEdit(url) {
        $.ajax({
            url: url,
            method: "POST",
            data: $('#form-edit-person').serialize(),
            success: function (response) {
                // Extract data from the model map
                var data = response;
                setSuccessMessage('Chỉnh sửa thông tin người nợ thành công');
                $('#modal-success-message').modal('show');
            },
            error: function (response) {
                console.log(response?.responseJSON?.message);
                setErrorMessage(response?.responseJSON?.message);
                $('#modal-error-message').modal('show');
            }
        });
    }

    document.getElementById("button-edit-person").addEventListener("click", function () {
            console.log("edit call");
            sendRequestEdit('/person/add-update');
        });
</script>

<script>
    //Make these field con only input number
    const phoneEdit = document.getElementById("phone-person-edit");

    const lstEdit = [];
    lstEdit.push(phoneEdit);

    lstEdit.forEach(function(element) {
        element.addEventListener("input", function(event) {
            const numberPattern = /^[0-9]*$/;

            if (!numberPattern.test(event.target.value)) {
              event.preventDefault();
              element.value = element.value.replace(/[^0-9]/g, '');
            }
          });
    });
</script>