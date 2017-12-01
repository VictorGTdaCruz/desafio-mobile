package victorcruz.dms.data;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Transaction {

    private int mValue;
    private String mCardName;
    private String mCardNumber;
    private String mCvv;
    private String mCardDate;
    private String mTransactionDate;

    public Transaction(int mValue, String mCardName, String mCardNumber, String mCvv, String mCardDate) {
        this.mValue = mValue;
        this.mCardName = mCardName;
        this.mCardNumber = mCardNumber;
        this.mCardDate = mCardDate;

        SimpleDateFormat formatter = new SimpleDateFormat("kk:mm dd/MM/yyyy");
        String result = formatter.format(Calendar.getInstance().getTime());
        this.mTransactionDate = result;
    }

    public int getValue() {
        return mValue;
    }

    public String getCardName(){
        return mCardName;
    }

    public String getCardNumber(){
        return mCardNumber;
    }

    public String getCvv(){return mCvv;}

    public String getCardDate() {
        return mCardDate;
    }

    public String getTransactionDate() {
        return mTransactionDate;
    }
}
