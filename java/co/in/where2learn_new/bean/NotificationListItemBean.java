package co.in.where2learn_new.bean;

public class NotificationListItemBean {

	private int imgViewIcon;
	private String title;
	private String classifiedID;
	private String categoryName;
	private String message;
	private String dataAndTime;
	
	private String startDateAndTime;
	private String endDateAndTime;

	public int getImgViewIcon() {
		return imgViewIcon;
	}

	public void setImgViewIcon(int imgViewIcon) {
		this.imgViewIcon = imgViewIcon;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getDataAndTime() {
		return dataAndTime;
	}

	public void setDataAndTime(String dataAndTime) {
		this.dataAndTime = dataAndTime;
	}

	public String getClassifiedID() {
		return classifiedID;
	}

	public void setClassifiedID(String classifiedID) {
		this.classifiedID = classifiedID;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
	
	public String getStartDateAndTime() {
		return startDateAndTime;
	}

	public void setStartDateAndTime(String startDateAndTime) {
		this.startDateAndTime = startDateAndTime;
	}
	
	public String getEndDateAndTime() {
		return endDateAndTime;
	}

	public void setEndDateAndTime(String endDateAndTime) {
		this.endDateAndTime = endDateAndTime;
	}

}
