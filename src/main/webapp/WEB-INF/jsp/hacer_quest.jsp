<%@ page pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>

<%@ include file="../jspf/header.jspf"%>


<div class="starter-template">
	
	<div class="tile">
		<h1 class="title-quest"></h1>
		
		<div class="historia">
			<p class="quest-snipped-text">
			</p>
			
			<div class="row botones_respuestas">
				<div class="col-md-4 col-xs-12">
					<a class="btn btn-block btn-main-color questopt r1" href="#" ></a>
				</div>
				<div class="col-md-4 col-xs-12">
					<a class="btn btn-block btn-main-color questopt r2" href="#" ></a>
				</div>
				<div class="col-md-4 col-xs-12">
					<a class="btn btn-block btn-main-color questopt r3" href="#"></a>
				</div>
			</div>
            <div class="row final hidden">
                <div class="col-md-12 col-xs-12">
					<p>Has terminado</p>
            </div>
            <form action="/terminar_quest" method="GET">
            	<input type="hidden" name="id_quest" value="1">
            	<input type="hidden" name="id_user" value="2">
            	<input type="hidden" name="resultado" value="Triste">
				<button value="terminar" type="submit" class="btn btn-block btn-main-color">Terminar </button>
			</form>	
			</div>
		</div>
	</div>

	<%@ include file="../jspf/authinfo.jspf"%>		
</div>

<script src="${s}/js/jquery.questhandler.js"> </script>
<%@ include file="../jspf/footer.jspf"%>
