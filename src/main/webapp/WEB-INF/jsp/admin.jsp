<%@ page pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>

<%@ include file="../jspf/header.jspf"%>

<div class="starter-template">
	<h1>Admin</h1>


	<hr/>

	<div class="columns">
		<div class="column">
			<h4>Añadir nuevo editor</h4>
			<form action="/admin/addUser" method="post">
				<label for="user">user<input name="user"/></label>
				<label for="password">password<input type="password" name="password"/></label>
				<label for="email">email<input type="text" name="email"></label>
				<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
	            
	            <div class="form-actions">
	                <button type="submit" class="btn">Crear editor</button>
	            </div>
			</form>
		</div>
		
		<div class="column">
			<h4>Reportes</h4>
			<div class="lista-reportes">
				<div class="reporte">
					<p>Usuario que realiza el reporte</p>
					<p>Usuario que se reporta</p>
					<p>Comentario del reporte...............					...................................
					...................................
					<button type="button" class="btn btn-success" aria-label="Left Align">
  						<span class="glyphicon glyphicon-ok" aria-hidden="true"></span>
					</button>
					<button type="button" class="btn btn-danger" aria-label="Left Align">
  						<span class="glyphicon glyphicon-remove" aria-hidden="true"></span>
					</button>
					</p>							
				</div>
				<div class="reporte">
					<p>Usuario que realiza el reporte</p>
					<p>Usuario que se reporta</p>
					<p>Comentario del reporte...............
					...................................
					...................................
					<button type="button" class="btn btn-success" aria-label="Left Align">
  						<span class="glyphicon glyphicon-ok" aria-hidden="true"></span>
					</button>
					<button type="button" class="btn btn-danger" aria-label="Left Align">
  						<span class="glyphicon glyphicon-remove" aria-hidden="true"></span>
					</button>
					
					</p>	
				</div>
		</div>
	</div>
</div>
	<div class="columns">
		<div class="column">
			<h4>Lista de editores</h4>
			<table>
			<thead>
			<tr><th>id<th>editor<th>historias publicadas</tr>
			</thead>
			<tbody>
			<c:forEach items="${users}" var="u">
				<tr>
				<td>${u.id}<td>${u.login}<td>${u.roles}
				</tr>	
			</c:forEach>
			</tbody>
			</table>
		</div>
		<div class="column">

			<%@ include file="../jspf/authinfo.jspf"%>		
		</div>
	</div>		
		
</div>

<%@ include file="../jspf/footer.jspf"%>
