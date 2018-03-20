<%@ page pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>

<%@ include file="../jspf/header.jspf"%>

<div class="starter-template">
	<h1>Admin</h1>
	<form action="/admin/addEditor" method="post">
				<label for="user">user<input name="user"/></label>
				<label for="password">password<input type="password" name="password"/></label>
				<label for="email">email<input type="text" name="email"></label>
				<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
	            
	            <div class="form-actions">
	                <button type="submit" class="btn">Crear editor</button>
	            </div>
	</form>

	<hr/>
	<table>
	<thead>
	<tr><th>id<th>login<th>roles</tr>
	</thead>
	<tbody>
	<c:forEach items="${users}" var="u">
		<tr>
		<td>${u.id}<td>${u.login}<td>${u.roles} <td>${u.password}
		</tr>	
	</c:forEach>
	</tbody>
</table>

		
</div>

<%@ include file="../jspf/footer.jspf"%>
