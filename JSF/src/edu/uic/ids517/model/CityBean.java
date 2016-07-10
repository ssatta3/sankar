package edu.uic.ids517.model;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

@ManagedBean
@RequestScoped
public class CityBean {
	private List<String> columnList= new ArrayList<String>();
	private boolean columnBoolean;
	private String name;
	private int codeCount;
	private String district;
	private int population;
	DBAccessBean dbAcess;
	private boolean statusCity;
	private ResultSet rs;
	private List<CityBean> cityBeanList = new ArrayList<CityBean>();
	public int getCodeCount() {
		return codeCount;
	}
	public void setCodeCount(int codeCount) {
		this.codeCount = codeCount;
	}
	public DBAccessBean getDbAcess() {
		return dbAcess;
	}
	public void setDbAcess(DBAccessBean dbAcess) {
		this.dbAcess = dbAcess;
	}
	public ResultSet getRs() {
		return rs;
	}
	public void setRs(ResultSet rs) {
		this.rs = rs;
	}
	public StatsBean getStatsBean() {
		return statsBean;
	}
	public void setStatsBean(StatsBean statsBean) {
		this.statsBean = statsBean;
	}
	private CityBean cityBean;
	private String cityCode1;
	private StatsBean statsBean;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getcodeCount() {
		return codeCount;
	}
	public void setCountryCode(int  codeCount) {
		this.codeCount = codeCount;
	}
	public int getPopulation() {
		return population;
	}
	public void setPopulation(int population) {
		this.population = population;
	}
	public String getDistrict() {
		return district;
	}
	public void setDistrict(String district) {
		this.district = district;
	}
   public CityBean(){
	   System.out.println("reportNumber2 is ");
   }
	@PostConstruct
	public void init(){
		if(dbAcess == null){
			dbAcess = new DBAccessBean();
		}else{
		FacesContext context = FacesContext.getCurrentInstance();
		Map <String, Object> m = context.getExternalContext().getSessionMap();
		this.dbAcess = (DBAccessBean) m.get("dbAccess");
	}
		if(cityBean==null){
	    cityBean = new CityBean();
		}else{
			FacesContext context = FacesContext.getCurrentInstance();
			Map <String, Object> m = context.getExternalContext().getSessionMap();
			this.cityBean = (CityBean)m.get("cityBean");
			
		}	
	}
	public String showTable(){
		if(dbAcess == null){dbAcess = new DBAccessBean();}
		System.out.println(dbAcess);
		if(cityBean==null){cityBean = new CityBean();}
		String status="fail";
		setStatusCity(false);
		String query = "select * from city";
		try{
		status = dbAcess.executeQuery(query);
		rs = dbAcess.getResultSet();
		while(rs.next()){
			cityBean = new CityBean();
			cityBean.setName(rs.getString(2));
			cityBean.setCityCode1(rs.getString(3));
			cityBean.setDistrict(rs.getString(4));
			cityBean.setPopulation(rs.getInt(5));
			cityBeanList.add(cityBean);
		}
		status = "SUCCESS";
		}catch(Exception e){
			e.printStackTrace();
		}
		cityBean.setStatusCity(true);
		System.out.println(cityBeanList.size());
		return status;
	}
	
	public String getColumns(){
		String status = "fail";
		String query = "select * from city where 1=0";
		try{
			FacesContext context = FacesContext.getCurrentInstance();
			Map <String, Object> m = context.getExternalContext().getSessionMap();
			statsBean = new StatsBean();
			if(dbAcess==null){dbAcess = new DBAccessBean();}
		status = dbAcess.executeQuery(query);
		rs = dbAcess.getResultSet();
		System.out.println(statsBean);
		int count = rs.getMetaData().getColumnCount();
		for(int i=1;i<=count;i++){
			columnList.add(rs.getMetaData().getColumnLabel(i));
		}
		if(statsBean.getColumnList().isEmpty()){
		for(int i=1;i<=count;i++){
		    statsBean.getPermColList().add(rs.getMetaData().getColumnLabel(i));
		    System.out.println(statsBean.getPermColList().get(i-1));
		}
		status = "SUCCESS";
		}
		}catch(Exception e){
			e.printStackTrace();
		}
		return status;
	}
	
	
	public String getReport(){
		
		System.out.println("inside getReport");
		setStatusCity(false);
		String status= "SUCCESS";
		String query = "select sum(Population) as totalPopulation, count(CountryCode) as codeCount from city";
		try{
			System.out.println("before query execute");
			System.out.println(dbAcess);
			 status = dbAcess.executeQuery(query);
			 System.out.println("before rs");
			rs = dbAcess.getResultSet();
			rs.next();
			population = rs.getInt("totalPopulation");
			System.out.println(population+"bjnknk");
			codeCount = rs.getInt("codeCount");
			System.out.println(codeCount);
			setStatusCity(true);
			status = "SUCCESS";
		}catch(Exception e){
			setStatusCity(false);
			status = "fail";
			System.err.println(e);
		}
		return status;
	}
	public boolean isStatusCity() {
		return statusCity;
	}
	public void setStatusCity(boolean statusCity) {
		System.out.println("inside setStatusCity");
		this.statusCity = statusCity;
	}
	public CityBean getCityBean() {
		return cityBean;
	}
	public void setCityBean(CityBean cityBean) {
		this.cityBean = cityBean;
	}
	public List<CityBean> getCityBeanList() {
		return cityBeanList;
	}
	public void setCityBeanList(List<CityBean> cityBeanList) {
		this.cityBeanList = cityBeanList;
	}
	public String getCityCode1() {
		return cityCode1;
	}
	public void setCityCode1(String cityCode1) {
		this.cityCode1 = cityCode1;
	}
	public List<String> getColumnList() {
		return columnList;
	}
	public void setColumnList(List<String> columnList) {
		this.columnList = columnList;
	}
	public boolean isColumnBoolean() {
		return columnBoolean;
	}
	public void setColumnBoolean(boolean columnBoolean) {
		this.columnBoolean = columnBoolean;
	}
}
