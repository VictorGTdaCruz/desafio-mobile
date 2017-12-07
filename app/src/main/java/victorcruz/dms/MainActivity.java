package victorcruz.dms;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import victorcruz.dms.cart.CartFragment;
import victorcruz.dms.payment.PaymentDialogFragment;
import victorcruz.dms.store.StoreFragment;
import victorcruz.dms.transactions.TransactionFragment;
import victorcruz.dms.CartFragmentAndPaymentFragmentContract.ClearCartInterface;
import victorcruz.dms.CartFragmentAndPaymentFragmentContract.GetPriceInterface;

public class MainActivity extends AppCompatActivity implements GetPriceInterface, ClearCartInterface, FloatingActionButtonInterface {

    private FragmentManager mFragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle(R.string.actionbar_store);
        }
        mFragmentManager = getSupportFragmentManager();
        StoreFragment mStoreFragment = StoreFragment.newInstance();
        mFragmentManager.beginTransaction().add(R.id.fragment_container_layout, mStoreFragment, "store_tag").commit();
    }

    @Override
    public void clearCart() {
        CartFragment fragment = (CartFragment) mFragmentManager.findFragmentByTag("cart_tag");
        fragment.clearCart();
    }

    @Override
    public int getPrice() {
        CartFragment fragment = (CartFragment) mFragmentManager.findFragmentByTag("cart_tag");
        return fragment.getPrice();
    }

    @Override
    public void showPaymentDialog() {
        PaymentDialogFragment.newInstance().show(mFragmentManager, null);
    }

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {

            FragmentTransaction transaction = mFragmentManager.beginTransaction();
            Fragment fragment = null;
            String tag = "";
            ActionBar actionBar = getSupportActionBar();

            switch (item.getItemId()) {
                case R.id.navigation_store:

                    if (actionBar != null) {
                        actionBar.setTitle(R.string.actionbar_store);
                    }
                    fragment = StoreFragment.newInstance();
                    tag = "store_tag";
                    break;

                case R.id.navigation_cart:

                    if (actionBar != null) {
                        actionBar.setTitle(R.string.actionbar_cart);
                    }
                    fragment = CartFragment.newInstance();
                    tag = "cart_tag";
                    break;

                case R.id.navigation_transactions:

                    if (actionBar != null) {
                        actionBar.setTitle(R.string.actionbar_transactions);
                    }
                    fragment = TransactionFragment.newInstance();
                    tag = "transactions_tag";
                    break;

            }
            transaction.replace(R.id.fragment_container_layout, fragment, tag).commit();
            return true;
        }

    };
}