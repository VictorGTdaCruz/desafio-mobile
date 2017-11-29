package victorcruz.dms.util;

/**
 * Created by victor.cruz on 24/11/2017.
 */

public class SellerFormatter {

    public static String formatSeller(String mSeller){
        return "de " + mSeller;
    }

    public static String unformatSeller(String mSeller){ return mSeller.substring(3);}

}
