/*****切换编辑器*****/
function switchEditor(type) {

    // 0：富文本编辑器
    if (type === 0){
        $("#codemirror").hide();
        $("#wangEditor").show();
        $("#codemirror-btn").hide();
        $("#wangEditor-btn").show();
    }

    // 1：Markdown 编辑器
    if(type === 1){
        $("#wangEditor").hide();
        $("#codemirror").show();
        $("#wangEditor-btn").hide();
        $("#codemirror-btn").show();
    }
}

$(function () {

    /****** 富文本编辑器 *****/
    var E = window.wangEditor;
    var wangEditor = new E('#wangEditor-content');
    wangEditor.customConfig.uploadFileName = 'file';
    wangEditor.customConfig.uploadImgServer = '/common/upload';
    wangEditor.customConfig.menus = [
        'head',  // 标题
        'bold',  // 粗体
        'italic',  // 斜体
        'underline',  // 下划线
        'strikeThrough',  // 删除线
        'link',  // 插入链接
        'list',  // 列表
        'quote',  // 引用
        'emoticon',  // 表情
        'image',  // 插入图片
        'table',  // 表格
        'code',  // 插入代码
        'undo',  // 撤销
        'redo'  // 重复
    ];

    wangEditor.create();

    $("#wangEditor-btn").click(function () {
        // 标题
        var title = $("#title").val();

        // html 格式的正文
        var contentHtml = wangEditor.txt.html();

        // 普通格式的正文
        // var contentText = wangEditor.txt.text();

        // 节点
        var node = $("#hidden-node").val();
        var nodeTitle = node ? node : $("#node option:selected").val();

        // 标签
        // var tag = $("#tag").val().trim();

        if (!title || title.length > 120) {
            toast("请输入标题，且最大长度在120个字符以内");
            return;
        }
        if (!nodeTitle || nodeTitle.length == 0) {
            toast("请选择一个节点");
            return;
        }
        /*if (!tag || tag.length == 0) {
            toast("请至少输入一个标签");
            return;
        }*/

        // 将标签以空格分割
        // var tags = tag.split(/\s+/);
        /*for (var i = 0; i < tags.length; i++) {
            if (tags[i].length > 10) {
                toast("每个标签的最大长度不能超过10个字符");
                return;
            }
        }*/

        var data = {
                "title": title,
                "content": contentHtml,
                "nodeId": nodeTitle,
            };
        	console.log(data);
        	console.log(JSON.stringify(data));
        $.ajax({
            url: '/api/v3/topic',
            contentType:"application/json;charset=utf-8",
            type: 'post',
            async: false,
            cache: false,
            dataType: 'json',
            data: JSON.stringify(data),
            success: function (data) {
                //console.log(JSON.stringify(data));
                if (data.success != null && data.success == true) {
                    window.location.href = "/topics/" + data.data.topic.topicId;
                } else {
                    toast(data.error);
                }
            },
            error: function (data) {
                toast(data.error);
            }
        })
    });

    /****** Markdown 编辑器*****/
    CodeMirror.keyMap.default["Shift-Tab"] = "indentLess";
    CodeMirror.keyMap.default["Tab"] = "indentMore";
    var CodeMirrorEditor = CodeMirror.fromTextArea(document.getElementById("codemirror-content"), {
        lineNumbers: false,     // 显示行数
        indentUnit: 4,         // 缩进单位为4
        tabSize: 4,
        matchBrackets: true,   // 括号匹配
        mode: 'markdown',     // Markdown模式
        lineWrapping: true,    // 自动换行
    });

    $("#codemirror-btn").click(function () {

        // 标题
        var title = $("#title").val();

        // 正文
        var content = CodeMirrorEditor.getDoc().getValue();

        // 节点
        var node = $("#hidden-node").val();
        var nodeTitle = node ? node : $("#node option:selected").val();

        // 标签
        // var tag = $("#tag").val().trim();

        if (!title || title.length > 120) {
            toast("请输入标题，且最大长度在120个字符以内");
            return;
        }
        if (!nodeTitle || nodeTitle.length == 0) {
            toast("请选择一个节点");
            return;
        }
        /*if (!tag || tag.length == 0) {
            toast("请至少输入一个标签");
            return;
        }*/

        // 将标签以空格分割
        // var tags = tag.split(/\s+/);
        /*for (var i = 0; i < tags.length; i++) {
            if (tags[i].length > 10) {
                toast("每个标签的最大长度不能超过10个字符");
                return;
            }
        }*/

        $.post("api/v3/topic", {
            title: title,
            content: content,
            nodeTitle: nodeTitle,
            // tag: tag,
            type: "1"
        }, function (data) {
            if (data.success != null && data.success == true) {
                toast("发布帖子成功", "success");
                setTimeout(function () {
                    window.location.href = "/topics/" + data.data.topic.topicId
                }, 700);
            } else {
                toast(data.error);
            }
        })
    })
})