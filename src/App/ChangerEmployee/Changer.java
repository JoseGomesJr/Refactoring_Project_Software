package App.ChangerEmployee;

import java.util.*;

import model.Employee.Employee;
import model.Syndicate.Syndicate;
import model.Undo.*;

public interface Changer {
    public void execute(List<Employee> employeelist, List<Syndicate> syndicates, Action_Undo action_Undo, int idname);
    
}
