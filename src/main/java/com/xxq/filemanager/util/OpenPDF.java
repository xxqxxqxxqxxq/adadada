package com.xxq.filemanager.util;


import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import java.awt.*;
import java.io.File;


/**
 * @ClassName :OpenPDF
 * @Description:
 * @Author xxq
 * @Date 2020/4/15  13:14
 * @Version V1.0
 **/
public class OpenPDF extends Component {
    public void open(){
        JFileChooser chooseFile = new JFileChooser();
        FileFilter filter = new FileFilter() {
            //要过滤的文件
            public boolean accept(File f) {
                //显示的文件类型
                if (f.isDirectory()) {
                    return true;
                }
                //显示满足条件的文件
                return f.getName().endsWith(".txt") || f.getName().endsWith(".java");
            }

            /**
             * 这就是显示在打开框中
             */
            public String getDescription() {

                return "*.txt,*.java";
            }
        };

        FileFilter filter1 = new FileFilter() {

            public boolean accept(File f) {
                if (f.isFile()) {
                    return true;
                }
                //显示满足条件的文件
                return f.getName().endsWith(".xls");
            }

            /**
             * 这就是显示在打开框中
             */
            public String getDescription() {
                return "*.xls";
            }
        };


        chooseFile.addChoosableFileFilter(filter);
        chooseFile.addChoosableFileFilter(filter1);
        int open = chooseFile.showOpenDialog(this);
        if(open ==JFileChooser.APPROVE_OPTION)

        {
            File f = chooseFile.getSelectedFile();
            Runtime runtime = Runtime.getRuntime();
            try {
                System.out.println(f.getAbsolutePath());
                //打开文件
                runtime.exec("rundll32 url.dll FileProtocolHandler " + f.getAbsolutePath());
            } catch (Exception ex) {
                ex.printStackTrace();
            }

        }
    }

}
