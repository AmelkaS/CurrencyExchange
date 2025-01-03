import static java.lang.Math.round;

public class CurrencyConverter implements ICurrencyConverter {
    @Override
    public double convert(double amount, String fromCurrency, String toCurrency) {
        IExchangeRateProvider rateProvider = ExchangeRateProvider.getInstance();
        CurrencyDictionary currencyDictionary = rateProvider.getExchangeRates();
        double fromRate = currencyDictionary.getCurrency(fromCurrency).getRate();
        double toRate = currencyDictionary.getCurrency(toCurrency).getRate();
        double result = (amount * fromRate) / toRate;
        return round(result * 100.0)/100.0;
    }
}

