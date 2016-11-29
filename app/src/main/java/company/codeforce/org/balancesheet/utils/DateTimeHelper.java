package company.codeforce.org.balancesheet.utils;

import android.util.Log;

import java.text.SimpleDateFormat;
import java.util.Date;

import static company.codeforce.org.balancesheet.BuildConfig.DEBUG;

/**
 * Created by ckumo on 10/25/2016.
 */

public class DateTimeHelper {

    private static final String DATE_TIME_FORMAT_MMMMddyyyy = "MMMM dd, yyyy";
    private static final String DATE_TIME_FORMAT_yyyyMMdd = "yyyy-MM-dd";
    private static final String TAG = DateTimeHelper.class.getSimpleName();


    public static String formatTimeStamp(String timeStamp, String inputFormat, String outputFormat) {
        SimpleDateFormat sdf = new SimpleDateFormat(inputFormat);
        try {
            Date date = sdf.parse(timeStamp);
            SimpleDateFormat sdf1 = new SimpleDateFormat(outputFormat);
            String updatedDate = sdf1.format(date);
            return updatedDate;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    public static String formatDateStringTo_MonthYearString(String dateString) {
        SimpleDateFormat sdf = new SimpleDateFormat(DATE_TIME_FORMAT_yyyyMMdd);
        try {
            Date date = sdf.parse(dateString);
            SimpleDateFormat dateFormat = new SimpleDateFormat(DATE_TIME_FORMAT_MMMMddyyyy);
            return dateFormat.format(date);
        } catch (Exception e) {
            if (DEBUG) Log.e(TAG, Log.getStackTraceString(e));
        }
        return ";";
    }
}
