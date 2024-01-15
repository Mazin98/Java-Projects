import components.sequence.Sequence;

/**
 * Implements method to smooth a {@code Sequence<Integer>}.
 *
 * @author Mazin Tagelsir
 *
 */
public final class SequenceSmooth {

    /**
     * Private constructor so this utility class cannot be instantiated.
     */
    private SequenceSmooth() {
    }

    /**
     * Smooths a given {@code Sequence<Integer>}.
     *
     * @param s1
     *            the sequence to smooth
     * @param s2
     *            the resulting sequence
     * @replaces s2
     * @requires |s1| >= 1
     * @ensures <pre>
     * |s2| = |s1| - 1  and
     *  for all i, j: integer, a, b: string of integer
     *      where (s1 = a * <i> * <j> * b)
     *    (there exists c, d: string of integer
     *       (|c| = |a|  and
     *        s2 = c * <(i+j)/2> * d))
     * </pre>
     */
    public static Sequence<Integer> smooth(Sequence<Integer> s1,
            Sequence<Integer> s2) {
        assert s1 != null : "Violation of: s1 is not null";
        assert s2 != null : "Violation of: s2 is not null";
        assert s1 != s2 : "Violation of: s1 is not s2"; //must be equal
        assert s1.length() >= 1 : "Violation of: |s1| >= 1";

        //base case
        if (s1.length() > 2) {

        }

        //use temp and add it to the next element
        int temp = s1.remove(0);

        while (s1.length() > 1) {

        }

        //use recursion?? or loop?

        //if odd then both will be even to get to num

        //s2 will be empty

//        for(int i =0;i<s1.length();i++) {
//            int temp = (s1.entry(i) + s1.entry(i +1) /2 );
        //s2.add(i, temp);
//        }
//

        //solve recursively

//        int temp = s1.remove(0);
//        int temp2 = s2.remove(0);

//        s1.add(, null);

        return s1;

    }

}