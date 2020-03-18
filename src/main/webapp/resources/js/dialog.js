/**
 * 此文件封装了一个全局的 dialog 对象，可以使用此对象来操作一些跟弹窗有关的方法。
 */
$(function() {
	function dialog() {
		
		// 设置 bootbox 的语言环境
		bootbox.setLocale("zh_CN");
		
		/**
		 * 删除确认框
		 * 
		 * @param href 点击确定后跳转的地址
		 */
		this.deleteConfirm = function(href) {
			bootbox.confirm({
				size: "small",
			    message: "确定要删除吗？",
			    buttons: {
			        confirm: {
			            label: '确定',
			            className: 'btn-success'
			        },
			        cancel: {
			            label: '取消',
			            className: 'btn-danger'
			        }
			    },
			    callback: function (result) {
			        if (result) {
			        	location.href = href;
			        }
			    }
			}).find('.modal-content').css({
				top: function() {
					var scrollTop = parent.document.documentElement.scrollTop || document.body.scrollTop;
					var topHeight = (window.screen.height / 4) + scrollTop;
					return topHeight;
					}
			});
		};
		
		/**
		 * 跳转到某个地址，并显示 loading 框。
		 * 
		 * @param href 跳转的地址
		 * @param target 跳转的目标
		 */
		this.href = function(href, target) {
			this.loading();
			$(location).attr({href: href, target: target});
		};
		
		/**
		 * loading
		 */
		this.loading = function() {
			bootbox.dialog({
				size: 'small',
				message: '<div class="text-center"><i class="fa fa-spinner fa-pulse fa-3x fa-fw" style="color: #fff"></i></div>', 
				closeButton: false,
			}).find('.modal-content').css({
				width: "0", 
				top: function() {
					var scrollTop = parent.document.documentElement.scrollTop || document.body.scrollTop;
					var topHeight = (window.screen.height / 4) + scrollTop;
					return topHeight;
					}
			});
		}
	}
	window.dialog = new dialog();
});