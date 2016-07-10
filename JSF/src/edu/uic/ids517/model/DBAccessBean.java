package edu.uic.ids517.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;


@ManagedBean(name="dbAccess")
@SessionScoped
public class DBAccessBean {
private String dbms;
private String schema;
private String hostUrl;
private String userName;
private String password;
private Connection connection;
private ResultSet resultSet;
private Statement stmt;
private String port;
private String url;
@PostConstruct
public void init(){
	dbms = "MYSQL";
	userName = "s16g40";
	password = "s16g40FpqU5";
	schema = "world";
	port = "3306";
	hostUrl  = "131.193.209.54";
}
public DBAccessBean(){
	dbms = "MYSQL";
	userName = "s16g40";
	password = "s16g40FpqU5";
	schema = "world";
	port = "3306";
	hostUrl  = "131.193.209.54";
}
public String getPassword() {
	return password;
}
public void setPassword(String password) {
	this.password = password;
}
public String getUserName() {
	return userName;
}
public void setUserName(String userName) {
	this.userName = userName;
}
public String getUrl() {
	return hostUrl;
}
public void setUrl(String url) {
	this.hostUrl = url;
}
public String getSchema() {
	return schema;
}
public void setSchema(String schema) {
	this.schema = schema;
}
public String getDbms() {
	return dbms;
}
public void setDbms(String dbms) {
	this.dbms = dbms;
}
public Connection getConnection() {
	return connection;
}
public void setConnection(Connection connection) {
	this.connection = connection;
}
public ResultSet getResultSet() {
	return resultSet;
}
public void setResultSet(ResultSet resultSet) {
	this.resultSet = resultSet;
}
public Statement getStmt() {
	return stmt;
}
public void setStmt(Statement stmt) {
	this.stmt = stmt;
}

public String connectToDb(){
	String status;
	String driver;
	switch(dbms.toUpperCase()){
	case "MYSQL": driver = "com.mysql.jdbc.Driver";
	              url = "jdbc:"+ dbms.toLowerCase()+"://"+hostUrl+":"+port+"/"+schema;
	              break;
	case "DB2": driver = "com.ibm.db2.jcc.DB2Driver";
	            url = "jdbc:"+dbms.toLowerCase()+"://"+hostUrl+":"+port+"/"+schema;
	            break;
	case "ORACLE": driver = "oracle.jdbc.driver.OracleDriver";
	               url = "jdbc:"+dbms.toLowerCase()+":@"+hostUrl+":"+port+":"+schema;
	               break;
	               
	default: url = null;
	         driver = null;
	}
	try{
		Class.forName(driver);
		connection = DriverManager.getConnection(url, userName, password);
		status = "success";
	}catch(ClassNotFoundException e){
		status = "fail";
		e.printStackTrace();
	}catch(NullPointerException ex){
		status = "fail";
		ex.printStackTrace();
	} catch (SQLException e) {
		status = "fail";
		e.printStackTrace();
	}catch(Exception e){
		status = "Fail";
		e.printStackTrace();
	}
	return status;
}
   public String executeQuery(String query){
	   String status = "success";
	   System.out.println("before connection if");
	   if(connection == null){
		   System.out.println("inside execute query before connection");
		   connectToDb();
	   }
	   try {
		stmt  =  connection.createStatement();
		System.out.println("stsmt created");
	} catch (SQLException e) {
		e.printStackTrace();
	}
	   try{
		   resultSet = stmt.executeQuery(query);
	   }catch(SQLException e){
		   status = "fail";
		   e.printStackTrace();
	   }
	   return status;
   }
   public List<List<String>> getData(String sqlQuery) 
	{
		List<String> dataList ;
		List<List<String>> data = new ArrayList<List<String>>();
		MessageBean message = new MessageBean();
		ResultSet rs;
		ResultSetMetaData resultSetMetaData;

		try
		{
		    this.executeQuery(sqlQuery);
		    rs = this.getResultSet();
			resultSetMetaData = rs.getMetaData();
			int numofcoloumns=resultSetMetaData.getColumnCount();
			System.out.println("Coloumn count : "+ numofcoloumns);
			
			while(rs.next()){
				dataList=new ArrayList<String>();
				for (int i=1;i<=numofcoloumns;i++){
					
					
					dataList.add(rs.getString(i));
				}

				data.add(dataList);
				
				/*dataList.add(rs.getString(4));*/
			}

		}
		catch(SQLException sqlExp){
			
		
			return data;
		}
		catch(Exception exp){
			System.out.println("Exception ");
			
			return data;
		}
		
		return data;
	}
   
   
   
   
}
