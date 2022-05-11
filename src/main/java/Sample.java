import utils.Copyable;

public class Sample {

    public static class A implements Copyable {
        public Object copy() {
            System.out.println("A");
            return null;
        }
    }

    public static class B implements Copyable {
        public Object copy() {
            System.out.println("B");
            return null;
        }
    }

    public static class C<T> implements Copyable {

        private T[][] a;

        public C() {
            a = (T[][]) new Object[5][5];
        }

        public Object copy() {
            System.out.println("C generic.");
            return null;
        }
    }

    public static void main(String[] args) {
        A a = new A();
        B b = new B();

        C<A> c = new C<>();
    }

}
