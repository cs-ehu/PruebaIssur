package ehu.eda.bst;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;

/*
 * Esta clase es una copia de la clase genérica MySetBST, pero particularizada
 * para "int"s.
 * 
 * CUIDADO!: los valores de tipo int NO son OBJETOS.
 * Pero las instancias de Integer SI.
 */
public class MySetBSTOfInt {
	protected final Comparator<Integer>	comparator;
	protected Node						root;
	protected int						size;

	/**
	 * Devuelve un nuevo conjunto.
	 */
	public MySetBSTOfInt() {
		/*
		 * El comparador a usar para comparar String's de la manera habitual.
		 */
		comparator = Comparator.naturalOrder();
	}

	/**
	 * Devuelve un nuevo conjunto.
	 * 
	 * @param comparator
	 *            el comparador usado internamente.
	 */
	public MySetBSTOfInt(Comparator<Integer> comparator) {
		this.comparator = comparator;
	}

	/**
	 * Adds the specified element to this set if it is not already present.
	 *
	 * More formally, adds the specified element {@code e} to this set if the set contains
	 * no element {@code e2} such that
	 * <tt>(e==null&nbsp;?&nbsp;e2==null&nbsp;:&nbsp;comparator.compare(e, e2) == 0)</tt>.
	 * If this set already contains the element, the call leaves the set unchanged and
	 * returns {@code false}.
	 *
	 * @param e
	 *            element to be added to this set
	 * @return {@code true} if this set did not already contain the specified element
	 * @throws NullPointerException
	 *             if the specified element is null and the comparator does not permit
	 *             null elements
	 */
	public boolean add(int e) {
		boolean rs = true;
		Node t = root;
		if (t == null) {
			root = newNode(e, null);
			size = 1;
		} else {
			int cmp;
			Node parent;
			boolean notFound = true;
			do {
				parent = t;
				cmp = comparator.compare(e, t.key);
				if (cmp < 0) {
					t = t.left;
				} else if (cmp > 0) {
					t = t.right;
				} else {
					notFound = false;
				}
			} while (t != null && notFound);
			if (notFound) {
				rs = false;
				Node n = newNode(e, parent);
				if (cmp < 0) {
					parent.left = n;
				} else {
					parent.right = n;
				}
				size++;
			}
		}
		assert validate();
		return rs;
	}

	/**
	 * Linear time tree building algorithm from sorted data.
	 *
	 * PRECONDICION: el iterador contiene al menos "size" elementos.
	 * 
	 * POSTCONDICION: este conjunto contiene los elementos "size" primeros elementos del
	 * iterador, y solo esos.
	 * 
	 * Dicho de otra manera, este método primero vacía este conjunto y después añade los
	 * primeros "size" elementos del iterador.
	 * 
	 * @param size
	 *            the number of keys to be read from the iterator
	 * @param it
	 *            new entries are created from keys read from this iterator.
	 * 
	 */
	public void buildFromSorted(@SuppressWarnings("hiding") int size,
			Iterator<Integer> it) {
		throw new UnsupportedOperationException();
	}

	/**
	 * Como la anterior pero los elementos a añadir se toman de un array.
	 * 
	 * PRECONDICION: el array está ordenado.
	 */
	public void buildFromSorted(int[] array) {
		throw new UnsupportedOperationException();
	}

	/**
	 * Returns the least element greater than or equal to the given element, or
	 * {@code null} if there is no such element.
	 *
	 * @param element
	 *            the element
	 * @return the least element less than or equal to {@code element}, or {@code null} if
	 *         there is no such element
	 * @throws NullPointerException
	 *             if the specified element is null and...
	 */
	public Integer ceilling(int e) {
		return keyOrNull(getCeilingNode(e));
	}

	public void clear() {
		root = null;
		size = 0;
	}

	@Override
	public MySetBSTOfInt clone() {
		throw new UnsupportedOperationException();
	}

	public boolean contains(int e) {
		return getNode(e) != null;
	}

	/**
	 * Devuelve true si este conjunto contiene todos los elementos del conjunto "other".
	 */
	public boolean containsAllOf(MySetBSTOfInt other) {
		throw new UnsupportedOperationException();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		MySetBSTOfInt other = (MySetBSTOfInt) obj;
		if (root == null) {
			if (other.root != null)
				return false;
		} else if (!root.equals(other.root))
			return false;
		if (size != other.size)
			return false;
		int c = inOrderCompare(root, other.root);
		return c == 0;
	}

	public Integer findMax() {
		if (isEmpty())
			throw new IllegalStateException();
		return findMax(root).key;
	}

	public Integer findMin() {
		if (isEmpty())
			throw new IllegalStateException();
		return findMin(root).key;
	}

	/**
	 * Returns the greatest element less than or equal to the given element, or
	 * {@code null} if there is no such element.
	 *
	 * @param element
	 *            the element
	 * @return the greatest element less than or equal to {@code element}, or {@code null}
	 *         if there is no such element
	 * @throws NullPointerException
	 *             if the specified element is null and...
	 */
	public String floor(int e) {
		throw new UnsupportedOperationException();
	}

	public boolean isEmpty() {
		return root == null;
	}

	/**
	 * Print the tree contents in sorted order.
	 */
	public void print() {
		if (isEmpty()) {
			System.out.println("Empty tree");
		} else {
			printTree(root);
		}
	}

	/**
	 * Print the tree contents level by level, left to right.
	 */
	public void printByLevel() {
		if (!isEmpty()) {
			/*
			 * Recorrer uno a uno los niveles del árbol => proceso iterativo. En cada
			 * iteración se procesarán los nodos del nivel en curso. Los hijos izdo y dcho
			 * de esos nodos son los nodos del nivel siguiente.
			 */
			ArrayList<Node> nodosEnNivelActual = new ArrayList<>();
			nodosEnNivelActual.add(root);

			boolean hayNivelesPorProcesar = true;
			while (hayNivelesPorProcesar) {
				/*
				 * Se procesan los nodos del nivel actual. Al mismo tiemmpo, se calculan
				 * los nodos del nivle siguiente.
				 */
				ArrayList<Node> nodosEnNivelSiguiente = new ArrayList<>();
				for (Node nodo : nodosEnNivelActual) {
					/*
					 * Procesar nodo del nivel actual
					 */
					System.out.println(nodo.key);
					/*
					 * Los hijos izdo y dcho del nodo son nodos del nivel siguiente
					 */
					if (nodo.left != null) {
						nodosEnNivelSiguiente.add(nodo.left);
					}
					if (nodo.right != null) {
						nodosEnNivelSiguiente.add(nodo.right);
					}
				}
				/*
				 * Los nodos a procesar en la siguiente iteración son los del nivel
				 * siguiente.
				 */
				nodosEnNivelActual = nodosEnNivelSiguiente; // \Theta(1) !!!
				/*
				 * Se puede evitar crear la lista de nodos del nivel siguiente en cada
				 * iteración. ES MEJOR, pero no cambiará el orden del tiempo de ejecución.
				 */
			}
		}
	}

	/**
	 * Retira el elemento "e" de este conjunto.
	 * 
	 * Devuelve true si este conjunto es modificado; false en caso contrario (es decir, si
	 * e no estaba en este conjunto).
	 */
	public boolean remove(int e) {
		Node p = getNode(e);
		if (p == null)
			return false;
		removeNode(p);
		assert validate();
		return true;
	}

	/**
	 * Devuelve el número de elementos de este conjunto.
	 */
	public int size() {
		return size;
	}

	/**
	 * Devuelve true si este árbol es un BST.
	 */
	public boolean validate() {
		return true;
	}

	/**
	 * Internal method to find the largest item in a subtree.
	 * 
	 * @param t
	 *            the node that roots the subtree.
	 * @return node containing the largest item.
	 */
	protected Node findMax(Node t) {
		if (t != null) {
			while (t.right != null) {
				t = t.right;
			}
		}
		return t;
	}

	/**
	 * Internal method to find the smallest item in a subtree.
	 * 
	 * @param t
	 *            the node that roots the subtree.
	 * @return node containing the smallest item.
	 */
	protected Node findMin(Node t) {
		if (t == null)
			return null;
		else if (t.left == null)
			return t;
		return findMin(t.left);
	}

	/**
	 * Gets the node corresponding to the specified element; if no such node exists,
	 * returns the node for the least element greater than the specified element; if no
	 * such node exists (i.e., the greatest element in the Tree is less than the specified
	 * element), returns {@code null}.
	 */
	protected Node getCeilingNode(int key) {
		throw new UnsupportedOperationException();
	}

	/**
	 * Gets the node corresponding to the specified element; if no such node exists,
	 * returns the node for the greatest element less than the specified element; if no
	 * such node exists, returns {@code null}.
	 */
	protected Node getFloorNode(int e) {
		throw new UnsupportedOperationException();
	}

	/**
	 * Devuelve el nodo que contiene el elemento especificado.
	 */
	protected Node getNode(int e) {
		Node p = root;
		while (p != null) {
			int cmp = comparator.compare(e, p.key);
			if (cmp < 0) {
				p = p.left;
			} else if (cmp > 0) {
				p = p.right;
			} else
				return p;
		}
		return null;
	}

	/**
	 * Compara uno a uno los elementos de los árboles cuyas raices son los nodos "a" y
	 * "b", según un recorrido en in-orden.
	 * 
	 * Devuelve un valor < 0 si el elemento en curso de a precede al elemento en curso de
	 * b, o si no quedan elementos por comparar en "a" y sí en "b"
	 * 
	 * Devuelve 0, si ambos árboles son iguales.
	 * 
	 * Devuelve un valor < 0 si el elemento en curso de b precede al elemento en curso de
	 * a, o si no quedan elementos por comparar en "b" y sí en "a"
	 */
	protected int inOrderCompare(Node a, Node b) {
		throw new UnsupportedOperationException();
	}

	/**
	 * Devuelve el primer nodo de este árbol cuando se recorre en inorden.
	 */
	protected Node inOrderFirstNode() {
		Node ptr = root;
		if (ptr != null) {
			while (ptr.left != null) {
				ptr = ptr.left;
			}
		}
		return ptr;
	}

	/**
	 * Devuelve el nodo de este árbol, siguiente al nodo especificado, cuando se recorre
	 * en inorden.
	 */
	protected Node inOrderSuccessor(Node t) {
		if (t == null)
			return null;
		else if (t.right != null) {
			Node p = t.right;
			while (p.left != null) {
				p = p.left;
			}
			return p;
		} else {
			Node p = t.parent;
			Node ch = t;
			while (p != null && ch == p.right) {
				ch = p;
				p = p.parent;
			}
			return p;
		}
	}

	protected Node newNode(int e, Node parent) {
		return new Node(e, parent);
	}

	/**
	 * Retira de este árbol el nodo especificado.
	 */
	/*
	 * From Cormen, Leisersson, Resnick, Stein
	 */
	protected void removeNode(Node p) {
		size--;
		// If strictly internal, copy successor's element to p and then make p
		// point to successor.
		if (p.left != null && p.right != null) {
			Node s = inOrderSuccessor(p);
			p.key = s.key;
			p = s;
		}
		// Start fixup at replacement node, if it exists.
		Node replacement = p.left != null ? p.left : p.right;
		/*
		 * replacement == null => p.left == null && p.right == null;
		 */
		if (replacement != null) {
			// Link replacement to parent
			replacement.parent = p.parent;
			if (p.parent == null) {
				root = replacement;
			} else if (p == p.parent.left) {
				p.parent.left = replacement;
			} else {
				p.parent.right = replacement;
			}
			p.left = null;
			p.right = null;
			p.parent = null;
		} else if (p.parent == null) {
			root = null;
		} else {
			unlinkNode(p);
		}
	}

	/**
	 * Método para retirar un nodo de este árbol.
	 * 
	 * Devuelve el padre del nodo retirado.
	 */
	protected Node unlinkNode(Node p) {
		assert p != null;
		Node parent = null;
		if (p.parent != null) {
			parent = p.parent;
			if (p == p.parent.left) {
				p.parent.left = null;
			} else if (p == p.parent.right) {
				p.parent.right = null;
			}
			p.parent = null; // help to GC
		}
		return parent;
	}

	/**
	 * Print a subtree in sorted order.
	 */
	private void printTree(Node t) {
		if (t != null) {
			printTree(t.left);
			System.out.println(t.key);
			printTree(t.right);
		}
	}

	protected static Integer keyOrNull(Node n) {
		return n == null ? null : n.key;
	}

	protected static class Node {
		int key;

		Node	parent;
		Node	left;
		Node	right;

		int xtra; // campo auxiliar para otros usos.

		Node(int e, Node parent) {
			key = e;
			this.parent = parent;
		}
	}
}