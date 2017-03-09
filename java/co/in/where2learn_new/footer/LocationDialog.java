package co.in.where2learn_new.footer;

import java.util.List;

import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;
import co.in.where2learn_new.R;
import co.in.where2learn_new.config.AppConfig;
import co.in.where2learn_new.sharedprefs.Where2LearnSharedPrefs;

public class LocationDialog extends Dialog {

	private Context context;
	private ListView listView1;

	private ListAdapter adapter;

	private Dialog dialog;

	private OnItemClickListener listView1OnItemClickListener;
	private List<String> list;
	
	private Where2LearnSharedPrefs prefs;
	public LocationDialog(Context context, boolean cancelable,
			OnCancelListener cancelListener) {
		this(context);
	}

	public LocationDialog(Context context, int theme) {
		this(context);
	}

	public LocationDialog(Context context) {
		super(context);

		/* Note: should before the setContextView* */
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);

		setContentView(R.layout.dialog_location);

		this.context = context;
		this.dialog = this;

		initListener();
		initComponent();

	}

	private void initListener() {

		listView1OnItemClickListener = new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> adapterView, View view,
					int position, long id) {
				
				/*if(context==CareerActivity.activity)
					((CareerActivity)context).changeCity();
				else if(context==CoachingActivity.activity)
					((CoachingActivity)context).changeCity();
				else if(context==CollegeActivity.activity)
					((CollegeActivity)context).changeCity();
				else if(context==HobbyClassesActivity.activity)
					((HobbyClassesActivity)context).changeCity();
				else if(context==ProfessionalCourseActivity.activity)
					((ProfessionalCourseActivity)context).changeCity();
				else if(context==SchoolActivity.activity)
					((SchoolActivity)context).changeCity();
				else if(context==TutorActivity.activity)
					((TutorActivity)context).changeCity();
				else if(context==WorkShopAndEventActivity.activity)
					((WorkShopAndEventActivity)context).changeCity();
				else if(context==ShopActivity.activity)
					((ShopActivity)context).changeCity();*/
//				Toast.makeText(context, "Career", Toast.LENGTH_LONG).show();

				if(((String) adapterView
						.getItemAtPosition(position)).trim().contains("(Coming Soon...)"))
				{
					Toast.makeText(context, context.getString(R.string.coming_soon), Toast.LENGTH_LONG).show();
				}
				else
				{
					prefs.setLocation((String) adapterView
							.getItemAtPosition(position));
					AppConfig.SelectedLocation = prefs.getLocation();
					
					dialog.dismiss();
				}
				
				
				
			}
		};

	}

	private void initComponent() {

		list = AppConfig.CityFilterModel.getCityFilter();

		listView1 = (ListView) findViewById(R.id.activity_location_listView1);
		adapter = new ArrayAdapter<String>(context,
				android.R.layout.simple_list_item_single_choice, list);

		listView1.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
		listView1.setAdapter(adapter);
 

		listView1.setOnItemClickListener(listView1OnItemClickListener);
		
		prefs = Where2LearnSharedPrefs.getsharedprefInstance(context);

	}

	 
}
