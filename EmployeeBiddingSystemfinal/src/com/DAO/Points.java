package com.DAO;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.vo.Bids;
import com.vo.Employee;
import com.vo.Items;

public class Points {
	public boolean addPoints(String points, int existingPoints, int empId) {
		boolean result = false;
		Statement stmt = null;
		Connection con = null;
		int totalPoints = existingPoints + (Integer.parseInt(points));
		try {
			con = DBConnectivity.connectDB();
			stmt = con.createStatement();
			String sql = "UPDATE employee_details " + "SET points_remaining = "
					+ totalPoints + " WHERE employee_id = " + empId + "";

			stmt.executeUpdate(sql);
			result = true;

		} catch (SQLException se) {
			se.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (stmt != null)
					con.close();
			} catch (SQLException se) {
			}
			try {
				if (con != null)
					con.close();
			} catch (SQLException se) {
				se.printStackTrace();
			}
		}
		return result;

	}

	public boolean removePoints(String points, int existingPoints, int empId) {
		boolean result = false;
		Statement stmt = null;
		Connection con = null;
		try {
			con = DBConnectivity.connectDB();
			stmt = con.createStatement();
			String sql = "UPDATE employee_details " + "SET points_remaining = "
					+ (existingPoints - (Integer.parseInt(points)))
					+ " WHERE employee_id = " + empId + "";
			stmt.executeUpdate(sql);

			result = true;

		} catch (SQLException se) {
			se.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (stmt != null)
					con.close();
			} catch (SQLException se) {
			}
			try {
				if (con != null)
					con.close();
			} catch (SQLException se) {
				se.printStackTrace();
			}
		}
		return result;
	}

	public boolean saveBidDetails(final int employee, final int itemId,
			final int points, final int remianingPoints) {
		boolean queryResult = false;
		Connection con = DBConnectivity.connectDB();
		PreparedStatement ps;
		try {
			ps = con.prepareStatement("insert into employee_fact(employee_id,product_id,points_added) values(?, ?, ?)");
			ps.setInt(1, employee);
			ps.setInt(2, itemId);
			ps.setInt(3, points);
			queryResult = ps.execute();
			removePoints("0", remianingPoints, employee);
			queryResult = true;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return queryResult;
	}

	/**
	 * submitBids
	 * 
	 * @param bidsDetails
	 * @param employee
	 * @return boolean
	 */
	public boolean submitBids(final List<Bids> bidsDetails,
			final Employee employee) {
		boolean queryResult = false;
		Connection con = DBConnectivity.connectDB();
		PreparedStatement ps;
		try {
			ps = con.prepareStatement("insert into employee_fact(employee_id,product_id,points_added) values(?, ?, ?)");
			for (Bids bids : bidsDetails) {
				ps.setInt(1, employee.getEmployeeId());
				ps.setInt(2, bids.getItemId());
				ps.setInt(3, bids.getPoints());
				queryResult = ps.execute();
			}

			removePoints("0", employee.getPointsRemaining(),
					employee.getEmployeeId());
			queryResult = true;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return queryResult;
	}

	/**
	 * getWinnerList()
	 * 
	 * @return HashMap<Employee, Items>
	 */
	public HashMap<Employee, Items> getEmpItemMap() {
		final HashMap<Employee, Items> empItemMap = new HashMap<Employee, Items>();
		Connection con = null;
		Statement stmt = null;
		try {
			con = DBConnectivity.connectDB();
			stmt = con.createStatement();
			String sqlEmpFact = "select p.description, p.item_name , p.id as product_id, sum(f.points_added) as points, d.employee_name, d.employee_id from employeebidding.product_details p join employeebidding.employee_fact f on f.product_id = p.id"
					+ " join employeebidding.employee_details d on f.employee_id = d.employee_id group by employee_id, Product_id";
			final ResultSet resultset = stmt.executeQuery(sqlEmpFact);
			while (resultset.next()) {
				final Items item = new Items();
				final Employee employee = new Employee();
				item.setDescription(resultset.getString("description"));
				item.setItemId(resultset.getInt("product_id"));
				item.setItemName(resultset.getString("item_name"));
				employee.setPointsAchived(resultset.getInt("points"));
				employee.setEmployeeName(resultset.getString("employee_name"));
				employee.setEmployeeId(resultset.getInt("employee_id"));
				empItemMap.put(employee, item);
			}
		} catch (SQLException se) {
			se.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (stmt != null)
					con.close();
			} catch (SQLException se) {
			}
			try {
				if (con != null)
					con.close();
			} catch (SQLException se) {
				se.printStackTrace();
			}
		}
		return empItemMap;
	}

	public ArrayList<String> getCategory() {
		Connection con = null;
		Statement stmt = null;
		final ArrayList<String> categoryList = new ArrayList<String>();
		try {
			con = DBConnectivity.connectDB();
			stmt = con.createStatement();
			String sqlEmpFact = "select *from categoryList";
			ResultSet resultset = stmt.executeQuery(sqlEmpFact);
			while (resultset.next()) {
				categoryList.add(resultset.getString("category"));
				;
			}
		} catch (SQLException se) {
			se.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (stmt != null)
					con.close();
			} catch (SQLException se) {
			}
			try {
				if (con != null)
					con.close();
			} catch (SQLException se) {
				se.printStackTrace();
			}
		}
		return categoryList;
	}

	/**
	 * deleteItems
	 * 
	 * @param delteItems
	 * @return boolean
	 */
	public List<String> deleteItems(String[] delteItems) {
		final List<String> list = new ArrayList<String>();
		Connection con = null;
		Statement stmt = null;
		con = DBConnectivity.connectDB();
		try {
			stmt = con.createStatement();
			for (String id : delteItems) {
				try {
					String sqlEmpFact = "DELETE FROM product_details"
							+ " WHERE id = " + id + "";
					stmt.executeUpdate(sqlEmpFact);
				} catch (SQLException se) {
					se.printStackTrace();
					list.add(id);
				} catch (Exception e) {
					e.printStackTrace();
					list.add(id);
				}
			}
		} catch (SQLException e1) {
			e1.printStackTrace();
		} finally {
			try {
				if (stmt != null)
					con.close();
			} catch (SQLException se) {
			}
			try {
				if (con != null)
					con.close();
			} catch (SQLException se) {
				se.printStackTrace();
			}
		}
		return list;
	}

	/**
	 * deleteCategory
	 * 
	 * @param parameter
	 * @return boolean
	 */
	public boolean deleteCategory(String category) {
		Connection con = null;
		Statement stmt = null;
		boolean returnvalue = false;
		try {
			con = DBConnectivity.connectDB();
			stmt = con.createStatement();
			String sqlEmpFact = "DELETE FROM categoryList"
					+ " WHERE category = '" + category + "'";
			if (stmt.executeUpdate(sqlEmpFact) > 0) {
				returnvalue = true;
			}
		} catch (SQLException se) {
			se.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (stmt != null)
					con.close();
			} catch (SQLException se) {
			}
			try {
				if (con != null)
					con.close();
			} catch (SQLException se) {
				se.printStackTrace();
			}
		}
		return returnvalue;
	}

	/**
	 * updateManageItem
	 * 
	 * @param map
	 * @return boolean
	 */
	public boolean updateManageItem(HashMap<String, HashMap<String, Object>> map) {
		PreparedStatement stmt = null;
		Connection con = null;
		boolean returnValue = false;
		try {
			con = DBConnectivity.connectDB();
			for (String itemId : map.keySet()) {
				if (map.get(itemId).containsKey("ItemImage")) {
					stmt = con
							.prepareStatement("UPDATE product_details "
									+ "SET description = ?, category =?,IMG = ?, item_name = ?  WHERE id = ?");
					stmt.setString(1,
							(String) map.get(itemId).get("Description"));
					stmt.setString(2, (String) map.get(itemId).get("Events"));
					stmt.setBlob(3,
							(InputStream) map.get(itemId).get("ItemImage"));
					stmt.setString(4, (String) map.get(itemId).get("itemName"));
					stmt.setInt(5, Integer.parseInt(itemId));
				} else {
					stmt = con.prepareStatement("UPDATE product_details "
							+ "SET description = ?, category =? WHERE id = ?");
					stmt.setString(1,
							(String) map.get(itemId).get("Description"));
					stmt.setString(2, (String) map.get(itemId).get("Events"));
					stmt.setInt(3, Integer.parseInt(itemId));
				}
				stmt.executeUpdate();
			}

			returnValue = true;
		} catch (SQLException se) {
			se.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (stmt != null)
					con.close();
			} catch (SQLException se) {
			}
			try {
				if (con != null)
					con.close();
			} catch (SQLException se) {
				se.printStackTrace();
			}
		}
		return returnValue;
	}

	/**
	 * updateEmployeeDetails
	 * 
	 * @param remainingPoints
	 * @param superVisorName
	 * @param employeeName
	 * @param i
	 * @return boolean
	 */
	public boolean updateEmployeeDetails(final String remainingPoints,
			final String superVisorName, final String employeeName,
			final int employeeId) {
		boolean result = false;
		Statement stmt = null;
		Connection con = null;
		try {
			con = DBConnectivity.connectDB();
			stmt = con.createStatement();
			String sql = "UPDATE employee_details SET points_remaining = "
					+ remainingPoints + ", employee_name ='" + employeeName
					+ "' WHERE employee_id = " + employeeId + "";
			stmt.executeUpdate(sql);
			
			final ResultSet rs = stmt.executeQuery("select supervisor_id from employee_details where employee_id  ="+ employeeId);
			int supervisorId = 0 ;
			while (rs.next()) {
				supervisorId = rs.getInt("supervisor_id");
				result = true;
			}
			sql = "Update employee_details set employee_name ='"+superVisorName+ "' where employee_id ="+supervisorId;
			stmt.executeUpdate(sql);
		} catch (SQLException se) {
			se.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (stmt != null)
					con.close();
			} catch (SQLException se) {
			}
			try {
				if (con != null)
					con.close();
			} catch (SQLException se) {
				se.printStackTrace();
			}
		}
		return result;
	}
}
