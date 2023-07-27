/**
 * 
 */

$(document).ready(function() {
	$('.table .eBtn').on('click', function(event) {
		event.preventDefault();
		var href = $(this).attr('href');
		$.get(href, function(item, status) {
			$('.modal fade #id').val(item.id);
		});
		$('.modal fade #name').val(item.name);

$('.modal fade#registerModal').modal();
	});
});