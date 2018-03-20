<%@ page pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>

<%@ include file="../jspf/header.jspf"%>

<div class="starter-template">
	
	<div class="login-form">	
	    <form action="/addUser" method="post">               
	        <fieldset>
	            <legend>Please Sign Up</legend>
	            <div class="form-group">
		            <input class="form-control login-field" value=""
						placeholder="Enter your username" id="username" type="text" name="username"/>
					<label class="login-field-icon fui-user" for="username"></label> 
		        </div>    
		        <div class="form-group">
		        	<input class="form-control login-field" value="" placeholder="Password" id="password" name="password" type="password">
             		<label class="login-field-icon fui-lock" for="password"></label>
	            </div>
	            <div class="form-group">
		        	<input class="form-control login-field" value="" placeholder="Repeat your password" id="password2" name="password2" type="password">
             		<label class="login-field-icon fui-lock" for="password2"></label>
	            </div>
	            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
	 
	            <div class="form-actions">
	            	<div class="col-md-12 col-xs-12">
	                	<button class="btn  btn-lg btn-block btn-main-color" type="submit" class="btn">Sign Up</button>
	                </div>
	            </div>
	        </fieldset>
	    </form>
	</div>
	<%@ include file="../jspf/authinfo.jspf"%>		
</div>

<%@ include file="../jspf/footer.jspf"%>
