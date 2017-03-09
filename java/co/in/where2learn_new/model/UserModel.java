package co.in.where2learn_new.model;

public class UserModel {

	private String emailString;
	private String passwordString;
	private String confirmPasswordString;
	private String phoneString;
	private String nameString;
	
	private String userID;

	public String getEmailString() {
		return emailString;
	}

	public void setEmailString(String emailString) {
		this.emailString = emailString;
	}

	public String getPasswordString() {
		return passwordString;
	}

	public void setPasswordString(String passwordString) {
		this.passwordString = passwordString;
	}

	public String getConfirmPasswordString() {
		return confirmPasswordString;
	}

	public void setConfirmPasswordString(String confirmPasswordString) {
		this.confirmPasswordString = confirmPasswordString;
	}

	public String getPhoneString() {
		return phoneString;
	}

	public void setPhoneString(String phoneString) {
		this.phoneString = phoneString;
	}

	public String getNameString() {
		return nameString;
	}

	public void setNameString(String nameString) {
		this.nameString = nameString;
	}
	
	/**
	 * For Rating only
	 * @return
	 */
	public String getUserID() {
		return userID;
	}

	public void setUserID(String userID) {
		this.userID = userID;
	}

	public boolean isValid() {
		boolean isValid = true;

		/*if (emailString.length() == 0) {
			isValid = false;
		}*/
		if (passwordString.length() == 0) {
			isValid = false;
		}
		if (confirmPasswordString.length() == 0) {
			isValid = false;
		}
		/*if (phoneString.length() == 0) {
			isValid = false;
		}*/
		if (nameString.length() == 0) {
			isValid = false;
		}

		return isValid;

	}

	public boolean isPasswordAndConfirmMatch() {
		boolean isPasswordAndConfirmMatch = false;

		if (passwordString.equals(confirmPasswordString)) {
			isPasswordAndConfirmMatch = true;
		}

		return isPasswordAndConfirmMatch;
	}

	public boolean isLoginDataValid() {

		boolean isLoginDataValid = true;

		if (emailString.length() == 0) {
			isLoginDataValid = false;
		}
		if (passwordString.length() == 0) {
			isLoginDataValid = false;
		}

		return isLoginDataValid;
	}

}
