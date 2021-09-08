package model.Undo;

import java.util.*;

import App.Employee;
import App.Syndicate;
public interface Undo {
    AuxRedo auxRedo= new AuxRedo();
    public void execute(List<Employee> employees, List<Syndicate> syndicates);
    public Undo execute(Undo reUndo, List<Employee> employees, List<Syndicate> syndicates);
}
