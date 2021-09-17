package model.Undo;

import java.util.*;

import App.utils.Adress;
import model.Employee.Employee;
import model.Pays.PaymentMethod;

import java.time.LocalTime;
public class History {
    protected Employee semployee;
    protected Adress adress;
    protected String sname;
    protected int sobj;
    protected int soption;
    protected int idsyn;
    protected Double spay;
    protected Double commission;
    protected PaymentMethod spayment;
    protected Double taxsynd;
    protected List<Double> comission;
    protected List<Double> taxservice;
    protected List<Double> payhour;
    protected List<String> date;
    protected LocalTime datetimehours;
    protected Date datehours;
    
}
