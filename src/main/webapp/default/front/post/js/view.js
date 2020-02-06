// 话题ID
var topicId = $("#hidden-topicId").val();

var tid = $("#hidden-topicId").val();

/***** 获取登录用户的信息 *****/
(function () {
    $.ajax({
        type: "get",
        url: "/session",
        dataType: "json",
        success: function (data) {
            if (data.success != null && data.success == true) {
                $("#pinglun").show();
                $("#collect").show();
            }
        },
        error: function (data) {

        }
    });
})();

/***** 是否已收藏了此话题 *****/
(function () {
    $.ajax({
        url: "/collect/isCollect",
        type: "get",
        dataType: "json",
        data: {tid: tid},
        success: function (data) {
            if (data.success != null && data.success == true) {
                $(".collectTopic").text("取消收藏");
            } else {
                $(".collectTopic").text("加入收藏");
            }
        },
        error: function (data) {

        }
    });
})();

/***** 收藏和取消收藏话题 *****/
function save() {
    var collectTopic = $(".collectTopic").text();
    var url;
    if (collectTopic == "加入收藏") {
        url = "/collect/save";
    }
    if (collectTopic == "取消收藏") {
        url = "/collect/delete";
    }
    $.ajax({
        url: url,
        type: "post",
        dataType: "json",
        data: {tid: tid},
        success: function (data) {
            if (data.success != null && data.success == true && data.error == "收藏成功") {
                //alert(JSON.stringify(data));
                $(".collectTopic").text("取消收藏");
            }
            if (data.success != null && data.success == true && data.error == "取消收藏成功") {
                //alert(JSON.stringify(data));
                $(".collectTopic").text("加入收藏");
            }
        },
        error: function (data) {

        }
    })
}

/***** 点赞 *****/
function upCount() {
    $.ajax({
        url: "/topic/vote/count",
        type: "get",
        dataType: "json",
        data: {
            tid: tid,
            vote: true
        },
        success: function (data) {
            var upNumber = data.data;
            //console.log("赞同=="+upNumber);
            if (data.success != null && data.success == true && data.data > 0) {
                $(".votes .vote_up").html('');
                $(".votes .vote_up").append("<li class=\"fa fa-chevron-up\"></li>" + data.data + "");
                $(".votes .vote_up").attr("title", data.data + " 赞同");
            }
        },
        error: function (data) {

        }
    });
}

/***** 点踩 *****/
function downCount() {
    $.ajax({
        url: "/topic/vote/count",
        type: "get",
        dataType: "json",
        data: {
            tid: tid,
            vote: false
        },
        success: function (data) {
            var downNumber = data.data;
            //console.log("反对=="+downNumber);
            if (data.success != null && data.success == true && data.data > 0) {
                $(".votes .vote_down").html('');
                $(".votes .vote_down").append("<li class=\"fa fa-chevron-down\"></li>" + data.data + "");
                $(".votes .vote_down").attr("title", data.data + " 反对");
            }
        },
        error: function (data) {

        }
    });
}

upCount();
downCount();


function voteTopic(tid, action) {
    $.ajax({
        url: "/topic/vote",
        type: "get",
        dataType: "json",
        data: {
            tid: tid,
            vote: action
        },
        success: function (data) {
            if (data.success != null && data.success == true) {
                upCount();
                downCount();
            }
        },
        error: function (data) {

        }
    });


}

/*****作者其它话题*****/
/*(function () {
    var userName = $('.author').text();
    $(".col-md-3").append('\
				<div class="panel panel-default">\
				  <div class="panel-heading"><span>作者其它话题</span></div>\
				  <table class="table" style="font-size: 14px;">\
				    <tbody></tbody>\
				  </table>\
				</div>');
    $.ajax({
        url: "/api/user/other/topic",
        type: "get",
        dataType: "json",
        data: {
            userName: userName,
            topicId: tid,

        },
        success: function (data) {
            if (data.success == true) {
                for (var i = 0; i < data.data.length; i++) {
                    $(".col-md-3 .panel .table tbody").append('\
								<tr><td><a href="/topic/' + data.data[i].topicId + '">' + data.data[i].title + '</a></td><tr>\
								');
                }
            }
        },
        error: function (data) {

        }
    });
})();*/

/*****切换编辑器*****/
function switchEditor(self) {
    // 获取编辑器的类型
    var type = $(self).context.children[0].innerText;

    // 富文本编辑器
    if (type === "富文本编辑器") {
        $("#codemirror").hide();
        $("#wangEditor").show();
        $("#codemirror-btn").hide();
        $("#wangEditor-btn").show();
        $("#editor-type").text("Markdown编辑器");
    }

    // Markdown编辑器
    if (type === "Markdown编辑器") {
        $("#wangEditor").hide();
        $("#codemirror").show();
        $("#wangEditor-btn").hide();
        $("#codemirror-btn").show();
        $("#editor-type").text("富文本编辑器");
    }
}

$(function () {
    /****** 富文本编辑器 *****/
    var E = window.wangEditor;
    var wangEditor = new E('#wangEditor-content');
    wangEditor.customConfig.debug = true;
    wangEditor.customConfig.uploadFileName = 'file';
    wangEditor.customConfig.uploadImgServer = '/common/wangEditorUpload';
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

    /* 回复话题 */
    $("#wangEditor-btn").click(function () {

        // html 格式的正文
        var contentHtml = wangEditor.txt.html();

        // 普通格式的正文
        // var contentText = wangEditor.txt.text();

        if (!contentHtml) {
            toast('请输入回复内容哦');
            return false;
        }

        $.ajax({
            url: '/reply/save',
            type: 'post',
            dataType: 'json',
            data: {
                content: contentHtml,
                topicId: topicId,
                type: "0"
            },
            success: function (data) {
                if (data.success != null && data.success == true) {
                    toast("评论成功", "success");
                    setTimeout(function () {
                        window.location.href = "/topic/" + data.data.reply.topicId;
                    }, 700);
                } else {
                    alert(data.data.error);
                }
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

        //正文
        var content = CodeMirrorEditor.getDoc().getValue();

        if (!content) {
            toast("请输入回复内容哦");
            return;
        }

        $.post("/reply/save", {
            content: content,
            topicId: topicId,
            type: "1"
        }, function (data) {
            if (data.success != null && data.success == true) {
                toast("评论成功", "success");
                setTimeout(function () {
                    window.location.href = "/topic/" + data.data.reply.topicId;
                }, 700);
            } else {
                toast(data.error);
            }
        })
    })
});