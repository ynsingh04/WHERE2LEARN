package co.in.where2learn_new.detailspage;

import java.util.Map;

import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnDismissListener;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import co.in.where2learn_new.R;
import co.in.where2learn_new.businesslogic.DetailsPageBusinessLogic;
import co.in.where2learn_new.config.AppConfig;
import co.in.where2learn_new.util.ChangeFooter;
import co.in.where2learn_new.util.CheckAnAppInstalledOrNot;
import co.in.where2learn_new.util.CheckInternet;
import co.in.where2learn_new.util.DrawableManager;
import co.in.where2learn_new.util.MapUtil;
import co.in.where2learn_new.util.NotifyMe;
import co.in.where2learn_new.util.RatingDialog;

public class CareerDetailsActivity extends Activity {

	private String TAG = this.getClass().getName();

	private Activity activity;

	private Map<String, String> classifiedDetails;
	
	private String classifiedID = "";
	
	private ProgressDialog ringProgressDialog;

	private boolean isError = false;

	private TextView titleTextView;

	private TextView callTextView;
	private TextView mapTextView;
	private TextView shareTextView;
	private TextView ratingTextView;
	private TextView notifyTextView;
	private TextView displayImagesTextView;

	private ImageView bannerImageView;
	
	private ImageView image1ImageView;
	private ImageView image2ImageView;
	private ImageView image3ImageView;
	private ImageView image4ImageView;

	private TextView descriptionTextView;
	private TextView facilities_availableTextView;
	private TextView mobilenumberTextView;
	private TextView mobilenumberTwoTextView;
	private TextView typeTextView;
	private TextView addressTextView;
	private TextView external_link1TextView;
	private TextView external_link2TextView;

	private RatingBar ratingRatingBar;

	private View.OnClickListener mobilenumberOnClickListener;
	private View.OnClickListener mobilenumberTwoOnClickListener;
	private View.OnClickListener callOnClickListener;
	private View.OnClickListener external_link1OnClickListener;
	private View.OnClickListener external_link2OnClickListener;

	private ImageView backImageView;

	private View.OnClickListener backOnClickListener;

	private DrawableManager drawableManager;

	private DetailsPageBusinessLogic detailsPageBusinessLogic;
	private RatingDialog ratingDialog;
	private NotifyMe notifyMe;
	private MapUtil mapUtil;

	private View.OnClickListener mapTextViewOnClickListener;
	private View.OnClickListener shareTextViewOnClickListener;
	private View.OnClickListener ratingTextViewOnClickListener;
	private View.OnClickListener notifyTextViewOnClickListener;
	private View.OnClickListener displayImagesTextViewOnClickListener;
	private View.OnClickListener imageViewOnClickListener;
	
	public static String CATEGORY = "";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_career_details);

		activity = this;

		getActionBar().hide();
		changeFooter();

		initListener();
		initComponent();
	}

	@Override
	protected void onResume() {
		super.onResume();
		changeFooter();
	}

	private void initListener() {

		backOnClickListener = new View.OnClickListener() {

			@Override
			public void onClick(View v) {

				onBackPressed();

			}
		};

		mobilenumberOnClickListener = new View.OnClickListener() {

			@Override
			public void onClick(View v) {

				String mobileNumber = mobilenumberTextView.getText().toString();
				detailsPageBusinessLogic.invokePhoneCall(mobileNumber);
				Log.e(TAG, mobileNumber);
			}
		};
		
		mobilenumberTwoOnClickListener = new View.OnClickListener() {

			@Override
			public void onClick(View v) {

				String mobileNumber = mobilenumberTwoTextView.getText().toString();
				detailsPageBusinessLogic.invokePhoneCall(mobileNumber);
				Log.e(TAG, mobileNumber);
			}
		};
		
		callOnClickListener = new View.OnClickListener() {

			@Override
			public void onClick(View v) {				
				if(mobilenumberTextView.getText().toString().length()>0) {
					String mobileNumber = mobilenumberTextView.getText().toString();
					detailsPageBusinessLogic.invokePhoneCall(mobileNumber);
				}					
				else if(mobilenumberTwoTextView.getText().toString().length()>0) {
					String mobileNumber = mobilenumberTwoTextView.getText().toString();
					detailsPageBusinessLogic.invokePhoneCall(mobileNumber);
				}
			}
		};

		mapTextViewOnClickListener = new View.OnClickListener() {

			@Override
			public void onClick(View v) {

				String address = classifiedDetails.get("address");

				if (address != null) {
					mapUtil.gpsMethod(address);
				}

			}
		};

		shareTextViewOnClickListener = new View.OnClickListener() {

			@Override
			public void onClick(View v) {

				Intent sharingIntent = new Intent(
						android.content.Intent.ACTION_SEND);
				sharingIntent.setType("text/plain");
				
//				String shareBody = classifiedDetails.get("description");
				String shareBody = activity.getString(R.string.career_details_type_career) +
						" - Details sent via " + activity.getString(R.string.app_name) + " app:-"+
						"\nName: " + classifiedDetails.get("title") +
						"\nPhone No.: " + mobilenumberTextView.getText().toString() + "\n" + mobilenumberTwoTextView.getText().toString() +
						"\nAddress: " + classifiedDetails.get("address");
				
				sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT,
						activity.getString(R.string.app_name));
				/*sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT,
						"WHERE2LEARN: " + activity.getString(R.string.career_details_type_career) + "-" +
								"\nName: " + classifiedDetails.get("title") +
								"\nPhone No.: " + mobilenumberTextView.getText().toString() + "\n" + mobilenumberTwoTextView.getText().toString() +
								"\nAddress: " + classifiedDetails.get("address"));*/
				sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT,
						shareBody);
				startActivity(Intent.createChooser(sharingIntent, "Share via"));
				
				/*CheckAnAppInstalledOrNot checkWhatsApp = new CheckAnAppInstalledOrNot(activity);
				if(checkWhatsApp.appInstalledOrNot("com.whatsapp")) {
					sharingIntent.setPackage("com.whatsapp");
					startActivity(Intent.createChooser(sharingIntent, "Share via"));
				}*/
			}
		};

		ratingTextViewOnClickListener = new View.OnClickListener() {

			@Override
			public void onClick(View v) {

				if (classifiedDetails.get("id") != null) {
					if (AppConfig.UserModel != null) {
						ratingDialog.setClassifiedID(classifiedDetails.get("id"));
						ratingDialog.show();
					}
					else {
						Toast.makeText(activity,
								activity.getString(R.string.please_login),
								Toast.LENGTH_LONG).show();
					}
				}
			}
		};

		notifyTextViewOnClickListener = new View.OnClickListener() {

			@Override
			public void onClick(View v) {

				if (classifiedDetails.get("id") != null) {
					String classifiedID = classifiedDetails.get("id");
					notifyMe.notify(classifiedID);
				}

			}
		};
		
		displayImagesTextViewOnClickListener = new View.OnClickListener() {

			@Override
			public void onClick(View v) {

				if (classifiedDetails.get("id") != null) {
					classifiedID = classifiedDetails.get("id");
					try {
						getImages();
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}

			}
		};
		
		imageViewOnClickListener = new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub				
				String contentDescription = null;
				
				if(v==image1ImageView)
				{
					contentDescription = image1ImageView.getContentDescription().toString();
				}
				else if(v==image2ImageView)
				{
					contentDescription = image2ImageView.getContentDescription().toString();
				}
				else if(v==image3ImageView)
				{
					contentDescription = image3ImageView.getContentDescription().toString();
				}
				else if(v==image4ImageView)
				{
					contentDescription = image4ImageView.getContentDescription().toString();
				}
				
				if(contentDescription!=null){
					
					Intent intent = new Intent(android.content.Intent.ACTION_VIEW);
					intent.setData(Uri.parse(contentDescription));
					startActivity(intent);
				}
//				startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(contentDescription)));
			}
		};
		
		external_link1OnClickListener = new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
				if(!external_link1TextView.getText().toString().equalsIgnoreCase(""))
				{
					if(CheckInternet.getConnectivityStatus(activity) == CheckInternet.TYPE_NOT_CONNECTED) {
						Toast.makeText(activity, 
								activity.getString(R.string.no_internet_found), 
								Toast.LENGTH_LONG).show();
						return;
					}
					
					String url = external_link1TextView.getText().toString();
					Intent i = new Intent(Intent.ACTION_VIEW);
					i.setData(Uri.parse(url));
					startActivity(i);
				}
			}
		};
		
		external_link2OnClickListener = new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
				if(!external_link2TextView.getText().toString().equalsIgnoreCase(""))
				{
					if(CheckInternet.getConnectivityStatus(activity) == CheckInternet.TYPE_NOT_CONNECTED) {
						Toast.makeText(activity, 
								activity.getString(R.string.no_internet_found), 
								Toast.LENGTH_LONG).show();
						return;
					}
					
					String url = external_link2TextView.getText().toString();
					Intent i = new Intent(Intent.ACTION_VIEW);
					i.setData(Uri.parse(url));
					startActivity(i);
				}
			}
		};

	}

	private void initComponent() {
		
		((RelativeLayout)findViewById(R.id.footer_rl_location)).setVisibility(View.GONE);
		((TextView)findViewById(R.id.location)).setVisibility(View.GONE);

		detailsPageBusinessLogic = new DetailsPageBusinessLogic(activity);
		ratingDialog = new RatingDialog(activity);
		notifyMe = new NotifyMe(activity);
		mapUtil = new MapUtil(activity);

		classifiedDetails = AppConfig.CLASSIFIEDMODEL;

		bannerImageView = (ImageView) findViewById(R.id.activity_career_details_bannerImageView);
		
		image1ImageView = (ImageView) findViewById(R.id.activity_career_details_image1ImageView);
		image2ImageView = (ImageView) findViewById(R.id.activity_career_details_image2ImageView);
		image3ImageView = (ImageView) findViewById(R.id.activity_career_details_image3ImageView);
		image4ImageView = (ImageView) findViewById(R.id.activity_career_details_image4ImageView);
		
		image1ImageView.setOnClickListener(imageViewOnClickListener);
		image2ImageView.setOnClickListener(imageViewOnClickListener);
		image3ImageView.setOnClickListener(imageViewOnClickListener);
		image4ImageView.setOnClickListener(imageViewOnClickListener);

		backImageView = (ImageView) findViewById(R.id.career_detailsBackImageView);
		titleTextView = (TextView) findViewById(R.id.activity_career_details_titleTextView);
		typeTextView = (TextView) findViewById(R.id.activity_career_details_typeTextView);
		external_link1TextView = (TextView) findViewById(R.id.activity_career_details_external_link1TextView);
		external_link2TextView = (TextView) findViewById(R.id.activity_career_details_external_link2TextView);

		callTextView = (TextView) findViewById(R.id.activity_career_details_callTextView);
		mapTextView = (TextView) findViewById(R.id.activity_career_details_mapTextView);
		shareTextView = (TextView) findViewById(R.id.activity_career_details_shareTextView);
		ratingTextView = (TextView) findViewById(R.id.activity_career_details_ratingTextView);
		notifyTextView = (TextView) findViewById(R.id.activity_career_details_notifyTextView);
		displayImagesTextView = (TextView) findViewById(R.id.activity_career_details_displayImagesTextView);

		descriptionTextView = (TextView) findViewById(R.id.activity_career_details_descriptionTextView);
		typeTextView.setText(activity
				.getString(R.string.career_details_type_career));
		if(!CATEGORY.equals(""))
			typeTextView.setText(CATEGORY);
		
		if(classifiedDetails.get("link0") != null && classifiedDetails.get("link1") != null &&
				!classifiedDetails.get("link0").equalsIgnoreCase("null") && !classifiedDetails.get("link1").equalsIgnoreCase("null")) {
			external_link1TextView.setText(classifiedDetails.get("link0"));
			external_link2TextView.setText(classifiedDetails.get("link1"));
		}
		else if(classifiedDetails.get("link0") != null && !classifiedDetails.get("link0").equalsIgnoreCase("null")) {
			external_link1TextView.setText(classifiedDetails.get("link0"));
		}
		else if(classifiedDetails.get("link1") != null && !classifiedDetails.get("link1").equalsIgnoreCase("null")) {
			external_link2TextView.setText(classifiedDetails.get("link1"));
		}
		Log.e(TAG, "External link: " + external_link1TextView.getText().toString() + "\n" + external_link2TextView.getText().toString());
		
		facilities_availableTextView = (TextView) findViewById(R.id.activity_career_details_facilities_availableTextView);
		addressTextView = (TextView) findViewById(R.id.activity_career_details_addressTextView);
		mobilenumberTextView = (TextView) findViewById(R.id.activity_career_details_mobilenumberTextView);
		mobilenumberTwoTextView = (TextView) findViewById(R.id.activity_career_details_mobilenumberTwoTextView);

		ratingRatingBar = (RatingBar) findViewById(R.id.activity_career_details_ratingRatingBar);

		Log.i(TAG, classifiedDetails.get("title") + "");

		titleTextView.setText(classifiedDetails.get("title"));		
		descriptionTextView.setText(classifiedDetails.get("description"));

		facilities_availableTextView.setText(classifiedDetails
				.get("facilitiesValue"));

		String phone = classifiedDetails.get("mobilenumber");
		if((phone.charAt(phone.length()-1)) == ',') {
			phone = phone.replace(",", "");
			mobilenumberTextView.setText(phone);
		}
		else if((phone.charAt(0)) == ',') {
			phone = phone.replace(",", "");
			mobilenumberTwoTextView.setText(phone);
		}
		else if(phone.contains(",")) {
			String[] phones = phone.split(",");
			String phone1 = phones[0];
			String phone2 = phones[1];
			mobilenumberTextView.setText(phone1);
			mobilenumberTwoTextView.setText(phone2);
		}

		addressTextView.setText(classifiedDetails.get("address"));

		if (!classifiedDetails.get("rating").equalsIgnoreCase("null")) {

			float rating = Float.parseFloat(classifiedDetails.get("rating"));
			ratingRatingBar.setRating(rating);
		}

		callTextView.setOnClickListener(callOnClickListener);
		mapTextView.setOnClickListener(mapTextViewOnClickListener);
		shareTextView.setOnClickListener(shareTextViewOnClickListener);
		ratingTextView.setOnClickListener(ratingTextViewOnClickListener);
		notifyTextView.setOnClickListener(notifyTextViewOnClickListener);
		displayImagesTextView.setOnClickListener(displayImagesTextViewOnClickListener);

		backImageView.setOnClickListener(backOnClickListener);
		mobilenumberTextView.setOnClickListener(mobilenumberOnClickListener);
		mobilenumberTwoTextView.setOnClickListener(mobilenumberTwoOnClickListener);
		external_link1TextView.setOnClickListener(external_link1OnClickListener);
		external_link2TextView.setOnClickListener(external_link2OnClickListener);

		/*String imageURL = AppConfig.SITE_URL
				+ classifiedDetails.get("imageurl");

		if (classifiedDetails.get("imageurl") != null) {

			drawableManager = new DrawableManager();

			initImage(imageURL, bannerImageView);

		}*/
		
				
		
	}
	
	public void getImages() throws Exception {

		ringProgressDialog = ProgressDialog.show(activity, "Please wait ...",
				"Fetching Data ...", true);
		ringProgressDialog.setCancelable(false);
		new Thread(new Runnable() {

			@Override
			public void run() {
				try {
					
					callImageGalleriesSearchService();

				} catch (Exception e) {
					Log.e(TAG, e.toString());
					isError = true;
				}
				ringProgressDialog.dismiss();

			}
		}).start();

		ringProgressDialog.setOnDismissListener(new OnDismissListener() {

			@Override
			public void onDismiss(DialogInterface dialog) {

				if (isError) {

					isError = false;
					Toast.makeText(
							activity,
							activity.getString(R.string.error_in_data_initialization),
							Toast.LENGTH_LONG).show();
				} else {
					/**** Setting images ***/
						setImages();
					/**** End Setting images ***/
				}

			}
		});

	}
	
	/**
	 * This method is called to call web service
	 */
	public synchronized void callImageGalleriesSearchService() throws Exception {

		String URI = AppConfig.URI_GETIMAGEGALLERIESSEARCH + "?id="
				+ classifiedID;

		Log.i(TAG, URI);

		if (android.os.Build.VERSION.SDK_INT > 9) {
			StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
					.permitAll().build();
			StrictMode.setThreadPolicy(policy);
		}

		HttpClient client = new DefaultHttpClient();
		HttpGet httpGet = new HttpGet(URI);

		ResponseHandler<String> handler = new BasicResponseHandler();
		Object result = new Object();

		result = client.execute(httpGet, handler);


		parseImageGalleriesXML(result);

	}

	private void parseImageGalleriesXML(Object result) throws JSONException {

		String resultString = result.toString();

		Log.i(TAG, resultString);
		Log.e(TAG, resultString);

		JSONArray cast = new JSONArray(resultString);

		for (int i = 0; i < cast.length(); i++) {
			JSONObject item = cast.getJSONObject(i);

			String imageurl = item.getString("Imageurl");

			Log.i(TAG, imageurl);

//			classifiedDetails.put("imageurl", imageurl);
			classifiedDetails.put("imageurl"+i, imageurl);

		}
		
		if(resultString.equalsIgnoreCase("[]")){
			((Activity) activity).runOnUiThread(new Runnable() {
			    public void run() {
			    	
			        Toast.makeText(activity, activity.getString(R.string.no_images_found), Toast.LENGTH_LONG).show();
			    }
			});			
		}

	}
	
	public void setImages(){		
		drawableManager = new DrawableManager();
		
		String imageURL = "";
		
		if (classifiedDetails.get("imageurl0") != null) {

			imageURL = AppConfig.SITE_URL + classifiedDetails.get("imageurl0");

			image1ImageView.setVisibility(View.VISIBLE);
			
			bannerImageView.setImageBitmap(null);
			image1ImageView.setImageBitmap(null);
			
			initImage(imageURL, bannerImageView);
			initImage(imageURL, image1ImageView);
			
			image1ImageView.setContentDescription(imageURL);

		}

		if (classifiedDetails.get("imageurl1") != null) {

			imageURL = AppConfig.SITE_URL +  classifiedDetails.get("imageurl1");

			image2ImageView.setVisibility(View.VISIBLE);
			
			image2ImageView.setImageBitmap(null);

			initImage(imageURL, image2ImageView);
			
			image2ImageView.setContentDescription(imageURL);

		}

		if (classifiedDetails.get("imageurl2") != null) {

			imageURL = AppConfig.SITE_URL +  classifiedDetails.get("imageurl2");

			image3ImageView.setVisibility(View.VISIBLE);
			
			image3ImageView.setImageBitmap(null);

			initImage(imageURL, image3ImageView);
			
			image3ImageView.setContentDescription(imageURL);

		}

		if (classifiedDetails.get("imageurl3") != null) {

			imageURL = AppConfig.SITE_URL +  classifiedDetails.get("imageurl3");

			image4ImageView.setVisibility(View.VISIBLE);
			
			image4ImageView.setImageBitmap(null);

			initImage(imageURL, image4ImageView);
			
			image4ImageView.setContentDescription(imageURL);

		}		
	}

	private void initImage(String imageURL, ImageView image1ImageView2) {

		drawableManager.fetchDrawableOnThread(imageURL, image1ImageView2);

	}

	/**
	 * This method is called to change footer bar
	 */
	private void changeFooter() {
		ChangeFooter changeFooter = new ChangeFooter(activity);
		changeFooter.change();

	}
}
