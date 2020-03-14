var dialog = function() {
	/**
	 * 删除确认框
	 * href: 点击确定后跳转的地址
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
		});
	}
}