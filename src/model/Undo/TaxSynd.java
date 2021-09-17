package model.Undo;

import java.util.List;

import model.Employee.Employee;
import model.Syndicate.Syndicate;

public class TaxSynd implements Undo {
    History hist;
    public TaxSynd( Double tax, Employee employee){
        hist= new History();
        hist.semployee= employee;
        hist.spay= tax;
        
    }
    public void Undo_Redo(){
        hist.semployee.setTaxSyndicate(hist.taxsynd);
    }
    @Override
    public Undo execute(Undo reUndo, List<Employee> employees, List<Syndicate> syndicates){
        Undo redo=this.auxRedo.redtaxsyn(reUndo, hist);
        this.Undo_Redo();
        return redo;
    }
    @Override
    public void execute(List<Employee> employees, List<Syndicate> syndicates){
        this.Undo_Redo();
    }
    
}
