$(function(){
	$("#feedback_add").on("click","#feedback_add_btn",function(){
		var info = $("#info").val();
		//console.log(info);
		if(!info){
			alert('您还没有提出建议');
			return false;
		}
		$.ajax({
			url:"/feedback/add",
			type:"post",
			dataTpe:"json",
			data:{
				info:info
			},
			success:function(data){
				if(data.success == true){
					//console.log(data);
					$(".panel-body").html("<h2>"+data.msg+"</h2>");
				}else{
					$(".panel-body").html("<h2>"+data.msg+"</h2>");
				}
			},
			error:function(data){

			}
		});
	});
});