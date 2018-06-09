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
		<c:forEach items="${lista_matches}" var="matches">
			<div class="col-md-4 col-xs-12">
				<div class="tile">
					<h2>${matches.login}</h2>
					<a href="/match?id=${matches.id}">
						<img src="/photo/${matches.login}/1" class="img-rounded img-responsive img-center">
					</a>
					<p>${matches.resumen}</p>
				</div>
				
			</div>
		</c:forEach>
	</div>
</div>
	
	<hr/>
	
	

	<%@ include file="../jspf/authinfo.jspf"%>		

<%@ include file="../jspf/footer.jspf"%>
