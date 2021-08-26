public class PayBanck {
    private int account;
    private String typeaccount;
    private String bank;

    public PayBanck(int account, String typeaccount, String bank){
        this.account= account;
        this.typeaccount= typeaccount;
        this.bank= bank;
    }
    public void setTypeaccount(String typeaccount) {
        this.typeaccount = typeaccount;
    }
    public void setBank(String bank) {
        this.bank = bank;
    }
    public void setAccount(int account) {
        this.account = account;
    }
    public String getBank(){
        return "Number account:"+account+"\nType account"+typeaccount+"\nBank"+ bank+"\n";
    }
}
