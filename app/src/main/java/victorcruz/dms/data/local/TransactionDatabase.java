package victorcruz.dms.data.local;

import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;

import victorcruz.dms.data.Transaction;
import victorcruz.dms.util.MyApplication;

public class TransactionDatabase {

    private static TransactionDatabase instance;

    private SQLiteDatabase sqLiteDatabase;

    private TransactionDatabase(){
        sqLiteDatabase = MyApplication.getAppContext().openOrCreateDatabase("transactions", MyApplication.getAppContext().MODE_PRIVATE, null);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS transactions");
        sqLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS transactions (id INTEGER PRIMARY KEY, value INTEGER, card_name TEXT, card_number TEXT, cvv TEXT, card_exp_date TEXT, transaction_date TEXT)");
    }

    public static TransactionDatabase getInstance() {
        if(instance == null)
            instance = new TransactionDatabase();
        return instance;
    }

    public void addItemToTransactions(Transaction transaction){
        sqLiteDatabase.execSQL("INSERT INTO transactions (value, card_name, card_number, cvv, card_exp_date, transaction_date) " +
                "VALUES ('" + transaction.getValue() + "','" + transaction.getCardName() + "','" + transaction.getCardNumber() + "','"
                + transaction.getCvv() + "','" + transaction.getCardDate() + "','" + transaction.getTransactionDate() + "')");
    }

    public int getTransactionDbSize(){
        return (int) DatabaseUtils.queryNumEntries(sqLiteDatabase, "transactions");
    }

    public Cursor getTransactionList(){
        Cursor c = sqLiteDatabase.rawQuery("SELECT * FROM transactions", null);
        return c;
    }
}
