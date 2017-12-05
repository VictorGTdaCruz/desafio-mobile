package victorcruz.dms.data;

public class Product {
    private String mTitle;
    private int mPrice;
    private String mZipcode;
    private String mSeller;
    private String mThumbnail;
    private String mDate;

    public Product(String mTitle, int mPrice, String mZipcode, String mSeller, String mThumbnail, String mDate) {
        this.mTitle = mTitle;
        this.mPrice = mPrice;
        this.mZipcode = mZipcode;
        this.mSeller = mSeller;
        this.mThumbnail = mThumbnail;
        this.mDate = mDate;
    }

    public String getTitle() {
        return mTitle;
    }

    public int getPrice() {
        return mPrice;
    }

    public String getZipcode() {
        return mZipcode;
    }

    public String getSeller(){
        return mSeller;
    }

    public String getThumbnail() {
        return mThumbnail;
    }

    public String getDate() {
        return mDate;
    }
}