function nodeInfo(){
	$.ajax({
		url:"/api/user/logininfo",
		type:"get",
		dataType:"json",
		success:function(data){
			if(data.success == true){
				$(".col-md-3 #session").append('\
					<div class="panel-body">\
					<div class="media">\
					<div class="media-left">\
					<a href="/user/'+data.data.userName+'">\
					<img src="/resources/images/'+data.data.avatar+'" title="" class="avatar img-circle">\
					</a>\
					</div>\
					<div class="media-body">\
					<div class="media-heading">\
					<strong><a href="/user/'+data.data.userName+'">'+data.data.userName+'</a></strong>\
					<div style="color: #7A7A7A; font-size: 12px; margin-top:5px;">\
					<i>'+data.data.signature+'</i>\
					</div>\
					</div>\
					</div>\
					<div style="margin-top: 15px;">\
					<a href="/topic/create" style="font-size: 14px;"><button class="btn btn-success">发布话题</button></a>\
					</div>\
					</div>\
					<div class="sep10" style="height: 10px;"></div>\
					<table cellpadding="0" cellspacing="0" border="0" width="100%" class="table_fade" style="font-size: 14px;">\
					<tbody><tr>\
					<td width="33%" align="center"><a href="/user/topics" class="dark" style="display: block;"><span class="bigger">'+data.data.countTopic+'</span><div class="sep3"></div><span class="fade">我的主题</span></a></td>\
					<td width="34%" style="border-left: 1px solid rgba(100, 100, 100, 0.4); border-right: 1px solid rgba(100, 100, 100, 0.4);" align="center"><a href="/collect/topics" class="dark" style="display: block;"><span class="bigger">'+data.data.countCollect+'</span><div class="sep3"></div><span class="fade">我的收藏</span></a></td>\
					<td width="33%" align="center"><a href="/follow/topics" class="dark" style="display: block;"><span class="bigger">'+data.data.countFollow+'</span><div class="sep3"></div><span class="fade">特别关注</span></a></td>\
					</tr>\
					</tbody></table>\
					</div>\
					<div class="panel-footer" style="background-color: white">\
					<div class="row">\
					<span class="col-md-6"><a href="/notification/list"><span id="n_count">'+data.data.countNotReadNotice+'</span> 条未读消息</a></span>\
					<span class="col-md-6 text-right">积分：<a href="/top100">'+data.data.countScore+'</a></span>\
					</div>\
					</div>\
					');
			}else{
				$(".col-md-3 #session").append(data.data.intro);
				$(".tab .nav .member").hide();
			}
		},
		error:function(data){

		}
	});
};