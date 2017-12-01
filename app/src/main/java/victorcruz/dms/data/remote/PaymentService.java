package victorcruz.dms.data.remote;

import java.io.DataOutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import victorcruz.dms.paymentDialog.PaymentContract;

public class PaymentService implements PaymentContract.Service{

    private final PaymentContract.Presenter mPaymentPresenter;

    public PaymentService(PaymentContract.Presenter mPaymentPresenter){
        this.mPaymentPresenter = mPaymentPresenter;
    }

    @Override
    public void postJson (final String mJSONString) {

        Thread mPostJSONThread = new Thread(new Runnable() {

            @Override
            public void run() {

                try {

                    // conecta na url recebida e configura o request
                    URL url = new URL("http://private-2ac02-desafiomobile2.apiary-mock.com/transactions");
                    HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                    httpURLConnection.setRequestMethod("POST");
                    httpURLConnection.setRequestProperty("Content-Type", "application/json");
                    httpURLConnection.setDoOutput(true);
                    httpURLConnection.setConnectTimeout(5000);
                    httpURLConnection.setReadTimeout(5000);
                    httpURLConnection.connect();


                    DataOutputStream dataOutputStream = new DataOutputStream(httpURLConnection.getOutputStream());
                    dataOutputStream.writeBytes(mJSONString);
                    dataOutputStream.flush();
                    dataOutputStream.close();

                    httpURLConnection.disconnect();


                } catch (Exception e) {
                    e.printStackTrace();
                }

            }

        }, "mPostJSONThread");
        mPostJSONThread.start();

    }




}
