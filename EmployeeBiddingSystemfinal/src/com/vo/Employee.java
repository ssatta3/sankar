package com.vo;

import java.util.List;

public class Employee 
{
	private String userName;
	private String password;
	private String employeeName;
	private int pointsAchived;
	private int pointsRemaining;
	private int supervisorId;
	private int total_points;
	private List<Bids> bids;
	private int employeeId;
	private String supervisorName;
	public String getSupervisorName() {
		return supervisorName;
	}
	public void setSupervisorName(String supervisorName) {
		this.supervisorName = supervisorName;
	}
	public int getEmployeeId() {
		return employeeId;
	}
	public void setEmployeeId(int employeeId) {
		this.employeeId = employeeId;
	}
	public List<Bids> getBids() {
		return bids;
	}
	public void setBids(List<Bids> bids) {
		this.bids = bids;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getEmployeeName() {
		return employeeName;
	}
	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}
	public int getPointsAchived() {
		return pointsAchived;
	}
	public void setPointsAchived(int pointsAchived) {
		this.pointsAchived = pointsAchived;
	}
	public int getPointsRemaining() {
		return pointsRemaining;
	}
	public void setPointsRemaining(int pointsRemaining) {
		this.pointsRemaining = pointsRemaining;
	}
	public int getSupervisorId() {
		return supervisorId;
	}
	public void setSupervisorId(int supervisorId) {
		this.supervisorId = supervisorId;
	}
	public int getTotal_points() {
		return total_points;
	}
	public void setTotal_points(int total_points) {
		this.total_points = total_points;
	}
}
