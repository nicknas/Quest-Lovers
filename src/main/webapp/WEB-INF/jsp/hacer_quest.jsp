<%@ page pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>

<%@ include file="../jspf/header.jspf"%>


<script src="${s}/js/jquery.questhandler.js"> </script>

<div class="starter-template">
	
	<div class="tile">
		<h1 class="title-quest"></h1>
		
		<!-- 
		<div>
			 <ul class="pagination">
				<li class="previous"><a href="#fakelink" class="fui-arrow-left"></a></li>
	            <li class="active"><a href="#fakelink">1</a></li>
	            <li><a href="#fakelink">2</a></li>
	            <li><a href="#fakelink">3</a></li>
	            <li><a href="#fakelink">4</a></li>
	            <li class="next"><a href="#fakelink" class="fui-arrow-right"></a></li>
            </ul>
            
		
		</div>
		-->
		<div class="historia">
			<p class="quest-snipped-text">
			</p>
			
			<div class="row">
				<div class="col-md-4 col-xs-12">
					<a class="btn btn-block btn-main-color r1" href="#"></a>
				</div>
				<div class="col-md-4 col-xs-12">
					<a class="btn btn-block btn-main-color r2" href="#"></a>
				</div>
				<div class="col-md-4 col-xs-12">
					<a class="btn btn-block btn-main-color r3" href="#"></a>
				</div>
			</div>
		</div>
	</div>

	<%@ include file="../jspf/authinfo.jspf"%>		
</div>

<%@ include file="../jspf/footer.jspf"%>
