package victorcruz.dms.get_post_data;

import android.os.AsyncTask;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import victorcruz.dms.produto.Product;
import victorcruz.dms.produto.ProductHandler;

public class GetJSON extends AsyncTask<String, Void, String> {

    private GetImage getImage;

    private ProductHandler productHandler;


    public GetJSON(ProductHandler productHandler){
        this.productHandler = productHandler;
    }

    @Override
    protected String doInBackground(String... params) {

        String result = "";

        try {

            // InputStream vai receber os bytes e decodificar em char e salvar em result
            URL url = new URL("https://raw.githubusercontent.com/stone-pagamentos/desafio-mobile/master/products.json");
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            InputStream inputStream = httpURLConnection.getInputStream();
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
            int data = inputStreamReader.read();

            // registra char por char em result
            while (data != -1) {
                char current = (char) data;

                result += current;

                data = inputStreamReader.read();
            }

            inputStreamReader.close();
            inputStream.close();
            httpURLConnection.disconnect();

            return result;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    protected void onPostExecute(String result) {
        super.onPostExecute(result);

        System.out.print("Resultado: " + result);

        try {
            JSONArray jsonArray = new JSONArray(result);

            System.out.println(productHandler.getProductsStore().size());

            for (int i = 0; i < jsonArray.length(); i++) {
                // separa o json em partes por item_store
                JSONObject jsonObject = jsonArray.getJSONObject(i);

                // baixa a imagem de cada item_store
                getImage = new GetImage( i, productHandler);
                getImage.execute(jsonObject.getString("thumbnailHd"));

                // cria cada item_store
                productHandler.getProductsStore().add(new Product(
                        jsonObject.getString("title"),
                        jsonObject.getInt("price"),
                        jsonObject.getString("zipcode"),
                        jsonObject.getString("seller"),
                        null,
                        jsonObject.getString("date")));


                System.out.println("Product eh: " + i + " " + productHandler.getProductsStore().get(i).getTitle());
            }

            System.out.println(productHandler.getProductsStore().size());

            productHandler.refreshStoreView();


        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}