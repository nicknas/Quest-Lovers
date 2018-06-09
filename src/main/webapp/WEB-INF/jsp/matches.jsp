<%@ page pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>




<%@ include file="../jspf/header.jspf"%>

<div class="starter-template">
	<h1>Matches</h1>

	<hr/>
	<div class="row">
		<c:forEach items="${lista_matches}" var="matches">
			<div class="col-md-4 col-xs-12">
				<div class="tile">
					<h2>${matches.login}</h2>
					<a href="/match?id=${matches.id}">
						<c:choose>
							<c:when test="${matches.getNumPhotos() > 0}">
								<div id="userCarousel" class="carousel slide" data-ride="carousel">
									  <!-- Indicators -->
									  <ol class="carousel-indicators">
									  	<c:forEach var="i" begin="0" end="${matches.getNumPhotos() - 1}" step="1">
									  		<c:choose>
									  			<c:when test="${i == 0}">
									  				<li data-target="#userCarousel" data-slide-to="0" class="active"></li>
									  			</c:when>
									  			<c:otherwise>
									  				<li data-target="#userCarousel" data-slide-to="${i}"></li>
									  			</c:otherwise>
									  		</c:choose>
									  	
									  	</c:forEach>
									  </ol>
									
									  <!-- Wrapper for slides -->
									  <div class="carousel-inner">
									  	<c:forEach var="i" begin="1" end="${matches.getNumPhotos()}" step="1">
									  		<c:choose>
									  			<c:when test="${i == 1}">
									  				<div class="item active">
									      				<img src="/photo/${matches.getLogin()}/${i}" class="img-responsive img-center" alt="${i}">
									    			</div>
									  			</c:when>
									  			<c:otherwise>
									  				<div class="item">				     			 
									     				<img src="/photo/${matches.getLogin()}/${i}" class="img-responsive img-center" alt="${i}">
									 			    </div>
												</c:otherwise>
									  		</c:choose>
									  	</c:forEach>
									  </div>
									
									  <!-- Left and right controls -->
									  <a class="left carousel-control" href="#userCarousel" data-slide="prev">
									    <span class="glyphicon glyphicon-chevron-left"></span>
									    <span class="sr-only">Previous</span>
									  </a>
									  <a class="right carousel-control" href="#userCarousel" data-slide="next">
									    <span class="glyphicon glyphicon-chevron-right"></span>
									    <span class="sr-only">Next</span>
									  </a>
								</div> 
							</c:when>
							<c:otherwise>
								<img class="img-responsive img-center" src="/photo/${matches.getLogin()}/0">
							</c:otherwise>
						</c:choose>
					</a>
					<p>${matches.resumen}</p>
				</div>
				
			</div>
		</c:forEach>
	</div>
</div>
	
	<hr/>
	
	

	<%@ include file="../jspf/authinfo.jspf"%>		

<%@ include file="../jspf/footer.jspf"%>
