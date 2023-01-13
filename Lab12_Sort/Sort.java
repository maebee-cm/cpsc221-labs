import java.util.Comparator;
import java.util.ListIterator;

/**
 * Class for sorting lists that implement the IndexedUnsortedList interface,
 * using ordering defined by class of objects in list or a Comparator.
 * As written uses Quicksort algorithm.
 *
 * @author CS221
 */
public class Sort
{
	/**
	 * Returns a new list that implements the IndexedUnsortedList interface.
	 * As configured, uses WrappedDLL. Must be changed if using
	 * your own IUDoubleLinkedList class.
	 *
	 * @return a new list that implements the IndexedUnsortedList interface
	 */
	private static <T> IndexedUnsortedList<T> newList()
	{
		return new WrappedDLL<T>();
	}

	/**
	 * Sorts a list that implements the IndexedUnsortedList interface
	 * using compareTo() method defined by class of objects in list.
	 * DO NOT MODIFY THIS METHOD
	 *
	 * @param <T>
	 *            The class of elements in the list, must extend Comparable
	 * @param list
	 *            The list to be sorted, implements IndexedUnsortedList interface
	 * @see IndexedUnsortedList
	 */
	public static <T extends Comparable<T>> void sort(IndexedUnsortedList<T> list)
	{
		quicksort(list);
	}

	/**
	 * Sorts a list that implements the IndexedUnsortedList interface
	 * using given Comparator.
	 * DO NOT MODIFY THIS METHOD
	 *
	 * @param <T>
	 *            The class of elements in the list
	 * @param list
	 *            The list to be sorted, implements IndexedUnsortedList interface
	 * @param c
	 *            The Comparator used
	 * @see IndexedUnsortedList
	 */
	public static <T> void sort(IndexedUnsortedList <T> list, Comparator<T> c)
	{
		quicksort(list, c);
	}

	/**
	 * Quicksort algorithm to sort objects in a list
	 * that implements the IndexedUnsortedList interface,
	 * using compareTo() method defined by class of objects in list.
	 * DO NOT MODIFY THIS METHOD SIGNATURE
	 *
	 * @param <T>
	 *            The class of elements in the list, must extend Comparable
	 * @param list
	 *            The list to be sorted, implements IndexedUnsortedList interface
	 */
	private static <T extends Comparable<T>> void quicksort(IndexedUnsortedList<T> list)
	{
		// TODO: Implement recursive quicksort algorithm
		quicksort(list, Comparator.naturalOrder());
	}

	/**
	 * Quicksort algorithm to sort objects in a list
	 * that implements the IndexedUnsortedList interface,
	 * using the given Comparator.
	 * DO NOT MODIFY THIS METHOD SIGNATURE
	 *
	 * @param <T>
	 *            The class of elements in the list
	 * @param list
	 *            The list to be sorted, implements IndexedUnsortedList interface
	 * @param c
	 *            The Comparator used
	 */
	private static <T> void quicksort(IndexedUnsortedList<T> list, Comparator<T> c) {
		// no need to sort empty lists or lists with one item
		if(list.size() < 2) {
			return;
		}

		IndexedUnsortedList<T> left = newList();
		IndexedUnsortedList<T> right = newList();
		// Pick the first item as our pivot
		T pivot = list.removeFirst();
		// split the list into left and right. left < pivot, right >= pivot
		int size = list.size();
		for(int i = 0; i < size; i++) {
			if(c.compare(pivot, list.first()) > 0) {
				left.add(list.removeFirst());
			}
			else {
				right.add(list.removeFirst());
			}
		}

		quicksort(left, c);
		quicksort(right, c);

		// recreate the list
		size = left.size();
		for(int i = 0; i < size; i++) {
			list.add(left.removeFirst());
		}
		list.add(pivot);
		size = right.size();
		for(int i = 0; i < size; i++) {
			list.add(right.removeFirst());
		}
	}
}
