package com.xxq.filemanager.util;

import javafx.scene.image.Image;
import org.springframework.beans.factory.annotation.Autowired;

import java.awt.*;
import java.io.File;
import java.io.IOException;

import static com.xxq.filemanager.springJavafxSupport.GUIState.getHostServices;

/**
 * @ClassName :FileUtils
 * @Description:
 * @Author xxq
 * @Date 2020/4/15  17:16
 * @Version V1.0
 **/
public class FileUtils {
    public  void loadPdf(String archNo,String path1) throws IOException {
        if(archNo!=null&&path1==null){
            String path = "/file/" +archNo+".txt";
            System.out.println(path);
            getHostServices().showDocument(getClass()
                    .getResource(path).toString());
        }else if(archNo==null&&path1!=null){
            String filePath ="file:"+path1;
            System.out.println(filePath);
            Desktop.getDesktop().open(new File(path1));
        }



    }

    public static void main(String[] args) throws IOException {
        FileUtils fileUtils = new FileUtils();
       String path= "C:/Users/xxq/Documents/user.sql";
        String filePath=path;
        fileUtils.loadPdf(null,path);
    }
    public  void loadFile(String path) {

        String filePath ="file:"+path;
        System.out.println(path);
        getHostServices().showDocument(getClass()
                .getResource(path).toString());

    }

}
