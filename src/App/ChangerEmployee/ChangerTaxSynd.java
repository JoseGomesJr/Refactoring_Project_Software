package App.ChangerEmployee;

import java.util.*;
import java.util.Scanner;

import App.*;
import model.Employee.Employee;
import model.Syndicate.Syndicate;
import model.Undo.*;

public class ChangerTaxSynd implements Changer {
    @Override
    public void execute(List<Employee> employeelist, List<Syndicate> syndicates, Action_Undo action_Undo, int idname){
        Undo sundo;
        Scanner input= new Scanner(System.in);
        System.out.println("Inform the new union fee");
        Double taxSyndicate= input.nextDouble();
        sundo= new TaxSynd( employeelist.get(idname).getTaxSyndicate() , employeelist.get(idname));
        action_Undo.setUndo(sundo);
        //rUndo.SalveTaxservi(Undo.CHANGERTAXSYND, employeelist.get(idname).getTaxSyndicate() , employeelist.get(idname));
        employeelist.get(idname).setTaxSyndicate(taxSyndicate);
    }
    
}
