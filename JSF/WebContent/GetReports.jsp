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
    margin-left: 10cm;
	background-color: #cccccc;
	margin-top: 5cm;
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
<a href = "faces/index1.html">Main Page</a>&#160;&#160;&#160;&#160;&#160;&#160;<a href="faces/Logout.jsp">Logout</a>
<br></br>
<a href = "faces/Select.jsp"> Back</a>
<f:view>
<h:panelGrid id="cityreport" rendered="#{cityBean.statusCity}" columns="2">
		<h:outputText value="Total Population:" />
		<h:outputText value="#{cityBean.population}" />
		<h:outputText value="CountryCodes Count:" />
		<h:outputText value="#{cityBean.codeCount}" />	
	</h:panelGrid>
		<h:panelGrid id="countryreport1" rendered="#{countryBean.statusCountry1}" columns="2">
		<h:outputText value="Total Population:" />
		<h:outputText value="#{countryBean.totalPopulation}" />
		<h:outputText value="Total Surface Area:" />
		<h:outputText value="#{countryBean.totalSurfaceArea}" />	
		<h:outputText value="Max LifeExpectancy:" />
		<h:outputText value="#{countryBean.maxLifeExp}" />
		<h:outputText value="Min LifeExpectancy:" />
		<h:outputText value="#{countryBean.minLifeExp}" />
		<h:outputText value="Avg LifeExpectancy:" />
		<h:outputText value="#{countryBean.avgLifeExp}" />
	</h:panelGrid>
		<h:panelGrid id="countryreport2" rendered="#{countryBean.statusCountry2}" columns="2">
		<h:outputText value="Total Population:" />
		<h:outputText value="#{countryBean.totalPopulation}" />
		<h:outputText value="Total Surface Area:" />
		<h:outputText value="#{countryBean.totalSurfaceArea}" />	
		<h:outputText value="MAX GNP:" />
		<h:outputText value="#{countryBean.maxGnp}" />
		<h:outputText value="MIN GNP:" />
		<h:outputText value="#{countryBean.minGnp}" />
		<h:outputText value="AVG GNP:" />
		<h:outputText value="#{countryBean.avgGnp}" />		
	</h:panelGrid>
		<h:panelGrid id="countrylanguagereport" rendered="#{countryLanguageBean.countryLanguageStatus}" columns="2">
		<h:outputText value="Max Percentage:" />
		<h:outputText value="#{countryLanguageBean.maxPercentage}" />
		<h:outputText value="Min Percentage:" />
		<h:outputText value="#{countryLanguageBean.minPercentage}" />
		<h:outputText value="Avg Percentage:" />
		<h:outputText value="#{countryLanguageBean.avgPercentage}" />
	</h:panelGrid>

</f:view>
</body>
</html>
</jsp:root>