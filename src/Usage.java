import java.util.Collection;
import java.util.LinkedList;
import java.util.Random;

/**
 * Created by fgtrjhyu on 15/03/29.
 */
public class Usage {


    public static Collection<Integer> randoms(Collection<Integer> dest, int size, int bound, Random src) {
        while (--size >= 0) {
            dest.add(src.nextInt(bound));
        }
        return dest;
    }

    public static void main(String[] args) {
        //単純な使い方:グループ化する対象の同値比較を使う
        Random rnd = new Random();
        Group<Integer> g = new Group<Integer>();
        g.addAll(randoms(new LinkedList<Integer>(), 33, 10, rnd));
        for (Group<Integer>.Member.Represent r = g.first; r != null; r = r.next) {
            for (Group<Integer>.Member m = r.first; m != null; m = m.next) {
                System.out.printf((m == r.first) ? ">%02d" : ",%02d", m.that);
            }
            System.out.println();
        }

        //任意のグループ化方法:Group#equivalentをオーバーライドして任意の比較を用いる
        Group<Foo> h = new Group<Foo>() {
            @Override
            protected boolean equivalent(Foo that, Foo other) {
                return that.num % 2 == other.num % 2;
            }
        };
        for (int i = 7; --i >= 0; ) {
            h.add(Foo.make(123, rnd));
        }
        for (Group<Foo>.Member.Represent r = h.first; r != null; r = r.next) {
            int sum = 0;
            for (Group<Foo>.Member m = r.first; m != null; m = m.next) {
                if (m == r.first) {
                    if (m.that.num % 2 == 0) {
                        System.out.print("even:");
                    } else {
                        System.out.print("odd:");
                    }
                    System.out.printf("%s", m.that.text);
                } else {
                    System.out.printf(",%s", m.that.text);
                }
                sum += m.that.num;
            }
            System.out.printf(":%03d", sum);
            System.out.println();
        }
    }

    public static class Foo {
        public final int num;
        public final String text;

        public Foo(int num, String text) {
            this.num = num;
            this.text = text;
        }

        private static String choose(int n) {
            switch (Math.abs(n) % 4) {
                case 0:
                    return "foo";
                case 1:
                    return "bar";
                case 2:
                    return "baz";
                case 3:
                    return "qux";
                default:
                    throw new IllegalArgumentException();
            }
        }

        public static Foo make(int bound, Random src) {
            return new Foo(src.nextInt(bound), choose(src.nextInt()));
        }
    }
}
