<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!-- Loan Detail Modal -->
<div class="modal fade" id="loan-modal-add" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel"
     aria-hidden="true">
    <div class="modal-dialog" role="document" style="max-width: 70%">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="exampleModalLabel">Tạo khoản cho vay</h5>
                <button class="close" type="button" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">×</span>
                </button>
            </div>
            <form method="POST" action="/loan/add" modelAttribute="loanRequestAdd">
                <div class="modal-body">

                    <div style="display: flex; flex-direction: row">
                        <div style="display: flex;flex: 1;flex-direction: column;max-width: 50%;">
                            <div style="display: flex;flex: 1;flex-direction: column;max-width: 70%;margin-bottom: 1rem;">
                                <label for="amountStr">Số tiền</label>
                                <input id="amountStr" name="amountStr" type="text" class="form-control"
                                       value=""
                                       placeholder="Nhập số tiền"/>
                            </div>
                            <div style="display: flex;flex: 1;flex-direction: column;max-width: 70%;margin-bottom: 1rem;">
                                <label for="type">Loại cho vay</label>
                                <select id="type" name="type" class="form-control">
                                    <option value="">Chọn loại cho vay</option>
                                    <option value="<%=Constant.LOAN_TYPE_INSTALLMENT%>">Lũy tiến
                                    </option>
                                    <option value="<%=Constant.LOAN_TYPE_ONE_TIME%>">Trả 1 lần
                                    </option>
                                </select>
                            </div>
                        </div>
                        <div style="display: flex;flex: 1;flex-direction: column;max-width: 50%">
                            <div style="display: flex;flex: 1;flex-direction: column;max-width: 70%;margin-bottom: 1rem;">
                                <label for="duration">Thời hạn cho vay</label>
                                <select id="duration" name="duration" class="form-control">
                                    <option value="">Chọn thời hạn cho vay</option>
                                    <option value="<%=Constant.DURATION_ONE_MONTH%>">1 tháng</option>
                                    <option value="<%=Constant.DURATION_TWO_MONTHS%>">2 tháng</option>
                                    <option value="<%=Constant.DURATION_THREE_MONTHS%>">3 tháng</option>
                                    <option value="<%=Constant.DURATION_ONE_YEAR%>">1 năm</option>
                                    </option>
                                </select>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="modal-footer">
                    <button class="btn btn-secondary" type="button" data-dismiss="modal">Đóng</button>
                    <input class="btn btn-primary" type="submit" value="Xác nhận"/>
                </div>
            </form>
        </div>
    </div>
</div>