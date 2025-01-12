package ATM_Management.Bank.ATM;

import java.util.ArrayList;

public class ATM  {
    private static int atmId = 0;
    private int atmAdminId;
    private final String bankName = "IOB";
    private String location;
    private boolean maintenance = false;
    private static long atmAmount = 10000;

    public ArrayList<String> atmHistory = new ArrayList<>();

    public ATM(int atmAdminId, String location){
        this.atmAdminId = atmAdminId;
        this.location = location;
    }

    public static int getAtmId() {
        return atmId;
    }

    public static void setAtmId() {
        ATM.atmId = ++atmId;
    }

    public int getAtmAdminId() {
        return atmAdminId;
    }

    public String getLocation() {
        return location;
    }

    public boolean isMaintenance() {
        return maintenance;
    }

    public void setMaintenance(boolean maintenance) {
        this.maintenance = maintenance;
    }

    public long getAtmAmount() {
        return atmAmount;
    }

    public void setAtmAmount(long atmAmount) {
        ATM.atmAmount = atmAmount;
    }

    public ArrayList<String> getAtmHistory() {
        return atmHistory;
    }
}
