package model.Employee;

import App.utils.Adress;
import App.utils.TimeCard;

public class Hourly extends Employee {
    private Double hours;
    private Double taxhours;
    private Double hoursDay;
    private Double pay;
    private TimeCard card= new TimeCard();
    public Hourly(String name, Adress adress, int id, Double hours, Double taxSyndicate){
        super(name, adress, id, taxSyndicate);
        this.hours=hours;
        this.taxhours=1.5;
        this.pay=0d;
    }
    public void setHours(Double hours){
        this.hours = hours;
    }
    public Double getHours() {
        return hours;
    }
    public Double getTaxhours() {
        return taxhours;
    }
    public void setEntryCard( ) {
        this.card.EntTimeCard();
    }
    public void setExitCard( ) {
        this.card.ExiTimeCard();
    }
    public void Cardinf(){
        card.getDateInfo();
        System.out.println(pay);
    }
    public TimeCard getCard() {
        return card;
    }
    public void setHoursDay() {
        this.hoursDay=card.InforHoras();
        if(hoursDay>8){
            Double ext= hoursDay-8d;
            ext= ext*(taxhours*hours);
            this.pay+=(8d*hours)+ext;
        }
        else{
            this.pay+=(hoursDay*hours);
        }
    }
    public String typeEmployee(){
        return "Hourly";
    }
    public Double payMent(int division) {

       Double paytotal= pay-(pay*(this.getTaxSyndicate()/100d));
       paytotal= taxservDouble(paytotal);
       this.hoursDay=0d;
       this.pay=0d;
       return paytotal;
    }
    public Double getPay() {
        return pay;
    }
    public void setPay(Double pay) {
        this.pay = pay;
    }
    public void resetTime(){
        this.card= new TimeCard();
    }
    public void resetEntry(){
        this.card.resetEntry();
    }
    public void resetExit(){
        this.card.resetExit();
    }
}
