<%@ page pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>

<%@ include file="../jspf/header.jspf"%>

<div class="starter-template">
	<h1>Quests</h1>

	<hr/>
	
	<div class="quests-list">
		<div class="quest">
			<h3>Titulo de la Quest</h3>
			<p>
			Por la naturaleza el hombre siempre se ha comunicado, 
			si consideramos la época prehistorica, encontramos registros 
			que nos hacen ver que tenían cierta comunicación, esto mediante 
			sonidos guturales y también perduran las pinturas rupestres que 
			pueden ser consideradas como el primer medio de comunicación.
			</p>
			<form action="/hacer_quest">
    			<input type="submit" value="Empezar esta Quest">
			</form>
		</div>
		<div class="quest">
			<h3>Titulo de la Quest</h3>
			<p>Por la naturaleza el hombre siempre se ha comunicado, 
			si consideramos la época prehistorica, encontramos registros 
			que nos hacen ver que tenían cierta comunicación, esto mediante 
			sonidos guturales y también perduran las pinturas rupestres que 
			pueden ser consideradas como el primer medio de comunicación.
			</p>
		</div>
	</div>
	
	<hr/>
	
	

	<%@ include file="../jspf/authinfo.jspf"%>		
</div>

<%@ include file="../jspf/footer.jspf"%>
