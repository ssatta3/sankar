<%@page import="org.apache.jasper.tagplugins.jstl.core.ForEach"%>
<%@page import="com.vo.Bids"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>My Bids</title>
<head>
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/layout.css" media="screen"/> 
</head>
<body>
<div><jsp:include page="../CommonHeader/header.jsp" /></div><br><br><br>
<%if(request.getAttribute("myBids")!=null && request.getAttribute("myBids")!=""){
List<Bids> bids = (List<Bids>)request.getAttribute("myBids");
int total = 0;
%>
<form action="LoginServlet" method="post">
<h2 style="padding-left:7em">My Bids</h2>
<table cellpadding="10" style="padding-left:10em">
<tr style="background-color: #9E9E9E;"><th colspan="2"><B>ITEMS</B></th><th width="300" style="text-align: right;"><b>POINTS</b></th></tr>
<%for (Bids bid : bids) {%>
	<tr><td><img src="${pageContext.request.contextPath}/partials/getImageDetails.jsp?imag_id=<%=bid.getItemId()%>" width="250" height="130" alt="IMAGE" /></td>
	<td><%=bid.getItemName()%><br/><br/><br/><%=bid.getDescription() %></td>
	<td align="right"><%=bid.getPoints() %></td></tr>
	<%total += bid.getPoints(); %>
<%} %>
<tr ><td colspan="3" style="background-color: #9E9E9E;text-align: right;">Total: <%=total%></td></tr>
<%} %>
</table>
<div style="padding-left:10em"><input style="width: inherit;" class="input-rounded-button" type="submit" name="submit" value="Back To Catalog"></div>
</form>
<div class="print" style="padding-left: 38em; border: 0px;" >
	<button name="submit" value="Print"  onClick="window.print()"><img src="${pageContext.request.contextPath}/Images/printer.png"></button>
</div>
</body>
</html>