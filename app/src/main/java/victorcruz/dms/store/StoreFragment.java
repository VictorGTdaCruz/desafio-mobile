package victorcruz.dms.store;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;

import victorcruz.dms.R;
import victorcruz.dms.data.Product;

public class StoreFragment extends Fragment implements StoreContract.View, StoreContract.CallbackAddItemToCart {

    private StoreContract.Presenter mPresenter;

    private ProductStoreAdapter mProductStoreAdapter;

    private TextView mEmptyStoreTextView;
    private CoordinatorLayout mStoreCoordinatorLayout;

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

        View view = inflater.inflate(R.layout.fragment_store, container, false);
        View viewEmpty = inflater.inflate(R.layout.list_empty,container, false);

        ListView mStoreListView = (ListView) view.findViewById(R.id.store_list_view);
        getActivity().addContentView(viewEmpty, new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.MATCH_PARENT));
        mStoreListView.setEmptyView(viewEmpty);
        mProductStoreAdapter = new ProductStoreAdapter(getContext(), StoreFragment.this);

        //mEmptyStoreTextView = (TextView) view.findViewById(R.id.empty_store_text_view);
        //mEmptyStoreTextView.setText(R.string.store_loading);
        //mStoreListView.setEmptyView(mEmptyStoreTextView);

        mStoreListView.setAdapter(mProductStoreAdapter);

        mStoreCoordinatorLayout = (CoordinatorLayout) view.findViewById(R.id.store_coordinator_layout);
        return view;
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
                //mProductStoreAdapter = new ProductStoreAdapter(getContext(), StoreFragment.this);
                StoreFragment.this.setmProductsList(mProductsList);
                //mStoreListView.setAdapter(mProductStoreAdapter);
                StoreFragment.this.mProductStoreAdapter.setItems(mProductsList);
                mProductStoreAdapter.notifyDataSetChanged();
            }
        });
    }

    public void setmProductsList(ArrayList<Product> mProductsList){
        this.mProductsList = mProductsList;
    }

    @Override
    public void addItemToCart(int position) {
        mPresenter.addItemToCart(mProductsList.get(position));
        Snackbar.make(mStoreCoordinatorLayout, R.string.store_added_item_to_cart, Snackbar.LENGTH_SHORT).show();
    }

    @Override
    public void warningJsonError(){
        mEmptyStoreTextView.setText(R.string.store_error);
    }

}
