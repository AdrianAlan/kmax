<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%-- <base href="${pageContext.request.contextPath}"> --%>
<link href="${pageContext.request.contextPath}/main_style.css"
	rel="stylesheet" type="text/css">
<title>Circuit Pusher</title>
</head>
<body>
	<%
		int mIterator = 0;
	%>

	Source: ${kMaxSource} Destination: ${kMaxDestination}
	<form method="post">
		SelectRoute: <select name="selectRoute">
			<c:forEach items="${kMaxRoutes}" var="route">
				<option value="<%=mIterator%>">
					<%
						mIterator++;
					%>
					<c:forEach items="${route}" var="r">:${r}:</c:forEach>
				</option>
			</c:forEach>
		</select> <input type="submit" />
	</form>
</body>
</html>