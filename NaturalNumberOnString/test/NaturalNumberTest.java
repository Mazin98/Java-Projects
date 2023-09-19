import static org.junit.Assert.assertEquals;

import org.junit.Test;

import components.naturalnumber.NaturalNumber;

/**
 * JUnit test fixture for {@code NaturalNumber}'s constructors and kernel
 * methods.
 *
 * @author Put your name here
 *
 */
public abstract class NaturalNumberTest {

    /**
     * Invokes the appropriate {@code NaturalNumber} constructor for the
     * implementation under test and returns the result.
     *
     * @return the new number
     * @ensures constructorTest = 0
     */
    protected abstract NaturalNumber constructorTest();

    /**
     * Invokes the appropriate {@code NaturalNumber} constructor for the
     * implementation under test and returns the result.
     *
     * @param i
     *            {@code int} to initialize from
     * @return the new number
     * @requires i >= 0
     * @ensures constructorTest = i
     */
    protected abstract NaturalNumber constructorTest(int i);

    /**
     * Invokes the appropriate {@code NaturalNumber} constructor for the
     * implementation under test and returns the result.
     *
     * @param s
     *            {@code String} to initialize from
     * @return the new number
     * @requires there exists n: NATURAL (s = TO_STRING(n))
     * @ensures s = TO_STRING(constructorTest)
     */
    protected abstract NaturalNumber constructorTest(String s);

    /**
     * Invokes the appropriate {@code NaturalNumber} constructor for the
     * implementation under test and returns the result.
     *
     * @param n
     *            {@code NaturalNumber} to initialize from
     * @return the new number
     * @ensures constructorTest = n
     */
    protected abstract NaturalNumber constructorTest(NaturalNumber n);

    /**
     * Invokes the appropriate {@code NaturalNumber} constructor for the
     * reference implementation and returns the result.
     *
     * @return the new number
     * @ensures constructorRef = 0
     */
    protected abstract NaturalNumber constructorRef();

    /**
     * Invokes the appropriate {@code NaturalNumber} constructor for the
     * reference implementation and returns the result.
     *
     * @param i
     *            {@code int} to initialize from
     * @return the new number
     * @requires i >= 0
     * @ensures constructorRef = i
     */
    protected abstract NaturalNumber constructorRef(int i);

    /**
     * Invokes the appropriate {@code NaturalNumber} constructor for the
     * reference implementation and returns the result.
     *
     * @param s
     *            {@code String} to initialize from
     * @return the new number
     * @requires there exists n: NATURAL (s = TO_STRING(n))
     * @ensures s = TO_STRING(constructorRef)
     */
    protected abstract NaturalNumber constructorRef(String s);

    /**
     * Invokes the appropriate {@code NaturalNumber} constructor for the
     * reference implementation and returns the result.
     *
     * @param n
     *            {@code NaturalNumber} to initialize from
     * @return the new number
     * @ensures constructorRef = n
     */
    protected abstract NaturalNumber constructorRef(NaturalNumber n);

    /*
     * Test cases for constructors
     */

    /**
     * Test no arg constructor - Reason:Boundary.
     */
    @Test
    public void testConstructorNoArgs() {
        NaturalNumber n = this.constructorTest();
        NaturalNumber nExpeected = this.constructorRef();

        assertEquals(n, nExpeected);
    }

    /**
     * Test integer argument - Reason:routine.
     */
    @Test
    public void test1Constructor() {

        final int seven = 7;
        NaturalNumber n = this.constructorTest(seven);
        NaturalNumber nExpected = this.constructorRef(seven);

        assertEquals(n, nExpected);
    }

    /**
     * Test to prove 0 is an empty constructor - reason: boundary.
     */
    @Test
    public void testFor0() {
        NaturalNumber n = this.constructorTest();
        NaturalNumber nExpected = this.constructorRef(0);

        assertEquals(n, nExpected);
    }

    //test cases to implement: String, NN in string

    /**
     * test string argument constructor, Reason: routine.
     */
    @Test
    public void test1ConstructorString() {
        /*
         * Set up variables
         */
        NaturalNumber n = this.constructorTest("123");
        NaturalNumber nExpected = this.constructorRef("123");
        /*
         * assert calls
         */
        assertEquals(n, nExpected);
    }

    /*
     * Test cases for multiplyBy10 routine cases below
     *
     */

    /**
     * Multiplies NN by 8 Reason- Routine.
     */
    @Test
    public final void test1multiplyBy8() {

        final int ten = 10;
        NaturalNumber n = this.constructorTest(ten);
        NaturalNumber nExpected = this.constructorRef(ten);
        /*
         * call method under test
         */
        n.multiplyBy10(2);
        nExpected.multiplyBy10(2);
        /*
         * assert calls
         */
        assertEquals(n, nExpected);

    }

    /**
     * Test multiplyBy10 using integer 0 on NN 10, Reason: Testing edge case
     * with k=0.
     */
    @Test
    public void testMultiplyBy10WithZero() {
        final int ten = 10;
        NaturalNumber n = this.constructorTest(ten);
        NaturalNumber nExpected = this.constructorRef(ten);
        n.multiplyBy10(0);
        nExpected.multiplyBy10(0);
        assertEquals(n, nExpected);
    }

    /**
     * Test multiplyBy10 using integer 2 on NN 10, Reason: routine.
     */
    @Test
    public void testMultiplyBy10WithTwo() {
        final int ten = 10;
        NaturalNumber n = this.constructorTest(ten);
        NaturalNumber nExpected = this.constructorRef(ten);
        n.multiplyBy10(2);
        nExpected.multiplyBy10(2);
        assertEquals(n, nExpected);
    }

    /**
     * Test multiplyBy10 using integer 2 on NN 0, Reason: Testing with initial
     * NaturalNumber as zero.
     */
    @Test
    public void testMultiplyBy10WithZeroNN() {
        final int zero = 0;
        NaturalNumber n = this.constructorTest(zero);
        NaturalNumber nExpected = this.constructorRef(zero);
        n.multiplyBy10(2);
        nExpected.multiplyBy10(2);
        assertEquals(n, nExpected);
    }

    /**
     * Test multiplyBy10 using integer 5 on NN 1, Reason: Testing with initial
     * NaturalNumber as one.
     */
    @Test
    public void testMultiplyBy10WithOneNN() {
        final int one = 1;
        final int five = 5;
        NaturalNumber n = this.constructorTest(one);
        NaturalNumber nExpected = this.constructorRef(one);
        n.multiplyBy10(five);
        nExpected.multiplyBy10(five);
        assertEquals(n, nExpected);
    }

    /*
     * Below are test cases for the divideby10 method
     */

    /**
     * Test divideBy10 on NN 0, Reason: Testing with initial NaturalNumber as
     * zero.
     */
    @Test
    public void testDivideBy10WithZero() {
        final int zero = 0;
        NaturalNumber n = this.constructorTest(zero);
        NaturalNumber nExpected = this.constructorRef(zero);

        int lastDigit = n.divideBy10();
        int lastDigitExpected = nExpected.divideBy10();

        assertEquals(lastDigit, lastDigitExpected);
        assertEquals(n, nExpected);
    }

    /**
     * Test divideBy10 on NN 10, Reason: Testing with initial NaturalNumber as
     * ten.
     */
    @Test
    public void testDivideBy10WithTen() {
        final int ten = 10;
        NaturalNumber n = this.constructorTest(ten);
        NaturalNumber nExpected = this.constructorRef(ten);

        int lastDigit = n.divideBy10();
        int lastDigitExpected = nExpected.divideBy10();

        assertEquals(lastDigit, lastDigitExpected);
        assertEquals(n, nExpected);
    }

    /**
     * Test divideBy10 on NN 123, Reason: Testing with a multi-digit
     * NaturalNumber.
     */
    @Test
    public void testDivideBy10WithMultiDigit() {
        final int oneTwoThree = 123;
        NaturalNumber n = this.constructorTest(oneTwoThree);
        NaturalNumber nExpected = this.constructorRef(oneTwoThree);

        int lastDigit = n.divideBy10();
        int lastDigitExpected = nExpected.divideBy10();

        assertEquals(lastDigit, lastDigitExpected);
        assertEquals(n, nExpected);
    }

    /**
     * test testMultiplyBy10 using integer 0 on NN 7, Reason: boundary for
     * integer.
     */
    @Test
    public void test3MultiplyBy10() {
        /*
         * Set up variables
         */
        final int seven = 7;
        NaturalNumber n = this.constructorTest(seven);
        NaturalNumber nExpected = this.constructorRef(seven);
        /*
         * call method under test
         */
        n.multiplyBy10(0);
        nExpected.multiplyBy10(0);
        /*
         * assert calls
         */
        assertEquals(n, nExpected);
    }

    /**
     * test testMultiplyBy10 using integer 8 on NN 0, Reason: boundary for NN.
     */
    @Test
    public void test4MultiplyBy10() {
        /*
         * Set up variables
         */
        final int eight = 8;
        NaturalNumber n = this.constructorTest(0);
        NaturalNumber nExpected = this.constructorRef(0);
        /*
         * call method under test
         */
        n.multiplyBy10(eight);
        nExpected.multiplyBy10(eight);
        /*
         * assert calls
         */
        assertEquals(n, nExpected);
    }

    /**
     * Test divideBy10 on NN 5, Reason: Testing with a single-digit
     * NaturalNumber.
     */
    @Test
    public void testDivideBy10WithSingleDigit() {
        final int five = 5;
        NaturalNumber n = this.constructorTest(five);
        NaturalNumber nExpected = this.constructorRef(five);

        int lastDigit = n.divideBy10();
        int lastDigitExpected = nExpected.divideBy10();

        assertEquals(lastDigit, lastDigitExpected);
        assertEquals(n, nExpected);
    }

    /**
     * Test divideBy10 on NN "", Reason-Testing with an empty NaturalNumber.
     */
    @Test
    public void testDivideBy10WithEmpty() {
        final int empty = 0;
        NaturalNumber n = this.constructorTest(empty);
        NaturalNumber nExpected = this.constructorRef(empty);

        int lastDigit = n.divideBy10();
        int lastDigitExpected = nExpected.divideBy10();

        assertEquals(lastDigit, lastDigitExpected);
        assertEquals(n, nExpected);
    }

    /**
     * test testDivideBy10 on NN 0, Reason: boundary, lowest numbers.
     */
    @Test
    public void test1DivideBy10() {
        /*
         * Set up variables
         */
        NaturalNumber n = this.constructorTest(0);
        NaturalNumber nExpected = this.constructorRef(0);
        /*
         * call method under test
         */
        int temp = n.divideBy10();
        int tempExpected = nExpected.divideBy10();
        /*
         * assert calls
         */
        assertEquals(n, nExpected);
        assertEquals(temp, tempExpected);
    }

    /*
     * below are test cases for the isZero method
     */

    /**
     * Test isZero with initial NaturalNumber as zero.Reason- boundary case (the
     * smallest number).
     */
    @Test
    public void testIsZeroWithZero() {
        final int zero = 0;
        NaturalNumber n = this.constructorTest(zero);
        NaturalNumber nExpected = this.constructorRef(zero);

        boolean isZero = n.isZero();
        boolean isZeroExpected = nExpected.isZero();

        assertEquals(isZeroExpected, isZero);
    }

    /**
     * Test isZero with initial NaturalNumber as one Reason-boundary case (the
     * smallest non-zero number).
     */
    @Test
    public void testIsZeroWithOne() {
        final int one = 1;
        NaturalNumber n = this.constructorTest(one);
        NaturalNumber nExpected = this.constructorRef(one);

        boolean isZero = n.isZero();
        boolean isZeroExpected = nExpected.isZero();

        assertEquals(isZeroExpected, isZero);
    }

    /**
     * Test isZero with a multi-digit NaturalNumber. Reason-Routine case
     */
    @Test
    public void testIsZeroWithMultiDigit() {
        final int oneTwoThree = 123;
        NaturalNumber n = this.constructorTest(oneTwoThree);
        NaturalNumber nExpected = this.constructorRef(oneTwoThree);

        boolean isZero = n.isZero();
        boolean isZeroExpected = nExpected.isZero();

        assertEquals(isZeroExpected, isZero);
    }

    /**
     * Test isZero with an empty NaturalNumber. Reason-Routine case
     */
    @Test
    public void testIsZeroWithEmpty() {
        final int empty = 0;
        NaturalNumber n = this.constructorTest(empty);
        NaturalNumber nExpected = this.constructorRef(empty);

        boolean isZero = n.isZero();
        boolean isZeroExpected = nExpected.isZero();

        assertEquals(isZeroExpected, isZero);
    }

    /**
     * test IsZero on NN 0, Reason: Boundary.
     */
    @Test
    public void testIsZeroTrue() {
        /*
         * Set up variables
         */
        NaturalNumber n = this.constructorTest();
        NaturalNumber nExpected = this.constructorRef();
        /*
         * call method under test
         */
        boolean ans = n.isZero();
        boolean ansExpected = nExpected.isZero();
        /*
         * assert calls
         */
        assertEquals(n, nExpected);
        assertEquals(ans, ansExpected);
    }

    /**
     * test IsZero on NN 10, Reason: Boundary.
     */
    @Test
    public void testIsZeroFalse() {
        /*
         * Set up variables
         */
        final int ten = 10;
        NaturalNumber n = this.constructorTest(ten);
        NaturalNumber nExpected = this.constructorRef(ten);
        /*
         * call method under test
         */
        boolean ans = n.isZero();
        boolean ansExpected = nExpected.isZero();
        /*
         * assert calls
         */
        assertEquals(n, nExpected);
        assertEquals(ans, ansExpected);
    }

}
