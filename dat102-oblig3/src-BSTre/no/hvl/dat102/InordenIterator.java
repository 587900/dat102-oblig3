package no.hvl.dat102;
import java.util.*;

/**
 * Klassen implementerer en InordenIterator
 */

public class InordenIterator<T> implements Iterator<T>{
	
	private Stack<BinaerTreNode<T>> s = null;
	private BinaerTreNode<T> aktuell = null;

	// gå helt til venstre
	// stable på venstrebarn
	private BinaerTreNode<T> gaaTilVenstre(BinaerTreNode<T> p){
		if (p == null)
		return null;
		while (p.getVenstre() != null) {
			s.push(p);
			p = p.getVenstre();
		}
		return p;
	}

	/**
	 * Creates an InordenIterator<T>, and goes all the way to the left in the binary search tree
	 */
	public InordenIterator(BinaerTreNode<T> rot) {
		s = new Stack<BinaerTreNode<T>>();
		aktuell = gaaTilVenstre(rot);
	}

	/**
	 * @return true if the iterator has more elements
	 */
	@Override
	public boolean hasNext() {
		return (aktuell != null);
	}

	/**
	 * @return the next element in the iterator, null if empty
	 */
	@Override
	public T next() {
		T resultat = null;  
		
		if(hasNext()) {  
			resultat = aktuell.getElement();
			
			if (aktuell.getHoyre() != null) // har et høyre undertre
				// stable på node for venstre undertre
				aktuell = gaaTilVenstre(aktuell.getHoyre());
			else if (!s.isEmpty())
				// ingen høyre undertre
				aktuell = (BinaerTreNode<T>)s.pop();
			else
				aktuell = null;   // slutt på treet  
		}
		return resultat;
	}

	//unused / unsupported
//	public void remove() {
//		// Ikke implementert
//	}

}
 
