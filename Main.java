// Main.java — Students version

import java.io.*;
import java.util.*;

public class Main {
    static final int MONTHS = 12;
    static final int DAYS = 28;
    static final int COMMS = 5;
    static String[] commodities = {"Gold", "Oil", "Silver", "Wheat", "Copper"};
    static String[] months = {"January", "February", "March", "April", "May", "June",
            "July", "August", "September", "October", "November", "December"};
    static int[][][] data = new int[MONTHS][DAYS][COMMS];

    // ======== REQUIRED METHOD LOAD DATA (Students fill this) ========
    public static void loadData() {
        String[] parts;
        int commindex = 0;
        for (int m = 0; m < 12; m++) {
            String fileName = "Data_Files/" + months[m] + ".txt";
            try {
                BufferedReader br = new BufferedReader(new FileReader(fileName));
                String line;
                while ((line = br.readLine()) != null) {
                    parts = line.split(",");
                    if (parts.length != 3) {
                        continue;
                    }
                    switch (parts[1]) {
                        case "Gold":
                            commindex = 0;
                            break;
                        case "Oil":
                            commindex = 1;
                            break;
                        case "Silver":
                            commindex = 2;
                            break;
                        case "Wheat":
                            commindex = 3;
                            break;
                        case "Copper":
                            commindex = 4;
                            break;
                    }
                    data[m][Integer.parseInt(parts[0]) - 1][commindex] = Integer.parseInt(parts[2]);

                }
                br.close();
            } catch (Exception a) {
            }
        }
    }
    // ======== 10 REQUIRED METHODS (Students fill these) ========

    public static String mostProfitableCommodityInMonth(int month) {
        if(month < 0 || month > 11) {
            return "INVALID_MONTH";
        }
        int[] profit = new int[COMMS];
        for (int i = 0; i < DAYS; i++) {
            for (int j = 0; j < COMMS; j++) {
                profit[j] += data[month][i][j];
            }
        }
        int max = profit[0];
        int b = 0;
        for (int i = 0; i < COMMS; i++) {
            if (profit[i] > max) {
                max = profit[i];
                b = i;
            }
        }
        return commodities[b]+ " " + max;
    }
    public static int totalProfitOnDay(int month, int day) {
        int a=0;
        if(month < 0 || month > 11) {
            return -99999;
        }
        if(day < 1 || day > DAYS) {
            return -99999;
        }

        for(int i=0;i<COMMS;i++){
            a+=data[month][day-1][i];
        }
        return a;
    }

    public static int commodityProfitInRange(String commodity, int from, int to) {
        if(!commodity.equals("Gold") && !commodity.equals("Oil") && !commodity.equals("Silver") && !commodity.equals("Wheat")&& !commodity.equals("Copper")) {
            return -99999;
        }
        if(from<1||to>28||to<from){
            return -99999;
        }
        int commindex=0;
        switch (commodity) {
            case "Gold":
                commindex = 0;
                break;
            case "Oil":
                commindex = 1;
                break;
            case "Silver":
                commindex = 2;
                break;
            case "Wheat":
                commindex = 3;
                break;
            case "Copper":
                commindex = 4;
                break;
        }
        int profit=0;
        for(int i=0;i<12;i++){
            for(int j= from-1 ; j <= to-1 ; j++) {
                profit += data[i][j][commindex];
            }
        }
        return profit;
    }

    public static int bestDayOfMonth(int month) {
        if(month < 0 || month > 11) {
            return -1;
        }
        int k=0;
        int m=0;
        int max=data[month][0][0];
        for(int i=0;i<28;i++){
            for(int j=0;j<5;j++){
                k+=data[month][i][j];

            }
            if(k>max){
                max=k;
                m=i;
            }
            k=0;
        }
        return m+1;
    }

    public static String bestMonthForCommodity(String comm) {
        int k=0;
        int m=0;
        int commindex=0;
        switch (comm) {
            case "Gold":
                commindex = 0;
                break;
            case "Oil":
                commindex = 1;
                break;
            case "Silver":
                commindex = 2;
                break;
            case "Wheat":
                commindex = 3;
                break;
            case "Copper":
                commindex = 4;
                break;
            default:
                return "INVALID_COMMODITY";
        }

        int max=0;
        for(int i=0;i<12;i++){
            for(int j=0;j<28;j++){
                k+=data[i][j][commindex];
            }
            if(k>max){
                max=k;
                m=i;
            }
            k=0;
        }
    return months[m];
    }

    public static int consecutiveLossDays(String comm) {
        int commindex=0;
        switch (comm) {
            case "Gold":
                commindex = 0;
                break;
            case "Oil":
                commindex = 1;
                break;
            case "Silver":
                commindex = 2;
                break;
            case "Wheat":
                commindex = 3;
                break;
            case "Copper":
                commindex = 4;
                break;
            default:
                return -1;

        }
        int a=0;
        int sayac=0;
        for(int i=0;i<12;i++) {
            for (int j = 0; j < 28; j++) {
                    if(data[i][j][commindex]<0) {
                        sayac++;
                    }
                        else{
                            sayac=0;
                        }
                    if (sayac>a) {
                        a=sayac;
                    }

            }
        }
        return a;
    }

    public static int daysAboveThreshold(String comm, int threshold) {
        int commindex=0;
        switch (comm) {
            case "Gold":
                commindex = 0;
                break;
            case "Oil":
                commindex = 1;
                break;
            case "Silver":
                commindex = 2;
                break;
            case "Wheat":
                commindex = 3;
                break;
            case "Copper":
                commindex = 4;
                break;
            default:
                return -1;
        }
        int sayac=0;
        for(int i=0;i<12;i++){
            for(int j=0;j<28;j++){
                if(data[i][j][commindex]>threshold){
                   sayac++;
                }
            }
        }
        return sayac;
    }

    public static int biggestDailySwing(int month) {
        return 1234;
    }

    public static String compareTwoCommodities(String c1, String c2) {
        return "DUMMY is better by 1234";
    }

    public static String bestWeekOfMonth(int month) {
        return "DUMMY";
    }

    public static void main(String[] args) {
        loadData();
        System.out.println("Data loaded – ready for queries");
    }
}
