import java.util.*;
public class AuxRedo {
    public void redoEmployee(Undo reUndo, History hist){
        reUndo.Salve(hist.soption, hist.semployee);
    }
    public void redotime(Undo reUndo, History hist){
        if(hist.sobj==1){
            reUndo.Salvetime(hist.soption, 0d, hist.semployee, 1, ((Hourly) hist.semployee).getCard().getDate(), 
            ((Hourly) hist.semployee).getCard().getEntraDate());
        }
        else{
            reUndo.Salvetime(hist.soption, ((Hourly) hist.semployee).getPay(), hist.semployee, 2, 
            ((Hourly) hist.semployee).getCard().getExitdate(), ((Hourly) hist.semployee).getCard().getExiDate());
        }
    }
    public void redcommssion(Undo reUndo, History hist){

        reUndo.Salvecommission(Undo.COMISSION, ((Commissioned) hist.semployee).getComissionTotal(), 
        ((Commissioned) hist.semployee).getDates(), hist.semployee);

    }
    public void redtax(Undo reUndo, History hist){
        reUndo.SalveTaxservi(Undo.TAXSERVICE, hist.semployee.getTaxService(), hist.semployee); 
    }
    public void redtaxsyn(Undo reUndo, History hist){
        reUndo.SalveTaxservi(Undo.CHANGERTAXSYND, hist.semployee.getTaxSyndicate(), hist.semployee);
    }
    public void redname(Undo reUndo, History hist)
    {
        reUndo.Salve(Undo.CHANGERNAME, hist.semployee.getName(), hist.semployee);
    }
    public void redadress(Undo reUndo, History hist)
    {
        reUndo.Salve(Undo.CHANGERADRESS, hist.semployee.getName(), hist.semployee);
    }
    public void redtype(Undo reUndo, List<Employee> employees, History hist){
        for(Employee employee: employees){
            if(hist.semployee.getName().equals(employee.getName()) && hist.semployee.getId()==employee.getId()){
                reUndo.Salve(hist.soption, employee);
                return;
            }
        }
    }
    public void redpay(Undo reUndo, History hist){
        reUndo.Salvepay(Undo.CHANGERPAY, hist.semployee.getPayment(), hist.semployee);
    }
    public void redoEmployeesynd(Undo reUndo, History hist, List<Syndicate> syndicatelist){
        
        for(Syndicate syndicate: syndicatelist){
            if(hist.semployee.getName().equals(syndicate.getUnionlist().getName()) && 
            hist.semployee.getId()==syndicate.getUnionlist().getId()){

                reUndo.Salvesyndi(hist.soption, hist.semployee, syndicate.getId());
                return;
            }
            
        }
        
    }
    public void rediddyn(Undo reUndo, History hist, List<Syndicate> syndicatelist){
        
        reUndo.Salveidsyn(Undo.CHANGERIDSYN, syndicatelist.get(hist.sobj).getId(), hist.sobj);
    }
}
