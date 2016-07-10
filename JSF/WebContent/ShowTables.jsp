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
<style type="text/css">
body {
	background-color: #cccccc;
}

#headerAlignment {
	text-align: center;
	font-size: 40px
}

#labelAlignment {
	width: 0px;
	height: 10px
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
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1" />
<title>Insert title here</title>
</head>
<body>
<f:view>
<center>
<br></br>
<a href="faces/index1.html">Main Page</a>&#160;&#160;&#160;&#160;&#160;&#160;<a href="faces/Logout.jsp">Logout</a>
<br></br><br></br><br></br><br></br><br></br><br></br>
<h:form>
<h:outputText value="SelectDatabase"/>
<h:selectOneListbox id="Selectdatabases" value = "#{actionTableBean.databaseId}">
                    <f:selectItem itemValue="1" itemLabel="world" />
					<f:selectItem itemValue="2" itemLabel="s16g40" />
</h:selectOneListbox>
<h:outputText value="Selecttable"/>
<h:selectOneListbox id="SelectTable" value="#{actionShowTableBean.tableId}">
<f:selectItem itemValue="1" itemLabel="city"/>
<f:selectItem itemValue="2" itemLabel="country" />
<f:selectItem itemValue="3" itemLabel="countrylanguage"/>
</h:selectOneListbox>
<h:commandButton id= "submit" value="show" action= "#{actionShowTableBean.getTable}"/>
</h:form>
<br>
</br>
<h:dataTable value="#{actionShowTableBean.cityBeanList}" var="rownumber"
  border="1" cellspacing="0" cellpadding="1" rendered="#{cityBean.statusCity}">
   <h:column>    				
      <f:facet name="header">  
      <h:outputText value = "Name"/>
      </f:facet>    				
      <h:outputText value="#{rownumber.name}"/>
   </h:column>
   <h:column>
      <f:facet name="header">
      <h:outputText value = "Code"/>
       </f:facet>
      <h:outputText value="#{rownumber.cityCode1}"/>
   </h:column>
   <h:column>
      <f:facet name="header">
  <h:outputText value = "District"/>
</f:facet>
      <h:outputText value="#{rownumber.district}"/>
   </h:column>
   <h:column>
      <f:facet name="header">
    <h:outputText value = "Population"/>
     </f:facet>
     <h:outputText value= "#{rownumber.population}"/>
   </h:column>
</h:dataTable>


<h:dataTable value="#{actionShowTableBean.countryLanguageBeanList}" var="rownumber"
  border="1" cellspacing="0" cellpadding="1" rendered="#{countryLanguageBean.countryLanguageStatus}">
   <h:column>    				
      <f:facet name="header">  
      <h:outputText value = "code"/>
      </f:facet>    				
      <h:outputText value="#{rownumber.countryCode}"/>
   </h:column>
   <h:column>
      <f:facet name="header">
      <h:outputText value = "language"/>
       </f:facet>
      <h:outputText value="#{rownumber.language}"/>
   </h:column>
   <h:column>
      <f:facet name="header">
  <h:outputText value = "isofficial"/>
</f:facet>
      <h:outputText value="#{rownumber.isOfficial}"/>
   </h:column>
   <h:column>
      <f:facet name="header">
    <h:outputText value = "percentage"/>
     </f:facet>
     <h:outputText value= "#{rownumber.percentage}"/>
   </h:column>
</h:dataTable>







<h:dataTable value="#{actionShowTableBean.countryBeanList}" var="rownumber"
  border="1" cellspacing="0" cellpadding="1" rendered="#{countryBean.statusCountry1}">
   <h:column>    				
      <f:facet name="header">  
      <h:outputText value = "Code"/>
      </f:facet>    				
      <h:outputText value="#{rownumber.code}"/>
   </h:column>
   <h:column>
      <f:facet name="header">
      <h:outputText value = "Name"/>
       </f:facet>
      <h:outputText value="#{rownumber.name}"/>
   </h:column>
   <h:column>
      <f:facet name="header">
  <h:outputText value = "Continent"/>
</f:facet>
      <h:outputText value="#{rownumber.continent}"/>
   </h:column>
   <h:column>
      <f:facet name="header">
    <h:outputText value = "Region"/>
     </f:facet>
     <h:outputText value= "#{rownumber.region}"/>
   </h:column>
    <h:column>
      <f:facet name="header">
    <h:outputText value = "SurfaceArea"/>
     </f:facet>
     <h:outputText value= "#{rownumber.surfaceArea}"/>
   </h:column>
     <h:column>
      <f:facet name="header">
    <h:outputText value = "IndepYear"/>
     </f:facet>
     <h:outputText value= "#{rownumber.indepYear}"/>
   </h:column>
     <h:column>
      <f:facet name="header">
    <h:outputText value = "population"/>
     </f:facet>
     <h:outputText value= "#{rownumber.population}"/>
   </h:column>
    <h:column>
      <f:facet name="header">
    <h:outputText value = "lifeexpectancy"/>
     </f:facet>
     <h:outputText value= "#{rownumber.lifeExpectancy}"/>
   </h:column>
     <h:column>
      <f:facet name="header">
    <h:outputText value = "GNP"/>
     </f:facet>
     <h:outputText value= "#{rownumber.gnp}"/>
     </h:column>
       <h:column>
      <f:facet name="header">
    <h:outputText value = "GnpOld"/>
     </f:facet>
     <h:outputText value= "#{rownumber.gnpOld}"/>
   </h:column>
    <h:column>
      <f:facet name="header">
    <h:outputText value = "LocalName"/>
     </f:facet>
     <h:outputText value= "#{rownumber.localName}"/>
   </h:column>
    <h:column>
      <f:facet name="header">
    <h:outputText value = "GovtForm"/>
     </f:facet>
     <h:outputText value= "#{rownumber.govtForm}"/>
   </h:column>
    <h:column>
      <f:facet name="header">
    <h:outputText value = "HeadOfState"/>
     </f:facet>
     <h:outputText value= "#{rownumber.headOfState}"/>
   </h:column>
    <h:column>
      <f:facet name="header">
    <h:outputText value = "capital"/>
     </f:facet>
     <h:outputText value= "#{rownumber.capital}"/>
   </h:column>
    <h:column>
      <f:facet name="header">
    <h:outputText value = "Code2"/>
     </f:facet>
     <h:outputText value= "#{rownumber.code2}"/>
   </h:column>
</h:dataTable>

</center>
</f:view>
</body>
</html>
</jsp:root>