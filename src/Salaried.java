public class Salaried extends Employee {
    private Double monthly_salary;
    Salaried(String name, String adress, int id, Double salary, Double taxSyndicate){
        super(name, adress, id, taxSyndicate);
        monthly_salary=salary;
    }
    public void setMonthly_salary(Double monthly_salary) {
        this.monthly_salary = monthly_salary;
    }
    public Double getMonthly_salary() {
        return monthly_salary;
    }
    public String typeEmployee(){
        return "Salaried";
    }
    public Double payMent(int division) {
        Double paytotal= (monthly_salary/division)-((monthly_salary/division)*(this.getTaxSyndicate()/100));
       if(this.getTaxService()!=0){
        paytotal= paytotal-(paytotal*(this.getTaxService()/100));
       }
       this.setTaxService(0d);
       return paytotal;
    }

}
