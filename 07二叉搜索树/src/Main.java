import printer.BinaryTrees;

import java.util.Comparator;

public class Main {

  private static class PersonComparator implements Comparator<Person> {
    @Override
    public int compare(Person e1, Person e2) {
      return e1.getAge() - e2.getAge();
    }
  }

  private static class PersonComparator2 implements Comparator<Person> {
    @Override
    public int compare(Person e1, Person e2) {
      return e2.getAge() - e1.getAge();
    }
  }

  static void test1() {
    Integer data[] = new Integer[] {7, 4, 9, 2, 5, 8, 11, 3, 12, 1};
    BinarySearchTree<Integer> bst = new BinarySearchTree<>();

    for (int i = 0; i < data.length; i++) {
      bst.add(data[i]);
    }

    BinaryTrees.println(bst);

    //    bst.preorderTraversal();
    //    bst.inorderTraversal();
    //    bst.postorderTraversal();
    //    bst.levelOrderTraversal();

    //    bst.levelOrder(
    //        new BinarySearchTree.Visitor<Integer>() {
    //          @Override
    //          public void visit(Integer element) {
    //            System.out.print("_" + element + "_");
    //          }
    //        });
    System.out.println(bst.height());
  }

  static void test2() {
    Integer[] data = new Integer[] {7, 4, 9, 2, 5, 8, 11, 3, 12, 1};
    BinarySearchTree<Person> bst1 = new BinarySearchTree<>();
    for (int i = 0; i < data.length; i++) {
      bst1.add(new Person(data[i]));
    }
    BinaryTrees.println(bst1);
  }

  public static void main(String[] args) {
    test1();
    //
    //    BinarySearchTree<Person> bst2 = new BinarySearchTree<>(new PersonComparator());
    //    bst2.add(new Person(10));
    //    bst2.add(new Person(20));
    //
    //    BinarySearchTree<Person> bst3 = new BinarySearchTree<>(new PersonComparator2());
    //    bst2.add(new Person(10));
    //    bst2.add(new Person(20));
  }
}
