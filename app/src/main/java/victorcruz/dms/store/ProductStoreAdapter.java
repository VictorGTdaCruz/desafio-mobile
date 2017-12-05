package victorcruz.dms.store;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import victorcruz.dms.R;
import victorcruz.dms.data.Product;
import victorcruz.dms.util.CurrencyFormatter;
import victorcruz.dms.util.SellerFormatter;

public class ProductStoreAdapter extends BaseAdapter {

    private final ArrayList<Product> mProductsList;

    private StoreContract.CallbackAddItemToCart callbackAddItemToCart;

    private Context mContext;

    ProductStoreAdapter(Context mContext, ArrayList<Product> mProductsList, StoreContract.CallbackAddItemToCart callbackAddItemToCart){
        this.mContext = mContext;
        this.mProductsList = mProductsList;
        this.callbackAddItemToCart = callbackAddItemToCart;
    }

    @Override
    public int getCount() {
        return mProductsList.size();
    }

    @Override
    public Object getItem(int position) {
        return mProductsList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        View view;

        if (convertView == null){
            view = LayoutInflater.from(mContext).inflate(R.layout.item_store, parent, false);
        }else{
            view = convertView;
        }

        Button button = (Button) view.findViewById(R.id.add_to_cart_button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                callbackAddItemToCart.addItemToCart(position);
            }
        });

        Product product = mProductsList.get(position);

        // seta os dados de cada produto em seu componente
        TextView title = (TextView) view.findViewById(R.id.title);
        TextView price = (TextView) view.findViewById(R.id.price);
        TextView seller = (TextView) view.findViewById(R.id.seller);
        ImageView image = (ImageView) view.findViewById(R.id.image);

        Picasso.with(mContext).load(product.getThumbnail()).into(image);

        title.setText(product.getTitle());

        price.setText(CurrencyFormatter.formatPrice(product.getPrice()));

        seller.setText(SellerFormatter.formatSeller(product.getSeller()));

        return view;
    }
}