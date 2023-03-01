// Call the dataTables jQuery plugin
$(document).ready(function() {
  $('#dataTable').DataTable();
});

// ===========================================================
//Float input in /loan-list
const toAmountStrElement = document.getElementById("toAmountStr");

// Restrict input to only numbers and a dot character
toAmountStrElement.addEventListener("input", function(event) {
  const regex = /[^0-9\.]/g;
  toAmountStrElement.value = toAmountStrElement.value.replace(regex, '');
});

const fromAmountStrElement = document.getElementById("fromAmountStr");

// Restrict input to only numbers and a dot character
fromAmountStrElement.addEventListener("input", function(event) {
  const regex = /[^0-9\.]/g;
  fromAmountStrElement.value = fromAmountStrElement.value.replace(regex, '');
});
// ===========================================================