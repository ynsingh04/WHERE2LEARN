package co.in.where2learn_new.model;

import java.util.ArrayList;

public class SchoolFilterModel {

	private ArrayList<String> ageFilter = new ArrayList<String>();
	private ArrayList<String> boardFilter = new ArrayList<String>();
	private ArrayList<String> sexFilter = new ArrayList<String>();
	private ArrayList<String> typeFilter = new ArrayList<String>();

	public ArrayList<String> getAgeFilter() {
		return ageFilter;
	}

	public void setAgeFilter(ArrayList<String> ageFilter) {
		this.ageFilter = ageFilter;
	}

	public ArrayList<String> getBoardFilter() {
		return boardFilter;
	}

	public void setBoardFilter(ArrayList<String> boardFilter) {
		this.boardFilter = boardFilter;
	}

	public ArrayList<String> getSexFilter() {
		return sexFilter;
	}

	public void setSexFilter(ArrayList<String> sexFilter) {
		this.sexFilter = sexFilter;
	}

	public ArrayList<String> getTypeFilter() {
		return typeFilter;
	}

	public void setTypeFilter(ArrayList<String> typeFilter) {
		this.typeFilter = typeFilter;
	}

}
