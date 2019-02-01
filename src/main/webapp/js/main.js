$(document).ready(function() {
	modal();
});

function modal() {
	$('#alert_modal').modal({
		backdrop : false
	})
	$('#alert_modal').modal('show');
	setTimeout(function() {
		$('#alert_modal').modal('hide');
	}, 2000);
}