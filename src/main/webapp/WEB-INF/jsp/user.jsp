<%@ page pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>

<%@ include file="../jspf/header.jspf"%>

<div class="starter-template">
	
	<form class="form-horizontal" action="/editUser" method="post">
		<fieldset>
		
		<!-- Form Name -->
		<legend>Mi cuenta</legend>
		
		<!-- Text input-->
		<div class="form-group">
		  <label class="col-md-4 control-label" for="textinput">Nombre</label>  
		  <div class="col-md-4">
		  <input id="user" name="user" value="${user.login}" class="form-control input-md" type="text">
		  <span class="help-block">Introduzca su nombre</span>  
		  </div>
		</div>
		
		<!-- Text input-->
		<div class="form-group">
		  <label class="col-md-4 control-label" for="Edad">Edad</label>  
		  <div class="col-md-4">
		  <input id="edad" name="edad" value="${user.edad}" class="form-control input-md" type="text">
		  <span class="help-block">Introduzca su edad</span>  
		  </div>
		</div>
		
		<!-- Textarea -->
		<div class="form-group">
		  <label class="col-md-4 control-label" for="resumen">Resumen</label>
		  <div class="col-md-4">                     
		    <textarea class="form-control input-md" id="resumen" name="resumen">${user.resumen}</textarea>
		  	<span class="help-block">Introduzca un resumen para presentarse</span>
		  </div>
		</div>
		
		<!-- Text input-->
		<div class="form-group">
		  <label class="col-md-4 control-label" for="ciudad">Ciudad</label>  
		  <div class="col-md-4">
		  <input id="ciudad" name="ciudad" value="${user.ciudad}" class="form-control input-md" type="text">
		  <span class="help-block">Introduzca la ciudad donde reside</span>  
		  </div>
		</div>
		
		<div class="form-group">
		  <label class="col-md-4 control-label" for="email">E-mail</label>  
		  <div class="col-md-4">
		  <input id="email" name="email" value="${user.email}" class="form-control input-md" type="text">
		  <span class="help-block">Introduzca su e-mail</span>  
		  </div>
		</div>
		
		
		
		<!-- Button (Double) -->
		<div class="form-group">
		  <label class="col-md-4 control-label" for="button1id"></label>
		  <div class="col-md-8">
		  	<input name="${_csrf.parameterName}" type="hidden" value="${_csrf.token}" />
		    <button id="button1id" name="button1id" class="btn btn-success">Guardar</button>
		    <button id="button2id" name="button2id" class="btn btn-danger">Cancelar</button>
		  </div>
		</div>
		
		</fieldset>
	</form>
	<sec:authentication property="principal.username" var="usuario" />
	
	<form class="form-group" action="/photo/${usuario}" enctype="multipart/form-data" method="post">
		<p> Añade imágenes a tu perfil 
			<input class="input-file" type="file" name="photo" size="40">
		</p>
		 <input name="${_csrf.parameterName}" type="hidden" value="${_csrf.token}" />
		<div>
			<input type="submit" value="Send">
		</div>
	</form>
	
	<img class="photo" src="/photo/${usuario}">
	
	<%@ include file="../jspf/authinfo.jspf"%>		
</div>

<%@ include file="../jspf/footer.jspf"%>
