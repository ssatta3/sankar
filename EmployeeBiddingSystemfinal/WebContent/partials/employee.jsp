<%@page import="com.vo.Employee"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Manage Accounts</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/layout.css" media="screen"/>
</head>
<script type="text/javascript">
function validateUpdatedEmp(){
	var returnValue = true;
	var employeeName = document.getElementsByName("employeeName")[0].value;
	var supervisorName = document.getElementsByName("supervisorName")[0].value;
	if(employeeName.length == 0){
		alert("Please enter Emloyee Name. It should not be empty");
		returnValue = false;
	}else if(supervisorName.length == 0){
		alert("Please enter supervisor Name.It should not be empty");
		returnValue = false;
	}
	return returnValue;
	
}

function validateFetchData(){
	if(document.getElementsByName("empid")[0].value.length == 0){
		alert("Please enter Employee Id that you want to edit");
		return false;
	}
}
function addPoint(val){
	var remainingPoints = document.getElementsByName("employeeRemainingPoints")[0].value;
	document.getElementsByName("employeeRemainingPoints")[0].value = +remainingPoints + +val.value;
}
function deletePoint(val){
	var remainingPoints = document.getElementsByName("employeeRemainingPoints")[0].value;
	if(remainingPoints - val.value < 0){
		alert("Remaining Points are going less than 0");
	}else{
		document.getElementsByName("employeeRemainingPoints")[0].value = remainingPoints - val.value;	
	}
}
function validateAddEmployee(){
		var employeeId = document.getElementsByName("Employeeid")[0].value;
		var empName = document.getElementsByName("name")[0].value;
		var supervisorId = document.getElementsByName("supervisorid")[0].value;
		var points = document.getElementsByName("points")[0].value;
		if(employeeId.length ==0 || empName.length ==0 || supervisorId.length ==0 || points.length == 0){
			alert("Emloyee Id, Employee name, supervisor Id, points are mandatory fiels. Please enter all the details");
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
<body>
<div><jsp:include page="../CommonHeader/header.jsp" /></div><br><br><br>
<%if(request.getAttribute("updateDataErrorReoprt") != null) { %>
	<span style="color: red; padding-left:4em"> <%=request.getAttribute("updateDataErrorReoprt") %></span>
<%} %>
<form action="ManageAccountServlet" method="post">
<h2 style="padding-left:3em">Manage Accounts</h2>
<table style="padding-left:5em">
<tr>
<td>Employee ID :</td>
<td><input type="text" name="empid" onkeypress="return isNumber(event)"/></td>
<td><input type="submit" name="submit" class="input-rounded-button" style="width: inherit;" value="Fetch Data" onclick="return validateFetchData()"/></td>

</tr>
   </table>
   <table style="padding-left:5em">
  <tr>
   		<td style=" padding-right: 50px;"><b>Employee Id</b></td>
   		<td style=" padding-right: 150px"><b>Name </b></td>
   		<td style=" padding-right: 150px"><b>Supervisor Name</b></td>
   		<td style=" padding-right: 50px"><b>Points Remaining</b></td>
   		<td style=" padding-right: 50px"><b>Add</b></td>
   		<td style=" padding-right: 50px"><b>Deduct</b></td>
   </tr>
   <tr>
   <%
   if(request.getAttribute("empDetails")!=null){
   final Employee emp = (Employee)request.getAttribute("empDetails");
   if(emp.getEmployeeId() != 0){
   %>
   		<td><%=emp.getEmployeeId() %></td>
   		<td><input type="text" name="employeeName" value="<%=emp.getEmployeeName() %>"/></td>
   		<td><input type="text" name="supervisorName" value="<%=emp.getSupervisorName() %>"/></td>
   		<td><input type="text" name="employeeRemainingPoints" value="<%=emp.getPointsRemaining() %>" readonly style="border-width: 0"/></td>
   		<td><input style="width:50px;" type="text" name="addPoints" onchange="addPoint(this)" onkeypress="return isNumber(event)"/></td>
   		<td><input style="width:50px;" type="text" name="deletePoints" onchange="deletePoint(this)" onkeypress="return isNumber(event)"/></td>
   		<td style=" padding-right: 20px"><div><input type="submit" name="submit" style="width: 100px" class="input-rounded-button"  value="Update" onclick="return validateUpdatedEmp()"/></div></td>
   		<td style=" padding-right: 20px"><button name="submit" value="deleteEmp" ><img src="${pageContext.request.contextPath}/Images/delete.png" width="30" height="30"></button></td>
   		
   		<%}else{%>
   			<span style="color: red; padding-left:5em;"> Employee Id not found in Database</span>
   		<%}}%>
   </tr>
   </table>
   <br/><br/>
   <table  style="padding-left:5em" width="90%">
   <tr ><td colspan="9"><h4>Add Employee</h4><hr/></td></tr>
   <tr><td>Employee Id: </td><td><input type="text" name="Employeeid" onkeypress="return isNumber(event)"></td>
   <td>Name: </td><td><input type="text" name="name"></td>
   <td>SupervisorId: </td><td><input type="text" name="supervisorid" onkeypress="return isNumber(event)"></td>
   <td>Points: </td><td><input type="text" name="points" onkeypress="return isNumber(event)"> </td>
   <td><input style="width: inherit"  class="input-rounded-button" type="submit" name="submit" value="Add Employee" onclick="return validateAddEmployee()" /></td>
</table>
<br/><br/>
</form>
<form action="LoginServlet" method="post">
<div style="padding-right: 9em;"><input style="width: inherit;  float: right;"  class="input-rounded-button" type="submit" name="submit" value="Back To Catalog" /></div>
</form>
</body>
</html>