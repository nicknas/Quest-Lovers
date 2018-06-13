var data = new Object();
var nombre_preguntas = new Map();
var nombre_finales = new Map();
var index_pregunta = 0;
var index_final = 0;
var links_finales = 0;
var num_respuestas = 0;
	jQuery(document).on("click", "#buttonStart", function(){
		if (!jQuery("#nombre_historia").val() || !jQuery("#descripcion").val()){
			if (!jQuery("#nombre_historia").val()){
				jQuery("#nombre_historia").css("border-color", "red");
			}
			if (!jQuery("#descripcion").val()){
				jQuery("#descripcion").css("border-color", "red");
			}
		}
		else{
			data.quest = new Object();
			data.quest.titulo = jQuery("#nombre_historia").val();
			data.quest.descripcion = jQuery("#descripcion").val();
			jQuery(".form-group").remove();
			jQuery("fieldset").append('<div class="form-group"><label class="col-md-6 control-label" for="numQuestions">Número de preguntas</label><div class="col-md-2"><select class="form-control" id="numQuestions"><option>1</option><option>2</option><option>3</option><option>4</option><option>5</option><option>6</option><option>7</option><option>8</option><option>9</option><option>10</option></select></div></div>');
			jQuery("fieldset").append('<div class="form-group"><label class="col-md-6 control-label" for="initial">Nombre de la pregunta inicial</label><div class="col-md-6"><input id="initial" name="initial" class="form-control input-md pregunta" type="text"/></div></div>');
			jQuery("fieldset").append('<div class="form-group" id="finBlock"><label class="col-md-6 control-label" for="numFinales">Número de finales</label><div class="col-md-2"><select class="form-control" id="numFinales"><option>1</option><option>2</option><option>3</option></select></div></div>');
			jQuery("fieldset").append('<div class="form-group"><label class="col-md-6 control-label" for="f1">Nombre del final 1</label><div class="col-md-6"><input id="f1" name="f1" class="form-control input-md final" type="text"/></div></div>');
			jQuery("fieldset").append('<div class="form-group" id="finButton"><label class="col-md-4 control-label" for="buttonQuestions"></label><div class="col-md-4 col-md-offset-4"><input name="${_csrf.parameterName}" type="hidden" value="${_csrf.token}" /><button id="buttonQuestions" type="button" name="buttonQuestions" class="btn btn-info ">Ir a preguntas</button></div></div>');
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
		num_respuestas = 1;
		nombre_preguntas.forEach(function(value, key, map){
			jQuery("fieldset").append('<div class="form-group"><label class="col-md-2 control-label" for="'+ key +'">'+ value +' (texto de la pregunta)</label><div class="col-md-6"><textarea id="'+ key +'" name="'+ key +'" class="form-control input-md pregunta"></textarea></div></div>');
			jQuery("fieldset").append('<div class="form-group" id="blockRespuesta'+ num_respuestas +'"><div class="form-group"><label class="col-md-6 control-label" for="numResponse'+ num_respuestas +'">Número de respuestas</label><div class="col-md-2"><select class="form-control numResponses" id="numResponse'+ num_respuestas +'"><option>1</option><option>2</option><option>3</option></select></div></div></div>');
			jQuery("#blockRespuesta" + num_respuestas).append('<div class="form-group respuestaBlock'+ num_respuestas +'"><label class="col-md-6 control-label" for="r'+ num_respuestas +'">Nombre de la respuesta '+ num_respuestas +'</label><div class="col-md-6"><input id="r'+ num_respuestas +'" name="r'+ num_respuestas +'" class="form-control input-md respuesta" type="text"/></div></div>');
			jQuery("#blockRespuesta" + num_respuestas).append('<div class="form-group linkResponseBlock'+ num_respuestas +'"><label class="col-md-6 control-label" for="linkResponse'+ num_respuestas +'">Enlace a: </label><div class="col-md-2"><select class="form-control linkResponses" id="linkResponse'+ num_respuestas +'"></select></div></div>');
			num_respuestas++;
		});
		jQuery(".linkResponses").each(function(i){
			var id = i + 1;
			jQuery("#linkResponse" + id).empty();
			nombre_preguntas.forEach(function(value, key, map){
				if (i == 0){
					if (key != "initial"){
						jQuery("#linkResponse" + id).append('<option>'+ value +'</option>');
					}
				}
				else {
					var keySelectorActual = "p" + i;
					if (key != keySelectorActual){
						jQuery("#linkResponse" + id).append('<option>'+ value +'</option>');
					}
				}
			});
			nombre_finales.forEach(function(value, key, map){
				jQuery("#linkResponse" + id).append('<option>'+ value +'</option>');
			});
		});
		jQuery("fieldset").append('<div class="form-group" id="finButton"><label class="col-md-4 control-label" for="buttonFinales"></label><div class="col-md-4 col-md-offset-4"><input name="${_csrf.parameterName}" type="hidden" value="${_csrf.token}" /><button id="buttonFinales" type="button" name="buttonFinales" class="btn btn-info ">Ir a los finales</button></div></div>');
	});
	jQuery(document).on("change", ".numResponses", function(){
		var id_block = jQuery(this).attr("id");
		id_block = id_block[id_block.length - 1];
		jQuery(".respuestaBlock" + id_block).each(function(){
			jQuery(this).remove();
			num_respuestas--;
		});
		jQuery(".linkResponseBlock" + id_block).remove();
		var numRespTotal = num_respuestas + parseInt(jQuery(this).find("option:selected").text());
		while (num_respuestas < numRespTotal){
			jQuery("#blockRespuesta" + num_respuestas).append('<div class="form-group respuestaBlock'+ num_respuestas +'"><label class="col-md-6 control-label" for="r'+ num_respuestas +'">Nombre de la respuesta '+ num_respuestas +'</label><div class="col-md-6"><input id="r'+ num_respuestas +'" name="r'+ num_respuestas +'" class="form-control input-md respuesta" type="text"/></div></div>');
			jQuery("#blockRespuesta" + num_respuestas).append('<div class="form-group linkResponseBlock'+ num_respuestas +'"><label class="col-md-6 control-label" for="linkResponse'+ num_respuestas +'">Enlace a: </label><div class="col-md-2"><select class="form-control linkResponses" id="linkResponse'+ num_respuestas +'"></select></div></div>');
			num_respuestas++;
			jQuery(".linkResponses").each(function(i){
				var id = i + 1;
				jQuery("#linkResponse" + id).empty();
				nombre_preguntas.forEach(function(value, key, map){
					if (i == 0){
						if (key != "initial"){
							jQuery("#linkResponse" + id).append('<option>'+ value +'</option>');
						}
					}
					else {
						if (key != ("p" + i)){
							jQuery("#linkResponse" + id).append('<option>'+ value +'</option>');
						}
					}
				});
				nombre_finales.forEach(function(value, key, map){
					jQuery("#linkResponse" + id).append('<option>'+ value +'</option>');
				});
			});
		}
	});
	
	
	