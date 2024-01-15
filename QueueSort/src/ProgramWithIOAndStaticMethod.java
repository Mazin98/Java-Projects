import components.map.Map;
import components.map.Map.Pair;
import components.map.Map1L;
import components.sequence.Sequence;
import components.sequence.Sequence1L;
import components.set.Set;
import components.set.Set1L;
import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;

/**
 * Put a short phrase describing the program here.
 *
 * @author Put your name here
 *
 */
public final class ProgramWithIOAndStaticMethod {

    /**
     * Private constructor so this utility class cannot be instantiated.
     */
    private ProgramWithIOAndStaticMethod() {
    }

    /**
     * Put a short phrase describing the static method myMethod here.
     */
    private static void myMethod() {
        /*
         * Put your code for myMethod here
         */
    }

//    public boolean swapK() {
//        Map<String, String> swapped = new Map1L();
//        Set<String> uniqueValues = new Set1L();
//        boolean hasDuplicates = false;
//
//        for (Map.Entry<String, String> entry : this.entrySet()) {
//            String key = entry.getKey();
//            String value = entry.getValue();
//            if (uniqueValues.contains(value)) {
//                hasDuplicates = true;
//            }
//            uniqueValues.add(value);
//            swapped.put(value, key);
//        }

    private static boolean swapKeysAndValues() {

        boolean hasDup = false;

        Map<String, String> map = new Map1L<String, String>();

        map.add("A", "5");
        map.add("B", "8");
        map.add("C", "5");

        Set<String> values = new Set1L<String>(); //set for dup values

        Map<String, String> tempMap = new Map1L<String, String>();

        for (Pair<String, String> pair : map) {
            if (values.contains(pair.value())) {
                hasDup = true;
                values.add(pair.value()); //adds set (won't be a duplicate)
            }
            tempMap.add(pair.value(), pair.key()); //adds to new map with
        }
        return hasDup;
    }

    private static void multiFlip(Sequence<Integer> s) {

        SimpleWriter outSimpleWriter = new SimpleWriter1L();
        if (s.length() > 1) {
            Sequence<Integer> tempSequence = new Sequence1L<>();
            s.flip();
            s.extract(0, 1, tempSequence);
            multiFlip(s);
            outSimpleWriter.print(s);
        }

        //1234
        //321

        //s2 = 4

//        int tempIndex = 0;
//
//        Sequence<Integer> s2 = new Sequence1L<>(); //temp
//        s.flip();
//        s2.insert(tempIndex, s);//insert index @ s to s2
//
//        tempIndex++; //dont need?
//
//        if (s.length() >= 1) { //base case
//            multiFlip(s);
//        }
//        s.insert(0, s2);

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

        Sequence<Integer> sequence = new Sequence1L<>();
        sequence.add(0, 1);
        sequence.add(1, 2);
        sequence.add(2, 3);
        sequence.add(3, 4);
        sequence.add(4, 5);
        sequence.add(5, 6);

//        out.print(swapKeysAndValues());

        multiFlip(sequence);

        in.close();
        out.close();
    }

}
