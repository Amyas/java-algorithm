public class LinkedList<E> extends AbstractList<E> {
  private Node<E> first;

  /** 清空所有元素 */
  @Override
  public void clear() {
    size = 0;
    first = null;
  }

  /**
   * 在index位置插入一个元素
   *
   * @param index
   * @param element
   */
  @Override
  public void add(int index, E element) {
    rangeCheckForAdd(index);

    if (index == 0) {
      first = new Node<>(element, first);
    } else {
      Node<E> prev = node(index - 1);
      prev.next = new Node<>(element, prev.next);
    }
    size++;
  }

  /**
   * 删除index位置的元素
   *
   * @param index
   */
  @Override
  public E remove(int index) {
    rangeCheck(index);

    Node<E> node = first;
    if (index == 0) {
      first = first.next;
    } else {
      Node<E> prev = node(index - 1);
      node = prev.next;
      prev.next = prev.next.next;
    }
    size--;
    return node.element;
  }

  /**
   * 获取index位置的元素
   *
   * @param index
   */
  @Override
  public E get(int index) {
    return node(index).element;
  }

  /**
   * 设置index位置的元素
   *
   * @param index
   * @param element
   */
  @Override
  public E set(int index, E element) {
    Node<E> node = node(index);
    E old = node.element;
    node.element = element;
    return old;
  }

  /**
   * 查看元素的索引
   *
   * @param element
   */
  @Override
  public int indexOf(E element) {
    Node<E> node = first;
    if (element == null) {
      for (int i = 0; i < size; i++) {
        if (node.element == null) return i;
        node = node.next;
      }
    } else {
      for (int i = 0; i < size; i++) {
        if (element.equals(node.element)) return i;
        node = node.next;
      }
    }
    return ELEMENT_NOT_FOUND;
  }

  /**
   * 获取index对应节点
   *
   * @param index
   * @return
   */
  private Node<E> node(int index) {
    rangeCheck(index);

    Node<E> node = first;
    for (int i = 0; i < index; i++) {
      node = node.next;
    }
    return node;
  }

  @Override
  public String toString() {
    StringBuilder str = new StringBuilder();
    str.append("Size=").append(size).append(", [");
    Node<E> node = first;
    for (int i = 0; i < size; i++) {
      if (i != 0) {
        str.append(", ");
      }
      str.append(node.element);

      node = node.next;
    }
    str.append("]");
    return str.toString();
  }

  private static class Node<E> {
    E element;
    Node<E> next;

    public Node(E element, Node<E> next) {
      this.element = element;
      this.next = next;
    }
  }
}
