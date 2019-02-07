$(document).ready(function() {
	if ($('.create_recipe_steps').length != 0) {
		checkSteps();
	}
});
function checkSteps() {
	addSteps();
	removeSteps();
}

function addSteps() {
	$('.create_recipe_steps button[name="addStep"]').unbind().click(function(){
		var count = $(".create_recipe_steps tr").length + 1;
		if (count <= 40) {
			var newRow = "<tr><td class=\"leftStep\">" +
			count +
			"</td><td class=\"rightStep\">" +
			"<textarea name=\"step-" + 
			count +
			"\" placeholder=\"Inserisci il procedimento\"></textarea></td></td></tr>";
				
			$('.create_recipe_steps tbody:last-child').append(newRow);
		}

	})
}

function removeSteps() {
	$('.create_recipe_steps button[name="removeStep"]').unbind().click(function(){
		var count = $(".create_recipe_steps tr").length;
		if (count > 1) {
			var buttons = "<button name=\"removeStep\" type=\"button\">&times;</button>" +
			"<button name=\"addStep\" type=\"button\">+</button>";
			
			$(".create_recipe_steps tr:last-child").remove();
		}

	})
}