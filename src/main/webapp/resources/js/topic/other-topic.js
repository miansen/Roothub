$(function() {
	(function() {
		var userName = $('.media-body span').eq(2).text();
		$(".col-md-3").append('\
				<div class="panel panel-default">\
				  <div class="panel-heading"><span style="color: #ccc;">作者其它话题</span></div>\
				  <table class="table" style="font-size: 14px;">\
				    <tbody></tbody>\
				  </table>\
				</div>');
		$.ajax({
			url : "/api/user/other/topic",
			type : "get",
			dataType : "json",
			data : {
				userName : userName,
				topicId : tid,

			},
			success : function(data) {
				if (data.success == true) {
					for (var i = 0; i < data.data.length; i++) {
						$(".col-md-3 .panel .table tbody").append('\
								<tr><td><a href="/topic/'+data.data[i].topicId+'" style="color:#778087">'+ data.data[i].title+ '</a></td><tr>\
								');
					}
				}
			},
			error : function(data) {

			}
		});
	})();
});