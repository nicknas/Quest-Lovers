<%@ page pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>

<%@ include file="../jspf/header.jspf"%>

<div class="starter-template">
	<c:choose>
			<c:when test="${user.getNumPhotos() > 0}">
				<div id="userCarousel" class="carousel slide" data-ride="carousel">
					  <!-- Indicators -->
					  <ol class="carousel-indicators">
					  	<c:forEach var="i" begin="0" end="${user.getNumPhotos() - 1}" step="1">
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
					  	<c:forEach var="i" begin="1" end="${user.getNumPhotos()}" step="1">
					  		<c:choose>
					  			<c:when test="${i == 1}">
					  				<div class="item active">
					      				<img src="/photo/${user.getLogin()}/${i}" class="img-responsive img-center" alt="${i}">
					    			</div>
					  			</c:when>
					  			<c:otherwise>
					  				<div class="item">				     			 
					     				<img src="/photo/${user.getLogin()}/${i}" class="img-responsive img-center" alt="${i}">
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
				<img class="col-md-4 img-responsive" src="/photo/${user.getLogin()}/0">
			</c:otherwise>
		</c:choose>
	<hr/>
<%-- 	<div id="myCarousel" class="carousel slide" data-ride="carousel">
		  <!-- Indicators -->
		  <ol class="carousel-indicators">
		    <li data-target="#myCarousel" data-slide-to="0" class="active"></li>
		    <li data-target="#myCarousel" data-slide-to="1"></li>
		    <li data-target="#myCarousel" data-slide-to="2"></li>
		  </ol>
		
		  <!-- Wrapper for slides -->
		  <div class="carousel-inner">
		    <div class="item active">
		      <img src="${s}/img/perfil.jpg" class="img-center" alt="1">
		    </div>
		
		    <div class="item">
		      <img src="${s}/img/perfil.jpg" class="img-center" alt="2">
		    </div>
		
		    <div class="item">
		      <img src="${s}/img/perfil.jpg" class="img-center" alt="3">
		    </div>
		  </div>
		
		  <!-- Left and right controls -->
		  <a class="left carousel-control" href="#myCarousel" data-slide="prev">
		    <span class="glyphicon glyphicon-chevron-left"></span>
		    <span class="sr-only">Previous</span>
		  </a>
		  <a class="right carousel-control" href="#myCarousel" data-slide="next">
		    <span class="glyphicon glyphicon-chevron-right"></span>
		    <span class="sr-only">Next</span>
		  </a>
	</div> --%>
	
	<hr/>
	<div class="demo-type-example">
	     <span class="demo-text-note">Nombre</span>
	     <p>${user.login}</p>
    </div>
    <div class="demo-type-example">
	     <span class="demo-text-note">Edad</span>
	     <p>${user.edad}</p>
    </div>
    <div class="demo-type-example">
	     <span class="demo-text-note">Ciudad</span>
	     <p>${user.ciudad}</p>
    </div>
    <div class="demo-type-example">
	     <span class="demo-text-note">Información</span>
		<p>${user.resumen}</p>
	</div>
    
    <hr/>
	
	<a href="/chat?u1=${user_actual.id}&u2=${user.id}" class="btn btn-block btn-lg btn-info">Enviar mensaje</a>
	<c:set var="usuario" value="${user_actual.id}"/>
	<div class="panel-group">
		<div class="panel panel-default">
				<h3 class="panel-title btn btn-block btn-lg btn-danger">
					Reportar usuario&nbsp;&nbsp;
					<button class="btn-circle" data-toggle="collapse"
						data-target="#collapse1">
						<span class="glyphicon glyphicon-plus"></span>
					</button>
				</h3>
			<div id="collapse1" class="panel-collapse collapse">
				<div class="panel-body">
					<form action="/reportar" method="GET">
						<input type="hidden" name="id1" value=${usuario}>
						<input type="hidden" name="id2" value=${user.id}>
						<textarea class="form-control input-md" id="m" name="m">Introduzca la razón del reporte</textarea>
						<button type="submit" class="btn btn-succes">Enviar reporte</button>
					</form>				
				</div>
			</div>
	</div>		
	
	
	
	
	<%@ include file="../jspf/authinfo.jspf"%>		
</div>

<%@ include file="../jspf/footer.jspf"%>
