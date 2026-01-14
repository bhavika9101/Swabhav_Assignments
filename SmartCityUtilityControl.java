package com.tss.test;

import java.util.Scanner;

public class SmartCityUtilityControl {
    static Scanner scanner = new Scanner(System.in);
    public static void main(String[] args) {
        //looping to continue the service
        System.out.println("WELCOME TO SMART CITY UTILITY CONTROL SYSTEM");
        while (true){
            //Main menu
            System.out.println("----------------------------------------------");
            System.out.println("MAIN MENU");
            System.out.println("1. Electricity Service");
            System.out.println("2. Water Service");
            System.out.println("3. Internet Service");
            System.out.println("4. Exit");
            System.out.println("Choose menu number: ");
            int choice = scanner.nextInt();
            switch (choice){
                case 1:{
                    electricityService();
                    break;
                }
                case 2:{
                    waterService();
                    break;
                }
                case 3:{
                    internetService();
                    break;
                }
                case 4:{
                    System.out.println("EXITING THE SYSTEM.");
                    return;
                }
                default:
                    System.out.println("Enter valid menu number.");
            }
        }

    }
    public static void electricityService(){
        System.out.println("----------------------------------------------");
        System.out.println("Welcome to Electricity Service");
        System.out.println("SUB MENU");
        System.out.println("1. Domestic Connection");
        System.out.println("2. Commercial Connection");
        System.out.println("3. Industrial Connection");
        System.out.println("4. Back");
        int choice = scanner.nextInt();
        double fixedCharge = 0, unitCharge = 0, bill = 0, totalBill;
        String connectionType;
        switch (choice){
            case 1:{
                System.out.println("Enter units consumed: ");
                int units = scanner.nextInt();
                connectionType = "Domestic";
                fixedCharge = 50;
                double surchage = 10;  //10% surcharge
                int billLimit = 2000;   //limit to avoid surcharge
                if(units>= 0 && units<=100){
                    unitCharge = 2;
                    bill += unitCharge*units;
                } else if (units>= 101 && units<=300) {
                    unitCharge = 3;
                    bill += unitCharge*units;
                }else if (units>=301){
                    unitCharge = 5;
                    bill += unitCharge*units;
                }else {
                    System.out.println("Please enter positive units.");
                    return;
                }
                totalBill = bill+fixedCharge;
                //adding surcharge
                if(bill>billLimit){
                    surchage = (surchage/100)*totalBill;
                    totalBill += surchage;
                }
                displayElectricityBill(connectionType, units, bill, fixedCharge, bill>billLimit?surchage:0, totalBill); //if bill doesnt exceed limit then pass surcharge as 0
                break;
            }
            case 2:{
                System.out.println("Enter units consumed: ");
                int units = scanner.nextInt();
                connectionType = "Commercial";
                fixedCharge = 150;
                if(units>= 0 && units<=200){
                    unitCharge = 5;
                    bill += unitCharge*units;
                } else if (units>= 201 && units<=500) {
                    unitCharge = 7;
                    bill += unitCharge*units;
                }else if (units>=501){
                    unitCharge = 10;
                    bill += unitCharge*units;
                }else {
                    System.out.println("Please enter positive units.");
                    return;
                }
                totalBill = bill+fixedCharge;
                displayElectricityBill(connectionType, units, bill, fixedCharge, 0, totalBill);
                break;
            }
            case 3:{
                System.out.println("Enter units consumed: ");
                int units = scanner.nextInt();
                connectionType = "Industrial";
                fixedCharge = 500;
                double rebate = 15;  //15% rebate
                int unitLimit = 1000;   //lower limit for rebate
                unitCharge = 12;
                bill += unitCharge*units;
                if(units<0){
                    System.out.println("Please enter positive units.");
                    return;
                }
                totalBill = bill+fixedCharge;
                //apply rebate if units exceeds unit limit
                if(units>unitLimit){
                    rebate = (rebate/100)*totalBill;
                    totalBill -= rebate;
                }
                displayElectricityBill(connectionType, units, bill, fixedCharge, units>unitLimit?rebate:0, totalBill); //pass 0 as rebate to display function for units under limit
                break;
            }
            case 4:{
                System.out.println("Returning to main menu");
                return;
            }
            default:
                System.out.println("Enter valid menu number. Returning to sub menu.");
        }
    }
    public static void displayElectricityBill(String connectionType,
                                              int units,
                                              double initialBill,
                                              double fixedCharge,
                                              double extraCharge,
                                              double totalBill){
        System.out.println("Connection type: " + connectionType);
        System.out.println("Units: " + units);
        System.out.println("Energy Charge: " + initialBill);
        System.out.println("Fixed Rate: " + fixedCharge);
        if(!connectionType.equals("Commercial")){
            System.out.println((connectionType.equals("Domestic")?"Surcharge: ":"Rebate: ") + extraCharge);

        }
        System.out.println("Total Bill: " + totalBill);
    }
    public static void waterService(){
        System.out.println("----------------------------------------------");
        System.out.println("Welcome to Water Service");
        System.out.println("SUB MENU");
        System.out.println("1. Residential");
        System.out.println("2. Society");
        System.out.println("3. Factory");
        System.out.println("4. Back");
        int choice = scanner.nextInt();
        String connectionType;
        double rate, bill, billLimit = 3000, totalBill, tax;
        switch (choice){
            case 1:{
                connectionType = "Residential";
                System.out.println("Number of persons: ");
                int personCount = scanner.nextInt();
                if(personCount<0){
                    System.out.println("Please enter positive number.");
                    return;
                }
                rate = 30;
                bill = rate*personCount;
                totalBill = bill;
                tax = 8;
                //adds tax to total bill for high bill value
                if(bill> billLimit){
                    tax = (tax/100)*bill;
                    totalBill += tax;
                }
                //if no tax then pass 0 to display method
                displayWaterBill(connectionType,personCount,bill,bill>billLimit?tax:0, totalBill);
                break;
            }
            case 2:{
                connectionType = "Residential";
                System.out.println("Number of taps: ");
                int tapCount = scanner.nextInt();
                if(tapCount<0){
                    System.out.println("Please enter positive number.");
                    return;
                }
                rate = 25;
                bill = rate*tapCount;
                totalBill = bill;
                tax = 8;
                if(bill> billLimit){
                    tax = (tax/100)*bill;
                    totalBill += tax;
                }
                displayWaterBill(connectionType,tapCount,bill,bill>billLimit?tax:0, totalBill);
                break;
            }
            case 3:{
                connectionType = "Factory";
                System.out.println("Number of machines: ");
                int machineCount = scanner.nextInt();
                if(machineCount<0){
                    System.out.println("Please enter positive number.");
                    return;
                }
                rate = 100;
                bill = rate*machineCount;
                totalBill = bill;
                tax = 8;
                if(bill> billLimit){
                    tax = (tax/100)*bill;
                    totalBill += tax;
                }
                displayWaterBill(connectionType,machineCount,bill,bill>billLimit?tax:0, totalBill);
                break;
            }
            case 4:{
                System.out.println("Returning to main menu");
                break;
            }
            default:
                System.out.println("Enter valid menu number. Returning to main menu.");
        }
    }
    public static void displayWaterBill(String connectionType, int count, double initialBill, double tax, double totalBill){
        System.out.println("Connection type: " + connectionType);
        System.out.print("Count of ");
        if(connectionType.equals("Residential")){
            System.out.print(" person: ");
        } else if (connectionType.equals("Society")) {
            System.out.print(" taps: ");
        }else {
            System.out.print(" machines: ");
        }
        System.out.print(count+"\n");
        System.out.println("Energy Charge: " + initialBill);
        System.out.println("Tax: "+tax);
        System.out.println("Total Bill: " + totalBill);
    }
    public static void internetService(){
        System.out.println("----------------------------------------------");
        System.out.println("Welcome to Internet Service");
        System.out.println("SUB MENU");
        System.out.println("1. Student plan");
        System.out.println("2. Home plan");
        System.out.println("3. Business Plan");
        System.out.println("4. Back");
        int choice = scanner.nextInt();
        int duration;
        double rate, discount = 5;
        switch (choice){
            case 1:{
                System.out.println("Duration: ");
                duration = scanner.nextInt();
                if(duration == 1){
                    rate = 299;
                } else if (duration == 3) {
                    rate = 799;
                } else if (duration == 6){
                    System.out.println("Eligible for 5% OFF!");
                    rate = 1499;
                    rate -= (discount/100)*rate;
                }else {
                    System.out.println("Not valid duration.");
                    return;
                }
                System.out.println("Subscription type: Student");
                System.out.println("Duration: " + duration);
                System.out.println("Bill: " + rate);
                break;
            }
            case 2:{
                System.out.println("Duration: ");
                duration = scanner.nextInt();
                if(duration == 1){
                    rate = 499;
                } else if (duration == 3) {
                    rate = 1399;
                } else if (duration == 6){
                    System.out.println("Eligible for 5% OFF!");
                    rate = 2699;
                    rate-= (discount/100)*rate;
                }else {
                    System.out.println("Not valid duration.");
                    return;
                }
                System.out.println("Subscription type: Home");
                System.out.println("Duration: " + duration);
                System.out.println("Bill: " + rate);
                break;
            }
            case 3:{
                System.out.println("Duration: ");
                duration = scanner.nextInt();
                if(duration == 1){
                    rate = 999;
                } else if (duration == 3) {
                    rate = 2799;
                } else if (duration == 6){
                    System.out.println("Eligible for 5% OFF!");
                    rate = 5499;
                    rate-= (discount/100)*rate;
                }else {
                    System.out.println("Not valid duration.");
                    return;
                }
                System.out.println("Subscription type: Business");
                System.out.println("Duration: " + duration);
                System.out.println("Bill: " + rate);
                break;
            }
            case 4:{
                System.out.println("Returning to main menu");
                break;
            }
            default:
                System.out.println("Enter valid menu number. Returning to main menu.");
        }
    }
}

