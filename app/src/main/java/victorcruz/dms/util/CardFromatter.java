package victorcruz.dms.util;

public class CardFromatter {

    public static String formatCard(String cardNumber){
        return "XXXX XXXX XXXX " + cardNumber.substring(12,16);
    }

}
