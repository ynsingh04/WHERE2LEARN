package co.in.where2learn_new;

import java.util.List;
import java.util.Vector;
import java.util.regex.PatternSyntaxException;

import android.Manifest;
import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupMenu;
import android.widget.PopupMenu.OnMenuItemClickListener;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.FacebookSdk;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.OptionalPendingResult;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;

import co.in.where2learn_new.adapter.CustomPagerAdapter;
import co.in.where2learn_new.businesslogic.FooterBusinessLogic;
import co.in.where2learn_new.businesslogic.MainActivityBusinessLogic;
import co.in.where2learn_new.config.AppConfig;
import co.in.where2learn_new.controls.Carousel;
import co.in.where2learn_new.database.DataBaseClass;
import co.in.where2learn_new.footer.LoginActivity;
import co.in.where2learn_new.fragments.MainFragmentOne;
import co.in.where2learn_new.fragments.MainFragmentThree;
import co.in.where2learn_new.fragments.MainFragmentTwo;
import co.in.where2learn_new.helper.VersionCheckHelper;
import co.in.where2learn_new.model.UserModel;
import co.in.where2learn_new.search.SearchActivity;
import co.in.where2learn_new.sharedprefs.Where2LearnSharedPrefs;
import co.in.where2learn_new.social.GooglePlusIntegration;
import co.in.where2learn_new.util.ChangeFooter;
import co.in.where2learn_new.util.ChangeFragmentOnlineStoreImageView;
import co.in.where2learn_new.util.CheckInternet;
import co.in.where2learn_new.util.SuggestionDialog;
import co.in.where2learn_new.webservice.Policy_TnC_WebServiceCall;
import co.in.where2learn_new.webservice.WriteToUsWebServiceCall;

public class MainActivity extends FragmentActivity implements
		View.OnClickListener,
		GoogleApiClient.OnConnectionFailedListener{
	
	private String TAG = getClass().getName();
	private Activity activity;
	public static Activity ref;
	
	private MainActivityBusinessLogic businessLogic;
	
	private Where2LearnSharedPrefs prefs;
	
	private Carousel carousel;
	
	private LinearLayout news_click;
	private LinearLayout talks_click;
	private Button privacy_policy_click;
	private Button tnc_click;
	private ImageView menu_click;
	private ImageView search_click;
	public static TextView userIdentificaton;
	public static TextView userWelcome;
	
	private OnClickListener news_listener;
	private OnClickListener talks_listener;
	private OnClickListener privacy_policy_listener;
	private OnClickListener tnc_listener;
	private OnClickListener menu_listener;
	private OnClickListener search_listener;
	
	
	/** New Views & Listeners */
	private RelativeLayout school;
	private RelativeLayout college;
	private RelativeLayout tuition;
	private RelativeLayout coaching;
	private RelativeLayout hobby;
	private RelativeLayout workshop;
	private RelativeLayout career;
	private RelativeLayout professional;
	private RelativeLayout shop;
	
	private View.OnClickListener school_Listener;
	private View.OnClickListener college_Listener;
	private View.OnClickListener tuition_Listener;
	private View.OnClickListener coaching_Listener;
	private View.OnClickListener hobby_Listener;
	private View.OnClickListener workshop_Listener;
	private View.OnClickListener career_Listener;
	private View.OnClickListener professional_Listener;
	private View.OnClickListener shop_Listener;
	
	private CustomPagerAdapter mPagerAdapter;
	public static ChangeFragmentOnlineStoreImageView fragmentMainImageView;
	
	
	public static Dialog mDialog;
	
	private String name_text = "";
	private String email_text = "";
	private String phone_text = "";
	private String message_text = "";
	
	private String email_pattern = "^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$";
	
	private String password_pattern = "^[a-zA-Z0-9@#$*_-]*$";
	
	private String name_pattern = "^[\\p{L} .'-]+$";
	
	private String message_pattern = "^[a-zA-Z0-9@#$*_(),. ]*$";
//	"^[a-zA-Z0-9@#$*_-()/,.]*$";

	/* For Permissions Management */
	final int MY_PERMISSIONS_REQUEST_CALL_PHONE=0;
	final int MY_PERMISSIONS_REQUEST_GET_ACCOUNTS=1;
	final String[] permissions = new String[]{Manifest.permission.CALL_PHONE,
			Manifest.permission.GET_ACCOUNTS};
	int count = 0;



	/************************************************************************/
	private static final int RC_SIGN_IN = 007;

	private GoogleApiClient mGoogleApiClient;
	private ProgressDialog mProgressDialog;

	private DataBaseClass dataBaseClass;

	public static MainActivity mainActivity;
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
//        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.main);
        
        activity = this;
        ref = activity;

		mainActivity = this;
        
        getActionBar().hide();

//		allowPermission();

		callInitMethods();




		Log.e(TAG,"G+");

		GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
				.requestEmail()
				.build();

		mGoogleApiClient = new GoogleApiClient.Builder(this.activity)
//                .enableAutoManage(this,this)
				.addApi(Auth.GOOGLE_SIGN_IN_API, gso)
				.build();

		mGoogleApiClient.connect();
    }

	/* Call Initialize Methods */
	private void callInitMethods() {
		changeFooter();

		initListener();

		initComponent();
	}
    
    /**
	 * This method is called to change footer bar
	 */
	private void changeFooter() {
		ChangeFooter changeFooter = new ChangeFooter(activity);
		changeFooter.change();

	}
    
    public void initComponent(){

//		googlePlusIntegration = new GooglePlusIntegration(activity);
		FacebookSdk.sdkInitialize(getApplicationContext());
    	
    	school = (RelativeLayout) findViewById(R.id.main_rl_school);
    	college = (RelativeLayout) findViewById(R.id.main_rl_college);
    	tuition = (RelativeLayout) findViewById(R.id.main_rl_tuition);
    	coaching = (RelativeLayout) findViewById(R.id.main_rl_coaching);
    	hobby = (RelativeLayout) findViewById(R.id.main_rl_hobby);
    	workshop = (RelativeLayout) findViewById(R.id.main_rl_workshop);
    	career = (RelativeLayout) findViewById(R.id.main_rl_career);
    	professional = (RelativeLayout) findViewById(R.id.main_rl_professional);
    	shop = (RelativeLayout) findViewById(R.id.main_rl_shop);
    	
    	school.setOnClickListener(school_Listener);
    	college.setOnClickListener(college_Listener);
    	tuition.setOnClickListener(tuition_Listener);
    	coaching.setOnClickListener(coaching_Listener);
    	hobby.setOnClickListener(hobby_Listener);
    	workshop.setOnClickListener(workshop_Listener);
    	career.setOnClickListener(career_Listener);
    	professional.setOnClickListener(professional_Listener);
    	shop.setOnClickListener(shop_Listener);
    	
    	((TextView) findViewById(R.id.main_tv_workshop)).setText("WORKSHOP & EVENTS");
    	
//    	checkVersion();
    	
    	((RelativeLayout)findViewById(R.id.footer_rl_parent)).setVisibility(View.VISIBLE);
    	
    	carousel = (Carousel)findViewById(R.id.carousel);
        carousel.setGravity(Gravity.CENTER_VERTICAL);
        
        news_click = (LinearLayout)findViewById(R.id.main_ll_news);
//        news_click.setOnClickListener(news_listener);
        
        talks_click = (LinearLayout)findViewById(R.id.main_ll_talks);
//        talks_click.setOnClickListener(talks_listener);
        
        privacy_policy_click = (Button)findViewById(R.id.main_btn_policy);
        tnc_click = (Button)findViewById(R.id.main_btn_tnc);
        menu_click = (ImageView)findViewById(R.id.main_iv_menu);
        
        search_click = (ImageView)findViewById(R.id.main_iv_search);
        
        news_click.setOnClickListener(news_listener);
		talks_click.setOnClickListener(talks_listener);
		privacy_policy_click.setOnClickListener(privacy_policy_listener);
		tnc_click.setOnClickListener(tnc_listener);
		menu_click.setOnClickListener(menu_listener);
		search_click.setOnClickListener(search_listener);
        
        ((Button)findViewById(R.id.main_btn_tnc)).setText("T&C");
        
        businessLogic = new MainActivityBusinessLogic(activity);
        
        prefs = Where2LearnSharedPrefs.getsharedprefInstance(activity);
		AppConfig.SelectedLocation = prefs.getLocation();
		
		userIdentificaton = (TextView) findViewById(R.id.main_tv_userIdentity);
		userWelcome = (TextView) findViewById(R.id.main_tv_userWelcome);
		
		if(AppConfig.UserModel!=null) {
			if(AppConfig.UserModel.getNameString()!=null)
			userIdentificaton.setText(AppConfig.UserModel.getNameString().toUpperCase());
		}			
		else
			userIdentificaton.setText("GUEST");
		
//		Log.e(TAG, AppConfig.UserModel.getNameString());
			
		
		setVersion();
		
		checkVersionAndUpdate();
		
		fragmentMainImageView = new ChangeFragmentOnlineStoreImageView(
				activity);
		
		initialisePaging();
    }
    
    /**
	 * This method is called to initialize the paging
	 */
	private void initialisePaging() {

		/**** Build Fragment List ***/
		List<Fragment> fragments = new Vector<Fragment>();

		/**** Add Fragments to List ***/		
		
		fragments.add(Fragment.instantiate(this,
				MainFragmentTwo.class.getName()));
		fragments.add(Fragment.instantiate(this,
				MainFragmentOne.class.getName()));
		fragments.add(Fragment.instantiate(this,
				MainFragmentThree.class.getName()));		
		
		/**** Initialize Pager Adapter to List ***/

		mPagerAdapter = new CustomPagerAdapter(getSupportFragmentManager(),
				fragments);
		ViewPager pager = (ViewPager) super.findViewById(R.id.main_vp_pager);
		pager.setAdapter(mPagerAdapter);

	}
    
    public void initListener(){
    	
    	/** New Views' Listeners */
    	
    	school_Listener = new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if(CheckInternet.getConnectivityStatus(activity)!=CheckInternet.TYPE_NOT_CONNECTED)
					businessLogic.invokeSchoolActivity();
					else
						Toast.makeText(activity,
								activity.getString(R.string.no_internet_found),
								Toast.LENGTH_LONG).show();
			}
		};
		
		college_Listener = new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if(CheckInternet.getConnectivityStatus(activity)!=CheckInternet.TYPE_NOT_CONNECTED)
					businessLogic.invokeCollegeActivity();
					else
						Toast.makeText(activity,
								activity.getString(R.string.no_internet_found),
								Toast.LENGTH_LONG).show();
			}
		};
		
		tuition_Listener = new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if(CheckInternet.getConnectivityStatus(activity)!=CheckInternet.TYPE_NOT_CONNECTED)
					businessLogic.invokeTutorActivity();
				else
					Toast.makeText(activity,
							activity.getString(R.string.no_internet_found),
							Toast.LENGTH_LONG).show();
			}
		};
		
		coaching_Listener = new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if(CheckInternet.getConnectivityStatus(activity)!=CheckInternet.TYPE_NOT_CONNECTED)
					businessLogic.invokeCoachingActivity();
				else
					Toast.makeText(activity,
							activity.getString(R.string.no_internet_found),
							Toast.LENGTH_LONG).show();
			}
		};
		
		hobby_Listener = new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if(CheckInternet.getConnectivityStatus(activity)!=CheckInternet.TYPE_NOT_CONNECTED)
					businessLogic.invokeHobbyClassesActivity();
				else
					Toast.makeText(activity,
							activity.getString(R.string.no_internet_found),
							Toast.LENGTH_LONG).show();
			}
		};
		
		workshop_Listener = new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if(CheckInternet.getConnectivityStatus(activity)!=CheckInternet.TYPE_NOT_CONNECTED)
					businessLogic.invokeWorkshopAndEvents();
				else
					Toast.makeText(activity,
							activity.getString(R.string.no_internet_found),
							Toast.LENGTH_LONG).show();
			}
		};
		
		career_Listener = new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if(CheckInternet.getConnectivityStatus(activity)!=CheckInternet.TYPE_NOT_CONNECTED)
					businessLogic.invokeCareerAndMore();
				else
					Toast.makeText(activity,
							activity.getString(R.string.no_internet_found),
							Toast.LENGTH_LONG).show();
			}
		};
		
		professional_Listener = new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if(CheckInternet.getConnectivityStatus(activity)!=CheckInternet.TYPE_NOT_CONNECTED)
					businessLogic.invokeProfessional();
				else
					Toast.makeText(activity,
							activity.getString(R.string.no_internet_found),
							Toast.LENGTH_LONG).show();
			}
		};
		
		shop_Listener = new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if(CheckInternet.getConnectivityStatus(activity)!=CheckInternet.TYPE_NOT_CONNECTED)
					//businessLogic.invokeShops();
						Toast.makeText(activity,
								activity.getString(R.string.coming_soon),
								Toast.LENGTH_LONG).show();
					else
						Toast.makeText(activity,
								activity.getString(R.string.no_internet_found),
								Toast.LENGTH_LONG).show();
			}
		};
    	
    	/** End New Views' Listeners */
    	
    	
    	/*carousel.setOnItemClickListener(new OnItemClickListener(){

			public void onItemClick(CarouselAdapter<?> parent, View view,
					int position, long id) {	
								
				*//** Calling respective activities *//*
				
				switch(position){
				case 0:
					if(CheckInternet.getConnectivityStatus(activity)!=CheckInternet.TYPE_NOT_CONNECTED)
					businessLogic.invokeSchoolActivity();
					else
						Toast.makeText(activity,
								activity.getString(R.string.no_internet_found),
								Toast.LENGTH_LONG).show();
					break;					
				case 1:
					if(CheckInternet.getConnectivityStatus(activity)!=CheckInternet.TYPE_NOT_CONNECTED)
					//businessLogic.invokeShops();
						Toast.makeText(activity,
								activity.getString(R.string.coming_soon),
								Toast.LENGTH_LONG).show();
					else
						Toast.makeText(activity,
								activity.getString(R.string.no_internet_found),
								Toast.LENGTH_LONG).show();
					break;
				case 2:
					if(CheckInternet.getConnectivityStatus(activity)!=CheckInternet.TYPE_NOT_CONNECTED)
						businessLogic.invokeProfessional();
					else
						Toast.makeText(activity,
								activity.getString(R.string.no_internet_found),
								Toast.LENGTH_LONG).show();
					break;
				case 3:
					if(CheckInternet.getConnectivityStatus(activity)!=CheckInternet.TYPE_NOT_CONNECTED)
						businessLogic.invokeCareerAndMore();
					else
						Toast.makeText(activity,
								activity.getString(R.string.no_internet_found),
								Toast.LENGTH_LONG).show();
					break;
				case 4:
					if(CheckInternet.getConnectivityStatus(activity)!=CheckInternet.TYPE_NOT_CONNECTED)
						businessLogic.invokeWorkshopAndEvents();
					else
						Toast.makeText(activity,
								activity.getString(R.string.no_internet_found),
								Toast.LENGTH_LONG).show();
					break;
				case 5:
					if(CheckInternet.getConnectivityStatus(activity)!=CheckInternet.TYPE_NOT_CONNECTED)
						businessLogic.invokeHobbyClassesActivity();
					else
						Toast.makeText(activity,
								activity.getString(R.string.no_internet_found),
								Toast.LENGTH_LONG).show();
					break;
				case 6:
					if(CheckInternet.getConnectivityStatus(activity)!=CheckInternet.TYPE_NOT_CONNECTED)
						businessLogic.invokeCoachingActivity();
					else
						Toast.makeText(activity,
								activity.getString(R.string.no_internet_found),
								Toast.LENGTH_LONG).show();
					break;
				case 7:
					if(CheckInternet.getConnectivityStatus(activity)!=CheckInternet.TYPE_NOT_CONNECTED)
						businessLogic.invokeTutorActivity();
					else
						Toast.makeText(activity,
								activity.getString(R.string.no_internet_found),
								Toast.LENGTH_LONG).show();
					break;
				case 8:
					if(CheckInternet.getConnectivityStatus(activity)!=CheckInternet.TYPE_NOT_CONNECTED)
					businessLogic.invokeCollegeActivity();
					else
						Toast.makeText(activity,
								activity.getString(R.string.no_internet_found),
								Toast.LENGTH_LONG).show();
					break;
				}
				
					Toast.makeText(MainActivity.this, 
							String.format("%s has been clicked", 
							((CarouselItem)parent.getChildAt(position)).getName()), 
							Toast.LENGTH_SHORT).show();
				
			}
        	
        });

        carousel.setOnItemSelectedListener(new OnItemSelectedListener(){

			public void onItemSelected(CarouselAdapter<?> parent, View view,
					int position, long id) {
				
			}

			public void onNothingSelected(CarouselAdapter<?> parent) {
			}
        	
        }
        );*/
        
        /** News Listener */
        news_listener = new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if(CheckInternet.getConnectivityStatus(activity)!=CheckInternet.TYPE_NOT_CONNECTED)
				businessLogic.invokeNews();
				else
					Toast.makeText(activity,
							activity.getString(R.string.no_internet_found),
							Toast.LENGTH_LONG).show();
			}
		};
		
		/*carousel.setOnTouchListener(new OnTouchListener() {
			
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				// TODO Auto-generated method stub
				
				switch(event.getAction()){
				case MotionEvent.ACTION_DOWN:
					
						((ImageView)findViewById(R.id.main_iv_mascot))
						.startAnimation(AnimationUtils.loadAnimation(activity, R.anim.slide_in_top));
						((ImageView)findViewById(R.id.main_iv_building_top))
						.startAnimation(AnimationUtils.loadAnimation(activity, R.anim.slide_out_top));
						((ImageView)findViewById(R.id.main_iv_building_bottom))
						.startAnimation(AnimationUtils.loadAnimation(activity, R.anim.slide_in_bottom));
					
					break;
					
				case MotionEvent.ACTION_UP:
					((ImageView)findViewById(R.id.main_iv_mascot))
					.startAnimation(AnimationUtils.loadAnimation(activity, R.anim.slide_in_top));
					((ImageView)findViewById(R.id.main_iv_building_top))
					.startAnimation(AnimationUtils.loadAnimation(activity, R.anim.slide_out_top));
					((ImageView)findViewById(R.id.main_iv_building_bottom))
					.startAnimation(AnimationUtils.loadAnimation(activity, R.anim.slide_in_bottom));
					break;
				}
				
				
				return false;
			}
		});*/
		
		/** Talks Listener */
        talks_listener = new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if(CheckInternet.getConnectivityStatus(activity)!=CheckInternet.TYPE_NOT_CONNECTED)
				businessLogic.invokeTalks();
				else
					Toast.makeText(activity,
							activity.getString(R.string.no_internet_found),
							Toast.LENGTH_LONG).show();
			}
		};
		
		/** Privacy Policy Listener */
        privacy_policy_listener = new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if(CheckInternet.getConnectivityStatus(activity)!=CheckInternet.TYPE_NOT_CONNECTED)
				{
					Policy_TnC_WebServiceCall getData = new Policy_TnC_WebServiceCall();
					try {
						getData.getDetails(activity, AppConfig.PrivacyPolicyColumn);
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}					
				else
					Toast.makeText(activity,
							activity.getString(R.string.no_internet_found),
							Toast.LENGTH_LONG).show();
			}
		};
		
		/** T&C Listener */
        tnc_listener = new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if(CheckInternet.getConnectivityStatus(activity)!=CheckInternet.TYPE_NOT_CONNECTED)
				{
					Policy_TnC_WebServiceCall getData = new Policy_TnC_WebServiceCall();
					try {
						getData.getDetails(activity, AppConfig.TnCColumn);
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				else
					Toast.makeText(activity,
							activity.getString(R.string.no_internet_found),
							Toast.LENGTH_LONG).show();
			}
		};
		
		/** Menu Listener */
        menu_listener = new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
				PopupMenu popup = new PopupMenu(activity, menu_click);
				popup.getMenuInflater().inflate(R.menu.popup, popup.getMenu());
				popup.setOnMenuItemClickListener(new OnMenuItemClickListener() {
					
					@Override
					public boolean onMenuItemClick(MenuItem item) {
						// TODO Auto-generated method stub
						
						switch(item.getItemId()){
						
						case R.id.item_school:
							if(CheckInternet.getConnectivityStatus(activity)!=CheckInternet.TYPE_NOT_CONNECTED)
								businessLogic.invokeSchoolActivity();
								else
									Toast.makeText(activity,
											activity.getString(R.string.no_internet_found),
											Toast.LENGTH_LONG).show();
							break;
							
						case R.id.item_college:
							if(CheckInternet.getConnectivityStatus(activity)!=CheckInternet.TYPE_NOT_CONNECTED)
								businessLogic.invokeCollegeActivity();
								else
									Toast.makeText(activity,
											activity.getString(R.string.no_internet_found),
											Toast.LENGTH_LONG).show();
							break;
							
						case R.id.item_hobby:
							if(CheckInternet.getConnectivityStatus(activity)!=CheckInternet.TYPE_NOT_CONNECTED)
								businessLogic.invokeHobbyClassesActivity();
								else
									Toast.makeText(activity,
											activity.getString(R.string.no_internet_found),
											Toast.LENGTH_LONG).show();
							break;
							
						case R.id.item_tuition:
							if(CheckInternet.getConnectivityStatus(activity)!=CheckInternet.TYPE_NOT_CONNECTED)
								businessLogic.invokeTutorActivity();
								else
									Toast.makeText(activity,
											activity.getString(R.string.no_internet_found),
											Toast.LENGTH_LONG).show();
							break;
							
						case R.id.item_coaching:
							if(CheckInternet.getConnectivityStatus(activity)!=CheckInternet.TYPE_NOT_CONNECTED)
								businessLogic.invokeCoachingActivity();
								else
									Toast.makeText(activity,
											activity.getString(R.string.no_internet_found),
											Toast.LENGTH_LONG).show();
							break;
							
						case R.id.item_career:
							if(CheckInternet.getConnectivityStatus(activity)!=CheckInternet.TYPE_NOT_CONNECTED)
								businessLogic.invokeCareerAndMore();
								else
									Toast.makeText(activity,
											activity.getString(R.string.no_internet_found),
											Toast.LENGTH_LONG).show();
							break;
							
						case R.id.item_professional:
							if(CheckInternet.getConnectivityStatus(activity)!=CheckInternet.TYPE_NOT_CONNECTED)
								businessLogic.invokeProfessional();
								else
									Toast.makeText(activity,
											activity.getString(R.string.no_internet_found),
											Toast.LENGTH_LONG).show();
							break;
							
						case R.id.item_workshop:
							if(CheckInternet.getConnectivityStatus(activity)!=CheckInternet.TYPE_NOT_CONNECTED)
								businessLogic.invokeWorkshopAndEvents();
								else
									Toast.makeText(activity,
											activity.getString(R.string.no_internet_found),
											Toast.LENGTH_LONG).show();
							break;
							
						case R.id.item_shop:
							if(CheckInternet.getConnectivityStatus(activity)!=CheckInternet.TYPE_NOT_CONNECTED)
								//businessLogic.invokeShops();
								Toast.makeText(activity,
										activity.getString(R.string.coming_soon),
										Toast.LENGTH_LONG).show();
								else
									Toast.makeText(activity,
											activity.getString(R.string.no_internet_found),
											Toast.LENGTH_LONG).show();
							break;
							
						case R.id.item_news:
							if(CheckInternet.getConnectivityStatus(activity)!=CheckInternet.TYPE_NOT_CONNECTED)
								businessLogic.invokeNews();
								else
									Toast.makeText(activity,
											activity.getString(R.string.no_internet_found),
											Toast.LENGTH_LONG).show();
							break;
							
						case R.id.item_talks:
							if(CheckInternet.getConnectivityStatus(activity)!=CheckInternet.TYPE_NOT_CONNECTED)
								businessLogic.invokeTalks();
								else
									Toast.makeText(activity,
											activity.getString(R.string.no_internet_found),
											Toast.LENGTH_LONG).show();
							break;
							
						case R.id.item_version:
							showDialogForAbout();
							break;
							
						case R.id.item_write_to_us:
							showDialogForWriteToUs();
							break;
						
						}						
						
						return true;
					}
				});
				popup.show();
			}
		};
		
		search_listener = new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if(CheckInternet.getConnectivityStatus(activity)!=CheckInternet.TYPE_NOT_CONNECTED)
					businessLogic.invokeSearchGetCity();
					else
						Toast.makeText(activity,
								activity.getString(R.string.no_internet_found),
								Toast.LENGTH_LONG).show();
			}
		};

        /*news_click.setOnClickListener(news_listener);
		talks_click.setOnClickListener(talks_listener);
		privacy_policy_click.setOnClickListener(privacy_policy_listener);
		tnc_click.setOnClickListener(tnc_listener);
		menu_click.setOnClickListener(menu_listener);*/
    }
    
    public void setVersion(){
		PackageManager packageMgr = activity.getPackageManager();
		PackageInfo packageInfo = null;
		
		try
		{
			packageInfo = packageMgr.getPackageInfo(activity.getPackageName(), 0);
			AppConfig.VERSION = packageInfo.versionName;
		}
		catch(NameNotFoundException e)
		{
			e.printStackTrace();
		}
	}
    
    public void showDialogForAbout()
    {
    	Dialog mDialog = new Dialog(activity);
    	mDialog.setContentView(R.layout.dialog_about);
    	mDialog.setTitle(activity.getString(R.string.version));
    	
    	TextView app_version = (TextView) mDialog.findViewById(R.id.about_tv_versionName);
    	app_version.setText(AppConfig.VERSION);
    	
    	mDialog.show();
    }
    
    public void showDialogForWriteToUs()
    {
    	Dialog mDialog = new Dialog(activity);
    	mDialog.setContentView(R.layout.dialog_write_to_us);
    	mDialog.setTitle(activity.getString(R.string.write_to_us));
    	
    	final EditText name = (EditText) mDialog.findViewById(R.id.writeToUs_et_name);
    	final EditText email = (EditText) mDialog.findViewById(R.id.writeToUs_et_email);
    	final EditText phone = (EditText) mDialog.findViewById(R.id.writeToUs_et_phone);
    	final EditText message = (EditText) mDialog.findViewById(R.id.writeToUs_et_message);
    	final TextView remaining_length = (TextView) mDialog.findViewById(R.id.writeToUs_tv_length);
    	Button submit = (Button) mDialog.findViewById(R.id.writeToUs_btn_submit);
    	
    	if(AppConfig.UserModel!=null)
    	{
    		name.setText(AppConfig.UserModel.getNameString());
    		email.setText(AppConfig.UserModel.getEmailString());
    		phone.setText(AppConfig.UserModel.getPhoneString());
    	}
    	
    	mDialog.show();
    	MainActivity.mDialog = mDialog;
    	
    	message.addTextChangedListener(new TextWatcher() {
			
			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void afterTextChanged(Editable s) {
				// TODO Auto-generated method stub
				int length = message.getText().toString().length();
				int remainingLength = 250 - length;
				remaining_length.setText(" " + String.valueOf(remainingLength));
			}
		});
    	
    	submit.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				name_text = name.getText().toString().trim();
		    	email_text = email.getText().toString().trim();
		    	phone_text = phone.getText().toString().trim();
		    	message_text = message.getText().toString();
		    	
		    	String validationsCheck = validationsCheck();
		    	
		    	if(validationsCheck.equalsIgnoreCase("Success"))
		    	{
		    		WriteToUsWebServiceCall writeToUsServiceCall = new WriteToUsWebServiceCall();
		        	try {
		    			writeToUsServiceCall.getDetails(activity, name_text, email_text, phone_text, message_text);
		    		} catch (Exception e) {
		    			// TODO Auto-generated catch block
		    			e.printStackTrace();
		    		}
		    	}
			}
		});
    	
    	/*WriteToUsWebServiceCall writeToUsServiceCall = new WriteToUsWebServiceCall();
    	try {
			writeToUsServiceCall.getDetails(activity, name_text, email_text, phone_text, message_text);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
    }
    
    public String validationsCheck()
    {
    	String validationsCheck = "";
    	boolean checkMessage = false;
    	
    	try{
    		checkMessage = message_text.matches(message_pattern);
    	} catch(NullPointerException e){
    		e.printStackTrace();
    		Log.e(TAG, "NullPointerException: " + e.toString());
    	} catch(PatternSyntaxException e){
    		e.printStackTrace();
    		Log.e(TAG, "PatternSyntaxException: " + e.toString());
    	}
    	
    	if(CheckInternet.getConnectivityStatus(activity)==CheckInternet.TYPE_NOT_CONNECTED){
			Toast.makeText(activity,
					activity.getString(R.string.no_internet_found),
					Toast.LENGTH_LONG).show();
		}
    	else if(name_text.equalsIgnoreCase("") ||
    			email_text.equalsIgnoreCase("") ||
    			phone_text.equalsIgnoreCase("") ||
    			message_text.equalsIgnoreCase(""))
    	{
    		Toast.makeText(activity, activity.getString(R.string.fill_all_fields), Toast.LENGTH_LONG).show();
    	}
    	else if(!name_text.matches(name_pattern))
    	{
    		Toast.makeText(activity,
					activity.getString(R.string.invalid_name),
					Toast.LENGTH_LONG).show();
    	}
    	else if(!email_text.matches(email_pattern))
    	{
    		Toast.makeText(activity,
					activity.getString(R.string.invalid_email_id),
					Toast.LENGTH_LONG).show();
    	}
    	else if(phone_text.length()<10) {
			
			Toast.makeText(activity,
					activity.getString(R.string.invalid_phone_no),
					Toast.LENGTH_LONG).show();
		}
    	else if(message_text.length()<10) {
			
			Toast.makeText(activity,
					activity.getString(R.string.invalid_message),
					Toast.LENGTH_LONG).show();
		}
    	/*else if(!checkMessage)
    	{
    		Toast.makeText(activity,
					"Special characters allowed for message are @#$*_(),. ",
					Toast.LENGTH_LONG).show();
    	}*/
    	else {
    		validationsCheck = "Success";
    	}
    	
    	return validationsCheck;
    }
    
    /** Check Version and Update Alert */
    public void checkVersionAndUpdate()
    {
    	if(CheckInternet.getConnectivityStatus(activity)!=CheckInternet.TYPE_NOT_CONNECTED)
    	new VersionCheckHelper(activity).execute();
    }
    
    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
    	// TODO Auto-generated method stub
    	super.onWindowFocusChanged(hasFocus);
    	
    	((ImageView)findViewById(R.id.main_iv_mascot))
		.startAnimation(AnimationUtils.loadAnimation(activity, R.anim.slide_in_top));
		((ImageView)findViewById(R.id.main_iv_building_top))
		.startAnimation(AnimationUtils.loadAnimation(activity, R.anim.slide_out_top));
		((ImageView)findViewById(R.id.main_iv_building_bottom))
		.startAnimation(AnimationUtils.loadAnimation(activity, R.anim.slide_in_bottom));
    }

	private void allowPermission() {
		if (ContextCompat.checkSelfPermission(activity,
				permissions[count])
				!=PackageManager.PERMISSION_GRANTED){

			// Should we show an explanation?
			/*if (ActivityCompat.shouldShowRequestPermissionRationale(activity,
					permissions[count])) {
				Log.e(TAG,"2");
				// Show an expanation to the user *asynchronously* -- don't block
				// this thread waiting for the user's response! After the user
				// sees the explanation, try again to request the permission.

			} else {*/
			// No explanation needed, we can request the permission.

			ActivityCompat.requestPermissions(activity,
					new String[]{permissions[count]},
					count);

			// MY_PERMISSIONS_REQUEST_READ_CONTACTS is an
			// app-defined int constant. The callback method gets the
			// result of the request.
//			}
		}
		else {
			if((ContextCompat.checkSelfPermission(activity,
					Manifest.permission.CALL_PHONE)
					==PackageManager.PERMISSION_GRANTED) &&
					(ContextCompat.checkSelfPermission(activity,
							Manifest.permission.GET_ACCOUNTS)
							==PackageManager.PERMISSION_GRANTED)) {
				callInitMethods();
			}
			else {
				count++;
				allowPermission();
			}
		}
	}

	@Override
	public void onRequestPermissionsResult(int requestCode,
										   String permissions[], int[] grantResults) {
		if(count<this.permissions.length)
		{
			switch (requestCode) {
				case MY_PERMISSIONS_REQUEST_CALL_PHONE: {
					// If request is cancelled, the result arrays are empty.
					if (grantResults.length > 0
							&& grantResults[0] == PackageManager.PERMISSION_GRANTED) {
//						Toast.makeText(this, "CALL_PHONE Permission Granted", Toast.LENGTH_LONG).show();
						count ++;
						allowPermission();
						// permission was granted, yay! Do the
						// contacts-related task you need to do.
					} else
						(new SuggestionDialog(activity)).initAlertDialogForFinish("Please grant all permissions for the app to work properly.");
//						Toast.makeText(this, "CALL_PHONE Permission not granted", Toast.LENGTH_LONG).show();


					return;
				}

				// other 'case' lines to check for other
				// permissions this app might request
				case MY_PERMISSIONS_REQUEST_GET_ACCOUNTS: {
					// If request is cancelled, the result arrays are empty.
					if (grantResults.length > 0
							&& grantResults[0] == PackageManager.PERMISSION_GRANTED) {
//						Toast.makeText(this, "GET_ACCOUNTS Permission Granted", Toast.LENGTH_LONG).show();
						allowPermission();
						// permission was granted, yay! Do the
						// contacts-related task you need to do.
					} else
						(new SuggestionDialog(activity)).initAlertDialogForFinish("Please grant all permissions for the app to work properly.");
//						Toast.makeText(this, "GET_ACCOUNTS Permission not granted", Toast.LENGTH_LONG).show();


					return;
				}

			}
		}
		else{
			return;
		}

	}

	/********************************************/





	public void signIn() {
		Intent signInIntent = Auth.GoogleSignInApi.getSignInIntent(mGoogleApiClient);
		this.activity.startActivityForResult(signInIntent, RC_SIGN_IN);
		if(!mGoogleApiClient.isConnected())
			mGoogleApiClient.connect();
		Log.e(TAG,"signIn");
	}


	public void signOut() {
		if(mGoogleApiClient.isConnected()) {
		Auth.GoogleSignInApi.signOut(mGoogleApiClient).setResultCallback(
				new ResultCallback<Status>() {
					@Override
					public void onResult(Status status) {
						updateUI(false);
					}
				});

		mGoogleApiClient.disconnect();
		}
	}

	public void revokeAccess() {
		Auth.GoogleSignInApi.revokeAccess(mGoogleApiClient).setResultCallback(
				new ResultCallback<Status>() {
					@Override
					public void onResult(Status status) {
						updateUI(false);
					}
				});
	}

	private void handleSignInResult(GoogleSignInResult result) {
		Log.e(TAG, "handleSignInResult:" + result.isSuccess());
		if (result.isSuccess()) {
			// Signed in successfully, show authenticated UI.
			GoogleSignInAccount acct = result.getSignInAccount();

			Log.e(TAG, "display name: " + acct.getDisplayName());

			String personName = acct.getDisplayName();
//			String personPhotoUrl = acct.getPhotoUrl().toString();
			String email = acct.getEmail();

			Log.e(TAG, "Name: " + personName + ", email: " + email
					/*+ ", Image: " + personPhotoUrl*/);

			MainActivity.userIdentificaton.setText(personName.toUpperCase());
            /*Glide.with(activity).load(personPhotoUrl)
                    .thumbnail(0.5f)
                    .crossFade()
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .into(imgProfilePic);*/

			// String Platform = jsonObject.getString("Platform");
			// String SysDateTime = jsonObject.getString("SysDateTime");
			String UserID = acct.getEmail();
			String UserName = acct.getDisplayName();

			UserModel model = new UserModel();

			model.setEmailString(UserID);
			model.setNameString(UserName);
			model.setPhoneString("");
//			model.setPasswordString(UserID);

			AppConfig.UserModel = model;

			updateUI(true);
		} else {
			// Signed out, show unauthenticated UI.
			updateUI(false);
		}
	}

	@Override
	public void onClick(View v) {
		int id = v.getId();

		switch (id) {
		}
	}

	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		Log.e(TAG, "onActivityResult");
		// Result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...);
		if (requestCode == RC_SIGN_IN) {
			Log.e(TAG, "RC_SIGN_IN: " + data);
			GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
			handleSignInResult(result);
		}
	}

	@Override
	public void onStart() {
		super.onStart();

		OptionalPendingResult<GoogleSignInResult> opr = Auth.GoogleSignInApi.silentSignIn(mGoogleApiClient);
		if (opr.isDone()) {
			// If the user's cached credentials are valid, the OptionalPendingResult will be "done"
			// and the GoogleSignInResult will be available instantly.
			Log.d(TAG, "Got cached sign-in");
			GoogleSignInResult result = opr.get();
//			handleSignInResult(result);
		} else {
			// If the user has not previously signed in on this device or the sign-in has expired,
			// this asynchronous branch will attempt to sign in the user silently.  Cross-device
			// single sign-on will occur in this branch.
//			showProgressDialog();
			opr.setResultCallback(new ResultCallback<GoogleSignInResult>() {
				@Override
				public void onResult(GoogleSignInResult googleSignInResult) {
					hideProgressDialog();
//					handleSignInResult(googleSignInResult);
				}
			});
		}
	}

	@Override
	public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
		// An unresolvable error has occurred and Google APIs (including Sign-In) will not
		// be available.
		Log.d(TAG, "onConnectionFailed:" + connectionResult);
	}

	private void showProgressDialog() {
		if (mProgressDialog == null) {
			mProgressDialog = new ProgressDialog(this);
			mProgressDialog.setMessage(getString(R.string.loading));
			mProgressDialog.setIndeterminate(true);
		}

		mProgressDialog.show();
	}

	private void hideProgressDialog() {
		if (mProgressDialog != null && mProgressDialog.isShowing()) {
			mProgressDialog.hide();
		}
	}

	private void updateUI(boolean isSignedIn) {
		if (isSignedIn) {
//			activity.finish();

			FooterBusinessLogic footerBusinessLogic = new FooterBusinessLogic(activity);
			footerBusinessLogic.invokeRegistrationLogic(AppConfig.UserModel);

			dataBaseClass = new DataBaseClass(activity);
			dataBaseClass.createUserDetails(AppConfig.UserModel);

            ((TextView)activity.findViewById(R.id.login)).requestFocus();
            ((TextView)activity.findViewById(R.id.login)).setText(activity.getString(R.string.logout));

//			MainActivity.ref.finish();
//			Intent intent = new Intent(activity, MainActivity.class);
//			activity.startActivity(intent);

			Toast.makeText(
					activity,
					activity.getString(R.string.login_successfully),
					Toast.LENGTH_LONG).show();
		} else {
			Toast.makeText(activity,
					activity.getString(R.string.logout_successfully),
					Toast.LENGTH_SHORT).show();
		}
	}
}

