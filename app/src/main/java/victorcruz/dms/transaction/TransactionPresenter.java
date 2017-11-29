package victorcruz.dms.transaction;

import java.util.ArrayList;

import victorcruz.dms.data.local.TransactionDatabase;

public class TransactionPresenter implements TransactionContract.Presenter{

    private TransactionContract.View mTransactionFragment;

    private ArrayList<Transaction> mTransactionList;

    public TransactionPresenter(TransactionContract.View mTransactionFragment){
        this.mTransactionFragment = mTransactionFragment;
    }

    @Override
    public void getTransactionsList() {
        TransactionDatabase mTransactionDatabase = TransactionDatabase.getInstance();
        mTransactionList = new ArrayList<>(mTransactionDatabase.getTransactionDbSize());
        for (int i = 1; i <= mTransactionDatabase.getTransactionDbSize(); i++ ){
            mTransactionList.add(mTransactionDatabase.getTransaction(i));
        }
        mTransactionFragment.setItens(mTransactionList);
    }
}
