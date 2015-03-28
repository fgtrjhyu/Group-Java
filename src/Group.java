import java.util.Collection;

/**
 * Created by fgtrjhyu on 15/03/28.
 */
public class Group<T> {

    public Member.Represent first;

    protected boolean equivalent(T that, T other) {
        return that.equals(other);
    }

    private void addMember(Member other) {
        if (first != null) {
            first.add(other);
        } else {
            first = other.new Represent();
        }
    }

    public void add(T other) {
        addMember(new Member(other));
    }

    public void addAll(Iterable<T> others) {
        for (T other : others) {
            add(other);
        }
    }

    public class Member {

        public final T that;

        public Member next;

        private Member(T that) {
            this.that = that;
        }

        private boolean group(Member other) {
            return equivalent(that, other.that);
        }

        private void add(Member other) {
            if (next != null) {
                next.add(other);
            } else {
                next = other;
            }
        }

        public class Represent {
            public Represent next;

            public final Member first = Member.this;

            public void add(Member other) {
                if (group(other)) {
                    first.add(other);
                } else if (next != null) {
                    next.add(other);
                } else {
                    next = other.new Represent();
                }
            }
        }

    }

}
