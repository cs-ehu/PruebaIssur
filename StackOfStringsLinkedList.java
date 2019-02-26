package ehu.eda.ref;

/**
 * Una implementación simple de colas LIFO (pilas), usando una lista ligada
 * (linked-list).
 * 
 * En esta implementación, las pilas tienen capacidad limitada solo por la
 * memoria disponible.
 * 
 * Todas las operaciones tienen tiempo de ejecucion en Theta(1) (tiempo
 * constante: independiente de la entrada y del tamaño de la pila); menos "size"
 * que esta en Theta(N), siendo N el tamaño de la pila (cantidad de elementos en
 * la pila).
 *
 */
public class StackOfStringsLinkedList {

	/*
	 * first es el nodo con la cima de la pila
	 * 
	 * Pila vacía <=> first es null
	 */
	private Node first;

	public boolean isEmpty() {
		return first == null;
	}

	public String pop() {
		String item = first.item;
		first = first.next;
		return item;
	}

	public void push(String item) {
		Node oldfirst = first;
		first = new Node();
		first.item = item;
		first.next = oldfirst;
	}

	/**
	 * Devuelve la cantidad de elememtos en la pila.
	 */
	/*
	 * Análisis de su tiempo de ejecución.
	 * 
	 * Para una pila con N elementos, se hacen N iteraciones.
	 * 
	 * Las operaciones en cada iteración son elementales => Theta(1)
	 */
	public int size() {
		int n = 0; // contador de elementos
		/*
		 * Parecido al recorrido de un array:
		 * 
		 * int index = 0;
		 * 
		 * while (index < array.length) { n = n + 1; index = index + 1; }
		 * 
		 */
		Node ptr = first;
		while (ptr != null) {
			n = n + 1;
			ptr = ptr.next;
		}
		return n;
	}

	/*
	 * Una clase "static" dentro de otra es como cualquier otra clase; pero si,
	 * además, es "private" no puede usarse fuera de aquí.
	 * 
	 * Así, los detalles de la implementación quedan totalmente ocultados a los
	 * clientes;
	 */
	private static class Node {
		String	item;
		Node	next;
	}
}
