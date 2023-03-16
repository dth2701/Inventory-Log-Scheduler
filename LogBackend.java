// --== CS400 Project One File Header ==--
// Name: nisitha de silva
// CSL Username: nisitha
// Email: ntdesilva@wisc.edu
// Lecture #: <001 @11:00am, 002 @1:00pm, 003 @2:25pm>
// Notes to Grader: <any optional extra notes to your grader>

import java.util.Date;
import java.text.DateFormat;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class LogBackend implements ILogBackend {

    private DateFormat dates = new SimpleDateFormat("MM/dd/yyyy");
    private RedBlackTree<IOrder> tree;

    public LogBackend(RedBlackTree<IOrder> tree) {
        this.tree = tree;
    }

    /**
     * Creates a new order to add to the RB Tree.
     *
     * @param date the date of the order
     * @param item the name of the inventory that was ordered
     * @return false if the order was not added, true if it was added.
     */
    @Override
    public boolean addOrder(String date, String item) {
        try {
            IOrder order = new IOrderImplement(item, dates.parse(date));
            tree.insert(order);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    /**
     * Removes the order object from the red balck tree.
     *
     * @param o the task to remove
     */
    @Override
    public void deleteOrder(IOrder o) {
        try {

            tree.remove(o);
        } catch (Exception e) {
            System.out.print(e);
        }

    }

    /**
     * @return shows all the orders of inventory in a list
     */
    @Override
    public List<IOrder> listOrders() {
        List<IOrder> list = new ArrayList<IOrder>();
        for (IOrder order : tree) {
            list.add(order);
        }
        return list;
    }

    /**
     * @return shows a list of all the orders that have not arrived by the expected
     *         delivery date
     */
    @Override
    public List<IOrder> listDelayed() {
        if (tree.isEmpty()) {
            return new ArrayList<IOrder>();
        }

        Date currentDate = Calendar.getInstance().getTime();
        List<IOrder> list = new ArrayList<IOrder>();
        for (IOrder order : tree) {
            list.add(order);
        }
        List<IOrder> list2 = new ArrayList<IOrder>();
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getArrivalDate().before(currentDate)) {
                list2.add(list.get(i));
            }
        }

        return list2;
    }

}