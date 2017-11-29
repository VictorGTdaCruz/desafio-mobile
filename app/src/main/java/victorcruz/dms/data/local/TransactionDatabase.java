package victorcruz.dms.data.local;

import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;

import victorcruz.dms.transaction.Transaction;
import victorcruz.dms.util.MyApplication;

public class TransactionDatabase {

    private static TransactionDatabase instance;

    private SQLiteDatabase sqLiteDatabase;

    private int id = 1;

    private TransactionDatabase(){
        sqLiteDatabase = MyApplication.getAppContext().openOrCreateDatabase("transactions", MyApplication.getAppContext().MODE_PRIVATE, null);
        //sqLiteDatabase.execSQL("DROP TABLE IF EXISTS transactions");
        sqLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS transactions (id INTEGER PRIMARY KEY, value INTEGER, card_name TEXT, card_number TEXT, date TEXT)");
    }

    public static TransactionDatabase getInstance() {
        if(instance == null)
            instance = new TransactionDatabase();
        return instance;
    }

    public void addItemToTransactions(Transaction transaction){
        sqLiteDatabase.execSQL("INSERT INTO transactions (id, title, value, seller, thumbnail) " +
                "VALUES ('" + id + "','" + transaction.getValue() + "','" + transaction.getCardName() + "','" + transaction.getCardNumber() + "','" + transaction.getDate() + "')");
        id++;
    }

    public int getTransactionDbSize(){
        return (int) DatabaseUtils.queryNumEntries(sqLiteDatabase, "transactions");
    }

    public Transaction getTransaction(int id){
        Cursor c = sqLiteDatabase.rawQuery("SELECT * FROM transactions WHERE id = '" + id + "'" , null);
        c.moveToFirst();
        Transaction mTransaction = new Transaction(c.getInt(1), c.getString(2), c.getString(3), c.getString(4));
        return mTransaction;
    }
}
