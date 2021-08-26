public class PaymentMethod {
    private String method;
    private PayBanck bank;
    private PayDay pDay;
    private int optionschedule;
    public PaymentMethod(String method){
        this.method= method;
    }
    public PaymentMethod(String method, int account, String bank, String typeaccount)
    {
        this.method= method;
        this.bank= new PayBanck(account, typeaccount, bank);
    }
    public void setMethod(String method){
        this.method = method;
    }
    public void setBank(int account, String bank, String typeaccount) {
        this.bank.setAccount(account);
        this.bank.setBank(bank);
        this.bank.setTypeaccount(typeaccount);
    }
    public String InfoPay(){
        return "Selected methodo: "+method;
    }
    public String Banck(){
        return this.bank.getBank();
    }
    public void setpDay(PayDay pDay) {
        this.pDay = pDay;
    }
    public PayDay getpDay() {
        return pDay;
    }
    public int getOptionschedule() {
        return optionschedule;
    }
    public void setOptionschedule(int optionschedule) {
        this.optionschedule = optionschedule;
    }
}
