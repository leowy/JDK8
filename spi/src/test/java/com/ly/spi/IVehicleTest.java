package com.ly.spi;

import org.junit.Test;

import java.util.Iterator;
import java.util.ServiceLoader;

public class IVehicleTest {

    @Test
    public void testSPI() {
        ServiceLoader<IVehicle> serviceLoader = ServiceLoader.load(IVehicle.class);

        // 调用一
        Iterator<IVehicle> iterator = serviceLoader.iterator();
        while (iterator.hasNext()) {
            IVehicle vehicle =  iterator.next();
            vehicle.desc("test1");
        }

        // 调用二
        for (IVehicle vehicle : serviceLoader) {
            vehicle.desc("test2");
        }
    }

}