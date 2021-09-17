package model.Undo;

import java.util.List;

import model.Employee.Employee;
import model.Syndicate.Syndicate;

public class SalveTaxservi implements Undo {
    History hist;
    public SalveTaxservi( Double tax, Employee employee){
        hist= new History();
        hist.semployee= employee;
        hist.spay= tax;
        
    }
    public void Undo_Redo(){
        hist.semployee.setTaxService(hist.spay);
        
    }
    @Override
    public Undo execute(Undo reUndo, List<Employee> employees, List<Syndicate> syndicates){
        Undo redo =this.auxRedo.redtax(reUndo, hist);
        this.Undo_Redo();
        return redo;
    }
    @Override
    public void execute(List<Employee> employees, List<Syndicate> syndicates){
        this.Undo_Redo();
    }
}
