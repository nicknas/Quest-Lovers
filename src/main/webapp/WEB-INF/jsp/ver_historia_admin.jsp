<%@ page pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>

<%@ include file="../jspf/header.jspf"%>

<div class="starter-template">
	<form class="form-horizontal" action="/quest_admin">
		<fieldset>
		<!-- Form Name -->
		<h2>Preguntas</h2>
		<input type="hidden" value="${id}" name="id_quest" />
		</fieldset>
	</form>
</div>
<script src="${s}/js/jquery.viewQuestHandler.js"></script>
<%@ include file="../jspf/footer.jspf"%>


