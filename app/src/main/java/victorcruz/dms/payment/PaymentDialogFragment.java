package victorcruz.dms.payment;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import victorcruz.dms.R;
import victorcruz.dms.util.CurrencyFormatter;
import victorcruz.dms.CartFragmentAndPaymentFragmentContract.ClearCartInterface;
import victorcruz.dms.CartFragmentAndPaymentFragmentContract.GetPriceInterface;

public class PaymentDialogFragment extends DialogFragment implements PaymentContract.View{

    private PaymentPresenter mPaymentPresenter;

    private Context mContext;

    private GetPriceInterface mGetPriceInterface;
    private ClearCartInterface mClearCartInterface;

    private EditText cardNumberEditText, cardNameEditText, cardCVVEditText, cardExpDateEditText;

    private int mValue;

    public static PaymentDialogFragment newInstance() {
        return new PaymentDialogFragment();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mGetPriceInterface = (GetPriceInterface) context;
        mClearCartInterface = (ClearCartInterface) context;
        mContext = context;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPaymentPresenter = new PaymentPresenter(this);
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the Builder class for convenient dialog construction
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.fragment_payment, null);

        TextView mValue_text_view = (TextView) view.findViewById(R.id.value_text_view);
        if (savedInstanceState != null){
            mValue = savedInstanceState.getInt("savedvalue");
        } else {
            mValue = mGetPriceInterface.getPrice();
        }
        String title = "Finalizar pagamento de " + CurrencyFormatter.formatPrice(mValue);
        mValue_text_view.setText(title);

        cardNumberEditText = (EditText)  view.findViewById(R.id.cardNumberEditText);
        cardNameEditText = (EditText)  view.findViewById(R.id.cardNameEditText);
        cardCVVEditText = (EditText)  view.findViewById(R.id.cardCVVEditText);
        cardExpDateEditText = (EditText)  view.findViewById(R.id.cardExpDateEditText);

        builder.setView(view)
                .setPositiveButton(R.string.payment_confirm, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {

                        String mCardNumber = cardNumberEditText.getText().toString();
                        String mCardName = cardNameEditText.getText().toString();
                        String mCVV = cardCVVEditText.getText().toString();
                        String mExpDate = cardExpDateEditText.getText().toString();

                        if (mCardNumber.length() == 16 && mCardName.length() > 0 && mCVV.length() == 3 && mExpDate.length() == 5 && mExpDate.substring(2,3).equals("/")){
                            mPaymentPresenter.sendPaymentInfoString(mValue, mCardNumber, mCardName, mCVV, mExpDate);
                            mClearCartInterface.clearCart();
                            Toast.makeText(mContext, "Compra realizada com sucesso!", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(mContext, "Dados rejeitados!", Toast.LENGTH_SHORT).show();
                        }

                    }
                })
                .setNegativeButton(R.string.payment_cancel, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {

                    }
                });

        return builder.create();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        outState.putInt("savedvalue", mValue);
        super.onSaveInstanceState(outState);
    }
}