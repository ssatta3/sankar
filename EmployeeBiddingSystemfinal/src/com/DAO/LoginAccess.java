package com.DAO;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.vo.Bids;
import com.vo.Employee;
import com.vo.Items;
import com.vo.Supervisor;

public class LoginAccess {
	private Employee empData;

	/**
	 * validateAccess
	 * @param userName
	 * @param password
	 * @return boolean
	 * @throws SQLException
	 */
	public boolean validateAccess(String userName, String password)
			throws SQLException {
		boolean loginResult = false;
		Connection con = DBConnectivity.connectDB();
		PreparedStatement ps;
		try {
			ps = con.prepareStatement("select * from employee_login where employee_id=? and password=?");
			ps.setInt(1, Integer.parseInt(userName));
			ps.setString(2, password);
			ResultSet queryResult = ps.executeQuery();
			loginResult = queryResult.next();
		} catch (SQLException e) {

			e.printStackTrace();
		} finally {
			con.close();
		}

		return loginResult;
	}
	/**
	 * getEmployeeData
	 * @param empId
	 * @return Employee
	 * @throws SQLException
	 */
	public Employee getEmployeeData(String empId) throws SQLException {
		Connection con = DBConnectivity.connectDB();
		Employee empData = new Employee();
		Statement ps;
		String employeeDetailsQuery = " select employee_id,employee_name,points_remaining,supervisor_id"
				+ " from employee_details where employee_id ="
				+ Integer.parseInt(empId) + "";

		try {
			ps = con.createStatement();
			ResultSet rs = ps.executeQuery(employeeDetailsQuery);
			while (rs.next()) {
				empData.setEmployeeName(rs.getString("employee_name"));
				empData.setPointsRemaining(rs.getInt("points_remaining"));
				empData.setSupervisorId(rs.getInt("supervisor_id"));
				empData.setEmployeeId(rs.getInt("employee_id"));
			}
			String supervisorName = "select employee_name from employee_details where employee_id = "+empData.getSupervisorId()+"";
			rs = ps.executeQuery(supervisorName);
			while(rs.next())
			{
				empData.setSupervisorName(rs.getString("employee_name"));
			}
		}
		catch (SQLException e) {

			e.printStackTrace();
		} finally {
			con.close();
		}

		return empData;

	}
	/**
	 * getEmployeeData
	 * @param userName
	 * @param password
	 * @return Employee
	 * @throws SQLException
	 */
	public Employee getEmployeeData(String userName, String password)
			throws SQLException {
		Connection con = DBConnectivity.connectDB();
		List<Bids> itemsList = new ArrayList<Bids>();
		Employee empData = new Employee();
		Statement ps;
		String employeeDetailsQuery = " select employee_id,employee_name,points_Achived,points_remaining,supervisor_id,total_points"
				+ " from employee_details where employee_id in (select employee_id from employee_login where user_name = '"
				+ userName + "' and password = '" + password + "')";

		try {
			ps = con.createStatement();
			ResultSet rs = ps.executeQuery(employeeDetailsQuery);
			while (rs.next()) {
				empData.setEmployeeName(rs.getString("employee_name"));
				empData.setPointsRemaining(rs.getInt("points_remaining"));
				empData.setPointsAchived(rs.getInt("points_Achived"));
				empData.setSupervisorId(rs.getInt("supervisor_id"));
				empData.setTotal_points(rs.getInt("total_points"));
				empData.setEmployeeId(rs.getInt("employee_id"));
			}
			String bidData = "select f.points_added,p.IMG, p.id,p.item_name,p.description from employee_fact f join product_details p"
					+ " on f.product_id = p.id where f.employee_id in (select employee_id from employee_login where user_name = '"
					+ userName + "' and password = '" + password + "')";
			rs = ps.executeQuery(bidData);
			while (rs.next()) {
				Bids getBids = new Bids();
				getBids.setItemName(rs.getString("item_name"));
				getBids.setDescription(rs.getString("description"));
				getBids.setPoints(rs.getInt("points_added"));
				ByteArrayOutputStream baos = new ByteArrayOutputStream();
				byte[] buf = new byte[1024];
				InputStream in = rs.getBinaryStream("IMG");
				int n = 0;
				try {
					while ((n = in.read(buf)) >= 0) {
						baos.write(buf, 0, n);
					}
					in.close();
				} catch (IOException e) {

					e.printStackTrace();
				}
				getBids.setItemImage(baos.toByteArray());
				getBids.setItemId(rs.getInt("id"));
				itemsList.add(getBids);
			}
			empData.setBids(itemsList);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			con.close();
		}

		return empData;

	}

	/**
	 * saveRegistrationDetails
	 * 
	 * @param loginData
	 * @return
	 * @throws SQLException
	 */
	public boolean saveRegistrationDetails(Employee loginData)
			throws SQLException {

		boolean loginResult = false;
		Connection con = DBConnectivity.connectDB();
		try {
			String insertTableSQL = "INSERT INTO employee_login"
					+ "(employee_id, user_name, password) VALUES" + "(?, ?, ?)";
			PreparedStatement preparedStatement = con
					.prepareStatement(insertTableSQL);
			preparedStatement.setInt(1, loginData.getEmployeeId());
			preparedStatement.setString(2, loginData.getUserName());
			preparedStatement.setString(3, loginData.getPassword());
			preparedStatement.executeUpdate();
			loginResult = true;
		} catch (SQLException e) {

			e.printStackTrace();
		} finally {
			con.close();
		}

		return loginResult;

	}

	/**
	 * getSupervisorData
	 * 
	 * @param superVisorId
	 * @return List<Supervisor>
	 */
	public List<Supervisor> getSupervisorData(String superVisorId) {
		Connection con = DBConnectivity.connectDB();
		List<Supervisor> superList = new ArrayList<Supervisor>();
		Statement ps;
		String employeeDetailsQuery = "select employee_id,employee_name,total_points from employee_details where supervisor_id in "
				+"(select employee_id from employee_details where employee_name = '"+superVisorId+"')";
		try {
			ps = con.createStatement();
			ResultSet rs = ps.executeQuery(employeeDetailsQuery);
			while (rs.next()) {
				final Supervisor supData = new Supervisor();
				if(rs.getString("employee_name").equals(superVisorId)){
					supData.setEmployeeId(rs.getInt("employee_id"));
				}
				supData.setEmployeeName(rs.getString("employee_name"));
				supData.setTotalPoints(rs.getInt("total_points"));
				superList.add(supData);
			}
		} catch (SQLException e) {

			e.printStackTrace(); 
		} finally {
			try {
				con.close();
			} catch (SQLException e) {

				e.printStackTrace();
			}
		}

		return superList;

	}

	/**
	 * getProductDetails()
	 * 
	 * @return List<Items>
	 */
	public List<Items> getProductDetails() {
		Connection con = DBConnectivity.connectDB();
		List<Items> productList = new ArrayList<Items>();
		Statement ps;
		String employeeDetailsQuery = "select id,item_name,description,category from product_details";
		try {
			ps = con.createStatement();
			ResultSet rs = ps.executeQuery(employeeDetailsQuery);
			while (rs.next()) {
				final Items itemData = new Items();
				itemData.setCategory(rs.getString("category"));
				itemData.setItemId(rs.getInt("id"));
				itemData.setItemName(rs.getString("item_name"));
				itemData.setDescription(rs.getString("description"));
				productList.add(itemData);
			}
		} catch (SQLException e) {

			e.printStackTrace();
		} finally {
			try {
				con.close();
			} catch (SQLException e) {

				e.printStackTrace();
			}
		}
		return productList;
	}
	
	public List<Bids> getBidDetails(int empId)
	{

		Connection con = DBConnectivity.connectDB();
		List<Bids> itemsList = new ArrayList<Bids>();
		Statement ps = null;
		try{
			ps = con.createStatement();
			String bidData = "select f.points_added,p.IMG, p.id,p.item_name,p.description from employee_fact f join product_details p"
					+ " on f.product_id = p.id where f.employee_id = "+empId+"";
			ResultSet rs = ps.executeQuery(bidData);
			while (rs.next()) {
				Bids getBids = new Bids();
				getBids.setItemName(rs.getString("item_name"));
				getBids.setDescription(rs.getString("description"));
				getBids.setPoints(rs.getInt("points_added"));
				ByteArrayOutputStream baos = new ByteArrayOutputStream();
				byte[] buf = new byte[1024];
				InputStream in = rs.getBinaryStream("IMG");
				int n = 0;
				try {
					while ((n = in.read(buf)) >= 0) {
						baos.write(buf, 0, n);
					}
					in.close();
				} catch (IOException e) {

					e.printStackTrace();
				}
				getBids.setItemImage(baos.toByteArray());
				getBids.setItemId(rs.getInt("id"));
				itemsList.add(getBids);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return itemsList;

	
	}
	public boolean setDate(String date) 
	{

		boolean loginResult = false;
		Connection con = DBConnectivity.connectDB();
		try {
			String insertTableSQL = "INSERT INTO browser_end_date"
					+ "(end_Date) VALUES" + "(?)";
			PreparedStatement preparedStatement = con
					.prepareStatement(insertTableSQL);
			preparedStatement.setString(1, date);
			preparedStatement.executeUpdate();
			loginResult = true;
		} catch (SQLException e) {

			e.printStackTrace();
		} finally {
			try {
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return loginResult;
		
	}
	
	public String getDate()
	{
		String date = null;
		Connection con = DBConnectivity.connectDB();
		Statement ps;
		String employeeDetailsQuery = "select end_Date from employeebidding.browser_end_date where id = "
+"(select max(id) from employeebidding.browser_end_date);";

		try {
			ps = con.createStatement();
			ResultSet rs = ps.executeQuery(employeeDetailsQuery);
			while (rs.next()) {
				date = rs.getString("end_Date");
			}
		}
		catch (SQLException e) {

			e.printStackTrace();
		} finally {
			try {
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return date;

	
	}
}
