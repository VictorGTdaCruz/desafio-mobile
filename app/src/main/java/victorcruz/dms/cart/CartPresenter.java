package victorcruz.dms.cart;

import android.database.Cursor;

import java.util.ArrayList;

import victorcruz.dms.data.Product;
import victorcruz.dms.data.local.CartDatabase;

public class CartPresenter implements CartContract.Presenter{

    //referencia para a camada view
    private CartContract.View mCartFragment;

    public CartPresenter(CartContract.View mCartFragment){
        this.mCartFragment = mCartFragment;
    }

    @Override
    public void getProductsOnCart() {
        CartDatabase mCartDatabase = CartDatabase.getInstance();

        Cursor cursor = mCartDatabase.getCartItens();
        cursor.moveToFirst();
        ArrayList<Product> mProductList = new ArrayList<>(cursor.getCount());
        for (int i = 0; i < cursor.getCount(); i++){
            mProductList.add(new Product(cursor.getString(1),
                    cursor.getInt(2),
                    null,
                    cursor.getString(3),
                    cursor.getString(4),
                    null));
            cursor.moveToNext();
        }
        cursor.close();

        mCartFragment.setItens(mProductList);
    }

    @Override
    public void saveProductsOfCart(ArrayList<Product> mProductsList) {
        CartDatabase mCartDatabase = CartDatabase.getInstance();
        mCartDatabase.resetCartTable();
        for (int i = 0; i < mProductsList.size(); i++) {
            mCartDatabase.addItemToCart(mProductsList.get(i));
        }
    }

}
