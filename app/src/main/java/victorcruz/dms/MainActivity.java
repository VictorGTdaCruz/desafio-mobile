package victorcruz.dms;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import victorcruz.dms.cart.CartFragment;
import victorcruz.dms.payment.PaymentDialogFragment;
import victorcruz.dms.store.StoreFragment;
import victorcruz.dms.transactions.TransactionFragment;
import victorcruz.dms.CartFragmentAndPaymentFragmentContract.ClearCartInterface;
import victorcruz.dms.CartFragmentAndPaymentFragmentContract.GetPriceInterface;

public class MainActivity extends AppCompatActivity implements GetPriceInterface, ClearCartInterface {

    private TextView mToolbarTitleTextView;
    private Button mToolbarButton;

    private FragmentManager mFragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        mFragmentManager = getSupportFragmentManager();
        StoreFragment mStoreFragment = StoreFragment.newInstance();
        mFragmentManager.beginTransaction().add(R.id.fragment_container_layout, mStoreFragment, "store_tag").commit();
        //mFragmentManager.beginTransaction().add(R.id.fragment_container_layout, mCartFragment, R.string.tag_store_fragment).commit();

        mToolbarTitleTextView = (TextView) findViewById(R.id.toolbarTitleTextView);
        mToolbarButton = (Button) findViewById(R.id.toolbar_button);
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

    public void showPaymentDialog(View view){
        if (getPrice() > 0){
            PaymentDialogFragment.newInstance().show(mFragmentManager, null);
        } else {
            Toast.makeText(this, "Carrinho Vazio", Toast.LENGTH_SHORT).show();
        }
    }

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {

            FragmentTransaction transaction = mFragmentManager.beginTransaction();
            Fragment fragment = null;
            String tag = "";

            switch (item.getItemId()) {
                case R.id.navigation_store:
                    mToolbarTitleTextView.setText(R.string.toolbar_store);
                    mToolbarButton.setVisibility(View.INVISIBLE);

                    fragment = StoreFragment.newInstance();
                    tag = "store_tag";
                    break;

                case R.id.navigation_cart:
                    mToolbarTitleTextView.setText(R.string.toolbar_cart);
                    mToolbarButton.setVisibility(View.VISIBLE);

                    fragment = CartFragment.newInstance();
                    tag = "cart_tag";
                    break;

                case R.id.navigation_transactions:
                    mToolbarTitleTextView.setText(R.string.toolbar_transactions);
                    mToolbarButton.setVisibility(View.INVISIBLE);

                    fragment = TransactionFragment.newInstance();
                    tag = "transactions_tag";
                    break;

            }

            transaction.replace(R.id.fragment_container_layout, fragment, tag).commit();
            return true;
        }

    };
}