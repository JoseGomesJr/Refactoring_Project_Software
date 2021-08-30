package model.Undo;

import java.util.List;

import App.Employee;
import App.PaymentMethod;
import App.Syndicate;

public class SalvePay extends Undo {
    protected History hist;
    public SalvePay(int option, PaymentMethod pay, Employee employee){
        hist= new History();
        hist.soption=option;
        hist.semployee= employee;
        hist.spayment= pay;
        
    }
    public void Undo_Redo(){
        hist.semployee.setPayment(hist.spayment);
        
    }
    @Override
    public Undo execute(Undo reUndo, List<Employee> employees, List<Syndicate> syndicates){
        Undo redo =this.auxRedo.redpay(reUndo, hist);
        this.Undo_Redo();
        return redo;
    }
    @Override
    public void execute(List<Employee> employees, List<Syndicate> syndicates){
        this.Undo_Redo();
    }
}
