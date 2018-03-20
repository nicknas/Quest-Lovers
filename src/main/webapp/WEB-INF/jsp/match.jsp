<%@ page pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>

<%@ include file="../jspf/header.jspf"%>

<div class="starter-template">
	
	<hr/>
	<div id="myCarousel" class="carousel slide" data-ride="carousel">
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
	</div>
	
	<hr/>
	<div class="demo-type-example">
	     <span class="demo-text-note">Nombre</span>
	     <p>Nombre de usuario</p>
    </div>
    <div class="demo-type-example">
	     <span class="demo-text-note">Edad</span>
	     <p>25</p>
    </div>
    <div class="demo-type-example">
	     <span class="demo-text-note">Ciudad</span>
	     <p>Madrid</p>
    </div>
    <div class="demo-type-example">
	     <span class="demo-text-note">Información</span>
		<p>Cum sociis natoque penatibus et magnis dis parturient montes,
			nascetur ridiculus mus.</p>
	</div>
    
    <hr/>
	<a href="#fakelink" class="btn btn-block btn-lg btn-info">Enviar mensaje</a>
	
	
	<%@ include file="../jspf/authinfo.jspf"%>		
</div>

<%@ include file="../jspf/footer.jspf"%>
