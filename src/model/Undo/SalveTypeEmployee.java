package model.Undo;

import java.util.List;

import App.AuxEmployee;
import model.Employee.Employee;
import model.Syndicate.Syndicate;

public class SalveTypeEmployee implements Undo{
    protected History hist;
    public SalveTypeEmployee( Employee nEmployee){
        hist= new History();
        hist.semployee= nEmployee;
        
    }
    public void Undo_Redo(List<Employee> employees, List<Syndicate> syndicates){
        AuxEmployee.reAdd(employees, syndicates, hist.semployee);
        
    }
    @Override
    public Undo execute(Undo reUndo, List<Employee> employees, List<Syndicate> syndicates) {
        Undo sredo=this.auxRedo.redtype(reUndo, employees, hist);
        this.Undo_Redo(employees, syndicates);
        return sredo;
    }
    @Override
    public void execute( List<Employee> employees, List<Syndicate> syndicates) {
        this.Undo_Redo(employees, syndicates);
    }
}
