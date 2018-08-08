<%@ page pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>

<%@ include file="../jspf/header.jspf"%>

<div class="starter-template">
	<form class="form-horizontal" action="#">
		<fieldset>
		
		<!-- Form Name -->
		<legend>Editar historia</legend>
		
		<!-- Text input-->
		<div class="form-group">
		  <label class="col-md-4 control-label" for="nombre_historia">Nombre de la historia</label>  
		  <div class="col-md-4">
		  <input id="nombre_historia" name="nombre_historia" class="form-control input-md" type="text">
		  </div>
		</div>
		
		<!-- Textarea -->
		<div class="form-group">
		  <label class="col-md-4 control-label" for="descripcion">Descripción</label>
		  <div class="col-md-4">                     
		    <textarea class="form-control input-md" id="descripcion" name="descripcion"></textarea>
		  </div>
		</div>
		
		<!-- Button -->
		<div class="form-group">
		  <label class="col-md-4 control-label" for="buttonStart"></label>  
		  <div class="col-md-4">
		  	<input name="${_csrf.parameterName}" type="hidden" value="${_csrf.token}" />
		    <button id="buttonStart" type="button" name="buttonStart" class="btn btn-info ">Siguiente</button>
		  </div>
		</div>
		
		</fieldset>
	</form>
</div>
<script src="${s}/js/jquery.uploadQuestHandler.js"></script>
<%@ include file="../jspf/footer.jspf"%>


