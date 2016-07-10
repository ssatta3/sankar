package edu.uic.ids517.controller;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;

import edu.uic.ids517.model.ChartBean;
import edu.uic.ids517.model.DBAccessBean;

@ManagedBean
@RequestScoped
public class ActionChartBean {
private List<String> columnNames= new ArrayList<String>();
private ResultSet rs;
private String sqlQuer;
private int tableId;
private List<List<String>> data;
private ArrayList<String> dataList = new ArrayList<String>();


public String getXySeriesChartFile() {
	return xySeries;
}

public void setXySeriesChartFile(String xySeriesChartFile) {
	this.xySeries = xySeriesChartFile;
}

public String getPieChartFile() {
	return pieChartFile;
}

public void setPieChartFile(String pieChartFile) {
	this.pieChartFile = pieChartFile;
}

private List<String> chartName=new ArrayList<String>();
private List<String> piechartPath=new ArrayList<String>();
String pieChartFile = null;
private boolean renderPie;
private boolean renderBar;
private boolean rendered;
private String xySeries=null;




String barChartFile = null;
public String getBarChartFile() {
	return barChartFile;
}

public void setBarChartFile(String barChartFile) {
	this.barChartFile = barChartFile;
}






String timeSeriesChartFile = null;
public String getTimeSeriesChartFile() {
	return timeSeriesChartFile;
}

public void setTimeSeriesChartFile(String timeSeriesChartFile) {
	this.timeSeriesChartFile = timeSeriesChartFile;
}

public List<String> getPiechartFilePath() {
	return piechartPath;
}

public void setPiechartFilePath(List<String> piechartFilePath) {
	this.piechartPath = piechartFilePath;
}
private List<String> barchartFilePath=new ArrayList<String>();

public List<String> getBarchartFilePath() {
	return barchartFilePath;
}
public List<String> getColumnNameList() {
	return columnNames;
}

public void setColumnNameList(List<String> columnNameList) {
	this.columnNames = columnNameList;
}






public ResultSet getRs() {
	return rs;
}






public void setRs(ResultSet rs) {
	this.rs = rs;
}






public String getQuery() {
	return sqlQuer;
}


public ActionTableBean getActionTableBean() {
	return actionTableBean;
}






public void setActionTableBean(ActionTableBean actionTableBean) {
	this.actionTableBean = actionTableBean;
}





public void setQuery(String query) {
	this.sqlQuer = query;
}

public List<List<String>> getData() {
	return data;
}






public void setData(List<List<String>> data) {
	this.data = data;
}






public ArrayList<String> getDataList() {
	return dataList;
}






public void setDataList(ArrayList<String> dataList) {
	this.dataList = dataList;
}






public List<String> getChartName() {
	return chartName;
}

private DBAccessBean dBAccessBean;
private String Names;




public void setChartName(List<String> chartName) {
	this.chartName = chartName;
}

public boolean isRenderedPie() {
	return renderPie;
}

public void setRenderedPie(boolean renderedPie) {
	this.renderPie = renderedPie;
}

public boolean isRenderedBar() {
	return renderBar;
}

public void setRenderedBar(boolean renderedBar) {
	this.renderBar = renderedBar;
}

public boolean isRendered() {
	return rendered;
}

public void setRendered(boolean rendered) {
	this.rendered = rendered;
}

public void setBarchartFilePath(List<String> barchartFilePath) {
	this.barchartFilePath = barchartFilePath;
}

@ManagedProperty(value="#{actionTableBean}")
private ActionTableBean actionTableBean;




public String getNames() {
	return Names;
}

public void setNames(String names) {
	Names = names;
}

public String getChart(){
	if(dBAccessBean==null){dBAccessBean = new DBAccessBean();}
	tableId = actionTableBean.getTableId();
	String status = "fail";
	int m = Integer.min(200, columnNames.size());
	for(int j=0;j<m;j++){
		if(tableId==1){
			sqlQuer = "Select "+columnNames.get(j)+", count(*) from "+"city"+" group by "+columnNames.get(j)+";";
			data  =dBAccessBean.getData(sqlQuer);
			this.Names=columnNames.get(j);
			String str=getChart(j);
			
		}
		else if(tableId==2){
			sqlQuer = "Select "+columnNames.get(j)+", count(*) from "+"country"+" group by "+columnNames.get(j)+";";
			data  =dBAccessBean.getData(sqlQuer);
			this.Names=columnNames.get(j);
			String str=getChart(j);
		}else if(tableId==3){
			sqlQuer = "Select "+columnNames.get(j)+", count(*) from "+"countrylanguage"+" group by "+columnNames.get(j)+";";
			data  =dBAccessBean.getData(sqlQuer);
			this.Names=columnNames.get(j);
			String str=getChart(j);
		}
		}
	
	return "SUCCESS";
}

public String getChart(int n) {
	
	FacesContext context = FacesContext.getCurrentInstance();
	
	String path = context.getExternalContext().getRealPath("/jfreeImages");
	System.out.println("path: " + path);
	JFreeChart chart = null;
	File output = null;
	ByteArrayOutputStream s = new ByteArrayOutputStream();
	String webContent = "/jfreeImages/";


	try {
		for(int k=0;k<chartName.size();k++){
			System.out.println("chart :" +chartName.get(k) );
			if(chartName.get(k).equals("Pie")){


				String string="pieChart"+String.valueOf(n)+".png";
				output = new File(path + "/"+string);

				
				chart = ChartBean.createPieChart(data,Names);
				System.out.println("Chart::"+chart);
				ChartUtilities.saveChartAsPNG(output, chart, 400, 300);
				ChartUtilities.writeChartAsPNG(s, chart, 400, 300);
				
				pieChartFile = webContent + "pieChart"+String.valueOf(n)+".png";
			

				piechartPath.add(pieChartFile);
				setRenderedPie(true);
			}
			else if (chartName.get(k).equals("Bar")){
				
				String str1="barChart"+String.valueOf(n)+".png";
				output = new File(path+"/"+str1);
				chart = ChartBean.createBar(data,Names);
				ChartUtilities.saveChartAsPNG(output, chart, 400, 300);
				
				barChartFile = webContent + "barChart"+String.valueOf(n)+".png";
				barchartFilePath.add(barChartFile);
				setRenderedBar(true);
			}
			else if (chartName.get(k).equals("Scatter")){/*
			
			 */}
			else{
				output = new File(path+"/"+"timeSeries.png");
				chart = ChartBean.createtimeSeries();
				ChartUtilities.saveChartAsPNG(output, chart, 400, 300);
			
				timeSeriesChartFile = webContent + "timeSeries.png";

			}
		}
	} catch (IOException e) {
	
		e.printStackTrace();
	}

	setRendered(true);
	return "SUCCESS";
}









}
