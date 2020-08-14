package se;

class A {
    String name;
//    public A() {
//
//    }
    public A(String name) {
        this.name = name;
    }

    public void doSomething() {
        System.out.println("我是" + name);
    }
}

class B extends A{

    public B(String name) {
        super(name);
    }

    public void doSomething() {
        System.out.println("我是" + name);
    }
}

public class extendsTest {
    public static void main(String[] args) {
        A a = new A("A");
        a.doSomething();

        B b = new B("B");
        b.doSomething();
    }
}
