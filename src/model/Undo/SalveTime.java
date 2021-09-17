package model.Undo;

import java.time.LocalTime;
import java.util.Date;
import java.util.List;

import App.AuxEmployee;
import model.Employee.Employee;
import model.Syndicate.Syndicate;
public class SalveTime implements Undo {
    History hist;
    public SalveTime(Double pay, Employee employee, int aux){
        hist= new History();
        hist.sobj=aux;
        hist.semployee=employee;
        
        if(aux == 2){
            hist.spay= pay;
        }
        
    }
    public SalveTime(Double pay, Employee employee, int aux, Date date, LocalTime datetime){
        hist= new History();
        hist.sobj=aux;
        hist.semployee=employee;
        
        hist.datehours= date;
        hist.datetimehours= datetime;
        if(aux == 2){
            hist.spay= pay;
        }
        
    }
    public void Undo_(){
        AuxEmployee.AddTimecard(hist.sobj, hist.semployee, hist.spay);
      
    }
    public void Redo_(){
        AuxEmployee.AddTimecard(hist.sobj, hist.semployee, hist.datehours, hist.datetimehours, hist.spay);
    }
    @Override
    public Undo execute(Undo reUndo, List<Employee> employees, List<Syndicate> syndicates){
        Undo redo =this.auxRedo.redotime(reUndo, hist);
        this.Undo_();
        return redo;
    }
    @Override
    public void execute(List<Employee> employees, List<Syndicate> syndicates){
        this.Redo_();
    }
}
