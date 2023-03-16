// --== CS400 Project Two File Header ==--
// Name: Nattarach Larptaweepornsup
// CSL Username: nattarach
// Email: larptaweepor@wisc.edu
// Lecture #: <001 @11:00am>
// Notes to Grader: https://docs.oracle.com/javase/7/docs/api/java/text/SimpleDateFormat.html

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

/**
 * This class provide functionality for user to use the Inventory Log Scheduler application.
 *
 * @author Nattarach Larptaweepornsup
 *
 */
public class LogFrontend implements ILogFrontend {
    Scanner scanner;
    ILogBackend backendLog;

    /**
     * ILogSchedulerPlaceholderFD constructor
     *
     * @param backendLog backend object
     */
    public LogFrontend(ILogBackend backendLog) {
        this.backendLog = backendLog;
    }

    /**
     * starts the application
     */
    @Override
    public void runApplication() {
        scanner = new Scanner(System.in);
        System.out.println("Welcome to the Inventory Log Scheduler!");
        System.out.println("x+x+x+x+x+x+x+x+x+x+x+x+x+x+x+x+x+x+x+x+x");
        displayMainMenu();
    }

    /**
     * display all of the options for user to choose what to do in this application
     */
    @Override
    public void displayMainMenu() {
        System.out.println("Main Menu:");
        System.out.println("   1) Add Order");
        System.out.println("   2) Display All Non-Arrival Orders");
        System.out.println("   3) Display Delayed Orders");
        System.out.println("   4) Flag Order Arrived");
        System.out.println("   5) Quit");

        System.out.println("Select a command to continue:");

        // only take in a number digit from 1-5
        String input = scanner.nextLine();
        try {
            int inputNum = Integer.parseInt(input);
            if (inputNum < 1 || inputNum > 5 || input.length() != 1) {
                throw new NumberFormatException();
            }
        } catch (Exception e) {
            System.out.println("Invalid input (please enter 1-5)");
            displayMainMenu();
        }

        switch (input) {
            case "1":
                addOrder();
                break;
            case "2":
                displayAllNonArrivalOrder();
                break;
            case "3":
                displayDelayedOrder();
                break;
            case "4":
                flagArrivedOrder();
                break;
            case "5":
                quit();
                break;
        }
    }

    /**
     * This method add new orders to the list of non-arrival orders
     */
    @Override
    public void addOrder() {
        // name of the order
        System.out.println("Order Name: ");
        String orderName = scanner.nextLine();
        String expectedDate;
        do {
            // expected arrival date
            System.out.println("Enter expected arrival date of order MM/DD/YYYY: ");
            expectedDate = scanner.nextLine();
            // validate the date
        } while (!dateValidator(expectedDate));

        // adding order
        if (backendLog.addOrder(expectedDate, orderName)) {
            // add successfully
            System.out.print("Order Added! Expected Arrival Date: ");
            System.out.println(expectedDate);
        }
        // fail to add
        else {
            System.out.println("Unable to add the task. Please try again with this format MM/DD/YYYY");
        }
        displayMainMenu();
    }

    /**
     * This method display all of the non-arrival order from the lists
     */
    @Override
    public void displayAllNonArrivalOrder() {
        displayNonArrivalOrderList(backendLog.listOrders(), false);
        displayMainMenu();

    }

    /**
     * This method display all of the delayed orders from the list
     */
    @Override
    public void displayDelayedOrder() {
        displayNonArrivalOrderList(backendLog.listDelayed(), true);
        displayMainMenu();
    }

    /**
     * This method flag the non-arrival order as arrived and delete that order from the lists
     */
    @Override
    public void flagArrivedOrder() {
        List<IOrder> allOrders = backendLog.listOrders();

        // check if list is empty or not
        if (allOrders.isEmpty()) {
            System.out.println("No order in the inventory");
        }

        else {
            // display all orders
            int orderNumber = 0;
            System.out.println("Which order has arrived? (Enter Order Number): ");
            for (IOrder order : allOrders) {
                orderNumber += 1;
                System.out.println(Integer.toString(orderNumber) + ") " + order.getName()
                        + " - to be delivered by " + order.getArrivalDate());
            }
            // user input to flag the order number and removed from list
            int orderNum = 0;
            try {
                orderNum = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Invalid input order, please input number");
            }

            // check if orderNum is valid
            // order must not be more than the number of orders in the list
            if (orderNum > allOrders.size() || orderNum <= 0) {
                System.out.println("Invalid input order, please enter valid number");
            } else {
                IOrder orderToDelete = allOrders.get(orderNum - 1);
                backendLog.deleteOrder(orderToDelete);
            }
        }
        System.out.println();
        displayMainMenu();
    }

    /**
     * This is a helper method to iterate through the order and print out the expected delivery date
     */
    @Override
    public void displayNonArrivalOrderList(List<IOrder> orders, boolean isDelayed) {
        if (isDelayed) {
            System.out.println("Delayed Orders: ");
        } else {
            System.out.println("All Orders: ");
        }
        for (IOrder order : orders) {
            System.out.println(order.getName() + " - to be delivered by " + order.getArrivalDate());
        }
        System.out.println();
    }

    /**
     * Check if the date is valid or not
     *
     * @param dateInput input of date in the format MM/dd/yyyy from the user
     * @return true if date is valid false otherwise
     */
    public boolean dateValidator(String dateInput) {
        // must not be empty
        if (dateInput.trim().equals("")) {
            System.out.println("Date must not be empty");
            return false;
        }
        // input format "MM/dd/yyyy"
        String[] dateformat = dateInput.split("/");
        // split must result in list of 3 including MM, dd, yyyy
        if (dateformat.length == 0 || dateformat.length == 1 || dateformat.length == 2) {
            System.out.println("Invalid date please follow this format: MM/dd/yyyy");
            System.out.println("====================================================");
            return false;
        }
        int year = Integer.parseInt(dateformat[2]);

        // filter year to be in 20th-21th century
        if (year > 2100 || year < 1901) {
            System.out.println("Date is not between 20th and 21th century");
            return false;
        }
        // month must have prefix 0 -> 01-09
        if (dateformat[0].substring(0).length() == 1) {
            System.out.println("If month less than 10, please use prefix 01-09");
            return false;
        }
        // day must have prefix 0 -> 01-09
        if (dateformat[1].substring(0).length() == 1) {
            System.out.println("If day less than 10, please use prefix 01-09");
            return false;
        } else {
            SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
            // input must match the above format
            dateFormat.setLenient(false);
            try {
                @SuppressWarnings("unused")
                Date date = dateFormat.parse(dateInput);
            }
            // invalid date format
            catch (ParseException e) {
                System.out.println("Invalid date format");
                return false;
            }
            return true;
        }
    }

    /**
     * This method quits the program
     */
    public void quit() {
        System.out.println("===GOODBYE===");
        return;
    }
}
