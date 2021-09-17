package App.ChangerEmployee;

import java.util.*;
import App.AuxEmployee;
import App.utils.Color;
import model.Employee.Employee;
import model.Syndicate.Syndicate;
import model.Undo.*;

public class ActionChanger {
    protected Changer actions;
    public void setActions(Changer actions) {
        this.actions = actions;
    }
    public void Acition(List<Employee> employeelist, List<Syndicate> syndicates, Action_Undo action_Undo){
        System.out.println("Select the employee you want to change");
        int idname= AuxEmployee.SearchEmployeeList(employeelist);
        if(idname==-1){
            System.out.println(Color.RED+"The data entered is not associated with any employee"+Color.RESET);
            return;
        }
        actions.execute(employeelist, syndicates, action_Undo, idname);
    }
}
