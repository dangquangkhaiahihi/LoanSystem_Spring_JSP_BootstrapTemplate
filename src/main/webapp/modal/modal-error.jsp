<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!-- User Detail Modal -->
<div class="modal fade" id="modal-error-message" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel"
     aria-hidden="true" style="z-index: 9999;">
    <div class="modal-dialog" role="document" style="max-width: 70%">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" style="color: red; id="exampleModalLabel">Có lỗi xảy ra.</h5>
                <button class="close" type="button" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">×</span>
                </button>
            </div>
            <div class="modal-body">
                <p id="content-error-message" style="color: red;"/>
            </div>
            <div class="modal-footer">
                <button id="close-error-modal" class="btn btn-secondary" type="button" data-dismiss="modal">Đóng</button>
            </div>
        </div>
    </div>
</div>