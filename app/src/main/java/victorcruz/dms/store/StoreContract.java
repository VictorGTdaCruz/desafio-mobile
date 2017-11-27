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

        void setItens(ArrayList<Product> mProductsList);

        // mensagem de erro no json

    }

    interface Presenter extends BasePresenter{

        void getProductsList();

        void formatJSON(String productsJSON);

    }

    interface Service extends BaseService {

        void getProductsString(GetProductsStringCallback getProductsStringCallback);

    }

    interface GetProductsStringCallback {

        void onSuccess(String JSONString);

        void onError(Exception e);

    }

}
