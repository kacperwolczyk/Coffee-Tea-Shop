$(document).ready(function() {
	"use strict";
		
	// OPEN MODAL ON TRIGGER CLICK
	$(".quickViewTrigger").on('click', function () {
		var $quickview = $(this).next(".quickViewContainer");
		$quickview.css({"display":"block"});
	});
		
	// CLOSE MODAL WITH MODAL CLOSE BUTTON
	$(".close, window").click(function() {
		$(".quickViewContainer").css({"display":"none"});
	});
		
});
	
// CLOSE MODAL ON CLICK OUTSIDE MODAL
$(document).mouseup(function (e) {
	"use strict";
	var container = $(".quickViewContainer");
	if (!container.is(e.target) && container.has(e.target).length === 0) 
	  {
	    $(".quickViewContainer").css({"display":"none"});
	  }
});