
/**
 * responsible for reading, logic and printing!
 *
 * ^-^ Trevor, with help from DaNub and UnWin
 * Finished on 03/02/2021
 */
import java.util.Scanner;

public class Main extends Methods   {
   

    public static void main(String args[]) {
        //entry
        int result = 0;
        Scanner Scanner = new Scanner(System.in);
        System.out.println("Single int? Y/N");
        String sumType = Scanner.next();
        //single type
        if (sumType.equals("Y")) {
            System.out.println("Enter integer.");
            int singleInt = Scanner.nextInt();
            System.out.println("Enter operation");
            String singleOp = Scanner.next();
            switch(singleOp) {
                case "square":
                result = Methods.squareSum(singleInt);
                break;
                case "sqrt": 
                result = Methods.sqrtSum(singleInt);
                break;
                case "sumto":
                result = Methods.sumToSum(singleInt);
                break;
                default:
                System.out.println("error");
                System.exit(0);
            }
        } else if (sumType.equals("N")) {
            System.out.println("Enter first integer.");
            int int1 = Scanner.nextInt();
            System.out.println("Enter operation.");
            String op = Scanner.next();
            System.out.println("Enter second integer.");
            int int2 = Scanner.nextInt();
            //operator determiner
            switch (op) {
                case "add":
                result = Methods.addSum(int1, int2);
                break;
                case "subract":
                result = Methods.minusSum(int1, int2);
                break;
                case "times" :
                result = Methods.timesSum(int1, int2);
                break;
                case "divide" : 
                result = Methods.divideSum(int1, int2);
                break;
                default:
                System.out.println("error");
                System.exit(0);
            }

        }
        //resulting
        System.out.println("Ding! Your result is:");
        System.out.println(result);
    }

}
