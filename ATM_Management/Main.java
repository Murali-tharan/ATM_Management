package ATM_Management;

import ATM_Management.Bank.Users.AtmAdmin;
import ATM_Management.Bank.ATM.AtmUtils;
import ATM_Management.Bank.Users.Account;
import ATM_Management.Bank.Bank;
import ATM_Management.Bank.Users.BankAdmin;
import ATM_Management.Bank.BankAdminUtils;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        int option;
        Scanner sc = new Scanner(System.in);

        BankAdminUtils.createAccount(new Account("Murali",9345479547L,"murali@gmail.com",1234,"savings"));
        BankAdminUtils.createAccount(new Account("Hari",9345871334L,"hari@gmail.com",12345,"savings"));
        BankAdminUtils.createAccount(new Account("Muthu",9342903397L,"muthu@gmail.com",123456,"savings"));

        Bank.getBankAdminDetails().add(new BankAdmin(100, "Bank Admin",123456789L, "100","bankadmin@gmail.com"));

        Bank bank = new Bank(100,"salem");

        boolean val = true;
        while(val) {
            System.out.print("\n1 -> Bank\n2 -> ATM \n3 -> Exit\nEnter option : ");
            option = sc.nextInt();
            switch (option) {
                case 1 -> {
                    bank.start();
                }

                case 2 -> {
                    AtmUtils atm = new AtmUtils();
                    atm.start();
                }
                case 3 -> val = false;

                default -> System.out.print("Invalid option...!!!");
            }
        }
    }
}
