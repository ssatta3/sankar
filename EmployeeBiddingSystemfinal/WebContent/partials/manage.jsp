<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Manage</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/layout.css" media="screen"/>
</head>

<script type="text/javascript">
function validateItem(){
	var returnValue = false;
	var Events = document.getElementById("Events").value;
	var Description = document.getElementsByName("Description")[0].value;
	var Image = document.getElementById("ItemImage").value;
	if(Events == 0){
		alert("please select category");
	}
	else if((Description != null & Description.length ==0) || Description == null){
		alert("please select Description");
	}else if(document.getElementsByName("submitionPoints")[0].value < 0){
		alert("Please use points with in remaining points");
	}else if(Image.length == 0){
		alert("Please select image");
	}else{
		returnValue =true;
	}
	return returnValue;
	
}
function getXMLObject()
{
   var xmlHttp = false;
   try {
     xmlHttp = new ActiveXObject("Msxml2.XMLHTTP")  // For Old Microsoft Browsers
   }
   catch (e) {
     try {
       xmlHttp = new ActiveXObject("Microsoft.XMLHTTP")  // For Microsoft IE 6.0+
     }
     catch (e2) {
       xmlHttp = false   // No Browser accepts the XMLHTTP Object then false
     }
   }
   if (!xmlHttp && typeof XMLHttpRequest != 'undefined') {
     xmlHttp = new XMLHttpRequest();        //For Mozilla, Opera Browsers
   }
   return xmlHttp;
}
 
var xmlhttp = new getXMLObject();
 
function ajaxFunction() {
  //var getdate = new Date();  //Used to prevent caching during ajax call
 var employeeid = document.getElementsByName("Employeeid")[0].value;
  if(xmlhttp) {
    xmlhttp.open("GET","ManageAccountServlet?submit=ajaxCall&employeeId=" + employeeid,true); //gettime will be the servlet name
    xmlhttp.onreadystatechange  = handleServerResponse;
    xmlhttp.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
    xmlhttp.send(null);
  }
}
 
function handleServerResponse() {
   if (xmlhttp.readyState == 4) {
     if(xmlhttp.status == 200) {
       document.getElementById("PointsRemaining").textContent = xmlhttp.responseText; //Update the HTML Form element
     }
     else {
        alert("Error during AJAX call. Please try again");
     }
   }
}
function calculateRemainingPoints(val){
	var remaningPoints = document.getElementById("PointsRemaining").textContent - val.value;
	if(remaningPoints < 0){
		alert("Insufficient points. This employee have Remaining points: "+  document.getElementById("PointsRemaining").textContent);
	}else{
		document.getElementById("PointsRemaining").textContent = remaningPoints;
		document.getElementsByName("pointsRemianing")[0].value = remaningPoints;
	}
	document.getElementsByName("submitionPoints")[0].value = remaningPoints;
}
function validateSubmitBid(){
	var empId = document.getElementsByName("Employeeid")[0].value;
	var itemId = document.getElementsByName("Itemid")[0].value;
	var point = document.getElementsByName("points")[0].value;
	if((empId != null & empId.length ==0) || empId == null ){
		alert("Please enter Employee id");
		return false;
	}
	else if((itemId != null & itemId.length ==0) || itemId == null ){
		alert("Please enter item id");
		return false;
	}
	else if((point != null & point.length ==0) || point == null ){
		alert("Please enter Bid points");
		return false;
	}
	else if(document.getElementsByName("submitionPoints")[0].value < 0){
		alert("Insufficient points. This employee have Remaining points: "+  document.getElementById("PointsRemaining").textContent);
		return false;
	}
	return true;
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
<%if(request.getAttribute("manageErrorValue") != null) { %>
	<span style="color: red;"> <%=request.getAttribute("manageErrorValue") %></span>
<%} %>
<form action="LoginServlet" method="post" enctype="multipart/form-data">
<div><span style="font-size: 50"><b>Manage</b></span>
<br><br>
<span style="background-color:#CCC;padding-right:90%;padding-bottom: 5px;padding-top: 5px;white-space: nowrap;"> Manage Accounts </span><br><br>
<input style="width: inherit"  class="input-rounded-button" type="submit" name="submit" value="Add/Edit Accounts"/>
<br><br><br>
<table  width="90%">
   <tr ><td colspan="9">Add Bid<hr/></td></tr>
   <tr><td>Employee Id: </td><td><input type="text" name="Employeeid" onchange="javascript:ajaxFunction();" onkeypress="return isNumber(event)"></td>
   <td>Item Id: </td><td><input type="text" name="Itemid" onkeypress="return isNumber(event)"></td>
   <td>points: </td><td><input type="text" name="points" onchange="calculateRemainingPoints(this)" onkeypress="return isNumber(event)"></td>
   <td>Points <br>Remainings: </td><td><span id="PointsRemaining" ></span> </td>
   <td><input style="width: inherit"  class="input-rounded-button" type="submit" name="submit" value="Submit Bid" onclick="return validateSubmitBid()"/></td>
</table>
<input type="hidden" name="pointsRemianing">
<br><br>
<span style="background-color:#CCC;padding-right:93%;padding-bottom: 5px;padding-top: 5px;white-space: nowrap;"> Manage Site </span><br><br>
<input style="width: inherit"  class="input-rounded-button" type="submit" name="submit" value="Manage Items" />
<br><br><br>
<table width="93%">
   <tr><td>Closing Date:</td><td><input type="text" name="date"></td>
   <td><input style="width: inherit"  class="input-rounded-button" type="submit" name="submit" value="Submit Date"/></td>
   </tr>
   <tr ><td colspan="5">Add Item<hr/></td></tr>
   <tr><td rowspan="2"><span>Upload Image..</span> <input type="file" id="ItemImage" name="ItemImage" size="50"/></td>
   <td>Description: </td><td><input type="text" name="Description"></td>
   <td>Category: </td>
   <td> <select name="Events" id="Events">
		<option value="0" selected>----Please select-----</option>
		<%if(request.getAttribute("categoryList")!=null && request.getAttribute("categoryList")!="")
		{
		ArrayList<String> categoryList = (ArrayList<String>)request.getAttribute("categoryList");
		for(String category : categoryList)
		{
		%>
		<option value="<%=category%>"> <%=category%>  </option>
		<%}}%>
		</select></td>
   <td><input style="width: inherit"  class="input-rounded-button" type="submit" name="submit" value="Add Item" onclick="return validateItem()"/></td>
</table>

<br><br>
<span style="background-color:#CCC;padding-right:95%;padding-bottom: 5px;padding-top: 5px"> Reports </span><br><br>
<input style="width: inherit"  class="input-rounded-button" type="submit" name="submit" value="Winners List" /><br><br>
<input style="width: inherit"  class="input-rounded-button" type="submit" name="submit" value="Supervisior List" /><br><br>
<input style="width: inherit"  class="input-rounded-button" type="submit" name="submit" value="Employee Bid History" />
<input style="width: inherit;  float: right;"  class="input-rounded-button" type="submit" name="submit" value="Back To Catalog" />
</div>
</form>
</body>
</html>