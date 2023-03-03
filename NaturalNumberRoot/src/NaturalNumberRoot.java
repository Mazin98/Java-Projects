import components.naturalnumber.NaturalNumber;
import components.naturalnumber.NaturalNumber1L;
import components.naturalnumber.NaturalNumber2;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;

/**
 * Program with implementation of {@code NaturalNumber} secondary operation
 * {@code root} implemented as static method.
 *
 * @author Mazin Tagelsir
 *
 */
public final class NaturalNumberRoot {

    /**
     * Private constructor so this utility class cannot be instantiated.
     */
    private NaturalNumberRoot() {
    }

    /**
     * Updates {@code n} to the {@code r}-th root of its incoming value.
     *
     * @param n
     *            the number whose root to compute
     * @param r
     *            root
     * @updates n
     * @requires r >= 2
     * @ensures n ^ (r) <= #n < (n + 1) ^ (r)
     */
    public static void root(NaturalNumber n, int r) {
        assert n != null : "Violation of: n is  not null";
        assert r >= 2 : "Violation of: r >= 2";

        //upper and lowerbound NN
        NaturalNumber lowEnough = new NaturalNumber1L(0);
        NaturalNumber tooHigh = new NaturalNumber1L();
        tooHigh.copyFrom(n);
        tooHigh.increment();

        NaturalNumber higher = n.newInstance();
        higher.copyFrom(tooHigh);

        //used for division
        NaturalNumber two = new NaturalNumber1L(2);

        //one
        NaturalNumber one = new NaturalNumber1L(1);

        //highest - lowest
        tooHigh.subtract(lowEnough);

        //will be used for halfway point
        NaturalNumber halfway = new NaturalNumber1L();

        //while highest-one = greater than 0
        while (tooHigh.compareTo(one) > 0) {
            tooHigh.add(lowEnough);

            //these 3 lines below are equal to (lowest+highest)/2
            halfway.copyFrom(lowEnough);
            halfway.add(tooHigh);
            halfway.divide(two);

            //the fraction for the power
            NaturalNumber power = new NaturalNumber1L();
            power.copyFrom(halfway);

            //raises NN power to the root
            power.power(r);

            /*
             * These two statement change the lower and upper bound variables
             * based on edge cases
             */
            if (power.compareTo(higher) < 0) { //if halfway is negative
                lowEnough.copyFrom(halfway); //halfway is the lower bound
            } else {
                tooHigh.copyFrom(halfway); //halfway is the upper bound
            }
            tooHigh.subtract(lowEnough); // highest - lowest

        }
        n.copyFrom(lowEnough); //copies the variable from the lowest

    }

    /**
     * Main method.
     *
     * @param args
     *            the command line arguments
     */
    public static void main(String[] args) {
        SimpleWriter out = new SimpleWriter1L();

        final String[] numbers = { "0", "1", "13", "1024", "189943527", "0",
                "1", "13", "4096", "189943527", "0", "1", "13", "1024",
                "189943527", "82", "82", "82", "82", "82", "9", "27", "81",
                "243", "143489073", "2147483647", "2147483648",
                "9223372036854775807", "9223372036854775808",
                "618970019642690137449562111",
                "162259276829213363391578010288127",
                "170141183460469231731687303715884105727" };
        final int[] roots = { 2, 2, 2, 2, 2, 3, 3, 3, 3, 3, 15, 15, 15, 15, 15,
                2, 3, 4, 5, 15, 2, 3, 4, 5, 15, 2, 2, 3, 3, 4, 5, 6 };
        final String[] results = { "0", "1", "3", "32", "13782", "0", "1", "2",
                "16", "574", "0", "1", "1", "1", "3", "9", "4", "3", "2", "1",
                "3", "3", "3", "3", "3", "46340", "46340", "2097151", "2097152",
                "4987896", "2767208", "2353973" };

        for (int i = 0; i < numbers.length; i++) {
            NaturalNumber n = new NaturalNumber2(numbers[i]);
            NaturalNumber r = new NaturalNumber2(results[i]);
            root(n, roots[i]);
            if (n.equals(r)) {
                out.println("Test " + (i + 1) + " passed: root(" + numbers[i]
                        + ", " + roots[i] + ") = " + results[i]);
            } else {
                out.println("*** Test " + (i + 1) + " failed: root("
                        + numbers[i] + ", " + roots[i] + ") expected <"
                        + results[i] + "> but was <" + n + ">");
            }
        }

        out.close();
    }

}
