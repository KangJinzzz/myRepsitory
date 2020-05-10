package io;

import java.io.*;

public class FileStreamTest {
    public static void main(String[] args) throws IOException {
        byte bwerite[] = {11,21,3,40,5};
        OutputStream os = new FileOutputStream("test.txt");
        for (int x = 0; x < bwerite.length; x++) {
            os.write(bwerite[x]);
        }
        os.close();

        InputStream is = new FileInputStream("test.txt");
        int size = is.available();

        for (int i = 0; i < size; i++) {
            System.out.println(is.read() + " ");
        }
        is.close();
    }
}
