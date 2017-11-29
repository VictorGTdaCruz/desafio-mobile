package victorcruz.dms.transaction;

import java.util.ArrayList;

public interface TransactionContract {

    interface View {

        void setItens(ArrayList<Transaction> mProductsList);

    }

    interface Presenter {

        void getTransactionsList();

    }

}
