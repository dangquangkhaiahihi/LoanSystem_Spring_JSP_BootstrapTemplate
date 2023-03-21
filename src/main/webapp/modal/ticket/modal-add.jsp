<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!-- Ticket Add Modal -->
<div class="modal fade" id="modal-add-ticket" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel"
     aria-hidden="true" style="z-index: 5000;backdrop-filter: blur(5px);background-color: rgba(0,0,0,0.5);" data-backdrop="static">
    <div class="modal-dialog" role="document" style="max-width: 70%">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="exampleModalLabel">Tạo phiếu nợ</h5>
                <button class="close" type="button" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">×</span>
                </button>
            </div>
                <div class="modal-body">
                    <form id="form-add-ticket">
                        <div class="row d-flex justify-content-end">
                                <div class="col-9 mb-3" style="display: flex;">
                                    <label for="note-ticket-add" style="flex:1;">Ghi chú</label>
                                    <input id="note-ticket-add" name="noteTicketAdd" type="text" class="form-control"
                                           value="" style="flex:3;"/>
                                </div>
                                <div class="col-9 mb-3" style="display: flex;">
                                    <label for="isPlus-ticket-add" style="flex:1;">Loại nợ (*)</label>
                                    <div id="button-select-plus" class="btn btn-primary mr-3" style="flex:1;background-color: gray; border-color: gray">+</div>
                                    <div id="button-select-minus" class="btn btn-primary" style="flex:1;background-color: gray; border-color: gray">-</div>
                                    <input id="isPlus-ticket-add" name="isPlusTicketAdd" type="text" hidden/>
                                </div>
                                <div class="col-9 mb-3" style="display: flex;">
                                    <label for="amount-ticket-add" style="flex:1;">Số tiền (*)</label>
                                    <input id="amount-ticket-add" name="amountTicketAdd" type="text" class="form-control" style="flex:3;"/>
                                </div>
                                <div class="col-9 mb-3" style="display: flex;">
                                    <label for="date-trans-ticket-add" style="flex:1;">Ngày lập phiếu</label>
                                    <input id="date-trans-ticket-add" name="dateTransTicketAddStr" type="datetime-local" class="form-control" style="flex:3;"/>
                                </div>
                                <div class="col-9 mb-3" style="display: flex;">
                                    <label for="img-ticket-add" style="flex:1;">Hình ảnh chứng minh</label>
                                    <input id="img-ticket-add" name="imgTicketAdd" type="file" class="form-control" style="flex:3;" accept="image/*"/>
                                </div>
                            </div>
                            <input id="person-id-ticket-add" name="personIdTicketAdd" type="text" hidden/>
                    </form>
                </div>
                <div class="modal-footer">
                    <button id="button-close-add-ticket" class="btn btn-secondary" type="button" data-dismiss="modal">Đóng</button>
                    <input id="button-add-ticket" class="btn btn-primary" value="Xác nhận"/>
                </div>
        </div>
    </div>
</div>

<script>
    function sendRequestAdd(url) {
        $.ajax({
            url: url,
            method: "POST",
            data: $('#form-add-ticket').serialize(),
            success: function (response) {
                // Extract data from the model map
                var data = response;
                setSuccessMessage('Tạo phiếu thành công');
                $('#modal-success-message').modal('show');
            },
            error: function (response) {
                console.log(response?.responseJSON?.message);
                setErrorMessage(response?.responseJSON?.message);
                $('#modal-error-message').modal('show');
            }
        });
    }

    document.getElementById("button-add-ticket").addEventListener("click", function () {
        console.log("add call");
        sendRequestAdd('/ticket/add-update');
    });

    document.getElementById("button-close-add-ticket").addEventListener("click", function () {
        document.getElementById("person-id-ticket-add").value = "";
        document.getElementById("note-ticket-add").value = "";
        document.getElementById("isPlus-ticket-add").value = "";
        document.getElementById("amount-ticket-add").value = "";
        document.getElementById("date-trans-ticket-add").value = "";
        document.getElementById("img-ticket-add").value = "";
    });

    document.addEventListener('keydown', function(event) {
        if (event.key === "Escape") {
            document.getElementById("person-id-ticket-add").value = "";
            document.getElementById("note-ticket-add").value = "";
            document.getElementById("isPlus-ticket-add").value = "";
            document.getElementById("amount-ticket-add").value = "";
            document.getElementById("date-trans-ticket-add").value = "";
            document.getElementById("img-ticket-add").value = "";
        }
    });

    document.getElementById("button-select-plus").addEventListener("click", function () {
        console.log('plus clicked',document.getElementById("isPlus-ticket-add").value);
        document.getElementById("button-select-plus").style.cssText = "flex:1;";
        document.getElementById("button-select-minus").style.cssText = "flex:1;background-color: gray; border-color: gray";
        document.getElementById("isPlus-ticket-add").value = true;
    });

    document.getElementById("button-select-minus").addEventListener("click", function () {
        console.log('minus clicked',document.getElementById("isPlus-ticket-add").value);
        document.getElementById("button-select-plus").style.cssText = "flex:1;background-color: gray; border-color: gray";
        document.getElementById("button-select-minus").style.cssText = "flex:1;";
        document.getElementById("isPlus-ticket-add").value = false;
    });
</script>

<script>
    //Make these field con only input number
    const amountTicketAdd = document.getElementById("amount-ticket-add");

    const lstTicketAdd = [];
    lstTicketAdd.push(amountTicketAdd);

    lstTicketAdd.forEach(function(element) {
        element.addEventListener("input", function(event) {
            const numberPattern = /^[0-9]*$/;

            if (!numberPattern.test(event.target.value)) {
              event.preventDefault();
              element.value = element.value.replace(/[^0-9]/g, '');
            }
          });
    });
</script>