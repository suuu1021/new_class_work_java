package ch02;

import java.io.FileInputStream;
import java.io.FileOutputStream;

public class Test1 {

    public static void main(String[] args) {

        try (FileInputStream in = new FileInputStream("my1.txt")) {
            FileOutputStream out = new FileOutputStream("my2.txt");
            int readData;
            while ((readData = in.read()) != -1) {
                System.out.print((char) readData);
                out.write(readData);
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
