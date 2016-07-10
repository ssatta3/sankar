package com.DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnectivity 

{
	   static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
	   static final String DB_URL = "jdbc:mysql://localhost:3306/employeebidding";

	   static final String USER = "root";
	   static final String PASS = "hyderabad";
	   
	   public static Connection connectDB() {
		   Connection conn = null;
		   try{
			      Class.forName("com.mysql.jdbc.Driver");
			      System.out.println("Connecting to database...");
			      conn = DriverManager.getConnection(DB_URL,USER,PASS);
	   }
		   catch(SQLException sqlexc){
			      sqlexc.printStackTrace();
			   }catch(Exception e){
			      e.printStackTrace();
			   }
	 return conn;
	  
	   }

}
