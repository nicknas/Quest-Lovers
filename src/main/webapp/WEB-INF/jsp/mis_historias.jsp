<%@ page pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>

<%@ include file="../jspf/header.jspf"%>

<div class="starter-template">
	<c:if test="${not empty success_message}">
		<div class="row">
			<div class="alert alert-success alert-dismissible">
	  			<a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
	  			<strong>${success_message}</strong>
			</div>
		</div>
	</c:if>
	<c:forEach var="item" items="${questList}">
		<div class="quest-container tile col-md-3 col-xs-12">
	        <h6>${item.titulo}</h6>
	        <p>${item.descripcion}</p>
	      	<form class="col-md-6" action="/borrar_quest" method="POST">
	      		<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
	      		<input type="hidden" name="id_quest" value="${item.id}"/>
	      		<button class="btn btn-block btn-danger" type="submit"><span class="glyphicon glyphicon-trash"></span></button>       	
	    	</form>
	    	<form class="col-md-6" action="/editar_quest" method="POST">
	    		<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
	      		<input type="hidden" name="id_quest" value="${item.id}"/>
	      		<button class="btn btn-block btn-info" type="submit"><span class="glyphicon glyphicon-pencil"></span></button>
	    	</form>
	    </div>
	</c:forEach>
</div>
<%@ include file="../jspf/authinfo.jspf"%>	
<%@ include file="../jspf/footer.jspf"%>