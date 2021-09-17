package model.Undo;

import java.util.List;

import App.AuxEmployee;
import model.Employee.Employee;
import model.Syndicate.Syndicate;

public class SalveEmployee implements Undo {
    protected History hist;
    public SalveEmployee(int option, Employee nEmployee){
        hist= new History();
        hist.semployee= nEmployee;
        hist.soption= option;
    }
    public SalveEmployee(int option, Employee nEmployee, int idsyn){
        hist= new History();
        hist.idsyn= idsyn;
        hist.semployee= nEmployee;
        hist.soption= option;
    }
    public void Undo_Redo(List<Syndicate> syndicatelist, List<Employee> employees){
        AuxEmployee.reAdd(hist.soption, hist.semployee, syndicatelist, employees, hist.idsyn);
    }
    @Override
    public Undo execute(Undo reUndo, List<Employee> employees, List<Syndicate> syndicates) {
        Undo redo =this.auxRedo.redoEmployee(reUndo, hist);
        this.Undo_Redo(syndicates, employees);
        return redo;
    }
    @Override
    public void execute( List<Employee> employees, List<Syndicate> syndicates) {
        this.Undo_Redo(syndicates, employees);
    }
}
