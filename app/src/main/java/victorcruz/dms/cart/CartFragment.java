package victorcruz.dms.cart;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

import victorcruz.dms.CartFragmentAndPaymentFragmentContract;
import victorcruz.dms.FloatingActionButtonInterface;
import victorcruz.dms.R;
import victorcruz.dms.data.Product;

public class CartFragment extends Fragment implements CartContract.View, CartContract.CallbackDeleteItemFromCart,
        CartFragmentAndPaymentFragmentContract.GetPriceInterface, CartFragmentAndPaymentFragmentContract.ClearCartInterface {

    private CartPresenter mCartPresenter;

    private FloatingActionButtonInterface mFloatingActionButtonInterface;

    private ListView mCartListView;
    private CoordinatorLayout mCoordinatorLayout;

    private ProductCartAdapter mProductCartAdapter;

    private ArrayList<Product> mProductsList;

    public CartFragment() {
        // Required empty public constructor
    }

    public static CartFragment newInstance() {
        return new CartFragment();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mFloatingActionButtonInterface = (FloatingActionButtonInterface) context;
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
        View view = inflater.inflate(R.layout.fragment_cart, container, false);
        mCartListView = (ListView) view.findViewById(R.id.cart_list_view);
        TextView mEmptyCartTextView = (TextView) view.findViewById(R.id.empty_cart_text_view);
        mCartListView.setEmptyView(mEmptyCartTextView);
        mCoordinatorLayout = (CoordinatorLayout) view.findViewById(R.id.cart_coordinator_layout);
        FloatingActionButton floatingActionButton = (FloatingActionButton) view.findViewById(R.id.payment_dialog_floating_button);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showPaymentDialog();
            }
        });
        return view;
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
    public void setItems(ArrayList<Product> mProductsList) {
        this.mProductsList = mProductsList;
        mProductCartAdapter = new ProductCartAdapter(getContext(), mProductsList, this);
        mCartListView.setAdapter(mProductCartAdapter);
    }

    @Override
    public void deleteItemFromCart(int position) {
        mProductsList.remove(position);
        mProductCartAdapter.notifyDataSetChanged();
        Snackbar.make(mCoordinatorLayout, R.string.cart_removed_item_from_cart, Snackbar.LENGTH_SHORT).show();
    }

    @Override
    public int getPrice() {
        return mCartPresenter.getCartPrice(mProductsList);
    }

    @Override
    public void clearCart() {
        int mSizeBeforePayment = mProductsList.size();
        for (int i = 0; i < mSizeBeforePayment; i++) {
            mProductsList.remove(0);
        }
        mProductCartAdapter.notifyDataSetChanged();
    }

    public void showPaymentDialog(){
        if (getPrice() > 0){
            mFloatingActionButtonInterface.showPaymentDialog();
        } else {
            Snackbar.make(mCoordinatorLayout, R.string.cart_empty, Snackbar.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {

        super.onSaveInstanceState(outState);
    }
}
