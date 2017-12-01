package victorcruz.dms.util;

public class DateFormatter {

    public static String formatHour(String date){
        return date.substring(0,5);
    }

    public static String formatDate(String date){
        return date.substring(6,16);
    }
}
