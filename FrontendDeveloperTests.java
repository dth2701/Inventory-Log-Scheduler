//// --== CS400 Project Two File Header ==--
//// Name: Nattarach Larptaweepornsup
//// CSL Username: nattarach
//// Email: larptaweepor@wisc.edu
//// Lecture #: <001 @11:00am>
//// Notes to Grader: <any optional extra notes to your grader>
//
//import static org.junit.jupiter.api.Assertions.*;
//import org.junit.jupiter.api.Test;
//
//
//public class FrontendDeveloperTests {
//    /**
//     * this method test the main application and ensure its correctness of functionality
//     */
//    @Test
//    public void testRunApplication() {
//        ILogSchedulerPlaceholderFD backend = new ILogSchedulerPlaceholderFD();
//        ILogSchedulerFrontend frontend = new ILogSchedulerFrontend(backend);
//        TextUITester test;
//        String result;
//
//        // test invalid user input -> String
//        test = new TextUITester("hello\n5\n");
//        frontend.runApplication();
//        result = test.checkOutput();
//        if (!result.contains("Invalid input (please enter 1-5)")) {
//            fail("Invalid output (string) must fail");
//        }
//        // number exceed 5
//        test = new TextUITester("56\n5\n");
//        frontend.runApplication();
//        result = test.checkOutput();
//        if (!result.contains("Invalid input (please enter 1-5)")) {
//            fail("Invalid output > 5 must fail");
//        }
//        // negative number
//        test = new TextUITester("-1\n5\n");
//        frontend.runApplication();
//        result = test.checkOutput();
//        if (!result.contains("Invalid input (please enter 1-5)")) {
//            fail("Invalid output <=0 must fail");
//        }
//        // zero number
//        test = new TextUITester("0\n5\n");
//        frontend.runApplication();
//        result = test.checkOutput();
//        if (!result.contains("Invalid input (please enter 1-5)")) {
//            fail("Invalid output <=0 must fail");
//        }
//    }
//
//    /**
//     * this method test the correctness of testAddOrder() method
//     */
//    @Test
//    public void testAddOrder() {
//        ILogSchedulerPlaceholderFD backend = new ILogSchedulerPlaceholderFD();
//        ILogSchedulerFrontend frontend = new ILogSchedulerFrontend(backend);
//        TextUITester test;
//        String result;
//        // passed in the correct input order, must get the following message correctly
//        test = new TextUITester("1\nchicken\n01/12/2022\n5\n");
//        frontend.runApplication();
//        result = test.checkOutput();
//        if (!result.contains("Order Name: ")) {
//            fail("method doesn't work correctly, must run addOrder() method");
//        }
//        // the below message must show up when adding the order
//        if (!result.contains("Enter expected arrival date of order MM/DD/YYYY: ")) {
//            fail("method doesn't work correctly, must take input as date");
//        }
//        if (!result.contains("Order Added! Expected Arrival Date: ")) {
//            fail("method doesn't work correctly, must add order successfully");
//        }
//
//        // passed in the wrong input (given tomato is restricted), must get the following message
//        // correctly
//        test = new TextUITester("1\ntomato\n11/12/2022\n5\n");
//        frontend.runApplication();
//        result = test.checkOutput();
//        if (!result.contains("Order Name: ")) {
//            fail("method doesn't work correctly, must run addOrder() method");
//        }
//        if (!result.contains("Enter expected arrival date of order MM/DD/YYYY: ")) {
//            fail("method doesn't work correctly, must take input as date");
//        }
//        // case when unable to add the order
//        if (!result.contains("Unable to add the task. Please try again with this format MM/DD/YYYY")) {
//            fail("method doesn't work correctly, must add order successfully");
//        }
//
//    }
//
//    /**
//     * this method test the correctness of displayAllNonArrivalOrder() method
//     */
//    @Test
//    public void testDisplayAllNonArrivalOrder() {
//        ILogSchedulerPlaceholderFD backend = new ILogSchedulerPlaceholderFD();
//        ILogSchedulerFrontend frontend = new ILogSchedulerFrontend(backend);
//        TextUITester test;
//        String result;
//        // addOrder() with parameter chicken then get the list of all non-arrival order
//        test = new TextUITester("1\nchicken\n01/12/2022\n2\n5\n");
//        frontend.runApplication();
//        result = test.checkOutput();
//        // must print as specify below
//        if (!result.contains("All Orders: ")) {
//            fail("method doesn't work correctly, must run displayNonArrivalOrderList() method");
//        }
//        if (!result.contains("chicken - to be delivered by 01/12/2022")) {
//            fail("method doesn't work correctly, must show the correct date and name of order");
//        }
//
//        // addOrder() must get the following message correctly
//        test = new TextUITester("1\nchicken\n01/12/2022\n1\nmango\n02/10/2002\n2\n5\n");
//        frontend.runApplication();
//        result = test.checkOutput();
//        if (!result.contains("All Orders: ")) {
//            fail("method doesn't work correctly, must run displayNonArrivalOrderList() method");
//        }
//        // must display list of orders as specify below
//        if (!result.contains("chicken - to be delivered by 01/12/2022")) {
//            fail("method doesn't work correctly, must show the correct date and name of order");
//        }
//        if (!result.contains("mango - to be delivered by 02/10/2002")) {
//            fail("method doesn't work correctly, must show the correct date and name of order");
//        }
//        // System.out.println(result);
//    }
//
//    /**
//     * this method test the correctness of displayDelayedOrder() method
//     */
//    @Test
//    public void testDisplayDelayedOrder() {
//        ILogSchedulerPlaceholderFD backend = new ILogSchedulerPlaceholderFD();
//        ILogSchedulerFrontend frontend = new ILogSchedulerFrontend(backend);
//        TextUITester test;
//        String result;
//        // addOrder() with parameter chicken then get the list of all delayed order
//        test = new TextUITester("1\norange\n09/12/2021\n3\n5\n");
//        frontend.runApplication();
//        result = test.checkOutput();
//        if (!result.contains("Delayed Orders: ")) {
//            fail("method doesn't work correctly, must run displayNonArrivalOrderList() method");
//        }
//        // must display delayed orders
//        if (!result.contains("orange - to be delivered by 09/12/2021")) {
//            fail("method doesn't work correctly, must show the correct date and name of order");
//        }
//
//        // addOrder() must get the following message correctly with the following input
//        test = new TextUITester("1\nboat\n01/12/2022\n1\nairplane\n02/10/2002\n3\n5\n");
//        frontend.runApplication();
//        result = test.checkOutput();
//        if (!result.contains("Delayed Orders: ")) {
//            fail("method doesn't work correctly, must run displayNonArrivalOrderList() method");
//        }
//        // must display multiple delayed orders below
//        if (!result.contains("boat - to be delivered by 01/12/2022")) {
//            fail("method doesn't work correctly, must show the correct date and name of order");
//        }
//        if (!result.contains("airplane - to be delivered by 02/10/2002")) {
//            fail("method doesn't work correctly, must show the correct date and name of order");
//        }
//        // System.out.println(result);
//    }
//
//    /**
//     * this method test the correctness of flagArrivedOrder
//     */
//    @Test
//    public void testFlagArrivedOrder() {
//        ILogSchedulerPlaceholderFD backend = new ILogSchedulerPlaceholderFD();
//        ILogSchedulerFrontend frontend = new ILogSchedulerFrontend(backend);
//        TextUITester test;
//        String result;
//
//        // on order, must print the following message
//        test = new TextUITester("4\n5\n");
//        frontend.runApplication();
//        result = test.checkOutput();
//        if (!result.contains("No order in the inventory")) {
//            fail("method doesn't work correctly, must check for empty and print correct message");
//        }
//        // passing in the right input with addOrder() adding orange to the List<IOrder>
//        test = new TextUITester("1\norange\n09/12/2021\n4\n1\n4\n5\n");
//        frontend.runApplication();
//        result = test.checkOutput();
//        if (!result.contains("Which order has arrived? (Enter Order Number): ")) {
//            fail("method doesn't work correctly, must show message asking for number of order");
//        }
//        if (!result.contains("orange - to be delivered by 09/12/2021")) {
//            fail("method doesn't work correctly, must show message asking for number of order");
//        }
//        // must show number in front of order
//        if (!result.contains("1) orange - to be delivered by 09/12/2021")) {
//            fail("method doesn't work correctly, must show all orders");
//        }
//        // after deleting the order, the list must be empty
//        if (!result.contains("No order in the inventory")) {
//            fail("method doesn't work correctly, must show message asking for number of order");
//        }
//
//        // passing in the invalid input with date -> passing string for input of order
//        test = new TextUITester("1\norange\n09/12/2021\n4\nwronginput\n5\n");
//        frontend.runApplication();
//        result = test.checkOutput();
//        if (!result.contains("Which order has arrived? (Enter Order Number): ")) {
//            fail("method doesn't work correctly, must show message asking for number of order");
//        }
//        // passing invalid input with string
//        if (!result.contains("Invalid input order, please input number")) {
//            fail("method doesn't work correctly, must show message invalid input");
//        }
//        // passed in the invalid number of order
//        test = new TextUITester("1\norange\n09/12/2021\n4\n999\n5\n");
//        frontend.runApplication();
//        result = test.checkOutput();
//        if (!result.contains("Invalid input order, please enter valid number")) {
//            fail("method doesn't work correctly, must show message invalid number");
//        }
//    }
//
//    /**
//     * this method test the correctness of dateValidator() method
//     */
//    @Test
//    public void testDateValidator() {
//        ILogSchedulerPlaceholderFD backend = new ILogSchedulerPlaceholderFD();
//        ILogSchedulerFrontend frontend = new ILogSchedulerFrontend(backend);
//        TextUITester test;
//        String result;
//        // passing empty date
//        test = new TextUITester("1\norange\n\n12/12/2022\n5\n");
//        frontend.runApplication();
//        result = test.checkOutput();
//        if (!result.contains("Date must not be empty")) {
//            fail("method doesn't work correctly, date passed must show invalid (empty)");
//        }
//
//        // passing in invalid month 14
//        test = new TextUITester("1\norange\n14/02/2020\n12/12/2022\n5\n");
//        frontend.runApplication();
//        result = test.checkOutput();
//        if (!result.contains("Invalid date format")) {
//            fail("method doesn't work correctly, date passed must show invalid (month)");
//        }
//        // passing in invalid month 9, must be 09
//        test = new TextUITester("1\norange\n9/02/2020\n12/12/2022\n5\n");
//        frontend.runApplication();
//        result = test.checkOutput();
//        if (!result.contains("If month less than 10, please use prefix 01-09")) {
//            fail("method doesn't work correctly, date passed must show invalid (month)");
//        }
//
//        // passing in invalid day 0
//        test = new TextUITester("1\norange\n11/0/2020\n12/12/2022\n5\n");
//        frontend.runApplication();
//        result = test.checkOutput();
//        if (!result.contains("If day less than 10, please use prefix 01-09")) {
//            fail("method doesn't work correctly, date passed must show invalid (day)");
//        }
//
//        // passing in invalid day 99
//        test = new TextUITester("1\norange\n11/99/2020\n12/12/2022\n5\n");
//        frontend.runApplication();
//        result = test.checkOutput();
//        if (!result.contains("Invalid date format")) {
//            fail("method doesn't work correctly, date passed must show invalid (day)");
//        }
//
//        // passing in invalid year
//        test = new TextUITester("1\norange\n11/09/3000\n12/12/2022\n5\n");
//        frontend.runApplication();
//        result = test.checkOutput();
//        if (!result.contains("Date is not between 20th and 21th century")) {
//            fail("method doesn't work correctly, date passed must show invalid (day)");
//        }
//    }
//
//    /**
//     * this method test the correctness of quit() method
//     */
//    @Test
//    public void testQuit() {
//        ILogSchedulerPlaceholderFD backend = new ILogSchedulerPlaceholderFD();
//        ILogSchedulerFrontend frontend = new ILogSchedulerFrontend(backend);
//        TextUITester test;
//        String result;
//        test = new TextUITester("5\n");
//        frontend.runApplication();
//        result = test.checkOutput();
//        // must display message as specify below
//        if (!result.contains("===GOODBYE===")) {
//            fail("method doesn't work correctly, must show correct message");
//        }
//    }
//
//    public static void main(String[] args) {
//        ILogSchedulerPlaceholderFD backend = new ILogSchedulerPlaceholderFD();
//        ILogSchedulerFrontend frontend = new ILogSchedulerFrontend(backend);
//        TextUITester test;
//        frontend.runApplication();
//    }
//}