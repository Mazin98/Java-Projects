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
public final class Newton3 {

    /**
     * Private constructor so this utility class cannot be instantiated.
     */
    private Newton3() {
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

        out.println("Do you wish to calculate another square root?");
        String userInput = in.nextLine();
        userInput = userInput.toLowerCase();

        while (userInput.equals("y")) {

            out.print("What is your number?");
            double num = in.nextDouble();
            out.print("Enter a value for e: ");
            final double e = in.nextDouble();

            if (num == 0) {
                out.println("You cannot get the square root of 0");
            } else {
                double numSq = sqrt(num, e);

                out.println("your number " + num + " is " + numSq
                        + "when square rooted");
                out.println("Would you like to calculate a new number?");
                userInput = in.nextLine();
                userInput = userInput.toLowerCase();
            }

        }

        if (!userInput.equals("y")) {
            out.println("Goodbye");
        }

        in.close();
        out.close();
    }

}
