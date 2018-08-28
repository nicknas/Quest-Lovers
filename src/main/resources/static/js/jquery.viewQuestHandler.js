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

				jQuery("fieldset").append('<div class="form-group blockPregunta"><label class="col-md-2 control-label" for="'+ key +'">'+ value +' (texto de la pregunta)</label><div class="col-md-6"><textarea id="'+ key +'" name="'+ key +'" class="form-control input-md pregunta">'+ data.quest.preguntas[key].texto +'</textarea></div></div>');


				jQuery("fieldset").append('<div class="form-group blockRespuesta" id="blockRespuesta'+ num_preguntas +'"></div>');
				
				data.quest.preguntas[key].respID.forEach(function(element, index){
					jQuery("#blockRespuesta" + num_preguntas).append('<div class="form-group"><label class="col-md-6 control-label">Texto de la respuesta '+ (index + 1) +'</label><div class="col-md-6"><input class="form-control input-md respuesta" value="'+ data.quest.preguntas[element].texto +'" type="text"/></div></div>');
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
				num_preguntas++;
			});
			jQuery("fieldset").append('<hr>');
			jQuery("fieldset").append('<h2>Finales</h2>');
			var index = 1;
			nombre_finales.forEach(function(value, key, map){
				jQuery("fieldset").append('<div class="form-group"><label class="col-md-2 control-label" for="'+ key +'">Final '+ index +' (texto del final)</label><div class="col-md-6"><textarea id="'+ key +'" name="'+ key +'" class="form-control input-md final">'+ data.quest.preguntas[key].texto +'</textarea></div></div>');
				jQuery("fieldset").append('<div class="form-group"><label class="col-md-offset-2 col-md-2 control-label">Tipo de final</label><div class="col-md-6"><input type="text" class="form-control input-md tipoFinal" value="'+ data.quest.preguntas[key].solution +'"/></div></div>');
				index++;
			});
			jQuery("fieldset").append('<div class="form-group"><div><input name="${_csrf.parameterName}" type="hidden" value="${_csrf.token}" /><button id="buttonSubmit" type="submit" name="buttonSubmit" class="btn btn-info col-md-offset-3 col-md-6"><span class="fui-exit"></span> Volver a Quests</button></div></div>');
		});
	});

	