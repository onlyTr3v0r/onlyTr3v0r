
/**
 * Conatains all methods needed for Main.
 *
 * ^-^ Trevor
 * 03/02/2021
 */
public class Methods
{
     //all operator functions
    static int addSum(int q, int w) {
        return q + w;
    }

    static int minusSum(int q, int w) {
        return q - w;
    }

    static int timesSum(int q, int w) {
        return q * w;
    }

    static int divideSum(int q, int w) {
        return q / w;
    }

    static int squareSum(int q) {
        return q*q;   
    }

    static int sqrtSum(int q) {
        return (int) Math.sqrt(q);    
    }

    static int sumToSum(int q) {
        if(q > 0) {
         return q + sumToSum(q - 1);
        } else {
         return 0;   
        }
    }
}
