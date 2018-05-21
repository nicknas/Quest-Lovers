var QUEST_TITTLE_CSS ="h1.title-quest";

jQuery(document).ready(function(){
	var id = getParam("id");
	 var data;
	 var datajson;
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
	         if(data.quest.titulo !== undefined && data.quest.titulo !== null) jQuery(QUEST_TITTLE_CSS).text(data.quest.titulo);
	         jQuery("p.quest-snipped-text").text(data.quest.preguntas.initial.texto);
	         jQuery(".botones_respuestas").empty();
        	 if (eval("data.quest.preguntas.initial.respID").length == 2){
        		 jQuery(".botones_respuestas").append("<div class='col-md-4 col-md-offset-2 col-xs-12'><a class='btn btn-block btn-main-color questopt r1' href='#'></a></div>");
        		 jQuery(".botones_respuestas").append("<div class='col-md-4 col-xs-12'><a class='btn btn-block btn-main-color questopt r2' href='#'></a></div>");
        	 }
        	 else if (eval("data.quest.preguntas.initial.respID").length == 3){
        		 jQuery(".botones_respuestas").append("<div class='col-md-4 col-xs-12'><a class='btn btn-block btn-main-color questopt r1' href='#' ></a></div>");
        		 jQuery(".botones_respuestas").append("<div class='col-md-4 col-xs-12'><a class='btn btn-block btn-main-color questopt r2' href='#' ></a></div>");
        		 jQuery(".botones_respuestas").append("<div class='col-md-4 col-xs-12'><a class='btn btn-block btn-main-color questopt r3' href='#' ></a></div>");
        	 }
	         jQuery.each(data.quest.preguntas.initial.respID,function(i,obj, json = data){
	        	 
	        	 var respContext = eval("json.quest.preguntas."+obj);
	        	 jQuery("a.r"+(i+1)).text(respContext.texto);
	        	 jQuery("a.r"+(i+1)).attr("respID",respContext.respID);
	         });
	         
	         jQuery(document).on("click", "a.questopt", function(datajson = data){
	     		console.log("hemos llegado");
	     		var pregunta = "data.quest.preguntas."+jQuery(this).attr("respID")+".texto";
	     		var respuestas = "data.quest.preguntas."+jQuery(this).attr("respID")+".respID";
	     		var tipo = "data.quest.preguntas."+jQuery(this).attr("respID")+".tipo";
	     		tipo=eval(tipo);
	     		if(tipo=="final"){
	     			jQuery("div.botones_respuestas").addClass("hidden");
	     			jQuery("div.final").removeClass("hidden");
	     			jQuery("div.final p").text(eval("data.quest.preguntas."+jQuery(this).attr("respID")+".texto"));
	     			jQuery("p.quest-snipped-text").addClass("hidden");

	     		}else{
	     			jQuery(".botones_respuestas").empty();
		        	 if (eval(respuestas).length == 2){
		        		 jQuery(".botones_respuestas").append("<div class='col-md-4 col-md-offset-2 col-xs-12'><a class='btn btn-block btn-main-color questopt r1' href='#'></a></div>");
		        		 jQuery(".botones_respuestas").append("<div class='col-md-4 col-xs-12'><a class='btn btn-block btn-main-color questopt r2' href='#'></a></div>");
		        	 }
		        	 else if (eval(respuestas).length == 3){
		        		 jQuery(".botones_respuestas").append("<div class='col-md-4 col-xs-12'><a class='btn btn-block btn-main-color questopt r1' href='#' ></a></div>");
		        		 jQuery(".botones_respuestas").append("<div class='col-md-4 col-xs-12'><a class='btn btn-block btn-main-color questopt r2' href='#' ></a></div>");
		        		 jQuery(".botones_respuestas").append("<div class='col-md-4 col-xs-12'><a class='btn btn-block btn-main-color questopt r3' href='#' ></a></div>");
		        	 }
	     			jQuery("p.quest-snipped-text").text(eval(pregunta));
		     		jQuery.each(eval(respuestas),function(i,obj, json = data){
			        	 var respContext = eval("json.quest.preguntas."+obj);
			        	 jQuery("a.r"+(i+1)).text(respContext.texto);
			        	 jQuery("a.r"+(i+1)).attr("respID",respContext.respID);
			         });
	     		}
	     	});
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



