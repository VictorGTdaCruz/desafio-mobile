package victorcruz.dms.paymentDialog;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import victorcruz.dms.R;
import victorcruz.dms.util.CurrencyFormatter;
import victorcruz.dms.CartFragmentAndPaymentFragmentContract.ActivityPlsClearCartInterface;
import victorcruz.dms.CartFragmentAndPaymentFragmentContract.ActivityPlsGetPriceInterface;

public class PaymentDialogFragment extends DialogFragment implements PaymentContract.View{

    private EditText cardNumberEditText, cardNameEditText, cardCVVEditText, cardExpDateEditText;
    private TextView mValue_text_view;

    private ActivityPlsGetPriceInterface sendPriceFromCartToPaymentInterface;
    private ActivityPlsClearCartInterface mActivityPlsClearCartInterfaceInterface;

    private PaymentPresenter mPaymentPresenter;

    public static PaymentDialogFragment newInstance() {
        return new PaymentDialogFragment();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        sendPriceFromCartToPaymentInterface = (ActivityPlsGetPriceInterface) context;
        mActivityPlsClearCartInterfaceInterface = (ActivityPlsClearCartInterface) context;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPaymentPresenter = new PaymentPresenter(this);
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the Builder class for convenient dialog construction
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.fragment_payment, null);

        // faz o valor no topo do fragmento
        mValue_text_view = (TextView) view.findViewById(R.id.value_text_view);
        final int value = sendPriceFromCartToPaymentInterface.ActivityPlsGetPrice();
        mValue_text_view.setText(CurrencyFormatter.formatPrice(value));

        cardNumberEditText = (EditText)  view.findViewById(R.id.cardNumberEditText);
        cardNameEditText = (EditText)  view.findViewById(R.id.cardNameEditText);
        cardCVVEditText = (EditText)  view.findViewById(R.id.cardCVVEditText);
        cardExpDateEditText = (EditText)  view.findViewById(R.id.cardExpDateEditText);

        builder.setMessage(R.string.finish_payment)
                .setView(view)
                .setPositiveButton(R.string.confirm_payment, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {

                        String mCardNumber = cardNumberEditText.getText().toString();
                        String mCardName = cardNameEditText.getText().toString();
                        String mCVV = cardCVVEditText.getText().toString();
                        String mExpDate = cardExpDateEditText.getText().toString();
                        mPaymentPresenter.sendPaymentInfoString(value, mCardNumber, mCardName, mCVV, mExpDate);
                        mActivityPlsClearCartInterfaceInterface.ActivityPlsClearCart();

                    }
                })
                .setNegativeButton(R.string.cancel_payment, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {

                    }
                });

        return builder.create();
    }
}
