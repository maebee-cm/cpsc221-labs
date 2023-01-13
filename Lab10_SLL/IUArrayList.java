import java.util.*;

/**
 * Array-based implementation of IndexedUnsortedList.
 * 
 * @author 
 *
 * @param <T> type to store
 */
public class IUArrayList<T> implements IndexedUnsortedList<T> {
	private static final int DEFAULT_CAPACITY = 10;
	private static final int NOT_FOUND = -1;
	
	private T[] array;
	private int rear;
	
	/** Creates an empty list with default initial capacity */
	public IUArrayList() {
		this(DEFAULT_CAPACITY);
	}
	
	/** 
	 * Creates an empty list with the given initial capacity
	 * @param initialCapacity
	 */
	@SuppressWarnings("unchecked")
	public IUArrayList(int initialCapacity) {
		array = (T[])(new Object[initialCapacity]);
		rear = 0;
	}
	
	/** Double the capacity of array */
	private void expandCapacity() {
		array = Arrays.copyOf(array, array.length*2);
	}

	@Override
	public void addToFront(T element) {
		add(0, element);
	}

	@Override
	public void addToRear(T element) {
		add(rear, element);
	}

	@Override
	public void add(T element) {
		add(rear, element);
	}

	@Override
	public void addAfter(T element, T target) {
		int index = indexOf(target);
		if (index == NOT_FOUND) {
			throw new NoSuchElementException();
		}
		add(index+1, element);
	}

	@Override
	public void add(int index, T element) {
		if (index > rear || index < 0) {
			throw new IndexOutOfBoundsException();
		}

		if (rear == array.length) {
			expandCapacity();
		}

		System.arraycopy(array, index, array, index+1, rear-index);
		array[index] = element;
		rear++;
	}

	@Override
	public T removeFirst() {
		T ret;
		try {
			ret = remove(0);
		} catch (IndexOutOfBoundsException e) {
			throw new NoSuchElementException();
		}

		return ret;
	}

	@Override
	public T removeLast() {
		T ret;
		try {
			ret = remove(rear-1);
		} catch (IndexOutOfBoundsException e) {
			throw new NoSuchElementException();
		}

		return ret;
	}

	@Override
	public T remove(T element) {
		int index = indexOf(element);
		if (index == NOT_FOUND) {
			throw new NoSuchElementException();
		}
		
		T retVal = array[index];
		
		rear--;
		//shift elements
		for (int i = index; i < rear; i++) {
			array[i] = array[i+1];
		}
		array[rear] = null;
		
		return retVal;
	}

	@Override
	public T remove(int index) {
		if (index >= rear || index < 0) {
			throw new IndexOutOfBoundsException();
		}

		T ret = array[index];
		System.arraycopy(array, index+1, array, index, rear-index);
		rear--;
		return ret;
	}

	@Override
	public void set(int index, T element) {
		if (index >= rear) {
			throw new IndexOutOfBoundsException();
		}
		array[index] = element;
	}

	@Override
	public T get(int index) {
		if (index >= rear) {
			throw new IndexOutOfBoundsException();
		}
		return array[index];
	}

	@Override
	public int indexOf(T element) {
		int index = NOT_FOUND;
		
		if (!isEmpty()) {
			int i = 0;
			while (index == NOT_FOUND && i < rear) {
				if (element.equals(array[i])) {
					index = i;
				} else {
					i++;
				}
			}
		}
		
		return index;
	}

	@Override
	public T first() {
		if (rear == 0) {
			throw new NoSuchElementException();
		}
		return array[0];
	}

	@Override
	public T last() {
		if (rear == 0) {
			throw new NoSuchElementException();
		}
		return array[rear-1];
	}

	@Override
	public boolean contains(T target) {
		return (indexOf(target) != NOT_FOUND);
	}

	@Override
	public boolean isEmpty() {
		return rear == 0;
	}

	@Override
	public int size() {
		return rear;
	}


	// IGNORE THE FOLLOWING ITERATOR AND LISTITERATOR IMPLEMENTATION METHODS
	// THESE WILL BE IMPLEMENTED IN A FUTURE LAB
	@Override
	public Iterator<T> iterator() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ListIterator<T> listIterator() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ListIterator<T> listIterator(int startingIndex) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String toString() {
		String ret = "[";
		// we don't want to print the last element with a trailing comma
		for (int i = 0; i < rear-1; i++) {
			ret += array[i] + ", ";
		}
		if (rear != 0) {
			ret += array[rear-1];
		}
		ret += "]";

		return ret;
	}
}
