package val.shlang;

import java.nio.file.Path;
import java.nio.file.Paths;

public class ExceptionDemo extends Demo{

    @Override
    public void demo() {
        Path path = Paths.get(".");
        System.out.println(path.toAbsolutePath());
    }

}
