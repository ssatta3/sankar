package com.BO;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import com.DAO.LoginAccess;

public class AlgoLogic 
{
	public long calDaysRemaining()
	{
		LoginAccess getDate = new LoginAccess();
		long diff = 0;
		String date = getDate.getDate();
		Date todayDate = new Date();
		SimpleDateFormat myFormat = new SimpleDateFormat("dd/MM/yyyy");
		try {
			 String date3 = myFormat.format(todayDate);
			    Date date2 = myFormat.parse(date3);
		    Date date1 = myFormat.parse(date);
		   
		     diff = date1.getTime() - date2.getTime();

		} catch (ParseException e) {
		    e.printStackTrace();
		}
		
		return TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);
	}
}
