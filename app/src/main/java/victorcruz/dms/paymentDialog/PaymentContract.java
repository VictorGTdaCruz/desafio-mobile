package victorcruz.dms.paymentDialog;

public interface PaymentContract {

    interface View {

    }

    interface Presenter {

        void sendPaymentInfoString(int value, String mCardNumber,String mCardName,String mCVV,String mExpDate);

    }

    interface Service {

        void postJson(String mJSONString);

    }

}
