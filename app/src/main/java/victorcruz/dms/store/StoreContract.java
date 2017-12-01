package victorcruz.dms.store;

import java.util.ArrayList;

import victorcruz.dms.data.Product;

public interface StoreContract {

    interface View {

        void setItens(ArrayList<Product> mProductsList);

        void warningJsonError();
    }

    interface Presenter {

        void getProductsList();

        void formatJSON(String mProductsJSON);

        void addItemToCart(Product product);

    }

    interface Service {

        void getJSONString(CallbackGetProductsString callbackGetProductsString);

    }

    interface CallbackGetProductsString {

        void onSuccess(String JSONString);

        void onError(Exception e);

    }

    interface CallbackAddItemToCart{

        void addItemToCart(int position);

    }

}
