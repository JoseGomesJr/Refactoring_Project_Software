package model.Undo;

import java.util.*;

import model.Employee.Employee;
import model.Syndicate.Syndicate;
public interface Undo {
    AuxRedo auxRedo= new AuxRedo();
    public void execute(List<Employee> employees, List<Syndicate> syndicates);
    public Undo execute(Undo reUndo, List<Employee> employees, List<Syndicate> syndicates);
}
