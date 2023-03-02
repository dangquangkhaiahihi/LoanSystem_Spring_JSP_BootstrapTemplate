<%@ page import="com.example.demo.model.LoanDto" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!-- Loan Detail Modal -->
<div class="modal fade" id="loan-modal-confirm-borrow" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel"
     aria-hidden="true">
    <div class="modal-dialog" role="document" style="max-width: 70%">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="exampleModalLabel">Bạn có chắc muốn vay tiền?</h5>
                <button class="close" type="button" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">×</span>
                </button>
            </div>
            <div class="modal-body">
                <h3>Thông tin khoản vay : </h3>
                <div style="display: flex; flex-direction: row">
                    <div style="display: flex;flex: 1;flex-direction: column;max-width: 50%;">
                        <div style="display: flex;flex: 1;flex-direction: row;max-width: 70%;margin-bottom: 1rem;">
                            <label style="flex: 2" for="loan-detail-confirm-amount">Số tiền : </label>
                            <label style="flex: 1;font-weight: bold;" id="loan-detail-confirm-amount"/>
                        </div>
                        <div style="display: flex;flex: 1;flex-direction: row;max-width: 70%;margin-bottom: 1rem;">
                            <label style="flex: 2" for="loan-detail-confirm-type">Loại cho vay : </label>
                            <label style="flex: 1;font-weight: bold;" id="loan-detail-confirm-type"/>
                        </div>
                    </div>
                    <div style="display: flex;flex: 1;flex-direction: column;max-width: 50%">
                        <div style="display: flex;flex: 1;flex-direction: row;max-width: 70%;margin-bottom: 1rem;">
                            <label style="flex: 2" for="loan-detail-confirm-duration">Thời hạn cho vay : </label>
                            <label style="flex: 1;font-weight: bold;" id="loan-detail-confirm-duration"/>
                        </div>
                        <div style="display: flex;flex: 1;flex-direction: row;max-width: 70%;margin-bottom: 1rem;">
                            <label style="flex: 2" for="loan-detail-confirm-interest">Tiền lãi  : </label>
                            <label style="flex: 1;font-weight: bold;" id="loan-detail-confirm-interest"/>
                        </div>
                    </div>
                </div>
            </div>
            <div class="modal-footer">
                <button class="btn btn-secondary" type="button" data-dismiss="modal">Đóng</button>
                <input id="submit-loan-lock" class="btn btn-primary" value="Xác nhận"/>
            </div>
        </div>
    </div>
</div>