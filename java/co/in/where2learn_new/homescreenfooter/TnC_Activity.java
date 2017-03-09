package co.in.where2learn_new.homescreenfooter;

import android.app.Activity;
import android.os.Bundle;
import android.webkit.WebView;
import co.in.where2learn_new.R;
import co.in.where2learn_new.config.AppConfig;

public class TnC_Activity extends Activity{

	private Activity activity;
	private String TAG = this.getClass().getName();
	
	private WebView tnc;
	private String tnc_text;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_tnc);
		
		this.activity = this;
		
		initComponents();
		
		setTextView();
	}
	
	public void initComponents()
	{
		tnc = (WebView) findViewById(R.id.tnc_tv_content);
		tnc_text = "";
	}
	
	public void setTextView()
	{
		tnc_text = AppConfig.Policy_TnC_text;
		
		if(tnc_text.length()>0)
			tnc.loadDataWithBaseURL(null, tnc_text, "text/html", "utf-8", null);
	}
	
}
