public class House {

    public int foo() {
        return 1;
    }

    class Animal {

        public int foo() {
            return 2;
        }
    }

    ;

    class Cat extends Animal {

        void hi() {
            foo();
            this.foo();
            House.this.foo();
        }
    }

    ;
}
