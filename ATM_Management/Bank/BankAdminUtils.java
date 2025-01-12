package ATM_Management.Bank;

import ATM_Management.Bank.Users.Account;
import ATM_Management.Bank.Users.AtmAdmin;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class BankAdminUtils {

    static Scanner sc = new Scanner(System.in);;

    public static boolean login(String inputPin, int userId, int option){
        return Bank.findAdminPassword(userId,option).equals(inputPin);
    }

    public static Account verifyUser(){
        System.out.print("Enter Aadhaar number : ");
        long aadhaarNumber = sc.nextInt();
        Account accountDetails = findAccount();
        try {
            if (accountDetails.getAadhaarNumber() == aadhaarNumber)
                return accountDetails;
        }catch (NullPointerException e) {
            System.out.println("You entered wrong details");
        }
        return null;
    }

    public static Account findAccount(){
        System.out.print("Enter Account number : ");
        long accountNumber = sc.nextLong();
        for(Account accountDetails : Bank.getUserDetails()){
            if(accountDetails.getAccountNumber() == accountNumber) {
                return accountDetails;
            }
        }
        return null;
    }

    public static void atmAdminCreation(){
        Bank.getAtmAdminDetails().add(new AtmAdmin(100, "Atm Admin1",123456789L, "salem","100","atmadmin1@gmail.com"));

        Bank.getAtmAdminDetails().add(new AtmAdmin(101, "Atm Admin2",123456789L, "erode","101","atmadmin2@gmail.com"));
    }

    public static void createAccount(Account acc){
        boolean flag = false;
        for(Account accountDetails : Bank.getUserDetails()){
            if(accountDetails.getAadhaarNumber() == acc.getAadhaarNumber()) {
                flag = true;
                System.out.println("Already account existing..!!!");
            }
        }
        if (!flag) {
            acc.setAccountId();
            acc.setAccountNumber(acc.getAccountId());
            acc.setCardNumber(-1);
            acc.setPin(-1);
            acc.setMinBalance(500);
            acc.setCurrentBalance(500);

            Bank.getUserDetails().add(acc);
            System.out.println(
                    "--- Newly Created Account details ---\nName : " + acc.name + "\nAccount Number : " + acc.getAccountNumber()
                            + "\nMinimum Balance : " + 500 + "\nCurrent Balance : " + 500 + "\nAadhaar Number : " + acc.getAadhaarNumber() + "\nMobile Number : " + acc.mobileNumber
                            + "\nAccount Type Number : " + acc.getAccountType()
            );
        }
    }

    public static void deleteAccount(){
        Bank.getUserDetails().remove(findAccount());
        System.out.println("Account successfully deleted!!..");
    }

    public static void generateAtmCard(Account accountDetails){
        if(accountDetails == null)
            System.out.println("Please enter valid account number, try again!!!");
        else {
            if (accountDetails.getCardNumber() == -1 ) {
                accountDetails.setCardId();
                accountDetails.setCardNumber(accountDetails.getCardId());
                Random random = new Random();
                int pin = (1000+random.nextInt(9000));
                accountDetails.setPin(pin);
                System.out.println("Your atm card number : "+accountDetails.getCardNumber()+"\nYour Pin number : "+ pin);
            } else {
                System.out.println("Atm card already existing!!!");
            }
        }
    }

    public static void showDetails(ArrayList<Account> userDetails){
        for(Account accountDetails : userDetails){
            System.out.println("-------------------------------\nName : " + accountDetails.getName() + "\nAccount Number : " + accountDetails.getAccountNumber() + "\nAtm Card Number : " + accountDetails.getCardNumber() + "\nPin : " + accountDetails.getPin()
                    + "\nMinimum Balance : " + 500 + "\nCurrent Balance : " + 500 + "\nAadhaar Number : " + accountDetails.getAadhaarNumber() + "\nMobile Number : " + accountDetails.mobileNumber
                    + "\nAccount Type Number : " + accountDetails.getAccountType()+"\n---------------------------\n");
        }
    }
}
