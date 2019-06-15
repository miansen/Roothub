$(function() {
	$.ajax({
		type : "get",
		url : "/session",
		dataType : "json",
		success : function(data) {
			//console.log(JSON.stringify(data));
			if (data.success != null && data.success == true) {
				$("#loginuser").show();
				$("#loginuser a").text(data.data.userName);
				$("#loginuser a").attr("href", "/user/" + data.data.userName);
				$("#shezhili").show();
				$("#tuichuli").show();
			}
			if (data.success != null && data.success == false) {
				$("#loginli").show();
				$("#zhuceli").show();
				$("#nologin").show();
			}
		},
		error : function(data) {

		}
	});
});