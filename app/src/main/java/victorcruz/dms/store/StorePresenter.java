package victorcruz.dms.store;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import victorcruz.dms.get_post_data.GetImage;
import victorcruz.dms.data.Product;
/**
 * Created by victor.cruz on 22/11/2017.
 */

public class StorePresenter implements StoreContract.Presenter, StoreContract.Callback {

    //referencia para a camada view
    private final StoreContract.View mStoreView;

    //referencia para a camada service
    private StoreContract.Service mStoreService;

    public StorePresenter(StoreContract.View storeView){
        this.mStoreView = storeView;
        mStoreService = new StoreService(this);
    }

    @Override
    public void getProducts() {
        mStoreService.downloadProductsString(this);
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
/*
        try {

            JSONArray productsJSON = new JSONArray(productsString);

            for (int i = 0; i < productsJSON.length(); i++) {
                // separa o json em produtos
                JSONObject product = productsJSON.getJSONObject(i);

                // baixa a imagem de cada produto
                // metodo de baixar imagem do service
                getImage = new GetImage(i, productHandler);
                getImage.execute(product.getString("thumbnailHd"));

                // cria cada item_store
                productHandler.getProductsStore().add(new Product(
                        product.getString("title"),
                        product.getInt("price"),
                        product.getString("zipcode"),
                        product.getString("seller"),
                        null,
                        product.getString("date")));

            }
        } catch (JSONException e) {
            // metodo de erro no view
            e.printStackTrace();
        }

        mStoreView.setItens(mProductsList);*/
    }


}
