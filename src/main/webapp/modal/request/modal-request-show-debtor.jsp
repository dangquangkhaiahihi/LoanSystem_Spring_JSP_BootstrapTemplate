<%@ page import="com.example.demo.model.LoanDto" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!-- Loan Detail Modal -->
<div class="modal fade" id="request-show-debtor" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel"
     aria-hidden="true">
    <div class="modal-dialog" role="document" style="max-width: 70%">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="exampleModalLabel">Thông tin người vay</h5>
                <button class="close" type="button" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">×</span>
                </button>
            </div>
            <div class="modal-body">
                <h3>Thông tin người vay : </h3>
                <div style="display: flex; flex-direction: row">
                    <div style="display: flex;flex: 1;flex-direction: column;max-width: 50%;">
                        <div style="display: flex;flex: 1;flex-direction: row;max-width: 70%;margin-bottom: 1rem;">
                            <label style="flex: 1.5" for="request-show-debtor-name">Tên người vay : </label>
                            <label style="flex: 1;font-weight: bold;" id="request-show-debtor-name"/>
                        </div>
                        <div style="display: flex;flex: 1;flex-direction: row;max-width: 70%;margin-bottom: 1rem;">
                            <label style="flex: 1.5" for="request-show-debtor-cccd">CMT/CCCD : </label>
                            <label style="flex: 1;font-weight: bold;" id="request-show-debtor-cccd"/>
                        </div>
                        <div style="display: flex;flex: 1;flex-direction: row;max-width: 70%;margin-bottom: 1rem;">
                            <label style="flex: 1.5" for="request-show-debtor-phone">Số điện thoại : </label>
                            <label style="flex: 1;font-weight: bold;" id="request-show-debtor-phone"/>
                        </div>
                    </div>
                    <div style="display: flex;flex: 1;flex-direction: column;max-width: 50%">
                        <div style="display: flex;flex: 1;flex-direction: row;max-width: 70%;margin-bottom: 1rem;">
                            <label style="flex: 1.5" for="request-show-debtor-credit-point">Điểm uy tín : </label>
                            <label style="flex: 1;font-weight: bold;" id="request-show-debtor-credit-point"/>
                        </div>
                        <div style="display: flex;flex: 1;flex-direction: row;max-width: 70%;margin-bottom: 1rem;">
                            <label style="flex: 1.5" for="request-show-debtor-gender">Giới tính : </label>
                            <label style="flex: 1;font-weight: bold;" id="request-show-debtor-gender"/>
                        </div>
                        <div style="display: flex;flex: 1;flex-direction: row;max-width: 70%;margin-bottom: 1rem;">
                            <label style="flex: 1.5" for="request-show-debtor-email">Email : </label>
                            <label style="flex: 1;font-weight: bold;" id="request-show-debtor-email"/>
                        </div>
                    </div>
                </div>
            </div>
            <div class="modal-footer">
                <button class="btn btn-secondary" type="button" data-dismiss="modal">Đóng</button>
            </div>
        </div>
    </div>
</div>