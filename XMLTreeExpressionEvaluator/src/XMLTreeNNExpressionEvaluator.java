import components.naturalnumber.NaturalNumber;
import components.naturalnumber.NaturalNumber2;
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
public final class XMLTreeNNExpressionEvaluator {

    /**
     * Private constructor so this utility class cannot be instantiated.
     */
    private XMLTreeNNExpressionEvaluator() {
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
    private static NaturalNumber evaluate(XMLTree exp) {

        NaturalNumber num = new NaturalNumber2();
        NaturalNumber num1 = new NaturalNumber2();

        //if the first child contains number
        if (exp.child(0).hasAttribute("number")) {

            //initializes it with the integer value of "value" whatever it may be
            num = new NaturalNumber2(
                    Integer.parseInt(exp.child(0).attributeValue("value")));
        } else { //else recursively go to the first child
            evaluate(exp.child(0));
        }

        //if the
        if (exp.child(1).hasAttribute("number")) {
            num1 = new NaturalNumber2(
                    Integer.parseInt(exp.child(1).attributeValue("value")));
        } else {
            evaluate(exp.child(0));
        }

        if (exp.label().equals("times")) { //if label is
            num.multiply(num1); //multiply num to the value

            //if label is divide
        } else if (exp.label().equals("divide")) {
            if (num1.isZero()) {
                Reporter.fatalErrorToConsole("Cannot divide by 0");
            }
            num.divide(num1); //divide by value

            //if label is plus
        } else if (exp.label().equals("plus")) {
            num.add(num1); //add to value

        } else if (exp.label().equals("minus")) { //if label is minus

            //If num1 is greater than num, it means that subtracting num from
            //num1 will result in a negative number.
            if (num1.compareTo(num) > 0) {
                Reporter.fatalErrorToConsole(
                        "Cannot subtract number to be negative");
            }
        }

        //make new NN and copy it from output
        NaturalNumber output = new NaturalNumber2();
        output.copyFrom(num1);
        return output;

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
