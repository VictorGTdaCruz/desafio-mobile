package victorcruz.dms.transaction;


public class Transaction {

    private int value;
    private String cardName;
    private String cardNumber;
    private String date;

    public Transaction(int value, String cardName, String cardNumber, String date) {
        this.value = value;
        this.cardName = cardName;
        this.cardNumber = cardNumber;
        this.date = date;
    }

    public int getValue() {
        return value;
    }

    public String getCardName(){
        return cardName;
    }

    public String getCardNumber(){
        return cardNumber;
    }

    public String getDate() {
        return date;
    }
}
