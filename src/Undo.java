import java.time.LocalTime;
import java.util.*;
public class Undo {
    //Acões que foram efetuadas
    public static final int NEWEMP=1;
    public static final int REMOVEEMP=2;
    public static final int TIME=3;
    public static final int COMISSION=4;
    public static final int TAXSERVICE=5;
    public static final int CHANGERNAME=61;
    public static final int CHANGERADRESS=62;
    public static final int CHANGERTYPE=63;
    public static final int CHANGERPAY=64;
    public static final int CHANGERSYND=65;
    public static final int CHANGERIDSYN=66;
    public static final int CHANGERTAXSYND=67;
    public static final int PAY=7;
    //Meta dados a serem salvos para possivel retrocesso
    private LinkedList<History> history= new LinkedList<>();
    public void Salve(int option, String name, Employee employee){
        History hist= new History();
        hist.soption= option;
        hist.sname= name;
        hist.semployee= employee;
        this.history.add(hist);
    }
    public void Salve(int option, Employee nEmployee){
        History hist= new History();
        hist.semployee= nEmployee;
        hist.soption= option;
        this.history.add(hist);
    }
    public void Salvesyndi(int option, Employee nEmployee, int idsyn){
        History hist= new History();
        hist.semployee= nEmployee;
        hist.soption= option;
        hist.idsyn= idsyn;
        this.history.add(hist);
    }
    public void Salvecommission(int option, Double comission, List<String> date, Employee employee){
        History hist= new History();
        hist.soption=option;
        hist.commission= comission;
        hist.date= date;
        hist.semployee= employee;
        this.history.add(hist);
    }
    public void SalveTaxservi(int option, Double tax, Employee employee){
        History hist= new History();
        hist.soption=option;
        hist.semployee= employee;
        hist.spay= tax;
        this.history.add(hist);
    }
    public void Salvetime(int option,Double pay, Employee employee, int aux){
        History hist= new History();
        hist.sobj=aux;
        hist.semployee=employee;
        hist.soption=option;
        if(aux == 2){
            hist.spay= pay;
        }
        this.history.add(hist);
    }
    public void Salvetime(int option,Double pay, Employee employee, int aux, Date date, LocalTime datetime){
        History hist= new History();
        hist.sobj=aux;
        hist.semployee=employee;
        hist.soption=option;
        hist.datehours= date;
        hist.datetimehours= datetime;
        if(aux == 2){
            hist.spay= pay;
        }
        this.history.add(hist);
    }
    public void Salvepay(int option, PaymentMethod pay, Employee employee){
        History hist= new History();
        hist.soption=option;
        hist.semployee= employee;
        hist.spayment= pay;
        this.history.add(hist);
    }
    public void Salveidsyn(int option, int idsyn, int sobj){
        History hist= new History();
        hist.soption= option;
        hist.idsyn= idsyn;
        hist.sobj= sobj;
        this.history.add(hist);
    }
    public void Salvesalary(int option,List<Employee> employeelist){
        History hist= new History();
        hist.soption=option;
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
        this.history.add(hist);
    }
    public void undoTime(){
        History hist = this.history.getLast();
        if(hist.sobj==1){
            if(hist.semployee.typeEmployee().equals("Hourly")){
                ((Hourly)hist.semployee).resetEntry();
            }else{
                System.out.println("Something went wrong!");
            }
        }
        else if(hist.sobj==2){
            if(hist.semployee.typeEmployee().equals("Hourly")){
                ((Hourly)hist.semployee).resetExit();
                ((Hourly)hist.semployee).setPay(hist.spay);
                hist.spay=0d;
            }else{
                System.out.println("Something went wrong!");
            }
        }
        this.history.removeLast();
    }
    public void redotime(){
        History hist = this.history.getLast();
        if(hist.sobj==1){
            if(hist.semployee.typeEmployee().equals("Hourly")){
                ((Hourly)hist.semployee).getCard().setDate(hist.datehours);
                ((Hourly)hist.semployee).getCard().setEntraDate(hist.datetimehours);
            }else{
                System.out.println("Something went wrong!");
            }
        }
        else if(hist.sobj==2){
            if(hist.semployee.typeEmployee().equals("Hourly")){
                ((Hourly)hist.semployee).getCard().setExitdate(hist.datehours);
                ((Hourly)hist.semployee).getCard().setExiDate(hist.datetimehours);
                ((Hourly)hist.semployee).setPay(hist.spay);
                hist.spay=0d;
            }else{
                System.out.println("Something went wrong!");
            }
        }
        this.history.removeLast();
    }
    public void undocomission(){
        History hist = this.history.getLast();
        if(hist.semployee.typeEmployee().equals("Commssioned")){
            ((Commissioned) hist.semployee).setComissionTotal(hist.commission);
            ((Commissioned) hist.semployee).removdate(hist.sname);
            
        }else{
            System.out.println("Something went wrong!");
        }
        this.history.removeLast();
    }
    public void undotaxService(){
        History hist = this.history.getLast();
        hist.semployee.setTaxService(hist.spay);
        this.history.removeLast();
    }
    public void undoname(){
        History hist = this.history.getLast();
        hist.semployee.setName(hist.sname);
        this.history.removeLast();
    }
    public void undoadress(){
        History hist = this.history.getLast();
        hist.semployee.setAdress(hist.sname);
        this.history.removeLast();
    }
    public void undotype( List<Employee> employees, List<Syndicate> syndicates){
        int posi=0;
        History hist = this.history.getLast();
        for(Employee employee: employees){
            if(hist.semployee.getName().equals(employee.getName()) && hist.semployee.getId()==employee.getId()){
                employees.remove(posi);
                employees.add(posi, hist.semployee);
            }
            posi++;
        }
        posi=0;
        if(hist.semployee.getSyndicate()){
            for(Syndicate syndicate: syndicates){
                if(hist.semployee.getName().equals(syndicate.getUnionlist().getName()) && 
                hist.semployee.getId()==syndicate.getUnionlist().getId()){

                    syndicates.get(posi).setUnionlist(hist.semployee);
                }
                posi++;
            }
        }
        this.history.removeLast();
    }
    public void undopayment(){
        History hist = this.history.getLast();
        hist.semployee.setPayment(hist.spayment);
        this.history.removeLast();
    }
    public void undoidsyn(List<Syndicate> syndicatelist){
        History hist = this.history.getLast();
        syndicatelist.get(hist.sobj).setId(hist.idsyn);
        this.history.removeLast();
    }
    public void undotaxsynd(){
        History hist = this.history.getLast();
        hist.semployee.setTaxSyndicate(hist.taxsynd);
        this.history.removeLast();
    }
    public void undosalary(List<Employee> employeelist){
        History hist = this.history.getLast();
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
        this.history.removeLast();
    }
    public Employee getSemployee() {
        return history.getLast().semployee;
    }
    public int getSoption() {
        return history.getLast().soption;
    }
    public int getIdsyn() {
        return history.getLast().idsyn;
    }
    public Double getSpay() {
        return history.getLast().spay;
    }
    public int getSobj() {
        return history.getLast().sobj;
    }
    public void removelast(){
        this.history.removeLast();
    }
    public boolean list(){
        if(this.history.size()==0) return false;
        else return true;
    }
    public void setHistory(LinkedList<History> history) {
        this.history = history;
    }
    public History getLast(){
        return this.history.getLast();
    }
    public void addHistory(History hist){
        this.history.add(hist);
    }
    /*public void rendo(History hist){
        History histrendo= new History();
        histrendo.soption= hist.soption;
        histrendo.semployee= hist.semployee;
        histrendo.sname = hist.semployee.getName();
        histrendo.sobj= hist.sobj;
    }*/
}
