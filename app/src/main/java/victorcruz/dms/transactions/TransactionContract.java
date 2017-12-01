package victorcruz.dms.transactions;

import java.util.ArrayList;

import victorcruz.dms.data.Transaction;

public interface TransactionContract {

    interface View {

        void setItens(ArrayList<Transaction> mProductsList);

    }

    interface Presenter {

        void getTransactionsList();

    }

}
