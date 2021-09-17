package model.Employee;

import App.utils.Adress;

public class Salaried extends Employee {
    private Double monthly_salary;
    public Salaried(String name, Adress adress, int id, Double salary, Double taxSyndicate){
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
        paytotal= taxservDouble(paytotal);
        return paytotal;
    }

}
