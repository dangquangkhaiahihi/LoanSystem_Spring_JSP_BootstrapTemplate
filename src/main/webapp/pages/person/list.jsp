<%@ page import="java.util.List" %>
<%@ page import="com.example.demo.entity.PersonEntity" %>
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

    <title>Danh sách người nợ</title>

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
                <h1 class="h3 mb-2 text-gray-800">Danh sách người nợ</h1>

                <div class="card shadow mb-4">
                    <div class="card-body">
                        <%-- Form Search --%>
                        <form method="GET" action="/person" modelAttribute="personFilterRequest">
                            <div style="display: flex;">
                                <div style="display: flex;flex:1;flex-direction: column;margin-right: 2rem">

                                    <div style="display: flex;flex: 1;flex-direction: column;max-width: 100%;margin-bottom: 1rem;">
                                        <% String fromId = request.getParameter("fromId"); %>
                                        <label for="fromId">Từ ID</label>
                                        <input id="fromId" name="fromId" type="text"
                                               class="form-control"
                                               value="<%=fromId != null ? fromId : "" %>"
                                               placeholder="Từ ID"/>
                                    </div>
                                    <div style="display: flex;flex: 1;flex-direction: column;max-width: 100%;margin-bottom: 1rem;">
                                        <% String fromTotal = request.getParameter("fromTotal"); %>
                                        <label for="fromTotal">Từ tổng nợ</label>
                                        <input id="fromTotal" name="fromTotal" type="text"
                                               class="form-control"
                                               value="<%=fromTotal != null ? fromTotal : "" %>"
                                               placeholder="Từ tổng nợ"/>
                                    </div>
                                    <div style="display: flex;flex: 1;flex-direction: column;max-width: 100%;margin-bottom: 1rem;">
                                        <% String fromCreatedDateStr = request.getParameter("fromCreatedDateStr"); %>
                                        <label for="fromCreatedDateStr">Từ ngày tạo</label>
                                        <input id="fromCreatedDateStr" name="fromCreatedDateStr" type="datetime-local"
                                               class="form-control"
                                               value="<%=fromCreatedDateStr != null ? fromCreatedDateStr : "" %>"
                                               placeholder="Từ ngày tạo"/>
                                    </div>
                                    <div style="display: flex;flex: 1;flex-direction: column;max-width: 100%;margin-bottom: 1rem;">
                                        <% String fromLastModifiedDateStr = request.getParameter("fromLastModifiedDateStr"); %>
                                        <label for="fromLastModifiedDateStr">Từ ngày cập nhật</label>
                                        <input id="fromLastModifiedDateStr" name="fromLastModifiedDateStr" type="datetime-local"
                                               class="form-control"
                                               value="<%=fromLastModifiedDateStr != null ? fromLastModifiedDateStr : "" %>"
                                               placeholder="Từ ngày cập nhật"/>
                                    </div>
                                    <div style="display: flex;flex: 1;flex-direction: column;max-width: 100%;margin-bottom: 1rem;">
                                        <% String name = request.getParameter("name"); %>
                                        <label for="name">Tên</label>
                                        <input id="name" name="name" type="text"
                                               class="form-control"
                                               value="<%=name != null ? name : "" %>"
                                               placeholder="Tên"/>
                                    </div>
                                    <div style="display: flex;flex: 1;flex-direction: column;max-width: 100%;margin-bottom: 1rem;">
                                        <% String phone = request.getParameter("phone"); %>
                                        <label for="phone">Số điện thoại</label>
                                        <input id="phone" name="phone" type="text"
                                               class="form-control"
                                               value="<%=phone != null ? phone : "" %>"
                                               placeholder="Số điện thoại"/>
                                    </div>
                                </div>
                                <div style="display: flex;flex:1;flex-direction: column;">
                                    <div style="display: flex;flex: 1;flex-direction: column;max-width: 100%;margin-bottom: 1rem;">
                                        <% String toId = request.getParameter("toId"); %>
                                        <label for="toId">Đến ID</label>
                                        <input id="toId" name="toId" type="text"
                                               class="form-control"
                                               value="<%=toId != null ? toId : "" %>"
                                               placeholder="Đến ID"/>
                                    </div>
                                    <div style="display: flex;flex: 1;flex-direction: column;max-width: 100%;margin-bottom: 1rem;">
                                        <% String toTotal = request.getParameter("toTotal"); %>
                                        <label for="toTotal">Đến tổng nợ</label>
                                        <input id="toTotal" name="toTotal" type="text"
                                               class="form-control"
                                               value="<%=toTotal != null ? toTotal : "" %>"
                                               placeholder="Đến tổng nợ"/>
                                    </div>
                                    <div style="display: flex;flex: 1;flex-direction: column;max-width: 100%;margin-bottom: 1rem;">
                                        <% String toCreatedDateStr = request.getParameter("toCreatedDateStr"); %>
                                        <label for="toCreatedDateStr">Đến ngày tạo</label>
                                        <input id="toCreatedDateStr" name="toCreatedDateStr" type="datetime-local"
                                               class="form-control"
                                               value="<%=toCreatedDateStr != null ? toCreatedDateStr : "" %>"
                                               placeholder="Đến ngày tạo"/>
                                    </div>
                                    <div style="display: flex;flex: 1;flex-direction: column;max-width: 100%;margin-bottom: 1rem;">
                                        <% String toLastModifiedDateStr = request.getParameter("toLastModifiedDateStr"); %>
                                        <label for="toLastModifiedDateStr">Đến ngày cập nhật</label>
                                        <input id="toLastModifiedDateStr" name="toLastModifiedDateStr" type="datetime-local"
                                               class="form-control"
                                               value="<%=toLastModifiedDateStr != null ? toLastModifiedDateStr : "" %>"
                                               placeholder="Đến ngày cập nhật"/>
                                    </div>
                                    <div style="display: flex;flex: 1;flex-direction: column;max-width: 100%;margin-bottom: 1rem;">
                                        <% String address = request.getParameter("address"); %>
                                        <label for="address">Địa chỉ</label>
                                        <input id="address" name="address" type="text"
                                               class="form-control"
                                               value="<%=address != null ? address : "" %>"
                                               placeholder="Địa chỉ"/>
                                    </div>
                                    <div style="display: flex;flex: 1;flex-direction: column;max-width: 100%;margin-bottom: 1rem;">
                                        <% String email = request.getParameter("email"); %>
                                        <label for="email">Email</label>
                                        <input id="email" name="email" type="text"
                                               class="form-control"
                                               value="<%=email != null ? email : "" %>"
                                               placeholder="Email"/>
                                    </div>
                                </div>
                            </div>
                            <div style="text-align: center;">
                                <a id="button-clear-filter" href="#" class="btn btn-primary" data-toggle="modal"
                                   style="background-color: gray; border-color: gray">
                                    <span class="text">Xóa bộ lọc</span>
                                </a>
                                <input class="btn btn-primary" type="submit" value="Tìm kiếm"/>
                                <a href="#" class="btn btn-primary" data-toggle="modal"
                                   style="background-color: orange; border-color: orange"
                                   data-target="#loan-modal-add">
                                    <span class="text">Thêm người nợ</span>
                                </a>
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
                                        <th>Id</th>
                                        <th>Tên</th>
                                        <th>Địa chỉ</th>
                                        <th>SĐT</th>
                                        <th>Email</th>
                                        <th>Tổng nợ</th>
                                        <th>Ngày tạo</th>
                                        <th>Ngày cập nhật</th>
                                        <th>Hành động</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <%List<PersonEntity> resultLst = (List<PersonEntity>) request.getAttribute("resultLst");%>
                                    <% for (PersonEntity person : resultLst) { %>
                                        <tr>
                                            <td><%= person.getId() %></td>
                                            <td><%= person.getName() %></td>
                                            <td><%= person.getAddress() %></td>
                                            <td><%= person.getPhone() %></td>
                                            <td><%= person.getEmail() %></td>
                                            <td><%= person.getTotalAmount() %></td>
                                            <td><%= person.getCreatedDateStr() %></td>
                                            <td><%= person.getLastModifiedDateStr() %></td>
                                            <td>String</td>
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

<%--=======================================================================================--%>
<%--CAPTURE CURRENT RECORD ID--%>
<script>
    var currentId;

    function captrueCurrentId(loanId) {
        currentId = loanId
    }
</script>
<%--END CAPTURE CURRENT RECORD ID--%>
<%--=======================================================================================--%>
<%--LOAN LOCK/UNLOCK SCRIPT--%>

<script>
    const fromId = document.getElementById("fromId");
    const toId = document.getElementById("toId");
    const fromTotal = document.getElementById("fromTotal");
    const toTotal = document.getElementById("toTotal");
    const phone = document.getElementById("phone");

    const lst = [];
    lst.push(fromId);
    lst.push(toId);
    lst.push(fromTotal);
    lst.push(toTotal);
    lst.push(phone);

    lst.forEach(function(element) {
        element.addEventListener("input", function(event) {
            const numberPattern = /^[0-9]*$/;

            if (!numberPattern.test(event.target.value)) {
              event.preventDefault();
              element.value = element.value.replace(/[^0-9]/g, '');
            }
          });
    });
</script>

<script>
    const button = document.getElementById("button-clear-filter");
    button.addEventListener("click",function(event) {
        document.getElementById("name").value = "";
        document.getElementById("address").value = "";
        document.getElementById("phone").value = "";
        document.getElementById("email").value = "";
        document.getElementById("fromId").value = "";
        document.getElementById("toId").value = "";
        document.getElementById("fromTotal").value = "";
        document.getElementById("toTotal").value = "";
        document.getElementById("fromCreatedDateStr").value = "";
        document.getElementById("toCreatedDateStr").value = "";
        document.getElementById("fromLastModifiedDateStr").value = "";
        document.getElementById("toLastModifiedDateStr").value = "";
    });
</script>

</body>
</html>