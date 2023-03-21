<%@ page import="java.util.List" %>
<%@ page import="com.example.demo.common.Utils" %>
<%@ page import="com.google.gson.Gson" %>
<%@ page import="com.example.demo.model.PersonDTO" %>
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

                <!-- DataTales -->
                <div class="card shadow mb-4">
                    <div class="card-body">
                        <form method="GET" action="/person" modelAttribute="personFilterRequest">
                        <div style="text-align: center;">
                            <a id="button-clear-filter" href="#" class="btn btn-primary" data-toggle="modal"
                               style="background-color: gray; border-color: gray">
                                <span class="text">Xóa bộ lọc</span>
                            </a>
                            <input class="btn btn-primary" type="submit" value="Tìm kiếm"/>
                            <a href="#" class="btn btn-primary" data-toggle="modal"
                               style="background-color: orange; border-color: orange"
                               data-target="#modal-add-person">
                                <span class="text">Thêm người nợ</span>
                            </a>
                        </div>
                        <div class="table-responsive">
                            <table class="table table-bordered" id="dataTable" style="width: 100%;mso-cellspacing: 0">
                                <thead>
                                    <tr>
                                        <th>
                                            <% String fromId = request.getParameter("fromId"); %>
                                            <% String toId = request.getParameter("toId"); %>
                                            <div style="text-align: center;" onClick="divStopPropagation(event)">
                                                ID
                                                <div class="row">
                                                    <div class="col-6">
                                                        <label>Từ</label>
                                                        <input id="fromId" name="fromId" type="text"
                                                              class="form-control"
                                                              value="<%=fromId != null ? fromId : "" %>"
                                                              placeholder="Từ ID"/>
                                                    </div>
                                                    <div class="col-6">
                                                        <label>Đến</label>
                                                        <input id="toId" name="toId" type="text"
                                                               class="form-control"
                                                               value="<%=toId != null ? toId : "" %>"
                                                               placeholder="Đến ID"/>
                                                   </div>
                                                </div>
                                            </div>
                                        </th>
                                        <th>
                                            <% String name = request.getParameter("name"); %>
                                            <div style="text-align: center;" onClick="divStopPropagation(event)">
                                                Tên
                                                <div class="row">
                                                    <div class="col-12">
                                                       <input id="name" name="name" type="text"
                                                              class="form-control"
                                                              value="<%=name != null ? name : "" %>"
                                                              placeholder="Tên"/>
                                                    </div>
                                                </div>
                                            </div>
                                        </th>
                                        <th>
                                            <% String address = request.getParameter("address"); %>
                                            <div style="text-align: center;" onClick="divStopPropagation(event)">
                                                Địa chỉ
                                                <div class="row">
                                                    <div class="col-12">
                                                       <input id="address" name="address" type="text"
                                                          class="form-control"
                                                          value="<%=address != null ? address : "" %>"
                                                          placeholder="Địa chỉ"/>
                                                    </div>
                                                </div>
                                            </div>
                                        </th>
                                        <th>
                                            <% String phone = request.getParameter("phone"); %>
                                            <div style="text-align: center;" onClick="divStopPropagation(event)">
                                                SĐT
                                                <div class="row">
                                                    <div class="col-12">
                                                        <input id="phone" name="phone" type="text"
                                                           class="form-control"
                                                           value="<%=phone != null ? phone : "" %>"
                                                           placeholder="Sđt"/>
                                                    </div>
                                                </div>
                                            </div>
                                        </th>
                                        <th>
                                            <% String email = request.getParameter("email"); %>
                                            <div style="text-align: center;" onClick="divStopPropagation(event)">
                                                Email
                                                <div class="row">
                                                    <div class="col-12">
                                                       <input id="email" name="email" type="text"
                                                          class="form-control"
                                                          value="<%=email != null ? email : "" %>"
                                                          placeholder="Email"/>
                                                    </div>
                                                </div>
                                            </div>
                                        </th>
                                        <th>
                                            <% String fromTotal = request.getParameter("fromTotal"); %>
                                            <% String toTotal = request.getParameter("toTotal"); %>
                                            <div style="text-align: center;" onClick="divStopPropagation(event)">
                                                Tổng nợ
                                                <div class="row">
                                                    <div class="col-6">
                                                        <label>Từ</label>
                                                        <input id="fromTotal" name="fromTotal" type="text"
                                                           class="form-control"
                                                           value="<%=fromTotal != null ? fromTotal : "" %>"
                                                           placeholder="Từ tổng nợ"/>
                                                    </div>
                                                    <div class="col-6">
                                                        <label>Đến</label>
                                                        <input id="toTotal" name="toTotal" type="text"
                                                           class="form-control"
                                                           value="<%=toTotal != null ? toTotal : "" %>"
                                                           placeholder="Đến tổng nợ"/>
                                                   </div>
                                                </div>
                                            </div>
                                        </th>
                                        <th>
                                            <% String toCreatedDateStr = request.getParameter("toCreatedDateStr"); %>
                                            <% String fromCreatedDateStr = request.getParameter("fromCreatedDateStr"); %>
                                            <div style="text-align: center;" onClick="divStopPropagation(event)">
                                                Ngày tạo
                                                <div class="row">
                                                    <div class="col-6">
                                                        <label>Từ</label>
                                                        <input id="toCreatedDateStr" name="toCreatedDateStr" type="datetime-local"
                                                           class="form-control"
                                                           value="<%=toCreatedDateStr != null ? toCreatedDateStr : "" %>"
                                                           placeholder="Đến ngày tạo"/>
                                                    </div>
                                                    <div class="col-6">
                                                        <label>Đến</label>
                                                        <input id="fromCreatedDateStr" name="fromCreatedDateStr" type="datetime-local"
                                                           class="form-control"
                                                           value="<%=fromCreatedDateStr != null ? fromCreatedDateStr : "" %>"
                                                           placeholder="Từ ngày tạo"/>
                                                   </div>
                                                </div>
                                            </div>
                                        </th>
                                        <th>
                                            <% String toLastModifiedDateStr = request.getParameter("toLastModifiedDateStr"); %>
                                            <% String fromLastModifiedDateStr = request.getParameter("fromLastModifiedDateStr"); %>
                                            <div style="text-align: center;" onClick="divStopPropagation(event)">
                                                Ngày cập nhật
                                                <div class="row">
                                                    <div class="col-6">
                                                        <label>Từ</label>
                                                        <input id="fromLastModifiedDateStr" name="fromLastModifiedDateStr" type="datetime-local"
                                                           class="form-control"
                                                           value="<%=fromLastModifiedDateStr != null ? fromLastModifiedDateStr : "" %>"
                                                           placeholder="Từ ngày cập nhật"/>
                                                    </div>
                                                    <div class="col-6">
                                                        <label>Đến</label>
                                                        <input id="toLastModifiedDateStr" name="toLastModifiedDateStr" type="datetime-local"
                                                           class="form-control"
                                                           value="<%=toLastModifiedDateStr != null ? toLastModifiedDateStr : "" %>"
                                                           placeholder="Đến ngày cập nhật"/>
                                                   </div>
                                                </div>
                                            </div>
                                        </th>
                                        <th>Hành động</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <%Gson gson = Utils.getGson();%>
                                    <%List<PersonDTO> resultDtos = (List<PersonDTO>) request.getAttribute("resultDtos");%>
                                    <% for (PersonDTO person : resultDtos) { %>
                                        <% String personJson = gson.toJson(person); %>
                                    <tr style=
                                        <% if (person.getTotalAmount() < 0 ) {%>
                                            "color: red;"
                                        <% } %>
                                    >
                                            <td><%= person.getId() %></td>
                                            <td><%= person.getName() %></td>
                                            <td><%= person.getAddress() %></td>
                                            <td><%= person.getPhone() %></td>
                                            <td><%= person.getEmail() %></td>
                                            <td><%= person.getTotalAmount() %></td>
                                            <td><%= person.getCreatedDateStr() %></td>
                                            <td><%= person.getLastModifiedDateStr() %></td>
                                            <td>
                                                <div style="display: flex">
                                                    <div>
                                                        <button
                                                                class="btn btn-transaprent btn-icon btn-sm"
                                                                data-tooltip="tooltip" title="Xem chi tiết"
                                                                data-toggle="modal" data-target="#modal-list-ticket"
                                                                onclick='event.preventDefault();captruePersonId(<%= person.getId() %>, "<%= person.getName() %>")'>
                                                            <img src="../../img/icon/24x24-information-circle.svg" alt=""
                                                                 class="btn-icon"/>
                                                        </button>
                                                    </div>
                                                    <div>
                                                        <button class="btn btn-transaprent btn-icon btn-sm"
                                                                data-tooltip="tooltip" title="Chỉnh sửa"
                                                                data-toggle="modal" data-target="#modal-edit-person"
                                                                onclick='event.preventDefault();captrueSelectedEdit(<%=personJson%>)'>
                                                            <img src="../../img/icon/24x24-edit.svg" alt=""
                                                                 class="btn-icon"/>
                                                        </button>
                                                    </div>
                                                    <div>
                                                        <button class="btn btn-transaprent btn-icon btn-sm"
                                                                data-tooltip="tooltip" title="Thêm phiếu nợ"
                                                                data-toggle="modal" data-target="#modal-add-ticket"
                                                                onclick='event.preventDefault();captruePersonIdTicketAdd(<%= person.getId() %>)'>
                                                            <img src="../../img/icon/24x24-plus-circle.svg" alt=""
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
                    </form>
                    </div>
                </div>

            </div>

        </div>
        <!-- End of Main Content -->

        <%-- Import footer--%>
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
<script src="../../js/person/used-in-person.js"></script>

<%--Import add modal--%>
<%@ include file="../../modal/person/modal-add.jsp" %>

<%--Import edit modal--%>
<%@ include file="../../modal/person/modal-edit.jsp" %>

<%--Import list tickets modal--%>
<%@ include file="../../modal/ticket/modal-list.jsp" %>

<%--Import add tickets modal--%>
<%@ include file="../../modal/ticket/modal-add.jsp" %>

<%--Import add tickets modal in modal--%>
<%@ include file="../../modal/ticket/modal-add-ticket-inModal.jsp" %>

<script>
    function captrueSelectedEdit(json) {
        document.getElementById("id-person-edit").value = json.id;
        document.getElementById("name-person-edit").value = json.name;
        document.getElementById("address-person-edit").value = json.address;
        document.getElementById("phone-person-edit").value = json.phone;
        document.getElementById("email-person-edit").value = json.email;
    }
</script>

<script>
    function captruePersonIdTicketAdd(personId) {
        console.log('ticket add', personId);
        document.getElementById("person-id-ticket-add").value = personId;
    }
</script>

<script>
    //Make these field con only input number
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
    //Clear filter button
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

<script>
    function captruePersonId (receivedPersonId, receivedPersonName){
        $.ajax({
            url: '/ticket',
            method: "GET",
            data: {
                personId : receivedPersonId,
            },
            success: function (response) {
                // Extract data from the model map
                //var data = response;
                //setSuccessMessage('Thêm người nợ thành công');
                //$('#modal-success-message').modal('show');
                setUpTableTickets(response, receivedPersonName, receivedPersonId);
            }
        });
    }
</script>

<script>
    var modalBodyTicketList = document.querySelector('#modal-body-ticket-list');

    //Close modal => clear all rows
    document.getElementById("button-close-list-tickets").addEventListener("click", function () {
        while (modalBodyTicketList.firstChild) {
          modalBodyTicketList.removeChild(modalBodyTicketList.firstChild);
        }
    });

    document.addEventListener('keydown', function(event) {
        if (event.key === "Escape") {
            while (modalBodyTicketList.firstChild) {
              modalBodyTicketList.removeChild(modalBodyTicketList.firstChild);
            }
        }
    });

    function divStopPropagation(event) {
        event.stopPropagation(); // Stops the event from bubbling up to the parent element
    }
</script>

</body>
</html>