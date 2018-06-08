<%@ page pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>

<%@ include file="../jspf/header.jspf"%>

<!--<script>
window.onload = function() {
	// code in here will only be executed when page fully loaded
	console.log("entered into chat");
	
	var socket = new WebSocket("${endpoint}");
	$("#escrito").submit(function (e) {
		var t = $("#texto").val();
		socket.send(t);
		$("#texto1").val("");
		e.preventDefault(); // avoid actual submit
	});
	socket.onmessage = function(e) {
		var ta = $("#recibido");
		ta.val(ta.val() + '\n' + e.data);
	}
}
</script>-->

<!-- <div class="starter-template">
	<h1>Chat</h1>
	<p class="lead">Ejemplo de uso de websockets</p>

	<textarea id="recibido" cols="80" rows="10">
	
	</textarea>
	<form id="escrito">
	<input id="texto" size="80" placeholder="escribe algo y pulsa enter para enviarlo"/>
	</form>
</div>
-->


<div class="starter-template">
	<h1>Chat</h1>
<!-- 
	<c:forEach items="${conversacion.texto}" var="mensaje">
		<p> ${mensaje}</p>
	
	</c:forEach>
	

	<c:set var="conversacion" value="${conversacion}"></c:set>
	-->
	<div class="row">
		<div class="col-xs-12 cuadro_chat" id="recibido" cols="80" rows="10">
			<c:forEach items="${lista_mensajes}" var="mensaje">
				<c:if test="${mensaje.sender.getId() == user_actual.id}">
					<p class="sender col-xs-12">${mensaje.sender.getLogin()}: ${mensaje.getTexto()}</p>
				</c:if>	
				<c:if test="${mensaje.sender.getId() != user_actual.id}">
					<p class="receiver col-xs-12">${mensaje.sender.getLogin()}: ${mensaje.getTexto()}</p>
				</c:if>	
			</c:forEach>
		</div>
	</div>
	<form id="escrito" action="/enviar_mensaje" method="POST">
		<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
		<input name="user" type="hidden" value="${user_actual.id}"/>
		<input name="id" type="hidden" value="${conversacion.id}"/>
		<input id="texto1" name="texto1" size="80" placeholder="escribe algo y pulsa enter para enviarlo"/>
		<button type="submit">Enviar</button>
	</form>
	



</div>

<%@ include file="../jspf/footer.jspf"%>
