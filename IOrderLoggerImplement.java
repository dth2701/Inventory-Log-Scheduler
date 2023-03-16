// --== CS400 Project Two File Header ==--
// Name: Michael Song
// CSL Username: msong
// Email: mmsong@wisc.edu
// Lecture #: <001 @11:00am>
// Notes to Grader: <any optional extra notes to your grader>

import java.io.File;
import java.util.zip.DataFormatException;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Scanner;
import java.util.Date;
import java.util.Stack;
import java.util.Iterator;

/**
 * This class contains methods to load orders from a XML file and store them
 * according to its attributes of name and arrival date
 *
 * @author Michael Song
 *
 */
public class IOrderLoggerImplement implements IOrderLogger {

    /**
     * From a given file, parse the XML data and convert it to a tree.
     *
     * @param filepath - XML data file
     * @param tree - RedBlackTree object that will absorb the XML data
     * @throws FileNotFoundException
     * @throws DataFormatException
     * @throws ParseException
     */
    @Override
    public void FileToTree(String filepath, RedBlackTree<IOrder> tree)
            throws FileNotFoundException, DataFormatException, ParseException {
        if (filepath == null) {
            throw new FileNotFoundException("File not found");
        }

        File file = new File(filepath);
        if (!file.exists()) {
            throw new FileNotFoundException("File not found");
        }
        String name = null;
        Date arrivalDate = null;
        Scanner reader = new Scanner(file);
        Stack<String> elements = new Stack<String>();
        while (reader.hasNextLine()) {
            String line = reader.nextLine();
            line = line.trim();

            if (line.charAt(0) == '<') {
                if (line.charAt(line.length() - 1) != '>') {
                    reader.close();
                    throw new DataFormatException("The XML file has an invalid format");
                }

                if (line.charAt(1) == '/') {
                    String child = line.substring(2, line.length() - 1);

                    if (elements.peek().equals(child)) {
                        elements.pop();
                    } else {
                        reader.close();
                        throw new DataFormatException("The XML file has an invalid format");
                    }

                    if (child.equals("order")) {
                        if (name == null || arrivalDate == null) {
                            reader.close();
                            throw new DataFormatException("The XML file has an invalid format");
                        }

                        IOrderImplement order = new IOrderImplement(name, arrivalDate);
                        tree.insert(order);

                        name = null;
                        arrivalDate = null;
                    }
                }

                else {
                    String child = line.substring(1, line.length() - 1);
                    elements.push(child);
                }
            }

            else {
                if (line.charAt(line.length() - 1) == '>') {
                    reader.close();
                    throw new DataFormatException("The XML file has an invalid format");
                }

                if (elements.peek().equals("name")) {
                    name = line;
                }

                else if (elements.peek().equals("arrivaldate")) {
                    arrivalDate = new SimpleDateFormat("MM/dd/yyyy").parse(line);
                }
            }
        }

        reader.close();

        if (elements.size() > 0)
            throw new DataFormatException("The XML file has an invalid format");

    }

    /**
     * Convert a RedBlackTree object (tree) to XML format and store it in the file.
     *
     * @param filepath - file to be stored to
     * @param tree - tree to be converted
     * @return
     * @throws FileNotFoundException
     */
    @Override
    public boolean TreeToFile(String filepath, RedBlackTree<IOrder> tree) throws FileNotFoundException {
        if (filepath == null) {
            throw new FileNotFoundException("File not found");
        }

        File file = new File(filepath);
        if (!file.exists()) {
            throw new FileNotFoundException("File not found");
        }

        PrintWriter writer;
        try {
            file.createNewFile();
            writer = new PrintWriter(file);
        } catch (IOException e) {
            System.out.println("An error has occured while trying to access the file");
            return false;
        }

        Iterator<IOrder> tasks = tree.iterator();

        writer.println("<ordersList>");
        while (tasks.hasNext()) {
            IOrder task = tasks.next();
            writer.println("  <order>");
            writer.println("    <name>" + task.getName() + "</name>");
            writer.println("    <arrivaldate>" + task.getArrivalDate() + "</arrivaldate");
            writer.println("  </order>");
        }
        writer.println("</ordersList>");
        writer.close();
        return true;
    }
}