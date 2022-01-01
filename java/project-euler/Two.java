//imports
import java.util.ArrayList;

public class Two {
    final static int MAX_LENGTH = 4000000;
    static ArrayList<Integer> sequence = new ArrayList<Integer>();
    static int sum = 0;

    public static void main(String[] args) {
        sequence.add(1);
        sequence.add(2);

        while (true) {
            sequence.add(sequence.get(sequence.size() - 1) + sequence.get(sequence.size() - 2));
            if (sequence.get(sequence.size() - 1) > MAX_LENGTH) {
                sequence.remove(sequence.size() - 1);
                break;
            }
        }

        for (int i : sequence) {
            if (i % 2 == 0) {
                sum += i;
            }
        }

        System.out.println(sum);
    }
}
