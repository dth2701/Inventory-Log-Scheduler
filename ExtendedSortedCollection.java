
/**
 * The interface for an iterable sorted collection which you can insert elements into and remove elements from.
 */
public interface ExtendedSortedCollection <T extends Comparable<T>> extends SortedCollectionInterface<T> {

    /**
     * Removes the node with its data for which compareTo() returns 0.
     * @param data the object to be removed.
     * @return true if the object being removed, false otherwise.
     */
    public boolean remove(T data);

}
