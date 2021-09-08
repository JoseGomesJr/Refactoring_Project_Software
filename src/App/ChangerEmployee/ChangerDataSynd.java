package App.ChangerEmployee;

import java.util.*;
import java.util.Scanner;

import App.*;
import model.Undo.*;

public class ChangerDataSynd implements Changer {
    public void Changer(Employee employee ,List<Syndicate> syndicatelist,  Action_Undo action_Undo){
        Undo sundo;
        Scanner input= new Scanner(System.in);
        if(!employee.getSyndicate()){

            System.out.println(Color.YELLOW+"Employee is not part of the union, would you like to add?\n1-Yes\n2-No"+Color.RESET);
            int select= input.nextInt();
            if(select==1){
                sundo= new SalveSyndicate( employee, 0);
                action_Undo.setUndo(sundo);
                AuxEmployee.AddSyndicate(employee, syndicatelist);
                int id= AuxEmployee.SeachSyndicate(syndicatelist, employee);
            }
       }
       else
       {
            System.out.println(Color.YELLOW+"Does the employee part of the union wish to remove?\n1-Yes\n2-No"+Color.RESET);
            int select= input.nextInt();
            if(select==1){
                int id= AuxEmployee.SeachSyndicate(syndicatelist, employee);
                sundo= new SalveSyndicate( employee, syndicatelist.get(id).getId());
                action_Undo.setUndo(sundo);
                AuxEmployee.RemoveSyndicate(employee, syndicatelist);
            }
            
       }
    }
    @Override
    public void execute(List<Employee> employeelist, List<Syndicate> syndicates, Action_Undo action_Undo, int idname){
       Changer(employeelist.get(idname), syndicates, action_Undo);
    }
    
}
