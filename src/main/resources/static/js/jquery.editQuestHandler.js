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

	jQuery(document).on("click", "#buttonQuestionsEndings", function(){
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
			jQuery("fieldset").append('<div class="form-group"><label class="col-md-6 control-label" for="numQuestions">Número de preguntas</label><div class="col-md-2"><select class="form-control" id="numQuestions"><option>1</option><option>2</option><option>3</option><option>4</option><option>5</option><option>6</option><option>7</option><option>8</option><option>9</option><option>10</option><option>11</option><option>12</option><option>13</option><option>14</option><option>15</option><option>16</option><option>17</option><option>18</option><option>19</option><option>20</option></select></div></div>');
			jQuery("fieldset").append('<div class="form-group"><label class="col-md-6 control-label" for="initial">Nombre de la pregunta inicial</label><div class="col-md-6"><input id="initial" name="initial" class="form-control input-md pregunta" type="text" value="'+ data.quest.preguntas.initial +'"/></div></div>');
			jQuery("fieldset").append('<div class="form-group" id="finBlock"><label class="col-md-6 control-label" for="numFinales">Número de finales</label><div class="col-md-2"><select class="form-control" id="numFinales"><option>1</option><option>2</option><option>3</option><option>4</option><option>5</option></select></div></div>');
			jQuery("fieldset").append('<div class="form-group"><label class="col-md-6 control-label" for="f1">Nombre del final 1</label><div class="col-md-6"><input id="f1" name="f1" class="form-control input-md final" type="text"/></div></div>');
			jQuery("fieldset").append('<div class="form-group" id="finButton"><label class="col-md-4 control-label" for="buttonQuestions"></label><div class="col-md-4 col-md-offset-4"><input name="${_csrf.parameterName}" type="hidden" value="${_csrf.token}" /><button id="buttonQuestions" type="button" name="buttonQuestions" class="btn btn-info ">Ir a preguntas</button></div></div>');
			var i = 1;
			var j = 1;
			for (var key in data.quest.preguntas){
				if (key.includes('p')){
					jQuery("#finBlock").before('<div class="form-group pblock"><label class="col-md-6 control-label" for="'+ key +'">Nombre de la pregunta '+ i +'</label><div class="col-md-6"><input id="'+ key +'" name="'+ key +'"class="form-control input-md pregunta" type="text"/></div></div>');
					i++;
				}
			}
		}
		
	});
	jQuery(document).on("change", "#numQuestions", function(){
		var numPregSel = parseInt(jQuery(this).find("option:selected").text());
		jQuery(".pblock").remove();
		for (var i = 1; i < numPregSel; i++){	
			var id = "p" + i;
			jQuery("#finBlock").before('<div class="form-group pblock"><label class="col-md-6 control-label" for="'+ id +'">Nombre de la pregunta '+ i +'</label><div class="col-md-6"><input id="'+ id +'" name="'+ id +'"class="form-control input-md pregunta" type="text"/></div></div>');
		}
	});
	jQuery(document).on("change", "#numFinales", function(){
		var numFinalSel = parseInt(jQuery(this).find("option:selected").text());
		jQuery(".fblock").remove();
		for (var i = 2; i <= numFinalSel; i++){	

			var id = "f" + i;
			jQuery("#finButton").before('<div class="form-group fblock"><label class="col-md-6 control-label" for="'+ id +'">Nombre del final '+ i +'</label><div class="col-md-6"><input id="'+ id +'" name="'+ id +'"class="form-control input-md final" type="text"/></div></div>');
		}
	});
	jQuery(document).on("click", "#buttonQuestions", function(){
		var num_preguntas = 0;
		var preguntasVacias = false;
		var finalesVacios = false;
		jQuery(".pregunta").each(function(){
			if (!jQuery(this).val()){
				preguntasVacias = true;
				jQuery(this).css("border-color", "red");
			}
		});
		jQuery(".final").each(function(){
			if (!jQuery(this).val()){
				finalesVacios = true;
				jQuery(this).css("border-color", "red");
			}
		});
		if (!preguntasVacias && !finalesVacios){
			data.quest.preguntas = new Object();
			jQuery(".pregunta").each(function(i){
				var key = jQuery(this).attr("id");
				var value = jQuery(this).val();
				nombre_preguntas.set(key, value);
				data.quest.preguntas[key] = new Object();
				if(i == 0){
					data.quest.preguntas[key].tipo = "preguntaInitial"
				}
				else{
					data.quest.preguntas[key].tipo = "pregunta";
				}
				
			});
			jQuery(".final").each(function(){
				var key = jQuery(this).attr("id");
				var value = jQuery(this).val();
				nombre_finales.set(key, value);
				data.quest.preguntas[key] = new Object();
				data.quest.preguntas[key].tipo = "final";
				data.quest.preguntas[key].solution = value;
			});
			jQuery(".form-group").remove();
			jQuery("legend").text("Preguntas");
			num_preguntas = 1;
			nombre_preguntas.forEach(function(value, key, map){
				jQuery("fieldset").append('<div class="form-group"><label class="col-md-2 control-label" for="'+ key +'">'+ value +' (texto de la pregunta)</label><div class="col-md-6"><textarea id="'+ key +'" name="'+ key +'" class="form-control input-md pregunta"></textarea></div></div>');
				jQuery("fieldset").append('<div class="form-group" id="blockRespuesta'+ num_preguntas +'"><div class="form-group"><label class="col-md-6 control-label" for="numResponse'+ num_preguntas +'">Número de respuestas</label><div class="col-md-2"><select class="form-control numResponses" id="numResponse'+ num_preguntas +'"><option>1</option><option>2</option><option>3</option><option>4</option><option>5</option><option>6</option><option>7</option><option>8</option><option>9</option><option>10</option></select></div></div></div>');
				jQuery("#blockRespuesta" + num_preguntas).append('<div class="form-group respuestasBlock'+ num_preguntas +'"><label class="col-md-6 control-label">Nombre de la respuesta 1</label><div class="col-md-6"><input class="form-control input-md respuesta" type="text"/></div></div>');
				jQuery("#blockRespuesta" + num_preguntas).append('<div class="form-group linkResponseBlock'+ num_preguntas +'"><label class="col-md-6 control-label">Enlace a: </label><div class="col-md-2"><select class="form-control linkResponses"></select></div></div>');
				num_preguntas++;
			});
			jQuery(".linkResponses").each(function(i){
				var linkResponse = jQuery(this);
				linkResponse.empty();
				nombre_preguntas.forEach(function(value, key, map){
					if (i == 0){
						if (key != "initial"){
							linkResponse.append('<option value="'+ key +'">'+ value +'</option>');
						}
					}
					else {
						var keySelectorActual = "p" + i;
						if (key != keySelectorActual){
							linkResponse.append('<option value="'+ key +'">'+ value +'</option>');
						}
					}
				});
				nombre_finales.forEach(function(value, key, map){
					linkResponse.append('<option value="'+ key +'">'+ value +'</option>');
				});
			});
			jQuery("fieldset").append('<div class="form-group" id="finButton"><label class="col-md-4 control-label" for="buttonFinales"></label><div class="col-md-4 col-md-offset-4"><input name="${_csrf.parameterName}" type="hidden" value="${_csrf.token}" /><button id="buttonFinales" type="button" name="buttonFinales" class="btn btn-info ">Ir a los finales</button></div></div>');
		}
		
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
		jQuery(".pregunta").each(function(i){
			var pregunta = jQuery(this);
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
	
	
	