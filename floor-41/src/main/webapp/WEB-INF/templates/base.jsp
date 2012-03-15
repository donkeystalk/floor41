<!DOCTYPE html>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
	<head>
		<tiles:insertAttribute name="imports" />
		<title><tiles:getAsString name="pageTitle" /></title>
	</head>
	<body>
		<div class="container">
			<div class="span-24 last">
				<tiles:insertAttribute name="header" />
			</div>
			<div class="span-24 last">
				<tiles:insertAttribute name="menu" />
			</div>
			<c:if test="${not empty info}">
				<div class="box span-24 last">
					<c:out value="${info}"/>
				</div>
			</c:if>
			<div class="span-24 last">
				<tiles:insertAttribute name="body" />
			</div>
			<div class="span-24 last">
				<tiles:insertAttribute name="footer" />
			</div>
		</div>
	</body>
</html>