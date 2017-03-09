package co.in.where2learn_new.model;

import java.util.ArrayList;

public class ShopFilterModel {

	private ArrayList<String> categoryFilter = new ArrayList<String>();
	private ArrayList<String> subCategoryFilter = new ArrayList<String>();

	public ArrayList<String> getCategoryFilter() {
		return categoryFilter;
	}

	public void setCategoryFilter(ArrayList<String> categoryFilter) {
		this.categoryFilter = categoryFilter;
	}

	public ArrayList<String> getSubCategoryFilter() {
		return subCategoryFilter;
	}

	public void setSubCategoryFilter(ArrayList<String> subCategoryFilter) {
		this.subCategoryFilter = subCategoryFilter;
	}

}
