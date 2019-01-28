function controlForm() {
	jQuery.validate.addMethod("person", function(value, element) {
	  return this.optional( element ) || /^[A-Za-z\s]+$/.test( value );
	}, 'Not a name');
	
	
	$('input').on('blur keyup change', function() {
        if ($("#registration_form").valid() && $('.form_priv input').is(":checked")) {
            $('#action').prop('disabled', false);
						$('#action').addClass('boo');
        } else {
            $('#action').prop('disabled', true);
						$('#action').removeClass('boo');
        }
    });
	$('#pantry_form').validate({
		rules: {
			malto: {
				required: true, person: true
			},
			
			zucchero: {
				required: true, person: true
			},
			
			additivi: {
				required: true
			},
			
			lievito: {
				required: true
			},
			
			luppolo: {
				required: true, email: true
			},
		},
		groups: {
			general: "malto zucchero additivi lievito luppolo"
		},
		messages: {
			malto: {
				required: "Inserire i campi richiesti", person: "Campi "
			},
			zucchero: {
				required: "Inserire i campi richiesti", person: "Campi "
			},
			additivi: {
				required: "Inserire i campi richiesti"
			},
			lievito: {
				required: "Inserire i campi richiesti"
			},
			luppolo: {
				required: "Inserire i campi richiesti"
			},
		},
	});
}

