public interface ICurrency {
    String getCode();
    String getName();
    double getRate();
    void setCode(String code);
    void setName(String name);
    void setRate(double rate);

}
