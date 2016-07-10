package edu.uic.ids517.controller;


import java.sql.Connection;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import edu.uic.ids517.model.DBAccessBean;
import edu.uic.ids517.model.MessageBean;

@ManagedBean (name="actionBeanLoginLogout")
@SessionScoped
public class ActionBeanLoginLogout {
     private String host;
     private Connection con;
     public Connection getCon() {
		return con;
	}
	public void setCon(Connection con) {
		this.con = con;
	}
	private String dbms;
     private int port;
     private DBAccessBean dbAccess;
     public DBAccessBean getDbAccess() {
		return dbAccess;
	}
	public void setDbAccess(DBAccessBean dbAccess) {
		this.dbAccess = dbAccess;
	}
	private String dbSchema;
     private String userName;
     private String password;
     private String status;
     @ManagedProperty(value="#{messageBean}")
     private MessageBean messageBean;
     
	public String getDbms() {
		return dbms;
	}
	public void setDbms(String dbms) {
		this.dbms = dbms;
	}
	public String getHost() {
		return host;
	}
	public void setHost(String host) {
		this.host = host;
	}
	public int getPort() {
		return port;
	}
	public void setPort(int port) {
		this.port = port;
	}
	public String getDbSchema() {
		return dbSchema;
	}
	public void setDbSchema(String dbSchema) {
		this.dbSchema = dbSchema;
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
	@PostConstruct
	public void init(){
		if(messageBean == null){
			messageBean = new MessageBean();
		}else{
		FacesContext context = FacesContext.getCurrentInstance();
		Map <String, Object> m = context.getExternalContext().getSessionMap();
		this.messageBean = (MessageBean) m.get("messageBean");
		this.dbAccess = (DBAccessBean)m.get("dbAccess");
		
	}
	}
   
	 public String validate(){
		 if(host.equals("131.193.209.54") && dbSchema.equals("world") &&dbms.equals("mysql") && userName.equals("s16g40") && password.equals("s16g40FpqU5")){
			 status = "SUCCESS";
			 System.out.println("hi");
		 }else{
			 status = "FAIL";
			 System.out.println("fail");
			 messageBean.setMessage("Invalid Credentials");
			 System.out.println(messageBean.getMessage());
		 }
		
		/* dbAccess = new DBAccessBean();
	 con = dbAccess.getConnection();
	 if(con!=null){
		 status = "SUCCESS";
	 }else{
		 status = "fail";
	 }*/
		 return status;
	 }
	 public String logout(){
		 String s = "fail";
		 FacesContext context = FacesContext.getCurrentInstance();
			Map <String, Object> m = context.getExternalContext().getSessionMap();
			context.getExternalContext().invalidateSession();
			s = "SUCCESS";
			return s;
	 }
	public MessageBean getMessageBean() {
		return messageBean;
	}
	public void setMessageBean(MessageBean messageBean) {
		this.messageBean = messageBean;
	}
	
}
