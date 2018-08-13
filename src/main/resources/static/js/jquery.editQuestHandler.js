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
			jQuery("legend").text("Preguntas");
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
						jQuery("#blockRespuesta" + num_preguntas).append('<div class="form-group respuestasBlock'+ num_preguntas +'"><label class="col-md-6 control-label">Texto de la respuesta '+ parseInt(index + 1) +'</label><div class="col-md-6"><input class="form-control input-md respuesta" value="'+ data.quest.preguntas[element].texto +'" type="text"/></div></div>');
					}
					else{
						jQuery("#blockRespuesta" + num_preguntas).append('<div class="form-group respuestasBlock'+ num_preguntas +'"><label class="col-md-6 control-label">Texto de la respuesta '+ parseInt(index + 1) +'</label><div class="col-md-6"><input class="form-control input-md respuesta" value="'+ data.quest.preguntas[element].texto +'" type="text"/><button type="button" class="btn btn-danger col-md-12"><span class="fui-cross"></span> Borrar Respuesta</button></div></div>');
					}
					jQuery("#blockRespuesta" + num_preguntas).append('<div class="form-group linkResponseBlock'+ num_preguntas +'"><label class="col-md-6 control-label">Enlace a: </label><div class="col-md-2"><select class="form-control linkResponses"></select></div></div>');
				});
				jQuery(".linkResponseBlock" + num_preguntas + " .linkResponses").each(function(i){
					var linkResponse = jQuery(this);				
					linkResponse.empty();
					nombre_preguntas.forEach(function(value, key, map){
						if (num_preguntas == 1){
							if (key != "initial"){
								linkResponse.append('<option value="'+ key +'">'+ value +'</option>');
							}
						}
						else {
							if (key != ("p" + parseInt(num_preguntas - 1))){
								linkResponse.append('<option value="'+ key +'">'+ value +'</option>');
							}
						}
					});
					nombre_finales.forEach(function(value, key, map){
						linkResponse.append('<option value="'+ key +'">'+ value +'</option>');
					});
					
					var respuesta = "";
					if (num_preguntas == 1){
						respuesta = data.quest.preguntas["initial"].respID[i];
					}
					else {
						respuesta = data.quest.preguntas["p" + parseInt(num_preguntas - 1)].respID[i];
					}
					linkResponse.find("option[value='"+ data.quest.preguntas[respuesta].respID +"']").prop("selected", true);
					
				});
				num_preguntas++;
			});
			jQuery("fieldset").append('<div class="form-group" id="finButton"><label class="col-md-4 control-label" for="buttonFinales"></label><div class="col-md-4 col-md-offset-4"><input name="${_csrf.parameterName}" type="hidden" value="${_csrf.token}" /><button id="buttonFinales" type="button" name="buttonFinales" class="btn btn-info ">Ir a los finales</button></div></div>');
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
			
		});
		
		jQuery(".linkResponses").each(function(i){
			if (jQuery(this).parent().parent().attr("class").includes("linkResponseBlock1")){
				jQuery(this).find("option").each(function(y){
					if (jQuery(this).val().includes("p")){
						jQuery(this).val("p" + (y+1));
						jQuery(this).text("Pregunta " + (y+1));
					}
				});
			}
			else {
				jQuery(this).find("option").each(function(y){
					if (jQuery(this).val().includes("p")){
						jQuery(this).val("p" + y);
						jQuery(this).text("Pregunta " + y);
					}
				});
			}
		});
						
		/*questionBlock.find("textarea").attr("id", "");
		questionBlock.find("textarea").attr("name", "");
		questionBlock.find("label").attr("for", "");
		jQuery(".blockPregunta").each(function(i){
			if (indexQuestionToRemove < i){
				jQuery(this).find("label").text("Pregunta " + (i-1).toString() + " (texto de la pregunta)");
			}
			
		});*/
	});
	
	jQuery(document).on("change", ".numResponses", function(){
		var numResponse = jQuery(this);
		var id_pregunta = numResponse.attr("id");
		id_pregunta = id_pregunta[id_pregunta.length - 1];
		jQuery(".respuestasBlock" + id_pregunta).remove();
		jQuery(".linkResponseBlock" + id_pregunta).remove();
		var numRespTotal = parseInt(numResponse.find("option:selected").text());
		for (var i = 1; i <= numRespTotal; i++){
			jQuery("#blockRespuesta" + id_pregunta).append('<div class="form-group respuestasBlock'+ id_pregunta +'"><label class="col-md-6 control-label">Nombre de la respuesta '+ i +'</label><div class="col-md-6"><input class="form-control input-md respuesta" type="text"/></div></div>');
			jQuery("#blockRespuesta" + id_pregunta).append('<div class="form-group linkResponseBlock'+ id_pregunta +'"><label class="col-md-6 control-label">Enlace a: </label><div class="col-md-2"><select class="form-control linkResponses"></select></div></div>');
			jQuery(".linkResponseBlock" + id_pregunta + " .linkResponses").each(function(){
				var linkResponse = jQuery(this);
				linkResponse.empty();
				nombre_preguntas.forEach(function(value, key, map){
					if (id_pregunta == 1){
						if (key != "initial"){
							linkResponse.append('<option value="'+ key +'">'+ value +'</option>');
						}
					}
					else {
						if (key != ("p" + parseInt(id_pregunta - 1))){
							linkResponse.append('<option value="'+ key +'">'+ value +'</option>');
						}
					}
				});
				nombre_finales.forEach(function(value, key, map){
					linkResponse.append('<option value="'+ key +'">'+ value +'</option>');
				});
			});
		}
	});
	jQuery(document).on("click", "#buttonFinales", function(){
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
					jQuery(".respuestasBlock1 .respuesta").each(function(k){
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
							jQuery(".linkResponseBlock1 .linkResponses").each(function(l){
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
					jQuery(".respuestasBlock" + id_pregunta + " .respuesta").each(function(k){
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
							jQuery(".linkResponseBlock"+ id_pregunta +" .linkResponses").each(function(l){
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
				jQuery("#buttonFinales").before('<div class="row"><div class="alert alert-danger alert-dismissible"><a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a><strong>La quest debe tener enlace a algún final</strong></div></div>');
			}
			else {
				jQuery(".form-group").remove();
				jQuery("legend").text("Finales");
				nombre_finales.forEach(function(value, key, map){
					jQuery("fieldset").append('<div class="form-group"><label class="col-md-2 control-label" for="'+ key +'">'+ value +' (texto del final)</label><div class="col-md-6"><textarea id="'+ key +'" name="'+ key +'" class="form-control input-md final"></textarea></div></div>');
				});
				jQuery("fieldset").append('<div class="form-group"><div class="col-md-4 col-md-offset-4"><button id="buttonSubmit" type="button" name="buttonSubmit" class="btn btn-success">Subir quest</button></div></div>');
			}
		}
	});
	
	jQuery(document).on("click", "#buttonSubmit", function(){
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
				data.quest.preguntas["f" + id_final].texto = jQuery(this).val();
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
	});
	
	
	