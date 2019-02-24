package com.deviceMonitor.util;

import java.util.ArrayList;
import java.util.List;

/**
 * 功能描述： 集合通用操作类型
 * 
 * @param <T>
 */
public class CollectionUtil<T> {
    /**
     * 功能描述：获取多集合的交集
     * 
     * @param <T>allList <T>多集合
     * @return 返回值 集合的交集
     */
    public List<T> retainAllList(List<List<T>> allList) {
        List<T> listTempA = new ArrayList<T>();
        for (int i = 0; i < allList.size(); i++) {
            listTempA = allList.get(i);
            for (List<T> listTempB : allList) {
                listTempA.retainAll(listTempB);
            }
        }

        return listTempA;
    }
}
