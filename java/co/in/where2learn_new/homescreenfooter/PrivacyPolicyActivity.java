package co.in.where2learn_new.homescreenfooter;

import android.app.Activity;
import android.os.Bundle;
import android.webkit.WebView;
import co.in.where2learn_new.R;
import co.in.where2learn_new.config.AppConfig;

public class PrivacyPolicyActivity extends Activity{

	private Activity activity;
	private String TAG = this.getClass().getName();
	
	private WebView privacy_policy;
	private String privacy_policy_text;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_privacy_policy);
		
		this.activity = this;
		
		initComponents();
		
		setTextView();
	}
	
	public void initComponents()
	{
		privacy_policy = (WebView) findViewById(R.id.policy_tv_content);
		privacy_policy_text = "";
	}
	
	public void setTextView()
	{
		privacy_policy_text = AppConfig.Policy_TnC_text;
		
		if(privacy_policy_text.length()>0)
		privacy_policy.loadDataWithBaseURL(null, privacy_policy_text, "text/html", "utf-8", null);
	}
	
}
