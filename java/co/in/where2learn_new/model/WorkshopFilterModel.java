package co.in.where2learn_new.model;

import java.util.ArrayList;

public class WorkshopFilterModel {
 
	private ArrayList<String> calendarMonthFilter = new ArrayList<String>();
	private ArrayList<String> calendarYearFilter = new ArrayList<String>();
	public ArrayList<String> getCalendarMonthFilter() {
		return calendarMonthFilter;
	}
	public void setCalendarMonthFilter(ArrayList<String> calendarMonthFilter) {
		this.calendarMonthFilter = calendarMonthFilter;
	}
	public ArrayList<String> getCalendarYearFilter() {
		return calendarYearFilter;
	}
	public void setCalendarYearFilter(ArrayList<String> calendarYearFilter) {
		this.calendarYearFilter = calendarYearFilter;
	}
 
 
}
