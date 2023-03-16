// --== CS400 Project Two File Header ==--
// Name: Nattarach Larptaweepornsup
// CSL Username: nattarach
// Email: larptaweepor@wisc.edu
// Lecture #: <001 @11:00am>
// Notes to Grader: <any optional extra notes to your grader>

import java.util.List;

/**
 * This interface defines method for displaying the working of the application
 *
 * @author Nattarach Larptaweepornsup
 *
 */
public interface ILogFrontend {
    /**
     * Starts the application
     */
    void runApplication();

    /**
     * Prints all of the commands for users to choose
     */
    void displayMainMenu();

    /**
     * Adds an order based on user input of the expected arrival date with order
     * name.
     */
    void addOrder();

    /**
     * Displays all non-arrival orders. Make use of displayNonArrivalOrderList().
     */
    void displayAllNonArrivalOrder();

    /**
     * Displays delayed order. Makes use of displayNonArrivalOrderList().
     */
    void displayDelayedOrder();

    /**
     * Displays all orders and allow the user to enter the number of order to flag
     * and remove them
     *
     */
    void flagArrivedOrder();

    /**
     * Displays a given list of Order.
     *
     * @param orders list of IOrder to be displayed
     */
    void displayNonArrivalOrderList(List<IOrder> orders, boolean isDelayed);
}