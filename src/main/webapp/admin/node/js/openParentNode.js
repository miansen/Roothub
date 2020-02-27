// 打开父节点页面
function openParentNode() {
    layui.use('layer', function(){
        var layer = layui.layer;
        layer.open({
            title: ['选择父节点', 'font-size:18px;'],
            type: 2,
            area: ['700px', '450px'],
            fixed: false, //不固定
            maxmin: true,
            content: '/admin/node/parent' //这里content是一个URL，如果你不想让iframe出现滚动条，你还可以content: ['http://sentsin.com', 'no']
        });
    });
}