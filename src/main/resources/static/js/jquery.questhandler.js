var QUEST_TITTLE_CSS ="h1.title-quest";

jQuery(document).ready(function(){
	var id = getParam("id");
	var data;
	var datajson;
	var token = $("meta[name='_csrf']").attr("content");
	var header = $("meta[name='_csrf_header']").attr("content");
	var url= "/get_quest";
	jQuery.ajax({
		beforeSend: function(headers){
			headers.setRequestHeader(header, token);
		},
		data: {"id": id},
		type: "POST",
		url: url		 
	})
	.done(function (data, textStatus, jqXHR){
		if (console && console.log){
			console.log( "La solicitud de quest se ha completado correctamente." );
			// data = JSON.parse(data);
			console.log(data);
			if(data.quest.titulo !== undefined && data.quest.titulo !== null) jQuery(QUEST_TITTLE_CSS).text(data.quest.titulo);
			jQuery("p.quest-snipped-text").text(data.quest.preguntas.initial.texto);
			jQuery(".botones_respuestas").empty();
			if ((eval("data.quest.preguntas.initial.respID").length % 3) == 1){
				var filas = parseInt(eval("data.quest.preguntas.initial.respID").length / 3);
				var indice = 1;
				for (var i = 0; i <= filas; i++){
					if (i == filas){
						jQuery(".botones_respuestas").append("<div><a class='btn btn-block btn-main-color questopt r"+ indice +"' href='#'></a></div>");
					}
					else {
						jQuery(".botones_respuestas").append("<div class='col-md-4 col-xs-12'><a class='btn btn-block btn-main-color questopt r"+ indice +"' href='#' ></a></div>");
						indice++;
						jQuery(".botones_respuestas").append("<div class='col-md-4 col-xs-12'><a class='btn btn-block btn-main-color questopt r"+ indice +"' href='#' ></a></div>");
						indice++;
						jQuery(".botones_respuestas").append("<div class='col-md-4 col-xs-12'><a class='btn btn-block btn-main-color questopt r"+ indice +"' href='#' ></a></div>");
						indice++;
					}
				}
			}
			else if ((eval("data.quest.preguntas.initial.respID").length % 3) == 2){
				var filas = parseInt(eval("data.quest.preguntas.initial.respID").length / 3);
				var indice = 1;
				for (var i = 0; i <= filas; i++){
					if (i == filas){
						jQuery(".botones_respuestas").append("<div class='col-md-4 col-md-offset-2 col-xs-12'><a class='btn btn-block btn-main-color questopt r"+ indice +"' href='#'></a></div>");
						indice++;
						jQuery(".botones_respuestas").append("<div class='col-md-4 col-xs-12'><a class='btn btn-block btn-main-color questopt r"+ indice +"' href='#'></a></div>");
					}
					else {
						jQuery(".botones_respuestas").append("<div class='col-md-4 col-xs-12'><a class='btn btn-block btn-main-color questopt r"+ indice +"' href='#' ></a></div>");
						indice++;
						jQuery(".botones_respuestas").append("<div class='col-md-4 col-xs-12'><a class='btn btn-block btn-main-color questopt r"+ indice +"' href='#' ></a></div>");
						indice++;
						jQuery(".botones_respuestas").append("<div class='col-md-4 col-xs-12'><a class='btn btn-block btn-main-color questopt r"+ indice +"' href='#' ></a></div>");
						indice++;
					}
				}
			}
			else if ((eval("data.quest.preguntas.initial.respID").length % 3) == 0){
				var filas = parseInt(eval("data.quest.preguntas.initial.respID").length / 3);
				var indice = 1;
				for (var i = 0; i < filas; i++){
					jQuery(".botones_respuestas").append("<div class='col-md-4 col-xs-12'><a class='btn btn-block btn-main-color questopt r"+ indice +"' href='#' ></a></div>");
					indice++;
					jQuery(".botones_respuestas").append("<div class='col-md-4 col-xs-12'><a class='btn btn-block btn-main-color questopt r"+ indice +"' href='#' ></a></div>");
					indice++;
					jQuery(".botones_respuestas").append("<div class='col-md-4 col-xs-12'><a class='btn btn-block btn-main-color questopt r"+ indice +"' href='#' ></a></div>");
					indice++;
				}
			}
			jQuery.each(data.quest.preguntas.initial.respID,function(i,obj){

				var respContext = eval("data.quest.preguntas."+obj);
				jQuery("a.r"+(i+1)).text(respContext.texto);
				jQuery("a.r"+(i+1)).attr("respID",respContext.respID);
			});

			jQuery(document).on("click", "a.questopt", function(i,obj){
				console.log("hemos llegado");
				var pregunta = "data.quest.preguntas."+jQuery(this).attr("respID")+".texto";
				var respuestas = "data.quest.preguntas."+jQuery(this).attr("respID")+".respID";
				var tipo = "data.quest.preguntas."+jQuery(this).attr("respID")+".tipo";
				tipo=eval(tipo);
				if(tipo=="final"){
					jQuery("div.botones_respuestas").addClass("hidden");
					jQuery("div.final").removeClass("hidden");
					jQuery("input.resultado").attr("value", eval("data.quest.preguntas."+jQuery(this).attr("respID")+".solution"));
					jQuery("div.final p").text(eval("data.quest.preguntas."+jQuery(this).attr("respID")+".texto"));
					jQuery("p.quest-snipped-text").addClass("hidden");

				}else{
					jQuery(".botones_respuestas").empty();
					if ((eval(respuestas).length % 3) == 1){
						var filas = parseInt(eval(respuestas).length / 3);
						var indice = 1;
						for (var i = 0; i <= filas; i++){
							if (i == filas){
								jQuery(".botones_respuestas").append("<div><a class='btn btn-block btn-main-color questopt r"+ indice +"' href='#'></a></div>");
							}
							else {
								jQuery(".botones_respuestas").append("<div class='col-md-4 col-xs-12'><a class='btn btn-block btn-main-color questopt r"+ indice +"' href='#' ></a></div>");
								indice++;
								jQuery(".botones_respuestas").append("<div class='col-md-4 col-xs-12'><a class='btn btn-block btn-main-color questopt r"+ indice +"' href='#' ></a></div>");
								indice++;
								jQuery(".botones_respuestas").append("<div class='col-md-4 col-xs-12'><a class='btn btn-block btn-main-color questopt r"+ indice +"' href='#' ></a></div>");
								indice++;
							}
						}
					}
					else if ((eval(respuestas).length % 3) == 2){
						var filas = parseInt(eval(respuestas).length / 3);
						var indice = 1;
						for (var i = 0; i <= filas; i++){
							if (i == filas){
								jQuery(".botones_respuestas").append("<div class='col-md-4 col-md-offset-2 col-xs-12'><a class='btn btn-block btn-main-color questopt r"+ indice +"' href='#'></a></div>");
								indice++;
								jQuery(".botones_respuestas").append("<div class='col-md-4 col-xs-12'><a class='btn btn-block btn-main-color questopt r"+ indice +"' href='#'></a></div>");
							}
							else {
								jQuery(".botones_respuestas").append("<div class='col-md-4 col-xs-12'><a class='btn btn-block btn-main-color questopt r"+ indice +"' href='#' ></a></div>");
								indice++;
								jQuery(".botones_respuestas").append("<div class='col-md-4 col-xs-12'><a class='btn btn-block btn-main-color questopt r"+ indice +"' href='#' ></a></div>");
								indice++;
								jQuery(".botones_respuestas").append("<div class='col-md-4 col-xs-12'><a class='btn btn-block btn-main-color questopt r"+ indice +"' href='#' ></a></div>");
								indice++;
							}
						}
					}
					else if ((eval(respuestas).length % 3) == 0){
						var filas = parseInt(eval(respuestas).length / 3);
						var indice = 1;
						for (var i = 0; i < filas; i++){
							jQuery(".botones_respuestas").append("<div class='col-md-4 col-xs-12'><a class='btn btn-block btn-main-color questopt r"+ indice +"' href='#' ></a></div>");
							indice++;
							jQuery(".botones_respuestas").append("<div class='col-md-4 col-xs-12'><a class='btn btn-block btn-main-color questopt r"+ indice +"' href='#' ></a></div>");
							indice++;
							jQuery(".botones_respuestas").append("<div class='col-md-4 col-xs-12'><a class='btn btn-block btn-main-color questopt r"+ indice +"' href='#' ></a></div>");
							indice++;
						}
					}
					jQuery("p.quest-snipped-text").text(eval(pregunta));
					jQuery.each(eval(respuestas),function(i,obj){
						var respContext = eval("data.quest.preguntas."+obj);
						jQuery("a.r"+(i+1)).text(respContext.texto);
						jQuery("a.r"+(i+1)).attr("respID",respContext.respID);
					});
				}
			});
		}    
	})
	.fail(function( jqXHR, textStatus, errorThrown ) {
		if ( console && console.log ) {
			console.log( "La solicitud de quest ha fallado: " +  textStatus);
		}
	});




});

function getParam(name){
	var results = new RegExp('[\?&]' + name + '=([^&#]*)').exec(window.location.href);
	return results[1] || 0;
}



