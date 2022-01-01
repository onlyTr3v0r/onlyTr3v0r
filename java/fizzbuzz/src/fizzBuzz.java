public class fizzBuzz {
    public static void main(String[] args) {
        for (int i = 1, f = 3, b = 5, l= 30; i <= l; i++) { //f = fizz, b = buzz, l = length
            System.out.println((i % f == 0 && i % b == 0 ? "fizzbuzz" : (i % b == 0 ? "buzz" : (i % f == 0) ? "fizz" : String.valueOf(i))));
        }
    }
}