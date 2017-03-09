package co.in.where2learn_new.businesslogic;

import android.app.Activity;
import android.util.Log;
import android.widget.Toast;
import co.in.where2learn_new.R;
import co.in.where2learn_new.config.AppConfig;
import co.in.where2learn_new.navigation.TalksActivity;
import co.in.where2learn_new.webservice.CareerWebServiceCall;
import co.in.where2learn_new.webservice.CoachingWebServiceCall;
import co.in.where2learn_new.webservice.CollegeWebServiceCall;
import co.in.where2learn_new.webservice.HobbyWebServiceCall;
import co.in.where2learn_new.webservice.NewsArrayListWebServiceCall;
import co.in.where2learn_new.webservice.ProfessionalCourseWebServiceCall;
import co.in.where2learn_new.webservice.SchoolWebServiceCall;
import co.in.where2learn_new.webservice.SearchGetCityWebService;
import co.in.where2learn_new.webservice.ShopWebServiceCall;
import co.in.where2learn_new.webservice.TalkListWebServiceCall;
import co.in.where2learn_new.webservice.TalkSendMessageWebServiceCall;
import co.in.where2learn_new.webservice.TutorWebServiceCall;
import co.in.where2learn_new.webservice.WorkShopWebServiceCall;

public class MainActivityBusinessLogic {

	private String TAG = this.getClass().getName();

	private Activity activity;

	private SchoolWebServiceCall schoolWebServiceCall;
	private CollegeWebServiceCall collegeWebServiceCall;
	private TutorWebServiceCall tutorWebServiceCall;
	private CoachingWebServiceCall coachingWebServiceCall;
	private HobbyWebServiceCall hobbyWebServiceCall;
	private WorkShopWebServiceCall workShopWebServiceCall;
	private CareerWebServiceCall careerWebServiceCall;
	private ProfessionalCourseWebServiceCall professionalCourseWebServiceCall;
	private ShopWebServiceCall shopWebServiceCall;

	private TalkListWebServiceCall getTalkListWebServiceCall;

	private NewsArrayListWebServiceCall newsArrayListWebServiceCall;
	private TalkSendMessageWebServiceCall talkSendMessageWebServiceCall;
	
	private SearchGetCityWebService searchWebServiceCall;

	public MainActivityBusinessLogic(Activity activity) {
		this.activity = activity;
	}

	public void invokeSchoolActivity() {

		boolean isArrayInitialized = initFilterSchoolArray();

		if (!isArrayInitialized) {
			Toast.makeText(activity,
					activity.getString(R.string.error_in_data_initialization),
					Toast.LENGTH_LONG).show();
		}

	}

	private boolean initFilterSchoolArray() {

		boolean isArrayInitialized = false;

		try {

			schoolWebServiceCall = new SchoolWebServiceCall();
			schoolWebServiceCall.getDetails(activity);

			isArrayInitialized = true;

		} catch (Exception e) {

			Log.e(TAG, e.toString());

		}

		return isArrayInitialized;

	}

	public void invokeCollegeActivity() {

		boolean isArrayInitialized = initFilterCollegeArray();

		if (!isArrayInitialized) {
			Toast.makeText(activity,
					activity.getString(R.string.error_in_data_initialization),
					Toast.LENGTH_LONG).show();
		}

	}

	private boolean initFilterCollegeArray() {
		boolean isArrayInitialized = false;

		try {

			collegeWebServiceCall = new CollegeWebServiceCall();
			collegeWebServiceCall.getDetails(activity);

			isArrayInitialized = true;

		} catch (Exception e) {

			Log.e(TAG, e.toString());

		}

		return isArrayInitialized;
	}
	
	public void invokeSearchGetCity() {

		boolean isArrayInitialized = initFilterSearchGetCityArray();

		if (!isArrayInitialized) {
			Toast.makeText(activity,
					activity.getString(R.string.error_in_data_initialization),
					Toast.LENGTH_LONG).show();
		}

	}

	private boolean initFilterSearchGetCityArray() {
		boolean isArrayInitialized = false;

		try {

			searchWebServiceCall = new SearchGetCityWebService();
			searchWebServiceCall.getDetails(activity);

			isArrayInitialized = true;

		} catch (Exception e) {

			Log.e(TAG, e.toString());

		}

		return isArrayInitialized;
	}

	public synchronized void invokeTutorActivity() {

		boolean isArrayInitialized = initFilterTutorArray();

		if (!isArrayInitialized) {
			Toast.makeText(activity,
					activity.getString(R.string.error_in_data_initialization),
					Toast.LENGTH_LONG).show();
		}

	}

	private boolean initFilterTutorArray() {
		boolean isArrayInitialized = false;

		try {

			tutorWebServiceCall = new TutorWebServiceCall();
			tutorWebServiceCall.getDetails(activity);

			isArrayInitialized = true;

		} catch (Exception e) {

			Log.e(TAG, e.toString());

		}

		return isArrayInitialized;
	}

	public synchronized void invokeCoachingActivity() {

		boolean isArrayInitialized = initFilterCoachingArray();

		if (!isArrayInitialized) {
			Toast.makeText(activity,
					activity.getString(R.string.error_in_data_initialization),
					Toast.LENGTH_LONG).show();
		}

	}

	private boolean initFilterCoachingArray() {
		boolean isArrayInitialized = false;

		try {

			coachingWebServiceCall = new CoachingWebServiceCall();
			coachingWebServiceCall.getDetails(activity);

			isArrayInitialized = true;

		} catch (Exception e) {

			Log.e(TAG, e.toString());

		}

		return isArrayInitialized;
	}

	public void invokeHobbyClassesActivity() {

		boolean isArrayInitialized = initFilterHobbyClassesArray();

		if (!isArrayInitialized) {
			Toast.makeText(activity,
					activity.getString(R.string.error_in_data_initialization),
					Toast.LENGTH_LONG).show();
		}

	}

	private boolean initFilterHobbyClassesArray() {
		boolean isArrayInitialized = false;

		try {

			hobbyWebServiceCall = new HobbyWebServiceCall();
			hobbyWebServiceCall.getDetails(activity);

			isArrayInitialized = true;

		} catch (Exception e) {

			Log.e(TAG, e.toString());

		}

		return isArrayInitialized;
	}

	public void invokeWorkshopAndEvents() {

		boolean isArrayInitialized = initFilterWorkShopArray();

		if (!isArrayInitialized) {
			Toast.makeText(activity,
					activity.getString(R.string.error_in_data_initialization),
					Toast.LENGTH_LONG).show();
		}

	}

	private boolean initFilterWorkShopArray() {
		boolean isArrayInitialized = false;

		try {

			workShopWebServiceCall = new WorkShopWebServiceCall();
			workShopWebServiceCall.getDetails(activity);

			isArrayInitialized = true;

		} catch (Exception e) {

			Log.e(TAG, e.toString());

		}

		return isArrayInitialized;
	}

	public void invokeCareerAndMore() {

		boolean isArrayInitialized = initFilterCareerArray();

		if (!isArrayInitialized) {
			Toast.makeText(activity,
					activity.getString(R.string.error_in_data_initialization),
					Toast.LENGTH_LONG).show();
		}

	}

	private boolean initFilterCareerArray() {
		boolean isArrayInitialized = false;

		try {

			careerWebServiceCall = new CareerWebServiceCall();
			careerWebServiceCall.getDetails(activity);

			isArrayInitialized = true;

		} catch (Exception e) {

			Log.e(TAG, e.toString());

		}

		return isArrayInitialized;
	}

	public void invokeProfessional() {

		boolean isArrayInitialized = initFilterProfessionalCourseArray();

		if (!isArrayInitialized) {
			Toast.makeText(activity,
					activity.getString(R.string.error_in_data_initialization),
					Toast.LENGTH_LONG).show();
		}

	}

	private boolean initFilterProfessionalCourseArray() {
		boolean isArrayInitialized = false;

		try {

			professionalCourseWebServiceCall = new ProfessionalCourseWebServiceCall();
			professionalCourseWebServiceCall.getDetails(activity);

			isArrayInitialized = true;

		} catch (Exception e) {

			Log.e(TAG, e.toString());

		}

		return isArrayInitialized;
	}

	public void invokeNews() {

		boolean isArrayInitialized = initNewsArray();

		if (!isArrayInitialized) {
			Toast.makeText(activity,
					activity.getString(R.string.error_in_data_initialization),
					Toast.LENGTH_LONG).show();
		}

	}

	private boolean initNewsArray() {
		boolean isArrayInitialized = false;

		try {

			newsArrayListWebServiceCall = new NewsArrayListWebServiceCall();
			newsArrayListWebServiceCall.getNewsArrayDetails(activity);

			isArrayInitialized = true;

		} catch (Exception e) {

			Log.e(TAG, e.toString());

		}

		return isArrayInitialized;
	}

	public void invokeTalks() {
		if (AppConfig.UserModel == null) {

			Toast.makeText(activity,
					activity.getString(R.string.please_login),
					Toast.LENGTH_LONG).show();

		} else {

			String userID = AppConfig.UserModel.getEmailString();
			
			boolean isArrayInitialized = getTalkList(userID);
	
			if (!isArrayInitialized) {
				Toast.makeText(activity,
						activity.getString(R.string.error_in_data_initialization),
						Toast.LENGTH_LONG).show();
			}
		}

	}

	private boolean getTalkList(String userID) {

		boolean isArrayInitialized = false;

		try {

			getTalkListWebServiceCall = new TalkListWebServiceCall(userID);
			getTalkListWebServiceCall.getTalkList(activity);

			isArrayInitialized = true;

		} catch (Exception e) {

			Log.e(TAG, e.toString());

		}

		return isArrayInitialized;

	}

	public void invokeShops() {

		boolean isArrayInitialized = initFilterShopArray();

		if (!isArrayInitialized) {
			Toast.makeText(activity,
					activity.getString(R.string.error_in_data_initialization),
					Toast.LENGTH_LONG).show();
		}

	}

	private boolean initFilterShopArray() {
		boolean isArrayInitialized = false;

		try {

			shopWebServiceCall = new ShopWebServiceCall();
			shopWebServiceCall.getDetails(activity);

			isArrayInitialized = true;

		} catch (Exception e) {

			Log.e(TAG, e.toString());

		}

		return isArrayInitialized;
	}

	public void invokeSendTalkMessage(String messageString) {
		
		messageString = processMessageString(messageString);

		boolean isSendMessage = sendMessage(messageString);

		if (!isSendMessage) {
			TalksActivity.messageEditText.setText("");
			Toast.makeText(activity,
					activity.getString(R.string.operation_failed),
					Toast.LENGTH_LONG).show();
		}

	}

	private String processMessageString(String messageString) {

		messageString = messageString.replace(" ", "%20");
		messageString = messageString.replace(".", "+.+");
		
		return messageString;
	}

	private boolean sendMessage(String messageString) {
		// TODO Auto-generated method stub

		boolean isMessageSend = false;

		try {

			if (AppConfig.UserModel == null) {

				Toast.makeText(activity,
						activity.getString(R.string.please_login),
						Toast.LENGTH_LONG).show();

			} else {

				String userID = AppConfig.UserModel.getEmailString();

				talkSendMessageWebServiceCall = new TalkSendMessageWebServiceCall(
						userID, messageString);
				talkSendMessageWebServiceCall.getDetails(activity);

				isMessageSend = true;
			}

		} catch (Exception e) {

			Log.e(TAG, e.toString());

		}

		return isMessageSend;
	}

}
