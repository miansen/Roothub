$("#loginuser").addClass("active");
var fid = $("#user_id").attr("title");
/*是否已关注*/
$.ajax({
	url : "/follow/isFollow",
	type : "get",
	dataType : "json",
	data : {
		fid : fid
	},
	success : function(data) {
		if (data.success != null && data.success == true) {
			$(".btn-red-hollow").text("取消关注");
		} else {
			$(".btn-red-hollow").text("关注");
		}
	},
	error : function(data) {

	}
});
/*关注我的数量*/
$.ajax({
	url : "/follow/count/to",
	type : "get",
	dataType : "json",
	data : {
		fid : fid
	},
	success : function(data) {
		if (data.success != null && data.success == true) {
			$(".follow_count_to").text(data.data);
			$("#follow_dl").attr("title",data.data);
		}
	},
	error : function(data) {

	}
});
/* 关注和取消关注*/
function save() {
	var isFollow = $("#follow").text();
	var userName = $("#user_id").text();
	var info;
	//console.log(isFollow);
	//alert(isFollow);
	var url;
	if (isFollow == "关注") {
		url = "/follow/save";
		info = "确定要开始关注" + userName + "?";
	}
	if (isFollow == "取消关注") {
		url = "/follow/delete";
		info = "确定要取消关注" + userName + "?";
	}
	//alert("isFollow："+isFollow+"  url："+url);
	if (confirm(info)) {
		$.ajax({
			url : url,
			type : "post",
			dataType : "json",
			data : {
				fid : fid
			},
			success : function(data) {
				if (data.success != null && data.success == true
						&& data.error == "关注成功") {
					//alert(data.error);
					$(".btn-red-hollow").text("取消关注");
				}
				if (data.success != null && data.success == true
						&& data.error == "取消关注成功") {
					//alert(data.error);
					$(".btn-red-hollow").text("关注");
				}
			},
			error : function(data) {

			}
		})
	}
}