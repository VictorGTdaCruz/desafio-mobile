package victorcruz.dms.transactions;

import android.database.Cursor;

import java.util.ArrayList;

import victorcruz.dms.data.Transaction;
import victorcruz.dms.data.local.TransactionDatabase;

public class TransactionPresenter implements TransactionContract.Presenter{

    private TransactionContract.View mTransactionFragment;

    TransactionPresenter(TransactionContract.View mTransactionFragment){
        this.mTransactionFragment = mTransactionFragment;
    }

    @Override
    public void getTransactionsList() {
        TransactionDatabase mTransactionDatabase = TransactionDatabase.getInstance();
        ArrayList<Transaction> mTransactionList = new ArrayList<>(mTransactionDatabase.getTransactionDbSize());
        mTransactionList = cursorToArrayList(mTransactionDatabase.getTransactionList());
        mTransactionFragment.setItens(mTransactionList);
    }

    private ArrayList<Transaction> cursorToArrayList(Cursor cursor){
        cursor.moveToFirst();
        ArrayList<Transaction> mTransactionList = new ArrayList<>(cursor.getCount());
        for (int i = 0; i < cursor.getCount(); i++){
            mTransactionList.add(new Transaction(cursor.getInt(1), cursor.getString(2), cursor.getString(3), cursor.getString(4), cursor.getString(5), cursor.getString(6)));
            cursor.moveToNext();
        }
        cursor.close();
        return mTransactionList;
    }

}
