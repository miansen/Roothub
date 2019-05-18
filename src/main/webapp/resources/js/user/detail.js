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
				$(".btn-follow").text("取消特别关注");
				$(".btn-follow").addClass("btn-default");
			} else {
				$(".btn-follow").text("加入特别关注");
				$(".btn-follow").addClass("btn-success");
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
	if (isFollow == "加入特别关注") {
		url = "/follow/save";
		info = "确定要开始关注" + authorName + "?";
	}
	if (isFollow == "取消特别关注") {
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
				// $(".btn-follow").text("取消特别关注");
				location.reload();
			}
			if (data.success != null && data.success == true
				&& data.error == "取消关注成功") {
					//alert(data.error);
				// $(".btn-follow").text("加入特别关注");
				location.reload();
			}
		},
		error : function(data) {

		}
	})
	}
}
$(".cell_tab_current").trigger("click");//默认点击
/*动态*/
/*function activitiesList(){
	$(".cell_tabs a").removeClass("cell_tab_current");
	$(".cell_tabs a").addClass("cell_tab");
	$(".cell_tabs a").eq(0).removeClass("cell_tab");
	$(".cell_tabs a").eq(0).addClass("cell_tab_current");
	$(".itemList").html('<h1>activities</h1>');
}*/

/*主题*/
function topicList(pageNumber) {
	$(".cell_tabs a").removeClass("cell_tab_current");
	$(".cell_tabs a").addClass("cell_tab");
	$(".cell_tabs a").eq(0).removeClass("cell_tab");
	$(".cell_tabs a").eq(0).addClass("cell_tab_current");
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
					<div class=\"tip\">\
					<p>\
					<span><a href=\"/n/"+data.data.list[i].nodeTitle+"\" class=\"node\">"+data.data.list[i].nodeTitle+"</a></span>\
					<span>•</span>\
					<a href=\"/user/"+data.data.list[i].author+"\" class=\"author_name\">"+data.data.list[i].author+"</a>\
					<span class=\"hidden-sm hidden-xs\">•</span>\
					<span class=\"hidden-sm hidden-xs\">"+data.data.list[i].viewCount+"次点击</span>\
					<span>•</span>\
					<span>"+formatDate(Date.parse(data.data.list[i].createDate))+"</span>\
					</p>\
					</div>\
					</div>\
					<div class=\"media-right\"><span class=\"badge badge-default\"><a href=\"/topic/"+data.data.list[i].topicId+"\">"+data.data.list[i].replyCount+"</a></span></div>\
					</div>\
					</div>");
			}
			$(".itemList").append("<div class=\"panel-footer\" id=\"paginate\"></div>");
			paginate(data.data.totalRow,data.data.pageSize,pageNumber,"#");
		},
		error : function(data) {

		}
	});
}
/*评论*/
function replyList(pageNumber) {
	$(".cell_tabs a").removeClass("cell_tab_current");
	$(".cell_tabs a").addClass("cell_tab");
	$(".cell_tabs a").eq(1).removeClass("cell_tab");
	$(".cell_tabs a").eq(1).addClass("cell_tab_current");
	$.ajax({
		url : "/api/user/reply",
		type : "get",
		dataType : "json",
		data : {
			name:authorName,
			p : pageNumber
		},
		success : function(data) {
			$(".itemList").html('<table class="table table-striped"><tbody></tbody></table>');
			for(var i = 0;i < data.data.list.length; i++){
				$(".table-striped").append("<tr>\
					<td>"+formatDate(Date.parse(data.data.list[i].createDate))+" 评论了 <a\
					href=\"/user/"+data.data.list[i].author+"\">"+data.data.list[i].author+"</a> 创建的话题 › <a\
					href=\"/topic/"+data.data.list[i].topicId+"\">"+data.data.list[i].title+"</a></td>\
					</tr>\
					<tr class=\"user_comments\">\
					<td class=\"show_big_image\">"+data.data.list[i].replyContent+"</td>\
					</tr>");
			}
			$(".itemList").append("<div class=\"panel-footer\" id=\"paginate\"></div>");
			paginate(data.data.totalRow,data.data.pageSize,pageNumber,"#");
		},
		error : function(data) {

		}
	});
}

/*收藏*/
function collectList(pageNumber) {
	$(".cell_tabs a").removeClass("cell_tab_current");
	$(".cell_tabs a").addClass("cell_tab");
	$(".cell_tabs a").eq(2).removeClass("cell_tab");
	$(".cell_tabs a").eq(2).addClass("cell_tab_current");
	$.ajax({
		url : "/api/user/collect",
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
					<div class=\"media-left\"><a href=\"/user/"+data.data.list[i].author+"\"><img src=\""+data.data.list[i].avatar+"\" class=\"avatar img-circle\" alt=\"\"></a></div>\
					<div class=\"media-body\">\
					<div class=\"title\"> <a href=\"/topic/"+data.data.list[i].topicId+"\"> "+data.data.list[i].title+" </a></div>\
					<div class=\"tip\">\
					<p>\
					<span><a href=\"/n/"+data.data.list[i].nodeTitle+"\" class=\"node\">"+data.data.list[i].nodeTitle+"</a></span>\
					<span>•</span>\
					<a href=\"/user/"+data.data.list[i].author+"\" class=\"author_name\">"+data.data.list[i].author+"</a>\
					<span class=\"hidden-sm hidden-xs\">•</span>\
					<span class=\"hidden-sm hidden-xs\">"+data.data.list[i].viewCount+"次点击</span>\
					<span>•</span>\
					<span>"+formatDate(Date.parse(data.data.list[i].createDate))+"</span>\
					</p>\
					</div>\
					</div>\
					<div class=\"media-right\"><span class=\"badge badge-default\"><a href=\"/topic/"+data.data.list[i].topicId+"\">"+data.data.list[i].replyCount+"</a></span></div>\
					</div>\
					</div>");
			}
			$(".itemList").append("<div class=\"panel-footer\" id=\"paginate\"></div>");
			paginate(data.data.totalRow,data.data.pageSize,pageNumber,"#");
		},
		error : function(data) {

		}
	});
}
/*关注*/
function followList(pageNumber) {
	$(".cell_tabs a").removeClass("cell_tab_current");
	$(".cell_tabs a").addClass("cell_tab");
	$(".cell_tabs a").eq(3).removeClass("cell_tab");
	$(".cell_tabs a").eq(3).addClass("cell_tab_current");
	$.ajax({
		url : "/api/user/follow/topic",
		type : "get",
		dataType : "json",
		data : {
			uid:userId,
			p : pageNumber
		},
		success : function(data) {
			$(".itemList").html('');
			for(var i = 0;i < data.data.list.length; i++){
				$(".itemList").append("<div class=\"panel-body paginate-bot\" style=\"border-bottom: 1px solid #e2e2e2;\">\
					<div class=\"media\">\
					<div class=\"media-left\"><a href=\"/user/"+data.data.list[i].author+"\"><img src=\""+data.data.list[i].avatar+"\" class=\"avatar img-circle\" alt=\"\"></a></div>\
					<div class=\"media-body\">\
					<div class=\"title\"><a href=\"/topic/"+data.data.list[i].topicId+"\"> "+data.data.list[i].title+" </a></div>\
					<div class=\"tip\">\
					<p>\
					<span><a href=\"/n/"+data.data.list[i].nodeTitle+"\" class=\"node\">"+data.data.list[i].nodeTitle+"</a></span>\
					<span>•</span>\
					<a href=\"/user/"+data.data.list[i].author+"\" class=\"author_name\">"+data.data.list[i].author+"</a>\
					<span class=\"hidden-sm hidden-xs\">•</span>\
					<span class=\"hidden-sm hidden-xs\">"+data.data.list[i].viewCount+"次点击</span>\
					<span>•</span>\
					<span>"+formatDate(Date.parse(data.data.list[i].createDate))+"</span>\
					</p>\
					</div>\
					</div>\
					<div class=\"media-right\"><span class=\"badge badge-default\"><a href=\"/topic/"+data.data.list[i].topicId+"\">"+data.data.list[i].replyCount+"</a></span></div>\
					</div>\
					</div>");
			}
			$(".itemList").append("<div class=\"panel-footer\" id=\"paginate\"></div>");
			paginate(data.data.totalRow,data.data.pageSize,pageNumber,"#");
		},
		error : function(data) {

		}
	});
}
/*粉丝*/
function fansList(pageNumber) {
	$(".cell_tabs a").removeClass("cell_tab_current");
	$(".cell_tabs a").addClass("cell_tab");
	$(".cell_tabs a").eq(4).removeClass("cell_tab");
	$(".cell_tabs a").eq(4).addClass("cell_tab_current");
	$(".itemList").html('');
	$.ajax({
		url : "/api/user/fans",
		async : false,
		type : "get",
		dataType : "json",
		data : {
			fid:userId,
			p : pageNumber
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
								<img src=\""+data.data.list[i].avatar+"\" class=\"avatar img-circle\" alt=\"\">\
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
								<img src=\""+data.data.list[i].avatar+"\" class=\"avatar img-circle\" alt=\"\">\
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
								<img src=\""+data.data.list[i].avatar+"\" class=\"avatar img-circle\" alt=\"\">\
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
			$(".itemList").append("<div class=\"panel-footer\" id=\"paginate\"></div>");
			paginate(data.data.totalRow,data.data.pageSize,pageNumber,"#");
		},
		error : function(data) {

		}
	});
}
/*提问*/
function topicQnaList(pageNumber) {
	$(".cell_tabs a").removeClass("cell_tab_current");
	$(".cell_tabs a").addClass("cell_tab");
	$(".cell_tabs a").eq(5).removeClass("cell_tab");
	$(".cell_tabs a").eq(5).addClass("cell_tab_current");
	$.ajax({
		url : "/api/user/topic/qna",
		type : "get",
		dataType : "json",
		data : {
			name:authorName,
			p : pageNumber
		},
		success : function(data) {
			$(".itemList").html('');
			for(var i = 0;i < data.data.list.length; i++){
				$(".itemList").append("<div class=\"panel-body paginate-bot\" style=\"border-bottom: 1px solid #e2e2e2;\">\
					<div class=\"media\">\
					<div class=\"media-body\">\
					<div class=\"title\"><a href=\"/topic/"+data.data.list[i].topicId+"\"> "+data.data.list[i].title+" </a></div>\
					<div class=\"tip\">\
					<p>\
					<span><a href=\"/n/"+data.data.list[i].nodeTitle+"\" class=\"node\">"+data.data.list[i].nodeTitle+"</a></span>\
					<span>•</span>\
					<a href=\"/user/"+data.data.list[i].author+"\" class=\"author_name\">"+data.data.list[i].author+"</a>\
					<span class=\"hidden-sm hidden-xs\">•</span>\
					<span class=\"hidden-sm hidden-xs\">"+data.data.list[i].viewCount+"次点击</span>\
					<span>•</span>\
					<span>"+formatDate(Date.parse(data.data.list[i].createDate))+"</span>\
					</p>\
					</div>\
					</div>\
					<div class=\"media-right\"><span class=\"badge badge-default\"><a href=\"/topic/"+data.data.list[i].topicId+"\">"+data.data.list[i].replyCount+"</a></span></div>\
					</div>\
					</div>");
			}
			$(".itemList").append("<div class=\"panel-footer\" id=\"paginate\"></div>");
			paginate(data.data.totalRow,data.data.pageSize,pageNumber,"#");
		},
		error : function(data) {

		}
	});
}

/*分页*/
$(document).on("click",".layui-laypage a",function(){
	var p = $(this).data("page");
	$(".cell_tabs a").each(function(index,element){
		/*if($(this).attr('class') == "cell_tab_current" && index == 0){
			activitiesList();
		}*/
		if($(this).attr('class') == "cell_tab_current" && index == 0){
			//console.log($(this).attr('class')+" "+index);
			//console.log("p:"+p);
			topicList(p);
		}
		if($(this).attr('class') == "cell_tab_current" && index == 1){
			//console.log($(this).attr('class')+" "+index);
			//console.log("p:"+p);
			replyList(p);
		}
		if($(this).attr('class') == "cell_tab_current" && index == 2){
			collectList(p);
		}
		if($(this).attr('class') == "cell_tab_current" && index == 3){
			followList(p);
		}
		if($(this).attr('class') == "cell_tab_current" && index == 4){
			fansList(p);
		}
		if($(this).attr('class') == "cell_tab_current" && index == 5){
			//console.log($(this).attr('class')+" "+index);
			//console.log("p:"+p);
			topicQnaList(p);
		}
  	//console.log($(this).attr('class')+index);
  });
});

/*最后回复用户*/
function lastReplyAuthor(lastReplyAuthor){
	if(lastReplyAuthor){
		return	"<span>•</span><span> 最后回复来自 <a href=\"/user/"+lastReplyAuthor+"\">"+lastReplyAuthor+"</a></span>";
	}else{
		return "";
	}
};

/**
 * 用户发表话题或者回复的数量
 * @param type:类型 topic(话题)、reply(回复)
 * @param userName:昵称
 * @returns
 */
function countTopicByUserName(type,userName){
	var ztsl = 0;
	$.ajax({
		url:"/api/numberOfCountTopicsOrReply",
		type:"get",
		async:false,
		dataType:"json",
		data:{type:type,userName:userName},
		success:function (data){
			ztsl = data.msg;
		},
		error:function(data){

		}
	});
	return ztsl;
}
//console.log(countTopicByUserName("reply","public"));

