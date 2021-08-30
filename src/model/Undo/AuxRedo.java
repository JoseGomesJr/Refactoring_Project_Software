package model.Undo;
import App.Employee;

import App.Hourly;
import App.Commissioned;
import App.Syndicate;
import java.util.*;
public class AuxRedo {
    public Undo redoEmployee(Undo reUndo, History hist){
        if(hist.soption==1){
            return reUndo= new SalveEmployee(2, hist.semployee);
        }
        else{
            return reUndo= new SalveEmployee(1, hist.semployee);
        }
    }
    public Undo redotime(Undo reUndo, History hist){
        if(hist.sobj==1){
            return reUndo= new SalveTime(hist.soption, 0d, hist.semployee, 1, ((Hourly) hist.semployee).getCard().getDate(), 
            ((Hourly) hist.semployee).getCard().getEntraDate());
        }
        else{
            return reUndo= new SalveTime(hist.soption, ((Hourly) hist.semployee).getPay(), hist.semployee, 2, 
            ((Hourly) hist.semployee).getCard().getExitdate(), ((Hourly) hist.semployee).getCard().getExiDate());
        }
    }
    public Undo redcommssion(Undo reUndo, History hist){
        return reUndo= new SalveCommission(Undo.COMISSION, ((Commissioned) hist.semployee).getComissionTotal(), 
        ((Commissioned) hist.semployee).getDates(), hist.semployee);

    }
    public Undo redtax(Undo reUndo, History hist){
        return reUndo= new SalveTaxservi(Undo.TAXSERVICE, hist.semployee.getTaxService(), hist.semployee);  
    }
    public Undo redtaxsyn(Undo reUndo, History hist){
        return reUndo= new TaxSynd(Undo.CHANGERTAXSYND, hist.semployee.getTaxSyndicate(), hist.semployee);
    }
    public Undo redname(Undo reUndo, History hist)
    {
        return reUndo= new SalveName(Undo.CHANGERNAME, hist.semployee.getName(), hist.semployee);
        
    }
    public Undo redadress(Undo reUndo, History hist)
    {
        return reUndo= new Salveadress(Undo.CHANGERADRESS, hist.semployee.getName(), hist.semployee);
    }
    public Undo redtype(Undo reUndo, List<Employee> employees, History hist){
        for(Employee employee: employees){
            if(hist.semployee.getName().equals(employee.getName()) && hist.semployee.getId()==employee.getId()){
                return reUndo= new SalveTypeEmployee(hist.soption, employee);
                
            }
        }
        return null;
    }
    public Undo redpay(Undo reUndo, History hist){
        return reUndo= new SalvePay(Undo.CHANGERPAY, hist.semployee.getPayment(), hist.semployee);
    }
    public Undo redoEmployeesynd(Undo reUndo, History hist, List<Syndicate> syndicatelist){
        
        for(Syndicate syndicate: syndicatelist){
            if(hist.semployee.getName().equals(syndicate.getUnionlist().getName()) && 
            hist.semployee.getId()==syndicate.getUnionlist().getId()){
                return reUndo= new SalveSyndicate(hist.soption, hist.semployee, syndicate.getId());
                
            }
            
        }
        return null;
        
    }
    public Undo rediddyn(Undo reUndo, History hist, List<Syndicate> syndicatelist){
        return reUndo= new SalveIDSyn(Undo.CHANGERIDSYN, syndicatelist.get(hist.sobj).getId(), hist.sobj);
    }
}
