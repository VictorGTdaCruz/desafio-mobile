package victorcruz.dms.data.local;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import victorcruz.dms.data.Product;
import victorcruz.dms.util.MyApplication;

public class CartDatabase {

    private static CartDatabase instance;

    private SQLiteDatabase sqLiteDatabase;

    private CartDatabase(){
        sqLiteDatabase = MyApplication.getAppContext().openOrCreateDatabase("cart", Context.MODE_PRIVATE, null);//Application to get context
        sqLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS cart (id INTEGER PRIMARY KEY, title TEXT, value INTEGER(7), seller TEXT, thumbnail TEXT)");
    }

    public static CartDatabase getInstance() {
        if(instance == null)
            instance = new CartDatabase();
        return instance;
    }

    public Cursor getCartItens(){
        return sqLiteDatabase.rawQuery("SELECT * FROM cart", null);
    }

    public void addItemToCart(Product product){
        sqLiteDatabase.execSQL("INSERT INTO cart (title, value, seller, thumbnail) " +
                "VALUES ('" + product.getTitle() + "','" + product.getPrice() + "','" + product.getSeller() + "','" + product.getThumbnail() + "')");
    }

    public void resetCartTable(){
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS cart");
        sqLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS cart (id INTEGER PRIMARY KEY, title TEXT, value INTEGER(7), seller TEXT, thumbnail TEXT)");
    }
/*
    public void removeItemFromCart(Product product){//'" + product.getTitle() + "'
        sqLiteDatabase.execSQL("DELETE FROM cart WHERE id IN (SELECT id FROM cart WHERE title = '" + product.getTitle() + "' LIMIT 1)");
    }
*/

    /*public long getCartSize(){
        return DatabaseUtils.queryNumEntries(sqLiteDatabase, "cart");
    }*/

}
