package com.hackathon.utilities;

import java.text.DateFormatSymbols;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateFunction {


	public static String selectCheckinDate() {
		Date dt1 = new Date();
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(dt1);
		calendar.add(Calendar.DATE, 1);
		dt1 = calendar.getTime();
		String tmroDate = new SimpleDateFormat("d").format(dt1);
		String startMonth = new SimpleDateFormat("M").format(dt1);
		String year = new SimpleDateFormat("YYYY").format(dt1);
		int month = Integer.parseInt(startMonth);
		//month = month - 1;
		
		DateFormatSymbols symbols = new DateFormatSymbols();
		String[] monthNames = symbols.getMonths();
		String monthName = monthNames[month - 1];
		
		String start = tmroDate + " " + monthName + " " + year;
		String checkinDate = start;
		return checkinDate;
	}


	public static String selectCheckoutDate() {
		Date dt2 = new Date();
		Calendar calendar2 = Calendar.getInstance();
		calendar2.setTime(dt2);
		calendar2.add(Calendar.DATE, 5);
		dt2 = calendar2.getTime();
		String endDay = new SimpleDateFormat("d").format(dt2);
		String endMonth = new SimpleDateFormat("M").format(dt2);
		String year2 = new SimpleDateFormat("YYYY").format(dt2);
		int month2 = Integer.parseInt(endMonth);
		//month2 = month2 - 1;
		
		DateFormatSymbols symbols = new DateFormatSymbols();
		String[] monthNames = symbols.getMonths();
		String monthName = monthNames[month2 - 1];
		
		String end = endDay + " " + monthName + " " + year2;
		
		String checkoutDate = end;
		return checkoutDate;
	}
}
