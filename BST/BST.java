package BST;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;
import java.util.Stack;

public class BST<E extends Comparable<E>> {

	private class Node{
		public E e;
		public Node left;
		public Node right;
		
		public Node(E e) {
			this.e = e;
			left = null;
			right = null;
		}
	}
	private Node root;
	private int size;
	
	public BST() {
		root = null;
		size = 0;
	}
	
	public int getSize() {
		return size;
	}
	
	public boolean isEmpty() {
		return size == 0;
	}
	
	public void add(E e) {
		root = add(root, e);	
	}
	
	// 向以node为根的二分搜索树中插入元素e，递归算法
    // 返回插入新节点后二分搜索树的根
	private Node add(Node node, E e) {
		if(node == null) {
			size ++;
			return new Node(e);
		}
		
		if(e.compareTo(node.e) < 0)
			node.left = add(node.left, e);
		else if(e.compareTo(node.e) > 0)
			node.right = add(node.right, e);
		
		return node;
	}
	
	public boolean contains(E e) {
		return contains(root, e);
	}
	
	private boolean contains(Node node, E e) {
		if(node == null)
			return false;
		if(e.compareTo(node.e) == 0)
			return true;
		else if(e.compareTo(node.e) < 0)
			return contains(node.left, e);
		else //e.compareTo(node.e) > 0
			return contains(node.right, e);
	}
	
	//前序遍历
	public void preOrder() {
		preOrder(root);
	}
	
	private void preOrder(Node node) {
		if(node == null)
			return;
		System.out.println(node.e);
		preOrder(node.left);
		preOrder(node.right);
	}
	
	//前序遍历的非递归实现
	public void preOrder2() {
		Stack<Node> stack = new Stack<>();
		
		stack.push(root);
		while(!stack.isEmpty()) {
			Node cur = stack.pop();
			System.out.println(cur.e);
			if(cur.right != null)
				stack.push(cur.right);
			if(cur.left != null)
				stack.push(cur.left);
		}
	}
	
	
	//中序遍历
	public void inOrder() {
		inOrder(root);
	}
	
	private void inOrder(Node node) {
		if(node == null)
			return;
		inOrder(node.left);
		System.out.println(node.e);
		inOrder(node.right);
	}
	
	//后序遍历
	public void postOrder() {
		postOrder(root);
	}
		
	private void postOrder(Node node) {
		if(node == null)
			return;
		inOrder(node.left);
		inOrder(node.right);
		System.out.println(node.e);
	}
	
	//层序遍历
	public void levelOrder() {
		Queue<Node> q = new LinkedList<>();
		q.add(root);
		while(!q.isEmpty()) {
			Node cur = q.remove();
			System.out.println(cur.e);
			if(cur.left != null)
				q.add(cur.left);
			if(cur.right != null)
				q.add(cur.right);
		}
	}
	
	public E minimum() {
		if(size == 0)
			throw new IllegalArgumentException("BST is empty!");
		Node minNode = minimum(root);
		return minNode.e;
	}
	
	private Node minimum(Node node) {
		if(node.left == null)
			return node;
		return minimum(node.left);
	}
	
	public E maximum() {
		if(size == 0)
			throw new IllegalArgumentException("BST is empty!");
		Node maxNode = maximum(root);
		return maxNode.e;
	}
	
	private Node maximum(Node node) {
		if(node.right == null)
			return node;
		return minimum(node.right);
	}
	
	public E removeMin() {
		E ret = minimum();
		root = removeMin(root);
		return ret;
		
	}
	
	private Node removeMin(Node node) {
		if(node.left == null) {
			Node rightNode = node.right;
			node.right = null;
			size --;
			return rightNode;
		}
		node.left = removeMin(node.left);
		return node;
	}
	
	public E removeMax() {
		E ret = maximum();
		root = removeMax(root);
		return ret;
	}
	
	private Node removeMax(Node node) {
		if(node.right == null) {
			Node leftNode = node.left;
			node.left = null;
			size --;
			return leftNode;
		}
		node.right = removeMax(node.right);
		return node;
	}
	
	public void remove(E e) {
		root = remove(root, e);
	}
	
	// 删除掉以node为根的二分搜索树中值为e的节点, 递归算法
    // 返回删除节点后新的二分搜索树的根
	private Node remove(Node node, E e) {
		if(node == null)
			return null;
		if(e.compareTo(node.e) < 0) {
			node.left = remove(node.left, e);
			return node;
		}
		else if(e.compareTo(node.e) > 0) {
			node.right = remove(node.right, e);
			return node;
		}
		else { // node.e == e
			
			if(node.left == null) {
				Node rightNode = node.right;
				node.right = null;
				size --;
				return rightNode;
			}
			if(node.right == null) {
				Node leftNode = node.left;
				node.left = null;
				size --;
				return leftNode;
			}
		    // 被删除结点有左右两颗子树
		    // 找到比待删除节点大的最小节点, 即待删除节点右子树的最小节点
	        // 用这个节点顶替待删除节点的位置
			Node successor = minimum(node.right);
			successor.right = removeMin(node.right);
			successor.left = node.left;
			node.left = node.right = null;
			return successor;
				
			
		}
	}
	
	@Override
	public String toString() {
		StringBuilder res = new StringBuilder();
		generateBSTString(root, 0, res);
		return res.toString();
	}
	
	private void generateBSTString(Node node, int depth, StringBuilder res) {
		if(node == null){
            res.append(generateDepthString(depth) + "null\n");
            return;
        }

        res.append(generateDepthString(depth) + node.e + "\n");
        generateBSTString(node.left, depth + 1, res);
        generateBSTString(node.right, depth + 1, res);
	}
	
	private String generateDepthString(int depth) {
		StringBuilder res = new StringBuilder();
		for(int i = 0 ; i < depth ; i ++)
            res.append("--");
        return res.toString();
	}
	
	
	
	public static void main(String[] args) {
		BST<Integer> bst = new BST<>();
        Random random = new Random();

        int n = 1000;

        // test removeMin
        for(int i = 0 ; i < n ; i ++)
            bst.add(random.nextInt(10000));

        ArrayList<Integer> nums = new ArrayList<>();
        while(!bst.isEmpty())
            nums.add(bst.removeMin());

        System.out.println(nums);
        for(int i = 1 ; i < nums.size() ; i ++)
            if(nums.get(i - 1) > nums.get(i))
                throw new IllegalArgumentException("Error!");
        System.out.println("removeMin test completed.");


        // test removeMax
        for(int i = 0 ; i < n ; i ++)
            bst.add(random.nextInt(10000));

        nums = new ArrayList<>();
        while(!bst.isEmpty())
            nums.add(bst.removeMax());

        System.out.println(nums);
        for(int i = 1 ; i < nums.size() ; i ++)
            if(nums.get(i - 1) < nums.get(i))
                throw new IllegalArgumentException("Error!");
        System.out.println("removeMax test completed.");
	}
	
	
}
