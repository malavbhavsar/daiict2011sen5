var activeDone = '';



function SwapDone(current) {
	    current = current.toLowerCase();
		var t = current;
		
    if (current != activeDone) {
  		
        $('#' + activeDone + '-nav').removeClass('active');
       //  $('#' + current).fadeIn(200);
        $('#' + activeDone).slideUp(3,function(){
												  $('#' + current + '-nav').addClass('active');
												  $('#' + current.toLowerCase().replace(' ','')).slideDown(300);
												 });
       
        activeDone = current;
        return false;
    } else {
        return false;
    }
}
$(function() {
		   $("#done-nav label").bind("click", function() {SwapDone($(this).attr("title"));
										
											return false;});
		   });