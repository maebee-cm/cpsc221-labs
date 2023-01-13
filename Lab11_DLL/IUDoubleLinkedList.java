import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.ListIterator;
import java.util.NoSuchElementException;

public class IUDoubleLinkedList<T> implements IndexedUnsortedList<T> {
    private BidirectionalNode<T> head, tail;
    private int size;
    private int modCount;

    public IUDoubleLinkedList(){
        head = tail = null;
        size = modCount = 0;
    }

    @Override
    public void addToFront(T element) {
        BidirectionalNode<T> newHead = new BidirectionalNode(element);
        newHead.setPrevious(null);
        newHead.setNext(head);

        // head is only null when the list size is 0
        if (head == null) {
            head = tail = newHead;
        }
        else {
            head.setPrevious(newHead);
            head = newHead;
        }

        size++;
        modCount++;
    }

    @Override
    public void addToRear(T element) {
        BidirectionalNode<T> newTail = new BidirectionalNode(element);
        newTail.setNext(null);
        newTail.setPrevious(tail);

        // tail is only null when the list size is 0
        if (tail == null) {
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

    private Tuple<BidirectionalNode<T>, Integer> findNodeValue(T value) {
        BidirectionalNode<T> current = head;
        int index = 0;

        while (current != null) {
            if (current.getElement() == value) {
                return new Tuple(current, index);
            }

            current = current.getNext();
            index++;
        }

        return new Tuple(null, -1);
    }

    private BidirectionalNode<T> findNodeIndex(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }

        BidirectionalNode<T> current = head;
        for (int i = 0; i < size; i++) {
            if (i == index) {
                return current;
            }
            current = current.getNext();
        }

        return null;
    }

    @Override
    public void addAfter(T element, T target) {
        Tuple<BidirectionalNode<T>, Integer> targetNode = findNodeValue(target);
        if (targetNode.x == null) {
            throw new NoSuchElementException();
        }
        else if (targetNode.x == tail) {
            addToRear(element);
        }
        else {
            BidirectionalNode<T> newNode = new BidirectionalNode(element);
            // set the new node's next and previous values
            newNode.setNext(targetNode.x.getNext());
            newNode.setPrevious(targetNode.x);
            // then set the next element's previous value (note: we know this cannot be null)
            newNode.getNext().setPrevious(newNode);
            // and finally set the target node's next pointer
            targetNode.x.setNext(newNode);
            size++;
        }

        modCount++;
    }

    // called by our add method, and the iterator's add method so we make it generic
    // This will insert a node before the given node, which is to say the new node
    // will be between target's previous node and target
    private void insertNode(BidirectionalNode<T> target, T element) {
        if (head == target) {
            addToFront(element);
        }
        else {
            BidirectionalNode<T> newNode = new BidirectionalNode(element);
            newNode.setPrevious(target.getPrevious());
            newNode.setNext(target);
            target.getPrevious().setNext(newNode);
            target.setPrevious(newNode);
            size++;
            modCount++;
        }
    }

    @Override
    public void add(int index, T element) {
        if (index == size) {
            addToRear(element);
        }
        else {
            insertNode(findNodeIndex(index), element);
        }
    }

    private T removeNode(BidirectionalNode<T> node) {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }

        T value = node.getElement();
        if (size == 1) {
            head = tail = null;
        }
        else if (node == head) {
            head = head.getNext();
        }
        else if (node == tail) {
            tail.getPrevious().setNext(null);
            tail = tail.getPrevious();
        }
        else {
            node.getPrevious().setNext(node.getNext());
            node.getNext().setPrevious(node.getPrevious());
        }

        size--;
        modCount++;
        return value;
    }

    @Override
    public T removeFirst() {
        return removeNode(head);
    }

    @Override
    public T removeLast() {
        return removeNode(tail);
    }

    @Override
    public T remove(T element) {
        Tuple<BidirectionalNode<T>, Integer> node = findNodeValue(element);
        if (node.x == null){
            throw new NoSuchElementException();
        }

        return removeNode(node.x);
    }

    @Override
    public T remove(int index) {
        return removeNode(findNodeIndex(index));
    }

    @Override
    public void set(int index, T element) {
        findNodeIndex(index).setElement(element);
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
    private T getElement(BidirectionalNode<T> node) {
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
        return findNodeValue(target).x != null;
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

        BidirectionalNode<T> current = head;
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
        return new DLLIterator();
    }

    @Override
    public ListIterator<T> listIterator() {
        return new DLLListIterator(0);
    }

    @Override
    public ListIterator<T> listIterator(int startingIndex) {
        return new DLLListIterator(startingIndex);
    }

    private class DLLIterator implements Iterator<T> {
        BidirectionalNode<T> current, next;
        int iterModCount;
        boolean nextCalled;

        DLLIterator() {
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

            removeNode(current);

            nextCalled = false;
            iterModCount++;
        }
    }

    private class DLLListIterator implements ListIterator<T> {
        BidirectionalNode<T> current, next;
        int nextIndex;
        int iterModCount;
        boolean removeCalled, addCalled;

        DLLListIterator(int index) {
            // we don't need to explicitly check if index > size, IndexOutOfBoundsException will be thrown when
            // findNodeIndex is called
            if (index == size) {
                next = null;
            }
            else {
                next = findNodeIndex(index);
            }
            current = null;
            nextIndex = index;
            iterModCount = modCount;
            removeCalled = addCalled = false;

        }

        private void checkForModification() {
            if (iterModCount != modCount) {
                throw new ConcurrentModificationException();
            }
        }

        @Override
        public boolean hasNext() {
            checkForModification();
            return nextIndex < size;
        }

        @Override
        public T next() {
            checkForModification();
            if (!hasNext()) {
                throw new NoSuchElementException();
            }

            current = next;
            next = next.getNext();
            nextIndex++;
            removeCalled = addCalled = false;

            return current.getElement();
        }

        @Override
        public boolean hasPrevious() {
            checkForModification();
            return nextIndex > 0;
        }

        @Override
        public T previous() {
            checkForModification();
            if (!hasPrevious()) {
                throw new NoSuchElementException();
            }

            if (next == null) {
                current = next = tail;
            }
            else {
                current = next = next.getPrevious();
            }
            nextIndex--;
            removeCalled = addCalled = false;

            return current.getElement();
        }

        @Override
        public int nextIndex() {
            checkForModification();
            return nextIndex;
        }

        @Override
        public int previousIndex() {
            checkForModification();
            return nextIndex-1;
        }

        @Override
        public void remove() {
            checkForModification();
            if (current == null || addCalled) {
                throw new IllegalStateException();
            }

            removeNode(current);
            iterModCount++;
            removeCalled = true;
            current = null;
        }

        @Override
        public void set(T t) {
            checkForModification();
            if (current == null || addCalled || removeCalled) {
                throw new IllegalStateException();
            }
            current.setElement(t);
        }

        @Override
        public void add(T t) {
            checkForModification();
            // the new node is going to be inserted before next, so we need
            // to update current and previous to have the newly added node
            // be before the cursor
            if (next == null) {
                addToRear(t);
                current = tail.getPrevious();
            }
            else {
                insertNode(next, t);
                current = next.getPrevious();
            }

            nextIndex++;
            iterModCount++;
            addCalled = true;
        }
    }
}
