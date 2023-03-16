// --== CS400 Project One File Header ==--
// Name: Huong Thien Do
// CSL Username: tdo
// Email: tdo@wisc.edu
// Lecture #: <001 @11:00am, 002 @1:00pm, 003 @2:25pm>
// Notes to Grader: <any optional extra notes to your grader>
import java.io.FileNotFoundException;
import java.sql.Date;
import java.text.ParseException;
import java.util.NoSuchElementException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.Iterator;
import java.util.zip.DataFormatException;

public class AlgorithmEngineerTests {
    protected RedBlackTree<Integer> myTree= null;
    /**
     * BeforeEach annotation makes a method invocked automatically before each test.
     */
    @BeforeEach
    public void createInstance() {
        myTree =  new RedBlackTree<>();
    }
    /**
     * Tests the getItemsBetween method of the RedBlackTree class in varius cases
     */
    @Test
    public void testIterator(){
        myTree.insert(10);
        myTree.insert(7);
        myTree.insert(20);
        myTree.insert(15);
        //Check  the size
        Iterator<Integer> itr = myTree.iterator();

        try {
            assertEquals(true, itr.hasNext());
            assertEquals(7, itr.next());
            assertEquals(10, itr.next());
            assertEquals(15, itr.next());
            assertEquals(20, itr.next());
            assertEquals(false, itr.hasNext());
        }
        catch (NoSuchElementException e) {
            System.out.print("There is no such element");
        }
    }

    /**
     * Test the case that does not relate to Double Black violation
     * and some special cases.
     */
    @Test
    public void testRemoveRedNode(){
        //Case 0 : No children.
        myTree.insert(10);
        myTree.insert(7);
        myTree.insert(20);
        myTree.insert(15);
        assertEquals(true, myTree.remove(15));
        assertEquals(10, myTree.root.data);
        assertEquals(1, myTree.root.blackHeight);
        assertEquals(20, myTree.root.rightChild.data);
        assertEquals(1, myTree.root.rightChild.blackHeight);
        assertEquals(7, myTree.root.leftChild.data);
        assertEquals(1, myTree.root.leftChild.blackHeight);

        //Special case 1: No element in the tree
        myTree = new RedBlackTree<>();
        assertEquals(false, myTree.remove(5));

        //Special case 2: Remove the invalid node.
        myTree = new RedBlackTree<>();
        myTree.insert(2);
        myTree.insert(3);
        myTree.insert(4);
        assertEquals(false, myTree.remove(1));

        //Special case 3: Remove the null node
        assertEquals(false, myTree.remove(null));

    }

    /**
     * Test case 03 of the enforceRBTreePropertiesAfterRemove() method.
     */
    @Test
    public void testRemoveBlackNode1(){
        //Case 1: Black Sibling with 1 RED child.
        myTree.insert(10);
        myTree.insert(7);
        myTree.insert(20);
        myTree.insert(15);
        assertEquals(true, myTree.remove(20));
        assertEquals(false, myTree.contains(20));
        assertEquals(15, myTree.root.rightChild.data);
        assertEquals(1, myTree.root.rightChild.blackHeight);
        assertEquals(3, myTree.size);

        //Case 2: Black Sibling with 2 RED children.
        myTree = new RedBlackTree<>();
        myTree.insert(10);
        myTree.insert(7);
        myTree.insert(20);
        myTree.insert(15);
        myTree.insert(30);
        assertEquals(true, myTree.remove(20));
        assertEquals(30, myTree.root.rightChild.data);
        assertEquals(1, myTree.root.rightChild.blackHeight);
        assertEquals(15, myTree.root.rightChild.leftChild.data);
        assertEquals(0, myTree.root.rightChild.leftChild.blackHeight);
    }

    /**
     * Test case 01 of the enforceRBTreePropertiesAfterRemove() method.
     */
    @Test
    public void testRemoveBlackNode2(){
        //Case 1: RED sibling, rotate and swap color.
        myTree.insert(10);
        myTree.insert(7);
        myTree.insert(20);
        myTree.insert(15);
        myTree.insert(30);

        myTree.root.leftChild.blackHeight = 1;
        myTree.root.rightChild.blackHeight = 0;
        myTree.root.rightChild.leftChild.blackHeight = 1;
        myTree.root.rightChild.rightChild.blackHeight = 1;

        assertEquals(true, myTree.remove(7));
        assertEquals(20, myTree.root.data);
        assertEquals(1, myTree.root.blackHeight);
        assertEquals(30, myTree.root.rightChild.data);
        assertEquals(1, myTree.root.rightChild.blackHeight);
        assertEquals(10, myTree.root.leftChild.data);
        assertEquals(1, myTree.root.leftChild.blackHeight);
        assertEquals(15, myTree.root.leftChild.rightChild.data);
        assertEquals(0, myTree.root.leftChild.rightChild.blackHeight);
    }
    /**
     * Test case 02 of the enforceRBTreePropertiesAfterRemove() method.
     */
    @Test
    public void testRemoveBlackNode3(){
        //Case 2: BLACK sibling with no RED children, recolor.
        myTree.insert(10);
        myTree.insert(7);
        myTree.insert(20);
        myTree.insert(15);
        myTree.insert(30);
        myTree.root.rightChild.blackHeight = 1;
        myTree.root.rightChild.rightChild.blackHeight = 1;
        myTree.root.rightChild.leftChild.blackHeight = 1;
        myTree.root.leftChild.blackHeight = 1;

        assertEquals(true, myTree.remove(7));
        assertEquals(10, myTree.root.data);
        assertEquals(1, myTree.root.blackHeight);
        assertEquals(20, myTree.root.rightChild.data);
        assertEquals(0, myTree.root.rightChild.blackHeight);
        assertEquals(30, myTree.root.rightChild.rightChild.data);
        assertEquals(0, myTree.root.rightChild.blackHeight);
        assertEquals(15, myTree.root.rightChild.leftChild.data);
        assertEquals(0, myTree.root.rightChild.blackHeight);
    }
    /**
     * Checks the Algorithm Engineer code when running with the Data Wrangler Development code work as expected.
     */
    @Test
    public void CodeReviewOfDataWrangler1() {
        String filepath = "src/orders.xml";
        @SuppressWarnings("unchecked")
        RedBlackTree<IOrder> myTree = new RedBlackTree<>();

        try {
            (new IOrderLoggerImplement()).FileToTree(filepath, myTree);
        } catch (FileNotFoundException | DataFormatException | ParseException e) {
            fail(e);
            return;
        }

        assertEquals(9, myTree.size());

        assertEquals("Sun Oct 01 00:00:00 CDT 2023",myTree.root.data.getArrivalDate().toString());
        assertEquals("Tue Apr 23 00:00:00 CDT 2024",myTree.root.rightChild.data.getArrivalDate().toString());
        assertEquals("Mon Jul 11 00:00:00 CDT 2022",myTree.root.leftChild.data.getArrivalDate().toString());

        assertEquals("Chicken",myTree.root.data.getName());
        assertEquals("Rice",myTree.root.rightChild.data.getName());
        assertEquals("Water",myTree.root.leftChild.data.getName());

    }
    /**
     * Checks the BackendDeveloper code when running with the Data Wrangler code work as expected.
     */
    @Test
    public void CodeReviewOfDataWrangler2() {
        RedBlackTree<IOrder> myTree = new RedBlackTree<>();
        long currentTimeMillis = System.currentTimeMillis();
        IOrderImplement root = new IOrderImplement("root", new Date(currentTimeMillis));
        IOrderImplement right  = new IOrderImplement("right", new Date(currentTimeMillis + 2));
        IOrderImplement left = new IOrderImplement("left", new Date(currentTimeMillis - 1));
        myTree.insert(root);
        myTree.insert(right);
        myTree.insert(left);
        String filepath =  "src/orders2.xml";
        try {
            boolean test = new IOrderLoggerImplement().TreeToFile(filepath, myTree);
            assertEquals(true, test);
            assertEquals("root",root.getName());
            //System.out.println(root.getName());
            assertEquals("right",right.getName());
            assertEquals("left",left.getName());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            fail(e);
        }
    }

    /**
     * Checks the codes of 4 roles work as expected.
     */
    @Test
    public void testIntegration1() {
        RedBlackTree<IOrder> tree = new RedBlackTree<>();
        LogBackend backend = new LogBackend(tree);
        LogFrontend frontend = new LogFrontend(backend);
        TextUITester test;
        String result;
        test = new TextUITester("1\nstrawberry\n01/27/2022\n5\n");
        frontend.runApplication();
        result = test.checkOutput();
        if (!result.contains("Order Name: ")) {
            fail("Fail to ask the order!");
        }
        if (!result.contains("Enter expected arrival date of order MM/DD/YYYY: ")) {
            fail("Fail to ask the date!");
        }
        if (!result.contains("Order Added! Expected Arrival Date: ")) {
            fail("Fail to add the order and date!");
        }
    }
    /**
     * Checks the codes of 4 roles work as expected.
     */
    @Test
    public void testIntegration2() {
        RedBlackTree<IOrder> tree = new RedBlackTree<>();
        LogBackend backend = new LogBackend(tree);
        LogFrontend frontend = new LogFrontend(backend);
        TextUITester test;
        String result;
        test = new TextUITester("1\nstrawberry\n01/27/2021\n3\n5\n");
        frontend.runApplication();
        result = test.checkOutput();
        if (!result.contains("Delayed Orders: ")) {
            fail("Fail to run the method!");
        }
    }
}
