<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:if test="${not empty signinErrorMessage}">
	<div class="error">
		Unable to Log In: <br/>	
		<c:out value="${signinErrorMessage}"/>
	</div>
</c:if>

<form id="login_form" action="<c:url value="j_spring_security_check" />" method="post">
  	<fieldset>	
  		<legend>Login</legend>
  		<p>
       		<label for="j_username">Username</label>
			<input type="text" name="j_username" id="j_username" />
		</p>
		<p>
			<label for="j_password">Password</label>
			<input type="password" name="j_password" id="j_password"/>
		</p>
		<p>
			<input type="submit" value="Sign In"/>
		</p>
	</fieldset>
</form>