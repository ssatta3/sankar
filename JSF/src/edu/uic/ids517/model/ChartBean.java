package edu.uic.ids517.model;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.function.LineFunction2D;
import org.jfree.data.general.DatasetUtilities;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.time.Day;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import org.jfree.date.MonthConstants;


public class ChartBean {
	public static JFreeChart createPieChart(List<List<String>> scatterPoints,String colName) {
		
		
		
		DefaultPieDataset data = new DefaultPieDataset();
		for (int i =0 ; i<scatterPoints.size();i++){
			
			data.setValue(scatterPoints.get(i).get(0), Double.parseDouble(scatterPoints.get(i).get(1)));
			
		}
		JFreeChart chart = ChartFactory.createPieChart(colName, data, false, true, false);
		
		return chart;
	}

	public static JFreeChart createBar(List<List<String>> scatterPt,String Names) {
		DefaultCategoryDataset dataset = new DefaultCategoryDataset();
		for (int i =0 ; i<scatterPt.size();i++){
			String s="S"+String.valueOf(i+1);
			String c="C"+String.valueOf(i+1);
			
			dataset.addValue(Double.parseDouble(scatterPt.get(i).get(1)),scatterPt.get(i).get(0),c);
		
		}
		JFreeChart chart = ChartFactory.createBarChart3D(
				Names,
				"Category",
				"Value",
				dataset,
				PlotOrientation.VERTICAL,
				false,
				true,
				false
				);
		
		
		return chart;
	}
	
	public static JFreeChart createtimeSeries() {
		
		TimeSeries series = new TimeSeries("Random Data");
		Day current = new Day(1, MonthConstants.JANUARY, 2001);
		for (int i = 0; i < 100; i++) {
			series.add(current, Math.random() * 100);
			current = (Day) current.next();
		}
		XYDataset data = new TimeSeriesCollection(series);
		JFreeChart chart = ChartFactory.createTimeSeriesChart(
				"Time Series Chart", "Date", "Rate",
				data, false, true, false
				);
		return chart;
	}

}
