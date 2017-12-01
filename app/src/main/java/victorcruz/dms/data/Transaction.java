package victorcruz.dms.data;

public class Transaction {

    private int mValue;
    private String mCardName;
    private String mCardNumber;
    private String mCvv;
    private String mCardDate;
    private String mTransactionDate;

    public Transaction(int mValue, String mCardName, String mCardNumber, String mCvv, String mCardDate, String mTransactionDate) {
        this.mValue = mValue;
        this.mCardName = mCardName;
        this.mCvv = mCvv;
        this.mCardNumber = mCardNumber;
        this.mCardDate = mCardDate;
        this.mTransactionDate = mTransactionDate;
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
