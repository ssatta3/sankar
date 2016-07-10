<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Employee Login</title>
</head>
<script>
	function validate(){
		var username = document.getElementsByName("username")[0].value;
		var pass = document.getElementsByName("userpass")[0].value;
		var returnValue = false;
		if(username.length < 2){
			alert(username);
			alert("Please enter Employee ID correctly");
		}else if(pass.length < 1){
			alert("please enter the password correctly");
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
<center><span  style="color: red;"><%=request.getAttribute("ErrorMesage") %></span></center>
<%} %>
<table align="center" cellspacing="5">
 <tr>
<td>Employee ID:</td>
</tr>

<tr><td><input type="text" name="username" onkeypress="return isNumber(event)"/></td></tr>
<tr><td> 
Password:
</td></tr>
<tr><td><input type="password" name="userpass"/></td></tr>
<tr><td>
<input type="submit" value="Login" name="submit" onclick="return validate()"/>
<input type="submit" value="Registration" name="submit"/></td>
<td><input type ="reset" value ="Reset"></td></tr>
</table>  
</form>
</body>
</html>