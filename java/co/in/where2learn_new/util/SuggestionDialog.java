package co.in.where2learn_new.util;

import java.util.List;
import java.util.regex.PatternSyntaxException;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnDismissListener;
import android.graphics.Color;
import android.text.Editable;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextPaint;
import android.text.TextWatcher;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import co.in.where2learn_new.R;
import co.in.where2learn_new.config.AppConfig;
import co.in.where2learn_new.webservice.WriteToUsWebServiceCall;

public class SuggestionDialog {

	private Activity context;
	
	private String name_text = "";
	private String email_text = "";
	private String phone_text = "";
	private String message_text = "";
	
	private String email_pattern = "^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$";
	
	private String password_pattern = "^[a-zA-Z0-9@#$*_-]*$";
	
	private String name_pattern = "^[\\p{L} .'-]+$";
	
	private String message_pattern = "^[a-zA-Z0-9@#$*_(),. ]*$";
//	"^[a-zA-Z0-9@#$*_-()/,.]*$";
	
	public static Dialog mDialog;

	public SuggestionDialog(Activity context) {
		this.context = context;
	}

	public void showDialog(List<?> itemList) {

		if (itemList.size() == 0) {

			AlertDialog.Builder builder = new AlertDialog.Builder(context);
			builder.setMessage(context.getString(R.string.sorry_message))
					.setCancelable(false)
					.setPositiveButton("OK",
							new DialogInterface.OnClickListener() {
								public void onClick(DialogInterface dialog,
										int id) {
									// do things
								}
							});
			AlertDialog alert = builder.create();
			alert.show();
			
			SpannableString ss = new SpannableString(context.getString(R.string.sorry_message));
			ClickableSpan clickableSpan = new ClickableSpan() {
			    @Override
			    public void onClick(View textView) {
//			    	context.finish();
			    	showDialogForWriteToUs();
//			        Toast.makeText(context, "Hi", Toast.LENGTH_LONG).show();
			    }
			    @Override
			    public void updateDrawState(TextPaint ds) {
			            super.updateDrawState(ds);
			            ds.setUnderlineText(false);
			        }
			};
			ss.setSpan(clickableSpan, 182, 196, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
			
			((TextView)alert.findViewById(android.R.id.message)).setText(ss);
			((TextView)alert.findViewById(android.R.id.message)).setMovementMethod(LinkMovementMethod.getInstance());
			((TextView)alert.findViewById(android.R.id.message)).setHighlightColor(Color.TRANSPARENT);

		}

	}
	
	public void showDialogForWriteToUs()
    {
    	Dialog mDialog = new Dialog(context);
    	mDialog.setContentView(R.layout.dialog_write_to_us);
    	mDialog.setTitle(context.getString(R.string.write_to_us));
    	
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
    	SuggestionDialog.mDialog = mDialog;
    	
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
		    			writeToUsServiceCall.getDetails(context, name_text, email_text, phone_text, message_text);
		    		} catch (Exception e) {
		    			// TODO Auto-generated catch block
		    			e.printStackTrace();
		    		}
		    	}
			}
		});
    	
    	mDialog.setOnDismissListener(new OnDismissListener() {
			
			@Override
			public void onDismiss(DialogInterface dialog) {
				// TODO Auto-generated method stub
				context.finish();
			}
		});
    }
	
	public String validationsCheck()
    {
    	String validationsCheck = "";
    	boolean checkMessage = false;
    	
    	try{
    		checkMessage = message_text.matches(message_pattern);
    	} catch(NullPointerException e){
    		e.printStackTrace();
    	} catch(PatternSyntaxException e){
    		e.printStackTrace();
    	}
    	
    	if(CheckInternet.getConnectivityStatus(context)==CheckInternet.TYPE_NOT_CONNECTED){
			Toast.makeText(context,
					context.getString(R.string.no_internet_found),
					Toast.LENGTH_LONG).show();
		}
    	else if(name_text.equalsIgnoreCase("") ||
    			email_text.equalsIgnoreCase("") ||
    			phone_text.equalsIgnoreCase("") ||
    			message_text.equalsIgnoreCase(""))
    	{
    		Toast.makeText(context, context.getString(R.string.fill_all_fields), Toast.LENGTH_LONG).show();
    	}
    	else if(!name_text.matches(name_pattern))
    	{
    		Toast.makeText(context,
    				context.getString(R.string.invalid_name),
					Toast.LENGTH_LONG).show();
    	}
    	else if(!email_text.matches(email_pattern))
    	{
    		Toast.makeText(context,
    				context.getString(R.string.invalid_email_id),
					Toast.LENGTH_LONG).show();
    	}
    	else if(phone_text.length()<10) {
			
			Toast.makeText(context,
					context.getString(R.string.invalid_phone_no),
					Toast.LENGTH_LONG).show();
		}
    	else if(message_text.length()<10) {
			
			Toast.makeText(context,
					context.getString(R.string.invalid_message),
					Toast.LENGTH_LONG).show();
		}
    	/*else if(!checkMessage)
    	{
    		Toast.makeText(context,
					"Special characters allowed for message are @#$*_(),. ",
					Toast.LENGTH_LONG).show();
    	}*/
    	else {
    		validationsCheck = "Success";
    	}
    	
    	return validationsCheck;
    }
	
	/**
	 * Initiate Alert Dialog
	 */
	public void initAlertDialog(String message) {
		AlertDialog.Builder builder = new AlertDialog.Builder(context);
		builder.setMessage(message)
				.setCancelable(false)
				.setPositiveButton("OK",
						new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog,
									int id) {
								// do things
							}
						});
		AlertDialog alert = builder.create();
		alert.show();
	}

	/**
	 * Initiate Alert Dialog
	 */
	public void initAlertDialogForFinish(String message) {
		AlertDialog.Builder builder = new AlertDialog.Builder(context);
		builder.setMessage(message)
				.setCancelable(false)
				.setPositiveButton("OK",
						new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog,
												int id) {
								context.finish();
								// do things
							}
						});
		AlertDialog alert = builder.create();
		alert.show();
	}

}
