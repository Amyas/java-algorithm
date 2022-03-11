public abstract class AbstractList<E> implements List<E> {
  protected int size;

  /**
   * 元素数量
   */
  @Override
  public int size() {
    return size;
  }

  /**
   * 是否为空
   */
  @Override
  public boolean isEmpty() {
    return size == 0;
  }

  /**
   * 是否包含某个元素
   *
   * @param element
   */
  @Override
  public boolean contains(E element) {
    return indexOf(element) != ELEMENT_NOT_FOUND;
  }


  /**
   * 添加元素到尾部
   *
   * @param element
   */
  @Override
  public void add(E element) {
    add(size, element);
  }

  /**
   * 异常抛出
   *
   * @param index
   */
  protected void outOfBounds(int index) {
    throw new IndexOutOfBoundsException("Index:" + index + ", Size:" + size);
  }

  /**
   * 越界检查
   *
   * @param index
   */
  protected void rangeCheck(int index) {
    if (index < 0 || index >= size) {
      outOfBounds(index);
    }
  }

  /**
   * 越界检查for add
   *
   * @param index
   */
  protected void rangeCheckForAdd(int index) {
    if (index < 0 || index > size) {
      outOfBounds(index);
    }
  }

}
