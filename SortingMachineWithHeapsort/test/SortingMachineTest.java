import static org.junit.Assert.assertEquals;

import java.util.Comparator;

import org.junit.Test;

import components.sortingmachine.SortingMachine;

/**
 * JUnit test fixture for {@code SortingMachine<String>}'s constructor and
 * kernel methods.
 *
 * @author Yahya and Mazin
 *
 */
public abstract class SortingMachineTest {

    /**
     * Invokes the appropriate {@code SortingMachine} constructor for the
     * implementation under test and returns the result.
     *
     * @param order
     *            the {@code Comparator} defining the order for {@code String}
     * @return the new {@code SortingMachine}
     * @requires IS_TOTAL_PREORDER([relation computed by order.compare method])
     * @ensures constructorTest = (true, order, {})
     */
    protected abstract SortingMachine<String> constructorTest(
            Comparator<String> order);

    /**
     * Invokes the appropriate {@code SortingMachine} constructor for the
     * reference implementation and returns the result.
     *
     * @param order
     *            the {@code Comparator} defining the order for {@code String}
     * @return the new {@code SortingMachine}
     * @requires IS_TOTAL_PREORDER([relation computed by order.compare method])
     * @ensures constructorRef = (true, order, {})
     */
    protected abstract SortingMachine<String> constructorRef(
            Comparator<String> order);

    /**
     *
     * Creates and returns a {@code SortingMachine<String>} of the
     * implementation under test type with the given entries and mode.
     *
     * @param order
     *            the {@code Comparator} defining the order for {@code String}
     * @param insertionMode
     *            flag indicating the machine mode
     * @param args
     *            the entries for the {@code SortingMachine}
     * @return the constructed {@code SortingMachine}
     * @requires IS_TOTAL_PREORDER([relation computed by order.compare method])
     * @ensures <pre>
     * createFromArgsTest = (insertionMode, order, [multiset of entries in args])
     * </pre>
     */
    private SortingMachine<String> createFromArgsTest(Comparator<String> order,
            boolean insertionMode, String... args) {
        SortingMachine<String> sm = this.constructorTest(order);
        for (int i = 0; i < args.length; i++) {
            sm.add(args[i]);
        }
        if (!insertionMode) {
            sm.changeToExtractionMode();
        }
        return sm;
    }

    /**
     *
     * Creates and returns a {@code SortingMachine<String>} of the reference
     * implementation type with the given entries and mode.
     *
     * @param order
     *            the {@code Comparator} defining the order for {@code String}
     * @param insertionMode
     *            flag indicating the machine mode
     * @param args
     *            the entries for the {@code SortingMachine}
     * @return the constructed {@code SortingMachine}
     * @requires IS_TOTAL_PREORDER([relation computed by order.compare method])
     * @ensures <pre>
     * createFromArgsRef = (insertionMode, order, [multiset of entries in args])
     * </pre>
     */
    private SortingMachine<String> createFromArgsRef(Comparator<String> order,
            boolean insertionMode, String... args) {
        SortingMachine<String> sm = this.constructorRef(order);
        for (int i = 0; i < args.length; i++) {
            sm.add(args[i]);
        }
        if (!insertionMode) {
            sm.changeToExtractionMode();
        }
        return sm;
    }

    /**
     * Comparator<String> implementation to be used in all test cases. Compare
     * {@code String}s in lexicographic order.
     */
    private static class StringLT implements Comparator<String> {

        @Override
        public int compare(String s1, String s2) {
            return s1.compareToIgnoreCase(s2);
        }

    }

    /**
     * Comparator instance to be used in all test cases.
     */
    private static final StringLT ORDER = new StringLT();

    /*
     * Sample test cases.
     */

    /*
     * Constructor Test
     */

    @Test
    public final void testConstructor() {
        SortingMachine<String> m = this.constructorTest(ORDER);
        SortingMachine<String> mExpected = this.constructorRef(ORDER);
        assertEquals(mExpected, m);
    }

    /*
     * Add Tests
     */

    @Test
    public final void testAdd1() {
        SortingMachine<String> m = this.createFromArgsTest(ORDER, true);
        SortingMachine<String> mExpected = this.createFromArgsRef(ORDER, true,
                "cake");
        m.add("cake");
        assertEquals(mExpected, m);
    }

    @Test
    public final void testAdd2() {
        /*
         * Set up variables
         */
        SortingMachine<String> m = this.createFromArgsTest(ORDER, true, "cake");
        SortingMachine<String> mExpected = this.createFromArgsRef(ORDER, true,
                "cake", "vanilla");
        /*
         * Call method to test
         */
        m.add("vanilla");
        /*
         * Assert values are true
         */
        assertEquals(mExpected, m);
    }

    @Test
    public final void testAdd3() {
        /*
         * Set up variables
         */
        SortingMachine<String> m = this.createFromArgsTest(ORDER, true, "Pizza",
                "Hotdog");
        SortingMachine<String> mExpected = this.createFromArgsRef(ORDER, true,
                "Pizza", "Hotdog", "Burger");
        /*
         * Call method to test
         */
        m.add("Burger");
        /*
         * Assert values are true
         */
        assertEquals(mExpected, m);
    }
    /*
     * changeToExtractionMode Tests
     */

    @Test
    public final void testChangeToExtraction() {
        SortingMachine<String> m = this.createFromArgsTest(ORDER, true, "hi",
                "hello");
        SortingMachine<String> mExpected = this.createFromArgsRef(ORDER, false,
                "hi", "hello");

        m.changeToExtractionMode();
        assertEquals(mExpected, m);
    }

    @Test
    public final void testChangeToExtractionEmpty() {
        SortingMachine<String> m = this.createFromArgsTest(ORDER, true);
        SortingMachine<String> mExpected = this.createFromArgsRef(ORDER, false);

        m.changeToExtractionMode();
        assertEquals(mExpected, m);
    }

    /*
     * removeFirst Tests
     */
    @Test
    public final void testRemoveFirstBase() {
        SortingMachine<String> m = this.createFromArgsTest(ORDER, false, "Abdi",
                "Mohamed", "Mazin");
        SortingMachine<String> mExpected = this.createFromArgsRef(ORDER, false,
                "Mohamed", "Mazin");

        String testString = m.removeFirst();
        String refString = "Abdi";
        assertEquals(mExpected, m);
        assertEquals(refString, testString);
    }

    /*
     * IsInInsertationMode Tests
     */
    @Test
    public final void testIsInInsertionBaseTrue() {
        SortingMachine<String> m = this.createFromArgsTest(ORDER, true, "twin");
        SortingMachine<String> mExpected = this.createFromArgsRef(ORDER, true,
                "twin");

        assertEquals(mExpected, m);
        assertEquals(m.isInInsertionMode(), mExpected.isInInsertionMode());
    }

    @Test
    public final void testIsInInsertionBaseFalse() {
        SortingMachine<String> m = this.createFromArgsTest(ORDER, false,
                "half");
        SortingMachine<String> mExpected = this.createFromArgsRef(ORDER, false,
                "half");

        assertEquals(mExpected, m);
        assertEquals(m.isInInsertionMode(), mExpected.isInInsertionMode());
    }

    @Test
    public final void testIsInInsertionEmpty() {
        SortingMachine<String> m = this.createFromArgsTest(ORDER, true);
        SortingMachine<String> mExpected = this.createFromArgsRef(ORDER, true);

        assertEquals(mExpected, m);
        assertEquals(m.isInInsertionMode(), mExpected.isInInsertionMode());
    }

    /*
     * Order Tests
     */

    @Test
    public final void testOrderBase() {
        SortingMachine<String> m = this.createFromArgsTest(ORDER, true, "wow");
        SortingMachine<String> mExpected = this.createFromArgsRef(ORDER, true,
                "wow");

        assertEquals(mExpected, m);
        assertEquals(m.order(), mExpected.order());
    }

    @Test
    public final void testOrderBase2() {
        SortingMachine<String> m = this.createFromArgsTest(ORDER, false, "key",
                "value");
        SortingMachine<String> mExpected = this.createFromArgsRef(ORDER, false,
                "key", "value");

        assertEquals(mExpected, m);
        assertEquals(m.order(), mExpected.order());
    }

    @Test
    public final void testOrderEmpty() {
        SortingMachine<String> m = this.createFromArgsTest(ORDER, false);
        SortingMachine<String> mExpected = this.createFromArgsRef(ORDER, false);

        assertEquals(mExpected, m);
        assertEquals(m.order(), mExpected.order());
    }

    //Size Tests
    @Test
    public final void testSizeBase() {
        SortingMachine<String> m = this.createFromArgsTest(ORDER, true, "elf");
        SortingMachine<String> mExpected = this.createFromArgsRef(ORDER, true,
                "elf");

        assertEquals(mExpected, m);
        assertEquals(m.size(), mExpected.size());
    }

    @Test
    public final void testSizeBase2() {
        SortingMachine<String> m = this.createFromArgsTest(ORDER, false, "dog",
                "cat");
        SortingMachine<String> mExpected = this.createFromArgsRef(ORDER, false,
                "dog", "cat");

        assertEquals(mExpected, m);
        assertEquals(m.size(), mExpected.size());
    }

    @Test
    public final void testSizeEmpty() {
        SortingMachine<String> m = this.createFromArgsTest(ORDER, true);
        SortingMachine<String> mExpected = this.createFromArgsRef(ORDER, true);

        assertEquals(mExpected, m);
        assertEquals(m.size(), mExpected.size());
    }

}
