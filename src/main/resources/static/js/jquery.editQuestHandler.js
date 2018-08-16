var data = new Object();
var nombre_preguntas = new Map();
var nombre_finales = new Map();
	jQuery(document).ready(function(){
		var token = $("meta[name='_csrf']").attr("content");
		var header = $("meta[name='_csrf_header']").attr("content");
		var url= "/get_quest";
		var id = jQuery("input[name='id_quest']").val();
		jQuery.ajax({
			beforeSend: function(headers){
				headers.setRequestHeader(header, token);
			},
			data: {"id": id},
			datatype: "json",
			type: "POST",
			url: url
		})
		.done(function (result, textStatus, jqXHR){
			data = result;
		});
	});

	jQuery(document).on("click", "#buttonPreguntas", function(){
		if (!jQuery("#nombre_historia").val() || !jQuery("#descripcion").val()){
			if (!jQuery("#nombre_historia").val()){
				jQuery("#nombre_historia").css("border-color", "red");
			}
			if (!jQuery("#descripcion").val()){
				jQuery("#descripcion").css("border-color", "red");
			}
		}
		else{
			data.quest.titulo = jQuery("#nombre_historia").val();
			data.quest.descripcion = jQuery("#descripcion").val();
			jQuery(".form-group").remove();
			jQuery("h2").text("Preguntas");
			for (var key in data.quest.preguntas){
				
				if (key.includes("initial")){
					nombre_preguntas.set(key, "Pregunta inicial");
				}
				else if (key.includes('p')){
					nombre_preguntas.set(key, "Pregunta " + key[key.length - 1]);
				}
				else if (key.includes('f')){
					nombre_finales.set(key, data.quest.preguntas[key].solution);
				}
			}
			num_preguntas = 1;
			nombre_preguntas.forEach(function(value, key, map){
				if (num_preguntas == 1){
					jQuery("fieldset").append('<div class="form-group blockPregunta"><label class="col-md-2 control-label" for="'+ key +'">'+ value +' (texto de la pregunta)</label><div class="col-md-6"><textarea id="'+ key +'" name="'+ key +'" class="form-control input-md pregunta">'+ data.quest.preguntas[key].texto +'</textarea></div></div>');
				}
				else{
					jQuery("fieldset").append('<div class="form-group blockPregunta"><label class="col-md-2 control-label" for="'+ key +'">'+ value +' (texto de la pregunta)</label><div class="col-md-6"><textarea id="'+ key +'" name="'+ key +'" class="form-control input-md pregunta">'+ data.quest.preguntas[key].texto +'</textarea><button type="button" class="btn btn-danger col-md-12"><span class="fui-cross"></span> Borrar Pregunta</button></div></div>');
				}
				jQuery("fieldset").append('<div class="form-group blockRespuesta" id="blockRespuesta'+ num_preguntas +'"></div>');
				
				data.quest.preguntas[key].respID.forEach(function(element, index){
					if (index == 0){
						jQuery("#blockRespuesta" + num_preguntas).append('<div class="form-group"><label class="col-md-6 control-label">Texto de la respuesta '+ (index + 1) +'</label><div class="col-md-6"><input class="form-control input-md respuesta" value="'+ data.quest.preguntas[element].texto +'" type="text"/></div></div>');
					}
					else{
						jQuery("#blockRespuesta" + num_preguntas).append('<div class="form-group"><label class="col-md-6 control-label">Texto de la respuesta '+ (index + 1) +'</label><div class="col-md-6"><input class="form-control input-md respuesta" value="'+ data.quest.preguntas[element].texto +'" type="text"/><button type="button" class="btn btn-danger col-md-12 btnDeleteResponse"><span class="fui-cross"></span> Borrar Respuesta</button></div></div>');
					}
					jQuery("#blockRespuesta" + num_preguntas).append('<div class="form-group"><label class="col-md-6 control-label">Enlace a: </label><div class="col-md-2"><select class="form-control linkResponses"></select></div></div>');
				});
				jQuery("#blockRespuesta" + num_preguntas + " .linkResponses").each(function(i){
					var linkResponse = jQuery(this);				
					linkResponse.empty();
					nombre_preguntas.forEach(function(value, key, map){
						if (num_preguntas == 1){
							if (key != "initial"){
								linkResponse.append('<option value="'+ key +'">'+ value +'</option>');
							}
						}
						else {
							if (key != ("p" + (num_preguntas - 1))){
								linkResponse.append('<option value="'+ key +'">'+ value +'</option>');
							}
						}
					});
					var indexFinal = 1;
					nombre_finales.forEach(function(value, key, map){
						linkResponse.append('<option value="'+ key +'">'+ "Final " + indexFinal +'</option>');
						indexFinal++;
					});
					
					var respuesta = "";
					if (num_preguntas == 1){
						respuesta = data.quest.preguntas["initial"].respID[i];
					}
					else {
						respuesta = data.quest.preguntas["p" + (num_preguntas - 1)].respID[i];
					}
					linkResponse.find("option[value='"+ data.quest.preguntas[respuesta].respID +"']").prop("selected", true);
					
				});
				jQuery("#blockRespuesta" + num_preguntas).append('<div class="form-group"><button type="button" class="btn btn-success col-md-offset-4 col-md-8 btnAddResponse"><span class="fui-plus-circle"></span> Agregar Respuesta</button></div>');
				num_preguntas++;
			});
			jQuery("fieldset").append('<div class="form-group"><button type="button" class="btn btn-success col-md-12" id="btnAddQuestion"><span class="fui-plus-circle"></span> Agregar Pregunta</button></div>');
			jQuery("fieldset").append('<hr>');
			jQuery("fieldset").append('<h2>Finales</h2>');
			var index = 1;
			nombre_finales.forEach(function(value, key, map){
				jQuery("fieldset").append('<div class="form-group"><label class="col-md-2 control-label" for="'+ key +'">Final '+ index +' (texto del final)</label><div class="col-md-6"><textarea id="'+ key +'" name="'+ key +'" class="form-control input-md final">'+ data.quest.preguntas[key].texto +'</textarea><button type="button" class="btn btn-danger col-md-12 btnDeleteFinal"><span class="fui-cross"></span> Borrar Final</button></div></div>');
				jQuery("fieldset").append('<div class="form-group"><label class="col-md-offset-2 col-md-2 control-label">Tipo de final</label><div class="col-md-6"><input type="text" class="form-control input-md tipoFinal" value="'+ data.quest.preguntas[key].solution +'"/></div></div>');
				index++;
			});
			jQuery("fieldset").append('<div class="form-group"><button type="button" id="btnAddFinal" class="btn btn-success col-md-12"><span class="fui-plus-circle"></span> Agregar Final</button></div>');
			jQuery("fieldset").append('<div class="form-group"><div><input name="${_csrf.parameterName}" type="hidden" value="${_csrf.token}" /><button id="buttonSubmit" type="button" name="buttonSubmit" class="btn btn-info col-md-offset-3 col-md-6"><span class="fui-exit"></span> Guardar Cambios</button></div></div>');
		}
		
	});
	
	jQuery(document).on("click", ".blockPregunta button", function(){
		var questionBlock = jQuery(this).parent().parent();
		var questionKey = questionBlock.find("textarea").attr("id");
		jQuery(".linkResponses").each(function(){
			jQuery(this).find("option[value='"+ questionKey +"']").remove();
		});
		questionBlock.next().fadeOut("fast", function(){
			questionBlock.fadeOut("slow", function(){
				questionBlock.next().remove();
				questionBlock.remove();
				jQuery(".blockPregunta").each(function(i){
					if (i != 0){
						jQuery(this).find("label").text("Pregunta " + i.toString() + " (texto de la pregunta)");
						jQuery(this).find("label").attr("for", "p" + i);
						jQuery(this).find("textarea").attr("id", "p" + i);
						jQuery(this).find("textarea").attr("name", "p" + i);
					}
				});
			});
		});
		
		jQuery(".blockRespuesta").each(function(i){
			jQuery(this).attr("id", "blockRespuesta" + (i+1));
			var incrementQuestions = false;
			if (jQuery(this).attr("id").includes("blockRespuesta1")){
				jQuery(this).find(".linkResponses").each(function(){			
					jQuery(this).find("option").each(function(y){
						if (jQuery(this).val().includes("p")){
							jQuery(this).val("p" + (y+1));
							jQuery(this).text("Pregunta " + (y+1));
							if (incrementQuestions){
								jQuery(this).val("p" + (y+2));
								jQuery(this).text("Pregunta " + (y+2));
							}
							else if ((y+1) == i){
								jQuery(this).val("p" + (y+2));
								jQuery(this).text("Pregunta " + (y+2));
								incrementQuestions = true;
							}
						}
					});
					incrementQuestions = false;
				});
				
			}
			else {
				jQuery(this).find(".linkResponses").each(function(){
					jQuery(this).find("option").each(function(y){
						if (jQuery(this).val().includes("p")){
							jQuery(this).val("p" + y);
							jQuery(this).text("Pregunta " + y);
							if (incrementQuestions){
								jQuery(this).val("p" + (y+1));
								jQuery(this).text("Pregunta " + (y+1));
							}
							else if (y == (i-1)){
								jQuery(this).val("p" + (y+1));
								jQuery(this).text("Pregunta " + (y+1));
								incrementQuestions = true;
							}
						}
					});
					incrementQuestions = false;
				});
				
			}
		});
				
	});
	
	jQuery(document).on("click", ".btnDeleteResponse", function(){
		var blockRespuesta = jQuery(this).parent().parent().parent();
		var divRespuesta = jQuery(this).parent().parent();
		divRespuesta.next().fadeOut("slow", function(){
			divRespuesta.fadeOut("slow", function(){
				divRespuesta.next().remove();
				divRespuesta.remove();
				blockRespuesta.find(".respuesta").each(function(i){
					var div = jQuery(this).parent().parent();
					div.find("label").text("Texto de la respuesta " + (i+1));
				});
			});
		});
		
	});
	
	jQuery(document).on("click", "#btnAddQuestion", function(){
		var id = jQuery(".blockPregunta").length;
		var key = "p" + id;
		var value = "Pregunta " + id;
		jQuery(this).parent().before('<div class="form-group blockPregunta" style="display: none;"><label class="col-md-2 control-label" for="'+ key +'">'+ value +' (texto de la pregunta)</label><div class="col-md-6"><textarea id="'+ key +'" name="'+ key +'" class="form-control input-md pregunta"></textarea><button type="button" class="btn btn-danger col-md-12"><span class="fui-cross"></span> Borrar Pregunta</button></div></div>');
		jQuery(this).parent().prev().fadeIn("slow", function(){
			jQuery(this).after('<div class="form-group blockRespuesta" style="display: none;" id="blockRespuesta'+ (id + 1) +'"></div>');
			jQuery("#blockRespuesta" + (id + 1)).append('<div class="form-group"><label class="col-md-6 control-label">Texto de la respuesta 1</label><div class="col-md-6"><input class="form-control input-md respuesta" type="text"/></div></div>');
			jQuery("#blockRespuesta" + (id + 1)).append('<div class="form-group"><label class="col-md-6 control-label">Enlace a: </label><div class="col-md-2"><select class="form-control linkResponses"></select></div></div>');
			jQuery("#blockRespuesta" + (id + 1) + " .linkResponses").each(function(i){
				var linkResponse = jQuery(this);				
				linkResponse.empty();
				jQuery(".pregunta").each(function(i){
					if (i == 0){
						linkResponse.append('<option value="initial">Pregunta inicial</option>');
					}
					else {
						if (jQuery(this).attr("id") != key){
							linkResponse.append('<option value="'+ jQuery(this).attr("id") +'">Pregunta '+ i +'</option>');
						}
					}
				});
				jQuery(".final").each(function(i){
					linkResponse.append('<option value="'+ jQuery(this).attr("id") +'">'+ "Final " + (i + 1) +'</option>');
				});
				
			});
			jQuery("#blockRespuesta" + (id + 1)).append('<div class="form-group"><button type="button" class="btn btn-success col-md-offset-4 col-md-8 btnAddResponse"><span class="fui-plus-circle"></span> Agregar Respuesta</button></div>');
			jQuery("#blockRespuesta" + (id + 1)).fadeIn("slow");
		});
	});
	
	jQuery(document).on("click", ".btnAddResponse", function(){
		var blockRespuesta = jQuery(this).parent().parent();
		var numRespuestas = blockRespuesta.find(".respuesta").length;
		jQuery(this).remove();
		blockRespuesta.append('<div class="form-group" style="display: none;"><label class="col-md-6 control-label">Texto de la respuesta '+ (numRespuestas + 1) +'</label><div class="col-md-6"><input class="form-control input-md respuesta" type="text"/><button type="button" class="btn btn-danger col-md-12 btnDeleteResponse"><span class="fui-cross"></span> Borrar Respuesta</button></div></div>');
		blockRespuesta.find("div.form-group").last().fadeIn("slow", function(){
			blockRespuesta.append(jQuery(".linkResponses").parent().parent().first().clone());
			blockRespuesta.append('<div class="form-group"><button type="button" class="btn btn-success col-md-offset-4 col-md-8 btnAddResponse"><span class="fui-plus-circle"></span> Agregar Respuesta</button></div>');
		});
		
	});
	
	jQuery(document).on("click", "#buttonSubmit", function(){
		var preguntasVacias = false;
		var respuestasVacias = false;
		var num_respuestas = 0;
		for (var key in data.quest.preguntas){
			if (key.includes('p')){
				delete data.quest.preguntas.key;
			}
			else if (key.includes('r')){
				delete data.quest.preguntas.key;
			}
			
			else if (key.includes('f')){
				delete data.quest.preguntas.key;
			}
		}
		jQuery(".pregunta").each(function(i){
			var pregunta = jQuery(this);
			data.quest.preguntas["p" + parseInt(i+1)] = new Object();
			data.quest.preguntas["p" + parseInt(i+1)].tipo = "pregunta";
			
			if (!pregunta.val()){
				preguntasVacias = true;
				pregunta.css("border-color", "red")
			}
			else {
				if (i == 0){
					data.quest.preguntas.initial.texto = pregunta.val();
					data.quest.preguntas.initial.respID = [];
					jQuery("#blockRespuesta1 .respuesta").each(function(k){
						var respuesta = jQuery(this);
						if (!respuesta.val()){
							respuestasVacias = true;
							respuesta.css("border-color", "red");
						}
						else{
							num_respuestas++;
							data.quest.preguntas.initial.respID.push("r" + num_respuestas);
							data.quest.preguntas["r" + num_respuestas] = new Object();
							data.quest.preguntas["r" + num_respuestas].tipo = "respuesta";
							data.quest.preguntas["r" + num_respuestas].texto = respuesta.val();
							jQuery("#blockRespuesta1 .linkResponses").each(function(l){
								var linkResponse = jQuery(this);
								if (k == l){
									data.quest.preguntas["r" + num_respuestas].respID = linkResponse.find("option:selected").val();
								}
							});
								
						
						}
					});
						
					
				}
				else {
					var id_pregunta = i + 1;
					data.quest.preguntas["p" + i].texto = pregunta.val();
					data.quest.preguntas["p" + i].respID = [];
					jQuery("#blockRespuesta" + id_pregunta + " .respuesta").each(function(k){
						var respuesta = jQuery(this);
						if (!respuesta.val()){
							respuestasVacias = true;
							respuesta.css("border-color", "red");
						}
						else{
							num_respuestas++;
							data.quest.preguntas["p" + i].respID.push("r" + num_respuestas);
							data.quest.preguntas["r" + num_respuestas] = new Object();
							data.quest.preguntas["r" + num_respuestas].tipo = "respuesta";
							data.quest.preguntas["r" + num_respuestas].texto = respuesta.val();
							jQuery("#blockRespuesta"+ id_pregunta +" .linkResponses").each(function(l){
								var linkResponse = jQuery(this);
								if (k == l){
									data.quest.preguntas["r" + num_respuestas].respID = linkResponse.find("option:selected").val();
								}
							});
						}
					});
				}
			}
		});
		if (!preguntasVacias && !respuestasVacias){
			console.log(JSON.stringify(data));
			var linkToFinal = false;
			jQuery(".linkResponses").each(function(i){
				if (jQuery(this).val().includes("f")){
					linkToFinal = true;
				}
			});
			if (!linkToFinal){
				jQuery("#buttonSubmit").before('<div class="row"><div class="alert alert-danger alert-dismissible"><a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a><strong>La quest debe tener enlace a algún final</strong></div></div>');
			}
			else {
				var finalesVacios = false;
				jQuery(".final").each(function(){
					if (!jQuery(this).val()){
						jQuery(this).css("border-color", "red");
						finalesVacios = true;
					}
				});
				if (!finalesVacios){
					jQuery(".final").each(function(i){
						var id_final = i + 1;
						data.quest.preguntas["f" + id_final] = new Object();
						data.quest.preguntas["f" + id_final].texto = jQuery(this).val();
					});
					jQuery(".tipoFinal").each(function(i){
						var id_final = i + 1;
						data.quest.preguntas["f" + id_final].solution = jQuery(this).val();
					});
					var jsonString = JSON.stringify(data);
					var token = $("meta[name='_csrf']").attr("content");
					var header = $("meta[name='_csrf_header']").attr("content");
					jQuery.ajax({
						beforeSend: function(headers){
							headers.setRequestHeader(header, token);
						},
						contentType: "application/json; charset=utf-8",
						data: jsonString,
						type: "POST",
						url: "/add_quest"		
					})
					.done(function (result, textStatus, jqXHR){
						if (result === "ok"){
							window.location.href = "/mis_historias";
						}
						else {
							alert("Error a la hora de subir la quest");
						}
					});
				}
			}
		}
	});
	
	
	