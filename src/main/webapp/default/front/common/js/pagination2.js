(function ($) {
	/**
	 * curr:当前页
	 * all:总页数
	 * count:显示的页数
	 */
    $.fn.pagination = function (curr, all, count) {
        //容错处理
        if (all <= 0) {
            all = 1;
        }
        if (curr <= 0) {
            curr = 1;
        } else if (curr > all) {
            curr = all;
        }
        //默认显示页数为10
        if (!count) {
            count = 10;
        } else if (count < 1) {
            count = 1;
        }
        //计算显示的页数
        var from = curr - parseInt(count / 2);
        var to = curr + parseInt(count / 2) + (count % 2) - 1;
        //显示的页数容处理
        if (from <= 0) {
            from = 1;
            to = from + count - 1;
            if (to > all) {
                to = all;
            }
        }
        if (to > all) {
            to = all;
            from = to - count + 1;
            if (from <= 0) {
                from = 1;
            }
        }
        //写入（可以根据自己需求修改）
        if (curr > 1) {
            var prev = $("<li><a href='"+url+"p=1'>&laquo;</a></li>");
            this.append(prev);
        }
        for (var i = from; i <= to; i++) {
            if (i == curr) {
                var li = $("<li class='active'><a href='"+url+"p="+i+"'>" + i + "</a></li>");
                this.append(li);
            } else {
                var li = $("<li><a href='"+url+"p="+i+"'>" + i + "</a></li>");
                this.append(li);
            }
        }
        if (curr < all) {
            var prev = $("<li><a href='"+url+"p="+all+"'>&raquo;</a></li>");
            this.append(prev);
        }
    }
})(jQuery);