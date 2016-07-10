<%@page import="org.eclipse.jdt.internal.compiler.ast.ForeachStatement"%>
<%@page import="java.util.HashMap"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Winner List</title>
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/css/layout.css" media="screen" />
<style type="text/css">
th {
	padding-right: 20px;
}

.rows0, th {
	background-color: #ccc;
}
</style>
</head>
<body>
	<div><jsp:include page="../CommonHeader/header.jsp" /></div>
	<br>
	<br>
	<br>
	<h1 style="padding-left: 5em">Reports</h1>
	<h2 style="padding-left: 7em">Winners</h2>
	<form action="LoginServlet" method="post">
		<table style="border: 0px; border-spacing: 0px; padding-left: 10em">
			<tr>
				<th>Item Id</th>
				<th>Description</th>
				<th>Winner Employee Id</th>
				<th>Winner Employee Name</th>
			</tr>
			<%
				HashMap<String, String> winnerList = (HashMap<String, String>) request.getAttribute("winnersList");
				int i = 0;
				for (String itemIdDesc : winnerList.keySet()) {
					i++;
			%>
			<tr class="rows<%=i % 2%>">
				<td align="center"><%=itemIdDesc.split(":")[0]%></td>
				<td align="center"><%=itemIdDesc.split(":")[1]%></td>
				<td align="center"><%=winnerList.get(itemIdDesc).split(":")[0]%></td>
				<td align="center"><%=winnerList.get(itemIdDesc).split(":")[1]%></td>
			</tr>
			<%
				}
			%>
		</table>
		<div style="padding-left: 38em">
			<input style="width: inherit;" class="input-rounded-button"
				type="submit" name="submit" value="Back To Catalog">
		</div>
	</form>
	<div class="print" style="padding-left: 25em; border: 0px;" >
		<button name="submit" value="Print"  onClick="window.print()"><img src="${pageContext.request.contextPath}/Images/printer.png"></button>
		</div>
</body>
</html>