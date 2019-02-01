$(document).ready(function() {
	controlForm();
	if ($('#dateOfBirth')[0].type != 'date')
		$('#dateOfBirth').datepicker();
});
function controlForm() {
	$.validator.addMethod("person", function(value, element) {
		return this.optional(element) || /^[A-Za-z\s]+$/.test(value);
	}, 'Not a name');
	$.validator.addMethod("dateValid",function(value, element) {
		return this.optional(element)
		|| /^(?:(?:(?:0?[13578]|1[02])(\/|-|\.)31)\1|(?:(?:0?[1,3-9]|1[0-2])(\/|-|\.)(?:29|30)\2))(?:(?:1[6-9]|[2-9]\d)?\d{2})$|^(?:0?2(\/|-|\.)29\3(?:(?:(?:1[6-9]|[2-9]\d)?(?:0[48]|[2468][048]|[13579][26])|(?:(?:16|[2468][048]|[3579][26])00))))$|^(?:(?:0?[1-9])|(?:1[0-2]))(\/|-|\.)(?:0?[1-9]|1\d|2[0-8])\4(?:(?:1[6-9]|[2-9]\d)?\d{2})$/
				.test(value);
		}, 'Not a date');

	$('input').on('blur keyup change', function() {
		if ($("#registration_form").valid()) {
			$('#addNewUser').prop('disabled', false);
			$('#addNewUser').addClass('boo');
		} else {
			$('#addNewUser').prop('disabled', true);
			$('#addNewUser').removeClass('boo');
		}
	});
	$('#registration_form')
			.validate(
					{
						rules : {
							name : {
								required : true,
								person : true
							},
							surname : {
								required : true,
								person : true
							},
							new_password : {
								required : true,
								minlength : 8
							},
							email : {
								required : true,
								email : true
							},
							dateOfBirth : {
								required : true,
								dateISO : true
							},
							confirm_password : {
								required : true,
								minlength : 8,
								equalTo : "#new_password"
							}
						},
						groups : {
							general : "name surname password checkPassword dateOfBirth email confirm_password"
						},
						errorPlacement : function() {
							return false;
						}
					});
}
