// --== CS400 Project Two File Header ==--
// Name: Michael Song
// CSL Username: msong
// Email: mmsong@wisc.edu
// Lecture #: <001 @11:00am>
// Notes to Grader: <any optional extra notes to your grader>

import java.util.Date;

/**
 * This class creates IOrder objects and defines the getter methods for its data attributes
 *
 * @author Michael Song
 *
 */
public class IOrderImplement implements IOrder {
    private Date arrivalDate;
    private String name;

    /**
     * The constructor of the class that creates IOrder objects
     *
     * @param arrivalDate
     * @param name
     */
    public IOrderImplement(String name, Date arrivalDate) {
        this.arrivalDate = arrivalDate;
        this.name = name;
    }

    /**
     * Returns the expected arrival date of the item order
     *
     * @return the arrival date of the item order
     */
    @Override
    public Date getArrivalDate() {
        return this.arrivalDate;
    }

    /**
     * Returns the name of the order
     *
     * @return the name of the order
     */
    @Override
    public String getName() {
        return this.name;
    }

    /**
     * Compares the arrival dates of two orders based on its year, then month, then day if necessary
     *
     * @param other the order to be compared against
     * @return -1 if the date of the order is earlier than the other, +1 if later, 0 if equal
     */
    @Override
    public int compareTo(IOrder other) {
        int dateCompare = this.arrivalDate.compareTo(other.getArrivalDate());
        return dateCompare;
    }

}