package model.Undo;

import java.util.List;

import App.Employee;
import App.Syndicate;

public class SalveName extends Undo {
    protected History hist;
    public SalveName(int option, String name, Employee employee){
        hist= new History();
        hist.soption= option;
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
