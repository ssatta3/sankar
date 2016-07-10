<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ page import="java.util.List" %>
    <%@ page import="com.vo.Supervisor" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>SuperVisior List</title>
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
function isNumber(evt){
	evt = (evt)?evt:window.event;
	var charcode = (evt.which)?evt.which:evt.keyCode;
	if(charcode > 31 && (charcode < 48 || charcode > 57)){
		return false;
	}
	return true;
}
</script>
<div><jsp:include page="../CommonHeader/header.jsp" /></div><br><br><br>
<form action="LoginServlet" method="post">
<h1 style=" padding-left: 5em;">Reports</h1><br/>
<div style="padding-left: 10em;">Supervisor ID :&nbsp;&nbsp;&nbsp;<input type="text" name="supid"/>
<input type="submit" class="input-rounded-button" style="width: inherit;" name="submit" value="Get Supervisor" onclick="return validateSupId()"/></div><br/><br/>
<%
	final List<Supervisor> employeeList = (List<Supervisor>)request.getAttribute("supervisorList");
	int i=0;%>
<table style="border: 0px; border-spacing: 0px; padding-left: 10em;">
	<% if(employeeList !=null){
		for(Supervisor sup : employeeList){
			if(sup.getEmployeeId() != 0){
	%>
	<tr><td colspan="2" align="center"><b><%=sup.getEmployeeName()+"-    Points: "+sup.getTotalPoints() %></b></td></tr>
	<%}}%>
	<tr><th align="center"><b>Employee Name</b></th>
	<th style="font-stretch: wider;"><b>  Points</b></th>
	</tr>
	<%for(Supervisor sup : employeeList){
		if(sup.getEmployeeId() == 0){
			i++;%>
	<tr class="rows<%=i%2 %>">
	<td align="center"><%=sup.getEmployeeName() %></td>
	<td align="center"><%=sup.getTotalPoints() %></td>
	</tr>
	<%}}} %>

</table>
<div style="padding-left:43em"><input style="width: inherit;" class="input-rounded-button" type="submit" name="submit" value="Back To Catalog"></div>
</form>
</body>
</html>