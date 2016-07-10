<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Registration Page</title>
</head>
<script>
	function validate(){
		var username = document.getElementsByName("username")[0].value;
		var pass1 = document.getElementsByName("userpass1")[0].value;
		var pass2 = document.getElementsByName("userpass2")[0].value;
		var returnValue = false;
		if(username.length < 2){
			alert("Employee name should contain more than 3 letters");
		}else if(pass1.length < 3){
			alert("Password should contain more than 3 letters");
		}else if(pass1 != pass2){
			alert("Two passwords doesn't match");
		}else if(document.getElementsByName("employeeId")[0].value == 0){
			alert("Please enter Employee Id");
		}else{
			returnValue =true;
		}
		return returnValue;
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
<form action="LoginServlet" method="post">
<div align="center"><img alt="Logo" src="Images/UCP_Seguin_logo.jpg" HEIGHT="40%" WIDTH="40%"></div>
<br>
<%if(request.getAttribute("ErrorMesage") != null) {%>
<span   style="color: red;padding-left: 35em "><%=request.getAttribute("ErrorMessage") %></span>
<%} %>
<table align="center" cellspacing="10">
 <tr>
<td>Employee ID:</td>
</tr>

<tr><td><input type="text" name="employeeId" onkeypress="return isNumber(event)"/></td></tr>
<tr><td>Employee Name:</td> </tr><tr><td><input type="text" name="username"></td></tr>
<tr><td> 
Password:
</td></tr>
<tr><td><input type="password" name="userpass1"/></td></tr>
<tr><td> 
Confirm Password:
</td></tr>
<tr><td><input type="password" name="userpass2"/></td></tr>
<tr><td colspan="2" align="center">
<input type="submit" value="Register" name="submit" style="background-color: #ccc; height: 30px; width: 100px;" onclick="return validate()"/>
<input type="submit" name="submit" value="Login Page" style="background-color: #ccc; height: 30px; width: 100px;">
</td>
</tr>
</table>
</form>
</body>
</html>