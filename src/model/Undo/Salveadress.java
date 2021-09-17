package model.Undo;

import java.util.List;

import App.utils.Adress;
import model.Employee.Employee;
import model.Syndicate.Syndicate;

public class Salveadress implements Undo {
    protected History hist;
    public Salveadress( Adress adress, Employee employee){
        hist= new History();
        
        hist.adress= adress;
        hist.semployee= employee;
        
    }
    @Override
    public Undo execute(Undo reUndo, List<Employee> employees, List<Syndicate> syndicates){
        Undo sredo=this.auxRedo.redadress(reUndo, hist);
        hist.semployee.setAdress(hist.adress);
        return sredo;
    }
    @Override
    public void execute( List<Employee> employees, List<Syndicate> syndicates){
        hist.semployee.setAdress(hist.adress);
    }
}
