package App.ChangerEmployee;
import java.util.*;

import App.*;
import model.Undo.*;

public class ChangerPay implements Changer {
    @Override
    public void execute(List<Employee> employeelist, List<Syndicate> syndicates, Action_Undo action_Undo, int idname){
        Undo sundo;
        sundo= new SalvePay(employeelist.get(idname).getPayment(), employeelist.get(idname));
        action_Undo.setUndo(sundo);
        employeelist.get(idname).setPayment(PayFunction.AddMethod());
    }
}
