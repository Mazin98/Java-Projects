import static org.junit.Assert.assertEquals;

import org.junit.Test;

import components.set.Set;

/**
 * JUnit test fixture for {@code Set<String>}'s constructor and kernel methods.
 *
 * @author Mazin Tagelsir & Yahya Elmi
 *
 */
public abstract class SetTest {

    /**
     * Invokes the appropriate {@code Set} constructor for the implementation
     * under test and returns the result.
     *
     * @return the new set
     * @ensures constructorTest = {}
     */
    protected abstract Set<String> constructorTest();

    /**
     * Invokes the appropriate {@code Set} constructor for the reference
     * implementation and returns the result.
     *
     * @return the new set
     * @ensures constructorRef = {}
     */
    protected abstract Set<String> constructorRef();

    /**
     * Creates and returns a {@code Set<String>} of the implementation under
     * test type with the given entries.
     *
     * @param args
     *            the entries for the set
     * @return the constructed set
     * @requires [every entry in args is unique]
     * @ensures createFromArgsTest = [entries in args]
     */
    private Set<String> createFromArgsTest(String... args) {
        Set<String> set = this.constructorTest();
        for (String s : args) {
            assert !set.contains(
                    s) : "Violation of: every entry in args is unique";
            set.add(s);
        }
        return set;
    }

    /**
     * Creates and returns a {@code Set<String>} of the reference implementation
     * type with the given entries.
     *
     * @param args
     *            the entries for the set
     * @return the constructed set
     * @requires [every entry in args is unique]
     * @ensures createFromArgsRef = [entries in args]
     */
    private Set<String> createFromArgsRef(String... args) {
        Set<String> set = this.constructorRef();
        for (String s : args) {
            assert !set.contains(
                    s) : "Violation of: every entry in args is unique";
            set.add(s);
        }
        return set;
    }

    @Test
    public final void testConstructor() {
        Set<String> set = this.constructorTest();
        Set<String> expected = this.constructorRef();

        assertEquals(set, expected);
    }

    /*
     * Adding tests
     */
    @Test
    public final void testAdd() {
        /*
         * Set up variables
         */
        Set<String> s = this.createFromArgsTest("red", "green");
        Set<String> sExpected = this.createFromArgsRef("red", "green", "blue");

        s.add("blue");

        assertEquals(s, sExpected);
    }

    @Test
    public final void testAdd1() {

        Set<String> s = this.createFromArgsTest("red");
        Set<String> sExpected = this.createFromArgsRef("red", "green");

        s.add("green");

        assertEquals(s, sExpected);
    }

    @Test
    public final void testAdd2() {

        Set<String> s = this.createFromArgsTest();
        Set<String> sExpected = this.createFromArgsRef("red");

        s.add("red");

        assertEquals(s, sExpected);
    }

    /*
     * remove methods
     */

    @Test
    public final void testRemove() {

        Set<String> s = this.createFromArgsTest("red", "green", "blue");
        Set<String> sExpected = this.createFromArgsRef("red", "green");

        //call method under test
        s.remove("blue");

        assertEquals(s, sExpected);
    }

    @Test
    public final void testRemove1() {

        Set<String> s = this.createFromArgsTest("red");
        Set<String> sExpected = this.createFromArgsRef();

        //call method under test
        s.remove("red");

        assertEquals(s, sExpected);
    }

    @Test
    public final void testsize() {

        Set<String> s = this.createFromArgsTest("red", "green", "blue");
        Set<String> sExpected = this.createFromArgsRef("red", "green", "blue");

        assertEquals(sExpected.size(), s.size());
    }

    @Test
    public final void testsize1() {

        Set<String> s = this.createFromArgsTest();
        Set<String> sExpected = this.createFromArgsRef();

        assertEquals(sExpected.size(), s.size());
    }

    @Test
    public final void testsize2() {

        Set<String> s = this.createFromArgsTest("one");
        Set<String> sExpected = this.createFromArgsRef("one");

        assertEquals(sExpected.size(), s.size());
    }

    /*
     * Contains methods
     */
    @Test
    public final void testcontains() {

        Set<String> s = this.createFromArgsTest("red", "green", "blue");
        Set<String> sExpected = this.createFromArgsRef("red", "green", "blue");

        //booleans for contains
        boolean ansR = s.contains("blue");
        boolean ansT = sExpected.contains("blue");

        assertEquals(ansR, ansT);
        assertEquals(s, sExpected);
    }

    @Test
    public final void testcontains1() {

        Set<String> s = this.createFromArgsTest();
        Set<String> sExpected = this.createFromArgsRef();

        //booleans if contains
        boolean ansR = s.contains("blue");
        boolean ansT = false;

        assertEquals(ansR, ansT);
        assertEquals(s, sExpected);
    }

    @Test
    public final void testcontains2() {

        Set<String> s = this.createFromArgsTest();
        Set<String> sExpected = this.createFromArgsRef();

        //contains methods for booleans
        boolean ansR = s.contains("blue");
        boolean ansT = false;

        assertEquals(ansR, ansT);
        assertEquals(s, sExpected);
    }

    //fix
    @Test
    public final void testcontains3() {

        Set<String> s = this.createFromArgsTest("green", "yellow");
        Set<String> sExpected = this.createFromArgsRef("green", "yellow");

        //call method in test
        boolean ansR = s.contains("rainbow");
        boolean ansT = false;

        assertEquals(ansR, ansT);
        assertEquals(s, sExpected);
    }

    //fix
    @Test
    public final void testcontains4() {

        Set<String> s = this.createFromArgsTest("green");
        Set<String> sExpected = this.createFromArgsRef("green");

        //boolean for contains
        boolean ansR = s.contains("green");
        boolean ansT = true;

        assertEquals(ansR, ansT);
        assertEquals(s, sExpected);
    }

    /*
     * Remove any methods
     */

    @Test
    public void testRemoveAny() {

        Set<String> s = this.createFromArgsTest("red", "green", "blue");
        Set<String> sExpected = this.createFromArgsRef("red", "green", "blue");

        String temp = s.removeAny();

        //Assert that values of variables match expectations
        assertEquals(true, sExpected.contains(temp));
        sExpected.remove(temp);
        assertEquals(sExpected, s);
    }

    @Test
    public void testRemoveAny1() {

        Set<String> s = this.createFromArgsTest("red");
        Set<String> sExpected = this.createFromArgsRef("red");

        String temp = s.removeAny();

        //Assert that values of variables match expectations
        assertEquals(true, sExpected.contains(temp));
        sExpected.remove(temp);
        assertEquals(sExpected, s);
    }

    @Test
    public void testRemoveAny2() {

        Set<String> s = this.createFromArgsTest("red", "blue", "yellow",
                "green");
        Set<String> sExpected = this.createFromArgsRef("red", "blue", "yellow",
                "green");

        String temp = s.removeAny();

        // Assert that values of variables match expectations
        assertEquals(true, sExpected.contains(temp));
        sExpected.remove(temp);
        assertEquals(sExpected, s);
    }
}
