package ATM_Management.Bank.ATM;

import ATM_Management.Bank.Users.Account;
import ATM_Management.Bank.Bank;

import java.util.Scanner;

public class AtmUtils{

    private int option;
    private int id;
    private String password;
    private long cardNumber;
    private int inputPin;
    private String location;

    Scanner sc = new Scanner(System.in);

    public void start(){
        System.out.print("Enter Atm location : ");
        location = sc.next();
        boolean val = true;
        while (val) {
            System.out.print("\n1 -> ATM Admin\n2 -> Users\n3 -> Exit\nEnter option : ");
            option = sc.nextInt();
            switch (option) {
                case 1 -> {
                    System.out.print("Enter user id : ");
                    id = sc.nextInt();
                    System.out.print("Enter password : ");
                    password = sc.next();

                    if (AtmAdminUtils.login(password, id, option + 1)) {
                        atmAdminChoices();
                    }
                }

                case 2 -> {
                    System.out.print("Enter ATM card number : ");
                    cardNumber = sc.nextLong();
                    System.out.print("Enter pin number : ");
                    inputPin = sc.nextInt();

                    if (checkPin(cardNumber, inputPin)) {
                        userChoices();
                    }
                }

                case 3 -> val = false;

                default -> System.out.println("Invalid option...!!!");
            }
        }
    }

    public void atmAdminChoices(){
        boolean val = true;
        while (val) {
            System.out.print("1 -> Check ATM Balance\n2 -> Maintenance\n3 -> Deposit Amount to ATM\n4 -> ATM transaction History\n5 -> Exit\nEnter option : ");
            option = sc.nextInt();
            switch (option) {
                case 1 -> AtmAdminUtils.checkAtmBalance(Bank.findAtm(location));
                case 2 -> AtmAdminUtils.maintenance(Bank.findAtm(location));
                case 3 -> AtmAdminUtils.amountDepositToAtm(Bank.findAtm(location));
                case 4 -> AtmAdminUtils.showAtmHistory(id);
                case 5 -> val = false;
                default -> System.out.print("Invalid option...!!!");
            }
        }
    }

    public void userChoices(){
        boolean val = true;
        while (val) {
            System.out.print("1 -> Deposit\n2 -> Withdraw\n3 -> Check Balance\n4 -> Check Transaction History\n5 -> Change Atm card pin\n6 -> Exit\nEnter option : ");
            option = sc.nextInt();
            switch (option) {
                case 1 -> Bank.deposit(findAccount(cardNumber),location);
                case 2 -> Bank.withdraw(findAccount(cardNumber),location);
                case 3 -> Bank.checkBalance(findAccount(cardNumber));
                case 4 -> Bank.checkHistory(findAccount(cardNumber));
                case 5 -> Bank.pinChange(findAccount(cardNumber));
                case 6 -> val = false;
                default -> System.out.println("Invalid option...!!!");
            }
        }
    }

    public static Account findAccount(long cardNumber){
        for(Account accountDetails : Bank.getUserDetails()){
            if(accountDetails.getCardNumber() == cardNumber) {
                return accountDetails;
            }
        }
        return null;
    }

    public static boolean checkPin(long cardNumber, int pin){
        for(Account accountDetails : Bank.getUserDetails()){
            if(accountDetails.getCardNumber() == cardNumber && accountDetails.getPin() == pin) {
                return true;
            }
        }
        return false;
    }

}
