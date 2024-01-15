import java.util.Comparator;

import components.map.Map;
import components.map.Map.Pair;
import components.map.Map1L;
import components.set.Set;
import components.set.Set1L;
import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;
import components.sortingmachine.SortingMachine;
import components.sortingmachine.SortingMachine1L;
import components.utilities.Reporter;

/**
 * Word occurrences in a given input file and outputs an HTML document with a
 * table of the words and counts listed in alphabetical order.
 *
 *
 * @author Yahya and Mazin
 *
 */

public final class TagCloudGenerator {
    /**
     * No argument constructor--private to prevent instantiation.
     */
    private TagCloudGenerator() {
    }

    /**
     * sort by words String
     */
    private static class StringSort
            implements Comparator<Map.Pair<String, Integer>> {
        @Override
        public int compare(Map.Pair<String, Integer> s1,
                Map.Pair<String, Integer> s2) {
            return s1.key().compareToIgnoreCase(s2.key());
        }
    }

    /**
     *
     * sort by word frequency
     *
     */
    private static class FreqSort
            implements Comparator<Map.Pair<String, Integer>> {
        @Override
        public int compare(Map.Pair<String, Integer> p1,
                Map.Pair<String, Integer> p2) {
            return p2.value().compareTo(p1.value());
        }
    }

    /**
     * Takes in a map and adds the contents in a sorting machine then sorts it
     *
     * @param Map<String,
     *            Integer> wordCount
     * @return sortingmachine
     * @requires Map is not empty
     * @ensures sorted word freq
     *
     */
    public static SortingMachine<Pair<String, Integer>> sortWords(
            Map<String, Integer> wordCount) {

        SortingMachine<Map.Pair<String, Integer>> freqSorted = new SortingMachine1L<>(
                new FreqSort());

        //temporary map
        Map<String, Integer> tempMap = wordCount.newInstance();
        tempMap.transferFrom(wordCount);
        //transfering contents from temp map to sorting machine
        while (tempMap.size() > 0) {
            Map.Pair<String, Integer> pair = tempMap.removeAny();

            freqSorted.add(pair);
        }
        freqSorted.changeToExtractionMode();

        return freqSorted;
    }

    /**
     * Generates the set of characters in the given {@code String} into the
     * given {@code Set}.
     *
     * @param str
     *            the given {@code String}
     * @param charSet
     *            the {@code Set} to be replaced
     * @replaces charSet
     * @ensures charSet = entries(str)
     */
    private static void generateElements(String str, Set<Character> charSet) {
        assert str != null : "Violation of: str is not null";
        assert charSet != null : "Violation of: charSet is not null";

        for (char c : str.toCharArray()) {
            if (!charSet.contains(c)) {
                charSet.add(c);
            }

        }

    }

    /**
     * Takes in and parses an input file from the user and converts it into a
     * map.
     *
     * @param input
     *            the input file
     * @param sort
     *            holds the terms to alphabetically sort them
     *
     * @return returns the map
     * @updates sort
     * @requires in.is_open
     * @ensures map = input
     */

    public static Map<String, Integer> processMap(SimpleReader input,
            SortingMachine<Map.Pair<String, Integer>> sort) {

        // Initialize the word frequency map
        Map<String, Integer> wordCount = new Map1L<>();

        // Set of characters that separate words
        Set<Character> sep = new Set1L<>();
        generateElements(" \t,.'-`~:!?;\"()[]{}", sep); // include all necessary separators

        // Read lines from the input file and process each word
        while (!input.atEOS()) {
            String line = input.nextLine();
            int position = 0; // Initialize position for each line

            // Process each word in the line
            while (position < line.length()) {
                String wordOrSeparator = nextWordOrSeparator(line, position,
                        sep);
                if (!sep.contains(wordOrSeparator.charAt(0))) { // Check if it's a word
                    String word = wordOrSeparator.toLowerCase(); // Convert word to lower case
                    if (!isUndesiredWord(word)) { // Check if the word is desired
                        // Update word frequency in the map
                        if (wordCount.hasKey(word)) {
                            wordCount.replaceValue(word,
                                    wordCount.value(word) + 1);
                        } else {
                            wordCount.add(word, 1);
                        }
                    }
                }
                position += wordOrSeparator.length();
            }
        }

        return wordCount;
    }

    /**
     * Checks whether a given word is considered an undesired word.
     *
     * @param word
     *            the word to check
     * @return true if the word consists only of whitespace characters, '.',
     *         ',', or '-', false otherwise
     * @requires word is not null
     * @ensures <pre>
     * isUndesiredWord =
     *   true, if word consists only of whitespace characters, '.', ',', or '-';
     *   false, otherwise
     * </pre>
     */
    private static boolean isUndesiredWord(String word) {
        for (char c : word.toCharArray()) {
            if (!Character.isWhitespace(c) && c != '.' && c != ',' && c != '-'
                    && c != '!' && c != '?') {
                /*
                 * If any non-whitespace character is found, the word is not
                 * undesired
                 */
                return false;
            }
        }
        // Only whitespace characters were found, the word is undesired
        return true;
    }

    /**
     * Outputs word counts as HTML.
     *
     * @param outPut
     *            Destination for HTML.
     * @param wordCounts
     *            Word counts map.
     * @param inputFileName
     *            Input file name for header.
     * @ensures void
     */
    public static void writeOutput(SimpleWriter outPut,
            Map<String, Integer> wordCounts, String inputFileName,
            int recurrences,
            SortingMachine<Pair<String, Integer>> sortedWords) {

        // Assume minCount and maxCount are obtained from wordCounts Map
        int minCount = Integer.MAX_VALUE;
        int maxCount = 0;

        // Create a second SortingMachine for alphabetical sorting
        SortingMachine<Map.Pair<String, Integer>> alphaSorted = new SortingMachine1L<>(
                new StringSort());

        //create new max and min count based on words
        int i = 0;
        while (i < recurrences) {
            Map.Pair<String, Integer> pair = sortedWords.removeFirst();
            int count = pair.value();
            if (count < minCount) {
                minCount = count;
            }
            if (count > maxCount) {
                maxCount = count;
            }
            alphaSorted.add(pair);
            i++;
        }
        alphaSorted.changeToExtractionMode();

        // Constants for font size
        final int minFontSize = 11; // minimum font size
        final int maxFontSize = 48; // maximum font size

        // Begin HTML structure
        outPut.println("<!DOCTYPE html>");
        outPut.println("<html>");
        outPut.println("<head>");
        outPut.println("<title>Top " + recurrences + " words in "
                + inputFileName + " </title>");
        outPut.println("<link href=\"http://web.cse.ohio-state.edu/software/"
                + "2231/web-sw2/assignments/projects/tag-cloud-generator/"
                + "data/tagcloud.css\" rel=\"stylesheet\" type=\"text/css\">\r\n"
                + "<link href=\"tagcloud.css\" rel=\"stylesheet\" type=\""
                + "text/css\">\r\n");
        outPut.println("</head>");
        outPut.println("<body>");
        outPut.println("<h2>Top " + recurrences + " words in " + inputFileName
                + "   </h2>");
        outPut.println("<hr>");
        outPut.println("<div class=\"cdiv\">");
        outPut.println("<p class=\"cbox\">");

        // Output each word in alphabetical order with font size based on frequency
        while (alphaSorted.size() > 0) {
            Pair<String, Integer> wordPair = alphaSorted.removeFirst();
            String word = wordPair.key();
            int count = wordPair.value();
            int fontSize;

            fontSize = ((maxFontSize - minFontSize) * (count - minCount)
                    / (maxCount - minCount)) + minFontSize;
            System.out.println(maxCount);
            outPut.println("<span class='tag' style='font-size: " + fontSize
                    + "px; line-height: " + fontSize + "px;'>" + word
                    + "</span> ");
        }

        outPut.println("</div>");
        outPut.println("</body>");
        outPut.println("</html>");
        outPut.close();
    }

    /**
     * Returns the first "word" (maximal length string of characters not in
     * {@code separators}) or "separator string" (maximal length string of
     * characters in {@code separators}) in the given {@code text} starting at
     * the given {@code position}.
     *
     * @param text
     *            the {@code String} from which to get the word or separator
     *            string
     * @param position
     *            the starting index
     * @param separators
     *            the {@code Set} of separator characters
     * @return the first word or separator string found in {@code text} starting
     *         at index {@code position}
     * @requires 0 <= position < |text|
     * @ensures <pre>
     * nextWordOrSeparator =
     *   text[position, position + |nextWordOrSeparator|)  and
     * if entries(text[position, position + 1)) intersection separators = {}
     * then
     *   entries(nextWordOrSeparator) intersection separators = {}  and
     *   (position + |nextWordOrSeparator| = |text|  or
     *    entries(text[position, position + |nextWordOrSeparator| + 1))
     *      intersection separators /= {})
     * else
     *   entries(nextWordOrSeparator) is subset of separators  and
     *   (position + |nextWordOrSeparator| = |text|  or
     *    entries(text[position, position + |nextWordOrSeparator| + 1))
     *      is not subset of separators)
     * </pre>
     */
    public static String nextWordOrSeparator(String text, int position,
            Set<Character> separators) {
        assert text != null : "Violation of: text is not null";
        assert separators != null : "Violation of: separators is not null";
        assert 0 <= position : "Violation of: 0 <= position";
        assert position < text.length() : "Violation of: position < |text|";

        //initiate separator to the text's length
        int sep = text.length();

        /*
         * checks the if the first position has a separator then loops thru each
         * position and checks the rest. else, checks if the text is a word and
         * loops thru the rest
         */
        if (separators.contains(text.charAt(position))) {
            for (int i = position; i < text.length()
                    && sep == text.length(); i++) {

                if (!separators.contains(text.charAt(i))) {
                    sep = i;
                }

            }

        } else {
            for (int j = position; j < text.length()
                    && sep == text.length(); j++) {

                if (separators.contains(text.charAt(j))) {
                    sep = j;
                }

            }
        }
        //returns the substring of text from position to the separator
        return text.substring(position, sep);
    }

    /*
     * Main Method
     */
    public static void main(String[] args) {
        SimpleReader in = new SimpleReader1L();
        SimpleWriter out = new SimpleWriter1L();

        out.print("Enter the name of the input file: ");
        String inputFileName = in.nextLine();
        SimpleReader fileIn = new SimpleReader1L(inputFileName);

        out.print("Enter the name of the output file: ");
        String outputFileName = in.nextLine();
        SimpleWriter fileOut = new SimpleWriter1L(outputFileName);

        out.print("Enter the number of words that are in the tag cloud: ");
        int numWords = in.nextInteger();

        Reporter.assertElseFatalError(numWords > 0, "Number must be positive.");

        //create sorting machine
        SortingMachine<Map.Pair<String, Integer>> wordSortingMachine = new SortingMachine1L<>(
                new FreqSort());

        Map<String, Integer> wordCount = processMap(fileIn, wordSortingMachine);
        fileIn.close(); // Close the input file after reading

        for (Map.Pair<String, Integer> entry : wordCount) {
            wordSortingMachine.add(entry);
        }

        if (numWords > wordCount.size()) {
            out.println(
                    "The input file contains less words than requested. The tag cloud will include all unique words.");
            numWords = wordCount.size();
        }

        // Transfer the sorted words into the SortingMachine for alphabetical sorting
        SortingMachine<Pair<String, Integer>> sortedWords = sortWords(
                wordCount);

        writeOutput(fileOut, wordCount, inputFileName, numWords, sortedWords);

        wordSortingMachine.changeToExtractionMode();

        // Close the resources in the reverse order of their opening

        out.close();
        in.close();

    }

}
