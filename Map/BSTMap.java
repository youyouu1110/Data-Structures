package Map;

import java.util.ArrayList;

import Set.FileOperation;

public class BSTMap<K extends Comparable<K>, V> implements Map<K, V> {

	public class Node{
		public K key;
		public V value;
		public Node left;
		public Node right;
		
		public Node(K key, V value) {
			this.key = key;
			this.value = value;
			this.left = this.right = null;
		}
	}
	private Node root;
	private int size;
	
	@Override
	public void add(K key, V value) {
		root = add(root, key, value);
	}
	
	private Node add(Node node, K key, V value) {
		if(node == null) {
			size ++;
			return new Node(key,value);
		}
		
		if(key.compareTo(node.key) < 0)
			node.left = add(node.left, key, value);
		else if(key.compareTo(node.key) > 0)
			node.right = add(node.right, key, value);
		else
			node.value = value;
		return node;
		
	}
	
	// ������nodeΪ���Ķ�������������Сֵ���ڵĽڵ�
    private Node minimum(Node node){
        if(node.left == null)
            return node;
        return minimum(node.left);
    }

    // ɾ������nodeΪ���Ķ����������е���С�ڵ�
    // ����ɾ���ڵ���µĶ����������ĸ�
    private Node removeMin(Node node){

        if(node.left == null){
            Node rightNode = node.right;
            node.right = null;
            size --;
            return rightNode;
        }

        node.left = removeMin(node.left);
        return node;
    }

	@Override
	public V remove(K key) {
		Node node = getNode(root, key);
        if(node != null){
            root = remove(root, key);
            return node.value;
        }
        return null;
	}
	
	private Node remove(Node node, K key){

        if( node == null )
            return null;

        if( key.compareTo(node.key) < 0 ){
            node.left = remove(node.left , key);
            return node;
        }
        else if(key.compareTo(node.key) > 0 ){
            node.right = remove(node.right, key);
            return node;
        }
        else{   // key.compareTo(node.key) == 0

            // ��ɾ���ڵ�������Ϊ�յ����
            if(node.left == null){
                Node rightNode = node.right;
                node.right = null;
                size --;
                return rightNode;
            }

            // ��ɾ���ڵ�������Ϊ�յ����
            if(node.right == null){
                Node leftNode = node.left;
                node.left = null;
                size --;
                return leftNode;
            }

            // ��ɾ���ڵ�������������Ϊ�յ����

            // �ҵ��ȴ�ɾ���ڵ�����С�ڵ�, ����ɾ���ڵ�����������С�ڵ�
            // ������ڵ㶥���ɾ���ڵ��λ��
            Node successor = minimum(node.right);
            successor.right = removeMin(node.right);
            successor.left = node.left;

            node.left = node.right = null;

            return successor;
        }
    }

	@Override
	public boolean contains(K key) {
		return getNode(root,key) != null;
	}

	@Override
	public V get(K key) {
		Node node = getNode(root, key);
		return node == null ? null : node.value;
	}

	@Override
	public void set(K key, V newValue) {
		Node node = getNode(root, key);
		if(node == null)
			throw new IllegalArgumentException(key + "doesn't exist");
		node.value = newValue;
	}

	@Override
	public int getSize() {
		return size;
	}

	@Override
	public boolean isEmpty() {
		return size == 0;
	}
	
	private Node getNode(Node node, K key) {
		if(node == null)
			return null;
		if(key.compareTo(node.key) < 0)
			return getNode(node.left, key);
		else if(key.compareTo(node.key) > 0)
			return getNode(node.right, key);
		else
			return node;
	}
	
	public static void main(String[] args){

        System.out.println("Pride and Prejudice");

        ArrayList<String> words = new ArrayList<>();
        if(FileOperation.readFile("pride-and-prejudice.txt", words)) {
            System.out.println("Total words: " + words.size());

            BSTMap<String, Integer> map = new BSTMap<>();
            for (String word : words) {
                if (map.contains(word))
                    map.set(word, map.get(word) + 1);
                else
                    map.add(word, 1);
            }

            System.out.println("Total different words: " + map.getSize());
            System.out.println("Frequency of PRIDE: " + map.get("pride"));
            System.out.println("Frequency of PREJUDICE: " + map.get("prejudice"));
        }

        System.out.println();
    }

}
