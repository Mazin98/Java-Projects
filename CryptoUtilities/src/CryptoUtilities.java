import components.naturalnumber.NaturalNumber;
import components.naturalnumber.NaturalNumber2;
import components.random.Random;
import components.random.Random1L;
import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;

/**
 * Utilities that could be used with RSA cryptosystems.
 *
 * @author Mazin Tagelsir
 *
 */
public final class CryptoUtilities {

    /**
     * Private constructor so this utility class cannot be instantiated.
     */
    private CryptoUtilities() {
    }

    /**
     * Useful constant, not a magic number: 3.
     */
    private static final int THREE = 3;

    /**
     * Pseudo-random number generator.
     */
    private static final Random GENERATOR = new Random1L();

    /**
     * Returns a random number uniformly distributed in the interval [0, n].
     *
     * @param n
     *            top end of interval
     * @return random number in interval
     * @requires n > 0
     * @ensures <pre>
     * randomNumber = [a random number uniformly distributed in [0, n]]
     * </pre>
     */
    public static NaturalNumber randomNumber(NaturalNumber n) {
        assert !n.isZero() : "Violation of: n > 0";
        final int base = 10;
        NaturalNumber result; //declare NN
        int d = n.divideBy10(); //d is the last digit

        //if n becomes 0 after division
        if (n.isZero()) {

            /*
             * for thw following code this is what im doing: get a random double
             * in the interval [0.0, 1.0) and multiply it by (d+1), make a new
             * NaturalNumber with the value of x and assign it to result
             * multiply n by 10 and add d to it
             */
            int x = (int) ((d + 1) * GENERATOR.nextDouble());
            result = new NaturalNumber2(x);
            n.multiplyBy10(d);

            /*
             * else, recursively call randomNumber with n as input get a random
             * double in the interval [0.0, 1.0) with generator multiply n by 10
             * and add d
             */
        } else {

            result = randomNumber(n);
            int lastDigit = (int) (base * GENERATOR.nextDouble());
            result.multiplyBy10(lastDigit);
            n.multiplyBy10(d);

            //if result > n
            if (result.compareTo(n) > 0) {

                //recursively call randomNumber with N as input
                result = randomNumber(n);

            }
        }
        return result;
    }

    /**
     * Finds the greatest common divisor of n and m.
     *
     * @param n
     *            one number
     * @param m
     *            the other number
     * @updates n
     * @clears m
     * @ensures n = [greatest common divisor of #n and #m]
     */
    public static void reduceToGCD(NaturalNumber n, NaturalNumber m) {

        if (!m.isZero()) {
            NaturalNumber rem = n.divide(m); //n/m
            n.copyFrom(rem); //copy the value of rem to n

            //recursively the 2 values
            reduceToGCD(m, rem);

            //transfer the value from m
            n.transferFrom(m);
        }

    }

    /**
     * Reports whether n is even.
     *
     * @param n
     *            the number to be checked
     * @return true iff n is even
     * @ensures isEven = (n mod 2 = 0)
     */
    public static boolean isEven(NaturalNumber n) {

        boolean even = true; //declare boolean

        //declare NNs
        NaturalNumber two = new NaturalNumber2(2);
        NaturalNumber nCopy = new NaturalNumber2(n);
        NaturalNumber k = nCopy.divide(two);

        //if k is 0 after declaration
        if (!k.isZero()) {
            even = false; //it is not even
        }
        return even;
    }

    /**
     * Updates n to its p-th power modulo m.
     *
     * @param n
     *            number to be raised to a power
     * @param p
     *            the power
     * @param m
     *            the modulus
     * @updates n
     * @requires m > 1
     * @ensures n = #n ^ (p) mod m
     */
    public static void powerMod(NaturalNumber n, NaturalNumber p,
            NaturalNumber m) {
        assert m.compareTo(new NaturalNumber2(1)) > 0 : "Violation of: m > 1";

        NaturalNumber one = new NaturalNumber2(1);
        NaturalNumber two = new NaturalNumber2(2);

        //if p == 0
        if (p.isZero()) {
            n.transferFrom(one); //make n = 1 & one = 0

        } else { //if p != 0

            //declared to n
            NaturalNumber formerN = new NaturalNumber2(n);

            //declared to p og value
            NaturalNumber pCopy = new NaturalNumber2(p);

            //divide the copy by two
            pCopy.divide(two);
            powerMod(n, pCopy, m); //recursive method w/ new values

            //Raises n to the power 2
            n.power(2);

            //if p is not even
            if (!isEven(p)) {
                n.multiply(formerN);
            }

            NaturalNumber result = n.divide(m);
            n.transferFrom(result); //transfers

        }

    }

    /**
     * Reports whether w is a "witness" that n is composite, in the sense that
     * either it is a square root of 1 (mod n), or it fails to satisfy the
     * criterion for primality from Fermat's theorem.
     *
     * @param w
     *            witness candidate
     * @param n
     *            number being checked
     * @return true iff w is a "witness" that n is composite
     * @requires n > 2 and 1 < w < n - 1
     * @ensures <pre>
     * isWitnessToCompositeness =
     *     (w ^ 2 mod n = 1)  or  (w ^ (n-1) mod n /= 1)
     * </pre>
     */
    public static boolean isWitnessToCompositeness(NaturalNumber w,
            NaturalNumber n) {
        assert n.compareTo(new NaturalNumber2(2)) > 0 : "Violation of: n > 2";
        assert (new NaturalNumber2(1)).compareTo(w) < 0 : "Violation of: 1 < w";
        n.decrement();
        assert w.compareTo(n) < 0 : "Violation of: w < n - 1";
        n.increment();

        //declaring NN + copies of inputs
        NaturalNumber one = new NaturalNumber2(1);
        NaturalNumber wCopy1 = new NaturalNumber2(w);
        NaturalNumber wCopy2 = new NaturalNumber2(w);
        NaturalNumber two = new NaturalNumber2(2);
        NaturalNumber nCopyM1 = new NaturalNumber2(n);

        boolean check = false; //declariing boolean

        //recursive method with NN
        powerMod(wCopy1, two, n);
        wCopy1.subtract(one); //subtract frrom copy of w

        nCopyM1.subtract(one);
        powerMod(wCopy2, nCopyM1, n); //recursive method again
        wCopy2.subtract(one); //subtract NN from 2

        //if 1 is 0 and 2 is not 0
        if ((wCopy1.isZero() || !wCopy2.isZero())) {
            check = true;
        }
        return check;
    }

    /**
     * Reports whether n is a prime; may be wrong with "low" probability.
     *
     * @param n
     *            number to be checked
     * @return true means n is very likely prime; false means n is definitely
     *         composite
     * @requires n > 1
     * @ensures <pre>
     * isPrime1 = [n is a prime number, with small probability of error
     *         if it is reported to be prime, and no chance of error if it is
     *         reported to be composite]
     * </pre>
     */
    public static boolean isPrime1(NaturalNumber n) {
        assert n.compareTo(new NaturalNumber2(1)) > 0 : "Violation of: n > 1";

        boolean isPrime; //boolean to be declared

        //if n
        if (n.compareTo(new NaturalNumber2(THREE)) <= 0) {
            isPrime = true;

        } else if (isEven(n)) { //if n is even

            isPrime = false;

            //Set isPrime to
        } else {
            isPrime = !isWitnessToCompositeness(new NaturalNumber2(2), n);
        }
        return isPrime;
    }

    /**
     * Reports whether n is a prime; may be wrong with "low" probability.
     *
     * @param n
     *            number to be checked
     * @return true means n is very likely prime; false means n is definitely
     *         composite
     * @requires n > 1
     * @ensures <pre>
     * isPrime2 = [n is a prime number, with small probability of error
     *         if it is reported to be prime, and no chance of error if it is
     *         reported to be composite]
     * </pre>
     */
    public static boolean isPrime2(NaturalNumber n) {
        assert n.compareTo(new NaturalNumber2(1)) > 0 : "Violation of: n > 1";

        /*
         * Use the ability to generate random numbers (provided by the
         * randomNumber method above) to generate several witness candidates --
         * say, 10 to 50 candidates -- guessing that n is prime only if none of
         * these candidates is a witness to n being composite (based on fact #3
         * as described in the project description); use the code for isPrime1
         * as a guide for how to do this, and pay attention to the requires
         * clause of isWitnessToCompositeness
         */

        //declares boolean
        boolean isPrime = true;
        //declare copy to n
        NaturalNumber nCopyM1 = new NaturalNumber2(n);
        nCopyM1.decrement(); // decrement nCopyM1

        //2 and 3 are primes
        if (n.compareTo(new NaturalNumber2(THREE)) <= 0) {

            isPrime = true;

        } else if (isEven(n)) { //evens are composite

            isPrime = false;

        } else {
            final int canidates = 50; //50 candidates
            for (int i = 0; i < canidates; i++) {

                //generate random wtn
                NaturalNumber wtn = randomNumber(nCopyM1);

                // check if the candidate is a witness to n's compositeness
                if (isWitnessToCompositeness(wtn, n)) {
                    isPrime = false; // if is is set isPrime to false
                }
            }

        }
        return isPrime; //return

    }

    /**
     * Generates a likely prime number at least as large as some given number.
     *
     * @param n
     *            minimum value of likely prime
     * @updates n
     * @requires n > 1
     * @ensures n >= #n and [n is very likely a prime number]
     */
    public static void generateNextLikelyPrime(NaturalNumber n) {
        assert n.compareTo(new NaturalNumber2(1)) > 0 : "Violation of: n > 1";

        // If n is even, increment it to the next odd number
        if (isEven(n)) {
            n.increment();
        }
        // While n is not a prime number, increment it by 2 until it becomes prime
        while (!isPrime2(n)) {
            n.increment();
            n.increment();
        }

    }

    /**
     * Main method.
     *
     * @param args
     *            the command line arguments
     */
    public static void main(String[] args) {
        SimpleReader in = new SimpleReader1L();
        SimpleWriter out = new SimpleWriter1L();

        /*
         * Sanity check of randomNumber method -- just so everyone can see how
         * it might be "tested"
         */
        final int testValue = 17;
        final int testSamples = 100000;
        NaturalNumber test = new NaturalNumber2(testValue);
        int[] count = new int[testValue + 1];
        for (int i = 0; i < count.length; i++) {
            count[i] = 0;
        }
        for (int i = 0; i < testSamples; i++) {
            NaturalNumber rn = randomNumber(test);
            assert rn.compareTo(test) <= 0 : "Help!";
            count[rn.toInt()]++;
        }
        for (int i = 0; i < count.length; i++) {
            out.println("count[" + i + "] = " + count[i]);
        }
        out.println("  expected value = "
                + (double) testSamples / (double) (testValue + 1));

        /*
         * Check user-supplied numbers for primality, and if a number is not
         * prime, find the next likely prime after it
         */

        while (true) {
            out.print("n = ");
            NaturalNumber n = new NaturalNumber2(in.nextLine());
            if (n.compareTo(new NaturalNumber2(2)) < 0) {
                out.println("Bye!");
                break;
            } else {
                if (isPrime1(n)) {
                    out.println(n + " is probably a prime number"
                            + " according to isPrime1.");
                } else {
                    out.println(n + " is a composite number"
                            + " according to isPrime1.");
                }
                if (isPrime2(n)) {
                    out.println(n + " is probably a prime number"
                            + " according to isPrime2.");
                } else {
                    out.println(n + " is a composite number"
                            + " according to isPrime2.");
                    generateNextLikelyPrime(n);
                    out.println("  next likely prime is " + n);
                }
            }
        }

        /*
         * Close input and output streams
         */
        in.close();
        out.close();
    }

}
