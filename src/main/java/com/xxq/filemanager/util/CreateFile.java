package com.xxq.filemanager.util;

import java.io.*;

/**
 * @ClassName :CreateFile
 * @Description:
 * @Author xxq
 * @Date 2020/4/15  19:34
 * @Version V1.0
 **/
public class CreateFile {
    public static void main(String[] args) throws IOException {
        writeStringToFile("sss",15);
    }
    public static void writeStringToFile(String data,Integer archNo) {
        byte[] sourceByte = data.getBytes();
        String path = "F:/XxqWork/bishe/filemanager/src/main/resources/file/";
        String fileName = archNo+".txt";
        if(null != sourceByte){
            try {
                File file = new File(path+fileName);//文件路径（路径+文件名）
                if (!file.exists()) {   //文件不存在则创建文件，先创建目录
                    File dir = new File(file.getParent());
                    dir.mkdirs();
                    file.createNewFile();
                }
                FileOutputStream outStream = new FileOutputStream(file); //文件输出流将数据写入文件
                outStream.write(sourceByte);
                outStream.close();
            } catch (Exception e) {
                e.printStackTrace();
                // do something
            } finally {
                // do something
            }
        }
    }

}
