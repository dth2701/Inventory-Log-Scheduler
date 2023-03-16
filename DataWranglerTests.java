//// --== CS400 Project Two File Header ==--
//// Name: Michael Song
//// CSL Username: msong
//// Email: mmsong@wisc.edu
//// Lecture #: <001 @11:00am>
//// Notes to Grader: <any optional extra notes to your grader>
//
//import java.io.File;
//import java.io.FileNotFoundException;
//import java.sql.Date;
//import java.util.zip.DataFormatException;
//import org.junit.Test;
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.junit.jupiter.api.Assertions.assertTrue;
//import static org.junit.jupiter.api.Assertions.*;
//
//
//
//public class DataWranglerTests {
//
//    /**
//     * Tests the correctness and functionality of the getName() and getArrivalDate() in
//     * IOrderImplement class
//     */
//    @Test
//    public void test1() {
//        String name = "Test";
//        Date date = new Date(System.currentTimeMillis());
//        IOrderImplement task = new IOrderImplement(name, date);
//
//        assertEquals(name, task.getName());
//        assertEquals(date, task.getArrivalDate());
//    }
//
//    /**
//     * Tests the correctness and functionality of the compareTo() method in IOrderImplement class with
//     * different arrival dates
//     */
//    @Test
//    public void test2() {
//        long currentTimeMillis = System.currentTimeMillis();
//        IOrderImplement firstTask = new IOrderImplement("Test", new Date(currentTimeMillis));
//        IOrderImplement secondTask = new IOrderImplement("Test", new Date(currentTimeMillis + 1000));
//
//        assertTrue(firstTask.compareTo(secondTask) < 0);
//    }
//
//    /**
//     * Tests the correctness and functionality of the compareTo() method in IOrderImplement class with
//     * the same arrival date
//     */
//    @Test
//    public void test3() {
//        Date currentTime = new Date(System.currentTimeMillis());
//        IOrderImplement firstTask = new IOrderImplement("Test", currentTime);
//        IOrderImplement secondTask = new IOrderImplement("Test", currentTime);
//
//        assertTrue(firstTask.compareTo(secondTask) == 0);
//    }
//
//    /**
//     * Tests the correctness and functionality of the FileToTree() method in IOrderLoggerImplement
//     * class
//     */
//    @Test
//    public void test4() {
//        String filepath = "orders.xml";
//        @SuppressWarnings("unchecked")
//        ExtendedSortedCollection<IOrder> tree = new RedBlackTreePH<>(); // placeholder RBT
//
//        try {
//            (new IOrderLoggerImplement()).FileToTree(filepath, tree);
//        } catch (FileNotFoundException | DataFormatException e) {
//            fail(e);
//            return;
//        }
//
//        assertEquals(3, tree.size());
//        assertTrue(tree.contains(new IOrderImplement("Chicken", new Date(10/01/2023))));
//        assertTrue(tree.contains(new IOrderImplement("Milk", new Date(05/06/2023))));
//        assertTrue(tree.contains(new IOrderImplement("Rice", new Date(04/23/2024))));
//    }
//
//    /**
//     * Tests the correctness and functionality of the TreeToFile() method in IOrderLoggerImplement
//     * class
//     */
//    @Test
//    public void test5() {
//        String filepath = "orders.xml";
//        RedBlackTree<IOrder> tree = new RedBlackTree<>(); // placeholder RBT
//
//        for (int i = 0; i < 30; i++) {
//            IOrderImplement task =
//                    new IOrderImplement(Integer.toString(i), new Date(System.currentTimeMillis()));
//            tree.insert(task);
//        }
//        try {
//            boolean successfulWrite = new IOrderLoggerImplement().TreeToFile(filepath, tree);
//            assertTrue(successfulWrite);
//        } catch (FileNotFoundException e) {
//            fail(e);
//            return;
//        }
//    }
//
//}