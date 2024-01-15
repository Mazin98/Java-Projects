import components.map.Map;
import components.program.Program;
import components.program.Program1;
import components.queue.Queue;
import components.set.Set;
import components.set.Set1L;
import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;
import components.statement.Statement;
import components.utilities.Reporter;
import components.utilities.Tokenizer;

/**
 * Layered implementation of secondary method {@code parse} for {@code Program}.
 *
 * @author Mazin Tagelsir
 *
 */
public final class Program1Parse1 extends Program1 {

    /*
     * Private members --------------------------------------------------------
     */

    /**
     * Parses a single BL instruction from {@code tokens} returning the
     * instruction name as the value of the function and the body of the
     * instruction in {@code body}.
     *
     * @param tokens
     *            the input tokens
     * @param body
     *            the instruction body
     * @return the instruction name
     * @replaces body
     * @updates tokens
     * @requires <pre>
     * [<"INSTRUCTION"> is a prefix of tokens]  and
     *  [<Tokenizer.END_OF_INPUT> is a suffix of tokens]
     * </pre>
     * @ensures <pre>
     * if [an instruction string is a proper prefix of #tokens]  and
     *    [the beginning name of this instruction equals its ending name]  and
     *    [the name of this instruction does not equal the name of a primitive
     *     instruction in the BL language] then
     *  parseInstruction = [name of instruction at start of #tokens]  and
     *  body = [Statement corresponding to the block string that is the body of
     *          the instruction string at start of #tokens]  and
     *  #tokens = [instruction string at start of #tokens] * tokens
     * else
     *  [report an appropriate error message to the console and terminate client]
     * </pre>
     */
    private static String parseInstruction(Queue<String> tokens,
            Statement body) {
        assert tokens != null : "Violation of: tokens is not null";
        assert body != null : "Violation of: body is not null";
        assert tokens.length() > 0 && tokens.front().equals("INSTRUCTION") : ""
                + "Violation of: <\"INSTRUCTION\"> is proper prefix of tokens";

        Set<String> primitives = new Set1L<>();
        primitives.add("move");
        primitives.add("turnright");
        primitives.add("turnleft");
        primitives.add("infect");
        primitives.add("skip");

        /*
         * This next part checks if the instruction is there
         */
        String movement = tokens.dequeue();
        Reporter.assertElseFatalError(movement.equals("INSTRUCTION"),
                "ERROR: found" + movement + "Instead of INSTRUCTION");

        String dequeudMovementName = tokens.dequeue();
        Reporter.assertElseFatalError(Tokenizer.isIdentifier(movement),
                "Violation of: instruction name is a valid identifier");

        if (primitives.contains(dequeudMovementName)) {
            Reporter.assertElseFatalError(false,
                    "ERROR: Instruction Name must not be a primitive name: "
                            + dequeudMovementName);

        }

        /*
         * this next part will check if IS is there after instruction if its not
         * available , the program will stop and let the user know then parses
         * the body with the statement parse method and checks to see if the END
         * token is there at the end of the sequence
         */

        String instructionIS = tokens.dequeue();

        Reporter.assertElseFatalError(instructionIS.equals("IS"),
                "ERROR: expected" + "IS,but found " + instructionIS);
        body.parseBlock(tokens);

        String instructionEND = tokens.dequeue();

        //will let user know if there is no end
        Reporter.assertElseFatalError(instructionEND.equals("END"),
                "ERROR: expected END, but found " + instructionIS);
        String postInstr = tokens.dequeue();
        Reporter.assertElseFatalError(dequeudMovementName.equals(postInstr),
                "ERROR: expected " + dequeudMovementName
                        + " at the end of instruction, but found " + postInstr);
        //returns instruction name.

        return dequeudMovementName;

    }

    /*
     * Constructors -----------------------------------------------------------
     */

    /**
     * No-argument constructor.
     */
    public Program1Parse1() {
        super();
    }

    /*
     * Public methods ---------------------------------------------------------
     */

    @Override
    public void parse(SimpleReader in) {
        assert in != null : "Violation of: in is not null";
        assert in.isOpen() : "Violation of: in.is_open";
        Queue<String> tokens = Tokenizer.tokens(in);
        this.parse(tokens);
    }

    @Override
    public void parse(Queue<String> tokens) {
        assert tokens != null : "Violation of: tokens is not null";
        assert tokens.length() > 0 : ""
                + "Violation of: Tokenizer.END_OF_INPUT is a suffix of tokens";

        String currentProgram = tokens.dequeue();

        //checks if first token is program

        Reporter.assertElseFatalError(currentProgram.equals("PROGRAM"),
                "ERROR: expected PROGRAM, but found " + currentProgram);

        //dequeues program name
        String programName = tokens.dequeue();
        Reporter.assertElseFatalError(Tokenizer.isIdentifier(programName),
                "Violation of: program name is a valid identifier");

        // dequeues program "IS" if available
        String programIS = tokens.dequeue();

        Reporter.assertElseFatalError(programIS.equals("IS"),
                "ERROR: Expected IS, but found " + programIS);
        String checkStarterToken = tokens.front();

        Map<String, Statement> tokensInfo = this.newContext();

        //keeps checking for different instructions

        while (checkStarterToken.equals("INSTRUCTION")) {
            Statement block = this.newBody();
            String instrName = parseInstruction(tokens, block);
            //takes out an instruction to check.

            for (Map.Pair<String, Statement> val : tokensInfo) {
                //cannot have 2 of the same instructions defined
                Reporter.assertElseFatalError(!val.key().equals(instrName),
                        "ERROR: Instruction Already Defined");
            }

            //tokensInfo.transferFrom(temp);
            tokensInfo.add(instrName, block);
            checkStarterToken = tokens.front();
        }

        //If checkStarterToken is not equal to begin
        Reporter.assertElseFatalError(checkStarterToken.equals("BEGIN"),
                "ERROR: Expected Begin, but found " + checkStarterToken);
        Statement block = this.newBody();

        //removes extra token
        tokens.dequeue();
        block.parseBlock(tokens);

        //check if last token is equal to the end
        String tokenEND = tokens.dequeue();
        Reporter.assertElseFatalError(tokenEND.equals("END"),
                "ERROR: expected END, but found " + tokenEND);

        String programNameEND = tokens.dequeue();
        //checks to see if end is the same as the name of the context body name
        Reporter.assertElseFatalError(programNameEND.equals(programName),
                "ERROR: expected end instruction name" + programName
                        + " but found " + programNameEND);

        //if token.front() isn't empty
        Reporter.assertElseFatalError(
                tokens.front().equals("### END OF INPUT ###"),
                "ERROR: expected end of input, but found " + tokens.front());

        this.swapContext(tokensInfo);
        this.setName(programName);
        this.swapBody(block);

    }

    /*
     * Main test method -------------------------------------------------------
     */

    /**
     * Main method.
     *
     * @param args
     *            the command line arguments
     */
    public static void main(String[] args) {
        SimpleReader in = new SimpleReader1L();
        SimpleWriter out = new SimpleWriter1L();
        /*
         * Get input file name
         */
        out.print("Enter valid BL program file name: ");
        String fileName = in.nextLine();
        /*
         * Parse input file
         */
        out.println("*** Parsing input file ***");
        Program p = new Program1Parse1();
        SimpleReader file = new SimpleReader1L(fileName);
        Queue<String> tokens = Tokenizer.tokens(file);
        file.close();
        p.parse(tokens);
        /*
         * Pretty print the program
         */
        out.println("*** Pretty print of parsed program ***");
        p.prettyPrint(out);

        in.close();
        out.close();
    }

}
