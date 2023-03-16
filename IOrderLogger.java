// --== CS400 Project Two File Header ==--
// Name: Michael Song
// CSL Username: msong
// Email: mmsong@wisc.edu
// Lecture #: <001 @11:00am>
// Notes to Grader: <any optional extra notes to your grader>

import java.io.File;
import java.util.zip.DataFormatException;
import java.io.FileNotFoundException;
import java.text.ParseException;

/**
 * This interface loads the data of item orders from a provided data set/XML
 * file
 *
 * @author Michael Song
 *
 */
public interface IOrderLogger {

    /**
     * From a given file, parse the XML data and convert it to a tree.
     *
     * @param filepath - XML data file
     * @param tree - RedBlackTree object that will absorb the XML data
     * @throws FileNotFoundException
     * @throws DataFormatException
     */
    void FileToTree(String filepath, RedBlackTree<IOrder> tree)
            throws FileNotFoundException, DataFormatException, ParseException;

    /**
     * Convert a RedBlackTree object to XML format and store it in the file.
     *
     * @param filepath - XML format file to be stored to
     * @param tree - tree to be converted
     * @return true if successfully converted, false if otherwise
     */
    boolean TreeToFile(String filepath, RedBlackTree<IOrder> tree) throws FileNotFoundException;
}