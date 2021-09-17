package App.utils;

public class Adress {
    private String city;
    private String street;
    private int number;
    private String district;
    public Adress(String city, String street, int number, String district){
        this.city= city;
        this.street= street;
        this.number= number;
        this.district= district;
    }
    public String getCity() {
        return city;
    }
    public String getDistrict() {
        return district;
    }
    public int getNumber() {
        return number;
    }
    public String getStreet() {
        return street;
    }
    
}
