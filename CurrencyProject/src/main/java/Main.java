
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        IUserInputHandler inputHandler = new UserInputHandler();
        ICurrencyConverter converter = new CurrencyConverter();
        IExchangeRateProvider rateProvider = ExchangeRateProvider.getInstance();
        CurrencyDictionary currencyDictionary = rateProvider.getExchangeRates();

        // Wyświetlanie wszystkich dostępnych walut
        System.out.println("Available currencies:");
        for (Currency currency : currencyDictionary.getAllCurrencies()) {
            System.out.printf("%s (%s): %f%n", currency.getCode(), currency.getName(), currency.getRate());
        }

        try {
            double amount = inputHandler.getAmount();
            String fromCurrency;
            Currency fromCurrencyObj;
            do {
                fromCurrency = inputHandler.getCurrencyCode("Enter source currency code (e.g., USD, EUR): ");
                fromCurrencyObj = currencyDictionary.getCurrency(fromCurrency);
                if (fromCurrencyObj == null) {
                    System.out.println("Invalid currency code. Please try again.");
                }
            } while (fromCurrencyObj == null);
            String toCurrency;
            Currency toCurrencyObj;
            do {
                toCurrency = inputHandler.getCurrencyCode("Enter target currency code (e.g., USD, EUR): ");
                toCurrencyObj = currencyDictionary.getCurrency(toCurrency);
                if (toCurrencyObj == null) {
                    System.out.println("Invalid currency code. Please try again.");
                }
            } while (toCurrencyObj == null);

            double result = converter.convert(amount, fromCurrency, toCurrency);
            System.out.printf(amount + " " + fromCurrency + " is equal to " + result + " " + toCurrency);
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}


