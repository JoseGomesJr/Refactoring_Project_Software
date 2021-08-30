package App;

import java.util.*;

import model.Undo.*;
public class AuxEmployee {
    private Random gerador = new Random(19904522);
    private int random;
    public int SeachEmployee (String name, List<Employee> employees){
        int posi=0, local=0;
        int aux[]= new int[employees.size()];
        Scanner input= new Scanner(System.in);
        for(Employee employee: employees)
        {
            if(name.equals(employee.getName()))
            {
                aux[local]=posi;
                local++;
            }
            posi++;
        }
        if(local==0) return -1; 
        if(local > 1){
            System.out.println(Color.YELLOW+"For more than one employee with the same name, select the correct one"+Color.RESET);
            for(int cont=0; cont < local; cont++){
                System.out.println(cont+" - "+"Name:"+employees.get(aux[cont]).getName()+"\n"+"ID: "+employees.get(aux[cont]).getId()+"\nType: "+
                employees.get(aux[cont]).typeEmployee());
            }
            int nSelect= input.nextInt();
            if(nSelect >= local || nSelect < 0)
            {
                System.out.println(Color.RED+"Invalid option"+Color.RESET);
                return -1;
            }
            else{
                return aux[nSelect];
            }
        }
        else{
            return aux[0];
        }

    }
    public int SeachEmployee (int id, List<Employee> employees){
        int posi=0, aux=-1;
        for(Employee employee: employees)
        {
            if(id==employee.getId())
            {
                aux=posi;
                break;
            }
            posi++;
        } 
        return aux;
    }

    public int CheckEmployees( List<Employee> employees, String name){
        Scanner input= new Scanner(System.in);
        int aux=SeachEmployee(name, employees);
        if(aux==-1){
            System.out.println(Color.RED+"Employee name does not exist"+Color.RESET);
            System.out.println("Would you like to enter a new name\n1-Yes\n2-No");
            int nSelect= input.nextInt();
            input.nextLine();
            switch (nSelect) {
                case 1:
                    System.out.println(Color.YELLOW+"Enter the new name\n"+
                    "Caution!The employee's name must be entered in the same way it was registered"+Color.RESET);
                    String newName= input.nextLine();
                    return CheckEmployees(employees, newName);
                default:
                    break;
            }
        }
        else{
            return aux;
        }
        return -1;

    }
    public int CheckEmployees(List<Employee> employees, int id){
        Scanner input= new Scanner(System.in);
        int aux=SeachEmployee(id, employees);
        if(aux==-1){
            System.out.println(Color.RED+"Employee id does not exist"+Color.RESET);
            System.out.println("Would you like to enter a new id\n1-Yes\n2-No");
            int nSelect= input.nextInt();
            switch (nSelect) {
                case 1:
                    System.out.println("Enter the new ID");
                    int newID= input.nextInt();
                    return CheckEmployees(employees, newID);
                default:

                    break;
            }
        }
        else{
            return aux;
        }
        return -1;

    }
    public void AddSyndicate(Employee employee, List<Syndicate> syndicates){
        Scanner input= new Scanner(System.in);
        Syndicate unionlist;
        System.out.println("Is the new employee part of the union?\n1-Yes\n2-No");
        int nSelect= input.nextInt();
        if(nSelect==1){
            System.out.println("What is the employee union id?");
            int id= input.nextInt();
            employee.setSyndicate(true);
            unionlist= new Syndicate(employee, id);
           syndicates.add(unionlist);
        }
    }
    public Undo AddTimecard(List<Employee> employees, int id, Undo rUndo){
        Scanner input= new Scanner(System.in);
        if(employees.get(id).typeEmployee().equals("Hourly"))
        {

            System.out.println("Choose the option:\n1-Entry time\n2-Exit time");
            int nSelect= input.nextInt();
            switch (nSelect) {
                case 1:
                    rUndo= new SalveTime(Undo.TIME, 0d, employees.get(id), nSelect);
                   
                    ((Hourly)employees.get(id)).setEntryCard();
                    System.out.println(Color.GREEN+"Registered entry. Good work!"+Color.RESET);
                    return rUndo;
                case 2:
                    rUndo= new SalveTime(Undo.TIME, ((Hourly)employees.get(id)).getPay(), employees.get(id), nSelect);
                    
                    ((Hourly)employees.get(id)).setExitCard();
                    ((Hourly)employees.get(id)).setHoursDay();
                    System.out.println(Color.GREEN+"Checkout registered. To the next"+Color.RESET);
                    return rUndo;
                default:
                    System.out.println(Color.YELLOW+"None of the options were selected, you will return to the start menu"+Color.RESET);
                    break;
            }
        }
        else{
            System.out.println(Color.RED+"The employee informed is not an hourly"+Color.RESET);
        }
        return rUndo;
    }
    public int day(){
        Calendar calendar= Calendar.getInstance();
        int day=calendar.get(Calendar.DAY_OF_WEEK);
        return day;
    }
    public void Printcard(List<Employee> employees, int id){
        if(employees.get(id).typeEmployee().equals("Hourly"))
        {
           ((Hourly)employees.get(id)).Cardinf();
        }
        return;
    }
    public int SearchEmployeeList(List<Employee> employeelist){
        Scanner input= new Scanner(System.in);
        int id=-1;
        System.out.println("Select a form of identification:\n1-id\n2-Name");
        int nSelect= input.nextInt();
        input.nextLine();
        switch (nSelect){
            case 1:
                System.out.println("Enter ID");
                id= input.nextInt();
                id= CheckEmployees(employeelist, id);
                if(id!=-1)  return id;
                break;
            case 2:
                System.out.println("Enter the name\n"+ Color.YELLOW+ 
                "Caution!The employee's name must be entered in the same way it was registered"+Color.RESET);
                String name= input.nextLine();
                id= CheckEmployees(employeelist, name);
                if(id!=-1) return id;
                break;
            default:
                System.out.println(Color.YELLOW+"None of the options were selected, you will return to the start menu"+Color.RESET);
                break;
        }
        return id;
    }
    public int SeachSyndicate(List<Syndicate> syndicates, Employee employee){
        int posi=0, aux=-1;
        for(Syndicate syndicate : syndicates)
        {
            if(syndicate.getUnionlist().equals(employee) && syndicate.getUnionlist().getId()== employee.getId())
            {
                aux=posi;
                break;
            }
            posi++;
        } 
        return aux;
    }
    public void AddSyndicate(Employee employee, List<Syndicate> syndicates, int id){
        Syndicate unionlist;
        employee.setSyndicate(true);
        unionlist= new Syndicate(employee, id);
        syndicates.add(unionlist);
    }
    public void RemoveSyndicate(Employee employee, List<Syndicate> syndicates){
        employee.setSyndicate(false);
        int aux= SeachSyndicate(syndicates, employee);
        if(aux!=-1){
            syndicates.remove(aux);
        }
        else{
            System.out.println(Color.RED+"The data entered is not associated with any unionist"+Color.RESET);
        }
    }
    public int random(List<Integer> randons){
        int random= gerador.nextInt(189773);
        for(Integer num: randons){
            if(random == ((int) num)){
                int randomnew = (int) random(randons);
                return randomnew;
            }
        }
        randons.add(random);
        return random;
    }
    
}
