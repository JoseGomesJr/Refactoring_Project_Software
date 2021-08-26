public class Syndicate {
    private Employee unionlist;
    private int id;
    Syndicate(Employee unionlist, int id){
        this.unionlist= unionlist;
        this.id= id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public void setUnionlist(Employee unionlist) {
        this.unionlist = unionlist;
    }
    public int getId(){
        return id;
    }
    public Employee getUnionlist() {
        return unionlist;
    }
    public String printInfo(){
        return "Name unionlis: "+unionlist.getName()+"\nID unionlist: "+id;
    }
}
