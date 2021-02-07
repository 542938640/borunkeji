
package com.taro.utils;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.util.Enumeration;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipOutputStream;

/**
 * ClassName:ZipUtil <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Date:     2017年12月15日 上午11:06:30 <br/>
 * @author   张宇唯
 * @since    JDK 1.6
 */
public class ZipUtil {  
    public static void zip(String zipFileName,String sourceFileName) throws Exception{
//        File zipFile = new File(zipFileName.substring(zipFileName.lastIndexOf("/")));
//        if (!zipFile.exists()) {  
//        	zipFile.mkdirs();  
//        }  
        System.out.println("压缩中...");
        //创建zip输出流
        ZipOutputStream out = new ZipOutputStream( new FileOutputStream(zipFileName));
        //创建缓冲输出流
        BufferedOutputStream bos = new BufferedOutputStream(out);
        File sourceFile = new File(sourceFileName);
        //调用函数
        compress(out,bos,sourceFile,sourceFile.getName());
        
        bos.close();
        out.close();
        System.out.println("压缩完成");
        
    }
    public static void compress(ZipOutputStream out,BufferedOutputStream bos,File sourceFile,String base) throws Exception{
        //如果路径为目录（文件夹）
        if(sourceFile.isDirectory()){
            //取出文件夹中的文件（或子文件夹）
            File[] flist = sourceFile.listFiles();
            if(flist.length==0){//如果文件夹为空，则只需在目的地zip文件中写入一个目录进入点
                System.out.println(base+"/");
                out.putNextEntry(  new ZipEntry(base+"/") );
            }else{//如果文件夹不为空，则递归调用compress，文件夹中的每一个文件（或文件夹）进行压缩
                for(int i=0;i<flist.length;i++){
                    compress(out,bos,flist[i],base+"/"+flist[i].getName());
                }
            }
        }else{//如果不是目录（文件夹），即为文件，则先写入目录进入点，之后将文件写入zip文件中
            out.putNextEntry( new ZipEntry(base) );
            FileInputStream fos = new FileInputStream(sourceFile);
            BufferedInputStream bis = new BufferedInputStream(fos);
            int tag;
            System.out.println(base);
            //将源文件写入到zip文件中
            while((tag=bis.read())!=-1){
                bos.write(tag);
            }
            bis.close();
            fos.close();
        }
    }
    /**
     * 删除空目录
     * @param dir 将要删除的目录路径
     */
    public static void doDeleteEmptyDir(String dir) {
        boolean success = (new File(dir)).delete();
        if (success) {
            System.out.println("Successfully deleted empty directory: " + dir);
        } else {
            System.out.println("Failed to delete empty directory: " + dir);
        }
    }

    /**
     * 递归删除目录下的所有文件及子目录下所有文件
     * @param dir 将要删除的文件目录
     * @return boolean Returns "true" if all deletions were successful.
     *                 If a deletion fails, the method stops attempting to
     *                 delete and returns "false".
     */
    public static boolean deleteDir(File dir) {
        if (dir.isDirectory()) {
            String[] children = dir.list();//递归删除目录中的子目录下
            for (int i=0; i<children.length; i++) {
                boolean success = deleteDir(new File(dir, children[i]));
                if (!success) {
                    return false;
                }
            }
        }
        // 目录此时为空，可以删除
        return dir.delete();
    }
    /** 
     * 解压到指定目录 
     * @param zipPath   
     * @param descDir  
     */  
    public static void unZipFiles(String zipPath, String descDir) throws IOException {  
        unZipFiles(new File(zipPath), descDir);  
    }  
  
    /** 
     * 解压文件到指定目录 
     * 解压后的文件名，和之前一致 
     * @param zipFile   待解压的zip文件 
     * @param descDir   指定目录 
     */  
    @SuppressWarnings("rawtypes")  
    public static void unZipFiles(File zipFile, String descDir) throws IOException {  
          
        ZipFile zip = new ZipFile(zipFile,Charset.forName("GBK"));//解决中文文件夹乱码  
        String name = zip.getName().substring(zip.getName().lastIndexOf('\\')+1, zip.getName().lastIndexOf('.'));  
          
        File pathFile = new File(descDir+name);  
        if (!pathFile.exists()) {  
            pathFile.mkdirs();  
        }  
          
        for (Enumeration<? extends ZipEntry> entries = zip.entries(); entries.hasMoreElements();) {  
            ZipEntry entry = (ZipEntry) entries.nextElement();  
            String zipEntryName = entry.getName();  
            InputStream in = zip.getInputStream(entry);  
            String outPath = (descDir + name +"/"+ zipEntryName).replaceAll("\\*", "/");  
              
            // 判断路径是否存在,不存在则创建文件路径  
            File file = new File(outPath.substring(0, outPath.lastIndexOf('/')));  
            if (!file.exists()) {  
                file.mkdirs();  
            }  
            // 判断文件全路径是否为文件夹,如果是上面已经上传,不需要解压  
            if (new File(outPath).isDirectory()) {  
                continue;  
            }  
  
            FileOutputStream out = new FileOutputStream(outPath);  
            byte[] buf1 = new byte[1024];  
            int len;  
            while ((len = in.read(buf1)) > 0) {  
                out.write(buf1, 0, len);  
            }  
            in.close();  
            out.close();  
        }  
        return;  
    }  
	      

}  

