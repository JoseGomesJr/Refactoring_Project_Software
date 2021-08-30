package model.Undo;
import java.util.List;

import App.AuxEmployee;
import App.Employee;
import App.Syndicate;
public class SalveSyndicate extends Undo {
    protected History hist;
    public AuxEmployee AuxEmployee;
    public SalveSyndicate(int option, Employee nEmployee, int idsyn){
        hist= new History();
        hist.semployee= nEmployee;
        hist.soption= option;
        hist.idsyn= idsyn;
        
    }
    public void Undo_Redo(List<Syndicate> syndicatelist){
        if(hist.semployee.getSyndicate()==true){
            AuxEmployee.RemoveSyndicate(hist.semployee, syndicatelist);
        }
        else{
            AuxEmployee.AddSyndicate(hist.semployee, syndicatelist, hist.idsyn);
        }
    }
    @Override
    public Undo execute(Undo reUndo, List<Employee> employees, List<Syndicate> syndicates) {
        
        Undo sredo=this.auxRedo.redoEmployeesynd(reUndo, hist, syndicates);
        this.Undo_Redo( syndicates);
        return sredo;
    }
    @Override
    public void execute( List<Employee> employees, List<Syndicate> syndicates) {
        this.Undo_Redo( syndicates);
    }
}