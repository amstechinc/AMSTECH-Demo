import java.util.Iterator;
import java.util.List;

/**
 * The Class Node represents a single node of the singly linked list.
 *
 * @param <T> the generic type
 */
class Node<T> {

	/** The data field to hold the values. */
	private T data;

	/** The next field to point to next node. */
	private Node<T> next;

	/**
	 * Instantiates a new node.
	 */
	public Node() {
	}

	/**
	 * Instantiates a new node.
	 *
	 * @param data the data
	 * @param next the next
	 */
	public Node(T data, Node<T> next) {
		super();
		this.data = data;
		this.next = next;
	}

	/**
	 * Gets the data.
	 *
	 * @return the data
	 */
	public T getData() {
		return data;
	}

	/**
	 * Sets the data.
	 *
	 * @param data the new data
	 */
	public void setData(T data) {
		this.data = data;
	}

	/**
	 * Gets the next.
	 *
	 * @return the next
	 */
	public Node<T> getNext() {
		return next;
	}

	/**
	 * Sets the next.
	 *
	 * @param next the new next
	 */
	public void setNext(Node<T> next) {
		this.next = next;
	}

}

/**
 * The Class MyList is a iterable custom list to hold the {@link Comparable} type of values. 
 * It is a iterable list but it has not of inherited {@link List} to keep the APIs minimum.
 *
 * @param <T> the generic type
 */
class MyList<T extends Comparable<? super T>> implements Iterable<T> {

	/** The head and tail node to point the beginning and end node of the list. */
	private Node<T> head, tail;

	/** The size. */
	private int size = 0;

	/**
	 * Append.
	 *
	 * @param value the value
	 */
	public void append(T value) {
		Node<T> node = new Node<>(value, null);
		size++;
		if (head == null && tail == null) {
			head = tail = node;
			return;
		}
		tail.setNext(node);
		tail = node;
	}

	/**
	 * Delete at end.
	 *
	 * @return true, if successful
	 */
	public boolean deleteAtEnd() {
		if (head == null) { // empty list
			return false;
		}
		if (head != null && head == tail) { // single node case
			head = tail = null;
			size--;
			return true;
		}

		Node<T> temp = head;

		while (temp.getNext() != tail) { // search for the previous node of tail node
			temp = temp.getNext();
		}
		temp.setNext(null);
		tail = temp;
		size--;
		return true;
	}

	/**
	 * Delete greater values then the provided value.
	 *
	 * @param value the value
	 * @return true, if successful
	 */
	public boolean deleteGreaterValuesThen(T value) {
		Node<T> current = head;
		Node<T> prev = head;
		boolean anyValueDeleted = false;

		while (current != null) { // traverse the list till end

			if (current.getData().compareTo(value) > 0) {

				if (current == head) { // case when node to be deleted is the first node
					head = current.getNext();
				} else { // for any middle node
					prev.setNext(current.getNext());
				}
				anyValueDeleted = true;

			} else {
				prev = current;
			}
			current = current.getNext(); // move to next node
		}
		return anyValueDeleted;

	}

	/**
	 * Size.
	 *
	 * @return the int
	 */
	public int size() {
		return size;
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Iterable#iterator()
	 */
	public Iterator<T> iterator() {
		return new NodeIterator();
	}

	/**
	 * The Class NodeIterator.
	 */
	class NodeIterator implements Iterator<T> {

		/** The temp. */
		Node<T> temp = head;

		/*
		 * (non-Javadoc)
		 * @see java.util.Iterator#hasNext()
		 */
		@Override
		public boolean hasNext() {
			if (temp != null) {
				return true;
			}
			return false;
		}

		/*
		 * (non-Javadoc)
		 * @see java.util.Iterator#next()
		 */
		@Override
		public T next() {
			T data = temp.getData();
			temp = temp.getNext();
			return data;
		}

	}

}

/**
 * The Class Demo to test the {@link MyList} class.
 */
public class Demo {

	/**
	 * The main method.
	 *
	 * @param args the arguments
	 * @throws Exception the exception
	 */
	public static void main(String[] args) throws Exception {

		System.out.println("Start");
		MyList<Integer> list = new MyList<>();

		list.append(10);
		list.append(20);
		list.append(30);
		list.append(40);
		list.append(50);

		System.out.println("== List size : " + list.size());
		System.out.println("== List Entries ==");
		list.forEach(e -> System.out.println(e));

		System.out.println("Delete at end: " + list.deleteAtEnd());
		System.out.println("Delete at end: " + list.deleteAtEnd());
		System.out.println("Delete at end: " + list.deleteAtEnd());

		System.out.println("== List size : " + list.size());
		System.out.println("== List Entries ==");
		list.forEach(e -> System.out.println(e));

		System.out.println("Delete at end: " + list.deleteAtEnd());
		System.out.println("Delete at end: " + list.deleteAtEnd());
		System.out.println("Delete at end: " + list.deleteAtEnd());

		System.out.println("== List size : " + list.size());
		System.out.println("== List Entries ==");
		list.forEach(e -> System.out.println(e));

		list.append(11);
		list.append(22);
		list.append(33);
		list.append(44);
		list.append(55);
		list.append(66);

		System.out.println("== List size : " + list.size());
		System.out.println("== List Entries ==");
		list.forEach(e -> System.out.println(e));

		list.deleteGreaterValuesThen(40);

		System.out.println("== List size : " + list.size());
		System.out.println("== List Entries ==");
		list.forEach(e -> System.out.println(e));

		System.out.println("Finish");

	}

}
