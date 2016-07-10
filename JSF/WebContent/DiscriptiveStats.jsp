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

<f:view>
<center>
<a href="faces/index1.html">Main Page</a>&#160;&#160;&#160;&#160;&#160;&#160;<a href="faces/Logout.jsp">Logout</a>
<br> </br>
<br> </br>
<h:form id="userForm">
<h:outputText value="Select-Database"/>
<h:selectOneListbox id="Selectdatabases" value = "#{selectionBean.databaseId}">
                    <f:selectItem itemValue="1" itemLabel="world" />
					<f:selectItem itemValue="2" itemLabel="s16g40" />
</h:selectOneListbox>
<br> </br>
<p>    </p>
<h:outputText value="Select-Table"/>
<h:selectOneListbox id="SelectTables" value="#{selectionBean.tableId}" 
                   >
      
<f:selectItem itemValue="1" itemLabel="city"/>
<f:selectItem itemValue="2" itemLabel="country" />
<f:selectItem itemValue="3" itemLabel="countrylanguage"/>
</h:selectOneListbox>
<h:commandButton id= "submit5" value="show-columns" action= "#{actionTableBean.displayColumns}" />



<br></br>

<h:selectOneListbox id = "selectcolumns" value = "#{actionTableBean.columnName}" >

 <f:selectItems value="#{actionTableBean.columnList}" />
 <br></br>
</h:selectOneListbox>
<h:commandButton  id= "submit1" value="Get-Stats" action= "#{actionTableBean.getStatistics}"/> 
</h:form>
<br></br>
<h:outputText value = "#{actionTableBean.message}" />
<br></br>
<h:dataTable value="#{actionTableBean.sb}" var="rownumber"
  border="1" cellspacing="0" cellpadding="1" rendered = "#{actionTableBean.displayStatsBoolean}">
   <h:column>    				
      <f:facet name="header">  
      <h:outputText value = "mean: "/>
      </f:facet>    				
      <h:outputText value="#{rownumber.mean}"/>
   </h:column>
   <h:column>
      <f:facet name="header">
      <h:outputText value = "min: "/>
       </f:facet>
      <h:outputText value="#{rownumber.min}"/>
   </h:column>
   <h:column>
      <f:facet name="header">
  <h:outputText value = "Max: "/>
</f:facet>
      <h:outputText value="#{rownumber.max}"/>
   </h:column>
   <h:column>
      <f:facet name="header">
    <h:outputText value = "q1: "/>
     </f:facet>
     <h:outputText value= "#{rownumber.q1}"/>
   </h:column>
   <h:column>
      <f:facet name="header">
    <h:outputText value = "q2: "/>
     </f:facet>
     <h:outputText value= "#{rownumber.q2}"/>
   </h:column>
      <h:column>
      <f:facet name="header">
    <h:outputText value = "stddev: "/>
     </f:facet>
     <h:outputText value= "#{rownumber.stddev}"/>
   </h:column>
</h:dataTable>
</center>
</f:view>
</body>
</html>
</jsp:root>