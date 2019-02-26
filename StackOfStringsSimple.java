package ehu.eda.ref;

/**
 * Una implementación simple de colas LIFO (pilas), usando un array.
 * 
 * En esta implementación, las pilas tienen capacidad limitada, que se establece
 * en la constructora.
 * 
 * Todas las operaciones tienen tiempo de ejecucion en Theta(1) (tiempo
 * constante: independiente de la entrada y del tamaño de la pila).
 *
 */
public class StackOfStringsSimple {
	private String[]	s;	// array para almacenar los elementos de la pila
	private int			n;	// cantidad de elementos en la pila

	public StackOfStringsSimple(int capacity) {
		s = new String[capacity];
	}

	public boolean isEmpty() {
		return n == 0;
	}

	public String pop() {
		// return s[--n];
		/*
		 * Lo mismo que:
		 * 
		 */
		n = n - 1;
		return s[n];
	}

	public void push(String item) {
		// s[n++] = item;
		/*
		 * Lo mismo que:
		 * 
		 */
		s[n] = item;
		n = n + 1;
	}

	public int size() {
		return n;
	}
}
