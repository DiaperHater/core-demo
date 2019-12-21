package val.shlang;

import val.shlang.Demo;

public class AnonymousDemo extends Demo {

    interface Greeting{void greet();}

    @Override
    public void demo() {
        //anonymous interface implementation
        Greeting greeting = new Greeting() {
            @Override
            public void greet() {
                System.out.println("Hello !!!");
            }
        };

        greeting.greet();

        //anonymous class extension
        Object o = new Object(){
            @Override
            public String toString() {
                return "Hello from Object anonymous subclass !!!";
            }
        };

        System.out.println(o);
    }
}
