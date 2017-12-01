package victorcruz.dms.cart;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;

import victorcruz.dms.MainActivity;
import victorcruz.dms.R;
import victorcruz.dms.data.Product;

public class CartFragment extends Fragment implements CartContract.View, CartContract.CallbackDeleteItemFromCart, MainActivity.GetCartPrice {

    private CartPresenter mCartPresenter;

    private ListView mCartListView;

    private ProductCartAdapter mProductCartAdapter;

    private ArrayList<Product> mProductsList;

    public CartFragment() {
        // Required empty public constructor
    }

    public static CartFragment newInstance() {
        CartFragment fragment;
        fragment = new CartFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mCartPresenter = new CartPresenter(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_cart, container, false);
        mCartListView = (ListView) root.findViewById(R.id.cart_list_view);
        return root;
    }

    @Override
    public void onResume() {
        super.onResume();
        mCartPresenter.getProductsOnCart();
    }

    @Override
    public void onPause() {
        super.onPause();
        mCartPresenter.saveProductsOfCart(mProductsList);
    }

    @Override
    public void setItens(ArrayList<Product> mProductsList) {
        this.mProductsList = mProductsList;
        mProductCartAdapter = new ProductCartAdapter(mProductsList, this);
        mCartListView.setAdapter(mProductCartAdapter);
    }

    @Override
    public void deleteItemFromCart(int position) {
        mProductsList.remove(position);
        mProductCartAdapter.notifyDataSetChanged();
    }

    @Override
    public int getPrice() {
        return mCartPresenter.getCartPrice(mProductsList);
    }
}
