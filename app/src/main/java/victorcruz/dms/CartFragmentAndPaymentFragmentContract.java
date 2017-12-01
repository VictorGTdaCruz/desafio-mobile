package victorcruz.dms;

public interface CartFragmentAndPaymentFragmentContract {

    //Activity implements and Payment fragment calls
    interface ActivityPlsClearCartInterface {
        void activityPlsClearCart();
    }

    //Cart fragment implements and Activity calls
    interface CartFragmentPlsClearCartInterface {
        void cartFragmentPlsClearCart();
    }

    //Activity implements and Payment fragment calls
    interface ActivityPlsGetPriceInterface {
        int activityPlsGetPrice();
    }

    //Cart fragment implements and Activity calls
    interface CartFragmentPlsGetPriceInterface {
        int cartFragmentPlsGetPrice();
    }
}
