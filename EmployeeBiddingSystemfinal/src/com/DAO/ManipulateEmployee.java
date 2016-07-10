package com.DAO;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.http.Part;

import com.vo.Employee;

public class ManipulateEmployee 
{
	public int getRemainingPoints(final int employeeId){
		int points = 0;
		Connection con = null;
		Statement stmt = null;
		try{
			con = DBConnectivity.connectDB();
			stmt = con.createStatement();
			String sqlEmpFact = "SELECT POINTS_REMAINING FROM employee_details" +
					" WHERE employee_id = "+employeeId;
			ResultSet resultset = stmt.executeQuery(sqlEmpFact);
			while (resultset.next()) {
				points = resultset.getInt("POINTS_REMAINING");
			}
		}
		catch(SQLException se){
			se.printStackTrace();
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			try{
				if(stmt!=null)
					con.close();
			}catch(SQLException se){
			}
			try{
				if(con!=null)
					con.close();
			}catch(SQLException se){
				se.printStackTrace();
			}
		}
		return points;
		
	}
	public boolean deleteEmp(int empId)
	{
		boolean result = false;
		Connection con = null;
		Statement stmt = null;
		try{
			con = DBConnectivity.connectDB();
			stmt = con.createStatement();
			String sqlEmpFact = "DELETE FROM employee_fact" +
					" WHERE employee_id = "+empId+"";
			String sqlempdetails = "DELETE FROM employee_details" +
					" WHERE employee_id = "+empId+"";
			stmt.executeUpdate(sqlEmpFact);
			stmt.executeUpdate(sqlempdetails);
			result = true;
		}
		catch(SQLException se){
			se.printStackTrace();
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			try{
				if(stmt!=null)
					con.close();
			}catch(SQLException se){
			}
			try{
				if(con!=null)
					con.close();
			}catch(SQLException se){
				se.printStackTrace();
			}
		}
		return result;
	}
	public boolean addEmployee(Employee emp)
	{
		boolean addemp = false;
		Connection con = DBConnectivity.connectDB();
		PreparedStatement preparedStatement = null;
		Statement stmt = null;
		try {
			String	insertTableSQL = "INSERT INTO employee_details"
						+ "(employee_id, employee_name,points_achived,points_remaining,supervisor_id,total_points)"+
						"VALUES (?,?,?,?,?,?)";
				preparedStatement = con.prepareStatement(insertTableSQL);
				preparedStatement.setInt(1,emp.getEmployeeId());  
				preparedStatement.setString(2,emp.getEmployeeName());
				preparedStatement.setInt(3, 0);
				preparedStatement.setInt(4, emp.getPointsRemaining());
				preparedStatement.setInt(5, emp.getSupervisorId());
				preparedStatement.setInt(6, emp.getPointsRemaining());
				int result = preparedStatement .executeUpdate();
				if(result > 0){
					addemp =true;
				}
			}
		
		catch(SQLException se){
			se.printStackTrace();
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			try{
				if(preparedStatement!=null)
					con.close();
			}catch(SQLException se){
			}
			try{
				if(con!=null)
					con.close();
			}catch(SQLException se){
				se.printStackTrace();
			}
		}
		return addemp;
	}
	/**
	 * used for adding items into db
	 * @param events
	 * @param description
	 * @param inputStream
	 * @param string 
	 */
	public boolean addItem(final String category, final String description, final InputStream inputStream, final String itemName) {
		boolean addemp = false;
		Connection con = DBConnectivity.connectDB();
		PreparedStatement preparedStatement = null;
		try {
			String insertTableSQL = "INSERT INTO product_details"
					+ "(item_name, description, category, IMG)"+
					"VALUES (?, ?, ?, ?)";
			preparedStatement = con.prepareStatement(insertTableSQL);
			preparedStatement.setString(1,itemName);  
			preparedStatement.setString(2,description);
			preparedStatement.setString(3, category);
			preparedStatement.setBlob(4, inputStream);
			int result = preparedStatement .executeUpdate();
			if(result > 0){
				addemp =true;
			}
		}
		catch(SQLException se){
			se.printStackTrace();
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			try{
				if(preparedStatement!=null)
					con.close();
			}catch(SQLException se){
			}
			try{
				if(con!=null)
					con.close();
			}catch(SQLException se){
				se.printStackTrace();
			}
	}
		return addemp;
}
	/**
	 * addCategory
	 * @param category
	 * @return boolean
	 */
	public boolean addCategory(final String category) {
		boolean addemp = false;
		Connection con = DBConnectivity.connectDB();
		PreparedStatement preparedStatement = null;
		try {
			String insertTableSQL = "INSERT INTO categoryList"
					+ "(category)"+
					"VALUES (?)";
			preparedStatement = con.prepareStatement(insertTableSQL);
			preparedStatement.setString(1,category); 
			int result = preparedStatement .executeUpdate();
			if(result > 0){
				addemp =true;
			}
		}
		catch(SQLException se){
			se.printStackTrace();
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			try{
				if(preparedStatement!=null)
					con.close();
			}catch(SQLException se){
			}
			try{
				if(con!=null)
					con.close();
			}catch(SQLException se){
				se.printStackTrace();
			}
	}
		return addemp;
		
	}
}

