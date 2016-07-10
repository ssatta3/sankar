<%@page import="java.util.ArrayList"%>
<%@page import="com.vo.Items"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Employee Home</title>
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.4/jquery.min.js"></script>
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/layout.css" media="screen"/> 
</head>
<script type="text/javascript">
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
<div><jsp:include page="../CommonHeader/header.jsp" /></div><br>
<form action="ManageAccountServlet" method="post">
<%if(request.getAttribute("ErrorMesage") != null) {%>
<span  style="color: red;"><%=request.getAttribute("ErrorMesage") %></span>
<%} %>
	<input type="hidden" name="pointRemaining" value="<%=(Integer)session.getAttribute("point_remaining")%>">
		
			<%
			int heighestItemId = 0;
			final List<Items> items = (List<Items>)request.getAttribute("ProductDetails");
			final ArrayList<String> categoryList = (ArrayList<String>)request.getAttribute("categoryList");
			for(String category : categoryList)
			{
			%>
			<div class="product_container">
			<div class="product_category">
				<h3><%=category %></h3>
			</div>
			<%for  (Items item : items) {
				if(item.getCategory().equals(category)) {
			%>
			<div class="product">
				<div class="left_container">
					<div class="item_img"><img src="${pageContext.request.contextPath}/partials/getImageDetails.jsp?imag_id=<%=item.getItemId()%>" height="150"/></div>
					<div class="item_amount">
						<span>Bid Amount : </span>
						<input type="text" id="item1" name="BidAmount<%=item.getItemId()%>" onchange="employeeRemainingPoints(this)" onkeypress="return isNumber(event)"/>
					</div>
				</div>
				<div class="right_container">
					<p>Item <%=item.getItemName() %></p>
					<p>Description <%=item.getDescription()%></p>
				</div>
			</div>
			<%if(heighestItemId < item.getItemId()){
				heighestItemId = item.getItemId();
			}
			} 
			}%>
			</div>
			<div class="clear"></div>
			<%}%>
		<input type="hidden" name="heighestItemId" value="<%=heighestItemId %>">
		<div class="footer" >
			<div class="print">
				<p>Print</p>
				<button name="submit" value="Print"  onClick="window.print()"><img src="${pageContext.request.contextPath}/Images/printer.png"></button>
			</div>
			<div class="submit" style="padding-right: 10em;"><input type="submit" class="input-rounded-button" name="submit" value="Submit Bids" style="width: inherit;" onclick="return validateHome()"/></div>
			<div class="clear"></div>
			<p class="notice">Notice: Bids are final after submission</p>
		</div>
</form>
</body>
</html>