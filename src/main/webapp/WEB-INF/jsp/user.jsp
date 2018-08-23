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
	
	<div class="row">
		<c:choose>
			<c:when test="${user.getListPhotos().size() > 0}">
					  <div id="userCarousel" class="carousel slide" data-ride="carousel">
					  <!-- Indicators -->
					  <ol class="carousel-indicators">
					  	<c:forEach var="i" begin="0" end="${user.getListPhotos().size() - 1}" step="1">
					  		<c:choose>
					  			<c:when test="${i == 0}">
					  				<li data-target="#userCarousel" data-slide-to="0" class="active"></li>
					  			</c:when>
					  			<c:otherwise>
					  				<li data-target="#userCarousel" data-slide-to="${i}"></li>
					  			</c:otherwise>
					  		</c:choose>
					  	
					  	</c:forEach>
					  </ol>
					
					  <!-- Wrapper for slides -->
					  <div class="carousel-inner">
					  	<c:forEach var="i" begin="1" end="${user.getListPhotos().size()}" step="1">
					  		<c:choose>
					  			<c:when test="${i == 1}">
					  				<div class="item active">
					  					<form action="/delete_photo" method="post">
					  						<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
					  						<input type="hidden" name="id_photo" value="${user.getListPhotos().get(i-1).getId()}"/>
					      					<img src="/photo/${usuario}/${i}" class="img-responsive img-center" alt="${i}">
					      					<button type="submit" class="btn btn-danger col-md-12"><span class="glyphicon glyphicon-trash"></span></button>
					      				</form>
					    			</div>
					  			</c:when>
					  			<c:otherwise>
					  				<div class="item">
					  					<form action="/delete_photo" method="post">
					  						<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>	
					  						<input type="hidden" name="id_photo" value="${user.getListPhotos().get(i-1).getId()}"/>			     			 
					     					<img src="/photo/${usuario}/${i}" class="img-responsive img-center" alt="${i}">
					     					<button type="submit" class="btn btn-danger col-md-12"><span class="glyphicon glyphicon-trash"></span></button>
					     				</form>
					 			    </div>
								</c:otherwise>
					  		</c:choose>
					  	</c:forEach>
					  </div>
					
					  <!-- Left and right controls -->
					  <a class="left carousel-control" href="#userCarousel" data-slide="prev">
					    <span class="glyphicon glyphicon-chevron-left"></span>
					    <span class="sr-only">Previous</span>
					  </a>
					  <a class="right carousel-control" href="#userCarousel" data-slide="next">
					    <span class="glyphicon glyphicon-chevron-right"></span>
					    <span class="sr-only">Next</span>
					  </a>
					</div> 
			</c:when>
			<c:otherwise>
				<div class="col-md-4"></div>
				<img class="col-md-4 img-responsive" src="/photo/${usuario}/0">
			</c:otherwise>
		</c:choose>
	</div>
	
	
	<%@ include file="../jspf/authinfo.jspf"%>		
</div>

<%@ include file="../jspf/footer.jspf"%>
