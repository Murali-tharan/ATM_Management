package ATM_Management.Bank.Users;

public class AtmAdmin extends Users {
    private int id;
    private String password;
    private String location;


    public AtmAdmin(int id, String name, long mobileNumber,String location, String password, String email){
        this.id = id;
        setName(name);
        setMobileNumber(mobileNumber);
        this.location = location;
        this.password = password;
        setEmail(email);
    }

    public int getId() {
        return id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}

