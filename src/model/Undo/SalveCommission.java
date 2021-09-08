package model.Undo;

import java.util.*;
import App.Employee;
import App.Syndicate;
import App.Commissioned;
public class SalveCommission implements Undo{
    protected History hist;
    public SalveCommission( Double comission, List<String> date, Employee employee){
        hist= new History();
        
        hist.commission= comission;
        hist.date= date;
        hist.semployee= employee;
        
    }
    public void Undo_Redo(){
        if(hist.semployee.typeEmployee().equals("Commssioned")){
            ((Commissioned) hist.semployee).setComissionTotal(hist.commission);
            ((Commissioned) hist.semployee).removdate(hist.sname);
            
        }else{
            System.out.println("Something went wrong!");
        }
        
    }
    @Override
    public Undo execute(Undo reUndo, List<Employee> employees, List<Syndicate> syndicates){
        Undo redo =this.auxRedo.redcommssion(reUndo, hist);
        this.Undo_Redo();
        return redo;
    }
    @Override
    public void execute(List<Employee> employees, List<Syndicate> syndicates){
        this.Undo_Redo();
    }
}
