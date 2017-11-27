package victorcruz.dms.store;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by victor.cruz on 22/11/2017.
 */

public class StoreService implements StoreContract.Service {

    private final StoreContract.Presenter mStorePresenter;

    public StoreService(StoreContract.Presenter mStorePresenter){
        this.mStorePresenter = mStorePresenter;
    }

    @Override
    public void getProductsString(final StoreContract.GetProductsStringCallback getProductsStringCallback) {

        Thread mGetJSONThread = new Thread(new Runnable() {

            @Override
            public void run() {

                try {

                    String JSONString = "";

                    // InputStream vai receber os bytes e decodificar em char e salvar em result
                    URL url = new URL("https://raw.githubusercontent.com/stone-pagamentos/desafio-mobile/master/products.json");
                    HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                    InputStream inputStream = httpURLConnection.getInputStream();
                    InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
                    int data = inputStreamReader.read();

                    // registra char por char em result
                    while (data != -1) {
                        char current = (char) data;

                        JSONString += current;

                        data = inputStreamReader.read();
                    }

                    inputStreamReader.close();
                    inputStream.close();
                    httpURLConnection.disconnect();

                    getProductsStringCallback.onSuccess(JSONString);

                } catch (MalformedURLException e) {
                    // metodo de erro
                    e.printStackTrace();
                } catch (IOException e) {
                    //metodo de erro
                    e.printStackTrace();
                } catch (Exception e){
                    e.printStackTrace();
                    getProductsStringCallback.onError(e);
                }
            }

        }, "DownloadJSONThread");
        mGetJSONThread.start();
    }

}
