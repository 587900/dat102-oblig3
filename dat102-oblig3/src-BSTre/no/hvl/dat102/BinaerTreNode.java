package no.hvl.dat102;

//  BinærTreNode.java               
//
//  Representerer en node i et binært tre.
//*******************************************************************

public class BinaerTreNode<T> {
	private T element;
	private BinaerTreNode<T> venstre;
	private BinaerTreNode<T> hoyre;

	/**
	 * Create a new tree node with the specified data
	 * @param el - the specified data
	 */
	public BinaerTreNode(T el) {
		element = el;
		venstre = null;
		hoyre = null;
	}

	public BinaerTreNode(T element, BinaerTreNode<T> venstre, BinaerTreNode<T> hoyre) {
		this.element = element;
		this.venstre = venstre;
		this.hoyre = hoyre;
	}

	/* Getters and setters */

	public BinaerTreNode<T> getVenstre() { return venstre; }
	public void setVenstre(BinaerTreNode<T> ny) { venstre = ny; }
	
	public BinaerTreNode<T> getHoyre() { return hoyre; }
	public void setHoyre(BinaerTreNode<T> ny) {	hoyre = ny; }
	
	public T getElement() {	return element; }
	public void setElement(T el) { element = el; }

}
