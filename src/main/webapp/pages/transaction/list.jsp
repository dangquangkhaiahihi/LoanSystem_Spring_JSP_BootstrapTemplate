<%@ page import="com.example.demo.model.TransactionDto" %>
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

    <title>Danh sách đã cho vay</title>

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
                <h1 class="h3 mb-2 text-gray-800">Xem các khoản đã cho vay</h1>

                <!-- DataTales Example -->
                <div class="card shadow mb-4">
                    <div class="card-body">
                        <div class="table-responsive">
                            <table class="table table-bordered" id="dataTable" style="width: 100%;mso-cellspacing: 0; text-align: center;">
                                <thead>
                                    <tr>
                                        <th>Số tiền</th>
                                        <th>Mô tả</th>
                                        <th>Thời gian</th>
                                    </tr>
                                </thead>
                                <tbody>
                                <%List<TransactionDto> transactions = (List<TransactionDto>) request.getAttribute("transactions");%>
                                <% for (TransactionDto transaction : transactions) { %>
                                <tr>
                                    <td><%= transaction.getAmountStr() %></td>
                                    <td><%= transaction.getDesc() %></td>
                                    <td><%= transaction.getCreatedAtStr() %></td>
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
</body>

</html>