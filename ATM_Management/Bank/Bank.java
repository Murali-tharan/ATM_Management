package ATM_Management.Bank;

import ATM_Management.Bank.ATM.ATM;
import ATM_Management.Bank.Users.Account;
import ATM_Management.Bank.Users.AtmAdmin;
import ATM_Management.Bank.Users.BankAdmin;

import java.util.*;

public class Bank {

    private int option;
    private int id;
    private String password;

    private static ArrayList<Account> userDetails = new ArrayList<>();
    private static ArrayList<ATM> atmDetails = new ArrayList<>();
    private static ArrayList<BankAdmin> bankAdminDetails = new ArrayList<>();
    private static ArrayList<AtmAdmin> atmAdminDetails = new ArrayList<>();

    public Bank(int adminId, String location){
        ATM.setAtmId();
        atmDetails.add(new ATM(adminId,location));
    }

    public static ArrayList<Account> getUserDetails() {
        return userDetails;
    }

    public static ArrayList<ATM> getAtmDetails() {
        return atmDetails;
    }

    public static ArrayList<BankAdmin> getBankAdminDetails() {
        return bankAdminDetails;
    }

    public static ArrayList<AtmAdmin> getAtmAdminDetails() {
        return atmAdminDetails;
    }


    static Scanner sc = new Scanner(System.in);

    public void start(){
        boolean val = true;
        while (val) {
            System.out.print("\n1 -> Bank Admin\n2 -> Users \n3 -> Exit\nEnter option : ");
            option = sc.nextInt();
            switch (option) {
                case 1 -> {
                    System.out.print("Enter user id : ");
                    id = sc.nextInt();
                    System.out.print("Enter password : ");
                    password = sc.next();

                    if (BankAdminUtils.login(password, id, option)) {
                        bankAdminChoices();
                    }
                }

                case 2 -> userChoices();

                case 3 -> val = false;

                default -> System.out.print("Invalid option...!!!");
            }
        }
    }

    private void bankAdminChoices(){
        boolean val = true;
        while (val) {
            System.out.print("1 -> ATM admin creation\n2 -> Create Account\n3 -> Delete account\n4 -> Show Users account details\n5 -> Exit\nEnter option : ");
            option = sc.nextInt();
            switch (option) {
                case 1 -> BankAdminUtils.atmAdminCreation();
                case 2 -> BankAdminUtils.createAccount(new Account("Sirenjeevi",6374194200L,"qwerty@gmail.com",6666,"savings"));
                case 3 -> BankAdminUtils.deleteAccount();
                case 4 -> BankAdminUtils.showDetails(userDetails);
                case 5 -> val = false;
                default -> System.out.print("Invalid option...!!!");
            }

        }
    }

    private void userChoices(){
        boolean val = true;
        while (val) {
            System.out.print("1 -> Deposit\n2 -> Withdraw\n3 -> Check Balance\n4 -> Check Transaction History\n5 -> Change Atm card pin\n6 -> Generate Atm Card\n7 -> Exit\nEnter option : ");
            option = sc.nextInt();
            switch (option) {
                case 1 -> deposit(BankAdminUtils.verifyUser(),"");
                case 2 -> withdraw(BankAdminUtils.verifyUser(),"");
                case 3 -> checkBalance(BankAdminUtils.findAccount());
                case 4 -> checkHistory(BankAdminUtils.verifyUser());
                case 5 -> pinChange(BankAdminUtils.verifyUser());
                case 6 -> generateAtmCard();
                case 7 -> val = false;
                default -> System.out.println("Invalid option...!!!");
            }
        }
    }


    public static String findAdminPassword(int userId,int option){
        if(option == 1){
            for(BankAdmin id : bankAdminDetails){
                if(id.getId() == userId)
                    return id.getPassword();
            }
        }
        else {
            for(AtmAdmin id : atmAdminDetails){
                if (id.getId() == userId) {
                    return id.getPassword();
                }
            }
        }
        return "";
    }

    public static ATM findAtm(String location){
        for(ATM details : atmDetails){
            if(details.getLocation().equals(location))
                return details;
        }
        return null;
    }

    public static ATM findAdminsAtm(int id){
        for(ATM atmAdminDetails : atmDetails){
            if(atmAdminDetails.getAtmAdminId() == id)
                return atmAdminDetails;
        }
        return null;
    }

    public static void deposit(Account accountDetails, String location){

        if(accountDetails != null) {
            int amount;
            System.out.print("Enter amount to deposit : ");
            amount = sc.nextInt();
            if(amount < 1)
                System.out.println("Please enter positive values !!!");
            else {
                if(!location.isEmpty()){
                    ATM atm = findAtm(location);
                    if(!atm.isMaintenance()) {
                        accountDetails.setCurrentBalance(accountDetails.getCurrentBalance() + amount);
                        accountDetails.setTransactionHistory("Credit : " + amount);
                        System.out.println("Amount Credited");
                        atm.setAtmAmount(atm.getAtmAmount() + amount);
                        atm.getAtmHistory().add("Account Number : " + accountDetails.getAccountNumber() + " -> Credit : " + amount);
                    }
                    else {
                        System.out.println("Cannot deposit money 'ATM under maintenance'");
                    }
                }
                else {
                    accountDetails.setCurrentBalance(accountDetails.getCurrentBalance() + amount);
                    accountDetails.setTransactionHistory("Credit : " + amount);
                    System.out.println("Amount Credited");
                }
            }
        }
    }


    public static void withdraw(Account accountDetails,String location){

        if(accountDetails != null) {
            int amount;
            System.out.print("Enter amount to withdraw : ");
            amount = sc.nextInt();

            if(amount < 1)
                System.out.println("You must entered positive value");
            else if (accountDetails.getCurrentBalance() >= amount && accountDetails.getLimit() > 0) {

                if(!location.isEmpty()){
                    ATM atm = findAtm(location);
                    if(!atm.isMaintenance() && atm.getAtmAmount() >= amount){
                        accountDetails.setCurrentBalance(accountDetails.getCurrentBalance() - amount);
                        accountDetails.setTransactionHistory( "Debit : " + amount);
                        accountDetails.setLimit(accountDetails.getLimit()-1);

                        System.out.println("Amount Debited");
                        atm.setAtmAmount(atm.getAtmAmount() - amount);
                        atm.getAtmHistory().add("Account Number : "+ accountDetails.getAccountNumber()+" -> Debit : " + amount);
                    }
                    else {
                        System.out.println("Atm has insufficient balance (or) Atm under maintenance");
                    }
                }
                else {
                    accountDetails.setCurrentBalance(accountDetails.getCurrentBalance() - amount);
                    accountDetails.setTransactionHistory( "Debit : " + amount);
                    accountDetails.setLimit(accountDetails.getLimit()-1);

                    System.out.println("Amount Debited");
                }

            } else
                System.out.println("Your account has insufficient balance");
        }
    }

    public static void checkBalance(Account accountDetails){
        if(accountDetails != null)
            System.out.println("Your Balance : "+accountDetails.getCurrentBalance());
    }

    public static void checkHistory(Account accountDetails){

        if(accountDetails != null){
            for(String history : accountDetails.getTransactionHistory()){
                System.out.println(history);
            }
        }
        else
            System.out.println("Can't find account");
    }

    public static void pinChange(Account accountDetails){

        if(accountDetails != null){
            System.out.println("Enter your old pin number : ");
            int oldPin,newPin,verifyPin;
            oldPin = sc.nextInt();
            if(oldPin == accountDetails.getPin()){
                System.out.println("Success");
                System.out.println("Enter new Pin");
                newPin = sc.nextInt();
                System.out.println("Re-enter the new pin");
                verifyPin = sc.nextInt();

                if(newPin == verifyPin){
                    System.out.println("Successfully changed the Pin");
                    accountDetails.setPin(newPin);
                }
                else
                    System.out.println("Your new pin is not matched");
            }
            else
                System.out.println("You entered wrong pin try again!!");
        }
    }

    public void generateAtmCard(){
        Account accountDetails = BankAdminUtils.verifyUser();
        if(accountDetails != null){
            BankAdminUtils.generateAtmCard(accountDetails);
        }
    }
}
