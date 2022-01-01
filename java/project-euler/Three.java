//imports


public class Three {
    static final long MAX_LENGTH = 600851475143L; 
    
    public static void main(String[] args) {
        System.out.println(isPrime(3));
        System.out.println(isPrime(4));
        for (int toDivide = 0; toDivide < MAX_LENGTH; toDivide++) {
            if (MAX_LENGTH % toDivide == 0) {

            }
        }
    }

    public static boolean isPrime(int num) {
        boolean isPrime = false;
        for (int i = 2; i < num; i++) {
            if (num % i != 0) isPrime = true;
            else isPrime = false;
        }
        return isPrime;
    }
}
