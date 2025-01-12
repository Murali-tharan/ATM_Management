package ATM_Management.Bank.ATM;

import ATM_Management.Bank.Bank;

import java.util.Scanner;

public class AtmAdminUtils{

    static Scanner sc = new Scanner(System.in);

    public static boolean login(String inputPin, int userId,int option){
        return Bank.findAdminPassword(userId,option).equals(inputPin);
    }

    public static void checkAtmBalance(ATM atm){
        System.out.println("Current amount in ATM : "+ atm.getAtmAmount());

    }

    public static void maintenance(ATM atm){
        atm.setMaintenance(true);
    }

    public static void amountDepositToAtm(ATM atm){
        int amount;
        System.out.print("Enter amount to deposit in the ATM : ");
        amount = sc.nextInt();
        atm.setAtmAmount(atm.getAtmAmount() + amount);
        System.out.println("Amount successfully deposit in the ATM");
    }

    public static void showAtmHistory(int id) {
        ATM atmDetails = Bank.findAdminsAtm(id);
        for(String transaction : atmDetails.getAtmHistory())
            System.out.println(transaction);
    }
}
