<%@ page import="com.example.demo.model.*" %>
<%@ page import="java.util.List" %>
<%@ page import="com.example.demo.common.Constant" %>
<%@ page import="java.time.LocalDateTime" %>
<%@ page import="com.example.demo.common.Utils" %>
<%@ page import="java.time.temporal.ChronoUnit" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="vi">

<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>Vay tiền</title>

    <!-- Custom fonts for this template-->
    <link href="../../vendor/fontawesome-free/css/all.min.css" rel="stylesheet" type="text/css">
    <link
            href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i"
            rel="stylesheet">

    <!-- Custom styles for this template-->
    <link href="../../css/custom-style.css" rel="stylesheet">
    <link href="../../css/sb-admin-2.min.css" rel="stylesheet">
    <!-- Custom styles for this page -->
    <link href="../../vendor/datatables/dataTables.bootstrap4.min.css" rel="stylesheet">
</head>

<body id="page-top">
<% LocalDateTime now = LocalDateTime.now(); %>
<!-- Page Wrapper -->
<div id="wrapper">
    <%-- Import side bar--%>
    <%@ include file="../../layout/sidebar.jsp" %>

    <!-- Content Wrapper -->
    <div id="content-wrapper" class="d-flex flex-column">

        <!-- Main Content -->
        <div id="content">
            <%-- Import top bar--%>
            <%@ include file="../../layout/topbar.jsp" %>

            <!-- Begin Page Content -->
            <div class="container-fluid">

                <!-- Page Heading -->
                <h1 class="h3 mb-2 text-gray-800">Tìm khoản vay phù hợp</h1>

                <div class="card shadow mb-4">
                    <div class="card-body">
                        <%-- Form Search --%>
                        <form method="GET" action="/borrow-money" modelAttribute="loanRequestFilter">
                            <div style="display: flex;">
                                <div style="display: flex;flex:1;flex-direction: column;margin-right: 2rem">
                                    <div style="display: flex;flex: 1;flex-direction: column;max-width: 100%;margin-bottom: 1rem;">
                                        <label for="fromAmountStr">Từ số tiền</label>
                                        <%
                                            String fromAmountStr = request.getParameter("fromAmountStr");
                                        %>
                                        <input id="fromAmountStr" name="fromAmountStr" type="text"
                                               class="form-control"
                                               value="<%=fromAmountStr != null ? fromAmountStr : "" %>"
                                               placeholder="Nhập số tiền tìm kiếm"/>
                                    </div>
                                    <div style="display: flex;flex: 1;flex-direction: column;max-width: 100%;margin-bottom: 1rem;">
                                        <label for="fromCreatedDateStr">Từ ngày bắt đầu</label>
                                        <input id="fromCreatedDateStr" name="fromCreatedDateStr" type="date"
                                               class="form-control"
                                                <% if (request.getParameter("fromCreatedDateStr") != null) { %>
                                               value="<%=request.getParameter("fromCreatedDateStr")%>"
                                                <% } else {%>
                                               value="<%=Utils.convertLocalDateTimeToyyyyMMdd(LocalDateTime.now().minus(1,ChronoUnit.MONTHS))%>"
                                                <% } %>
                                               placeholder="Chọn từ ngày"/>
                                    </div>
                                    <div style="display: flex;flex: 1;flex-direction: column;max-width: 100%;margin-bottom: 1rem;">
                                        <label for="type">Loại cho vay</label>
                                        <select
                                                id="type" name="type"
                                                class="form-control"
                                                value="<%=request.getParameter("type")%>"
                                        >
                                            <option value="" <% if (request.getParameter("type") == null) { %>
                                                    selected<% } %>>Tất cả
                                            </option>
                                            <option value="<%=Constant.LOAN_TYPE_BASED_ON_CURRENT_DEBT%>" <% if (Constant.LOAN_TYPE_BASED_ON_CURRENT_DEBT.equals(request.getParameter("type"))) { %>
                                                    selected<% } %>>Theo số dư nợ giảm dần
                                            </option>
                                            <option value="<%=Constant.LOAN_TYPE_BASED_ON_INITIAL_DEBT%>" <% if (Constant.LOAN_TYPE_BASED_ON_INITIAL_DEBT.equals(request.getParameter("type"))) { %>
                                                    selected<% } %>>Theo số dư nợ gốc
                                            </option>
                                        </select>
                                    </div>
                                </div>
                                <div style="display: flex;flex:1;flex-direction: column;">
                                    <div style="display: flex;flex: 1;flex-direction: column;max-width: 100%;margin-bottom: 1rem;">
                                        <label for="toAmountStr">Đến số tiền</label>
                                        <%
                                            String toAmountStr = request.getParameter("toAmountStr");
                                        %>
                                        <input id="toAmountStr" name="toAmountStr" type="text"
                                               class="form-control"
                                               value="<%=toAmountStr != null ? toAmountStr : "" %>"
                                               placeholder="Nhập số tiền tìm kiếm"/>
                                    </div>
                                    <div style="display: flex;flex: 1;flex-direction: column;max-width: 100%;margin-bottom: 1rem;">
                                        <label for="toCreatedDateStr">Đến ngày bắt đầu</label>
                                        <input id="toCreatedDateStr" name="toCreatedDateStr" type="date"
                                               class="form-control"
                                                <% if (request.getParameter("toCreatedDateStr") != null) { %>
                                               value="<%=request.getParameter("toCreatedDateStr")%>"
                                                <% } else {%>
                                               value="<%=Utils.convertLocalDateTimeToyyyyMMdd(LocalDateTime.now().plus(1,ChronoUnit.DAYS))%>"
                                                <% } %>
                                               placeholder="Chọn đến ngày"/>
                                    </div>
                                    <div style="display: flex;flex: 1;flex-direction: column;max-width: 100%;margin-bottom: 1rem;">
                                        <label for="duration">Thời hạn cho vay</label>
                                        <select id="duration" name="duration" class="form-control">
                                            <option value=""
                                                    <% if (request.getParameter("duration") == null) { %>
                                                    selected<% } %>>
                                                Tất cả
                                            </option>
                                            <option value="<%=Constant.DURATION_ONE_MONTH%>"
                                                    <% if (Constant.DURATION_ONE_MONTH.equals(request.getParameter("duration"))) { %>
                                                    selected <% } %>>
                                                1 tháng
                                            </option>
                                            <option value="<%=Constant.DURATION_TWO_MONTHS%>"
                                                    <% if (Constant.DURATION_TWO_MONTHS.equals(request.getParameter("duration"))) { %>
                                                    selected<% } %>>
                                                2 tháng
                                            </option>
                                            <option value="<%=Constant.DURATION_THREE_MONTHS%>"
                                                    <% if (Constant.DURATION_THREE_MONTHS.equals(request.getParameter("duration"))) { %>
                                                    selected<% } %>>
                                                3 tháng
                                            </option>
                                            <option value="<%=Constant.DURATION_ONE_YEAR%>"
                                                    <% if (Constant.DURATION_ONE_YEAR.equals(request.getParameter("duration"))) { %>
                                                    selected<% } %>>
                                                1 năm
                                            </option>
                                        </select>
                                    </div>
                                </div>
                            </div>
                            <div style="text-align: center;">
                                <input class="btn btn-primary" type="submit" value="Tìm kiếm"/>
                            </div>
                        </form>
                    </div>
                </div>

                <!-- DataTales Example -->
                <div class="card shadow mb-4">
                    <div class="card-body">
                        <div class="table-responsive">
                            <table class="table table-bordered" id="dataTable" style="width: 100%;mso-cellspacing: 0">
                                <thead>
                                <tr>
                                    <th>Loại cho vay</th>
                                    <th>Số tiền cho vay</th>
                                    <th>Ngày tạo</th>
                                    <th>Thời hạn</th>
                                    <th>Hành động</th>
                                </tr>
                                </thead>
                                <tbody>
                                <%List<LoanDto> loanDtos = (List<LoanDto>) request.getAttribute("loanDtos");%>
                                <% for (LoanDto loanDto : loanDtos) { %>
                                <tr>
                                    <td>
                                        <% if (Constant.LOAN_TYPE_BASED_ON_CURRENT_DEBT.equals(loanDto.getType())) { %>
                                        <p>
                                            Theo số dư nợ giảm dần
                                        </p>
                                        <% } %>
                                        <% if (Constant.LOAN_TYPE_BASED_ON_INITIAL_DEBT.equals(loanDto.getType())) { %>
                                        <p>
                                            Theo số dư nợ gốc
                                        </p>
                                        <% } %>
                                    </td>
                                    <td><%= loanDto.getAmount()  %>
                                    </td>
                                    <td><%= loanDto.getCreatedAt() %>
                                    </td>
                                    <td>
                                        <% if (Constant.DURATION_ONE_MONTH.equals(loanDto.getDuration())) { %>
                                            <p>1 tháng</p>
                                        <% } else if (Constant.DURATION_TWO_MONTHS.equals(loanDto.getDuration())) { %>
                                            <p>2 tháng</p>
                                        <% } else if (Constant.DURATION_THREE_MONTHS.equals(loanDto.getDuration())) { %>
                                            <p>3 tháng</p>
                                        <% } else if (Constant.DURATION_ONE_YEAR.equals(loanDto.getDuration())) { %>
                                            <p>1 năm</p>
                                        <% } %>
                                    </td>
                                    <td>
                                        <div style="display: flex">
                                            <div>
                                            <% if (loanDto.getCanLoan()) {%>
                                                <button class="btn btn-transaprent btn-icon btn-sm"
                                                        data-tooltip="tooltip" title="Vay tiền" style="background-color: #5a7087"
                                                        data-toggle="modal" data-target="#loan-modal-confirm-borrow"
                                                        onclick="captrueCurrentLoanDto(<%=loanDto.getId()%>,<%=loanDto.getAmount()%>,'<%=loanDto.getType()%>','<%=loanDto.getDuration()%>','<%=loanDto.getInterestStr()%>','<%=loanDto.getInterest()%>','<%=loanDto.getUser().getId()%>')">
                                                    <i class="fas fa-donate text-white" ></i>
                                                </button>
                                            <% } else { %>
                                                <button class="btn btn-transaprent btn-icon btn-sm"
                                                        data-tooltip="tooltip" title="Vay tiền" style="background-color: red"
                                                        data-toggle="modal" data-target="#loan-modal-can-not-borrow"
                                                        onclick="captrueCurrentLoanDto(<%=loanDto.getId()%>,<%=loanDto.getAmount()%>,'<%=loanDto.getType()%>','<%=loanDto.getDuration()%>','<%=loanDto.getInterestStr()%>','<%=loanDto.getInterest()%>')">
                                                    <i class="fas fa-donate text-white" ></i>
                                                </button>
                                                <button class="btn btn-transaprent btn-icon btn-sm"
                                                        data-tooltip="tooltip" title="Hủy yêu cầu"
                                                        data-toggle="modal" data-target="#confirm-modal-delete-request"
                                                        onclick="captrueCurrentLoanDto(<%=loanDto.getId()%>,<%=loanDto.getAmount()%>,'<%=loanDto.getType()%>','<%=loanDto.getDuration()%>','<%=loanDto.getInterestStr()%>','<%=loanDto.getInterest()%>')">
                                                    <img src="../../img/icon/24x24-close-circle.svg" alt="" class="btn-icon"/>
                                                </button>
                                            <% } %>
                                            </div>
                                        </div>
                                    </td>
                                </tr>
                                <% } %>
                                </tbody>
                            </table>
                        </div>
                    </div>
                </div>

            </div>
            <!-- /.container-fluid -->

        </div>
        <!-- End of Main Content -->

        <%-- Import top bar--%>
        <%@ include file="../../layout/footer.jsp" %>

    </div>
    <!-- End of Content Wrapper -->

</div>
<!-- End of Page Wrapper -->

<%--Import confirm borrow modal--%>
<%@ include file="../../modal/borrow-money/modal-confirm-borrow.jsp" %>

<%--Import can not borrow modal--%>
<%@ include file="../../modal/borrow-money/modal-can-not-borrow.jsp" %>

<%--Import confirm delete request modal--%>
<%@ include file="../../modal/borrow-money/modal-confirm-delete-request.jsp" %>

<!-- Bootstrap core JavaScript-->
<script src="../../vendor/jquery/jquery.min.js"></script>
<script src="../../vendor/bootstrap/js/bootstrap.bundle.min.js"></script>

<!-- Core plugin JavaScript-->
<script src="../../vendor/jquery-easing/jquery.easing.min.js"></script>

<!-- Custom scripts for all pages-->
<script src="../../js/sb-admin-2.min.js"></script>

<!-- Page level plugins -->
<script src="../../vendor/datatables/jquery.dataTables.min.js"></script>
<script src="../../vendor/datatables/dataTables.bootstrap4.min.js"></script>

<!-- Page level custom scripts -->
<script src="../../js/demo/datatables-demo.js"></script>

<%--=======================================================================================--%>
<%--CAPTURE CURRENT RECORD DETAIL--%>
<script>
    var selectedLoanDto = {
        id : '',
        amount : '',
        type : '',
        duration : ''
    };
    function captrueCurrentLoanDto(id,amount,type,duration,interestStr,interest,loanerId) {
        console.log("...............................",id,amount,type,duration,loanerId)
        selectedLoanDto.id = id;
        selectedLoanDto.amount = amount;
        if(type == '<%=Constant.LOAN_TYPE_BASED_ON_INITIAL_DEBT%>') selectedLoanDto.type = 'Theo số dư nợ gốc';
        else if(type == '<%=Constant.LOAN_TYPE_BASED_ON_CURRENT_DEBT%>') selectedLoanDto.type = 'Theo số dư nợ giảm dần';

        if(duration === '<%=Constant.DURATION_ONE_MONTH%>') selectedLoanDto.duration = '1 tháng';
        else if(duration === '<%=Constant.DURATION_TWO_MONTHS%>') selectedLoanDto.duration = '2 tháng';
        else if(duration === '<%=Constant.DURATION_THREE_MONTHS%>') selectedLoanDto.duration = '3 tháng';
        else if(duration === '<%=Constant.DURATION_ONE_YEAR%>') selectedLoanDto.duration = '1 năm';

        console.log('------------------------------',JSON.stringify(selectedLoanDto))
        document.getElementById("loan-detail-confirm-amount").innerHTML = selectedLoanDto.amount;
        document.getElementById("loan-detail-confirm-type").innerHTML = selectedLoanDto.type;
        document.getElementById("loan-detail-confirm-duration").innerHTML = selectedLoanDto.duration;
        document.getElementById("loan-detail-confirm-interest").innerHTML = interestStr;

        document.getElementById("loan-detail-cannot-amount").innerHTML = selectedLoanDto.amount;
        document.getElementById("loan-detail-cannot-type").innerHTML = selectedLoanDto.type;
        document.getElementById("loan-detail-cannot-duration").innerHTML = selectedLoanDto.duration;
        document.getElementById("loan-detail-cannot-interest").innerHTML = interestStr;

        if(id && loanerId) {
            document.getElementById("value-borrow-loan-id").value = id;
            document.getElementById("value-borrow-loaner-id").value = loanerId;
        }
    }
</script>
<%--END CAPTURE CURRENT RECORD DETAIL--%>
<%--=======================================================================================--%>
<%--DELETE REQUEST SCRIPT--%>
<script>
        document.getElementById("submit-delete-request").addEventListener("click", function () {
        // code to execute when submit-delete-request is clicked
        sendRequest( '/borrow-money/delete-request')
    });
</script>

<script>
    function sendRequest( url) {
        $.ajax({
            url: url,
            method: "DELETE",
            data: {loanId: selectedLoanDto.id},
            success: function (response) {
                // Extract data from the model map
                var data = response;
                // Redirect to the view
                window.location.href = data;
            }
        });
    }
</script>
<%--END LOAN LOCK/UNLOCK SCRIPT--%>
<%--=======================================================================================--%>

</body>

</html>