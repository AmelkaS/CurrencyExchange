public interface ICurrencyDictionary {
    Currency getCurrency(String code);
    void addCurrency(Currency currency);
    void deleteCurrency(Currency currency);
    void modifyCurrency(Currency currency);
}
