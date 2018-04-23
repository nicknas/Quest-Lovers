jQuery(document).ready(function(){
	var id = getParam("id");
	 var data;
	 var url= "static/jsons/esqueleto"+ id +".json";
	jQuery.ajax({
	    // En data puedes utilizar un objeto JSON, un array o un query string
	    data:data,
	    //Cambiar a type: POST si necesario
	    type: "GET",
	    // Formato de datos que se espera en la respuesta
	    dataType: "json",
	    // URL a la que se enviará la solicitud Ajax
	    url: url,
	})
	 .done(function( data, textStatus, jqXHR ) {
	     if ( console && console.log ) {
	         console.log( "La solicitud se ha completado correctamente." );
	         console.log(data );
	     }
	 })
	 .fail(function( jqXHR, textStatus, errorThrown ) {
	     if ( console && console.log ) {
	         console.log( "La solicitud a fallado: " +  textStatus);
	     }
	});
});

function getParam(name){
	var results = new RegExp('[\?&]' + name + '=([^&#]*)').exec(window.location.href);
	return results[1] || 0;
}