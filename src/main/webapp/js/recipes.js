$(document).ready(function() {
	addRemoveEl();
	listener();
});

function listener() {
	menu();
}

function menu() {
	$('.recipe_menu button[name="recipe_desc"]').click(
			function() {
				if ($(".active", this).length == 0) {
					console.log("desc");
					$(this).addClass('active');
					$('.recipe_menu button[name="recipe_brews"]').removeClass(
							'active');
					$('#recipe_brews').hide();
					$('#recipe_desc').show();
				}
			});
	$('.recipe_menu button[name="recipe_brews"]').click(function() {
		if ($(".active", this).length == 0) {
			console.log("brews");
			$(this).addClass('active');
			$('.recipe_menu button[name="recipe_desc"]').removeClass('active');
			$('#recipe_desc').hide();
			$('#recipe_brews').show();
		}
	})
}

function addRemoveEl() {
	addSteps();
	removeSteps();

}

function addSteps() {
	if ($('.create_recipe_steps').length != 0) {
		$('.create_recipe_steps button[name="addStep"]')
		.unbind()
		.click(
				function() {
					var count = $(".create_recipe_steps tr").length + 1;
					if (count <= 40) {
						var newRow = "<tr><td class=\"leftStep\">"
								+ count
								+ "</td><td class=\"rightStep\">"
								+ "<textarea name=\"step-"
								+ count
								+ "\" placeholder=\"Inserisci il procedimento\"></textarea></td></td></tr>";

						$('.create_recipe_steps tbody:last-child').append(
								newRow);
					}

				})
	}
	if ($('.edit_recipe_steps').length != 0) {
		$('.edit_recipe_steps button[name="addStep"]')
		.unbind()
		.click(
				function() {
					var count = $(".edit_recipe_steps tr").length + 1;
					if (count <= 40) {
						var newRow = "<tr><td class=\"leftStep\">"
								+ count
								+ "</td><td class=\"rightStep\">"
								+ "<textarea name=\"step-"
								+ count
								+ "\" placeholder=\"Inserisci il procedimento\"></textarea></td></td></tr>";

						$('.edit_recipe_steps tbody:last-child').append(
								newRow);
					}

				})
	}

}

function removeSteps() {
	if ($('.create_recipe_steps').length != 0) {
		$('.create_recipe_steps button[name="removeStep"]')
		.unbind()
		.click(
				function() {
					var count = $(".create_recipe_steps tr").length;
					if (count > 1) {
						var buttons = "<button name=\"removeStep\" type=\"button\">&times;</button>"
								+ "<button name=\"addStep\" type=\"button\">+</button>";

						$(".create_recipe_steps tr:last-child").remove();
					}

				})
	}
	if ($('.edit_recipe_steps').length != 0) {
		$('.edit_recipe_steps button[name="removeStep"]')
		.unbind()
		.click(
				function() {
					var count = $(".edit_recipe_steps tr").length;
					if (count > 1) {
						var buttons = "<button name=\"removeStep\" type=\"button\">&times;</button>"
								+ "<button name=\"addStep\" type=\"button\">+</button>";

						$(".edit_recipe_steps tr:last-child").remove();
					}

				})
	}

}