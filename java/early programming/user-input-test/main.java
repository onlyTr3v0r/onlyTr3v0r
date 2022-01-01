
/**
 * Main Class.
 *
 * ^-^ Trevor
 * 18/01/2021
 */
import java.util.Scanner;

public class main {

    static void input() {
        Scanner scanObj = new Scanner(System.in); 
        String input = scanObj.nextLine();
        System.out.println("you typed: ".concat(input)); 
        if (input.equals("end")) {
            System.exit(0);
        } else {
            input();
        }

    }

    public static void main(String[] Args) {
        System.out.println("input below");
        input();
    }    
}
