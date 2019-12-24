package val.shlang;

import java.util.Arrays;

public class Main {

    public static void main(String[] args) {

        Demo[] demos = {new AnonymousDemo(),
                        new ExceptionDemo(),
                        new FileHandlingDemo()};

        Arrays.stream(demos).forEach(Demo::run);
    }
}
