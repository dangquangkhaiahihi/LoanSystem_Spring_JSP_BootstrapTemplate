

//Call api to get ticket list -> open modal
function setUpTableTickets (ticketArr, personName, personId, ticketFilterRequest){
    if(personName) {
        document.getElementById("modal-list-tickets-title").innerHTML = personName;
    }

    // Create a new button wrapper element
    var buttonWrapper = document.createElement('div');
    buttonWrapper.style.cssText = 'text-align: center;';

    // Create a new CLEAR FILTER button element
        var clearTicketFilterButton = document.createElement('a');
        clearTicketFilterButton.id = 'button-clear-ticket-filter';
        clearTicketFilterButton.href = '#';
        clearTicketFilterButton.className = 'btn btn-primary';
        clearTicketFilterButton.style.cssText = 'background-color: gray; border-color: gray;margin-right: 10px;';
        clearTicketFilterButton.addEventListener("click", function () {
            while (modalBodyTicketList.firstChild) {
              modalBodyTicketList.removeChild(modalBodyTicketList.firstChild);
            }
            setUpTableTickets(ticketArr, null, personId, null);
        });

        var clearTicketFilterSpan = document.createElement('span');
        clearTicketFilterSpan.className = 'text';

        var contentClearTicketFilterSpan = document.createTextNode("Xóa bộ lọc");

        clearTicketFilterSpan.appendChild(contentClearTicketFilterSpan);
        clearTicketFilterButton.appendChild(clearTicketFilterSpan);

    // Create a new FILTER TICKET button element
        var filterTicketButton = document.createElement('input');
        filterTicketButton.id = 'button-filter-ticket';
        filterTicketButton.className = 'btn btn-primary';
        filterTicketButton.value = 'Tìm kiếm';
        filterTicketButton.addEventListener("click", function () {
            console.log("add call");
            sendRequestFilterTicket('/ticket');
        });

    // Create a new ADD TICKET button element
        var addTicketButton = document.createElement('a');
        addTicketButton.href = '#';
        addTicketButton.className = 'btn btn-primary';
        addTicketButton.setAttribute("data-toggle", "modal");
        addTicketButton.setAttribute("data-target", "#modal-add-ticket-inModal");
        addTicketButton.style.cssText = 'background-color: orange; border-color: orange;margin-left: 10px;';

        var addTicketSpan = document.createElement('span');
        addTicketSpan.className = 'text';

        var contentAddTicketSpan = document.createTextNode("Tạo phiếu nợ");

        addTicketSpan.appendChild(contentAddTicketSpan);
        addTicketButton.appendChild(addTicketSpan);
        addTicketButton.addEventListener("click", function () {
            console.log("Tạo phiếu nợ trong modal",personId);
            document.getElementById("person-id-ticket-add-inModal").value = personId;
        });

    buttonWrapper.appendChild(clearTicketFilterButton);
    buttonWrapper.appendChild(filterTicketButton);
    buttonWrapper.appendChild(addTicketButton);

    modalBodyTicketList.appendChild(buttonWrapper);

    generateTicketDataTable(ticketArr,personId,ticketFilterRequest);

    $('#modal-list-tickets').modal('show');
    $('#dataTable-ticket-list').DataTable();
}

//Generate Ticket Data Table -> generateTicketDataTable, generateIconButtonListTickets, generateStyleMinus, generateStylePlus
function generateTicketDataTable (ticketArr,personId, ticketFilterRequest) {
    // Create a new card body element
    var cardBody = document.createElement('div');
    cardBody.className = 'card-body';

    // Create a new table-responsive element
    var tableResponsive = document.createElement('div');
    tableResponsive.className = 'table-responsive';

    // Create a new table element
    var table = document.createElement('table');
    table.className = 'table table-bordered data-table-list-tickets';
    table.id = 'dataTable-ticket-list';
    table.style.width = '100%';
    table.style.msoCellSpacing = '0';

    // Create a new table header element
    var thead = document.createElement('thead');
    var theadRow = document.createElement('tr');
    var th1 = document.createElement('th');

    // Create the outer div element
    var outerDiv1 = document.createElement("div");
    outerDiv1.style.textAlign = "center";
    outerDiv1.addEventListener("click", divStopPropagation);

    // Create the inner text element
    var innerText1 = document.createTextNode("ID");
    outerDiv1.appendChild(innerText1);

    // Create the row div element
    var rowDiv1 = document.createElement("div");
    rowDiv1.classList.add("row");
    outerDiv1.appendChild(rowDiv1);

    // Create the column div element
    var colDiv1 = document.createElement("div");
    colDiv1.classList.add("col-12");
    rowDiv1.appendChild(colDiv1);

    // Create the input element
    var inputIdTicket = document.createElement("input");
    inputIdTicket.id = "idTicket";
    inputIdTicket.name = "idTicket";
    inputIdTicket.type = "text";
    inputIdTicket.classList.add("form-control");
    inputIdTicket.addEventListener("input", function(event) {
        const numberPattern = /^[0-9]*$/;

        if (!numberPattern.test(event.target.value)) {
          event.preventDefault();
          inputIdTicket.value = inputIdTicket.value.replace(/[^0-9]/g, '');
        }
    });
    if(ticketFilterRequest) inputIdTicket.value = ticketFilterRequest.idTicket;
    inputIdTicket.placeholder = "ID";
    colDiv1.appendChild(inputIdTicket);

    th1.appendChild(outerDiv1);

    var th2 = document.createElement('th');

    // Create the outer div element
    var outerDiv2 = document.createElement("div");
    outerDiv2.style.textAlign = "center";
    outerDiv2.addEventListener("click", divStopPropagation);

    // Create the inner text element
    var innerText2 = document.createTextNode("Ghi chú");
    outerDiv2.appendChild(innerText2);

    // Create the row div element
    var rowDiv2 = document.createElement("div");
    rowDiv2.classList.add("row");
    outerDiv2.appendChild(rowDiv2);

    // Create the column div element
    var colDiv2 = document.createElement("div");
    colDiv2.classList.add("col-12");
    rowDiv2.appendChild(colDiv2);

    // Create the input element
    var inputNoteTicket = document.createElement("input");
    inputNoteTicket.id = "noteTicket";
    inputNoteTicket.name = "noteTicket";
    inputNoteTicket.type = "text";
    inputNoteTicket.classList.add("form-control");
    if(ticketFilterRequest) inputNoteTicket.value = ticketFilterRequest.noteTicket;
    inputNoteTicket.placeholder = "Ghi chú";
    colDiv2.appendChild(inputNoteTicket);

    th2.appendChild(outerDiv2);

    var th3 = document.createElement('th');

    // Create the outer div element
    var outerDiv3 = document.createElement("div");
    outerDiv3.style.textAlign = "center";
    outerDiv3.addEventListener("click", divStopPropagation);

    // Create the inner text element
    var innerText3 = document.createTextNode("Loại nợ");
    outerDiv3.appendChild(innerText3);

    // Create the row div element
    var rowDiv3 = document.createElement("div");
    rowDiv3.classList.add("row");
    outerDiv3.appendChild(rowDiv3);

    // Create the column div element
    var colDiv3 = document.createElement("div");
    colDiv3.classList.add("col-12");
    rowDiv3.appendChild(colDiv3);

    // Create the input element
    var selectIsPlusTicket = document.createElement('select');
    selectIsPlusTicket.id = 'isPlusTicket';
    selectIsPlusTicket.name = 'isPlusTicket';
    selectIsPlusTicket.className = 'form-control';

    // Create a new oprion Tất cả element
    var option1 = document.createElement('option');
    option1.value = '0';
    if(ticketFilterRequest && ticketFilterRequest.isPlusTicket == 0){
        option1.selected = true;
    }
    var contentOption1 = document.createTextNode("Tất cả");
    option1.appendChild(contentOption1);

    // Create a new oprion Tất cả element
    var option2 = document.createElement('option');
    option2.value = '1';
    if(ticketFilterRequest && ticketFilterRequest.isPlusTicket == 1){
        option2.selected = true;
    }
    var contentOption2 = document.createTextNode("+");
    option2.appendChild(contentOption2);

    // Create a new oprion Tất cả element
    var option3 = document.createElement('option');
    option3.value = '2';
    if(ticketFilterRequest && ticketFilterRequest.isPlusTicket == 2){
        option3.selected = true;
    }
    var contentOption3 = document.createTextNode("-");
    option3.appendChild(contentOption3);

    selectIsPlusTicket.appendChild(option1);
    selectIsPlusTicket.appendChild(option2);
    selectIsPlusTicket.appendChild(option3);

    colDiv3.appendChild(selectIsPlusTicket);

    th3.appendChild(outerDiv3);


    var th4 = document.createElement('th');

    // Create the outer div element
    var outerDiv4 = document.createElement("div");
    outerDiv4.style.textAlign = "center";
    outerDiv4.addEventListener("click", divStopPropagation);

    // Create the inner text element
    var innerText4 = document.createTextNode("Số tiền");
    outerDiv4.appendChild(innerText4);

    // Create the row div element
    var rowDiv4 = document.createElement("div");
    rowDiv4.classList.add("row");
    outerDiv4.appendChild(rowDiv4);

    // Create the first column div element
    var col1Div4 = document.createElement("div");
    col1Div4.classList.add("col-6");
    rowDiv4.appendChild(col1Div4);

    // Create the label element for the first column
    var label14 = document.createElement("label");
    label14.textContent = "Từ";
    col1Div4.appendChild(label14);

    // Create the input element for the first column
    var inputAmountTicket = document.createElement("input");
    inputAmountTicket.id = "fromAmountTicket";
    inputAmountTicket.name = "fromAmountTicket";
    inputAmountTicket.type = "text";
    inputAmountTicket.classList.add("form-control");
    inputAmountTicket.addEventListener("input", function(event) {
        const numberPattern = /^[0-9]*$/;

        if (!numberPattern.test(event.target.value)) {
          event.preventDefault();
          inputAmountTicket.value = inputAmountTicket.value.replace(/[^0-9]/g, '');
        }
    });
    if(ticketFilterRequest) inputAmountTicket.value = ticketFilterRequest.fromAmountTicket;

    inputAmountTicket.placeholder = "Từ số tiền";
    col1Div4.appendChild(inputAmountTicket);

    // Create the second column div element
    var col2Div4 = document.createElement("div");
    col2Div4.classList.add("col-6");
    rowDiv4.appendChild(col2Div4);

    // Create the label element for the second column
    var label24 = document.createElement("label");
    label24.textContent = "Đến";
    col2Div4.appendChild(label24);

    // Create the input element for the second column
    var inputToAmountTicket = document.createElement("input");
    inputToAmountTicket.id = "toAmountTicket";
    inputToAmountTicket.name = "toAmountTicket";
    inputToAmountTicket.type = "text";
    inputToAmountTicket.classList.add("form-control");
    inputToAmountTicket.addEventListener("input", function(event) {
        const numberPattern = /^[0-9]*$/;

        if (!numberPattern.test(event.target.value)) {
          event.preventDefault();
          inputToAmountTicket.value = inputToAmountTicket.value.replace(/[^0-9]/g, '');
        }
    });
    if(ticketFilterRequest) inputToAmountTicket.value = ticketFilterRequest.toAmountTicket;

    inputToAmountTicket.placeholder = "Đến số tiền";
    col2Div4.appendChild(inputToAmountTicket);

    th4.appendChild(outerDiv4);

    var th5 = document.createElement('th');

    // Create the outer div element
    var outerDiv5 = document.createElement("div");
    outerDiv5.style.textAlign = "center";
    outerDiv5.addEventListener("click", divStopPropagation);

    // Create the inner text element
    var innerText5 = document.createTextNode("Ngày lập phiếu");
    outerDiv5.appendChild(innerText5);

    // Create the row div element
    var rowDiv5 = document.createElement("div");
    rowDiv5.classList.add("row");
    outerDiv5.appendChild(rowDiv5);

    // Create the first column div element
    var col1Div5 = document.createElement("div");
    col1Div5.classList.add("col-6");
    rowDiv5.appendChild(col1Div5);

    // Create the label element for the first column
    var label15 = document.createElement("label");
    label15.textContent = "Từ";
    col1Div5.appendChild(label15);

    // Create the input element for the first column
    var inputDateOfTransStrTicket = document.createElement("input");
    inputDateOfTransStrTicket.id = "fromDateOfTransStrTicket";
    inputDateOfTransStrTicket.name = "fromDateOfTransStrTicket";
    inputDateOfTransStrTicket.type = "datetime-local";
    inputDateOfTransStrTicket.classList.add("form-control");
    if(ticketFilterRequest) inputDateOfTransStrTicket.value = ticketFilterRequest.fromDateOfTransStrTicket;

    col1Div5.appendChild(inputDateOfTransStrTicket);

    // Create the second column div element
    var col2Div5 = document.createElement("div");
    col2Div5.classList.add("col-6");
    rowDiv5.appendChild(col2Div5);

    // Create the label element for the second column
    var label25 = document.createElement("label");
    label25.textContent = "Đến";
    col2Div5.appendChild(label25);

    // Create the input element for the second column
    var inputToDateOfTransStrTicket = document.createElement("input");
    inputToDateOfTransStrTicket.id = "toDateOfTransStrTicket";
    inputToDateOfTransStrTicket.name = "toDateOfTransStrTicket";
    inputToDateOfTransStrTicket.type = "datetime-local";
    inputToDateOfTransStrTicket.classList.add("form-control");
    if(ticketFilterRequest) inputToDateOfTransStrTicket.value = ticketFilterRequest.toDateOfTransStrTicket;

    col2Div5.appendChild(inputToDateOfTransStrTicket);

    th5.appendChild(outerDiv5);

    var th6 = document.createElement('th');

    // Create the outer div element
    var outerDiv6 = document.createElement("div");
    outerDiv6.style.textAlign = "center";
    outerDiv6.addEventListener("click", divStopPropagation);

    // Create the inner text element
    var innerText6 = document.createTextNode("Chỉnh sửa lần cuối");
    outerDiv6.appendChild(innerText6);

    // Create the row div element
    var rowDiv6 = document.createElement("div");
    rowDiv6.classList.add("row");
    outerDiv6.appendChild(rowDiv6);

    // Create the first column div element
    var col1Div6 = document.createElement("div");
    col1Div6.classList.add("col-6");
    rowDiv6.appendChild(col1Div6);

    // Create the label element for the first column
    var label16 = document.createElement("label");
    label16.textContent = "Từ";
    col1Div6.appendChild(label16);

    // Create the input element for the first column
    var inputLastModifiedDateStrTicket = document.createElement("input");
    inputLastModifiedDateStrTicket.id = "fromLastModifiedDateStrTicket";
    inputLastModifiedDateStrTicket.name = "fromLastModifiedDateStrTicket";
    inputLastModifiedDateStrTicket.type = "datetime-local";
    inputLastModifiedDateStrTicket.classList.add("form-control");
    if(ticketFilterRequest) inputLastModifiedDateStrTicket.value = ticketFilterRequest.fromLastModifiedDateStrTicket;

    col1Div6.appendChild(inputLastModifiedDateStrTicket);

    // Create the second column div element
    var col2Div6 = document.createElement("div");
    col2Div6.classList.add("col-6");
    rowDiv6.appendChild(col2Div6);

    // Create the label element for the second column
    var label26 = document.createElement("label");
    label26.textContent = "Đến";
    col2Div6.appendChild(label26);

    // Create the input element for the second column
    var inputToLastModifiedDateStrTicket = document.createElement("input");
    inputToLastModifiedDateStrTicket.id = "toLastModifiedDateStrTicket";
    inputToLastModifiedDateStrTicket.name = "toLastModifiedDateStrTicket";
    inputToLastModifiedDateStrTicket.type = "datetime-local";
    inputToLastModifiedDateStrTicket.classList.add("form-control");
    if(ticketFilterRequest) inputToLastModifiedDateStrTicket.value = ticketFilterRequest.toLastModifiedDateStrTicket;

    col2Div6.appendChild(inputToLastModifiedDateStrTicket);

    th6.appendChild(outerDiv6);

    var th7 = document.createElement('th');
    th7.textContent = 'Hành động';
    th7.style.cssText = 'text-align: center;';

    theadRow.appendChild(th1);
    theadRow.appendChild(th2);
    theadRow.appendChild(th3);
    theadRow.appendChild(th4);
    theadRow.appendChild(th5);
    theadRow.appendChild(th6);
    theadRow.appendChild(th7);
    thead.appendChild(theadRow);

    table.appendChild(thead);

    var tbody = document.createElement('tbody');

    // Loop through the data and generate table rows
    for (var i = 0; i < ticketArr.length; i++) {
        var row = document.createElement('tr');

        var type = '+';
        if(!ticketArr[i].isPlus) {
            type = '-';
        }

        if(ticketArr[i].isPlus){
            row.setAttribute('style', 'color: green;');
        } else {
            row.setAttribute('style', 'color: red;');
        }

        row.innerHTML = '<td>' + ticketArr[i].id + '</td>' +
                        '<td>' + ticketArr[i].note + '</td>' +
                        '<td style="font-size: 200%;">' + type + '</td>' +
                        '<td>' + ticketArr[i].amount + '</td>' +
                        '<td>' + ticketArr[i].dateOfTransStr + '</td>' +
                        '<td>' + ticketArr[i].lastModifiedDateStr + '</td>' +
                        '<td>' + generateIconButtonListTickets(ticketArr[i].isPlus) +  '</td>';
        tbody.appendChild(row);
    }

    table.appendChild(tbody);
    tableResponsive.appendChild(table);
    cardBody.appendChild(tableResponsive);

    var hiddenPersonIdInput = document.createElement('input');
    hiddenPersonIdInput.id = 'personIdTicket';
    hiddenPersonIdInput.name = 'personIdTicket';
    hiddenPersonIdInput.type = 'text';
    hiddenPersonIdInput.value = personId;
    hiddenPersonIdInput.hidden = true;

    var form = document.createElement('form');
    form.id = 'form-filter-ticket';
    form.appendChild(cardBody);
    form.appendChild(hiddenPersonIdInput);

    modalBodyTicketList.appendChild(form);
}

function generateIconButtonListTickets (isPlus){
    return '<div style="display: flex">' +
       '<div>' +
           '<button class="btn btn-transaprent btn-icon btn-sm" onclick="event.preventDefault();"' +
                   '>' +
               '<img src="../../img/icon/24x24-information-circle.svg" alt=""' +
                    'class="btn-icon"/>' +
           '</button>' +
       '</div>' +
       '<div>' +
           '<button class="btn btn-transaprent btn-icon btn-sm" onclick="event.preventDefault();"' +
                    generateStyleMinus(isPlus) +
                   '>' +
               '<img src="../../img/icon/24x24-minus-circle-color.svg" alt=""' +
                    ' class="btn-icon"/>' +
           '</button>' +
       '</div>' +
       '<div>' +
           '<button class="btn btn-transaprent btn-icon btn-sm" onclick="event.preventDefault();"' +
                    generateStylePlus(isPlus) +
                   '>' +
               '<img src="../../img/icon/24x24-plus-circle-color.svg" alt=""' +
                    'class="btn-icon"/>' +
           '</button>' +
       '</div>' +
   '</div>';
}

function generateStyleMinus(isPlus){
    if(!isPlus) {
        return ' style="opacity: 0.65;cursor: not-allowed;"'
    }
    return '';
}

function generateStylePlus(isPlus){
    if(isPlus) {
        return ' style="opacity: 0.65;cursor: not-allowed;"'
    }
    return '';
}

function sendRequestFilterTicket(url) {
    $.ajax({
        url: url,
        method: "GET",
        data: $('#form-filter-ticket').serialize(),
        success: function (response) {
            console.log("sendRequestFilterTicket",response);
            var personId = document.getElementById("personIdTicket").value;
            const ticketFilterRequest = {
                idTicket: document.getElementById("idTicket").value,
                fromAmountTicket: document.getElementById("fromAmountTicket").value,
                fromDateOfTransStrTicket: document.getElementById("fromDateOfTransStrTicket").value,
                fromLastModifiedDateStrTicket: document.getElementById("fromLastModifiedDateStrTicket").value,
                isPlusTicket: document.getElementById("isPlusTicket").value,
                noteTicket: document.getElementById("noteTicket").value,
                toAmountTicket: document.getElementById("toAmountTicket").value,
                toDateOfTransStrTicket: document.getElementById("toDateOfTransStrTicket").value,
                toLastModifiedDateStrTicket: document.getElementById("toLastModifiedDateStrTicket").value,
                personIdTicket: document.getElementById("personIdTicket").value,
            }
            while (modalBodyTicketList.firstChild) {
              modalBodyTicketList.removeChild(modalBodyTicketList.firstChild);
            }
            setUpTableTickets(response, null, personId, ticketFilterRequest);
        }
    });
}

//document.getElementById("button-close-add-person").addEventListener("click", function () {
//    document.getElementById("name-person-add").value = "";
//    document.getElementById("address-person-add").value = "";
//    document.getElementById("phone-person-add").value = "";
//    document.getElementById("email-person-add").value = "";
//});