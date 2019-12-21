package val.shlang;

import val.shlang.Demo.Demo;

import java.util.Comparator;

public class Main {

    private static Demo demo;

    public static void main(String[] args) {

        demo = new AnonymousDemo();
        demo.run();

    }
}
