<%@ page pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>

<%@ include file="../jspf/header.jspf"%>

<div class="starter-template">
    <h1>Todas las Quest</h1>    
    <hr />
        <div class="col-xs-12 quest">
            <ul class="list-quest">
            	<c:forEach var = "quest" items = "${listQuest}">
            		<c:if test='${quest.getEnabled() eq Byte.parseByte("1")}'>
            			<li class="quest col-md-3 col-xs-12">
		                    <div class="quest-container tile">
		                        <h6>${quest.getTitulo()}</h6>
		                        <p>${quest.getDescripcion()}</p>
		                        <div class="row">
			                        <form class="col-md-6" action="/ban_quest" method="POST">
			                        	<input type="hidden" name="id_quest" value="${quest.getId()}"/>
			                        	<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
			                        	<button type="submit" class="btn btn-block btn-danger">Banear esta Quest</button>
			                       	</form>
			                        <form class="col-md-6" action="/ver_historia_admin" method="POST">
			                        	<input type="hidden" name="id_quest" value="${quest.getId()}"/>
			                        	<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
			                        	<button type="submit" class="btn btn-block btn-info">Ver Quest</button>
			                        </form>
		                        </div>
		                    </div>
                		</li>
            		</c:if>
            	</c:forEach>
            </ul>
        </div>
    
    <hr/>
    
    

    <%@ include file="../jspf/authinfo.jspf"%>        
</div>

<%@ include file="../jspf/footer.jspf"%>
