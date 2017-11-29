package victorcruz.dms.transaction;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;

import victorcruz.dms.R;

public class TransactionFragment extends Fragment implements TransactionContract.View{

    private TransactionContract.Presenter mPresenter;

    private ListView mTransactionListView;

    public TransactionFragment() {
        // Required empty public constructor
    }

    public static TransactionFragment newInstance() {
        TransactionFragment fragment = new TransactionFragment();
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPresenter = new TransactionPresenter(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_transaction, container, false);
        mTransactionListView = (ListView) root.findViewById(R.id.transaction_list_view);
        return root;
    }

    @Override
    public void onResume() {
        super.onResume();
        mPresenter.getTransactionsList();
    }

    @Override
    public void setItens(ArrayList<Transaction> mTransactionList) {
        TransactionAdapter mTransactionAdapter = new TransactionAdapter(mTransactionList, this);
        mTransactionListView.setAdapter(mTransactionAdapter);
    }
}
