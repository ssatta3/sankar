package edu.uic.ids517.model;

import javax.annotation.ManagedBean;
import javax.annotation.PostConstruct;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;

@ManagedBean
@SessionScoped
public class MessageBean {
	
   private String message;
   @PostConstruct
   public void init(){
		message="";
	}
public String getMessage() {
	System.out.println("hi get reached");
	return message;
}

public void setMessage(String message) {
	System.out.println("hi set reached");
	 this.message = message;
	
}
	
   
}
