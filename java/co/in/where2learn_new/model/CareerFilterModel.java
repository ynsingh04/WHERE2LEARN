package co.in.where2learn_new.model;

import java.util.ArrayList;

public class CareerFilterModel {

	private ArrayList<String> category = new ArrayList<String>();
	private ArrayList<String> subject = new ArrayList<String>();

	public ArrayList<String> getCategory() {
		return category;
	}

	public void setCategory(ArrayList<String> category) {
		this.category = category;
	}
	
	public ArrayList<String> getSubject() {
		return subject;
	}
	public void setSubject(ArrayList<String> subject) {
		this.subject = subject;
	}

}
