package val.shlang;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;

public class ExceptionDemo extends Demo{

    @Override
    public void demo() {
        checkedException();
        System.out.println("****************************************");
        uncheckedException();
    }

    private void uncheckedException() {
        try {
            int i = 1 / 0;
        } catch (RuntimeException e){
            e.printStackTrace(System.out);
        }
    }

    private void checkedException() {
        Path path = null;
        try {
            path = Files.createTempDirectory("tmp");
            Files.writeString(path, "Hello world!", StandardCharsets.UTF_8, StandardOpenOption.APPEND);
        } catch (AccessDeniedException e) {
            e.printStackTrace(System.out);
        } catch (IOException e) {
            e.printStackTrace(System.out);
        }

        if (path != null){System.out.println(path.toAbsolutePath());}
    }

}
