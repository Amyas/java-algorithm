public class ArrayList<E> {
  /**
   * 元素的数量
   */
  private int size;

  /**
   * 所有的元素
   */
  private E[] elements;

  private static final int DEFAULT_CAPACITY = 2;
  private static final int ELEMENT_NOT_FOUND = -1;

  public ArrayList(int capaticy) {
    capaticy = (capaticy < DEFAULT_CAPACITY) ? DEFAULT_CAPACITY : capaticy;
    elements = (E[]) new Object[capaticy];
  }

  public ArrayList() {
    this(DEFAULT_CAPACITY);
  }

  /**
   * 清除所有元素
   */
  public void clear() {
    for (int i = 0; i < size; i++) {
      elements[i] = null;
    }
    size = 0;
  }

  /**
   * 元素的数量
   */
  public int size() {
    return size;
  }

  /**
   * 是否为空
   */
  public boolean isEmpty() {
    return size == 0;
  }

  /**
   * 是否包含某个元素
   */
  public boolean contains(E element) {
    return indexOf(element) != ELEMENT_NOT_FOUND;
  }

  /**
   * 获取index位置的元素
   */
  public E get(int index) {
    rangeCheck(index);
    return elements[index];
  }

  /**
   * 设置index位置的元素
   */
  public E set(int index, E element) {
    rangeCheck(index);
    E old = elements[index];
    elements[index] = element;
    return old;
  }

  /**
   * 查看元素的索引
   */
  public int indexOf(E element) {
    for (int i = 0; i < size; i++) {
      if (element.equals(elements[i])) return i;
    }
    return ELEMENT_NOT_FOUND;
  }

  /**
   * 在index位置插入一个元素
   */
  public void add(int index, E element) {
    rangeCheckForAdd(index);

    ensureCapaticy(size + 1);

    for (int i = size - 1; i >= index; i--) {
      elements[i + 1] = elements[i];
    }
    elements[index] = element;
    size++;
  }

  /**
   * 添加元素到尾部
   *
   * @param element
   */
  public void add(E element) {
    add(size, element);
  }

  /**
   * 删除index位置的元素
   *
   * @param index
   * @return
   */
  public E remove(int index) {
    rangeCheck(index);
    E old = elements[index];
    for (int i = index + 1; i < size; i++) {
      elements[i - 1] = elements[i];
    }
    size--;

    elements[size] = null;

    return old;
  }

  /**
   * 保证要有capacity的容量
   *
   * @param capacity
   */
  private void ensureCapaticy(int capacity) {
    int oldCapacity = elements.length;
    if (oldCapacity >= capacity) return;

    int newCapacity = oldCapacity * 2;
    E[] newElements = (E[]) new Object[newCapacity];
    for (int i = 0; i < size; i++) {
      newElements[i] = elements[i];
    }
    elements = newElements;
  }

  /**
   * 异常抛出
   *
   * @param index
   */
  private void outOfBounds(int index) {
    throw new IndexOutOfBoundsException("Index:" + index + ", Size:" + size);
  }

  /**
   * 越界检查
   *
   * @param index
   */
  private void rangeCheck(int index) {
    if (index < 0 || index >= size) {
      outOfBounds(index);
    }
  }

  /**
   * 越界检查for add
   *
   * @param index
   */
  private void rangeCheckForAdd(int index) {
    if (index < 0 || index > size) {
      outOfBounds(index);
    }
  }

  @Override
  public String toString() {
    StringBuilder str = new StringBuilder();
    str.append("Size=").append(size).append(", [");
    for (int i = 0; i < size; i++) {
      if (i != 0) {
        str.append(", ");
      }
      str.append(elements[i]);
    }
    str.append("]");
    return str.toString();
  }
}
