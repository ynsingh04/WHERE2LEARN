package co.in.where2learn_new.navigation;

import android.app.Activity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;
import co.in.where2learn_new.R;
import co.in.where2learn_new.businesslogic.FilterListBusinessLogic;
import co.in.where2learn_new.config.AppConfig;
import co.in.where2learn_new.util.ChangeFooter;
import co.in.where2learn_new.util.DropDownListBaseClass;

/**
 * @author chitresh.verma
 *         <p>
 *         This activity handles the colleges filter.
 *         </p>
 */
public class CollegeActivity extends Activity {

	public static Activity activity;
 

	// store select/unselect // information // about the // values in the //
	// list
	public static boolean[] checkSelectedCityFilterTextView;
	public static boolean[] checkSelectedCourseFilterTextView;

	private ImageView collegeCityFilterImageView;
	private ImageView collegeCourseFilterImageView;

	public static TextView collegeCityFilterTextView;
	private static TextView collegeCourseFilterTextView;

	private FilterListBusinessLogic filterListBusinessLogic;

	private ImageView collegeOKImageView;

	private View.OnClickListener collegeCityFilterOnClickListener;
	private View.OnClickListener collegeCourseFilterOnClickListener;

	private View.OnClickListener collegeOKOnClickListener;
 

	private DropDownListBaseClass dropDownListBaseClass;

	private ImageView college_searchImageView;

	private View.OnClickListener college_searchOnClickListener;

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.app.Activity#onCreate(android.os.Bundle)
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_college);

		activity = this;

		initListener();
		initComponent();

		getActionBar().hide();
		changeFooter();
	}

	private void initListener() {

		college_searchOnClickListener = new View.OnClickListener() {

			@Override
			public void onClick(View v) {

				onBackPressed();
 

			}
		};

		collegeCityFilterOnClickListener = new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				
				/** Check data presence */
				if(AppConfig.CityFilterModel!=null)
				{
					if(AppConfig.CityFilterModel.getCityFilter()==null)
					{
						Toast.makeText(
								activity,
								activity.getString(R.string.no_data_found),
								Toast.LENGTH_LONG).show();
						return;
					}
					else
					{
						if(AppConfig.CityFilterModel.getCityFilter().size()==0)
						{
							Toast.makeText(
									activity,
									activity.getString(R.string.no_data_found),
									Toast.LENGTH_LONG).show();
							return;
						}
					}
				}
				else
				{
					Toast.makeText(
							activity,
							activity.getString(R.string.no_data_found),
							Toast.LENGTH_LONG).show();
					return;
				}
				/** End Check data presence */

				DropDownListBaseClass.checkSelected = checkSelectedCityFilterTextView;

				dropDownListBaseClass.initiatePopUp(
						AppConfig.CityFilterModel.getCityFilter(),
						collegeCityFilterTextView);

			}
		};

		collegeCourseFilterOnClickListener = new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				
				/** Check data presence */
				if(AppConfig.CollegeFilterModel!=null)
				{
					if(AppConfig.CollegeFilterModel.getCourseFilter()==null)
					{
						Toast.makeText(
								activity,
								activity.getString(R.string.no_data_found),
								Toast.LENGTH_LONG).show();
						return;
					}
					else
					{
						if(AppConfig.CollegeFilterModel.getCourseFilter().size()==0)
						{
							Toast.makeText(
									activity,
									activity.getString(R.string.no_data_found),
									Toast.LENGTH_LONG).show();
							return;
						}
					}
				}
				else
				{
					Toast.makeText(
							activity,
							activity.getString(R.string.no_data_found),
							Toast.LENGTH_LONG).show();
					return;
				}
				/** End Check data presence */

				DropDownListBaseClass.checkSelected = checkSelectedCourseFilterTextView;

				dropDownListBaseClass.initiatePopUp(
						AppConfig.CollegeFilterModel.getCourseFilter(),
						collegeCourseFilterTextView);

			};
		};

		 
		collegeOKOnClickListener = new View.OnClickListener() {

			@Override
			public void onClick(View v) {

				String city = getCity();
				String course = getCourse();
				
				if(city.equalsIgnoreCase("")) {
					Toast.makeText(activity,
							activity.getString(R.string.select_a_city),
							Toast.LENGTH_LONG).show();
				}
				else if(city.contains(",")) {
					Toast.makeText(activity,
							activity.getString(R.string.please_select_only_one_city),
							Toast.LENGTH_LONG).show();
				}
				else if(course.equalsIgnoreCase("")) {
					Toast.makeText(activity,
							activity.getString(R.string.select_a_course),
							Toast.LENGTH_LONG).show();
				}
				else {
					AppConfig.FiltersSelected = "<b>" + activity.getString(R.string.school_city) + "</b>" + "->" + city 
							+ "; <b>" + activity.getString(R.string.school_course) + "</b>" + "->" + course;
					
					filterListBusinessLogic.invokeCollegeFilteredList(city, course);
				}

			}
		};

	}

	protected String getCity() {

		String city = "";

		if(checkSelectedCityFilterTextView == null){
			return "";
		}

		for (int i = 0; i < AppConfig.CityFilterModel.getCityFilter().size(); i++) {
			/*if (checkSelectedCityFilterTextView[i] == true) {
				if (city.length() == 0) {
					city = AppConfig.CityFilterModel.getCityFilter().get(i);
				} else {
					city = "";
					break;
				}*/
			if (checkSelectedCityFilterTextView[i] == true) {
				if (city.length() == 0) {
					city = AppConfig.CityFilterModel.getCityFilter().get(i);
				} else {
					city += "," + AppConfig.CityFilterModel.getCityFilter().get(i);
				}
			}
		}

		return city;
	}

	protected String getCourse() {

		String course = "";
		
		if(checkSelectedCourseFilterTextView == null)
		{
			return "";
		}

		for (int i = 0; i < AppConfig.CollegeFilterModel.getCourseFilter()
				.size(); i++) {
			if (checkSelectedCourseFilterTextView[i] == true) {
				
				if(AppConfig.CollegeFilterModel.getCourseFilter().get(i)
						.equalsIgnoreCase(activity.getString(R.string.select_all))) {
					
					course = AppConfig.CollegeFilterModel.getCourseFilter().get(i);
					break;
				}
				
				if(course.length() == 0) {
					course = AppConfig.CollegeFilterModel.getCourseFilter().get(i);
				} else {
					course += "," + AppConfig.CollegeFilterModel.getCourseFilter().get(i);
				}
			}
		}

		return course;
	}

	private void initComponent() {

		initCheckSelection();
		filterListBusinessLogic = new FilterListBusinessLogic(activity);

		dropDownListBaseClass = new DropDownListBaseClass();
		dropDownListBaseClass.setActivity(activity);

		filterListBusinessLogic = new FilterListBusinessLogic(activity);
		
		((ScrollView) findViewById(R.id.college_sv_container)).setScrollbarFadingEnabled(false);

		college_searchImageView = (ImageView) findViewById(R.id.college_searchImageView);

		collegeCityFilterImageView = (ImageView) findViewById(R.id.collegeCityFilterImageView);
		collegeCourseFilterImageView = (ImageView) findViewById(R.id.collegeCourseFilterImageView);

		collegeCityFilterTextView = (TextView) findViewById(R.id.collegeCityFilterTextView);
		collegeCourseFilterTextView = (TextView) findViewById(R.id.collegeCourseFilterTextView);
		
		if(!AppConfig.SelectedLocation.equalsIgnoreCase(""))
		collegeCityFilterTextView.setText(activity.getString(R.string.school_city) + " - " + getCity());

		college_searchImageView
				.setOnClickListener(college_searchOnClickListener);
		
		collegeCityFilterTextView.setOnClickListener(collegeCityFilterOnClickListener);
		collegeCourseFilterTextView.setOnClickListener(collegeCourseFilterOnClickListener);

		collegeCityFilterImageView
				.setOnClickListener(collegeCityFilterOnClickListener);
		collegeCourseFilterImageView
				.setOnClickListener(collegeCourseFilterOnClickListener);
 

		collegeOKImageView = (ImageView) findViewById(R.id.collegeOKImageView);

		collegeOKImageView.setOnClickListener(collegeOKOnClickListener);
		
		collegeCityFilterTextView.setMovementMethod(new ScrollingMovementMethod());
		collegeCityFilterTextView.setSingleLine();
		collegeCourseFilterTextView.setMovementMethod(new ScrollingMovementMethod());
		collegeCourseFilterTextView.setSingleLine();

	}

	private void initCheckSelection() {

		checkSelectedCityFilterTextView = new boolean[AppConfig.CityFilterModel
				.getCityFilter().size()];
		checkSelectedCourseFilterTextView = new boolean[AppConfig.CollegeFilterModel
				.getCourseFilter().size()];

		// initialize all values of list to 'unselected' initially
		for (int i = 0; i < checkSelectedCityFilterTextView.length; i++) {
			checkSelectedCityFilterTextView[i] = false;
			if(AppConfig.CityFilterModel
					.getCityFilter().get(i).equalsIgnoreCase(AppConfig.SelectedLocation))
					checkSelectedCityFilterTextView[i] = true;
		}

		// initialize all values of list to 'unselected' initially
		for (int i = 0; i < checkSelectedCourseFilterTextView.length; i++) {
			checkSelectedCourseFilterTextView[i] = false;
		}
	}

	private void changeFooter() {
		ChangeFooter changeFooter = new ChangeFooter(activity);
		changeFooter.change();

	}
	
	/*@Override
	public void onWindowFocusChanged(boolean hasFocus) {
		// TODO Auto-generated method stub
		super.onWindowFocusChanged(hasFocus);		
		for (int i = 0; i < checkSelectedCityFilterTextView.length; i++) {
			if(AppConfig.CityFilterModel
					.getCityFilter().get(i).equalsIgnoreCase(AppConfig.SelectedLocation))
					checkSelectedCityFilterTextView[i] = true;
		}
	}*/
	
	public void changeCity(){
		for (int i = 0; i < checkSelectedCityFilterTextView.length; i++) {
			checkSelectedCityFilterTextView[i] = false;
			if(AppConfig.CityFilterModel
					.getCityFilter().get(i).equalsIgnoreCase(AppConfig.SelectedLocation))
					checkSelectedCityFilterTextView[i] = true;
		}
	}
	
	public static void setSelections(TextView tv){
		if(tv == collegeCityFilterTextView) {
			if(new CollegeActivity().getCity()!="")
			collegeCityFilterTextView.setText(activity.getString(R.string.school_city) + " - " + new CollegeActivity().getCity());
			else
				collegeCityFilterTextView.setText(activity.getString(R.string.school_city));
		}
		else if(tv == collegeCourseFilterTextView) {
			if(new CollegeActivity().getCourse()!="")
			collegeCourseFilterTextView.setText(activity.getString(R.string.school_course) + " - " + new CollegeActivity().getCourse());
			else
				collegeCourseFilterTextView.setText(activity.getString(R.string.school_course));
		}
	}
}
