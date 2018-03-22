<%@ page pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>

<%@ include file="../jspf/header.jspf"%>

<div class="starter-template">
	<h1>Reportes</h1>

	<hr />
	<div class="row">
		<div class="col-sm-6">
		<h4>Pendientes</h4>
			<div class="mycontent-left">
				<div class="panel-group" id="desplegable-izq">
					<c:forEach items="${reportes_nuevos}" var="nuevos"> 
						<div class="panel panel-default">
							<div class="panel-heading">
								<h4 class="panel-title">
									<a data-toggle="collapse" data-parent="#desplegable-izq"
										href="#${nuevos.id}">${nuevos.reportado}</a>
								</h4>
							</div>
							<div id="${nuevos.id}" class="panel-collapse collapse">
								<div class="panel-body">${nuevos.comentario}</div>
								<div class="panel-footer panel-footer-reporte">
									<div class="usuario-reporta">Usuario que reporta: ${nuevos.reportador}</div>
									<button type="button" class="btn btn-success"
										aria-label="Left Align">
										<span class="glyphicon glyphicon-ok" aria-hidden="true"></span>
									</button>
									<button type="button" class="btn btn-danger"
										aria-label="Left Align">
										<span class="glyphicon glyphicon-remove" aria-hidden="true"></span>
									</button>
								</div>
							</div>
						</div>
					</c:forEach>					
				</div>
			</div>
		</div>
		<div class="col-sm-6">
		<h4>Resueltos</h4>
			<div class="mycontent-right">
				<div class="panel-group" id="desplegable-der">
					<c:forEach items="${reportes_vistos}" var="vistos"> 
						<div class="panel panel-default">
							<div class="panel-heading">
								<h4 class="panel-title">
									<a data-toggle="collapse" data-parent="#desplegable-izq"
										href="#${vistos.id}">${vistos.reportado}</a>
								</h4>
							</div>
							<div id="${vistos.id}" class="panel-collapse collapse">
								<div class="panel-body">${vistos.comentario}</div>
								<div class="panel-footer panel-footer-reporte">
									<div class="usuario-reporta">Usuario que reporta: ${vistos.reportador}</div>
									<c:choose>
									    <c:when test="${vistos.baneado == 0}">
									        <span class="label label-danger">Rechazado</span>
									    </c:when>
									    <c:otherwise>
									        <span class="label label-success">Aceptado</span>
									    </c:otherwise>
									</c:choose>
								</div>
							</div>
						</div>
					</c:forEach>	
				</div>
			</div>
		</div>
	</div>


</div>

<%@ include file="../jspf/footer.jspf"%>
