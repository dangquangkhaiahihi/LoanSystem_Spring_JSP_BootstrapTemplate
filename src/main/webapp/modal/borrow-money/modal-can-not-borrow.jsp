<%@ page import="com.example.demo.model.LoanDto" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!-- Loan Detail Modal -->
<div class="modal fade" id="loan-modal-can-not-borrow" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel"
     aria-hidden="true">
    <div class="modal-dialog" role="document" style="max-width: 70%">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="exampleModalLabel">Bạn có chắc muốn vay tiền?</h5>
                <button class="close" type="button" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">×</span>
                </button>
            </div>
            <form method="POST" action="/borrow-money/request_borrow" modelAttribute="borrowMoneyRequest">
            <div class="modal-body">
                <h3>Thông tin khoản vay : </h3>
                <div style="display: flex; flex-direction: row">
                    <div style="display: flex;flex: 1;flex-direction: column;max-width: 50%;">
                        <div style="display: flex;flex: 1;flex-direction: row;max-width: 70%;margin-bottom: 1rem;">
                            <label style="flex: 1.5" for="loan-detail-cannot-amount">Số tiền : </label>
                            <label style="flex: 1;font-weight: bold;" id="loan-detail-cannot-amount"/>
                        </div>
                        <div style="display: flex;flex: 1;flex-direction: row;max-width: 70%;margin-bottom: 1rem;">
                            <label style="flex: 1.5" for="loan-detail-cannot-type">Loại cho vay : </label>
                            <label style="flex: 1;font-weight: bold;" id="loan-detail-cannot-type"/>
                        </div>
                    </div>
                    <div style="display: flex;flex: 1;flex-direction: column;max-width: 50%">
                        <div style="display: flex;flex: 1;flex-direction: row;max-width: 70%;margin-bottom: 1rem;">
                            <label style="flex: 1.5" for="loan-detail-cannot-duration">Thời hạn cho vay : </label>
                            <label style="flex: 1;font-weight: bold;" id="loan-detail-cannot-duration"/>
                        </div>
                        <div style="display: flex;flex: 1;flex-direction: row;max-width: 70%;margin-bottom: 1rem;">
                            <label style="flex: 1.5" for="loan-detail-cannot-interest">Lãi suất : </label>
                            <label style="flex: 1;font-weight: bold;" id="loan-detail-cannot-interest"/>
                        </div>
                    </div>
                </div>
                <h5 style="color : red">Bạn đã yêu cầu khoản vay này rồi.</h5>
            </div>
            <div class="modal-footer">
                <button class="btn btn-secondary" type="button" data-dismiss="modal">Đóng</button>
            </div>
            </form>
        </div>
    </div>
</div>