import java.util.Scanner;

import javax.imageio.plugins.tiff.ExifGPSTagSet;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<Character> arrList = new ArrayList<Character>();
        char[] input = scanner.nextLine().trim().toLowerCase().toCharArray();
        for (char c : input) {
            arrList.add(c);
        }
        arrList.remove(arrList.size() - 1);


        //System.out.println(arrList.size());
        for (char c : arrList) {
            System.out.println(c);
        }

        scanner.close();
    }
}