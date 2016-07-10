package com.manipulate;

import java.util.ArrayList;
import java.util.HashMap;

import com.vo.Employee;
import com.vo.Items;

public class ManipulateClass {

	public static HashMap<String,String> getWinnerList(HashMap<Employee, Items> empItemMap) {
		final HashMap<String,String> WinnerList = new HashMap<String,String>();
		final HashMap<String, ArrayList<String>> map = new HashMap<String, ArrayList<String>>();
		for (final Employee employee : empItemMap.keySet()) {
			final Items item = empItemMap.get(employee);
			final ArrayList<String> list = new ArrayList<String>();
			for(int i=0; i < employee.getPointsAchived(); i++){
				list.add(employee.getEmployeeId()+":"+i+":"+employee.getEmployeeName());
			}
			final String itemIdDesc = item.getItemId()+":"+item.getDescription();
			if(map.containsKey(itemIdDesc)){	
				list.addAll(map.get(itemIdDesc));
			}
			map.put(itemIdDesc, list);
		}
		for (String itemIdDesc : map.keySet()) {
			final ArrayList<String> list = map.get(itemIdDesc);
			String temp = list.get((int) (Math.random()*(list.size())));
			WinnerList.put(itemIdDesc, temp.split(":")[0]+":"+temp.split(":")[2]);
		}
		return WinnerList;
	}
}
