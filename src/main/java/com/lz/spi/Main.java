package com.lz.spi;

import java.util.Iterator;
import java.util.ServiceLoader;

/**
 * @Auther: Leowy Zhuang
 * @Date: 2020/8/20 10:41
 * @Description:
 */
public class Main {
    public static void main(String[] args) {
        System.out.println("start");
        ServiceLoader<IVehicle> s = ServiceLoader.load(IVehicle.class);
//        for (IVehicle t : s) {
//            t.desc("test");
//        }
        Iterator<IVehicle> it = s.iterator();
        while (it.hasNext()) {
            IVehicle vehicle = it.next();
            vehicle.desc("zhangw");
        }
    }
}
