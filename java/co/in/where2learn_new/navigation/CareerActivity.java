package co.in.where2learn_new.navigation;

import android.app.Activity;
import android.os.Bundle;
import android.text.Html;
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

public class CareerActivity extends Activity {

	private static String TAG = "CareerActivity"; 
	
	public static Activity activity;

	private ImageView careerOKImageView;

	private View.OnClickListener careerOKOnClickListener;
	private View.OnClickListener career_searchOnClickListener;

	private FilterListBusinessLogic filterListBusinessLogic;
 

	// store select/unselect // information // about the // values in the //
	// list
	public static boolean[] checkSelectedCityFilterTextView;
	public static boolean[] checkSelectedAreaFilterTextView;
	public static boolean[] checkSelectedCategoryFilterTextView;
	public static boolean[] checkSelectedSubjectFilterTextView;

	private ImageView careerCityFilterImageView;
	private ImageView careerAreaFilterImageView;
	private ImageView careerCategoryFilterImageView;
	private ImageView careerSubjectFilterImageView;

	private ImageView career_searchImageView;

	public static TextView careerCityFilterTextView;
	public static TextView careerAreaFilterTextView;
	private static TextView careerCategoryFilterTextView;
	private static TextView careerSubjectFilterTextView;

	private View.OnClickListener careerCityFilterOnClickListener;
	private View.OnClickListener careerAreaFilterOnClickListener;
	private View.OnClickListener careerCategoryFilterOnClickListener;
	private View.OnClickListener careerSubjectFilterOnClickListener;

	private DropDownListBaseClass dropDownListBaseClass;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_career);

		activity = this;

		initListener();
		initComponent();

		getActionBar().hide();
		changeFooter();
	}

	private void initListener() {

		career_searchOnClickListener = new View.OnClickListener() {

			@Override
			public void onClick(View v) {

				onBackPressed();

			}
		};

		careerCityFilterOnClickListener = new View.OnClickListener() {

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
						careerCityFilterTextView);
				
				checkSelectedAreaFilterTextView = null;
				
				if(new CareerActivity().getArea()!="")
					careerAreaFilterTextView.setText(activity.getString(R.string.school_area) + " - " + new CareerActivity().getArea());
					else
						careerAreaFilterTextView.setText(activity.getString(R.string.school_area));

			}
		};

		careerCategoryFilterOnClickListener = new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				
				/** Check data presence */
				if(AppConfig.CareerFilterModel!=null)
				{
					if(AppConfig.CareerFilterModel.getCategory()==null)
					{
						Toast.makeText(
								activity,
								activity.getString(R.string.no_data_found),
								Toast.LENGTH_LONG).show();
						return;
					}
					else
					{
						if(AppConfig.CareerFilterModel.getCategory().size()==0)
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
				
				if(AppConfig.CareerFilterModel.getCategory()==null)
				{
					Toast.makeText(
							activity,
							activity.getString(R.string.no_data_found),
							Toast.LENGTH_LONG).show();
					return;
				}
 
				DropDownListBaseClass.checkSelected = checkSelectedCategoryFilterTextView;

				dropDownListBaseClass.initiatePopUp(
						AppConfig.CareerFilterModel.getCategory(),
						careerCategoryFilterTextView);

			}
		};

		careerAreaFilterOnClickListener = new View.OnClickListener() {

			@Override
			public void onClick(View v) {

				String city = getCity();

				if (city.length() != 0 && !city.contains(",")) {
					filterListBusinessLogic.getAreaList(city, careerAreaFilterTextView);

				 
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
		
		careerSubjectFilterOnClickListener = new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				
				/** Check data presence */
				if(AppConfig.CareerFilterModel!=null)
				{
					if(AppConfig.CareerFilterModel.getSubject()==null)
					{
						Toast.makeText(
								activity,
								activity.getString(R.string.no_data_found),
								Toast.LENGTH_LONG).show();
						return;
					}
					else
					{
						if(AppConfig.CareerFilterModel.getSubject().size()==0)
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

				DropDownListBaseClass.checkSelected = checkSelectedSubjectFilterTextView;

				dropDownListBaseClass.initiatePopUp(
						AppConfig.CareerFilterModel.getSubject(),
						careerSubjectFilterTextView);

			}
		};
		
		
		careerOKOnClickListener = new View.OnClickListener() {

			@Override
			public void onClick(View v) {

				String cityList = getCity();
				String areaList = getArea();
				String categoryList = getCategory();
				String subjectList = getSubjects();
				
//				Toast.makeText(activity, cityList, Toast.LENGTH_LONG).show();
//				Toast.makeText(activity, areaList, Toast.LENGTH_LONG).show();
				Log.e("City", cityList);
				Log.e("Area", areaList);
				
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
				else if(categoryList.equalsIgnoreCase("")) {
					Toast.makeText(activity,
							activity.getString(R.string.select_a_category),
							Toast.LENGTH_LONG).show();
				}
				/*else if(subjectList.equalsIgnoreCase("")) {
					Toast.makeText(activity,
							activity.getString(R.string.select_a_subject),
							Toast.LENGTH_LONG).show();
				}*/
				else if(areaList.equalsIgnoreCase("") && !cityList.contains(",")) {
					Toast.makeText(activity,
							activity.getString(R.string.select_an_area),
							Toast.LENGTH_LONG).show();
				}				
				else {
					AppConfig.FiltersSelected = "<b>" + "City" + "</b>" + "->" + cityList 
							+ "; <b>" + activity.getString(R.string.school_category) + "</b>" + "->" + categoryList
							+ "; <b>" + activity.getString(R.string.school_area) + "</b>" + "->" + areaList;
					
					filterListBusinessLogic.invokeCareerFilteredList(cityList,
							areaList, categoryList, subjectList); 
				}

			}
		};

	}

	protected String getCategory() {

		String category = "";
		
		if(checkSelectedCategoryFilterTextView == null){
			return "";
		}

		for (int i = 0; i < AppConfig.CareerFilterModel.getCategory().size(); i++) {
			if (checkSelectedCategoryFilterTextView[i] == true) {
				
				if(AppConfig.CareerFilterModel.getCategory().get(i)
						.equalsIgnoreCase(activity.getString(R.string.select_all))) {
					
					category = AppConfig.CareerFilterModel.getCategory().get(i);
					break;
				}
				
				if(category.length() == 0){
					category = AppConfig.CareerFilterModel.getCategory().get(i);
				} else{
					category += "," + AppConfig.CareerFilterModel.getCategory().get(i);
				}
			}
		}

		return category;
	}
	
	protected String getSubjects() {
		String subject = "";
		
		if(checkSelectedSubjectFilterTextView == null)
		{
			return "";
		}

		for (int i = 0; i < AppConfig.CareerFilterModel.getSubject().size(); i++) {
			if (checkSelectedSubjectFilterTextView[i] == true) {
				
				if(AppConfig.CareerFilterModel.getSubject().get(i)
						.equalsIgnoreCase(activity.getString(R.string.select_all))) {
					
					subject = AppConfig.CareerFilterModel.getSubject().get(i);
					break;
				}
				
				if(subject.length() == 0) {
					subject = AppConfig.CareerFilterModel.getSubject().get(i);
				} else {
					subject += "," + AppConfig.CareerFilterModel.getSubject().get(i);
				}
			}
		}

		return subject;
	}

	public String getCity() {

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

		Log.e("City", city);
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

		Log.e("Area", area);
//		Toast.makeText(activity, "Area : " + area, Toast.LENGTH_LONG).show();
		
		return area;
	}

	private void initComponent() {

		filterListBusinessLogic = new FilterListBusinessLogic(activity);

		dropDownListBaseClass = new DropDownListBaseClass();
		dropDownListBaseClass.setActivity(activity);

		initCheckSelection();
		
		((ScrollView) findViewById(R.id.career_sv_container)).setScrollbarFadingEnabled(false);

		careerCityFilterTextView = (TextView) findViewById(R.id.careerCityFilterTextView);
		careerAreaFilterTextView = (TextView) findViewById(R.id.careerAreaFilterTextView);
		careerCategoryFilterTextView = (TextView) findViewById(R.id.careerCategoryFilterTextView);
		careerSubjectFilterTextView = (TextView) findViewById(R.id.careerSubjectFilterTextView);
		
		if(!AppConfig.SelectedLocation.equalsIgnoreCase(""))
		careerCityFilterTextView.setText(activity.getString(R.string.school_city) + " - " + getCity());

		career_searchImageView = (ImageView) findViewById(R.id.career_searchImageView);

		careerCityFilterImageView = (ImageView) findViewById(R.id.careerCityFilterImageView);
		careerAreaFilterImageView = (ImageView) findViewById(R.id.careerAreaFilterImageView);
		careerCategoryFilterImageView = (ImageView) findViewById(R.id.careerCategoryFilterImageView);
		careerSubjectFilterImageView = (ImageView) findViewById(R.id.careerSubjectFilterImageView); 

		careerCityFilterTextView.setOnClickListener(careerCityFilterOnClickListener);
		careerAreaFilterTextView.setOnClickListener(careerAreaFilterOnClickListener);
		careerCategoryFilterTextView.setOnClickListener(careerCategoryFilterOnClickListener);
		careerSubjectFilterTextView.setOnClickListener(careerSubjectFilterOnClickListener);
		
		careerCityFilterImageView
				.setOnClickListener(careerCityFilterOnClickListener);
		careerAreaFilterImageView
				.setOnClickListener(careerAreaFilterOnClickListener);
		careerCategoryFilterImageView
				.setOnClickListener(careerCategoryFilterOnClickListener);
		careerSubjectFilterImageView.setOnClickListener(careerSubjectFilterOnClickListener);

		careerOKImageView = (ImageView) findViewById(R.id.careerOKImageView);

		careerOKImageView.setOnClickListener(careerOKOnClickListener);
		career_searchImageView.setOnClickListener(career_searchOnClickListener);
		
		careerAreaFilterTextView.setMovementMethod(new ScrollingMovementMethod());
		careerAreaFilterTextView.setSingleLine();
		careerCityFilterTextView.setMovementMethod(new ScrollingMovementMethod());
		careerCityFilterTextView.setSingleLine();
		careerCategoryFilterTextView.setMovementMethod(new ScrollingMovementMethod());
		careerCategoryFilterTextView.setSingleLine();
		careerSubjectFilterTextView.setMovementMethod(new ScrollingMovementMethod());
		careerSubjectFilterTextView.setSingleLine();

	}

	private void initCheckSelection() {

		checkSelectedCityFilterTextView = new boolean[AppConfig.CityFilterModel
				.getCityFilter().size()];

		checkSelectedCategoryFilterTextView = new boolean[AppConfig.CareerFilterModel
				.getCategory().size()];
		
		checkSelectedSubjectFilterTextView = new boolean[AppConfig.CareerFilterModel
			                                 				.getSubject().size()];

		// initialize all values of list to 'unselected' initially
		for (int i = 0; i < checkSelectedCityFilterTextView.length; i++) {
			checkSelectedCityFilterTextView[i] = false;
			if(AppConfig.CityFilterModel
				.getCityFilter().get(i).equalsIgnoreCase(AppConfig.SelectedLocation))
				checkSelectedCityFilterTextView[i] = true;
		}

		// initialize all values of list to 'unselected' initially
		for (int i = 0; i < checkSelectedCategoryFilterTextView.length; i++) {
			checkSelectedCategoryFilterTextView[i] = false;
		}		

		// initialize all values of list to 'unselected' initially
		for (int i = 0; i < checkSelectedSubjectFilterTextView.length; i++) {
		checkSelectedSubjectFilterTextView[i] = false;
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
		if(tv == careerCityFilterTextView) {
			if(new CareerActivity().getCity()!="")
			careerCityFilterTextView.setText(activity.getString(R.string.school_city) + " - " + new CareerActivity().getCity());
			else
				careerCityFilterTextView.setText(activity.getString(R.string.school_city));
		}
		else if(tv == careerAreaFilterTextView) {
			checkSelectedAreaFilterTextView = DropDownListBaseClass.checkSelected;
			if(new CareerActivity().getArea()!="")
			careerAreaFilterTextView.setText(activity.getString(R.string.school_area) + " - " + new CareerActivity().getArea());
			else
				careerAreaFilterTextView.setText(activity.getString(R.string.school_area));
		}
		else if(tv == careerCategoryFilterTextView) {
			if(new CareerActivity().getCategory()!="")
			careerCategoryFilterTextView.setText(activity.getString(R.string.school_category) + " - " + new CareerActivity().getCategory());
			else
				careerCategoryFilterTextView.setText(activity.getString(R.string.school_category));
		}
		else if(tv == careerSubjectFilterTextView) {
			if(new CareerActivity().getSubjects()!="")
			careerSubjectFilterTextView.setText(activity.getString(R.string.career_subject) + " - " + new CareerActivity().getSubjects());
			else
				careerSubjectFilterTextView.setText(activity.getString(R.string.career_subject));
		}
	}
}
