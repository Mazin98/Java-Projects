import static org.junit.Assert.assertEquals;

import org.junit.Test;

import components.set.Set;
import components.set.Set1L;
import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;

public class StringReassemblyTest {

    @Test
    public void overlap_ABCDEFG_GJK() {
        String str1 = "ABCDEFG";
        String str2 = "FGA";
        int overlapValue = 2;

        assertEquals(overlapValue, StringReassembly.overlap(str1, str2));
    }

    @Test
    public void addToSetAvoidingSubstrings_NoSubstring_GJK() {
        String str1 = "GJK";
        Set<String> set1 = new Set1L<String>();
        set1.add("GJK");
        set1.add("ABDF");
        set1.add("ADDWW");
        Set<String> set1Expected = new Set1L<String>();
        set1Expected.add("GJK");
        set1Expected.add("ABDF");
        set1Expected.add("ADDWW");

        StringReassembly.addToSetAvoidingSubstrings(set1, str1);
        assertEquals(set1Expected, set1);
    }

    @Test
    public void overlap_ABC_DEF() {
        String str1 = "abc";
        String str2 = "def";
        int overlap = StringReassembly.overlap(str1, str2);
        assertEquals(0, overlap);
    }

    @Test
    public void overlap_kangaroo_ooze() {
        String str1 = "kangaroo";
        String str2 = "ooze";
        int overlap = StringReassembly.overlap(str1, str2);
        assertEquals(2, overlap);
    }

    @Test
    public void combination_batman() {
        String str1 = "batman";
        String str2 = "tmanz";
        int overlap = 4;
        String combine = StringReassembly.combination(str1, str2, overlap);
        assertEquals("batmanz", combine);
    }

    @Test
    public void combination_ABCDE_DEFG_2() {
        String str1 = "ABCDE";
        String str2 = "DEFG";
        int overlapValue = 2;
        String outcome = StringReassembly.combination(str1, str2, overlapValue);
        assertEquals(outcome, "ABCDEFG");
    }

    @Test
    public void PrintWithLineSeparators_firetruck() {
        SimpleWriter out = new SimpleWriter1L("testoutput.txt");
        SimpleReader in = new SimpleReader1L("testoutput.txt");
        String text = "Testing 1 2 3 4~firetruck";
        String expect = "Testing 1 2 3 4" + "\n" + "firetruck";
        StringReassembly.printWithLineSeparators(text, out);
        String test = in.nextLine();
        String test2 = in.nextLine();
        in.close();
        out.close();
        assertEquals(expect, test + "\n" + test2);
    }

    @Test
    public void assemble_Hello() {
        Set<String> strSet = new Set1L<>();
        strSet.add("Hey h");
        strSet.add("y how'");
        strSet.add("w's it g");
        strSet.add("it going?");
        Set<String> expect = new Set1L<>();
        expect.add("Hey how's it going?");
        StringReassembly.assemble(strSet);
        assertEquals(expect, strSet);
    }

    @Test
    public void Assemble_wheensOnTheBus() {
        Set<String> strSet = new Set1L<>();
        strSet.add("The w");
        strSet.add("wheels");
        strSet.add("ls on the bus");
        strSet.add("s go round and round");
        Set<String> expect = new Set1L<>();
        expect.add("The wheels on the bus go round and round");
        StringReassembly.assemble(strSet);
        System.out.println(strSet);
        assertEquals(expect, strSet);
    }

}
