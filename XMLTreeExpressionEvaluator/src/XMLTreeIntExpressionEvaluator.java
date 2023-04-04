import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;
import components.utilities.Reporter;
import components.xmltree.XMLTree;
import components.xmltree.XMLTree1;

/**
 * Program to evaluate XMLTree expressions of {@code int}.
 *
 * @author Mazin Tagelsir
 *
 */
public final class XMLTreeIntExpressionEvaluator {

    /**
     * Private constructor so this utility class cannot be instantiated.
     */
    private XMLTreeIntExpressionEvaluator() {
    }

    /**
     * Evaluate the given expression.
     *
     * @param exp
     *            the {@code XMLTree} representing the expression
     * @return the value of the expression
     * @requires <pre>
     * [exp is a subtree of a well-formed XML arithmetic expression]  and
     *  [the label of the root of exp is not "expression"]
     * </pre>
     * @ensures evaluate = [the value of the expression]
     */
    private static int evaluate(XMLTree exp) {
        assert exp != null : "Violation of: exp is not null";

        int val = 0;

        //if the label is number
        if (exp.label().equals("number")) {
            // Get the integer value of the "value" attribute
            val = Integer.parseInt(exp.attributeValue("value"));
        } else if (exp.label().equals("plus")) { //if label is "plus"

            //recursively input the first child + 2nd
            val = evaluate(exp.child(0)) + evaluate(exp.child(1));

            //If the label of the XMLTree is divide
        } else if (exp.label().equals("divide")) {

            //if value is 0 then throw an error
            if (exp.child(1).attributeValue("value").equals("0")) {
                Reporter.fatalErrorToConsole("Error: divide by 0");
            }
//Recursively evaluate the first and second child XMLTrees and divide their values
            val = evaluate(exp.child(0)) / evaluate(exp.child(1));

            //if label is times
        } else if (exp.label().equals("times")) {
//Recursively evaluate the first and second child XMLTrees and multiply their values
            val = evaluate(exp.child(0)) * evaluate(exp.child(1));

            // If the label of the XMLTree is "minus"
        } else if (exp.label().equals("minus")) {
//Recursively evaluate the first and second child XMLTrees and subtract their values
            val = evaluate(exp.child(0)) - evaluate(exp.child(1));
        }

        return val; //returns value in end
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

        out.print("Enter the name of an expression XML file: ");
        String file = in.nextLine();
        while (!file.equals("")) {
            XMLTree exp = new XMLTree1(file);
            out.println(evaluate(exp.child(0)));
            out.print("Enter the name of an expression XML file: ");
            file = in.nextLine();
        }

        in.close();
        out.close();
    }

}
