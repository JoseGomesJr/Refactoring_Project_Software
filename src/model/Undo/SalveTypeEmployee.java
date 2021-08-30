package model.Undo;

import java.util.List;

import App.Employee;
import App.Syndicate;

public class SalveTypeEmployee extends Undo{
    protected History hist;
    public SalveTypeEmployee(int option, Employee nEmployee){
        hist= new History();
        hist.semployee= nEmployee;
        hist.soption= option;
        
    }
    public void Undo_Redo(List<Employee> employees, List<Syndicate> syndicates){
        int posi=0;
        for(Employee employee: employees){
            if(hist.semployee.getName().equals(employee.getName()) && hist.semployee.getId()==employee.getId()){
                employees.remove(posi);
                employees.add(posi, hist.semployee);
            }
            posi++;
        }
        posi=0;
        if(hist.semployee.getSyndicate()){
            for(Syndicate syndicate: syndicates){
                if(hist.semployee.getName().equals(syndicate.getUnionlist().getName()) && 
                hist.semployee.getId()==syndicate.getUnionlist().getId()){

                    syndicates.get(posi).setUnionlist(hist.semployee);
                }
                posi++;
            }
        }
        
    
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
