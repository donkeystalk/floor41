<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<form:errors path="user" class="error" />
<form:form modelAttribute="user" action="register" method="post">
	<fieldset>
		<legend>Register</legend>
		<p>
			<form:label path="username" cssErrorClass="error">Username</form:label><br />
			<form:input path="username" /><form:errors cssStyle="margin-left: 0.5em; color: red; font-weight: bolder" path="username" />
		</p>
		<p>
			<form:label path="password" cssErrorClass="error">Password</form:label><br />
			<form:password path="password"/><form:errors cssStyle="margin-left: 0.5em; color: red; font-weight: bolder" path="password" />
		</p>
		<p>
			<form:label path="confirmPassword">Password</form:label><br />
			<form:password path="confirmPassword"/>
		</p>
		<p>
			<form:button>Submit</form:button>
		</p>
	</fieldset>
</form:form>