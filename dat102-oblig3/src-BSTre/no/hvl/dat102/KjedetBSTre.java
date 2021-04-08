package no.hvl.dat102;

import java.util.Iterator;

import no.hvl.dat102.adt.BSTreADT;

//********************************************************************
// KjedetBinærSøkeTre.java        
//
//********************************************************************

public class KjedetBSTre<T extends Comparable<T>> implements BSTreADT<T>,Iterable<T> {

	private int antall;
	private BinaerTreNode<T> rot;

	/**
	 * Create an empty binary tree
	 */
	public KjedetBSTre() {
		antall = 0;
		rot = null;
	}

	/**
	 * Create a binary tree with one element (one node with element)
	 * @param element - the element
	 */
	public KjedetBSTre(T element) {
		rot = new BinaerTreNode<T>(element);
		antall = 1;
	}
	
	/* Getters and setters */
	public BinaerTreNode<T> getRot() { return rot; }
	public void setRot(BinaerTreNode<T> rot) { this.rot = rot; }

	
	/**
	 * @returns the number of elements in the tree
	 */
	@Override
	public int antall() {
		return antall;
	}

	/**
	 * @returns true if tree is empty, false otherwise
	 */
	@Override
	public boolean erTom() {
		return (antall == 0);
	}
	
	
	/**
	 * Inserts an element into the tree.
	 * Equal elements are biased towards the right.
	 * Methodology: Uses recursive insertion
	 * @param element - The element to insert
	 */
	@Override
	public void leggTil(T element) {
		rot = leggTilRek(rot, element);
		antall++;
	}

	//leggTil, but with recursion
	private BinaerTreNode<T> leggTilRek(BinaerTreNode<T> p, T element) {
		// TODO 
		return null;
	}

	/**
	 * Inserts an element into the tree.
	 * Equal elements are biased towards the right.
	 * Methodology: Uses standard insertion
	 * @param element - The element to insert
	 */
	public void leggTil2(T element) {
		// TODO
	}

	/**
	 * Remove the node with the smallest value.
	 * Does nothing if the tree is empty.
	 * @returns The removed node, null if tree is empty
	 */
	@Override
	public T fjernMin() {
		// TODO 
		return null;
	}

	/**
	 * Remove the node with the highest value.
	 * Does nothing if the tree is empty.
	 * @returns The removed node, null if tree is empty
	 */
	@Override
	public T fjernMaks() {
		// TODO 
		return null;
	}

	/**
	 * Find the node with the smallest value.
	 * @returns The node, null if tree is empty
	 */
	@Override
	public T finnMin() {
		// TODO 
		return null;
	}

	/**
	 * Find the node with the highest value.
	 * @returns The node, null if tree is empty
	 */
	@Override
	public T finnMaks() {
		// TODO 
		return null;
	}

	/**
	 * Returns a reference to an object in the tree that is equal to the specified element.
	 * If no equal element is found in the tree, returns null.
	 * Methodology: Uses recursive searching
	 * @param element - The element to look for (by .equals())
	 * @return A reference to the equal element, null if none found.
	 */
	@Override
	public T finn(T element) {
		// Søk med rekursiv hjelpemetode

		// return finnRek(element, rot);
		return null;
	}

	// Den rekursive hjelpemetoden for søking
	
	// TODO 

	/**
	 * Returns a reference to an object in the tree that is equal to the specified element.
	 * If no equal element is found in the tree, returns null.
	 * Methodology: Uses standard searching.
	 * @param element - The element to look for (by .equals())
	 * @return A reference to the equal element, null if none found.
	 */
	public T finn2(T element) {
		// TODO 
		return null;
	}

	/**
	 * Display a tree (ordered) in the console.
	 */
	public void visInorden() {
		visInorden(rot);
		System.out.println();
	}

	//Helper method for displaying a tree. Display left node, display self, display right node
	private void visInorden(BinaerTreNode<T> p) {
		if (p != null) {
			visInorden(p.getVenstre());
			System.out.print(" " + p.getElement());
			visInorden(p.getHoyre());
		}  
	}

	@Override
	public Iterator<T> iterator() {
		return new InordenIterator<T>(rot);
	}
	
}
