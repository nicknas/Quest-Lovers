/**
 * 
 */

$(document).ready(function(){
	
	$('.checkbox-editor').click(function () {
	    if($(".checkbox-editor:checked").length > 0){
	         $('#btn-eliminar-editor').show();
	    } else {
	         $('#btn-eliminar-editor').hide(); 
	    }
	});
	
	$('#btn-cancelar-editor').click(function() {
		$('#formulario-editor')[0].reset();
		$('#collapse1').collapse("hide");
	});
});