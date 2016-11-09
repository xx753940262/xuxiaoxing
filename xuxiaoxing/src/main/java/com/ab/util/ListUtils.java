package com.ab.util;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

/**
 * 描述：List工具类
 * 作者：徐小星 on 2016/10/17 0017 10:32
 * 邮箱：xx@yougudongli.com
 */

public class ListUtils {

    /**
     * 去掉list重复数据
     *
     * @param list
     */
    public static void removeDuplicate(List<?> list)

    {
        Set set = new LinkedHashSet<>();
        set.addAll(list);
        list.clear();
        list.addAll(set);
        System.out.println(" remove duplicate ======00000=============" + list.size());
    }

    public static void removeDuplicateWithOrder(List list) {
        Set set = new HashSet();
        List newList = new ArrayList();
        for (Iterator iter = list.iterator(); iter.hasNext(); ) {
            Object element = iter.next();
            if (set.add(element))
                newList.add(element);
        }
        list.clear();
        list.addAll(newList);
        System.out.println(" remove duplicate ===================" + list.size());
    }

    public static void removeDuplicate1(List list) {
        for (int i = 0; i < list.size() - 1; i++) {
            for (int j = list.size() - 1; j > i; j--) {
                if (list.get(j).equals(list.get(i))) {
                    list.remove(j);
                }
            }
        }
        System.out.println(" remove duplicate ======11111=============" + list.size());
    }
}
