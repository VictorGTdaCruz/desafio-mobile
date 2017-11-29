package victorcruz.dms.util;

import java.text.DecimalFormat;

/**
 * Created by victor.cruz on 24/11/2017.
 */

public class CurrencyFormatter {

    public static String formatPrice(double price){
        DecimalFormat decimalFormat = new DecimalFormat("#,#####,00");
        String formattedPrice = decimalFormat.format((double) price);
        formattedPrice = "R$ " + formattedPrice;
        return formattedPrice;
    }

    public static int unformatPrice(String price){
        price = price.substring(3).replaceAll(",","");
        return Integer.parseInt(price);
    }

}
