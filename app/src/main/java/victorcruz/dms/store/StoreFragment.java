package victorcruz.dms.store;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;

import victorcruz.dms.R;
import victorcruz.dms.data.Product;

public class StoreFragment extends Fragment implements StoreContract.View, StoreContract.CallbackAddItemToCart {

    private StoreContract.Presenter mPresenter;

    private ProductStoreAdapter productStoreAdapter;

    private ListView storeListView;

    private ArrayList<Product> mProductsList;

    public StoreFragment() {
        // Required empty public constructor
    }

    public static StoreFragment newInstance() {
        return new StoreFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPresenter = new StorePresenter(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_store, container, false);
        storeListView = (ListView) root.findViewById(R.id.store_list_view);
        return root;
    }

    @Override
    public void onResume() {
        super.onResume();
        mPresenter.getProductsList();
    }

    @Override
    public void setItens(final ArrayList<Product> mProductsList) {
        getActivity().runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    productStoreAdapter = new ProductStoreAdapter(mProductsList, StoreFragment.this);
                    StoreFragment.this.setmProductsList(mProductsList);
                    storeListView.setAdapter(productStoreAdapter);
                }
            }
        );
    }

    public void setmProductsList(ArrayList<Product> mProductsList){
        this.mProductsList = mProductsList;
    }

    @Override
    public void addItemToCart(int position) {
        mPresenter.addItemToCart(mProductsList.get(position));
    }

}
