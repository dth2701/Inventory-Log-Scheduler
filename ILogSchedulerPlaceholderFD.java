//// --== CS400 Project Two File Header ==--
//// Name: Nattarach Larptaweepornsup
//// CSL Username: nattarach
//// Email: larptaweepor@wisc.edu
//// Lecture #: <001 @11:00am>
//// Notes to Grader: https://docs.oracle.com/javase/7/docs/api/java/text/SimpleDateFormat.html
//
//import java.text.ParseException;
//import java.text.SimpleDateFormat;
//import java.util.ArrayList;
//import java.util.Date;
//import java.util.List;
//import java.util.Scanner;
//
///**
// * placeholder class for testing functionality of Frontend Application
// *
// * @author Nattarach Larptaweepornsup
// *
// */
//public class ILogSchedulerPlaceholderFD implements ILogBackend {
//    List<IOrder> backend;
//
//    /**
//     * constructor for creating the object of backend
//     */
//    public ILogSchedulerPlaceholderFD() {
//        backend = new ArrayList<IOrder>();
//    }
//
//    /**
//     * this class is for order object, use as a placeholder for testing Frontend functionality
//     *
//     * @author Nattarach Larptaweepornsup
//     *
//     */
//    class IOrderObj implements IOrder {
//        Date date;
//        String name;
//
//        /**
//         * contructor for order obj
//         *
//         * @param date expected arrival date of order object
//         * @param name name of order
//         */
//        public IOrderObj(Date date, String name) {
//            this.date = date;
//            this.name = name;
//        }
//
//        /**
//         * get date of order
//         */
//        @Override
//        public Date getArrivalDate() {
//            return this.date;
//        }
//
//        /**
//         * get order name
//         */
//        @Override
//        public String getName() {
//            return this.name;
//        }
//
//        /**
//         * compare orders
//         */
//        @Override
//        public int compareTo(IOrder other) {
//            // TODO Auto-generated method stub
//            return 0;
//        }
//    }
//
//    /**
//     * add order to the list
//     */
//    @Override
//    public boolean addOrder(String date, String item) {
//        // placeholder for checking the addOrder, suppose tomato is not allowed in inventory
//        if (item.equals("tomato")) {
//            return false;
//        }
//        IOrderObj newOrder = new IOrderObj(date, item);
//        backend.add(newOrder);
//        return true;
//    }
//
//    /**
//     * delete order from the list
//     */
//    @Override
//    public IOrder deleteOrder(IOrder o) {
//        // TODO Auto-generated method stub
//        IOrder orderToDelete = o;
//        backend.remove(o);
//        return orderToDelete;
//    }
//
//    /**
//     * display all orders from the list
//     */
//    @Override
//    public List<IOrder> listOrders() {
//        return backend;
//    }
//
//    /**
//     * display all delayed orders from the list
//     */
//    @Override
//    public List<IOrder> listDelayed() {
//        // TODO Auto-generated method stub
//        return backend;
//    }
//
//    /**
//     * display all orders from the list in in-order traversal
//     */
//    @Override
//    public List<IOrder> getInOrder(String dateFrom) throws ParseException {
//        // TODO Auto-generated method stub
//        return null;
//    }
//}