package App;

import java.util.*;

import App.ChangerEmployee.*;
import App.utils.Adress;
import App.utils.Color;
import model.Employee.Commissioned;
import model.Employee.Employee;
import model.Employee.Hourly;
import model.Employee.Salaried;
import model.Pays.PayFunction;
import model.Syndicate.Syndicate;
import model.Undo.*;


public class EmployeeList {
    //Lista de Empregados e Membros do Sindicato
    private List<Employee> employeelist= new ArrayList<>();
    private List<Syndicate> syndicatelist= new ArrayList<>();
    //Classes auxiliares para o controle de dados
    private PayFunction payFunction= new PayFunction();
    // Variavies de controle
    private List<Integer> randons= new ArrayList<>();
    private boolean rundo=false;
    private int datepay= -1;
    //Classes para retrocesseder ou refazer uma ação do usuario
    Action_Undo action_Undo= new Action_Undo();

    public void novo(){
        Employee employee;
        Scanner input= new Scanner(System.in);
        action_Undo.reset();
        System.out.println("Employer's name");
        String name= input.nextLine();

        System.out.println("Employer's Adress");
        Adress adress= AuxEmployee.addAdress();
        System.out.println("Union fee to be charged.");
        Double taxSyndicate= input.nextDouble();
        int id= AuxEmployee.random(randons);

       System.out.printf("Type Employee:1-Hourly\n2-Salaried\n3-Comissioned\nSelect Number:");
       int num= input.nextInt();

       switch (num) {
           case 1:
                System.out.println("Hourly wage");
                Double hours= input.nextDouble();
                employee= new Hourly(name,adress,id,hours, taxSyndicate);
             
            break;

            case 2:
                System.out.println("Monthly salary");
                Double salary= input.nextDouble();
                employee= new Salaried(name, adress, id, salary, taxSyndicate);
               
            break;

            case 3:
                System.out.println("Monthly salary");
                Double salary_commission= input.nextDouble();
                System.out.println("Commission");
                Double commission= input.nextDouble();
                employee= new Commissioned(name, adress, id, salary_commission, commission, taxSyndicate);
            break;

           default:
            System.out.println(Color.RED+"None of the options were selected, you will return to the start menu"+Color.RESET);
            return;
       }
       AuxEmployee.AddSyndicate(employee, syndicatelist);
       employee.setPayment(PayFunction.AddMethod());
       payFunction.Schedule(employee.getPayment(), employee.typeEmployee());
       employeelist.add(employee);
       Undo sundo= new SalveEmployee(1, employee);
       action_Undo.setUndo(sundo);
       
    }
    public void printList()
    {
        for(Employee employees: employeelist)
        {
            System.out.println(Color.BLUE+"----EMPLOYEE----");
            System.out.println(employees.printInfo());
            System.out.println(employees.getPayment().InfoPay()+"\nEmployee belongs to the union: "+employees.getSyndicate());
            
        }
        System.out.println(Color.RESET);
    }
    public void removeEmployee()
    {
        action_Undo.reset();
        int id= AuxEmployee.SearchEmployeeList(employeelist);
        int idsyn;
        if(id!=-1) {
            if(employeelist.get(id).getSyndicate()==true){
                idsyn=AuxEmployee.SeachSyndicate(syndicatelist, employeelist.get(id));
                Undo sundo= new SalveEmployee(2, employeelist.get(id), syndicatelist.get(idsyn).getId());
                action_Undo.setUndo(sundo);
                syndicatelist.remove(idsyn);
                employeelist.remove(id);
            }
            else{
                Undo sundo= new SalveEmployee(2, employeelist.get(id));
                action_Undo.setUndo(sundo);
                employeelist.remove(id);
            }
            System.out.println(Color.GREEN+"Employee successfully removed"+Color.RESET);

        }
        else{
            System.out.println(Color.RED+"The data entered is not associated with any employee"+Color.RESET);
        }
        
    }
    public void getSyndicatelist() {
        for(Syndicate syndicate: syndicatelist)
        {
            System.out.println("----SYNDICATE----");
            System.out.println(syndicate.printInfo());  
        }
    }
    public void Timecard(){
        action_Undo.reset();
        int idname= AuxEmployee.SearchEmployeeList(employeelist);
        Undo aux=null;
        if(idname!=-1){
            aux=AuxEmployee.AddTimecard(employeelist, idname, aux);
            action_Undo.setUndo(aux);
        }
        else{
            System.out.println(Color.RED+"The data entered is not associated with any employee"+Color.RESET);
        }
    }
    public void Infotime(){
        int idname= AuxEmployee.SearchEmployeeList(employeelist);
        if(idname!=-1){
            AuxEmployee.Printcard(employeelist, idname);
        }
        else{
            System.out.println(Color.RED+"The data entered is not associated with any employee"+Color.RESET);
        }
    }
    public void Salles(){
        action_Undo.reset();
        Scanner input= new Scanner(System.in);
        int idname= AuxEmployee.SearchEmployeeList(employeelist);
        System.out.println("Informs the sales result:");
        if(idname!=-1){
            if(employeelist.get(idname).typeEmployee().equals("Commssioned")){

                System.out.println("Sale value:");
                Double valor= input.nextDouble();
                System.out.println("Date:");
                System.out.println(Color.YELLOW+"The date must be informed in the following format : dd/mm/yyyy HH:mm"+Color.RESET);
                input.nextLine();
                String date= input.nextLine();
                
                Undo sundo= new SalveCommission( ((Commissioned) employeelist.get(idname)).getComissionTotal(), 
                ((Commissioned) employeelist.get(idname)).getDates(), employeelist.get(idname));

                action_Undo.setUndo(sundo);

                ((Commissioned) employeelist.get(idname)).setComissionTotal(valor, date);
            }
            else{
                System.out.println(Color.RED+"The employee informed is not a commissioner"+Color.RESET);
            }
        }
        else{
            System.out.println(Color.RED+"The data entered is not associated with any employee"+Color.RESET);
        }
    }
    public void TaxService(){
        action_Undo.reset();
        Scanner input= new Scanner(System.in);
        int idname= AuxEmployee.SearchEmployeeList(employeelist);
        if(idname!=-1){
            System.out.println("Inform the percentage that should be charged to the employee");
            Double tax= input.nextDouble();
            Undo sundo= new SalveTaxservi( employeelist.get(idname).getTaxService(), employeelist.get(idname));
            action_Undo.setUndo(sundo);
            employeelist.get(idname).setTaxService(tax);
           

        }
        else{
            System.out.println(Color.RED+"The data entered is not associated with any employee"+Color.RESET);
        }
    }
    public void ChangerEmployee(){
        
        InputChanger inputChanger= new InputChanger();
        ActionChanger change= new ActionChanger();
        System.out.println("1-Change employee name");
        System.out.println("2-Change employee address");
        System.out.println("3-Change employee type");
        System.out.println("4-Change employee payment method");
        System.out.println("5-Remove a union member / Make an employee part of the union");
        System.out.println("6-Change union ID");
        System.out.println("7-Change the union fee");
        if(inputChanger.input()==0) return;
        change.setActions(inputChanger.getAction());
        //O polimorfismo esta sendo aplicado na chamada abaixo
        change.Acition(employeelist, syndicatelist, action_Undo);
        action_Undo.reset();
        

    }
    public void pay(){
        action_Undo.reset();
        Calendar date= Calendar.getInstance();
        int sdate= date.get(Calendar.DAY_OF_MONTH);
        if(datepay!=sdate){
            datepay=sdate;
            Undo sundo= new SalveDayPay( employeelist);      
            action_Undo.setUndo(sundo);
            System.out.println(Color.YELLOW+"The employees who will be paid today will be shown below:"+Color.RESET);
            payFunction.payment(employeelist);
            System.out.println(Color.YELLOW+"End of today's payment list\n"+Color.RESET);
        }
        else{
            System.out.println(Color.YELLOW+"The payment for the day has already been made.\n"+Color.RESET);
        }
    }
    public void scheduleoptions(){
        System.out.println("Select the employee you want to change");
        int idname= AuxEmployee.SearchEmployeeList(employeelist);
        if(idname!=-1){
            payFunction.PaymentSchedule(employeelist.get(idname).getPayment()); 
        }
        else{
            System.out.println(Color.RED+"The data entered is not associated with any employee"+Color.RESET);
        }
    }
    public void addSchedule(){
        Scanner input= new Scanner(System.in);
        System.out.println("Enter new payment schedule:");
        String schedule= input.nextLine();
        payFunction.addSchedule(schedule);
    }
    public void undo(){
        if(action_Undo.undo()){
            System.out.println(Color.RED+"You undid all the actions."+Color.RESET);
            return;
        }
        rundo=true;
        action_Undo.execute_undo(employeelist, syndicatelist);
    }
    public void redo(){
        if(action_Undo.redo() || rundo==false){
            System.out.println(Color.RED+"You've redo all the actions"+Color.RESET);
            return;
        }
        action_Undo.execute_redo(employeelist, syndicatelist);
    }

}