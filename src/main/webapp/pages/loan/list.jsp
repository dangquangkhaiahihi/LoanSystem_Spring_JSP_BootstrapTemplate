<%@ page import="com.example.demo.model.*" %>
<%@ page import="java.util.List" %>
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

    <title>Danh sách khoản cho vay</title>

    <!-- Custom fonts for this template-->
    <link href="../../vendor/fontawesome-free/css/all.min.css" rel="stylesheet" type="text/css">
    <link
            href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i"
            rel="stylesheet">

    <!-- Custom styles for this template-->
    <link href="../../css/sb-admin-2.min.css" rel="stylesheet">
    <!-- Custom styles for this page -->
    <link href="../../vendor/datatables/dataTables.bootstrap4.min.css" rel="stylesheet">
</head>

<body id="page-top">

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
                <h1 class="h3 mb-2 text-gray-800">Danh sách khoản cho vay</h1>

                <div class="card shadow mb-4">
                    <div class="card-body">
                        <%-- Form Search --%>
                        <form method="GET" action="/loan" modelAttribute="loanRequest">
                            <div style="display: flex;">
                                <div style="display: flex;flex:1;flex-direction: column;margin-right: 2rem">
                                    <div style="display: flex;flex: 1;flex-direction: column;max-width: 100%;margin-bottom: 1rem;">
                                        <label for="fromAmountStr">Từ số tiền</label>
                                        <input id="fromAmountStr" name="fromAmountStr" type="text"
                                               class="form-control floatInput"
                                               value=""
                                               placeholder="Nhập số tiền tìm kiếm"/>
                                    </div>
                                    <div style="display: flex;flex: 1;flex-direction: column;max-width: 100%;margin-bottom: 1rem;">
                                        <label for="fromCreatedDateStr">Từ ngày bắt đầu</label>
                                        <input id="fromCreatedDateStr" name="fromCreatedDateStr" type="date"
                                               class="form-control"
                                               value=""
                                               placeholder="Chọn từ ngày"/>
                                    </div>
                                    <div style="display: flex;flex: 1;flex-direction: column;max-width: 100%;margin-bottom: 1rem;">
                                        <label for="fromDeadlineStr">Từ hạn chót</label>
                                        <input id="fromDeadlineStr" name="fromDeadlineStr" type="date" class="form-control"
                                               value=""
                                               placeholder="Chọn từ ngày"/>
                                    </div>
                                    <div style="display: flex;flex: 1;flex-direction: column;max-width: 100%;margin-bottom: 1rem;">
                                        <label for="type">Loại cho vay</label>
                                        <input id="type" name="type" type="text" class="form-control"
                                               value=""
                                               placeholder="Chọn loại cho vay"/>
                                    </div>
                                </div>
                                <div style="display: flex;flex:1;flex-direction: column;">
                                    <div style="display: flex;flex: 1;flex-direction: column;max-width: 100%;margin-bottom: 1rem;">
                                        <label for="toAmountStr">Đến số tiền</label>
                                        <input id="toAmountStr" name="toAmountStr" type="text"
                                               class="form-control floatInput"
                                               value=""
                                               placeholder="Nhập số tiền tìm kiếm"/>
                                    </div>
                                    <div style="display: flex;flex: 1;flex-direction: column;max-width: 100%;margin-bottom: 1rem;">
                                        <label for="toCreatedDateStr">Đến ngày bắt đầu</label>
                                        <input id="toCreatedDateStr" name="toCreatedDateStr" type="date" class="form-control"
                                               value=""
                                               placeholder="Chọn đến ngày"/>
                                    </div>
                                    <div style="display: flex;flex: 1;flex-direction: column;max-width: 100%;margin-bottom: 1rem;">
                                        <label for="toDeadlineStr">Đến hạn chót</label>
                                        <input id="toDeadlineStr" name="toDeadlineStr" type="date" class="form-control"
                                               value=""
                                               placeholder="Đến hạn chót"/>
                                    </div>
                                    <div style="display: flex;flex: 1;flex-direction: column;max-width: 100%;margin-bottom: 1rem;">
                                        <label for="status">Trạng thái</label>
                                        <input id="status" name="status" type="text" class="form-control"
                                               value=""
                                               placeholder="Chọn trạng thái"/>
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
                                    <th>Hạn chót</th>
                                    <th>Trạng thái</th>
                                    <th>Hành động</th>
                                </tr>
                                </thead>
                                <tbody>
                                <%List<LoanDto> loanDtos = (List<LoanDto>) request.getAttribute("loanDtos");%>
                                <% for (LoanDto loanDto : loanDtos) { %>
                                <tr>
                                    <td><%= loanDto.getType() %>
                                    </td>
                                    <td><%= loanDto.getAmount() %>
                                    </td>
                                    <td><%= loanDto.getCreatedAt() %>
                                    </td>
                                    <td><%= loanDto.getDeadline() %>
                                    </td>
                                    <td><%= loanDto.getStatus() %>
                                    </td>
                                    <td></td>
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