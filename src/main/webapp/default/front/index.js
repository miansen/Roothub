$(function () {
    // $("#shouye").addClass("active");

    // 初始化板块的样式
    function section() {
        if (tab == "good") {
            $("#tab li:eq(1)").addClass("active");
        } else if (tab == "newest") {
            $("#tab li:eq(2)").addClass("active");
        } else if (tab == "noReply") {
            $("#tab li:eq(3)").addClass("active");
        } else {
            $("#tab li:eq(0)").addClass("active");
        }
    };

    // section();

    //改变当前选中板块的样式
    /*$("#tab li").each(function () {
        if (tab == $(this).attr("class")) {
            $("#tab li").removeClass("active");
            $(this).addClass("active");
        }
    });*/

    /*积分榜*/
    (function () {
        $.ajax({
            url: "/api/user/top100",
            type: "get",
            dataType: "json",
            data: {limit: 10},
            success: function (data) {
                if (data.success == true) {
                    $(".panel .top100").append('\
    			  <ol></ol>\
    	  ');
                    for (var i = 0; i < data.data.length; i++) {
                        $(".panel .top100 ol").append('\
    				  <li>\
    				  <span class="top_score">' + data.data[i].score + '</span>\
    				  <span class="user_name" style="margin-right: 20px;float: right;"><a href="/user/' + data.data[i].userName + '">' + data.data[i].userName + '</a></span>\
    				  </li>\
    		  ');
                    }
                }
            },
            error: function (data) {

            }
        });
    })();

    //格式化时间
    $(".formate-date").each(function (i, e) {
        $(this).text(formatDate(Date.parse($(this).text())));
    })
});