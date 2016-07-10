<%@page import="java.util.ArrayList"%>
<%@page import="com.vo.Items"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>ManageItems</title>
<style type="text/css">
tr {
    border-bottom: 1px solid black;
    border-top: 1px solid black;
    border-collapse: collapse;
}​
</style>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/layout.css" media="screen"/>
</head>
<script type="text/javascript">
function showAlertMessage(){
	alert("Image won't update in front end, it will update in back end ones you saved");
}
function validatecategory(){
	var cat = document.getElementsByName("addedCategory")[0].value;
	if((cat != null & cat.length==0)|| cat == null){
		alert("Enter category name");
		return false;
	}
}
</script>
<body>
<div><jsp:include page="../CommonHeader/header.jsp" /></div><br><br><br>
<form action="ManageAccountServlet" method="post" enctype="multipart/form-data">
<%if(request.getAttribute("manageErrorValue") != null) { %>
	<span style="color: red;"> <%=request.getAttribute("manageErrorValue") %></span>
<%} %>
<h1>Manage Items</h1>
<span style="background: #ccc; padding-right: 100%; size: 48;padding-top: 5px; padding-bottom: 5px;">Items</span><br/><br/>
<table>
<%final List<Items> list= (List<Items>)request.getAttribute("ProductDetails");
  final ArrayList<String> categoryList = (ArrayList<String>)request.getAttribute("categoryList");
  for(Items item : list){%>
	  <tr><td><img src="${pageContext.request.contextPath}/partials/getImageDetails.jsp?imag_id=<%=item.getItemId()%>" height="150" width="200"/></td>
	  <td><%=item.getItemName()+"#"+item.getItemId() %><br/>
	  Description: <input type="text" name ="Description:<%=item.getItemId()%>" value="<%=item.getDescription() %>"><br/>
	  Category   :  <select name="Events:<%=item.getItemId()%>" id="Events">
		<%for(String category : categoryList)
		{
			if(category.equals(item.getCategory())){
				%>
				<option value="<%=category%>" selected> <%=category%>  </option>
				<%
			}else{
		%>
		<option value="<%=category%>"> <%=category%>  </option>
		<%}
		}%>
		</select><br/>
		<span>Upload New Image..</span> <input type="file" name="ItemImage:<%=item.getItemId() %>" onclick="showAlertMessage()" size="50"/>
	  </td>
	  <td><input type="checkbox" value="<%=item.getItemId()%>" name="deleteItemCheck"></td>
	  </tr>
	  <%
  }%>
  <tr><td align="right" colspan="3"><input type="submit" name="submit" class="input-rounded-button" value="Delete"> </td></tr>
</table><br/>
<span style="background: #ccc; padding-right: 100%; size: 48;padding-top: 5px; padding-bottom: 5px; white-space: nowrap;">Add/Delete Category</span><br/><br/>
<table>
<% for(String category : categoryList){%>
<tr><td><%=category %> </td><td><a href="${pageContext.request.contextPath}/ManageAccountServlet?submit=hrefDelete&categoryName=<%=category %>" accesskey="1" title="">Delete</a></td>
<%} %>
<tr><td><input type="text" name="addedCategory"> </td><td> <input type="submit" class="input-rounded-button" name="submit" value="Add" onclick="return validatecategory()"></td></tr>
</table>
<div align="right"><input type="submit" name="submit" class="input-rounded-button" value="save"> </div>
</form>
</body>
</html>