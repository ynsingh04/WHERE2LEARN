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

public class TutorActivity extends AreaInterface {

	public static Activity activity;

	private FilterListBusinessLogic filterListBusinessLogic;
	private ImageView tutorOKImageView;
	private ImageView tutor_searchImageView;

	// store select/unselect // information // about the // values in the //
	// list
	public static boolean[] checkSelectedCityFilterTextView;
	public static boolean[] checkSelectedTypeFilterTextView;
	public static boolean[] checkSelectedBoardFilterTextView;
	public static boolean[] checkSelectedClassFilterTextView;
	public static boolean[] checkSelectedSubjectFilterTextView;

	private ImageView tutorCityFilterImageView;

	public static TextView tutorCityFilterTextView;

	private View.OnClickListener tutorCityFilterOnClickListener;

	private View.OnClickListener tutorOKOnClickListener;

	private View.OnClickListener tutor_searchOnClickListener;

	private DropDownListBaseClass dropDownListBaseClass;

	public static TextView tutorAreaFilterTextView;
	private ImageView tutorAreaFilterImageView;

	private static TextView tutorTypeFilterTextView;
	private ImageView tutorTypeFilterImageView;

	private static TextView tutorBoardFilterTextView;
	private ImageView tutorBoardFilterImageView;

	private static TextView tutorClassFilterTextView;
	private ImageView tutorClassFilterImageView;

	private static TextView tutorSubjectFilterTextView;
	private ImageView tutorSubjectFilterImageView;

	private View.OnClickListener tutorAreaFilterOnClickListener;

	private View.OnClickListener tutorTypeFilterOnClickListener;

	private View.OnClickListener tutorClassFilterOnClickListener;

	private View.OnClickListener tutorBoardFilterOnClickListener;

	private View.OnClickListener tutorSubjectFilterOnClickListener;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_tutor);

		activity = this;

		initListener();
		initComponent();

		getActionBar().hide();
		changeFooter();
	}

	private void initListener() {

		tutor_searchOnClickListener = new View.OnClickListener() {

			@Override
			public void onClick(View v) {

				onBackPressed();

			}
		};

		tutorCityFilterOnClickListener = new View.OnClickListener() {

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
						tutorCityFilterTextView);
				
				checkSelectedAreaFilterTextView = null;
				
				if(new TutorActivity().getArea()!="")
					tutorAreaFilterTextView.setText(activity.getString(R.string.school_area) + " - " + new TutorActivity().getArea());
					else
						tutorAreaFilterTextView.setText(activity.getString(R.string.school_area));

			}
		};

		tutorAreaFilterOnClickListener = new View.OnClickListener() {

			@Override
			public void onClick(View v) {

				String city = getCity();
				String type = getType();

				if (city.length() != 0 && !city.contains(",")) {

					//Check for home tutor
					/*if (type.contains("Home tutor")) {
						Toast.makeText(
								activity,
								activity.getString(R.string.area_option_is_not_available_for_home_tutor),
								Toast.LENGTH_LONG).show();
					} else {*/
					if(type.contains(AppConfig.HomeString))	{
						Toast.makeText(
								activity,
								activity.getString(R.string.area_option_is_not_available_for_home_tutor) + " " + AppConfig.HomeString,
								Toast.LENGTH_LONG).show();
					}
					else {
						filterListBusinessLogic.getAreaList(city,
								tutorAreaFilterTextView);
					}
						
//					}

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

		tutorClassFilterOnClickListener = new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				
				/** Check data presence */
				if(AppConfig.TuitionFilterModel!=null)
				{
					if(AppConfig.TuitionFilterModel.getClasses()==null)
					{
						Toast.makeText(
								activity,
								activity.getString(R.string.no_data_found),
								Toast.LENGTH_LONG).show();
						return;
					}
					else
					{
						if(AppConfig.TuitionFilterModel.getClasses().size()==0)
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

				DropDownListBaseClass.checkSelected = checkSelectedClassFilterTextView;

				dropDownListBaseClass.initiatePopUp(
						AppConfig.TuitionFilterModel.getClasses(),
						tutorClassFilterTextView);

			}
		};

		/*** Type ***/

		tutorTypeFilterOnClickListener = new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				
				/** Check data presence */
				if(AppConfig.TuitionFilterModel!=null)
				{
					if(AppConfig.TuitionFilterModel.getType()==null)
					{
						Toast.makeText(
								activity,
								activity.getString(R.string.no_data_found),
								Toast.LENGTH_LONG).show();
						return;
					}
					else
					{
						if(AppConfig.TuitionFilterModel.getType().size()==0)
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
						AppConfig.TuitionFilterModel.getType(),
						tutorTypeFilterTextView);
				
				checkSelectedAreaFilterTextView = null;
				
				tutorAreaFilterTextView.setText(activity.getString(R.string.school_area));

			}
		};

		/*** End Type **/

		tutorBoardFilterOnClickListener = new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				
				/** Check data presence */
				if(AppConfig.TuitionFilterModel!=null)
				{
					if(AppConfig.TuitionFilterModel.getBoard()==null)
					{
						Toast.makeText(
								activity,
								activity.getString(R.string.no_data_found),
								Toast.LENGTH_LONG).show();
						return;
					}
					else
					{
						if(AppConfig.TuitionFilterModel.getBoard().size()==0)
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
						AppConfig.TuitionFilterModel.getBoard(),
						tutorBoardFilterTextView);

			}
		};

		tutorSubjectFilterOnClickListener = new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				
				/** Check data presence */
				if(AppConfig.TuitionFilterModel!=null)
				{
					if(AppConfig.TuitionFilterModel.getSubject()==null)
					{
						Toast.makeText(
								activity,
								activity.getString(R.string.no_data_found),
								Toast.LENGTH_LONG).show();
						return;
					}
					else
					{
						if(AppConfig.TuitionFilterModel.getSubject().size()==0)
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
						AppConfig.TuitionFilterModel.getSubject(),
						tutorSubjectFilterTextView);

			}
		};

		tutorOKOnClickListener = new View.OnClickListener() {

			@Override
			public void onClick(View v) {

				String cityList = getCity();
				String typeList = getType();
				String areaList = getArea();
				String classList = getClasses();
				String boardList = getBoard();
				String subjectList = getSubject();

				/*Toast.makeText(activity, "cityList: " + cityList 
						+ "\ntypeList: " + typeList 
						+ "\nareaList: " + areaList
						+ "\nclassList: " + classList
						+ "\nboardList: " + boardList
						+ "\nsubjectList: " + subjectList, Toast.LENGTH_LONG).show();*/
				
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
				else if(subjectList.equalsIgnoreCase("")) {
					Toast.makeText(activity,
							activity.getString(R.string.select_a_subject),
							Toast.LENGTH_LONG).show();
				}
				else if(classList.equalsIgnoreCase("")) {
					Toast.makeText(activity,
							activity.getString(R.string.select_a_class),
							Toast.LENGTH_LONG).show();
				}
				else if(boardList.equalsIgnoreCase("")) {
					Toast.makeText(activity,
							activity.getString(R.string.select_a_board),
							Toast.LENGTH_LONG).show();
				}
				else if(typeList.equalsIgnoreCase("")) {
					Toast.makeText(activity,
							activity.getString(R.string.select_a_type),
							Toast.LENGTH_LONG).show();
				}
				else if(typeList.contains(AppConfig.HomeString) &&
						!areaList.equalsIgnoreCase("")) {
					Toast.makeText(activity,
							activity.getString(R.string.area_option_is_not_available_for_home_tutor) + " " + AppConfig.HomeString,
							Toast.LENGTH_LONG).show();
				}
				else if(areaList.equalsIgnoreCase("") &&
						!cityList.contains(",") &&
						!typeList.contains(AppConfig.HomeString)) {
					Toast.makeText(activity,
							activity.getString(R.string.select_an_area),
							Toast.LENGTH_LONG).show();
				}				
				else {
					AppConfig.FiltersSelected = "<b>" + activity.getString(R.string.school_city) + "</b>" + "->" + cityList 
							+ "; <b>" + activity.getString(R.string.tutor_subject) + "</b>" + "->" + subjectList
							+ "; <b>" + activity.getString(R.string.tutor_class) + "</b>" + "->" + classList
							+ "; <b>" + activity.getString(R.string.school_board) + "</b>" + "->" + boardList
							+ "; <b>" + activity.getString(R.string.tutor_type) + "</b>" + "->" + typeList
							+ "; <b>" + activity.getString(R.string.school_area) + "</b>" + "->" + areaList;
					
					filterListBusinessLogic.invokeTuitionFilteredList(cityList,
							typeList, areaList, classList, boardList, subjectList); 
				}
			}

		};

	}

	private String getClasses() {

		String classes = "";

		if(checkSelectedClassFilterTextView == null)
		{
			return "";
		}

		for (int i = 0; i < AppConfig.TuitionFilterModel.getClasses().size(); i++) {
			if (checkSelectedClassFilterTextView[i] == true) {
				
				if(AppConfig.TuitionFilterModel.getClasses().get(i)
						.equalsIgnoreCase(activity.getString(R.string.select_all))) {
					
					classes = AppConfig.TuitionFilterModel.getClasses().get(i);
					break;
				}
				
				if(classes.length() == 0) {
					classes = AppConfig.TuitionFilterModel.getClasses().get(i);
				} else{
					classes += "," + AppConfig.TuitionFilterModel.getClasses().get(i);
				}
			}
		}

		return classes;
	}

	protected String getType() {
		String type = "";

		if(checkSelectedTypeFilterTextView == null)
		{
			return "";
		}

		for (int i = 0; i < AppConfig.TuitionFilterModel.getType().size(); i++) {
			if (checkSelectedTypeFilterTextView[i] == true) {
				if(type.length() == 0) {
					type = AppConfig.TuitionFilterModel.getType().get(i);
				} else {
					type += "," + AppConfig.TuitionFilterModel.getType().get(i);
				}
			}
		}

		return type;
	}

	protected String getBoard() {
		String board = "";

		if(checkSelectedBoardFilterTextView == null)
		{
			return "";
		}

		for (int i = 0; i < AppConfig.TuitionFilterModel.getBoard().size(); i++) {
			if (checkSelectedBoardFilterTextView[i] == true) {
				
				if(AppConfig.TuitionFilterModel.getBoard().get(i)
						.equalsIgnoreCase(activity.getString(R.string.select_all))) {
					
					board = AppConfig.TuitionFilterModel.getBoard().get(i);
					break;
				}
				
				if(board.length() == 0) {
					board = AppConfig.TuitionFilterModel.getBoard().get(i);
				} else {
					board += "," + AppConfig.TuitionFilterModel.getBoard().get(i);
				}
			}
		}

		return board;
	}

	protected String getSubject() {
		String subject = "";

		if(checkSelectedSubjectFilterTextView == null)
		{
			return "";
		}

		for (int i = 0; i < AppConfig.TuitionFilterModel.getSubject().size(); i++) {
			if (checkSelectedSubjectFilterTextView[i] == true) {
				
				if(AppConfig.TuitionFilterModel.getSubject().get(i)
						.equalsIgnoreCase(activity.getString(R.string.select_all))) {
					
					subject = AppConfig.TuitionFilterModel.getSubject().get(i);
					break;
				}
				
				if(subject.length() == 0) {
					subject = AppConfig.TuitionFilterModel.getSubject().get(i);
				} else {
					subject += "," + AppConfig.TuitionFilterModel.getSubject().get(i);
				}
			}
		}

		return subject;
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
		
		((ScrollView) findViewById(R.id.tutor_sv_container)).setScrollbarFadingEnabled(false);

		tutor_searchImageView = (ImageView) findViewById(R.id.tutor_searchImageView);

		tutorCityFilterTextView = (TextView) findViewById(R.id.tutorCityFilterTextView);
		tutorAreaFilterTextView = (TextView) findViewById(R.id.tutorAreaFilterTextView);
		tutorTypeFilterTextView = (TextView) findViewById(R.id.tutorTypeFilterTextView);
		tutorClassFilterTextView = (TextView) findViewById(R.id.tutorClassFilterTextView);
		tutorBoardFilterTextView = (TextView) findViewById(R.id.tutorBoardFilterTextView);
		tutorSubjectFilterTextView = (TextView) findViewById(R.id.tutorSubjectFilterTextView);
		
		if(!AppConfig.SelectedLocation.equalsIgnoreCase(""))
		tutorCityFilterTextView.setText(activity.getString(R.string.school_city) + " - " + getCity());

		tutorCityFilterImageView = (ImageView) findViewById(R.id.tutorCityFilterImageView);
		tutorAreaFilterImageView = (ImageView) findViewById(R.id.tutorAreaFilterImageView);
		tutorTypeFilterImageView = (ImageView) findViewById(R.id.tutorTypeFilterImageView);
		tutorClassFilterImageView = (ImageView) findViewById(R.id.tutorClassFilterImageView);
		tutorBoardFilterImageView = (ImageView) findViewById(R.id.tutorBoardFilterImageView);
		tutorSubjectFilterImageView = (ImageView) findViewById(R.id.tutorSubjectFilterImageView);

		tutorCityFilterTextView
				.setOnClickListener(tutorCityFilterOnClickListener);
		tutorAreaFilterTextView
				.setOnClickListener(tutorAreaFilterOnClickListener);
		tutorTypeFilterTextView
				.setOnClickListener(tutorTypeFilterOnClickListener);
		tutorClassFilterTextView
				.setOnClickListener(tutorClassFilterOnClickListener);
		tutorBoardFilterTextView
				.setOnClickListener(tutorBoardFilterOnClickListener);
		tutorSubjectFilterTextView
				.setOnClickListener(tutorSubjectFilterOnClickListener);

		tutorCityFilterImageView
				.setOnClickListener(tutorCityFilterOnClickListener);
		tutorAreaFilterImageView
				.setOnClickListener(tutorAreaFilterOnClickListener);
		tutorClassFilterImageView
				.setOnClickListener(tutorClassFilterOnClickListener);
		tutorBoardFilterImageView
				.setOnClickListener(tutorBoardFilterOnClickListener);
		tutorSubjectFilterImageView
				.setOnClickListener(tutorSubjectFilterOnClickListener);
		tutorTypeFilterImageView
				.setOnClickListener(tutorTypeFilterOnClickListener);

		tutorOKImageView = (ImageView) findViewById(R.id.tutorOKImageView);

		tutorOKImageView.setOnClickListener(tutorOKOnClickListener);
		tutor_searchImageView.setOnClickListener(tutor_searchOnClickListener);
		
		tutorAreaFilterTextView.setMovementMethod(new ScrollingMovementMethod());
		tutorAreaFilterTextView.setSingleLine();
		tutorCityFilterTextView.setMovementMethod(new ScrollingMovementMethod());
		tutorCityFilterTextView.setSingleLine();
		tutorTypeFilterTextView.setMovementMethod(new ScrollingMovementMethod());
		tutorTypeFilterTextView.setSingleLine();
		tutorClassFilterTextView.setMovementMethod(new ScrollingMovementMethod());
		tutorClassFilterTextView.setSingleLine();
		tutorBoardFilterTextView.setMovementMethod(new ScrollingMovementMethod());
		tutorBoardFilterTextView.setSingleLine();
		tutorSubjectFilterTextView.setMovementMethod(new ScrollingMovementMethod());
		tutorSubjectFilterTextView.setSingleLine();

	}

	private void initCheckSelection() {

		checkSelectedCityFilterTextView = new boolean[AppConfig.CityFilterModel
				.getCityFilter().size()];

		// initialize all values of list to 'unselected' initially
		for (int i = 0; i < checkSelectedCityFilterTextView.length; i++) {
			checkSelectedCityFilterTextView[i] = false;
			if(AppConfig.CityFilterModel
					.getCityFilter().get(i).equalsIgnoreCase(AppConfig.SelectedLocation))
					checkSelectedCityFilterTextView[i] = true;
		}

		checkSelectedBoardFilterTextView = new boolean[AppConfig.TuitionFilterModel
				.getBoard().size()];

		// initialize all values of list to 'unselected' initially
		for (int i = 0; i < checkSelectedBoardFilterTextView.length; i++) {
			checkSelectedBoardFilterTextView[i] = false;
		}

		checkSelectedClassFilterTextView = new boolean[AppConfig.TuitionFilterModel
				.getClasses().size()];

		// initialize all values of list to 'unselected' initially
		for (int i = 0; i < checkSelectedClassFilterTextView.length; i++) {
			checkSelectedClassFilterTextView[i] = false;
		}
		checkSelectedSubjectFilterTextView = new boolean[AppConfig.TuitionFilterModel
				.getSubject().size()];

		// initialize all values of list to 'unselected' initially
		for (int i = 0; i < checkSelectedSubjectFilterTextView.length; i++) {
			checkSelectedSubjectFilterTextView[i] = false;
		}
		

		checkSelectedTypeFilterTextView = new boolean[AppConfig.TuitionFilterModel
				.getType().size()];

		// initialize all values of list to 'unselected' initially
		for (int i = 0; i < checkSelectedTypeFilterTextView.length; i++) {
			checkSelectedTypeFilterTextView[i] = false;
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
		if(tv == tutorCityFilterTextView) {
			if(new TutorActivity().getCity()!="")
			tutorCityFilterTextView.setText(activity.getString(R.string.school_city) + " - " + new TutorActivity().getCity());
			else
				tutorCityFilterTextView.setText(activity.getString(R.string.school_city));
		}
		else if(tv == tutorTypeFilterTextView) {
			if(new TutorActivity().getType()!="")
			tutorTypeFilterTextView.setText(activity.getString(R.string.tutor_type) + " - " + new TutorActivity().getType());
			else
				tutorTypeFilterTextView.setText(activity.getString(R.string.tutor_type));
		}
		else if(tv == tutorAreaFilterTextView) {
			checkSelectedAreaFilterTextView = DropDownListBaseClass.checkSelected;
			if(new TutorActivity().getArea()!="")
			tutorAreaFilterTextView.setText(activity.getString(R.string.school_area) + " - " + new TutorActivity().getArea());
			else
				tutorAreaFilterTextView.setText(activity.getString(R.string.school_area));
		}
		else if(tv == tutorClassFilterTextView) {
			if(new TutorActivity().getClasses()!="")
			tutorClassFilterTextView.setText(activity.getString(R.string.tutor_class) + " - " + new TutorActivity().getClasses());
			else
				tutorClassFilterTextView.setText(activity.getString(R.string.tutor_class));
		}
		else if(tv == tutorBoardFilterTextView) {
			if(new TutorActivity().getBoard()!="")
			tutorBoardFilterTextView.setText(activity.getString(R.string.school_board) + " - " + new TutorActivity().getBoard());
			else
				tutorBoardFilterTextView.setText(activity.getString(R.string.school_board));
		}
		else if(tv == tutorSubjectFilterTextView) {
			if(new TutorActivity().getSubject()!="")
			tutorSubjectFilterTextView.setText(activity.getString(R.string.tutor_subject) + " - " + new TutorActivity().getSubject());
			else
				tutorSubjectFilterTextView.setText(activity.getString(R.string.tutor_subject));
		}
	}
}
