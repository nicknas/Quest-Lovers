<%@ page pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>

<%@ include file="../jspf/header.jspf"%>

<div class="starter-template">
	<h1>Bienvenido</h1>
	<p class="lead">Bienvenido <sec:authentication property="principal.username"/></p> 
	<sec:authorize access="hasRole('USER')">
	<div class="row">
		<div class="col-sm-6 col-xs-12">
			<p>Tienes Quests que hacer</p>
		</div>
	</div>
	<div class="row">
		<div class="col-sm-6 col-xs-12">
			<p>Quieres hablar con alguien?</p>
		</div>
	</div>
	
	</sec:authorize>
	
	<%@ include file="../jspf/authinfo.jspf"%>		
</div>

<%@ include file="../jspf/footer.jspf"%>
