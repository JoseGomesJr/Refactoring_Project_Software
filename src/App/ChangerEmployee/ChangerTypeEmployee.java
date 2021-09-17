package App.ChangerEmployee;

import java.util.*;
import java.util.Scanner;

import App.*;
import model.Employee.Commissioned;
import model.Employee.Employee;
import model.Employee.Hourly;
import model.Employee.Salaried;
import model.Pays.PaymentMethod;
import model.Syndicate.Syndicate;
import model.Undo.*;

public class ChangerTypeEmployee implements Changer {

    private Employee ChangerTypeEmployees(Employee employee){
        Scanner input= new Scanner(System.in);
        PaymentMethod auxPay= employee.getPayment();
        System.out.println("Select the new employee type:\n1-Hourly\n2-Salaried\n3-Commissioned");
        int nSelect= input.nextInt();
        switch (nSelect) {
            case 1:
                if(employee.typeEmployee().equals("Hourly")){
                    System.out.println("Selected employee is hourly type.");
                    break;
                }
                System.out.println("Hourly wage");
                Double hours= input.nextDouble();
                employee= new Hourly(employee.getName(), employee.getAdress(), employee.getId(), hours, employee.getTaxSyndicate());
                employee.setPayment(auxPay);
                break;
            case 2:
            if(employee.typeEmployee().equals("Salaried")){
                System.out.println("Selected employee is salaried type.");
                break;
            }
                System.out.println("Monthly salary");
                Double salary= input.nextDouble();
                employee= new Salaried(employee.getName(), employee.getAdress(), employee.getId(), salary, employee.getTaxSyndicate());
                employee.setPayment(auxPay);
            break;

            case 3:
            if(employee.typeEmployee().equals("Commssioned")){
                System.out.println("Selected employee is commssioned type.");
                break;
            }
                System.out.println("Monthly salary");
                Double salary_commission= input.nextDouble();
                System.out.println("Commission");
                Double commission= input.nextDouble();
                employee= new Commissioned(employee.getName(), employee.getAdress(), employee.getId(), salary_commission, commission, employee.getTaxSyndicate());
                employee.setPayment(auxPay);
            break;
            default:
                break;
        }
        return employee;
    }
    public void Changer(int idname, List<Employee> employeelist, List<Syndicate> syndicatelist){
        Employee employee;
        if(employeelist.get(idname).getSyndicate()==false){
            employee=employeelist.get(idname);
            employeelist.remove(idname);
            employeelist.add(idname, ChangerTypeEmployees(employee));
        }
        else{
             int id= AuxEmployee.SeachSyndicate(syndicatelist, employeelist.get(idname));
             employee=employeelist.get(idname);
             employeelist.remove(idname);
             employeelist.add(idname, ChangerTypeEmployees(employee));
             if(id!=-1) {
                 int idsyndicate=syndicatelist.get(id).getId();
                 syndicatelist.remove(id);
                 AuxEmployee.AddSyndicate(employeelist.get(idname), syndicatelist, idsyndicate);
             }
        }
    }
    @Override
    public void execute(List<Employee> employeelist, List<Syndicate> syndicates, Action_Undo action_Undo, int idname){
        Undo sundo;
        sundo= new SalveTypeEmployee( employeelist.get(idname));
        action_Undo.setUndo(sundo);
        Changer(idname, employeelist, syndicates);
    }
    
}
