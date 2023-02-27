// Call the dataTables jQuery plugin
$(document).ready(function() {
  $('#dataTable').DataTable();
});

const inputElement = document.querySelector(".floatInput");

// Restrict input to only numbers and dot character
inputElement.addEventListener("input", function(event) {
  const regex = /[^0-9\.]/g;
  inputElement.value = inputElement.value.replace(regex, '');
});