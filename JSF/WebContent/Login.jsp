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
<title>Login Page</title>
<style type="text/css">
body {
	background-color: #dddddd;
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
	margin-bottom: 50px;
}
</style>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1" />
<title>Insert title here</title>
</head>
<body>
<f:view>
<h:form>
			<center>
				<h3>IDS517 S16G40 JSF Application</h3>
				<a href="index.jsp">Home</a>
			</center>
	
			<center>
			<h:panelGrid columns="2" >
				<h:outputText value="User name:"/>
				<h:inputText id="userName" value="#{actionBeanLoginLogout.userName}" />
				<h:outputText value="Password:" />
				<h:inputSecret id="password" value="#{actionBeanLoginLogout.password}" />
				<h:outputText value="Host:" />
				<h:selectOneListbox value="#{actionBeanLoginLogout.host}" size="1">
					<f:selectItem itemValue="131.193.209.54" itemLabel="54" />
					<f:selectItem itemValue="131.193.209.57" itemLabel="57" />
					<f:selectItem itemValue="localhost" />
				</h:selectOneListbox>
				<h:outputText value="RDBMS:" />
				<h:selectOneListbox value="#{actionBeanLoginLogout.dbms}" size="1">
					<f:selectItem itemValue="mysql" />
					<f:selectItem itemValue="DB2" />
					<f:selectItem itemValue="Oracle" />
				</h:selectOneListbox>
				<h:outputText value="database schema:" />
				<h:inputText id="databaseSchema"
					value="#{actionBeanLoginLogout.dbSchema}" />
				<h:outputText value="" />
				<h:outputText value="#{messageBean.message}" />
				<h:outputText value="" />
				<h:commandButton type="submit" value="Login"
					action="#{actionBeanLoginLogout.validate}" />
			</h:panelGrid>
			</center>
		</h:form>
		<br></br><br></br>
		<br></br><br></br>
			&#160;&#160;&#160;&#160;&#160;&#160;<center><a href ="HelpDocument.pdf">Help Doc</a></center>
</f:view>
</body>
</html>
</jsp:root>