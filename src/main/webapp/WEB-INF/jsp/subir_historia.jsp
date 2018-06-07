<%@ page pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>

<%@ include file="../jspf/header.jspf"%>

<div class="starter-template">
	
	<form class="form-horizontal" action="/add_quest" enctype="multipart/form-data" method="post">
		<fieldset>
		
		<!-- Form Name -->
		<legend>Subir historia</legend>
		
		<!-- Text input-->
		<div class="form-group">
		  <label class="col-md-4 control-label" for="textinput">Nombre de la historia</label>  
		  <div class="col-md-4">
		  <input id="nombre_historia" name="nombre_historia" class="form-control input-md" type="text">
		  </div>
		</div>
		
		<!-- Textarea -->
		<div class="form-group">
		  <label class="col-md-4 control-label" for="descripcion">Descripción</label>
		  <div class="col-md-4">                     
		    <textarea class="form-control input-md" id="descripcion" name="descripcion"></textarea>
		  	<span class="help-block">Introduzca una descripción de la historia</span>
		  </div>
		</div>
		
		
		<div class="form-group">
		  <label class="col-md-4 control-label" for="historia">Subir historia</label>  
		  <div class="col-md-4">
		  <input class="input-file" type="file" accept=".json" name="historia" size="40">
		  </div>
		</div>	
		
		<!-- Button -->
		<div class="form-group">
		  <label class="col-md-4 control-label" for="buttonlid"></label>  
		  <div class="col-md-4">
		  	<input name="${_csrf.parameterName}" type="hidden" value="${_csrf.token}" />
		    <button id="button1id" name="button1id" class="btn btn-success ">Guardar</button>
		  </div>
		</div>
		
		</fieldset>
	</form>
	<sec:authentication property="principal.username" var="usuario" />
	
	<%@ include file="../jspf/authinfo.jspf"%>		
</div>

<%@ include file="../jspf/footer.jspf"%>