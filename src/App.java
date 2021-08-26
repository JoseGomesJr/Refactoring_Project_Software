import java.util.*;
public class App {
    public static void main(String[] args) throws Exception {
        EmployeeList employees= new EmployeeList();
        Scanner input= new Scanner(System.in);
        int nSelect=0;
        do
        {   System.out.println(Color.GREEN+"Main selection menu"+Color.RESET);
            System.out.println("Selection Option:");
            System.out.println("1-New employee");
            System.out.println("2-Remove employee");
            System.out.println("3-Report time card");
            System.out.println("4-Sales result");
            System.out.println("5-Additional union fee");
            System.out.println("6-Changing an employee's data");
            System.out.println("7-Payroll Wheel.");
            System.out.println("8-Change employee's pay schedule");
            System.out.println("9-Registered employees");
            System.out.println("10-Add new payment schedule");
            System.out.println("11-Current union members");
            System.out.println("12-Employee Timecard Information");
            System.out.println("13-Undo");
            System.out.println("14-Redo");
            System.out.println("0-Exit");
            nSelect= input.nextInt();
           switch (nSelect) {
               case 1:
                    employees.novo();
                    continue;
                case 2:
                    employees.removeEmployee();
                    continue;
                case 3:
                    employees.Timecard();
                    continue;
                case 4:
                    employees.Salles();
                    continue;
                case 5:
                    employees.TaxService();
                    continue;
                case 6:
                    employees.ChangerEmployee();
                    continue;
                case 7:
                    employees.pay();
                    continue;
                case 8:
                    employees.scheduleoptions();
                    continue;
                case 9:
                    employees.printList();
                    continue;
                case 10:
                    employees.addSchedule();
                    continue;
                case 11:
                    employees.getSyndicatelist();
                    continue;
                case 12:
                    employees.Infotime();
                    continue;
                case 13:
                    employees.undo();
                    continue;
                case 14:
                    employees.redo();
                    continue;
               default:
                   break;
           }
           
        }while(nSelect!=0);

        input.close();
    }
}
