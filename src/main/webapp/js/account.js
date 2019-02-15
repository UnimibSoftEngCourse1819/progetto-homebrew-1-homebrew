$(document).ready(function() {
	listener();
});

function listener() {
	menuAccount();
	
}

function menuAccount() {
	$('.account-menu li').click(function() {
		if ($(".active", this).length == 0) {
			$(".account-menu li").removeClass('active');
			$(this).addClass('active');
			var selector = $(this).data("selector");
			$('.account-form').removeClass('active');
			$('.' + selector).addClass('active');
		}
	});

}

