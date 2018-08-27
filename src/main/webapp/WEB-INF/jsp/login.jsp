<%@ page pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>

<%@ include file="../jspf/header.jspf"%>

<div class="starter-template">
	<hr>
	<c:if test="${(errorLogin != null)}">
		<div class="alert alert-danger alert-dismissible">
			<input type="hidden" autofocus/>
 			<a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
 			<strong>El usuario o la contraseña es incorrecto</strong>
		</div>
	</c:if>
	<div class="login-form">	
	    <form action="/login" method="POST">               
	        <fieldset>
	            <legend>Please Login</legend>
	            <div class="form-group">
		            <input class="form-control login-field" value=""
						placeholder="Enter your username" id="username" type="text" name="username"/>
					<label class="login-field-icon fui-user" for="username"></label> 
		        </div>    
		        <div class="form-group">
		        	<input class="form-control login-field" value="" placeholder="Password" id="password" name="password" type="password">
             		<label class="login-field-icon fui-lock" for="password"></label>
	            </div>
	            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
	 
	            <div class="form-actions">
	            	<div class="col-md-6 col-xs-12">
	                	<button class="btn  btn-lg btn-block btn-main-color" type="submit" class="btn">Sign in</button>
	                </div>
	                <div class="col-md-6 col-xs-12">
	               	 <a href="/registro" class="btn  btn-lg btn-block btn-main-color">Sign up</a>
	                </div>
	            </div>
	        </fieldset>
	    </form>
	</div>
	<%@ include file="../jspf/authinfo.jspf"%>		
</div>

<%@ include file="../jspf/footer.jspf"%>
