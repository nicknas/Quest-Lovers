<%@ page pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>

<%@ include file="../jspf/header.jspf"%>

<div class="starter-template">
	<h1>Mis mensajes</h1>
	<hr/>
	<div class="row ">
		<c:forEach items="${lista}" var="conversacion">
				<div class="tile">
					<a href="/chat?u1=${conversacion.user1.getId()}&u2=${conversacion.user2.getId()}">
						<h3 class="tile-title">${conversacion.user1.getLogin()}</h3>
						<h3 class="tile-title">${conversacion.user2.getLogin()}</h3>
					</a>			
				</div>
			
		</c:forEach>
	</div>

	<%@ include file="../jspf/authinfo.jspf"%>
</div>

			

<%@ include file="../jspf/footer.jspf"%>