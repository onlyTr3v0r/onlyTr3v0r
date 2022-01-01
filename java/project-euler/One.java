public class One {
    static final int MAX_LENGTH = 1000;
    static long sum = 0;
    static int[] checkAgainst = { 3, 5 };

    public static void main(String[] args) {
        for (int i = 0; i < MAX_LENGTH; i++) {
            for (int check : checkAgainst) {
                if (i % check == 0) {
                    sum += i; 
                    break;
                }
            }
        }
        System.out.println(sum);
    }
}