import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import org.json.JSONArray;
import org.json.JSONObject;

public class ExchangeRateProvider implements IExchangeRateProvider {
    private static ExchangeRateProvider instance;
    private CurrencyDictionary currencyDictionary;

    private ExchangeRateProvider() {
        loadExchangeRates();
    }

    public static ExchangeRateProvider getInstance() {
        if (instance == null) {
            instance = new ExchangeRateProvider();
        }
        return instance;
    }

    private void loadExchangeRates() {
        currencyDictionary = new CurrencyDictionary();
        currencyDictionary.addCurrency(new Currency("PLN", "Polish Zloty", 1.0));
        try {
            URL url = new URL("https://api.nbp.pl/api/exchangerates/tables/a/?format=json");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String inputLine;
            StringBuilder content = new StringBuilder();
            while ((inputLine = in.readLine()) != null) {
                content.append(inputLine);
            }
            in.close();
            JSONArray jsonArray = new JSONArray(content.toString());
            JSONArray rates = jsonArray.getJSONObject(0).getJSONArray("rates");
            for (int i = 0; i < rates.length(); i++) {
                JSONObject rate = rates.getJSONObject(i);
                String code = rate.getString("code");
                String name = rate.getString("currency");
                double midRate = rate.getDouble("mid");
                currencyDictionary.addCurrency(new Currency(code, name, midRate));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public CurrencyDictionary getExchangeRates() {
        return currencyDictionary;
    }
}