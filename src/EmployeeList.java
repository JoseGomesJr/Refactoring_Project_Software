import java.util.*;
public class EmployeeList {
    private List<Employee> employeelist= new ArrayList<>();
    private List<Syndicate> syndicatelist= new ArrayList<>();
    private Employee employee;
    private PayFunction payFunction= new PayFunction();
    private AuxEmployee AuxEmployee= new AuxEmployee();
    private ChangeEmployee change= new ChangeEmployee();
    private List<Integer> randons= new ArrayList<>();
    private boolean rundo=false;
    private int datepay= -1;
    private Undo rUndo= new Undo();
    private Undo reUndo= new Undo();
    private AuxRedo AuxRedo = new AuxRedo();
    private LinkedList<History> history= new LinkedList<>();
    public void novo(){
        Scanner input= new Scanner(System.in);
        this.reUndo= new Undo();
        history= new LinkedList<>();
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
       rUndo.Salve(Undo.NEWEMP, employee);
       
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
        this.reUndo= new Undo();
        history= new LinkedList<>();
        int id= AuxEmployee.SearchEmployeeList(employeelist);
        int idsyn;
        // remover o empregado da lista de sindicatos
        if(id!=-1) {
            if(employeelist.get(id).getSyndicate()==true){
                idsyn=AuxEmployee.SeachSyndicate(syndicatelist, employeelist.get(id));
                rUndo.Salvesyndi(Undo.REMOVEEMP, employeelist.get(id), syndicatelist.get(idsyn).getId());
                syndicatelist.remove(idsyn);
                employeelist.remove(id);
                //reUndo.Salvesyndi(Undo.REMOVEEMP, employeelist.get(id), syndicatelist.get(idsyn).getId());
            }
            else{
                rUndo.Salve(Undo.REMOVEEMP, employeelist.get(id));
                employeelist.remove(id);
                //reUndo.Salve(Undo.REMOVEEMP, employeelist.get(id));
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
        this.reUndo= new Undo();
        history= new LinkedList<>();
        int idname= AuxEmployee.SearchEmployeeList(employeelist);
        int aux;
        if(idname!=-1){
            aux=AuxEmployee.AddTimecard(employeelist, idname, rUndo);
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
        this.reUndo= new Undo();
        history= new LinkedList<>();
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
                
                rUndo.Salvecommission(Undo.COMISSION, ((Commissioned) employeelist.get(idname)).getComissionTotal(), 
                ((Commissioned) employeelist.get(idname)).getDates(), employeelist.get(idname));

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
        this.reUndo= new Undo();
        Scanner input= new Scanner(System.in);
        int idname= AuxEmployee.SearchEmployeeList(employeelist);
        if(idname!=-1){
            System.out.println("Inform the percentage that should be charged to the employee");
            Double tax= input.nextDouble();
            rUndo.SalveTaxservi(Undo.TAXSERVICE, employeelist.get(idname).getTaxService(), employeelist.get(idname));
            employeelist.get(idname).setTaxService(tax);
            //reUndo.SalveTaxservi(Undo.TAXSERVICE, employeelist.get(idname).getTaxService(), employeelist.get(idname));

        }
        else{
            System.out.println(Color.RED+"The data entered is not associated with any employee"+Color.RESET);
        }
    }
    public void ChangerEmployee(){
        
        Scanner input= new Scanner(System.in);
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
                    rUndo.Salve(Undo.CHANGERNAME, employeelist.get(idname).getName(), employeelist.get(idname));
                    change.ChangeName(employeelist.get(idname));
                    this.reUndo= new Undo();
                    history= new LinkedList<>();
                    break;
                case 2:
                    rUndo.Salve(Undo.CHANGERADRESS, employeelist.get(idname).getAdress(), employeelist.get(idname));
                    change.ChangeAdress(employeelist.get(idname));
                    this.reUndo= new Undo();
                    history= new LinkedList<>();
                    break;
                case 3:
                    rUndo.Salve(Undo.CHANGERTYPE, employeelist.get(idname));
                    change.Changer(idname, employeelist, syndicatelist);
                    this.reUndo= new Undo();
                    history= new LinkedList<>();
                    break;
                case 4:
                    System.out.println("Choose new payment method");
                    rUndo.Salvepay(Undo.CHANGERPAY, employeelist.get(idname).getPayment(), employeelist.get(idname));
                    employeelist.get(idname).setPayment(payFunction.AddMethod());
                    this.reUndo= new Undo();
                    history= new LinkedList<>();
                   break;
                case 5:
                
                   if(!employeelist.get(idname).getSyndicate()){

                        System.out.println(Color.YELLOW+"Employee is not part of the union, would you like to add?\n1-Yes\n2-No"+Color.RESET);
                        int select= input.nextInt();
                        if(select==1){
                            rUndo.Salve(Undo.CHANGERSYND, employeelist.get(idname));
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
                            rUndo.Salvesyndi(Undo.CHANGERSYND, employeelist.get(idname), syndicatelist.get(id).getId());
                            AuxEmployee.RemoveSyndicate(employeelist.get(idname), syndicatelist);
                            //reUndo.Salve(Undo.CHANGERSYND, employeelist.get(idname));
                        }
                        
                   }
                   this.reUndo= new Undo();
                   history= new LinkedList<>();
                   break;
                case 6:
                    int id= AuxEmployee.SeachSyndicate(syndicatelist, employeelist.get(idname));
                    if(id!=-1){
                        System.out.println("Enter the new ID");
                        int newid= input.nextInt();
                        rUndo.Salveidsyn(Undo.CHANGERIDSYN, syndicatelist.get(id).getId(), id);
                        syndicatelist.get(id).setId(newid);
                        this.reUndo= new Undo();
                        history= new LinkedList<>();
                    }
                    else{
                        System.out.println(Color.RED+"The data entered is not associated with any unionist"+Color.RESET);
                    }
                   break;
                case 7:
                    System.out.println("Inform the new union fee");
                    Double taxSyndicate= input.nextDouble();
                    rUndo.SalveTaxservi(Undo.CHANGERTAXSYND, employeelist.get(idname).getTaxSyndicate() , employeelist.get(idname));
                    employeelist.get(idname).setTaxSyndicate(taxSyndicate);
                    this.reUndo= new Undo();
                    history= new LinkedList<>();
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
        this.reUndo= new Undo();
        history= new LinkedList<>();
        Calendar date= Calendar.getInstance();
        int sdate= date.get(Calendar.DAY_OF_MONTH);
        if(datepay!=sdate){
            datepay=sdate;
            rUndo.Salvesalary(Undo.PAY, employeelist);
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
        int idsyn;
        if(rUndo.list()==false){
            System.out.println(Color.RED+"You undid all the actions."+Color.RESET);
            return;
        }
        rundo=true;
        history.add(rUndo.getLast());
        switch (rUndo.getSoption()){
            case Undo.NEWEMP:
                if(rUndo.getSemployee().getSyndicate()==true){
                    AuxRedo.redoEmployeesynd(reUndo, rUndo.getLast(), syndicatelist);
                    idsyn=AuxEmployee.SeachSyndicate(syndicatelist, rUndo.getSemployee());
                    syndicatelist.remove(idsyn);
                    employeelist.remove(rUndo.getSemployee());
                }
                else{
                    AuxRedo.redoEmployee(reUndo, rUndo.getLast());
                    employeelist.remove(rUndo.getSemployee());
                }
                rUndo.removelast();
                break;
            case Undo.REMOVEEMP:
                AuxRedo.redoEmployee(reUndo, rUndo.getLast());
                if(rUndo.getSemployee().getSyndicate()==true){
                    AuxEmployee.AddSyndicate(rUndo.getSemployee(), syndicatelist, rUndo.getIdsyn());
                    employeelist.add(rUndo.getSemployee());
                }
                else{
                    employeelist.add(rUndo.getSemployee());
                }
                rUndo.removelast();
                break;
            case Undo.TIME:
                AuxRedo.redotime(reUndo, rUndo.getLast());
                rUndo.undoTime();
                break;

            case Undo.COMISSION:
                AuxRedo.redcommssion(reUndo, rUndo.getLast());
                rUndo.undocomission();
                break;
            case Undo.TAXSERVICE:
                AuxRedo.redtax(reUndo, rUndo.getLast());
                rUndo.undotaxService();
                break;
            case Undo.CHANGERNAME:
                AuxRedo.redname(reUndo, rUndo.getLast());
                rUndo.undoname();
                break;
            case Undo.CHANGERADRESS:
                AuxRedo.redadress(reUndo, rUndo.getLast());
                rUndo.undoadress();
                break;
            case Undo.CHANGERTYPE:
                AuxRedo.redtype(reUndo, employeelist, rUndo.getLast());
                rUndo.undotype(employeelist, syndicatelist);
                break;
            case Undo.CHANGERPAY:
                AuxRedo.redpay(reUndo, rUndo.getLast());
                rUndo.undopayment();
                break;
            case Undo.CHANGERSYND:
                
                if(rUndo.getSemployee().getSyndicate()==true){
                    AuxRedo.redoEmployeesynd(reUndo, rUndo.getLast(), syndicatelist);
                    AuxEmployee.RemoveSyndicate(rUndo.getSemployee(), syndicatelist);
                    rUndo.removelast();
                }
                else{
                    AuxRedo.redoEmployee(reUndo, rUndo.getLast());
                    AuxEmployee.AddSyndicate(rUndo.getSemployee(), syndicatelist, rUndo.getIdsyn());
                    rUndo.removelast();
                }
                break;
            case Undo.CHANGERIDSYN:
                AuxRedo.rediddyn(reUndo, rUndo.getLast(), syndicatelist);
                rUndo.undoidsyn(syndicatelist);
                break;
            case Undo.CHANGERTAXSYND:
                AuxRedo.redtaxsyn(reUndo, rUndo.getLast());
                rUndo.undotaxsynd();
                break;
            case Undo.PAY:
                rUndo.undosalary(employeelist);
                datepay=-1;
                break;
            default:
                System.out.println(Color.RED+"Something went wrong, you will return to the menu."+Color.RESET);
                return;
        }
        System.out.println(Color.GREEN+"Your last action was successfully undone."+Color.RESET);
    }
    public void redo(){
        int idsyn;
        if(reUndo.list()==false || rundo==false){
            System.out.println(Color.RED+"You've redo all the actions"+Color.RESET);
            return;
        }
        rUndo.addHistory(history.getLast());
        history.removeLast();
        switch (reUndo.getSoption()){
            case Undo.NEWEMP:
                if(reUndo.getSemployee().getSyndicate()==true){
                    AuxEmployee.AddSyndicate(reUndo.getSemployee(), syndicatelist, reUndo.getIdsyn());
                }
                employeelist.add(reUndo.getSemployee());
                reUndo.removelast();
                break;
            case Undo.REMOVEEMP:

                if(reUndo.getSemployee().getSyndicate()==true){
                    idsyn=AuxEmployee.SeachSyndicate(syndicatelist, reUndo.getSemployee());
                    syndicatelist.remove(idsyn);
                }
                else{
                    employeelist.remove(reUndo.getSemployee());
                }
                reUndo.removelast();
                break;
            case Undo.TIME:

                reUndo.redotime();
                break;

            case Undo.COMISSION:
                reUndo.undocomission();
                break;
            case Undo.TAXSERVICE:
                reUndo.undotaxService();
                break;
            case Undo.CHANGERNAME:
                reUndo.undoname();
                break;
            case Undo.CHANGERADRESS:
                reUndo.undoadress();
                break;
            case Undo.CHANGERTYPE:
                reUndo.undotype(employeelist, syndicatelist);
                break;
            case Undo.CHANGERPAY:
                reUndo.undopayment();
                break;
            case Undo.CHANGERSYND:
                if(rUndo.getSemployee().getSyndicate()==true){
                    AuxEmployee.RemoveSyndicate(reUndo.getSemployee(), syndicatelist);
                    reUndo.removelast();
                }
                else{
                    AuxEmployee.AddSyndicate(reUndo.getSemployee(), syndicatelist, reUndo.getIdsyn());
                    reUndo.removelast();
                }
                break;
            case Undo.CHANGERIDSYN:
                reUndo.undoidsyn(syndicatelist);
                break;
            case Undo.CHANGERTAXSYND:
                reUndo.undotaxsynd();
                break;
            case Undo.PAY:
                pay();
                break;
            default:
                System.out.println(Color.RED+"Something went wrong, you will return to the menu."+Color.RESET);
                return;
        }
        System.out.println(Color.GREEN+"Your last action was successfully redone."+Color.RESET);
    }

}