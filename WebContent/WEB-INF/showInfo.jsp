<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>aap.sdn.project</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href='http://fonts.googleapis.com/css?family=Droid+Sans:400,700'
	rel='stylesheet' type='text/css'>
<style type="text/css">
body {
	font-family: 'Droid Sans', sans-serif;
	font-size: 1.2em;
}

h1 {
	display: inline;
	font-size: 80px;
}

form input,select {
	background: #ffffff;
	color: #000000;
	border: 1px solid #000000;
	font-family: Droid Sans;
}

td {
	border: 3px solid #ffffff;
	background: #dddddd;
	font-size: 0.9em;
}

.error {
	color: #A80000;
}

.success {
	color: #00CC66;
}
</style>
<script type="text/javascript">
	function validate() {
		if (document.getElementById("source").value == document
				.getElementById("destination").value) {
			alert("Source has to be different from destianation");
		}
		return document.getElementById("source").value != document
				.getElementById("destination").value;
	}
</script>
</head>
<body>
	<c:if test="${Clean=='true'}">
		<img
			src="http://icons.iconarchive.com/icons/gakuseisean/ivista-2/256/Alarm-Tick-icon.png"
			height="20px" />
		<span class="">Successfully cleaned old route</span>
		<br />
	</c:if>
	<c:if test="${Clean=='false'}">
		<img
			src="https://familysearch.org/learn/wiki/en/images/8/89/Deletion_icon.svg.png"
			height="20px" />
		<span class="">Failed to clean old route</span>
		<br />
	</c:if>
	<c:if test="${Change=='true'}">
		<img
			src="http://icons.iconarchive.com/icons/gakuseisean/ivista-2/256/Alarm-Tick-icon.png"
			height="20px" />
		<span class="">Successfully changed the route</span>
		<br />
	</c:if>
	<c:if test="${Change=='false'}">
		<img
			src="https://familysearch.org/learn/wiki/en/images/8/89/Deletion_icon.svg.png"
			height="20px" />
		<span class="">Failed to change to new route</span>
		<br />
	</c:if>
	<h1>aap.sdn.service</h1>
	<c:if test="${Loading=='1'}">
		<img src="http://flappy-birds.mobi/assets/img/loading.gif"
			height="80px">
	</c:if>
	<br />version 0.1. Created and written by aap 2014 under MIT License
	<br />
	<br /> This project is a Floodlight service that enhance forwarding
	controllability. By default Floodlight uses shortest paths as a routing
	routine. aap.sdn.service creates possibilities to change default
	forwarding to one of the others. This service uses a variant of
	Bellman-Ford algorithm which operates with complexity of O(k*n*(m-^2))
	where m is the number of edges and n is number of vertices. For more
	information about the functions and settings please READ ME.
	<br />
	<br />
	<h3>Reconfigure path:</h3>
	<c:if test="${DevicesNumber=='0'}">Unfortunately I can't detect any hosts in the network</c:if>
	<c:if test="${DevicesNumber>'0'}">
		<div>
			<form method="post" onsubmit="return validate()">
				Source: <select name="sourceHost" id="source">
					<c:forEach items="${DevicesList}" var="device">
						<c:forEach items="${device.mac}" var="mac">
							<c:if test="${SourceHost == mac}">
								<option selected value="${mac}">${mac}</option>
							</c:if>
							<c:if test="${SourceHost != mac}">
								<option value="${mac}">${mac}</option>
							</c:if>
						</c:forEach>
					</c:forEach>
				</select> Destination: <select name="destinationHost" id="destination">
					<c:forEach items="${DevicesList}" var="device">
						<c:forEach items="${device.mac}" var="mac">
							<c:if test="${DestinationHost == mac}">
								<option selected value="${mac}">${mac}</option>
							</c:if>
							<c:if test="${DestinationHost != mac}">
								<option value="${mac}">${mac}</option>
							</c:if>
						</c:forEach>
					</c:forEach>
				</select> <input type="submit" value="Configure" />
			</form>
		</div>
	</c:if>
	<br />


	<c:if test="${Error=='0'}">
		<c:if test="${RoutesNumber != '0'}">

		<form method="post">
								<input type="hidden" name="cleanAll" value="-1" />
								<input value="Clean All Entries" type="submit" /></form>
		
		
			<%
				int mIterator = 0;
			%>

			<table>
				<tr>
					<td style="text-align: center"><b>Route</b></td>
					<td style="text-align: center"><b>Change</b></td>
					<td style="text-align: center"><b>RTT</b></td>
				</tr>
				<tr>
					<td>Block traffic</td>
					<td><form method="post">
								<input type="hidden" name="selectRoute" value="-1" />
								<input value="Activate" type="submit" /></form></td>
								<td>Inf.</td>
				</tr>
				<c:forEach items="${Routes}" var="route">
					<tr>
						<td><c:forEach items="${route}" var="r">:${r}:</c:forEach></td>
						<td>
							<form method="post">
								<input type="hidden" name="selectRoute" value="<%=mIterator%>" />
								<input value="Activate" type="submit" />
								<%
									mIterator++;
								%>
							</form>
						</td>
						<td></td>

					</tr>
				</c:forEach>
			</table>
		</c:if>
	</c:if>
	<c:if test="${Error!='0'}">
		<img
			src="https://familysearch.org/learn/wiki/en/images/8/89/Deletion_icon.svg.png"
			height="20px" />
		<span class="">${Error}</span>
	</c:if>
</body>
</html>