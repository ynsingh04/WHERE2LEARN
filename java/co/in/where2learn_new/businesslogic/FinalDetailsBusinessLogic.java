package co.in.where2learn_new.businesslogic;

import android.app.Activity;
import android.util.Log;
import android.widget.Toast;
import co.in.where2learn_new.R;
import co.in.where2learn_new.bean.SearchListItemBean;
import co.in.where2learn_new.detailspage.CareerDetailsActivity;
import co.in.where2learn_new.webservice.CareerDetailsWebServiceCall;
import co.in.where2learn_new.webservice.CoachingDetailsWebServiceCall;
import co.in.where2learn_new.webservice.CollegeDetailsWebServiceCall;
import co.in.where2learn_new.webservice.HobbyDetailsWebServiceCall;
import co.in.where2learn_new.webservice.ProfessionalDetailsWebServiceCall;
import co.in.where2learn_new.webservice.SchoolDetailsWebServiceCall;
import co.in.where2learn_new.webservice.ShopDetailsWebServiceCall;
import co.in.where2learn_new.webservice.TuitionDetailsWebServiceCall;
import co.in.where2learn_new.webservice.WorkShopDetailsWebServiceCall;

public class FinalDetailsBusinessLogic {

	private String TAG = this.getClass().getName();

	private Activity activity;

	private SchoolDetailsWebServiceCall schoolDetailsWebServiceCall;
	private CollegeDetailsWebServiceCall collegeDetailsWebServiceCall;
	private TuitionDetailsWebServiceCall tuitionDetailsWebServiceCall;
	private CoachingDetailsWebServiceCall coachingDetailsWebServiceCall;
	private HobbyDetailsWebServiceCall hobbyDetailsWebServiceCall;
	private WorkShopDetailsWebServiceCall workShopDetailsWebServiceCall;
	private CareerDetailsWebServiceCall careerDetailsWebServiceCall;
	private ProfessionalDetailsWebServiceCall professionalDetailsWebServiceCall;

	private ShopDetailsWebServiceCall shopDetailsWebServiceCall;

	public FinalDetailsBusinessLogic(Activity activity) {

		this.activity = activity;

	}

	public void invokeSchoolDetailsPage(SearchListItemBean bean) {

		String id = bean.getClassified_id();

		boolean isDetailsInitialized = initFilterSchoolDetails(id);

		if (!isDetailsInitialized) {
			Toast.makeText(activity,
					activity.getString(R.string.error_in_data_initialization),
					Toast.LENGTH_LONG).show();
		}

	}

	private boolean initFilterSchoolDetails(String id) {
		boolean isDetailsInitialized = false;

		try {

			schoolDetailsWebServiceCall = new SchoolDetailsWebServiceCall(id);
			schoolDetailsWebServiceCall.getDetails(activity);

			isDetailsInitialized = true;

		} catch (Exception e) {

			Log.e(TAG, e.toString());

		}

		return isDetailsInitialized;
	}

	public void invokeCollegeDetailsPage(SearchListItemBean bean) {

		String id = bean.getClassified_id();

		boolean isDetailsInitialized = initFilterCollegeDetails(id);

		if (!isDetailsInitialized) {
			Toast.makeText(activity,
					activity.getString(R.string.error_in_data_initialization),
					Toast.LENGTH_LONG).show();
		}

	}

	private boolean initFilterCollegeDetails(String id) {
		boolean isDetailsInitialized = false;

		try {

			collegeDetailsWebServiceCall = new CollegeDetailsWebServiceCall(id);
			collegeDetailsWebServiceCall.getDetails(activity);

			isDetailsInitialized = true;

		} catch (Exception e) {

			Log.e(TAG, e.toString());

		}

		return isDetailsInitialized;
	}

	public void invokeTuitionDetailsPage(SearchListItemBean bean) {

		String id = bean.getClassified_id();

		boolean isDetailsInitialized = initFilterTuitionDetails(id);

		if (!isDetailsInitialized) {
			Toast.makeText(activity,
					activity.getString(R.string.error_in_data_initialization),
					Toast.LENGTH_LONG).show();
		}

	}

	private boolean initFilterTuitionDetails(String id) {

		boolean isDetailsInitialized = false;

		try {

			tuitionDetailsWebServiceCall = new TuitionDetailsWebServiceCall(id);
			tuitionDetailsWebServiceCall.getDetails(activity);

			isDetailsInitialized = true;

		} catch (Exception e) {

			Log.e(TAG, e.toString());

		}
		return isDetailsInitialized;
	}

	public void invokeCoachingDetailsPage(SearchListItemBean bean) {

		String id = bean.getClassified_id();

		boolean isDetailsInitialized = initFilterCoachingDetails(id);

		if (!isDetailsInitialized) {
			Toast.makeText(activity,
					activity.getString(R.string.error_in_data_initialization),
					Toast.LENGTH_LONG).show();
		}

	}

	private boolean initFilterCoachingDetails(String id) {

		boolean isDetailsInitialized = false;

		try {

			coachingDetailsWebServiceCall = new CoachingDetailsWebServiceCall(id);
			coachingDetailsWebServiceCall.getDetails(activity);

			isDetailsInitialized = true;

		} catch (Exception e) {

			Log.e(TAG, e.toString());

		}
		
		return isDetailsInitialized;
		
	}

	public void invokeHobbyDetailsPage(SearchListItemBean bean) {

		String id = bean.getClassified_id();

		boolean isDetailsInitialized = initFilterHobbyDetails(id);

		if (!isDetailsInitialized) {
			Toast.makeText(activity,
					activity.getString(R.string.error_in_data_initialization),
					Toast.LENGTH_LONG).show();
		}

	}

	private boolean initFilterHobbyDetails(String id) {

		boolean isDetailsInitialized = false;

		try {

			hobbyDetailsWebServiceCall = new HobbyDetailsWebServiceCall(id);
			hobbyDetailsWebServiceCall.getDetails(activity);

			isDetailsInitialized = true;

		} catch (Exception e) {

			Log.e(TAG, e.toString());

		}
		return isDetailsInitialized;
	}

	public void invokeWorkShopDetailsPage(SearchListItemBean bean) {

		String id = bean.getClassified_id();

		boolean isDetailsInitialized = initFilterWorkShopDetails(id);

		if (!isDetailsInitialized) {
			Toast.makeText(activity,
					activity.getString(R.string.error_in_data_initialization),
					Toast.LENGTH_LONG).show();
		}

	}

	private boolean initFilterWorkShopDetails(String id) {

		boolean isDetailsInitialized = false;

		try {

			workShopDetailsWebServiceCall = new WorkShopDetailsWebServiceCall(id);
			workShopDetailsWebServiceCall.getDetails(activity);

			isDetailsInitialized = true;

		} catch (Exception e) {

			Log.e(TAG, e.toString());

		}
		return isDetailsInitialized;
	}

	public void invokeCareerDetailsPage(SearchListItemBean bean) {

		String id = bean.getClassified_id();
		
		if(bean.getTxtViewCategory()!=null)
			CareerDetailsActivity.CATEGORY = bean.getTxtViewCategory();

		boolean isDetailsInitialized = initFilterCareerDetails(id);

		if (!isDetailsInitialized) {
			Toast.makeText(activity,
					activity.getString(R.string.error_in_data_initialization),
					Toast.LENGTH_LONG).show();
		}

	}

	private boolean initFilterCareerDetails(String id) {

		boolean isDetailsInitialized = false;

		try {

			careerDetailsWebServiceCall = new CareerDetailsWebServiceCall(id);
			careerDetailsWebServiceCall.getDetails(activity);

			isDetailsInitialized = true;

		} catch (Exception e) {

			Log.e(TAG, e.toString());

		}
		return isDetailsInitialized;
	}

	public void invokeProfessionalDetailsPage(SearchListItemBean bean) {

		String id = bean.getClassified_id();

		boolean isDetailsInitialized = initFilterProfessionalDetails(id);

		if (!isDetailsInitialized) {
			Toast.makeText(activity,
					activity.getString(R.string.error_in_data_initialization),
					Toast.LENGTH_LONG).show();
		}

	}

	private boolean initFilterProfessionalDetails(String id) {


		boolean isDetailsInitialized = false;

		try {

			professionalDetailsWebServiceCall = new ProfessionalDetailsWebServiceCall(id);
			professionalDetailsWebServiceCall.getDetails(activity);

			isDetailsInitialized = true;

		} catch (Exception e) {

			Log.e(TAG, e.toString());

		}
		return isDetailsInitialized;
	}

	public void invokeShopDetailsPage(SearchListItemBean bean) {

		String id = bean.getClassified_id();

		boolean isDetailsInitialized = initFilterShopDetails(id);

		if (!isDetailsInitialized) {
			Toast.makeText(activity,
					activity.getString(R.string.error_in_data_initialization),
					Toast.LENGTH_LONG).show();
		}
		
	}

	private boolean initFilterShopDetails(String id) {
		boolean isDetailsInitialized = false;

		try {

			shopDetailsWebServiceCall = new ShopDetailsWebServiceCall(id);
			shopDetailsWebServiceCall.getDetails(activity);

			isDetailsInitialized = true;

		} catch (Exception e) {

			Log.e(TAG, e.toString());

		}
		return isDetailsInitialized;
	}

}
