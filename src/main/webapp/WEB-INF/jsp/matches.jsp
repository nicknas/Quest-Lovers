<%@ page pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>




<%@ include file="../jspf/header.jspf"%>

<div class="starter-template">
	<h1>Matches</h1>

	<hr/>
	<div class="row">
		<div class="col-md-4 col-xs-12">
			<div class="tile">
				<h2>Usuario</h2>
				<a href="/match">
					<img src="${s}/img/perfil.jpg" class="img-rounded img-responsive img-center">
				</a>
				<p>Foto y algunos datos basicos del match</p>
			</div>
			
		</div>
		
		<div class="col-md-4 col-xs-12">
			<div class="tile">
				<h2>Usuario</h2>
				<img src="${s}/img/perfil.jpg" class="img-rounded img-responsive img-center">
				<p>Foto y algunos datos basicos del match</p>
			</div>	
		</div>
		<div class="col-md-4 col-xs-12">
			<div class="tile">
				<h2>Usuario</h2>
				<img src="${s}/img/perfil.jpg" class="img-rounded img-responsive img-center">		
				<p>Foto y algunos datos basicos del match</p>
			</div>	
		</div>
	</div>
	<div class="row">
		<div class="col-md-4 col-xs-12">
			<div class="tile">
				<h2>Usuario</h2>
				<img src="${s}/img/perfil.jpg" class="img-rounded img-responsive img-center">
				<p>Foto y algunos datos basicos del match</p>
			</div>	
		</div>
		<div class="col-md-4 col-xs-12">
			<div class="tile">
				<h2>Usuario</h2>
				<img src="${s}/img/perfil.jpg" class="img-rounded img-responsive img-center">
				<p>Foto y algunos datos basicos del match</p>
			</div>	
		</div>
		<div class="col-md-4 col-xs-12">
			<div class="tile">
				<h2>Usuario</h2>
				<img src="${s}/img/perfil.jpg" class="img-rounded img-responsive img-center">
				<p>Foto y algunos datos basicos del match</p>
			</div>	
		</div>
	</div>
</div>
	
	<hr/>
	
	

	<%@ include file="../jspf/authinfo.jspf"%>		

<%@ include file="../jspf/footer.jspf"%>
