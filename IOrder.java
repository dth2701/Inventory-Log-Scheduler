// --== CS400 Project Two File Header ==--
// Name: Michael Song
// CSL Username: msong
// Email: mmsong@wisc.edu
// Lecture #: <001 @11:00am>
// Notes to Grader: <any optional extra notes to your grader>

import java.util.Date;

/**
 * This interface defines getter methods for each order's data attributes
 *
 * @author Michael Song
 *
 */
public interface IOrder extends Comparable<IOrder>{

    /**
     * Returns the expected arrival date of the item order
     *
     * @return the arrival date of the item order
     */
    Date getArrivalDate();

    /**
     * Returns the name of the order
     *
     * @return the name of the order
     */
    String getName();

    /**
     * Compares the arrival dates of two orders based on its year, then month, then day if necessary
     *
     * @param other the order to be compared against
     * @return -1 if the date of the order is earlier than the other, +1 if later, 0 if equal
     */
    int compareTo(IOrder other);
}