
import java.io.FileNotFoundException;
import java.text.ParseException;
import java.util.zip.DataFormatException;
import java.util.List;

public interface ILogBackend {
    /**
     * Creates a new order to add to the RB Tree.
     *
     * @param date the date of the order
     * @param item the name of the inventory that was ordered
     * @return false if the order was not added, true if it was added.
     */
    boolean addOrder(String date, String item);

    /**
     * Removes the order object from the red balck tree.
     *
     * @param o the task to remove
     * @return the deleted order
     */
    void deleteOrder(IOrder o);

    /**
     * @return shows all the orders of inventory in a list
     */
    List<IOrder> listOrders();

    /**
     * @return shows a list of all the orders that have not arrived by the expected
     *         delivery date
     */
    List<IOrder> listDelayed();

}