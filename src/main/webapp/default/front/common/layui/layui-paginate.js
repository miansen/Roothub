function paginate(count,limit,page,url){
	layui.use('laypage', function(){
		  var laypage = layui.laypage;
		  //执行一个laypage实例
		  laypage.render({
		    elem: 'paginate', //注意，这里的 paginate 是 ID，不用加 # 号
		    count: count, //数据总数，从服务端得到
		    limit:limit,//每页显示的条数。laypage将会借助 count 和 limit 计算出分页数。
		    //通过url获取当前页
		    curr: page,
		    //skin: 'molv', //加载内置皮肤，也可以直接赋值16进制颜色值，如:#c00  
		    groups:5,//连续显示分页数
		    theme:'#337ab7',//自定义主题。支持传入：颜色值
		    //skip: true, //是否开启跳页
		    //prev: '<', //若不显示，设置false即可  
		    //next: '>', //若不显示，设置false即可
		    //hash:'/tab=all&ptab=all&p',
		    jump: function(obj, first){
		        //obj包含了当前分页的所有参数，比如：
		        //console.log(obj.curr); //得到当前页，以便向服务端请求对应页的数据。
		        //console.log(obj.limit); //得到每页显示的条数
		        //首次不执行
		        if(!first){
		        	location.href = url+obj.curr;
		        }
		      }
		  });
		});
}