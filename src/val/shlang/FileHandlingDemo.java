package val.shlang;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class FileHandlingDemo extends Demo {

    private static final String OK_POSTFIX = " is OK!";

    @Override
    public void demo() {

        bufferedRWDemo();
        printRWDemo();
        fileInOutStreamDemo();
        dataInOutStream();
        randomAccessFileDemo();
    }

    private void randomAccessFileDemo() {
        Path path = createTmpFile();

        try (RandomAccessFile file = new RandomAccessFile(path.toFile(), "rw")){

            String value = "condom randomAccessFileDemo()";
            file.writeUTF(value);

            file.seek(9);
            value = file.readLine();

            System.out.println(value + OK_POSTFIX);

        } catch (IOException e) {
            e.printStackTrace();
            System.exit(-1);
        }
    }

    private void dataInOutStream(){
        Path path = createTmpFile();

        try(DataOutputStream dos =
                    new DataOutputStream(
                            new BufferedOutputStream(
                                    new FileOutputStream(path.toFile()))))
        {
            double[] doubles = {3.14, 95.28, 3.09};
            for (int i = 0; i < doubles.length; i++) {
                dos.writeDouble(doubles[i]);
            }

        }catch (IOException e){
            e.printStackTrace();
            System.exit(-1);
        }

        try (DataInputStream dis =
                     new DataInputStream(
                             new BufferedInputStream(
                                     new FileInputStream(path.toFile())
                             )))
        {

            ArrayList<Double> doubles = new ArrayList<>();
            double n;
            try {
                while ((n = dis.readDouble()) != 0 ){
                    doubles.add(n);
                }
            } catch (EOFException e) {
                System.out.println("dataInOutStream()" + doubles.toString() + OK_POSTFIX);
            }

        }catch (IOException e){
            e.printStackTrace();
            System.exit(-1);
        }
    }

    private void fileInOutStreamDemo() {
        Path path = createTmpFile();

        try (FileOutputStream outputStream = new FileOutputStream(path.toFile())){

            String value = "fileInOutStreamDemo()";
            byte[] bytes = value.getBytes();
            outputStream.write(bytes);

        } catch (IOException e) {
            e.printStackTrace();
            System.exit(-1);
        }

        try ( FileInputStream inputStream = new FileInputStream(path.toFile())){

            byte[] bytes = inputStream.readAllBytes();
            String value = new String(bytes);
            System.out.println(value + OK_POSTFIX);

        } catch (IOException e){
            e.printStackTrace();
            System.exit(-1);
        }
    }

    private void printRWDemo() {
        Path path = createTmpFile();

        try(PrintWriter printWriter = new PrintWriter(new FileWriter(path.toFile()))){
            String value = "printRWDemo() is OK!";
            printWriter.println(value);
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(-1);
        }

        try (Scanner scanner = new Scanner(path)) {
            if(scanner.hasNextLine()){
                String value = scanner.nextLine();
                System.out.println(value);
            }else {
                throw new IOException("VAL_Empty_file_VAL");
            }
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(-1);
        }
    }

    private void bufferedRWDemo() {
        Path path = createTmpFile();

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(path.toFile()))){
            String value = "bufferedRWDemo() is OK!";
            writer.write(value);
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(-1);
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(path.toFile()))) {
          String value = reader.readLine();
          System.out.println(value);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private Path createTmpFile() {
        Path path = null;
        try {
             path = Files.createTempFile(null, null);
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(-1);
        }

        return path;
    }
}
