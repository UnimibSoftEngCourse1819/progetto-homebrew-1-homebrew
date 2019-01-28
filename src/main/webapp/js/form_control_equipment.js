function controlForm() {
	jQuery.validate.addMethod("person", function(value, element) {
	  return this.optional( element ) || /^[A-Za-z\s]+$/.test( value );
	}, 'Not a name');
	
	
	$('input').on('blur keyup change', function() {
        if ($("#equipment_form").valid() && $('.form_priv input').is(":checked")) {
            $('#action').prop('disabled', false);
						$('#action').addClass('boo');
        } else {
            $('#action').prop('disabled', true);
						$('#action').removeClass('boo');
        }
    });
	$('#equipment_form').validate({
		rules: {
			weightScale: {
				required: true, person: true
			},
			
			caludron: {
				required: true, person: true
			},
			
			fermenter: {
				required: true, person: true
			},
			
			tube: {
				required: true, person: true
			},
			
			thermometers: {
				required: true, person: true
			},
			
			ladles: {
				required: true, person: true
			},
		},
		groups: {
			general: "weightScale caludron fermenter tube thermometers ladles"
		},
		messages: {
			weightScale: {
				required: "Inserire i campi richiesti", person: "Campi "
			},
			caludron: {
				required: "Inserire i campi richiesti", person: "Campi "
			},
			fermenter: {
				required: "Inserire i campi richiesti"
			},
			tube: {
				required: "Inserire i campi richiesti"
			},
			thermometers: {
				required: "Inserire i campi richiesti"
			},
			ladles: {
				required: "Inserire i campi richiesti"
			}
		},
	});
}