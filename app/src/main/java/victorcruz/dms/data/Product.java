package victorcruz.dms.data;

public class Product {
    private String mTitle;
    private int mPrice;
    private String mZipcode;
    private String mSeller;
    private String mImageURL;
    private String mDate;

    public Product(String mTitle, int mPrice, String mZipcode, String mSeller, String mImageURL, String mDate) {
        this.mTitle = mTitle;
        this.mPrice = mPrice;
        this.mZipcode = mZipcode;
        this.mSeller = mSeller;
        this. mImageURL = mImageURL;
        this.mDate = mDate;
    }

    public Product(Product product) {
        this.mTitle = product.getTitle();
        this.mPrice = product.getPrice();
        this.mZipcode = product.getZipcode();
        this.mSeller = product.getSeller();
        this. mImageURL = mImageURL;
        this.mDate = product.getDate();
    }

    public String getTitle() {
        return mTitle;
    }

    public int getPrice() {
        return mPrice;
    }

    public String getZipcode(){
        return mZipcode;
    }

    public String getSeller(){
        return mSeller;
    }

    public String getImageURL() {
        return mImageURL;
    }

    public void setImageURL(String mImageURL) {
        this.mImageURL = mImageURL;
    }

    public String getDate() {
        return mDate;
    }

    public void setDate(String mDate) {
        this.mDate = mDate;
    }
}
