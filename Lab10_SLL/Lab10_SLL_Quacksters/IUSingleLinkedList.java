import java.util.*;

/**
 * Single-linked node implementation of IndexedUnsortedList.
 * An Iterator with working remove() method is implemented, but
 * ListIterator is unsupported.
 * 
 * @author 
 * 
 * @param <T> type to store
 */
public class IUSingleLinkedList<T> implements IndexedUnsortedList<T> {
	private LinearNode<T> head, tail;
	private int size;
	private int modCount;
	
	/** Creates an empty list */
	public IUSingleLinkedList() {
		head = tail = null;
		size = 0;
		modCount = 0;
	}

	@Override
	public void addToFront(T element) {
		LinearNode<T> newHead = new LinearNode(element);
		newHead.setNext(head);
		head = newHead;
		if (size == 0) {
			tail = newHead;
		}

		size++;
		modCount++;
	}

	@Override
	public void addToRear(T element) {
		LinearNode<T> newTail = new LinearNode(element);
		if (size == 0) {
			head = tail = newTail;
		}
		else {
			tail.setNext(newTail);
			tail = newTail;
		}

		size++;
		modCount++;
	}

	@Override
	public void add(T element) {
		addToRear(element);
	}

	private Tuple<LinearNode<T>, Integer> findNodeValue(T element) {
		LinearNode<T> current = head;
		int index = 0;

		while (current != null) {
			if (current.getElement() == element) {
				return new Tuple(current, index);
			}
			current = current.getNext();
			index++;
		}

		return new Tuple(null, -1);
	}

	private LinearNode<T> findNodeIndex(int index) {
		if (index >= size || index < 0) {
			throw new IndexOutOfBoundsException();
		}

		LinearNode<T> current = head;
		for (int i = 0; i < size; i++) {
			if (i == index) {
				return current;
			}
			current = current.getNext();
		}

		// unreachable, but we need it to make java happy
		return null;
	}

	@Override
	public void addAfter(T element, T target) {
		Tuple<LinearNode<T>, Integer> targetNode = findNodeValue(target);
		if (targetNode.x == null) {
			throw new NoSuchElementException();
		}
		else if (targetNode.x == tail) {
			addToRear(element);
			return;
		}
		else {
			LinearNode<T> newNode = new LinearNode(element);
			newNode.setNext(targetNode.x.getNext());
			targetNode.x.setNext(newNode);

			size++;
			modCount++;
		}
	}

	@Override
	public void add(int index, T element) {
		if (index == size) {
			addToRear(element);
			return;
		}
		else if (index == 0) {
			addToFront(element);
			return;
		}

		// findNodeIndex doesn't give us the previous node which we'd need
		// instead we're just going to swap node values cause it helps keep
		// code more generic and there's no real performance hit.
		LinearNode<T> targetNode = findNodeIndex(index);
		// make a new node and have it be a copy of targetNode
		LinearNode<T> newNode = new LinearNode(targetNode.getElement());
		newNode.setNext(targetNode.getNext());
		// make target node contain the new element and point to newNode
		targetNode.setElement(element);
		targetNode.setNext(newNode);
		// if target node is tail then we have to change it to point to the new tail object
		if (targetNode == tail) {
			tail = newNode;
		}

		size++;
		modCount++;
	}

	@Override
	public T removeFirst() {
		if (isEmpty()) {
			throw new NoSuchElementException();
		}

		return removeNode(head, null);
	}

	@Override
	public T removeLast() {
		if (isEmpty()) {
			throw new NoSuchElementException();
		}

		if (size == 1) {
			return removeNode(tail, null);
		}
		else {
			return removeNode(tail, findNodeIndex(size - 2));
		}
	}

	// Generic version of the remove functionality. Removes target and gets previous
	// to point at the node in front of target if target is not the head. If target is
	// the head node, null should be passed as the previous value.
	// This is going to be almost an exact copy of remove(T element)
	private T removeNode(LinearNode<T> target, LinearNode<T> previous) {
		if (isEmpty()) {
			throw new NoSuchElementException();
		}

		if (size == 1) {
			head = tail = null;
		}
		else if (target == head) {
			head = target.getNext();
		}
		else if (target == tail) {
			tail = previous;
			tail.setNext(null);
		}
		else {
			previous.setNext(target.getNext());
		}

		size--;
		modCount++;
		return target.getElement();
	}

	@Override
	public T remove(T element) {
		if (isEmpty()) {
			throw new NoSuchElementException();
		}

		boolean found = false;
		LinearNode<T> previous = null;
		LinearNode<T> current = head;

		while (current != null && !found) {
			if (element.equals(current.getElement())) {
				found = true;
			} else {
				previous = current;
				current = current.getNext();
			}
		}

		if (!found) {
			throw new NoSuchElementException();
		}

		if (size() == 1) { //only node
			head = tail = null;
		} else if (current == head) { //first node
			head = current.getNext();
		} else if (current == tail) { //last node
			tail = previous;
			tail.setNext(null);
		} else { //somewhere in the middle
			previous.setNext(current.getNext());
		}

		size--;
		modCount++;

		return current.getElement();
	}

	@Override
	public T remove(int index) {
		// we need a custom version of findNodeIndex to find the previous node too
		if (index >= size || index < 0) {
			throw new IndexOutOfBoundsException();
		}

		LinearNode<T> current = head;
		LinearNode<T> previous = null;
		for (int i = 0; i < size; i++) {
			if (i == index) {
				break;
			}

			previous = current;
			current = current.getNext();
		}

		return removeNode(current, previous);
	}

	@Override
	public void set(int index, T element) {
		findNodeIndex(index).setElement(element);
		return;
	}

	@Override
	public T get(int index) {
		return findNodeIndex(index).getElement();
	}

	@Override
	public int indexOf(T element) {
		return findNodeValue(element).y;
	}

	// Generic get, helper for other get functions
	private T getElement(LinearNode<T> node) {
		if (node == null) {
			throw new NoSuchElementException();
		}

		return node.getElement();
	}

	@Override
	public T first() {
		return getElement(head);
	}

	@Override
	public T last() {
		return getElement(tail);
	}

	@Override
	public boolean contains(T target) {
		return indexOf(target) != -1;
	}

	@Override
	public boolean isEmpty() {
		return size == 0;
	}

	@Override
	public int size() {
		return size;
	}

	public String toString() {
		StringBuilder ret = new StringBuilder("[");

		LinearNode<T> current = head;
		if (size > 0) {
			while (current != null) {
				// don't print anything if this is the last element, we don't want to add a comma
				if (current.getNext() != null) {
					ret.append(current.getElement()).append(", ");
				}
				else {
					ret.append(current.getElement());;
				}

				current = current.getNext();
			}
		}
		ret.append("]");

		return ret.toString();
	}

	@Override
	public Iterator<T> iterator() {
		return new SLLIterator();
	}

	/** Iterator for IUSingleLinkedList */
	private class SLLIterator implements Iterator<T> {
		private LinearNode<T> previous;
		private LinearNode<T> current;
		private LinearNode<T> next;
		private int iterModCount;
		boolean nextCalled;
		
		/** Creates a new iterator for the list */
		public SLLIterator() {
			previous = null;
			current = null;
			next = head;
			iterModCount = modCount;
			nextCalled = false;
		}

		@Override
		public boolean hasNext() {
			if (iterModCount != modCount) {
				throw new ConcurrentModificationException();
			}
			return next != null;
		}

		@Override
		public T next() {
			if (iterModCount != modCount) {
				throw new ConcurrentModificationException();
			}

			if (next == null) {
				throw new NoSuchElementException();
			}

			previous = current;
			current = next;
			next = next.getNext();
			nextCalled = true;

			return current.getElement();
		}
		
		@Override
		public void remove() {
			if (iterModCount != modCount) {
				throw new ConcurrentModificationException();
			}
			if (!nextCalled) {
				throw new IllegalStateException();
			}

			removeNode(current, previous);
			iterModCount++;
			nextCalled = false;
		}
	}

	// IGNORE THE FOLLOWING LISTITERATOR IMPLEMENTATION METHODS
	// THESE WILL BE IMPLEMENTED IN A FUTURE LAB
	@Override
	public ListIterator<T> listIterator() {
		throw new UnsupportedOperationException();
	}

	@Override
	public ListIterator<T> listIterator(int startingIndex) {
		throw new UnsupportedOperationException();
	}
}
