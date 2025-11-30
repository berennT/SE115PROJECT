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
                    if (parts[0].equals("Day")) {
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
        if (month < 0 || month > 11) {
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
        return commodities[b] + " " + max;
    }

    public static int totalProfitOnDay(int month, int day) {
        int a = 0;
        if (month < 0 || month > 11) {
            return -99999;
        }
        if (day < 1 || day > DAYS) {
            return -99999;
        }

        for (int i = 0; i < COMMS; i++) {
            a += data[month][day - 1][i];
        }
        return a;
    }

    public static int commodityProfitInRange(String commodity, int from, int to) {
        if (!commodity.equals("Gold") && !commodity.equals("Oil") && !commodity.equals("Silver") && !commodity.equals("Wheat") && !commodity.equals("Copper")) {
            return -99999;
        }
        if (from < 1 || to > 28 || to < from) {
            return -99999;
        }
        int commindex = 0;
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
        int profit = 0;
        for (int i = 0; i < 12; i++) {
            for (int j = from - 1; j <= to - 1; j++) {
                profit += data[i][j][commindex];
            }
        }
        return profit;
    }

    public static int bestDayOfMonth(int month) {
        if (month < 0 || month > 11) {
            return -1;
        }
        int k = 0;
        int m = 0;
        int max = 0;
        for (int j = 0; j < COMMS; j++) {
            max += data[month][0][j];
        }
        for (int i = 1; i < DAYS ; i++) {
            for (int j = 0; j < COMMS ; j++) {
                k += data[month][i][j];

            }
            if (k > max) {
                max = k;
                m = i;
            }
            k = 0;
        }
        return m + 1;
    }

    public static String bestMonthForCommodity(String comm) {
        int k = 0;
        int m = 0;
        int commindex = 0;
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
        int max = 0;
        for (int j = 0; j < DAYS; j++) {
            max += data[0][j][commindex];
        }

        for (int i = 1; i < MONTHS; i++) {
            for (int j = 0; j < DAYS; j++) {
                k += data[i][j][commindex];
            }
            if (k > max) {
                max = k;
                m = i;
            }
            k = 0;
        }
        return months[m];
    }

    public static int consecutiveLossDays(String comm) {
        int commindex = 0;
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
        int a = 0;
        int sayac = 0;
        for (int i = 0; i < 12; i++) {
            for (int j = 0; j < 28; j++) {
                if (data[i][j][commindex] < 0) {
                    sayac++;
                } else {
                    sayac = 0;
                }
                if (sayac > a) {
                    a = sayac;
                }

            }
        }
        return a;
    }

    public static int daysAboveThreshold(String comm, int threshold) {
        int commindex = 0;
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
        int sayac = 0;
        for (int i = 0; i < MONTHS ; i++) {
            for (int j = 0; j < DAYS ; j++) {
                if (data[i][j][commindex] > threshold) {
                    sayac++;
                }
            }
        }
        return sayac;
    }


    public static int biggestDailySwing(int month) {
        if (month < 0 || month > 11) {
            return -99999;
        }
        int a=0;
        int[] dailyTotal = new int[28];
        for (int i = 0; i < DAYS; i++) {
            for (int j = 0; j < COMMS; j++) {
                a += data[month][i][j];
            }
            dailyTotal[i]=a;
            a=0;
        }
        int diff=0;
        int b=0;
        for (int i = 0; i < 27; i++) {
            diff=dailyTotal[i]-dailyTotal[i+1];
            if(diff<0){
                diff*=-1;
            }
            if(diff>b){
                b=diff;
            }
        }

        return b;
    }

    public static String compareTwoCommodities(String c1, String c2) {
        int commindex1 = 0;
        switch (c1) {
            case "Gold":
                commindex1 = 0;
                break;
            case "Oil":
                commindex1 = 1;
                break;
            case "Silver":
                commindex1 = 2;
                break;
            case "Wheat":
                commindex1 = 3;
                break;
            case "Copper":
                commindex1 = 4;
                break;
            default:
                return "INVALID_COMMODITY";

        }
        int commindex2 = 0;
        switch (c2) {
            case "Gold":
                commindex2 = 0;
                break;
            case "Oil":
                commindex2 = 1;
                break;
            case "Silver":
                commindex2 = 2;
                break;
            case "Wheat":
                commindex2 = 3;
                break;
            case "Copper":
                commindex2 = 4;
                break;
            default:
                return "INVALID_COMMODITY";

        }

        int total1 = 0;
        for (int i = 0; i < MONTHS; i++) {
            for (int j = 0; j < DAYS; j++) {
                total1 += data[i][j][commindex1];

            }
        }
        int total2 = 0;
        for (int i = 0; i < MONTHS; i++) {
            for (int j = 0; j < DAYS; j++) {
                total2 += data[i][j][commindex2];

            }
        }
        if (total1 > total2) {
            return (c1 + " is better by " + (total1 - total2));
        } else if (total2 > total1) {
            return (c2 + " is better by " + (total2 - total1));
        } else {
            return "Equal";
        }
    }

    public static String bestWeekOfMonth(int month) {
        if (month < 0 || month > 11) {
            return "INVALID_MONTH";
        }
        int total1 = 0;
        int total2 = 0;
        int total3 = 0;
        int total4 = 0;

        for (int i = 0; i < 7; i++) {
            for (int j = 0; j < COMMS; j++) {
                total1 += data[month][i][j];
            }
        }

        for (int i = 7; i < 14; i++) {
            for (int j = 0; j < COMMS; j++) {
                total2 += data[month][i][j];
            }
        }
        for (int i = 14; i < 21; i++) {
            for (int j = 0; j < COMMS; j++) {
                total3 += data[month][i][j];
            }
        }
        for (int i = 21; i < 28; i++) {
            for (int j = 0; j < COMMS; j++) {
                total4 += data[month][i][j];
            }
        }
        int max = total1;
        int k = 0;
        int arr[] = {total1, total2, total3, total4};
        for (int i = 0; i < 4; i++) {
            if (arr[i] > max) {
                max = arr[i];
                k = i;
            }
        }
        return "Week " + (k + 1);
    }

    public static void main(String[] args) {
        loadData();
        System.out.println("Data loaded – ready for queries");
    }
}
