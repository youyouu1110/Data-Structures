package Set;

import java.util.ArrayList;

import BST.BST;

public class BSTSet<E extends Comparable<E>> implements Set<E> {

	
	private BST<E> bst;
	
	public BSTSet() {
		bst = new BST<>();
	}
	
	@Override
	public void add(E e) {
		bst.add(e);
	}

	@Override
	public void remove(E e) {
		bst.remove(e);
	}

	@Override
	public boolean contains(E e) {
		return bst.contains(e);
	}

	@Override
	public int getSize() {
		return bst.getSize();
	}

	@Override
	public boolean isEmpty() {
		return bst.isEmpty();
	}

	public static void main(String[] args) {
		 System.out.println("Pride and Prejudice");

	        ArrayList<String> words1 = new ArrayList<>();
	        if(FileOperation.readFile("pride-and-prejudice.txt", words1)) {
	            System.out.println("Total words: " + words1.size());

	            LinkedListSet<String> set1 = new LinkedListSet<>();
	            for (String word : words1)
	                set1.add(word);
	            System.out.println("Total different words: " + set1.getSize());
	        }

	        System.out.println();
	}
	
	
}
