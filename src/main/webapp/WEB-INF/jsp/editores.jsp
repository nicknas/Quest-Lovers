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
					<form class="form-horizontal" id="formulario-editor">
						<fieldset>

							<div class="col-md-6">
								<!-- Text input-->
								<div class="form-group">
									<label class="col-md-3 control-label" for="Nombre">Nombre</label>
									<div class="col-md-7">
										<input id="Nombre" name="Nombre" placeholder="Nombre"
											class="form-control input-md" type="text">
									</div>
								</div>

								<!-- Text input-->
								<div class="form-group">
									<label class="col-md-3 control-label" for="Apellidos">Apellidos</label>
									<div class="col-md-7">
										<input id="Apellidos" name="Apellidos" placeholder="Apellidos"
											class="form-control input-md" type="text">
									</div>
								</div>

								<!-- Text input-->
								<div class="form-group">
									<label class="col-md-3 control-label" for="Email">Email</label>
									<div class="col-md-7">
										<input id="Email" name="Email" placeholder="Email"
											class="form-control input-md" type="text">
									</div>
								</div>

								<!-- Text input-->
								<div class="form-group">
									<label class="col-md-3 control-label" for="Teléfono">Teléfono</label>
									<div class="col-md-7">
										<input id="Teléfono" name="Teléfono" placeholder="Teléfono"
											class="form-control input-md" type="text">
									</div>
								</div>

							</div>
							<div class="col-md-6">
								<!-- Text input-->
								<div class="form-group">
									<label class="col-md-4 control-label" for="Usuario">Usuario</label>
									<div class="col-md-7">
										<input id="Usuario" name="Usuario" placeholder="Usuario"
											class="form-control input-md" type="text">
									</div>
								</div>

								<!-- Text input-->
								<div class="form-group">
									<label class="col-md-4 control-label" for="Contraseña">Contraseña</label>
									<div class="col-md-7">
										<input id="Contraseña" name="Contraseña"
											placeholder="Contraseña" class="form-control input-md"
											type="password">
									</div>
								</div>
								<!-- Text input-->
								<div class="form-group">
									<label class="col-md-4 control-label" for="Contraseña">Repite contraseña</label>
									<div class="col-md-7">
										<input id="Contraseña" name="Contraseña"
											placeholder="Contraseña" class="form-control input-md"
											type="password">
									</div>
								</div>
							</div>

						</fieldset>
					</form>
				</div>
				<div class="panel-footer">
					<button id="btn-guardar-editor" class="btn btn-success">Guardar</button>
					<button id="btn-cancelar-editor" class="btn btn-danger">Cancelar</button>
				</div>
			</div>
		</div>
	</div>
	<table class="table table-bordered" id="tabla-editores">
		<thead>
			<tr>
				<th>Select</th>
				<th>Nombre</th>
				<th>Apellidos</th>
				<th>Email</th>
				<th>Teléfono</th>
				<th>Usuario</th>
				<th>Contraseña</th>
			</tr>
		</thead>
		<tbody>
			<tr>
				<td><input type="checkbox" class="checkbox-editor"></td>
				<td>John</td>
				<td>Doe</td>
				<td>john@example.com</td>
				<td>636123123</td>
				<td>Doe</td>
				<td>Doe</td>
			</tr>
			<tr>
				<td><input type="checkbox" class="checkbox-editor"></td>
				<td>Mary</td>
				<td>Moe</td>
				<td>mary@example.com</td>
				<td>636123123</td>
				<td>Doe</td>
				<td>Doe</td>
			</tr>
			<tr>
				<td><input type="checkbox" class="checkbox-editor"></td>
				<td>July</td>
				<td>Dooley</td>
				<td>july@example.com</td>
				<td>636123123</td>
				<td>Doe</td>
				<td>Doe</td>
			</tr>
		</tbody>
	</table>

	<button type="button" style="display: none" id="btn-eliminar-editor"
		class="btn btn-danger btn-block">Eliminar editor/es</button>

</div>

<%@ include file="../jspf/footer.jspf"%>
