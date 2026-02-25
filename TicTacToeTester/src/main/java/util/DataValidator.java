package util;

import java.util.Scanner;

public class DataValidator{
    static Scanner scanner = new Scanner(System.in);

    public static int validateInt(){
        while (!scanner.hasNextInt()){
            System.out.println("Enter a valid integer.");
            scanner.next();
        }
        return Math.abs(scanner.nextInt());
    }
    public static int validateCategoryChoice(){
        int i = validateInt();
        while (i < 1 || i > 10){
            System.out.println("Enter a valid choice.");
            i = validateInt();
        }
        return i;
    }
    public static String validateEmailId(){
        String e;
//        scanner.next();
        while (true){
            e = scanner.nextLine().trim();
            if(e.matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$")){
                return e;
            }
//            System.out.println(e);
            System.out.println("Enter a valid email id.");
        }
    }
    public static long validateLong(){
        while (!scanner.hasNextLong()){
            System.out.println("Enter a valid integer.");
            scanner.next();
        }
        return scanner.nextLong();
    }

    public static char validateChar(){
        String s;
        while (true){
            s = scanner.next().toLowerCase();
            if(s.length()==1 && (s.charAt(0) >= 'a' && s.charAt(0) <= 'z')){
                return s.toLowerCase().charAt(0);
            }
            System.out.println("Enter a valid character.");
        }
    }
    public static String validateUserName(){
        String s;
        scanner.nextLine();
        while (true){
            s = scanner.nextLine().trim();
            if(s.matches("[a-zA-Z ]+")){
//                System.out.println("hehe");
                return s;
            }
            System.out.println("Enter a valid name.");
        }
    }
    public static double validateDouble(){
        while (!scanner.hasNextDouble()){
            scanner.next();
            System.out.println("Enter a valid number.");
        }
        return scanner.nextDouble();
    }

}
