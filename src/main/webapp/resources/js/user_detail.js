
	$("#loginuser").addClass("active");
	var fid = $("#user_id").attr("title");
    $.ajax({
    	url:"/follow/isFollow",
    	type:"get",
    	dataType:"json",
    	data:{fid:fid},
    	success:function(data){
    		if(data.success != null && data.success == true){
				$(".btn-red-hollow").text("取消关注");
			}else{
				$(".btn-red-hollow").text("关注");
			}
    	},
    	error:function(data){
    		
    	}
    });
    /* 关注和取消关注*/
    function save(){
    	var isFollow = $(".btn-red-hollow").text();
        //console.log(isFollow);
        //alert(isFollow);
    	var url;
    	/*因为有两个（大小屏）btn-red-hollow，所以这里的isFollow == "关注关注"或者"取消关注取消关注"*/
    	if(isFollow == "关注关注"){
			url = "/follow/save";
		}
    	if(isFollow == "取消关注取消关注"){
			url = "/follow/delete";
		}
    	//alert("isFollow："+isFollow+"  url："+url);
    	$.ajax({
    		url:url,
    		type:"post",
    		dataType:"json",
    		data:{fid:fid},
    		success:function(data){
    			if(data.success != null && data.success == true && data.error == "关注成功"){
    				//alert(data.error);
    				$(".btn-red-hollow").text("取消关注");
    			}
    			if(data.success != null && data.success == true && data.error == "取消关注成功"){
    				//alert(data.error);
    				$(".btn-red-hollow").text("关注");
    			}
    		},
    		error:function(data){
    			
    		}
    	})
    }
