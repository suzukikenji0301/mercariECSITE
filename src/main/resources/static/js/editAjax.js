/**
 * 
 */
"use-strict";

window.onload = function() {

}

$(function() {
	console.log("funcition内にきた")
	//大カテゴリーを選択したらカテゴリーに関連した中カテゴリーが表示される
	$('#bigcategory').change(function() {
		console.log("bIGCATEGORY内")
		let hostUrl = 'http://localhost:8080/mercari/edit/childCategory';
		let bigcategory = $('#bigcategory').val();
		$.ajax({
			url: hostUrl, //アクセスするURLかディレクトリ
			type: 'POST',
			dataType: 'json', //data type scriptなどデータタイプの指定
			data: {
				bigCategory: bigcategory
			}, //アクセスするときに必要なデータを 記載
			async: true, //非同期通信
		})
			.done(function(childList) {
				console.log("childListの中身");
				console.log(childList);
				let $child = $("#childcategory");
				let $grand = $("#grandcategory");
				$child.empty();
				$grand.empty();

				$child.append($("<option>-- 中分類　--</option>"));
				$grand.append($("<option>-- 中分類を選択してください　--</option>"));
				
				$.each(childList, function(index,value) {
					
					$child.append($("<option></option>")
						.attr("value", value.id).text(value.name));
				})
				$("#childcategory").append(childList);
			})
			.fail(function(XMLHttpRequest, textStatus, errorThrown) {
				alert("エラーになり、値が取得できませんでした。");
				console.log('XMLHttpRequest: ' + XMLHttpRequest.status);

				console.log('textStatus :' + textStatus);

				console.log('errorThrown :' + errorThrown.message);
			}).always(function() { //通信結果にかかわらず実行する処理を書く
			})
	});

window.onload = function() {

}
	$('#childcategory').change(function() {
		let childcategory = $('#childcategory').val();
		console.log("チャイルド" + childcategory);
		$.ajax({
			url: 'http://localhost:8080/mercari/edit/grandCategory',
			type: 'POST',
			dataType: 'json',
			data: {
				childCategory : childcategory
			},
			async: true,
		}).done(function(grandList) {
			let $grand = $("#grandcategory");
			
			$grand.empty();
			$grand.append($("<option>-- 小分類 --</option>"));
			$.each(grandList, function(index,value) {
				$grand.append($("<option></option>")
					.attr("value", value.id).text(value.name));
				console.log("value = " + value);
			})
			$("#grandcategory").append(grandList);
			console.log("childListの中身");
			console.log(grandList);
		});
	});
});