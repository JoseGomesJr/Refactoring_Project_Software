package App.ChangerEmployee;

import java.util.*;
import java.util.Scanner;

import App.*;
import model.Undo.*;

public class ChangerName implements Changer {
    ChangerName(){

    }
    public void ChangeName(Employee employee){
        Scanner input= new Scanner(System.in);
        System.out.println("Write the new name:");
        String name= input.nextLine();
        employee.setName(name);
    }
    @Override
    public void execute(List<Employee> employeelist, List<Syndicate> syndicates, Action_Undo action_Undo, int idname){
        Undo sundo;
        sundo= new SalveName( employeelist.get(idname).getName(), employeelist.get(idname));
        action_Undo.setUndo(sundo);
        ChangeName(employeelist.get(idname));
    }
    
}
