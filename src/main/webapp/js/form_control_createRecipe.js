function controlForm() {
	jQuery.validate.addMethod("person", function(value, element) {
	  return this.optional( element ) || /^[A-Za-z\s]+$/.test( value );
	}, 'Not a name');
	
	
	$('input, textarea').on('blur keyup change', function() {
        if ($("#pantry_form").valid() && $('.form_priv input').is(":checked")) {
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
			
			luppolo: {
				required: true, person: true
			},
			
			additivi: {
				required: true, person: true
			},
			
			zucchero: {
				required: true, person: true
			},
			
			lievito: {
				required: true, person: true
			},
			
			acqua: {
				required: true, person: true
			},
			
			procedimento: {
				required: true, person: true
			},
		},
		groups: {
			general: "malto luppolo additivi zucchero lievito acqua procedimento"
		},
		messages: {
			malto: {
				required: "Inserire i campi richiesti", person: "Campi "
			},
			luppolo: {
				required: "Inserire i campi richiesti", person: "Campi "
			},
			additivi: {
				required: "Inserire i campi richiesti"
			},
			zucchero: {
				required: "Inserire i campi richiesti"
			},
			lievito: {
				required: "Inserire i campi richiesti"
			},
			acqua: {
				required: "Inserire i campi richiesti"
			},
			procedimento: {
				required: "Inserire i campi richiesti"
			}
		},
	});
}