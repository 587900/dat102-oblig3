package no.hvl.dat102.adt;

public interface BSTreADT<T extends Comparable<T>> {

	/**
	 * The number of elements in my tree (number of elements in a tree i am the root for / if I am the top root -> number of elements in the entire tree)
	 * @return the number of elements in the tree
	 */
	public int antall();
	
	/**
	 * Check if my sub-tree is empty.
	 * If I contain an element, this will return false as well.
	 * @return true if my sub-tree is empty, false otherwise
	 */
	public boolean erTom();


	/**
	 * Insert an element into the tree (Add the specified element to its appropriate place in the binary search tree).
	 * Equal elements are biased towards the right (and will never be added towards the left).
	 * @param element - The element to add
	 */
	public void leggTil(T element);
	
	/**
	 * Returns a reference to an object in the tree that is equal to the specified element.
	 * If no equal element is found in the tree, returns null.
	 * If two or more elements have the same value that is equal to the provided element, pick the top-most element from those in the tree (first encountered from root->down)
	 * @param element - The element to look for (by .compareTo())
	 * @return A reference to the equal element, null if none found.
	 */
	public T finn(T element);
	
	/**
	 * Returns a reference to the object with the minimum value.
	 * Returns null if the tree is empty
	 * If two or more elements have the same value and are the minimum value, pick the top-most element from those in the tree (first encountered from root->down)
	 * @return A reference to the object with the minimum value, null if tree is empty.
	 */
	public T finnMin();
	
	/**
	 * Returns a reference to the object with the maximum value.
	 * Returns null if the tree is empty
	 * If two or more elements have the same value and are the maximum value, pick the top-most element from those in the tree (first encountered from root->down)
	 * @return A reference to the object with the maximum value, null if tree is empty.
	 */
	public T finnMaks();

	/**
	 * Remove an element from the tree if if exists and return it.
	 * Returns null if element not found.
	 * If two or more elements have the same value that is equal to the provided element, pick the top-most element from those in the tree (first encountered from root->down)
	 * @param element - The element to look for / remove (by .compareTo())
	 * @returns A reference to the removed object, null if no equal object found.
	 */
	public T fjern(T element);
	
	/**
	 * Remove the the object with the minimum value in the tree and return it.
	 * Does nothing (and returns null) if tree is empty.
	 * If two or more elements have the same value and are the minimum value, pick the top-most element from those in the tree (first encountered from root->down)
	 * @return A reference to the removed object (minimum value object), null if tree is empty
	 */
	public T fjernMin();
	
	/**
	 * Remove the the object with the maximum value in the tree and return it.
	 * Does nothing (and returns null) if tree is empty.
	 * If two or more elements have the same value and are the maximum value, pick the top-most element from those in the tree (first encountered from root->down)
	 * @return A reference to the removed object (maximum value object), null if tree is empty
	 */
	public T fjernMaks();
}
