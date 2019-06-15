$(function(){
	(function (){
		//改变当前选中section的样式
	    $(".node_header_tabs a").each(function(){
	    	if(nodeTabCode == $(this).attr("id")){
	    		$(this).addClass("node_header_tab_current");
	    	}
	    })
	})();
});