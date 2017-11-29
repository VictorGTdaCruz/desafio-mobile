package victorcruz.dms.util;

/**
 * Created by victor.cruz on 28/11/2017.
 */

public class DateFormatter {

    public static String formatHour(String date){
        return date.substring(0,5);
    }

    public static String formatDate(String date){
        return date.substring(6,16);
    }


}
