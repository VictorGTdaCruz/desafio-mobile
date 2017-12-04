package victorcruz.dms;

public interface CartFragmentAndPaymentFragmentContract {

    interface ClearCartInterface {
        void clearCart();
    }

    interface GetPriceInterface {
        int getPrice();
    }

}
