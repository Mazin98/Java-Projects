import java.awt.Cursor;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import components.naturalnumber.NaturalNumber;

/**
 * View class.
 *
 * @author Mazin Tagelsir
 */
public final class NNCalcView1 extends JFrame implements NNCalcView {
    private static final long serialVersionUID = 1L;
    /**
     * Controller object registered with this view to observe user-interaction
     * events.
     */
    private NNCalcController controller;

    /**
     * State of user interaction: last event "seen".
     */
    private enum State {
        /**
         * Last event was clear, enter, another operator, or digit entry, resp.
         */
        SAW_CLEAR, SAW_ENTER_OR_SWAP, SAW_OTHER_OP, SAW_DIGIT
    }

    /**
     * State variable to keep track of which event happened last; needed to
     * prepare for digit to be added to bottom operand.
     */
    private State currentState;

    /**
     * Text areas.
     */
    private final JTextArea tTop, tBottom;

    /**
     * Operator and related buttons.
     */
    private final JButton bClear, bSwap, bEnter, bAdd, bSubtract, bMultiply,
            bDivide, bPower, bRoot;

    /**
     * Digit entry buttons.
     */
    private final JButton[] bDigits;

    /**
     * Useful constants.
     */
    private static final int TEXT_AREA_HEIGHT = 5, TEXT_AREA_WIDTH = 20,
            DIGIT_BUTTONS = 10, MAIN_BUTTON_PANEL_GRID_ROWS = 4,
            MAIN_BUTTON_PANEL_GRID_COLUMNS = 4, SIDE_BUTTON_PANEL_GRID_ROWS = 3,
            SIDE_BUTTON_PANEL_GRID_COLUMNS = 1, CALC_GRID_ROWS = 3,
            CALC_GRID_COLUMNS = 1;

    /**
     * No argument constructor.
     */
    public NNCalcView1() {
        /*
         * Call the JFrame (superclass) constructor with a String parameter to
         * name the window in its title bar
         */
        super("Natural Number Calculator");

        /*
         * Set up initial state of GUI to behave like last event was "Clear";
         * currentState is not a GUI widget per se, but is needed to process
         * digit button events appropriately
         */
        this.currentState = State.SAW_CLEAR;

        this.tTop = new JTextArea(TEXT_AREA_HEIGHT, TEXT_AREA_WIDTH);

        this.tBottom = new JTextArea(TEXT_AREA_HEIGHT, TEXT_AREA_WIDTH);

        this.bClear = new JButton("clear");
        this.bDigits = new JButton[DIGIT_BUTTONS];
        this.bDivide = new JButton("/");
        this.bSwap = new JButton("Swap");
        this.bEnter = new JButton("Enter");
        this.bAdd = new JButton("+");
        this.bSubtract = new JButton("-");
        this.bMultiply = new JButton("*");
        this.bPower = new JButton("Power");

        this.bRoot = new JButton("Root");

        for (int i = 0; i < this.bDigits.length; i++) {
            this.bDigits[i] = new JButton("" + i);
        }

        /*
         * Create widgets - done
         */

        // Set up the GUI widgets --------------------------------------------

        /*
         * Text areas should wrap lines, and should be read-only; they cannot be
         * edited because allowing keyboard entry would require checking whether
         * entries are digits, which we don't want to have to do
         */

        this.tTop.setEditable(false);
        this.tTop.setLineWrap(true);
        this.tTop.setWrapStyleWord(true);

        this.tBottom.setEditable(false);
        this.tBottom.setLineWrap(true);
        this.tBottom.setWrapStyleWord(true);
        /*
         * Initially, the following buttons should be disabled: divide (divisor
         * must not be 0) and root (root must be at least 2) -- hint: see the
         * JButton method setEnabled
         */

        this.bDivide.setEnabled(false);
        this.bRoot.setEnabled(false);
        /*
         * Create scroll panes for the text areas in case number is long enough
         * to require scrolling
         */

        JScrollPane topPane = new JScrollPane(this.tTop);
        JScrollPane bottomPane = new JScrollPane(this.tBottom);

        //main button panel
        JPanel mainButtonPanel = new JPanel(new GridLayout(
                MAIN_BUTTON_PANEL_GRID_ROWS, MAIN_BUTTON_PANEL_GRID_COLUMNS));
        //magic number stuff
        final int seven = 7, eight = 8, nine = 9, four = 4, five = 5, six = 6,
                three = 3;
        mainButtonPanel.add(this.bDigits[seven]);
        mainButtonPanel.add(this.bDigits[eight]);
        mainButtonPanel.add(this.bDigits[nine]);
        mainButtonPanel.add(this.bAdd);
        mainButtonPanel.add(this.bDigits[four]);
        mainButtonPanel.add(this.bDigits[five]);
        mainButtonPanel.add(this.bDigits[six]);
        mainButtonPanel.add(this.bSubtract);
        mainButtonPanel.add(this.bDigits[1]);
        mainButtonPanel.add(this.bDigits[2]);
        mainButtonPanel.add(this.bDigits[three]);
        mainButtonPanel.add(this.bMultiply);
        mainButtonPanel.add(this.bDigits[0]);
        mainButtonPanel.add(this.bPower);
        mainButtonPanel.add(this.bRoot);
        mainButtonPanel.add(this.bDivide); //done w nums
        /*
         * Add the buttons to the main button panel, from left to right and top
         * to bottom
         */
        //create side button panel
        JPanel sideButtonPanel = new JPanel(new GridLayout(
                SIDE_BUTTON_PANEL_GRID_ROWS, SIDE_BUTTON_PANEL_GRID_COLUMNS));
        sideButtonPanel.add(this.bClear);
        sideButtonPanel.add(this.bSwap);
        sideButtonPanel.add(this.bEnter);
        /*
         * Add the buttons to the side button panel, from left to right and top
         * to bottom
         */
        JPanel combinedButtonPanel = new JPanel(new FlowLayout());
        combinedButtonPanel.add(mainButtonPanel);
        combinedButtonPanel.add(sideButtonPanel);
        this.setLayout(new GridLayout(CALC_GRID_ROWS, CALC_GRID_COLUMNS));
        /*
         * Create combined button panel organized using flow layout, which is
         * simple and does the right thing: sizes of nested panels are natural,
         * not necessarily equal as with grid layout
         */
        /*
         * Add scroll panes and button panel to main window, from left to right
         * and top to bottom
         */
        this.add(topPane);
        this.add(bottomPane);
        this.add(combinedButtonPanel);
        this.bDigits[0].addActionListener(this);
        this.bDigits[1].addActionListener(this);
        this.bDigits[2].addActionListener(this);
        this.bDigits[three].addActionListener(this);
        this.bDigits[four].addActionListener(this);
        this.bDigits[five].addActionListener(this);
        this.bDigits[six].addActionListener(this);
        this.bDigits[seven].addActionListener(this);
        this.bDigits[eight].addActionListener(this);
        this.bDigits[nine].addActionListener(this);
        this.bClear.addActionListener(this);
        this.bSwap.addActionListener(this);
        this.bEnter.addActionListener(this);
        this.bAdd.addActionListener(this);
        this.bSubtract.addActionListener(this);
        this.bMultiply.addActionListener(this);
        this.bDivide.addActionListener(this);
        this.bPower.addActionListener(this);
        this.bRoot.addActionListener(this);
        /*
         * Make sure the main window is appropriately sized, exits this program
         * on close, and becomes visible to the user
         */
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.pack();
        this.tTop.setText("0");
        this.tBottom.setText("0");
    }

    @Override
    public void registerObserver(NNCalcController controller) {

        this.controller = controller;

    }

    @Override
    public void updateTopDisplay(NaturalNumber n) {

        String k = n.toString();
        this.tTop.setText(k);

    }

    @Override
    public void updateBottomDisplay(NaturalNumber n) {

        String k = n.toString();
        this.tBottom.setText(k);

    }

    @Override
    public void updateSubtractAllowed(boolean allowed) {

        if (allowed) {
            this.bSubtract.setEnabled(true);
        } else {
            this.bSubtract.setEnabled(false);
        }

    }

    @Override
    public void updateDivideAllowed(boolean allowed) {

        if (allowed) {
            this.bDivide.setEnabled(true);
        } else {
            this.bDivide.setEnabled(false);
        }

    }

    @Override
    public void updatePowerAllowed(boolean allowed) {

        if (allowed) {
            this.bPower.setEnabled(true);
        } else {
            this.bPower.setEnabled(false);
        }

    }

    @Override
    public void updateRootAllowed(boolean allowed) {

        if (allowed) {
            this.bRoot.setEnabled(true);
        } else {
            this.bRoot.setEnabled(false);
        }

    }

    @Override
    public void actionPerformed(ActionEvent event) {
        /*
         * Set cursor to indicate computation on-going; this matters only if
         * processing the event might take a noticeable amount of time as seen
         * by the user
         */
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        /*
         * Determine which event has occurred that we are being notified of by
         * this callback; in this case, the source of the event (i.e, the widget
         * calling actionPerformed) is all we need because only buttons are
         * involved here, so the event must be a button press; in each case,
         * tell the controller to do whatever is needed to update the model and
         * to refresh the view
         */
        Object source = event.getSource();
        if (source == this.bClear) {
            this.controller.processClearEvent();
            this.currentState = State.SAW_CLEAR;
        } else if (source == this.bSwap) {
            this.controller.processSwapEvent();
            this.currentState = State.SAW_ENTER_OR_SWAP;
        } else if (source == this.bEnter) {
            this.controller.processEnterEvent();
            this.currentState = State.SAW_ENTER_OR_SWAP;
        } else if (source == this.bAdd) {
            this.controller.processAddEvent();
            this.currentState = State.SAW_OTHER_OP;
        } else if (source == this.bSubtract) {
            this.controller.processSubtractEvent();
            this.currentState = State.SAW_OTHER_OP;
        } else if (source == this.bMultiply) {
            this.controller.processMultiplyEvent();
            this.currentState = State.SAW_OTHER_OP;
        } else if (source == this.bDivide) {
            this.controller.processDivideEvent();
            this.currentState = State.SAW_OTHER_OP;
        } else if (source == this.bPower) {
            this.controller.processPowerEvent();
            this.currentState = State.SAW_OTHER_OP;
        } else if (source == this.bRoot) {
            this.controller.processRootEvent();
            this.currentState = State.SAW_OTHER_OP;
        } else {
            for (int i = 0; i < DIGIT_BUTTONS; i++) {
                if (source == this.bDigits[i]) {
                    switch (this.currentState) {
                        case SAW_ENTER_OR_SWAP:
                            this.controller.processClearEvent();
                            break;
                        case SAW_OTHER_OP:
                            this.controller.processEnterEvent();
                            this.controller.processClearEvent();
                            break;
                        default:
                            break;
                    }
                    this.controller.processAddNewDigitEvent(i);
                    this.currentState = State.SAW_DIGIT;
                    break;
                }
            }
        }
        /*
         * Set the cursor back to normal (because we changed it at the beginning
         * of the method body)
         */
        this.setCursor(Cursor.getDefaultCursor());
    }

}
