// --== CS400 Project One File Header ==--
// Name: Huong Thien Do
// CSL Username: tdo
// Email: tdo@wisc.edu
// Lecture #: <001 @11:00am, 002 @1:00pm, 003 @2:25pm>
// Notes to Grader: <any optional extra notes to your grader>

import java.util.*;
/**
 * Red-Black Tree implementation with a Node inner class for representing
 * the nodes of the tree. Currently, this implements a Binary Search Tree that
 * we will turn into a red black tree by modifying the insert functionality.
 * In this activity, we will start with implementing rotations for the binary
 * search tree insert algorithm. You can use this class' insert method to build
 * a regular binary search tree, and its toString method to display a level-order
 * traversal of the tree.
 */
public class RedBlackTree<T extends Comparable<T>> implements ExtendedSortedCollection<T> {

    /**
     * This class represents a node holding a single value within a binary tree
     * the parent, left, and right child references are always maintained.
     */
    protected static class Node<T> {
        public T data;
        public Node<T> parent; // null for root node
        public Node<T> leftChild;
        public Node<T> rightChild;
        public int blackHeight;

        public Node(T data) {
            this.data = data;
            this.blackHeight = 0;
        }

        /**
         * @return true when this node has a parent and is the left child of
         * that parent, otherwise return false
         */
        public boolean isLeftChild() {
            return parent != null && parent.leftChild == this;
        }

        /**
         * @return the black height of the tree of the current node
         * following by the rule: 0 = red, 1 = black, and 2 = double-black.
         */
        public int blackHeight() {
            return this.blackHeight;
        }

    }

    protected Node<T> root; // reference to root node of tree, null when empty
    protected int size = 0; // the number of values in the tree

    /**
     * Performs a naive insertion into a binary search tree: adding the input
     * data value to a new node in a leaf position within the tree. After
     * this insertion, no attempt is made to restructure or balance the tree.
     * This tree will not hold null references, nor duplicate data values.
     *
     * @param data to be added into this binary search tree
     * @return true if the value was inserted, false if not
     * @throws NullPointerException     when the provided data argument is null
     * @throws IllegalArgumentException when the newNode and subtree contain
     *                                  equal data references
     */
    public boolean insert(T data) throws NullPointerException, IllegalArgumentException {
        // null references cannot be stored within this tree
        if (data == null) throw new NullPointerException(
                "This RedBlackTree cannot store null references.");

        Node<T> newNode = new Node<>(data);
        if (root == null) {
            root = newNode;
            size++;
            this.root.blackHeight = 1;
            return true;
        } // add first node to an empty tree
        else {
            boolean returnValue = insertHelper(newNode, root); // recursively insert into subtree
            if (returnValue) size++;
            else throw new IllegalArgumentException(
                    "This RedBlackTree already contains that value.");
            this.root.blackHeight = 1;
            return returnValue;
        }
    }

    /**
     * Recursive helper method to find the subtree with a null reference in the
     * position that the newNode should be inserted, and then extend this tree
     * by the newNode in that position.
     *
     * @param newNode is the new node that is being added to this tree
     * @param subtree is the reference to a node within this tree which the
     *                newNode should be inserted as a descenedent beneath
     * @return true is the value was inserted in subtree, false if not
     */
    private boolean insertHelper(Node<T> newNode, Node<T> subtree) {
        int compare = newNode.data.compareTo(subtree.data);
        // do not allow duplicate values to be stored within this tree
        if (compare == 0) return false;

            // store newNode within left subtree of subtree
        else if (compare < 0) {
            if (subtree.leftChild == null) { // left subtree empty, add here
                subtree.leftChild = newNode;
                newNode.parent = subtree;
                enforceRBTreePropertiesAfterInsert(newNode);
                return true;
                // Otherwise, continue recursive search for location to insert
            } else return insertHelper(newNode, subtree.leftChild);
        }

        // store newNode within the right subtree of subtree
        else {
            if (subtree.rightChild == null) { // right subtree empty, add here
                subtree.rightChild = newNode;
                newNode.parent = subtree;
                enforceRBTreePropertiesAfterInsert(newNode);
                return true;
                // otherwise, continue recursive search for location to insert
            } else return insertHelper(newNode, subtree.rightChild);
        }
    }
    /**
     * Resolves any Red Black Tree property violations using color swaps and rotations (sometimes recursively)
     * @param newNode red node at which to check for and resolve violations
     */
    protected void enforceRBTreePropertiesAfterInsert(Node<T> newNode) {

        if(newNode == this.root) {
            newNode.blackHeight = 1; //Set new Node is the Black root.
            return;
        }
        Node<T> parent = newNode.parent;
        if(parent.blackHeight > 0) return;
        if(parent == this.root) return;

        //If this parent is RED, fix the violations.
        if(parent.blackHeight == 0) {
            Node<T> grandParent = newNode.parent.parent;
            Node<T> uncle; //The parent's sibling of this new node.
            if (parent.isLeftChild()) { uncle = grandParent.rightChild; }
            else { uncle = grandParent.leftChild; }

            //Case 1: parent's sibling (uncle) is RED, recolor.
            if (uncle != null && uncle.blackHeight == 0) {
                    parent.blackHeight = 1;
                    uncle.blackHeight = 1;
                    grandParent.blackHeight = 0;
                    enforceRBTreePropertiesAfterInsert(grandParent);
                    return;
            }
            //Case 2: parent's sibling(uncle) is BLACK or null
            // and new node and parent are in the SAME side.
            if (newNode.isLeftChild() == parent.isLeftChild()) {
                rotate(parent, grandParent); //Rotation
                int temp = grandParent.blackHeight; //Swap color
                grandParent.blackHeight = parent.blackHeight;
                parent.blackHeight = temp;
            }

            //Case 3: parent's sibling(uncle) is BLACK or null
            // and new node and parent are in the OPPOSITE side.
            else if (newNode.isLeftChild() != parent.isLeftChild()) {
                rotate(newNode, parent);
                enforceRBTreePropertiesAfterInsert(parent);
                return;
            }
        }
    }

    /**
     * Performs the rotation operation on the provided nodes within this tree.
     * When the provided child is a leftChild of the provided parent, this
     * method will perform a right rotation. When the provided child is a
     * rightChild of the provided parent, this method will perform a left rotation.
     * When the provided nodes are not related in one of these ways, this method
     * will throw an IllegalArgumentException.
     *
     * @param child  is the node being rotated from child to parent position
     *               (between these two node arguments)
     * @param parent is the node being rotated from parent to child position
     *               (between these two node arguments)
     * @throws IllegalArgumentException when the provided child and parent
     *                                  node references are not initially (pre-rotation) related that way
     */
    private void rotate(Node<T> child, Node<T> parent) throws IllegalArgumentException {

        if (child == null || parent == null) throw new NullPointerException(
                "This Red Black Tree cannot store null references.");

        //Case 1: Right rotation if this child is a leftChild.
        if (child.isLeftChild()) {
            //Turn this child's leftChild into this parent's rightChild.
            parent.leftChild = child.rightChild;
            //Check if this parent's rightChild is not null to do the right rotation.
            if (child.rightChild != null) {
                child.rightChild.parent = parent; //Set this parent to the rightChild of this parent.
            }

            //Set this parent as a child of this child's parent instead of this child.
            child.parent = parent.parent;

            //2a: Check this parent is the root.
            if (parent.parent == null) this.root = child;
            //2b: Check this parent is the left child of this parent's subtree.
            else if (parent == parent.parent.rightChild) {
                parent.parent.rightChild = child;
            }
            //2c: Check this parent is the right child of this parent's subtree.
            else {
                parent.parent.leftChild = child;
            }
            child.rightChild = parent;
            parent.parent = child;
        }
        //Case 2: Left rotation if this child is a rightChild.
        else {
            //Turn this child's rightChild into this parent's leftChild.
            parent.rightChild = child.leftChild;

            //Check if this parent's leftChild is not null to do the left rotation.
            if (child.leftChild != null) {
                child.leftChild.parent = parent; //Set this parent to the leftChild of this parent.
            }

            //Set this parent as a child of this child's parent instead of this child.
            child.parent = parent.parent;

            //1a: Check this parent is the root.
            if (parent.parent == null) this.root = child;

                //1b: Check this parent is the left child of this parent's subtree.
            else if (parent == parent.parent.leftChild) {
                parent.parent.leftChild = child;
            }
            //1c: Check this parent is the right child of this parent's subtree.
            else {
                parent.parent.rightChild = child;
            }

            //Finally, set this parent as the left child of this child.
            child.leftChild = parent;
            parent.parent = child;
        }
    }

    /**
     * Removes the node with its data for which compareTo() returns 0.
     *
     * @param data the object to be removed.
     * @return true if the object being removed, false otherwise.
     */
    @Override
    public boolean remove(T data) {
        if(data == null) return false;
        Node<T> toRemove = search(root, data); // recursively search into subtree
        if(toRemove == null) return false;
        removeHelper(toRemove);
        this.size--;
        return true;
    }
    /**
     * This method search the removed node by implementing compareTo().
     * @param toRemove is the node that is being removed to this tree.
     * @param data the data that is being removed.
     *
     * @return the node that need to be removed
     */
    private Node<T> search(Node<T> toRemove, T data) {
        if(toRemove == null) {return null;}
        int compare = toRemove.data.compareTo(data);
        // do not allow duplicate values to be stored within this tree
        if(compare < 0) { //Search the right subtree when
            if(toRemove.rightChild == null) return null;
            return search(toRemove.rightChild, data);
        }
        if(compare > 0) {
            if (toRemove.leftChild == null) return null;
            return search(toRemove.leftChild, data);
        }
        return toRemove;
    }

    protected void deleteNode(Node<T> node){
        if(node.isLeftChild()){
            node.parent.leftChild = null;
            return;
        }
        node.parent.rightChild = null;
    }
    /**
     * Recursive helper method to locate the removed node and then delete it.
     * by the newNode in that position.
     * @param toRemove the node needs to be removed
     * @return
     */
    protected void removeHelper(Node<T> toRemove){
        //Case 0: no children
        if(toRemove.leftChild == null && toRemove.rightChild == null){
            if(toRemove == root) {root = null;}
            //Case 0a: toRemove is RED.
            if(toRemove.blackHeight == 0)
                deleteNode(toRemove);
            //Case 0b: toRemove is BLACK, fix the double black violations and delete.
            else {
                fixDoubleBlack(toRemove);
                deleteNode(toRemove);
            }
        }
        //Case 1: toRemove has 1 children
        else if(toRemove.leftChild == null && toRemove.rightChild != null) { //1a: Right child
            toRemove.data = toRemove.rightChild.data;
            deleteNode(toRemove.rightChild);
        }
        else if(toRemove.rightChild == null && toRemove.leftChild != null) { //1b: Left child
            toRemove.data = toRemove.leftChild.data;
            deleteNode(toRemove.leftChild);
        }
        //Case 2: toRemove has 2 children
        //Get the smallest node in the right subtree
        else if (toRemove.leftChild != null && toRemove.rightChild!=null) {
            Node<T> minRight = toRemove.rightChild;
            while (minRight.leftChild != null) {
                minRight = minRight.leftChild;
            }
            //replace node with successor and then call recursive method.
            toRemove.data = minRight.data;
            removeHelper(minRight);
        }
        return;
    }
    /**
     * Resolves any Red Black Tree property violations using color swaps and rotations (sometimes recursively)
     * @param doubleB the node to be removed
     */
    protected void fixDoubleBlack(Node<T> doubleB) {
        if (doubleB.parent == null) {
            doubleB.blackHeight = 1;
            return;
        }
        Node<T> parent = doubleB.parent;
        Node<T> sibling;
        if (doubleB.isLeftChild()) sibling = parent.rightChild;
        else sibling = parent.leftChild;

        //Case 01: RED sibling, rotate and swap color.
        if (sibling.blackHeight == 0) {
            parent.blackHeight = 0;
            sibling.blackHeight = 1;
            rotate(sibling, parent);
            fixDoubleBlack(doubleB);
            return;
        }
        //Case 2: BLACK sibling with no RED children, recolor.
        else if (sibling.blackHeight == 1) {
            if((sibling.leftChild == null && sibling.rightChild ==null) ||
                    (sibling.leftChild != null && sibling.rightChild != null
                            && sibling.leftChild.blackHeight == 1 && sibling.rightChild.blackHeight == 1)) {
                sibling.blackHeight--; //RED
                //doubleB.blackHeight--;
                //Case 1a: RED parent, recolor.
                if (parent.blackHeight == 0) {
                    parent.blackHeight = 1;
                    if (parent == root) {
                        root.blackHeight = 1;
                        return;
                    }
                } else {//Case 1b: BLACK parent, resolve double black parent.
                    //parent.blackHeight = 2;
                    fixDoubleBlack(parent);
                    return;
                }
            }
                //Case 3: BLACK sibling with at least one RED child, rotate + swap color, recolor.
            if (sibling.isLeftChild()) {
                //Case 3.1a: Left BlACK sibling with its RED child on the opposite side.
                if (sibling.leftChild != null && sibling.rightChild.blackHeight == 0) {
                    sibling.blackHeight = sibling.parent.blackHeight;
                    sibling.parent.blackHeight = 1;
                    rotate(sibling, sibling.parent);
                    sibling.rightChild.blackHeight = 1;
                }
                //Case 3.1b: Left BlACK sibling with its RED child on the same side.
                else if (sibling.leftChild != null && sibling.leftChild.blackHeight== 0) {
                    sibling.leftChild.blackHeight = 1;
                    sibling.blackHeight = 0;
                    rotate(sibling.leftChild, sibling);
                    fixDoubleBlack(doubleB);
                }
            } else {
                //Case 3.2a: right BlACK sibling with its RED child on the opposite side.
                if (sibling.rightChild != null && sibling.leftChild.blackHeight == 0) {
                    sibling.blackHeight = sibling.parent.blackHeight;
                    sibling.parent.blackHeight = 1;
                    rotate(sibling, sibling.parent);
                    sibling.leftChild.blackHeight = 1;
                    //doubleB.blackHeight--; //Resolve the double black
                }
                //Case 3.2b: right BlACK sibling with its RED child on the same side.
                else if (sibling.rightChild != null && sibling.rightChild.blackHeight == 0) {
                    sibling.rightChild.blackHeight = 1;
                    sibling.blackHeight = 0;
                    rotate(sibling.rightChild, sibling);
                    fixDoubleBlack(doubleB);
                }
            }
        }
    }

    /**
     * Override an iterator over the red-black tree.
     * @return iterator object that traverses the tree in in-order traversal.
     */

    public Iterator<T> iterator() {
        return new Iterator<>() {
            Stack<Node<T>> stack = null; //Initialize the stack
            Node<T> current = root; //set current node = root

            /**
             * Returns boolean if the iteration has more elements.
             * @return boolean if the iteration has more elements
             */
            @Override
            public boolean hasNext(){
                return current != null || !(stack == null || stack.isEmpty());
            }
            /**
             * Returns the next element in the iteration.
             *
             * @throws NoSuchElementException if the iteration has no more elements
             * @return the next element in the iteration
             */
            @Override
            public T next() {
                // Initialize the stack traversal when stepping into the root.
                if (stack == null) {
                    stack = new Stack<>();
                    current = root;
                }
                // Recursive to traversal the left subtree until the leftmost leaf is null.
                while (current != null) {
                    stack.push(current);
                    current = current.leftChild;
                }
                //Then traversal the right subtree until the rightmost leaf is null.
                if(!stack.isEmpty()) {
                    Node<T> topNode = stack.pop();
                    current = topNode.rightChild;
                    return topNode.data;
                }
                // if the stack is empty, the traversal is done!
                else {
                    throw new NoSuchElementException("There are no more elements to traversal.");
                }
            }
        };
    }
    /**
     * Get the size of the tree (its number of nodes).
     *
     * @return the number of nodes in the tree
     */
    public int size() {
        return size;
    }

    /**
     * Method to check if the tree is empty (does not contain any node).
     *
     * @return true of this.size() return 0, false if this.size() > 0
     */
    public boolean isEmpty() {
        return this.size() == 0;
    }

    /**
     * Checks whether the tree contains the value *data*.
     *
     * @param data the data value to test for
     * @return true if *d1ata* is in the tree, false if it is not in the tree
     */
    @Override
    public boolean contains(T data) {
        // null references will not be stored within this tree
        if (data == null) throw new NullPointerException(
                "This RedBlackTree cannot store null references.");
        return this.containsHelper(data, root);
    }

    /**
     * Recursive helper method that recurses through the tree and looks
     * for the value *data*.
     *
     * @param data    the data value to look for
     * @param subtree the subtree to search through
     * @return true of the value is in the subtree, false if not
     */
    private boolean containsHelper(T data, Node<T> subtree) {
        if (subtree == null) {
            // we are at a null child, value is not in tree
            return false;
        } else {
            int compare = data.compareTo(subtree.data);
            if (compare < 0) {
                // go left in the tree
                return containsHelper(data, subtree.leftChild);
            } else if (compare > 0) {
                // go right in the tree
                return containsHelper(data, subtree.rightChild);
            } else {
                // we found it :)
                return true;
            }
        }
    }

    /**
     * This method performs an inorder traversal of the tree. The string
     * representations of each data value within this tree are assembled into a
     * comma separated string within brackets (similar to many implementations
     * of java.util.Collection, like java.util.ArrayList, LinkedList, etc).
     * Note that this RedBlackTree class implementation of toString generates an
     * inorder traversal. The toString of the Node class class above
     * produces a level order traversal of the nodes / values of the tree.
     *
     * @return string containing the ordered values of this tree (in-order traversal)
     */
    public String toInOrderString() {
        // generate a string of all values of the tree in (ordered) in-order
        // traversal sequence
        StringBuffer sb = new StringBuffer();
        sb.append("[ ");
        sb.append(toInOrderStringHelper("", this.root));
        if (this.root != null) {
            sb.setLength(sb.length() - 2);
        }
        sb.append(" ]");
        return sb.toString();
    }

    private String toInOrderStringHelper(String str, Node<T> node) {
        if (node == null) {
            return str;
        }
        str = toInOrderStringHelper(str, node.leftChild);
        str += (node.data.toString() + ", ");
        str = toInOrderStringHelper(str, node.rightChild);
        return str;
    }

    /**
     * This method performs a level order traversal of the tree rooted
     * at the current node. The string representations of each data value
     * within this tree are assembled into a comma separated string within
     * brackets (similar to many implementations of java.util.Collection).
     * Note that the Node's implementation of toString generates a level
     * order traversal. The toString of the RedBlackTree class below
     * produces an inorder traversal of the nodes / values of the tree.
     * This method will be helpful as a helper for the debugging and testing
     * of your rotation implementation.
     *
     * @return string containing the values of this tree in level order
     */
    public String toLevelOrderString() {
        String output = "[ ";
        if (this.root != null) {
            LinkedList<Node<T>> q = new LinkedList<>();
            q.add(this.root);
            while (!q.isEmpty()) {
                Node<T> next = q.removeFirst();
                if (next.leftChild != null) q.add(next.leftChild);
                if (next.rightChild != null) q.add(next.rightChild);
                output += next.data.toString();
                if (!q.isEmpty()) output += ", ";
            }
        }
        return output + " ]";
    }
    public String toString() {
        return "level order: " + this.toLevelOrderString() +
                "\nin order: " + this.toInOrderString();
    }
}