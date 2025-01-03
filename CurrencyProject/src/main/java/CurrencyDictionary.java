import java.util.List;
import java.util.ArrayList;

public class CurrencyDictionary implements ICurrencyDictionary {
    private List<Currency> currencies = new ArrayList<>();

    @Override
    public Currency getCurrency(String code) {
        return currencies.stream()
                .filter(currency -> currency.getCode().equalsIgnoreCase(code))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Currency code not found"));
    }

    @Override
    public void addCurrency(Currency currency) {
        if (currencies.stream().noneMatch(c -> c.getCode().equalsIgnoreCase(currency.getCode()))) {
            currencies.add(currency);
        }
    }

    @Override
    public void deleteCurrency(Currency currency) {
        currencies.remove(currency); // Usuwanie waluty na podstawie obiektu
    }

    @Override
    public void modifyCurrency(Currency currency) {
        Currency existingCurrency = getCurrency(currency.getCode());
        if (existingCurrency != null){
            existingCurrency.setRate(currency.getRate());
            existingCurrency.setName(currency.getName());
        }

    }

    public List<Currency> getAllCurrencies() {
        return currencies;
    }
}
