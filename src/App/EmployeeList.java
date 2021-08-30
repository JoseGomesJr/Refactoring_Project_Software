package App;

import java.util.*;

import model.Undo.*;
public class EmployeeList {
    //Lista de Empregados e Membros do Sindicato
    private List<Employee> employeelist= new ArrayList<>();
    private List<Syndicate> syndicatelist= new ArrayList<>();
    //Classes auxiliares para o controle de dados
    private PayFunction payFunction= new PayFunction();
    private AuxEmployee AuxEmployee= new AuxEmployee();
    private ChangeEmployee change= new ChangeEmployee();
    private List<Integer> randons= new ArrayList<>();
    // Variavies de controle
    private boolean rundo=false;
    private int datepay= -1;
    //Classes para retrocesseder ou refazer uma ação do usuario
    private LinkedList<Undo> Hrundo= new LinkedList<>();
    private LinkedList<Undo> undo= new LinkedList<>();
    private LinkedList<Undo> redo= new LinkedList<>();

    public void novo(){
        Employee employee;
        Scanner input= new Scanner(System.in);
        redo= new LinkedList<>();
        System.out.println("Employer's name");
        String name= input.nextLine();

        System.out.println("Employer's Adress");
        String adress= input.nextLine();
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
       employee.setPayment(payFunction.AddMethod());
       payFunction.Schedule(employee.getPayment(), employee.typeEmployee());
       employeelist.add(employee);
       Undo sundo= new SalveEmployee(1, employee);
       undo.add(sundo);
       
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
        redo= new LinkedList<>();
        int id= AuxEmployee.SearchEmployeeList(employeelist);
        int idsyn;
        if(id!=-1) {
            if(employeelist.get(id).getSyndicate()==true){
                idsyn=AuxEmployee.SeachSyndicate(syndicatelist, employeelist.get(id));
                Undo sundo= new SalveEmployee(2, employeelist.get(id), syndicatelist.get(idsyn).getId());
                undo.add(sundo);
                syndicatelist.remove(idsyn);
                employeelist.remove(id);
            }
            else{
                Undo sundo= new SalveEmployee(2, employeelist.get(id));
                undo.add(sundo);
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
        this.redo=  new LinkedList<>();
        int idname= AuxEmployee.SearchEmployeeList(employeelist);
        Undo aux=null;
        if(idname!=-1){
            aux=AuxEmployee.AddTimecard(employeelist, idname, aux);
            undo.add(aux);
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
        redo= new LinkedList<>();
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
                
                Undo sundo= new SalveCommission(Undo.COMISSION, ((Commissioned) employeelist.get(idname)).getComissionTotal(), 
                ((Commissioned) employeelist.get(idname)).getDates(), employeelist.get(idname));

                undo.add(sundo);

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
        redo= new LinkedList<>();
        Scanner input= new Scanner(System.in);
        int idname= AuxEmployee.SearchEmployeeList(employeelist);
        if(idname!=-1){
            System.out.println("Inform the percentage that should be charged to the employee");
            Double tax= input.nextDouble();
            Undo sundo= new SalveTaxservi(Undo.TAXSERVICE, employeelist.get(idname).getTaxService(), employeelist.get(idname));
            undo.add(sundo);
            employeelist.get(idname).setTaxService(tax);
            //reUndo.SalveTaxservi(Undo.TAXSERVICE, employeelist.get(idname).getTaxService(), employeelist.get(idname));

        }
        else{
            System.out.println(Color.RED+"The data entered is not associated with any employee"+Color.RESET);
        }
    }
    public void ChangerEmployee(){
        
        Scanner input= new Scanner(System.in);
        Undo sundo;
        System.out.println("Select the employee you want to change");
        int idname= AuxEmployee.SearchEmployeeList(employeelist);
        if(idname!=-1){
            System.out.println("1-Change employee name");
            System.out.println("2-Change employee address");
            System.out.println("3-Change employee type");
            System.out.println("4-Change employee payment method");
            System.out.println("5-Remove a union member / Make an employee part of the union");
            System.out.println("6-Change union ID");
            System.out.println("7-Change the union fee");
            int nSelect= input.nextInt();
            switch (nSelect) {
                case 1:
                    sundo= new SalveName(Undo.CHANGERNAME, employeelist.get(idname).getName(), employeelist.get(idname));
                    undo.add(sundo);
                    change.ChangeName(employeelist.get(idname));
                    redo= new LinkedList<>();
                    break;
                case 2:
                    sundo= new Salveadress(Undo.CHANGERNAME, employeelist.get(idname).getAdress(), employeelist.get(idname));
                    undo.add(sundo);
                    change.ChangeAdress(employeelist.get(idname));
                    redo= new LinkedList<>();
                    break;
                case 3:
                    sundo= new SalveTypeEmployee(Undo.CHANGERTYPE, employeelist.get(idname));
                    undo.add(sundo);
                    change.Changer(idname, employeelist, syndicatelist);
                    redo= new LinkedList<>();
                    break;
                case 4:
                    System.out.println("Choose new payment method");
                    sundo= new SalvePay(Undo.CHANGERPAY, employeelist.get(idname).getPayment(), employeelist.get(idname));
                    undo.add(sundo);
                    employeelist.get(idname).setPayment(payFunction.AddMethod());
                    redo= new LinkedList<>();
                   break;
                case 5:
                
                   if(!employeelist.get(idname).getSyndicate()){

                        System.out.println(Color.YELLOW+"Employee is not part of the union, would you like to add?\n1-Yes\n2-No"+Color.RESET);
                        int select= input.nextInt();
                        if(select==1){
                            sundo= new SalveSyndicate(Undo.CHANGERSYND, employeelist.get(idname), 0);
                            undo.add(sundo);
                            AuxEmployee.AddSyndicate(employeelist.get(idname), syndicatelist);
                            int id= AuxEmployee.SeachSyndicate(syndicatelist, employeelist.get(idname));
                            //reUndo.Salvesyndi(Undo.CHANGERSYND, employeelist.get(idname), syndicatelist.get(id).getId());
                        }
                   }
                   else
                   {
                        System.out.println(Color.YELLOW+"Does the employee part of the union wish to remove?\n1-Yes\n2-No"+Color.RESET);
                        int select= input.nextInt();
                        if(select==1){
                            int id= AuxEmployee.SeachSyndicate(syndicatelist, employeelist.get(idname));
                            sundo= new SalveSyndicate(Undo.CHANGERSYND, employeelist.get(idname), syndicatelist.get(id).getId());
                            undo.add(sundo);
                            AuxEmployee.RemoveSyndicate(employeelist.get(idname), syndicatelist);
                            //reUndo.Salve(Undo.CHANGERSYND, employeelist.get(idname));
                        }
                        
                   }
                   redo= new LinkedList<>();
                   break;
                case 6:
                    int id= AuxEmployee.SeachSyndicate(syndicatelist, employeelist.get(idname));
                    if(id!=-1){
                        System.out.println("Enter the new ID");
                        int newid= input.nextInt();
                        sundo= new SalveIDSyn(Undo.CHANGERIDSYN, syndicatelist.get(id).getId(), id);
                        undo.add(sundo);
                        syndicatelist.get(id).setId(newid);
                        redo= new LinkedList<>();
                    }
                    else{
                        System.out.println(Color.RED+"The data entered is not associated with any unionist"+Color.RESET);
                    }
                   break;
                case 7:
                    System.out.println("Inform the new union fee");
                    Double taxSyndicate= input.nextDouble();
                    sundo= new TaxSynd(Undo.CHANGERTAXSYND, employeelist.get(idname).getTaxSyndicate() , employeelist.get(idname));
                    undo.add(sundo);
                    //rUndo.SalveTaxservi(Undo.CHANGERTAXSYND, employeelist.get(idname).getTaxSyndicate() , employeelist.get(idname));
                    employeelist.get(idname).setTaxSyndicate(taxSyndicate);
                    redo= new LinkedList<>();
                    break;
                default:
                    System.out.println(Color.RED+"None of the options were selected, you will return to the start menu"+Color.RESET);
                    break;
            }
           
        }
        else{
            System.out.println(Color.RED+"The data entered is not associated with any employee"+Color.RESET);
        }
    }
    public void pay(){
        redo= new LinkedList<>();
        Calendar date= Calendar.getInstance();
        int sdate= date.get(Calendar.DAY_OF_MONTH);
        if(datepay!=sdate){
            datepay=sdate;
            Undo sundo= new SalveDayPay(8, employeelist);      
            undo.add(sundo);
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
        if(undo.isEmpty()){
            System.out.println(Color.RED+"You undid all the actions."+Color.RESET);
            return;
        }
        rundo=true;
        Undo sredo=null;
        sredo = undo.getLast().execute(sredo, employeelist, syndicatelist);
        redo.add(sredo);
        Hrundo.add(undo.removeLast());
        System.out.println(Color.GREEN+"Your last action was successfully undone."+Color.RESET);
    }
    public void redo(){
        int idsyn;
        if(redo.isEmpty() || rundo==false){
            System.out.println(Color.RED+"You've redo all the actions"+Color.RESET);
            return;
        }
        redo.getLast().execute(employeelist, syndicatelist);
        redo.removeLast();
        undo.add(Hrundo.removeLast());
        System.out.println(Color.GREEN+"Your last action was successfully redone."+Color.RESET);
    }

}