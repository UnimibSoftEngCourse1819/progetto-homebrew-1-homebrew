$(document).ready(function() {
	listener();
});

function listener() {
	searchButton();
	menuBrew();
	openModal();
	addRemoveEl();
	
}
function openModal() {
	$('.button_brew').click(function() {
		$('#brew_modal .modal_Bname p').empty().append($('#brew_name', this).text());
		$('#brew_modal .modal_Buser p').empty().append($('#brew_user', this).text());
		$('#brew_modal .modal_Bdate p').empty().append($('#brew_date', this).text());
		$('#brew_modal .modal_Bquantity p').empty().append($('#brew_quantity', this).text());
		$('#brew_modal .modal_Bdesc p').empty().append($('#brew_desc', this).text());
		$('#brew_modal .modal_Bnote p').empty().append($('#brew_note', this).text());
	})
}


function searchButton() {
	$('#open_search').click(function() {
		$("#search_cont").css({right: "0px"});
		$("#search_cont").addClass('active');
	});
	$('#close_search').click(function() {
		$("#search_cont").css({right: "-400px"});
		$("#search_cont").removeClass('active');
	});

}

function menuBrew() {
	$('.recipe_menu button[name="recipe_desc"]').click(
			function() {
				if ($(".active", this).length == 0) {
					$(this).addClass('active');
					$('.recipe_menu button[name="recipe_brews"]').removeClass(
							'active');
					$('#recipe_brews').hide();
					$('#recipe_desc').show();
				}
			});
	$('.recipe_menu button[name="recipe_brews"]').click(function() {
		if ($(".active", this).length == 0) {
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
	if ($('.create-recipe-steps').length != 0) {
		console.log("Prova");

		$('.create-recipe-steps button[name="addStep"]')
		.unbind()
		.click(
				function() {
					var count = $(".create-recipe-steps tr").length + 1;
					if (count <= 40) {
						var newRow = "<tr><td class=\"leftStep\">"
								+ count
								+ "</td><td class=\"rightStep\">"
								+ "<textarea name=\"step-"
								+ count
								+ "\"></textarea></td></td></tr>";

						$('.create-recipe-steps tbody:last-child').append(
								newRow);
					}

				})
	}
	if ($('.edit-recipe-steps').length != 0) {
		$('.edit-recipe-steps button[name="addStep"]')
		.unbind()
		.click(
				function() {
					var count = $(".edit-recipe-steps tr").length + 1;
					if (count <= 40) {
						var newRow = "<tr><td class=\"leftStep\">"
								+ count
								+ "</td><td class=\"rightStep\">"
								+ "<textarea name=\"step-"
								+ count
								+ "\"></textarea></td></td></tr>";

						$('.edit-recipe-steps tbody:last-child').append(
								newRow);
					}

				})
	}

}

function removeSteps() {
	if ($('.create-recipe-steps').length != 0) {
		$('.create-recipe-steps button[name="removeStep"]')
		.unbind()
		.click(
				function() {
					var count = $(".create-recipe-steps tr").length;
					if (count > 1) {
						var buttons = "<button name=\"removeStep\" type=\"button\">&times;</button>"
								+ "<button name=\"addStep\" type=\"button\">+</button>";

						$(".create-recipe-steps tr:last-child").remove();
					}

				})
	}
	if ($('.edit-recipe-steps').length != 0) {
		$('.edit-recipe-steps button[name="removeStep"]')
		.unbind()
		.click(
				function() {
					var count = $(".edit-recipe-steps tr").length;
					if (count > 1) {
						var buttons = "<button name=\"removeStep\" type=\"button\">&times;</button>"
								+ "<button name=\"addStep\" type=\"button\">+</button>";

						$(".edit-recipe-steps tr:last-child").remove();
					}

				})
	}

}