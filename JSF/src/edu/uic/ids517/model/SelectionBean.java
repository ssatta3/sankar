package edu.uic.ids517.model;


import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean
@SessionScoped
public class SelectionBean {
private int tableId;
private String columnName;
private String databaseId;

public int getTableId() {
	return tableId;
}
public void setTableId(int tableId) {
	this.tableId = tableId;
}
public String getColumnName() {
	return columnName;
}
public void setColumnName(String columnName) {
	System.out.println("columnnameset");
	this.columnName = columnName;
}
public String getDatabaseId() {
	return databaseId;
}
public void setDatabaseId(String databaseId) {
	this.databaseId = databaseId;
}


}
