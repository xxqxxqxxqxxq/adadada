package com.xxq.filemanager.util;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @ClassName :DateUtil
 * @Description:
 * @Author xxq
 * @Date 2020/5/23  17:26
 * @Version V1.0
 **/
public class DateUtil {
    public static String tranfer(Date date){

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return dateFormat.format(date);
    }

}
