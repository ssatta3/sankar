package edu.uic.ids517.controller;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;

import edu.uic.ids517.model.CityBean;
import edu.uic.ids517.model.CountryBean;
import edu.uic.ids517.model.CountryLanguageBean;

@ManagedBean
@RequestScoped
public class ActionReportBean {
	public ActionReportBean(){
		
	}
	
private int reportId;
@ManagedProperty(value = "#{cityBean}")
private CityBean cityBean;
@ManagedProperty(value = "#{countryBean}")
private CountryBean countryBean;
@ManagedProperty(value = "#{countryLanguageBean}")
private CountryLanguageBean countryLanguageBean;

public void setCityBean(CityBean cityBean){
	this.cityBean = cityBean;
}
public void setCountryBean(CountryBean countryBean){
	this.countryBean = countryBean;
}
public void setCountryLanguageBean(CountryLanguageBean countryLanguageBean){
    this.countryLanguageBean = countryLanguageBean;
}
public int getReportId() {
	return reportId;
}
public void setReportId(int reportId) {
	this.reportId = reportId;
}

public  String  directToReport(){
	String status = "fail";
	System.out.println("repo=rtNumber is "+this.reportId+"demo");
	if(reportId==1){
		System.out.println("inside if of directreport");
		
			System.out.println("inside if of city bean null");
			System.out.println(" is "+this.reportId+"hello");
			//cityBean = new CityBean();
			System.out.println("reportNumber is "+this.reportId+"world");
	
		status = cityBean.getReport();
		System.out.println("dhfksjfk");
		cityBean.setStatusCity(true);
		System.out.println("dhfksjfk32");
	}else if(reportId==2){
		status = countryBean.getReport1();
		countryBean.setStatusCountry1(true);
	}else if(reportId==3){
		status = countryBean.getReport2();
		countryBean.setStatusCountry2(true);
	}else if(reportId==4){
		status = countryLanguageBean.getReport();
		countryLanguageBean.setCountryLanguageStatus(true);
	}
	return status;	
}
}
