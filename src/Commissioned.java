import java.util.*;

public class Commissioned extends Employee {
    private Double monthly_salary;
    private Double commission;
    private Double comissionTotal;
    private ArrayList<String> dates= new ArrayList<>();
    Commissioned(String name, String adress, int id, Double salary, Double commission, Double taxSyndicate)
    {
        super(name, adress, id, taxSyndicate);
        this.monthly_salary= salary;
        this.commission= commission;
        this.comissionTotal=0d;
    }
    public void setMonthly_salary(Double monthly_salary) {
        this.monthly_salary = monthly_salary;
    }
    public void setCommission(Double commission) {
        this.commission = commission;
    }
    public Double getMonthly_salary() {
        return monthly_salary;
    }
    public Double getCommission() {
        return commission;
    }
    public List<String> getDates() {
        return dates;
    }
    public void setComissionTotal(Double valor){
        this.comissionTotal=valor;
    }
    public Double getComissionTotal() {
        return comissionTotal;
    }
    public void setComissionTotal(Double valor, String date) {
       comissionTotal+=valor*(commission/100);
       dates.add(date);
    }
    public String typeEmployee(){
        return "Commssioned";
    }
    public void removdate(String date){
        dates.remove(date);
    }
    public Double payMent(int division) {
        Double paytotal= (monthly_salary/division)-((monthly_salary/division)*(this.getTaxSyndicate()/100));
        if(this.getTaxService()!=0){
         paytotal= paytotal-(paytotal*(this.getTaxService()/100));
        }
        paytotal+=comissionTotal;
        this.comissionTotal=0d;
        this.setTaxService(0d);
        return paytotal;
        
    }
}
