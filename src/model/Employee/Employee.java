package model.Employee;

import App.utils.Adress;
import model.Pays.PaymentMethod;

public abstract class Employee {
    private String name;
    private Adress adress;
    private int id;
    private PaymentMethod payment;
    private boolean syndicate;
    private Double taxSyndicate;
    private Double taxService=0d;
    //private int maior=0;

    Employee(String name, Adress adress, int id, Double taxSyndicate){
        this.name=name;
        this.adress= adress;
        this.id= id;
        this.syndicate= false;
        this.taxSyndicate= taxSyndicate;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setAdress(Adress adress) {
        this.adress = adress;
    }
    public void setId(int id) {
        this.id = id;
    }
    public void setSyndicate(boolean syndicate){
        this.syndicate = syndicate;
    }
    public String getName() {
        return this.name;
    }
    public Adress getAdress() {
        return this.adress;
    }
    public int getId() {
        return this.id;
    }
    public boolean getSyndicate(){
        return this.syndicate;
    }
    public void setPayment(PaymentMethod payment) {
        this.payment = payment;
    }
    public PaymentMethod getPayment() {
        return this.payment;
    }
    public abstract String typeEmployee();

    public void setTaxService(Double taxService) {
        this.taxService = taxService;
    }
    public Double getTaxService() {
        return taxService;
    }
    public void setTaxSyndicate(Double taxSyndicate) {
        this.taxSyndicate = taxSyndicate;
    }
    public Double getTaxSyndicate() {
        return taxSyndicate;
    }
    public String printInfo(){
        return "Name Employee: "+name+"\nAdress Employee: "+adress.getCity()+"\nID Employee: "+id+"\nType Employee: "+this.typeEmployee()+"\n";
    }
    public Double taxservDouble(Double paytotal){
        if(this.getTaxService()!=0){
            paytotal= paytotal-(paytotal*(this.getTaxService()/100));
        }
        this.setTaxService(0d);
        return paytotal;
    }
    public abstract Double payMent(int division);
}
