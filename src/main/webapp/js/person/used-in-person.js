

//Call api to get ticket list -> open modal
function setUpTableTickets (ticketArr, personName, personId, ticketFilterRequest){
    if(personName) {
        document.getElementById("modal-list-tickets-title").innerHTML = personName;
    }

    generateFormSearchTicket(personId,ticketFilterRequest,ticketArr);
    generateTicketDataTable(ticketArr);

    $('#modal-list-tickets').modal('show');
    $('#dataTable-ticket-list').DataTable();
}

//Generate Ticket Data Table -> generateTicketDataTable, generateIconButtonListTickets, generateStyleMinus, generateStylePlus
function generateTicketDataTable (ticketArr) {
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
    th1.textContent = 'Id';
    var th2 = document.createElement('th');
    th2.textContent = 'Ghi chú';
    var th3 = document.createElement('th');
    th3.textContent = 'Loại nợ';
    var th4 = document.createElement('th');
    th4.textContent = 'Số tiền';
    var th5 = document.createElement('th');
    th5.textContent = 'Ngày lập phiếu';
    var th6 = document.createElement('th');
    th6.textContent = 'Chỉnh sửa lần cuối';
    var th7 = document.createElement('th');
    th7.textContent = 'Hành động';
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
    modalBodyTicketList.appendChild(cardBody);
}

function generateIconButtonListTickets (isPlus){
    return '<div style="display: flex">' +
       '<div>' +
           '<button class="btn btn-transaprent btn-icon btn-sm"' +
                   '>' +
               '<img src="../../img/icon/24x24-information-circle.svg" alt=""' +
                    'class="btn-icon"/>' +
           '</button>' +
       '</div>' +
       '<div>' +
           '<button class="btn btn-transaprent btn-icon btn-sm"' +
                    generateStyleMinus(isPlus) +
                   '>' +
               '<img src="../../img/icon/24x24-minus-circle-color.svg" alt=""' +
                    ' class="btn-icon"/>' +
           '</button>' +
       '</div>' +
       '<div>' +
           '<button class="btn btn-transaprent btn-icon btn-sm"' +
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

//Generate Form Search Ticket
function generateFormSearchTicket (personId, ticketFilterRequest, ticketArr) {
    // Create a new form element
    var form = document.createElement('form');
    form.id = 'form-filter-ticket';

    // Create a new div wrapper element
    var formWrapper = document.createElement('div');
    formWrapper.style.cssText = 'display: flex;';

    // Create a new div left column element
    var divLeftColumn = document.createElement('div');
    divLeftColumn.style.cssText = 'display: flex;flex:1;flex-direction: column;margin-right: 2rem';

    // Create a new div right column element
    var divRightColumn = document.createElement('div');
    divRightColumn.style.cssText = 'display: flex;flex:1;flex-direction: column;margin-right: 2rem';

    //INPUT ID
        // Create a new form item IdTicket element
        var formItemIdTicket = document.createElement('div');
        formItemIdTicket.style.cssText = 'display: flex;flex: 1;flex-direction: column;max-width: 100%;margin-bottom: 1rem;';

        // Create a new label IdTicket element
        var labelIdTicket = document.createElement('label');
        labelIdTicket.for = 'idTicket'
        var contentLabelIdTicket = document.createTextNode("ID");
        labelIdTicket.appendChild(contentLabelIdTicket);

        // Create a new input IdTicket element
        var inputIdTicket = document.createElement('input');
        inputIdTicket.id = 'idTicket';
        inputIdTicket.name = 'idTicket';
        inputIdTicket.type = 'text';
        inputIdTicket.className = 'form-control';
        inputIdTicket.placeholder = 'ID';
        inputIdTicket.addEventListener("input", function(event) {
            const numberPattern = /^[0-9]*$/;

            if (!numberPattern.test(event.target.value)) {
              event.preventDefault();
              inputIdTicket.value = inputIdTicket.value.replace(/[^0-9]/g, '');
            }
        });
        if(ticketFilterRequest) inputIdTicket.value = ticketFilterRequest.idTicket;

        formItemIdTicket.appendChild(labelIdTicket);
        formItemIdTicket.appendChild(inputIdTicket);

        divLeftColumn.appendChild(formItemIdTicket);

    //INPUT AMOUNT
        // Create a new form item AmountTicket element
        var formItemAmountTicket = document.createElement('div');
        formItemAmountTicket.style.cssText = 'display: flex;flex: 1;flex-direction: column;max-width: 100%;margin-bottom: 1rem;';

        // Create a new label AmountTicket element
        var labelAmountTicket = document.createElement('label');
        labelAmountTicket.for = 'fromAmountTicket'
        var contentLabelAmountTicket = document.createTextNode("Từ số tiền");
        labelAmountTicket.appendChild(contentLabelAmountTicket);

        // Create a new input AmountTicket element
        var inputAmountTicket = document.createElement('input');
        inputAmountTicket.id = 'fromAmountTicket';
        inputAmountTicket.name = 'fromAmountTicket';
        inputAmountTicket.type = 'text';
        inputAmountTicket.className = 'form-control';
        inputAmountTicket.placeholder = 'Từ số tiền';
        inputAmountTicket.addEventListener("input", function(event) {
            const numberPattern = /^[0-9]*$/;

            if (!numberPattern.test(event.target.value)) {
              event.preventDefault();
              inputAmountTicket.value = inputAmountTicket.value.replace(/[^0-9]/g, '');
            }
        });
        if(ticketFilterRequest) inputAmountTicket.value = ticketFilterRequest.fromAmountTicket;

        formItemAmountTicket.appendChild(labelAmountTicket);
        formItemAmountTicket.appendChild(inputAmountTicket);

        divLeftColumn.appendChild(formItemAmountTicket);

    //INPUT DATE OF TRANS
        // Create a new form item DateOfTransStrTicket element
        var formItemDateOfTransStrTicket = document.createElement('div');
        formItemDateOfTransStrTicket.style.cssText = 'display: flex;flex: 1;flex-direction: column;max-width: 100%;margin-bottom: 1rem;';

        // Create a new label DateOfTransStrTicket element
        var labelDateOfTransStrTicket = document.createElement('label');
        labelDateOfTransStrTicket.for = 'fromDateOfTransStrTicket'
        var contentLabelDateOfTransStrTicket = document.createTextNode("Từ ngày lập phiếu");
        labelDateOfTransStrTicket.appendChild(contentLabelDateOfTransStrTicket);

        // Create a new input DateOfTransStrTicket element
        var inputDateOfTransStrTicket = document.createElement('input');
        inputDateOfTransStrTicket.id = 'fromDateOfTransStrTicket';
        inputDateOfTransStrTicket.name = 'fromDateOfTransStrTicket';
        inputDateOfTransStrTicket.type = 'datetime-local';
        inputDateOfTransStrTicket.className = 'form-control';
        inputDateOfTransStrTicket.placeholder = 'Từ ngày lập phiếu';
        if(ticketFilterRequest) inputDateOfTransStrTicket.value = ticketFilterRequest.fromDateOfTransStrTicket;

        formItemDateOfTransStrTicket.appendChild(labelDateOfTransStrTicket);
        formItemDateOfTransStrTicket.appendChild(inputDateOfTransStrTicket);

        divLeftColumn.appendChild(formItemDateOfTransStrTicket);

    //INPUT LAST MODIFIED DATE
            // Create a new form item LastModifiedDateStrTicket element
            var formItemLastModifiedDateStrTicket = document.createElement('div');
            formItemLastModifiedDateStrTicket.style.cssText = 'display: flex;flex: 1;flex-direction: column;max-width: 100%;margin-bottom: 1rem;';

            // Create a new label LastModifiedDateStrTicket element
            var labelLastModifiedDateStrTicket = document.createElement('label');
            labelLastModifiedDateStrTicket.for = 'fromLastModifiedDateStrTicket'
            var contentLabelLastModifiedDateStrTicket = document.createTextNode("Từ ngày cập nhật");
            labelLastModifiedDateStrTicket.appendChild(contentLabelLastModifiedDateStrTicket);

            // Create a new input LastModifiedDateStrTicket element
            var inputLastModifiedDateStrTicket = document.createElement('input');
            inputLastModifiedDateStrTicket.id = 'fromLastModifiedDateStrTicket';
            inputLastModifiedDateStrTicket.name = 'fromLastModifiedDateStrTicket';
            inputLastModifiedDateStrTicket.type = 'datetime-local';
            inputLastModifiedDateStrTicket.className = 'form-control';
            inputLastModifiedDateStrTicket.placeholder = 'Từ ngày cập nhật';
            if(ticketFilterRequest) inputLastModifiedDateStrTicket.value = ticketFilterRequest.fromLastModifiedDateStrTicket;

            formItemLastModifiedDateStrTicket.appendChild(labelLastModifiedDateStrTicket);
            formItemLastModifiedDateStrTicket.appendChild(inputLastModifiedDateStrTicket);

            divLeftColumn.appendChild(formItemLastModifiedDateStrTicket);

    //INPUT IS PLUS
        // Create a new form item IsPlusTicket element
        var formItemIsPlusTicket = document.createElement('div');
        formItemIsPlusTicket.style.cssText = 'display: flex;flex: 1;flex-direction: column;max-width: 100%;margin-bottom: 1rem;';

        // Create a new label IsPlusTicket element
        var labelIsPlusTicket = document.createElement('label');
        labelIsPlusTicket.for = 'isPlusTicket'
        var contentLabelIsPlusTicket = document.createTextNode("Loại nợ");
        labelIsPlusTicket.appendChild(contentLabelIsPlusTicket);

        // Create a new select IsPlusTicket element
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

        formItemIsPlusTicket.appendChild(labelIsPlusTicket);
        formItemIsPlusTicket.appendChild(selectIsPlusTicket);

        divLeftColumn.appendChild(formItemIsPlusTicket);

    //INPUT NOTE
        // Create a new form item NoteTicket element
        var formItemNoteTicket = document.createElement('div');
        formItemNoteTicket.style.cssText = 'display: flex;flex: 1;flex-direction: column;max-width: 100%;margin-bottom: 1rem;';

        // Create a new label NoteTicket element
        var labelNoteTicket = document.createElement('label');
        labelNoteTicket.for = 'noteTicket'
        var contentLabelNoteTicket = document.createTextNode("Ghi chú");
        labelNoteTicket.appendChild(contentLabelNoteTicket);

        // Create a new input NoteTicket element
        var inputNoteTicket = document.createElement('input');
        inputNoteTicket.id = 'noteTicket';
        inputNoteTicket.name = 'noteTicket';
        inputNoteTicket.type = 'text';
        inputNoteTicket.className = 'form-control';
        inputNoteTicket.placeholder = 'Ghi chú';
        if(ticketFilterRequest) inputNoteTicket.value = ticketFilterRequest.noteTicket;

        formItemNoteTicket.appendChild(labelNoteTicket);
        formItemNoteTicket.appendChild(inputNoteTicket);

        divRightColumn.appendChild(formItemNoteTicket);

    //INPUT AMOUNT
        // Create a new form item AmountTicket element
        var toItemAmountTicket = document.createElement('div');
        toItemAmountTicket.style.cssText = 'display: flex;flex: 1;flex-direction: column;max-width: 100%;margin-bottom: 1rem;';

        // Create a new label AmountTicket element
        var labeltoAmountTicket = document.createElement('label');
        labeltoAmountTicket.for = 'toAmountTicket'
        var contentLabelToAmountTicket = document.createTextNode("Đến số tiền");
        labeltoAmountTicket.appendChild(contentLabelToAmountTicket);

        // Create a new input AmountTicket element
        var inputToAmountTicket = document.createElement('input');
        inputToAmountTicket.id = 'toAmountTicket';
        inputToAmountTicket.name = 'toAmountTicket';
        inputToAmountTicket.type = 'text';
        inputToAmountTicket.className = 'form-control';
        inputToAmountTicket.placeholder = 'Đến số tiền';
        inputToAmountTicket.addEventListener("input", function(event) {
            const numberPattern = /^[0-9]*$/;

            if (!numberPattern.test(event.target.value)) {
              event.preventDefault();
              inputToAmountTicket.value = inputToAmountTicket.value.replace(/[^0-9]/g, '');
            }
        });
        if(ticketFilterRequest) inputToAmountTicket.value = ticketFilterRequest.toAmountTicket;

        toItemAmountTicket.appendChild(labeltoAmountTicket);
        toItemAmountTicket.appendChild(inputToAmountTicket);

        divRightColumn.appendChild(toItemAmountTicket);

    //INPUT TO DATE OF TRANS
        // Create a new form item To DateOfTransStrTicket element
        var formItemToDateOfTransStrTicket = document.createElement('div');
        formItemToDateOfTransStrTicket.style.cssText = 'display: flex;flex: 1;flex-direction: column;max-width: 100%;margin-bottom: 1rem;';

        // Create a new label DateOfTransStrTicket element
        var labelToDateOfTransStrTicket = document.createElement('label');
        labelToDateOfTransStrTicket.for = 'toDateOfTransStrTicket'
        var contentLabelToDateOfTransStrTicket = document.createTextNode("Đến ngày lập phiếu");
        labelToDateOfTransStrTicket.appendChild(contentLabelToDateOfTransStrTicket);

        // Create a new input DateOfTransStrTicket element
        var inputToDateOfTransStrTicket = document.createElement('input');
        inputToDateOfTransStrTicket.id = 'toDateOfTransStrTicket';
        inputToDateOfTransStrTicket.name = 'toDateOfTransStrTicket';
        inputToDateOfTransStrTicket.type = 'datetime-local';
        inputToDateOfTransStrTicket.className = 'form-control';
        inputToDateOfTransStrTicket.placeholder = 'Đến ngày lập phiếu';
        if(ticketFilterRequest) inputToDateOfTransStrTicket.value = ticketFilterRequest.toDateOfTransStrTicket;

        formItemToDateOfTransStrTicket.appendChild(labelToDateOfTransStrTicket);
        formItemToDateOfTransStrTicket.appendChild(inputToDateOfTransStrTicket);

        divRightColumn.appendChild(formItemToDateOfTransStrTicket);

    //INPUT TO LAST MODIFIED DATE
        // Create a new form item LastModifiedDateStrTicket element
        var formItemToLastModifiedDateStrTicket = document.createElement('div');
        formItemToLastModifiedDateStrTicket.style.cssText = 'display: flex;flex: 1;flex-direction: column;max-width: 100%;margin-bottom: 1rem;';

        // Create a new label LastModifiedDateStrTicket element
        var labelToLastModifiedDateStrTicket = document.createElement('label');
        labelToLastModifiedDateStrTicket.for = 'toLastModifiedDateStrTicket'
        var contentLabelToLastModifiedDateStrTicket = document.createTextNode("Từ ngày cập nhật");
        labelToLastModifiedDateStrTicket.appendChild(contentLabelToLastModifiedDateStrTicket);

        // Create a new input LastModifiedDateStrTicket element
        var inputToLastModifiedDateStrTicket = document.createElement('input');
        inputToLastModifiedDateStrTicket.id = 'toLastModifiedDateStrTicket';
        inputToLastModifiedDateStrTicket.name = 'toLastModifiedDateStrTicket';
        inputToLastModifiedDateStrTicket.type = 'datetime-local';
        inputToLastModifiedDateStrTicket.className = 'form-control';
        inputToLastModifiedDateStrTicket.placeholder = 'Từ ngày cập nhật';
        if(ticketFilterRequest) inputToLastModifiedDateStrTicket.value = ticketFilterRequest.toLastModifiedDateStrTicket;

        formItemToLastModifiedDateStrTicket.appendChild(labelToLastModifiedDateStrTicket);
        formItemToLastModifiedDateStrTicket.appendChild(inputToLastModifiedDateStrTicket);

        divRightColumn.appendChild(formItemToLastModifiedDateStrTicket);

    //INPUT TO LAST MODIFIED DATE
        // Create a new form item LastModifiedDateStrTicket element
        var blankDiv = document.createElement('div');
        blankDiv.style.cssText = 'display: flex;flex: 1;flex-direction: column;max-width: 100%;margin-bottom: 1rem;';

        divRightColumn.appendChild(blankDiv);

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
        addTicketButton.setAttribute("data-target", "#modal-add-ticket");
        addTicketButton.style.cssText = 'background-color: orange; border-color: orange;margin-left: 10px;';

        var addTicketSpan = document.createElement('span');
        addTicketSpan.className = 'text';

        var contentAddTicketSpan = document.createTextNode("Tạo phiếu nợ");

        addTicketSpan.appendChild(contentAddTicketSpan);
        addTicketButton.appendChild(addTicketSpan);

    buttonWrapper.appendChild(clearTicketFilterButton);
    buttonWrapper.appendChild(filterTicketButton);
    buttonWrapper.appendChild(addTicketButton);

    formWrapper.appendChild(divLeftColumn);
    formWrapper.appendChild(divRightColumn);
    form.appendChild(formWrapper);
    form.appendChild(buttonWrapper);

    var hiddenPersonIdInput = document.createElement('input');
    hiddenPersonIdInput.id = 'personIdTicket';
    hiddenPersonIdInput.name = 'personIdTicket';
    hiddenPersonIdInput.type = 'text';
    hiddenPersonIdInput.value = personId;
    hiddenPersonIdInput.hidden = true;

    form.appendChild(hiddenPersonIdInput);
    modalBodyTicketList.appendChild(form);
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