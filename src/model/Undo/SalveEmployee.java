package model.Undo;

import java.util.List;

import App.Employee;
import App.Syndicate;

public class SalveEmployee extends Undo {
    protected History hist;
    public App.AuxEmployee AuxEmployee;
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
        if(hist.soption==1){ 
            employees.remove(hist.semployee);
            if(hist.semployee.getSyndicate()==true){
                AuxEmployee.RemoveSyndicate(hist.semployee, syndicatelist);
            }
            
        }
        else{
            employees.add(hist.semployee);
            if(hist.semployee.getSyndicate()){
                AuxEmployee.AddSyndicate(hist.semployee, syndicatelist, hist.idsyn);
            }
        }
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
