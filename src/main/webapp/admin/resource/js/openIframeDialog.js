/**
 * 打开 iframe 窗口
 * 
 * @param title 窗口的标题
 * @param url 请求 URL
 * @returns
 */
function openIframeDialog(title, url) {
	layui.use('layer', function() {
		var layer = layui.layer;
		layer.open({
			title : [ title, 'font-size:18px;' ],
			type : 2,
			area : [ '700px', '450px' ],
			fixed : false,
			maxmin : true,
			content : url
		});
	});
}