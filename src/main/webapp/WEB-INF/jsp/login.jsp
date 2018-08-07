<%@ page pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>

<%@ include file="../jspf/header.jspf"%>

<div class="starter-template">
	<hr>
	<div class="login-form">	
	    <form action="/login" method="post">               
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
	                	<button class="btn  btn-lg btn-block btn-main-color" type="submit" class="btn">Log in</button>
	                </div>
	                <div class="col-md-6 col-xs-12">
	               	 <a href="/registro" class="btn  btn-lg btn-block btn-main-color"  class="btn">Register</a>
	                </div>
	            </div>
	        </fieldset>
	    </form>
	</div>
	<%@ include file="../jspf/authinfo.jspf"%>		
</div>

<%@ include file="../jspf/footer.jspf"%>
