/**
 * 
 */
 
 window.onload = function(){
	 
 
 
 $('#bigCategory').change(function() {
	 console.log("Ajax前")
	var bigCategory = $('#bigCategory').val();
	console.log(bigCategory);
	$.ajax({
		url: 'http://localhost:8080/mercari/items/childCategory',
		type: 'post',
		dataType: 'json',
		data :{
			bigCategory : bigCategory
		},
		async: true,
	}).done(function(childList){
			console.log(childList);
		//selectに当てはめる
		let $middle = $("#childCategory");
		let $small = $("#grandCategory");
		$middle.empty();
		$small.empty();
		$middle.append($("<option>-- 中分類　--</option>"));
		$small.append($("<option>-- 中分類を選択してください　--</option>"));
		$.each(childList,function(value){
			$middle.append($("<option></option>")
				.attr("value", value.name).text(value.name));
		})
		$("#childCategory").append(childList);
		console.log(childList);
		
	}).fail(function(XMLHttpRequest,textStatus,errorThrown){
		alert('値がないんやけど。');
	});


});

}