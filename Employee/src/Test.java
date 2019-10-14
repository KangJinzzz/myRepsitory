public class Test {
    public static void main(String[] args) {
        Employee em = new Employee("靳曌", 7000, 2019, 10, 8);
        em.raiseSalary(5);
        System.out.println("name = " + em.getName() + ", salary = " + em.getSalary() + ", hire = " +
                                em.getHireDay());
    }
}
