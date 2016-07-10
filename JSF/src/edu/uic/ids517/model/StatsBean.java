package edu.uic.ids517.model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import org.apache.commons.math3.stat.descriptive.DescriptiveStatistics;

import edu.uic.ids517.controller.ActionTableBean;

@ManagedBean
@SessionScoped
public class StatsBean {
        private StatsBean statsBean;
        private StatsBean sb;
	    public StatsBean getSb() {
			return sb;
		}
		public void setSb(StatsBean sb) {
			this.sb = sb;
		}
		private CityBean cityBean;
		private ResultSet rs;
		public ResultSet getRs() {
			return rs;
		}
		public void setRs(ResultSet rs) {
			this.rs = rs;
		}
		private MessageBean messageBean;
		private ActionTableBean actionTableBean;
		public ActionTableBean getActionTableBean() {
			return actionTableBean;
		}
		public void setActionTableBean(ActionTableBean actionTableBean) {
			this.actionTableBean = actionTableBean;
		}
		private DBAccessBean dbAccess;
	    public DBAccessBean getDbAccess() {
			return dbAccess;
		}
		public void setDbAccess(DBAccessBean dbAccess) {
			this.dbAccess = dbAccess;
		}
		private List<String> columnList; 
	    private int tableId;
	    public int getTableId() {
			return tableId;
		}
		public void setTableId(int tableId) {
			this.tableId = tableId;
		}
		private List<String> permColList= new ArrayList<String>();
		private CountryBean countryBean;
		public CountryBean getCountryBean() {
			return countryBean;
		}
		public void setCountryBean(CountryBean countryBean) {
			this.countryBean = countryBean;
		}
		private CountryLanguageBean countryLanguageBean;
		public CountryLanguageBean getCountryLanguageBean() {
			return countryLanguageBean;
		}
		public void setCountryLanguageBean(CountryLanguageBean countryLanguageBean) {
			this.countryLanguageBean = countryLanguageBean;
		}
		private double mean = 0;
		private double min = 0;
		private double max = 0;
		private double q1 = 0;
		private double q2 = 0;
		private double stddev = 0 ;
		private String columnName= null ;
		public String getColumnName() {
			return columnName;
		}
		public void setColumnName(String columnName) {
			this.columnName = columnName;
		}
		@PostConstruct
		public void init(){
			mean=0;
			min=0;
			max=0;
			q1=0;
			q2=0;
			stddev=0;
			columnName= null;
		}
		public double getMean() {
			return mean;
		}
		public void setMean(double mean) {
			this.mean = mean;
		}
		public double getMin() {
			return min;
		}
		public void setMin(double min) {
			this.min = min;
		}
		public double getMax() {
			return max;
		}
		public void setMax(double max) {
			this.max = max;
		}
		public double getQ1() {
			return q1;
		}
		public void setQ1(double q1) {
			this.q1 = q1;
		}
		public double getQ2() {
			return q2;
		}
		public void setQ2(double q2) {
			this.q2 = q2;
		}
		public double getStddev() {
			return stddev;
		}
		public void setStddev(double stddev) {
			this.stddev = stddev;
		}
		public List<String> getPermColList() {
			return permColList;
		}
		public void setPermColList(List<String> permColList) {
			this.permColList = permColList;
		}
		public String displayColumns(){
			String status = "FAIL";
			FacesContext context = FacesContext.getCurrentInstance();
			Map<String,Object> m = context.getExternalContext().getSessionMap();
			SelectionBean selectionBean = (SelectionBean)m.get("selectionBean");
		    cityBean = (CityBean)m.get("cityBean");
		    if(cityBean==null) {cityBean = new CityBean();}
		    System.out.println(cityBean);
			tableId=selectionBean.getTableId();
			if(tableId==1){
				System.out.println("reached display");
				status = cityBean.getColumns();
				setColumnList(cityBean.getColumnList());
				cityBean.setColumnBoolean(true);
			}else if(tableId==2){
				System.out.println("reached display");
				status = countryBean.getColumns();
				setColumnList(countryBean.getColumnList());
				countryBean.setColumnBoolean(true);	
			}else if(tableId==3){
				status = countryLanguageBean.getColumns();
				setColumnList(countryLanguageBean.getColumnList());
				countryLanguageBean.setColumnBoolean(true);
			}
			status = "SUCCESS";
			return status;
		}
		public List<String> getColumnList() {
			return columnList;
		}
		public void setColumnList(List<String> columnList) {
			this.columnList = columnList;
		}
		public StatsBean getStatsBean() {
			return statsBean;
		}
		public void setStatsBean(StatsBean statsBean) {
			this.statsBean = statsBean;
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
			
			columnName = selectionBean.getColumnName();
			System.out.println(columnName+"column name empty");
			if(selectionBean.getTableId()==1){
				query = "select" +columnName+ "from city";
			    dbAccess.executeQuery(query);
			    rs= dbAccess.getResultSet();
			    try {
			     s = rs.getMetaData().getColumnTypeName(1);
				} catch (SQLException e) {
					e.printStackTrace();
				}
			    if(!s.equals("INT")||!s.equals("FLOAT")){
			    	messageBean.setMessage("NOT NUMERIC. STATS CANT BE DISPLAYED");
			    }else{
			    	try {
						while(rs.next()){
						stats1.addValue(Double.parseDouble(rs.getString(1)));	
						}
					} catch (SQLException e) {
					
						e.printStackTrace();
					}	
			    } 
			}
			sb.setMean(stats1.getMean());
			sb.setQ1(stats1.getPercentile(25));
			sb.setQ2(stats1.getPercentile(75));
			sb.setMax(stats1.getMax());
			sb.setMin(stats1.getMin());
			sb.setStddev(stats1.getStandardDeviation());
			actionTableBean.setDisplayStatsBoolean(true);	
			return "SUCCESS";
		}
		
	}


