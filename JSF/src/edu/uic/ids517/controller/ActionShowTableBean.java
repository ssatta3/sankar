package edu.uic.ids517.controller;

import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import edu.uic.ids517.model.CityBean;
import edu.uic.ids517.model.CountryBean;
import edu.uic.ids517.model.CountryLanguageBean;
@ManagedBean
@RequestScoped


public class ActionShowTableBean {
 @ManagedProperty(value = "#{cityBean}")
 private CityBean cityBean;
 public CityBean getCityBean() {
	return cityBean;
}

public void setCityBean(CityBean cityBean) {
	this.cityBean = cityBean;
}

@ManagedProperty(value = "#{countryBean}")
private CountryBean countryBean;
 public CountryBean getCountryBean() {
	return countryBean;
}

public void setCountryBean(CountryBean countryBean) {
	this.countryBean = countryBean;
}

public CountryLanguageBean getCountryLanguageBean() {
	return countryLanguageBean;
}

public void setCountryLanguageBean(CountryLanguageBean countryLanguageBean) {
	this.countryLanguageBean = countryLanguageBean;
}

public String getStatus() {
	return status;
}

public void setStatus(String status) {
	this.status = status;
}

public int getTableId() {
	return tableId;
}

public void setTableId(int tableId) {
	this.tableId = tableId;
}

public List<CountryBean> getCountryBeanList() {
	return countryBeanList;
}

public void setCountryBeanList(List<CountryBean> countryBeanList) {
	this.countryBeanList = countryBeanList;
}

public List<CountryLanguageBean> getCountryLanguageBeanList() {
	return countryLanguageBeanList;
}

public void setCountryLanguageBeanList(List<CountryLanguageBean> countryLanguageBeanList) {
	this.countryLanguageBeanList = countryLanguageBeanList;
}

@ManagedProperty(value = "#{countryLanguageBean}")
 private CountryLanguageBean countryLanguageBean;
 private String status;
private int tableId;
private List<CityBean> cityBeanList;
public List<CityBean> getCityBeanList() {
	return cityBeanList;
}

public void setCityBeanList(List<CityBean> cityBeanList) {
	this.cityBeanList = cityBeanList;
}

private List<CountryBean> countryBeanList;
private List<CountryLanguageBean> countryLanguageBeanList;

	public String getTable(){
		status = "fail";
		FacesContext context = FacesContext.getCurrentInstance();
		Map<String,Object> m = context.getExternalContext().getSessionMap();
		if(cityBean == null){ cityBean = new CityBean(); m.put("cityBean", cityBean);}
		if(countryBean == null){ countryBean = new CountryBean();}
		if(countryLanguageBean == null){ countryLanguageBean = new CountryLanguageBean();}
		if(tableId==1){
			status = cityBean.showTable();
			cityBeanList=cityBean.getCityBeanList();
			cityBean.setStatusCity(true);
			
		}else if(tableId ==2){
			status = countryBean.showTable();
			countryBeanList = countryBean.getCountryBeanList();
			countryBean.setStatusCountry1(true);
		}else if(tableId==3){
			status = countryLanguageBean.showTable();
			countryLanguageBeanList = countryLanguageBean.getCountryLanguageBeanList();
			countryLanguageBean.setCountryLanguageStatus(true);
		}
		return status;
	}
}
