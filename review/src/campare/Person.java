package campare;


import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Person {
    public String name;

    public int age;
    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

//    @Override
//    public int compareTo(Person person) {
//        return this.age - person.getAge();
//    }

    public static void main(String[] args) {
        Person p4 = new Person("张曼迎", 100);
        Person p1 = new Person("康佳旺", 22);
        Person p2 = new Person("靳瞾", 21);
        Person p3 = new Person("孙艺谋", 20);
        List<Person> list = new ArrayList<>();
        list.add(p1);
        list.add(p2);
        list.add(p3);
        list.add(p4);
        Collections.sort(list, new PersonComp());
        for (Person p : list) {
            System.out.println(p.toString());
        }

    }
}

class PersonComp implements Comparator<Person> {

    @Override
    public int compare(Person o1, Person o2) {
        return o1.age - o2.age;
    }
}
