package edu.uic.ids517.controller;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.apache.commons.math3.stat.descriptive.DescriptiveStatistics;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;

import edu.uic.ids517.model.CityBean;
import edu.uic.ids517.model.CountryBean;
import edu.uic.ids517.model.CountryLanguageBean;
import edu.uic.ids517.model.DBAccessBean;
import edu.uic.ids517.model.MessageBean;
import edu.uic.ids517.model.SelectionBean;
import edu.uic.ids517.model.StatsBean;
@ManagedBean
@SessionScoped
public class ActionTableBean {
	
	private String message;
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	private CityBean cityBean;
	private CountryBean countryBean;
	private CountryLanguageBean countryLanguageBean;
	private ResultSet rs;
	private List<CityBean> cityBeanList;
	private List<CountryLanguageBean> countryLanguageBeanList;
    private StatsBean sb;
    private MessageBean messageBean;
	private int tableId;
	private String status;
	private int databaseId;
	private List<CountryBean> countryBeanList;
	private List<String> columnList;
	private String columnName;
	private DBAccessBean dbAccess;
	private boolean displayStatsBoolean;
	private ActionTableBean actionTableBean;
	public String getColumnName() {
		return columnName;
	}
	public void setColumnName(String columnName) {
		this.columnName = columnName;
	}
	public void setDatabaseId(int databaseId){
		this.databaseId = databaseId;
	}
	public int getDatabaseId(){
		return databaseId;
	}
	public void setCityBean(CityBean cityBean){
		this.cityBean = cityBean;
	}
	public void setCountryBean(CountryBean countryBean){
		this.countryBean = countryBean;
	}
	public void setCountryLanguageBean(CountryLanguageBean countryLanguageBean){
	    this.countryLanguageBean = countryLanguageBean;
	}
	
	public int getTableId() {
		return tableId;
	}
	public void setTableId(int tableId) {
		this.tableId = tableId;
	}
	public String getStatus(){
		return status;
	}
	public void setStatus(String status){
		this.status = status;
	}
	
	public String getStatistics(){
		System.out.println("reached stats");
		String query="";
		String s="";
		FacesContext context = FacesContext.getCurrentInstance();
		Map<String,Object> m = context.getExternalContext().getSessionMap();
		DescriptiveStatistics stats1 = new DescriptiveStatistics();
		SelectionBean selectionBean = (SelectionBean)m.get("selectionBean");
		dbAccess = (DBAccessBean) m.get("dbmsUserBean");
		messageBean = (MessageBean) m.get("messageBean");
		actionTableBean = (ActionTableBean) m.get("actionTableBean");
		setSb((StatsBean) m.get("statsBean"));
		if(dbAccess==null){dbAccess = new DBAccessBean();}
		if(messageBean==null){messageBean = new MessageBean();}
		if(sb==null){sb = new StatsBean();}
		
		System.out.println(columnName+"column name empty");
		if(selectionBean.getTableId()==1){
			query = "select " +columnName+ " from city";
		    dbAccess.executeQuery(query);
		    rs= dbAccess.getResultSet();
		    try {
		     s = rs.getMetaData().getColumnTypeName(1);
		     System.out.println(s);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		    if(!s.equals("INT")&&!s.equals("FLOAT")){
		    	//message = "NOT NUMERIC. STATS CANT BE DISPLAYED";
		    	//System.out.println("failed");
		    }else{
		    	try {
					while(rs.next()){
					stats1.addValue(Double.parseDouble(rs.getString(1)));	
					System.out.println(Double.parseDouble(rs.getString(1)));
					}
				} catch (SQLException e) {
				
					e.printStackTrace();
				}	
		    } 
		}else if(tableId==2){
			query = "select " +columnName+ " from country";
		    dbAccess.executeQuery(query);
		    rs= dbAccess.getResultSet();
		    try {
		     s = rs.getMetaData().getColumnTypeName(1);
		     System.out.println(s);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		    if(!s.equals("INT")&&!s.equals("FLOAT")){
		    	//message = "NOT NUMERIC. STATS CANT BE DISPLAYED";
		    	//System.out.println("failed");
		    }else{
		    	try {
					while(rs.next()){
						if(rs.getString(1)==null){continue;}
					stats1.addValue(Float.parseFloat(rs.getString(1)));	
					System.out.println(Double.parseDouble(rs.getString(1)));
					}
				} catch (SQLException e) {
				
					e.printStackTrace();
				}	
		    } 
		}else if(tableId ==3 ){
			query = "select " +columnName+ " from countryLanguage";
		    dbAccess.executeQuery(query);
		    rs= dbAccess.getResultSet();
		    try {
		     s = rs.getMetaData().getColumnTypeName(1);
		     System.out.println(s);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		    if(!s.equals("INT")&&!s.equals("FLOAT")){
		    	//message = "NOT NUMERIC. STATS CANT BE DISPLAYED";
		    	//System.out.println("failed");
		    }else{
		    	try {
					while(rs.next()){
					stats1.addValue(Double.parseDouble(rs.getString(1)));	
					System.out.println(Double.parseDouble(rs.getString(1)));
					}
				} catch (SQLException e) {
				
					e.printStackTrace();
				}	
		    }
		}
		sb.setMean(stats1.getMean());
		System.out.println(stats1.getMean());
		sb.setQ1(stats1.getPercentile(25));
		sb.setQ2(stats1.getPercentile(75));
		sb.setMax(stats1.getMax());
		sb.setMin(stats1.getMin());
		sb.setStddev(stats1.getStandardDeviation());
		actionTableBean.setDisplayStatsBoolean(true);	
		return "SUCCESS";
	}
	
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
	public String displayColumns(){
		String status = "FAIL";
		FacesContext context = FacesContext.getCurrentInstance();
		Map<String,Object> m = context.getExternalContext().getSessionMap();
		SelectionBean selectionBean = (SelectionBean)m.get("selectionBean");
		 cityBean =(CityBean) m.get("cityBean");
		 if(cityBean==null){
			 cityBean = new CityBean();
		 }
		 if(countryBean==null){
			 countryBean = new CountryBean();
		 }
		 if(countryLanguageBean==null){
			 
			 countryLanguageBean =new CountryLanguageBean();
		 }
		tableId=selectionBean.getTableId();
		if(tableId==1){
			System.out.println("reached display");
			status = cityBean.getColumns();
			columnList = cityBean.getColumnList();
			cityBean.setColumnBoolean(true);
		}else if(tableId==2){
			System.out.println("reached display");
			status = countryBean.getColumns();
			columnList = countryBean.getColumnList();
			countryBean.setColumnBoolean(true);	
		}else if(tableId==3){
			status = countryLanguageBean.getColumns();
			columnList = countryLanguageBean.getColumnList();
			countryLanguageBean.setColumnBoolean(true);
		}
		status = "SUCCESS";
		return status;
	}
	public void selectColumn(ValueChangeEvent event){
		FacesContext context = FacesContext.getCurrentInstance();
		Map<String,Object> m = context.getExternalContext().getSessionMap();
		SelectionBean selectionBean = (SelectionBean)m.get("selectionBean");
		selectionBean.setColumnName((String) event.getNewValue());
	}
	
	public List<CityBean> getCityBeanList() {
		return cityBeanList;
	}
	public void setCityBeanList(List<CityBean> cityBeanList) {
		this.cityBeanList = cityBeanList;
	}
	public List<CountryLanguageBean> getCountryLanguageBeanList() {
		return countryLanguageBeanList;
	}
	public void setCountryLanguageBeanList(List<CountryLanguageBean> countryLanguageBeanList) {
		this.countryLanguageBeanList = countryLanguageBeanList;
	}
	public List<CountryBean> getCountryBeanList() {
		return countryBeanList;
	}
	public void setCountryBeanList(List<CountryBean> countryBeanList) {
		this.countryBeanList = countryBeanList;
	}
	public List<String> getColumnList() {
		return columnList;
	}
	public void setColumnList(List<String> columnList) {
		this.columnList = columnList;
	}
	
	public DBAccessBean getDbAccess() {
		return dbAccess;
	}
	public void setDbAccess(DBAccessBean dbAccess) {
		this.dbAccess = dbAccess;
	}
	public StatsBean getSb() {
		return sb;
	}
	public void setSb(StatsBean sb) {
		this.sb = sb;
	}
	public MessageBean getMessageBean() {
		return messageBean;
	}
	public void setMessageBean(MessageBean messageBean) {
		this.messageBean = messageBean;
	}
	public ResultSet getRs() {
		return rs;
	}
	public void setRs(ResultSet rs) {
		this.rs = rs;
	}
	public boolean isDisplayStatsBoolean() {
		return displayStatsBoolean;
	}
	public void setDisplayStatsBoolean(boolean displayStatsBoolean) {
		this.displayStatsBoolean = displayStatsBoolean;
	}
	public ActionTableBean getActionTableBean() {
		return actionTableBean;
	}
	public void setActionTableBean(ActionTableBean actionTableBean) {
		this.actionTableBean = actionTableBean;
	}
}
