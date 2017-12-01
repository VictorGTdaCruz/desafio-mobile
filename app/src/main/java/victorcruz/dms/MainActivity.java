package victorcruz.dms;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import victorcruz.dms.cart.CartFragment;
import victorcruz.dms.paymentDialog.PaymentDialogFragment;
import victorcruz.dms.store.StoreFragment;
import victorcruz.dms.transactions.TransactionFragment;
import victorcruz.dms.CartFragmentAndPaymentFragmentContract.ActivityPlsClearCartInterface;
import victorcruz.dms.CartFragmentAndPaymentFragmentContract.ActivityPlsGetPriceInterface;

public class MainActivity extends AppCompatActivity implements ActivityPlsGetPriceInterface, ActivityPlsClearCartInterface {

    private TextView mToolbarTitleTextView;
    private Button mToolbarButton;

    private StoreFragment mStoreFragment;
    private CartFragment mCartFragment;
    private TransactionFragment mTransactionFragment;
    PaymentDialogFragment paymentDialogFragment;


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
        paymentDialogFragment = PaymentDialogFragment.newInstance();
        mFragmentManager.beginTransaction().add(R.id.fragment_container_layout, mStoreFragment).commit();

        mToolbarTitleTextView = (TextView) findViewById(R.id.toolbarTitleTextView);
        mToolbarButton = (Button) findViewById(R.id.toolbar_button);
    }

    @Override
    public void ActivityPlsClearCart() {
        mCartFragment.CartFragmentPlsClearCart();
    }

    @Override
    public int ActivityPlsGetPrice() {
        return mCartFragment.CartFragmentPlsGetPrice();
    }

    public void showPaymentDialog(View view){
        if (mCartFragment.CartFragmentPlsGetPrice() > 0){
            paymentDialogFragment.show(mFragmentManager, null);
        } else Toast.makeText(this, "Carrinho Vazio", Toast.LENGTH_SHORT).show();
    }

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_store:
                    mToolbarTitleTextView.setText("Loja");
                    mToolbarButton.setVisibility(View.INVISIBLE);
                    mFragmentManager.beginTransaction().replace(R.id.fragment_container_layout, mStoreFragment).commit();
                    return true;
                case R.id.navigation_cart:
                    mToolbarTitleTextView.setText("Carrinho");
                    mToolbarButton.setVisibility(View.VISIBLE);
                    mFragmentManager.beginTransaction().replace(R.id.fragment_container_layout, mCartFragment).commit();
                    return true;
                case R.id.navigation_transactions:
                    mToolbarTitleTextView.setText("Transações");
                    mToolbarButton.setVisibility(View.INVISIBLE);
                    mFragmentManager.beginTransaction().replace(R.id.fragment_container_layout, mTransactionFragment).commit();
                    return true;
            }
            return false;
        }

    };

}
