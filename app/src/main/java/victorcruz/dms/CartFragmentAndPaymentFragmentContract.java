package victorcruz.dms;

public interface CartFragmentAndPaymentFragmentContract {

    //Activity implements and Payment fragment calls
    interface ActivityPlsClearCartInterface {
        void ActivityPlsClearCart();
    }

    //Cart fragment implements and Activity calls
    interface CartFragmentPlsClearCartInterface {
        void CartFragmentPlsClearCart();
    }

    //Activity implements and Payment fragment calls
    interface ActivityPlsGetPriceInterface {
        int ActivityPlsGetPrice();
    }

    //Cart fragment implements and Activity calls
    interface CartFragmentPlsGetPriceInterface {
        int CartFragmentPlsGetPrice();
    }
}
