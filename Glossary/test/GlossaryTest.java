import static org.junit.Assert.assertEquals;

import org.junit.Test;

import components.set.Set;
import components.set.Set1L;

/**
 * @author Mazin Tagelsir
 *
 */
public class GlossaryTest {

    @Test
    public void generateElements_withSep() {

        final String separatorStr = " \t,";
        Set<Character> separatorSet = new Set1L<>();
        Glossary.generateElements(separatorStr, separatorSet);
        Set<Character> expected = new Set1L<>();
        expected.add(' ');
        expected.add('\t');
        expected.add(',');
        System.out.print(expected);
        System.out.print(separatorSet);
        assertEquals(separatorSet, expected);
    }

    @Test
    public void generateElements_routineCase() {

        final String separatorStr = "abcdef";
        Set<Character> separatorSet = new Set1L<>();
        Glossary.generateElements(separatorStr, separatorSet);
        Set<Character> expected = new Set1L<>();
        expected.add('a');
        expected.add('b');
        expected.add('c');
        expected.add('d');
        expected.add('e');
        expected.add('f');
        assertEquals(separatorSet, expected);
    }

    @Test
    public void generateElements_multipleSameChar() {

        final String separatorStr = ",,,,,,,,";
        Set<Character> separatorSet = new Set1L<>();
        Glossary.generateElements(separatorStr, separatorSet);
        Set<Character> expected = new Set1L<>();
        expected.add(',');
        assertEquals(separatorSet, expected);
    }

    @Test
    public void nextWordOrSeparator_word() {

        final String separatorStr = " \t,";
        Set<Character> separatorSet = new Set1L<>();
        Glossary.generateElements(separatorStr, separatorSet);

        String test = "wordwordword space wordwordword";
        String str = Glossary.nextWordOrSeparator(test, 0, separatorSet);
        assertEquals(str, "wordwordword");

    }

    @Test
    public void nextWordOrSeparator_separator() {

        final String separatorStr = " \t,";
        Set<Character> separatorSet = new Set1L<>();
        Glossary.generateElements(separatorStr, separatorSet);

        String test = " space wordwordword";
        String str = Glossary.nextWordOrSeparator(test, 0, separatorSet);
        assertEquals(str, " ");

    }

    @Test
    public void nextWordOrSeparator_differentPosition() {

        final String separatorStr = " \t,";
        Set<Character> separatorSet = new Set1L<>();
        Glossary.generateElements(separatorStr, separatorSet);

        String test = "mid dle";
        final int three = 3;
        String str = Glossary.nextWordOrSeparator(test, three, separatorSet);
        assertEquals(str, " ");

    }

}
