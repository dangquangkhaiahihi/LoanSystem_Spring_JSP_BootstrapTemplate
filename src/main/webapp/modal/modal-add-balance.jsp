<%@ page import="com.example.demo.service.dto.UserDto" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!-- Add Balance Modal-->
<div class="modal fade" id="addBalance" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel"
     aria-hidden="true">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="exampleModalLabel">Nạp tiền</h5>
                <button class="close" type="button" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">×</span>
                </button>
            </div>
            <form method="POST" action="/add-balance" modelAttribute="addBalanceVal">
                <div class="modal-body">
                    <div style="display: flex;flex-direction: column;">
                        <div style="display: flex;flex: 1;flex-direction: column;max-width: 100%;margin-bottom: 1rem;">
                            <label for="addBalanceVal">Số tiền cần nạp</label>
                            <input id="addBalanceVal" name="addBalanceVal" type="number" class="form-control" value=""
                                   placeholder="Nhập số tiền cần nạp"/>
                        </div>
                    </div>

                </div>
                <div class="modal-footer">
                    <button class="btn btn-secondary" type="button" data-dismiss="modal">Hủy</button>
                    <input class="btn btn-primary" type="submit" value="Xác nhận"/>
                </div>
            </form>
        </div>
    </div>
</div>