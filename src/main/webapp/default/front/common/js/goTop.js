function showScroll() {
	$(window).scroll(function() {
		var scrollValue = $(window).scrollTop();
		//console.log(scrollValue);
		if (scrollValue > 200) {
			$('#back2Top').css("display", "flex");
		} else {
			$('#back2Top').css("display", "none");
		}
	});
}
showScroll();
$("#back2Top").click(function() {
	//缓慢效果回到顶部
	$('body,html').animate({
		scrollTop : 0
	}, 500);
	return false;
	//直接回到顶部
	//window.scroll(0,0);
});