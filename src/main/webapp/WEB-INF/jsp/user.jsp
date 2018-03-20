<%@ page pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>

<%@ include file="../jspf/header.jspf"%>

<div class="starter-template">
	
	<hr/>
	<form class="form-horizontal">
		<fieldset>
		
		<!-- Form Name -->
		<legend>Mi cuenta</legend>
		
		<!-- Text input-->
		<div class="form-group">
		  <label class="col-md-4 control-label" for="textinput">Nombre</label>  
		  <div class="col-md-4">
		  <input id="textinput" name="textinput" placeholder="Nombre" class="form-control input-md" type="text">
		  <span class="help-block">Introduzca su nombre</span>  
		  </div>
		</div>
		
		<!-- Text input-->
		<div class="form-group">
		  <label class="col-md-4 control-label" for="Edad">Edad</label>  
		  <div class="col-md-4">
		  <input id="Edad" name="Edad" placeholder="Edad" class="form-control input-md" type="text">
		  <span class="help-block">Introduzca su edad</span>  
		  </div>
		</div>
		
		<!-- Textarea -->
		<div class="form-group">
		  <label class="col-md-4 control-label" for="resumen">Resumen</label>
		  <div class="col-md-4">                     
		    <textarea class="form-control" id="resumen" name="resumen">Introduzca un resumen sobre ti para presentarte</textarea>
		  </div>
		</div>
		
		<!-- Text input-->
		<div class="form-group">
		  <label class="col-md-4 control-label" for="ciudad">Ciudad</label>  
		  <div class="col-md-4">
		  <input id="ciudad" name="ciudad" placeholder="Ciudad" class="form-control input-md" type="text">
		  <span class="help-block">Introduzca la ciudad donde reside</span>  
		  </div>
		</div>
		
		<!-- File Button --> 
		<div class="form-group">
		  <label class="col-md-4 control-label" for="filebutton">Añade imágenes a tu perfil</label>
		  <div class="col-md-4">
		    <input id="filebutton" name="filebutton" class="input-file" type="file">
		  </div>
		</div>
		
		<!-- Button (Double) -->
		<div class="form-group">
		  <label class="col-md-4 control-label" for="button1id"></label>
		  <div class="col-md-8">
		    <button id="button1id" name="button1id" class="btn btn-success">Guardar</button>
		    <button id="button2id" name="button2id" class="btn btn-danger">Cancelar</button>
		  </div>
		</div>
		
		</fieldset>
	</form>
	
	
	
	<%@ include file="../jspf/authinfo.jspf"%>		
</div>

<%@ include file="../jspf/footer.jspf"%>
