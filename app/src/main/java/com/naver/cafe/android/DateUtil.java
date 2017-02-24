package com.naver.cafe.android;

import java.text.DecimalFormat;
import java.util.Calendar;

public class DateUtil {

	
	public static String getDate() {
        Calendar cal = Calendar.getInstance();
        int yy = cal.get(Calendar.YEAR);
        int mo = cal.get(Calendar.MONTH) + 1;
        int dd = cal.get(Calendar.DAY_OF_MONTH);

        String yyy = null;
        String mmo = null;
        String ddd = null;

        yyy = "" + yy;
        if (mo < 10) mmo = "0" + mo;
        else mmo = "" + mo;
        
        if (dd < 10) ddd = "0" + dd;
        else ddd = "" + dd;

        String addDate = "" + yyy + mmo + ddd;
        return addDate;
    }
	
	public static String getDateFormat(String date) {
        return date.substring(0,4)+"-"+date.substring(4,6)+"-"+date.substring(6);
    }
	public static String getDateFormat(String date, String del) {
        return date.substring(0,4)+del+date.substring(4,6)+del+date.substring(6);
    }
	
	public static int getYmdInt(String date, int calType) {
		Calendar c = Calendar.getInstance();
		c.set(  Integer.parseInt(date.substring(0, 4)),
				Integer.parseInt(date.substring(4, 6)) - 1,
				Integer.parseInt(date.substring(6, 8)));
		return c.get(calType);
    }
	public static String getMonInt(String date) {
        return date.substring(0,4)+"-"+date.substring(4,6)+"-"+date.substring(6);
    }
	public static String getDayInt(String date) {
        return date.substring(0,4)+"-"+date.substring(4,6)+"-"+date.substring(6);
    }
	
	public static String getYear() {
        Calendar cal = Calendar.getInstance();
        int yy = cal.get(Calendar.YEAR);
        return ""+yy;
    }
	
	public static String getMonth() {
        Calendar cal = Calendar.getInstance();
        int mo = cal.get(Calendar.MONTH) + 1;
        return ""+mo;
    }
	public static String getCDate() {
        Calendar cal = Calendar.getInstance();
        int mo = cal.get(Calendar.DAY_OF_MONTH);
        return ""+mo;
    }
	
	
	
	public static String getYearPart(String str) {
		String rtn=str;
		int first = str.indexOf('-');
		int second = str.lastIndexOf('-');
		if(first !=-1 && second!=-1 && first!=second){
			rtn = str.substring(0,first);
		}
		return rtn;
	}
	
	public static String getMonthPart(String str) {
		String rtn=str;
		int first = str.indexOf('-');
		int second = str.lastIndexOf('-');
		if(first !=-1 && second!=-1 && first!=second){
			rtn = str.substring(first+1,second);
		}
		return rtn;
	}
	
	public static String getDatePart(String str) {
		String rtn=str;
		int first = str.indexOf('-'); 
		int second = str.lastIndexOf('-');
		if(first !=-1 && second!=-1 && first!=second){
			rtn = str.substring(second+1,str.length());
		}
		return rtn;
	} 
}
