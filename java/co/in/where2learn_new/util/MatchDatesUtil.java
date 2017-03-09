package co.in.where2learn_new.util;

import android.util.Log;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import co.in.where2learn_new.config.AppConfig;

/**
 * Created by gaurav_gupta on 12/20/2016.
 */

public class MatchDatesUtil {

    private String TAG = getClass().getName();

    public boolean compareDates(String date1,String date2){

        boolean isDateValid = false;
        SimpleDateFormat sdf = new SimpleDateFormat(AppConfig.PROFILE_DATE_FORMAT);

        if(date1.contains("-")){
            date1 = date1.replaceAll("-", "/");
        }
        if(date2.contains("-")){
            date2 = date2.replaceAll("-", "/");
        }

        try {
            Log.e(TAG, "Date1: " + date1 + "\nDate2: " + date2);
            Date d1 = sdf.parse(date1);
            Date d2 = sdf.parse(date2);

            if(d1.after(d2) && d2.compareTo(d1)!=0){
                Log.e(TAG, "Date1 is greater than Date2");
            }
            else{
                isDateValid = true;
                Log.e(TAG, "Date is valid");
            }

        } catch (ParseException e) {
            // TODO Auto-generated catch block
            Log.e(TAG, "Compare dates catch");
            e.printStackTrace();
        }
        return isDateValid;
    }

    public String setDateFill(){
        Calendar calendar = Calendar.getInstance();
        System.out.println("Current Time==> " + calendar.getTime());

        SimpleDateFormat sdf = new SimpleDateFormat(AppConfig.PROFILE_DATE_FORMAT);
        String mDate = sdf.format(calendar.getTime());
        System.out.println("Current Date==> " + mDate);

        return mDate;
    }

}
