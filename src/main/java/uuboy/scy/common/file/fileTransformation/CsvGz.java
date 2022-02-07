package uuboy.scy.common.file.fileTransformation;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.GZIPOutputStream;

/**
 * Project: uuboycommon
 * Package: uuboy.scy.common.file.fileTransformation
 * CsvGz.java
 * Description:
 * User: uuboyscy
 * Created Date: 2/7/22
 * Version: 0.0.1
 */

public class CsvGz {

    public static void saveCsvGzFile(String path, String content) throws IOException {
        FileOutputStream outputStream = new FileOutputStream(path);
        GZIPOutputStream gzipOutputStream = new GZIPOutputStream(outputStream);
        gzipOutputStream.write(content.getBytes());
        gzipOutputStream.close();
    }

    public static void main(String[] args) {
        String str1 = "123123123";
        str1 += "\naaaaa";
        StringBuilder sb = new StringBuilder();
        sb.append("123123123");
        sb.append("\naaaaa");

//        String idString = "1234567890|22,35,11";
//        for (int i = 36 ; i < 40 ; i++) idString += "," + i;
//        idString += "\n1234567890|22,35,11";


        try {
            saveCsvGzFile("./string3.csv.gz", str1);
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            saveCsvGzFile("./stringBuilder3.csv.gz", sb.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }


//        try {
//            saveCsvGzFile("./string1.csv.gz", idString);
//        } catch (IOException e){
//            e.printStackTrace();
//        }

        //////////////////////

        String idString = "1234567890|22,35,11";
        for (int i = 36 ; i < 40 ; i++) idString += "," + i;
        idString += "\n1234567890|22,35,11";

//        ByteArrayOutputStream baos = new ByteArrayOutputStream(10);
//        ObjectOutputStream out = null;

//        out = new ObjectOutputStream(baos);
//
//        out.writeObject(id);
//        out.flush();
//        out.close();
//        System.out.println(baos.toByteArray().length);
//        baos.reset();

//        out = new ObjectOutputStream(baos);
//
//        out.writeObject(idString);
//        out.flush();
//        out.close();
//        System.out.println(baos.toByteArray().length);
//        baos.reset();

//        byte[] buffer = new byte[2048];
        String pathname = "./test_idString_getbytes5.csv.gz";
//        FileInputStream inputStream = new FileInputStream("./test.csv");
        FileOutputStream outputStream = null;
        try {
            outputStream = new FileOutputStream(pathname);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        GZIPOutputStream gzipOutputStream = null;
        try {
            gzipOutputStream = new GZIPOutputStream(outputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            gzipOutputStream.write(idString.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
//        gzipOutputStream.write(baos.toByteArray());
//        int length;
//        while ((length = inputStream.read(buffer)) > 0) {
//            System.out.println(new String(buffer));
//            out = new ObjectOutputStream(baos);
//            out.write(buffer);
//            out.flush();
//            out.close();
//            gzipOutputStream.write(baos.toByteArray());
//            baos.reset();
//            gzipOutputStream.write(buffer, 0, length);
//        }
//        inputStream.close();
        try {
            gzipOutputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
