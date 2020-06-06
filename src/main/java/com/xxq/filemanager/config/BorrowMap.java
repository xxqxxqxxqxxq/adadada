package com.xxq.filemanager.config;

import java.util.HashMap;

/**
 * @ClassName :BorrowMap
 * @Description:
 * @Author xxq
 * @Date 2020/6/3  23:06
 * @Version V1.0
 **/

public class BorrowMap {
    private HashMap<String,Integer> map ;
    public  BorrowMap(){
        map = new HashMap<>();
    }

    public HashMap<String, Integer> getMap() {
        return map;
    }

    public void setMap(HashMap<String, Integer> map) {
        this.map = map;
    }
}
