<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html>
<html>
	<head>
		<title>Floor 41 - Register</title>
		<link type="text/css" rel="stylesheet" href="<c:url value="/resources/css/blueprint/print.css"/>" />
		<link type="text/css" rel="stylesheet" href="<c:url value="/resources/css/blueprint/screen.css"/>" />
		<!--[if IE]>
		<link type="text/css" rel="stylesheet" href="<c:url value="/resources/css/blueprint/ie.css"/>" />	
		<![endif]-->
		<link type="text/css" rel="stylesheet" href="<c:url value="/resources/css/blueprint/print.css"/>" />
		<link type="text/css" rel="stylesheet" href="<c:url value="/resources/css/menu.css"/>" />
		<script type="text/javascript" src="<c:url value="/resources/js/jquery-1.7.1.min.js"/>"></script>
	</head>
	<body>
		<c:if test="${not empty registerErrorMessage}">
		<div class="error">
			Registration Error: <br/>	
			<c:out value="${registerErrorMessage}"/>
		</div>
		</c:if>
		<div class="container">
			<form:errors path="user" class="error" />
			<form:form modelAttribute="user" action="register" method="post">
				<fieldset>
					<legend>Register</legend>
					<p>
						<form:label path="username" cssErrorClass="error">Username</form:label><br />
						<form:input path="username" />
					</p>
					<p>
						<form:label path="password" cssErrorClass="error">Password</form:label><br />
						<form:password path="password"/>
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
		</div>
	</body>
</html>