package victorcruz.dms;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.TextView;

import victorcruz.dms.cart.CartFragment;
import victorcruz.dms.data.local.CartDatabase;
import victorcruz.dms.store.StoreFragment;
import victorcruz.dms.transaction.TransactionFragment;

public class MainActivity extends AppCompatActivity {

    private TextView ToolbarTitleTextView;

    private StoreFragment mStoreFragment;
    private CartFragment mCartFragment;
    private TransactionFragment mTransactionFragment;

    private FragmentManager mFragmentManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        mFragmentManager = getSupportFragmentManager();
        mStoreFragment = StoreFragment.newInstance();
        mCartFragment = CartFragment.newInstance();
        mTransactionFragment = TransactionFragment.newInstance();
        mFragmentManager.beginTransaction().add(R.id.fragment_container_layout, mStoreFragment).commit();
        ToolbarTitleTextView = (TextView) findViewById(R.id.toolbarTitleTextView);

    }

    /*public void showPaymentDialog(View view){

        if(productHandler.getCartTotalValue() == 0){
            Toast.makeText(this, "O seu carrinho está vazio!", Toast.LENGTH_SHORT).show();
        } else {
            //PaymentDialogFragment paymentDialogFragment = PaymentDialogFragment.newInstance(null);
            PaymentDialogFragment paymentDialogFragment = new PaymentDialogFragment();
            paymentDialogFragment.setArguments(productHandler, transactionsHandler);
            paymentDialogFragment.show(getSupportFragmentManager(), null);
        }

    }*/

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_store:
                    ToolbarTitleTextView.setText("Loja");
                    mFragmentManager.beginTransaction().replace(R.id.fragment_container_layout, mStoreFragment).commit();
                    return true;
                case R.id.navigation_cart:
                    ToolbarTitleTextView.setText("Carrinho");
                    mFragmentManager.beginTransaction().replace(R.id.fragment_container_layout, mCartFragment).commit();
                    return true;
                case R.id.navigation_transactions:
                    ToolbarTitleTextView.setText("Transações");
                    mFragmentManager.beginTransaction().replace(R.id.fragment_container_layout, mTransactionFragment).commit();
                    return true;
            }
            return false;
        }

    };

}
