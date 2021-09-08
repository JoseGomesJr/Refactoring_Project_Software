package App.ChangerEmployee;

import java.util.*;
import java.util.Scanner;

import App.*;
import model.Undo.*;
public class ChangerAdress implements Changer{
    public void ChangeAdress(Employee employee){
        Scanner input= new Scanner(System.in);
        System.out.println("Write the new address:");
        String adress= input.nextLine();
        employee.setAdress(adress);
    }

    @Override
    public void execute(List<Employee> employeelist, List<Syndicate> syndicates, Action_Undo action_Undo, int idname){
        Undo sundo;
        sundo= new Salveadress( employeelist.get(idname).getName(), employeelist.get(idname));
        action_Undo.setUndo(sundo);
        ChangeAdress(employeelist.get(idname));
    }
}
