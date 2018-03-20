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
					<div class="panel panel-default">
						<div class="panel-heading">
							<h4 class="panel-title">
								<a data-toggle="collapse" data-parent="#desplegable-izq"
									href="#collapse11">Pepito Fernanadez</a>
							</h4>
						</div>
						<div id="collapse11" class="panel-collapse collapse">
							<div class="panel-body">Lorem ipsum dolor sit amet,
								consectetur adipisicing elit, sed do eiusmod tempor incididunt
								ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis
								nostrud exercitation ullamco laboris nisi ut aliquip ex ea
								commodo consequat.</div>
							<div class="panel-footer panel-footer-reporte">
								<div class="usuario-reporta">Usuario que reporta: Nick</div>
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
					<div class="panel panel-default">
						<div class="panel-heading">
							<h4 class="panel-title">
								<a data-toggle="collapse" data-parent="#desplegable-izq"
									href="#collapse12">Usuario123</a>
							</h4>
						</div>
						<div id="collapse12" class="panel-collapse collapse">
							<div class="panel-body">Lorem ipsum dolor sit amet,
								consectetur adipisicing elit, sed do eiusmod tempor incididunt
								ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis
								nostrud exercitation ullamco laboris nisi ut aliquip ex ea
								commodo consequat.</div>
							<div class="panel-footer panel-footer-reporte">
								<div class="usuario-reporta">Usuario que reporta: Nick</div>
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
				</div>
			</div>
		</div>
		<div class="col-sm-6">
		<h4>Resueltos</h4>
			<div class="mycontent-right">
				<div class="panel-group" id="desplegable-der">
					<div class="panel panel-default">
						<div class="panel-heading">
							<h4 class="panel-title">
								<a data-toggle="collapse" data-parent="#desplegable-der"
									href="#collapse21">Pepito Fernanadez</a>
							</h4>
						</div>
						<div id="collapse21" class="panel-collapse collapse">
							<div class="panel-body">Lorem ipsum dolor sit amet,
								consectetur adipisicing elit, sed do eiusmod tempor incididunt
								ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis
								nostrud exercitation ullamco laboris nisi ut aliquip ex ea
								commodo consequat.</div>
							<div class="panel-footer panel-footer-reporte">
								<div class="usuario-reporta">Usuario que reporta: Nick</div>
								<span class="label label-success">Aceptado</span>
							</div>
						</div>
					</div>
					<div class="panel panel-default">
						<div class="panel-heading">
							<h4 class="panel-title">
								<a data-toggle="collapse" data-parent="#desplegable-der"
									href="#collapse22">Usuario123</a>
							</h4>
						</div>
						<div id="collapse22" class="panel-collapse collapse">
							<div class="panel-body">Lorem ipsum dolor sit amet,
								consectetur adipisicing elit, sed do eiusmod tempor incididunt
								ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis
								nostrud exercitation ullamco laboris nisi ut aliquip ex ea
								commodo consequat.</div>
							<div class="panel-footer panel-footer-reporte">
								<div class="usuario-reporta">Usuario que reporta: Nick</div>
								<span class="label label-danger">Rechazado</span>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>


</div>

<%@ include file="../jspf/footer.jspf"%>
