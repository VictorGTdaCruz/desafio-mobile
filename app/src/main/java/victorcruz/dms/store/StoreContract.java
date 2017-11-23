package victorcruz.dms.store;

import java.util.ArrayList;

import victorcruz.dms.BaseService;
import victorcruz.dms.BasePresenter;
import victorcruz.dms.BaseView;
import victorcruz.dms.data.Product;

/**
 * Created by victor.cruz on 22/11/2017.
 */

public interface StoreContract {

    interface View extends BaseView{

        void setItens(ArrayList<Product> productsStore);

        // mensagem de erro no json

    }

    interface Presenter extends BasePresenter{

        void getProducts();

        void formatJSON(String productsJSON);

    }

    interface Service extends BaseService {

        void downloadProductsString(Callback callback);

    }

    interface Callback{

        void onSuccess(String JSONString);

        void onError(Exception e);

    }

}
