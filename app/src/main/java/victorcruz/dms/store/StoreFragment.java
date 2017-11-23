package victorcruz.dms.store;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import victorcruz.dms.R;
import victorcruz.dms.data.Product;

/**
 * A simple {@link Fragment} subclass.
 */
public class StoreFragment extends Fragment implements StoreContract.View {

    private StoreContract.Presenter mPresenter;

    public StoreFragment() {
        // Required empty public constructor
        mPresenter = new StorePresenter(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_store, container, false);
    }

    @Override
    public void onResume() {
        super.onResume();
        mPresenter.getProducts();
    }

    @Override
    public void setItens(ArrayList<Product> productsStore) {
    }
}
