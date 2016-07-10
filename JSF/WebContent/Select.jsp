<?xml version="1.0" encoding="ISO-8859-1" ?>
<jsp:root xmlns:jsp="http://java.sun.com/JSP/Page" xmlns:f="http://java.sun.com/jsf/core" xmlns:h="http://java.sun.com/jsf/html" version="2.0">
    <jsp:directive.page language="java"
        contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" />
    <jsp:text>
        <![CDATA[ <?xml version="1.0" encoding="ISO-8859-1" ?> ]]>
    </jsp:text>
    <jsp:text>
        <![CDATA[ <!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd"> ]]>
    </jsp:text>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1" />
<title>Insert title here</title>
<style type="text/css">
body {
	background-color: #cccccc;
	margin-top: 4cm;
	margin-left: 10cm;
}

#headerAlignment {
    margin-top: 300px;
	text-align: center;
	font-size: 40px
}

#labelAlignment {
	width: 0px;
	height: 10px
	margin-top: 300px
}

#labelAlignment1 {
	text-align: center;
	font-size: 20px
}

#textBoxAlignment {
	width: 362px;
	height: 0px;
	margin-bottom: 10px;
}
</style>

<title>S16G40 Project</title>
</head>
<body>
<f:view>
<br></br>
<p>    </p><a href = "faces/index1.html">Main Page</a>&#160;&#160;&#160;&#160;&#160;&#160;<a href="faces/Logout.jsp">Logout</a>
<p> Select the report to be displayed </p>

<br></br>
<h:form>

<h:selectOneMenu id="selectreport" value="#{actionReportBean.reportId}">
   	<f:selectItem itemValue="1" itemLabel="CityReport"/>
   	<f:selectItem itemValue="2" itemLabel="Country-Life-Expectancy"/>
   	<f:selectItem itemValue="3" itemLabel="Country-Gnp" />
    <f:selectItem itemValue="4" itemLabel="CountryLanguage"/>
    
</h:selectOneMenu>
<p>              </p><h:commandButton id= "submit" value="submit" action= "#{actionReportBean.directToReport}"/>
</h:form>
</f:view>
</body>
</html>
</jsp:root>