package ATM_Management.Bank.Users;

public class BankAdmin extends Users {
    private int id;
    private String password;

    public BankAdmin(int id, String name, long mobileNumber, String password, String email){
        this.id = id;
        setName(name);
        setMobileNumber(mobileNumber);
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
