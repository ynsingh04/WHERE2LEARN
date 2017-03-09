package co.in.where2learn_new.navigation;

import android.app.Activity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
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
 *         This activity handles the school filters.
 *         </p>
 */
public class SchoolActivity extends AreaInterface {
	
	private String TAG = getClass().getName();

	public static Activity activity;
 

	// values are displayed completely or in
	// shortened representation

	// store select/unselect // information // about the // values in the //
	// list
	public static boolean[] checkSelectedCityFilterTextView; 
	public static boolean[] checkSelectedAgeFilterTextView;
	public static boolean[] checkSelectedBoardFilterTextView;
	public static boolean[] checkSelectedSexFilterTextView;
	public static boolean[] checkSelectedTypeFilterTextView;

	private ImageView schoolCityFilterImageView;
	private ImageView schoolAreaFilterImageView;
	private ImageView schoolAgeFilterImageView;
	private ImageView schoolBoardFilterImageView;
	private ImageView schoolSexFilterImageView;
	private ImageView schoolTypeFilterImageView;

	public static TextView schoolCityFilterTextView;
	public static TextView schoolAreaFilterTextView;
	public static TextView schoolAgeFilterTextView;
	private static TextView schoolBoardFilterTextView;
	private static TextView schoolSexFilterTextView;
	private static TextView schoolTypeFilterTextView;

	private RelativeLayout schoolTitleBarLinearLayout;

	private ImageView schoolOKImageView;
	private ImageView school_searchImageView;

	private View.OnClickListener schoolCityFilterOnClickListener;
	private View.OnClickListener schoolAreaFilterOnClickListener;
	private View.OnClickListener schoolAgeFilterOnClickListener;
	private View.OnClickListener schoolBoardFilterOnClickListener;
	private View.OnClickListener schoolSexFilterOnClickListener;
	private View.OnClickListener schoolTypeFilterOnClickListener;
 

	private View.OnClickListener schoolTitleBarOnClickListener;
	private View.OnClickListener schoolOKOnClickListener;

	private DropDownListBaseClass dropDownListBaseClass;

	private FilterListBusinessLogic filterListBusinessLogic;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_school);

		activity = this;

		getActionBar().hide();
		changeFooter();

		initListener();
		initComponent();

	}

	private void changeFooter() {
		ChangeFooter changeFooter = new ChangeFooter(activity);
		changeFooter.change();

	}

	private void initListener() {

		schoolCityFilterOnClickListener = new View.OnClickListener() {

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
						schoolCityFilterTextView);
				
				checkSelectedAreaFilterTextView = null;
				
				if(new SchoolActivity().getArea()!="")
					schoolAreaFilterTextView.setText(activity.getString(R.string.school_area) + " - " + new SchoolActivity().getArea());
					else
						schoolAreaFilterTextView.setText(activity.getString(R.string.school_area));

			}
		};

		schoolAreaFilterOnClickListener = new View.OnClickListener() {

			@Override
			public void onClick(View v) {

				String city = getCity();

				if (city.length() != 0 && !city.contains(",")) {
//					DropDownListBaseClass.checkSelected = checkSelectedAreaFilterTextView;
					filterListBusinessLogic.getAreaList(city, schoolAreaFilterTextView);
 
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

		schoolAgeFilterOnClickListener = new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				
				/** Check data presence */
				if(AppConfig.SchoolFilterModel!=null)
				{
					if(AppConfig.SchoolFilterModel.getAgeFilter()==null)
					{
						Toast.makeText(
								activity,
								activity.getString(R.string.no_data_found),
								Toast.LENGTH_LONG).show();
						return;
					}
					else
					{
						if(AppConfig.SchoolFilterModel.getAgeFilter().size()==0)
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

				DropDownListBaseClass.checkSelected = checkSelectedAgeFilterTextView;

				dropDownListBaseClass.initiatePopUp(
						AppConfig.SchoolFilterModel.getAgeFilter(),
						schoolAgeFilterTextView);
				
				checkSelectedBoardFilterTextView = new boolean[AppConfig.SchoolFilterModel
				                               				.getBoardFilter().size()];
				
				schoolBoardFilterTextView.setText(activity.getString(R.string.school_board));

			}
		};

		schoolBoardFilterOnClickListener = new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				
				String Class = getAge();
				
				if(Class.contains("Playschool/Pre-school")) {
					Toast.makeText(
							activity,
							activity.getString(R.string.board_option_is_not_available_for_playschool),
							Toast.LENGTH_LONG).show();
					return;
				}
				
				/** Check data presence */
				if(AppConfig.SchoolFilterModel!=null)
				{
					if(AppConfig.SchoolFilterModel.getBoardFilter()==null)
					{
						Toast.makeText(
								activity,
								activity.getString(R.string.no_data_found),
								Toast.LENGTH_LONG).show();
						return;
					}
					else
					{
						if(AppConfig.SchoolFilterModel.getBoardFilter().size()==0)
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

				DropDownListBaseClass.checkSelected = checkSelectedBoardFilterTextView;

				dropDownListBaseClass.initiatePopUp(
						AppConfig.SchoolFilterModel.getBoardFilter(),
						schoolBoardFilterTextView);

			};
		};

		schoolSexFilterOnClickListener = new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				
				/** Check data presence */
				if(AppConfig.SchoolFilterModel!=null)
				{
					if(AppConfig.SchoolFilterModel.getSexFilter()==null)
					{
						Toast.makeText(
								activity,
								activity.getString(R.string.no_data_found),
								Toast.LENGTH_LONG).show();
						return;
					}
					else
					{
						if(AppConfig.SchoolFilterModel.getSexFilter().size()==0)
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

				DropDownListBaseClass.checkSelected = checkSelectedSexFilterTextView;

				dropDownListBaseClass.initiatePopUp(
						AppConfig.SchoolFilterModel.getSexFilter(),
						schoolSexFilterTextView);

			};
		};

		schoolTypeFilterOnClickListener = new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				
				/** Check data presence */
				if(AppConfig.SchoolFilterModel!=null)
				{
					if(AppConfig.SchoolFilterModel.getTypeFilter()==null)
					{
						Toast.makeText(
								activity,
								activity.getString(R.string.no_data_found),
								Toast.LENGTH_LONG).show();
						return;
					}
					else
					{
						if(AppConfig.SchoolFilterModel.getTypeFilter().size()==0)
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

				DropDownListBaseClass.checkSelected = checkSelectedTypeFilterTextView;

				dropDownListBaseClass.initiatePopUp(
						AppConfig.SchoolFilterModel.getTypeFilter(),
						schoolTypeFilterTextView);

			};
		};

		 
		 

		schoolTitleBarOnClickListener = new View.OnClickListener() {

			@Override
			public void onClick(View v) {

				onBackPressed();
				 
			}
		};

		schoolOKOnClickListener = new View.OnClickListener() {

			@Override
			public void onClick(View v) {

				String city = getCity();
				String area = getArea();
				String age = getAge();
				String board = getBoard();
				String sex = getSex();
				String type = getType();
				
				/*Log.e(TAG, "city=" + city
						+ "area=" + area + "class=" + age + "board=" + board + "sex="
						+ sex + "type=" + type);*/
				
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
				else if(age.equalsIgnoreCase("")) {
					Toast.makeText(activity,
							activity.getString(R.string.select_a_class),
							Toast.LENGTH_LONG).show();
				}
				else if(age.contains(",") || age.contains(activity.getString(R.string.select_all))) {
					Toast.makeText(activity,
							activity.getString(R.string.select_only_one_class),
							Toast.LENGTH_LONG).show();
				}
				else if(age.contains("Playschool/Pre-school") &&
						!board.equalsIgnoreCase("")) {
					Toast.makeText(activity,
							activity.getString(R.string.board_option_is_not_available_for_playschool) + " " + AppConfig.HomeString,
							Toast.LENGTH_LONG).show();
				}
				else if(board.equalsIgnoreCase("") &&
						!age.contains("Playschool/Pre-school")) {
					Toast.makeText(activity,
							activity.getString(R.string.select_a_board),
							Toast.LENGTH_LONG).show();
				}
				else if(type.equalsIgnoreCase("")) {
					Toast.makeText(activity,
							activity.getString(R.string.select_a_type),
							Toast.LENGTH_LONG).show();
				}
				else if(sex.equalsIgnoreCase("")) {
					Toast.makeText(activity,
							activity.getString(R.string.select_a_sex),
							Toast.LENGTH_LONG).show();
				}
				else if(area.equalsIgnoreCase("") && !city.contains(",")) {
					Toast.makeText(activity,
							activity.getString(R.string.select_an_area),
							Toast.LENGTH_LONG).show();
				}				
				else {
					AppConfig.FiltersSelected = "<b>" + activity.getString(R.string.school_city) + "</b>" + "->" + city 
							+ "; <b>" + activity.getString(R.string.school_group) + "</b>" + "->" + age
							+ "; <b>" + activity.getString(R.string.school_board) + "</b>" + "->" + board
							+ "; <b>" + activity.getString(R.string.school_type) + "</b>" + "->" + type
							+ "; <b>" + activity.getString(R.string.school_sex) + "</b>" + "->" + sex
							+ "; <b>" + activity.getString(R.string.school_area) + "</b>" + "->" + area;
					
					filterListBusinessLogic.invokeSchoolFilteredList(city, area,
							age, board, sex, type); 
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

	public String getArea() {

		String area = "";
		
		Log.e(TAG, String.valueOf(AppConfig.AreaFilterModel.getAreaFilter().size()));

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
		else {
			Log.e("Area", "null");
		}

		return area;
	}

	protected String getAge() {
		String age = "";

		if(checkSelectedAgeFilterTextView == null)
		{
			return "";
		}

		for (int i = 0; i < AppConfig.SchoolFilterModel.getAgeFilter().size(); i++) {
			if (checkSelectedAgeFilterTextView[i] == true) {
				
				if(AppConfig.SchoolFilterModel.getAgeFilter().get(i)
						.equalsIgnoreCase(activity.getString(R.string.select_all))) {
					
					age = AppConfig.SchoolFilterModel.getAgeFilter().get(i);
					break;
				}
				
				if(age.length() == 0) {
					age = AppConfig.SchoolFilterModel.getAgeFilter().get(i);
				} else {
					age += "," + AppConfig.SchoolFilterModel.getAgeFilter().get(i);
				}
			}
		}

		return age;
	}

	protected String getBoard() {
		String board = "";

		if(checkSelectedBoardFilterTextView == null)
		{
			return "";
		}

		for (int i = 0; i < AppConfig.SchoolFilterModel.getBoardFilter().size(); i++) {
			if (checkSelectedBoardFilterTextView[i] == true) {
				
				if(AppConfig.SchoolFilterModel.getBoardFilter().get(i)
						.equalsIgnoreCase(activity.getString(R.string.select_all))) {
					
					board = AppConfig.SchoolFilterModel.getBoardFilter().get(i);
					break;
				}
				
				if(board.length() == 0) {
					board = AppConfig.SchoolFilterModel.getBoardFilter().get(i);
				} else {
					board += "," + AppConfig.SchoolFilterModel.getBoardFilter().get(i);
				}
			}
		}

		return board;
	}

	protected String getSex() {
		String sex = "";

		if(checkSelectedSexFilterTextView == null)
		{
			return "";
		}

		for (int i = 0; i < AppConfig.SchoolFilterModel.getSexFilter().size(); i++) {
			if (checkSelectedSexFilterTextView[i] == true) {
				if(sex.length() == 0) {
					sex = AppConfig.SchoolFilterModel.getSexFilter().get(i);
				} else {
					sex += "," + AppConfig.SchoolFilterModel.getSexFilter().get(i);
				}
			}
		}

		return sex;
	}

	protected String getType() {
		String type = "";
		
		if(checkSelectedTypeFilterTextView == null)
		{
			return "";
		}

		for (int i = 0; i < AppConfig.SchoolFilterModel.getTypeFilter().size(); i++) {
			if (checkSelectedTypeFilterTextView[i] == true) {
				if(type.length() == 0) {
					type = AppConfig.SchoolFilterModel.getTypeFilter().get(i);
				} else {
					type += "," + AppConfig.SchoolFilterModel.getTypeFilter().get(i);
				}
			}
		}

		return type;
	}

	private void initComponent() {

		initCheckSelection(); 

		filterListBusinessLogic = new FilterListBusinessLogic(activity);

		dropDownListBaseClass = new DropDownListBaseClass();
		dropDownListBaseClass.setActivity(activity);
		
		((ScrollView) findViewById(R.id.school_sv_container)).setScrollbarFadingEnabled(false);

		schoolCityFilterImageView = (ImageView) findViewById(R.id.schoolCityFilterImageView);
		schoolAreaFilterImageView = (ImageView) findViewById(R.id.schoolAreaFilterImageView);
		schoolAgeFilterImageView = (ImageView) findViewById(R.id.schoolAgeFilterImageView);
		schoolBoardFilterImageView = (ImageView) findViewById(R.id.schoolBoardFilterImageView);
		schoolSexFilterImageView = (ImageView) findViewById(R.id.schoolSexFilterImageView);
		schoolTypeFilterImageView = (ImageView) findViewById(R.id.schoolTypeFilterImageView);

		schoolCityFilterTextView = (TextView) findViewById(R.id.schoolCityFilterTextView);
		schoolAreaFilterTextView = (TextView) findViewById(R.id.schoolAreaFilterTextView);
		schoolAgeFilterTextView = (TextView) findViewById(R.id.schoolAgeFilterTextView);
		schoolBoardFilterTextView = (TextView) findViewById(R.id.schoolBoardFilterTextView);
		schoolSexFilterTextView = (TextView) findViewById(R.id.schoolSexFilterTextView);
		schoolTypeFilterTextView = (TextView) findViewById(R.id.schoolTypeFilterTextView);
		
		if(!AppConfig.SelectedLocation.equalsIgnoreCase(""))
		schoolCityFilterTextView.setText(activity.getString(R.string.school_city) + " - " + getCity());
		
		school_searchImageView = (ImageView) findViewById(R.id.school_searchImageView);

		schoolTitleBarLinearLayout = (RelativeLayout) findViewById(R.id.schoolTitleBarLinearLayout);

		schoolOKImageView = (ImageView) findViewById(R.id.schoolOKImageView);

		
		schoolCityFilterTextView.setOnClickListener(schoolCityFilterOnClickListener);
		schoolAreaFilterTextView.setOnClickListener(schoolAreaFilterOnClickListener);
		schoolAgeFilterTextView.setOnClickListener(schoolAgeFilterOnClickListener);
		schoolBoardFilterTextView.setOnClickListener(schoolBoardFilterOnClickListener);
		schoolSexFilterTextView.setOnClickListener(schoolSexFilterOnClickListener);
		schoolTypeFilterTextView.setOnClickListener(schoolTypeFilterOnClickListener);
		
		schoolCityFilterImageView
				.setOnClickListener(schoolCityFilterOnClickListener);
		schoolAreaFilterImageView
				.setOnClickListener(schoolAreaFilterOnClickListener);
		schoolAgeFilterImageView
				.setOnClickListener(schoolAgeFilterOnClickListener);
		schoolBoardFilterImageView
				.setOnClickListener(schoolBoardFilterOnClickListener);
		schoolSexFilterImageView
				.setOnClickListener(schoolSexFilterOnClickListener);
		schoolTypeFilterImageView
				.setOnClickListener(schoolTypeFilterOnClickListener);
 

		school_searchImageView
				.setOnClickListener(schoolTitleBarOnClickListener);

		schoolOKImageView.setOnClickListener(schoolOKOnClickListener);
		
		schoolAreaFilterTextView.setMovementMethod(new ScrollingMovementMethod());
		schoolAreaFilterTextView.setSingleLine();
		schoolCityFilterTextView.setMovementMethod(new ScrollingMovementMethod());
		schoolCityFilterTextView.setSingleLine();
		schoolAgeFilterTextView.setMovementMethod(new ScrollingMovementMethod());
		schoolAgeFilterTextView.setSingleLine();
		schoolBoardFilterTextView.setMovementMethod(new ScrollingMovementMethod());
		schoolBoardFilterTextView.setSingleLine();
		schoolSexFilterTextView.setMovementMethod(new ScrollingMovementMethod());
		schoolSexFilterTextView.setSingleLine();
		schoolTypeFilterTextView.setMovementMethod(new ScrollingMovementMethod());
		schoolTypeFilterTextView.setSingleLine();

	}

	private void initCheckSelection() {

		checkSelectedCityFilterTextView = new boolean[AppConfig.CityFilterModel
				.getCityFilter().size()];
		checkSelectedAgeFilterTextView = new boolean[AppConfig.SchoolFilterModel
				.getAgeFilter().size()];
		checkSelectedBoardFilterTextView = new boolean[AppConfig.SchoolFilterModel
				.getBoardFilter().size()];
		checkSelectedSexFilterTextView = new boolean[AppConfig.SchoolFilterModel
				.getSexFilter().size()];
		checkSelectedTypeFilterTextView = new boolean[AppConfig.SchoolFilterModel
				.getTypeFilter().size()];

		// initialize all values of list to 'unselected' initially
		for (int i = 0; i < checkSelectedCityFilterTextView.length; i++) {
			checkSelectedCityFilterTextView[i] = false;
			if(AppConfig.CityFilterModel
					.getCityFilter().get(i).equalsIgnoreCase(AppConfig.SelectedLocation))
					checkSelectedCityFilterTextView[i] = true;
		}

		// initialize all values of list to 'unselected' initially
		for (int i = 0; i < checkSelectedAgeFilterTextView.length; i++) {
			checkSelectedAgeFilterTextView[i] = false;
		}

		// initialize all values of list to 'unselected' initially
		for (int i = 0; i < checkSelectedBoardFilterTextView.length; i++) {
			checkSelectedBoardFilterTextView[i] = false;
		}

		// initialize all values of list to 'unselected' initially
		for (int i = 0; i < checkSelectedSexFilterTextView.length; i++) {
			checkSelectedSexFilterTextView[i] = false;
		}

		// initialize all values of list to 'unselected' initially
		for (int i = 0; i < checkSelectedTypeFilterTextView.length; i++) {
			checkSelectedTypeFilterTextView[i] = false;
		}

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
		if(tv == schoolCityFilterTextView) {
			if(new SchoolActivity().getCity()!="")
			schoolCityFilterTextView.setText(activity.getString(R.string.school_city) + " - " + new SchoolActivity().getCity());
			else
				schoolCityFilterTextView.setText(activity.getString(R.string.school_city));
		}
		else if(tv == schoolAreaFilterTextView) {
			checkSelectedAreaFilterTextView = DropDownListBaseClass.checkSelected;
			if(new SchoolActivity().getArea()!="")
			schoolAreaFilterTextView.setText(activity.getString(R.string.school_area) + " - " + new SchoolActivity().getArea());
			else
				schoolAreaFilterTextView.setText(activity.getString(R.string.school_area));
		}
		else if(tv == schoolAgeFilterTextView) {
			if(new SchoolActivity().getAge()!="")
			schoolAgeFilterTextView.setText(activity.getString(R.string.school_group) + " - " + new SchoolActivity().getAge());
			else
				schoolAgeFilterTextView.setText(activity.getString(R.string.school_group));
		}
		else if(tv == schoolBoardFilterTextView) {
			if(new SchoolActivity().getBoard()!="")
			schoolBoardFilterTextView.setText(activity.getString(R.string.school_board) + " - " + new SchoolActivity().getBoard());
			else
				schoolBoardFilterTextView.setText(activity.getString(R.string.school_board));
		}
		else if(tv == schoolSexFilterTextView) {
			if(new SchoolActivity().getSex()!="")
			schoolSexFilterTextView.setText(activity.getString(R.string.school_sex) + " - " + new SchoolActivity().getSex());
			else
				schoolSexFilterTextView.setText(activity.getString(R.string.school_sex));
		}
		else if(tv == schoolTypeFilterTextView) {
			if(new SchoolActivity().getType()!="")
			schoolTypeFilterTextView.setText(activity.getString(R.string.school_type) + " - " + new SchoolActivity().getType());
			else
				schoolTypeFilterTextView.setText(activity.getString(R.string.school_type));
		}
	}
	
}
