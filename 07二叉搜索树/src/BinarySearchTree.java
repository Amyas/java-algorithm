import printer.BinaryTreeInfo;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.Queue;

public class BinarySearchTree<E> implements BinaryTreeInfo {
  private int size;
  private Node<E> root;
  private Comparator<E> comparator;

  public BinarySearchTree(Comparator<E> comparator) {
    this.comparator = comparator;
  }

  public BinarySearchTree() {
    this(null);
  }

  public void add(E element) {
    elementNotNullCheck(element);

    // 添加第一个节点
    if (root == null) {
      root = new Node<>(element, null);
      size++;
      return;
    }

    // 添加的不是第一个节点

    // 1.找到父节点
    Node<E> node = root;
    Node<E> parent = root;
    int cmp = 0;

    while (node != null) {
      cmp = compare(element, node.element);
      parent = node;
      if (cmp > 0) {
        node = node.right;
      } else if (cmp < 0) {
        node = node.left;
      } else {
        node.element = element;
        return;
      }
    }

    Node<E> newNode = new Node<>(element, parent);
    if (cmp > 0) {
      parent.right = newNode;
    } else {
      parent.left = newNode;
    }
    size++;
  }

  public void remove(E element) {
    remove(node(element));
  }

  private void remove(Node<E> node) {
    if (node == null) return;
    size--;

    if (node.hasTwoChildren()) {
      Node<E> s = successor(node);
      node.element = s.element;
      node = s;
    }

    Node<E> replacement = node.left != null ? node.left : node.right;

    if (replacement != null) {
      replacement.parent = node.parent;
      if (node.parent == null) {
        node = replacement;
      } else if (node == node.parent.left) {
        node.parent.left = replacement;
      } else if (node == node.parent.right) {
        node.parent.right = replacement;
      }
    } else if (node.parent == null) {
      root = null;
    } else {
      if (node == node.parent.right) {
        node.parent.right = null;
      } else {
        node.parent.left = null;
      }
    }
  }

  private Node<E> node(E element) {
    Node<E> node = root;
    while (node != null) {
      int cmp = compare(element, node.element);
      if (cmp == 0) return node;
      if (cmp > 0) {
        node = node.right;
      } else {
        node = node.left;
      }
    }
    return null;
  }

  public boolean contains(E element) {
    return false;
  }

  public void clear() {}

  public int size() {
    return size;
  }

  public boolean isEmpty() {
    return size == 0;
  }

  public void preorderTraversal() {
    preorderTraversal(root);
  }

  public void inorderTraversal() {
    inorderTraversal(root);
  }

  public void postorderTraversal() {
    postorderTraversal(root);
  }

  public void levelOrderTraversal() {
    if (root == null) return;

    Queue<Node<E>> queue = new LinkedList<>();
    Node<E> curr = root;
    queue.offer(curr);
    while (!queue.isEmpty()) {
      Node<E> node = queue.poll();
      System.out.println(node.element);
      if (node.left != null) {
        queue.offer(node.left);
      }
      if (node.right != null) {
        queue.offer(node.right);
      }
    }
  }

  public void levelOrder(Visitor<E> visitor) {
    if (root == null || visitor == null) return;

    Queue<Node<E>> queue = new LinkedList<>();
    Node<E> curr = root;
    queue.offer(curr);
    while (!queue.isEmpty()) {
      Node<E> node = queue.poll();
      visitor.visit(node.element);

      if (node.left != null) {
        queue.offer(node.left);
      }
      if (node.right != null) {
        queue.offer(node.right);
      }
    }
  }

  public int height() {
    if (root == null) return 0;
    int height = 0;
    int levelSize = 0;
    Queue<Node<E>> queue = new LinkedList<>();
    queue.offer(root);
    levelSize = 1;

    while (!queue.isEmpty()) {
      Node<E> node = queue.poll();
      levelSize--;

      if (node.left != null) {
        queue.offer(node.left);
      }
      if (node.right != null) {
        queue.offer(node.right);
      }

      if (levelSize == 0) {
        levelSize = queue.size();
        height++;
      }
    }
    return height;
    //    return height(root);
  }

  private int height(Node<E> node) {
    if (node == null) return 0;
    int l = height(node.left);
    int r = height(node.right);
    return 1 + Math.max(l, r);
  }

  // 判断是否为完全二叉树
  public boolean isComplete() {
    if (root == null) return false;
    Queue<Node<E>> queue = new LinkedList<>();
    queue.offer(root);

    boolean leaf = false;
    while (!queue.isEmpty()) {
      Node<E> node = queue.poll();
      if (leaf && !node.isLeaf()) {
        return false;
      }

      if (node.left != null) {
        queue.offer(node.left);
      } else if (node.right != null) {
        return false;
      }

      if (node.right != null) {
        queue.offer(node.right);
      } else {
        leaf = true;
      }

      //      if (node.hasTwoChildren()) {
      //        queue.offer(node.left);
      //        queue.offer(node.right);
      //      } else if (node.left == null && node.right != null) {
      //        return false;
      //      } else {
      //        leaf = true;
      //      }
    }
    return true;
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    toString(root, sb, "");
    return sb.toString();
  }

  private void toString(Node<E> node, StringBuilder sb, String prefix) {
    if (node == null) return;

    sb.append(prefix).append(node.element).append("\n");
    toString(node.left, sb, prefix + "【L】");
    toString(node.right, sb, prefix + "【R】");
  }

  public static interface Visitor<E> {
    void visit(E element);
  }

  private void preorderTraversal(Node<E> node) {
    if (node == null) return;
    System.out.println(node.element);
    preorderTraversal(node.left);
    preorderTraversal(node.right);
  }

  private void inorderTraversal(Node<E> node) {
    if (node == null) return;
    inorderTraversal(node.left);
    System.out.println(node.element);
    inorderTraversal(node.right);
  }

  private void postorderTraversal(Node<E> node) {
    if (node == null) return;
    postorderTraversal(node.left);
    postorderTraversal(node.right);
    System.out.println(node.element);
  }

  private Node<E> predesessor(Node<E> node) {
    if (node == null) return null;

    Node<E> p = node.left;
    if (p != null) {
      while (p.right != null) {
        p = p.right;
      }
      return p;
    }

    while (node.parent != null && node == node.parent.left) {
      node = node.parent;
    }

    return node.parent;
  }

  private Node<E> successor(Node<E> node) {
    if (node == null) return null;

    Node<E> p = node.right;
    if (p != null) {
      while (p.left != null) {
        p = p.left;
      }
      return p;
    }

    while (node.parent != null && node.parent.right != null) {
      node = node.parent;
    }

    return node.parent;
  }

  /** @return 返回值等于0，代表e1和e2相等；返回值大于0，代表e1大于e2；返回值小于0；代表e1小于e2 */
  private int compare(E e1, E e2) {
    //    return e1.compareTo(e2);
    if (comparator != null) {
      return comparator.compare(e1, e2);
    }
    return ((Comparable<E>) e1).compareTo(e2);
  }

  private void elementNotNullCheck(E element) {
    if (element == null) {
      throw new IllegalArgumentException("element must not be null");
    }
  }

  /** who is the root node */
  @Override
  public Object root() {
    return root;
  }

  /**
   * how to get the left child of the node
   *
   * @param node
   */
  @Override
  public Object left(Object node) {
    return ((Node<E>) node).left;
  }

  /**
   * how to get the right child of the node
   *
   * @param node
   */
  @Override
  public Object right(Object node) {
    return ((Node<E>) node).right;
  }

  /**
   * how to print the node
   *
   * @param node
   */
  @Override
  public Object string(Object node) {
    //    return ((Node<E>) node).element;
    Node<E> myNode = (Node<E>) node;
    String parentString = "null";
    if (myNode.parent != null) {
      parentString = myNode.parent.element.toString();
    }
    return myNode.element + "_p(" + parentString + ")";
  }

  private static class Node<E> {
    E element;
    Node<E> left;
    Node<E> right;
    Node<E> parent;

    public Node(E element, Node<E> parent) {
      this.element = element;
      this.parent = parent;
    }

    public boolean isLeaf() {
      return left == null && right == null;
    }

    public boolean hasTwoChildren() {
      return left != null && right != null;
    }
  }
}
