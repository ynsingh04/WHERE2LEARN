package co.in.where2learn_new.model;

import java.util.ArrayList;

public class HobbyFilterModel {

	private ArrayList<String> type = new ArrayList<String>();
	private ArrayList<String> age = new ArrayList<String>();
	private ArrayList<String> sex = new ArrayList<String>();
	private ArrayList<String> category1 = new ArrayList<String>();
	private ArrayList<String> category2 = new ArrayList<String>();
	private ArrayList<HobbyFilterCategoryModel> category1Complete = new ArrayList<HobbyFilterCategoryModel>();
	private ArrayList<HobbyFilterCategoryModel> category2Complete = new ArrayList<HobbyFilterCategoryModel>();
	
	public ArrayList<String> getType() {
		return type;
	}
	public void setType(ArrayList<String> type) {
		this.type = type;
	}
	public ArrayList<String> getAge() {
		return age;
	}
	public void setAge(ArrayList<String> age) {
		this.age = age;
	}
	public ArrayList<String> getSex() {
		return sex;
	}
	public void setSex(ArrayList<String> sex) {
		this.sex = sex;
	}
	public ArrayList<String> getCategory1() {
		return category1;
	}
	public void setCategory1(ArrayList<String> category1) {
		this.category1 = category1;
	}
	public ArrayList<String> getCategory2() {
		return category2;
	}
	public void setCategory2(ArrayList<String> category2) {
		this.category2 = category2;
	}
	public ArrayList<HobbyFilterCategoryModel> getCategory1Complete() {
		return category1Complete;
	}
	public void setCategory1Complete(ArrayList<HobbyFilterCategoryModel> category1Complete) {
		this.category1Complete = category1Complete;
	}
	public ArrayList<HobbyFilterCategoryModel> getCategory2Complete() {
		return category2Complete;
	}
	public void setCategory2Complete(ArrayList<HobbyFilterCategoryModel> category2Complete) {
		this.category2Complete = category2Complete;
	}

	 

}
