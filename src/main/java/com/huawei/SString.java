package com.huawei;


import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class SString {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String input ="";
        while(scanner.hasNextLine()) {
            input = scanner.nextLine();
            if (input == null || input.length() == 0) continue;
            int i = 0;
            while(i + 8 <= input.length()) {
                System.out.println(input.substring(i, i + 8));
                i = i + 8;
            }
            if (i != input.length()) {
                System.out.print(input.substring(i, input.length()));
                for (int j = 0; j < 8 - (input.length() - i); j++)
                    System.out.print(0);
                System.out.println();
            }
        }

        Integer.parseInt(input.substring(0,1));

    }

//    public static void main(String[] args) {
//        Scanner scanner = new Scanner(System.in);
//        int input;
//        int count = 0;
//        ArrayList<Integer> group = new ArrayList<>();
//        while(scanner.hasNextInt()) {
//            input = scanner.nextInt();
//            if (count == 0) {
//                count = input;
//                if (group.size() != 0) {
//                    output(group);
//                    group = new ArrayList<>();
//                }
//            }
//            else {
//                if (!group.contains(input)) group.add(input);
//                count--;
//            }
//        }
//    }
//    private static void output(ArrayList<Integer> group){
//        Collections.sort(group);
//        for (int i = 0; i < group.size(); i++) {
//            System.out.println(group.get(i));
//        }
//    }
}