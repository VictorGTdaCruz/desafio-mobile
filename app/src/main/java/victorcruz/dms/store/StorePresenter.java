package victorcruz.dms.store;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import victorcruz.dms.data.Product;
import victorcruz.dms.data.local.CartDatabase;
import victorcruz.dms.data.remote.StoreService;

public class StorePresenter implements StoreContract.Presenter, StoreContract.CallbackGetProductsString {

    //referencia para a camada view
    private StoreContract.View mStoreView;

    //referencia para a camada service
    private StoreContract.Service mStoreService;

    public StorePresenter(StoreContract.View storeView){
        this.mStoreView = storeView;
        mStoreService = new StoreService(this);
    }

    @Override
    public void getProductsList() {
        mStoreService.getJSONString(this);
    }

    @Override
    public void onSuccess(String JSONString) {
        formatJSON(JSONString);
    }

    @Override
    public void onError(Exception e) {
        mStoreView.warningJsonError();
    }

    @Override
    public void formatJSON(String productsString) {

        try {

            JSONArray productsJSONArray = new JSONArray(productsString);

            ArrayList<Product> mProductList = new ArrayList<>(productsJSONArray.length());

            for (int i = 0; i < productsJSONArray.length(); i++) {

                JSONObject JSONproduct = productsJSONArray.getJSONObject(i);

                // seta os campos de cada produto
                mProductList.add(new Product(
                        JSONproduct.getString("title"),
                        JSONproduct.getInt("price"),
                        JSONproduct.getString("zipcode"),
                        JSONproduct.getString("seller"),
                        JSONproduct.getString("thumbnailHd"),
                        JSONproduct.getString("date")));
            }

            mStoreView.setItens(mProductList);

        } catch (JSONException e) {
            mStoreView.warningJsonError();
            e.printStackTrace();
        }
    }

    @Override
    public void addItemToCart(Product product) {
        CartDatabase mCartDatabase = CartDatabase.getInstance();
        mCartDatabase.addItemToCart(product);
    }
}
