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
			<div class="userpan">
				<p>Tienes nuevas Quests?</p>
				<a href="/quests">Mis Quests</a>
			</div>	
		</div>
		<div class="col-sm-6 col-xs-12 ">
			<div class="userpan">
				<p>¡Mira tus Mensajes</p>
				<a href="/messages">Mis Mensajes</a>
			</div>
		</div>
	</div>
	<div class="row last">
		<div class="col-sm-6 col-xs-12">
			<div class="userpan">
				<p>O mira si tienes alguien nuevo con quien hablar</p>
				<a href="/matches">Mis Matches</a>
			</div>
		</div>
		<div class="col-sm-6 col-xs-12 ">
			<div class="userpan">
				<p>Actualiza tu perfil</p>
				<a href="/user">Mi Cuenta</a>
			</div>
		</div>
	</div>
	
	</sec:authorize>
	
	<%@ include file="../jspf/authinfo.jspf"%>		
</div>

<%@ include file="../jspf/footer.jspf"%>
