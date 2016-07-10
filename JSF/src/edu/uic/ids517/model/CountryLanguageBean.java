package edu.uic.ids517.model;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

@ManagedBean
@RequestScoped
public class CountryLanguageBean {
	private List<String> columnList = new ArrayList<String>();
	private boolean columnBoolean;
	public boolean isColumnBoolean() {
		return columnBoolean;
	}
	public void setColumnBoolean(boolean columnBoolean) {
		this.columnBoolean = columnBoolean;
	}
	public List<String> getColumnList() {
		return columnList;
	}
	public void setColumnList(List<String> columnList) {
		this.columnList = columnList;
	}
	private CountryLanguageBean countryLanguageBean;
	private List<CountryLanguageBean> countryLanguageBeanList = new ArrayList<CountryLanguageBean>();
	private String countryCode;
	private String language;
	private String isOfficial;
	private float percentage;
private int maxPercentage;
private int minPercentage;
private int avgPercentage;
private ResultSet rs;
private boolean countryLanguageStatus;
private DBAccessBean dbAccess;
public int getMaxPercentage() {
	return maxPercentage;
}
public void setMaxPercentage(int maxPercentage) {
	this.maxPercentage = maxPercentage;
}
public int getMinPercentage() {
	return minPercentage;
}
public void setMinPercentage(int minPercentage) {
	this.minPercentage = minPercentage;
}
public int getAvgPercentage() {
	return avgPercentage;
}
public void setAvgPercentage(int avgPercentage) {
	this.avgPercentage = avgPercentage;
}
public boolean isCountryLanguageStatus() {
	return countryLanguageStatus;
}
public void setCountryLanguageStatus(boolean countryLanguageStatus) {
	this.countryLanguageStatus = countryLanguageStatus;
}
@PostConstruct
public void init(){
	if(dbAccess == null){
		dbAccess = new DBAccessBean();
	}else{
	FacesContext context = FacesContext.getCurrentInstance();
	Map <String, Object> m = context.getExternalContext().getSessionMap();
	this.dbAccess = (DBAccessBean) m.get("dbAccess");
}
}

public String showTable(){
	String status ="fail";
	setCountryLanguageStatus(false);
	String query = "select * from countrylanguage";
	status = dbAccess.executeQuery(query);
	try{
		rs = dbAccess.getResultSet();
		while(rs.next()){
			countryLanguageBean = new CountryLanguageBean();
			countryLanguageBean.setCountryCode(rs.getString(1));
			countryLanguageBean.setLanguage(rs.getString(2));
			countryLanguageBean.setIsOfficial(rs.getString(3));
			countryLanguageBean.setPercentage(rs.getFloat(4));
			countryLanguageBeanList.add(countryLanguageBean);
		}
		status = "SUCCESS";
		setCountryLanguageStatus(true);
	}catch(Exception e){
		e.printStackTrace();
	}
	return status;
	
	
	
}

public String getColumns(){
	String status = "fail";
	String query = "select * from countryLanguage where 1=0";
	try{
		System.out.println("hi");
		if(dbAccess==null){dbAccess = new DBAccessBean();}
	status = dbAccess.executeQuery(query);
	rs = dbAccess.getResultSet();
	int count = rs.getMetaData().getColumnCount();
	if(columnList.isEmpty()){
	for(int i=1;i<=count;i++){
		columnList.add(rs.getMetaData().getColumnLabel(i));
		System.out.println(rs.getMetaData().getColumnTypeName(4));
	}
	status = "SUCCESS";
	}
	}catch(Exception e){
		e.printStackTrace();
	}
	return status;
}

public String getReport(){
	countryLanguageStatus = false;
	String status= "SUCCESS";
	String query = "select min(Percentage) as minPercentage,max(Percentage) as maxPercentage,avg(Percentage) as avgPercentage from countrylanguage";
	try{
		 status = dbAccess.executeQuery(query);
		rs = dbAccess.getResultSet();
		rs.next();
		setMaxPercentage(rs.getInt("maxPercentage"));
		setMinPercentage(rs.getInt("minPercentage"));
		setAvgPercentage(rs.getInt("avgPercentage"));
		countryLanguageStatus = true;
		status = "SUCCESS";
	}catch(Exception e){
		countryLanguageStatus= false;
		status = "fail";
		System.err.println(e);
	}
	return status;
}
public float getPercentage() {
	return percentage;
}
public void setPercentage(float percentage) {
	this.percentage = percentage;
}
public String getIsOfficial() {
	return isOfficial;
}
public void setIsOfficial(String isOfficial) {
	this.isOfficial = isOfficial;
}
public String getLanguage() {
	return language;
}
public void setLanguage(String language) {
	this.language = language;
}
public String getCountryCode() {
	return countryCode;
}
public void setCountryCode(String countryCode) {
	this.countryCode = countryCode;
}
public CountryLanguageBean getCountryLanguageBean() {
	return countryLanguageBean;
}
public void setCountryLanguageBean(CountryLanguageBean countryLanguageBean) {
	this.countryLanguageBean = countryLanguageBean;
}
public List<CountryLanguageBean> getCountryLanguageBeanList() {
	return countryLanguageBeanList;
}
public void setCountryLanguageBeanList(List<CountryLanguageBean> countryLanguageBeanList) {
	this.countryLanguageBeanList = countryLanguageBeanList;
}
}
