package victorcruz.dms.data.remote;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import victorcruz.dms.store.StoreContract;

public class StoreService implements StoreContract.Service {

    private final StoreContract.Presenter mStorePresenter;

    public StoreService(StoreContract.Presenter mStorePresenter){
        this.mStorePresenter = mStorePresenter;
    }

    @Override
    public void getJSONString(final StoreContract.CallbackGetProductsString callbackGetProductsString) {

        Thread mGetJSONThread = new Thread(new Runnable() {

            @Override
            public void run() {

                try {

                    StringBuilder JSONString = new StringBuilder();

                    // InputStream vai receber os bytes e decodificar em char e salvar em result
                    URL url = new URL("https://raw.githubusercontent.com/stone-pagamentos/desafio-mobile/master/products.json");
                    HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                    InputStream inputStream = httpURLConnection.getInputStream();
                    InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
                    int data = inputStreamReader.read();

                    // registra char por char em result
                    while (data != -1) {
                        char current = (char) data;

                        JSONString.append(current);

                        data = inputStreamReader.read();
                    }

                    inputStreamReader.close();
                    inputStream.close();
                    httpURLConnection.disconnect();

                    callbackGetProductsString.onSuccess(JSONString.toString());

                } catch (Exception e){
                    callbackGetProductsString.onError(e);
                    e.printStackTrace();
                }

            }

        }, "DownloadJSONThread");
        mGetJSONThread.start();
    }

}
