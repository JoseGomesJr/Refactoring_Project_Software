package model.Undo;

import java.util.*;

import App.*;
import model.Employee.Commissioned;
import model.Employee.Employee;
import model.Employee.Hourly;
import model.Pays.PayFunction;
import model.Syndicate.Syndicate;

public class SalveDayPay implements Undo {
    History hist;
    public SalveDayPay(List<Employee> employeelist){
        hist= new History();
        
        hist.comission= new ArrayList<>();
        hist.payhour= new ArrayList<>();
        hist.taxservice= new ArrayList<>();
        for(Employee employee: employeelist){
            switch (employee.typeEmployee()) {
                case "Hourly":
                    Double aux_pay= ((Hourly) employee).getPay();
                    hist.payhour.add(aux_pay);
                    hist.taxservice.add(employee.getTaxService());
                    break;
                case "Commssioned":
                    Double aux_commission= ((Commissioned) employee).getComissionTotal();
                    hist.comission.add(aux_commission);
                    hist.taxservice.add(employee.getTaxService());
                    break;
                case "Salaried":
                    hist.taxservice.add(employee.getTaxService());
                    break;
                default:
                    break;
            }
        }
        
    }
    public void Undo_Redo(List<Employee> employeelist){
        int posipay=0;
        int positax=0;
        int posicomission=0;
        for(Employee employee: employeelist){
            switch (employee.typeEmployee()){
                case "Hourly":
                    Double aux_pay=hist.payhour.get(posipay);
                    ((Hourly) employee).setPay(aux_pay);
                    employee.setTaxService(hist.taxservice.get(positax));
                    posipay++;
                    positax++;
                    break;
                case "Commssioned":
                    Double aux_commission=hist.comission.get(posicomission); 
                    ((Commissioned) employee).setComissionTotal(aux_commission);
                    employee.setTaxService(hist.taxservice.get(positax));
                    posicomission++;
                    positax++;
                    break;
                case "Salaried":
                    employee.setTaxService(hist.taxservice.get(positax));
                    positax++;
                    break;
                default:
                    break;
            }
        }
        
    }
    @Override
    public Undo execute(Undo reUndo, List<Employee> employees, List<Syndicate> syndicates){
        this.Undo_Redo(employees);
        return new SalveDayPay(employees);
    }
    @Override
    public void execute(List<Employee> employees, List<Syndicate> syndicates){
        PayFunction pay= new PayFunction();
        pay.payment(employees);
    }
}
