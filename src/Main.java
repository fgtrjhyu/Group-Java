import java.util.Collection;
import java.util.LinkedList;
import java.util.Random;

/**
 * Created by fgtrjhyu on 15/03/29.
 */
public class Main {


    public static Collection<Integer> randoms(Collection<Integer> dest, int size, int bound, Random src) {
        while (--size >= 0) {
            dest.add(src.nextInt(bound));
        }
        return dest;
    }


    public static void main(String[] args) {
        Group<Integer> g = new Group<Integer>();
        g.addAll(randoms(new LinkedList<Integer>(), 33, 10, new Random()));
        for (Group<Integer>.Member.Represent r = g.first; r != null; r = r.next) {
            for (Group<Integer>.Member m = r.first; m != null; m = m.next) {
                System.out.printf((m == r.first) ? ">%02d" : ",%02d", m.that);
            }
            System.out.println();
        }
    }
}
