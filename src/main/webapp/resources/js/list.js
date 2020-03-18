/**
 * 与表格相关的函数
 */
$(function() {
	// 全选与单选联动
	$("input[name='checkedAll']").on("change", function() {
		var checked = $(this).prop("checked");
		$("input[name='checked']").each(function() {
			if (checked == true) {
				$(this).prop("checked",true);
			} else {
				$(this).prop("checked",false);
			}
		});
	});
	
	// 全选与单选联动
	$("input[name='checked']").on("change", function() {
		var checkedAll = true;
		$("input[name='checked']").each(function() {
			var checked = $(this).prop("checked");
			if (checked == false) {
				checkedAll = false;
				return false;
			}
		});
		if (checkedAll) {
			$("input[name='checkedAll']").prop("checked", true);
		} else {
			$("input[name='checkedAll']").prop("checked", false);
		}
	});
});