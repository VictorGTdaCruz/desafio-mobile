package victorcruz.dms.data;

import android.graphics.Bitmap;

public class Product {
    private String mTitle;
    private int mPrice;
    private String mZipcode;
    private String mSeller;
    private Bitmap mImage;
    private String mDate;

    public Product(String mTitle, int mPrice, String mZipcode, String mSeller, Bitmap mImage, String mDate) {
        this.mTitle = mTitle;
        this.mPrice = mPrice;
        this.mZipcode = mZipcode;
        this.mSeller = mSeller;
        this.mImage = mImage;
        this.mDate = mDate;
    }

    public Product(Product product) {
        this.mTitle = product.getTitle();
        this.mPrice = product.getPrice();
        this.mZipcode = product.getZipcode();
        this.mSeller = product.getSeller();
        this.mImage = product.getImage();
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

    public Bitmap getImage() {
        return mImage;
    }

    public void setImage(Bitmap mImage) {
        this.mImage = mImage;
    }

    public String getDate() {
        return mDate;
    }

    public void setDate(String mDate) {
        this.mDate = mDate;
    }
}
