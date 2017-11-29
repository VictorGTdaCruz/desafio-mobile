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
        mCartFragment.setItens(cursorToArrayList(CartDatabase.getInstance().getCartItens()));
    }

    /*
    CartDatabase mCartDatabase = CartDatabase.getInstance();
        mCartDatabase.resetCartTable();
        for (int i = 0; i < mProductsList.size(); i++) {
        mCartDatabase.addItemToCart(mProductsList.get(i));
    }*/

    @Override
    public void saveProductsOfCart(ArrayList<Product> mNewList) {

        CartDatabase mCartDatabase = CartDatabase.getInstance();

        ArrayList<Product> mCurrentList = cursorToArrayList(CartDatabase.getInstance().getCartItens());

        // confere se tem itens iguais e os exclui das duas listas
        for (int i = 0; i < mNewList.size(); i++) {
            if (mCurrentList.contains(mNewList.get(i))) {
                mCurrentList.remove(mCurrentList.indexOf(mNewList.get(i)));
                mNewList.remove(i);
            }
        }

        // exclui do banco de dados os itens que nao estao na lista nova
        for (int i = 0; i < mNewList.size(); i++){
            mCartDatabase.addItemToCart(mNewList.get(i));
        }

        // inclui no banco de dados os itens que estao na lista nova
        for (int i = 0; i < mCurrentList.size(); i++){
            mCartDatabase.removeItemFromCart(mCurrentList.get(i));
        }
    }

    private ArrayList<Product> cursorToArrayList(Cursor cursor){
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
        return mProductList;
    }
}
