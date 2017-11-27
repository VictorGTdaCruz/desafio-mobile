package victorcruz.dms.store;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;

import victorcruz.dms.R;
import victorcruz.dms.data.Product;
import victorcruz.dms.product.ProductStoreAdapter;

/**
 * A simple {@link Fragment} subclass.
 */
public class StoreFragment extends Fragment implements StoreContract.View {

    private StoreContract.Presenter mPresenter;

    private ProductStoreAdapter productStoreAdapter;

    private ListView storeListView;

    public StoreFragment() {
        // Required empty public constructor
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
                    productStoreAdapter = new ProductStoreAdapter(getActivity().getBaseContext(), mProductsList);
                    storeListView.setAdapter(productStoreAdapter);
                }
            }

        );
    }
}
