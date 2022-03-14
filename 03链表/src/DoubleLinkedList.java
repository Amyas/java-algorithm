public class DoubleLinkedList<E> extends AbstractList<E> {
  private Node<E> first;
  private Node<E> last;

  /** 清空所有元素 */
  @Override
  public void clear() {
    size = 0;
    first = null;
    last = null;
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

    //    if (index == 0) {
    //      first = new Node<>(element, first);
    //    } else {
    //      Node<E> prev = node(index - 1);
    //      prev.next = new Node<>(element, prev.next);
    //    }

    if (index == size) { // 忘最后面添加元素
      Node<E> oldLast = last;
      last = new Node<>(oldLast, element, null);
      if (oldLast == null) { // 这是链表添加的第一个元素
        first = last;
      } else {
        oldLast.next = last;
      }
    } else {
      Node<E> next = node(index);
      Node<E> prev = next.prev;
      Node<E> node = new Node<>(prev, element, next);
      next.prev = node;

      if (prev == null) {
        first = node;
      } else {
        prev.next = node;
      }
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

    //    Node<E> node = first;
    //    if (index == 0) {
    //      first = first.next;
    //    } else {
    //      Node<E> prev = node(index - 1);
    //      node = prev.next;
    //      prev.next = prev.next.next;
    //    }

    Node<E> node = node(index);
    Node<E> prev = node.prev;
    Node<E> next = node.next;

    if (prev == null) {
      first = next;
    } else {
      prev.next = next;
    }

    if (next == null) {
      last = prev;
    } else {
      next.prev = prev;
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

    if (index < (size >> 1)) { // index < size / 2
      Node<E> node = first;
      for (int i = 0; i < index; i++) {
        node = node.next;
      }
      return node;
    } else { // index > size / 2
      Node<E> node = last;
      for (int i = size - 1; i > index; i--) {
        node = node.prev;
      }
      return node;
    }
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
    Node<E> prev;
    Node<E> next;

    public Node(Node<E> prev, E element, Node<E> next) {
      this.element = element;
      this.next = next;
    }
  }
}
