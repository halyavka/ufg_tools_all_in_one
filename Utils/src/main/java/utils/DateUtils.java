package utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

public class DateUtils {
    public static final String yyyyMMddHHmmss = "yyyy-MM-dd HH:mm:ss";
    public static final String yyyyMMddTHHmmssSSSZ = "yyyy-MM-dd'T'HH:mm:ss.SSSZ";

    public static Date parseDate(String dateFormat, String date) {
        if (date == null) return null;
        DateFormat dft = new SimpleDateFormat(dateFormat, Locale.US);
        try {
            return dft.parse(date);
        } catch (ParseException ex) {/* */}
        return null;
    }

    public static Date parseSQLDate(String date) {
        return parseDate(yyyyMMddHHmmss, date);
    }

    public static Date parseXMLDate(String date) {
        return parseDate(yyyyMMddTHHmmssSSSZ, date);
    }

    public String getCurrentTime(String timeFormat, TimeZone timeZone) {
      /* Specifying the format */
        DateFormat dateFormat = new SimpleDateFormat(timeFormat);
      /* Setting the Timezone */
        Calendar cal = Calendar.getInstance(timeZone);
        dateFormat.setTimeZone(cal.getTimeZone());
      /* Picking the time value in the required Format */
        String currentTime = dateFormat.format(cal.getTime());
        return currentTime;
    }

}
