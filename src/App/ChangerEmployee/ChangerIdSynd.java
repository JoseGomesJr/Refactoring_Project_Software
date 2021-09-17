package App.ChangerEmployee;

import java.util.*;
import java.util.Scanner;

import App.*;
import App.utils.Color;
import model.Employee.Employee;
import model.Syndicate.Syndicate;
import model.Undo.*;

public class ChangerIdSynd implements Changer {
    public void Changer(Employee employee , List<Syndicate> syndicatelist, Action_Undo action_Undo){
        Scanner input= new Scanner(System.in);
        int id= AuxEmployee.SeachSyndicate(syndicatelist, employee);
        if(id!=-1){
            System.out.println("Enter the new ID");
            int newid= input.nextInt();
            Undo sundo = new SalveIDSyn( syndicatelist.get(id).getId(), id);
            action_Undo.setUndo(sundo);
            syndicatelist.get(id).setId(newid);
        }
        else{
            System.out.println(Color.RED+"The data entered is not associated with any unionist"+Color.RESET);
        }
    }

    @Override
    public void execute(List<Employee> employeelist, List<Syndicate> syndicates, Action_Undo action_Undo, int idname){
        Undo sundo;
        Changer(employeelist.get(idname), syndicates, action_Undo);
    }
}
