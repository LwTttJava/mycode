package test;

import java.io.*;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

/**
 * @author luotao
 * @date 2022-3-21  21:34
 */
public class ZipTest {

    /**
     *
     * @param ysUrl 待压缩文件路径
     * @param ysFileSaveUrl 压缩文件存储地址
     * @throws IOException
     */
    public static void ys(String ysUrl,String ysFileSaveUrl) throws IOException{
        long start = System.currentTimeMillis();
        ZipOutputStream zos = new ZipOutputStream(new FileOutputStream(new File(ysFileSaveUrl)));
        ys(ysUrl,zos,"");
        long end = System.currentTimeMillis();
        System.out.println("耗时："+(end-start)+" 毫秒");
    }

    /**
     *
     * @param ysUrl 待压缩文件地址
     * @param zos   zip压缩文件流
     * @param temp 上一级的文件目录
     * @throws IOException
     */
    public static void ys(String ysUrl,ZipOutputStream zos,String temp) throws IOException {

        File file = new File(ysUrl);

        File[] files = file.listFiles();
        if(files.length==0){
            //空文件夹压缩
            zos.putNextEntry(new ZipEntry(temp));
        }
        for (File listFile : files) {
            if (listFile.isDirectory()) {
                /**
                 * 目录递归调用
                 * temp 含义： （父级文件位置+当前文件位置）作为参数，就是等于下一级的文件的父级位置
                 */
                ys(listFile.getAbsolutePath(),zos,(temp + listFile.getName()+"/"));
            }else{
                // 非目录文件压缩
                oneEntry(listFile,zos,temp+listFile.getName());
            }
        }
    }

    /**
     * 非文件夹压缩
     * @param f         待压缩文件
     * @param zos       Zip文件输出流
     * @param yscjUrl   文件在Zip中的位置
     * @throws IOException
     */
    public static void oneEntry(File f,ZipOutputStream zos,String yscjUrl) throws IOException {
        BufferedInputStream fis = new BufferedInputStream(new FileInputStream(f));
        ZipEntry ze = new ZipEntry(yscjUrl);
        zos.putNextEntry(ze);

        int length;
        byte bytes[] = new byte[1024];
        while((length = fis.read(bytes))>=0){
            zos.write(bytes,0,length);
        }
        zos.closeEntry();
        fis.close();
    }

    /**
     * "D:\\maven_repo\\com\\alipay\\sdk\\alipay-sdk-java\\3.7.73.ALL\\alipay-sdk-java-3.7.73.ALL.jar"
     */
    public void test(String ysUrl){
        ZipInputStream zip = null;
        List<String> classNames = new ArrayList<String>();
        try {
            zip = new ZipInputStream(new FileInputStream(ysUrl));
            for (ZipEntry entry = zip.getNextEntry(); entry != null; entry = zip.getNextEntry()) {
                if (!entry.isDirectory() && entry.getName().endsWith(".class")) {
                    // This ZipEntry represents a class. Now, what class does it represent?
                    //String className = entry.getName().replace('/', '.'); // including ".class"
                    //classNames.add(className.substring(0, className.length() - ".class".length()));
                    classNames.add(entry.getName().replace('/', '.'));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        classNames.forEach((a)->System.out.println(a));
    }

    public static void main(String[] args) throws IOException {
/*        Arrays.asList(System.getProperty("sun.boot.class.path").split(";")).forEach((a)->System.out.println(a));
        System.out.println();
        System.out.println("java.ext.dirs");
        Arrays.asList(System.getProperty("java.ext.dirs").split(";")).forEach((a)->System.out.println(a));
        System.out.println();
        System.out.println("java.class.path");
        Arrays.asList(System.getProperty("java.class.path").split(";")).forEach((a)->System.out.println(a));*/
        //ys("E:\\桌面\\github学习资料","\"E:\\\\logs\\\\github学习资料\"");

/*        System.out.println(PathList.class.getClassLoader());
        System.out.println(ZipInfo.class.getClassLoader());
        System.out.println(ZipTest.class.getClassLoader());*/
      /*  Date date = Date.valueOf("2022-03-22");
        System.out.println(date.toString());
        System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date));*/
        Timestamp timestamp = new Timestamp(2531543254000L);
        System.out.println(timestamp.toLocalDateTime().toString().replaceAll("T"," "));
        System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(timestamp));
    }
}
