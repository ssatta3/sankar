<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<script type="text/javascript">
function employeeRemainingPoints(val){
	var remaningPoints = document.getElementById("pointRemaining").textContent - val.value;
	if(remaningPoints < 0){
		alert("Insufficient points. you have Remaining points: "+  document.getElementById("pointRemaining").textContent);
	}else{
		document.getElementById("pointRemaining").textContent = remaningPoints;
		document.getElementsByName("pointRemaining")[0].value = remaningPoints;
	}
	document.getElementsByName("submitionPoints")[0].value = remaningPoints;
}
function validateHome(){
	var returnValue =false;
	if(document.getElementsByName("submitionPoints")[0].value < 0){
		alert("Please use points with in remianing points");
	}else{
		returnValue = true;
	}
	return returnValue;
}
</script>
<body>
<form action="LoginServlet" method="post">
<input type="hidden" name="submitionPoints">
<div style="float:left; width:25%;"><img alt="Logo" src="${pageContext.request.contextPath}/Images/UCP_Seguin_logo.jpg" HEIGHT="30%" WIDTH="70%"></div>
<div style="float:left; width:40%;"><br>
<table border="1" style="border-collapse:collapse;" cellpadding="10">
	<tr><td>Points <br> Remaining</td><td style="background-color: #ccc"> <span id="pointRemaining"> <%=(Integer)session.getAttribute("point_remaining")%></span> 
	<td>Days still store <br> closes</td><td><%=request.getAttribute("daysRemaining") %></td>
	</tr>
</table>
</div>
<div style="float:left; width:35%;"><br>
<table border="1" style="border-collapse:collapse;" cellpadding="10">
	<tr><td style="background-color: #9E9E9E">EmpID:<br><%=session.getAttribute("employeeId")%></td>
	<td style="background-color: #ccc" ><input type = "submit" name="submit" value="My Bids" style="background-color: #ccc;width: 80px;height: 40px;"> </td>
	<%if(session.getAttribute("ADMIN") != null && session.getAttribute("ADMIN").equals("ADMIN")){ %>
		<td style="background-color: #ccc">
		<input type = "submit" name="submit" value="Manage" style="background-color: #ccc;width: 80px;height: 40px;">	</td>
	<%} %>
	<td style="background-color: #ccc"><input type = "submit" name="submit" value="Sign Out" style="background-color: #ccc;width: 80px;height: 40px;"></td></tr>
</table>
</div>
</form>
<br><br><br>
</body>
</html>