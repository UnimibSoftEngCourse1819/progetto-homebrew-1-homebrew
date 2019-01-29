$(document).ready(function() {
	controlForm();
});
function controlForm() {
	$.validator.addMethod("person", function(value, element) {
		return this.optional(element) || /^[A-Za-z\s]+$/.test(value);
	}, 'Not a name');

	$('input').on('blur keyup change', function() {
		if ($("#registration_form").valid()) {
			$('#addNewUser').prop('disabled', false);
			$('#addNewUser').addClass('boo');
		} else {
			$('#addNewUser').prop('disabled', true);
			$('#addNewUser').removeClass('boo');
		}
	});
	$('#registration_form').validate({
		rules : {
			name : {
				required : true,
				person : true
			},

			surname : {
				required : true,
				person : true
			},

			password : {
				required : true
			},

			checkPassword : {
				required : true
			},

			email : {
				required : true,
				email : true
			},

			dateOfBirth : {
				required : true
			},
		},
		groups : {
			general : "name surname password checkPassword dateOfBirth email"
		},
		messages : {
			name : {
				required : "Inserire i campi richiesti",
				person : "Campi "
			},
			surname : {
				required : "Inserire i campi richiesti",
				person : "Campi "
			},
			password : {
				required : "Inserire i campi richiesti"
			},
			checkPassword : {
				required : "Inserire i campi richiesti"
			},
			email : {
				required : "Inserire i campi richiesti"
			},
			dateOfBirth : {
				required : "Inserire i campi richiesti"
			}
		},
	});
}
