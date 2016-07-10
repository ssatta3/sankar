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
	margin-left: 4cm;
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
</head>
<body>
<br></br>
<a href= "faces/index1.html">Main Page</a>&#160;&#160;&#160;&#160;&#160;&#160;<a href="faces/Logout.jsp">Logout</a>
<br></br><br></br>
<f:view>
<h:form id="userForm">
<h:outputText value="SelectDatabase"/>
<h:selectOneListbox id="Selectdatabases" value = "#{selectionBean.databaseId}">
                    <f:selectItem itemValue="1" itemLabel="world" />
					<f:selectItem itemValue="2" itemLabel="s16g40" />
</h:selectOneListbox>
<br> </br>
<h:outputText value="Selecttable"/>
<h:selectOneListbox id="SelectTables" value="#{selectionBean.tableId}" 
                   >
      
<f:selectItem itemValue="1" itemLabel="city"/>
<f:selectItem itemValue="2" itemLabel="country" />
<f:selectItem itemValue="3" itemLabel="countrylanguage"/>
</h:selectOneListbox>
<h:commandButton id= "submit5" value="show" action= "#{actionTableBean.displayColumns}" />



<br></br>

<h:selectManyListbox id = "selectcolumns" value = "#{actionChartBean.columnNameList}" >

 <f:selectItems value="#{actionTableBean.columnList}" />
</h:selectManyListbox>
<br></br>
<h:selectManyListbox id = "typeofchart" value = "#{actionChartBean.chartName}">
 <f:selectItem itemValue="Pie" itemLabel="Pie"/>
<f:selectItem itemValue="Bar" itemLabel="Bar" />
<f:selectItem itemValue="TimeSeries" itemLabel="TimeSeries"/>
</h:selectManyListbox>

<h:commandButton id = "submit2" value = "charts" action = "#{actionChartBean.getChart}"/>
<h:panelGrid columns="2" id="charts"
				style="background-color: Beige; border-bottom-style: solid; border-topstyle:
solid; border-left-style: solid; border-right-style: solid">
				<h:outputText value="#{actionChartBean.pieChartFile}"
					rendered="#{actionChartBean.rendered}" />
				<h:graphicImage value="#{actionChartBean.pieChartFile}" height="300"
					width="400" rendered="#{actionChartBean.rendered}" alt="piChart" />
				<h:outputText value="#{actionChartBean.barChartFile}"
					rendered="#{actionChartBean.rendered}" />
				<h:graphicImage value="#{actionChartBean.barChartFile}" height="300"
					width="400" rendered="#{actionChartBean.rendered}" alt="barChart" />
				<h:outputText value="#{actionChartBean.timeSeriesChartFile}"
					rendered="#{actionChartBean.rendered}" />
				<h:graphicImage value="#{actionChartBean.timeSeriesChartFile}"
					height="300" width="400" rendered="#{actionChartBean.rendered}"
					alt="timeSeriesChart" />
				
			</h:panelGrid>
</h:form>
</f:view>
</body>
</html>
</jsp:root>