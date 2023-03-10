<%@ page import="com.example.demo.model.LoanDto" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!-- Loan Detail Modal -->
<div class="modal fade" id="request-modal-reject" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel"
     aria-hidden="true">
    <div class="modal-dialog" role="document" style="max-width: 70%">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="exampleModalLabel">Xác nhận.</h5>
                <button class="close" type="button" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">×</span>
                </button>
            </div>
        <form method="POST" action="/request/reject" modelAttribute="requestFilterRequest">
            <div class="modal-body">
                Bạn có chắc muốn từ chối yêu cầu cho vay?
                <input id="request-id-reject" name="requestId" type="text" hidden/>
            </div>
            <div class="modal-footer">
                <button class="btn btn-secondary" type="button" data-dismiss="modal">Đóng</button>
                <input type="submit" class="btn btn-primary" value="Xác nhận"/>
            </div>
        </form>
        </div>
    </div>
</div>