package personcollection;

public interface PersonCollectionI {
	public void add(Person person);

	/**
	 * Adds the person at the index. Throws IndexOutOfBoundsException if index is
	 * not in [0, size()].
	 */
	public void add(int index, Person person);

	/**
	 * Removes and returns the person at the index. Throws IndexOutOfBoundsException
	 * if this list is empty or index is not in [0, size()-1].
	 */
	public Person remove(int index);

	/**
	 * Returns the person at the index. Throws IndexOutOfBoundsException if this
	 * list is empty or index is not in [0, size()-1].
	 */
	public Person get(int index);

	/**
	 * Return true if the entry is in this list.
	 */
	public boolean contains(Person person);

	/**
	 * Returns the number of entries in this list.
	 */
	public int size();

	/**
	 * Returns true if this list is empty.
	 */
	public boolean isEmpty();

	/**
	 * Removes all entries from this list.
	 */
	public void clear();

}
