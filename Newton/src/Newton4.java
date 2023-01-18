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
public final class Newton4 {

    /**
     * Private constructor so this utility class cannot be instantiated.
     */
    private Newton4() {
    }

    /**
     * Computes estimate of square root of x to within relative error 0.01%.
     *
     * @param x
     *            positive number to compute square root of
     * @param e
     * @return estimate of square root
     */
    private static double sqrt(double x, final double e) {
        double userNum = x;
        final double marginE = 0.01;

        //while loop that checks if number is in range
        while (Math.abs(userNum * userNum - x) / x > marginE * marginE) {
            userNum = (userNum + x / userNum) / 2;
        }

        return userNum;

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

        out.println("Enter a number to have square rooted "
                + "(if you would like to quit type a negative value): ");
        //prompts user for a number
        double num = in.nextDouble();

        while (num >= 0) { //loop to read the number and do something with it

            if (num == 0) {
                //won't work for the number 0
                out.println("You cannot get the square root of 0");
            } else {
                out.print("Enter a value for e: ");
                final double e = in.nextDouble();

                //plugs user number into the previous method
                double numSq = sqrt(num, e);

                out.println("Your number " + num + " is " + numSq
                        + " when square rooted");

                out.println(
                        "Enter a number to have square rooted(if you would like "
                                + "to quit type a negative value):");
                num = in.nextDouble(); // asks user if they want to enter a new number

            }

        }

        out.println("Goodbye");

        in.close();
        out.close();
    }

}
