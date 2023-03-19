<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!-- User Detail Modal -->
<div class="modal fade" id="modal-success-message" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel"
     aria-hidden="true">
    <div class="modal-dialog" role="document" style="max-width: 70%">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" style="color: green; id="exampleModalLabel">Thành công.</h5>
                <button class="close" type="button" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">×</span>
                </button>
            </div>
            <div class="modal-body">
                <p id="content-success-message" style="color: green;"/>
            </div>
            <div class="modal-footer">
                <button id="button-close-success-modal" class="btn btn-secondary" type="button" data-dismiss="modal">Đóng</button>
            </div>
        </div>
    </div>
</div>

<script>
    document.getElementById("button-close-success-modal").addEventListener("click", function () {
        location.reload();
    });
</script>