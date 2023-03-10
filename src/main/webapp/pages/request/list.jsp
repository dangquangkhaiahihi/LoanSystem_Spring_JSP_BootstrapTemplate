<%@ page import="com.example.demo.model.*" %>
<%@ page import="java.util.List" %>
<%@ page import="com.example.demo.common.Constant" %>
<%@ page import="java.time.LocalDateTime" %>
<%@ page import="com.example.demo.common.Utils" %>
<%@ page import="java.time.temporal.ChronoUnit" %>
<%@ page import="com.google.gson.Gson" %>
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

    <title>Xác nhận yêu cầu vay</title>

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
                <h1 class="h3 mb-2 text-gray-800">Xác nhận yêu cầu vay</h1>

                <div class="card shadow mb-4">
                    <div class="card-body">
                        <%-- Form Search --%>
                        <form method="GET" action="/request" modelAttribute="requestFilterRequest">
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
                                            <option value=""<% if (request.getParameter("type") == null) { %>
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
                                    <th>Người vay</th>
                                    <th>Số tiền cho vay</th>
                                    <th>Ngày yêu cầu</th>
                                    <th>Hành động</th>
                                </tr>
                                </thead>
                                <tbody>
                                <%List<RequestDto> requestDtos = (List<RequestDto>) request.getAttribute("requestDtos");%>
                                <%Gson gson = Utils.getGson();%>
                                <% for (RequestDto requestDto : requestDtos) { %>
                                    <% String requestJson = gson.toJson(requestDto); %>
                                <tr>
                                    <td>
                                        <%= requestDto.getDebtorDto().getName() %>
                                    </td>
                                    <td><%= requestDto.getLoanDto().getAmount() %>
                                    </td>
                                    <td><%= requestDto.getCreatedAt() %>
                                    </td>
                                    <td>
                                        <div style="display: flex">
                                            <div>
                                                <button class="btn btn-transaprent btn-icon btn-sm"
                                                        data-toggle="modal" data-target="#request-show-loan"
                                                        data-tooltip="tooltip" title="Thông tin khoản vay"
                                                        onclick='captrueCurrentRequestLoanDetail(<%=requestJson%>)' >
                                                    <img src="../../img/icon/24x24-information-circle.svg" alt=""
                                                         class="btn-icon"/>
                                                </button>
                                            </div>
                                            <div>
                                                <button class="btn btn-transaprent btn-icon btn-sm"
                                                        data-toggle="modal" data-target="#request-show-debtor"
                                                        data-tooltip="tooltip" title="Thông tin người vay"
                                                        onclick='captrueCurrentRequestLoanDetail(<%=requestJson%>)' >
                                                    <img src="../../img/icon/24x24-user.svg" alt=""
                                                         class="btn-icon"/>
                                                </button>
                                            </div>
                                            <div>
                                                <button class="btn btn-transaprent btn-icon btn-sm"
                                                        data-toggle="modal" data-target="#request-approve"
                                                        data-tooltip="tooltip" title="Đồng ý cho vay">
                                                    <img src="../../img/icon/24x24-check.svg" alt=""
                                                         class="btn-icon"/>
                                                </button>
                                            </div>
                                            <div>
                                                <button class="btn btn-transaprent btn-icon btn-sm"
                                                        data-toggle="modal" data-target="#request-reject"
                                                        data-tooltip="tooltip" title="Từ chối cho vay">
                                                    <img src="../../img/icon/24x24-close-circle.svg" alt=""
                                                         class="btn-icon"/>
                                                </button>
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

<%--Import request show loan modal--%>
<%@ include file="../../modal/request/request-show-loan.jsp" %>

<%--Import request show debtor modal--%>
<%@ include file="../../modal/request/request-show-debtor.jsp" %>

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
<%--CAPTURE CURRENT RECORD--%>
<script>
    function captrueCurrentRequestLoanDetail(json) {
    console.log('asdasdasd ',json);
        var type;
        var duration;

        if(json.loanDto.type == '<%=Constant.LOAN_TYPE_BASED_ON_INITIAL_DEBT%>') type = 'Theo số dư nợ gốc';
        else if(json.loanDto.type == '<%=Constant.LOAN_TYPE_BASED_ON_CURRENT_DEBT%>') type = 'Theo số dư nợ giảm dần';

        if(json.loanDto.duration === '<%=Constant.DURATION_ONE_MONTH%>') duration = '1 tháng';
        else if(json.loanDto.duration === '<%=Constant.DURATION_TWO_MONTHS%>') duration = '2 tháng';
        else if(json.loanDto.duration === '<%=Constant.DURATION_THREE_MONTHS%>') duration = '3 tháng';
        else if(json.loanDto.duration === '<%=Constant.DURATION_ONE_YEAR%>') duration = '1 năm';

        document.getElementById("request-show-loan-amount").innerHTML = json.loanDto.amount;
        document.getElementById("request-show-loan-type").innerHTML = type;
        document.getElementById("request-show-loan-createdAt").innerHTML = json.loanDto.createdAt;
        document.getElementById("request-show-loan-duration").innerHTML = duration;

        var gender;

        if(json.debtorDto.gender) gender = 'Nam';
        else gender = 'Nữ';

        document.getElementById("request-show-debtor-name").innerHTML = json.debtorDto.name;
        document.getElementById("request-show-debtor-cccd").innerHTML = json.debtorDto.cccdNum;
        document.getElementById("request-show-debtor-phone").innerHTML = json.debtorDto.phone;
        document.getElementById("request-show-debtor-credit-point").innerHTML = json.debtorDto.creditPoint;
        document.getElementById("request-show-debtor-gender").innerHTML = gender;
        document.getElementById("request-show-debtor-email").innerHTML = json.debtorDto.email;
    }
</script>
<%--END CAPTURE CURRENT RECORD ID--%>
<%--=======================================================================================--%>
<%--LOAN LOCK/UNLOCK SCRIPT--%>
<script>
</script>

<script>
    function sendRequest(loanId, url) {
        $.ajax({
            url: url,
            method: "POST",
            data: {loanId: loanId},
            success: function (response) {
                // Extract data from the model map
                var data = response;
                currentId = null;
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