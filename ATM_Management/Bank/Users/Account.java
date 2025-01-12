package ATM_Management.Bank.Users;

import java.util.ArrayList;

public class Account extends Users {
    private static int accountId = 0;
    private static int cardId = 0;
    private long accountNumber;
    private long cardNumber;
    private int pin;
    private int minBalance;
    private int currentBalance;
    private long aadhaarNumber;
    private String accountType;
    private int limit = Integer.MAX_VALUE;

    private ArrayList<String> transactionHistory = new ArrayList<>();

    public Account(String name, long mobileNumber, String email, long aadhaarNumber, String accountType) {
        setName(name);
        setMobileNumber(mobileNumber);
        setEmail(email);
        this.aadhaarNumber = aadhaarNumber;
        this.accountType = accountType;
        transactionHistory.add("Credit : 500");
        if(accountType.equals("savings"))
                limit = 10;
    }

    public static int getAccountId() {
        return accountId;
    }

    public void setAccountId() {
        this.accountId = ++accountId;
    }

    public static int getCardId() {
        return cardId;
    }

    public void setCardId() {
        this.cardId = ++cardId;
    }

    public long getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(long accountNumber) {
        this.accountNumber = accountNumber;
    }

    public long getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(long cardNumber) {
        this.cardNumber = cardNumber;
    }

    public int getPin() {
        return pin;
    }

    public void setPin(int pin) {
        this.pin = pin;
    }

    public void setMinBalance(int minBalance) {
        this.minBalance = minBalance;
    }

    public int getCurrentBalance() {
        return currentBalance;
    }

    public void setCurrentBalance(int currentBalance) {
        this.currentBalance = currentBalance;
    }

    public long getAadhaarNumber() {
        return aadhaarNumber;
    }

    public String getAccountType() {
        return accountType;
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    public ArrayList<String> getTransactionHistory() {
        return transactionHistory;
    }

    public void setTransactionHistory(String history) {
        transactionHistory.add(history);
    }
}
