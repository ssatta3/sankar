<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import="org.apache.jasper.tagplugins.jstl.core.ForEach"%>
<%@page import="com.vo.Bids"%>
<%@page import="java.util.List"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<style type="text/css">
.input-rounded-button {
    -webkit-border-radius: 5px;
    -moz-border-radius: 5px;
    border-radius: 5px;
    border: 1px solid gray;
    padding:0 3px 0 3px;
    display:inline-block;
    text-decoration:none;
    background:#ccc;
    cursor:pointer;
    font:16px sans-serif;
    width: inherit;
}

.input-rounded-button:hover { 
    text-decoration:none;  
    cursor:pointer;
    border:1px solid #FF2B06;
}
th
{
padding-right: 125px;
padding-left: 125px;
}
.rows0,th
{
background-color: #ccc;
}
</style>
<body>
<script type="text/javascript">
function validateSupId(){
	var supId = document.getElementsByName("supid")[0].value;
	if(supId.length == 0){
		alert("Please select Supervisor id");
		return false;
	}
}
</script>
<div><jsp:include page="../CommonHeader/header.jsp" /></div><br><br><br>
<form action="LoginServlet" method="post">
<h1 style=" padding-left: 5em;">Reports</h1><br/>
<div style="padding-left: 10em;">Employee ID :&nbsp;&nbsp;&nbsp;<input type="text" name="empId"/>
<input type="submit" class="input-rounded-button" style="width: inherit;" name="submit" value="Employee Bid details" onclick="return validateSupId()"/></div><br/><br/>
<%
if(request.getAttribute("myBids")!=null && request.getAttribute("myBids")!=""){
List<Bids> bids = (List<Bids>)request.getAttribute("myBids");
int total = 0;
%>
<h2 style="padding-left:7em">Employee Bids</h2>
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
</body>
</html>