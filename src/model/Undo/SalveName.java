package model.Undo;

import java.util.List;

import model.Employee.Employee;
import model.Syndicate.Syndicate;

public class SalveName implements Undo {
    protected History hist;
    public SalveName(String name, Employee employee){
        hist= new History();
        hist.sname= name;
        hist.semployee= employee;
        
    }
    @Override
    public Undo execute(Undo reUndo, List<Employee> employees, List<Syndicate> syndicates){
        Undo sredo=this.auxRedo.redname(reUndo, hist);
        hist.semployee.setName(hist.sname);
        return sredo;
    }
    @Override
    public void execute( List<Employee> employees, List<Syndicate> syndicates){
        hist.semployee.setName(hist.sname);
    }
}
