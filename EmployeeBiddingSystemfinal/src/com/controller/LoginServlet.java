package com.controller;

import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import com.BO.AlgoLogic;
import com.BO.DataReader;
import com.DAO.LoginAccess;
import com.DAO.ManipulateEmployee;
import com.DAO.Points;
import com.constants.Constants;
import com.manipulate.ManipulateClass;
import com.vo.Bids;
import com.vo.Employee;
import com.vo.Supervisor;



/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginServlet")
@MultipartConfig(maxFileSize = 16177215) 
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Employee emp;
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		
		String accessHandler = request.getParameter("submit");
		AlgoLogic getDate = new AlgoLogic();
	Long days = getDate.calDaysRemaining();
		request.setAttribute("daysRemaining", days);
		 String userName = request.getParameter("username");
		final Employee loginData = new Employee();
		HttpSession session = request.getSession();
		loginData.setUserName(userName);
		RequestDispatcher rd = null;
		final LoginAccess access = new LoginAccess();
		Employee empData;
		try {
			if(accessHandler.equals(Constants.REGISTER)){
			    userName = request.getParameter("username");
				loginData.setUserName(userName);
				final String registration = request.getParameter("employeeId");
				loginData.setEmployeeId(Integer.parseInt(registration));
				final String password = request.getParameter("userpass1");
				loginData.setPassword(password);
				if(access.saveRegistrationDetails(loginData)){
					request.setAttribute("ErrorMessage", "Employee details updated sucessfully");
					rd = request.getRequestDispatcher("registration.jsp");
					rd.forward(request, response);
				}else{
					request.setAttribute("ErrorMessage", "Employee id is already exists");
					rd = request.getRequestDispatcher("registration.jsp");
					rd.forward(request, response);
				}
			}else if(accessHandler.equals(Constants.LOGIN)){
				userName = request.getParameter("username");
				loginData.setEmployeeId(Integer.parseInt(userName));
				final String password = request.getParameter("userpass");
				loginData.setPassword(password);
				if(access.validateAccess(userName,password))
				{
					empData = access.getEmployeeData(userName);
					session.setAttribute("userName", userName);
					setEmployee(empData);
					request.setAttribute("employeeId", empData.getEmployeeId());
					session.setAttribute("employeeId", empData.getEmployeeId());
					request.setAttribute("points_achived", empData.getPointsAchived());
					request.setAttribute("point_remaining", empData.getPointsRemaining());
					session.setAttribute("point_remaining", empData.getPointsRemaining());
					request.setAttribute("supervisor_id", empData.getSupervisorId());
					if(userName.equals("1001") || userName.equals("1002"))
					{
						session.setAttribute("ADMIN", "ADMIN");
					}
					request.setAttribute("ProductDetails", access.getProductDetails());
					final Points point = new Points();
					request.setAttribute("categoryList", point.getCategory());
					rd = request.getRequestDispatcher("partials/employeeHomePage.jsp");
					rd.forward(request, response);
				}else {
					rd = request.getRequestDispatcher("Login.jsp");
					request.setAttribute("ErrorMesage", "Please enter correct credentials");
					rd.forward(request, response);
					System.out.println("login falied : username :"+userName+" password : "+password);
				}
			}else if(accessHandler.equals(Constants.MYBIDS))
			{
				final List<Bids> bids = access.getBidDetails(Integer.parseInt((String)session.getAttribute("userName")));
				request.setAttribute("myBids", bids);
				rd = request.getRequestDispatcher("partials/myBids.jsp");
				rd.forward(request, response);
			}
			else if(accessHandler.equals(Constants.SUB_DATE))
			{
				String date = request.getParameter("date");
				boolean result = access.setDate(date);
				if(result)
				{
					request.setAttribute("manageErrorValue","Date is updated sucessfully");
				}
				else
				{
					request.setAttribute("manageErrorValue","Date is not updated");
				}
				rd = request.getRequestDispatcher("partials/manage.jsp");
				rd.forward(request, response);
			}
			else if(accessHandler.equals(Constants.GET_EMP_BID))
			{
				String empId = request.getParameter("empId");
				List<Bids> bids = access.getBidDetails(Integer.parseInt(empId));
				request.setAttribute("myBids", bids);
				rd = request.getRequestDispatcher("partials/employeeBidHistory.jsp");
				rd.forward(request, response);
			}else if(accessHandler.equals(Constants.MANAGE)){//getdata from db arjun change here
				final Points point = new Points();
				request.setAttribute("categoryList", point.getCategory());
				rd = request.getRequestDispatcher("partials/manage.jsp");
				rd.forward(request, response);
			}else if(accessHandler.equals(Constants.SIGNOUT)){
				session.invalidate();
				rd = request.getRequestDispatcher("Login.jsp");
				response.setHeader("Cache-Control", "no-cache, no-store");
			    response.setHeader("Pragma", "no-cache");
				rd.forward(request, response);
			}else if(accessHandler.equals(Constants.BACKTOCATALOG)){
				final Points point = new Points();
				request.setAttribute("categoryList", point.getCategory());
				request.setAttribute("ProductDetails", access.getProductDetails());
				rd = request.getRequestDispatcher("partials/employeeHomePage.jsp");
				rd.forward(request, response);
			}else if(accessHandler.equals(Constants.SUBMITBID)){
				final int employee = Integer.parseInt(request.getParameter("Employeeid"));
				final int itemId = Integer.parseInt(request.getParameter("Itemid"));
				final int points = Integer.parseInt(request.getParameter("points"));
				final int remianingPoints = Integer.parseInt(request.getParameter("pointsRemianing"));
				final Points point = new Points();
				request.setAttribute("categoryList", point.getCategory());
				rd = request.getRequestDispatcher("partials/manage.jsp");
				if(!point.saveBidDetails(employee, itemId, points, remianingPoints)) {	
					request.setAttribute("manageErrorValue", "Employee id or Itemid is not available");
				}else{
					request.setAttribute("manageErrorValue", "Details updated sucessfully");
				}
				rd.forward(request, response);
			}else if(accessHandler.equals(Constants.ADDITEM)){
				String events  = request.getParameter("Events");
				String description = request.getParameter("Description");
				InputStream inputStream = null; 
		        final Part filePart = request.getPart("ItemImage");
		        if (filePart != null) {
		            inputStream = filePart.getInputStream();
		        }
		        final Points point = new Points();
				request.setAttribute("categoryList", point.getCategory());
		        rd = request.getRequestDispatcher("partials/manage.jsp");
		        final ManipulateEmployee manipulateEmployee = new ManipulateEmployee();
		        if(!manipulateEmployee.addItem(events, description, inputStream, getSubmittedFileName(filePart))){
		        	request.setAttribute("manageErrorValue", "Item details are not saved image size should be less than 3GB");
				}else{
					request.setAttribute("manageErrorValue", "Item details updated sucessfully");
				}
		        rd.forward(request, response);
			}
			else if(accessHandler.equals(Constants.SUPERVISOR_LIST))
			{
				rd = request.getRequestDispatcher("partials/supervisor.jsp");
				rd.forward(request, response);
			}
			else if(accessHandler.equals(Constants.SUPERVISOR))
			{
				String superVisorName= request.getParameter("supid");
				List<Supervisor> supervisorList = access.getSupervisorData(superVisorName);
				request.setAttribute("supervisorList", supervisorList);
				rd = request.getRequestDispatcher("partials/supervisor.jsp");
				rd.forward(request, response);
			}else if(accessHandler.equals(Constants.WINNER_LIST))
			{
				final Points points = new Points();
				request.setAttribute("winnersList", ManipulateClass.getWinnerList(points.getEmpItemMap()));
				rd = request.getRequestDispatcher("partials/WinnerList.jsp");
				rd.forward(request, response);
			}else if(accessHandler.equals(Constants.MANAGE_ITEAMS))
			{
				request.setAttribute("ProductDetails", access.getProductDetails());
				final Points point = new Points();
				request.setAttribute("categoryList", point.getCategory());
				rd = request.getRequestDispatcher("partials/ManageItems.jsp");
				rd.forward(request, response);
			}
			 else if(accessHandler.equals(Constants.ADD_EDIT))
			 {
				 rd = request.getRequestDispatcher("partials/employee.jsp");
				 rd.forward(request, response);
			 }else if(accessHandler.equals(Constants.REGISTRATION))
			 {
				 rd = request.getRequestDispatcher("registration.jsp");
				 rd.forward(request, response);
			 }else if(accessHandler.equals(Constants.LOGINPAGE))
			 {
				 rd = request.getRequestDispatcher("Login.jsp");
				 rd.forward(request, response);
			 }
			 else if(accessHandler.equals(Constants.EMP_BID_HIST))
			 {
				 rd = request.getRequestDispatcher("partials/employeeBidHistory.jsp");
				 rd.forward(request, response);
			 }
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	/**
	 * for getting getSubmittedFileName
	 * @param part
	 * @return
	 */
	public static String getSubmittedFileName(Part part) {
	    for (String cd : part.getHeader("content-disposition").split(";")) {
	        if (cd.trim().startsWith("filename")) {
	            String fileName = cd.substring(cd.indexOf('=') + 1).trim().replace("\"", "");
	            fileName = fileName.substring(fileName.lastIndexOf('/') + 1).substring(fileName.lastIndexOf('\\') + 1);
	            return fileName.substring(0, fileName.lastIndexOf('.'));
	        }
	    }
	    return null;
	}
	private void setEmployee(Employee emp) {
		
		
		this.emp = emp;
	}
	private Employee getEmployee()
	{
		return emp;
	}
}




