public class Main {
  public static void main(String[] args) {
//    ArrayList<Integer> list = new ArrayList<>();
//    list.add(99);
//    list.add(88);
//    list.add(77);
//    list.add(66);
//    list.add(55);
//    System.out.println(list);
//    list.set(3, 80);
//    Assert.test(list.get(3) == 80);
//
//    list.remove(0);
//    Assert.test(list.get(0) == 88);
//    Assert.test(list.size() == 4);

    ArrayList<Person> persons = new ArrayList<>();
    persons.add(new Person(10, "a"));
    persons.add(new Person(12, "b"));
    persons.add(new Person(13, "c"));

    persons.clear();
    System.out.println(persons);

    System.gc();
  }
}
