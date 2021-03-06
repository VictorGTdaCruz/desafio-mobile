package victorcruz.dms.cart;

import java.util.ArrayList;

import victorcruz.dms.data.Product;

public interface CartContract {

    interface View {

        void setItems(ArrayList<Product> mProductsList);

    }

    interface Presenter {

        void getProductsOnCart();

        void saveProductsOfCart(ArrayList<Product> mProductsList);

        int getCartPrice(ArrayList<Product> mProductsList);

        }

    interface CallbackDeleteItemFromCart{

        void deleteItemFromCart(int position);

    }
}
