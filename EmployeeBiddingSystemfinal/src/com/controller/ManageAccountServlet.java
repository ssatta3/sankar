package com.controller;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
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

import com.DAO.ManipulateEmployee;
import com.DAO.LoginAccess;
import com.DAO.Points;
import com.constants.Constants;
import com.vo.Bids;
import com.vo.Employee;

/**
 * Servlet implementation class ManageAccountServlet
 */
@WebServlet("/ManageAccountServlet")
@MultipartConfig(maxFileSize = 16177215) 
public class ManageAccountServlet extends HttpServlet {
	private int employeeId;
	private int pointsRemaining;
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ManageAccountServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		doPost(request, response);
	}
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		final String accessHandler = request.getParameter("submit");
		int employeeId = 0;
		int pointsRemaining = 0;
	    final HttpSession session = request.getSession();
	    RequestDispatcher rd = null;
		final LoginAccess access = new LoginAccess();
		 if(accessHandler.equals(Constants.MANAGE_EMP_dATA))
		{
			String empId = request.getParameter("empid");
			Employee empData;
			try {
				empData = access.getEmployeeData(empId);
				setEmployeeId(empData.getEmployeeId());
				setPointsRemaining(empData.getPointsRemaining());
				request.setAttribute("empDetails", empData);
				rd = request.getRequestDispatcher("partials/employee.jsp");
				rd.forward(request, response);
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
			
		}
		 else if(accessHandler.equals(Constants.ADD_OR_REMOVE_POINTS))
		 {
	    final Points points = new Points();
			final boolean result = points.updateEmployeeDetails(request.getParameter("employeeRemainingPoints"), request.getParameter("supervisorName"), request.getParameter("employeeName"), getEmployeeId());
			if(result)
			{
				request.setAttribute("updateDataErrorReoprt", "Details updated sucessfully");
				}
			else
			{
				request.setAttribute("updateDataErrorReoprt", "Error while updating employee data. There might be supervisior details not available");
			}
			rd = request.getRequestDispatcher("partials/employee.jsp");
			rd.forward(request, response);
		
		 }
		 else if(accessHandler.equals(Constants.DELETE_EMPLOYEE))
		 {
			 ManipulateEmployee del = new ManipulateEmployee();
			 boolean result = del.deleteEmp(getEmployeeId());
			 if(result)
			 {
				 request.setAttribute("updateDataErrorReoprt", "Employee with id "+getEmployeeId()+" is deleted sucessfully");
			 }
			 else
			 {
				 request.setAttribute("updateDataErrorReoprt", "unable to delete the employee");
			 }
			 rd = request.getRequestDispatcher("partials/employee.jsp");
				rd.forward(request, response);
		 }
		 else if(accessHandler.equals(Constants.ADD_EMP))
		 {
			 Employee emp = new Employee();
			 emp.setEmployeeId(Integer.parseInt(request.getParameter("Employeeid").toString()));
			 emp.setEmployeeName(request.getParameter("name").toString());
			 emp.setSupervisorId(Integer.parseInt(request.getParameter("supervisorid").toString()));
			 emp.setPointsRemaining(Integer.parseInt(request.getParameter("points").toString()));
			 ManipulateEmployee addEmp = new ManipulateEmployee();
			 boolean result = addEmp.addEmployee(emp);
			 if(result)
			 {
				 request.setAttribute("updateDataErrorReoprt", "Employee added sucessfully");

			 }
			 else
			 {
				 request.setAttribute("updateDataErrorReoprt", ""+emp.getEmployeeId()+" employeeID is already exist or supervisorId "+emp.getSupervisorId()+" doesnot exist");
			 }
			 rd = request.getRequestDispatcher("partials/employee.jsp");
			 rd.forward(request, response);
		 }
		 else if(accessHandler.equals(Constants.AJAXCALL)){
			 ManipulateEmployee manipulateEmployee = new ManipulateEmployee();
			 PrintWriter out = response.getWriter();
			 employeeId = Integer.parseInt(request.getParameter("employeeId"));
			 out.println(manipulateEmployee.getRemainingPoints(employeeId));
		 }
		 else if(accessHandler.equals(Constants.SUBMITBIDS)){
			 employeeId = (int)session.getAttribute("employeeId");
			 final List<Bids> bidsDetails = new ArrayList<Bids>();
			 final int itemid = Integer.parseInt(request.getParameter("heighestItemId"));
			 pointsRemaining = Integer.parseInt(request.getParameter("pointRemaining"));
			 session.setAttribute("point_remaining", pointsRemaining);
			 final Employee employee = new Employee();
			 employee.setEmployeeId(employeeId);
			 employee.setPointsRemaining(pointsRemaining);
			 boolean bidDetailsFound = false;
			 for(int i=1; i<= itemid; i++){
				 final String bidAmount = request.getParameter("BidAmount"+i);
				 if(bidAmount != null && !bidAmount.equals("")){
					 final Bids bids = new Bids();
					 bids.setPoints(Integer.parseInt(request.getParameter("BidAmount"+i)));
					 bids.setItemId(i);
					 bidsDetails.add(bids);
					 bidDetailsFound=true;
				 }
			 }
			 final Points points = new Points();
			 if(bidDetailsFound && points.submitBids(bidsDetails,employee)){
				 request.setAttribute("ErrorMesage", "Details are updated sucessfully");
			 }else if(!bidDetailsFound){
				 request.setAttribute("ErrorMesage", "Please Bid one Item atleast");
			 }else{
				 request.setAttribute("ErrorMesage", "Some error while saving details into db");
			 }
			 request.setAttribute("ProductDetails", access.getProductDetails());
			 final Points point = new Points();
			 request.setAttribute("categoryList", point.getCategory());
			 rd = request.getRequestDispatcher("partials/employeeHomePage.jsp");
			 rd.forward(request, response);
		 }else if(accessHandler.equals(Constants.DELETEITEM)){
			final String[] delteItems = request.getParameterValues("deleteItemCheck");
			final Points points = new Points();
			final List<String> list = points.deleteItems(delteItems);
			if(list.size()>0){
				String val = "";
				for (String id : list) {
					val = val +"  " +id;
				}
				request.setAttribute("manageErrorValue", val+" these item id's are already bidded. you are allowed to modify that items only");
			}else{
				request.setAttribute("manageErrorValue", "Items are deleted succesfully");
			}
			request.setAttribute("ProductDetails", access.getProductDetails());
			final Points point = new Points();
			request.setAttribute("categoryList", point.getCategory());
			rd = request.getRequestDispatcher("partials/ManageItems.jsp");
			rd.forward(request, response);
		 }else if(accessHandler.equals(Constants.ADDManageITEM)){
			 final ManipulateEmployee manipulateEmployee  = new ManipulateEmployee ();
			 if(manipulateEmployee.addCategory(request.getParameter("addedCategory"))){
				 request.setAttribute("manageErrorValue", "Category added sucessfully");
				}else{
				 request.setAttribute("manageErrorValue", "There is some problem while adding category");
				}
			 request.setAttribute("ProductDetails", access.getProductDetails());
			 final Points point = new Points();
			 request.setAttribute("categoryList", point.getCategory());
			 rd = request.getRequestDispatcher("partials/ManageItems.jsp");
			 rd.forward(request, response);
		 }else if(accessHandler.equals(Constants.HREFCATAGORYDELETE)){
			 	final String category = request.getParameter("categoryName");			 	
				 final Points point = new Points();
				 if(point.deleteCategory(category)){
					 request.setAttribute("manageErrorValue", "Category deleted sucessfully");
					}else{
					 request.setAttribute("manageErrorValue", "There is some problem while deleting category");
					}
				 request.setAttribute("ProductDetails", access.getProductDetails());
				 request.setAttribute("categoryList", point.getCategory());
				 rd = request.getRequestDispatcher("partials/ManageItems.jsp");
				 rd.forward(request, response);
			}else if(accessHandler.equals(Constants.MANAGEITEMSSAVE)){
				HashMap<String, HashMap<String,Object>> map = new HashMap<String, HashMap<String,Object>>();
				final Enumeration allParameterNames = request.getParameterNames();
				while(allParameterNames.hasMoreElements())
				{
				    Object object = allParameterNames.nextElement();
				    String param =  (String)object;
				    System.out.println(param);
				     if(param.contains(":") ){
				    	final String key = param.split(":")[1];
				    	HashMap<String, Object> internalMap;
				    	if(map.containsKey(key)){
				    		internalMap = map.get(key);
				    	}else{
				    		internalMap = new HashMap<String,Object>();
				    	}
				    	internalMap.put(param.split(":")[0], request.getParameter(param));
				    	final Part filePart = request.getPart("ItemImage:"+key);
				    	try{
			    		internalMap.put("itemName", LoginServlet.getSubmittedFileName(filePart));
			    		InputStream inputStream = null; 
				        if (filePart != null) {
				            inputStream = filePart.getInputStream();
				        }
				        internalMap.put("ItemImage", inputStream);
				    	}catch(Exception e){
				    		
				    	}
				    	map.put(key, internalMap );
				    }
				}  			 	
				 final Points point = new Points();
				 if(point.updateManageItem(map)){
					 request.setAttribute("manageErrorValue", "Details are updated in db");
					}else{
					 request.setAttribute("manageErrorValue", "There is some problem while updating. Please select image less then 16MB");
					}
				 request.setAttribute("ProductDetails", access.getProductDetails());
				 request.setAttribute("categoryList", point.getCategory());
				 rd = request.getRequestDispatcher("partials/ManageItems.jsp");
				 rd.forward(request, response);
			}
		 
	}

	private void setPointsRemaining(int pointsRemaining2) {
		// TODO Auto-generated method stub
		this.pointsRemaining = pointsRemaining2;
		
	}
	private int getEmployeeId()
	{
		return employeeId;
	}
	private int getPointsRemaining()
	{
		return pointsRemaining;
	}

	private void setEmployeeId(int employeeId) {
		this.employeeId = employeeId;
		
	}
	

}
