package co.in.where2learn_new.businesslogic;

import android.app.Activity;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;
import co.in.where2learn_new.R;
import co.in.where2learn_new.list.CareerlListActivity;
import co.in.where2learn_new.list.CoachinglListActivity;
import co.in.where2learn_new.list.CollegeListActivity;
import co.in.where2learn_new.list.HobbyListActivity;
import co.in.where2learn_new.list.ProfessionalCourseslListActivity;
import co.in.where2learn_new.list.SchoolListActivity;
import co.in.where2learn_new.list.TuitionListActivity;
import co.in.where2learn_new.list.WorkShopListActivity;
import co.in.where2learn_new.search.SearchActivity;
import co.in.where2learn_new.shop.ShopListActivity;
import co.in.where2learn_new.webservice.AreaListWebServiceCall;
import co.in.where2learn_new.webservice.SendCareerFiltersWebServiceCall;
import co.in.where2learn_new.webservice.SendCoachingFiltersWebServiceCall;
import co.in.where2learn_new.webservice.SendCollgeFiltersWebServiceCall;
import co.in.where2learn_new.webservice.SendHobbyFiltersWebServiceCall;
import co.in.where2learn_new.webservice.SendProfessionalFiltersWebServiceCall;
import co.in.where2learn_new.webservice.SendSchoolFiltersWebServiceCall;
import co.in.where2learn_new.webservice.SendSearchFilterWebService;
import co.in.where2learn_new.webservice.SendShopFiltersWebServiceCall;
import co.in.where2learn_new.webservice.SendTuitionFiltersWebServiceCall;
import co.in.where2learn_new.webservice.SendWorkShopFiltersWebServiceCall;

public class FilterListBusinessLogic {

	private String TAG = this.getClass().getName();

	private Activity activity;

	private AreaListWebServiceCall areaListWebServiceCall;

	private static SendSchoolFiltersWebServiceCall sendSchoolFiltersWebServiceCall;
	private static SendCollgeFiltersWebServiceCall sendCollgeFiltersWebServiceCall;

	private static SendTuitionFiltersWebServiceCall sendTuitionFiltersWebServiceCall;
	private static SendCoachingFiltersWebServiceCall sendCoachingFiltersWebServiceCall;

	private static SendCareerFiltersWebServiceCall sendCareerFiltersWebServiceCall;
	private static SendProfessionalFiltersWebServiceCall sendProfessionalFiltersWebServiceCall;
	private static SendWorkShopFiltersWebServiceCall sendWorkShopFiltersWebServiceCall;
	private static SendShopFiltersWebServiceCall sendShopFiltersWebServiceCall;

	private static SendHobbyFiltersWebServiceCall sendHobbyFiltersWebServiceCall;
	
	private static SendSearchFilterWebService sendSearchFiltersWebServiceCall;

	public FilterListBusinessLogic(Activity activity) {

		this.activity = activity;

	}

	public void getAreaList(String city, TextView professionalAreaFilterTextView) {

		boolean isArrayInitialized = initFilterAreaArray(city,
				professionalAreaFilterTextView);

		if (!isArrayInitialized) {
			Toast.makeText(activity,
					activity.getString(R.string.error_in_data_initialization),
					Toast.LENGTH_LONG).show();
		}

	}

	private boolean initFilterAreaArray(String city,
			TextView professionalAreaFilterTextView) {
		boolean isArrayInitialized = false;

		try {

			areaListWebServiceCall = new AreaListWebServiceCall(city,
					professionalAreaFilterTextView);
			areaListWebServiceCall.getDetails(activity);

			isArrayInitialized = true;

		} catch (Exception e) {

			Log.e(TAG, e.toString());

		}

		return isArrayInitialized;
	}

	public void invokeSchoolFilteredList(String city, String area, String age,
			String board, String sex, String type) {

		boolean isArrayInitialized = sendSchoolFilters(city, area, age, board,
				sex, type);

		if (!isArrayInitialized) {
			Toast.makeText(activity,
					activity.getString(R.string.error_in_data_initialization),
					Toast.LENGTH_LONG).show();
		}

	}

	private boolean sendSchoolFilters(String city, String area, String age,
			String board, String sex, String type) {
		boolean isArrayInitialized = false;

		try {

			initSchoolCallObject();
			sendSchoolFiltersWebServiceCall.sendSchoolFilters(activity, city,
					area, age, board, sex, type);

			isArrayInitialized = true;

		} catch (Exception e) {

			Log.e(TAG, e.toString());

		}

		return isArrayInitialized;
	}

	private void initSchoolCallObject() {

		sendSchoolFiltersWebServiceCall = new SendSchoolFiltersWebServiceCall(); 

	}

	public void callNextSchoolRange(SchoolListActivity activity) {

		try {

			sendSchoolFiltersWebServiceCall.nextRange();
			sendSchoolFiltersWebServiceCall.loadSchoolFilters(activity);

		} catch (Exception e) {

			Toast.makeText(activity,
					activity.getString(R.string.error_in_data_initialization),
					Toast.LENGTH_LONG).show();
			Log.e(TAG, e.toString());

		}

	}

	public void invokeCollegeFilteredList(String city, String course) {

		boolean isArrayInitialized = sendCourseFilters(city, course);

		if (!isArrayInitialized) {
			Toast.makeText(activity,
					activity.getString(R.string.error_in_data_initialization),
					Toast.LENGTH_LONG).show();
		}

	}
	

	
	private boolean sendCourseFilters(String city, String course) {
		boolean isArrayInitialized = false;

		try {

			sendCollgeFiltersWebServiceCall = new SendCollgeFiltersWebServiceCall();
			sendCollgeFiltersWebServiceCall.sendCollgeFilters(activity, city,
					course);

			isArrayInitialized = true;

		} catch (Exception e) {

			Log.e(TAG, e.toString());

		}

		return isArrayInitialized;
	}
	
	public void callNextCollegeRange(CollegeListActivity activity) {

		try {

			sendCollgeFiltersWebServiceCall.nextRange();
			sendCollgeFiltersWebServiceCall.loadCollegeFilters(activity);

		} catch (Exception e) {

			Toast.makeText(activity,
					activity.getString(R.string.error_in_data_initialization),
					Toast.LENGTH_LONG).show();
			Log.e(TAG, e.toString());

		}
		
	}


	public void invokeTuitionFilteredList(String cityList, String typeList,
			String areaList, String classList, String boardList,
			String subjectList) {

		boolean isArrayInitialized = sendTuitionFilters(cityList, typeList,
				areaList, classList, boardList, subjectList);

		if (!isArrayInitialized) {
			Toast.makeText(activity,
					activity.getString(R.string.error_in_data_initialization),
					Toast.LENGTH_LONG).show();
		}

	}

	private boolean sendTuitionFilters(String cityList, String typeList,
			String areaList, String classList, String boardList,
			String subjectList) {
		boolean isArrayInitialized = false;

		try {

			sendTuitionFiltersWebServiceCall = new SendTuitionFiltersWebServiceCall();
			sendTuitionFiltersWebServiceCall.sendTuitionFilters(activity,
					cityList, typeList, areaList, classList, boardList,
					subjectList);

			isArrayInitialized = true;

		} catch (Exception e) {

			Log.e(TAG, e.toString());

		}

		return isArrayInitialized;
	}


	public void callNextTuitionRange(TuitionListActivity activity) {

		try {

			sendTuitionFiltersWebServiceCall.nextRange();
			sendTuitionFiltersWebServiceCall.loadTuitionFilters(activity);

		} catch (Exception e) {

			Toast.makeText(activity,
					activity.getString(R.string.error_in_data_initialization),
					Toast.LENGTH_LONG).show();
			Log.e(TAG, e.toString());

		}
		
		
	}

	
	public void invokeCoachingFilteredList(String city, String area,
			String classes, String board, String subject) {

		boolean isArrayInitialized = sendCoachingFilters(city, area, classes,
				board, subject);

		if (!isArrayInitialized) {
			Toast.makeText(activity,
					activity.getString(R.string.error_in_data_initialization),
					Toast.LENGTH_LONG).show();
		}

	}

	private boolean sendCoachingFilters(String city, String area,
			String classes, String board, String subject) {
		boolean isArrayInitialized = false;

		try {

			sendCoachingFiltersWebServiceCall = new SendCoachingFiltersWebServiceCall();
			sendCoachingFiltersWebServiceCall.sendCoachingFilters(activity,
					city, area, classes, board, subject);

			isArrayInitialized = true;

		} catch (Exception e) {

			Log.e(TAG, e.toString());

		}

		return isArrayInitialized;
	}
	

	public void callNextCoachingRange(
			CoachinglListActivity activity) {
		
		try {

			sendCoachingFiltersWebServiceCall.nextRange();
			sendCoachingFiltersWebServiceCall.loadCoachingFilters(activity);

		} catch (Exception e) {

			Toast.makeText(activity,
					activity.getString(R.string.error_in_data_initialization),
					Toast.LENGTH_LONG).show();
			Log.e(TAG, e.toString());

		}
		
	}


	public void invokeHobbyFilteredList(String cityList, String ageList,
			String sexList, String category1List, String category2List,
			String areaList, String typeList) {

		boolean isArrayInitialized = sendHobbyFilters(cityList, ageList,
				sexList, category1List, category2List, areaList, typeList);

		if (!isArrayInitialized) {
			Toast.makeText(activity,
					activity.getString(R.string.error_in_data_initialization),
					Toast.LENGTH_LONG).show();
		}

	}

	private boolean sendHobbyFilters(String cityList, String ageList,
			String sexList, String category1List, String category2List,
			String areaList, String typeList) {

		boolean isArrayInitialized = false;

		try {

			sendHobbyFiltersWebServiceCall = new SendHobbyFiltersWebServiceCall();
			sendHobbyFiltersWebServiceCall.sendHobbyFilters(activity, cityList,
					ageList, sexList, category1List, category2List, areaList, typeList);

			isArrayInitialized = true;

		} catch (Exception e) {

			Log.e(TAG, e.toString());

		}

		return isArrayInitialized;

	}
	

	public void callNextHobbyRange(HobbyListActivity activity) {


		try {

			sendHobbyFiltersWebServiceCall.nextRange();
			sendHobbyFiltersWebServiceCall.loadHobbyFilters(activity);

		} catch (Exception e) {

			Toast.makeText(activity,
					activity.getString(R.string.error_in_data_initialization),
					Toast.LENGTH_LONG).show();
			Log.e(TAG, e.toString());

		}
		
	}


	public void invokeWorkShopFilteredList(String cityList,
			String calendarList, String calendarYearList) {

		boolean isArrayInitialized = sendWorkShopFilters(cityList,
				calendarList, calendarYearList);

		if (!isArrayInitialized) {
			Toast.makeText(activity,
					activity.getString(R.string.error_in_data_initialization),
					Toast.LENGTH_LONG).show();
		}

	}

	private boolean sendWorkShopFilters(String cityList, String calendarList,
			String calendarYearList) {
		boolean isArrayInitialized = false;

		try {

			sendWorkShopFiltersWebServiceCall = new SendWorkShopFiltersWebServiceCall();
			sendWorkShopFiltersWebServiceCall.sendWorkShopFilters(activity,
					cityList, calendarList, calendarYearList);

			isArrayInitialized = true;

		} catch (Exception e) {

			Log.e(TAG, e.toString());

		}

		return isArrayInitialized;

	}
	

	public void callNextWorkShopRange(WorkShopListActivity activity) {

		try {

			sendWorkShopFiltersWebServiceCall.nextRange();
			sendWorkShopFiltersWebServiceCall.loadWorkShopFilters(activity);

		} catch (Exception e) {

			Toast.makeText(activity,
					activity.getString(R.string.error_in_data_initialization),
					Toast.LENGTH_LONG).show();
			Log.e(TAG, e.toString());

		}
		
		
	}

	public void invokeCareerFilteredList(String cityList, String areaList,
			String categoryList, String subjectList) {

		boolean isArrayInitialized = sendCareerFilters(cityList, areaList,
				categoryList, subjectList);

		if (!isArrayInitialized) {
			Toast.makeText(activity,
					activity.getString(R.string.error_in_data_initialization),
					Toast.LENGTH_LONG).show();
		}

	}

	private boolean sendCareerFilters(String cityList, String areaList,
			String categoryList, String subjectList) {

		boolean isArrayInitialized = false;

		try {

			sendCareerFiltersWebServiceCall = new SendCareerFiltersWebServiceCall();
			sendCareerFiltersWebServiceCall.sendCareerFilters(activity,
					cityList, areaList, categoryList, subjectList);

			isArrayInitialized = true;

		} catch (Exception e) {

			Log.e(TAG, e.toString());

		}

		return isArrayInitialized;

	}
	


	public void callNextCareerRange(CareerlListActivity activity) {

		try {

			sendCareerFiltersWebServiceCall.nextRange();
			sendCareerFiltersWebServiceCall.loadCareerFilters(activity);

		} catch (Exception e) {

			Toast.makeText(activity,
					activity.getString(R.string.error_in_data_initialization),
					Toast.LENGTH_LONG).show();
			Log.e(TAG, e.toString());

		}
		
	}

	public void invokeProfessionalFilteredList(String cityList,
			String areaList, String courseList) {

		boolean isArrayInitialized = sendProfessionalFilters(cityList,
				areaList, courseList);

		if (!isArrayInitialized) {
			Toast.makeText(activity,
					activity.getString(R.string.error_in_data_initialization),
					Toast.LENGTH_LONG).show();
		}

	}

	private boolean sendProfessionalFilters(String cityList, String areaList,
			String courseList) {

		boolean isArrayInitialized = false;

		try {

			sendProfessionalFiltersWebServiceCall = new SendProfessionalFiltersWebServiceCall();
			sendProfessionalFiltersWebServiceCall.sendProfessionalFilters(
					activity, cityList, areaList, courseList);

			isArrayInitialized = true;

		} catch (Exception e) {

			Log.e(TAG, e.toString());

		}

		return isArrayInitialized;

	}
	


	public void callNextProfessionalRange(
			ProfessionalCourseslListActivity activity) {

		try {

			sendProfessionalFiltersWebServiceCall.nextRange();
			sendProfessionalFiltersWebServiceCall.loadProfessionalFilters(activity);

		} catch (Exception e) {

			Toast.makeText(activity,
					activity.getString(R.string.error_in_data_initialization),
					Toast.LENGTH_LONG).show();
			Log.e(TAG, e.toString());

		}
		
	}

	public void invokeShopFilteredList(String cityList, String areaList,
			String category, String subCategory) {

		boolean isArrayInitialized = sendShopFilters(cityList, areaList,
				category, subCategory);

		if (!isArrayInitialized) {
			Toast.makeText(activity,
					activity.getString(R.string.error_in_data_initialization),
					Toast.LENGTH_LONG).show();
		}

	}

	private boolean sendShopFilters(String cityList, String areaList,
			String category, String subCategory) {
		boolean isArrayInitialized = false;

		try {

			sendShopFiltersWebServiceCall = new SendShopFiltersWebServiceCall();
			sendShopFiltersWebServiceCall.sendShopFilters(activity, cityList,
					areaList, category, subCategory);

			isArrayInitialized = true;

		} catch (Exception e) {

			Log.e(TAG, e.toString());

		}

		return isArrayInitialized;

	}

	public void callNextShopRange(ShopListActivity activity) {
		try {

			sendShopFiltersWebServiceCall.nextRange();
			sendShopFiltersWebServiceCall.loadShopFilters(activity);

		} catch (Exception e) {

			Toast.makeText(activity,
					activity.getString(R.string.error_in_data_initialization),
					Toast.LENGTH_LONG).show();
			Log.e(TAG, e.toString());

		}
		
	}
	
	
	public void invokeSearchFilteredList(String title, String CategoryName, String CityName) {

		boolean isArrayInitialized = sendSearchFilters(title, CategoryName, CityName);

		if (!isArrayInitialized) {
			Toast.makeText(activity,
					activity.getString(R.string.error_in_data_initialization),
					Toast.LENGTH_LONG).show();
		}

	}

	private boolean sendSearchFilters(String title, String CategoryName, String CityName) {

		boolean isArrayInitialized = false;

		try {

			sendSearchFiltersWebServiceCall = new SendSearchFilterWebService();
			sendSearchFiltersWebServiceCall.sendSearchFilters(activity,
					title, CategoryName, CityName);

			isArrayInitialized = true;

		} catch (Exception e) {

			Log.e(TAG, e.toString());

		}

		return isArrayInitialized;

	}

	public void callNextSearchRange(SearchActivity activity) {
		try {

			sendSearchFiltersWebServiceCall.nextRange();
			sendSearchFiltersWebServiceCall.loadSearchFilters(activity);

		} catch (Exception e) {

			Toast.makeText(activity,
					activity.getString(R.string.error_in_data_initialization),
					Toast.LENGTH_LONG).show();
			Log.e(TAG, e.toString());

		}
		
	}
}
