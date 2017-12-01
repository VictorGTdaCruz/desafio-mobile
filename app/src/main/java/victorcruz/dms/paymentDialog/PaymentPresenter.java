package victorcruz.dms.paymentDialog;

import org.json.JSONException;
import org.json.JSONObject;

import victorcruz.dms.data.Transaction;
import victorcruz.dms.data.local.TransactionDatabase;
import victorcruz.dms.data.remote.PaymentService;

public class PaymentPresenter implements PaymentContract.Presenter{

    //referencia para a camada view
    private PaymentContract.View mPaymentView;

    //referencia para a camada service
    private PaymentContract.Service mPaymentService;

    private TransactionDatabase mTransactionDatabase;

    public PaymentPresenter(PaymentContract.View mPaymentView){
        this.mPaymentView = mPaymentView;
        mPaymentService = new PaymentService(this);
        mTransactionDatabase = TransactionDatabase.getInstance();
    }

    @Override
    public void sendPaymentInfoString(int value, String mCardNumber,String mCardName,String mCVV,String mExpDate) {

        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.accumulate("value", value);
            jsonObject.accumulate("card_number", mCardNumber);
            jsonObject.accumulate("cvv", mCardName);
            jsonObject.accumulate("card_holder_name", mCVV);
            jsonObject.accumulate("exp_date", mExpDate);
        } catch (JSONException e) {
            // metodo de erro
            e.printStackTrace();
        }

        // registrar data certa
        registerTransaction(new Transaction(value, mCardName, mCardNumber, mCVV, mExpDate));
        mPaymentService.postJson(jsonObject.toString());
    }

    public void registerTransaction(Transaction transaction) {
        mTransactionDatabase.addItemToTransactions(transaction);
    }


}