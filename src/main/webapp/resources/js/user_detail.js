$("#loginuser").addClass("active");
var userId = $("#user_id").attr("title");//当前主页用户的ID
var authorName = $("#user_id").text();//当前主页用户的昵称
var userName = $(".navbar .container ul #loginuser").text();//当前登录用户的昵称
var url = "/user/public/topics?";
//console.log("userName:"+userName);
/*是否已关注*/
function isFollow() {
	$.ajax({
		url : "/follow/isFollow",
		type : "get",
		dataType : "json",
		data : {
			fid : userId
		},
		success : function(data) {
			if (data.success != null && data.success == true) {
				$(".btn-red-hollow").text("已关注");
			} else {
				$(".btn-red-hollow").text("关注");
			}
		},
		error : function(data) {

		}
	});
}
isFollow();
/*关注我的数量*/
function followMe() {
	$.ajax({
		url : "/follow/count/to",
		type : "get",
		dataType : "json",
		data : {
			fid : userId
		},
		success : function(data) {
			if (data.success != null && data.success == true) {
				$(".follow_count_to").text(data.data);
				$("#fan_title").attr("title", data.data);
			}
		},
		error : function(data) {

		}
	});
}
followMe();
/*我关注的数量*/
function MyFollow() {
	$.ajax({
		url : "/follow/count/for",
		type : "get",
		dataType : "json",
		data : {
			uid : userId
		},
		success : function(data) {
			if (data.success != null && data.success == true) {
				$(".follow_count_for").text(data.data);
				$("#follow_title").attr("title", data.data);
			}
		},
		error : function(data) {

		}
	});
}
MyFollow();
/* 关注和取消关注*/
function save() {
	var isFollow = $("#follow").text();
	//var userName = $("#user_id").text();
	var info;
	//console.log(isFollow);
	//alert(isFollow);
	var url;
	if (isFollow == "关注") {
		url = "/follow/save";
		info = "确定要开始关注" + authorName + "?";
	}
	if (isFollow == "已关注") {
		url = "/follow/delete";
		info = "确定要取消关注" + authorName + "?";
	}
	//alert("isFollow："+isFollow+"  url："+url);
	if (confirm(info)) {
		$.ajax({
			url : url,
			type : "post",
			dataType : "json",
			data : {
				fid : userId
			},
			success : function(data) {
				if (data.success != null && data.success == true
					&& data.error == "关注成功") {
					//alert(data.error);
				$(".btn-red-hollow").text("已关注");
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
/*话题列表*/
function topicList(pageNumber) {
	$(".cell_tabs a").removeClass("cell_tab_current");
	$(".cell_tabs a").addClass("cell_tab");
	$(".cell_tabs a").eq(1).removeClass("cell_tab");
	$(".cell_tabs a").eq(1).addClass("cell_tab_current");
	$.ajax({
		url : "/api/user/topic",
		type : "get",
		dataType : "json",
		data : {
			name:authorName,
			p : pageNumber
		},
		success : function(data) {
			$(".itemList").html('');
			for(var i = 0;i < data.data.list.length; i++){
				$(".itemList").append("<div class=\"panel-body paginate-bot\"\
					style=\"border-bottom: 1px solid #e2e2e2;\">\
					<div class=\"media\">\
					<div class=\"media-body\">\
					<div class=\"title\">\
					<a href=\"/topic/"+data.data.list[i].topicId+"\"> "+data.data.list[i].title+" </a>\
					</div>\
					<p>\
					<span><a href=\"/user/"+data.data.list[i].author+"\" class=\"author_name\">"+data.data.list[i].author+"</a></span>\
					<span class=\"hidden-sm hidden-xs\">•</span> <span\
					class=\"hidden-sm hidden-xs\"><a\
					href=\"/topic/"+data.data.list[i].topicId+"\">"+data.data.list[i].replyCount+"个评论</a></span> <span\
					class=\"hidden-sm hidden-xs\">•</span> <span\
					class=\"hidden-sm hidden-xs\">"+data.data.list[i].viewCount+"次点击</span> <span>•</span>\
					<span>"+data.data.list[i].createDate+"</span>\
					</p>\
					</div>\
					</div>\
					</div>");
			}
			$(".itemList").append("<div class=\"panel-footer\" id=\"paginate\"></div>");

			/*$(".pagination2").pagination(data.data.pageNumber,data.data.totalPage,10);*/
			paginate(data.data.totalRow,/*data.data.pageSize*/1,pageNumber,"#");
		},
		error : function(data) {

		}
	});
}
/*收藏的话题*/
function collectList() {
	$(".cell_tabs a").removeClass("cell_tab_current");
	$(".cell_tabs a").addClass("cell_tab");
	$(".cell_tabs a").eq(3).removeClass("cell_tab");
	$(".cell_tabs a").eq(3).addClass("cell_tab_current");
	$.ajax({
		url : "/api/user/collect",
		type : "get",
		dataType : "json",
		data : {
			name:authorName,
			p : 1
		},
		success : function(data) {
			$(".itemList").html('');
			for(var i = 0;i < data.data.list.length; i++){
				$(".itemList").append("<div class=\"panel-body paginate-bot\"\
					style=\"border-bottom: 1px solid #e2e2e2;\">\
					<div class=\"media\">\
					<div class=\"media-left\">\
					<a href=\"/user/"+data.data.list[i].author+"\"><img src=\"/resources/images/"+data.data.list[i].avatar+"\" class=\"avatar img-circle\" alt=\"\"></a>\
					</div>\
					<div class=\"media-body\">\
					<div class=\"title\">\
					<a href=\"/topic/"+data.data.list[i].topicId+"\"> "+data.data.list[i].title+" </a>\
					</div>\
					<p>\
					<span><a href=\"/user/"+data.data.list[i].author+"\" class=\"author_name\">"+data.data.list[i].author+"</a></span>\
					<span class=\"hidden-sm hidden-xs\">•</span> <span\
					class=\"hidden-sm hidden-xs\"><a\
					href=\"/topic/"+data.data.list[i].topicId+"\">"+data.data.list[i].replyCount+"个评论</a></span> <span\
					class=\"hidden-sm hidden-xs\">•</span> <span\
					class=\"hidden-sm hidden-xs\">"+data.data.list[i].viewCount+"次点击</span> <span>•</span>\
					<span>"+data.data.list[i].createDate+"</span>\
					</p>\
					</div>\
					</div>\
					</div>");
			}
		},
		error : function(data) {

		}
	});
}
/*评论列表*/
function replyList() {
	$(".cell_tabs a").removeClass("cell_tab_current");
	$(".cell_tabs a").addClass("cell_tab");
	$(".cell_tabs a").eq(2).removeClass("cell_tab");
	$(".cell_tabs a").eq(2).addClass("cell_tab_current");
	$.ajax({
		url : "/api/user/reply",
		type : "get",
		dataType : "json",
		data : {
			name:authorName,
			p : 1
		},
		success : function(data) {
			$(".itemList").html('<table class="table table-striped"><tbody></tbody></table>');
			for(var i = 0;i < data.data.list.length; i++){
				$(".table-striped").append("<tr>\
					<td>"+data.data.list[i].createDate+" 评论了 <a\
					href=\"/user/"+data.data.list[i].author+"\">"+data.data.list[i].author+"</a> 创建的话题 › <a\
					href=\"/topic/"+data.data.list[i].topicId+"\">"+data.data.list[i].title+"</a></td>\
					</tr>\
					<tr class=\"user_comments\">\
					<td class=\"show_big_image\">"+data.data.list[i].replyContent+"</td>\
					</tr>");
			}
		},
		error : function(data) {

		}
	});
}
/*关注人的话题*/
function followList() {
	$(".cell_tabs a").removeClass("cell_tab_current");
	$(".cell_tabs a").addClass("cell_tab");
	$(".cell_tabs a").eq(4).removeClass("cell_tab");
	$(".cell_tabs a").eq(4).addClass("cell_tab_current");
	$.ajax({
		url : "/api/user/follow/topic",
		type : "get",
		dataType : "json",
		data : {
			uid:userId,
			p : 1
		},
		success : function(data) {
			$(".itemList").html('');
			for(var i = 0;i < data.data.list.length; i++){
				$(".itemList").append("<div class=\"panel-body paginate-bot\"\
					style=\"border-bottom: 1px solid #e2e2e2;\">\
					<div class=\"media\">\
					<div class=\"media-left\">\
					<a href=\"/user/"+data.data.list[i].author+"\"><img src=\"/resources/images/"+data.data.list[i].avatar+"\" class=\"avatar img-circle\" alt=\"\"></a>\
					</div>\
					<div class=\"media-body\">\
					<div class=\"title\">\
					<a href=\"/topic/"+data.data.list[i].topicId+"\"> "+data.data.list[i].title+" </a>\
					</div>\
					<p>\
					<span><a href=\"/user/"+data.data.list[i].author+"\" class=\"author_name\">"+data.data.list[i].author+"</a></span>\
					<span class=\"hidden-sm hidden-xs\">•</span> <span\
					class=\"hidden-sm hidden-xs\"><a\
					href=\"/topic/"+data.data.list[i].topicId+"\">"+data.data.list[i].replyCount+"个评论</a></span> <span\
					class=\"hidden-sm hidden-xs\">•</span> <span\
					class=\"hidden-sm hidden-xs\">"+data.data.list[i].viewCount+"次点击</span> <span>•</span>\
					<span>"+data.data.list[i].createDate+"</span>\
					</p>\
					</div>\
					</div>\
					</div>");
			}
		},
		error : function(data) {

		}
	});
}
/*粉丝*/
function fansList() {
	$(".cell_tabs a").removeClass("cell_tab_current");
	$(".cell_tabs a").addClass("cell_tab");
	$(".cell_tabs a").eq(5).removeClass("cell_tab");
	$(".cell_tabs a").eq(5).addClass("cell_tab_current");
	$(".itemList").html('');
	$.ajax({
		url : "/api/user/fans",
		async : false,
		type : "get",
		dataType : "json",
		data : {
			fid:userId,
			p : 1
		},
		success : function(data) {
			$(".itemList").html('<ul class="fans-ul"></ul>');
			for(var i = 0;i < data.data.list.length; i++){
				
				$.ajax({
					url : "/follow/isFollow",
					async : false,
					type : "get",
					dataType : "json",
					data : {
						fid : data.data.list[i].userId
					},
					success : function(data2) {
						if (data2.success != null && data2.success == true) {
							//console.log(data.data.list[i].userName);
							//console.log(userName);
							//console.log(data.data.list[i].userName == userName);
							//$("#follow-btn").text("已关注");
							//$(".itemList ul .item").eq(data.data.list[i]).(".follow-btn").text("已关注");
							$(".fans-ul").append("\
								<li data-v-dc1504f6=\"\" class=\"item\">\
								<div data-v-dc1504f6=\"\" itemscope=\"itemscope\" itemtype=\"http://schema.org/Person\" class=\"user\">\
								<a data-v-dc1504f6=\"\" href=\"/user/"+data.data.list[i].userName+"\" \
								class=\"link\">\
								<div class=\"media-left\">\
								<img src=\"/resources/images/"+data.data.list[i].avatar+"\" class=\"avatar img-circle\" alt=\"\">\
								</div>\
								<div data-v-dc1504f6=\"\" class=\"info-box\">\
								<div data-v-dc1504f6=\"\" class=\"username\" title=\""+data.data.list[i].userId+"\">"+data.data.list[i].userName+"</div>\
								<div data-v-dc1504f6=\"\" class=\"detail\">"+data.data.list[i].signature+"</div>\
								</div><button data-v-dc1504f6=\"\" class=\"follow-btn\">已关注</button>\
								</a>\
								</div>\
								</li>");
						} else if (data2.success != null && data2.success == false && data2.error == "同一用户"){
							//$(".follow-btn").text("关注");
							//$(".itemList ul .item").eq(data.data.list[i]).(".follow-btn").text("已关注");
							$(".fans-ul").append("\
								<li data-v-dc1504f6=\"\" class=\"item\">\
								<div data-v-dc1504f6=\"\" itemscope=\"itemscope\" itemtype=\"http://schema.org/Person\" class=\"user\">\
								<a data-v-dc1504f6=\"\" href=\"/user/"+data.data.list[i].userName+"\" \
								class=\"link\">\
								<div class=\"media-left\">\
								<img src=\"/resources/images/"+data.data.list[i].avatar+"\" class=\"avatar img-circle\" alt=\"\">\
								</div>\
								<div data-v-dc1504f6=\"\" class=\"info-box\">\
								<div data-v-dc1504f6=\"\" class=\"username\" title=\""+data.data.list[i].userId+"\">"+data.data.list[i].userName+"</div>\
								<div data-v-dc1504f6=\"\" class=\"detail\">"+data.data.list[i].signature+"</div>\
								</div>\
								</a>\
								</div>\
								</li>");
						}else{
							$(".fans-ul").append("\
								<li data-v-dc1504f6=\"\" class=\"item\">\
								<div data-v-dc1504f6=\"\" itemscope=\"itemscope\" itemtype=\"http://schema.org/Person\" class=\"user\">\
								<a data-v-dc1504f6=\"\" href=\"/user/"+data.data.list[i].userName+"\" \
								class=\"link\">\
								<div class=\"media-left\">\
								<img src=\"/resources/images/"+data.data.list[i].avatar+"\" class=\"avatar img-circle\" alt=\"\">\
								</div>\
								<div data-v-dc1504f6=\"\" class=\"info-box\">\
								<div data-v-dc1504f6=\"\" class=\"username\" title=\""+data.data.list[i].userId+"\">"+data.data.list[i].userName+"</div>\
								<div data-v-dc1504f6=\"\" class=\"detail\">"+data.data.list[i].signature+"</div>\
								</div><button data-v-dc1504f6=\"\" class=\"follow-btn\">关注</button>\
								</a>\
								</div>\
								</li>");
						}
					},
					error : function(data2) {

					}
				});
			}
		},
		error : function(data) {

		}
	});
}
/*用户的提问*/
function topicQnaList() {
	$(".cell_tabs a").removeClass("cell_tab_current");
	$(".cell_tabs a").addClass("cell_tab");
	$(".cell_tabs a").eq(6).removeClass("cell_tab");
	$(".cell_tabs a").eq(6).addClass("cell_tab_current");
	$.ajax({
		url : "/api/user/topic/qna",
		type : "get",
		dataType : "json",
		data : {
			name:authorName,
			p : 1
		},
		success : function(data) {
			$(".itemList").html('');
			for(var i = 0;i < data.data.list.length; i++){
				$(".itemList").append("<div class=\"panel-body paginate-bot\"\
					style=\"border-bottom: 1px solid #e2e2e2;\">\
					<div class=\"media\">\
					<div class=\"media-body\">\
					<div class=\"title\">\
					<a href=\"/topic/"+data.data.list[i].topicId+"\"> "+data.data.list[i].title+" </a>\
					</div>\
					<p>\
					<span><a href=\"/user/"+data.data.list[i].author+"\" class=\"author_name\">"+data.data.list[i].author+"</a></span>\
					<span class=\"hidden-sm hidden-xs\">•</span> <span\
					class=\"hidden-sm hidden-xs\"><a\
					href=\"/topic/"+data.data.list[i].topicId+"\">"+data.data.list[i].replyCount+"个评论</a></span> <span\
					class=\"hidden-sm hidden-xs\">•</span> <span\
					class=\"hidden-sm hidden-xs\">"+data.data.list[i].viewCount+"次点击</span> <span>•</span>\
					<span>"+data.data.list[i].createDate+"</span>\
					</p>\
					</div>\
					</div>\
					</div>");
			}
			
		},
		error : function(data) {

		}
	});
}
$(document).on("click",".layui-laypage a",function(){
	var p = $(this).data("page");
	//alert(p);
    topicList(p);
  });