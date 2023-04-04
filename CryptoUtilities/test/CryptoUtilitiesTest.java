import static org.junit.Assert.assertEquals;

import org.junit.Test;

import components.naturalnumber.NaturalNumber;
import components.naturalnumber.NaturalNumber2;

/**
 * @author Mazin Tagelsir
 *
 */
public class CryptoUtilitiesTest {

    /**
     * Test for reducToGCD.
     */
    @Test
    public void testReduceToGCD00() {
        NaturalNumber n = new NaturalNumber2(0);
        NaturalNumber nExpected = new NaturalNumber2(0);
        NaturalNumber m = new NaturalNumber2(0);
        NaturalNumber mExpected = new NaturalNumber2(0);
        CryptoUtilities.reduceToGCD(n, m);
        assertEquals(nExpected, n);
        assertEquals(mExpected, m);
    }

    /**
     * test reduceGCD 19.
     */
    @Test
    public void testIsEven19() {
        final NaturalNumber n = new NaturalNumber2(19);
        final NaturalNumber nExpected = new NaturalNumber2(19);
        boolean result = CryptoUtilities.isEven(n);
        assertEquals(nExpected, n);
        assertEquals(false, result);
    }

    /**
     * test for reduce to GCD.
     */
    @Test
    public void testReduceToGCD3021() {
        final NaturalNumber n = new NaturalNumber2(30);
        final NaturalNumber nExpected = new NaturalNumber2(3);
        final NaturalNumber m = new NaturalNumber2(21);
        NaturalNumber mExpected = new NaturalNumber2(0);
        CryptoUtilities.reduceToGCD(n, m);
        assertEquals(nExpected, n);
        assertEquals(mExpected, m);
    }

    /**
     * test reduceGCD for integer max value and integer max value.
     */
    @Test
    public void testReduceToGCDforIntMaxValue() {
        NaturalNumber n = new NaturalNumber2(Integer.MAX_VALUE);
        n.increment();
        NaturalNumber nExpected = new NaturalNumber2(1);
        NaturalNumber m = new NaturalNumber2(Integer.MAX_VALUE);
        NaturalNumber mExpected = new NaturalNumber2(0);
        CryptoUtilities.reduceToGCD(n, m);
        assertEquals(nExpected, n);
        assertEquals(mExpected, m);
    }

    /**
     * Tests of isEven.
     */
    @Test
    public void testIsEven0() {
        NaturalNumber n = new NaturalNumber2(0);
        NaturalNumber nExpected = new NaturalNumber2(0);
        boolean result = CryptoUtilities.isEven(n);
        assertEquals(nExpected, n);
        assertEquals(true, result);
    }

    /**
     * test powerMod for 12, 0, 4 - lowest exponent number case.
     */
    @Test
    public void testPowerMod1204() {
        final NaturalNumber n = new NaturalNumber2(12);
        final NaturalNumber nExpected = new NaturalNumber2(1);
        NaturalNumber p = new NaturalNumber2(0);
        NaturalNumber pExpected = new NaturalNumber2(0);
        final NaturalNumber m = new NaturalNumber2(4);
        final NaturalNumber mExpected = new NaturalNumber2(4);
        CryptoUtilities.powerMod(n, p, m);
        assertEquals(nExpected, n);
        assertEquals(pExpected, p);
        assertEquals(mExpected, m);
    }

    /**
     * test isPrime2 - 3.
     */
    @Test
    public void testIsPrime23() {
        final NaturalNumber n = new NaturalNumber2(3);
        final NaturalNumber nExpected = new NaturalNumber2(3);
        boolean result = CryptoUtilities.isPrime2(n);
        assertEquals(nExpected, n);
        assertEquals(true, result);
    }

    /**
     * test isPrime2 - 12.
     */
    @Test
    public void testIsPrime2ifEvenComp12() {
        final NaturalNumber n = new NaturalNumber2(12);
        final NaturalNumber nExpected = new NaturalNumber2(12);
        boolean result = CryptoUtilities.isPrime2(n);
        assertEquals(nExpected, n);
        assertEquals(false, result);
    }

    /**
     * Ttest isEven.
     */
    @Test
    public void testIsEven1() {
        NaturalNumber n = new NaturalNumber2(1);
        NaturalNumber nExpected = new NaturalNumber2(1);
        boolean result = CryptoUtilities.isEven(n);
        assertEquals(nExpected, n);
        assertEquals(false, result);
    }

    /**
     * Tests of powerMod - 0,0,2.
     */
    @Test
    public void testPowerMod002() {
        NaturalNumber n = new NaturalNumber2(0);
        NaturalNumber nExpected = new NaturalNumber2(1);
        NaturalNumber p = new NaturalNumber2(0);
        NaturalNumber pExpected = new NaturalNumber2(0);
        NaturalNumber m = new NaturalNumber2(2);
        NaturalNumber mExpected = new NaturalNumber2(2);
        CryptoUtilities.powerMod(n, p, m);
        assertEquals(nExpected, n);
        assertEquals(pExpected, p);
        assertEquals(mExpected, m);
    }

    /**
     * test reduceGCD for 52 and 13.
     */
    @Test
    public void testReduceToGCD5213() {
        final NaturalNumber n = new NaturalNumber2(52);
        final NaturalNumber nExpected = new NaturalNumber2(13);
        final NaturalNumber m = new NaturalNumber2(13);
        final NaturalNumber mExpected = new NaturalNumber2(0);
        CryptoUtilities.reduceToGCD(n, m);
        assertEquals(nExpected, n);
        assertEquals(mExpected, m);
    }

    /**
     * test powerMod - 17, 18,19.
     */
    @Test
    public void testPowerMod171819() {
        final NaturalNumber n = new NaturalNumber2(17);
        final NaturalNumber nExpected = new NaturalNumber2(1);
        final NaturalNumber p = new NaturalNumber2(18);
        final NaturalNumber pExpected = new NaturalNumber2(18);
        final NaturalNumber m = new NaturalNumber2(19);
        final NaturalNumber mExpected = new NaturalNumber2(19);
        CryptoUtilities.powerMod(n, p, m);
        assertEquals(nExpected, n);
        assertEquals(pExpected, p);
        assertEquals(mExpected, m);
    }

    /**
     * test reduceGCD for 100 and 100.
     */
    @Test
    public void testReduceToGCDfor100100() {
        final NaturalNumber n = new NaturalNumber2(100);
        final NaturalNumber nExpected = new NaturalNumber2(100);
        final NaturalNumber m = new NaturalNumber2(100);
        NaturalNumber mExpected = new NaturalNumber2(0);
        CryptoUtilities.reduceToGCD(n, m);
        assertEquals(nExpected, n);
        assertEquals(mExpected, m);
    }

}
