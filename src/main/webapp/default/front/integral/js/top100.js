$(function(){
	(function () {
		$.ajax({
			url:"/api/user/top100",
			type:"get",
			dataType:"json",
			data:{limit:100},
			success:function(data){
				if(data.success == true){
					$(".panel-body .inner").append('\
						<table class="table table-condensed table-striped">\
						<thead>\
						<tr>\
						<th>#</th>\
						<th>用户名</th>\
						<th>积分</th>\
						<th>主题数</th>\
						<th>评论数</th>\
						</tr>\
						</thead>\
						<tbody>\
						\
						</tbody>\
						</table>\
						');
					for(var i = 0;i < data.data.length;i++){
						$(".panel-body .inner table tbody").append('\
							<tr>\
							<td><b>'+(i+1)+'</b></td>\
							<td>\
							<a class="user_avatar" href="/user/'+data.data[i].userName+'">\
							<img src="'+data.data[i].avatar+'" title="'+data.data[i].userName+'">\
							</a>\
							<span class="sp10"></span>\
							<a href="/user/'+data.data[i].userName+'">'+data.data[i].userName+'</a></td>\
							<td>'+data.data[i].score+'</td>\
							<td>'+data.data[i].countTopic+'</td>\
							<td>'+data.data[i].countReply+'</td>\
							</tr>\
							');
					}
				}
			},
			error:function(data){

			}
		});
	})();
});