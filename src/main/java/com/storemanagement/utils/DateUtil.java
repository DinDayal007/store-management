package com.storemanagement.utils;
import java.util.Calendar;
public class DateUtil {
	public static String getDate() {
		Calendar calendar = Calendar.getInstance();
		int hour = calendar.get(Calendar.HOUR_OF_DAY);
		int minute = calendar.get(Calendar.MINUTE);
		int second = calendar.get(Calendar.SECOND);
		int day = calendar.get(Calendar.DATE);
		int month = calendar.get(Calendar.MONTH) + 1;
//		int year = calendar.get(Calendar.YEAR);
		return (day + "" + month + "" + hour + "" + minute + ""
				+ second);
	}
}
