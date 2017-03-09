package co.in.where2learn_new.navigation;

import android.app.Activity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
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

public class ProfessionalCourseActivity extends AreaInterface{

	public static Activity activity; 
	
	private FilterListBusinessLogic filterListBusinessLogic;
	private ImageView professionalOKImageView;
	private ImageView professional_searchImageView;

	private View.OnClickListener professionalOKOnClickListener;
	private View.OnClickListener professional_searchOnClickListener;
 

	// store select/unselect // information // about the // values in the //
	// list
	public static boolean[] checkSelectedCityFilterTextView;
	public static boolean[] checkSelectedCourseFilterTextView;

	private ImageView professionalCityFilterImageView;
	private ImageView professionalAreaFilterImageView;
	private ImageView professionalCourseFilterImageView;

	public static TextView professionalCityFilterTextView;
	public static TextView professionalAreaFilterTextView;
	private static TextView professionalCourseFilterTextView;

	private View.OnClickListener professionalCityFilterOnClickListener;
	private View.OnClickListener professionalAreaFilterOnClickListener;
	private View.OnClickListener professionalCourseFilterOnClickListener;
 

	private DropDownListBaseClass dropDownListBaseClass;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_professional_course);

		activity = this; 

		initListener();
		initComponent();

		getActionBar().hide();
		changeFooter();
	}

	private void initListener() {

		professional_searchOnClickListener = new View.OnClickListener() {

			@Override
			public void onClick(View v) {

				onBackPressed();

			}
		};

		professionalCityFilterOnClickListener = new View.OnClickListener() {

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
						professionalCityFilterTextView);
				
				checkSelectedAreaFilterTextView = null;
				
				if(new ProfessionalCourseActivity().getArea()!="")
					professionalAreaFilterTextView.setText(activity.getString(R.string.school_area) + " - " + new ProfessionalCourseActivity().getArea());
					else
						professionalAreaFilterTextView.setText(activity.getString(R.string.school_area));

			}
		};

		professionalAreaFilterOnClickListener = new View.OnClickListener() {

			@Override
			public void onClick(View v) {

				String city = getCity();

				if (city.length() != 0 && !city.contains(",")) {
					filterListBusinessLogic.getAreaList(city,
							professionalAreaFilterTextView);

				} else if(city.contains(",")){
					Toast.makeText(
							activity,
							activity.getString(R.string.please_select_only_one_city),
							Toast.LENGTH_LONG).show();
				}
				else if(city.length() == 0){
					Toast.makeText(
							activity,
							activity.getString(R.string.select_a_city),
							Toast.LENGTH_LONG).show();
				}

			}
		};

		 

		 

		professionalCourseFilterOnClickListener = new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				
				/** Check data presence */
				if(AppConfig.ProfessionalFilterModel!=null)
				{
					if(AppConfig.ProfessionalFilterModel.getCourseFilter()==null)
					{
						Toast.makeText(
								activity,
								activity.getString(R.string.no_data_found),
								Toast.LENGTH_LONG).show();
						return;
					}
					else
					{
						if(AppConfig.ProfessionalFilterModel.getCourseFilter().size()==0)
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
						AppConfig.ProfessionalFilterModel.getCourseFilter(),
						professionalCourseFilterTextView);

			};
		};
 
		professionalOKOnClickListener = new View.OnClickListener() {

			@Override
			public void onClick(View v) {

				String cityList = getCity();
				String areaList = getArea();
				String courseList = getCourse();
				
				if(cityList.equalsIgnoreCase("")) {
					Toast.makeText(activity,
							activity.getString(R.string.select_a_city),
							Toast.LENGTH_LONG).show();
				}
				else if(cityList.contains(",")) {
					Toast.makeText(activity,
							activity.getString(R.string.please_select_only_one_city),
							Toast.LENGTH_LONG).show();
				}
				else if(courseList.equalsIgnoreCase("")) {
					Toast.makeText(activity,
							activity.getString(R.string.select_a_course),
							Toast.LENGTH_LONG).show();
				}
				else if(areaList.equalsIgnoreCase("") && !cityList.contains(",")) {
					Toast.makeText(activity,
							activity.getString(R.string.select_an_area),
							Toast.LENGTH_LONG).show();
				}				
				else {
					AppConfig.FiltersSelected = "<b>" + activity.getString(R.string.school_city) + "</b>" + "->" + cityList 
							+ "; <b>" + activity.getString(R.string.school_course) + "</b>" + "->" + courseList
							+ "; <b>" + activity.getString(R.string.school_area) + "</b>" + "->" + areaList;
					
					filterListBusinessLogic.invokeProfessionalFilteredList(
							cityList, areaList, courseList); 
				}
			}
		};

	}

	protected String getCourse() {
		String course = "";

		if(checkSelectedCourseFilterTextView == null)
		{
			return "";
		}

		for (int i = 0; i < AppConfig.ProfessionalFilterModel.getCourseFilter()
				.size(); i++) {
			if (checkSelectedCourseFilterTextView[i] == true) {
				
				if(AppConfig.ProfessionalFilterModel.getCourseFilter().get(i)
						.equalsIgnoreCase(activity.getString(R.string.select_all))) {
					
					course = AppConfig.ProfessionalFilterModel.getCourseFilter().get(i);
					break;
				}
				
				if(course.length() == 0) {
					course = AppConfig.ProfessionalFilterModel.getCourseFilter().get(i);
				} else {
					course += "," + AppConfig.ProfessionalFilterModel.getCourseFilter().get(i);
				}
			}
		}

		return course;

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

	public String getArea() {

		String area = "";

		if(checkSelectedAreaFilterTextView!=null){
			Log.e("Area", "not null");
//			Toast.makeText(activity, "Area : not null", Toast.LENGTH_LONG).show();
			for (int i = 0; i < AppConfig.AreaFilterModel.getAreaFilter().size(); i++) {
				Log.e("Area", "has items");
//				Toast.makeText(activity, "Area : " + "has items", Toast.LENGTH_LONG).show();
				if (checkSelectedAreaFilterTextView[i] == true) {
					
					if(AppConfig.AreaFilterModel.getAreaFilter().get(i)
							.equalsIgnoreCase(activity.getString(R.string.select_all))) {
						
						area = AppConfig.AreaFilterModel.getAreaFilter().get(i);
						break;
					}
					
					Log.e("Area", "is true");
//					Toast.makeText(activity, "Area : " + "is true", Toast.LENGTH_LONG).show();
					if(area.length() == 0) {
						area = AppConfig.AreaFilterModel.getAreaFilter().get(i);
					}
					else {
						area += "," + AppConfig.AreaFilterModel.getAreaFilter().get(i);
					}
				}
			}
		}		

		return area;
	}

	private void initComponent() {

		filterListBusinessLogic = new FilterListBusinessLogic(activity);

		dropDownListBaseClass = new DropDownListBaseClass();
		dropDownListBaseClass.setActivity(activity);

		initCheckSelection();
		
		((ScrollView) findViewById(R.id.professional_sv_container)).setScrollbarFadingEnabled(false);

		professional_searchImageView = (ImageView) findViewById(R.id.professional_searchImageView);
 
		professionalCityFilterTextView = (TextView) findViewById(R.id.professionalCityFilterTextView);
		professionalAreaFilterTextView = (TextView) findViewById(R.id.professionalAreaFilterTextView);
		professionalCourseFilterTextView = (TextView) findViewById(R.id.professionalCourseFilterTextView);
		
		if(!AppConfig.SelectedLocation.equalsIgnoreCase(""))
		professionalCityFilterTextView.setText(activity.getString(R.string.school_city) + " - " + getCity());

		professionalCityFilterImageView = (ImageView) findViewById(R.id.professionalCityFilterImageView);
		professionalAreaFilterImageView = (ImageView) findViewById(R.id.professionalAreaFilterImageView);
		professionalCourseFilterImageView = (ImageView) findViewById(R.id.professionalCourseFilterImageView);

		professionalCityFilterTextView
				.setOnClickListener(professionalCityFilterOnClickListener);
		professionalAreaFilterTextView
				.setOnClickListener(professionalAreaFilterOnClickListener);
		professionalCourseFilterTextView
				.setOnClickListener(professionalCourseFilterOnClickListener);

		professionalCityFilterImageView
				.setOnClickListener(professionalCityFilterOnClickListener);
		professionalAreaFilterImageView
				.setOnClickListener(professionalAreaFilterOnClickListener);
		professionalCourseFilterImageView
				.setOnClickListener(professionalCourseFilterOnClickListener);

		professionalOKImageView = (ImageView) findViewById(R.id.professionalOKImageView);

		professionalOKImageView
				.setOnClickListener(professionalOKOnClickListener);
		professional_searchImageView
				.setOnClickListener(professional_searchOnClickListener);
		
		professionalAreaFilterTextView.setMovementMethod(new ScrollingMovementMethod());
		professionalAreaFilterTextView.setSingleLine();
		professionalCityFilterTextView.setMovementMethod(new ScrollingMovementMethod());
		professionalCityFilterTextView.setSingleLine();
		professionalCourseFilterTextView.setMovementMethod(new ScrollingMovementMethod());
		professionalCourseFilterTextView.setSingleLine();

	}

	private void initCheckSelection() {

		checkSelectedCityFilterTextView = new boolean[AppConfig.CityFilterModel
				.getCityFilter().size()];

		checkSelectedCourseFilterTextView = new boolean[AppConfig.ProfessionalFilterModel
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
		if(tv == professionalCityFilterTextView) {
			if(new ProfessionalCourseActivity().getCity()!="")
			professionalCityFilterTextView.setText(activity.getString(R.string.school_city) + " - " + new ProfessionalCourseActivity().getCity());
			else
				professionalCityFilterTextView.setText(activity.getString(R.string.school_city));
		}
		else if(tv == professionalAreaFilterTextView) {
			checkSelectedAreaFilterTextView = DropDownListBaseClass.checkSelected;
			if(new ProfessionalCourseActivity().getArea()!="")
			professionalAreaFilterTextView.setText(activity.getString(R.string.school_area) + " - " + new ProfessionalCourseActivity().getArea());
			else
				professionalAreaFilterTextView.setText(activity.getString(R.string.school_area));
		}
		else if(tv == professionalCourseFilterTextView) {
			if(new ProfessionalCourseActivity().getCourse()!="")
			professionalCourseFilterTextView.setText(activity.getString(R.string.school_course) + " - " + new ProfessionalCourseActivity().getCourse());
			else
				professionalCourseFilterTextView.setText(activity.getString(R.string.school_course));
		}
	}
}
