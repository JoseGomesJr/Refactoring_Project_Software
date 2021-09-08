package model.Undo;

import java.util.List;

import App.Employee;
import App.Syndicate;

public class Salveadress implements Undo {
    protected History hist;
    public Salveadress( String name, Employee employee){
        hist= new History();
        
        hist.sname= name;
        hist.semployee= employee;
        
    }
    @Override
    public Undo execute(Undo reUndo, List<Employee> employees, List<Syndicate> syndicates){
        Undo sredo=this.auxRedo.redadress(reUndo, hist);
        hist.semployee.setAdress(hist.sname);
        return sredo;
    }
    @Override
    public void execute( List<Employee> employees, List<Syndicate> syndicates){
        hist.semployee.setAdress(hist.sname);
    }
}
