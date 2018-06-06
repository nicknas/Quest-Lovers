<%@ page pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix = "fn" %>


<%@ include file="../jspf/header.jspf"%>

<div class="starter-template">
    <h1>Quests List</h1>    
    </hr>
    <div class="quests-container row">
        <div class="col-xs-12 quest">
            <h3>Todas nuestras Quests disponibles</h3>
            <ul class="list-quest">

            	<c:forEach items="${all_quests}" var="quests">
	               
	                	<li class="quest col-md-3 col-xs-12">
	                    <div  id="${quests.id}" class="quest-container tile">
	                        <h6>${quests.titulo}</h6>
	                        <p>${quests.descripcion}</p>
	                        <form action="/hacer_quest?id=${quests.id}" method="GET">
	                        	<input type="hidden" name="id" value="${quests.id}">
	                            <input class="btn btn-block btn-primary" type="submit" value="Empezar esta Quest">
	                        </form>
	                    </div>
	                </li>
	            </c:forEach> 
	           
            </ul>
        </div>
    </div>
    
    <hr/>
    
    

    <%@ include file="../jspf/authinfo.jspf"%>        
</div>

<%@ include file="../jspf/footer.jspf"%>
