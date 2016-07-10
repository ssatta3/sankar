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
public class CountryBean {
    private List<String> columnList = new ArrayList<String>();
	public List<String> getColumnList() {
		return columnList;
	}
	public void setColumnList(List<String> columnList) {
		this.columnList = columnList;
	}

	private CountryBean countryBean;
	private boolean columnBoolean;
	private long totalSurfaceArea;
	private long  totalPopulation;
	private int avgLifeExp;
	private int maxLifeExp;
	private int minLifeExp;
	private int maxGnp;
	private int minGnp;
	private int avgGnp;
	private List<CountryBean> countryBeanList= new ArrayList<CountryBean>();
	private ResultSet rs;
	private boolean statusCountry1;
	private boolean statusCountry2;
	DBAccessBean dbAccess;
	private String code;
	private String name;
	private String continent;
    private String region;
    private float surfaceArea;
    public float getSurfaceArea() {
		return surfaceArea;
	}
	public void setSurfaceArea(float surfaceArea) {
		this.surfaceArea = surfaceArea;
	}

	private int indepYear;
    public int getIndepYear() {
		return indepYear;
	}
	public void setIndepYear(int indepYear) {
		this.indepYear = indepYear;
	}

	private long population;
    public long getPopulation() {
		return population;
	}
	public void setPopulation(long population) {
		this.population = population;
	}

	private float lifeExpectancy;
    public float getLifeExpectancy() {
		return lifeExpectancy;
	}
	public void setLifeExpectancy(float lifeExpectancy) {
		this.lifeExpectancy = lifeExpectancy;
	}

	private float gnp;
    public float getGnp() {
		return gnp;
	}
	public void setGnp(float gnp) {
		this.gnp = gnp;
	}

	private float gnpOld;
    public float getGnpOld() {
		return gnpOld;
	}
	public void setGnpOld(float gnpOld) {
		this.gnpOld = gnpOld;
	}

	private String localName;
    public String getLocalName() {
		return localName;
	}
	public void setLocalName(String localName) {
		this.localName = localName;
	}

	private String govtForm;
    public String getGovtForm() {
		return govtForm;
	}
	public void setGovtForm(String govtForm) {
		this.govtForm = govtForm;
	}

	private String headOfState;
    public String getHeadOfState() {
		return headOfState;
	}
	public void setHeadOfState(String headOfState) {
		this.headOfState = headOfState;
	}

	private String capital;
    public String getCapital() {
		return capital;
	}
	public void setCapital(String capital) {
		this.capital = capital;
	}

	private String code2;
    public String getCode2() {
		return code2;
	}
	public void setCode2(String code2) {
		this.code2 = code2;
	}
	public String getRegion() {
		return region;
	}
	public void setRegion(String region) {
		this.region = region;
	}
	public String getContinent() {
		return continent;
	}
	public void setContinent(String continent) {
		this.continent = continent;
	}
	public String getName(){
    	return name;
    }
    public void setName(String name){
    	this.name=name;
    }
    public String getCode(){
    	return code;
    }
    public void setCode(String code){
    	this.code = code;
    }
	public boolean isStatusCountry1() {
		return statusCountry1;
	}

	public void setStatusCountry1(boolean statusCountry1) {
		this.statusCountry1 = statusCountry1;
	}
	public boolean isStatusCountry2() {
		return statusCountry2;
	}

	public void setStatusCountry2(boolean statusCountry2) {
		this.statusCountry2 = statusCountry2;
	}
	@PostConstruct
	public void init(){
		System.out.println("country bean created");
		if(dbAccess == null){
			dbAccess = new DBAccessBean();
		}else{
		FacesContext context = FacesContext.getCurrentInstance();
		Map <String, Object> m = context.getExternalContext().getSessionMap();
		this.dbAccess = (DBAccessBean) m.get("dbAccess");
	}
		if(countryBean==null){
			countryBean = new CountryBean();
		}else{
			FacesContext context = FacesContext.getCurrentInstance();
			Map <String, Object> m = context.getExternalContext().getSessionMap();
			this.countryBean = (CountryBean)m.get("countryBean");
		}	
	}
	public String showTable(){
		int i=0;
		String status = "fail";
		statusCountry1 = false;
		String query = "select * from country";
		try{
		  status = dbAccess.executeQuery(query);
		  System.out.println(status + "query executed");
		  rs = dbAccess.getResultSet();
		  while(rs.next()){
			  countryBean = new CountryBean();
			  countryBean.setCode(rs.getString(1));
			  System.out.println(countryBean.code);
			  countryBean.setName(rs.getString(2));
			  countryBean.setContinent(rs.getString(3));
			  System.out.println(countryBean.continent+ "got continent");
			  countryBean.setRegion(rs.getString(4));
			  countryBean.setSurfaceArea(rs.getFloat(5));
			  System.out.println(countryBean.surfaceArea+"sa");
			  countryBean.setIndepYear(rs.getInt(6));
			  countryBean.setPopulation(rs.getLong(7));
			  countryBean.setLifeExpectancy(rs.getFloat(8));
			  countryBean.setGnp(rs.getFloat(9));
			  countryBean.setGnpOld(rs.getFloat(10));
			  countryBean.setLocalName(rs.getString(11));
			  countryBean.setGovtForm(rs.getString(12));
			  countryBean.setHeadOfState(rs.getString(13));
			  countryBean.setCapital(rs.getString(14));
			  countryBean.setCode2(rs.getString(15));
			  System.out.println("reched end"+ i);
			  countryBeanList.add(countryBean);
			  i++;			 
		  }
		  setStatusCountry1(true);
		  status = "SUCCESS";
		}catch(Exception e){
			e.printStackTrace();
		}
		 return status;
	}
	
	public String getColumns(){
		String status = "fail";
		String query = "select * from country where 1=0";
		try{
			System.out.println("hi");
			System.out.println(dbAccess);
			dbAccess =new DBAccessBean();
		status = dbAccess.executeQuery(query);
		rs = dbAccess.getResultSet();
		int count = rs.getMetaData().getColumnCount();
		if(columnList.isEmpty()){
		for(int i=1;i<=count;i++){
			columnList.add(rs.getMetaData().getColumnLabel(i));
		}
		status = "SUCCESS";
		}
		}catch(Exception e){
			e.printStackTrace();
		}
		return status;
	}
	
	
	
	public String getReport1(){
		statusCountry1 = false;
		String status= "SUCCESS";
		String query = "select sum(Population) as totalPopulation, sum(SurfaceArea) as totalSA,max(LifeExpectancy) as maxLifeExp,min(LifeExpectancy) as minLifeExp,avg(LifeExpectancy) as avgLifeExp from country";
		try{
			 status = dbAccess.executeQuery(query);
			rs = dbAccess.getResultSet();
			rs.next();
			setTotalPopulation(rs.getLong("totalPopulation"));
			setTotalSurfaceArea(rs.getLong("totalSA"));
			setMaxLifeExp(rs.getInt("maxLifeExp"));
			setMinLifeExp(rs.getInt("minLifeExp"));
			setAvgLifeExp(rs.getInt("avgLifeExp"));
			statusCountry1 = true;
			status = "SUCCESS";
		}catch(Exception e){
			statusCountry1 = false;
			status = "fail";
			System.err.println(e);
		}
		return status;
	}
	public String getReport2(){
		statusCountry2 = false;
		String status= "SUCCESS";
		String query = "select sum(Population) as totalPopulation,sum(SurfaceArea) as totalSurfaceArea,max(GNP) as maxGnp, min(GNP) as minGnp,avg(GNP) as avgGnp from country";
		try{
			 status = dbAccess.executeQuery(query);
			rs = dbAccess.getResultSet();
			rs.next();
			setTotalPopulation(rs.getLong("totalPopulation"));
			setTotalSurfaceArea(rs.getLong("totalSurfaceArea"));
			setMaxGnp(rs.getInt("maxGnp"));
			setMinGnp(rs.getInt("minGnp"));
			setAvgGnp(rs.getInt("avgGnp"));
			statusCountry2 = true;
			status = "SUCCESS";
		}catch(Exception e){
			statusCountry2 = false;
			status = "fail";
			System.err.println(e);
		}
		return status;
	}

	public int getAvgLifeExp() {
		return avgLifeExp;
	}

	public void setAvgLifeExp(int avgLifeExp) {
		this.avgLifeExp = avgLifeExp;
	}

	public int getMaxLifeExp() {
		return maxLifeExp;
	}

	public void setMaxLifeExp(int maxLifeExp) {
		this.maxLifeExp = maxLifeExp;
	}

	public int getMinLifeExp() {
		return minLifeExp;
	}

	public void setMinLifeExp(int minLifeExp) {
		this.minLifeExp = minLifeExp;
	}

	public int getMaxGnp() {
		return maxGnp;
	}

	public void setMaxGnp(int maxGnp) {
		this.maxGnp = maxGnp;
	}

	public int getMinGnp() {
		return minGnp;
	}

	public void setMinGnp(int minGnp) {
		this.minGnp = minGnp;
	}

	public int getAvgGnp() {
		return avgGnp;
	}

	public void setAvgGnp(int avgGnp) {
		this.avgGnp = avgGnp;
	}

	public long getTotalPopulation() {
		return totalPopulation;
	}

	public void setTotalPopulation(long totalPopulation) {
		this.totalPopulation = totalPopulation;
	}

	public long getTotalSurfaceArea() {
		return totalSurfaceArea;
	}

	public void setTotalSurfaceArea(long totalSurfaceArea) {
		this.totalSurfaceArea = totalSurfaceArea;
	}

	public List<CountryBean> getCountryBeanList() {
		return countryBeanList;
	}

	public void setCountryBeanList(List<CountryBean> countryBeanList) {
		this.countryBeanList = countryBeanList;
	}

	public CountryBean getCountryBean() {
		return countryBean;
	}

	public void setCountryBean(CountryBean countryBean) {
		this.countryBean = countryBean;
	}
	public boolean isColumnBoolean() {
		return columnBoolean;
	}
	public void setColumnBoolean(boolean columnBoolean) {
		this.columnBoolean = columnBoolean;
	}

}
