// --== CS400 Project One File Header ==--
// Name: nisitha de silva
// CSL Username: nisitha
// Email: ntdesilva@wisc.edu
// Lecture #: <001 @11:00am, 002 @1:00pm, 003 @2:25pm>
// Notes to Grader: <any optional extra notes to your grader>
import java.io.FileNotFoundException;
import java.text.ParseException;
import java.util.Scanner;
import java.util.zip.DataFormatException;

public class IOrderLoader {

    public static void main(String[] args) throws FileNotFoundException, DataFormatException, ParseException {
        // load the orders from the data file
        RedBlackTree<IOrder> tree = new RedBlackTree<>();
        IOrderLoggerImplement orders = (new IOrderLoggerImplement());
        orders.FileToTree("src/orders.xml", tree);
        // instantiate the backend
        ILogBackend backend = new LogBackend(tree);
        // instantiate the scanner for user input (to be used by the front end)
        Scanner userInputScanner = new Scanner(System.in);

        ILogFrontend frontend = new LogFrontend(backend);
        // start the input loop of the front end
        frontend.runApplication();
    }

}