package victorcruz.dms.transactions;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import victorcruz.dms.R;
import victorcruz.dms.data.Transaction;
import victorcruz.dms.util.CardFromatter;
import victorcruz.dms.util.CurrencyFormatter;
import victorcruz.dms.util.DateFormatter;
import victorcruz.dms.util.MyApplication;

public class TransactionAdapter extends BaseAdapter{

    private ArrayList<Transaction> mTransactionList;

    private TransactionContract.View mTransactionFragment;

    public TransactionAdapter(ArrayList mTransactionList, TransactionContract.View mTransactionFragment){
        this.mTransactionList = mTransactionList;
        this.mTransactionFragment = mTransactionFragment;
    }

    @Override
    public int getCount() {
        return mTransactionList.size();
    }

    @Override
    public Object getItem(int position) {
        return mTransactionList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View view;

        if (convertView == null){
            view = LayoutInflater.from(MyApplication.getAppContext()).inflate(R.layout.item_transaction, parent, false);
        }else{
            view = convertView;
        }

        Transaction transaction = mTransactionList.get(position);

        TextView value = (TextView) view.findViewById(R.id.transactionValueTextView);
        TextView name = (TextView) view.findViewById(R.id.transactionNameTextView);
        TextView cardNumber = (TextView) view.findViewById(R.id.transactionCardTextView);
        TextView date = (TextView) view.findViewById(R.id.transactionDateTextView);
        TextView hour = (TextView) view.findViewById(R.id.transactionHourTextView);

        value.setText(CurrencyFormatter.formatPrice(transaction.getValue()));

        name.setText(transaction.getCardName());

        cardNumber.setText(CardFromatter.formatCard(transaction.getCardNumber()));

        hour.setText(DateFormatter.formatHour(mTransactionList.get(position).getTransactionDate()));
        date.setText(DateFormatter.formatDate(mTransactionList.get(position).getTransactionDate()));

        return view;
    }
}
