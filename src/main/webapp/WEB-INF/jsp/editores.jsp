<%@ page pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>

<%@ include file="../jspf/header.jspf"%>

<div class="starter-template">

	<h1>Editores</h1>

	<hr />

	<div class="panel-group">
		<div class="panel panel-default">
			<div class="panel-heading">
				<h3 class="panel-title">
					Añadir editor&nbsp;&nbsp;
					<button class="btn-circle" data-toggle="collapse"
						data-target="#collapse1">
						<span class="glyphicon glyphicon-plus"></span>
					</button>
				</h3>
			</div>
			<div id="collapse1" class="panel-collapse collapse">
				<div class="panel-body">
					<form class="form-horizontal" id="formulario-editor" action="/admin/addEditor" method="post">
						<fieldset>

							<div class="col-md-6">

								<!-- Text input-->
								<div class="form-group">
									<label class="col-md-3 control-label" for="email">Email</label>
									<div class="col-md-7">
										<input id="email" name="email" placeholder="Email"
											class="form-control input-md" type="text">
									</div>
								</div>

								<!-- Text input-->
								<div class="form-group">
									<label class="col-md-3 control-label" for="ciudad">Ciudad</label>
									<div class="col-md-7">
										<input id="ciudad" name="ciudad" placeholder="ciudad"
											class="form-control input-md" type="text">
									</div>
								</div>
								
								<div class="form-group">
									<label class="col-md-3 control-label" for="resumen">Resumen</label>
									<div class="col-md-7">
										<input id="ciudad" name="resumen" placeholder="resumen"
											class="form-control input-md" type="text">
									</div>
								</div>
								
								<div class="form-group">
									<label class="col-md-3 control-label" for="edad">Edad</label>
									<div class="col-md-7">
										<input id="ciudad" name="edad" placeholder="edad"
											class="form-control input-md" type="text">
									</div>
								</div>

							</div>
							<div class="col-md-6">
								<!-- Text input-->
								<div class="form-group">
									<label class="col-md-4 control-label" for="user">Usuario</label>
									<div class="col-md-7">
										<input id="Usuario" name="user" placeholder="user"
											class="form-control input-md" type="text">
									</div>
								</div>

								<!-- Text input-->
								<div class="form-group">
									<label class="col-md-4 control-label" for="password">Contraseña</label>
									<div class="col-md-7">
										<input id="Contraseña" name="password"
											placeholder="Contraseña" class="form-control input-md"
											type="password">
									</div>
								</div>
								<!-- Text input-->
								<div class="form-group">
									<label class="col-md-4 control-label" for="password2">Repite contraseña</label>
									<div class="col-md-7">
										<input id="Contraseña" name="password2"
											placeholder="Repite Contraseña" class="form-control input-md"
											type="password">
									</div>
								</div>
							</div>

						</fieldset>
					
				</div>
				<div class="panel-footer">
					<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>	
					<button type="submit" id="btn-guardar-editor" class="btn btn-success">Guardar</button>
					<button type="cancel" id="btn-cancelar-editor" class="btn btn-danger">Cancelar</button>
					</form>
				</div>
			</div>
		</div>
	</div>
	<table class="table table-bordered" id="tabla-editores">
		<thead>
			<tr>
				<th>Select</th>
				<th>ID</th>
				<th>Nombre</th>
				<th>Email</th>
				<th>Ciudad</th>
				<th>Contraseña</th>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${editores}" var="u">
			<tr>
				<td><input type="checkbox" class="checkbox-editor"></td>
				<td>${u.id}</td>
				<td>${u.login}</td>
				<td>${u.email}</td>
				<td>${u.ciudad}</td>
				<td>${u.password}</td>
			</tr>	
		</c:forEach>	
		</tbody>
	</table>

	<button type="button" style="display: none" id="btn-eliminar-editor"
		class="btn btn-danger btn-block">Eliminar editor/es</button>

</div>

<%@ include file="../jspf/footer.jspf"%>
