package victorcruz.dms.transactions;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

import victorcruz.dms.R;
import victorcruz.dms.data.Transaction;

public class TransactionFragment extends Fragment implements TransactionContract.View{

    private TransactionContract.Presenter mPresenter;

    private ListView mTransactionListView;
    private TextView mEmptyTransactionTextView;

    public TransactionFragment() {
        // Required empty public constructor
    }

    public static TransactionFragment newInstance() {
        return new TransactionFragment();
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
        mEmptyTransactionTextView = (TextView) root.findViewById(R.id.empty_transaction_text_view);
        return root;
    }

    @Override
    public void onResume() {
        super.onResume();
        mPresenter.getTransactionsList();
    }

    @Override
    public void setItens(ArrayList<Transaction> mTransactionList) {
        if (mTransactionList.size() > 0){
            mEmptyTransactionTextView.setVisibility(View.INVISIBLE);
            TransactionAdapter mTransactionAdapter = new TransactionAdapter(mTransactionList);
            mTransactionListView.setAdapter(mTransactionAdapter);
        }
    }
}
