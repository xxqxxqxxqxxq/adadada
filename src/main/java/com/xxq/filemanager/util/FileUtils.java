package com.xxq.filemanager.util;

import static com.xxq.filemanager.springJavafxSupport.GUIState.getHostServices;

/**
 * @ClassName :FileUtils
 * @Description:
 * @Author xxq
 * @Date 2020/4/15  17:16
 * @Version V1.0
 **/
public class FileUtils {

    public  void loadPdf(Integer archNo) {
        String path = "/file/" +archNo+".txt";
        System.out.println(path);
                getHostServices().showDocument(getClass()
                        .getResource(path).toString());

    }

}
