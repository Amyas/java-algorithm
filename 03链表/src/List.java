public interface List<E> {
  static final int DEFAULT_CAPACITY = 2;
  static final int ELEMENT_NOT_FOUND = -1;

  /**
   * 清空所有元素
   */
  void clear();

  /**
   * 元素数量
   */
  int size();

  /**
   * 是否为空
   */
  boolean isEmpty();

  /**
   * 是否包含某个元素
   */
  boolean contains(E element);

  /**
   * 在index位置插入一个元素
   */
  void add(int index, E element);

  /**
   * 添加元素到尾部
   */
  void add(E element);

  /**
   * 删除index位置的元素
   */
  E remove(int index);


  /**
   * 获取index位置的元素
   */
  public E get(int index);

  /**
   * 设置index位置的元素
   */
  public E set(int index, E element);

  /**
   * 查看元素的索引
   */
  int indexOf(E element);
}
