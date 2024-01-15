import static org.junit.Assert.assertEquals;

import org.junit.Test;

import components.sequence.Sequence;
import components.sequence.Sequence1L;

/**
 * Sample JUnit test fixture for SequenceSmooth.
 * 
 * @author Mazin Tagelsir
 * 
 */
public final class SequencePalindrome {

    /**
     * Constructs and returns a sequence of the integers provided as arguments.
     * 
     * @param args
     *            0 or more integer arguments
     * @return the sequence of the given arguments
     * @ensures createFromArgs= [the sequence of integers in args]
     */
    private Sequence<Integer> createFromArgs(Integer... args) {
        Sequence<Integer> s = new Sequence1L<Integer>();
        for (Integer x : args) {
            s.add(s.length(), x);
        }
        return s;
    }

    /**
     * Test smooth with s1 = <2, 4, 6> and s2 = <-5, 12>.
     */
    @Test
    public void test1() {
        /*
         * Set up variables and call method under test
         */
        Sequence<Integer> seq1 = this.createFromArgs(2, 4, 6);
        Sequence<Integer> expectedSeq1 = this.createFromArgs(2, 4, 6);
        Sequence<Integer> seq2 = this.createFromArgs(-5, 12);
        Sequence<Integer> expectedSeq2 = this.createFromArgs(3, 5);
        SequenceSmooth.smooth(seq1, seq2);
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(expectedSeq1, seq1);
        assertEquals(expectedSeq2, seq2);
    }

    /**
     * Test smooth with s1 = <7> and s2 = <13, 17, 11>.
     */
    @Test
    public void test2() {
        /*
         * Set up variables and call method under test
         */
        Sequence<Integer> seq1 = this.createFromArgs(7);
        Sequence<Integer> expectedSeq1 = this.createFromArgs(7);
        Sequence<Integer> seq2 = this.createFromArgs(13, 17, 11);
        Sequence<Integer> expectedSeq2 = this.createFromArgs();
        SequenceSmooth.smooth(seq1, seq2);
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(expectedSeq1, seq1);
        assertEquals(expectedSeq2, seq2);
    }
    
    
    //below are my test cases
    
    
    @Test
    public void testSmallestSequence() {
        s1.add(0, 5);
        SequencePalindrome.smooth(s1, s2); 
        assertTrue(s2.length() == 0);
    }

    @Test
    public void testEvenLengthSequence() {
        s1.add(0, 2);
        s1.add(1, 4);
        s1.add(2, 6);
        s1.add(3, 8);

        Sequence<Integer> expected = new Sequence<>();
        expected.add(0, 3);
        expected.add(1, 5);
        expected.add(2, 7);

        SequencePalindrome.smooth(s1, s2);
        assertEquals(expected, s2);
    }

    @Test
    public void testOddLengthSequence() {
        s1.add(0, 1);
        s1.add(1, 3);
        s1.add(2, 5);
        s1.add(3, 7);
        s1.add(4, 9);

        Sequence<Integer> expected = new Sequence<>();
        expected.add(0, 2);
        expected.add(1, 4);
        expected.add(2, 6);
        expected.add(3, 8);

        SequencePalindrome.smooth(s1, s2);
        assertEquals(expected, s2);
    }

    @Test
    public void testAllNegativeValuesSequence() {
        s1.add(0, -1);
        s1.add(1, -3);
        s1.add(2, -5);
        s1.add(3, -7);

        Sequence<Integer> expected = new Sequence<>();
        expected.add(0, -2);
        expected.add(1, -4);
        expected.add(2, -6);

        SequencePalindrome.smooth(s1, s2);
        assertEquals(expected, s2);
    }

    @Test
    public void testMixedValuesSequence() {
        s1.add(0, 1);
        s1.add(1, -2);
        s1.add(2, 3);
        s1.add(3, -4);
        s1.add(4, 5);

        Sequence<Integer> expected = new Sequence<>();
        expected.add(0, -0.5);
        expected.add(1, 0.5);
        expected.add(2, -0.5);
        expected.add(3, 0.5);

        SequencePalindrome.smooth(s1, s2);
        assertEquals(expected, s2);
    }

    @Test(expected = IllegalArgumentException.class) // Or whatever exception your smooth method throws for |s1| < 1
    public void testErrorCase() {
        SequencePalindrome.smooth(s1, s2);
    }
    

}