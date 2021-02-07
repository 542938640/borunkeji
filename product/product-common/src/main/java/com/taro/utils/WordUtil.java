package com.taro.utils;

//import sun.misc.BASE64Encoder;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Enumeration;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipOutputStream;

public class WordUtil {
    public static String getImageString(String filename) throws IOException {
        InputStream in = null;
        byte[] data = null;
        try {
            if (filename.indexOf("http://") == 0)
                in = saveToFile(filename);
            else
                in = new FileInputStream(filename);
            data = new byte[in.available()];
            in.read(data);
            in.close();
        } catch (IOException e) {
            throw e;
        } finally {
            if(in != null) in.close();
        }
//        BASE64Encoder encoder = new BASE64Encoder();
//        return data != null ? encoder.encode(data) : "";
        return null;
    }

    public static InputStream saveToFile(String destUrl){
        HttpURLConnection httpUrl = null;
        URL url = null;
        InputStream in = null;
        try {
            url = new URL(destUrl);
            httpUrl = (HttpURLConnection) url.openConnection();
            httpUrl.connect();
            httpUrl.getInputStream();
            in = httpUrl.getInputStream();
            return in;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void outDocx(File documentFile, String docxTemplate, String toFilePath,
                               boolean imageIn, File imageFile) throws IOException {
        try {
            File docxFile = new File(docxTemplate);
            ZipFile zipFile = new ZipFile(docxFile);
            Enumeration<? extends ZipEntry> zipEntrys = zipFile.entries();
            ZipOutputStream zipout = new ZipOutputStream(new FileOutputStream(toFilePath));
            int len = -1;
            byte[] buffer = new byte[1024];
            while (zipEntrys.hasMoreElements()) {
                ZipEntry next = zipEntrys.nextElement();
                InputStream is = zipFile.getInputStream(next);
                zipout.putNextEntry(new ZipEntry(next.toString()));
                if ("word/document.xml".equals(next.toString())) {
                    InputStream in = new FileInputStream(documentFile);
                    while ((len = in.read(buffer)) != -1) {
                        zipout.write(buffer, 0, len);
                    }
                    in.close();
                } else {
                    if (imageIn && "word/media/image1.gif".equals(next.toString())) {
                        InputStream in = new FileInputStream(imageFile);
                        while ((len = in.read(buffer)) != -1) {
                            zipout.write(buffer, 0, len);
                        }
                        in.close();
                    } else {
                        while ((len = is.read(buffer)) != -1) {
                            zipout.write(buffer, 0, len);
                        }
                        is.close();
                    }
                }
            }
            zipout.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
