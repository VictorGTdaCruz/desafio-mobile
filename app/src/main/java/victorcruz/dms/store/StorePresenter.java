package victorcruz.dms.store;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import victorcruz.dms.data.Product;
/**
 * Created by victor.cruz on 22/11/2017.
 */

public class StorePresenter implements StoreContract.Presenter, StoreContract.GetProductsStringCallback {

    //referencia para a camada view
    private final StoreContract.View mStoreView;

    //referencia para a camada service
    private StoreContract.Service mStoreService;

    public StorePresenter(StoreContract.View storeView){
        this.mStoreView = storeView;
        mStoreService = new StoreService(this);
    }

    @Override
    public void getProductsList() {
        mStoreService.getProductsString(this);
    }

    @Override
    public void onSuccess(String JSONString) {
        formatJSON(JSONString);
    }

    @Override
    public void onError(Exception e) {

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
            // metodo de erro
            e.printStackTrace();
        }


    }
}
