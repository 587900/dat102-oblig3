package no.hvl.dat102;

import java.util.Iterator;
import java.util.concurrent.Callable;

import no.hvl.dat102.adt.BSTreADT;

//********************************************************************
// KjedetBinærSøkeTre.java        
//
//********************************************************************

public class KjedetBSTre<T extends Comparable<T>> implements BSTreADT<T>,Iterable<T> {

	private int antall;
	private BinaerTreNode<T> rot;

	/**
	 * Create an empty binary search tree
	 */
	public KjedetBSTre() {
		antall = 0;
		rot = null;
	}

	/**
	 * Create a binary search tree with one element (one node with element)
	 * @param element - the element
	 */
	public KjedetBSTre(T element) {
		rot = new BinaerTreNode<T>(element);
		antall = 1;
	}
	
	/* Getters and setters */
	public BinaerTreNode<T> getRot() { return rot; }
	public void setRot(BinaerTreNode<T> rot) { this.rot = rot; }

	
	@Override
	public int antall() {
		return antall;
	}

	@Override
	public boolean erTom() {
		return (antall == 0);
	}
	
	/**
	 * {@inheritDoc}
	 * Methodology: Uses recursive insertion
	 */
	@Override
	public void leggTil(T element) {
		rot = leggTilRek(rot, element);
		antall++;
	}

	//leggTil recursion part
	private BinaerTreNode<T> leggTilRek(BinaerTreNode<T> p, T element) {
		if (p == null) return new BinaerTreNode<>(element);
		
		//set left/right to result of leggTilRek(left/right, element)
		if (element.compareTo(p.getElement()) < 0) p.setVenstre(leggTilRek(p.getVenstre(), element)); else p.setHoyre(leggTilRek(p.getHoyre(), element));
		
		return p;
	}

	/**
	 * @see KjedetBSTre#leggTil(Comparable)
	 * Methodology: Uses standard insertion
	 */
	public void leggTil2(T element) {
		throw new Error("Method unimplemented");
	}
	
	@Override
	public T fjern(T element) {
		
		if (rot == null || element == null) return null;

		BinaerTreNode<T> parent = null;
		BinaerTreNode<T> node = rot;
		
		//Go to comparatively equal node
		int comparison;
		while ((comparison = element.compareTo(node.getElement())) != 0) {
			parent = node;
			node = (comparison < 0 ? node.getVenstre() : node.getHoyre());
			if (node == null) return null;	//element not found
		}
		
		//Decide what shall replace our node
		BinaerTreNode<T> replacement;
		if (node.getVenstre() == null && node.getHoyre() == null) {
			//no children, delete node
			replacement = null;
		} else if (node.getVenstre() != null && node.getHoyre() != null) {
			//two children, replace with lowest value in right sub-tree. Remember to attach our current children.
			//We know that node can at most have one child, so there is no infinite loop (because minimum value cannot have something less than it in the tree)
			replacement = fjernMin(node.getHoyre());
			replacement.setVenstre(node.getVenstre());
			replacement.setHoyre(node.getHoyre());
		} else {
			//one child, replace myself with it
			replacement = node.getVenstre();
			if (replacement == null) replacement = node.getHoyre();
		}
		
		if (parent == null) rot = replacement;	//no way of getting around this check in java without recursion
		else if (comparison < 0) parent.setVenstre(replacement); else parent.setHoyre(replacement);
		
		return node.getElement();
	}
	
	@Override
	public T fjernMin() {
		return fjern(finnMin());
	}
	
	//this always works.
	//because equal elements are biased towards the right, the first instance of any value will always be the furthest left in the tree of that value
	//(ex. if two instances of the number 40 exists in the tree, the first encounter of the value 40 will be the left-most node in the tree that contains the value 40)
	//
	//optimally, we could use recursion to skip a potentially large part of any tree, since we know the starting node 
	//(fjern will always go to that node first, since this method essentially removes the smallest value under this node)
	//this is currently not done due to code / function duplication
	private BinaerTreNode<T> fjernMin(BinaerTreNode<T> node) {
		return new BinaerTreNode<>(fjern(finnMin(node)));
	}
	
	//helper
	private T finnMin(BinaerTreNode<T> node) {
		if (node == null) return null;
		while (node.getVenstre() != null) node = node.getVenstre();
		return node.getElement();
	}

	@Override
	public T fjernMaks() {
		return fjern(finnMaks());
	}

	@Override
	public T finnMin() {
		BinaerTreNode<T> node = rot;
		while (node.getVenstre() != null) node = node.getVenstre();
		return node.getElement();
	}

	@Override
	public T finnMaks() {
		BinaerTreNode<T> node = rot;
		while (node.getHoyre() != null) node = node.getHoyre();
		return node.getElement();
	}

	/**
	 * {@inheritDoc}
	 * Methodology: Uses recursive searching
	 */
	@Override
	public T finn(T element) {
		return finnRekursiv(rot, element);
	}
	
	private T finnRekursiv(BinaerTreNode<T> node, T element) {
		if (node == null) return null;
		
		int comp = element.compareTo(node.getElement());
		
		if (comp == 0) return node.getElement();
		
		return finnRekursiv((comp < 0 ? node.getVenstre() : node.getHoyre()), element);		
	}

	/**
	 * @see KjedetBSTre#finn(Comparable)
	 * Methodology: Uses standard searching.
	 */
	public T finn2(T element) {
		BinaerTreNode<T> node = rot;
		while (true) {
			if (node == null) return null;
			int comp = element.compareTo(node.getElement());
			if (comp == 0) return node.getElement();
			node = (comp < 0 ? node.getVenstre() : node.getHoyre());
		}
	}
	
	//3a)
	/**
	 * Returns the height of the tree (the longest path from the root to any given leaf).
	 * Returns -1 if the tree is empty.
	 * Methodology: Uses recursion.
	 * @return the height of the tree, -1 if the tree is empty
	 */
	public int hoyde() {
		return maxDistanceRecursive(rot) - 1; 	//-1 to not count root
	}
	
	/**
	 * Get the number of nodes between a node and the furthest-away leaf from it (inclusive) (aka height of tree +1).
	 * If node null (tree considered empty), returns 0.
	 * @return the number of nodes between the provided node and the furthest-away leaf (inclusive) (aka height of tree +1).
	 */
	private int maxDistanceRecursive(BinaerTreNode<T> node) {
		if (node == null) return 0;
		return Math.max(maxDistanceRecursive(node.getHoyre()), maxDistanceRecursive(node.getVenstre())) + 1;
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
	
	
	//3e) (frivillig) - below:
	
	/**
	 * Print all values that are comparatively (by .compareTo()) between the lower and upper bounds, in order.
	 * @param min - the lower bound (inclusive)
	 * @param max - the upper bound (inclusive)
	 */
	public void skrivVerdier(T lower, T upper) { skrivVerdierRek(rot, lower, upper); System.out.println(); }
	
	private void skrivVerdierRek(BinaerTreNode<T> node, T lower, T upper) {
		if (node == null) return;
		int compLower = node.getElement().compareTo(lower);
		int compUpper = node.getElement().compareTo(upper);
		
		if (compLower >= 0) skrivVerdierRek(node.getVenstre(), lower, upper);				//lower bound is in range
		if (compLower >= 0 && compUpper <= 0) System.out.print(node.getElement() + " ");	//we are in range
		if (compUpper <= 0) skrivVerdierRek(node.getHoyre(), lower, upper);					//upper bound is in range
	}
	
	/**
	 * 3e) i. - explain why this method is not optimal:
	 * Explanation: we know that if this node's value is lower than the "lower" bound, 
	 * that all the values on the left side of this node are subsequently lower, and thus need not be checked.
	 * The same goes for if this node's value is higher than the upper bound, we don't need to check the nodes on the right side.
	 * PS: I would also strongly prefer to use {@code if(node == null) return;}, though this is not necessarily relevant to the code's optimization.
	 */
	private void skrivVerdierRek2(BinaerTreNode<T> node, T lower, T upper) {
		if (node != null) {
			skrivVerdierRek2(node.getVenstre(), lower, upper);
			if ((node.getElement().compareTo(lower) >= 0) && (node.getElement().compareTo(upper) <= 0)) {
				System.out.print(node.getElement() + " ");
			}
			skrivVerdierRek2(node.getHoyre(), lower, upper);
		}
	}
	
}
